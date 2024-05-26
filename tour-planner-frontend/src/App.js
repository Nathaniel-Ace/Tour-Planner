// App.js
import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import './App.css';
import AppBarComponent from './components/Appbar';
import TourPlanner from './components/TourPlanner';
import CreateTour from './components/CreateTour';
import ErrorBoundary from './components/ErrorBoundary';
import TourDetail from "./components/TourDetail";
import EditTour from "./components/EditTour";
import CreateTourLog from "./components/CreateTourLog";
import EditTourLog from "./components/EditTourLog";

function App() {
    return (
        <Router>
            <div className="App">
                <AppBarComponent />
                <ErrorBoundary>
                    <Routes>
                        <Route path="/" element={<TourPlanner />} />
                        <Route path="/create-tour" element={<CreateTour />} />
                        <Route path="/tour/:id" element={<TourDetail />} />
                        <Route path="/edit-tour/:id" element={<EditTour />} />
                        <Route path="/create-tourlogs/:id" element={<CreateTourLog />} />
                        <Route path="/edit-tourlog/:id" element={<EditTourLog />} />
                    </Routes>
                </ErrorBoundary>
            </div>
        </Router>
    );
}

export default App;
