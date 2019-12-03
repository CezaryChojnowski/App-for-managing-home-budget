import React, {Component} from "react";
import PropTypes from "prop-types";
import {connect} from "react-redux";
import {createCategory} from "../../actions/categoryActions";
import classnames from "classnames";
class AddCategory extends Component {

    constructor() {
        super();

        this.state = {
            name: "",
            errors: {},
            checked: false,
            validationError: {
                name: false
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
        name_incorrect: 'Category name can not be empty',
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
                name: validation.name,
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
                name: validation.name,
            }
        })
        let resultValidation = false;
        resultValidation = (Object.values(this.state.validationError)).includes(true);
        if (resultValidation === false) {
            const newCategory = {
                name: this.state.name,
            };
            this
                .props
                .createCategory(newCategory, this.props.history);

        } else {
            this.setState({
                validationError: {
                    name: validation.name,
                }
            })
        }
    }

    render() {

        const content = this.state.checked
        const {errors} = this.state;

        return (
            <div>
                <div className="project">
                    <div className="container">
                        <div className="row">
                            <div className="col-md-8 m-auto">
                                <h5 className="display-4 text-center">Add new category</h5>
                                <hr/>
                                <form onSubmit={this.onSubmit}>
                                    <div className="form-group">
                                        <input
                                            type="text"
                                            className={classnames("form-control form-control-lg", {
                                                "is-invalid": errors.status === 409
                                            })}
                                            placeholder="Category name"
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

AddCategory.propTypes = {
    createCategory: PropTypes.func.isRequired,
    errors: PropTypes.object.isRequired
};

const mapStateToProps = state => ({errors: state.errors});

export default connect(mapStateToProps, {createCategory})(AddCategory);