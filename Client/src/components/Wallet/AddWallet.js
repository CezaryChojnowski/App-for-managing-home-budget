import React, {Component} from "react";
import PropTypes from "prop-types";
import {connect} from "react-redux";
import {createWallet} from "../../actions/walletActions";
import classnames from "classnames";
import TextField from '@material-ui/core/TextField';
import Grid from '@material-ui/core/Grid';
class AddWallet extends Component {

    constructor() {
        super();

        this.state = {
            name: "",
            comment: "",
            balance: "",
            financialGoal: "",
            savings: false,
            errors: {},
            checked: false,
            validationError: {
                name: false,
                balance: false,
                financialGoal: false
            }
        }

        this.handleChange = this
            .handleChange
            .bind(this);
        this.onChange = this
            .onChange
            .bind(this);
        this.onSubmit = this
            .onSubmit
            .bind(this);
    }

    messages = {
        name_incorrect: 'Wallet name can not be empty',
        balance_incorrect: 'Balance can not be less than 0',
        financialGoal_incorrect: 'Financial goal must be greater than 0 and balance can not be greater than financial goal'
        }

    handleChange() {
        this.setState({
            checked: !this.state.checked
        })
    }

    componentWillReceiveProps(nextProps) {
        if (nextProps.errors) {
            this.setState({errors: nextProps.errors});
        }
    }

    onChange(e) {
        this.setState({
            [e.target.name]: e.target.value
        });
    }

    formValidation = () => {
        let name = false;
        let balance = false;
        let financialGoal = false;

        if (this.state.name.length < 0 || this.state.name == "") {
            name = true;
        }

        if (this.state.balance < 0 || this.state.balance == "") {
            balance = true;
        }

        if (this.state.checked == true && (this.state.financialGoal <= 0 || this.state.financialGoal == "") || (this.state.balance>this.state.financialGoal)) {
            financialGoal = true;
        }

        return ({name, balance, financialGoal})

    }

    onSubmit(e) {
        e.preventDefault();
        const validation = this.formValidation();
        let resultValidation = false;
        resultValidation = (Object.values(validation)).includes(true);
        if (resultValidation === false) {
            if(this.state.balance>this.state.financialGoal){
                this.setState({balance: this.state.financialGoal})
            }
            var newWallet = {
                name: this.state.name,
                comment: this.state.comment,
                balance: this.state.balance,
                financialGoal: this.state.financialGoal,
                savings: this.state.checked
            };
            this
                .props
                .createWallet(newWallet, this.props.history);
        } else {
            this.setState({
                validationError: {
                    name: validation.name,
                    balance: validation.balance,
                    financialGoal: validation.financialGoal
                }
            })
        }
    }

    render() {
        console.log(errors)
        const content = this.state.checked
            ? 
            <>
            <br/>
            <Grid container="container" justify="space-around">
                    <TextField
                        error={this.state.validationError.financialGoal}
                        helperText={this.state.validationError.financialGoal && this.messages.financialGoal_incorrect}
                        label="Financial goal"
                        type="number"
                        name="financialGoal"
                        autoComplete="off"
                        style={{
                            width: 300
                        }}
                        InputLabelProps={{
                            shrink: true
                        }}
                        value={this.state.financialGoal}
                        onChange={this.onChange}/>
                </Grid>
                </>
            : null;

        const {errors} = this.state;

        return (
            <div>
                <div className="project">
                    <div className="container">
                        <div className="row">
                            <div className="col-md-8 m-auto">
                            <h3>Add wallet</h3>
                                <hr/>
                                <form onSubmit={this.onSubmit}>

                                    <Grid container="container" justify="space-around">

                                        <TextField
                                            id="outlined-multiline-flexible"
                                            label="Wallet name"
                                            name="name"
                                            multiline="multiline"
                                            style={{
                                                width: 300
                                            }}
                                            rowsMax="4"
                                            error={this.state.validationError.name || errors.status===409}
                                            helperText={(this.state.validationError.name && this.messages.name_incorrect) || (errors.status === 409 && errors.details)} 
                                            autoComplete="off"
                                            value={this.state.name}
                                            onChange={this.onChange}
                                            variant="outlined"/> {
                                            errors.status === 409 && (
                                                <div className="invalid-feedback">{errors.details}</div>
                                            )
                                        }
                                    </Grid>
                                    <br/>

                                    <Grid container="container" justify="space-around">
                                        <TextField
                                            error={this.state.validationError.balance}
                                            helperText={this.state.validationError.balance && this.messages.balance_incorrect}
                                            label="Balance"
                                            type="number"
                                            name="balance"
                                            autoComplete="off"
                                            style={{
                                                width: 300
                                            }}
                                            InputLabelProps={{
                                                shrink: true
                                            }}
                                            value={this.state.balance}
                                            onChange={this.onChange}/>
                                    </Grid>
                                    
                                    {content}
                                    <br/> 

                                    <div class="custom-control custom-checkbox">
                                        <input
                                            type="checkbox"
                                            class="custom-control-input"
                                            id="customCheck1"
                                            value={this.state.savings}
                                            onChange={this.handleInputChange,
                                            this.handleChange}/>
                                        <label class="custom-control-label" for="customCheck1">Savings wallet</label>
                                    </div>
                                    <br/>

                                    <input type="submit" className="btn btn-primary" value="Create"/>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

AddWallet.propTypes = {
    createWallet: PropTypes.func.isRequired,
    errors: PropTypes.object.isRequired
};

const mapStateToProps = state => ({errors: state.errors});

export default connect(mapStateToProps, {createWallet})(AddWallet);