package de.fhb.systemintegration.wikipedics.dao.inter.impl;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import de.fhb.systemintegration.wikipedics.dao.inter.SSHDAO;

/**
 * This is the implementation of the SHDAO interface.
 *
 * @author mlelansky
 * @version 0.0.1
 */
public final class SSHDAOImpl implements SSHDAO {

    /**
     * This is the ssh client.
     */
    private final JSch sshClient;

    /**
     * This is the default amazon port.
     */
    private static final int SSH_PORT = 22;

    /**
     * This is the default ec2-user.
     */
    private static final String EC2_USER = "ec2-user";

    /**
     * This is the default amazon user.
     */
    private static final String AMAZON_USER = "amazon";

    /**
     * This is the default user.
     */
    private static final String ROOT_USER = "root";

    /**
     * This is the default connection timeout.
     */
    private static final int CONNECTION_TIMEOUT = 10000;

    /**
     * This is the default constructor.
     */
    public SSHDAOImpl() {
        this.sshClient = new JSch();
    }


    @Override
    public void runScript(final String shellScript, final String url,
                          final String privateKeyFile) {
        Session sshSession = null;
        Channel sshTerminal = null;
        try {
            configIdentity(privateKeyFile);
            sshSession = getSSHSession(url);
            sshSession.setTimeout(CONNECTION_TIMEOUT);
            sshTerminal = sshSession.openChannel("shell");
            sshTerminal.connect(CONNECTION_TIMEOUT);

        } catch (Exception e) {
            System.err.println("No SSH Connection are amiable");
        } finally {
            if (sshTerminal != null && sshTerminal.isConnected()) {
                sshTerminal.disconnect();
            }
            if (sshSession != null && sshSession.isConnected()) {
                sshSession.disconnect();
            }
        }
    }

    /**
     * This method sets ths private key file to the ssh client.
     * @param keyFile the private key file
     * @throws JSchException if the key file could not parsed
     */
    private void configIdentity(final String keyFile) throws JSchException {
        this.sshClient.addIdentity(keyFile);
    }

    /**
     * This method creates the ssh-session.
     * @param url the host url
     * @throws JSchException if the session could not be created
     * @return the create session
     */
    private Session getSSHSession(final String url) throws JSchException {
        try {
            return this.sshClient.getSession(EC2_USER, url,
                    SSH_PORT);
        } catch (JSchException e) {
            try {
                return this.sshClient.getSession(AMAZON_USER, url,
                        SSH_PORT);
            } catch (JSchException e1) {
                try {
                    return this.sshClient.getSession(ROOT_USER,
                            url, SSH_PORT);
                } catch (JSchException e2) {
                    throw new JSchException("No SSH-Connection could "
                            + "be established.");
                }
            }
        }
    }
}
