import React from 'react'
import {Link} from 'react-router-dom'

const UpdateWalletButton = () => {
    return(
        <>
            <Link to="/updateWallet" className="btn btn-lg btn-info">
                Update Wallet
            </Link>
        </>
    );
};

export default UpdateWalletButton;