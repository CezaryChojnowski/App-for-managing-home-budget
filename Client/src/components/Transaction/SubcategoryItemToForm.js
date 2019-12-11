import React, { Component } from "react";
import { connect } from "react-redux";

class SubcategoryItemToForm extends Component {

  render() {
    const { subcategory } = this.props;
    return (
        <option value={subcategory.id}>{subcategory.name}</option>
    );
  }
}


export default connect(
  null,
)(SubcategoryItemToForm);
