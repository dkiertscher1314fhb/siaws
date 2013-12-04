package de.fhb.systemintegration.wikipedics.util;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * This class is a configuration class an holds the default values of the hole
 * application.
 *
 * @author mlelansky
 * @version 0.0.1
 */
public final class Config {

    /**
     * This is the default constructor of the class.
     */
    private Config() {
        super();
    }

    /**
     * This field defines the max retry count to fetch the output messages.
     */
    public static final int MAX_RETRIES = 20;
    /**
     *
     * This field defines the sleep time of the threads.
     */
    public static final long MAX_SLEEP_TIME = 100L;
    /**
     * This is the default charset used for conversation.
     */
    public static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    /**
     * This is the tcp-port-option for the h2-tcp-server.
     */
    public static final String TCP_PORT_OPTION = "-tcpPort";

    /**
     * This is the used tcp-port.
     */
    public static final String TCP_PORT = "8888";

    /**
     * This is the default settings path.
     */
    public static final String SETTINGS_PATH  = "settings.properties";

    /**
     * This is the minimum size of properties file.
     */
    public static final int MIN_PROPERITES_SIZE = 4;

    /**
     * This is the id of the actual user.
     */
    private static Long id = Long.valueOf(-1L);

    /**
     * This method sets the id of the user.
     * @param _id the user id
     */
    public static void setId(final Long _id) {
        id = _id;
    }

    /**
     * This method returns the actual user id.
     * @return the actual id
     */
    public static Long getId() {
        return id;
    }

}
