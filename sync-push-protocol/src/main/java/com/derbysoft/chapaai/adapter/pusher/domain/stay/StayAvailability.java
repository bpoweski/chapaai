
package com.derbysoft.chapaai.adapter.pusher.domain.stay;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/** 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns:ns="http://www.derbysoft.com/chapaai" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="StayAvailability">
 *   &lt;xs:sequence>
 *     &lt;xs:element type="ns:DateSpan" name="DateSpan" minOccurs="1" maxOccurs="1"/>
 *     &lt;xs:element name="DayRates" minOccurs="0" maxOccurs="1">
 *       &lt;!-- Reference to inner class DayRates -->
 *     &lt;/xs:element>
 *   &lt;/xs:sequence>
 *   &lt;xs:attribute type="xs:string" use="required" name="RoomTypeCode"/>
 *   &lt;xs:attribute type="xs:string" use="required" name="RatePlanCode"/>
 *   &lt;xs:attribute type="xs:integer" use="required" name="LOS"/>
 *   &lt;xs:attribute type="xs:integer" name="AvailableRooms"/>
 *   &lt;xs:attribute type="xs:boolean" name="Closed"/>
 * &lt;/xs:complexType>
 * </pre>
 */
public class StayAvailability
{
    private DateSpan dateSpan;
    private DayRates dayRates;
    private String roomTypeCode;
    private String ratePlanCode;
    private BigInteger LOS;
    private BigInteger availableRooms;
    private Boolean closed;

    /** 
     * Get the 'DateSpan' element value.
     * 
     * @return value
     */
    public DateSpan getDateSpan() {
        return dateSpan;
    }

    /** 
     * Set the 'DateSpan' element value.
     * 
     * @param dateSpan
     */
    public void setDateSpan(DateSpan dateSpan) {
        this.dateSpan = dateSpan;
    }

    /** 
     * Get the 'DayRates' element value.
     * 
     * @return value
     */
    public DayRates getDayRates() {
        return dayRates;
    }

    /** 
     * Set the 'DayRates' element value.
     * 
     * @param dayRates
     */
    public void setDayRates(DayRates dayRates) {
        this.dayRates = dayRates;
    }

    /** 
     * Get the 'RoomTypeCode' attribute value.
     * 
     * @return value
     */
    public String getRoomTypeCode() {
        return roomTypeCode;
    }

    /** 
     * Set the 'RoomTypeCode' attribute value.
     * 
     * @param roomTypeCode
     */
    public void setRoomTypeCode(String roomTypeCode) {
        this.roomTypeCode = roomTypeCode;
    }

    /** 
     * Get the 'RatePlanCode' attribute value.
     * 
     * @return value
     */
    public String getRatePlanCode() {
        return ratePlanCode;
    }

    /** 
     * Set the 'RatePlanCode' attribute value.
     * 
     * @param ratePlanCode
     */
    public void setRatePlanCode(String ratePlanCode) {
        this.ratePlanCode = ratePlanCode;
    }

    /** 
     * Get the 'LOS' attribute value.
     * 
     * @return value
     */
    public BigInteger getLOS() {
        return LOS;
    }

    /** 
     * Set the 'LOS' attribute value.
     * 
     * @param LOS
     */
    public void setLOS(BigInteger LOS) {
        this.LOS = LOS;
    }

    /** 
     * Get the 'AvailableRooms' attribute value.
     * 
     * @return value
     */
    public BigInteger getAvailableRooms() {
        return availableRooms;
    }

    /** 
     * Set the 'AvailableRooms' attribute value.
     * 
     * @param availableRooms
     */
    public void setAvailableRooms(BigInteger availableRooms) {
        this.availableRooms = availableRooms;
    }

    /** 
     * Get the 'Closed' attribute value.
     * 
     * @return value
     */
    public Boolean getClosed() {
        return closed;
    }

    /** 
     * Set the 'Closed' attribute value.
     * 
     * @param closed
     */
    public void setClosed(Boolean closed) {
        this.closed = closed;
    }
    /** 
     * Schema fragment(s) for this class:
     * <pre>
     * &lt;xs:element xmlns:ns="http://www.derbysoft.com/chapaai" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="DayRates" minOccurs="0" maxOccurs="1">
     *   &lt;xs:complexType>
     *     &lt;xs:sequence>
     *       &lt;xs:element type="ns:DayRate" name="DayRate" minOccurs="0" maxOccurs="unbounded"/>
     *     &lt;/xs:sequence>
     *   &lt;/xs:complexType>
     * &lt;/xs:element>
     * </pre>
     */
    public static class DayRates
    {
        private List<DayRate> dayRateList = new ArrayList<DayRate>();

        /** 
         * Get the list of 'DayRate' element items.
         * 
         * @return list
         */
        public List<DayRate> getDayRateList() {
            return dayRateList;
        }

        /** 
         * Set the list of 'DayRate' element items.
         * 
         * @param list
         */
        public void setDayRateList(List<DayRate> list) {
            dayRateList = list;
        }
    }
}
