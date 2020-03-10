import React, { Component } from 'react';
import { Statistic, Row, Col } from 'antd';
import 'antd/dist/antd.css'; // This one does the same job like putting the sytleSheet in index.html file

function Example(){

    const { Countdown } = Statistic;
    const deadline = Date.now() + 342345*1000; // Moment is also OK

    console.log('data now', Date.now())
        return (
            <div>
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

export default Example;
