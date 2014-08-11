
package com.derbysoft.chapaai.adapter.pusher.domain.stay;

/** 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns:ns="http://www.derbysoft.com/chapaai" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="StayAvailabilityUpdateResponseError">
 *   &lt;xs:attribute type="xs:string" use="required" name="Code"/>
 *   &lt;xs:attribute type="xs:string" use="required" name="Message"/>
 * &lt;/xs:complexType>
 * </pre>
 */
public class StayAvailabilityUpdateResponseError
{
    private String code;
    private String message;

    /** 
     * Get the 'Code' attribute value.
     * 
     * @return value
     */
    public String getCode() {
        return code;
    }

    /** 
     * Set the 'Code' attribute value.
     * 
     * @param code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /** 
     * Get the 'Message' attribute value.
     * 
     * @return value
     */
    public String getMessage() {
        return message;
    }

    /** 
     * Set the 'Message' attribute value.
     * 
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
