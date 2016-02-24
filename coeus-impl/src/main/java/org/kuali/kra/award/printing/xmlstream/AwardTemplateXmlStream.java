/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.kuali.kra.award.printing.xmlstream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.common.framework.print.stream.xml.XmlStream;
import org.kuali.coeus.common.framework.print.util.PrintingUtils;
import org.kuali.coeus.common.framework.rolodex.Rolodex;
import org.kuali.coeus.common.framework.sponsor.Sponsor;
import org.kuali.coeus.common.framework.sponsor.term.SponsorTerm;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.kra.award.home.*;
import org.kuali.kra.award.paymentreports.Frequency;
import org.kuali.kra.award.paymentreports.FrequencyBase;
import org.kuali.kra.award.paymentreports.Report;
import org.kuali.kra.award.paymentreports.ReportClass;
import org.kuali.kra.award.printing.AwardPrintType;
import org.kuali.kra.printing.schema.*;
import org.kuali.kra.printing.schema.ContactType;
import org.kuali.kra.printing.schema.ReportTermDetailsType.MailCopies;
import org.kuali.kra.printing.schema.TemplateDocument.Template;
import org.kuali.rice.core.api.datetime.DateTimeService;

import java.sql.Date;
import java.util.*;

/**
 * This class generates XML that conforms with the XSD related to Award Template
 * Report. The data for XML is derived from {@link org.kuali.coeus.sys.framework.model.KcTransactionalDocumentBase} and
 * {@link Map} of details passed to the class.
 * 
 * 
 */
public class AwardTemplateXmlStream implements XmlStream {
	
	private static final Log LOG = LogFactory.getLog(AwardTemplateXmlStream.class);
	

	private DateTimeService dateTimeService = null;
	private static final String SCHOOL_NAME = "SCHOOL_NAME";
	private static final String SCHOOL_ACRONYM = "SCHOOL_ACRONYM";
	private String previousDescription="";

	/**
	 * This method generates XML for Award template Report. It uses data passed
	 * in {@link org.kuali.coeus.sys.framework.model.KcTransactionalDocumentBase} for populating the XML nodes. The XMl
	 * once generated is returned as {@link XmlObject}
	 * 
	 * @param printableBusinessObject
	 *            using which XML is generated
	 * @param reportParameters
	 *            parameters related to XML generation
	 * @return {@link XmlObject} representing the XML
	 */
	public Map<String, XmlObject> generateXmlStream(
			KcPersistableBusinessObjectBase printableBusinessObject, Map<String, Object> reportParameters) {
		Map<String, XmlObject> awardTemplateXmlStream = new HashMap<String, XmlObject>();
		AwardTemplate awardTemplate = (AwardTemplate) printableBusinessObject;
		TemplateDocument templateDocument = TemplateDocument.Factory.newInstance();
		if (awardTemplate != null) {
			templateDocument.setTemplate(getTemplate(awardTemplate));
		}
		awardTemplateXmlStream.put(AwardPrintType.AWARD_TEMPLATE.getAwardPrintType(), templateDocument);
		return awardTemplateXmlStream;
	}

	/*
	 * This method will set the values to template elements and finally returns
	 * the template xml object.
	 */
	private Template getTemplate(AwardTemplate awardTemplate) {
		Template template = Template.Factory.newInstance();
		awardTemplate.refreshNonUpdateableReferences();
		template.setTemplateMaster(getTemplateMaster(awardTemplate));
		template.setSchoolInfo(getSchoolInfoType());
		template.setCommentArray(getCommentType(awardTemplate));
		template.setContactArray(getContactType(awardTemplate));
		template.setTermArray(getTerms(awardTemplate));
		template.setReportArray(getReportTermTypes(awardTemplate));
		return template;
	}

