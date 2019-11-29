import React, { Component } from "react";
import PropTypes from "prop-types";
import { connect } from "react-redux";
import { createWallet } from "../../actions/walletActions";
import classnames from "classnames";
class AddWallet extends Component{

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
          validationError:{
            name:false,
            balance:false,
            financialGoal:false,
          }
        }

        this.handleChange = this.handleChange.bind(this);
        this.onChange = this.onChange.bind(this);
        this.onSubmit = this.onSubmit.bind(this);
      } 

      messages = {
        name_incorrect: 'Wallet name can not be empty',
        balance_incorrect: 'Balance can not be less than 0',
        financialGoal_incorrect: 'Financial goal must be greater than 0',
      }

      handleChange() {
        this.setState({
          checked: !this.state.checked
        })
      }

  componentWillReceiveProps(nextProps) {
    if (nextProps.errors) {
      this.setState({ errors: nextProps.errors });
    }
  }
    
      onChange(e) {
        this.setState({ [e.target.name]: e.target.value });
        const validation = this.formValidation();
        this.setState({
          validationError: {
            name: validation.name,
            balance: validation.balance,
            financialGoal: validation.financialGoal,
          }
        })
      }

      formValidation = () => {
        let name = false;
        let balance = false;
        let financialGoal = false;

        if(this.state.name.length<3 || this.state.name == ""){
          name = true;
        }

        if(this.state.balance <= 0 || this.state.balance == ""){
          balance = true;
        }

        if(this.state.checked==true && (this.state.financialGoal < 0 || this.state.financialGoal == "")){
          financialGoal = true;
        }

        return ({
          name,
          balance,
          financialGoal,
        })

      }

    
      onSubmit(e) {
        e.preventDefault();
        const validation = this.formValidation();
        this.setState({
          validationError: {
            name: validation.name,
            balance: validation.balance,
            financialGoal: validation.financialGoal
          }
        })
        let resultValidation = false;
        resultValidation = (Object.values(this.state.validationError)).includes(true);
        if(resultValidation===false){
          const newWallet = {
            name: this.state.name,
            comment: this.state.comment,
            balance: this.state.balance,
            financialGoal: this.state.financialGoal,
            savings: this.state.savings
          };
              this.props.createWallet(newWallet, this.props.history); 
                          
        }
        else{
          this.setState({
            validationError: {
              name: validation.name,
              balance: validation.balance,
              financialGoal: validation.financialGoal
            }
          })
        }
      }

    render(){

      const content = this.state.checked 
      ? <div className="form-group">
          <input className="form-control form-control-lg" placeholder="Financial Goal" type="number" name="financialGoal" value={this.state.financialGoal} onChange={this.onChange}/>
          {this.state.validationError.financialGoal && <span>{this.messages.financialGoal_incorrect}</span>}
       </div>
      : null;

      const { errors } = this.state;

        return(
            <div>
                <div className="project">
                        <div className="container">
                            <div className="row">
                            <div className="col-md-8 m-auto">
                                <h5 className="display-4 text-center">Create Wallet form</h5>
                                <hr />
                                <form onSubmit={this.onSubmit}>
                                  <div className="form-group">
                                      <input type="text" className={classnames("form-control form-control-lg", {"is-invalid": errors.message})} placeholder="Wallet name" name="name" value={this.state.name} onChange={this.onChange} autoComplete="off"/>
                                      {this.state.validationError.name && <span>{this.messages.name_incorrect}</span>}
                                      {errors.status===409 && (
                                        <div className="invalid-feedback">{errors.details}</div>
                                      )}
                                  </div>

                                  <div className="form-group">
                                      <textarea type="text" className="form-control form-control-lg" placeholder="Comment" name="comment" value={this.state.comment} onChange={this.onChange}/>
                                  </div>

                                  <div className="form-group">
                                      <input className="form-control form-control-lg" placeholder="Balance" type="number" name="balance" value={this.state.balance} onChange={this.onChange}/>
                                      {this.state.validationError.balance && <span>{this.messages.balance_incorrect}</span>}
                                  </div>

                                  { content }

                                  <label className="form-group">
                                      <input className="form-control form-control-lg" placeholder="Savings" type="checkbox" name="savings" value={this.state.savings} onChange={this.handleInputChange, this.handleChange} checked = {this.state.checked}/> Savings account
                                  </label>

                                  <input
                                      type="submit"
                                      className="btn btn-primary btn-block mt-4"
                                  />
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

const mapStateToProps = state => ({
  errors: state.errors
});

export default connect(
  mapStateToProps,
  { createWallet }
)(AddWallet);