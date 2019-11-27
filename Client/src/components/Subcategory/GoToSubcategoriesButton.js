import React from 'react'
import {Link} from 'react-router-dom'

const GoToSubcategoriesButton = () => {
    return(
        <>
            <Link to="/getSubcategories" className="btn btn-lg btn-info">
                Subcategories
            </Link>
        </>
    );
};

export default GoToSubcategoriesButton;