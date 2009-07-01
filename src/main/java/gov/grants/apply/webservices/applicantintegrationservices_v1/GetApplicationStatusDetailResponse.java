
package gov.grants.apply.webservices.applicantintegrationservices_v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://apply.grants.gov/WebServices/ApplicantIntegrationServices-V1.0}Grants_govTrackingNumber"/>
 *         &lt;element name="DetailedStatus" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "grantsGovTrackingNumber",
    "detailedStatus"
})
@XmlRootElement(name = "GetApplicationStatusDetailResponse")
public class GetApplicationStatusDetailResponse {

    @XmlElement(name = "Grants_govTrackingNumber", required = true)
    protected String grantsGovTrackingNumber;
    @XmlElement(name = "DetailedStatus", required = true)
    protected Object detailedStatus;

    /**
     * Gets the value of the grantsGovTrackingNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGrantsGovTrackingNumber() {
        return grantsGovTrackingNumber;
    }

    /**
     * Sets the value of the grantsGovTrackingNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGrantsGovTrackingNumber(String value) {
        this.grantsGovTrackingNumber = value;
    }

    /**
     * Gets the value of the detailedStatus property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getDetailedStatus() {
        return detailedStatus;
    }

    /**
     * Sets the value of the detailedStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setDetailedStatus(Object value) {
        this.detailedStatus = value;
    }

}
