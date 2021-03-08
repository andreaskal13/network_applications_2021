import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Card from '@material-ui/core/Card';
import CardContent from '@material-ui/core/CardContent';
import Typography from '@material-ui/core/Typography';

const useStyles = makeStyles({
  root: {
    minWidth: 275,
    height:300,
  },
  title: {
    fontSize: 14,
    marginTop:50,
  },
  pos: {
    marginBottom: 10,
  },
});


export default function SimpleCard(props){
  const classes = useStyles();
  
  return (
    <Card elevation={4} className={classes.root}>
      <CardContent>
        <Typography className={classes.title} color="textSecondary" gutterBottom>
          Οδός
        </Typography>
        <Typography className={classes.pos} variant="h5" component="h2">
          {props.streetName}
        </Typography>
        <Typography color="textSecondary">
          Μέσος αριθμός οχημάτων:
        </Typography>
        <Typography variant="body2" component="p">
          {props.speed}
        </Typography>
      </CardContent>
    </Card>
  );
}