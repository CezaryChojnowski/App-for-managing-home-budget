import React, {Component} from "react";
import {connect} from "react-redux";
import {getEventTransactions} from "../../actions/transactionActions";
import PropTypes from "prop-types";
import TransactionItem from "../Transaction/TransactionItem";

class GetEventTransactions extends Component {
    
    constructor() {
        super();
        const d = new Date()
        this.state = {
          month: d.getMonth()
        };
    }
    componentDidMount() {
        const { id } = this.props.match.params;
        this.props.getEventTransactions(id, this.props.history);
    }

    render() {
        const {transactions} = this.props.transaction;
        transactions.sort((a, b) => (a.date > b.date) ? 1 : -1)
        return (
            <> 
            < div className = "wallets" > <div className="container">
                <div className="row">
                    <div className="col-md-12">
                        <h1 className="display-4 text-center">Transactions</h1>
                        <br/>
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

GetEventTransactions.propTypes = {
    transaction: PropTypes.object.isRequired,
    getEventTransactions: PropTypes.func.isRequired
};

const mapStateToProps = state => ({transaction: state.transaction});

export default connect(mapStateToProps, {getEventTransactions})(GetEventTransactions);