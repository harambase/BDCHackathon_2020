package com.harambase.hackathon.server.dao;

import com.harambase.hackathon.common.ResultMap;
import com.harambase.hackathon.common.support.util.ReturnMsgUtil;
import com.harambase.hackathon.server.dao.connection.DataServiceConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.OutputStream;

@Component
public class DatabaseManager {

    private final static Logger logger = LoggerFactory.getLogger(DataServiceConnection.class);

    public ResultMap restart() {
        return null;
    }

    public ResultMap reset(String username, String password) {

        try {
            Process process = Runtime.getRuntime().exec("mysql db_name < /home/liova/download/tpch/queries/Q1.sql");
            OutputStream outputStream = process.getOutputStream();

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnMsgUtil.systemError();
        }
        return ReturnMsgUtil.success(null);
    }

    public ResultMap backup(String username, String password) {
        return null;
    }


}
