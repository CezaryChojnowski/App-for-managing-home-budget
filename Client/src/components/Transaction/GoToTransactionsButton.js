import React from 'react'
import {Link} from 'react-router-dom'

const GoToTransactionsButton = () => {
    return(
        <>
            <Link to="/getTransactions" className="btn btn-lg btn-info">
                Transactions
            </Link>
        </>
    );
};

export default GoToTransactionsButton;