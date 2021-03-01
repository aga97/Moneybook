import React, { useEffect } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import { useDispatch, useSelector } from 'react-redux';
import { deleteTag, getTag } from '../../module/tagReducer';
import { Button, IconButton, ListItem, ListItemSecondaryAction, ListItemText, TablePagination } from '@material-ui/core';
import { getMoneyBookByTag } from '../../api/MoneyBookApi';
import { getMoneyByTag } from '../../module/moneyReducer';
import CloseIcon from '@material-ui/icons/Close';

const useStyles = makeStyles((theme) =>({
  table: {
    minWidth: 0,
  },
  closeButton: {  
    position: 'absolute',
    right: theme.spacing(1),
    color: theme.palette.grey[500],
  },
  buttonSize: {
    fontSize: '18px'
  }   
}));

function createData(id, tag) {
  return {id, tag };
}

const rows = [
  createData( 1, "BASIC 1"),
  createData( 2, "BASIC 2"),
  createData( 3, "BASIC 3"),
  createData( 4, "BASIC 4"),
];

export default function TotalTable() {
  const classes = useStyles();
  const { tags } = useSelector(state => state.tagReducer);
  const dispatch = useDispatch();

  useEffect(() => {      
    try {
      dispatch(getTag()); 
    } catch (error) {
      console.log(error);
    }      
  },[dispatch])

  const handleClick = (tag) => {
    console.log(tag)
    dispatch(getMoneyByTag(tag));
  }

  const handleDelete = (id) => {
    dispatch(deleteTag(id));
    setTimeout(() => {
      dispatch(getTag());
    }, 1000);
  }

  return (
    <div>
      {
        rows.map((row) => (
          <ListItem dense="true" button onClick={() => handleClick(row.tag)}>
            <ListItemText  primary= {row.tag} />
          </ListItem>
        ))
      }
      {
        tags && tags.map((row) => (
          <ListItem dense="true" button onClick={() => handleClick(row.tag)} >      
            <ListItemText  primary= {row.tag} />
            <ListItemSecondaryAction>
              <IconButton  size="small" edge="end" onClick={() => handleDelete(row.id)} > <CloseIcon className={classes.buttonSize} /> </IconButton>
            </ListItemSecondaryAction>
          </ListItem>
        ))
      }
    </div>
  );
}