import React, { useState, useEffect } from 'react';
import {
    Container,
    TextField,
    Button,
    Grid,
    Typography, FormControl, InputLabel, Select, MenuItem, List, ListItem
} from '@mui/material';
import { useParams, useNavigate } from 'react-router-dom';
import axios from 'axios';
import debounce from 'lodash.debounce';

const EditTour = () => {
    const { id } = useParams();
    const navigate = useNavigate();
    const [formData, setFormData] = useState({
        name: '',
        description: '',
        from_location: '',
        to_location: '',
        transport_type: '',
        distance: '',
        time: '',
        startCoordinates: '',
        endCoordinates: ''
    });

    const [fromSuggestions, setFromSuggestions] = useState([]);
    const [toSuggestions, setToSuggestions] = useState([]);

    useEffect(() => {
        const fetchTour = async () => {
            try {
                const response = await axios.get(`http://localhost:8080/tour/${id}`);
                setFormData(response.data);
            } catch (error) {
                console.error('Error fetching tour:', error);
            }
        };

        fetchTour();
    }, [id]);

    const handleChange = (event) => {
        const { name, value } = event.target;
        setFormData((prevData) => ({ ...prevData, [name]: value }));

        if (name === 'from_location') {
            fetchFromSuggestions(value);
        } else if (name === 'to_location') {
            fetchToSuggestions(value);
        }
    };

    const fetchFromSuggestions = debounce(async (query) => {
        if (query.length < 3) {
            setFromSuggestions([]);
            return;
        }

        try {
            const response = await axios.get(`http://localhost:8080/autocomplete?text=${query}`);
            setFromSuggestions(response.data);
        } catch (error) {
            console.error('Error fetching from location suggestions:', error);
        }
    }, 300);

    const fetchToSuggestions = debounce(async (query) => {
        if (query.length < 3) {
            setToSuggestions([]);
            return;
        }

        try {
            const response = await axios.get(`http://localhost:8080/autocomplete?text=${query}`);
            setToSuggestions(response.data);
        } catch (error) {
            console.error('Error fetching to location suggestions:', error);
        }
    }, 300);

    const handleSuggestionClick = (field, suggestion) => {
        setFormData((prevData) => ({ ...prevData, [field]: suggestion }));
        if (field === 'from_location') {
            setFromSuggestions([]);
        } else {
            setToSuggestions([]);
        }
    };

    const searchAddress = async (address) => {
        try {
            const response = await axios.get(`http://localhost:8080/searchAddress?text=${address}`);
            return response.data;
        } catch (error) {
            console.error('Error searching address:', error);
            return null;
        }
    };

    const handleSubmit = async (event) => {
        event.preventDefault();

        try {
            const fromCoordinates = await searchAddress(formData.from_location);
            const toCoordinates = await searchAddress(formData.to_location);
            if (fromCoordinates && toCoordinates) {
                const data = {
                    ...formData,
                    startCoordinates: fromCoordinates,
                    endCoordinates: toCoordinates
                };
                await axios.put(`http://localhost:8080/tour/${id}`, data);
                navigate(`/tour/${id}`);
            } else {
                console.error('Error searching address');
            }
        } catch (error) {
            console.error('Error updating tour:', error);
        }
    };

    return (
        <Container maxWidth="sm" style={{ marginTop: '40px' }}>
            <Typography variant="h4" gutterBottom>
                Edit Tour
            </Typography>
            <form onSubmit={handleSubmit}>
                <Grid container spacing={2}>
                    <Grid item xs={12}>
                        <TextField
                            fullWidth
                            name="name"
                            label="Name"
                            variant="outlined"
                            value={formData.name}
                            onChange={handleChange}
                        />
                    </Grid>
                    <Grid item xs={12}>
                        <TextField
                            fullWidth
                            name="description"
                            label="Tour Description"
                            variant="outlined"
                            value={formData.description}
                            onChange={handleChange}
                        />
                    </Grid>
                    <Grid item xs={12}>
                        <TextField
                            fullWidth
                            name="from_location"
                            label="From"
                            variant="outlined"
                            value={formData.from_location}
                            onChange={handleChange}
                        />
                        <List>
                            {fromSuggestions.map((suggestion, index) => (
                                <ListItem button key={index} onClick={() => handleSuggestionClick('from_location', suggestion)}>
                                    {suggestion}
                                </ListItem>
                            ))}
                        </List>
                    </Grid>
                    <Grid item xs={12}>
                        <TextField
                            fullWidth
                            name="to_location"
                            label="To"
                            variant="outlined"
                            value={formData.to_location}
                            onChange={handleChange}
                        />
                        <List>
                            {toSuggestions.map((suggestion, index) => (
                                <ListItem button key={index} onClick={() => handleSuggestionClick('to_location', suggestion)}>
                                    {suggestion}
                                </ListItem>
                            ))}
                        </List>
                    </Grid>
                    <Grid item xs={12}>
                        <FormControl fullWidth variant="outlined">
                            <InputLabel>Transport Type</InputLabel>
                            <Select
                                name="transport_type"
                                value={formData.transport_type}
                                onChange={handleChange}
                                label="Transport Type"
                            >
                                <MenuItem value="driving-car">Driving</MenuItem>
                                <MenuItem value="cycling-regular">Cycling</MenuItem>
                                <MenuItem value="foot-walking">Walking</MenuItem>
                            </Select>
                        </FormControl>
                    </Grid>
                    <Grid item xs={12}>
                        <TextField
                            fullWidth
                            name="distance"
                            label="Tour Distance"
                            variant="outlined"
                            value={formData.distance}
                            onChange={handleChange}
                        />
                    </Grid>
                    <Grid item xs={12}>
                        <TextField
                            fullWidth
                            name="time"
                            label="Estimated Time"
                            variant="outlined"
                            value={formData.time}
                            onChange={handleChange}
                        />
                    </Grid>
                    <Grid item xs={12}>
                        <Button type="submit" variant="contained" color="primary">
                            Update
                        </Button>
                    </Grid>
                </Grid>
            </form>
        </Container>
    );
};

export default EditTour;
