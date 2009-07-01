
package org.xmlsoap.schemas.wsdl;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for tDefinitions complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tDefinitions">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.xmlsoap.org/wsdl/}tExtensibleDocumented">
 *       &lt;sequence>
 *         &lt;group ref="{http://schemas.xmlsoap.org/wsdl/}anyTopLevelOptionalElement" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="targetNamespace" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}NCName" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tDefinitions", propOrder = {
    "anyTopLevelOptionalElement"
})
public class TDefinitions
    extends TExtensibleDocumented
{

    @XmlElements({
        @XmlElement(name = "portType", type = TPortType.class),
        @XmlElement(name = "service", type = TService.class),
        @XmlElement(name = "types", type = TTypes.class),
        @XmlElement(name = "import", type = TImport.class),
        @XmlElement(name = "binding", type = TBinding.class),
        @XmlElement(name = "message", type = TMessage.class)
    })
    protected List<TDocumented> anyTopLevelOptionalElement;
    @XmlAttribute
    @XmlSchemaType(name = "anyURI")
    protected String targetNamespace;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String name;

    /**
     * Gets the value of the anyTopLevelOptionalElement property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the anyTopLevelOptionalElement property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAnyTopLevelOptionalElement().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TPortType }
     * {@link TService }
     * {@link TTypes }
     * {@link TImport }
     * {@link TBinding }
     * {@link TMessage }
     * 
     * 
     */
    public List<TDocumented> getAnyTopLevelOptionalElement() {
        if (anyTopLevelOptionalElement == null) {
            anyTopLevelOptionalElement = new ArrayList<TDocumented>();
        }
        return this.anyTopLevelOptionalElement;
    }

    /**
     * Gets the value of the targetNamespace property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTargetNamespace() {
        return targetNamespace;
    }

    /**
     * Sets the value of the targetNamespace property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTargetNamespace(String value) {
        this.targetNamespace = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

}
