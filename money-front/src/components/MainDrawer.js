import React, { useEffect } from 'react';
import clsx from 'clsx';
import { makeStyles, useTheme } from '@material-ui/core/styles';
import Drawer from '@material-ui/core/Drawer';
import CssBaseline from '@material-ui/core/CssBaseline';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import List from '@material-ui/core/List';
import Typography from '@material-ui/core/Typography';
import Divider from '@material-ui/core/Divider';
import IconButton from '@material-ui/core/IconButton';
import MenuIcon from '@material-ui/icons/Menu';
import ChevronLeftIcon from '@material-ui/icons/ChevronLeft';
import ChevronRightIcon from '@material-ui/icons/ChevronRight';
import ListItem from '@material-ui/core/ListItem';
import ListItemIcon from '@material-ui/core/ListItemIcon';
import ListItemText from '@material-ui/core/ListItemText';
import InboxIcon from '@material-ui/icons/MoveToInbox';
import MailIcon from '@material-ui/icons/Mail';
import HomeIcon from '@material-ui/icons/Home';
import ExpandLess from '@material-ui/icons/ExpandLess';
import ExpandMore from '@material-ui/icons/ExpandMore';
import CalendarIcon from '@material-ui/icons/CalendarToday';
import { Button, Collapse, Grid, ListSubheader, Table } from '@material-ui/core';
import MoneyTable from './MoneyBookTable/MoneyTable';
import StockTable from './MoneyBookTable/StockTable';
import TotalTable from './MoneyBookTable/TotalTable';
import { useDispatch, useSelector } from 'react-redux';
import { getDateAcync, getDateFunc, timeRev } from '../module/dateReducer';
import { signOut } from '../module/signReducer';
import { Redirect } from 'react-router-dom';


const drawerWidth = 240;

