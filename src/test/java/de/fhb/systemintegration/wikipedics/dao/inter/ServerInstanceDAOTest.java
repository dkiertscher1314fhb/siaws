package de.fhb.systemintegration.wikipedics.dao.inter;

import de.fhb.systemintegration.wikipedics.domain.ServerInstance;
import de.fhb.systemintegration.wikipedics.domain.ServerType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * This class is a test case for the ServerInstanceDAO class.
 *
 * @author mlelansky
 * @version 0.0.1
 */
public final class ServerInstanceDAOTest extends AbstractDAOTest {

    /**
     * This is the dao interface to test.
     */
    private ServerInstanceDAO serverInstanceDAO;

    /**
     * This method setups every test.
     */
    @Before
    public void setup() {
        this.serverInstanceDAO =  this.getBuilder().getServerInstanceDAO();
    }

    /**
     * This method checks that nobody can save an instance with no instance id.
     */
    @Test
    public void checkDontSaveWithEmptyInstanceId() {
        ServerInstance instance = new ServerInstance();
        instance.setImageId("ABCD1243");
        instance.setType(ServerType.JAVA_BACKEND);
        instance.setUrl("http://invalid");
        final ServerInstance result = this.serverInstanceDAO.save(instance);
        Assert.assertNull("The id should be null", result.getId());
    }

    /**
     * This method checks that every url is unique.
     */
    @Test
    public void checkSingleUrl() {
        ServerInstance firstInstance = new ServerInstance();
        firstInstance.setImageId("ABC123");
        firstInstance.setInstanceId("ABC123");
        firstInstance.setType(ServerType.RAILS_FRONTEND);
        firstInstance.setUrl("http://test.amazon.com");
        this.serverInstanceDAO.save(firstInstance);
        ServerInstance secondInstance = new ServerInstance();
        secondInstance.setImageId("ABC123");
        secondInstance.setInstanceId("ABC456");
        secondInstance.setType(ServerType.RAILS_FRONTEND);
        secondInstance.setUrl("http://test.amazon.com");
        final ServerInstance result = this.serverInstanceDAO.save(
                secondInstance);
        Assert.assertNull("The id should be null", result.getId());
        this.serverInstanceDAO.delete(firstInstance);
    }

    /**
     * This method checks that the find method works correct.
     */
    @Test
    public void checkFindByType() {
        ServerInstance neo4jInstance = new ServerInstance();
        neo4jInstance.setImageId("ABC123");
        neo4jInstance.setInstanceId("ABC123");
        neo4jInstance.setType(ServerType.NEO4J);
        neo4jInstance.setUrl("http://neo4j.amazon.de");
        this.serverInstanceDAO.save(neo4jInstance);
        ServerInstance backend1 = new ServerInstance();
        backend1.setImageId("ABC123");
        backend1.setInstanceId("123ABC");
        backend1.setType(ServerType.JAVA_BACKEND);
        backend1.setUrl("http://backend1.amazon.de");
        this.serverInstanceDAO.save(backend1);
        ServerInstance backend2 = new ServerInstance();
        backend2.setImageId("ABC123");
        backend2.setInstanceId("ABC456");
        backend2.setType(ServerType.JAVA_BACKEND);
        backend2.setUrl("http://backend2.amazon.de");
        this.serverInstanceDAO.save(backend2);
        ServerInstance frontent1 = new ServerInstance();
        frontent1.setImageId("ABC123");
        frontent1.setInstanceId("456ABC");
        frontent1.setType(ServerType.RAILS_FRONTEND);
        frontent1.setUrl("http://frontend1.amazon.de");
        this.serverInstanceDAO.save(frontent1);
        ServerInstance frontent2 = new ServerInstance();
        frontent2.setImageId("ABC123");
        frontent2.setInstanceId("ABC789");
        frontent2.setType(ServerType.RAILS_FRONTEND);
        frontent2.setUrl("http://frontend2.amazon.de");
        this.serverInstanceDAO.save(frontent2);
        final List<ServerInstance> neo4JList = this.serverInstanceDAO.
                findByType(ServerType.NEO4J);
        final List<ServerInstance> backendList = this.serverInstanceDAO.
                findByType(ServerType.JAVA_BACKEND);
        final List<ServerInstance> frontendList = this.serverInstanceDAO.
                findByType(ServerType.RAILS_FRONTEND);
        Assert.assertEquals(1, neo4JList.size());
        Assert.assertEquals(2, backendList.size());
        Assert.assertEquals(2, frontendList.size());
        this.serverInstanceDAO.delete(neo4jInstance);
        this.serverInstanceDAO.delete(backend1);
        this.serverInstanceDAO.delete(backend2);
        this.serverInstanceDAO.delete(frontent1);
        this.serverInstanceDAO.delete(frontent2);
    }
}
