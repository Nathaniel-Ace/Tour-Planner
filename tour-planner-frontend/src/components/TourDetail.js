import React, { useEffect, useState, useCallback, useRef } from 'react';
import { useParams, Link, useNavigate } from 'react-router-dom';
import {
    Typography,
    Card,
    CardContent,
    Grid,
    Table,
    TableBody,
    TableCell,
    TableHead,
    TableRow,
    Button, Rating,
} from '@mui/material';
import axios from 'axios';
import Box from "@mui/material/Box";
import IconButton from "@mui/material/IconButton";
import { Delete, Edit } from "@mui/icons-material";
import { MapContainer, TileLayer, Polyline, Marker, Popup } from 'react-leaflet';
import L from 'leaflet';
import 'leaflet/dist/leaflet.css';

import iconUrl from 'leaflet/dist/images/marker-icon.png';
import iconRetinaUrl from 'leaflet/dist/images/marker-icon-2x.png';
import shadowUrl from 'leaflet/dist/images/marker-shadow.png';

L.Icon.Default.mergeOptions({
    iconUrl,
    iconRetinaUrl,
    shadowUrl,
});

const MapComponent = React.memo(({ routeCoordinates, destination }) => (
    <MapContainer center={routeCoordinates[0]} zoom={10} style={{ height: '100%', width: '100%' }}>
        <TileLayer url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png" />
        <Polyline positions={routeCoordinates} color="blue" />
        <Marker position={routeCoordinates[routeCoordinates.length - 1]}>
            <Popup>{destination}</Popup>
        </Marker>
    </MapContainer>
));

