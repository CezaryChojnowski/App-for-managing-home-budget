import React, { Component } from "react";
import WalletItem from "./WalletItem";
import CreateWalletButton from "./CreateWalletButton";
import { connect } from "react-redux";
import { getWallets } from "../../actions/walletActions";
import PropTypes from "prop-types";
import UpdateBalanceButton from "./UpdateBalanceButton";

class GetWallets extends Component {
  componentDidMount() {
    this.props.getWallets();
  }



  render() {
    const {wallets} = this.props.wallet;
    return (
      <>
      <div className="wallets">
        <div className="container">
          <div className="row">
            <div className="col-md-12">
              <h1 className="display-4 text-center">Wallets</h1>
              <br />
              <CreateWalletButton />
              <br />
              <hr />
              {wallets.map(wallet => (
                <WalletItem key={wallet.id} wallet={wallet} />
              ))}
            </div>
          </div>
        </div>
      </div>
      </>
    );
  }
}

GetWallets.propTypes = {
  wallet: PropTypes.object.isRequired,
  getWallets: PropTypes.func.isRequired
};

const mapStateToProps = state => ({
  wallet: state.wallet
});

export default connect(
  mapStateToProps,
  { getWallets }
)(GetWallets);
