// App.js
import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import './App.css';
import AppBarComponent from './components/Appbar';
import TourPlanner from './components/TourPlanner';
import CreateTour from './components/CreateTour';
import ErrorBoundary from './components/ErrorBoundary';

function App() {
    return (
        <Router>
            <div className="App">
                <AppBarComponent />
                <ErrorBoundary>
                    <Routes>
                        <Route path="/" element={<TourPlanner />} />
                        <Route path="/create-tour" element={<CreateTour />} />
                    </Routes>
                </ErrorBoundary>
            </div>
        </Router>
    );
}

export default App;
