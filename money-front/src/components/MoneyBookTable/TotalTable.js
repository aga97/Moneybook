import React, { useEffect } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import { useDispatch, useSelector } from 'react-redux';
import { deleteTag, getTag } from '../../module/tagReducer';
import { ListItem } from '@material-ui/core';

const useStyles = makeStyles({
  table: {
    minWidth: 0,
  },
});

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

  const handleClick = (id) => {
    console.log(id)
    dispatch(deleteTag(id));
    setTimeout(() => {
      dispatch(getTag());
    }, 1000);
  }

  return (
    <div>
      {
        rows.map((row) => (
          <ListItem button >
            {row.tag}
          </ListItem>
        ))
      }
      {
        tags && tags.map((row) => (
          <ListItem button onClick={() => handleClick(row.id)}>
            {row.tag}
          </ListItem>
        ))
      }
    </div>
  );
}