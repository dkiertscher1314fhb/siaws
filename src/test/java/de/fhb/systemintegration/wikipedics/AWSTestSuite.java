package de.fhb.systemintegration.wikipedics;

import de.fhb.systemintegration.wikipedics.ui.UITestSuite;
import de.fhb.systemintegration.wikipedics.dao.DAOTestSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * This is a test suite which runs all the test cases.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ UITestSuite.class, DAOTestSuite.class })
public class AWSTestSuite {
}
