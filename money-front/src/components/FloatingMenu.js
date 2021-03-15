import React from 'react';
import { makeStyles, withStyles } from '@material-ui/core/styles';
import Fab from '@material-ui/core/Fab';
import AddIcon from '@material-ui/icons/Add';
import { Button, Dialog, Menu, MenuItem, TextField } from '@material-ui/core';
import MuiDialogTitle from '@material-ui/core/DialogTitle';
import MuiDialogContent from '@material-ui/core/DialogContent';
import MuiDialogActions from '@material-ui/core/DialogActions';
import IconButton from '@material-ui/core/IconButton';
import CloseIcon from '@material-ui/icons/Close';
import Typography from '@material-ui/core/Typography';
import { useDispatch, useSelector } from 'react-redux';
import { createMoney, getMoney } from '../module/moneyReducer';
import { createTag, getTag } from '../module/tagReducer';
import { createPersonal, getPersonal } from '../module/stockPersonalReducer';

const useStyles = makeStyles((theme) => ({
  root: {
    '& > *': {
      margin: theme.spacing(1),
    },
    position: 'fixed',
    right: 50,
    bottom: 30,
  },
  menu: {
    height: 80,
    fontWeight: "bold"
  },
  textfield: {
    '& .MuiTextField-root': {
      margin: theme.spacing(1),
      width: '25ch',
    },
  },
  table: {
    minWidth: 0,
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


export default function FloatingActionButtons() {
  const classes = useStyles();
  const [anchorEl, setAnchorEl] = React.useState(null);
  const [open, setOpen] = React.useState(false);
  const [bookData, setBookData] = React.useState({});
  const [startDate, setStartDate] = React.useState(new Date());
  const [tagData, setTagData] = React.useState(null);
  const [tagOpen, setTagOpen] = React.useState(false);
  const [stockOpen, setStockOpen] = React.useState(false);
  const [personalData, setPersonalData] = React.useState({});
  const dispatch = useDispatch();

  const { personaldata } = useSelector(state => state.stockPersonalReducer);

  const handleClick = (e) => {
    setAnchorEl(e.currentTarget);
  }

  const handleClose = () => {
    setAnchorEl(null);
  }

  const handleOpenMoney = () => {
    setAnchorEl(null);
    setOpen(true);
  }

  const handleCloseMoney = () => {
    setOpen(false);
  }

  const handleOpenTag = () => {
    setAnchorEl(null);
    setTagOpen(true);
  }

  const handleCloseTag = () => {
    setTagOpen(false);
  }

  const handleOpenStock = () => {
    setStockOpen(true);
  }

  const handleCloseStock = () => {
    setStockOpen(false);
  }

  const handleMoneyCreate = () => {    
    dispatch(createMoney(bookData));
    setTimeout(() => {
      dispatch(getMoney(bookData.year, bookData.month))
    }, 1000);
    setBookData(null);
    setOpen(false);
  }

  const handleTagCreate = () => {
    dispatch(createTag(tagData));
    setTagData(null);
    setTagOpen(false);
    setTimeout(() => {
      dispatch(getTag()); 
    }, 1000); 
  }

  const handlePersonalCreate = () => {
    dispatch(createPersonal(personalData));
    setTimeout(() => {
      dispatch(getPersonal()); 
    }, 1000); 
  }

  return (
    <div>
      <Fab className={classes.root} color="primary" aria-label="add" aria-haspopup="true" onClick={handleClick} >
        <AddIcon />
      </Fab>
      <Menu 
          id="floating-menu"
          anchorEl={anchorEl}
          keepMounted
          open={Boolean(anchorEl)}
          onClose={handleClose}
        >
          <MenuItem onClick={handleOpenMoney} className={classes.menu} >가계부 기입</MenuItem>
          <MenuItem onClick={handleOpenTag} className={classes.menu} >커스텀태그 관리</MenuItem>
          <MenuItem onClick={handleOpenStock} className={classes.menu} >관심주 관리</MenuItem>
        </Menu>

        {//
        }
        <Dialog open={open}>
          <DialogTitle onClose={handleCloseMoney}>
            가계부 생성
          </DialogTitle>
          <DialogContent dividers>        
            <form className={classes.textfield} noValidate autoComplete="off">
              <TextField id="year" label="년도" variant="filled" type="number" onChange={(e) => {setBookData({...bookData, year : e.target.value})}} />
              <TextField id="month" label="월" variant="filled" type="number" onChange={(e) => {setBookData({...bookData, month : e.target.value})}} />
              <TextField id="day" label="날짜" variant="filled" onChange={(e) => {setBookData({...bookData, day : e.target.value})}} />
              <TextField id="context" label="내용" variant="filled" onChange={(e) => {setBookData({...bookData, context : e.target.value})}}/>
              <TextField id="amount" type="number" label="금액" variant="filled" onChange={(e) => {setBookData({...bookData, amount : e.target.value})}}/>
              <TextField id="tag" label="태그" variant="filled" onChange={(e) => {setBookData({...bookData, tag : e.target.value})}} />
            </form>        
          </DialogContent>
          <DialogActions>
            <Button autoFocus color="primary" onClick={() => handleMoneyCreate()} >
                생성
            </Button>
          </DialogActions>
        </Dialog>

        {//
        }
        <Dialog open={tagOpen}>
          <DialogTitle onClose={handleCloseTag}>
            태그 관리
          </DialogTitle>
          <DialogContent dividers>        
            <form className={classes.textfield} noValidate autoComplete="off">
              <TextField id="tagname" label="태그 이름" variant="filled" onChange={(e) => {setTagData(e.target.value)}} />
            </form>        
          </DialogContent>
          <DialogActions>
            <Button autoFocus color="primary" onClick={() => handleTagCreate()} >
                생성
            </Button>
          </DialogActions>
        </Dialog>

        {//
        }
        <Dialog open={stockOpen}>
          <DialogTitle onClose={handleCloseStock}>
            관심주 관리
          </DialogTitle>
          <DialogContent dividers>        
            <form className={classes.textfield} noValidate autoComplete="off">
              <TextField id="tickername" label="티커" variant="filled" onChange={(e) => {setPersonalData({...personalData, ticker:e.target.value})}} />
              <TextField id="targetquantity" label="목표 수량" variant="filled" type="number" onChange={(e) => {setPersonalData({...personalData, targetquantity:e.target.value})}} />
              <TextField id="currentquantity" label="보유 수량" variant="filled" type="number" onChange={(e) => {setPersonalData({...personalData, currentquantity:e.target.value})}} />
            </form> 
          </DialogContent>
          <DialogActions>
            <Button autoFocus color="primary" onClick={() => handlePersonalCreate()} >
              생성
            </Button>
          </DialogActions>
        </Dialog>

    </div>
  );
}