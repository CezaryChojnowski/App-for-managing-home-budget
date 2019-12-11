import React, { Component } from "react";
import { connect } from "react-redux";

class WalletItemToForm extends Component {

  render() {
    const { wallet } = this.props;
    return (
        <option value={wallet.id}>{wallet.name}</option>
    );
  }
}


export default connect(
  null,
)(WalletItemToForm);
