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
import Radio from '@material-ui/core/Radio';
import Autocomplete from '@material-ui/lab/Autocomplete';
import TextField from '@material-ui/core/TextField';
import Grid from '@material-ui/core/Grid';
import "../../../src/table.css";
import axios from 'axios';



import DateFnsUtils from '@date-io/date-fns';
import {
  MuiPickersUtilsProvider,
  KeyboardTimePicker,
  KeyboardDatePicker,
} from '@material-ui/pickers';

class AddTransaction extends Component {

    constructor(props) {
        super(props);
        const d = new Date()
        this.state = {
            comment: "",
            amount: "",
            date: d,
            subcategory: "",
            category: "",
            wallet: "",
            event: "",
            person: "",
            travelMode:"",
            expenditure: true,
            moreOptions: false,
            blocks:false,
            SelectEvent:"Select event",
            errors: {},
            validationError: {
                amountValidation: false,
                walletValidation: false,
                categoryValidation: false,
                subcategoryValidation: false,
                eventValidation: false,
                dateValidation: false
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
        this.handleChangeExpenditure = this
            .handleChangeExpenditure
            .bind(this);
        this.handleChangeDate = this
            .handleChangeDate
            .bind(this);
    }

    messages = {
        amountValidation: 'Amount can not be empty',
        walletValidation: 'Select wallet',
        categoryValidation: 'Select category',
        subcategoryValidation: 'Select subcategory',
        eventValidation: 'Select event ',
        dateValidation: 'Date can not be empty'
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
        
            axios
            .get(`http://localhost:8080/users/travelMode`)
            .then(res => {
                const response = res
                    .data
                    this
                    .setState({travelMode: response})
                    if(response!=0){
                        this.setState({moreOptions: true})
                        this.setState({blocks:true})
                    }
                    
                    
            })


            var validationError =  {
                amountValidation:  false,
                walletValidation: false,
                categoryValidation:   false,
                subcategoryValidation: false,
                eventValidation:  false,
                dateValidation:  false
            }
            this.setState({validationError: validationError})
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

    handleChangeWallet(event, values) {
        if(values==null){
            this.setState({wallet: ""})
        }
        else{
        this.setState({wallet: values.id});
        }
    }

    handleChangeCategory(event, values) {
        this.setState({category: values});
        console.log(this.state.category)
    }

    handleChangeEvent(event, values) {
        this.setState({event: values});
    }

    handleChangeSubcategory(event, values) {
        this.setState({subcategory: values.id});
    }

    handleChangePerson(event) {
        this.setState({person: event.target.value});
    }

    handleChangeDate = date => {
        this.setState({date: date});
    }

    handleChangeExpenditure() {
        this.setState({
            expenditure: !this.state.expenditure
        });
    }

    runValidationAndSetStateValidationError(){
        const validation = this.formValidation();
        this.setState({
            validationError: {
                amountValidation: validation.amountValidation,
                walletValidation: validation.walletValidation,
                categoryValidation: validation.categoryValidation,
                subcategoryValidation: validation.subcategoryValidation,
                dateValidation: validation.dateValidation
            }
        })
    }

    onChange(e) {
        this.setState({
            [e.target.name]: e.target.value
        });
    }

    formValidation = () => {
        let amountValidation = false
        let walletValidation = false
        let categoryValidation = false
        let subcategoryValidation = false
        let eventValidation = false
        let dateValidation = false

        if (this.state.amount.length < 0 || this.state.amount == "") {
            amountValidation = true;
        }
        if (this.state.wallet == "" || this.state.wallet == null) {
            walletValidation = true;
        }
        if (this.state.category == "" || this.state.category == null) {
            categoryValidation = true;
        }
        if (this.state.subcategory == "" || this.state.subcategory == null) {
            subcategoryValidation = true;
        }
        if (this.state.moreOptions==true && (this.state.subcategory == "" || this.state.subcategory == null)) {
            eventValidation = true;
        }
        if(this.state.date==null){
            dateValidation = true;
        }
        return ({amountValidation, walletValidation, categoryValidation, subcategoryValidation, eventValidation, dateValidation})
    }

    onSubmit(e) {
        const validation = this.formValidation();
        this.setState({
            validationError: {
                amountValidation: validation.amountValidation,
                walletValidation: validation.walletValidation,
                categoryValidation: validation.categoryValidation,
                subcategoryValidation: validation.subcategoryValidation,
                eventValidation: validation.eventValidation,
                dateValidation: validation.dateValidation
            }
        })
        e.preventDefault();
        let resultValidation = false;
        resultValidation = (Object.values(this.state.validationError)).includes(true);
        if (resultValidation === false) {
            const newTransaction = {
                comment: this.state.comment,
                amount: this.state.amount,
                date: this.state.date,
                expenditure: this.state.expenditure
            };
            this
                .props
                .createTransaction(
                    this.state.event.id,
                    this.state.person,
                    this.state.wallet,
                    this.state.subcategory,
                    newTransaction,
                    this.props.history
                );

        } else {
            this.setState({
                validationError: {
                    amountValidation: validation.amountValidation,
                    walletValidation: validation.walletValidation,
                    categoryValidation: validation.categoryValidation,
                    subcategoryValidation: validation.subcategoryValidation,
                    eventValidation: validation.eventValidation,
                    dateValidation: validation.dateValidation
                }
            })
        }
    }

    render() {

        const {wallets} = this.props.wallet;
        const {events} = this.props.event;
        const {subcategories} = this.props.subcategory;
        const {categories} = this.props.category;
        const {errors} = this.state;
        var subcategoriesTemp;        

        var disableSubcategory=false;
        if(this.state.category!="" && this.state.category!=null){
            subcategoriesTemp = this.state.category.subcategories;
        }

        if(this.state.category=="" || this.state.category==null){
            disableSubcategory=true;
        }


        const content = this.state.moreOptions
            ? <>   <Grid container justify="space-around">
            <Autocomplete
                id="combo-box-demo"
                options={events}
                getOptionLabel={option => option.name}
                onChange={this.handleChangeEvent}
                style={{ width: 300}}
                disabled={this.state.blocks}
                renderInput={params => (
                    <TextField {...params} error={this.state.validationError.eventValidation} helperText={this.state.validationError.eventValidation && this.messages.eventValidation} label={"Select event"} variant="outlined" fullWidth />
                )}
                />
        </Grid>
    </>: null;
        return (

            <div>
                <div className="transacion">
                    <div className="container">
                        <div className="row">
                            <div className="col-md-8 m-auto">
                            <h3>Add transacion</h3>
                                <hr/>
                                <form onSubmit={this.onSubmit}>
                                <Grid container justify="space-around">
                                    <div>
                                        Expences
                                <Radio
                                    checked={this.state.expenditure === true}
                                    onChange={this.handleChangeExpenditure}
                                    value={true}
                                    name="radio-button-demo"
                                    inputProps={{ 'aria-label': 'A' }}
                                    label="Expenses"
                                />
                                InCome
                                <Radio
                                    checked={this.state.expenditure === false}
                                    onChange={this.handleChangeExpenditure}
                                    value={false}
                                    name="radio-button-demo"
                                    inputProps={{ 'aria-label': 'C' }}
                                    label="InCome"
                                />
                                </div>
                                </Grid>
                                    <Grid container justify="space-around">
                                    <Autocomplete
                                            id="combo-box-demo"
                                            options={wallets}
                                            getOptionLabel={option => option.name}                                       
                                            onChange={this.handleChangeWallet}
                                            style={{ width: 300 }}                                         
                                             renderInput={params => (
                                                <TextField {...params} error={this.state.validationError.walletValidation} helperText={this.state.validationError.walletValidation && this.messages.walletValidation} label="Select wallet" variant="outlined" fullWidth />
                                            )}
                                            />
                                    </Grid>
                                    <br/>
                                    <Grid container justify="space-around">
                                        <Autocomplete
                                            id="combo-box-demo"
                                            options={categories}
                                            name="category"
                                            getOptionLabel={option => option.name}
                                            onChange={this.handleChangeCategory}
                                            style={{ width: 300}}
                                            renderInput={params => (
                                                <TextField {...params} error={this.state.validationError.categoryValidation} helperText={this.state.validationError.categoryValidation && this.messages.categoryValidation} label="Select category" variant="outlined" fullWidth />
                                            )}
                                            />
                                    </Grid>
                                    <br/>
                                    <Grid container justify="space-around">
                                        <Autocomplete
                                            id="combo-box-demo"
                                            options={subcategoriesTemp}
                                            name="subcategory"
                                            disabled={disableSubcategory}
                                            onChange={this.handleChangeSubcategory}
                                            getOptionLabel={option => option.name}
                                            style={{ width: 300}}
                                            renderInput={params => (
                                                <TextField {...params} error={this.state.validationError.subcategoryValidation} helperText={this.state.validationError.subcategoryValidation && this.messages.subcategoryValidation} label="Select subcategory" variant="outlined" fullWidth />
                                            )}
                                            />
                                    </Grid>
                                    <br/>
                                    {/* <Grid container justify="space-around">

                                    <TextField
          id="outlined-multiline-flexible"
          label="Comment"
          name="comment"
          multiline
          style={{ width: 300}}
          rowsMax="4"
          value={this.state.comment}
          onChange={this.onChange}
          variant="outlined"
        />
        </Grid> */}
        <br/>
                            <Grid container justify="space-around">                     
                                <TextField
                                error={this.state.validationError.amountValidation} 
                                helperText={this.state.validationError.amountValidation && this.messages.amountValidation}
                                
                                        label="Amount"
                                        type="number"
                                        name="amount"
                                        style={{ width: 300}}
                                        InputLabelProps={{
                                            shrink: true,
                                        }}
                                        value={this.state.amount}
                                        onChange={this.onChange}/>
                            </Grid>
                                    <div className="form-group">

                                    <MuiPickersUtilsProvider utils={DateFnsUtils}>
      <Grid container justify="space-around">
      <KeyboardDatePicker
          margin="normal"
          id="date-picker-dialog"
          label="Date"
          format="MM/dd/yyyy"
          value={this.state.date}
          onChange={this.handleChangeDate}
          error={this.state.validationError.dateValidation} helperText={this.state.validationError.dateValidation && this.messages.dateValidation}
          style={{ width: 300}}
          KeyboardButtonProps={{
            'aria-label': 'change date',
          }}
        />
        </Grid>
        </MuiPickersUtilsProvider>

                                    </div>

                                    {content}

                                    <div className="custom-control custom-checkbox">
                                        <input
                                            type="checkbox"
                                            className="custom-control-input"
                                            id="customCheck1"
                                            value={this.state.moreOptions}
                                            disabled={this.state.blocks}
                                            onChange={this.handleChange}/>
                                        <label className="custom-control-label" htmlFor="customCheck1">More options</label>
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