package de.fhb.systemintegration.wikipedics.util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * This class provides the methods to handles a properties file
 * for the system setting.
 *
 * @author mlelansky
 * @version 0.0.1
 */
public class ApplicationProperitesHandlerImpl
        implements ApplicationPropertiesHandler {



    /**
     * The properties file used in the app.
     */
    private Properties props;

    /**
     * This is the default constructor of the class.
     */
    public ApplicationProperitesHandlerImpl() {
        this(Config.SETTINGS_PATH);
    }

    /**
     * This is the initialisation constructor of the class.
     * @param path the file path to the properties file.
     */
    public ApplicationProperitesHandlerImpl(final String path) {
        this.props = new Properties();
        if (fileExists(path)) {
            loadFile(path);
        } else {
            System.err.println("The file could not be loaded");
        }
    }

    @Override
    public final String getObject(final String key) {
        String result = "";
        if (this.props.containsKey(key)) {
            result = this.props.getProperty(key);
        }
        return result;
    }

    @Override
    public final int size() {
        return this.props.size();
    }

    /**
     * This method checks if a file exists on the computer.
     * @param path the path to check
     * @return true if the file exists otherwise false
     */
    private boolean fileExists(final String path) {
        boolean result = false;
        File file = new File(path);
        if (file.exists() && file.isFile() && file.canRead()) {
            result = true;
        }
        return result;
    }

    /**
     * This method reads the properties file.
     * @param path the path to the file
     */
    private void loadFile(final String path) {
        try (FileReader reader = new FileReader(path)) {
            this.props.load(reader);
        } catch (IOException e) {
            System.err.println("The FileReader could not initialises.");
        }
    }
}
