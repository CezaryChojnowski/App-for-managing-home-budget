import React from 'react';
import styled from 'styled-components';
import propTypes from 'prop-types';

const Track = styled.div`
    width: 100%;
    height: 20px;
    background-color: #222;
    border-radius: 10px;
    box-shadow:inset 0 0 5px #000;
`

const Thumb = styled.div`
    width: ${props => props.percentage}%;
    height: 100%;
    background-color: #6bccf9;
    border-radius: 8px;
    font-size: 12px;
`

export default class ProgressBar extends React.Component{
    render(){
        return(
            <Track>
                <Thumb percentage={this.props.percentage}>
                </Thumb>
                {this.props.balance} / {this.props.financialGoal}
            </Track>
        )
    }
}

ProgressBar.propTypes = {
    percentage: propTypes.number,
    balance: propTypes.number,
    financialGoal: propTypes.number
}