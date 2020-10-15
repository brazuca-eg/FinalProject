package ua.kharkov.repairagency.db;

import org.junit.Test;
import ua.kharkov.repairagency.db.entity.Status;
import ua.kharkov.repairagency.db.entity.StatusEntity;

import java.sql.Connection;
import java.util.List;

import static org.junit.Assert.*;

public class DAOTest {
    private static DAO dao;
    private static final ConnectionPool pool = ConnectionPool.getInstance();

    @Test
    public void getStatuses() {


    }
}