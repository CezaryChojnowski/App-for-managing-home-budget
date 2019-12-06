import React, {Component} from "react";
import {connect} from "react-redux";
import {getPeople} from "../../actions/peopleActions";
import PropTypes from "prop-types";
import PersonItem from "./PeronItem"

class GetPeople extends Component {
    componentDidMount() {
        this
            .props
            .getPeople();
    }

    render() {
        const {people} = this.props.person;
        {console.log(people)}
        return (
            <> < div className = "wallets" > <div className="container">
                <div className="row">
                    <div className="col-md-12">
                        <h1 className="display-4 text-center">People</h1>
                        <br/>
                        <br/>
                        <hr/> {people.map(person => (<PersonItem key={person.id} person={person}/>))}
                    </div>
                </div>
            </div>
        </div>
    </>
        );
    }
}

GetPeople.propTypes = {
    person: PropTypes.object.isRequired,
    getPeople: PropTypes.func.isRequired
};

const mapStateToProps = state => ({person: state.person});

export default connect(mapStateToProps, {getPeople})(GetPeople);