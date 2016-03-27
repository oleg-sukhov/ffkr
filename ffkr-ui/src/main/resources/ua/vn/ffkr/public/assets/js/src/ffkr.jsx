import React from 'react';
import ReactDOM from 'react-dom';
import Header from './components/common/header.jsx';
import Navbar from './components/common/navbar.jsx';
import Footer from './components/common/footer.jsx';
import Articles from './components/articles/articles.jsx';

class Home extends React.Component {

    render() {
        return (
            <div>
                <Header/>
                <Navbar/>
                <Articles/>
                <Footer/>
            </div>
        );
    }
}

ReactDOM.render(<Home/>, document.getElementById('ffkr-content'));