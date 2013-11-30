package de.fhb.systemintegration.wikipedics.util;

import org.h2.tools.Server;

import java.sql.SQLException;

/**
 * This class manages the H2-Database-Server.
 *
 * @author mlelansky
 * @version 0.0.1
 */
public final class H2Manager {

    /**
     * This is the actual h2 database server instance.
     */
    private Server h2Server;

    /**
     * This is the default constructor of the class.
     * @throws SQLException if the h2 tcp-server could not created
     */
    public H2Manager() throws SQLException {
        this.h2Server = Server.createTcpServer(
                new String[]{Config.TCP_PORT_OPTION, Config.TCP_PORT});
    }

    /**
     * This method starts the h2 tcp-server.
     * @throws SQLException     if the h2 tcp-server could not started
     */
    public synchronized void startServer() throws SQLException {
        if (!this.h2Server.isRunning(false)) {
             this.h2Server.start();
        }
    }

    /**
     * This method stops the h2 tcp-server.
     */
    public synchronized void stopServer() {
        if (this.h2Server.isRunning(false)) {
            this.h2Server.stop();
        }
    }

}
