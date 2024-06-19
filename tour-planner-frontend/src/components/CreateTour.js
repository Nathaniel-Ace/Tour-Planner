import React, { useState } from 'react';
import {
    Container,
    TextField,
    Button,
    Grid,
    Typography, MenuItem, InputLabel, FormControl, Select, List, ListItem
} from '@mui/material';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import debounce from 'lodash.debounce';

const CreateTour = () => {
    const [formData, setFormData] = useState({
        name: '',
        description: '',
        from_location: '',
        to_location: '',
        transport_type: '',
        startCoordinates: '',
        endCoordinates: '',
    });

    const [fromSuggestions, setFromSuggestions] = useState([]);
    const [toSuggestions, setToSuggestions] = useState([]);

    const navigate = useNavigate();

    const searchAddress = async (address) => {
        console.log('Searching address:', address);
        try {
            const response = await axios.get(`http://localhost:8080/searchAddress?text=${address}`);
            return response.data;
        } catch (error) {
            console.error('Error searching address:', error);
            return null;
        }
    };

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

        console.log('Fetching from location suggestions');
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

        console.log('Fetching to location suggestions');
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

    const [errorMessage, setErrorMessage] = useState('');

    const handleSubmit = async (event) => {
        event.preventDefault();

        // Check if all fields are filled
        for (let field in formData) {
            if (!formData[field]) {
                setErrorMessage('All fields must be filled');
                return;
            }
        }

        try {
            const { from_location, to_location } = formData;
            const startCoordinates = await searchAddress(from_location);
            const endCoordinates = await searchAddress(to_location);
            if (startCoordinates && endCoordinates) {
                const data = { ...formData, startCoordinates, endCoordinates };
                console.log('Submitting form data:', data);
                await axios.post('http://localhost:8080/tour', data);
                navigate('/');
            } else {
                setErrorMessage('Error: Coordinates are missing');
            }
        } catch (error) {
            setErrorMessage('Error creating tour: ' + error.message);
        }
    };

    return (
        <Container maxWidth="sm" style={{ marginTop: '40px' }}>
            <Typography variant="h4" gutterBottom>
                Create New Tour
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
                            autoComplete="off"
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
                            autoComplete="off"
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
                            autoComplete="off"
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
                            autoComplete="off"
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
                        <Button type="submit" variant="contained" color="primary">
                            Create
                        </Button>
                    </Grid>
                    <Grid item xs={12}>
                        {errorMessage && <Typography color="error">{errorMessage}</Typography>}
                    </Grid>
                </Grid>
            </form>
        </Container>
    );
};

export default CreateTour;
