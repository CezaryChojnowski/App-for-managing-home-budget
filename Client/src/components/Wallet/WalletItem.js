import React, {Component} from "react";
import {connect} from "react-redux";
import {deleteWallet} from "../../actions/walletActions";
import PropTypes from "prop-types";
import Button from '@material-ui/core/Button';
import Menu from '@material-ui/core/Menu';
import MenuItem from '@material-ui/core/MenuItem';
import {Progress} from 'react-sweet-progress';
import "react-sweet-progress/lib/style.css";
import {Redirect} from 'react-router-dom'
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogContentText from '@material-ui/core/DialogContentText';
import DialogTitle from '@material-ui/core/DialogTitle';
import {getWallets} from "../../actions/walletActions";
import Select from '@material-ui/core/Select';
import Input from '@material-ui/core/Input';
import {transferFunds} from "../../actions/walletActions";
import TextField from '@material-ui/core/TextField';
import Autocomplete from '@material-ui/lab/Autocomplete';

class WalletItem extends Component {

    constructor(props) {
        super(props);
        this.state = {
            anchorEl: null,
            open: false,
            openDialog: false,
            senderWallet: "",
            amount: "",
            recipientWallet: "",
            errors:{},
            validationError: {
                amountValidation: false,
                walletSenderValidation: false,
                InsufficientFunds: false,
                goalExceeded: false,
            }
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

    messages = {
        amountValidation: 'Amount can not be empty',
        walletSenderValidation: 'Select wallet',
        InsufficientFunds: 'Insufficient funds in the wallet',
        goalExceeded: 'Goal exceeded'
    }

    formValidation = () => {
        let amountValidation = false
        let walletSenderValidation = false
        let InsufficientFunds = false
        let goalExceeded = false

        if (this.state.amount.length < 0 || this.state.amount == "") {
            amountValidation = true;
        }
        if (this.state.senderWallet == "" || this.state.senderWallet == null) {
            walletSenderValidation = true;
        }
        if (this.state.senderWallet.balance < this.state.amount) {
            InsufficientFunds = true;
        }
        if (this.state.amount > (this.state.recipientWallet.financialGoal - this.state.recipientWallet.balance)) {
            goalExceeded = true;
        }
        return (
            {amountValidation, walletSenderValidation, InsufficientFunds, goalExceeded}
        )
    }

    runValidationAndSetStateValidationError(){
        const validation = this.formValidation();
        this.setState({
            validationError: {
                amountValidation: validation.amountValidation,
                walletSenderValidation: validation.walletSenderValidation,
                InsufficientFunds: validation.InsufficientFunds,
                goalExceeded: validation.goalExceeded,
            }
        })
    }

    componentDidMount() {
        this
            .props
            .getWallets();
    }

    componentWillReceiveProps(nextProps) {
        if (nextProps.errors) {
            this.setState({errors: nextProps.errors});
        }
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

    handleChangeWallet(event, values) {
        if (values == null) {
            this.setState({senderWallet: ""})
        } else {
            this.setState({senderWallet: values});
        }
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
        const validation = this.formValidation();
        this.setState({
            validationError: {
                amountValidation: validation.amountValidation,
                walletSenderValidation: validation.walletSenderValidation,
                InsufficientFunds: validation.InsufficientFunds,
                goalExceeded: validation.goalExceeded,
            }
        })
        let resultValidation = false;
        resultValidation = (Object.values(validation)).includes(true);
        if (resultValidation === false) {
            this
                .props
                .transferFunds(
                    this.state.senderWallet.id,
                    this.state.recipientWallet.id,
                    this.state.amount
                );
            this.setState({openDialog: false}); window     .location     .reload(false);
        } else {
            this.setState({
                validationError: {
                    amountValidation: validation.amountValidation,
                    walletSenderValidation: validation.walletSenderValidation,
                    InsufficientFunds: validation.InsufficientFunds,
                    goalExceeded: validation.goalExceeded,
                }
            })
        }
        this.setState({temp: this.state.temp+1})
    }

    onChange(e) {
        this.setState({
            [e.target.name]: e.target.value
        });
    }

    render() {
        const {wallet} = this.props;
        const {wallets} = this.props.wallets;
        this.state.recipientWallet = wallet;
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

        const {errors} = this.state;
        return (
            <tr>
                <td class="text-left">{wallet.name}</td>
                <td class="text-left">{wallet.balance}</td>

                {
                    wallet.savings && <td class="text-left">
                            {wallet.financialGoal}
                        </td>
                }

                {
                    wallet.savings && <td class="text-left">
                            <Progress percent={progressValue}/>
                        </td>
                }

                <td class="text-center">
                    <Button
                        aria-controls="simple-menu"
                        aria-haspopup="true"
                        onClick={this.handleClick}>
                        . . .
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
                    <Dialog
                        open={this.state.openDialog}
                        onClose={this.handleCloseDialog}
                        aria-labelledby="form-dialog-title">
                        <DialogTitle id="form-dialog-title">Transfer funds</DialogTitle>
                        <DialogContent>
                            <DialogContentText>
                                Transfer funds to the savings wallet
                            </DialogContentText>
                            <br></br>
                            <Autocomplete
                                id="combo-box-demo"
                                options={wallets}
                                getOptionLabel={option => option.name}
                                onChange={this.handleChangeWallet}
                                style={{
                                    width: 300
                                }}
                                renderInput={params => (
                                    <TextField
                                        {...params}
                                        error={this.state.validationError.walletSenderValidation}
                                        helperText={this.state.validationError.walletSenderValidation && this.messages.walletSenderValidation}
                                        label="Select wallet"
                                        variant="outlined"
                                        fullWidth="fullWidth"/>
                                )}/>

                            <br></br>
                            <div className="form-group">
                                <TextField
                                    error={this.state.validationError.amountValidation || this.state.validationError.InsufficientFunds || this.state.validationError.goalExceeded}
                                    helperText={(this.state.validationError.amountValidation && this.messages.amountValidation) || (this.state.validationError.InsufficientFunds && this.messages.InsufficientFunds) || (this.state.validationError.goalExceeded && this.messages.goalExceeded)}
                                    label="Amount"
                                    type="number"
                                    name="amount"
                                    style={{
                                        width: 300
                                    }}
                                    InputLabelProps={{
                                        shrink: true
                                    }}
                                    value={this.state.amount}
                                    onChange={this.onChange}/>
                            </div>
                        </DialogContent>
                        <DialogActions>
                            <Button onClick={this.handleCloseDialog} color="primary">
                                Cancel
                            </Button>
                            <Button onClick={this.onSubmit} color="primary">
                                Transfer founds
                            </Button>
                        </DialogActions>
                    </Dialog>
                </td>
            </tr>
        );
    }
}

WalletItem.propTypes = {
    deleteWallet: PropTypes.func.isRequired,
    wallets: PropTypes.object.isRequired,
    getWallets: PropTypes.func.isRequired,
    transferFunds: PropTypes.func.isRequired,
    errors: PropTypes.object.isRequired
};

const mapStateToProps = state => ({wallets: state.wallet, errors:state.errors});

export default connect(
    mapStateToProps,
    {deleteWallet, getWallets, transferFunds}
)(WalletItem);