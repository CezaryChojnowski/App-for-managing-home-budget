import React, {Component} from "react";
import {connect} from "react-redux";
import Dialog from "./Dialog";
import CreateWalletButton from "./CreateWalletButton";
import {deleteWallet} from "../../actions/walletActions";
import PropTypes from "prop-types";

class WalletItem extends Component {

    onDeleteClick = id => {
        this
            .props
            .deleteWallet(id);
    };

    state = {
        isOpen: false
    }

    render() {
        const {wallet} = this.props;
        return (
            <div className="container">
                <div className="card card-body bg-light mb-3">
                    <div className="row">
                        <div className="col-lg-6 col-md-4 col-8">
                            <h2>{wallet.name}</h2>
                            <p>{wallet.comment}</p>
                            <p>{wallet.balance}</p>
                            <div>
                                <button onClick={(e) => this.setState({isOpen: true})}>. . .</button>
                                <Dialog
                                    isOpen={this.state.isOpen}
                                    onClose={(e) => this.setState({isOpen: false})}>
                                    <li
                                        className="list-group-item delete"
                                        onClick={this
                                            .onDeleteClick
                                            .bind(this, wallet.id)}>
                                        <i className="fa fa-minus-circle pr-1">
                                            Delete Wallet</i>
                                    </li>
                                </Dialog>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

WalletItem.propTypes = {
    deleteWallet: PropTypes.func.isRequired
};

export default connect(null, {deleteWallet})(WalletItem);
