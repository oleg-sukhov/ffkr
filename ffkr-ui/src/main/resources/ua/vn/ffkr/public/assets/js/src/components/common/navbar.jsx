import React from 'react';

class Navbar extends React.Component {

    render() {
        return (
            <nav class="navbar ffkr-navbar">
                <div class="container-fluid">
                    <div class="navbar-header">
                    </div>
                    <div class="collapse navbar-collapse">
                        <ul class="nav navbar-nav">
                            <li><a href="#">Articles</a></li>
                            <li className="dropdown">
                                <a className="dropdown-toggle" data-toggle="dropdown">Dropdown
                                    <b className="caret"></b>
                                </a>
                                <ul className="dropdown-menu">
                                    <li><a href="#">Action</a></li>
                                    <li><a href="#">Another action</a></li>
                                    <li><a href="#">Something else here</a></li>
                                    <li><a href="#">Separated link</a></li>
                                    <li><a href="#">One more separated link</a></li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
        );
    }
}

export default Navbar;