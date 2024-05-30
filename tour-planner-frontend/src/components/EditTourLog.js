import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import {Grid, TextField, Button, Select, MenuItem, Box, Typography, Container} from '@mui/material';
import Rating from '@mui/material/Rating';
import axios from 'axios';

const EditTourLog = () => {
    const { id } = useParams();
    const navigate = useNavigate();
    const [formData, setFormData] = useState({
        comment: '',
        totalDistance: '',
        totalTime: '',
        difficulty: '',
        rating: 0,
    });
    const [tourId, setTourId] = useState(null); // Declare new state variable for tour ID

    useEffect(() => {
        const fetchTourLog = async () => {
            try {
                const response = await axios.get(`http://localhost:8080/tourlog/${id}`);
                setFormData(response.data);
                setTourId(response.data.tour); // Save the tour ID from the response data

                // Split totalTime into hours and minutes
                const [hours, minutes] = response.data.totalTime.split(':');
                setTotalTimeHours(hours);
                setTotalTimeMinutes(minutes);
            } catch (error) {
                console.error('Error fetching tour log:', error);
            }
        };

        fetchTourLog();
    }, [id]);

    const [totalTimeHours, setTotalTimeHours] = useState('');
    const [totalTimeMinutes, setTotalTimeMinutes] = useState('');

    const handleChange = (event) => {
        const { name, value } = event.target;

        if (name === 'totalTimeHours') {
            setTotalTimeHours(value);
        } else if (name === 'totalTimeMinutes') {
            setTotalTimeMinutes(value);
        } else {
            setFormData({ ...formData, [name]: value });
        }
    };

    const handleSubmit = async (event) => {
        event.preventDefault();

        try {
            const totalTime = `${totalTimeHours}:${totalTimeMinutes}`;
            // Set dateTime to current date and time
            const updatedFormData = { ...formData, totalTime, dateTime: new Date().toISOString() };
            await axios.put(`http://localhost:8080/tourlog/${id}`, updatedFormData);
        } catch (error) {
            console.error('Error updating tour log:', error);
        } finally {
            navigate(`/tour/${tourId}`); // Use the tourId state variable to navigate back
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
                    <Grid item xs={6}>
                        <TextField
                            fullWidth
                            name="totalTimeHours"
                            label="Total Time (Hours)"
                            variant="outlined"
                            value={totalTimeHours}
                            onChange={handleChange}
                        />
                    </Grid>
                    <Grid item xs={6}>
                        <TextField
                            fullWidth
                            name="totalTimeMinutes"
                            label="Total Time (Minutes)"
                            variant="outlined"
                            value={totalTimeMinutes}
                            onChange={handleChange}
                        />
                    </Grid>
                    <Grid item xs={12}>
                        <Select
                            fullWidth
                            name="difficulty"
                            value={formData.difficulty}
                            onChange={handleChange}
                            variant="outlined"
                        >
                            <MenuItem value="easy">Easy</MenuItem>
                            <MenuItem value="moderate">Moderate</MenuItem>
                            <MenuItem value="challenging">Challenging</MenuItem>
                            <MenuItem value="difficult">Difficult</MenuItem>
                            <MenuItem value="expert">Expert</MenuItem>
                        </Select>
                    </Grid>
                    <Grid item xs={12}>
                        <Box component="fieldset" mb={3} borderColor="transparent">
                            <Typography component="legend">Rating</Typography>
                            <Rating
                                name="rating"
                                value={formData.rating}
                                onChange={(event, newValue) => {
                                    setFormData((prevData) => ({ ...prevData, rating: newValue }));
                                }}
                                precision={0.5}
                            />
                        </Box>
                    </Grid>
                    <Grid item xs={12}>
                        <Button type="submit" fullWidth variant="contained" color="primary">
                            Update
                        </Button>
                    </Grid>
                </Grid>
            </form>
        </Container>
    );
};

export default EditTourLog;