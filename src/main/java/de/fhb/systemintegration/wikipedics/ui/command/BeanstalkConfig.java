package de.fhb.systemintegration.wikipedics.ui.command;

/**
 * This is class holds the configuration for the beanstalk deployment.
 *
 * @author mlelansky
 * @version 0.0.1
 */
final class BeanstalkConfig {

    /**
     * This is the name of the Beanstalk Application.
     */
    public static final String BEANSTALK_APP_NAME = "Wikipedics";

    /**
     * This is the description of the Beanstalk Application.
     */
    public static final String BEANSTALK_APP_DESCRIPTION = "This is an Amazon "
            + "AWS Cloud Application which query the Wikipedia-Dump. "
            + "The frontend of the application is developed in Ruby on Rails "
            + " and the backend is developed in Java/Closure.";


    /**
     * The prefix for the https-protocol.
     */
    public static final String HTTPS_PREFIX = "http://";

    /**
     * This is the prefix of the beanstalk url.
     */
    public static final String BEANSTALK_URL_PREFIX = "elasticbeanstalk.";
    /**
     * This is the prefix of the beanstalk url.
     */
    public static final String BEANSTALK_URL_POSTFIX = ".amazonaws.com";

    /**
     * This is the default constructor of the class.
     */
    private BeanstalkConfig() {
        super();
    }
}
