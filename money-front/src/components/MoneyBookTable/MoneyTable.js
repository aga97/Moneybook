import React, { useEffect } from 'react';
import { makeStyles, withStyles } from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import MuiDialogTitle from '@material-ui/core/DialogTitle';
import MuiDialogContent from '@material-ui/core/DialogContent';
import MuiDialogActions from '@material-ui/core/DialogActions';
import Snackbar from '@material-ui/core/Snackbar';
import MuiAlert from '@material-ui/lab/Alert';
import IconButton from '@material-ui/core/IconButton';
import CloseIcon from '@material-ui/icons/Close';
import Typography from '@material-ui/core/Typography';
import { useDispatch, useSelector } from 'react-redux';
import { Button, Dialog, TextField } from '@material-ui/core';
import { deleteMoney, getMoney, getMoneyByTag, updateMoney } from '../../module/moneyReducer';

// 스타일 정의

const useStyles = makeStyles((theme) =>({
  root: {
    width: '100%',
    '& > * + *': {
      marginTop: theme.spacing(2),
    },
  },
  table: {
    minWidth: 1000,
  },
  textfield: {
    '& .MuiTextField-root': {
      margin: theme.spacing(1),
      width: '25ch',
    },
  },
}));

const styles = (theme) => ({
  root: {
    margin: 0,
    padding: theme.spacing(2),
  },
  closeButton: {
    position: 'absolute',
    right: theme.spacing(1),
    top: theme.spacing(1),
    color: theme.palette.grey[500],
  },
})

const DialogTitle = withStyles(styles)((props) => {
  const { children, classes, onClose, ...other } = props;
  return (
    <MuiDialogTitle disableTypography className={classes.root} {...other}>
      <Typography variant="h6">{children}</Typography>
      {onClose ? (
        <IconButton aria-label="close" className={classes.closeButton} onClick={onClose}>
          <CloseIcon />
        </IconButton>
      ) : null}
    </MuiDialogTitle>
  );
});

const DialogContent = withStyles((theme) => ({
  root: {
    padding: theme.spacing(2),
  },
}))(MuiDialogContent);

const DialogActions = withStyles((theme) => ({
  root: {
    margin: 0,
    padding: theme.spacing(1),
  },
}))(MuiDialogActions);

function Alert(props) {
  return <MuiAlert elevation={6} variant="filled" {...props} />;
}

export default function MoneyTable() {
  const classes = useStyles();
  const [open, setOpen] = React.useState(false);
  const [snack, setSnack] = React.useState(false);
  const [data, setData] = React.useState(null);
  const [startDate, setStartDate] = React.useState(new Date());
  const [year, setYear] = React.useState(startDate.getFullYear());
  const [month, setMonth] = React.useState(startDate.getMonth() + 1);
  const dispatch = useDispatch();

  const { time } = useSelector(state => state.dateReducer);
  const { moneydate } = useSelector(state => state.moneyReducer);

  useEffect(() => {      
    try {
      if(time.year) {
        dispatch(getMoney(time.year,time.month)); 
      }
    } catch (error) {
      console.log(error);
    }      
  },[dispatch, time])

  const handleClickOpen = (row) => {
    setData({
      id: row.id,
      day: row.day,
      context: row.context,
      amount: row.amount,
      year: year,
      month: month,
      tag: row.tag
    });

    setOpen(true);
  }

  const handleClose = () => {
    setOpen(false);
  }

  const handleDelete = (id) => {
    setSnack(true);
    dispatch(deleteMoney(id));
    if(time.year) {
      setTimeout(() => {
        dispatch(getMoney(time.year, time.month))
      }, 1000); 
    } else {
      setTimeout(() => {
        dispatch(getMoneyByTag(moneydate[0].tag))
      }, 1000); 
    }
    
  }

  const handleUpdate = (id) => {
    setSnack(true);
    dispatch(updateMoney(id, data));
    if(time.year) {
      setTimeout(() => {
        dispatch(getMoney(time.year, time.month))
      }, 1000); 
    } else {
      setTimeout(() => {
        dispatch(getMoneyByTag(moneydate[0].tag))
      }, 1000); 
    }
  }

  const handleSnackClose = () => {
    setSnack(false);
  }

  return (
    <div>
    <TableContainer component={Paper}>
      <Table className={classes.table} aria-label="simple table">
        <TableHead>
          <TableRow>
            <TableCell>날짜</TableCell>
            <TableCell>내용</TableCell>
            <TableCell>금액</TableCell>
            <TableCell>태그</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {
          moneydate && moneydate.map((row) => (            
            <TableRow key={row.id} hover onClick={() => handleClickOpen(row)}>
              <TableCell component="th" scope="row">{row.day}</TableCell>
              <TableCell>{row.context}</TableCell>
              <TableCell>{row.amount}</TableCell>
              <TableCell>{row.tag}</TableCell>
              
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
    {
      data && 
      <Dialog open={open}>
      <DialogTitle onClose={handleClose}>
        가계부 수정
      </DialogTitle>
      <DialogContent dividers>        
          <form className={classes.textfield} noValidate autoComplete="off">
            <TextField id="year" label="년도" defaultValue={year} variant="filled" type="number" onChange={(e) => {setData({...data, year: e.target.value})}} />
            <TextField id="month" label="월" defaultValue={month} variant="filled" type="number" onChange={(e) => {setData({...data, month: e.target.value})}} />
            <TextField id="date" label="날짜" defaultValue={data.day} variant="filled" onChange={(e) => {setData({...data, day: e.target.value})}} />
            <TextField id="context" label="내용" defaultValue={data.context} variant="filled" onChange={(e) => {setData({...data, context: e.target.value})}} />
            <TextField id="amount" label="금액" defaultValue={data.amount} variant="filled" type="number" onChange={(e) => {setData({...data, amount: e.target.value})}} />
            <TextField id="tag" label="태그" defaultValue={data.tag} variant="filled" onChange={(e) => {setData({...data, tag: e.target.value})}} />
          </form>        
      </DialogContent>
      <DialogActions>
        <Button autoFocus color="primary" onClick={() => handleUpdate(data.id)}>
            수정
        </Button>
        <Button autoFocus color="secondary" onClick={() => handleDelete(data.id)}>
            삭제
        </Button>
      </DialogActions>
      </Dialog>
    }
    <Snackbar open={snack} autoHideDuration={2000}  onClose={handleSnackClose}>
      <Alert onClose={handleSnackClose} severiry="info">
        alert
      </Alert>
    </Snackbar>

    </div>
  );

 
}