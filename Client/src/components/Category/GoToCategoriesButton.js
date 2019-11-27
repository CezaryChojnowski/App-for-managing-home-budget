import React from 'react'
import {Link} from 'react-router-dom'

const GoToCategoriesButton = () => {
    return(
        <>
            <Link to="/getCategories" className="btn btn-lg btn-info">
                Categories
            </Link>
        </>
    );
};

export default GoToCategoriesButton;