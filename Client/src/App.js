import React, {Component} from "react";
import "./App.css";
import Dashboard from "./components/Dashboard";
import Header from "./components/Layout/Header";
import "bootstrap/dist/css/bootstrap.min.css";
import {BrowserRouter as Router, Route, Switch} from "react-router-dom";
import AddWallet from "./components/Wallet/AddWallet";
import {Provider} from "react-redux";
import store from "./store";
import Landing from "./components/Layout/Landing";
import Register from "./components/UserManagement/Register";
import Login from "./components/UserManagement/Login";
import jwt_decode from "jwt-decode";
import setJWTToken from "./securityUtils/setJWTToken";
import {SET_CURRENT_USER} from "./actions/types";
import {logout} from "./actions/securityActions";
import SecuredRoute from "./securityUtils/SecureRoute";
import GetWallets from "./components/Wallet/GetWallets";
import GetCategories from "./components/Category/GetCategories";
import UpdateBalance from "./components/Wallet/UpdateBalance"
import UpdateWallet from "./components/Wallet/UpdateWallet";
import AddCategory from "./components/Category/AddCategory";
import AddSubcategory from "./components/Category/AddSubcategory";
import GetEvents from "./components/Event/GetEvents";
import AddEvent from "./components/Event/AddEvent";


const jwtToken = localStorage.jwtToken;

if (jwtToken) {
    setJWTToken(jwtToken);
    const decoded_jwtToken = jwt_decode(jwtToken);
    store.dispatch({type: SET_CURRENT_USER, payload: decoded_jwtToken});

    const currentTime = Date.now() / 1000;
    if (decoded_jwtToken.exp < currentTime) {
        store.dispatch(logout());
        window.location.href = "/";
    }
}

class App extends Component {
    render() {
        return (
            <Provider store={store}>
                <Router>
                    <div className="App">
                        <Header/>

                        <Route exact="exact" path="/" component={Landing}/>
                        <Route exact="exact" path="/register" component={Register}/>
                        <Route exact="exact" path="/login" component={Login}/>

                        <Switch>
                            <SecuredRoute exact="exact" path="/updateBalance" component={UpdateBalance}/>
                            <SecuredRoute exact="exact" path="/getCategories" component={GetCategories}/>
                            <SecuredRoute exact="exact" path="/getWallets" component={GetWallets}/>
                            <SecuredRoute exact="exact" path="/dashboard" component={Dashboard}/>
                            <SecuredRoute exact="exact" path="/addWallet" component={AddWallet}/>
                            <SecuredRoute exact="exact" path="/addCategory" component={AddCategory}/>
                            <SecuredRoute exact="exact" path="/addSubcategory" component={AddSubcategory}/>
                            <SecuredRoute exact path="/updateWallet/:id" component={UpdateWallet}/>
                            <SecuredRoute exact="exact" path="/getEvents" component={GetEvents}/>
                            <SecuredRoute exact="exact" path="/addEvent" component={AddEvent}/>
                        </Switch>
                    </div>
                </Router>
            </Provider>
        );
    }
}

export default App;