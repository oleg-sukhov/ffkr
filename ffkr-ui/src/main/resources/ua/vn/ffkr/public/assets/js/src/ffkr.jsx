import { Articles } from './components/articles/articles.jsx'

class Home extends React.Component {

    render() {
        return '<div><h1>This is home component</h1></div>'
    }
}

ReactDOM.render(<Home/>, document.getElementById('ffkr-content'));