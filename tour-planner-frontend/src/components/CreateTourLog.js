// components/CreateTourLog.js
import React, { useState } from 'react';
import {
    Container,
    TextField,
    Button,
    Grid,
    Typography, Select, MenuItem, InputLabel, FormControl, Rating, Paper,
} from '@mui/material';
import { useNavigate, useParams } from 'react-router-dom';
import axios from 'axios';
import Box from "@mui/material/Box";

const CreateTourLog = () => {
    const { id: tourId } = useParams(); // get tour ID from URL
    const [formData, setFormData] = useState({
        tour: tourId, // set tour ID
        dateTime: new Date().toISOString(),
        comment: '',
        totalDistance: '',
        totalTime: '',
        rating: '',
        difficulty: '',
    });

    const navigate = useNavigate();

    const handleChange = (event) => {
        const { name, value } = event.target;
        setFormData((prevData) => ({ ...prevData, [name]: value }));
    };

    const handleRatingChange = (event, newValue) => {
        setFormData((prevData) => ({ ...prevData, rating: newValue }));
    };

    const handleSubmit = async (event) => {
        event.preventDefault();
        try {
            // Set dateTime to current date and time
            formData.dateTime = new Date().toISOString();
            await axios.post('http://localhost:8080/tourlog', formData);
            navigate(`/tour/${tourId}`);
        } catch (error) {
            console.error('Error creating tour log:', error);
        }
    };

    return (
        <Container maxWidth="sm" style={{ marginTop: '40px' }}>
            <Typography variant="h4" gutterBottom>
                Create New Tour Log
            </Typography>
            <form onSubmit={handleSubmit}>
                <Grid container spacing={2}>
                    <Grid item xs={12}>
                        <TextField
                            fullWidth
                            name="comment"
                            label="Comment"
                            variant="outlined"
                            value={formData.comment}
                            onChange={handleChange}
                        />
                    </Grid>
                    <Grid item xs={12}>
                        <TextField
                            fullWidth
                            name="totalDistance"
                            label="Total Distance"
                            variant="outlined"
                            value={formData.totalDistance}
                            onChange={handleChange}
                        />
                    </Grid>
                    <Grid item xs={12}>
                        <TextField
                            fullWidth
                            name="totalTime"
                            label="Total Time"
                            variant="outlined"
                            value={formData.totalTime}
                            onChange={handleChange}
                        />
                    </Grid>
                    <Grid item xs={12}>
                        <FormControl fullWidth variant="outlined">
                            <InputLabel>Difficulty</InputLabel>
                            <Select
                                name="difficulty"
                                value={formData.difficulty}
                                onChange={handleChange}
                                label={"Difficulty"}
                            >
                                <MenuItem value="easy">Easy</MenuItem>
                                <MenuItem value="moderate">Moderate</MenuItem>
                                <MenuItem value="challenging">Challenging</MenuItem>
                                <MenuItem value="difficult">Difficult</MenuItem>
                                <MenuItem value="expert">Expert</MenuItem>
                            </Select>
                        </FormControl>
                    </Grid>
                    <Grid item xs={12}>
                        <Box
                            component={Paper}
                            variant="outlined"
                            sx={{ padding: '10px', display: 'flex', alignItems: 'center', borderColor: 'rgba(0, 0, 0, 0.23)', borderRadius: '4px' }}
                        >
                            <Typography variant="body1" style={{ marginRight: '10px' }}>Rating</Typography>
                            <Box sx={{ flexGrow: 1, display: 'flex', justifyContent: 'center' }}>
                                <Rating
                                    name="rating"
                                    value={formData.rating}
                                    onChange={handleRatingChange}
                                    precision={0.5}
                                />
                            </Box>
                        </Box>
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

export default CreateTourLog;