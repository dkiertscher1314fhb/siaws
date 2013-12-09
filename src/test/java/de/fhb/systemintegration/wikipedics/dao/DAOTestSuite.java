package de.fhb.systemintegration.wikipedics.dao;

import de.fhb.systemintegration.wikipedics.dao.inter.DAOInterTestSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * This is a test suite which runs all the test of the dao package.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ DAOInterTestSuite.class })
public class DAOTestSuite {
}
