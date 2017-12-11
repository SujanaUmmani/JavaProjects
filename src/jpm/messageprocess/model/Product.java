package jpm.messageprocess.model;

public class Product {

	// Product name
	String productType;
	// Product single Unit price
	double productUnitPrice;
	// Product unit quantity
	int productQuantity;
	//Total Product Quantity
	int totalProdQuantity;
	//Current Sale Value
	double productSaleValue;
	// Total products price value
	double totalProductSaleValue;
	//Adjustment Type
	String operatorType;
	
	
	 // Constructor
    public Product(String type) {
        this.totalProductSaleValue =  0.0;
        this.productSaleValue=0.0;
        this.totalProdQuantity = 0;
        this.productType = type;
        this.operatorType = null;
    }

    public Product() {
		// TODO Auto-generated constructor stub
	}

	// Calculate the given quantity with given price and return the value
    public double calculatePrice(int productQuantity, float productUnitPrice){
        return productQuantity * productUnitPrice;
    }
 // Calculate the given quantity with given price and return the value
    public void caluculateTotalPrice(double price){
    	//System.out.println("Inside caluculate price"+this.totalProductSaleValue+"  "+price+ "Total value is"+(this.totalProductSaleValue+price));
    	this.totalProductSaleValue+=price;
    }
    
    
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public double getProductUnitPrice() {
		return productUnitPrice;
	}
	public void setProductUnitPrice(double productUnitPrice) {
		this.productUnitPrice = productUnitPrice;
	}
	public int getProductQuantity() {
		return productQuantity;
	}
	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}
	public double getTotalProductSaleValue() {
		return totalProductSaleValue;
	}
	public void setTotalProductSaleValue(double productSaleValue) {
		this.totalProductSaleValue = productSaleValue;
	}
	public String getOperatorType() {
		return operatorType;
	}
	public void setOperatorType(String operatorType) {
		this.operatorType = operatorType;
	}
	public int getTotalProdQuantity() {
		return totalProdQuantity;
	}
	public void setTotalProdQuantity(int quanity) {
		this.totalProdQuantity += quanity;
	}

	public double getProductSaleValue() {
		return productSaleValue;
	}

	public void setProductSaleValue(int productQuantity,double productUnitPrice) {
		this.productSaleValue= productQuantity * productUnitPrice;
	}

	
	

}
