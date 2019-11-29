import React from 'react'
import {Link} from 'react-router-dom'

const UpdateBalanceButton = () => {
    return(
        <>
            <Link to="/updateBalance" className="btn btn-lg btn-info">
                Update balace
            </Link>
        </>
    );
};

export default UpdateBalanceButton;