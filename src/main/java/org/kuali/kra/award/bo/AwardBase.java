/*
 * Copyright 2006-2008 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.award.bo;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.Sponsor;

/**
 * This class...
 */
@SuppressWarnings("serial")
public abstract class AwardBase<R extends AwardReportTermBase,
                                C extends AwardCommentBase,
                                T extends AwardTermBase,
                                P extends AwardSponsorContactBase> extends KraPersistableBusinessObjectBase 
                                                                    implements AwardSynchronizable{
    private Integer templateCode; 
//    private String description; 
    private String primeSponsorCode; 
    private String nonCompetingContPrpslDueCode; 
    private String competingRenewalPrpslDueCode; 
    private String basisOfPaymentCode; 
    private String methodOfPaymentCode; 
    private String paymentInvoiceFreqCode; 
    private Integer invoiceNumberOfCopies; 
    private Integer finalInvoiceDue;
    
    private AwardTemplate awardTemplate;
    private Sponsor primeSponsor;
    private Frequency nonCompetingContPrpslDue;
    private Frequency competingRenewalPrpslDue;
    private Frequency paymentInvoiceFrequency;
    private AwardBasisOfPayment awardBasisOfPayment;
    private AwardMethodOfPayment awardMethodOfPayment;
    private Map<String, String> syncPropertyMap;
    
    public abstract List<R> getAwardReportTermItems();
    public abstract List<C> getAwardComments();
    public abstract List<T> getAwardSponsorTerms();
    public abstract List<P> getSponsorContacts();
    
    
    /**
     * 
     * @see org.kuali.kra.award.bo.AwardSynchronizable#getAwardSyncClass()
     */
    public Class getAwardSyncClass() {
        return Award.class;
    }

    /**
     * Gets the templateCode attribute. 
     * @return Returns the templateCode.
     */
    public Integer getTemplateCode() {
        return templateCode;
    }
    /**
     * Sets the templateCode attribute value.
     * @param templateCode The templateCode to set.
     */
    public void setTemplateCode(Integer templateCode) {
        this.templateCode = templateCode;
    }
    /**
     * Gets the primeSponsorCode attribute. 
     * @return Returns the primeSponsorCode.
     */
    public String getPrimeSponsorCode() {
        return primeSponsorCode;
    }
    /**
     * Sets the primeSponsorCode attribute value.
     * @param primeSponsorCode The primeSponsorCode to set.
     */
    public void setPrimeSponsorCode(String primeSponsorCode) {
        this.primeSponsorCode = primeSponsorCode;
    }
    /**
     * Gets the nonCompetingContPrpslDueCode attribute. 
     * @return Returns the nonCompetingContPrpslDueCode.
     */
    public String getNonCompetingContPrpslDueCode() {
        return nonCompetingContPrpslDueCode;
    }
    /**
     * Sets the nonCompetingContPrpslDueCode attribute value.
     * @param nonCompetingContPrpslDueCode The nonCompetingContPrpslDueCode to set.
     */
    public void setNonCompetingContPrpslDueCode(String nonCompetingContPrpslDueCode) {
        this.nonCompetingContPrpslDueCode = nonCompetingContPrpslDueCode;
    }
    /**
     * Gets the competingRenewalPrpslDueCode attribute. 
     * @return Returns the competingRenewalPrpslDueCode.
     */
    public String getCompetingRenewalPrpslDueCode() {
        return competingRenewalPrpslDueCode;
    }
    /**
     * Sets the competingRenewalPrpslDueCode attribute value.
     * @param competingRenewalPrpslDueCode The competingRenewalPrpslDueCode to set.
     */
    public void setCompetingRenewalPrpslDueCode(String competingRenewalPrpslDueCode) {
        this.competingRenewalPrpslDueCode = competingRenewalPrpslDueCode;
    }
    /**
     * Gets the basisOfPaymentCode attribute. 
     * @return Returns the basisOfPaymentCode.
     */
    public String getBasisOfPaymentCode() {
        return basisOfPaymentCode;
    }
    /**
     * Sets the basisOfPaymentCode attribute value.
     * @param basisOfPaymentCode The basisOfPaymentCode to set.
     */
    public void setBasisOfPaymentCode(String basisOfPaymentCode) {
        this.basisOfPaymentCode = basisOfPaymentCode;
    }
    /**
     * Gets the methodOfPaymentCode attribute. 
     * @return Returns the methodOfPaymentCode.
     */
    public String getMethodOfPaymentCode() {
        return methodOfPaymentCode;
    }
    /**
     * Sets the methodOfPaymentCode attribute value.
     * @param methodOfPaymentCode The methodOfPaymentCode to set.
     */
    public void setMethodOfPaymentCode(String methodOfPaymentCode) {
        this.methodOfPaymentCode = methodOfPaymentCode;
    }
    /**
     * Gets the paymentInvoiceFreqCode attribute. 
     * @return Returns the paymentInvoiceFreqCode.
     */
    public String getPaymentInvoiceFreqCode() {
        return paymentInvoiceFreqCode;
    }
    /**
     * Sets the paymentInvoiceFreqCode attribute value.
     * @param paymentInvoiceFreqCode The paymentInvoiceFreqCode to set.
     */
    public void setPaymentInvoiceFreqCode(String paymentInvoiceFreqCode) {
        this.paymentInvoiceFreqCode = paymentInvoiceFreqCode;
    }
    /**
     * Gets the invoiceNumberOfCopies attribute. 
     * @return Returns the invoiceNumberOfCopies.
     */
    public Integer getInvoiceNumberOfCopies() {
        return invoiceNumberOfCopies;
    }
    /**
     * Sets the invoiceNumberOfCopies attribute value.
     * @param invoiceNumberOfCopies The invoiceNumberOfCopies to set.
     */
    public void setInvoiceNumberOfCopies(Integer invoiceNumberOfCopies) {
        this.invoiceNumberOfCopies = invoiceNumberOfCopies;
    }
    /**
     * Gets the finalInvoiceDue attribute. 
     * @return Returns the finalInvoiceDue.
     */
    public Integer getFinalInvoiceDue() {
        return finalInvoiceDue;
    }
    /**
     * Sets the finalInvoiceDue attribute value.
     * @param finalInvoiceDue The finalInvoiceDue to set.
     */
    public void setFinalInvoiceDue(Integer finalInvoiceDue) {
        this.finalInvoiceDue = finalInvoiceDue;
    } 
    @SuppressWarnings("unchecked")
    @Override 
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap hashMap = new LinkedHashMap();
        hashMap.put("templateCode", getTemplateCode());
//        hashMap.put("description", getDescription());
        hashMap.put("primeSponsorCode", getPrimeSponsorCode());
        hashMap.put("nonCompetingContPrpslDue", getNonCompetingContPrpslDueCode());
        hashMap.put("competingRenewalPrpslDue", getCompetingRenewalPrpslDueCode());
        hashMap.put("basisOfPaymentCode", getBasisOfPaymentCode());
        hashMap.put("methodOfPaymentCode", getMethodOfPaymentCode());
        hashMap.put("paymentInvoiceFreqCode", getPaymentInvoiceFreqCode());
        hashMap.put("invoiceNumberOfCopies", getInvoiceNumberOfCopies());
        hashMap.put("finalInvoiceDue", getFinalInvoiceDue());
        return hashMap;
    }
    /**
     * Gets the awardTemplate attribute. 
     * @return Returns the awardTemplate.
     */
    public AwardTemplate getAwardTemplate() {
        return awardTemplate;
    }
    /**
     * Sets the awardTemplate attribute value.
     * @param awardTemplate The awardTemplate to set.
     */
    public void setAwardTemplate(AwardTemplate awardTemplate) {
        this.awardTemplate = awardTemplate;
    }
    /**
     * Gets the primeSponsor attribute. 
     * @return Returns the primeSponsor.
     */
    public Sponsor getPrimeSponsor() {
        return primeSponsor;
    }
    /**
     * Sets the primeSponsor attribute value.
     * @param primeSponsor The primeSponsor to set.
     */
    public void setPrimeSponsor(Sponsor primeSponsor) {
        this.primeSponsor = primeSponsor;
    }
    /**
     * Gets the nonCompetingContPrpslDue attribute. 
     * @return Returns the nonCompetingContPrpslDue.
     */
    public Frequency getNonCompetingContPrpslDue() {
        return nonCompetingContPrpslDue;
    }
    /**
     * Sets the nonCompetingContPrpslDue attribute value.
     * @param nonCompetingContPrpslDue The nonCompetingContPrpslDue to set.
     */
    public void setNonCompetingContPrpslDue(Frequency nonCompetingContPrpslDue) {
        this.nonCompetingContPrpslDue = nonCompetingContPrpslDue;
    }
    /**
     * Gets the competingRenewalPrpslDue attribute. 
     * @return Returns the competingRenewalPrpslDue.
     */
    public Frequency getCompetingRenewalPrpslDue() {
        return competingRenewalPrpslDue;
    }
    /**
     * Sets the competingRenewalPrpslDue attribute value.
     * @param competingRenewalPrpslDue The competingRenewalPrpslDue to set.
     */
    public void setCompetingRenewalPrpslDue(Frequency competingRenewalPrpslDue) {
        this.competingRenewalPrpslDue = competingRenewalPrpslDue;
    }
    /**
     * Gets the paymentInvoiceFrequency attribute. 
     * @return Returns the paymentInvoiceFrequency.
     */
    public Frequency getPaymentInvoiceFrequency() {
        return paymentInvoiceFrequency;
    }
    /**
     * Sets the paymentInvoiceFrequency attribute value.
     * @param paymentInvoiceFrequency The paymentInvoiceFrequency to set.
     */
    public void setPaymentInvoiceFrequency(Frequency paymentInvoiceFrequency) {
        this.paymentInvoiceFrequency = paymentInvoiceFrequency;
    }
    /**
     * Gets the awardBasisOfPayment attribute. 
     * @return Returns the awardBasisOfPayment.
     */
    public AwardBasisOfPayment getAwardBasisOfPayment() {
        return awardBasisOfPayment;
    }
    /**
     * Sets the awardBasisOfPayment attribute value.
     * @param awardBasisOfPayment The awardBasisOfPayment to set.
     */
    public void setAwardBasisOfPayment(AwardBasisOfPayment awardBasisOfPayment) {
        this.awardBasisOfPayment = awardBasisOfPayment;
    }
    /**
     * Gets the awardMethodOfPayment attribute. 
     * @return Returns the awardMethodOfPayment.
     */
    public AwardMethodOfPayment getAwardMethodOfPayment() {
        return awardMethodOfPayment;
    }
    /**
     * Sets the awardMethodOfPayment attribute value.
     * @param awardMethodOfPayment The awardMethodOfPayment to set.
     */
    public void setAwardMethodOfPayment(AwardMethodOfPayment awardMethodOfPayment) {
        this.awardMethodOfPayment = awardMethodOfPayment;
    }
    
    public Class getSyncBaseClass() {
        return AwardBase.class;
    }

    
    /**
     * Gets the syncPropertyMap attribute. 
     * @return Returns the syncPropertyMap.
     */
    public Map<String, String> getSyncPropertyMap() {
        syncPropertyMap = new HashMap<String, String>();
        syncPropertyMap.put("awardComments", "templateComments");
        syncPropertyMap.put("awardReportTermItems", "templateReportTerms");
        syncPropertyMap.put("awardSponsorTerms", "templateTerms");
        syncPropertyMap.put("sponsorContacts", "templateContacts");
        return syncPropertyMap;
    }

}
