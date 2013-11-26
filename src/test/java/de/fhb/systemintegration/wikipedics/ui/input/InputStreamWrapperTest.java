package de.fhb.systemintegration.wikipedics.ui.input;

import de.fhb.systemintegration.wikipedics.util.Config;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.hamcrest.CoreMatchers;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/**
 * This class is a test case of the InputStreamWrapper.
 *
 * @author mlelansky
 * @version 0.0.1
 */
public final class InputStreamWrapperTest {

    /**
     * This is the wrapper object to test.
     */
    private InputStreamWrapper wrapper;

    /**
     * This method setup the test for every test method.
     */
    @Before
    public void setup() {
        this.wrapper = new InputStreamWrapper();
    }

    /**
     * This method checks if the InputStreamWrapper implements
     * the AutoCloseable-Interface.
     * @throws Exception if any error occurred
     */
    @Test
    public void checkAutoCloseable() throws Exception {
        Class<?>[] interfaces = this.wrapper.getClass().getInterfaces();
        List<Class<?>> interfaceList = Arrays.asList(interfaces);
        Assert.assertThat(interfaceList,
                CoreMatchers.hasItem(AutoCloseable.class));
    }

    /**
     * This method checks that the default constructor creates an InputStream
     * from System.in.
     * @throws Exception if any error occurred
     */
    @Test
    public void checkInputStreamCreation() throws Exception {
        Assert.assertEquals(System.in, this.wrapper.getInputStream());
    }

    /**
     * This method checks if someone can use a custom InputStream object.
     * @throws Exception if any error occurred
     */
    @Test
    public void checkCustomInputStreamCreation() throws Exception {
        final String hello = "hello";
        InputStream stream = new ByteArrayInputStream(
                hello.getBytes(Config.DEFAULT_CHARSET));
        final InputStreamWrapper inputStreamWrapper =
                new InputStreamWrapper(stream);
        Assert.assertEquals(stream, inputStreamWrapper.getInputStream());
    }

    /**
     * This method checks if the constructor creates an InputStreamReader.
     * @throws Exception if any error occurred
     */
    @Test
    public void checkInputStreamReaderCreation() throws Exception {
        Assert.assertNotNull(this.wrapper.getReader());
    }

    /**
     * This method checks if the class creates an BufferedReader.
     * @throws Exception if any error occurred
     */
    @Test
    public void checkBufferedReaderCreation() throws Exception {
        Assert.assertNotNull(this.wrapper.getBufferedReader());
    }

    /**
     * This method checks that the InputStream is successfully closed.
     * @throws Exception if any error occurred
     */
    @Test
    public void checkInputStreamClosed() throws Exception {
        this.wrapper.close();
        Assert.assertNull(this.wrapper.getInputStream());
    }

    /**
     * This method checks if the reader is successfully closed.
     * @throws Exception if any error occurred
     */
    @Test
    public void checkReaderClosed() throws Exception {
        this.wrapper.close();
        Assert.assertNull(this.wrapper.getReader());
    }

    /**
     * This method checks if the reader is successfully closed.
     * @throws Exception if any error occurred
     */
    @Test
    public void checkBufferedReaderClosed() throws Exception {
        this.wrapper.close();
        Assert.assertNull(this.wrapper.getBufferedReader());
    }

    /**
     * This method checks that the correct input is read.
     * @throws Exception if any error occurred
     */
    @Test
    public void checkReadLine() throws Exception {
        final String hello = "Hello World";
        final ByteArrayInputStream inputStream = new ByteArrayInputStream(
                        hello.getBytes(Config.DEFAULT_CHARSET));
        final InputStreamWrapper inputStreamWrapper =
                new InputStreamWrapper(inputStream);
        final String result = inputStreamWrapper.readLine();
        Assert.assertEquals(hello, result);
        inputStreamWrapper.close();
        inputStream.close();
    }

    /**
     * This method checks that nobody can read after the streams are closed.
     */
    @Test
    public void checkDoNotReadAfterClose() {
        this.wrapper.close();
        final String result = this.wrapper.readLine();
        Assert.assertTrue(result.isEmpty());
    }

    /**
     * This method cleans the test.
     */
    @After
    public void teardown() {
        this.wrapper.close();
    }
}
