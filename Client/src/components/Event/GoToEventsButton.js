import React from 'react'
import {Link} from 'react-router-dom'

const GoToEventsButton = () => {
    return(
        <>
            <Link to="/getEvents" className="btn btn-outline-secondary">
                Events
            </Link>
        </>
    );
};

export default GoToEventsButton;