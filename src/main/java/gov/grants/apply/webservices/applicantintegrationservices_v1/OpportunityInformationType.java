
package gov.grants.apply.webservices.applicantintegrationservices_v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for OpportunityInformationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OpportunityInformationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://apply.grants.gov/WebServices/ApplicantIntegrationServices-V1.0}OpportunityID"/>
 *         &lt;element ref="{http://apply.grants.gov/WebServices/ApplicantIntegrationServices-V1.0}OpportunityTitle"/>
 *         &lt;element ref="{http://apply.grants.gov/WebServices/ApplicantIntegrationServices-V1.0}OpeningDate" minOccurs="0"/>
 *         &lt;element ref="{http://apply.grants.gov/WebServices/ApplicantIntegrationServices-V1.0}ClosingDate" minOccurs="0"/>
 *         &lt;element ref="{http://apply.grants.gov/WebServices/ApplicantIntegrationServices-V1.0}CFDANumber" minOccurs="0"/>
 *         &lt;element ref="{http://apply.grants.gov/WebServices/ApplicantIntegrationServices-V1.0}CompetitionID" minOccurs="0"/>
 *         &lt;element name="SchemaURL" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="InstructionURL" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OpportunityInformationType", propOrder = {
    "opportunityID",
    "opportunityTitle",
    "openingDate",
    "closingDate",
    "cfdaNumber",
    "competitionID",
    "schemaURL",
    "instructionURL"
})
public class OpportunityInformationType {

    @XmlElement(name = "OpportunityID", required = true)
    protected String opportunityID;
    @XmlElement(name = "OpportunityTitle", required = true)
    protected String opportunityTitle;
    @XmlElement(name = "OpeningDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar openingDate;
    @XmlElement(name = "ClosingDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar closingDate;
    @XmlElement(name = "CFDANumber")
    protected String cfdaNumber;
    @XmlElement(name = "CompetitionID")
    protected String competitionID;
    @XmlElement(name = "SchemaURL", required = true)
    protected String schemaURL;
    @XmlElement(name = "InstructionURL", required = true)
    protected String instructionURL;

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
     * Gets the value of the opportunityTitle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOpportunityTitle() {
        return opportunityTitle;
    }

    /**
     * Sets the value of the opportunityTitle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOpportunityTitle(String value) {
        this.opportunityTitle = value;
    }

    /**
     * Gets the value of the openingDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getOpeningDate() {
        return openingDate;
    }

    /**
     * Sets the value of the openingDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setOpeningDate(XMLGregorianCalendar value) {
        this.openingDate = value;
    }

    /**
     * Gets the value of the closingDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getClosingDate() {
        return closingDate;
    }

    /**
     * Sets the value of the closingDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setClosingDate(XMLGregorianCalendar value) {
        this.closingDate = value;
    }

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
     * Gets the value of the schemaURL property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSchemaURL() {
        return schemaURL;
    }

    /**
     * Sets the value of the schemaURL property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSchemaURL(String value) {
        this.schemaURL = value;
    }

    /**
     * Gets the value of the instructionURL property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInstructionURL() {
        return instructionURL;
    }

    /**
     * Sets the value of the instructionURL property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInstructionURL(String value) {
        this.instructionURL = value;
    }

}
