
package com.derbysoft.chapaai.adapter.pusher.domain.stay;

/** 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns:ns="http://www.derbysoft.com/chapaai" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="DateSpan">
 *   &lt;xs:attribute type="xs:string" use="required" name="start"/>
 *   &lt;xs:attribute type="xs:string" use="required" name="end"/>
 * &lt;/xs:complexType>
 * </pre>
 */
public class DateSpan
{
    private String start;
    private String end;

    /** 
     * Get the 'start' attribute value.
     * 
     * @return value
     */
    public String getStart() {
        return start;
    }

    /** 
     * Set the 'start' attribute value.
     * 
     * @param start
     */
    public void setStart(String start) {
        this.start = start;
    }

    /** 
     * Get the 'end' attribute value.
     * 
     * @return value
     */
    public String getEnd() {
        return end;
    }

    /** 
     * Set the 'end' attribute value.
     * 
     * @param end
     */
    public void setEnd(String end) {
        this.end = end;
    }
}
