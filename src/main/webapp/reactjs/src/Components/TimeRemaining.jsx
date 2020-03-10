import React from 'react';
import { Statistic, Row, Col } from 'antd';
import 'antd/dist/antd.css'; // This one does the same job like putting the sytleSheet in index.html file

function timeRemaining(){

    const { Countdown } = Statistic;
    const deadline = Date.now() - new Date('March 14, 2020 07:30:00'); // Moment is also OK
    let birthday = new Date('March 14, 2020 07:30:00');

    console.log(birthday,deadline)
    console.log('data now', Date.now())
        return (
            <div>
                <iframe
                    src="http://free.timeanddate.com/countdown/i76wl6si/n421/cf12/cm0/cu4/ct0/cs0/ca0/cr0/ss0/cac000/cpc000/pcfff/tcfff/fs100/szw320/szh135/tatTime%20left%20to%20Event%20in/tac000/tptTime%20since%20Event%20started%20in/tpc000/mac000/mpc000/iso2020-03-11T00:00:00"
                    allowTransparency="true" frameBorder="0" width="180" height="71"></iframe>

                <Row gutter={16} style={{marginLeft:"30px"}}>
                    <Col span={12}>
                        <Countdown title="Countdown" value={deadline} />
                    </Col>
                    <Col span={12}>
                        <Countdown title="Million Seconds" value={deadline} format="HH:mm:ss:SSS" />
                    </Col>
                    <Col span={24} style={{ marginTop: 32 }}>
                        <Countdown title="Day Level" value={deadline} format="D 天 H 时 m 分 s 秒" />
                    </Col>
                </Row>
            </div>
        );

}

export default timeRemaining;
