package de.fhb.systemintegration.wikipedics.ui;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import de.fhb.systemintegration.wikipedics.ui.input.InputTestSuite;
import de.fhb.systemintegration.wikipedics.ui.command.CommandTestSuite;

/**
 * This is a test suite which runs all the test of the ui package.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ InputTestSuite.class, CommandTestSuite.class })
public class UITestSuite {
}
