import React from 'react'
import {Link} from 'react-router-dom'

const GoToCategoriesButton = () => {
    return(
        <>
            <Link to="/getCategories" className="btn btn-outline-secondary">
                Categories
            </Link>
        </>
    );
};

export default GoToCategoriesButton;