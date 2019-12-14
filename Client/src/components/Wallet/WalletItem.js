import React, {Component} from "react";
import {connect} from "react-redux";
import {deleteWallet} from "../../actions/walletActions";
import PropTypes from "prop-types";
import Button from '@material-ui/core/Button';
import Menu from '@material-ui/core/Menu';
import MenuItem from '@material-ui/core/MenuItem';
import {Link} from "react-router-dom";
import {Progress} from 'react-sweet-progress';
import "react-sweet-progress/lib/style.css";
import {Redirect} from 'react-router-dom'
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogContentText from '@material-ui/core/DialogContentText';
import DialogTitle from '@material-ui/core/DialogTitle';
import TextField from '@material-ui/core/TextField';
import {getWallets} from "../../actions/walletActions";
import FormControl from '@material-ui/core/FormControl';
import InputLabel from '@material-ui/core/InputLabel';
import Select from '@material-ui/core/Select';
import Input from '@material-ui/core/Input';
import {transferFunds} from "../../actions/walletActions";

class WalletItem extends Component {

    constructor(props) {
        super(props);
        this.state = {
            anchorEl: null,
            open: false,
            openDialog: false,
            senderWallet: "",
            amount: "",
            recipientWallet: ""
        };
        this.handleChangeWallet = this
            .handleChangeWallet
            .bind(this);
        this.onSubmit = this
            .onSubmit
            .bind(this);
        this.onChange = this
            .onChange
            .bind(this);
    }

    componentDidMount() {
        this
            .props
            .getWallets();
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
            .deleteWallet(id);
    };

    handleClickOpenDialog = () => {
        this.setState({openDialog: true})
    }

    handleCloseDialog = () => {
        this.setState({openDialog: false})
    }

    setRedirect = () => {
        this.setState({redirect: true})
    }
    renderRedirect = id => {
        if (this.state.redirect) {
            return <Redirect
                to={{
                    pathname: `/updateWallet/${id}`
                }}/>
        }
    }

    handleChangeWallet(event) {
        this.setState({senderWallet: event.target.value});
    }

    ITEM_HEIGHT = 48;
    ITEM_PADDING_TOP = 8;
    MenuProps = {
        PaperProps: {
            style: {
                maxHeight: this.ITEM_HEIGHT * 4.5 + this.ITEM_PADDING_TOP,
                width: 250
            }
        }
    };

    onSubmit(e) {
        this
            .props
            .transferFunds( this.state.senderWallet.id, this.state.recipientWallet.id, "1000");
        this.setState({openDialog: false});
        window
            .location
            .reload(false);
    }

    onChange(e) {
        this.setState({
            [e.target.name]: e.target.value
        });
    }

    render() {
        const {wallet} = this.props;
        const {wallets} = this.props.wallets;
        this.state.recipientWallet=wallet;

        const open = this.state.anchorEl === null
            ? false
            : true;
        const id = this.state.open
            ? "simple-popper"
            : null;
        const {classes} = this.props;
        var progressValue = (wallet.balance / wallet.financialGoal) * 100;
        if (progressValue > 100) {
            progressValue = 100;
        }
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
                                    onClose={this.handlClose}>
                                    {wallet.savings && <MenuItem onClick={this.handleClickOpenDialog}>Transfer the funds</MenuItem>}
                                    <MenuItem
                                        onClick={this
                                            .onDeleteClick
                                            .bind(this, wallet.id)}>Delete</MenuItem>
                                    {this.renderRedirect(wallet.id)}
                                    <MenuItem onClick={this.setRedirect}>Edit</MenuItem>
                                </Menu>
                            </div>
                            {
                                wallet.savings && <div>
                                        <Progress percent={progressValue}/>
                                    </div>
                            }
                            <Dialog
                                open={this.state.openDialog}
                                onClose={this.handleCloseDialog}
                                aria-labelledby="form-dialog-title">
                                <DialogTitle id="form-dialog-title">Subscribe</DialogTitle>
                                <DialogContent>
                                    <DialogContentText>
                                        To subscribe to this website, please enter your email address here. We will send
                                        updates occasionally.
                                    </DialogContentText>
                                    <Select
                                        labelId="demo-mutiple-name-label"
                                        id="demo-mutiple-name"
                                        value={this.state.wallet}
                                        onChange={this.handleChangeWallet}
                                        input={<Input />}
                                        MenuProps={this.MenuProps}>
                                        {
                                            wallets.map(wallet => (
                                                <MenuItem key={wallet.id} value={wallet}>
                                                    {wallet.name}
                                                </MenuItem>
                                            ))
                                        }
                                    </Select>
                                    <br></br>
                                    <input
                                        autoFocus="autoFocus"
                                        margin="dense"
                                        id="amount"
                                        label="amount"
                                        type="number"
                                        value={this.state.amount}
                                        onChange={this.onChange}/>
                                </DialogContent>
                                <DialogActions>
                                    <Button onClick={this.handleCloseDialog} color="primary">
                                        Cancel
                                    </Button>
                                    <Button onClick={this.onSubmit} color="primary">
                                        Subscribe
                                    </Button>
                                </DialogActions>
                            </Dialog>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

WalletItem.propTypes = {
    deleteWallet: PropTypes.func.isRequired,
    wallets: PropTypes.object.isRequired,
    getWallets: PropTypes.func.isRequired,
    transferFunds: PropTypes.func.isRequired
};

const mapStateToProps = state => ({wallets: state.wallet});

export default connect(
    mapStateToProps,
    {deleteWallet, getWallets, transferFunds}
)(WalletItem);