
package gov.grants.apply.webservices.applicantintegrationservices_v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GrantsGovApplicationStatusType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="GrantsGovApplicationStatusType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Receiving"/>
 *     &lt;enumeration value="Received"/>
 *     &lt;enumeration value="Processing"/>
 *     &lt;enumeration value="Validated"/>
 *     &lt;enumeration value="Rejected with Errors"/>
 *     &lt;enumeration value="Download Preparation"/>
 *     &lt;enumeration value="Received by Agency"/>
 *     &lt;enumeration value="Agency Tracking Number Assigned"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "GrantsGovApplicationStatusType")
@XmlEnum
public enum GrantsGovApplicationStatusType {

    @XmlEnumValue("Receiving")
    RECEIVING("Receiving"),
    @XmlEnumValue("Received")
    RECEIVED("Received"),
    @XmlEnumValue("Processing")
    PROCESSING("Processing"),
    @XmlEnumValue("Validated")
    VALIDATED("Validated"),
    @XmlEnumValue("Rejected with Errors")
    REJECTED_WITH_ERRORS("Rejected with Errors"),
    @XmlEnumValue("Download Preparation")
    DOWNLOAD_PREPARATION("Download Preparation"),
    @XmlEnumValue("Received by Agency")
    RECEIVED_BY_AGENCY("Received by Agency"),
    @XmlEnumValue("Agency Tracking Number Assigned")
    AGENCY_TRACKING_NUMBER_ASSIGNED("Agency Tracking Number Assigned");
    private final String value;

    GrantsGovApplicationStatusType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static GrantsGovApplicationStatusType fromValue(String v) {
        for (GrantsGovApplicationStatusType c: GrantsGovApplicationStatusType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
