
package com.derbysoft.chapaai.adapter.pusher.domain.stay;

/** 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:simpleType xmlns:ns="http://www.derbysoft.com/chapaai" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="StayAvailabilityUpdateResponseStatus">
 *   &lt;xs:restriction base="xs:string">
 *     &lt;xs:enumeration value="Successful"/>
 *     &lt;xs:enumeration value="Failed"/>
 *   &lt;/xs:restriction>
 * &lt;/xs:simpleType>
 * </pre>
 */
public enum StayAvailabilityUpdateResponseStatus {
    SUCCESSFUL("Successful"), FAILED("Failed");
    private final String value;

    private StayAvailabilityUpdateResponseStatus(String value) {
        this.value = value;
    }

    public String xmlValue() {
        return value;
    }

    public static StayAvailabilityUpdateResponseStatus convert(String value) {
        for (StayAvailabilityUpdateResponseStatus inst : values()) {
            if (inst.xmlValue().equals(value)) {
                return inst;
            }
        }
        return null;
    }
}
