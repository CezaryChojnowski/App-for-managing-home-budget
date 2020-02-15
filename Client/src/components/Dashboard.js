import React, {useState} from "react";
import {getStats} from "../actions/transactionActions"
import PropTypes from "prop-types";
import axios from 'axios';
// react plugin for creating charts
import ChartistGraph from "react-chartist";
// @material-ui/core
import {Bar, Line, Pie} from 'react-chartjs-2'

import {makeStyles} from "@material-ui/core/styles";
import Icon from "@material-ui/core/Icon";
// @material-ui/icons
import Store from "@material-ui/icons/Store";
import Warning from "@material-ui/icons/Warning";
import DateRange from "@material-ui/icons/DateRange";
import LocalOffer from "@material-ui/icons/LocalOffer";
import Update from "@material-ui/icons/Update";
import ArrowUpward from "@material-ui/icons/ArrowUpward";
import AccessTime from "@material-ui/icons/AccessTime";
import Accessibility from "@material-ui/icons/Accessibility";
import BugReport from "@material-ui/icons/BugReport";
import Code from "@material-ui/icons/Code";
import Cloud from "@material-ui/icons/Cloud";
// core components
import GridItem from "../components/Grid/GridItem.js";
import GridContainer from "../components/Grid/GridContainer.js";
import Table from "../components/Table/Table.js";
import Tasks from "../components/Tasks/Tasks.js";
import CustomTabs from "../components/CustomTabs/CustomTabs.js";
import Danger from "../components/Typography/Danger.js";
import Card from "../components/Card/Card.js";
import CardHeader from "../components/Card/CardHeader.js";
import CardIcon from "../components/Card/CardIcon.js";
import CardBody from "../components/Card//CardBody.js";
import CardFooter from "../components/Card/CardFooter.js";
import DailyExpenses from "../components/charts/DailyExpenses"
import MonetizationOnIcon from '@material-ui/icons/MonetizationOn';
import {bugs, website, server} from "../components/variables/general.js";

import {dailySalesChart, emailsSubscriptionChart, completedTasksChart} from "./variables/charts.js";

import styles from "../../src/assets/jss/material-dashboard-react/views/dashboardStyle.js";
import {useEffect} from "react";

const useStyles = makeStyles(styles);

