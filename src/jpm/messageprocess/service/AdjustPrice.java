package jpm.messageprocess.service;

import jpm.messageprocess.model.Product;

/**
 * 
 * @author Sujana Ummani
 * @since 11-12-2017 ClassName: AdjustPrice This class is used to calculate the
 *        adjustment price based on the input message. Here assumption is 3
 *        types of adjustments can make and these are Add,Multiply and Subtract.
 *        Finally it will generate the adjustment report.
 *
 */
public class AdjustPrice {
	// adjustedPrice holds the adjusted price value
	private double adjustedPrice;

	// product holds the Product object.
	private Product product;

	// Constructor takes Product as argument.
	public AdjustPrice(Product product) {
		this.product = product;
		this.adjustedPrice = 0.0;
	}

	public AdjustPrice() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * Calculates adjustment price based on operator type
	 * 
	 * @returns adjusted price value
	 */
	public double getAdjustedPrice(String type, double totalSaleValue, int totalQuantity, double unitPrice) {
		/*System.out.println(
				 type + "  " + totalSaleValue+" "+totalQuantity+" "+unitPrice);*/
		if (type.equalsIgnoreCase("Add")) {
			
			this.adjustedPrice = totalSaleValue + (totalQuantity * unitPrice);
			//System.out.println("adjusted price is " + this.adjustedPrice);
		} else if (type.equalsIgnoreCase("Multiply")) {
			this.adjustedPrice = totalSaleValue + (totalQuantity * unitPrice) + (totalSaleValue * unitPrice);
		} else if (type.equalsIgnoreCase("Subtract")) {
			this.adjustedPrice = totalSaleValue - (totalQuantity * unitPrice);
		} else {
			System.out.println("Wrong adjustment operatoer");
		}
//System.out.println("op is "+adjustedPrice);
		return adjustedPrice;
	}

	// @returns [String] e.g "Performed Add 20p to 21 apples and price adjusted from
	// 2.10p to 6.30p"
	public String adjustmentReport() {
		String adjustmentReport = String.format("Performed %s %.2fp to %d %s and price adjusted from %.2fp to %.2fp",
				this.product.getOperatorType(), this.product.getProductUnitPrice(), this.product.getTotalProdQuantity(),
				this.product.getProductType(), this.product.getTotalProductSaleValue(), this.adjustedPrice);
		return adjustmentReport;
	}

}
