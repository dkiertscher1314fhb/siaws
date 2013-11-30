package de.fhb.systemintegration.wikipedics.ui.input;

import de.fhb.systemintegration.wikipedics.util.Config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * This class is a wrapper class for one InputStream.
 *
 * @author mlelansky
 * @version 0.0.1
 */
public class InputStreamWrapper implements AutoCloseable {

    /**
     * This is the inputStream that are used in the class.
     */
    private InputStream inputStream;

    /**
     * This is object represents an Reader object which
     * came from a InputStream.
     */
    private InputStreamReader reader;

    /**
     * This objects wraps the InputStreamReader.
     */
    private BufferedReader bufferedReader;

    /**
     * This method is the default constructor.
     */
    public InputStreamWrapper() {
        this(System.in);
    }

    /**
     * This is the initialisation constructor.
     * @param _inputStream the InputStream which should be used.
     */
    public InputStreamWrapper(final InputStream _inputStream) {
        this.setInputStream(_inputStream);
        this.setReader(new InputStreamReader(this.getInputStream(),
                Config.DEFAULT_CHARSET));
        this.setBufferedReader(new BufferedReader(this.getReader()));
    }

    /**
     * This method reads one line of the stream.
     *
     * @return the read line
     */
    public final String readLine() {
        String result = "";
        try {
            if (this.getBufferedReader() != null) {
                result = this.getBufferedReader().readLine();
            } else {
                throw new IOException("The BufferedReader are closed.");
            }
        } catch (IOException e) {
            System.err.println("It could not read a line.");
            System.err.println(e.getLocalizedMessage());
        }
        return result;
    }

    @Override
    public final void close() {
        try {
            if (this.getBufferedReader() != null) {
                this.getBufferedReader().close();
                this.bufferedReader = null;
            }
            if (this.getReader() != null) {
                this.getReader().close();
                this.reader = null;
            }
            if (this.getInputStream() != null) {
                this.getInputStream().close();
                this.inputStream = null;
            }
        } catch (IOException e) {
            System.err.println("The streams couldn't successfully closed.");
            System.err.println(e.getLocalizedMessage());
        }
    }

    /**
     * This method returns the used InputStream object.
     * @return the actual used InputStream
     */
    public final InputStream getInputStream() {
        return this.inputStream;
    }

    /**
     * This method change the used InputStream object.
     * @param _inputStream the InputSteam that should be used
     */
    public final void setInputStream(final InputStream _inputStream) {
        if (_inputStream != null) {
            this.inputStream = _inputStream;
        }
    }

    /**
     * This method returns the InputStreamReader that are used in the class.
     * @return the actual InputStreamReader.
     */
    public final InputStreamReader getReader() {
        return this.reader;
    }

    /**
     * This method changes the used InputStreamReader object.
     * @param _reader the InputStreamReader that should be used
     */
    private void setReader(final InputStreamReader _reader) {
        if (_reader != null) {
            this.reader = _reader;
        }
    }

    /**
     * This method returns the used BufferedReader.
     * @return the actual BufferedReader
     */
    public final BufferedReader getBufferedReader() {
        return this.bufferedReader;
    }

    /**
     * This method changes the used BufferedReader object.
     * @param _bufferedReader the BufferedReader that should be used
     */
    private void setBufferedReader(final BufferedReader _bufferedReader) {
        if (_bufferedReader != null) {
            this.bufferedReader = _bufferedReader;
        }
    }
}
