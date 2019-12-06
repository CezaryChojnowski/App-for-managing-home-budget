import React from 'react'
import {Link} from 'react-router-dom'

const GoToPersonsButton = () => {
    return(
        <>
            <Link to="/getPeople" className="btn btn-lg btn-info">
                Persons
            </Link>
        </>
    );
};

export default GoToPersonsButton;