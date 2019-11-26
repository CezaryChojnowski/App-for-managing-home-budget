import React from 'react'
import {Link} from 'react-router-dom'

const CreateWalletButton = () => {
    return(
        <>
            <Link to="/addWallet" className="btn btn-lg btn-info">
                Create a wallet
            </Link>
        </>
    );
};

export default CreateWalletButton;