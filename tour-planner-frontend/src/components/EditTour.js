// components/EditTour.js
import React, { useState, useEffect } from 'react';
import {
    Container,
    TextField,
    Button,
    Grid,
    Typography, FormControl, InputLabel, Select, MenuItem,
} from '@mui/material';
import { useParams, useNavigate } from 'react-router-dom';
import axios from 'axios';

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
        time: ''
    });

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
    };

    const handleSubmit = async (event) => {
        event.preventDefault();

        try {
            await axios.put(`http://localhost:8080/tour/${id}`, formData);
            navigate(`/tour/${id}`);
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
                            Update
                        </Button>
                    </Grid>
                </Grid>
            </form>
        </Container>
    );
};

export default EditTour;