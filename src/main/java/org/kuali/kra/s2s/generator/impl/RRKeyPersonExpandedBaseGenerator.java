/*
 * Copyright 2005-2010 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.s2s.generator.impl;

import gov.grants.apply.coeus.personProfile.PersonProfileListDocument.PersonProfileList;
import gov.grants.apply.coeus.personProfile.PersonProfileListDocument.PersonProfileList.ExtraKeyPerson;
import gov.grants.apply.coeus.personProfile.PersonProfileListDocument.PersonProfileList.ExtraKeyPerson.Address.Country.Enum;
import gov.grants.apply.system.attachmentsV10.AttachedFileDataType;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.xmlbeans.XmlObject;
import org.kuali.kra.award.home.ContactRole;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.printing.PrintingException;
import org.kuali.kra.printing.service.impl.PrintingServiceImpl;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.bo.NarrativeAttachment;
import org.kuali.kra.proposaldevelopment.bo.NarrativeType;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonBiography;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.service.NarrativeService;
import org.kuali.kra.s2s.generator.S2SBaseFormGenerator;
import org.kuali.kra.s2s.service.S2SUtilService;
import org.kuali.kra.s2s.util.S2SConstants;
import org.kuali.rice.kns.service.DateTimeService;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfDestination;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfOutline;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfWriter;

/**
 * This abstract class has methods that are common to all the versions of RRKeyPersonExpanded form.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public abstract class RRKeyPersonExpandedBaseGenerator extends S2SBaseFormGenerator {

	private static final Logger LOG = Logger.getLogger(PrintingServiceImpl.class);
	
    protected static final String OTHER = "Other (Specify)";
    protected static final String BIOSKETCH = "BIOSKETCH";
    protected static final String BIOSKETCH_TYPE = "1";
    protected static final String CURRENT_PENDING_TYPE = "2";
    public static final int BIOSKETCH_DOC_TYPE = 16;
    public static final int CURRENTPENDING_DOC_TYPE = 17;
    protected static final int PROFILE_TYPE = 18;
    protected static final String KEYPERSON = "KP";
    protected static final String CO_INVESTIGATOR = "COI";
    protected static final String NIH_CO_INVESTIGATOR = "Co-Investigator";
    protected static final int MAX_KEY_PERSON_COUNT = 40;
    protected S2SUtilService s2sUtilService;
    
    protected List<ProposalPerson> extraPersons = null;
    protected String pIPersonOrRolodexId = null;
    protected static final int DIRECTORY_TITLE_MAX_LENGTH = 45;
    protected static final int DEPARTMENT_NAME_MAX_LENGTH = 30;
    protected static final int ROLE_DESCRIPTION_MAX_LENGTH = 40;
    protected static final String NIH = "NIH";
	private int WHITESPACE_LENGTH_76 = 76;
	private int WHITESPACE_LENGTH_60 = 60;
	private static final String COMMENT = "Auto generated document for ";
	private static final String BIOSKETCH_COMMENT = "BIOSKETCH";
	private static final String CURRENT_PENDING_COMMENT = "CURRENTPENDING";
	protected static final String PROFILE_COMMENT = "PROFILE";
	protected static final String ADDITIONALKEYPERSONPROFILES_XSL = "/org/kuali/kra/s2s/stylesheet/additionalkeypersonprofiles.xsl";
    /**
     * 
     * Constructs a RRKeyPersonExpandedBaseGenerator.java.
     */
    public RRKeyPersonExpandedBaseGenerator() {
        s2sUtilService = KraServiceLocator.getService(S2SUtilService.class);
    }

    protected void addKeyPersonAttachmentsToProposal(){
    	getKeyPersonAttachments();
    	getKeyPersonInfoAttachment();
    }
    private void getKeyPersonInfoAttachment() {
    	XmlObject xyz = getKeypersonProfileObject();
    }
    protected abstract XmlObject getKeypersonProfileObject();

    
    private void getKeyPersonAttachments() {
    	List<String> bioSketchBookMarks = new ArrayList<String>();
    	List<String> curPendBookMarks = new ArrayList<String>();
    	List<byte[]> bioSketchDataList = new ArrayList<byte[]>();
    	List<byte[]> curPendDataList = new ArrayList<byte[]>();
    	
    	for(ProposalPerson proposalPerson : extraPersons){
    		setBookMarkAndData(bioSketchBookMarks, bioSketchDataList,
					proposalPerson,BIOSKETCH_TYPE);
    		setBookMarkAndData(curPendBookMarks, curPendDataList,
					proposalPerson,CURRENT_PENDING_TYPE);
    	}
    	byte[] bioSketchData = null;
    	byte[] curPendData = null;
    	try {
			bioSketchData = mergePdfBytes(bioSketchDataList, bioSketchBookMarks);
			curPendData = mergePdfBytes(curPendDataList, curPendBookMarks);
			String fileName = null;
			if(bioSketchData !=null && bioSketchData.length > 0){
				fileName = pdDoc.getDevelopmentProposal().getProposalNumber() +"_"+BIOSKETCH_COMMENT;
				saveNarrative(bioSketchData,""+BIOSKETCH_DOC_TYPE, fileName, COMMENT + BIOSKETCH_COMMENT);
			}
			if(curPendData !=null && curPendData.length > 0){
				fileName = pdDoc.getDevelopmentProposal().getProposalNumber() +"_"+BIOSKETCH_COMMENT;
				saveNarrative(curPendData,""+CURRENTPENDING_DOC_TYPE, fileName, COMMENT + CURRENT_PENDING_COMMENT);
			}
		} catch (PrintingException e) {
			e.printStackTrace();
		}
    }

	private void setBookMarkAndData(List<String> bookMarksList,
			List<byte[]> dataList, ProposalPerson proposalPerson,String docType) {
		String personId=null;
		if (proposalPerson.getPersonId() != null && proposalPerson.getPersonId().length()>0){
			personId = proposalPerson.getPersonId();
		}else{
			personId = ""+proposalPerson.getRolodexId();
		}
		
		for(ProposalPersonBiography personBiography : getPernonnelAttachments(pdDoc,proposalPerson,docType)){
			personBiography.refreshReferenceObject("personnelAttachmentList");
			byte[] content = personBiography.getPersonnelAttachmentList().get(0).getContent();
			if(content!=null && content.length>0){
				dataList.add(content);
				bookMarksList.add(personId);
			}
		}
	}
    
    private List<ProposalPersonBiography> getPernonnelAttachments(ProposalDevelopmentDocument pdDoc, ProposalPerson proposalPerson,
            String documentType) {
        List<ProposalPersonBiography> result = new ArrayList<ProposalPersonBiography> ();
        for (ProposalPersonBiography proposalPersonBiography : pdDoc.getDevelopmentProposal().getPropPersonBios()) {
        	String personId = proposalPerson.getPersonId();
        	Integer rolodexId = proposalPerson.getRolodexId();
            if (personId != null && proposalPersonBiography.getPersonId() != null
                    && proposalPersonBiography.getPersonId().equals(personId)
                    && documentType.equals(proposalPersonBiography.getDocumentTypeCode())) {
            	result.add(proposalPersonBiography);
            }
            else if (rolodexId != null && proposalPersonBiography.getRolodexId() != null
                    && proposalPersonBiography.getRolodexId().toString().equals(rolodexId.toString())
                    && documentType.equals(proposalPersonBiography.getDocumentTypeCode())) {
            	result.add(proposalPersonBiography);
            }
        }
        return result;
    }
	/**
	 * @param pdfBytesList
	 *            List containing the PDF data bytes
	 * @param bookmarksList
	 *            List of bookmarks corresponding to the PDF bytes.
	 * @return
	 * @throws PrintingException
	 */
	private byte[] mergePdfBytes(List<byte[]> pdfBytesList,
			List<String> bookmarksList) throws PrintingException {
		Document document = null;
		PdfWriter writer = null;
		ByteArrayOutputStream mergedPdfReport = new ByteArrayOutputStream();
		int totalNumOfPages = 0;
		PdfReader[] pdfReaderArr = new PdfReader[pdfBytesList.size()];
		int pdfReaderCount = 0;
		for (byte[] fileBytes : pdfBytesList) {
			LOG.debug("File Size " + fileBytes.length +" For " +  bookmarksList.get(pdfReaderCount));
			PdfReader reader = null;
			try {
				reader = new PdfReader(fileBytes);
				pdfReaderArr[pdfReaderCount] = reader;
				pdfReaderCount = pdfReaderCount + 1;
				totalNumOfPages += reader.getNumberOfPages();
			} catch (IOException e) {
				LOG.error(e.getMessage(), e);
				throw new PrintingException(e.getMessage(), e);
			}
		}
		DateTimeService dateTimeService = KraServiceLocator.getService(DateTimeService.class);
		Calendar calendar = dateTimeService.getCurrentCalendar();
		DateFormat dateFormat = new SimpleDateFormat("M/d/yy h:mm a");
		String dateString = dateFormat.format(calendar.getTime());
		StringBuilder footerPhStr = new StringBuilder();
		footerPhStr.append(" of ");
		footerPhStr.append(totalNumOfPages);
		footerPhStr.append(getWhitespaceString(WHITESPACE_LENGTH_76));
		footerPhStr.append(getWhitespaceString(WHITESPACE_LENGTH_76));
		footerPhStr.append(getWhitespaceString(WHITESPACE_LENGTH_60));
		footerPhStr.append(dateString);
		Font font = FontFactory.getFont(FontFactory.TIMES, 8, Font.NORMAL,
				Color.BLACK);
		Phrase beforePhrase = new Phrase("Page ", font);
		Phrase afterPhrase = new Phrase(footerPhStr.toString(), font);
		HeaderFooter footer = new HeaderFooter(beforePhrase, afterPhrase);
		footer.setAlignment(Element.ALIGN_BASELINE);
		footer.setBorderWidth(0f);
		for (int count = 0; count < pdfReaderArr.length; count++) {
			PdfReader reader = pdfReaderArr[count];
			int nop;
			if (reader == null) {
				LOG.debug("Empty PDF byetes found for " + bookmarksList.get(count));
				continue;
			} else {
				nop = reader.getNumberOfPages();
			}

			if (count == 0) {
				document = nop > 0 ? new com.lowagie.text.Document(reader
						.getPageSizeWithRotation(1))
						: new com.lowagie.text.Document();
				try {
					writer = PdfWriter.getInstance(document, mergedPdfReport);
				} catch (DocumentException e) {
					LOG.error(e.getMessage(), e);
					throw new PrintingException(e.getMessage(), e);
				}
				document.setFooter(footer);
				document.open();
			}
			PdfContentByte cb = writer.getDirectContent();
			int pageCount = 0;
			while (pageCount < nop) {
				document.setPageSize(reader.getPageSize(++pageCount));
				document.newPage();
				document.setFooter(footer);
				PdfImportedPage page = writer
						.getImportedPage(reader, pageCount);

				cb.addTemplate(page, 1, 0, 0, 1, 0, 0);

				PdfOutline root = cb.getRootOutline();
				if (pageCount == 1) {
					String pageName = bookmarksList.get(count);
					cb.addOutline(new PdfOutline(root, new PdfDestination(
							PdfDestination.FITH), pageName), pageName);
				}
			}
		}
		if (document != null) {
			try {
				document.close();
				return mergedPdfReport.toByteArray();
			} catch (Exception e) {
				LOG
						.error(
								"Exception occured because the generated PDF document has no pages",
								e);
			}
		}
		return null;
	}
	private String getWhitespaceString(int length) {
		StringBuffer sb = new StringBuffer();
		char[] whiteSpace = new char[length];
		Arrays.fill(whiteSpace, Constants.SPACE_SEPARATOR);
		sb.append(whiteSpace);
		return sb.toString();
	}
	protected PersonProfileList.ExtraKeyPerson[] getExtraKeyPersons() {
		List<PersonProfileList.ExtraKeyPerson> extraPersonList = new ArrayList<PersonProfileList.ExtraKeyPerson>();

		for (ProposalPerson proposalPerson : extraPersons) {

			PersonProfileList.ExtraKeyPerson extraPerson = PersonProfileList.ExtraKeyPerson.Factory.newInstance();

			extraPerson.setName(getExtraPersonName(proposalPerson));
			extraPerson.setAddress(getExtraPersonAddress(proposalPerson));
			if (proposalPerson.getPrimaryTitle() != null
					&& proposalPerson.getPrimaryTitle().length() > 45)
				extraPerson.setTitle(proposalPerson.getPrimaryTitle().substring(0,
						45));
			else{
				extraPerson.setTitle(proposalPerson.getPrimaryTitle());
			}

			if (proposalPerson.getRole() != null){
				if (ContactRole.PI_CODE.equals(proposalPerson.getProjectRole())){
					extraPerson.setProjectRole(gov.grants.apply.coeus.personProfile.PersonProfileListDocument.PersonProfileList.ExtraKeyPerson.ProjectRole.PD_PI);
				}
				else if (ContactRole.COI_CODE.equals(proposalPerson.getRole())) {
					if (isSponsorNIH(pdDoc)) {
						extraPerson.setProjectRole(gov.grants.apply.coeus.personProfile.PersonProfileListDocument.PersonProfileList.ExtraKeyPerson.ProjectRole.OTHER_SPECIFY);
						extraPerson.setOtherProjectRoleCategory(NIH_CO_INVESTIGATOR);
					} else{
						extraPerson.setProjectRole(gov.grants.apply.coeus.personProfile.PersonProfileListDocument.PersonProfileList.ExtraKeyPerson.ProjectRole.CO_PD_PI);
					}
				} else{	
					String otherRole = "";
					if (proposalPerson.getProjectRole() != null
							&& proposalPerson.getProjectRole().length() > 40) {
						otherRole = proposalPerson.getProjectRole().substring(0, 40);
					} else {
						otherRole = proposalPerson.getProjectRole();
					}
					extraPerson.setOtherProjectRoleCategory(otherRole);
					extraPerson.setProjectRole(gov.grants.apply.coeus.personProfile.PersonProfileListDocument.PersonProfileList.ExtraKeyPerson.ProjectRole.OTHER_SPECIFY);
				}
			}
			
			if (proposalPerson.getEraCommonsUserName() != null){
				extraPerson.setCredential(proposalPerson.getEraCommonsUserName());
			}
			setDepartmentName(extraPerson,proposalPerson);
			setDivisionName(extraPerson,proposalPerson);
			if (proposalPerson.getEmailAddress() != null){
				extraPerson.setEmail(proposalPerson.getEmailAddress());
			}
			if (proposalPerson.getFaxNumber() != null){
				extraPerson.setFax(proposalPerson.getFaxNumber());
			}
			if (proposalPerson.getUnit() != null && proposalPerson.getOrganization() != null){
				extraPerson.setOrganizationName(proposalPerson.getOrganization());
			}
			if (proposalPerson.getPhoneNumber() != null)
				extraPerson.setPhone(proposalPerson.getPhoneNumber());

			// degree type and year added for version 1.2 - case 4337
			if (proposalPerson.getDegree() != null){
				extraPerson.setDegreeType(proposalPerson.getDegree());
			}
			if (proposalPerson.getYearGraduated() != null){
				extraPerson.setDegreeYear(proposalPerson.getYearGraduated());
			}
			AttachedFileDataType bioSketchAttachment = getPernonnelAttachments(pdDoc,proposalPerson.getPersonId(),proposalPerson.getRolodexId(),"16");
			if (bioSketchAttachment != null) {
				extraPerson.setBioSketchAttached(gov.grants.apply.coeus.personProfile.PersonProfileListDocument.PersonProfileList.ExtraKeyPerson.BioSketchAttached.YES);
			}

			AttachedFileDataType curPendingAttachment = getPernonnelAttachments(pdDoc,proposalPerson.getPersonId(),proposalPerson.getRolodexId(),"16");
			if (curPendingAttachment != null) {
				extraPerson.setSupportsAttached(gov.grants.apply.coeus.personProfile.PersonProfileListDocument.PersonProfileList.ExtraKeyPerson.SupportsAttached.YES);
			}
			extraPersonList.add(extraPerson);
		}
		return extraPersonList.toArray(new PersonProfileList.ExtraKeyPerson[0]);
	}
	private PersonProfileList.ExtraKeyPerson.Address getExtraPersonAddress(ProposalPerson proposalPerson) {
		PersonProfileList.ExtraKeyPerson.Address address = PersonProfileList.ExtraKeyPerson.Address.Factory.newInstance();
		if (proposalPerson.getAddressLine1() != null) {
			if (proposalPerson.getAddressLine1().length() > 55)
				address.setStreet1(proposalPerson.getAddressLine1().substring(
						0, 55));
			else
				address.setStreet1(proposalPerson.getAddressLine1());
		}
		if (proposalPerson.getAddressLine2() != null) {
			if (proposalPerson.getAddressLine2().length() > 55)
				address.setStreet2(proposalPerson.getAddressLine2().substring(
						0, 55));
			else
				address.setStreet2(proposalPerson.getAddressLine2());
		}
		if (proposalPerson.getCity() != null)
			address.setCity(proposalPerson.getCity());
		if (proposalPerson.getCounty() != null)
			address.setCounty(proposalPerson.getCounty());

		
		if (proposalPerson.getPostalCode() != null) {
			address.setZipCode(proposalPerson.getPostalCode());
		}

		if (proposalPerson.getCountryCode() != null) {
			PersonProfileList.ExtraKeyPerson.Address.Country.Enum county = Enum
					.forString(proposalPerson.getCountryCode());
			address.setCountry(county);
		}
		if (proposalPerson.getState()!= null) {
			address.setState(proposalPerson.getState());
		}
		return address;
	}

	private PersonProfileList.ExtraKeyPerson.Name getExtraPersonName(ProposalPerson proposalPerson) {
		PersonProfileList.ExtraKeyPerson.Name name = PersonProfileList.ExtraKeyPerson.Name.Factory.newInstance();
		if (proposalPerson.getFirstName() != null)
			name.setFirstName(proposalPerson.getFirstName());
		if (proposalPerson.getMiddleName() != null)
			name.setMiddleName(proposalPerson.getMiddleName());
		if (proposalPerson.getLastName() != null)
			name.setLastName(proposalPerson.getLastName());
		return name;
	}
	private void setDivisionName(ExtraKeyPerson extraPerson,
			ProposalPerson proposalPerson) {
		String divisionName = "";// s2sTxnBean.fn_get_division(homeUnit);
		if (divisionName != null && divisionName.length() > 29) {
			divisionName = divisionName.substring(0, 29);
		}
		extraPerson.setDivisionName(divisionName);
	}

	private void setDepartmentName(ExtraKeyPerson extraPerson,
			ProposalPerson proposalPerson) {
		String departmentName = "";//proposalPerson.getUnitName();
		if (departmentName != null && departmentName.length() > 30) {
			departmentName = departmentName.substring(0, 29);
		}
		if (departmentName != null)
			extraPerson.setDepartmentName(departmentName);
		
	}

	protected void saveNarrative(byte[] attachment,String narrativeTypeCode,String fileName, String comment) {
		Narrative narrative = null;
		for(Narrative savedNarrative:pdDoc.getDevelopmentProposal().getNarratives()){
			if(savedNarrative.getNarrativeTypeCode().equals(narrativeTypeCode)){
				narrative=savedNarrative;
				break;
			}
		}
		if(narrative==null){
			narrative=new Narrative();
			narrative.setModuleStatusCode("C");
			narrative.setNarrativeTypeCode(narrativeTypeCode);
			narrative.setComments(comment);
			NarrativeType narrativeType = new NarrativeType();
			narrativeType.setDescription(comment);
			narrativeType.setNarrativeTypeCode(narrativeTypeCode);
			narrative.setNarrativeType(narrativeType);
			NarrativeAttachment narrativeAttachment = new NarrativeAttachment();
			narrativeAttachment.setContentType(S2SConstants.CONTENT_TYPE_OCTET_STREAM);
			narrativeAttachment.setNarrativeData(attachment);
			narrativeAttachment.setFileName(fileName);
			narrative.setFileName(narrativeAttachment.getFileName());
			narrative.getNarrativeAttachmentList().add(narrativeAttachment);
			KraServiceLocator.getService(NarrativeService.class).addNarrative(pdDoc, narrative);
		}else{
			narrative.refreshReferenceObject("narrativeAttachmentList");
			narrative.getNarrativeAttachmentList().get(0).setNarrativeData(attachment);
			KraServiceLocator.getService(NarrativeService.class).replaceAttachment(narrative);
		}

	}
}
