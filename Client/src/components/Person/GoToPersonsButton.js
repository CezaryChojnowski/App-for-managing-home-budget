import React from 'react'
import {Link} from 'react-router-dom'

const GoToPersonsButton = () => {
    return(
        <>
            <Link to="/getPeople" className="btn btn-outline-secondary">
                Persons
            </Link>
        </>
    );
};

export default GoToPersonsButton;