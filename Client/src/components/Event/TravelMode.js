import React, {Component} from "react";
import Button from '@material-ui/core/Button';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogContentText from '@material-ui/core/DialogContentText';
import DialogTitle from '@material-ui/core/DialogTitle';
import axios from 'axios';
import TextField from '@material-ui/core/TextField';
import Autocomplete from '@material-ui/lab/Autocomplete';

class TravelMode extends Component {
    constructor(props) {
        super(props);
        this.state = {
            anchorEl: null,
            open: false,
            openDialog: false,
            eventID: "",
            events:[],
            event:""
        }
        this.handleChangeEvent = this
        .handleChangeEvent
        .bind(this);
    };

    componentDidMount() {
        axios
            .get(`http://localhost:8080/users/travelMode`)
            .then(res => {
                const response = res
                    .data
                    this
                    .setState({eventID: response})
            })
            axios
            .get(`http://localhost:8080/events/all`)
            .then(res => {
                const response = res
                    .data
                    this
                    .setState({events: response})
            })
    }

    handleChangeEvent(event, values) {
        if (values == null) {
            this.setState({event: ""})
        } else {
            this.setState({event: values});
        }
        console.log(this.state.event.id)
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

    handleClickOpenDialog = () => {
        this.setState({openDialog: true})
    }

    handleCloseDialog = () => {
        this.setState({openDialog: false})
    }

    turnOff = () => {
        axios.patch(`http://localhost:8080/users/turnOffTravelMode`)
        window.location.reload(false)
    }

    turnOn = () => {
        axios.patch(`http://localhost:8080/users/turnOnTravelMode?event=${this.state.event.id}`)
        window.location.reload(false)
    }

    render() {
        return (
            <> 
            < Button className = "nav-link" onClick = {
                this.handleClickOpenDialog
            } > Travel mode
            </Button>
            <Dialog
                        open={this.state.openDialog}
                        onClose={this.handleCloseDialog}
                        aria-labelledby="form-dialog-title">
                        <DialogTitle id="form-dialog-title">Travel mode</DialogTitle > <DialogContent>
                {(this.state.eventID==0) && 
                <DialogContentText>                
Select the event that will be set by default when adding transactions
                
                </DialogContentText>
                }
                {(this.state.eventID!=0) && 
                <DialogContentText>
                     A travel system is currently available
                </DialogContentText>
                }
                <br></br>
                {(this.state.eventID==0) && 
                <Autocomplete
                            id="combo-box-demo"
                            options={this.state.events}
                            getOptionLabel={option => option.name}
                            onChange={this.handleChangeEvent}
                            style={{
                                width: 300
                            }}
                            renderInput={params => (
                                <TextField
                                    {...params}                           
                                    label="Select events"
                                    variant="outlined"
                                    fullWidth="fullWidth"/>
            )}/>}
            </DialogContent>
            <DialogActions>
                <Button onClick={this.handleCloseDialog} color="primary">
                    Cancel
                </Button>
                {(this.state.eventID!=0) && 
                <Button onClick={this.turnOff} color="primary">
                    Turn off travel mode
                </Button>
                }                        
                {(this.state.eventID==0) && 
                <Button onClick={this.turnOn} color="primary">
                    Turn on travel mode
                </Button>
                }


            </DialogActions>
        </Dialog>

    </>
        )
    }
}

export default TravelMode;