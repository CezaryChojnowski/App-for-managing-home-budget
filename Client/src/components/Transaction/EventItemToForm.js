import React, { Component } from "react";
import { connect } from "react-redux";

class EventItemToForm extends Component {

  render() {
    const { event } = this.props;
    return (
        <option value={event.id}>{event.name}</option>
    );
  }
}


export default connect(
  null,
)(EventItemToForm);
