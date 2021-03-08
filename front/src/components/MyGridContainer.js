import React, { Component } from 'react';
import Grid from '@material-ui/core/Grid';
import Paper from '@material-ui/core/Paper';
import Container from '@material-ui/core/Container';
import SimpleCard from './Card';
import TextField from '@material-ui/core/TextField';
import Autocomplete from '@material-ui/lab/Autocomplete';
import axios from 'axios';


//test dao
const roads = [
    { id: 1, streetName: 'ΠΑΤΗΣΙΩΝ' },
    { id: 2, streetName: 'ΑΛΕΞΑΝΔΡΑΣ' },
    { id: 3, streetName: 'ΘΗΒΩΝ' },
    { id: 4, streetName: 'ΒΟΥΛΙΑΓΜΕΝΗΣ' },
  ];

class MyGridContainer extends Component{
    state=
    {
        streetName:"Επιλέξτε δρόμο",
        speed:"----",
        roads :[
            { id: 1, streetName: 'ΠΑΤΗΣΙΩΝ' },
            { id: 2, streetName: 'ΑΛΕΞΑΝΔΡΑΣ' },
            { id: 3, streetName: 'ΘΗΒΩΝ' },
            { id: 4, streetName: 'ΒΟΥΛΙΑΓΜΕΝΗΣ' },
          ],
    }


    onReturnedRoadsHandler =(val)=>{
         console.log("inside returnnnnnn");
         //console.log([...val]);
         console.log("inside retur222");
         console.log(this.state.roads);
        this.setState(
            {
                //load /roads from api
                //take json from val should be placed here
                //TODO
                // roads: [
                //     { id: 1, streetName: 'ΠΑΤΗΣΙΩΝ' },
                //     { id: 2, streetName: 'ΑΛΕΞΑΝΔΡΑΣ' },
                //     { id: 3, streetName: 'ΘΗΒΩΝ' },
                //     { id: 4, streetName: 'ΒΟΥΛΙΑΓΜΕΝΗΣ' },
                //     { id: 5, streetName: 'ΦΩΚΑΑ' },
                //   ]
                roads: val
            }
        )
    }

    onChangeHandler =(val)=>{
        this.setState(
            {
            streetName:val,
            speed:"..."
            }
        )
    }

    onApiReturnHandler =(val)=>{
        this.setState(
        {
            speed:val
        }
        )
    }

    //On component mount we request 
    //the names of all the roads
    //for the dropdown menu 
    componentDidMount(){
        axios.get('/roads')
        .then(response=>{
            console.log(response.data);
            this.onReturnedRoadsHandler(response.data);
        });
    }

    render(){
        return(
            <Grid container style={{paddingTop:50}} spacing={4} justify = "center">
            <Grid item md={6} sm={12} xs={12} >
              <Paper elevation={4} style={{height:300, width:'100%'}}>
                <Container justify = "center" style={{paddingTop:100}}>
                    <Autocomplete
                        id="combo-box-demo"
                        options={this.state.roads}
                        onChange={(event, newValue) => {
                            if(newValue==null){
                                newValue={
                                    streetName:'επιλέξτε δρόμο',
                                    id:100,
                                }
                            }
                            console.log(newValue.streetName);

                            //the id of the selected road
                            let id = newValue.id;
                            //get the average number of cars for this road
                            axios.get(`/road/${id}`)
                            .then(response=>{
                                console.log(response.data);
                                this.onApiReturnHandler(response.data);
                            });
                            this.onChangeHandler(newValue.streetName);
                        }}
                        getOptionLabel={(option) => option.streetName}
                        style={{ width: 300 }}
                        renderInput={(params) => <TextField {...params} label="Δρόμος" variant="outlined" />}
                    />
                </Container>
              </Paper>
            </Grid>
            <Grid item md ={6} sm={12} xs={12} >
              <SimpleCard streetName={this.state.streetName} speed={this.state.speed}></SimpleCard>
            </Grid>
          </Grid>
        );
    }
}

export default MyGridContainer;