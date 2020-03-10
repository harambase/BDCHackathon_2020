import React from 'react';
import {Link} from 'react-router-dom';
import { Layout, Menu } from 'antd';
import {
    MenuUnfoldOutlined,
    MenuFoldOutlined,
    UserOutlined,
    VideoCameraOutlined,
    UploadOutlined,
} from '@ant-design/icons';
import './Home.css'; // Tell webpack that Button.js uses these styles

import TimeRemaining from './TimeRemaining.jsx';

const { Header, Sider, Content } = Layout;

class Home extends React.Component {
    state = {
        collapsed: false,
        optionSelected: 0
    };

    toggle = () => {
        this.setState({
            collapsed: !this.state.collapsed,
        });
    };

    handleClick = e => {
        this.setState({
            optionSelected: e.key,
        });
    };

    render() {
        console.log(this.state.optionSelected)

        return (
            <Layout>
                <Sider trigger={null} collapsible collapsed={this.state.collapsed}>
                    <div className="logo" />
                    <Menu theme="dark" mode="inline" defaultSelectedKeys={['1']}
                          onClick={this.handleClick}
                    >
                        <Menu.Item key="1">
                            <span>TimeRemaing</span>
                        </Menu.Item>
                        <Menu.Item key="2">
                            <VideoCameraOutlined />
                            <span>nav 2</span>
                        </Menu.Item>
                        <Menu.Item key="3">
                            <UploadOutlined />
                            <span>nav 3</span>
                        </Menu.Item>
                    </Menu>
                </Sider>
                <Layout className="site-layout">
                    <Header className="site-layout-background" style={{ padding: 0 }}>
                        {React.createElement(this.state.collapsed ? MenuUnfoldOutlined : MenuFoldOutlined, {
                            className: 'trigger',
                            onClick: this.toggle,
                        })}
                    </Header>
                    <Content
                        className="site-layout-background"
                        style={{
                            margin: '24px 16px',
                            padding: 24,
                            minHeight: 280,
                        }}
                    >
                        {  this.state.optionSelected == 1 ?      <TimeRemaining /> :   <Link to={"/time"}>Click Me</Link>}






                    </Content>
                </Layout>
            </Layout>
        );
    }
}

export default Home;