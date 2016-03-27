import React from 'react';
import ReactDOM from 'react-dom';
import Header from './components/common/header.jsx';
import Navbar from './components/common/navbar.jsx';
import Footer from './components/common/footer.jsx';

class Home extends React.Component {

    render() {
        return (
            <div>
                <Header/>
                <Navbar/>
                <Footer/>
            </div>
        );
    }
}

ReactDOM.render(<Home/>, document.getElementById('ffkr-content'));