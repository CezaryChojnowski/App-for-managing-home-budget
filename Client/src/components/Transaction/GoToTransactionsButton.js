import React from 'react'
import {Link} from 'react-router-dom'

const GoToTransactionsButton = () => {
    return(
        <>
            <Link to="/getTransactions" className="btn btn-outline-secondary">
                Transactions
            </Link>
        </>
    );
};

export default GoToTransactionsButton;