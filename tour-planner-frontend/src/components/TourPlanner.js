// components/TourPlanner.js
import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import {
    Grid,
    Card,
    CardContent,
    CardActions,
    Typography,
    Table,
    TableBody,
    TableCell,
    TableHead,
    TableRow,
    Button,
} from '@mui/material';
import { MapContainer, TileLayer, Marker, Popup } from 'react-leaflet';
import 'leaflet/dist/leaflet.css';
import axios from 'axios';

const tours = [
    {
        title: 'Wanderung im Wienerwald',
        description: 'Erkunde die malerischen Wanderwege',
        distance: '15 km',
        time: '5 Stunden',
        transport: 'Zu Fuß',
        coordinates: { lat: 48.2082, lng: 16.3738 },
        backgroundImage: '/images/forest.png',
    },
    {
        title: 'Bike Tour durch die Alpen',
        description: 'Eine aufregende Fahrradtour',
        distance: '50 km',
        time: '3 Stunden',
        transport: 'Fahrrad',
        coordinates: { lat: 47.2692, lng: 11.4041 },
        backgroundImage: '/images/mountains.png',
    },
    {
        title: 'Städtetrip nach Berlin',
        description: 'Entdecke die kulturellen Highlights',
        distance: '0 km',
        time: '2 Tage',
        transport: 'Öffentliche Verkehrsmittel',
        coordinates: { lat: 52.5200, lng: 13.4050 },
        backgroundImage: '/images/berlin.png',
    },
];

const containerStyle = {
    width: '100%',
    height: '500px',
};

const center = {
    lat: 48.2082,
    lng: 16.3738,
};

const TourPlanner = () => {
    const [tourLogs, setTourLogs] = useState([]);

    useEffect(() => {
        const fetchTourLogs = async () => {
            try {
                const response = await axios.get('http://localhost:4000/tourlogs');
                setTourLogs(response.data);
            } catch (error) {
                console.error('Fehler beim Laden der Tour Logs:', error);
            }
        };

        fetchTourLogs();
    }, []);

    return (
        <div className="TourPlanner">
            <Typography variant="h4" gutterBottom className="TourPlanner-logs">
                Tour Logs
            </Typography>
            <Button
                variant="contained"
                color="primary"
                component={Link}
                to="/create-tour"
                style={{ marginBottom: '10px' }}
            >
                Create Tour
            </Button>
            <Table>
                <TableHead>
                    <TableRow>
                        <TableCell>Date</TableCell>
                        <TableCell>Duration</TableCell>
                        <TableCell>Distance</TableCell>
                        <TableCell>Rating</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {tourLogs.length > 0 ? (
                        tourLogs.map((log, index) => (
                            <TableRow key={index}>
                                <TableCell>{log.date}</TableCell>
                                <TableCell>{log.duration}</TableCell>
                                <TableCell>{log.distance}</TableCell>
                                <TableCell>{log.rating}</TableCell>
                            </TableRow>
                        ))
                    ) : (
                        <TableRow>
                            <TableCell colSpan={4} align="center">
                                Keine Tour Logs vorhanden
                            </TableCell>
                        </TableRow>
                    )}
                </TableBody>
            </Table>

            <Typography variant="h5" gutterBottom style={{ marginTop: '40px' }}>
                Vorgeschlagene Touren
            </Typography>
            <Grid container spacing={2} style={{ marginBottom: '20px' }}>
                {tours.map((tour, index) => (
                    <Grid item xs={4} key={index}>
                        <Card
                            className="TourPlanner-card"
                            style={{
                                backgroundImage: `url(${tour.backgroundImage})`,
                                backgroundSize: 'cover',
                                backgroundPosition: 'center',
                                color: 'white',
                                height: '200px',
                                display: 'flex',
                                flexDirection: 'column',
                                justifyContent: 'flex-end',
                                padding: '10px',
                            }}
                        >
                            <CardContent style={{ background: 'rgba(0, 0, 0, 0.5)', borderRadius: '5px' }}>
                                <Typography variant="h6" style={{ color: 'white' }}>{tour.title}</Typography>
                                <Typography variant="body2" style={{ color: 'white' }}>{tour.description}</Typography>
                                <Typography variant="body2" style={{ color: 'white' }}>Entfernung: {tour.distance}</Typography>
                                <Typography variant="body2" style={{ color: 'white' }}>Zeit: {tour.time}</Typography>
                                <Typography variant="body2" style={{ color: 'white' }}>Transport: {tour.transport}</Typography>
                            </CardContent>
                            <CardActions style={{ justifyContent: 'center' }}>
                                <Button
                                    size="small"
                                    style={{ background: 'white', color: 'black', borderRadius: '5px' }}
                                    onClick={() => alert('Details zu ' + tour.title)}
                                >
                                    Details
                                </Button>
                            </CardActions>
                        </Card>
                    </Grid>
                ))}
            </Grid>

            <Typography variant="h5" gutterBottom style={{ marginTop: '20px' }}>
                Karte
            </Typography>
            <MapContainer center={center} zoom={10} style={containerStyle}>
                <TileLayer
                    url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
                    attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
                />
                {tours.map((tour, index) => (
                    <Marker key={index} position={tour.coordinates}>
                        <Popup>{tour.title}</Popup>
                    </Marker>
                ))}
            </MapContainer>
        </div>
    );
};

export default TourPlanner;
