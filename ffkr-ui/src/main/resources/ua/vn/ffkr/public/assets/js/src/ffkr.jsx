import React from 'react';
import ReactDOM from 'react-dom';
import {Button} from 'elemental';

class Home extends React.Component {

    render() {
        return (
            <div>
                <Button type="default-success">Default Success</Button>
            </div>
        );
    }
}

ReactDOM.render(<Home/>, document.getElementById('ffkr-content'));