import React, { useEffect } from 'react';
import { makeStyles, withStyles } from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import Button from '@material-ui/core/Button';
import Dialog from '@material-ui/core/Dialog';
import MuiDialogTitle from '@material-ui/core/DialogTitle';
import MuiDialogContent from '@material-ui/core/DialogContent';
import MuiDialogActions from '@material-ui/core/DialogActions';
import IconButton from '@material-ui/core/IconButton';
import CloseIcon from '@material-ui/icons/Close';
import Typography from '@material-ui/core/Typography';
import { useDispatch, useSelector } from 'react-redux';
import { createTrading, deleteTrading, getStockByTicker, updateTrading } from '../../module/stockTradingReducer';
import { getStockPersonal } from '../../api/StockPersonalApi';
import { deletePersonal, getPersonal, updatePersonal } from '../../module/stockPersonalReducer';
import { TextField } from '@material-ui/core';
import { updateStockTrading } from '../../api/StockTradingApi';

const useStyles = makeStyles((theme) =>({
  table: {
    minWidth: 0,
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
});

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

export default function StockTable() {
  const classes = useStyles();
  const dispatch = useDispatch();
  const [open, setOpen] = React.useState(false);
  const [changeOpen, setChangeOpen] = React.useState(false);
  const [data, setData] = React.useState({});
  const [startDate, setStartDate] = React.useState(new Date());
  const [day, setDat] = React.useState(startDate.getDate());
  const [year, setYear] = React.useState(startDate.getFullYear());
  const [month, setMonth] = React.useState(startDate.getMonth() + 1);
  const [stockPersonalData, setStockPersonalData] = React.useState({});
  const [changePersonalOpen, setChangePersonalOpen] = React.useState(false);
  const [changeTradingOpen, setChangeTradingOpen] = React.useState(false);
  const [stockTradingData, setStockTradingData] = React.useState({});

  const { time } = useSelector(state => state.dateReducer);
  const { tradingdata } = useSelector(state => state.stockTradingReducer);
  const { personaldata } = useSelector(state => state.stockPersonalReducer);

  useEffect(() => {      
    try {           
      dispatch(getPersonal());      
    } catch (error) {
      console.log(error);
    }      
  },[dispatch])

  const handleClick = (row) => {
    setOpen(true);    
    setData({
      ...data,
      ticker: row.ticker
    })
    try {
      dispatch(getStockByTicker(row.ticker));
    } catch (error) {
      console.log(error);
    }
  }

  const handleChange = (row) => {
    setData({
      id: row.id,
      day: row.day,
      ticker: row.ticker,
      price: row.price,
      year: year,
      month: month,
      stockquantity: row.stockquantity
    });
    setChangeOpen(true);
  }

  const handleChageClose = () => {
    setChangeOpen(false);
  }

  const handleClose = () => {
    setOpen(false);
  }

  const handleDelete = () => {
    dispatch(deleteTrading(data.id));  
  }

  const handleUpdate = () => {
    dispatch(updateTrading(data.id, data));
    setTimeout(() => {
      dispatch(getStockByTicker(data.ticker));
    }, 1000); 
  }

  const handleChangePersonalOpen = (data) => {
    setStockPersonalData({
      id: data.id,
      ticker: data.ticker,
      targetquantity: data.targetquantity,
      currentquantity: 0
    });
    setChangePersonalOpen(true);
  }

  const handleChangePersonalClose = () => {
    setChangePersonalOpen(false);
  }

  const handlePersonalDelete = (id) => {
    dispatch(deletePersonal(id));
    setTimeout(() => {
      dispatch(getPersonal()); 
    }, 1000); 
  }

  const handleUpdatePersonal = () => {
    dispatch(updatePersonal(stockPersonalData.id, stockPersonalData));
    setTimeout(() => {
      dispatch(getPersonal()); 
    }, 1000); 
  }

  const handleChangeTradingOpen = () => {
    setData({
      ...data,
      day: day,
      price: 0,
      year: year,
      month: month,
      stockquantity: 0
    });
    setChangeTradingOpen(true);
  }

  const handleChangeTradingClose = () => {
    setChangeTradingOpen(false);
  }

  const handleCreateTrading = () => {
    dispatch(createTrading(data));
    setTimeout(() => {
      dispatch(getStockByTicker(data.ticker));
    }, 1000); 
    
  }

  return (
    <div>
    <TableContainer component={Paper}>
      <Table className={classes.table} aria-label="simple table">
        <TableHead>
          <TableRow >
            <TableCell>티커</TableCell>
            <TableCell>목표 수량</TableCell> 
            <TableCell>보유 수량</TableCell>   
            <TableCell></TableCell>               
          </TableRow>
        </TableHead>
        <TableBody>
          {personaldata && personaldata.map((row) => (       
            <TableRow key={row.id} hover  >
              <TableCell component="th" scope="row" onClick={() => handleClick(row)}>
                {row.ticker}
              </TableCell>
              <TableCell onClick={() => handleClick(row)}>{row.targetquantity}</TableCell>
              <TableCell onClick={() => handleClick(row)}>{row.currentquantity}</TableCell>
              <TableCell padding="none" >
                <Button color="primary" variant="contained" onClick={() => handleChangePersonalOpen(row)}>수정</Button>
                <Button color="secondary" variant="contained" onClick={() => handlePersonalDelete(row.id)}>삭제</Button>
                </TableCell>             
            </TableRow>                 
          ))}
        </TableBody>
      </Table>
    </TableContainer>

      <Dialog onClose={handleClose} aria-labelledby="stock_modal" open={open}>
        <DialogTitle id="stock_modal" onClose={handleClose}>
          거래 내역
        </DialogTitle>
        <DialogContent dividers>
          <TableContainer component={Paper}>
            <Table className={classes.table} aria-label="stock_table">
              <TableHead>
                <TableRow>
                  <TableCell>거래 날짜</TableCell>
                  <TableCell>티커</TableCell> 
                  <TableCell>가격</TableCell>  
                  <TableCell>거래량</TableCell>                      
                </TableRow>
              </TableHead>
              <TableBody>
              {tradingdata && tradingdata.map((row) => (            
                <TableRow key={row.id} hover onClick={() => handleChange(row)}>
                  <TableCell component="th" scope="row">{row.day}</TableCell>
                  <TableCell>{row.ticker}</TableCell>
                  <TableCell>{row.price}</TableCell>
                  <TableCell>{row.stockquantity}</TableCell>                  
                </TableRow>
              ))}
              </TableBody>
            </Table>
          </TableContainer>        
        </DialogContent>
        <DialogActions>    
          <Button color="primary" onClick={handleChangeTradingOpen}>
            거래 내역 생성
          </Button>  
        </DialogActions>
      </Dialog>

      <Dialog onClose={handleChageClose} open={changeOpen} >
        <DialogTitle id="stock_modal" onClose={handleChageClose}>
           내역 수정
        </DialogTitle>
        <DialogContent dividers>
          <form className={classes.textfield} noValidate autoComplete="off">
              <TextField id="year" label="년도" defaultValue={year} variant="filled" type="number" onChange={(e) => {setData({...data, year: e.target.value})}} />
              <TextField id="month" label="월" defaultValue={month} variant="filled" type="number" onChange={(e) => {setData({...data, month: e.target.value})}} />
              <TextField id="date" label="날짜" defaultValue={data.day} variant="filled" type="number" onChange={(e) => {setData({...data, day: e.target.value})}} />
              <TextField id="ticker" label="티커" defaultValue={data.ticker} variant="filled" onChange={(e) => {setData({...data, ticker: e.target.value})}} />
              <TextField id="price" label="금액" defaultValue={data.price} variant="filled" type="number" onChange={(e) => {setData({...data, price: e.target.value})}} />
              <TextField id="stockweight" label="수량" defaultValue={data.stockquantity} variant="filled" onChange={(e) => {setData({...data, stockquantity: e.target.value})}} />
          </form>
        </DialogContent>
        <DialogActions>
          <Button onClick={() => handleUpdate()} color="primary">
            수정
          </Button>
          <Button onClick={() => handleDelete()} color="secondary">
            삭제
          </Button>
        </DialogActions>
      </Dialog>

      <Dialog onClose={handleChangePersonalClose} open={changePersonalOpen}>
        <DialogTitle id="stock_personal_modal" onClose={handleChangePersonalClose}>
           관심주 수정
        </DialogTitle>
        <DialogContent dividers>
          <form className={classes.textfield} noValidate autoComplete="off">
              <TextField id="ticker" label="티커" defaultValue={stockPersonalData.ticker} variant="filled" onChange={(e) => {setStockPersonalData({...stockPersonalData, ticker: e.target.value})}} />
              <TextField id="targetquantity" label="목표 수량" defaultValue={stockPersonalData.targetquantity} variant="filled" type="number" onChange={(e) => {setStockPersonalData({...stockPersonalData, targetquantity: e.target.value})}} />
              <TextField id="currentquantity" label="보유 수량(가중치)" defaultValue="0" variant="filled" type="number" onChange={(e) => {setStockPersonalData({...stockPersonalData, currentquantity: e.target.value})}} />              
          </form>
        </DialogContent>
        <DialogActions>
          <Button color="primary" onClick={() => handleUpdatePersonal()}>
            수정
          </Button>
        </DialogActions>   
      </Dialog>

      <Dialog onClose={handleChangeTradingClose} open={changeTradingOpen}>
        <DialogTitle id="stock_personal_modal" onClose={handleChangeTradingClose}>
          거래 내역 생성
        </DialogTitle>
        <DialogContent dividers>
          <form className={classes.textfield} noValidate autoComplete="off">
            <TextField id="year" label="년도" defaultValue={year} variant="filled" type="number" onChange={(e) => {setData({...data, year: e.target.value})}} />
            <TextField id="month" label="월" defaultValue={month} variant="filled" type="number" onChange={(e) => {setData({...data, month: e.target.value})}} />
            <TextField id="date" label="날짜" defaultValue={day} variant="filled" type="number" onChange={(e) => {setData({...data, day: e.target.value})}} />
            <TextField id="ticker" label="티커" defaultValue={data.ticker} variant="filled" onChange={(e) => {setData({...data, ticker: e.target.value})}} />
            <TextField id="price" label="금액" defaultValue={0} variant="filled" type="number" onChange={(e) => {setData({...data, price: e.target.value})}} />
            <TextField id="stockweight" label="수량" defaultValue={0} variant="filled" onChange={(e) => {setData({...data, stockquantity: e.target.value})}} />
          </form>
        </DialogContent>
        <DialogActions>
          <Button color="primary" onClick={() => handleCreateTrading()}>
            생성
          </Button>
        </DialogActions>   
      </Dialog>

    </div>
  );
}