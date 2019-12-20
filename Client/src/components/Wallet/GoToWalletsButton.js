import React from 'react'
import {Link} from 'react-router-dom'

const GoToWalletsButton = () => {
    return(
        <>
            <Link to="/getWallets" className="btn btn-outline-secondary">
                Wallets
            </Link>
        </>
    );
};

export default GoToWalletsButton;