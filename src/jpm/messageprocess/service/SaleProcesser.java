package jpm.messageprocess.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import jpm.messageprocess.model.Product;

/**
 * 
 * @author Sujana Ummani
 * @since 11-12-2017 ClassName:SaleProcesser This class will receive each
 *        message as an input and records the sale and generate the reports
 *
 */
public class SaleProcesser {

	// Product object
	Product product;
	// This property will hold the product type and its sales info as key value
	// pairs
	HashMap<String, Product> saleInfo = new HashMap<String, Product>();
	// MeassageProcesser Object
	MessageProcesser msgProc;
	// AdjustPrice object
	AdjustPrice adPrice;
	// This property will hold the adjusted sales log as an array list
	ArrayList<String> adjustLog = new ArrayList<>();
	// This property will hold total generated sales records as an array list
	ArrayList<String> recordSaleLog = new ArrayList<>();
	// Total products price value
	double totalProductSaleValue;

	// Read each message then call Message Processor class and parse the
	// message.Finally
	// update the product object and related report info
	public void processSale(String msg) {

		double adjustPrice;
		msgProc = new MessageProcesser();

		// Call to Message Processor
		msgProc.processMessage(msg);

		// Create or update the Product object
		this.product = saleInfo.getOrDefault(msgProc.getProductType(), new Product(msgProc.getProductType()));
		this.product.setOperatorType(msgProc.getAdjustmentOpType());
		this.product.setProductQuantity(msgProc.getProductQuantiy());
		this.product.setProductUnitPrice(msgProc.getProductPrice());
		this.product.setTotalProdQuantity(msgProc.getProductQuantiy());
		this.product.setProductSaleValue(msgProc.getProductQuantiy(), msgProc.getProductPrice());

		// Adjust Price If required
		if (this.product.getOperatorType() != null && this.product.getOperatorType() != "") {
			adPrice = new AdjustPrice(product);
			adjustPrice = adPrice.getAdjustedPrice(this.product.getOperatorType(),this.product.getTotalProductSaleValue(),this.product.getTotalProdQuantity(),this.product.getProductUnitPrice());
			this.adjustLog.add(adPrice.adjustmentReport());
			this.product.setTotalProductSaleValue(adjustPrice);

		} else {
			this.product.caluculateTotalPrice(this.product.getProductSaleValue());
		}

		// Record Sale Info
		saleInfo.put(this.product.getProductType(), this.product);

		// Record the sale log
		recordSaleLog.add(msg);

	}

	// Generate the reports
	public void processSalesLog() {
		int repFactor = 4;
		int adjustRepFact = 6;

		// Report after 10th iteration and not at the beginning.
		if ((recordSaleLog.size() % repFactor) == 0 && recordSaleLog.size() != 0) {
			System.out.println("4 sales appended to log");
			System.out.println("*************** Log Report *****************");
			System.out.println("|Product           |Quantity   |Value      |");
			for (Map.Entry<String, Product> type : saleInfo.entrySet()) {
				formatReports(type.getKey(), type.getValue());
			}
			System.out.println("End\n\n");
			try {
				// Add 2 second pause
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// Report and stop execution after 50th message
		if ((recordSaleLog.size() % adjustRepFact) == 0 && recordSaleLog.size() != 0) {
			System.out.println(
					"Application reached 50 messages and pausing the process. The following are the adjustments made to previous sales;\n");

			// Display all the adjustment reports so far recorded.
			getAdjustLog().forEach(System.out::println);
			System.exit(1);
		}

	}

	// Report format structure generation
	public void formatReports(String type, Product product) {
		String lineItem = String.format("|%-18s|%-11d|%-11.2f|", product.getProductType(),
				product.getTotalProdQuantity(), product.getTotalProductSaleValue());
		System.out.println(lineItem);
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public HashMap<String, Product> getSaleInfo() {
		return saleInfo;
	}

	public void setSaleInfo(HashMap<String, Product> saleInfo) {
		this.saleInfo = saleInfo;
	}

	public MessageProcesser getMsgProc() {
		return msgProc;
	}

	public void setMsgProc(MessageProcesser msgProc) {
		this.msgProc = msgProc;
	}

	public AdjustPrice getAdPrice() {
		return adPrice;
	}

	public void setAdPrice(AdjustPrice adPrice) {
		this.adPrice = adPrice;
	}

	public ArrayList<String> getAdjustLog() {
		return adjustLog;
	}

	public void setAdjustLog(ArrayList<String> adjustLog) {
		this.adjustLog = adjustLog;
	}

	public ArrayList<String> getRecordSaleLog() {
		return recordSaleLog;
	}

	public void setRecordSaleLog(ArrayList<String> recordSaleLog) {
		this.recordSaleLog = recordSaleLog;
	}

}
