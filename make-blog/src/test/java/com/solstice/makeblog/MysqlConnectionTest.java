package com.solstice.makeblog;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;

public class MysqlConnectionTest {

    private static final Logger log = LoggerFactory.getLogger(MysqlConnectionTest.class);
    private DataSource ds;

    @Test
    public void 데이터베이스_연결() {
        try (Connection conn = ds.getConnection()) {
            log.info("\n MySQL 연결 : " + conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
