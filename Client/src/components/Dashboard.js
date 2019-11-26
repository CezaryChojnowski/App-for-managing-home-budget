import React, { Component } from "react";
import WalletItem from "./Wallet/WalletItem";
import CreateWalletItem from "./Wallet/CreateWalletButton";
import { connect } from "react-redux";
import { getWallets } from "../actions/walletActions";
import PropTypes from "prop-types";
import CreateWalletButton from "./Wallet/CreateWalletButton";
import MovingToWalletsButton from "./Wallet/MovingToWalletsButton";

class Dashboard extends Component {
  render(){
    return(
      <MovingToWalletsButton/>
    )
  }
}
export default Dashboard;
