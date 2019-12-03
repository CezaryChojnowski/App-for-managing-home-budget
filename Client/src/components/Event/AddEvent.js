import React, {Component} from "react";
import PropTypes from "prop-types";
import {connect} from "react-redux";
import {createEvent} from "../../actions/eventActions";
import classnames from "classnames";
class AddEvent extends Component {

    constructor() {
        super();

        this.state = {
            name: "",
            startDate: "",
            finishDate: "",
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
        const validation = this.formValidation();
        this.setState({
            validationError: {
                name: validation.name
            }
        })
    }

    formValidation = () => {
        let name = false;

        if (this.state.name.length < 0 || this.state.name == "") {
            name = true;
        }
        return ({name})

    }

    onSubmit(e) {
        e.preventDefault();
        const validation = this.formValidation();
        this.setState({
            validationError: {
                name: validation.name
            }
        })
        let resultValidation = false;
        resultValidation = (Object.values(this.state.validationError)).includes(true);
        if (resultValidation === false) {
            const newEvent = {
                name: this.state.name,
                startDate: this.state.startDate,
                finishDate: this.state.finishDate,

            };
            this
                .props
                .createEvent(newEvent, this.props.history);

        } else {
            this.setState({
                validationError: {
                    name: validation.name
                }
            })
        }
    }

    render() {

        const content = this.state.checked
            ? <div className="form-group">
                    <input
                        className="form-control form-control-lg"
                        placeholder="Financial Goal"
                        type="number"
                        name="financialGoal"
                        value={this.state.financialGoal}
                        onChange={this.onChange}/> {this.state.validationError.financialGoal && <span>{this.messages.financialGoal_incorrect}</span>}
                </div>
            : null;

        const {errors} = this.state;

        return (
            <div>
                <div className="event">
                    <div className="container">
                        <div className="row">
                            <div className="col-md-8 m-auto">
                                <h5 className="display-4 text-center">Add new wallet</h5>
                                <hr/>
                                <form onSubmit={this.onSubmit}>
                                    <div className="form-group">
                                        <input
                                            type="text"
                                            className={classnames("form-control form-control-lg", {
                                                "is-invalid": errors.status === 409
                                            })}
                                            placeholder="Event name"
                                            name="name"
                                            value={this.state.name}
                                            onChange={this.onChange}
                                            autoComplete="off"/> {this.state.validationError.name && <span>{this.messages.name_incorrect}</span>}
                                        {
                                            errors.status === 409 && (
                                                <div className="invalid-feedback">{errors.details}</div>
                                            )
                                        }
                                    </div>

                                    <div className="form-group">
                                        <input
                                            type="date"
                                            className="form-control form-control-lg"
                                            placeholder="Start date"
                                            name="startDate"
                                            value={this.state.startDate}
                                            onChange={this.onChange}/>
                                    </div>

                                    <div className="form-group">
                                        <input
                                            className="form-control form-control-lg"
                                            placeholder="Finish date"
                                            type="date"
                                            name="finishDate"
                                            value={this.state.finishDate}
                                            onChange={this.onChange}/>
                                    </div>
                                    <input type="submit" className="btn btn-primary btn-block mt-4"/>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

AddEvent.propTypes = {
    createEvent: PropTypes.func.isRequired,
    errors: PropTypes.object.isRequired
};

const mapStateToProps = state => ({errors: state.errors});

export default connect(mapStateToProps, {createEvent})(AddEvent);