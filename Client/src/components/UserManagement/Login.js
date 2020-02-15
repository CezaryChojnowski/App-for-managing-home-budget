import React, {Component} from "react";
import PropTypes from "prop-types";
import {connect} from "react-redux";
import classnames from "classnames";
import {login} from "../../actions/securityActions";
import TextField from '@material-ui/core/TextField';
import Grid from '@material-ui/core/Grid';

class Login extends Component {
    constructor() {
        super();
        this.state = {
            email: "",
            password: "",
            errors: {},
            validationError: {
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
        email_incorrect: 'Invalid Email',
        password_incorrect: 'Invalid Password'
    }

        formValidation = () => {
            let email = false;
            let password = false;
    
            if (this.state.email.length < 0 || this.state.email == "") {
                email = true;
            }
    
            if (this.state.password.length < 0 || this.state.password == "") {
                password = true;
            }
            return ({email, password})
        }

    componentDidMount() {
        if (this.props.security.validToken) {
            this
                .props
                .history
                .push("");
        }
    }

    componentWillReceiveProps(nextProps) {
        if (nextProps.security.validToken) {
            this
                .props
                .history
                .push("");
        }

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
        const LoginRequest = {
            email: this.state.email,
            password: this.state.password
        };
        this
            .props
            .login(LoginRequest);
    }else{
        this.setState({
            validationError: {
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

    render() {
        const {errors} = this.state;
        return (
            <div className="login">
                <div className="container">
                    <div className="row">
                        <div className="col-sm-8 m-auto">
                        <Grid container justify="space-around" style={{textAlign:"left"}}>
                        <p className="lead text-center">Sign in</p>
                            </Grid>
                            <form onSubmit={this.onSubmit}>
                                <div className="form-group">
                                        <Grid container justify="space-around">
                                        <TextField
                                        id="standard-error-helper-text"
                                        label="E-mail"
                                        name="email"
                                        error={errors.email || this.state.validationError.email}
                                        helperText={(errors.email && errors.email) || (this.state.validationError.email && this.messages.email_incorrect)}
                                        style={{ width: 300}}
                                        rowsMax="4"
                                        value={this.state.email}
                                        onChange={this.onChange}
                                        variant="outlined"
                                        className={classnames("", {"is-invalid": errors.email})}
                                        />
                                        {errors.email && (<div className="invalid-feedback">{errors.email}</div>)}
                                        </Grid>
                                </div>
                                <div className="form-group">
                                <Grid container justify="space-around">
                                        <TextField
                                        id="standard-password-input"
                                        label="Password"
                                        type="password"
                                        name="password"
                                        error={errors.password || this.state.validationError.password}
                                        helperText={(errors.password && errors.password) || this.state.validationError.password && this.messages.password_incorrect}
                                        autoComplete="current-password"
                                        autoComplete="current-password"
                                        variant="outlined"                                        
                                        style={{ width: 300}}
                                        rowsMax="4"
                                        value={this.state.password}
                                        onChange={this.onChange}
                                        />
                                        {errors.password && (<div className="invalid-feedback">{errors.password}</div>)}
                                        </Grid>
                                </div>
                                <input type="submit" className="btn btn-secondary" value="Sign in"/>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

Login.propTypes = {
    login: PropTypes.func.isRequired,
    errors: PropTypes.object.isRequired,
    security: PropTypes.object.isRequired
};

const mapStateToProps = state => (
    {security: state.security, errors: state.errors}
);

export default connect(mapStateToProps, {login})(Login);