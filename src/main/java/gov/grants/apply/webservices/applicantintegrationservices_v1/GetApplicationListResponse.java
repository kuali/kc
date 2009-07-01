
package gov.grants.apply.webservices.applicantintegrationservices_v1;

import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element name="AvailableApplicationNumber" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ApplicationInformation" type="{http://apply.grants.gov/WebServices/ApplicantIntegrationServices-V1.0}ApplicationInformationType" maxOccurs="unbounded" minOccurs="0"/>
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
    "availableApplicationNumber",
    "applicationInformation"
})
@XmlRootElement(name = "GetApplicationListResponse")
public class GetApplicationListResponse {

    @XmlElement(name = "AvailableApplicationNumber")
    protected int availableApplicationNumber;
    @XmlElement(name = "ApplicationInformation")
    protected List<ApplicationInformationType> applicationInformation;

    /**
     * Gets the value of the availableApplicationNumber property.
     * 
     */
    public int getAvailableApplicationNumber() {
        return availableApplicationNumber;
    }

    /**
     * Sets the value of the availableApplicationNumber property.
     * 
     */
    public void setAvailableApplicationNumber(int value) {
        this.availableApplicationNumber = value;
    }

    /**
     * Gets the value of the applicationInformation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the applicationInformation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getApplicationInformation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ApplicationInformationType }
     * 
     * 
     */
    public List<ApplicationInformationType> getApplicationInformation() {
        if (applicationInformation == null) {
            applicationInformation = new ArrayList<ApplicationInformationType>();
        }
        return this.applicationInformation;
    }

}