	private TemplateMasterData getTemplateMaster(AwardTemplate awardTemplate) {
	    TemplateMasterData templateMasterData = TemplateMasterData.Factory.newInstance();
	    templateMasterData.setCurrentDate(getDateTimeService().getCurrentCalendar());
	    templateMasterData.setDescription(awardTemplate.getDescription());
	    templateMasterData.setTemplateCode(awardTemplate.getTemplateCode());
	    if(awardTemplate.getBasisOfPaymentCode()!=null){
	        BasisPaymentType basisPayment = templateMasterData.addNewBasisPayment();
	        AwardBasisOfPayment awardBasisOfPayment = awardTemplate.getAwardBasisOfPayment();
	        if(awardBasisOfPayment!=null){
	            basisPayment.setBasisPaymentCode(awardBasisOfPayment.getBasisOfPaymentCode());
	            basisPayment.setBasisPaymentDesc(awardBasisOfPayment.getDescription());
	        }
	    }
	    if(awardTemplate.getCompetingRenewalPrpslDueCode()!=null){
	        CompetingRenewalType competingRenewal = templateMasterData.addNewCompetingRenewal();
	        competingRenewal.setCompetingRenewalCode(awardTemplate.getCompetingRenewalPrpslDueCode());
	    }
	    if(awardTemplate.getNonCompetingContPrpslDueCode()!=null){
	        NonCompetingContType nonCompetingCont = templateMasterData.addNewNonCompetingCont();
	        nonCompetingCont.setNonCompetingContCode(awardTemplate.getNonCompetingContPrpslDueCode());
	    }
	    if(awardTemplate.getMethodOfPaymentCode()!=null){
	        AwardMethodOfPayment awardMethodOfPayment = awardTemplate.getAwardMethodOfPayment();
	        PaymentMethodType paymentMethod = templateMasterData.addNewPaymentMethod();
	        paymentMethod.setPaymentMethodCode(awardMethodOfPayment.getMethodOfPaymentCode());
	        paymentMethod.setPaymentMethodDesc(awardMethodOfPayment.getDescription());
	    }
	    if(awardTemplate.getPrimeSponsorCode()!=null){
	        SponsorType sponsorType = templateMasterData.addNewPrimeSponsor();
	        Sponsor sponsor = awardTemplate.getPrimeSponsor();
	        sponsorType.setSponsorCode(sponsor.getSponsorCode());
	        sponsorType.setSponsorName(sponsor.getSponsorName());
	    }
	    if(awardTemplate.getStatusCode()!=null){
	        TemplateStatusType templateStatus = templateMasterData.addNewTemplateStatus();
	        AwardStatus awardTemplateStatus = awardTemplate.getAwardTemplateStatus();
	        templateStatus.setStatusCode(Integer.parseInt(awardTemplateStatus.getStatusCode()));
	        templateStatus.setStatusDesc(awardTemplateStatus.getDescription());
	    }
        return templateMasterData;
    }

    private TermType[] getTerms(AwardTemplate awardTemplate) {
        List<TermType> termTypes = new ArrayList<TermType>();
        for (AwardTemplateTerm awardTemplateTerm : awardTemplate.getAwardSponsorTerms()) {
            TermType termType = TermType.Factory.newInstance();
            setTermDetails(termType,awardTemplateTerm);
            termTypes.add(termType);
        }
        return termTypes.toArray(new TermType[0]);
    }

    private void setTermDetails(TermType termType,AwardTemplateTerm awardTemplateTerm) {
        awardTemplateTerm.refreshNonUpdateableReferences();
        SponsorTerm sponsorTerm = awardTemplateTerm.getSponsorTerm();
        if(sponsorTerm!=null){
           if(previousDescription.equals("")||!previousDescription.equals(awardTemplateTerm.getSponsorTerm().getSponsorTermType().getDescription())){
            termType.setDescription(awardTemplateTerm.getSponsorTerm().getSponsorTermType().getDescription());
            previousDescription=awardTemplateTerm.getSponsorTerm().getSponsorTermType().getDescription();
           }
            TermDetailsType termDetails = termType.addNewTermDetails();
            termDetails.setTermCode(Integer.parseInt(sponsorTerm.getSponsorTermCode()));
            termDetails.setTermDescription(sponsorTerm.getDescription());
        }
    }

    /*
	 * This method will set the values to report term types elements and finally
	 * returns the report term type.It iterates over the Award Template Report
	 * Term.
	 */
	private ReportTermType[] getReportTermTypes(AwardTemplate awardTemplate) {
		List<ReportTermType> reportTermTypes = new ArrayList<ReportTermType>();
		ReportTermType reportTermType = null;
		for (AwardTemplateReportTerm awardTemplateReportTerm : awardTemplate
				.getTemplateReportTerms()) {
			reportTermType = ReportTermType.Factory.newInstance();
			awardTemplateReportTerm.refreshNonUpdateableReferences();
			ReportClass reportClass = awardTemplateReportTerm.getReportClass();
			if (reportClass != null && reportClass.getDescription() != null) {
				reportTermType.setDescription(reportClass.getDescription());
			}
			reportTermType.setReportTermDetailsArray(getReportTermDetails(awardTemplateReportTerm));
			reportTermTypes.add(reportTermType);
		}
		return reportTermTypes.toArray(new ReportTermType[0]);
	}

