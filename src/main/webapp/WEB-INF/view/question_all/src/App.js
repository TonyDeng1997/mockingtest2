import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import axios from 'axios';

class App extends Component {

  constructor(props) {
    super(props);
    this.state = {
      error: null,
      isLoaded: false,
      items: []
    };
  }

  componentDidMount() {

    /*
    fetch("http://localhost:9000/problems/all")
      .then(res => res.json())
      .then(
        (result) => {
          this.setState({
            isLoaded: true,
            items: result.items
          });
        },
        // Note: it's important to handle errors here
        // instead of a catch() block so that we don't swallow
        // exceptions from actual bugs in components.
        (error) => {
          this.setState({
            isLoaded: true,
            error
          });
        }
      )
      */

     axios.get('http://localhost:9000/problems/all')
     .then(function (response) {
       // handle success
       console.log(response);
     }).then(res => res.json())
     .then(
       (result) => {
         this.setState({
           isLoaded: true,
           items: result.items
         });
       })
     .catch(function (error) {
       // handle error
       console.log(error);
     })
     .then(function () {
       // always executed
     });
  }

  render() {
    const { error, isLoaded, items } = this.state;
    if (error) {
      return <div>Error: {error.message}</div>;
    } else if (!isLoaded) {
      return <div>Loading...</div>;
    } else {
      return (
        <ul>
          {items.map(item => (
            <li key={item.id}>
              {item.title} {item.acceptance_rate}
            </li>
          ))}
        </ul>
      );
    }
  }
}

export default App;
