import React, {Component} from "react";
import {connect} from "react-redux";

class TransactionItem extends Component {

    render() {
        const {transaction} = this.props;
        return (
            <div className="container">
                <div className="card card-body bg-light mb-3">
                    <div className="row">
                        <div className="col-lg-6 col-md-4 col-8">
                            <h2>{transaction.amount}</h2>
                            <p>{transaction.wallet.name}</p>
                            {/* <p>{transaction.subcategory.category.name}</p> */}
                            <p>{transaction.subcategory.name}</p>
                            <p>{transaction.date}</p>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default connect(null,)(TransactionItem);
