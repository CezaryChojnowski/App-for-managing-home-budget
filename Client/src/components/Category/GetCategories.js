import React, {Component} from "react";
import CategoryItem from "./CategoryItem";
import {connect} from "react-redux";
import {getCategories} from "../../actions/categoryActions";
import PropTypes from "prop-types";
import CreateCategoryButton from "./CreateCategoryButton"
import CreateSubcategoryButton from "./CreateSubcategoryButton"


class GetCategories extends Component {
    componentDidMount() {
        this
            .props
            .getCategories();
    }

    render() {
        const {categories} = this.props.category;
        return (
            <div className="categories">
                <div className="container">
                    <div className="row">
                        <div className="col-md-12">
                            <h1 className="display-4 text-center">Categories</h1>
                            <br/>
                            <CreateCategoryButton/>Create category
                            <CreateSubcategoryButton/>Create subcategory
                            <hr/> {
                                categories.map(
                                    category => (<CategoryItem key={category.id} category={category}/>)
                                )
                            }
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

GetCategories.propTypes = {
    category: PropTypes.object.isRequired,
    getCategories: PropTypes.func.isRequired
};

const mapStateToProps = state => ({category: state.category});

export default connect(mapStateToProps, {getCategories})(GetCategories);
