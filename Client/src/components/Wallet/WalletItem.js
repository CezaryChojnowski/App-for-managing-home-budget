import React, { Component } from "react";
import { Link } from "react-router-dom";
import PropTypes from "prop-types";
import { connect } from "react-redux";

class WalletItem extends Component {

  render() {
    const { wallet } = this.props;
    return (
      <div className="container">
        <div className="card card-body bg-light mb-3">
          <div className="row">
            <div className="col-lg-6 col-md-4 col-8">
              <h2>{wallet.name}</h2>
              <p>{wallet.comment}</p>
            </div>
          </div>
        </div>
      </div>
    );
  }
}


export default connect(
  null,
)(WalletItem);
