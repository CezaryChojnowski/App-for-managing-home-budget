import React, {Component} from "react";
import CategoryItem from "./CategoryItem";
import {connect} from "react-redux";
import {getCategories} from "../../actions/categoryActions";
import PropTypes from "prop-types";
import CreateCategoryButton from "./CreateCategoryButton"
import CreateSubcategoryButton from "./CreateSubcategoryButton"
import "../../../src/table.css";

class GetCategories extends Component {
    componentDidMount() {
        this
            .props
            .getCategories();
    }

    render() {
        const {categories} = this.props.category;
        var lenght = categories.length;
        return (
            <div className="categories">
                <div className="container">
                            <h1 className="display-4 text-center">Categories</h1>
                            <br/>
                            <CreateCategoryButton/>Create category
                            <CreateSubcategoryButton/>Create subcategory
                            <hr/> 
                            <table class="table-fill">
                <thead>
                    <tr>
                        <th class="text-left">Name</th>
                        <th class="text-left">More options</th>
                    </tr>
                </thead>
                <tbody class="table-hover">
                            {
                                categories.map(
                                    category => (<CategoryItem key={category.id} category={category}/>)
                                )
                            }
                            </tbody>
                            </table>
                </div>
            </div>
        );
    }
}

GetCategories.propTypes = {
    category: PropTypes.object.isRequired,
    getCategories: PropTypes.func.isRequired,
    xyz: PropTypes.number.isRequired
    
};

const mapStateToProps = state => ({category: state.category, xyz: state.xyz});

export default connect(mapStateToProps, {getCategories})(GetCategories);
