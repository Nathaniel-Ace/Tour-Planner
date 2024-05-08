// components/CreateTour.js
import React, { useState } from 'react';
import {
    Container,
    TextField,
    Button,
    Grid,
    Typography,
} from '@mui/material';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

const CreateTour = () => {
    const [formData, setFormData] = useState({
        date: '',
        duration: '',
        distance: '',
        rating: '',
    });

    const navigate = useNavigate();

    const handleChange = (event) => {
        const { name, value } = event.target;
        setFormData((prevData) => ({ ...prevData, [name]: value }));
    };

    const handleSubmit = async (event) => {
        event.preventDefault();
        try {
            await axios.post('http://localhost:4000/tourlogs', formData);
            navigate('/');
        } catch (error) {
            console.error('Fehler beim Erstellen der Tour:', error);
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
                            name="date"
                            label="Datum"
                            variant="outlined"
                            value={formData.date}
                            onChange={handleChange}
                        />
                    </Grid>
                    <Grid item xs={12}>
                        <TextField
                            fullWidth
                            name="duration"
                            label="Dauer"
                            variant="outlined"
                            value={formData.duration}
                            onChange={handleChange}
                        />
                    </Grid>
                    <Grid item xs={12}>
                        <TextField
                            fullWidth
                            name="distance"
                            label="Entfernung"
                            variant="outlined"
                            value={formData.distance}
                            onChange={handleChange}
                        />
                    </Grid>
                    <Grid item xs={12}>
                        <TextField
                            fullWidth
                            name="rating"
                            label="Bewertung"
                            variant="outlined"
                            value={formData.rating}
                            onChange={handleChange}
                        />
                    </Grid>
                    <Grid item xs={12}>
                        <Button type="submit" variant="contained" color="primary">
                            Erstellen
                        </Button>
                    </Grid>
                </Grid>
            </form>
        </Container>
    );
};

export default CreateTour;
