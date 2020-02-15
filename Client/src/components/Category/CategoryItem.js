import React, {Component} from "react";
import {connect} from "react-redux";
import SubcategoryItem from "./SubcategoryItem"
import {deleteCategory} from "../../actions/categoryActions"
import PropTypes from "prop-types";
import Button from '@material-ui/core/Button';
import Menu from '@material-ui/core/Menu';
import MenuItem from '@material-ui/core/MenuItem';
import { Redirect } from 'react-router-dom'
import "../../../src/table.css";


class CategoryItem extends Component {

    constructor(props) {
        super(props);
        this.state = {
            anchorEl: null,
            open: false,
            categories:[],
            show:[]
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
            .deleteCategory(id);
    };

    setRedirect = () => {
        this.setState({
          redirect: true
        })
      }
      renderRedirect = id => {
        if (this.state.redirect) {
          return <Redirect to={{
            pathname: `/updateCategory/${id}`,
        }}/>
        }
      }

    myFunction = () => {
        var x = document.getElementById("myDIV");
        if (x.style.display === "none") {
          x.style.display = "block";
        } else {
          x.style.display = "none";
        }
      }
    abc;
    abcde = (category) => {
        this.state.categories.push(category);
        // this.setState({categories: this.state.categories})
        console.log(this.abc)
        return this.abc;
    }

    render() {
        const {category} = this.props;
        const {subcategories} = this.props.category
        // this.state.categories.push(category);
        // var lenght= this.state.categories.length
        console.log(this.props)
        // console.log(lenght);
        // console.log(list.length)
        console.log(category)
        this.abcde(category)
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
                            <td class="text-left"><b>{category.name}</b></td>
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
                                            .bind(this, category.id)}>Delete</MenuItem>
                                    {this.renderRedirect(category.id)}        
                                    <MenuItem onClick={this.setRedirect}>Edit</MenuItem>
                                </Menu>
                            </td>
                            </tr>
                            {
                                subcategories.map(
                                    subcategory => (<SubcategoryItem key={subcategory.id} subcategory={subcategory}/>)
                                )
                            }
                            </>

        );
    }
}

CategoryItem.propTypes = {
    deleteCategory: PropTypes.func.isRequired
};

export default connect(null, {deleteCategory})(CategoryItem);
