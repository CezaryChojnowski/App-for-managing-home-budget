import React, {Component, Children} from "react";
import PropTypes from "prop-types";
import {connect} from "react-redux";
import {createSubcategory} from "../../actions/subcategoryActions";
import classnames from "classnames";
import { getCategories } from "../../actions/categoryActions";
import store from '../../store'
import state from "axios"
import CategoryItemToForm from "./CategoryItemToForm";
class AddSubcategory extends Component {

    constructor(props) {
        super(props);
        this.state = {
            name: "",
            value: "",
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
        name_incorrect: 'Subcategory name can not be empty',
    }

    componentDidMount() {
        this.props.getCategories();        
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

    handleChange(event) {
        this.setState({value: event.target.value});
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
            const newSubcategory = {
                name: this.state.name,
            };
            console.log(this.state.value);
            
            this
                .props
                .createSubcategory(this.state.value,newSubcategory, this.props.history);

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
        const {errors} = this.state
        const {categories} = this.props.category;
        
        return (
            <>
            <div>
                <div className="project">
                    <div className="container">
                        <div className="row">
                            <div className="col-md-8 m-auto">
                                <h5 className="display-4 text-center">Add new subcategory</h5>
                                <hr/>
                                <form onSubmit={this.onSubmit}>
                                    <div className="form-group">
                                    <select value={this.state.value} onChange={this.handleChange} name="id">
                                    <option value={""}> - </option>
                                    {categories.map(
                                        category => (<CategoryItemToForm key={category.id} category={category}/>)
                                    )}
                                    </select>
                                    </div>
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
            </>
        );
    }
}

AddSubcategory.propTypes = {
    createSubcategory: PropTypes.func.isRequired,
    errors: PropTypes.object.isRequired,
    category: PropTypes.object.isRequired,
    getCategories: PropTypes.func.isRequired
};


const mapStateToProps = state => ({errors: state.errors, category:state.category});

export default connect(mapStateToProps, {createSubcategory, getCategories})(AddSubcategory);