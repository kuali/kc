
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
 *         &lt;element name="ApplicationFilter" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Filter">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;enumeration value="Status"/>
 *                         &lt;enumeration value="OpportunityID"/>
 *                         &lt;enumeration value="CFDANumber"/>
 *                         &lt;enumeration value="SubmissionTitle"/>
 *                         &lt;enumeration value="GrantsGovTrackingNumber"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="FilterValue" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
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
    "applicationFilter"
})
@XmlRootElement(name = "GetApplicationListRequest")
public class GetApplicationListRequest {

    @XmlElement(name = "ApplicationFilter")
    protected List<GetApplicationListRequest.ApplicationFilter> applicationFilter;

    /**
     * Gets the value of the applicationFilter property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the applicationFilter property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getApplicationFilter().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GetApplicationListRequest.ApplicationFilter }
     * 
     * 
     */
    public List<GetApplicationListRequest.ApplicationFilter> getApplicationFilter() {
        if (applicationFilter == null) {
            applicationFilter = new ArrayList<GetApplicationListRequest.ApplicationFilter>();
        }
        return this.applicationFilter;
    }


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
     *         &lt;element name="Filter">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;enumeration value="Status"/>
     *               &lt;enumeration value="OpportunityID"/>
     *               &lt;enumeration value="CFDANumber"/>
     *               &lt;enumeration value="SubmissionTitle"/>
     *               &lt;enumeration value="GrantsGovTrackingNumber"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="FilterValue" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
        "filter",
        "filterValue"
    })
    public static class ApplicationFilter {

        @XmlElement(name = "Filter", required = true)
        protected String filter;
        @XmlElement(name = "FilterValue", required = true)
        protected String filterValue;

        /**
         * Gets the value of the filter property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFilter() {
            return filter;
        }

        /**
         * Sets the value of the filter property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFilter(String value) {
            this.filter = value;
        }

        /**
         * Gets the value of the filterValue property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFilterValue() {
            return filterValue;
        }

        /**
         * Sets the value of the filterValue property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFilterValue(String value) {
            this.filterValue = value;
        }

    }

}
