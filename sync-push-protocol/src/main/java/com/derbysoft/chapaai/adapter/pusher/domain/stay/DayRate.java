
package com.derbysoft.chapaai.adapter.pusher.domain.stay;

import java.util.ArrayList;
import java.util.List;

/** 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns:ns="http://www.derbysoft.com/chapaai" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="DayRate">
 *   &lt;xs:sequence>
 *     &lt;xs:element name="Rates" minOccurs="0" maxOccurs="1">
 *       &lt;!-- Reference to inner class Rates -->
 *     &lt;/xs:element>
 *     &lt;xs:element name="ExtraChildRates" minOccurs="0" maxOccurs="1">
 *       &lt;!-- Reference to inner class ExtraChildRates -->
 *     &lt;/xs:element>
 *   &lt;/xs:sequence>
 *   &lt;xs:attribute type="xs:string" use="required" name="CurrencyCode"/>
 *   &lt;xs:attribute type="xs:string" use="required" name="DayPattern"/>
 * &lt;/xs:complexType>
 * </pre>
 */
public class DayRate
{
    private Rates rates;
    private ExtraChildRates extraChildRates;
    private String currencyCode;
    private String dayPattern;

    /** 
     * Get the 'Rates' element value.
     * 
     * @return value
     */
    public Rates getRates() {
        return rates;
    }

    /** 
     * Set the 'Rates' element value.
     * 
     * @param rates
     */
    public void setRates(Rates rates) {
        this.rates = rates;
    }

    /** 
     * Get the 'ExtraChildRates' element value.
     * 
     * @return value
     */
    public ExtraChildRates getExtraChildRates() {
        return extraChildRates;
    }

    /** 
     * Set the 'ExtraChildRates' element value.
     * 
     * @param extraChildRates
     */
    public void setExtraChildRates(ExtraChildRates extraChildRates) {
        this.extraChildRates = extraChildRates;
    }

    /** 
     * Get the 'CurrencyCode' attribute value.
     * 
     * @return value
     */
    public String getCurrencyCode() {
        return currencyCode;
    }

    /** 
     * Set the 'CurrencyCode' attribute value.
     * 
     * @param currencyCode
     */
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    /** 
     * Get the 'DayPattern' attribute value.
     * 
     * @return value
     */
    public String getDayPattern() {
        return dayPattern;
    }

    /** 
     * Set the 'DayPattern' attribute value.
     * 
     * @param dayPattern
     */
    public void setDayPattern(String dayPattern) {
        this.dayPattern = dayPattern;
    }
    /** 
     * Schema fragment(s) for this class:
     * <pre>
     * &lt;xs:element xmlns:ns="http://www.derbysoft.com/chapaai" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="Rates" minOccurs="0" maxOccurs="1">
     *   &lt;xs:complexType>
     *     &lt;xs:sequence>
     *       &lt;xs:element type="ns:Rate" name="Rate" minOccurs="1" maxOccurs="unbounded"/>
     *     &lt;/xs:sequence>
     *   &lt;/xs:complexType>
     * &lt;/xs:element>
     * </pre>
     */
    public static class Rates
    {
        private List<Rate> rateList = new ArrayList<Rate>();

        /** 
         * Get the list of 'Rate' element items.
         * 
         * @return list
         */
        public List<Rate> getRateList() {
            return rateList;
        }

        /** 
         * Set the list of 'Rate' element items.
         * 
         * @param list
         */
        public void setRateList(List<Rate> list) {
            rateList = list;
        }
    }
    /** 
     * Schema fragment(s) for this class:
     * <pre>
     * &lt;xs:element xmlns:ns="http://www.derbysoft.com/chapaai" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="ExtraChildRates" minOccurs="0" maxOccurs="1">
     *   &lt;xs:complexType>
     *     &lt;xs:sequence>
     *       &lt;xs:element type="ns:ExtraRate" name="ExtraRate" minOccurs="0" maxOccurs="unbounded"/>
     *     &lt;/xs:sequence>
     *   &lt;/xs:complexType>
     * &lt;/xs:element>
     * </pre>
     */
    public static class ExtraChildRates
    {
        private List<ExtraRate> extraRateList = new ArrayList<ExtraRate>();

        /** 
         * Get the list of 'ExtraRate' element items.
         * 
         * @return list
         */
        public List<ExtraRate> getExtraRateList() {
            return extraRateList;
        }

        /** 
         * Set the list of 'ExtraRate' element items.
         * 
         * @param list
         */
        public void setExtraRateList(List<ExtraRate> list) {
            extraRateList = list;
        }
    }
}
