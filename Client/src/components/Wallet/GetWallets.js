import React, {Component} from "react";
import WalletItem from "./WalletItem";
import CreateWalletButton from "./CreateWalletButton";
import {connect} from "react-redux";
import {getWallets} from "../../actions/walletActions";
import PropTypes from "prop-types";
import "../../../src/table.css";

class GetWallets extends Component {
    componentDidMount() {
        this
            .props
            .getWallets();
    }

    render() {
        const {wallets} = this.props.wallet;
        const walletsSavings = wallets.filter(function (el) {
            return el
                .savings == true
        });

        const walletsNormal = wallets.filter(function (el) {
            return el
                .savings == false
        });
        return (
            
            <> 
            <div class="text-center"><CreateWalletButton/></div>

            <h3>Normal wallets</h3>
            < div className = "container" >
            <table class="table-fill">
                <thead>
                    <tr>
                        <th class="text-left">Name</th>
                        <th class="text-left">Balance</th>
                        <th class="text-left">More options</th>
                    </tr>
                </thead>
                <tbody class="table-hover">
                    {walletsNormal.map(wallet => (<WalletItem key={wallet.id} wallet={wallet}/>))}    
                </tbody>
            </table>
        </div>

        <br/>
        <hr/>

            <h3>Savings wallets</h3>

        < div className = "container" >
        <table class="table-fill">
            <thead>
                <tr>
                    <th class="text-left">Name</th>
                    <th class="text-left">Balance</th>
                    <th class="text-left">Financial goal</th>
                    <th class="text-left">Progress</th>
                    <th class="text-left">More options</th>
                </tr>
            </thead>
            <tbody class="table-hover">
                {walletsSavings.map(wallet => (<WalletItem key={wallet.id} wallet={wallet}/>))}    
            </tbody>
        </table>
    </div>
    </>
        );
    }
}

GetWallets.propTypes = {
    wallet: PropTypes.object.isRequired,
    getWallets: PropTypes.func.isRequired
};

const mapStateToProps = state => ({wallet: state.wallet});

export default connect(mapStateToProps, {getWallets})(GetWallets);