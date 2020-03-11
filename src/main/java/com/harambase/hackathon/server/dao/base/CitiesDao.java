package com.harambase.hackathon.server.dao.base;

import com.harambase.hackathon.server.dao.connection.DataServiceConnection;
import com.harambase.hackathon.server.dao.connection.ResultSetHelper;
import com.harambase.hackathon.server.pojo.base.Cities;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class CitiesDao {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public List<Cities> getPersonBySearch(String search, String type, String status, String role, String maxLength) throws Exception {
        ResultSet rs = null;
        Connection connection = null;
        Statement stmt = null;
        List<Cities> personList = new ArrayList<>();
        try {
            connection = DataServiceConnection.openDBConnection();
            if (connection == null)
                return personList;

            stmt = connection.createStatement();

            String queryString = "select * from Cities where 1=1 ";
            if (StringUtils.isNotEmpty(type))
                queryString += "AND type LIKE '%" + type + "%' ";
            if (StringUtils.isNotEmpty(role))
                queryString += "AND role_id LIKE '%" + role + "%' ";
            if (StringUtils.isNotEmpty(status))
                queryString += "AND status = '" + status + "' ";

            if (StringUtils.isNotEmpty(search)) {
                queryString += "" +
                        "AND (user_id    LIKE '%" + search + "%' OR" +
                        "     username   LIKE '%" + search + "%' OR" +
                        "     first_name LIKE '%" + search + "%' OR" +
                        "     last_name  LIKE '%" + search + "%')";
            }

            if (StringUtils.isNotEmpty(maxLength))
                queryString += "limit 0," + Integer.parseInt(maxLength);
            else
                queryString += "limit 0,6";

            logger.info(queryString);

            rs = stmt.executeQuery(queryString);
            personList = ResultSetHelper.getObjectFor(rs, Cities.class);
            return personList;
        } finally {
            if (stmt != null)
                stmt.close();
            if (rs != null)
                rs.close();
            if (connection != null)
                connection.close();
        }
    }

    public Long getCountByMapPageSearchOrdered(String search, String type, String status, String role) throws Exception {
        ResultSet rs = null;
        Connection connection = null;
        Long count = 0L;
        Statement stmt = null;
        try {
            connection = DataServiceConnection.openDBConnection();
            if (connection == null)
                return count;

            stmt = connection.createStatement();

            String queryString = "SELECT COUNT(*) AS count FROM Cities WHERE 1=1 ";
            if (StringUtils.isNotEmpty(type))
                queryString += "AND type LIKE '%" + type + "%' ";
            if (StringUtils.isNotEmpty(status))
                queryString += "AND status = '" + status + "' ";
            if (StringUtils.isNotEmpty(role))
                queryString += "AND role_id LIKE '%" + role + "%' ";
            if (StringUtils.isNotEmpty(search)) {
                queryString += "AND(" +
                        "user_id     LIKE  '%" + search + "%' OR " +
                        "username    LIKE  '%" + search + "%' OR " +
                        "first_name  LIKE  '%" + search + "%' OR " +
                        "last_name   LIKE  '%" + search + "%' OR " +
                        "update_time LIKE  '%" + search + "%')";
            }
            logger.info(queryString);
            rs = stmt.executeQuery(queryString);

            if (rs.next()) {
                count = rs.getLong("count");
            }
            return count;

        } finally {
            if (stmt != null)
                stmt.close();
            if (rs != null)
                rs.close();
            if (connection != null)
                connection.close();
        }
    }

    public List<Cities> getByMapPageSearchOrdered(int currentIndex, int pageSize, String search,
                                                  String order, String orderColumn, String type, String status, String role) throws Exception {
        ResultSet rs = null;
        Connection connection = null;
        Statement stmt = null;
        List<Cities> personList = new ArrayList<>();
        try {
            connection = DataServiceConnection.openDBConnection();
            if (connection == null)
                return personList;

            stmt = connection.createStatement();

            String queryString = "SELECT * FROM Cities WHERE 1=1 ";
            if (StringUtils.isNotEmpty(type))
                queryString += "AND type LIKE '%" + type + "%' ";
            if (StringUtils.isNotEmpty(status))
                queryString += "AND status = '" + status + "' ";
            if (StringUtils.isNotEmpty(role))
                queryString += "AND role_id LIKE '%" + role + "%' ";
            if (StringUtils.isNotEmpty(search)) {
                queryString += "AND(" +
                        "user_id     LIKE  '%" + search + "%' OR " +
                        "username    LIKE  '%" + search + "%' OR " +
                        "first_name  LIKE  '%" + search + "%' OR " +
                        "last_name   LIKE  '%" + search + "%' OR " +
                        "update_time LIKE  '%" + search + "%')";
            }

            queryString += "order by " + orderColumn + " " + order + " "
                    + "limit " + currentIndex + "," + pageSize;
            logger.info(queryString);

            rs = stmt.executeQuery(queryString);
            personList = ResultSetHelper.getObjectFor(rs, Cities.class);
            return personList;

        } finally {
            if (stmt != null)
                stmt.close();
            if (rs != null)
                rs.close();
            if (connection != null)
                connection.close();
        }
    }

}
