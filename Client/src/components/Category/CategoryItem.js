import React, {Component} from "react";
import {connect} from "react-redux";
import CategoryItem from "./SubcategoryItem"

class SubcategoryItem extends Component {

    render() {
        const {category} = this.props;
        const {subcategories} = this.props.category
        return (
            <div className="container">
                <div className="card card-body bg-light mb-3">
                    <div className="row">
                        <div className="col-lg-6 col-md-4 col-8">
                            <h1>{category.name}</h1>
                            {
                                subcategories.map(
                                    subcategory => (<CategoryItem key={subcategory.id} subcategory={subcategory}/>)
                                )
                            }
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default connect(null,)(SubcategoryItem);
