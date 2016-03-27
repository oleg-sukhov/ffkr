import React from 'react';

class BriefArticle extends React.Component {

    render() {
        return (
            <div className="panel news-item-panel">
                <div className="news-item">
                    <div className="news-item-avatar">
                        <a className="thumbnail">
                            <img src="http://www.eglin.af.mil/shared/AFImages/ICON%20Image%2072.jpg"/>
                        </a>
                    </div>
                    <div className="news-item-body">
                        <div className="panel panel-default news-item-content">
                        </div>
                        <div className="panel panel-default news-item-tags">
                            <span className="label label-default tag">New</span>
                            <span className="label label-default tag">Success</span>
                            <span className="label label-default tag">Dinamo Kiev</span>
                            <span className="label label-default tag">Barcelona</span>
                            <span className="label label-default tag">Championship</span>
                            <span className="label label-default tag">Carlo Anchelotti</span>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default BriefArticle;
