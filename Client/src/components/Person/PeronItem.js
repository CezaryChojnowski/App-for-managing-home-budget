import React, {Component} from "react";
import {connect} from "react-redux";

class PersonItem extends Component {

    render() {
        const {person} = this.props;
        return (
            <div className="container">
                <div className="card card-body bg-light mb-3">
                    <div className="row">
                        <div className="col-lg-6 col-md-4 col-8">
                            <h2>{person.firstName}</h2>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default connect(null,)(PersonItem);
