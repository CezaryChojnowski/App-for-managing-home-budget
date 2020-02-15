import React, {Component} from "react";
import {connect} from "react-redux";
import {deleteSubcategory} from "../../actions/subcategoryActions"
import PropTypes from "prop-types";
import Button from '@material-ui/core/Button';
import Menu from '@material-ui/core/Menu';
import MenuItem from '@material-ui/core/MenuItem';
import "../../../src/table.css";


class SubcategoryItem extends Component {

    constructor(props) {
        super(props);
        this.state = {
            anchorEl: null,
            open: false
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

    onDeleteClick = id => {
        this
            .props
            .deleteSubcategory(id);
            window
            .location
            .reload(false);
    };

    render() {
        const {subcategory} = this.props;
        const open = this.state.anchorEl === null
            ? false
            : true;
        const id = this.state.open
            ? "simple-popper"
            : null;
        return (
            <>
            <tr>
                            <td class="text-left"><p class="p-subcategory" >{subcategory.name}</p></td>
                            <td class="text-left">
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
                                    <MenuItem
                                        onClick={this
                                            .onDeleteClick
                                            .bind(this, subcategory.id)}>Delete</MenuItem>
                                    {/* <MenuItem onClick={this.handlClose}>Edit</MenuItem> */}
                                </Menu>
                            </td>
                            </tr>
                            </>
        );
    }
}

SubcategoryItem.propTypes = {
    deleteSubcategory: PropTypes.func.isRequired
};

export default connect(null, {deleteSubcategory})(SubcategoryItem);
