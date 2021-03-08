import logo from './logo.svg';
import './App.css';
import Container from '@material-ui/core/Container';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';
import { makeStyles, ThemeProvider, createMuiTheme, withTheme } from '@material-ui/core/styles';
import Paper from '@material-ui/core/Paper';
import Grid from '@material-ui/core/Grid';
import { orange, pink, yellow } from '@material-ui/core/colors';
import "@fontsource/roboto";
import axios from 'axios';
import Test from './components/Test';
import SimpleCard from './components/Card';
import ComboBox from "./components/ComboBox";
import MyGridContainer from './components/MyGridContainer';

const useStyles = makeStyles((theme) => ({
  root: {
    flexGrow: 1,
    border:0,
    marginBottom:15,
    borderRadius:15,
    color:'white',
    padding: '5px 30px'
  },
  menuButton: {
    marginRight: theme.spacing(2),
  },
  title: {
    flexGrow: 1,
  },
}));

const theme = createMuiTheme({
  palette:{
    primary:{
      main: pink[700],
    }
  }
})

function App() {
  const classes = useStyles();
  return (

  <ThemeProvider theme={theme}>
    <div className="App">
    <header>
      <AppBar position="static">
        <Toolbar>
          <Typography variant="h6" className={classes.title}>
            TrafficApp
          </Typography>
        </Toolbar>
      </AppBar>
      </header>
      <div className='main'>
      <Container>
          <Grid container style={{paddingTop:50}} spacing={2}>
            <Grid item md ={12} sm={12} xs={12}>
              <Paper elevation={3} style={{height:75, width:'100%'}}>
                <h4>Εργασία στο Μάθημα "Διαδίκτυο & Εφαρμογές" :</h4>
                <h4>Ανδρέας Καλογεράς ΑΜ:0311822</h4>
              </Paper>
            </Grid>
            <Grid item md ={12} sm={12} xs={12}>
              <Paper elevation={3} style={{height:150, width:'100%'}}>
                <h4>Η εφαρμογή επιτρέπει στο χρήστη να επιλέξει ένα κεντρικό δρόμο της Αθήνας και να υπολογίσει τον </h4>
                <h4> μέσο όρο αυτοκινήτων που διέλευσαν όλα τα σημεία μετρήσεων κατα το διάστημα</h4>
                <h4>25/2/2021 έως 4/3/2021</h4>
              </Paper>
            </Grid>
          </Grid>
          <MyGridContainer></MyGridContainer>
      </Container>
      </div> 
      <Test></Test>
      
    </div>
  </ThemeProvider>
  
  );
}

export default App;
