package util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactory {
    private final HikariDataSource dataSource;

    public ConnectionFactory() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:xe");
        config.setUsername("root");
        config.setPassword("1234");
        config.setMaximumPoolSize(10); // 풀의 최대 크기
        this.dataSource = new HikariDataSource(config);
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public DataSource getDataSource() {
        return dataSource;
    }
}
