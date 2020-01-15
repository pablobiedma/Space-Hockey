package com.mygdx.airhockey.database;

import java.sql.Connection;

public interface ConnectionFactory {
    Connection getConnection();
}
