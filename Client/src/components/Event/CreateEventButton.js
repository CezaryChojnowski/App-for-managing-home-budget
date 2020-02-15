import React from 'react'
import {Link} from 'react-router-dom'
import AddIcon from '@material-ui/icons/Add';
import {makeStyles} from '@material-ui/core/styles';
import Fab from '@material-ui/core/Fab';
import Tooltip from '@material-ui/core/Tooltip';


const useStyles = makeStyles(theme => ({
    root: {
        '& > *': {
            margin: theme.spacing(1)
        }
    },
    extendedIcon: {
        marginRight: theme.spacing(1)
    },
    fab: {
        margin: theme.spacing(2),
      },
    absolute: {
        position: 'absolute',
        bottom: theme.spacing(2),
        right: theme.spacing(3),
      },
}));
const CreateEventsButton = () => {
    const classes = useStyles();
    return (
        <> < div className = {
            classes.root
        } > 
        <Link to="/addEvent">
            <Tooltip title="Add" aria-label="add">
        <Fab color="primary" className={classes.fab}>
          <AddIcon />
        </Fab>
      </Tooltip>

        </Link>
    </div>
</>
    );
};

export default CreateEventsButton;