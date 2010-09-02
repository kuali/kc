
package org.kuali.kra.external.award.impl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for accountParametersDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="accountParametersDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="accountName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="accountNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="adminContactAddressCityName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="adminContactAddressStateCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="adminContactAddressStreetAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="adminContactAddressZipCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cfdaNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="defaultAddressCityName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="defaultAddressStateCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="defaultAddressStreetAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="defaultAddressZipCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="effectiveDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="expenseGuidelineText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="expirationDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="higherEdFunctionCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="incomeGuidelineText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="indirectCostRate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="indirectCostTypeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="offCampusIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="paymentAddressCityName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="paymentAddressStateCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="paymentAddressStreetAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="paymentAddressZipCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="principalId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="purposeText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="unit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "accountParametersDTO", propOrder = {
        "accountName",
        "accountNumber",
        "adminContactAddressCityName",
        "adminContactAddressStateCode",
        "adminContactAddressStreetAddress",
        "adminContactAddressZipCode",
        "cfdaNumber",
        "defaultAddressCityName",
        "defaultAddressStateCode",
        "defaultAddressStreetAddress",
        "defaultAddressZipCode",
        "effectiveDate",
        "expenseGuidelineText",
        "expirationDate",
        "higherEdFunctionCode",
        "incomeGuidelineText",
        "indirectCostRate",
        "indirectCostTypeCode",
        "offCampusIndicator",
        "paymentAddressCityName",
        "paymentAddressStateCode",
        "paymentAddressStreetAddress",
        "paymentAddressZipCode",
        "principalId",
        "purposeText",
        "unit"
        })
public class AccountParametersDTO {

    protected String accountName;
    protected String accountNumber;
    protected String adminContactAddressCityName;
    protected String adminContactAddressStateCode;
    protected String adminContactAddressStreetAddress;
    protected String adminContactAddressZipCode;
    protected String cfdaNumber;
    protected String defaultAddressCityName;
    protected String defaultAddressStateCode;
    protected String defaultAddressStreetAddress;
    protected String defaultAddressZipCode;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar effectiveDate;
    protected String expenseGuidelineText;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar expirationDate;
    protected String higherEdFunctionCode;
    protected String incomeGuidelineText;
    protected String indirectCostRate;
    protected String indirectCostTypeCode;
    protected boolean offCampusIndicator;
    protected String paymentAddressCityName;
    protected String paymentAddressStateCode;
    protected String paymentAddressStreetAddress;
    protected String paymentAddressZipCode;
    protected String principalId;
    protected String purposeText;
    protected String unit;

