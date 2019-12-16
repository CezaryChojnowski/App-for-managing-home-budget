import React, {Component} from "react";
import PropTypes from "prop-types";
import {connect} from "react-redux";
import {createTransaction} from "../../actions/transactionActions";
import CategoryItemToForm from "../Category/CategoryItemToForm";
import {getCategories} from "../../actions/categoryActions";
import {getSimpleWallets} from "../../actions/walletActions";
import classnames from "classnames";
import WalletItemToForm from "./WalletItemToForm";
import {getEvents} from "../../actions/eventActions";
import {getSubcategories} from "../../actions/subcategoryActions";
import {getPeople} from "../../actions/peopleActions";
import EventItemToForm from "./EventItemToForm";
import SubcategoryItemToForm from "./SubcategoryItemToForm";
import PersonItemToForm from "./PersonItemToForm";
class AddTransaction extends Component {

    constructor(props) {
        super(props);

        this.state = {
            comment: "",
            amount: "",
            date: "",
            subcategory: "",
            category: "",
            wallet: "",
            event: "",
            person: "",
            moreOptions: false,
            errors: {},
            validationError: {
                name: false
            }
        }

        this.onChange = this
            .onChange
            .bind(this);
        this.onSubmit = this
            .onSubmit
            .bind(this);
        this.handleChangeCategory = this
            .handleChangeCategory
            .bind(this);
        this.handleChangeEvent = this
            .handleChangeEvent
            .bind(this);
        this.handleChangeWallet = this
            .handleChangeWallet
            .bind(this);
        this.handleChangeSubcategory = this
            .handleChangeSubcategory
            .bind(this);
        this.handleChangePerson = this
            .handleChangePerson
            .bind(this);
        this.handleChange = this
            .handleChange
            .bind(this);
    }

    messages = {
        amount_incorrect: 'Amount can not be empty',
        date_incorrect: 'Date can no be empty'
    }

    componentDidMount() {
        this
            .props
            .getSimpleWallets();
        this
            .props
            .getEvents();
        this
            .props
            .getPeople();
        this
            .props
            .getSubcategories();
        this
            .props
            .getCategories();
    }

    componentWillReceiveProps(nextProps) {
        if (nextProps.errors) {
            this.setState({errors: nextProps.errors});
        }
    }

    handleChange() {
        this.setState({
            moreOptions: !this.state.moreOptions
        })
    }

    handleChangeWallet(event) {
        this.setState({wallet: event.target.value});
    }

    handleChangeCategory(event) {
        this.setState({category: event.target.value});
    }

    handleChangeEvent(event) {
        this.setState({event: event.target.value});
    }

    handleChangeSubcategory(event) {
        this.setState({subcategory: event.target.value});
    }

    handleChangePerson(event) {
        this.setState({person: event.target.value});
    }

    onChange(e) {
        this.setState({
            [e.target.name]: e.target.value
        });
        const validation = this.formValidation();
        this.setState({
            validationError: {
                amount: validation.amount,
                date: validation.date
            }
        })
    }

    formValidation = () => {
        let amount = false;
        let date = false;

        if (this.state.amount.length < 0 || this.state.amount == "") {
            amount = true;
        }
        if (this.date == "") {
            date = true;
        }
        return ({amount, date})

    }

    onSubmit(e) {
        e.preventDefault();
        const validation = this.formValidation();
        this.setState({
            validationError: {
                amount: validation.amount,
                date: validation.date
            }
        })
        let resultValidation = false;
        resultValidation = (Object.values(this.state.validationError)).includes(true);
        if (resultValidation === false) {
            const newTransaction = {
                comment: this.state.comment,
                amount: this.state.amount,
                date: this.state.date
            };
            this
                .props
                .createTransaction(
                    this.state.event,
                    this.state.person,
                    this.state.wallet,
                    this.state.subcategory,
                    newTransaction,
                    this.props.history
                );

        } else {
            this.setState({
                validationError: {
                    amount: validation.amount,
                    date: validation.date
                }
            })
        }
    }