const TourDetail = () => {
    const { id } = useParams();
    const navigate = useNavigate();
    const [tour, setTour] = useState(null);
    const [logs, setLogs] = useState([]);
    const [routeCoordinates, setRouteCoordinates] = useState([]);
    const [distance, setDistance] = useState(null);
    const [time, setTime] = useState(null);
    const isMounted = useRef(false);

    console.log('TourDetail rendered');

    const fetchTourAndLogs = useCallback(async () => {
        console.log('Fetching tour and logs');
        try {
            const tourResponse = await axios.get(`http://localhost:8080/tour/${id}`);
            if (isMounted.current) setTour(tourResponse.data);

            const logsResponse = await axios.get(`http://localhost:8080/tourlog/tour/${id}`);
            console.log("Fetched tour logs:", logsResponse.data);
            if (isMounted.current) setLogs(logsResponse.data);
        } catch (error) {
            console.error('Error fetching tour and logs:', error);
        }
    }, [id]);

    const fetchRoute = useCallback(async (startCoordinates, endCoordinates, transportType) => {
        console.log('Fetching route');
        try {
            const directionUrl = `http://localhost:8080/searchDirection?start=${startCoordinates}&end=${endCoordinates}&profile=${transportType}`;
            console.log(`Making request to: ${directionUrl}`);

            const directionsResponse = await axios.get(directionUrl);
            const data = directionsResponse.data;
            const coordinates = data.features[0].geometry.coordinates.map(coord => [coord[1], coord[0]]);
            if (isMounted.current) setRouteCoordinates(coordinates);

            const distance = data.features[0].properties.segments[0].distance;
            const duration = data.features[0].properties.segments[0].duration;
            if (isMounted.current) {
                setDistance((distance / 1000).toFixed(2)); // Convert to km
                setTime(Math.round(duration / 60)); // Convert to minutes
            }
        } catch (error) {
            console.error('Error fetching route:', error);
        }
    }, []);

    useEffect(() => {
        isMounted.current = true;
        fetchTourAndLogs();
        return () => { isMounted.current = false };
    }, [fetchTourAndLogs]);

    useEffect(() => {
        if (tour && tour.startCoordinates && tour.endCoordinates && tour.transport_type) {
            fetchRoute(tour.startCoordinates, tour.endCoordinates, tour.transport_type);
        }
    }, [tour, fetchRoute]);

    const handleDelete = async (logId) => {
        try {
            await axios.delete(`http://localhost:8080/tourlog/${logId}`);
            setLogs(logs.filter(log => log.id !== logId));
        } catch (error) {
            console.error('Error deleting tour log:', error);
        }
    };

    const handleDeleteTour = async () => {
        try {
            await axios.delete(`http://localhost:8080/tour/${id}`);
            navigate('/');
        } catch (error) {
            console.error('Error deleting tour:', error);
        }
    };

    const formatTime = (minutes) => {
        if (minutes < 60) {
            return `${minutes} minutes`;
        } else {
            const hours = Math.floor(minutes / 60);
            const remainingMinutes = minutes % 60;
            return `${hours} hour${hours > 1 ? 's' : ''} ${remainingMinutes > 0 ? `and ${remainingMinutes} minute${remainingMinutes > 1 ? 's' : ''}` : ''}`;
        }
    };

    return (
        <div className="TourDetail">
            {tour && (
                <div>
                    <Typography variant="h4" gutterBottom>
                        {tour.name}
                    </Typography>
                    <Grid container spacing={2} style={{ height: '60vh' }}>
                        <Grid item xs={12} sm={6} style={{ display: 'flex' }}>
                            <Card style={{ flexGrow: 1, display: 'flex', flexDirection: 'column' }}>
                                <CardContent style={{ flexGrow: 1 }}>
                                    <Box mb={4}>
                                        <Typography variant="h6">Description</Typography>
                                        <Typography>{tour.description}</Typography>
                                    </Box>
                                    <Box mt={4}>
                                        <Typography variant="h6">Details</Typography>
                                        <Typography>Start: {tour.from_location}</Typography>
                                        <Typography>Destination: {tour.to_location}</Typography>
                                        <Typography>Transport Type: {tour.transport_type}</Typography>
                                        <Typography>Distance: {distance ? `${distance} km` : 'Calculating...'}</Typography>
                                        <Typography>Time: {time ? formatTime(time) : 'Calculating...'}</Typography>
                                    </Box>
                                </CardContent>
                                <Box style={{ padding: '16px', textAlign: 'center' }}>
                                    <Button
                                        variant="contained"
                                        color="primary"
                                        component={Link}
                                        to={`/edit-tour/${id}`}
                                        style={{ marginRight: '10px' }}
                                    >
                                        Edit Tour
                                    </Button>
                                    <Button
                                        variant="contained"
                                        color="secondary"
                                        onClick={handleDeleteTour}
                                    >
                                        Delete Tour
                                    </Button>
                                </Box>
                            </Card>
                        </Grid>
                        <Grid item xs={12} sm={6} style={{ display: 'flex' }}>
                            <Card style={{ flexGrow: 1, display: 'flex', flexDirection: 'column' }}>
                                <CardContent style={{ flexGrow: 1, padding: 0 }}>
                                    {routeCoordinates.length > 0 && (
                                        <MapComponent routeCoordinates={routeCoordinates} destination={tour.to_location} />
                                    )}
                                </CardContent>
                            </Card>
                        </Grid>
                    </Grid>
                    <Box display="flex" justifyContent="center" marginTop={2}>
                        <Button
                            variant="contained"
                            color="primary"
                            component={Link}
                            to={`/create-tourlogs/${id}`}
                        >
                            Create Tour Log
                        </Button>
                    </Box>
                </div>
            )}

            {logs && logs.length > 0 ? (
                <div>
                    <Typography variant="h5" gutterBottom style={{ marginTop: '20px' }}>
                        Tour Logs
                    </Typography>
                    <Table>
                        <TableHead>
                            <TableRow>
                                <TableCell>Date</TableCell>
                                <TableCell>Report</TableCell>
                                <TableCell>Distance</TableCell>
                                <TableCell>Time</TableCell>
                                <TableCell>Rating</TableCell>
                                <TableCell>Difficulty</TableCell>
                                <TableCell></TableCell>
                                <TableCell></TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {logs.map((log, index) => (
                                <TableRow key={index}>
                                    <TableCell>
                                        {log.dateTime ? new Date(log.dateTime).toISOString().split('T')[0] : 'N/A'}
                                    </TableCell>
                                    <TableCell>{log.comment || 'N/A'}</TableCell>
                                    <TableCell>{log.totalDistance || 'N/A'}</TableCell>
                                    <TableCell>{log.totalTime || 'N/A'}</TableCell>
                                    <TableCell>
                                        <Rating name="read-only" value={log.rating} precision={0.5} readOnly />
                                    </TableCell>
                                    <TableCell>{log.difficulty || 'N/A'}</TableCell>
                                    <TableCell>
                                        <IconButton component={Link} to={`/edit-tourlog/${log.id}`}>
                                            <Edit />
                                        </IconButton>
                                    </TableCell>
                                    <TableCell>
                                        <IconButton onClick={() => handleDelete(log.id)}>
                                            <Delete />
                                        </IconButton>
                                    </TableCell>
                                </TableRow>
                            ))}
                        </TableBody>
                    </Table>
                </div>
            ) : (
                <Typography variant="h6" style={{ marginTop: '20px' }}>
                    No tour logs yet.
                </Typography>
            )}
        </div>
    );
};

export default TourDetail;
