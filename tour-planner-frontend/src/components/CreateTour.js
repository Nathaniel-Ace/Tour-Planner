// components/CreateTour.js
import React, { useState } from 'react';
import {
    Container,
    TextField,
    Button,
    Grid,
    Typography, MenuItem, InputLabel, FormControl, Select,
} from '@mui/material';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

const CreateTour = () => {
    const [formData, setFormData] = useState({
        name: '',
        description: '',
        from_location: '',
        to_location: '',
        transport_type: '',
        distance: '',
        time: '',
        startCoordinates: '',
        endCoordinates: '',
    });

    const navigate = useNavigate();

    const handleChange = (event) => {
        const { name, value } = event.target;
        setFormData((prevData) => ({ ...prevData, [name]: value }));
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
                await axios.post('http://localhost:8080/tour', data);
                navigate('/');
            } else {
                console.error('Error searching address');
            }
        } catch (error) {
            console.error('Error creating tour:', error);
        }
    };

    return (
        <Container maxWidth="sm" style={{ marginTop: '40px' }}>
            <Typography variant="h4" gutterBottom>
                Neue Tour erstellen
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
                                <MenuItem value="Driving">Driving</MenuItem>
                                <MenuItem value="Cycling">Cycling</MenuItem>
                                <MenuItem value="Walking">Walking</MenuItem>
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
                            Create
                        </Button>
                    </Grid>
                </Grid>
            </form>
        </Container>
    );
};

export default CreateTour;
