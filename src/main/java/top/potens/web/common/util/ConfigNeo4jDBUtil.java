package top.potens.web.common.util;

import java.sql.*;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className ConfigNeo4jDBUtil
 * @projectName web-api
 * @date 2019/7/3 15:57
 */
public class ConfigNeo4jDBUtil {
    public Connection getNeo4jConnection() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:neo4j:bolt://potens.top:7687/", "neo4j", "Wendi_1209");
        return conn;
    }
    public static void main(String[] args) throws SQLException {
        ConfigNeo4jDBUtil configNeo4jDBUtil = new ConfigNeo4jDBUtil();
        Statement statement = configNeo4jDBUtil.getNeo4jConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("MATCH (n) RETURN n");
        while (resultSet.next()){
            System.out.println(resultSet.getString(1));
        }
    }
}
