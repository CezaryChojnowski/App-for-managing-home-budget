import React, {Component} from "react";
import {connect} from "react-redux";
import {getTransactions} from "../../actions/transactionActions";
import PropTypes from "prop-types";
import TransactionItem from "./TransactionItem"
import CreateTransactionButton from "./CreateTransactionButton"
import {makeStyles} from '@material-ui/core/styles';
import Button from '@material-ui/core/Button';
import "../../../src/Transaction.css";

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
            month: d.getMonth() + 1,
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
        if (this.state.month == 1) {
            this.setState({month: 12})
            this.setState({
                year: this.state.year - 1
            })
        } else {
            this.setState({
                month: this.state.month - 1
            })
        }

    };
    nextMonth = () => {
        if (this.state.month == 12) {
            this.setState({month: 1})
            this.setState({
                year: this.state.year + 1
            })
        } else {
            this.setState({
                month: this.state.month + 1
            })
        }

    };

    Round(n, k) {
        var factor = Math.pow(10, k + 1);
        n = Math.round(Math.round(n * factor) / 10);
        return n / (factor / 10);
    }
    render() {
        const Month = this.state.month;
        const Year = this.state.year;
        const {transactions} = this.props.transaction;
        var lastYear,
            lastMonth,
            firstYear,
            firstMonth,
            lastDate,
            firstDate;
        var totalExpenditure = 0,
            totalRevenues = 0;
        transactions.sort(
            (a, b) => (a.date > b.date)
                ? 1
                : -1
        )
        if (transactions.length != 0) {
            firstYear = 1 * transactions[0]
                .date
                .substring(4, 0);
            firstMonth = 1 * transactions[0]
                .date
                .substring(7, 5);
            lastYear = 1 * transactions[transactions.length - 1]
                .date
                .substring(4, 0);
            lastMonth = 1 * transactions[transactions.length - 1]
                .date
                .substring(7, 5);
            if ((lastYear == Year && lastMonth == Month && this.state.disableButtonNextMonth == false) == true) {
                this.setState({disableButtonNextMonth: true})
            } else if (lastMonth != Month && this.state.disableButtonNextMonth == true) {
                this.setState({disableButtonNextMonth: false})
            }

            if ((firstYear == Year && firstMonth == Month && this.state.disableButtonPreviousMonth == false) == true) {
                this.setState({disableButtonPreviousMonth: true})
            } else if (firstMonth != Month && this.state.disableButtonPreviousMonth == true) {
                this.setState({disableButtonPreviousMonth: false})
            }
        }
        console.log(transactions)
        const transactionsByMonth = transactions.filter(function (el) {
            return el
                .date
                .substring(7, 5) == Month && el
                .date
                .substring(4, 0) == Year
        });
        var s;
        for (s of transactionsByMonth) {
            if (s.expenditure === true) {
                totalExpenditure = totalExpenditure + s.amount
            } else {
                totalRevenues = totalRevenues + s.amount
            }
        }
        return (
            <> 
            <h3>Transacions</h3>
            <hr/>
            <div className="container">
            <CreateTransactionButton/>
            <div class="qwerty1">
                <div class="table-title">
                    <h5 className="text-danger">Outflow: {this.Round(totalExpenditure, 2)}</h5>
                </div>
            </div>
            <div class="qwerty2">
                <div class="table-title">
                    <h5>Inflow: {this.Round(totalRevenues, 2)}</h5>
                </div>
            </div>
            <div class="qwerty1">
                <div class="table-title">
            <Button
                onClick={this.aMonthAgo}
                variant="contained"
                color="primary"
                disabled={this.state.disableButtonPreviousMonth}>
                {
                    this.state.month == 1 && <> {
                        MonthEnum[this.state.month - 1]
                    } {
                        this.state.year - 1
                    }
                    </>
                }
                {
                    this.state.month != 1 && <> {
                        MonthEnum[this.state.month - 1]
                    } {
                        this.state.year
                    }
                    </>
                }
            </Button>
            <Button variant="contained" color="secondary">
                {MonthEnum[this.state.month]}
            </Button>
            <Button
                id="next"
                onClick={this.nextMonth}
                variant="contained"
                color="primary"
                disabled={this.state.disableButtonNextMonth}>
                {
                    this.state.month != 12 && <> {
                        MonthEnum[this.state.month + 1]
                    } {
                        this.state.year
                    }
                    </>
                }
                {
                    this.state.month == 12 && <> {
                        MonthEnum[this.state.month + 1]
                    } {
                        this.state.year + 1
                    }
                    </>
                }
            </Button>
            </div>
                </div>
                <br/>
                <table class="table-fill">
                    <thead>
                        <tr>
                            <th class="text-center">Wallet</th>
                            <th class="text-center">Subcategory</th>
                            <th class="text-center">Amount</th>
                            <th class="text-center">More options</th>
                        </tr>
                    </thead>
                    <tbody class="table-hover">
                        {
                            transactionsByMonth.map(
                                transaction => (<TransactionItem key={transaction.id} transaction={transaction}/>)
                            )
                        }
                    </tbody>
                </table>
                <table className="table">
                    <tbody></tbody>
                </table>
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