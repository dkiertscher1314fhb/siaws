package de.fhb.systemintegration.wikipedics.dao.inter;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import de.fhb.systemintegration.wikipedics.domain.UserSettings;

/**
 * This class is a test case for the UserSettingsDAO class.
 *
 * @author mlelansky
 * @version 0.0.1
 */
public final class UserSettingsDAOTest extends AbstractDAOTest {

    /**
     * This is the dao interface to test.
     */
    private UserSettingsDAO userSettingsDAO;

    /**
     * This method setups every test.
     */
    @Before
    public void setup() {
        this.userSettingsDAO =  this.getBuilder().getUserSettingsDAO();
    }

    /**
     * This method checks if nobody can save an object
     * with an empty access key.
     */
    @Test
    public void dontSaveEmptyAccesskey() {
        UserSettings settings = new UserSettings();
        settings.setRegion("EU-WEST-1");
        settings.setSecretkey("123ABC");
        settings.setAccesskey(null);
        UserSettings result = this.userSettingsDAO.save(settings);
        Assert.assertNull("The id should be null.", result.getId());
    }

    /**
     * This method checks if the object is stored successfully.
     */
    @Test
    public void saveSettingsSuccessfully() {
        UserSettings settings = new UserSettings();
        settings.setRegion("EU-WEST-1");
        settings.setSecretkey("123ABC");
        settings.setAccesskey("112233AABBCC4455DDEE");
        UserSettings result = this.userSettingsDAO.save(settings);
        Assert.assertNotNull("The id should be not null.", result.getId());
    }

    /**
     * This method read an object from the database and checks the values.
     */
    @Test
    public void findSavedSettings() {
        UserSettings settings = new UserSettings();
        settings.setRegion("EU-WEST-1");
        settings.setSecretkey("ABC123");
        settings.setAccesskey("AABBCC4455DDEE112233");
        this.userSettingsDAO.save(settings);
        final UserSettings result = this.userSettingsDAO.findById(
                settings.getId());
        Assert.assertEquals("The accesskey should be the same.",
                result.getAccesskey(), settings.getAccesskey());
    }

}
