package jpm.messageprocess.tests;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

import jpm.messageprocess.service.MessageProcesser;
/**
 * 
 * @author Sujana Ummani
 * @since  11-12-2017
 * Test Class Name:MessageProcesserTest
 * This class tests the functionality of MessageProcesser Class
 *
 */

public class MessageProcesserTest {

	@Test
	public void testProcessMessage() {
		MessageProcesser processer=new MessageProcesser();
		//MessageFormat1
		String msg="apple at 10p";
		assertEquals(true,processer.processMessage(msg));
        assertEquals("apples", processer.getProductType());
        assertEquals(0.10000000149011612, processer.getProductPrice());
        assertEquals(1, processer.getProductQuantiy());
        
      //MessageFormat2
        msg="20 sales of apples at 10p each";
        MessageProcesser processer1=new MessageProcesser();
        assertEquals(true,processer1.processMessage(msg));
        assertEquals("apples", processer1.getProductType());
        assertEquals(0.10000000149011612, processer1.getProductPrice());
        assertEquals(20, processer1.getProductQuantiy());
        
      //MessageFormat3
        msg="Add 20p apples";
        MessageProcesser processer2=new MessageProcesser();
        assertEquals(true,processer2.processMessage(msg));
        assertEquals("apples", processer2.getProductType());
        assertEquals(0.20000000298023224, processer2.getProductPrice());
        assertEquals("Add", processer2.getAdjustmentOpType());
        
      //MessageFormat4
        msg="Divide 20p apples";
        MessageProcesser processer3=new MessageProcesser();
        assertEquals(false, processer3.processMessage(msg));
        
	}

}
