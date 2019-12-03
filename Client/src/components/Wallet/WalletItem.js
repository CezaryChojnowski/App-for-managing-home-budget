import React, {Component} from "react";
import {connect} from "react-redux";
import {deleteWallet} from "../../actions/walletActions";
import PropTypes from "prop-types";
import Button from '@material-ui/core/Button';
import Menu from '@material-ui/core/Menu';
import MenuItem from '@material-ui/core/MenuItem';

class WalletItem extends Component {

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
        this.state.ancherEl
            ? this.setState({anchorEl: null})
            : this.setState({anchorEl: event.currentTarget});
    };

    onDeleteClick = id => {
        this
            .props
            .deleteWallet(id);
    };

    render() {
        const {wallet} = this.props;
        const open = this.state.anchorEl === null
            ? false
            : true;
        const id = this.state.open
            ? "simple-popper"
            : null;
        const {classes} = this.props;
        return (
            <div className="container">
                <div className="card card-body bg-light mb-3">
                    <div className="row">
                        <div className="col-lg-6 col-md-4 col-8">
                            <h2>{wallet.name}</h2>
                            <p>{wallet.comment}</p>
                            <p>{wallet.balance}</p>
                            <div>
                                <Button
                                    aria-controls="simple-menu"
                                    aria-haspopup="true"
                                    onClick={this.handleClick}>
                                    Open Menu
                                </Button>
                                <Menu
                                    id="simple-menu"
                                    anchorEl={this.state.anchorEl}
                                    keepMounted="keepMounted"
                                    open={Boolean(this.state.anchorEl)}
                                    onClose={this.handleClose}>
                                    <MenuItem
                                        onClick={this
                                            .onDeleteClick
                                            .bind(this, wallet.id)}>Delete</MenuItem>
                                    <MenuItem onClick={this.handleClose}>Edit</MenuItem>
                                </Menu>
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