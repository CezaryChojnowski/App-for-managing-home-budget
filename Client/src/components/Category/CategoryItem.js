import React, {Component} from "react";
import {connect} from "react-redux";
import SubcategoryItem from "./SubcategoryItem"
import {deleteCategory} from "../../actions/categoryActions"
import PropTypes from "prop-types";
import Button from '@material-ui/core/Button';
import Menu from '@material-ui/core/Menu';
import MenuItem from '@material-ui/core/MenuItem';
import { Redirect } from 'react-router-dom'

class CategoryItem extends Component {

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

    render() {
        const {category} = this.props;
        const {subcategories} = this.props.category

        const {subcategory} = this.props;
        const open = this.state.anchorEl === null
            ? false
            : true;
        const id = this.state.open
            ? "simple-popper"
            : null;

        return (
            <div className="container">
                <div className="card card-body bg-light mb-3">
                    <div className="row">
                        <div className="col-lg-6 col-md-4 col-8">
                            <h1>{category.name}</h1>
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
                                    <MenuItem
                                        onClick={this
                                            .onDeleteClick
                                            .bind(this, category.id)}>Delete</MenuItem>
                                    {this.renderRedirect(category.id)}        
                                    <MenuItem onClick={this.setRedirect}>Edit</MenuItem>
                                </Menu>
                            </div>                           
                            {
                                subcategories.map(
                                    subcategory => (<SubcategoryItem key={subcategory.id} subcategory={subcategory}/>)
                                )
                            }
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

CategoryItem.propTypes = {
    deleteCategory: PropTypes.func.isRequired
};

export default connect(null, {deleteCategory})(CategoryItem);
