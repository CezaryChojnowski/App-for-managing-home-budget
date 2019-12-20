import React, {Component} from "react";
import {connect} from "react-redux";
import {deleteTransaction} from "../../actions/transactionActions"
import PropTypes from "prop-types";
import Button from '@material-ui/core/Button';
import Menu from '@material-ui/core/Menu';
import MenuItem from '@material-ui/core/MenuItem';

class TransactionItem extends Component {

    constructor(props) {
        super(props);
        this.state = {
            anchorEl: null,
            open: false
        };
    }
    flipOpen = () => this.setState({
        ...this.state,
        open: !this.state.open
    });
    handleClick = event => {
        this.setState({anchorEl: event.currentTarget});
    };
    handlClose = () => {
        this.setState({anchorEl: null})
    }

    onDeleteClick = id => {
        this
            .props
            .deleteTransaction(id);
    };

    render() {
        const {transaction} = this.props;
        const open = this.state.anchorEl === null
            ? false
            : true;
        const id = this.state.open
            ? "simple-popper"
            : null;
        return (
            <>
                <tr>
                <th scope="row" className="text-left">{transaction.wallet.name}</th>
                    <th className="text-left">{transaction.subcategory.name}</th>
                                                  
                            {transaction.expenditure && <th className="text-right"><p className="text-danger">{-transaction.amount}</p></th>}
                            {!transaction.expenditure && <th className="text-right">{transaction.amount}</th>}

                                <th><Button
                                    aria-controls="simple-menu"
                                    aria-haspopup="true"
                                    onClick={this.handleClick}>
                                    ... 
                                </Button>
                                <Menu
                                    id="simple-menu"
                                    anchorEl={this.state.anchorEl}
                                    keepMounted="keepMounted"
                                    open={Boolean(this.state.anchorEl)}
                                    onClose={this.handlClose}>
                                    <MenuItem
                                        onClick={this
                                            .onDeleteClick
                                            .bind(this, transaction.id)}>Delete</MenuItem>
                                    {/* <MenuItem onClick={this.handlClose}>Edit</MenuItem> */}
                                </Menu>
                                </th>
                                </tr>

            </>
        );
    }
}

TransactionItem.propTypes = {
    deleteTransaction: PropTypes.func.isRequired
};

export default connect(null, {deleteTransaction})(TransactionItem);
