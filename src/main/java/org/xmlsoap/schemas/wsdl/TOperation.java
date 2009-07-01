
package org.xmlsoap.schemas.wsdl;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for tOperation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tOperation">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.xmlsoap.org/wsdl/}tExtensibleDocumented">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;group ref="{http://schemas.xmlsoap.org/wsdl/}request-response-or-one-way-operation"/>
 *           &lt;group ref="{http://schemas.xmlsoap.org/wsdl/}solicit-response-or-notification-operation"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}NCName" />
 *       &lt;attribute name="parameterOrder" type="{http://www.w3.org/2001/XMLSchema}NMTOKENS" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tOperation", propOrder = {
    "rest"
})
public class TOperation
    extends TExtensibleDocumented
{

    @XmlElementRefs({
        @XmlElementRef(name = "fault", namespace = "http://schemas.xmlsoap.org/wsdl/", type = JAXBElement.class),
        @XmlElementRef(name = "input", namespace = "http://schemas.xmlsoap.org/wsdl/", type = JAXBElement.class),
        @XmlElementRef(name = "output", namespace = "http://schemas.xmlsoap.org/wsdl/", type = JAXBElement.class)
    })
    protected List<JAXBElement<? extends TExtensibleAttributesDocumented>> rest;
    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String name;
    @XmlAttribute
    @XmlSchemaType(name = "NMTOKENS")
    protected List<String> parameterOrder;

    /**
     * Gets the rest of the content model. 
     * 
     * <p>
     * You are getting this "catch-all" property because of the following reason: 
     * The field name "Output" is used by two different parts of a schema. See: 
     * line 0 of http://schemas.xmlsoap.org/wsdl/
     * line 0 of http://schemas.xmlsoap.org/wsdl/
     * <p>
     * To get rid of this property, apply a property customization to one 
     * of both of the following declarations to change their names: 
     * Gets the value of the rest property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rest property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRest().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link TParam }{@code >}
     * {@link JAXBElement }{@code <}{@link TParam }{@code >}
     * {@link JAXBElement }{@code <}{@link TFault }{@code >}
     * 
     * 
     */
    public List<JAXBElement<? extends TExtensibleAttributesDocumented>> getRest() {
        if (rest == null) {
            rest = new ArrayList<JAXBElement<? extends TExtensibleAttributesDocumented>>();
        }
        return this.rest;
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

    /**
     * Gets the value of the parameterOrder property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the parameterOrder property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getParameterOrder().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getParameterOrder() {
        if (parameterOrder == null) {
            parameterOrder = new ArrayList<String>();
        }
        return this.parameterOrder;
    }

}
