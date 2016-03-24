import React from 'react';
import ReactDOM from 'react-dom';
import Navbar from './components/common/navbar.jsx';

class Home extends React.Component {

    render() {
        return (
            <div>
                <Navbar/>
            </div>
        );
    }
}

ReactDOM.render(<Home/>, document.getElementById('ffkr-content'));