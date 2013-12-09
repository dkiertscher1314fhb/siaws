package de.fhb.systemintegration.wikipedics.dao.inter;

/**
 * This interface provides the connection to the server.
 *
 * @author mlelansky
 * @version 0.0.1
 */
public interface SSHDAO {

    /**
     * This method run a shell script on the specific server.
     * @param shellScript the path to the shell script
     * @param url the server path
     * @param privateKeyFile the path to the private key
     */
    void runScript(final String shellScript, final String url,
                   final String privateKeyFile);
}
