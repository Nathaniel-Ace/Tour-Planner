import React, { useEffect, useState } from 'react';
import { useParams, Link } from 'react-router-dom';
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
import {Delete, Edit} from "@mui/icons-material";

const TourDetail = () => {
    const { id } = useParams();
    const [tour, setTour] = useState(null);
    const [logs, setLogs] = useState([]);

    useEffect(() => {
        const fetchTourAndLogs = async () => {
            try {
                const tourResponse = await axios.get(`http://localhost:8080/tour/${id}`);
                setTour(tourResponse.data);

                const logsResponse = await axios.get(`http://localhost:8080/tourlog/tour/${id}`);
                console.log("Fetched tour logs:", logsResponse.data); // Debug log
                setLogs(logsResponse.data);
            } catch (error) {
                console.error('Error fetching tour and logs:', error);
            }
        };

        fetchTourAndLogs();
    }, [id]);

    const handleDelete = async (logId) => {
        try {
            await axios.delete(`http://localhost:8080/tourlog/${logId}`);
            setLogs(logs.filter(log => log.id !== logId)); // Update the logs state
        } catch (error) {
            console.error('Error deleting tour log:', error);
        }
    };

    return (
        <div className="TourDetail">
            {tour && (
                <div>
                    <Typography variant="h4" gutterBottom>
                        {tour.name}
                    </Typography>
                    <Grid container spacing={2}>
                        <Grid item xs={12} sm={6}>
                            <Card>
                                <CardContent>
                                    <Typography variant="h6">Description</Typography>
                                    <Typography>{tour.description}</Typography>
                                    <Typography variant="h6">Details</Typography>
                                    <Typography>Start: {tour.from_location}</Typography>
                                    <Typography>Destination: {tour.to_location}</Typography>
                                    <Typography>Transport Type: {tour.transport_type}</Typography>
                                    <Typography>Distance: {tour.distance}</Typography>
                                    <Typography>Time: {tour.time}</Typography>
                                    <Button
                                        variant="contained"
                                        color="primary"
                                        component={Link}
                                        to={`/edit-tour/${id}`}
                                        style={{ marginTop: '20px' }}
                                    >
                                        Edit Tour
                                    </Button>
                                </CardContent>
                            </Card>
                        </Grid>
                    </Grid>
                    <Box display="flex" justifyContent="center" marginTop={2}>
                        <Button
                            variant="contained"
                            color="primary"
                            component={Link}
                            to={`/create-tourlogs/${id}`} // update this to the correct route
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
                                        {log.dateTime ? new Date(log.dateTime).toISOString()/*.split('T')[0]*/ : 'N/A'}
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
