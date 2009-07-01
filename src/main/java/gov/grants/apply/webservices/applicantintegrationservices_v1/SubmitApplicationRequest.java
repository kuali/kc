
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
 *         &lt;element name="GrantApplicationXML" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
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
    "grantApplicationXML"
})
@XmlRootElement(name = "SubmitApplicationRequest")
public class SubmitApplicationRequest {

    @XmlElement(name = "GrantApplicationXML", required = true)
    protected Object grantApplicationXML;

    /**
     * Gets the value of the grantApplicationXML property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getGrantApplicationXML() {
        return grantApplicationXML;
    }

    /**
     * Sets the value of the grantApplicationXML property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setGrantApplicationXML(Object value) {
        this.grantApplicationXML = value;
    }

}
