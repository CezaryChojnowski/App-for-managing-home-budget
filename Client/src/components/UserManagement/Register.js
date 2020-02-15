import React, {Component} from "react";
import {createNewUser} from "../../actions/securityActions";
import PropTypes from "prop-types";
import {connect} from "react-redux";
import classnames from "classnames";
import TextField from '@material-ui/core/TextField';
import Grid from '@material-ui/core/Grid';

class Register extends Component {
    constructor() {
        super();

        this.state = {
            firstName: "",
            lastName: "",
            password: "",
            email: "",
            errors: {},
            validationError: {
                firstName: false,
                lastName: false,
                password: false,
                email: false
            }
        };
        this.onChange = this
            .onChange
            .bind(this);
        this.onSubmit = this
            .onSubmit
            .bind(this);
    }

    messages = {
        firstName_incorrect: 'First name can not be empty',
        lastName_incorrect: 'Last name can not be empty',
        password_incorrect: 'Password can not be empty',
        email_incorrect: 'Email can not be empty'
        }

    componentDidMount() {
        if (this.props.security.validToken) {
            this
                .props
                .history
                .push("/dashboard");
        }
    }

    componentWillReceiveProps(nextProps) {
        if (nextProps.errors) {
            this.setState({errors: nextProps.errors});
        }
    }

    onSubmit(e) {
        e.preventDefault();
        const validation = this.formValidation();
        let resultValidation = false;
        resultValidation = (Object.values(validation)).includes(true);
        if(resultValidation===false){
            const newUser = {
                firstName: this.state.firstName,
                lastName: this.state.lastName,
                password: this.state.password,
                email: this.state.email
            }
            this
            .props
            .createNewUser(newUser, this.props.history);
        }
        else{
            this.setState({
                validationError: {
                    firstName: validation.firstName,
                    lastName: validation.lastName,
                    password: validation.password,
                    email: validation.email
                }
            })
        }


    }

    onChange(e) {
        this.setState({
            [e.target.name]: e.target.value
        });
    }

    formValidation = () => {
        let firstName = false;
        let lastName = false;
        let password = false;
        let email = false;

        if (this.state.firstName.length < 0 || this.state.firstName == "") {
            firstName = true;
        }

        if (this.state.lastName.length < 0 || this.state.lastName == "") {
            lastName = true;
        }

        if (this.state.password.length < 0 || this.state.password == "") {
            password = true;
        }

        if (this.state.email.length < 0 || this.state.email == "") {
            email = true;
        }

        return ({firstName, lastName, password, email})

    }

    render() {
        const {errors} = this.state;
        return (
            <div className="register">
                <div className="container">
                    <div className="row">
                        <div className="col-md-8 m-auto">
                            <p className="lead text-center">Create your account</p>
                            <form onSubmit={this.onSubmit}>
                            <div className="form-group">
                                        <Grid container justify="space-around">
                                        <TextField
                                        id="outlined-multiline-flexible"
                                        label="First name"
                                        error={this.state.validationError.firstName}
                                        helperText={this.state.validationError.firstName && this.messages.firstName_incorrect}
                                        type="text"
                                        name="firstName"
                                        autoComplete="current-password"
                                        autoComplete="current-password"
                                        variant="outlined"                                        
                                        style={{ width: 300}}
                                        rowsMax="4"
                                        value={this.state.firstName}
                                        onChange={this.onChange}
                                        />
                                        </Grid>
                                </div>
                                <div className="form-group">
                                <Grid container justify="space-around">
                                        <TextField
                                        id="outlined-multiline-flexible"
                                        label="Last name"
                                        error={this.state.validationError.lastName}
                                        helperText={this.state.validationError.lastName && this.messages.lastName_incorrect}
                                        type="text"
                                        name="lastName"
                                        autoComplete="current-password"
                                        autoComplete="current-password"
                                        variant="outlined"                                        
                                        style={{ width: 300}}
                                        rowsMax="4"
                                        value={this.state.lastName}
                                        onChange={this.onChange}
                                        />
                                        </Grid>
                                </div>
                                {console.log(errors.details)}
                                <div className="form-group">
                                <Grid container justify="space-around">
                                        <TextField
                                        id="standard-password-input"
                                        label="Password"
                                        error={this.state.validationError.password}
                                        helperText={(this.state.validationError.password && this.messages.password_incorrect)}
                                        type="password"
                                        name="password"
                                        autoComplete="current-password"
                                        autoComplete="current-password"
                                        variant="outlined"                                        
                                        style={{ width: 300}}
                                        rowsMax="4"
                                        value={this.state.password}
                                        onChange={this.onChange}
                                        />
                                        </Grid>
                                    </div>
                                    <div className="form-group">
                                    <Grid container justify="space-around">
                                        <TextField
                                        id="outlined-multiline-flexible"
                                        label="E-mail"
                                        error={this.state.validationError.email || errors.status===400 ||errors.status===4009}
                                        helperText={(this.state.validationError.email && this.messages.email_incorrect) || (errors.status===400 && errors.details) || (errors.status===409 && errors.details)}
                                        type="text"
                                        name="email"
                                        autoComplete="current-password"
                                        autoComplete="current-password"
                                        variant="outlined"                                        
                                        style={{ width: 300}}
                                        rowsMax="4"
                                        value={this.state.email}
                                        onChange={this.onChange}
                                        />
                                        </Grid>
                                    </div>
                                    <input type="submit" className="btn btn-secondary" value="Sign up"/>                            </form>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

Register.propTypes = {
    createNewUser: PropTypes.func.isRequired,
    errors: PropTypes.object.isRequired,
    security: PropTypes.object.isRequired
};

const mapStateToProps = state => (
    {errors: state.errors, security: state.security}
);
export default connect(mapStateToProps, {createNewUser})(Register);