const useStyles = makeStyles((theme) => ({
    root: {
      display: 'flex',
    },
    appBar: {
      transition: theme.transitions.create(['margin', 'width'], {
        easing: theme.transitions.easing.sharp,
        duration: theme.transitions.duration.leavingScreen,
      }),
    },
    appBarShift: {
      width: `calc(100% - ${drawerWidth}px)`,
      marginLeft: drawerWidth,
      transition: theme.transitions.create(['margin', 'width'], {
        easing: theme.transitions.easing.easeOut,
        duration: theme.transitions.duration.enteringScreen,
      }),
    },
    menuButton: {
      marginRight: theme.spacing(2),
    },
    hide: {
      display: 'none',
    },
    drawer: {
      width: drawerWidth,
      flexShrink: 0,
    },
    drawerPaper: {
      width: drawerWidth,
    },
    drawerHeader: {
      display: 'flex',
      alignItems: 'center',
      padding: theme.spacing(0, 1),
      // necessary for content to be below app bar
      ...theme.mixins.toolbar,
      justifyContent: 'flex-end',
    },
    content: {
      flexGrow: 1,
      padding: theme.spacing(3),
      marginTop: theme.spacing(4),
      transition: theme.transitions.create('margin', {
        easing: theme.transitions.easing.sharp,
        duration: theme.transitions.duration.leavingScreen,
      }),
      marginLeft: -drawerWidth,
    },
    contentShift: {
      transition: theme.transitions.create('margin', {
        easing: theme.transitions.easing.easeOut,
        duration: theme.transitions.duration.enteringScreen,
      }),
      marginLeft: 0,
    },
    nested: {
      paddingLeft: theme.spacing(4),
    },
    tableRoot: {
      flexGrow: 1,
    },
    logout: {
      position: 'fixed',
      right: 50,
    }
  }));

  export default function MainDrawer() {
    const classes = useStyles();
    const theme = useTheme();
    const [open, setOpen] = React.useState(true);
    const [nestedOpen, setNestedOpen] = React.useState(false);
    
    const dispatch = useDispatch();

    const { date } = useSelector(state => state.dateReducer);
    const { username } = useSelector(state => state.signReducer);
    //const { moneydate } = useSelector(state => state.MoneyReducer);

    const handleDrawerOpen = () => {
      setOpen(true);
    };
  
    const handleDrawerClose = () => {
      setOpen(false);
    };
  
    const handleNested = () => {
      setNestedOpen(!nestedOpen);
    }

    const handleDate = (year,month) => {
      dispatch(timeRev(year,month));
    }

    const handleLogOut = () => {
      dispatch(signOut());
      if(sessionStorage.getItem('username') === null) {
        //window.history.replaceState({data: null}, 'push to login', '/');
        //window.location.reload();
        window.location.href = '/'
      }
    }

    useEffect(() => {      
      try {
        dispatch(getDateAcync());         
      } catch (error) {
        console.log(error);
      }              
    },[dispatch])

    useEffect(() => {      
      if(sessionStorage.getItem('username') === null) {
        //window.history.replaceState({data: null}, 'push to login', '/');
        //window.location.reload();
        //window.location.href = '/'
      }
    },[username])    

    if(!date) return(
      <div>
        loading...
      </div>
    )
      return (
        <div className={classes.root}>
          <CssBaseline />
          <AppBar
            position="fixed"
            className={clsx(classes.appBar, {
              [classes.appBarShift]: open,
            })}
          >
            <Toolbar>
              <IconButton
                color="inherit"
                aria-label="open drawer"
                onClick={handleDrawerOpen}
                edge="start"
                className={clsx(classes.menuButton, open && classes.hide)}
              >
                <MenuIcon />
              </IconButton>
              <Typography variant="h6" noWrap>
                Money Book
              </Typography>
              <Button className={clsx(classes.logout)} onClick={handleLogOut}>
                <Typography variant="h6" noWrap>
                  LOGOUT
                </Typography>
              </Button>
            </Toolbar>
          </AppBar>
          <Drawer
            className={classes.drawer}
            variant="persistent"
            anchor="left"
            open={open}
            classes={{
              paper: classes.drawerPaper,
            }}
          >
            <div className={classes.drawerHeader}>
              <h5 className={classes.drawerPaper}>
              </h5>
              <IconButton onClick={handleDrawerClose}>
                {theme.direction === 'ltr' ? <ChevronLeftIcon /> : <ChevronRightIcon />}
              </IconButton>
            </div>
            <Divider />
            <List>    
              {
                date.date.map((split) => (
                  <List key={split.year}>
                    <ListItem button onClick={handleNested} >
                      <ListItemIcon> <CalendarIcon /> </ListItemIcon>
                      <ListItemText primary={split.year + '년'} />
                      {nestedOpen ? <ExpandLess /> : <ExpandMore />}
                    </ListItem>
                    <Collapse in={nestedOpen} timeout="auto" unmountOnExit>
                    {
                      split.monthArr.map((list) => (
                        <ListItem button className={classes.nested} onClick={() => handleDate(split.year,list)} key={split.year + list} >
                          <ListItemText primary={list + '월'} />
                        </ListItem>
                      ))
                    }
                    </Collapse>
                  </List>

                ))
              }                  
            </List>
            <Divider />
            <List component="div" subheader={
              <ListSubheader component="div" id="subheader">
                카테고리
              </ListSubheader>
            } >
              <TotalTable />
            </List>
            <Divider />
          </Drawer>
          <main
            className={clsx(classes.content, {
              [classes.contentShift]: open,
            })}
          >
            <div />
            {/* 여기에 다음 컴포넌트 */}
            <Grid container className={classes.tableRoot} spacing={10} direction="row">
              <Grid item>        
                <MoneyTable /> 
              </Grid >
              <Grid item> 
                <Grid container spacing={2} direction="column">                
                  <Grid item>        
                    <StockTable />                  
                  </Grid >
                </Grid>   
              </Grid >            
            </Grid>
          </main>
        </div>
      );  
  }