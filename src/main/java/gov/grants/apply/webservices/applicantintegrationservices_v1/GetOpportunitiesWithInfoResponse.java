
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
 *         &lt;element name="OpportunityInfo" type="{http://apply.grants.gov/WebServices/ApplicantIntegrationServices-V1.0}OpportunityWithInfoType" maxOccurs="unbounded" minOccurs="0"/>
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
    "opportunityInfo"
})
@XmlRootElement(name = "GetOpportunitiesWithInfoResponse")
public class GetOpportunitiesWithInfoResponse {

    @XmlElement(name = "OpportunityInfo")
    protected List<OpportunityWithInfoType> opportunityInfo;

    /**
     * Gets the value of the opportunityInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the opportunityInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOpportunityInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OpportunityWithInfoType }
     * 
     * 
     */
    public List<OpportunityWithInfoType> getOpportunityInfo() {
        if (opportunityInfo == null) {
            opportunityInfo = new ArrayList<OpportunityWithInfoType>();
        }
        return this.opportunityInfo;
    }

}