	/*
	 * This method will set the values to report term details elements and
	 * finally return the array of the report term details type.It iterates over
	 * the
	 */
	private ReportTermDetailsType[] getReportTermDetails(AwardTemplateReportTerm awardTemplateReportTerm) {
		List<ReportTermDetailsType> reportTermDetailsTypes = new ArrayList<ReportTermDetailsType>();
		ReportTermDetailsType reportTermDetailsType = null;
		reportTermDetailsType = ReportTermDetailsType.Factory.newInstance();
		Date dueDate = awardTemplateReportTerm.getDueDate();
		if (dueDate != null) {
			reportTermDetailsType.setDueDate(dateTimeService.getCalendar(dueDate));
		}
		setFrequencyBaseDetails(awardTemplateReportTerm, reportTermDetailsType);
		setFrequencyDetails(awardTemplateReportTerm, reportTermDetailsType);
		setOspDistributionDetails(awardTemplateReportTerm,reportTermDetailsType);
		setReportClassDetails(awardTemplateReportTerm, reportTermDetailsType);
		setReportDetails(awardTemplateReportTerm, reportTermDetailsType);
		reportTermDetailsType.setMailCopiesArray(getMailCopies(awardTemplateReportTerm));
		reportTermDetailsTypes.add(reportTermDetailsType);
		return reportTermDetailsTypes.toArray(new ReportTermDetailsType[0]);
	}

	/*
	 * This method will set the values to report term details of report
	 * elements.
	 */
	private void setReportDetails(
			AwardTemplateReportTerm awardTemplateReportTerm,
			ReportTermDetailsType reportTermDetailsType) {
		Report report = awardTemplateReportTerm.getReport();
		String reportCode = report.getReportCode();
		String reportDescription = report.getDescription();
		if (reportCode != null) {
			reportTermDetailsType.setReportCode(Integer.valueOf(reportCode));
		}
		if (reportDescription != null) {
			reportTermDetailsType.setReportCodeDesc(reportDescription);
		}
	}

	/*
	 * This method will set the values to report term details of report class
	 * elements.
	 */
	private void setReportClassDetails(
			AwardTemplateReportTerm awardTemplateReportTerm,
			ReportTermDetailsType reportTermDetailsType) {
		ReportClass reportClass = awardTemplateReportTerm.getReportClass();
		String reportClassCode = reportClass.getReportClassCode();
		String reportClassDescription = reportClass.getDescription();
		if (reportClassCode != null) {
			reportTermDetailsType.setReportClassCode(Integer
					.valueOf(reportClassCode));
		}
		if (reportClassDescription != null) {
			reportTermDetailsType.setReportCodeDesc(reportClassDescription);
		}
	}

	/*
	 * This method will set the values to report term details of OSP
	 * distribution elements.
	 */
	private void setOspDistributionDetails(
			AwardTemplateReportTerm awardTemplateReportTerm,
			ReportTermDetailsType reportTermDetailsType) {
		String ospDistributionCode = awardTemplateReportTerm
				.getOspDistributionCode();
		if (ospDistributionCode != null) {
			reportTermDetailsType.setOSPDistributionCode(Integer
					.valueOf(ospDistributionCode));
		}
		Distribution distribution = awardTemplateReportTerm.getDistribution();
		String ospDescription = distribution.getDescription();
		if (ospDescription != null) {
			reportTermDetailsType.setOSPDistributionDesc(ospDescription);
		}
	}

	/*
	 * This method will set the values to report term details of frequency
	 * elements.
	 */
	private void setFrequencyDetails(
			AwardTemplateReportTerm awardTemplateReportTerm,
			ReportTermDetailsType reportTermDetailsType) {
		Frequency frequency = awardTemplateReportTerm.getFrequency();
		String frequencyCode = frequency.getFrequencyCode();
		String desription = frequency.getDescription();
		if (frequencyCode != null) {
			reportTermDetailsType.setFrequencyCode(Integer
					.valueOf(frequencyCode));
		}
		if (desription != null) {
			reportTermDetailsType.setFrequencyCodeDesc(desription);
		}
	}

