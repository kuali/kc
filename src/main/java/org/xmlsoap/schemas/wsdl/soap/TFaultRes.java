
package org.xmlsoap.schemas.wsdl.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for tFaultRes complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tFaultRes">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://schemas.xmlsoap.org/wsdl/soap/}tBody">
 *       &lt;attGroup ref="{http://schemas.xmlsoap.org/wsdl/soap/}tBodyAttributes"/>
 *       &lt;attribute ref="{http://schemas.xmlsoap.org/wsdl/}required"/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tFaultRes")
@XmlSeeAlso({
    TFault.class
})
public abstract class TFaultRes
    extends TBody
{


}
