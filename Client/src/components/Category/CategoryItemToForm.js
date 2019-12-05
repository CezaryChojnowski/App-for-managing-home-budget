import React, { Component } from "react";
import { connect } from "react-redux";

class CategoryItemToForm extends Component {

  render() {
    const { category } = this.props;
    return (
        <option value={category.id}>{category.name}</option>
    );
  }
}


export default connect(
  null,
)(CategoryItemToForm);
