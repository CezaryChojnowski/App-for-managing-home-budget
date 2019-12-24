import React, {useState} from "react";
import {getStats} from "../actions/transactionActions"
import PropTypes from "prop-types";
import axios from 'axios';
// react plugin for creating charts
import ChartistGraph from "react-chartist";
// @material-ui/core
import { makeStyles } from "@material-ui/core/styles";
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

import { bugs, website, server } from "../components/variables/general.js";

import {
  dailySalesChart,
  emailsSubscriptionChart,
  completedTasksChart
} from "./variables/charts.js";

import styles from "../../src/assets/jss/material-dashboard-react/views/dashboardStyle.js";
import { useEffect } from "react";

const useStyles = makeStyles(styles);

export default function Dashboard() {

  const [numberOfTransactionsInTheCurrentMonth, setNumberOfTransactionsInTheCurrentMonth] = useState(null);
  const [balanceOfAllAccounts, setBalanceOfAllAccounts] = useState(null);
  const [averageDailyExpensesInCurrentMonth, setAverageDailyExpensesInCurrentMonth] = useState(null);
  const [sumOfExpensesForTheCurrentMonth, setSumOfExpensesForTheCurrentMonth] = useState(null);
  const [sumOfInComeForTheCurrentMonth, setSumOfInComeForTheCurrentMonth] = useState(null);


  useEffect(() => {
    axios.get(`http://localhost:8080/transactions/stats`).then(res => {const response = res.data
    setNumberOfTransactionsInTheCurrentMonth(response.numberOfTransactionsInTheCurrentMonth);
    setBalanceOfAllAccounts(response.balanceOfAllAccounts);
    setAverageDailyExpensesInCurrentMonth(response.averageDailyExpensesInCurrentMonth);
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
            <CardHeader color="warning" stats icon>
              <CardIcon color="warning">
                {/* <Icon>content_copy</Icon> */}
              </CardIcon>
              <p className={classes.cardCategory}>Average daily expenses</p>
              <h3 className={classes.cardTitle}>
                {averageDailyExpensesInCurrentMonth} <small>zł</small>
              </h3>
            </CardHeader>
            <CardFooter stats>
              <div className={classes.stats}>
                <Danger>
                  <Warning />
                </Danger>
                <a href="#pablo" onClick={e => e.preventDefault()}>
                  {/* Get more space */}
                </a>
              </div>
            </CardFooter>
          </Card>
        </GridItem>
        <GridItem xs={12} sm={6} md={3}>
          <Card>
            <CardHeader color="success" stats icon>
              <CardIcon color="success">
                <Store />
              </CardIcon>
              <p className={classes.cardCategory}>Balance of all accounts</p>
              <h3 className={classes.cardTitle}>{balanceOfAllAccounts} zł</h3>
            </CardHeader>
            <CardFooter stats>
              <div className={classes.stats}>
                <DateRange />
                {/* Last 24 Hours */}
              </div>
            </CardFooter>
          </Card>
        </GridItem>
        <GridItem xs={12} sm={6} md={3}>
          <Card>
            <CardHeader color="danger" stats icon>
              <CardIcon color="danger">
                {/* <Icon>info_outline</Icon> */}
              </CardIcon>
              <p className={classes.cardCategory}>Transactions in the month</p>
              <h3 className={classes.cardTitle}>{numberOfTransactionsInTheCurrentMonth}</h3>
            </CardHeader>
            <CardFooter stats>
              {/* <div className={classes.stats}>
                <LocalOffer />
                Tracked from Github
              </div> */}
            </CardFooter>
          </Card>
        </GridItem>
        <GridItem xs={12} sm={6} md={3}>
          <Card>
            <CardHeader color="info" stats icon>
              <CardIcon color="info">
                <Accessibility />
              </CardIcon>
              <p className={classes.cardCategory}>Debts</p>
            <h6 className={classes.cardTitle}>Expenses: {sumOfExpensesForTheCurrentMonth}</h6>
            <h6 className={classes.cardTitle}>InCome: {sumOfInComeForTheCurrentMonth}</h6>
            </CardHeader>
            <CardFooter stats>
              {/* <div className={classes.stats}>
                <Update />
                Just Updated
              </div> */}
            </CardFooter>
          </Card>
        </GridItem>
      </GridContainer>
      <GridContainer>
        <GridItem xs={12} sm={12} md={4}>
          <Card chart>
            <CardHeader color="success">
              <ChartistGraph
                className="ct-chart"
                data={dailySalesChart.data}
                type="Line"
                options={dailySalesChart.options}
                listener={dailySalesChart.animation}
              />
            </CardHeader>
            <CardBody>
              <h4 className={classes.cardTitle}>Daily expenses</h4>
              <p className={classes.cardCategory}>
                {/* <span className={classes.successText}>
                  <ArrowUpward className={classes.upArrowCardCategory} /> 55%
                </span>{" "}
                increase in today sales. */}
              </p>
            </CardBody>
            <CardFooter chart>
              <div className={classes.stats}>
                {/* <AccessTime /> updated 4 minutes ago */}
              </div>
            </CardFooter>
          </Card>
        </GridItem>
        <GridItem xs={12} sm={12} md={4}>
          <Card chart>
            <CardHeader color="warning">
              <ChartistGraph
                className="ct-chart"
                data={emailsSubscriptionChart.data}
                type="Bar"
                options={emailsSubscriptionChart.options}
                responsiveOptions={emailsSubscriptionChart.responsiveOptions}
                listener={emailsSubscriptionChart.animation}
              />
            </CardHeader>
            <CardBody>
              <h4 className={classes.cardTitle}>Daily report by category of last week</h4>
              {/* <p className={classes.cardCategory}>Last Campaign Performance</p> */}
            </CardBody>
            <CardFooter chart>
              <div className={classes.stats}>
                {/* <AccessTime /> campaign sent 2 days ago */}
              </div>
            </CardFooter>
          </Card>
        </GridItem>
        <GridItem xs={12} sm={12} md={4}>
          <Card chart>
            <CardHeader color="danger">
              <ChartistGraph
                className="ct-chart"
                data={completedTasksChart.data}
                type="Line"
                options={completedTasksChart.options}
                listener={completedTasksChart.animation}
              />
            </CardHeader>
            <CardBody>
              <h4 className={classes.cardTitle}>xyz</h4>
              {/* <p className={classes.cardCategory}>Last Campaign Performance</p> */}
            </CardBody>
            <CardFooter chart>
              <div className={classes.stats}>
                {/* <AccessTime /> campaign sent 2 days ago */}
              </div>
            </CardFooter>
          </Card>
        </GridItem>
      </GridContainer>
      <GridContainer>
        <GridItem xs={12} sm={12} md={6}>
          <CustomTabs
            // title="Tasks:"
            headerColor="primary"
            tabs={[
              {
                tabName: "Savings wallets",
                tabIcon: BugReport,
                // tabContent: (
                //   <Tasks
                //     checkedIndexes={[0, 3]}
                //     tasksIndexes={[0, 1, 2, 3]}
                //     tasks={bugs}
                //   />
                // )
              },
              {
                tabName: "Events",
                tabIcon: Code,
                // tabContent: (
                //   <Tasks
                //     checkedIndexes={[0]}
                //     tasksIndexes={[0, 1]}
                //     tasks={website}
                //   />
                // )
              },
              {
                tabName: "Recurring transactions",
                tabIcon: Cloud,
                // tabContent: (
                //   // <Tasks
                //   //   checkedIndexes={[1]}
                //   //   tasksIndexes={[0, 1, 2]}
                //   //   tasks={server}
                //   // />
                // )
              }
            ]}
          />
        </GridItem>
        <GridItem xs={12} sm={12} md={6}>
          <Card>
            <CardHeader color="warning">
              <h4 className={classes.cardTitleWhite}>Last 5 transactions</h4>
              <p className={classes.cardCategoryWhite}>
                {/* New employees on 15th September, 2016 */}
              </p>
            </CardHeader>
            <CardBody>
              <Table
                tableHeaderColor="warning"
                tableHead={["Wallet", "Category", "Amount"]}
                tableData={[
                  ["Karta", "Kino", "36,78"],
                  ["Karta", "Dom", "23,79"],
                  ["Gotówka", "Książka", "56,14"],
                  ["Karta", "Rachunek", "38,73"],
                  ["Karta", "Praca", "19,99"]
                ]}
              />
            </CardBody>
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
