
package com.derbysoft.chapaai.adapter.pusher.domain.stay;

import java.math.BigInteger;

/** 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns:ns="http://www.derbysoft.com/chapaai" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="ExtraRate">
 *   &lt;xs:attribute type="xs:double" use="required" name="BeforeTaxAmount"/>
 *   &lt;xs:attribute type="xs:double" use="required" name="AfterTaxAmount"/>
 *   &lt;xs:attribute type="xs:integer" use="required" name="ChildAge"/>
 * &lt;/xs:complexType>
 * </pre>
 */
public class ExtraRate
{
    private Double beforeTaxAmount;
    private Double afterTaxAmount;
    private BigInteger childAge;

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
     * Get the 'ChildAge' attribute value.
     * 
     * @return value
     */
    public BigInteger getChildAge() {
        return childAge;
    }

    /** 
     * Set the 'ChildAge' attribute value.
     * 
     * @param childAge
     */
    public void setChildAge(BigInteger childAge) {
        this.childAge = childAge;
    }
}
