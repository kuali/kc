/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.award.bo;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.Sponsor;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class AwardTemplate extends KraPersistableBusinessObjectBase { 
	
	/**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -3038831932003349194L;
    private String templateCode; 
	private String description; 
	private String statusCode; 
	private String primeSponsorCode; 
	private String nonCompetingContPrpslDueCode; 
	private String competingRenewalPrpslDueCode; 
	private String basisOfPaymentCode; 
	private String methodOfPaymentCode; 
	private String paymentInvoiceFreqCode; 
	private Integer invoiceNumberOfCopies; 
	private Integer finalInvoiceDue; 
	
	private Sponsor sponsor; 
	private AwardStatus awardStatus; 
    private AwardBasisOfPayment awardBasisOfPayment; 
    private AwardMethodOfPayment awardMethodOfPayment; 
    private Frequency nonCompetingContPrpslDue;
    private Frequency competingRenewalPrpslDue;
    private Frequency paymentInvoiceFrequency;
//	private TemplateReportTerms templateReportTerms; 
//	private TemplateComments templateComments; 
	private List<AwardTemplateContact> templateContacts; 
//	private TemplateDocumentTerms templateDocumentTerms; 
//	private TemplateEquipmentTerms templateEquipmentTerms; 
//	private TemplateInventionTerms templateInventionTerms; 
//	private TemplatePriorTerms templatePriorTerms; 
//	private TemplatePropertyTerms templatePropertyTerms; 
//	private TemplatePublicationTerms templatePublicationTerms; 
//	private TemplateRightsTerms templateRightsTerms; 
//	private TemplateSubcontractTerms templateSubcontractTerms; 
//	private TemplateTravelTerms templateTravelTerms; 
//	private Award award; 
	
	public AwardTemplate() { 
	    templateContacts = new ArrayList<AwardTemplateContact>();
	} 
	
	public String getTemplateCode() {
		return templateCode;
	}

	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getPrimeSponsorCode() {
		return primeSponsorCode;
	}

	public void setPrimeSponsorCode(String primeSponsorCode) {
		this.primeSponsorCode = primeSponsorCode;
	}

	public String getBasisOfPaymentCode() {
		return basisOfPaymentCode;
	}

	public void setBasisOfPaymentCode(String basisOfPaymentCode) {
		this.basisOfPaymentCode = basisOfPaymentCode;
	}

	public String getMethodOfPaymentCode() {
		return methodOfPaymentCode;
	}

	public void setMethodOfPaymentCode(String methodOfPaymentCode) {
		this.methodOfPaymentCode = methodOfPaymentCode;
	}

	public String getPaymentInvoiceFreqCode() {
		return paymentInvoiceFreqCode;
	}

	public void setPaymentInvoiceFreqCode(String paymentInvoiceFreqCode) {
		this.paymentInvoiceFreqCode = paymentInvoiceFreqCode;
	}

	public Integer getInvoiceNumberOfCopies() {
		return invoiceNumberOfCopies;
	}

	public void setInvoiceNumberOfCopies(Integer invoiceNumberOfCopies) {
		this.invoiceNumberOfCopies = invoiceNumberOfCopies;
	}

	public Integer getFinalInvoiceDue() {
		return finalInvoiceDue;
	}

	public void setFinalInvoiceDue(Integer finalInvoiceDue) {
		this.finalInvoiceDue = finalInvoiceDue;
	}

	public Sponsor getSponsor() {
		return sponsor;
	}

	public void setSponsor(Sponsor sponsor) {
		this.sponsor = sponsor;
	}

	public AwardStatus getAwardStatus() {
		return awardStatus;
	}

	public void setAwardStatus(AwardStatus awardStatus) {
		this.awardStatus = awardStatus;
	}
//
//	public TemplateReportTerms getTemplateReportTerms() {
//		return templateReportTerms;
//	}
//
//	public void setTemplateReportTerms(TemplateReportTerms templateReportTerms) {
//		this.templateReportTerms = templateReportTerms;
//	}
//
//	public TemplateComments getTemplateComments() {
//		return templateComments;
//	}
//
//	public void setTemplateComments(TemplateComments templateComments) {
//		this.templateComments = templateComments;
//	}

	public List<AwardTemplateContact> getTemplateContacts() {
		return templateContacts;
	}

	public void setTemplateContacts(List<AwardTemplateContact> templateContacts) {
		this.templateContacts = templateContacts;
	}

	public AwardTemplateContact getTemplateContact(int index) {
        return templateContacts.isEmpty()?new AwardTemplateContact():templateContacts.get(index);
    }


//	public TemplateDocumentTerms getTemplateDocumentTerms() {
//		return templateDocumentTerms;
//	}
//
//	public void setTemplateDocumentTerms(TemplateDocumentTerms templateDocumentTerms) {
//		this.templateDocumentTerms = templateDocumentTerms;
//	}
//
//	public TemplateEquipmentTerms getTemplateEquipmentTerms() {
//		return templateEquipmentTerms;
//	}
//
//	public void setTemplateEquipmentTerms(TemplateEquipmentTerms templateEquipmentTerms) {
//		this.templateEquipmentTerms = templateEquipmentTerms;
//	}
//
//	public TemplateInventionTerms getTemplateInventionTerms() {
//		return templateInventionTerms;
//	}
//
//	public void setTemplateInventionTerms(TemplateInventionTerms templateInventionTerms) {
//		this.templateInventionTerms = templateInventionTerms;
//	}
//
//	public TemplatePriorTerms getTemplatePriorTerms() {
//		return templatePriorTerms;
//	}
//
//	public void setTemplatePriorTerms(TemplatePriorTerms templatePriorTerms) {
//		this.templatePriorTerms = templatePriorTerms;
//	}
//
//	public TemplatePropertyTerms getTemplatePropertyTerms() {
//		return templatePropertyTerms;
//	}
//
//	public void setTemplatePropertyTerms(TemplatePropertyTerms templatePropertyTerms) {
//		this.templatePropertyTerms = templatePropertyTerms;
//	}
//
//	public TemplatePublicationTerms getTemplatePublicationTerms() {
//		return templatePublicationTerms;
//	}
//
//	public void setTemplatePublicationTerms(TemplatePublicationTerms templatePublicationTerms) {
//		this.templatePublicationTerms = templatePublicationTerms;
//	}
//
//	public TemplateRightsTerms getTemplateRightsTerms() {
//		return templateRightsTerms;
//	}
//
//	public void setTemplateRightsTerms(TemplateRightsTerms templateRightsTerms) {
//		this.templateRightsTerms = templateRightsTerms;
//	}
//
//	public TemplateSubcontractTerms getTemplateSubcontractTerms() {
//		return templateSubcontractTerms;
//	}
//
//	public void setTemplateSubcontractTerms(TemplateSubcontractTerms templateSubcontractTerms) {
//		this.templateSubcontractTerms = templateSubcontractTerms;
//	}
//
//	public TemplateTravelTerms getTemplateTravelTerms() {
//		return templateTravelTerms;
//	}
//
//	public void setTemplateTravelTerms(TemplateTravelTerms templateTravelTerms) {
//		this.templateTravelTerms = templateTravelTerms;
//	}

//	public Award getAward() {
//		return award;
//	}
//
//	public void setAward(Award award) {
//		this.award = award;
//	}

	@Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("templateCode", getTemplateCode());
		hashMap.put("description", getDescription());
//		hashMap.put("statusCode", getStatusCode());
//		hashMap.put("primeSponsorCode", getPrimeSponsorCode());
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
	
}