export default function Dashboard() {

    const [numberOfTransactionsInTheCurrentMonth, setNumberOfTransactionsInTheCurrentMonth] = useState(
        null
    );
    const [balanceOfAllAccounts, setBalanceOfAllAccounts] = useState(null);
    const [averageDailyExpensesInCurrentMonth, setAverageDailyExpensesInCurrentMonth] = useState(
        null
    );
    const [sumOfExpensesForTheCurrentMonth, setSumOfExpensesForTheCurrentMonth] = useState(
        null
    );
    const [sumOfInComeForTheCurrentMonth, setSumOfInComeForTheCurrentMonth] = useState(
        null
    );
    const [dailyExpensesOfTheLastSevenDays, setDailyExpensesOfTheLastSevenDays] = useState(
      {
        labels: [
            '27-01-2020',
            '28-01-2020',
            '29-01-2020',
            '30-01-2020',
            '31-01-2020',
            '01-02-2020',
            '02-02-2020'
        ],
        datasets: [
            {
                label: 'Dialy expenses',
                data: [
                    19.99,
                    21.99,
                    50,
                    34.12,
                    35,
                    33,
                    62
                ],
                backgroundColor: ['rgba(0,0,0,0.6)']
            }
        ]
    }
    );
    const [expensesOfTheLastSevenDaysByCategories, setExpensesOfTheLastSevenDaysByCategories] = useState(
      {
        labels: [
            'Food',
            'Transport',
            'Clothes',
            'Healthcare',
            'Hobby',
            'Other'
        ],
        datasets: [
            {
                label: 'Dialy expenses',
                data: [
                    65.99,
                    30.50,
                    62,
                    34.12,
                    35,
                    33,
                ],
                backgroundColor:[
                  'rgba(255,99,132,0.6)',
                  'rgba(54,162,235,0.6)',
                  'rgba(255,206,86,0.6)',
                  'rgba(75,192,192,0.6)',
                  'rgba(153,102,255,0.6)',
                  'rgba(255,0,64,0.6)',
                ]
            }
        ]
    }
    );

    useEffect(() => {
        axios
            .get(`http://localhost:8080/transactions/stats`)
            .then(res => {
                const response = res.data
                setNumberOfTransactionsInTheCurrentMonth(
                    response.numberOfTransactionsInTheCurrentMonth
                );
                setBalanceOfAllAccounts(response.balanceOfAllAccounts);
                setAverageDailyExpensesInCurrentMonth(
                    response.averageDailyExpensesInCurrentMonth
                );
                setSumOfInComeForTheCurrentMonth(response.sumOfInComeForTheCurrentMonth);
                setSumOfExpensesForTheCurrentMonth(response.sumOfExpensesForTheCurrentMonth);
            });

    })

    const classes = useStyles();
    return (
        <div>

            <GridContainer>
                <GridItem xs={12} sm={6} md={3}>
                    <Card>
                        <CardHeader color="warning" stats="stats" icon="icon">
                            <CardIcon color="warning">
                                <LocalOffer/>
                            </CardIcon>
                            {averageDailyExpensesInCurrentMonth!=undefined &&       
                            <>                       
                            <p className={classes.cardCategory}>Average expenses in current month</p>
                            <h3 className={classes.cardTitle}>
                                {averageDailyExpensesInCurrentMonth.toFixed(2)}
                            </h3>
                            </>
                            }
                        </CardHeader>
                        <CardFooter stats="stats"></CardFooter>
                    </Card>
                </GridItem>
                <GridItem xs={12} sm={6} md={3}>
                    <Card>
                        <CardHeader color="success" stats="stats" icon="icon">
                            <CardIcon color="success">
                                <Store/>
                            </CardIcon>
                            <p className={classes.cardCategory}>Balance of all accounts</p>
                            <h3 className={classes.cardTitle}>{balanceOfAllAccounts}</h3>
                        </CardHeader>
                        <CardFooter stats="stats"></CardFooter>
                    </Card>
                </GridItem>
                <GridItem xs={12} sm={6} md={3}>
                    <Card>
                        <CardHeader color="danger" stats="stats" icon="icon">
                            <CardIcon color="danger">
                                <LocalOffer/>
                            </CardIcon>
                            <p className={classes.cardCategory}>Expenses in current month</p>
                            <h3 className={classes.cardTitle}>{sumOfExpensesForTheCurrentMonth}</h3>
                        </CardHeader>
                        <CardFooter stats="stats"></CardFooter>
                    </Card>
                </GridItem>
                <GridItem xs={12} sm={6} md={3}>
                    <Card>
                        <CardHeader color="info" stats="stats" icon="icon">
                            <CardIcon color="info">
                                <Accessibility/>
                            </CardIcon>
                            <p className={classes.cardCategory}>Income in current month</p>
                            <h3 className={classes.cardTitle}>{sumOfInComeForTheCurrentMonth}</h3>
                        </CardHeader>
                        <CardFooter stats="stats"></CardFooter>
                    </Card>
                </GridItem>
            </GridContainer>
            <GridContainer>
                <GridItem xs={12} sm={12} md={6}>
                    <Card chart="chart">
                        <CardHeader color="success">
                            {
                                dailyExpensesOfTheLastSevenDays != undefined && 
                                <Line
                                        data={dailyExpensesOfTheLastSevenDays}
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
                                                text: 'Daily expenses of the last 7 days',
                                                fontSize: 18
                                            },
                                            legend: {
                                                display: false,
                                                position: 'right',
                                                labels: {
                                                    FontSize: 5,
                                                    padding: 5
                                                }
                                            }
                                        }}/>
                            } 
                        </CardHeader>
                        <CardBody></CardBody>
                        <CardFooter chart="chart"></CardFooter>
                    </Card>
                </GridItem>
                <GridItem xs={12} sm={12} md={6}>
                    <Card chart="chart">
                        <CardHeader color="warning">
                            {
                                expensesOfTheLastSevenDaysByCategories != undefined && <Bar
                                        data={expensesOfTheLastSevenDaysByCategories}
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
                                                text: 'Expenses of the last 7 days by categories',
                                                fontSize: 18
                                            },
                                            legend: {
                                                display: false,
                                                position: 'right',
                                                labels: {
                                                    FontSize: 5,
                                                    padding: 5
                                                }
                                            }
                                        }}/>
                            }
                        </CardHeader>
                        <CardBody></CardBody>
                        <CardFooter chart="chart">
                            {/* <div className={classes.stats}></div> */}
                        </CardFooter>
                    </Card>
                </GridItem>
            </GridContainer>
        </div>
    );
}

Dashboard.propTypes = {
    // transaction: PropTypes.object.isRequired,
    getStats: PropTypes.func.isRequired
};
