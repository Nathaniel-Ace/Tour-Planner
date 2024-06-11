import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import {
    Grid,
    Card,
    CardContent,
    Typography,
    Table,
    TableBody,
    TableCell,
    TableHead,
    TableRow,
    Button,
    TextField,
} from '@mui/material';
import { MapContainer, TileLayer, Marker, Popup } from 'react-leaflet';
import 'leaflet/dist/leaflet.css';
import axios from 'axios';

const tours = [
    {
        title: 'Hike through Wienerwald',
        description: 'Discover picturesque hiking trails',
        distance: '15 km',
        time: '4 hours',
        transport: 'Walking',
        coordinates: { lat: 48.2082, lng: 16.3738 },
        backgroundImage: '/images/forest.png',
    },
    {
        title: 'Bike Tour through the Alps',
        description: 'An exciting bike tour',
        distance: '50 km',
        time: '3 hours',
        transport: 'Bike',
        coordinates: { lat: 47.2692, lng: 11.4041 },
        backgroundImage: '/images/mountains.png',
    },
    {
        title: 'City trip to Berlin',
        description: 'Discover cultural highlights',
        distance: '690 km',
        time: '7 hours 30 min',
        transport: 'By Car',
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
    const [searchQuery, setSearchQuery] = useState('');

    useEffect(() => {
        const fetchTours = async () => {
            try {
                const response = await axios.get('http://localhost:8080/tour');
                setTourLogs(response.data);
            } catch (error) {
                console.error('Fehler beim Laden der Tour Logs:', error);
            }
        };

        fetchTours();
    }, []);

    const handleSearchChange = (event) => {
        setSearchQuery(event.target.value);
    };

    const filteredTourLogs = tourLogs.filter(log => {
        const lowerCaseQuery = searchQuery.toLowerCase();
        return (
            (log.name && log.name.toLowerCase().includes(lowerCaseQuery)) ||
            (log.from_location && log.from_location.toLowerCase().includes(lowerCaseQuery)) ||
            (log.to_location && log.to_location.toLowerCase().includes(lowerCaseQuery)) ||
            (log.distance && log.distance.toString().toLowerCase().includes(lowerCaseQuery))
        );
    });


    return (
        <div className="TourPlanner">
            <Typography variant="h4" gutterBottom className="TourPlanner">
                Tours
            </Typography>
            <TextField
                label="Search Tours"
                variant="outlined"
                fullWidth
                value={searchQuery}
                onChange={handleSearchChange}
                style={{ marginBottom: '20px' }}
            />
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
                        <TableCell>Name</TableCell>
                        <TableCell>Start</TableCell>
                        <TableCell>Destination</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {filteredTourLogs.length > 0 ? (
                        filteredTourLogs.map((log, index) => (
                            <TableRow key={index}>
                                <TableCell>
                                    <Link to={`/tour/${log.id}`}>{log.name}</Link>
                                </TableCell>
                                <TableCell>{log.from_location}</TableCell>
                                <TableCell>{log.to_location}</TableCell>
                            </TableRow>
                        ))
                    ) : (
                        <TableRow>
                            <TableCell colSpan={4} align="center">
                                Keine Tours vorhanden
                            </TableCell>
                        </TableRow>
                    )}
                </TableBody>
            </Table>

            <Typography variant="h5" gutterBottom style={{ marginTop: '40px' }}>
                Recommended Tours
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
                                <Typography variant="body2" style={{ color: 'white' }}>Distance: {tour.distance}</Typography>
                                <Typography variant="body2" style={{ color: 'white' }}>Time: {tour.time}</Typography>
                                <Typography variant="body2" style={{ color: 'white' }}>Transport: {tour.transport}</Typography>
                            </CardContent>
                        </Card>
                    </Grid>
                ))}
            </Grid>

            <Typography variant="h5" gutterBottom style={{ marginTop: '20px' }}>
                Map
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
