/*
 * Copyright 2005-2010 The Kuali Foundation
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

package org.kuali.kra.award.printing.xmlstream;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import noNamespace.CommentType;
import noNamespace.ContactType;
import noNamespace.ReportTermDetailsType;
import noNamespace.ReportTermType;
import noNamespace.SchoolInfoType;
import noNamespace.TemplateDocument;
import noNamespace.ReportTermDetailsType.MailCopies;
import noNamespace.TemplateDocument.Template;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xmlbeans.XmlObject;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardTemplate;
import org.kuali.kra.award.home.AwardTemplateComment;
import org.kuali.kra.award.home.AwardTemplateContact;
import org.kuali.kra.award.home.AwardTemplateReportTerm;
import org.kuali.kra.award.home.AwardTemplateReportTermRecipient;
import org.kuali.kra.award.home.Distribution;
import org.kuali.kra.award.paymentreports.Frequency;
import org.kuali.kra.award.paymentreports.FrequencyBase;
import org.kuali.kra.award.paymentreports.Report;
import org.kuali.kra.award.paymentreports.ReportClass;
import org.kuali.kra.award.printing.AwardPrintType;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.printing.util.PrintingUtils;
import org.kuali.kra.printing.xmlstream.XmlStream;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DateTimeService;

/**
 * This class generates XML that conforms with the XSD related to Award Template
 * Report. The data for XML is derived from {@link ResearchDocumentBase} and
 * {@link Map} of details passed to the class.
 * 
 * @author
 * 
 */
public class AwardTemplateXmlStream implements XmlStream {
	
	private static final Log LOG = LogFactory.getLog(AwardTemplateXmlStream.class);
	
	private BusinessObjectService businessObjectService = null;
	private DateTimeService dateTimeService = null;
	private static final String SCHOOL_NAME = "SCHOOL_NAME";
	private static final String SCHOOL_ACRONYM = "SCHOOL_ACRONYM";

	/**
	 * This method generates XML for Award template Report. It uses data passed
	 * in {@link ResearchDocumentBase} for populating the XML nodes. The XMl
	 * once generated is returned as {@link XmlObject}
	 * 
	 * @param printableBusinessObject
	 *            using which XML is generated
	 * @param reportParameters
	 *            parameters related to XML generation
	 * @return {@link XmlObject} representing the XML
	 */
	public Map<String, XmlObject> generateXmlStream(
			KraPersistableBusinessObjectBase printableBusinessObject, Map<String, Object> reportParameters) {
		Map<String, XmlObject> awardTemplateXmlStream = new HashMap<String, XmlObject>();
		Award award = (Award) printableBusinessObject;
		TemplateDocument templateDocument = TemplateDocument.Factory
				.newInstance();
		if (award.getAwardTemplate() != null) {
			templateDocument.setTemplate(getTemplate(award.getAwardTemplate()));
		}
		awardTemplateXmlStream.put(AwardPrintType.AWARD_TEMPLATE
				.getAwardPrintType(), templateDocument);
		return awardTemplateXmlStream;
	}

	/*
	 * This method will set the values to template elements and finally returns
	 * the template xml object.
	 */
	private Template getTemplate(AwardTemplate awardTemplate) {
		Template template = Template.Factory.newInstance();
		template.setSchoolInfo(getSchoolInfoType());
		template.setCommentArray(getCommentType(awardTemplate));
		template.setContactArray(getContactType(awardTemplate));
		template.setReportArray(getReportTermTypes(awardTemplate));
		return template;
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
			ReportClass reportClass = awardTemplateReportTerm.getReportClass();
			if (reportClass != null && reportClass.getDescription() != null) {
				reportTermType.setDescription(reportClass.getDescription());
			}
			reportTermType
					.setReportTermDetailsArray(getReportTermDetails(awardTemplateReportTerm));
		}
		return reportTermTypes.toArray(new ReportTermType[0]);
	}

	/*
	 * This method will set the values to report term details elements and
	 * finally return the array of the report term details type.It iterates over
	 * the
	 */
	private ReportTermDetailsType[] getReportTermDetails(
			AwardTemplateReportTerm awardTemplateReportTerm) {
		List<ReportTermDetailsType> reportTermDetailsTypes = new ArrayList<ReportTermDetailsType>();
		ReportTermDetailsType reportTermDetailsType = null;
		reportTermDetailsType = ReportTermDetailsType.Factory.newInstance();
		Date dueDate = awardTemplateReportTerm.getDueDate();
		if (dueDate != null) {
			reportTermDetailsType.setDueDate(dateTimeService
					.getCalendar(dueDate));
		}
		setFrequencyBaseDetails(awardTemplateReportTerm, reportTermDetailsType);
		setFrequencyDetails(awardTemplateReportTerm, reportTermDetailsType);
		setOspDistributionDetails(awardTemplateReportTerm,
				reportTermDetailsType);
		setReportClassDetails(awardTemplateReportTerm, reportTermDetailsType);
		setReportDetails(awardTemplateReportTerm, reportTermDetailsType);
		reportTermDetailsType
				.setMailCopiesArray(getMailCopies(awardTemplateReportTerm));
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
		String frequencyBaseCode = awardTemplateReportTerm
				.getFrequencyBaseCode();
		if (frequencyBaseCode != null) {
			reportTermDetailsType.setFrequencyBaseCode(Integer
					.valueOf(frequencyBaseCode));
		}
		FrequencyBase frequencyBase = awardTemplateReportTerm
				.getFrequencyBase();
		String description = frequencyBase.getDescription();
		if (description != null) {
			reportTermDetailsType.setFrequencyBaseDesc(description);
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
			org.kuali.kra.award.home.ContactType contactType = awardTemplateReportTermRecipient
					.getContactType();
			String contactTypeCode = contactType.getContactTypeCode();
			String contactTypeDescription = contactType.getDescription();
			if (contactTypeCode != null) {
				mailCopies.setContactTypeCode(Integer.valueOf(contactTypeCode));
			}
			if (contactTypeDescription != null) {
				mailCopies.setContactTypeDesc(contactTypeDescription);
			}
			Integer numberofmailCopies = awardTemplateReportTermRecipient
					.getNumberOfCopies();
			if (numberofmailCopies != null) {
				mailCopies
						.setNumberOfCopies(String.valueOf(numberofmailCopies));
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
			contactTypes.add(contactType);
		}
		return contactTypes.toArray(new ContactType[0]);
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
		for (AwardTemplateComment awardTemplateComment : templateComments) {
			commentType = CommentType.Factory.newInstance();
			String commentTypeCode = awardTemplateComment.getCommentTypeCode();
			if (commentTypeCode != null) {
				commentType.setCommentCode(Integer.valueOf(commentTypeCode)
						.intValue());
			}
			AwardTemplate template = awardTemplateComment.getTemplate();
			String description = null;
			if (template != null) {
				description = template.getDescription();
			}
			if (description != null) {
				commentType.setDescription(description);
			}
			String comments = awardTemplateComment.getComments();
			if (comments != null) {
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

	public BusinessObjectService getBusinessObjectService() {
		return businessObjectService;
	}

	public void setBusinessObjectService(
			BusinessObjectService businessObjectService) {
		this.businessObjectService = businessObjectService;
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
