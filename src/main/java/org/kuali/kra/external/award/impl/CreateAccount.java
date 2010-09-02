
package org.kuali.kra.external.award.impl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;



/**
 * <p>Java class for createAccount complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="createAccount">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="accountParametersDTO" type="{KFS}accountParametersDTO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createAccount", propOrder = {
        "accountParametersDTO"
        })
public class CreateAccount {

    protected AccountParametersDTO accountParametersDTO;

    /**
     * Gets the value of the accountParametersDTO property.
     * 
     * @return
     *     possible object is
     *     {@link AccountParametersDTO }
     *     
     */
    public AccountParametersDTO getAccountParametersDTO() {
        return accountParametersDTO;
    }

    /**
     * Sets the value of the accountParametersDTO property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountParametersDTO }
     *     
     */
    public void setAccountParametersDTO(AccountParametersDTO value) {
        this.accountParametersDTO = value;
    }

}
