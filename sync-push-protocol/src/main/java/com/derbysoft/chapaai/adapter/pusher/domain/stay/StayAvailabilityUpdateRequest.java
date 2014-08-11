
package com.derbysoft.chapaai.adapter.pusher.domain.stay;

import java.util.ArrayList;
import java.util.List;

/** 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns:ns="http://www.derbysoft.com/chapaai" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="StayAvailabilityUpdateRequest">
 *   &lt;xs:sequence>
 *     &lt;xs:element name="StayAvailabilities" minOccurs="1" maxOccurs="1">
 *       &lt;xs:complexType>
 *         &lt;xs:sequence>
 *           &lt;xs:element type="ns:StayAvailability" name="StayAvailability" minOccurs="1" maxOccurs="unbounded"/>
 *         &lt;/xs:sequence>
 *       &lt;/xs:complexType>
 *     &lt;/xs:element>
 *   &lt;/xs:sequence>
 *   &lt;xs:attribute type="xs:string" use="required" name="Token"/>
 *   &lt;xs:attribute type="xs:string" use="required" name="UserName"/>
 *   &lt;xs:attribute type="xs:string" use="required" name="Password"/>
 *   &lt;xs:attribute type="xs:string" use="required" name="HotelCode"/>
 * &lt;/xs:complexType>
 * </pre>
 */
public class StayAvailabilityUpdateRequest
{
    private List<StayAvailability> stayAvailabilityList = new ArrayList<StayAvailability>();
    private String token;
    private String userName;
    private String password;
    private String hotelCode;

    /** 
     * Get the list of 'StayAvailability' element items.
     * 
     * @return list
     */
    public List<StayAvailability> getStayAvailabilityList() {
        return stayAvailabilityList;
    }

    /** 
     * Set the list of 'StayAvailability' element items.
     * 
     * @param list
     */
    public void setStayAvailabilityList(List<StayAvailability> list) {
        stayAvailabilityList = list;
    }

    /** 
     * Get the 'Token' attribute value.
     * 
     * @return value
     */
    public String getToken() {
        return token;
    }

    /** 
     * Set the 'Token' attribute value.
     * 
     * @param token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /** 
     * Get the 'UserName' attribute value.
     * 
     * @return value
     */
    public String getUserName() {
        return userName;
    }

    /** 
     * Set the 'UserName' attribute value.
     * 
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /** 
     * Get the 'Password' attribute value.
     * 
     * @return value
     */
    public String getPassword() {
        return password;
    }

    /** 
     * Set the 'Password' attribute value.
     * 
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /** 
     * Get the 'HotelCode' attribute value.
     * 
     * @return value
     */
    public String getHotelCode() {
        return hotelCode;
    }

    /** 
     * Set the 'HotelCode' attribute value.
     * 
     * @param hotelCode
     */
    public void setHotelCode(String hotelCode) {
        this.hotelCode = hotelCode;
    }
}
