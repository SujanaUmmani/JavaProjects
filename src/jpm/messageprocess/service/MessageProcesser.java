package jpm.messageprocess.service;

/**
 * 
 * @author Sujana Ummani
 * @since 11-12-17 Class Name: MessageProcesser This class will receive each
 *        message and identifies  of its format and do the necessary parsing
 *
 */

public class MessageProcesser {
	// Holds the productQuantity number
	int productQuantiy;
	// Holds the product type name
	String productType;
	// Holds the single product price info
	double productPrice;
	// Holds the adjustment operator name
	String adjustmentOpType;

	// Constructor
	public MessageProcesser() {
		productQuantiy = 0;
		productType = "";
		productPrice = 0.0;
		adjustmentOpType = "";

	}

	// Reads each message processes it and identifies its message format
	public boolean processMessage(String msg) {

		String[] strTokens = msg.trim().split(" ");
		System.out.println("msg is "+msg);

		if (strTokens.length == 7 || strTokens[1].equalsIgnoreCase("at")) {
			//System.out.println("Inside m1" + adjustmentOpType);
			recordSales(msg);
		} else if (strTokens[0].equalsIgnoreCase("Add") || strTokens[0].equalsIgnoreCase("Multiply")
				|| strTokens[0].equalsIgnoreCase("Subtract")) {
			System.out.println("Inside m2");
			adjustPreviousSales(msg);

		} else {
			System.out.println("Message is not correctly Formatted");
			return false;
		}
		return true;
	}

	// Processes message Format 1 and message format 2
	public void recordSales(String msg) {
		String[] msgTokens = msg.trim().split(" ");

		if (msgTokens.length == 3) {
			if (msgTokens[1].equalsIgnoreCase("at")) {
				productType = parseType(msgTokens[0]);
				productPrice = parsePrice(msgTokens[2]);
				productQuantiy = 1;
			}
		} else if (msgTokens.length == 7) {
			productType = parseType(msgTokens[3]);
			productPrice = parsePrice(msgTokens[5]);
			productQuantiy = (Integer.parseInt(msgTokens[0]));

		}

	}

	// Processes message Format 3
	public void adjustPreviousSales(String msg) {

		String[] messageArray = msg.trim().split(" ");
		if (messageArray.length == 3) {
			adjustmentOpType = messageArray[0];
			productType = messageArray[2];
			productQuantiy = 0;
			productPrice = parsePrice(messageArray[1]);
		}
	}

	// Method to convert the price
	public float parsePrice(String rawPrice) {
		float price = Float.parseFloat(rawPrice.replaceAll("£|p", ""));
		if (!rawPrice.contains(".")) {
			price = Float.valueOf(Float.valueOf(price) / Float.valueOf("100"));
		}
		return price;
	}

	// Method to convert the product name from singular to plural
	public String parseType(String rawType) {
		String parsedType = "";
		String typeWithoutLastChar = rawType.substring(0, rawType.length() - 1);
		if (rawType.endsWith("o")) {
			parsedType = String.format("%soes", typeWithoutLastChar);
		} else if (rawType.endsWith("y")) {
			parsedType = String.format("%sies", typeWithoutLastChar);
		} else if (rawType.endsWith("h")) {
			parsedType = String.format("%shes", typeWithoutLastChar);
		} else if (!rawType.endsWith("s")) {
			parsedType = String.format("%ss", rawType);
		} else {
			parsedType = String.format("%s", rawType);
		}
		return parsedType.toLowerCase();
	}

	public int getProductQuantiy() {
		return productQuantiy;
	}

	public void setProductQuantiy(int productQuantiy) {
		this.productQuantiy = productQuantiy;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public String getAdjustmentOpType() {
		return adjustmentOpType;
	}

	public void setAdjustmentOpType(String adjustmentOpType) {
		this.adjustmentOpType = adjustmentOpType;
	}

}
