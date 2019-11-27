import React, { Component } from "react";
import GoToWalletsButton from "./Wallet/GoToWalletsButton";
import GoToCategoriesButton from "./Category/GoToCategoriesButton";
import GoToSubcategoriesButton from "./Subcategory/GoToSubcategoriesButton";
import GoToPersonsButton from "./Person/GoToPersonsButton";
import GoToTransactionsButton from "./Transaction/GoToTransactionsButton";
import GoToEventsButton from "./Event/GoToEventsButton";

class Dashboard extends Component {
  render(){
    return(
      <>
      <GoToWalletsButton/>
      <GoToCategoriesButton/>
      <GoToSubcategoriesButton/>
      <GoToPersonsButton/>
      <GoToEventsButton/>
      <GoToTransactionsButton/>
      </>
    )
  }
}
export default Dashboard;
