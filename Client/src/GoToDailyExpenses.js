import React from 'react'
import {Link} from 'react-router-dom'

const GoToDailyExpenses = () => {
    return(
        <>
            <Link to="/dailyExpenses" className="btn btn-outline-secondary">
            Daily expenses
            </Link>
        </>
    );
};

export default GoToDailyExpenses;