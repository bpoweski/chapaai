
package com.derbysoft.chapaai.adapter.pusher.domain.stay;

import java.math.BigInteger;

/** 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns:ns="http://www.derbysoft.com/chapaai" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="Rate">
 *   &lt;xs:attribute type="xs:double" use="required" name="BeforeTaxAmount"/>
 *   &lt;xs:attribute type="xs:double" use="required" name="AfterTaxAmount"/>
 *   &lt;xs:attribute type="xs:integer" use="required" name="AdultCount"/>
 * &lt;/xs:complexType>
 * </pre>
 */
public class Rate
{
    private Double beforeTaxAmount;
    private Double afterTaxAmount;
    private BigInteger adultCount;

    /** 
     * Get the 'BeforeTaxAmount' attribute value.
     * 
     * @return value
     */
    public Double getBeforeTaxAmount() {
        return beforeTaxAmount;
    }

    /** 
     * Set the 'BeforeTaxAmount' attribute value.
     * 
     * @param beforeTaxAmount
     */
    public void setBeforeTaxAmount(Double beforeTaxAmount) {
        this.beforeTaxAmount = beforeTaxAmount;
    }

    /** 
     * Get the 'AfterTaxAmount' attribute value.
     * 
     * @return value
     */
    public Double getAfterTaxAmount() {
        return afterTaxAmount;
    }

    /** 
     * Set the 'AfterTaxAmount' attribute value.
     * 
     * @param afterTaxAmount
     */
    public void setAfterTaxAmount(Double afterTaxAmount) {
        this.afterTaxAmount = afterTaxAmount;
    }

    /** 
     * Get the 'AdultCount' attribute value.
     * 
     * @return value
     */
    public BigInteger getAdultCount() {
        return adultCount;
    }

    /** 
     * Set the 'AdultCount' attribute value.
     * 
     * @param adultCount
     */
    public void setAdultCount(BigInteger adultCount) {
        this.adultCount = adultCount;
    }
}
