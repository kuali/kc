/*
 * Copyright 2005-2010 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.award.home;

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.award.paymentreports.awardreports.AwardReportTermRecipient;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.Sponsor;
import org.springframework.util.AutoPopulatingList;

public class AwardTemplate extends KraPersistableBusinessObjectBase {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -3038831932003349194L;

    private String statusCode;

    private String description;

    private AwardStatus awardTemplateStatus;

    //sync to templateCode  
    private Integer templateCode;

    //sync to primeSponsorCode  
    private String primeSponsorCode;

    //  
    private String nonCompetingContPrpslDueCode;

    //  
    private String competingRenewalPrpslDueCode;

    //  
    private String basisOfPaymentCode;

    //  
    private String methodOfPaymentCode;

    private Sponsor primeSponsor;

    private AwardBasisOfPayment awardBasisOfPayment;

    private AwardMethodOfPayment awardMethodOfPayment;

    private List<AwardTemplateReportTerm> templateReportTerms;

    private List<AwardTemplateComment> templateComments;

    private List<AwardTemplateContact> templateContacts;

    private List<AwardTemplateTerm> templateTerms;

    @SuppressWarnings("unchecked")
    public AwardTemplate() {
        templateContacts = new AutoPopulatingList<AwardTemplateContact>(AwardTemplateContact.class);
        templateComments = new AutoPopulatingList<AwardTemplateComment>(AwardTemplateComment.class);
        templateTerms = new AutoPopulatingList<AwardTemplateTerm>(AwardTemplateTerm.class);
        templateReportTerms = new AutoPopulatingList<AwardTemplateReportTerm>(AwardTemplateReportTerm.class);
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public AwardStatus getAwardTemplateStatus() {
        return awardTemplateStatus;
    }

    public void setAwardTemplateStatus(AwardStatus awardTemplateStatus) {
        this.awardTemplateStatus = awardTemplateStatus;
    }

    public List<AwardTemplateReportTerm> getTemplateReportTerms() {
        return templateReportTerms;
    }

    public void setTemplateReportTerms(List<AwardTemplateReportTerm> templateReportTerms) {
        this.templateReportTerms = templateReportTerms;
    }

    public List<AwardTemplateContact> getTemplateContacts() {
        return templateContacts;
    }

    public void setTemplateContacts(List<AwardTemplateContact> templateContacts) {
        this.templateContacts = templateContacts;
    }

    /**
     * Gets the templateComments attribute. 
     * @return Returns the templateComments.
     */
    public List<AwardTemplateComment> getTemplateComments() {
        return templateComments;
    }

    /**
     * Sets the templateComments attribute value.
     * @param templateComments The templateComments to set.
     */
    public void setTemplateComments(List<AwardTemplateComment> templateComments) {
        this.templateComments = templateComments;
    }

    /**
     * Gets the templateTerms attribute. 
     * @return Returns the templateTerms.
     */
    public List<AwardTemplateTerm> getTemplateTerms() {
        return templateTerms;
    }

    /**
     * Sets the templateTerms attribute value.
     * @param templateTerms The templateTerms to set.
     */
    public void setTemplateTerms(List<AwardTemplateTerm> templateTerms) {
        this.templateTerms = templateTerms;
    }

    /**
     * Gets the description attribute. 
     * @return Returns the description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description attribute value.
     * @param description The description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public List<AwardTemplateComment> getAwardComments() {
        return getTemplateComments();
    }

    public List<AwardTemplateReportTerm> getAwardReportTermItems() {
        return getTemplateReportTerms();
    }

    public List<AwardTemplateTerm> getAwardSponsorTerms() {
        return getTemplateTerms();
    }

    public List<AwardTemplateContact> getSponsorContacts() {
        return getTemplateContacts();
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

    @SuppressWarnings("unchecked")
    @Override
    public List buildListOfDeletionAwareLists() {
        // TODO : need this ?  
        List managedLists = super.buildListOfDeletionAwareLists();
        managedLists.add(getTemplateTerms());
        managedLists.add(getAwardComments());
        managedLists.add(getTemplateReportTerms());
        managedLists.add(getTemplateContacts());
        ArrayList<AwardReportTermRecipient> rcpts = new ArrayList<AwardReportTermRecipient>();
        for (AwardTemplateReportTerm rt : getTemplateReportTerms()) {
            rcpts.addAll(rt.getAwardReportTermRecipients());
        }
        managedLists.add(rcpts);
        return managedLists;
    }

    public void processAfterCopy() {
        this.setTemplateCode(null);
        for (AwardTemplateReportTerm tempReportTerm : getTemplateReportTerms()) {
            tempReportTerm.setTemplateReportTermId(null);
        }
        for (AwardTemplateComment tempComment : getTemplateComments()) {
            tempComment.setTemplateCommentsId(null);
        }
        for (AwardTemplateContact tempContact : getTemplateContacts()) {
            tempContact.setTemplateContactId(null);
        }
        for (AwardTemplateTerm tempTerm : getTemplateTerms()) {
            ;
            tempTerm.setAwardTemplateTermId(null);
        }
    }
}
