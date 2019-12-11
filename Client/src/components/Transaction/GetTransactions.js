import React, {Component} from "react";
import {connect} from "react-redux";
import {getTransactions} from "../../actions/transactionActions";
import PropTypes from "prop-types";
import TransactionItem from "./TransactionItem"
import CreateTransactionButton from "./CreateTransactionButton"

class GetTransactions extends Component {
    
    constructor() {
        super();
        const d = new Date()
        this.state = {
          month: d.getMonth()
        };
    }
    componentDidMount() {
        this
            .props
            .getTransactions();
    }

    render() {
        const {transactions} = this.props.transaction;
        transactions.sort((a, b) => (a.date > b.date) ? 1 : -1)
        const {transactionsByMonth} = transactions.filter(object => {
            return object.amount > 10.00 ;              
        });
        return (
            <> 
            {console.log(transactions)}
            {console.log(transactionsByMonth)}
            < div className = "wallets" > <div className="container">
                <div className="row">
                    <div className="col-md-12">
                        <h1 className="display-4 text-center">Transactions</h1>
                        <br/>
                        <CreateTransactionButton/>
                        <br/>
                        <hr/> {transactions.map(transaction => (<TransactionItem key={transaction.id} transaction={transaction}/>))}
                    </div>
                </div>
            </div>
        </div>
    </>
        );
    }
}

GetTransactions.propTypes = {
    transaction: PropTypes.object.isRequired,
    getTransactions: PropTypes.func.isRequired
};

const mapStateToProps = state => ({transaction: state.transaction});

export default connect(mapStateToProps, {getTransactions})(GetTransactions);