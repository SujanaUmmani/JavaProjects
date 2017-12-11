package jpm.messageprocess.tests;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;

import org.junit.Test;

import jpm.messageprocess.model.Product;
import jpm.messageprocess.service.SaleProcesser;
/**
 * 
 * @author Sujana Ummani
 * @since  11-12-2017
 * Test Class Name:SaleProcesserTest
 * This class tests the functionality of SaleProcesserTest Class
 *
 */
public class SaleProcesserTest {

	@Test
	public void testProcessSale() {
		SaleProcesser saleProc = new SaleProcesser();
		Product product = new Product();
		HashMap<String, Product> saleInfo = new HashMap<String, Product>();
		
		// Test With new Product Type
		String msg = "apple at 10p";
		saleProc.processSale(msg);
		assertEquals(true, saleProc.getSaleInfo().containsKey("apples"));
		product = saleProc.getSaleInfo().get("apples");
		assertEquals("apples", product.getProductType());
		assertEquals(0.10000000149011612, product.getProductUnitPrice());
		assertEquals(1, product.getProductQuantity());

		// Test updating existing Product Type
		msg = "20 sales of apples at 10p each";
		saleProc.processSale(msg);
		assertEquals(true, saleProc.getSaleInfo().containsKey("apples"));
		product = saleProc.getSaleInfo().get("apples");
		assertEquals("apples", product.getProductType());
		assertEquals(0.10000000149011612, product.getProductUnitPrice());
		assertEquals(20, product.getProductQuantity());

		// Test with adjustment message
		msg = "Add 20p apples";
		saleProc.processSale(msg);
		assertEquals(true, saleProc.getSaleInfo().containsKey("apples"));
		product = saleProc.getSaleInfo().get("apples");
		assertEquals("apples", product.getProductType());
		assertEquals("Add", product.getOperatorType());
	}

}
