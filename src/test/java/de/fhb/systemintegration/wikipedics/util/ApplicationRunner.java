package de.fhb.systemintegration.wikipedics.util;

import de.fhb.systemintegration.wikipedics.AWSApplication;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

/**
 * This class is a special test runner, that runs the real application.
 * This class controls the standard input and output streams.
 * This implementation based on the implementation
 * from the book "Growning Object-Oriented Software" and of the blog from
 * Sven Grand under http://svengrand.blogspot.de/2011/12/
 * testing-java-console-applications.html .
 *
 * @author mlelansky
 * @version 0.0.1
 */
public final class ApplicationRunner implements AutoCloseable {

    /**
     * This stream connect the outputStream with System.in .
     */
    private PipedInputStream inputStream;
    /**
     * This is stream stands for System.in .
     */
    private PipedOutputStream outputStream;
    /**
     * This printStream connect System.out with the byteArrayOutputStream.
     */
    private PrintStream printStream;
    /**
     * This stream stands for System.out .
     */
    private ByteArrayOutputStream byteArrayOutputStream;
    /**
     * This is the backup of System.in .
     */
    private InputStream systeminBackup;
    /**
     * This is the backup of System.out .
     */
    private PrintStream systemoutBackup;


    /**
     * This is the default constructor of the this test runner class.
     * This class initializes some input/output to manage the user inputs.
     *
     */
    public ApplicationRunner() {
        try {
            this.outputStream = new PipedOutputStream();
            this.inputStream = new PipedInputStream(this.outputStream);
            this.systeminBackup = System.in;
            System.setIn(this.inputStream);

            this.byteArrayOutputStream = new ByteArrayOutputStream();
            this.printStream = new PrintStream(this.byteArrayOutputStream,
                    true, Config.DEFAULT_CHARSET.name());
            this.systemoutBackup = System.out;
            System.setOut(this.printStream);
        } catch (IOException e) {
            System.err.println(e.getLocalizedMessage());
            close();
        }
    }

    /**
     * This method starts the real application.
     */
    public void startApplication() {
        Thread applicationThread = new Thread() {
            @Override
            public void run() {
                AWSApplication.main(new String[]{});
            }
        };
        applicationThread.setDaemon(true);
        applicationThread.start();

    }

    /**
     * This method set the new input values.
     * @param input the new user input
     */
    public void setUserInput(final String input) {
        try {
            this.outputStream.write(input.getBytes(Config.DEFAULT_CHARSET));
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
            close();
        }
    }

    /**
     * This method returns the last displayed messages.
     * @return the displayed messages
     */
    public String getDisplayed() {
        String result = "";
        int tries = Config.MAX_RETRIES;
        try {
            while (tries > 0 && result.length() <= 0) {
                Thread.sleep(Config.MAX_SLEEP_TIME);
                result = this.byteArrayOutputStream.toString(
                        Config.DEFAULT_CHARSET.name());
                tries--;
            }
        } catch (InterruptedException e) {
            System.err.println(e.getLocalizedMessage());
        } catch (UnsupportedEncodingException e) {
            System.err.println(e.getLocalizedMessage());
        }
        return result;
    }

    /**
     * This method closed opened stream.
     */
    public void close() {
        try {
            if (this.inputStream != null) {
                this.inputStream.close();
                this.inputStream = null;
            }
            if (this.outputStream != null) {
                this.outputStream.close();
                this.outputStream = null;
            }
            if (this.printStream != null) {
                this.printStream.close();
                this.printStream = null;
            }
            if (this.byteArrayOutputStream != null) {
                this.byteArrayOutputStream.close();
                this.byteArrayOutputStream = null;
            }
        } catch (IOException e) {
            System.err.println(e.getLocalizedMessage());
        } finally {
            System.setIn(this.systeminBackup);
            System.setOut(this.systemoutBackup);
        }
    }

}
