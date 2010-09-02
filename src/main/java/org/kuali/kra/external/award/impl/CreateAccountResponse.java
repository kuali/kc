
package org.kuali.kra.external.award.impl;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;



/**
 * <p>Java class for createAccountResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="createAccountResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{KFS}accountCreationStatusDTO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createAccountResponse", propOrder = {
        "accountCreationStatusDTO"
        })
public class CreateAccountResponse {

    @XmlElement(name = "return")
    protected AccountCreationStatusDTO accountCreationStatusDTO;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link AccountCreationStatusDTO }
     *     
     */
    public AccountCreationStatusDTO getReturn() {
        return accountCreationStatusDTO;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountCreationStatusDTO }
     *     
     */
    public void setReturn(AccountCreationStatusDTO value) {
        this.accountCreationStatusDTO = value;
    }

}