	/*
	 * This method will set the values to report term details of frequency base
	 * elements.
	 */
	private void setFrequencyBaseDetails(
			AwardTemplateReportTerm awardTemplateReportTerm,
			ReportTermDetailsType reportTermDetailsType) {
		String frequencyBaseCode = awardTemplateReportTerm.getFrequencyBaseCode();
		if (frequencyBaseCode != null) {
			reportTermDetailsType.setFrequencyBaseCode(Integer
					.valueOf(frequencyBaseCode));
		}
		awardTemplateReportTerm.refreshNonUpdateableReferences();
		FrequencyBase frequencyBase = awardTemplateReportTerm.getFrequencyBase();
		if(frequencyBase!=null){
		String description = frequencyBase.getDescription();
    		if (description != null) {
    			reportTermDetailsType.setFrequencyBaseDesc(description);
    		}
		}
	}

	/*
	 * This method will set the values to mail copies elements and finally
	 * return the array of mail copies.It iterates over the award template
	 * report term recipient.
	 */
	private MailCopies[] getMailCopies(
			AwardTemplateReportTerm awardTemplateReportTerm) {
		List<MailCopies> mailCopiesList = new ArrayList<MailCopies>();
		MailCopies mailCopies = null;
		for (AwardTemplateReportTermRecipient awardTemplateReportTermRecipient : awardTemplateReportTerm
				.getAwardTemplateReportTermRecipients()) {
			mailCopies = MailCopies.Factory.newInstance();
			awardTemplateReportTermRecipient.refreshNonUpdateableReferences();
			org.kuali.kra.award.home.ContactType contactType = awardTemplateReportTermRecipient.getContactType();
			if(contactType!=null){
    			String contactTypeCode = contactType.getContactTypeCode();
    			String contactTypeDescription = contactType.getDescription();
    			if (contactTypeCode != null) {
    				mailCopies.setContactTypeCode(Integer.valueOf(contactTypeCode));
    			}
    			if (contactTypeDescription != null) {
    				mailCopies.setContactTypeDesc(contactTypeDescription);
    			}
			}
			Integer numberofmailCopies = awardTemplateReportTermRecipient.getNumberOfCopies();
			if (numberofmailCopies != null) {
				mailCopies.setNumberOfCopies(String.valueOf(numberofmailCopies));
			}
			Integer rolodexid = awardTemplateReportTermRecipient.getRolodexId();
			if (rolodexid != null) {
				mailCopies.setRolodexId(String.valueOf(rolodexid));
			}
			mailCopiesList.add(mailCopies);
		}
		return mailCopiesList.toArray(new MailCopies[0]);
	}

	/*
	 * This method will set the values to contact type elements and finally
	 * return the contact type array. From AwardTemplate get the list of
	 * AwardTemplateContact and iterates over it.
	 */
	private ContactType[] getContactType(AwardTemplate awardTemplate) {
		List<ContactType> contactTypes = new ArrayList<ContactType>();
		ContactType contactType = null;
		for (AwardTemplateContact awardTemplateContact : awardTemplate
				.getTemplateContacts()) {
			contactType = ContactType.Factory.newInstance();
			awardTemplateContact.refreshNonUpdateableReferences();
			org.kuali.kra.award.home.ContactType type = awardTemplateContact
					.getContactType();
			String contactTypeCode = null;
			String description =null;
			if (type != null) {
				contactTypeCode = type.getContactTypeCode();
				description= type.getDescription();
			}
			if (contactTypeCode != null) {
				contactType
						.setContactTypeCode(Integer.valueOf(contactTypeCode));
			}
			if (description != null) {
				contactType.setContactTypeDesc(description);
			}
			setRolodexDetails(contactType,awardTemplateContact);
			contactTypes.add(contactType);
		}
		return contactTypes.toArray(new ContactType[0]);
	}

