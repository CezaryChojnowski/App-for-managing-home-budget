import React, {Component} from "react";
import {connect} from "react-redux";
import {getEvents} from "../../actions/eventActions";
import PropTypes from "prop-types";
import CreateEventButton from "./CreateEventButton";
import EventItem from "./EventItem";

class GetEvents extends Component {
    componentDidMount() {
        this
            .props
            .getEvents();
    }

    render() {
        const {events} = this.props.event;
        return (
            <> < div className = "wallets" > <div className="container">
                <div className="row">
                    <div className="col-md-12">
                        <h1 className="display-4 text-center">Events</h1>
                        <br/>
                        <CreateEventButton/>
                        <br/>
                        <hr/> {events.map(event => (<EventItem key={event.id} event={event}/>))}
                    </div>
                </div>
            </div>
        </div>
    </>
        );
    }
}

GetEvents.propTypes = {
    event: PropTypes.object.isRequired,
    getEvents: PropTypes.func.isRequired
};

const mapStateToProps = state => ({event: state.event});

export default connect(mapStateToProps, {getEvents})(GetEvents);