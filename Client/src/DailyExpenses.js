import React, {Component} from "react";
import {Bar, Line, Pie} from 'react-chartjs-2'
import axios from 'axios';
import Grid from '@material-ui/core/Grid';
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

class DailyExpenses extends Component {
    constructor() {
        super();
        const d = new Date()
        this.state = {
            DailyExpenses:[],
            chartData: {},
            month: d.getMonth()+1,
            year: d.getFullYear(),
            disableButtonNextMonth: false,
            disableButtonPreviousMonth: false
        }
    }

    componentDidMount(){
        axios.get(`http://localhost:8080/transactions/getDailyExpenses`).then(res => {const response = res.data
        this.setState({DailyExpenses: response})
        const d = new Date()

        const transactionsByMonth = this.state.DailyExpenses.filter(function (el) {
            return el
                .date
                .substring(7, 5) == d.getMonth()+1 && el
                .date
                .substring(4, 0) == d.getFullYear()
        });
        var s;
        var DateArray = [];
        var amountArray = [];
        for (s of transactionsByMonth) {
            DateArray.push(s.date.substring(10,8))
          }
          for (s of transactionsByMonth) {
            amountArray.push(s.amount)
          }
          var datasetsToSate = {
            labels: DateArray,
            datasets: [
                {
                    label: 'Expenses',
                    data: amountArray,
                    backgroundColor: ['rgba(255,99,132,0.6)', 
                    'rgba(52,162,235,0.6)', 
                    'rgba(115,5,161,0.6)',
                    'rgba(104,217,139,0.6)',
                    'rgba(140,186,205,0.6)',
                    'rgba(200,138,58,0.6)',
                    'rgba(218,178,76,0.6)',
                    'rgba(1,99,141,0.6)',
                    'rgba(7,53,110,0.6)',
                    'rgba(36,137,150,0.6)',
                    'rgba(0,70,57,0.6)',
                    'rgba(153,107,86,0.6)',
                    'rgba(249,155,204,0.6)',
                ]
                },
            ],
        }
        this.setState({chartData: datasetsToSate})
    })
    console.log(this.state.month)

    }

    aMonthAgo = () => {
        console.log(this.state)
        if(this.state.month==1){
            this.setState({month: 12})
            this.setState({year:this.state.year-1})
        }
        else{
            this.setState({month: this.state.month-1})
            this.setState({month: this.state.month-1})
        }
        console.log(this.state)
        var Month = this.state.month
        var Year = this.state.year
        var transactionsByMonth = this.state.DailyExpenses.filter(function (el) {
            return el
                .date
                .substring(7, 5) == Month && el
                .date
                .substring(4, 0) == Year
        });
        var s;
        var DateArray = [];
        var amountArray = [];
        for (s of transactionsByMonth) {
            DateArray.push(s.date.substring(10,8))
          }
          for (s of transactionsByMonth) {
            amountArray.push(s.amount)
          }
          var datasetsToSate = {
            labels: DateArray,
            datasets: [
                {
                    label: 'Expenses',
                    data: amountArray,
                    backgroundColor: ['rgba(255,99,132,0.6)', 
                    'rgba(52,162,235,0.6)', 
                    'rgba(115,5,161,0.6)',
                    'rgba(104,217,139,0.6)',
                    'rgba(140,186,205,0.6)',
                    'rgba(200,138,58,0.6)',
                    'rgba(218,178,76,0.6)',
                    'rgba(1,99,141,0.6)',
                    'rgba(7,53,110,0.6)',
                    'rgba(36,137,150,0.6)',
                    'rgba(0,70,57,0.6)',
                    'rgba(153,107,86,0.6)',
                    'rgba(249,155,204,0.6)',
                ]
                },
            ],
        }
        this.setState({chartData: datasetsToSate})
        console.log(this.state.month)
    };   

    nextMonth = () => {
        if(this.state.month==12){
            this.setState({month: 1})
            this.setState({year:this.state.year+1})
        }
        else{
            this.setState({month: this.state.month+1})           
        }

        var Month = this.state.month
        var Year = this.state.year
        const d = new Date()

        const transactionsByMonth = this.state.DailyExpenses.filter(function (el) {
            return el
                .date
                .substring(7, 5) == Month && el
                .date
                .substring(4, 0) == Year
        });
        var s;
        var DateArray = [];
        var amountArray = [];
        for (s of transactionsByMonth) {
            DateArray.push(s.date.substring(10,8))
          }
          for (s of transactionsByMonth) {
            amountArray.push(s.amount)
          }
          var datasetsToSate = {
            labels: DateArray,
            datasets: [
                {
                    label: 'Expenses',
                    data: amountArray,
                    backgroundColor: ['rgba(255,99,132,0.6)', 
                    'rgba(52,162,235,0.6)', 
                    'rgba(115,5,161,0.6)',
                    'rgba(104,217,139,0.6)',
                    'rgba(140,186,205,0.6)',
                    'rgba(200,138,58,0.6)',
                    'rgba(218,178,76,0.6)',
                    'rgba(1,99,141,0.6)',
                    'rgba(7,53,110,0.6)',
                    'rgba(36,137,150,0.6)',
                    'rgba(0,70,57,0.6)',
                    'rgba(153,107,86,0.6)',
                    'rgba(249,155,204,0.6)',
                ]
                },
            ],
        }
        this.setState({chartData: datasetsToSate})
        console.log(this.state.month)
    };

    render() {
        return (
            <>
            <br/>
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
            <div className="container">
                <div className="row">
                    <div className="col-md-8 m-auto">
                        <Grid container="container" justify="space-around">

                            <Bar
                                data={this.state.chartData}
                                options={{
                                    low: 0,
                                    high: 50, // creative tim: we recommend you to set the high sa the biggest value + something for a better look
                                    chartPadding: {
                                      top: 0,
                                      right: 0,
                                      bottom: 0,
                                      left: 0
                                    },
                                    title: {
                                        display: true,
                                        text: 'Daily expenses',
                                        fontSize: 18
                                    },
                                    legend: {
                                        display: false,
                                        labels: {
                                            // This more specific font property overrides the global property
                                            FontSize: 5,
                                            padding: 5
                                        }},
                                }}
                                />
                        </Grid>
                    </div>
                </div>
            </div>
            </>
        )
    }
}

export default DailyExpenses;