    /**
     * Gets the value of the accountName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     * Sets the value of the accountName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountName(String value) {
        this.accountName = value;
    }

    /**
     * Gets the value of the accountNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Sets the value of the accountNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountNumber(String value) {
        this.accountNumber = value;
    }

    /**
     * Gets the value of the adminContactAddressCityName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdminContactAddressCityName() {
        return adminContactAddressCityName;
    }

    /**
     * Sets the value of the adminContactAddressCityName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdminContactAddressCityName(String value) {
        this.adminContactAddressCityName = value;
    }

    /**
     * Gets the value of the adminContactAddressStateCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdminContactAddressStateCode() {
        return adminContactAddressStateCode;
    }

    /**
     * Sets the value of the adminContactAddressStateCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdminContactAddressStateCode(String value) {
        this.adminContactAddressStateCode = value;
    }

    /**
     * Gets the value of the adminContactAddressStreetAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdminContactAddressStreetAddress() {
        return adminContactAddressStreetAddress;
    }

    /**
     * Sets the value of the adminContactAddressStreetAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdminContactAddressStreetAddress(String value) {
        this.adminContactAddressStreetAddress = value;
    }

    /**
     * Gets the value of the adminContactAddressZipCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdminContactAddressZipCode() {
        return adminContactAddressZipCode;
    }

    /**
     * Sets the value of the adminContactAddressZipCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdminContactAddressZipCode(String value) {
        this.adminContactAddressZipCode = value;
    }

    /**
     * Gets the value of the cfdaNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCfdaNumber() {
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
    public void setCfdaNumber(String value) {
        this.cfdaNumber = value;
    }

    /**
     * Gets the value of the defaultAddressCityName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefaultAddressCityName() {
        return defaultAddressCityName;
    }

    /**
     * Sets the value of the defaultAddressCityName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefaultAddressCityName(String value) {
        this.defaultAddressCityName = value;
    }

    /**
     * Gets the value of the defaultAddressStateCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefaultAddressStateCode() {
        return defaultAddressStateCode;
    }

    /**
     * Sets the value of the defaultAddressStateCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefaultAddressStateCode(String value) {
        this.defaultAddressStateCode = value;
    }

    /**
     * Gets the value of the defaultAddressStreetAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefaultAddressStreetAddress() {
        return defaultAddressStreetAddress;
    }

    /**
     * Sets the value of the defaultAddressStreetAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefaultAddressStreetAddress(String value) {
        this.defaultAddressStreetAddress = value;
    }

    /**
     * Gets the value of the defaultAddressZipCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefaultAddressZipCode() {
        return defaultAddressZipCode;
    }

    /**
     * Sets the value of the defaultAddressZipCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefaultAddressZipCode(String value) {
        this.defaultAddressZipCode = value;
    }

    /**
     * Gets the value of the effectiveDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEffectiveDate() {
        return effectiveDate;
    }

    /**
     * Sets the value of the effectiveDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEffectiveDate(XMLGregorianCalendar value) {
        this.effectiveDate = value;
    }

    /**
     * Gets the value of the expenseGuidelineText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExpenseGuidelineText() {
        return expenseGuidelineText;
    }

    /**
     * Sets the value of the expenseGuidelineText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExpenseGuidelineText(String value) {
        this.expenseGuidelineText = value;
    }

    /**
     * Gets the value of the expirationDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getExpirationDate() {
        return expirationDate;
    }

    /**
     * Sets the value of the expirationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setExpirationDate(XMLGregorianCalendar value) {
        this.expirationDate = value;
    }

    /**
     * Gets the value of the higherEdFunctionCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHigherEdFunctionCode() {
        return higherEdFunctionCode;
    }

    /**
     * Sets the value of the higherEdFunctionCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHigherEdFunctionCode(String value) {
        this.higherEdFunctionCode = value;
    }

    /**
     * Gets the value of the incomeGuidelineText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIncomeGuidelineText() {
        return incomeGuidelineText;
    }

    /**
     * Sets the value of the incomeGuidelineText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIncomeGuidelineText(String value) {
        this.incomeGuidelineText = value;
    }

    /**
     * Gets the value of the indirectCostRate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndirectCostRate() {
        return indirectCostRate;
    }

    /**
     * Sets the value of the indirectCostRate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndirectCostRate(String value) {
        this.indirectCostRate = value;
    }

    /**
     * Gets the value of the indirectCostTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndirectCostTypeCode() {
        return indirectCostTypeCode;
    }

    /**
     * Sets the value of the indirectCostTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndirectCostTypeCode(String value) {
        this.indirectCostTypeCode = value;
    }

    /**
     * Gets the value of the offCampusIndicator property.
     * 
     */
    public boolean isOffCampusIndicator() {
        return offCampusIndicator;
    }

    /**
     * Sets the value of the offCampusIndicator property.
     * 
     */
    public void setOffCampusIndicator(boolean value) {
        this.offCampusIndicator = value;
    }

    /**
     * Gets the value of the paymentAddressCityName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentAddressCityName() {
        return paymentAddressCityName;
    }

    /**
     * Sets the value of the paymentAddressCityName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentAddressCityName(String value) {
        this.paymentAddressCityName = value;
    }

    /**
     * Gets the value of the paymentAddressStateCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentAddressStateCode() {
        return paymentAddressStateCode;
    }

    /**
     * Sets the value of the paymentAddressStateCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentAddressStateCode(String value) {
        this.paymentAddressStateCode = value;
    }

    /**
     * Gets the value of the paymentAddressStreetAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentAddressStreetAddress() {
        return paymentAddressStreetAddress;
    }

    /**
     * Sets the value of the paymentAddressStreetAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentAddressStreetAddress(String value) {
        this.paymentAddressStreetAddress = value;
    }

    /**
     * Gets the value of the paymentAddressZipCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentAddressZipCode() {
        return paymentAddressZipCode;
    }

    /**
     * Sets the value of the paymentAddressZipCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentAddressZipCode(String value) {
        this.paymentAddressZipCode = value;
    }

    /**
     * Gets the value of the principalId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrincipalId() {
        return principalId;
    }

    /**
     * Sets the value of the principalId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrincipalId(String value) {
        this.principalId = value;
    }

    /**
     * Gets the value of the purposeText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPurposeText() {
        return purposeText;
    }

    /**
     * Sets the value of the purposeText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPurposeText(String value) {
        this.purposeText = value;
    }

    /**
     * Gets the value of the unit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnit() {
        return unit;
    }

    /**
     * Sets the value of the unit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnit(String value) {
        this.unit = value;
    }

}
