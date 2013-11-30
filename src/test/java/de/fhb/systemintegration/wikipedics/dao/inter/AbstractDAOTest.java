package de.fhb.systemintegration.wikipedics.dao.inter;

import de.fhb.systemintegration.wikipedics.dao.builder.DAOBuilder;

import java.sql.DriverManager;
import java.sql.Connection;

import org.h2.Driver;
import org.junit.AfterClass;
import org.junit.BeforeClass;

/**
 * This class is an abstract test case for all the database test.
 *
 * @author mlelansky
 * @version 0.0.1
 */
public abstract class AbstractDAOTest {

    /**
     * This is the helper object which builds the interface instance.
     */
    private static DAOBuilder builder;

    /**
     * This is the actual db connection.
     */
    private static Connection dbConnection;

    /**
     * This method setups the database.
     * @throws Exception if any exception occurred
     */
    @BeforeClass
    public static void init() throws Exception {
        builder = new DAOBuilder();
        DriverManager.registerDriver(Driver.load());
        AbstractDAOTest.dbConnection = DriverManager.getConnection(
                "jdbc:h2:mem:wikipedics", "wikipedics", "wikipedics");
    }

    /**
     * This method returns the actual builder instance.
     * @return the dao interface builder
     */
    protected final DAOBuilder getBuilder() {
        return this.builder;
    }

    /**
     * This method close all the active db connections.
     * @throws Exception if the connection could not close successfully
     */
    @AfterClass
    public static void clean() throws Exception {
        AbstractDAOTest.dbConnection.close();
    }
}
