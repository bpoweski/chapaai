
package com.derbysoft.chapaai.adapter.pusher.domain.stay;

/** 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns:ns="http://www.derbysoft.com/chapaai" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="StayAvailabilityUpdateResponse">
 *   &lt;xs:sequence>
 *     &lt;xs:element type="ns:StayAvailabilityUpdateResponseError" name="Error" minOccurs="0" maxOccurs="1"/>
 *   &lt;/xs:sequence>
 *   &lt;xs:attribute type="xs:string" use="required" name="Token"/>
 *   &lt;xs:attribute type="ns:StayAvailabilityUpdateResponseStatus" use="required" name="Status"/>
 * &lt;/xs:complexType>
 * </pre>
 */
public class StayAvailabilityUpdateResponse
{
    private StayAvailabilityUpdateResponseError error;
    private String token;
    private StayAvailabilityUpdateResponseStatus status;

    /** 
     * Get the 'Error' element value.
     * 
     * @return value
     */
    public StayAvailabilityUpdateResponseError getError() {
        return error;
    }

    /** 
     * Set the 'Error' element value.
     * 
     * @param error
     */
    public void setError(StayAvailabilityUpdateResponseError error) {
        this.error = error;
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
     * Get the 'Status' attribute value.
     * 
     * @return value
     */
    public StayAvailabilityUpdateResponseStatus getStatus() {
        return status;
    }

    /** 
     * Set the 'Status' attribute value.
     * 
     * @param status
     */
    public void setStatus(StayAvailabilityUpdateResponseStatus status) {
        this.status = status;
    }
}
