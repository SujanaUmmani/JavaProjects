package jpm.messageprocess.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * 
 * @author Sujana Ummani
 * @since 11-12-2017 Test suite Class Name:AllTests This class contains the all
 *        test classes as a suite
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ AdjustPriceTest.class, MessageProcesserTest.class, SaleProcesserTest.class })
public class AllTests {

}