    render() {

        const {wallets} = this.props.wallet;
        const {events} = this.props.event;
        const {subcategories} = this.props.subcategory;
        const {people} = this.props.person;
        const {errors} = this.state;

        if (wallets[0] != undefined && subcategories[0] != undefined) {
            this.state.wallet = wallets[0].id
            this.state.subcategory = subcategories[0].id
        }

        const content = this.state.moreOptions
            ? <> 
            < div className = "form-group" > Select event < select
        className = "custom-select"
        value = {
            this.state.event
        }
        onChange = {
            this.handleChangeEvent
        }
        name = "id" > <option value={""}>
                -
            </option>
            {
            events.map(event => (<EventItemToForm key={event.id} event={event}/>))
        } </select>
            </div > <div className="form-group">
            Select person
            <select
                className="custom-select"
                value={this.state.person}
                onChange={this.handleChangePerson}
                name="id">
                <option value={""}>
                    -
                </option>
                {people.map(person => (<PersonItemToForm key={person.id} person={person}/>))}
            </select>
        </div>
    </>: null;

        return (

            <div>
                <div className="transacion">
                    <div className="container">
                        <div className="row">
                            <div className="col-md-8 m-auto">
                                <h5 className="display-4 text-center">Add transaction</h5>
                                <hr/>
                                <form onSubmit={this.onSubmit}>
                                    <div className="form-group">
                                        Sellect wallet
                                        <select
                                            className="custom-select"
                                            value={this.state.wallet}
                                            onChange={this.handleChangeWallet}
                                            name="id">
                                            {wallets.map(wallet => (<WalletItemToForm key={wallet.id} wallet={wallet}/>))}
                                        </select>
                                    </div>
                                    <div className="form-group">
                                        Sellect subcategory
                                        <select
                                            className="custom-select"
                                            value={this.state.subcategory}
                                            onChange={this.handleChangeSubcategory}
                                            name="id">
                                            {
                                                subcategories.map(
                                                    subcategory => (<SubcategoryItemToForm key={subcategory.id} subcategory={subcategory}/>)
                                                )
                                            }
                                        </select>
                                    </div>
                                    <div className="form-group">
                                        <input
                                            type="text"
                                            className={classnames("form-control form-control-lg", {
                                                "is-invalid": errors.status === 409
                                            })}
                                            placeholder="Transaction comment"
                                            name="comment"
                                            value={this.state.comment}
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
                                            type="number"
                                            className="form-control form-control-lg"
                                            placeholder="amount"
                                            name="amount"
                                            value={this.state.amount}
                                            onChange={this.onChange}/>
                                    </div>
                                    <div className="form-group">
                                        {
                                            <input
                                                    type="date"
                                                    className="form-control form-control-lg"
                                                    placeholder="Date"
                                                    name="date"
                                                    value={this.state.date}
                                                    onChange={this.onChange}/>
                                        }
                                    </div>

                                    {content}

                                    <div className="custom-control custom-checkbox">
                                        <input
                                            type="checkbox"
                                            className="custom-control-input"
                                            id="customCheck1"
                                            value={this.state.moreOptions}
                                            onChange={this.handleChange}/>
                                        <label className="custom-control-label" htmlFor="customCheck1">More option</label>
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

AddTransaction.propTypes = {
    createTransaction: PropTypes.func.isRequired,
    errors: PropTypes.object.isRequired,
    category: PropTypes.object.isRequired,
    getCategories: PropTypes.func.isRequired,
    wallet: PropTypes.object.isRequired,
    getSimpleWallets: PropTypes.func.isRequired,
    event: PropTypes.object.isRequired,
    getEvents: PropTypes.func.isRequired,
    subcategory: PropTypes.object.isRequired,
    getSubcategories: PropTypes.func.isRequired,
    person: PropTypes.object.isRequired,
    getPeople: PropTypes.func.isRequired

};

const mapStateToProps = state => ({
    errors: state.errors,
    category: state.category,
    wallet: state.wallet,
    event: state.event,
    subcategory: state.subcategory,
    person: state.person
});

export default connect(mapStateToProps, {
    createTransaction,
    getCategories,
    getSimpleWallets,
    getEvents,
    getSubcategories,
    getPeople
})(AddTransaction);