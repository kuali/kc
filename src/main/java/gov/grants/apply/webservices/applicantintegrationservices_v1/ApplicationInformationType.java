
package gov.grants.apply.webservices.applicantintegrationservices_v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for ApplicationInformationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ApplicationInformationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://apply.grants.gov/WebServices/ApplicantIntegrationServices-V1.0}CFDANumber" minOccurs="0"/>
 *         &lt;element ref="{http://apply.grants.gov/WebServices/ApplicantIntegrationServices-V1.0}OpportunityID" minOccurs="0"/>
 *         &lt;element ref="{http://apply.grants.gov/WebServices/ApplicantIntegrationServices-V1.0}CompetitionID" minOccurs="0"/>
 *         &lt;element ref="{http://apply.grants.gov/WebServices/ApplicantIntegrationServices-V1.0}Grants_govTrackingNumber"/>
 *         &lt;element ref="{http://apply.grants.gov/WebServices/ApplicantIntegrationServices-V1.0}ReceivedDateTime" minOccurs="0"/>
 *         &lt;element name="GrantsGovApplicationStatus" type="{http://apply.grants.gov/WebServices/ApplicantIntegrationServices-V1.0}GrantsGovApplicationStatusType"/>
 *         &lt;element ref="{http://apply.grants.gov/WebServices/ApplicantIntegrationServices-V1.0}StatusDateTime" minOccurs="0"/>
 *         &lt;element ref="{http://apply.grants.gov/WebServices/ApplicantIntegrationServices-V1.0}AgencyTrackingNumber" minOccurs="0"/>
 *         &lt;element ref="{http://apply.grants.gov/WebServices/ApplicantIntegrationServices-V1.0}SubmissionTitle" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ApplicationInformationType", propOrder = {
    "cfdaNumber",
    "opportunityID",
    "competitionID",
    "grantsGovTrackingNumber",
    "receivedDateTime",
    "grantsGovApplicationStatus",
    "statusDateTime",
    "agencyTrackingNumber",
    "submissionTitle"
})
public class ApplicationInformationType {

    @XmlElement(name = "CFDANumber")
    protected String cfdaNumber;
    @XmlElement(name = "OpportunityID")
    protected String opportunityID;
    @XmlElement(name = "CompetitionID")
    protected String competitionID;
    @XmlElement(name = "Grants_govTrackingNumber", required = true)
    protected String grantsGovTrackingNumber;
    @XmlElement(name = "ReceivedDateTime")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar receivedDateTime;
    @XmlElement(name = "GrantsGovApplicationStatus", required = true)
    protected GrantsGovApplicationStatusType grantsGovApplicationStatus;
    @XmlElement(name = "StatusDateTime")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar statusDateTime;
    @XmlElement(name = "AgencyTrackingNumber")
    protected String agencyTrackingNumber;
    @XmlElement(name = "SubmissionTitle")
    protected String submissionTitle;

    /**
     * Gets the value of the cfdaNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCFDANumber() {
        return cfdaNumber;
    }

    /**
     * Sets the value of the cfdaNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCFDANumber(String value) {
        this.cfdaNumber = value;
    }

    /**
     * Gets the value of the opportunityID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOpportunityID() {
        return opportunityID;
    }

    /**
     * Sets the value of the opportunityID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOpportunityID(String value) {
        this.opportunityID = value;
    }

    /**
     * Gets the value of the competitionID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCompetitionID() {
        return competitionID;
    }

    /**
     * Sets the value of the competitionID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompetitionID(String value) {
        this.competitionID = value;
    }

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
     * Gets the value of the receivedDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getReceivedDateTime() {
        return receivedDateTime;
    }

    /**
     * Sets the value of the receivedDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setReceivedDateTime(XMLGregorianCalendar value) {
        this.receivedDateTime = value;
    }

    /**
     * Gets the value of the grantsGovApplicationStatus property.
     * 
     * @return
     *     possible object is
     *     {@link GrantsGovApplicationStatusType }
     *     
     */
    public GrantsGovApplicationStatusType getGrantsGovApplicationStatus() {
        return grantsGovApplicationStatus;
    }

    /**
     * Sets the value of the grantsGovApplicationStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link GrantsGovApplicationStatusType }
     *     
     */
    public void setGrantsGovApplicationStatus(GrantsGovApplicationStatusType value) {
        this.grantsGovApplicationStatus = value;
    }

    /**
     * Gets the value of the statusDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getStatusDateTime() {
        return statusDateTime;
    }

    /**
     * Sets the value of the statusDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStatusDateTime(XMLGregorianCalendar value) {
        this.statusDateTime = value;
    }

    /**
     * Gets the value of the agencyTrackingNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAgencyTrackingNumber() {
        return agencyTrackingNumber;
    }

    /**
     * Sets the value of the agencyTrackingNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAgencyTrackingNumber(String value) {
        this.agencyTrackingNumber = value;
    }

    /**
     * Gets the value of the submissionTitle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubmissionTitle() {
        return submissionTitle;
    }

    /**
     * Sets the value of the submissionTitle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubmissionTitle(String value) {
        this.submissionTitle = value;
    }

}
