package com.finx.util;

public class TransactionDetailExtn
{
    private String correlationId;

    private String productCode;
    /**
	 * Method Name: getCorrelationId 
	 * Purpose: Get correlation ID
	 * @return correlationId
	 */
    public String getCorrelationId ()
    {
        return correlationId;
    }
    /**
  	 * Method Name: setCorrelationId 
  	 * Purpose: Set correlation ID
  	 * @param correlationId
  	 */
    public void setCorrelationId (String correlationId)
    {
        this.correlationId = correlationId;
    }
    /**
	 * Method Name: getProductCode 
	 * Purpose: Get product code
	 * @return product code
	 */
    public String getProductCode ()
    {
        return productCode;
    }
    /**
  	 * Method Name: setProductCode 
  	 * Purpose: Set productCode
  	 * @param productCode
  	 */
    public void setProductCode (String productCode)
    {
        this.productCode = productCode;
    }

    @Override
    /**
  	 * Method Name: toString 
  	 * Purpose: convert  correlation ID and product code to string
  	 * @return correlationId
  	 * @return productCode
  	 */
    public String toString()
    {
        return "ClassPojo [correlationId = "+correlationId+", productCode = "+productCode+"]";
    }
}
		
