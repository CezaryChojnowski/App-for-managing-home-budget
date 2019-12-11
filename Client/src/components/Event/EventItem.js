import React, {Component} from "react";
import {connect} from "react-redux";
import PropTypes from "prop-types";
import Button from '@material-ui/core/Button';
import Menu from '@material-ui/core/Menu';
import MenuItem from '@material-ui/core/MenuItem';
import { Link } from "react-router-dom";
import { Redirect } from 'react-router-dom'



class EventItem extends Component {

    constructor(props) {
        super(props);
        this.state = {
            anchorEl: null,
            open: false,
            redirect: false

        };
    }
    flipOpen = () => this.setState({
        ...this.state,
        open: !this.state.open
    });
    handleClick = event => {
        this.setState({anchorEl: event.currentTarget});
    };
    handlClose = () => {
        this.setState({anchorEl: null})
    }

    setRedirect = () => {
      this.setState({
        redirect: true
      })
    }
    renderRedirect = id => {
      if (this.state.redirect) {
        return <Redirect to={{
          pathname: `/getEventTransactions/${id}`,
      }}/>
      }
    }

    render() {
      
        const {event} = this.props;
        const open = this.state.anchorEl === null
            ? false
            : true;
        const id = this.state.open
            ? "simple-popper"
            : null;
        const {classes} = this.props;
        return (
            <div className="container">
                <div className="card card-body bg-light mb-3">
                    <div className="row">
                        <div className="col-lg-6 col-md-4 col-8">
                            <h2>{event.name}</h2>
                            <h2>{event.id}</h2>
                            <div>
                                <Button
                                    aria-controls="simple-menu"
                                    aria-haspopup="true"
                                    onClick={this.handleClick}>
                                    Open Menu
                                </Button>
                                <Menu
                                    id="simple-menu"
                                    anchorEl={this.state.anchorEl}
                                    keepMounted="keepMounted"
                                    open={Boolean(this.state.anchorEl)}
                                    onClose={this.handlClose}>
                                      {this.renderRedirect(event.id)}
                                    <MenuItem
                                        onClick={this.setRedirect}>Event transactions</MenuItem>
                                    <MenuItem onClick={this.handlClose}>Edit</MenuItem>
                                </Menu>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default connect(null,)(EventItem);
