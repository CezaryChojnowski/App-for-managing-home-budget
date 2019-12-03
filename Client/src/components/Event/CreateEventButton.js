import React from 'react'
import {Link} from 'react-router-dom'
import AddIcon from '@material-ui/icons/Add';
import {makeStyles} from '@material-ui/core/styles';
import Fab from '@material-ui/core/Fab';


const useStyles = makeStyles(theme => ({
    root: {
        '& > *': {
            margin: theme.spacing(1)
        }
    },
    extendedIcon: {
        marginRight: theme.spacing(1)
    }
}));
const CreateEventsButton = () => {
    const classes = useStyles();
    return (
        <> < div className = {
            classes.root
        } > <Link to="/addEvent">
            <Fab color="primary" aria-label="add">
                <AddIcon/>
            </Fab>
        </Link>
    </div>
</>
    );
};

export default CreateEventsButton;