	private void setRolodexDetails(ContactType contactType, AwardTemplateContact awardTemplateContact) {
	    if(awardTemplateContact.getRolodexId()!=null){
	        Rolodex rolodex = awardTemplateContact.getRolodex();
	        if(rolodex!=null){
	            RolodexDetailsType rolodexDetails = contactType.addNewRolodexDetails();
	            rolodexDetails.setAddress1(rolodex.getAddressLine1());
	            rolodexDetails.setAddress2(rolodex.getAddressLine2());
	            rolodexDetails.setAddress3(rolodex.getAddressLine3());
	            rolodexDetails.setCity(rolodex.getCity());
	            rolodexDetails.setComments(rolodex.getComments());
	            rolodexDetails.setCountryCode(rolodex.getCountryCode());
	            rolodexDetails.setCountryDescription(rolodex.getCountryCode());
	            rolodexDetails.setCounty(rolodex.getCounty());
	            rolodexDetails.setEmail(rolodex.getEmailAddress());
	            rolodexDetails.setFax(rolodex.getFaxNumber());
	            rolodexDetails.setFirstName(rolodex.getFirstName());
	            rolodexDetails.setLastName(rolodex.getLastName());
	            rolodexDetails.setMiddleName(rolodex.getMiddleName());
	            rolodexDetails.setOrganization(rolodex.getOrganization());
	            rolodexDetails.setOwnedByUnit(rolodex.getOwnedByUnit());
	            if(rolodex.getUnit()!=null){
	                rolodexDetails.setOwnedByUnitName(rolodex.getUnit().getUnitName());
	            }
	            rolodexDetails.setPhoneNumber(rolodex.getPhoneNumber());
	            rolodexDetails.setPostalCode(rolodex.getPostalCode());
	            rolodexDetails.setPrefix(rolodex.getPrefix());
	            rolodexDetails.setRolodexId(rolodex.getRolodexId().toString());
	            rolodexDetails.setSponsorCode(rolodex.getSponsorCode());
	            if(rolodex.getSponsor()!=null){
	                rolodexDetails.setSponsorName(rolodex.getSponsor().getSponsorName());
	            }
	            rolodexDetails.setStateCode(rolodex.getState());
	            rolodexDetails.setStateDescription(rolodex.getSponsorCode());
	            rolodexDetails.setSuffix(rolodex.getSuffix());
	            rolodexDetails.setTitle(rolodex.getTitle());
	        }
	        
	    }
    }

    /*
	 * This method will set the values to comment type elements and finally
	 * return the comment type array. From AwardTemplate get the list of
	 * AwardTemplateComment and iterates over it.
	 */
	private CommentType[] getCommentType(AwardTemplate awardTemplate) {
		List<CommentType> commentTypes = new ArrayList<CommentType>();
		List<AwardTemplateComment> templateComments = awardTemplate
				.getTemplateComments();
		CommentType commentType = null;
		ArrayList templateCommentList=new ArrayList();		
		HashMap<String,String> templateCommentHm=new HashMap<String,String>();
		for (AwardTemplateComment awardTemplateComment : templateComments) {		
            String commentTypeCode = awardTemplateComment.getCommentTypeCode();           
		    AwardTemplate template = awardTemplateComment.getTemplate();		  
		    String description = null;
            if (template != null) {               
                awardTemplateComment.refreshReferenceObject("commentType");
                description= awardTemplateComment.getCommentType().getDescription();   
                templateCommentList.add(description);
            }
            String comments = awardTemplateComment.getComments();          
            if(comments!=null && description!=null){
                templateCommentHm.put(description, comments);
            }           
            		    
		}
		 Collections.sort(templateCommentList);		
		 for (int templateComment=0;templateComment<templateCommentList.size();templateComment++){
             if(templateCommentHm.containsKey(templateCommentList.get(templateComment))==true){
                 commentType = CommentType.Factory.newInstance();
                 String comments=(String)templateCommentHm.get(templateCommentList.get(templateComment));
                 String description =templateCommentList.get(templateComment).toString();
                 commentType.setDescription(description);
                 commentType.setComments(comments);    
                
             }      
             commentTypes.add(commentType);           
        }

	        return commentTypes.toArray(new CommentType[0]);	
	}
		
	
	

	/*
	 * This method will set the values to school info attributes and finally
	 * returns SchoolInfoType XmlObject
	 */
	private SchoolInfoType getSchoolInfoType() {
		SchoolInfoType schoolInfoType = SchoolInfoType.Factory.newInstance();
		String schoolName = getAwardParameterValue(SCHOOL_NAME);
		String schoolAcronym = getAwardParameterValue(SCHOOL_ACRONYM);
		if (schoolName != null) {
			schoolInfoType.setSchoolName(schoolName);
		}
		if (schoolAcronym != null) {
			schoolInfoType.setAcronym(schoolAcronym);
		}
		return schoolInfoType;
	}

	public DateTimeService getDateTimeService() {
		return dateTimeService;
	}

	public void setDateTimeService(DateTimeService dateTimeService) {
		this.dateTimeService = dateTimeService;
	}

	private String getAwardParameterValue(String param) {
		String value = null;
		try {
			value = PrintingUtils.getParameterValue(param);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return value;
	}
}
