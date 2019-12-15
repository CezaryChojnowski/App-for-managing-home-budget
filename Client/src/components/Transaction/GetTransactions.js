import React, {Component} from "react";
import {connect} from "react-redux";
import {getTransactions} from "../../actions/transactionActions";
import PropTypes from "prop-types";
import TransactionItem from "./TransactionItem"
import CreateTransactionButton from "./CreateTransactionButton"
import { makeStyles } from '@material-ui/core/styles';
import Button from '@material-ui/core/Button';


const MonthEnum = {
    0: 'December',
    1: 'January',
    2: 'February',
    3: 'March',
    4: 'April',
    5: 'May',
    6: 'June',
    7: 'July',
    8: 'August',
    9: 'September',
    10: 'October',
    11: 'November',
    12: 'December',
    13: 'January'
}

class GetTransactions extends Component {

    constructor() {
        super();
        const d = new Date()
        this.state = {
            month: d.getMonth(),
            year: d.getFullYear(),
            disableButtonNextMonth: false,
            disableButtonPreviousMonth: false

        };
    }
    componentDidMount() {
        this
            .props
            .getTransactions();
    }

    aMonthAgo = () => {
        if(this.state.month==1){
            this.setState({month: 12})
            this.setState({year:this.state.year-1})
        }
        else{
            this.setState({month: this.state.month-1})
        }
        
    };   
    nextMonth = () => {
        if(this.state.month==12){
            this.setState({month: 1})
            this.setState({year:this.state.year+1})
        }
        else{
            this.setState({month: this.state.month+1})           
        }
        
    };


    render() {
        console.log(this.state.month)
        const Month = this.state.month;
        const Year = this.state.year;
        const {transactions} = this.props.transaction;
        var lastYear, lastMonth, firstYear, firstMonth, lastDate, firstDate;
        transactions.sort(
            (a, b) => (a.date > b.date)
                ? 1
                : -1
        )
        if(transactions.length!=0){
            firstYear = 1*transactions[0].date.substring(4,0);
            firstMonth = 1*transactions[0].date.substring(7,5);
            lastYear = 1*transactions[transactions.length-1].date.substring(4,0);
            lastMonth = 1*transactions[transactions.length-1].date.substring(7,5);
            if((lastYear==Year && lastMonth==Month && this.state.disableButtonNextMonth==false)==true){
                this.setState({disableButtonNextMonth: true})
            }
            else if(lastMonth!=Month && this.state.disableButtonNextMonth==true){
                this.setState({disableButtonNextMonth: false})
            }

            if((firstYear==Year && firstMonth==Month && this.state.disableButtonPreviousMonth==false)==true){
                this.setState({disableButtonPreviousMonth: true})
            }      
            else if(firstMonth!=Month && this.state.disableButtonPreviousMonth==true){
                this.setState({disableButtonPreviousMonth: false})
            }
        }
        const transactionsByMonth = transactions.filter(function (el) {
            return el
                .date
                .substring(7, 5) == Month &&
                el.date.substring(4,0) == Year
        });
        return (
            <> 
            < div className = "wallets" > <div className="container">
                <div className="row">
                    <div className="col-md-12">
                        <h1 className="display-4 text-center">Transactions</h1>
                        <br/>
                        <CreateTransactionButton/>
                        {}
                        <Button onClick={this.aMonthAgo} variant="contained" color="primary" disabled={this.state.disableButtonPreviousMonth}>
                            {this.state.month==1 &&
                                <>{MonthEnum[this.state.month-1]} { this.state.year-1}</>
                            }
                            {this.state.month!=1 &&
                                <>{MonthEnum[this.state.month-1]} { this.state.year}</>
                            }
                        </Button>
                        <Button variant="contained" color="secondary">
                            {MonthEnum[this.state.month]}
                        </Button>
                        <Button id="next" onClick={this.nextMonth} variant="contained" color="primary" disabled={this.state.disableButtonNextMonth}>
                            {this.state.month!=12 && 
                                <>{MonthEnum[this.state.month+1]} { this.state.year}</>
                            }
                            {this.state.month==12 && 
                                <>{MonthEnum[this.state.month+1]} { this.state.year+1}</>
                            }
                        </Button>
                        <br/>
                        <hr/> {
                            transactionsByMonth.map(
                                transaction => (<TransactionItem key={transaction.id} transaction={transaction}/>)
                            )
                        }
                    </div>
                </div>
            </div>
        </div>
    </>
        );
    }
}

GetTransactions.propTypes = {
    transaction: PropTypes.object.isRequired,
    getTransactions: PropTypes.func.isRequired
};

const mapStateToProps = state => ({transaction: state.transaction});

export default connect(mapStateToProps, {getTransactions})(GetTransactions);