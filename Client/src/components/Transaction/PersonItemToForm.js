import React, { Component } from "react";
import { connect } from "react-redux";

class PersonItemToForm extends Component {

  render() {
    const { person } = this.props;
    return (
        <option value={person.id}>{person.firstName}</option>
    );
  }
}


export default connect(
  null,
)(PersonItemToForm);
