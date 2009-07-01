/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra.s2s.service.impl;

import gov.grants.apply.system.metaGrantApplication.GrantApplicationDocument;
import gov.grants.apply.system.metaGrantApplication.GrantApplicationDocument.GrantApplication.Forms;

import java.awt.Color;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.Driver;
import org.apache.log4j.Logger;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.kuali.kra.bo.Parameter;
import org.kuali.kra.bo.SponsorFormTemplate;
import org.kuali.kra.bo.SponsorFormTemplateList;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.proposaldevelopment.bo.AttachmentDataSource;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.s2s.S2SException;
import org.kuali.kra.s2s.bo.S2sAppAttachments;
import org.kuali.kra.s2s.bo.S2sAppSubmission;
import org.kuali.kra.s2s.bo.S2sOppForms;
import org.kuali.kra.s2s.formmapping.FormMappingInfo;
import org.kuali.kra.s2s.formmapping.FormMappingLoader;
import org.kuali.kra.s2s.generator.S2SFormGenerator;
import org.kuali.kra.s2s.generator.S2SGeneratorNotFoundException;
import org.kuali.kra.s2s.generator.bo.AttachmentData;
import org.kuali.kra.s2s.service.PrintService;
import org.kuali.kra.s2s.service.S2SFormGeneratorService;
import org.kuali.kra.s2s.service.S2SValidatorService;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.kns.util.AuditError;
import org.kuali.rice.kns.util.GlobalVariables;
import org.w3c.dom.Document;

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
 * This class is implementation of PrintService. It provides the functionality to generate PDF for all forms along with their
 * attachments
 */
public class PrintServiceImpl implements PrintService {
    private static final Logger LOG = Logger.getLogger(PrintServiceImpl.class);

    private BusinessObjectService businessObjectService;
    private S2SFormGeneratorService s2SFormGeneratorService;
    private S2SValidatorService s2SValidatorService;
    private ArrayList<String> bookmarksList = null;
    private static final String NARRATIVE_ATTACHMENT_LIST = "narrativeAttachmentList";
    private static final String KEY_PROPOSAL_NUMBER = "PROPOSAL_NUMBER";
    private static final String KEY_SPONSOR_CODE = "SPONSOR_CODE";
    private static final String KEY_PACKAGE_NUMBER = "PACKAGE_NUMBER";
    private static final String KEY_PAGE_NUMBER = "PAGE_NUMBER";
    private static final String KEY_PAGE_DATA = "PAGE_DATA";
    private static final String KEY_PRINT_PROPOSAL = "PRINT_PROPOSAL";
    private static final String KEY_PARAMETER = "parameter";
    private static final String SPONSOR_CODE_DB_KEY = "sponsorCode";
    private static final String PAGE_NUMBER_DB_KEY = "pageNumber";


    /**
     * Prints the proposal sponsor forms by passing the given proposal information to {@link ProposalPrintReader}
     * 
     * @param proposalNumber proposal number.
     * @param sponsorFormTemplates list of SponsorFormTemplate.
     * @return byte array of forms corresponding to the proposal number and SponsorFormTemplate objects.
     * @throws S2SException
     * @see org.kuali.kra.s2s.service.PrintService#printProposalSponsorForms(java.lang.String, java.util.List)
     */
    public byte[] printProposalSponsorForms(String proposalNumber, List<SponsorFormTemplate> sponsorFormTemplates)
            throws S2SException {
//        List<Map<String, Object>> listData = new ArrayList<Map<String, Object>>();
//        for (SponsorFormTemplate sponsorFormTemplate : sponsorFormTemplates) {
//            SponsorTemplateBean coeusSponsorTemplate = new SponsorTemplateBean();
//            try {
//                BeanUtils.copyProperties(coeusSponsorTemplate, sponsorFormTemplate);
//            }
//            catch (IllegalAccessException e) {
//                LOG.error(e.getMessage(), e);
//                throw new S2SException(e);
//            }
//            catch (InvocationTargetException e) {
//                LOG.error(e.getMessage(), e);
//                throw new S2SException(e);
//            }
//            listData.add(getproposalSponsorMap(proposalNumber, sponsorFormTemplate, coeusSponsorTemplate));
//        }
//        Map<String, List<Map<String, Object>>> map = new HashMap<String, List<Map<String, Object>>>();
//        map.put(KEY_PRINT_PROPOSAL, listData);
//        ProposalPrintReader proposalPrintReader = new ProposalPrintReader();
//        try {
//            return proposalPrintReader.read(map).getDocumentData();
//        }
//        catch (CoeusException e) {
//            LOG.error(e.getMessage(), e);
//            return null;
//        }
        throw new RuntimeException("Unsupported functionality");
    }

    /**
     * 
     * This method populates and returns map of proposal sponsor information
     * 
     * @param proposalNumber proposal numbert for which sponsor map is required
     * @param sponsorFormTemplate template of the sponsor form
     * @param coeusSponsorTemplate {@link SponsorTemplateBean}
     * 
     * @return {@link Map} containing the properties related to sponsor form for the proposal
     */
//    private Map<String, Object> getproposalSponsorMap(String proposalNumber, SponsorFormTemplate sponsorFormTemplate,
//            SponsorTemplateBean coeusSponsorTemplate) {
//        Map<String, Object> htData = new HashMap<String, Object>();
//        htData.put(KEY_PROPOSAL_NUMBER, proposalNumber);
//        htData.put(KEY_SPONSOR_CODE, sponsorFormTemplate.getSponsorCode());
//        htData.put(KEY_PACKAGE_NUMBER, sponsorFormTemplate.getPackageNumber());
//        htData.put(KEY_PAGE_NUMBER, sponsorFormTemplate.getPageNumber());
//        htData.put(KEY_PAGE_DATA, coeusSponsorTemplate);
//        return htData;
//    }

    /**
     * 
     * This method is to get templates for generic sponsor code.
     * 
     * @param sponsorFormTemplates list of SponsorFormTemplateList.
     * @param sponsorCode code for the sponsor.
     */
    public void populateSponsorForms(List<SponsorFormTemplateList> sponsorFormTemplates, String sponsorCode) {
        // check if sponsor forms isEmpty
        if (!sponsorFormTemplates.isEmpty()) {
            // if exists - check if sponsor code has changed
            if (!sponsorCode.equalsIgnoreCase(sponsorFormTemplates.get(0).getSponsorCode())) {
                sponsorFormTemplates.clear();
            }
        }

        if (sponsorFormTemplates.isEmpty()) {
            // fetch all templates for proposal sponsor code
            String genericSponsorCode = null;
            Collection<SponsorFormTemplateList> clsponsorFormTemplates = getSponsorTemplatesList(sponsorCode);
            sponsorFormTemplates.addAll(clsponsorFormTemplates);
            // fetch all templates for generic sponsor code
            Map<String, String> parameterMap = new HashMap<String, String>();
            parameterMap.put(KEY_PARAMETER, Constants.GENERIC_SPONSOR_CODE);
            Collection<Parameter> parameters = getBusinessObjectService().findMatching(Parameter.class, parameterMap);
            for (Parameter parameter : parameters) {
                genericSponsorCode = parameter.getValue();
            }
            clsponsorFormTemplates = getSponsorTemplatesList(genericSponsorCode);
            sponsorFormTemplates.addAll(clsponsorFormTemplates);
        }
        else {
            resetSelectedFormList(sponsorFormTemplates);
        }
        Collections.sort(sponsorFormTemplates);
    }


    /**
     * 
     * This method is to reset the selected form list.
     * 
     * @param sponsorFormTemplates list of SponsorFormTemplateList.
     */
    private void resetSelectedFormList(List<SponsorFormTemplateList> sponsorFormTemplates) {
        for (SponsorFormTemplateList sponsorFormTemplateList : sponsorFormTemplates) {
            sponsorFormTemplateList.setSelectToPrint(false);
        }
    }

    /**
     * This method gets the sponsor form template from the given sponsor form template list
     * 
     * @param sponsorFormTemplateLists - list of sponsor form template list
     * @return list of sponsor form template
     * @see org.kuali.kra.s2s.service.PrintService#getSponsorFormTemplates(java.util.List)
     */
    public List<SponsorFormTemplate> getSponsorFormTemplates(List<SponsorFormTemplateList> sponsorFormTemplateLists) {
        List<SponsorFormTemplate> sponsorFormTemplates = new ArrayList<SponsorFormTemplate>();
        Set<String> sponsorCodes = new HashSet<String>();
        Set<String> formKeys = new HashSet<String>();
        for (SponsorFormTemplateList sponsorFormTemplateList : sponsorFormTemplateLists) {
            if (sponsorFormTemplateList.getSelectToPrint()) {
                if (!sponsorCodes.contains(sponsorFormTemplateList.getSponsorCode())) {
                    sponsorCodes.add(sponsorFormTemplateList.getSponsorCode());
                }
            }
            else {
                StringBuilder fKeys = new StringBuilder();
                fKeys.append(sponsorFormTemplateList.getSponsorCode());
                fKeys.append(sponsorFormTemplateList.getPackageNumber().toString());
                fKeys.append(sponsorFormTemplateList.getPageNumber().toString());
                if (!formKeys.contains(fKeys.toString())) {
                    formKeys.add(fKeys.toString());
                }
            }
        }

        String sponsorCode = null;
        Iterator<String> iter = sponsorCodes.iterator();
        // fetch all the templates for applicable sponsor codes
        while (iter.hasNext()) {
            sponsorCode = iter.next();
            Collection<SponsorFormTemplate> clsponsorFormTemplates = getSponsorTemplates(sponsorCode);
            sponsorFormTemplates.addAll(clsponsorFormTemplates);
            iter.remove();
        }

        // verify selected package
        List<SponsorFormTemplate> printFormTemplates = new ArrayList<SponsorFormTemplate>();
        HashMap<String, SponsorFormTemplate> formTemplates = new HashMap<String, SponsorFormTemplate>();
        for (SponsorFormTemplate sponsorFormTemplate : sponsorFormTemplates) {
            StringBuilder templateKey = new StringBuilder();
            templateKey.append(sponsorFormTemplate.getSponsorCode());
            templateKey.append(sponsorFormTemplate.getPackageNumber().toString());
            templateKey.append(sponsorFormTemplate.getPageNumber().toString());
            formTemplates.put(templateKey.toString(), sponsorFormTemplate);
        }
        Iterator<String> keyIter = formKeys.iterator();
        while (keyIter.hasNext()) {
            String formKey = keyIter.next();
            formTemplates.remove(formKey);
            keyIter.remove();
        }
        printFormTemplates.addAll(formTemplates.values());
        Collections.sort(printFormTemplates);
        resetSelectedFormList(sponsorFormTemplateLists);
        return printFormTemplates;
    }

    /**
     * 
     * This method is used to get the Sponsor template for the given sponsor code.
     * 
     * @param sponsorCode code number of the sponsor.
     * @return Collection<SponsorFormTemplate> collection of SponsorFormTemplate for the given sponsor code.
     */
    private Collection<SponsorFormTemplate> getSponsorTemplates(String sponsorCode) {
        Map<String, String> sponsorCodeMap = new HashMap<String, String>();        
        sponsorCodeMap.put(SPONSOR_CODE_DB_KEY, sponsorCode);
        Collection<SponsorFormTemplate> sponsorFormTemplates = getBusinessObjectService().findMatchingOrderBy(
                SponsorFormTemplate.class, sponsorCodeMap, PAGE_NUMBER_DB_KEY, false);
        return sponsorFormTemplates;
    }

    /**
     * 
     * This method is used for the printing of forms in PDF format. It generates PDF forms and puts it into
     * {@link AttachmentDataSource}
     * 
     * @param pdDoc(ProposalDevelopmentDocument)
     * @return {@link AttachmentDataSource} which contains all information related to the generated PDF
     * @throws S2SException
     * 
     * @see org.kuali.kra.s2s.service.PrintService#printForm(org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument)
     */
    public org.kuali.kra.proposaldevelopment.bo.AttachmentDataSource printForm(ProposalDevelopmentDocument pdDoc)
            throws S2SException {
        GrantsGovAttachmentData grantsGovPdfData = new GrantsGovAttachmentData();
        ByteArrayOutputStream[] pdfArray = null;
        S2sAppSubmission s2sAppSubmission = getLatestS2SAppSubmission(pdDoc);
        if (s2sAppSubmission != null && s2sAppSubmission.getGgTrackingId() != null) {
            pdfArray = getSubmittedPDFStream(pdDoc);
        }
        else {
            pdfArray = getPDFStream(pdDoc);
        }
        if (pdfArray == null) {
            return null;
        }
        String[] bookmarks = new String[0];
        bookmarks = bookmarksList.toArray(bookmarks);
        grantsGovPdfData.setContent(mergePdfBytes(pdfArray, bookmarks));
        StringBuilder fileName = new StringBuilder();
        fileName.append(pdDoc.getDocumentNumber());
        fileName.append(pdDoc.getProgramAnnouncementNumber());
        fileName.append(Constants.PDF_FILE_EXTENSION);
        grantsGovPdfData.setFileName(fileName.toString());
        grantsGovPdfData.setContentType(Constants.PDF_REPORT_CONTENT_TYPE);
        return grantsGovPdfData;
    }

    /**
     * 
     * This method is used to get the Sponsor template list for the given sponsor code.
     * 
     * @param sponsorCode code number of the sponsor.
     * @return Collection<SponsorFormTemplateList> collection of SponsorFormTemplateList for the given sponsor code.
     */
    private Collection<SponsorFormTemplateList> getSponsorTemplatesList(String sponsorCode) {
        Map<String, String> sponsorCodeMap = new HashMap<String, String>();
        sponsorCodeMap.put(SPONSOR_CODE_DB_KEY, sponsorCode);
        Collection<SponsorFormTemplateList> sponsorFormTemplates = getBusinessObjectService().findMatching(
                SponsorFormTemplateList.class, sponsorCodeMap);
        return sponsorFormTemplates;
    }

    /**
     * Gets the businessObjectService attribute.
     * 
     * @return Returns the businessObjectService.
     */
    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    /**
     * Sets the businessObjectService attribute value.
     * 
     * @param businessObjectService The businessObjectService to set.
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    /**
     * 
     * This method is to write the submitted application data to a pdfStream
     * 
     * @param pdDoc Proposal Development Document.
     * @return ByteArrayOutputStream[] of the submitted application data.
     * @throws S2SException
     */
    private ByteArrayOutputStream[] getSubmittedPDFStream(ProposalDevelopmentDocument pdDoc) throws S2SException {
        ArrayList<ByteArrayOutputStream> pdfBaosList = new ArrayList<ByteArrayOutputStream>();
        bookmarksList = new ArrayList<String>();
        byte[] formPdfBytes = null;
        ByteArrayOutputStream[] pdfArray = null;
        GrantApplicationDocument submittedXml;
        try {
            submittedXml = GrantApplicationDocument.Factory.parse(getLatestS2SAppSubmission(pdDoc).getS2sApplication().get(0)
                    .getApplication());
        }
        catch (XmlException e) {
            LOG.error(e.getMessage(), e);
            throw new S2SException(e);
        }
        FormMappingInfo info = null;
        S2SFormGenerator s2sFormGenerator = null;
        List<String> errors = new ArrayList<String>();
        List<String> sortedNameSpaces = getSortedNameSpaces(pdDoc.getS2sOppForms());
        for (String namespace : sortedNameSpaces) {
            XmlObject formFragment = null;
            try {
                info = new FormMappingLoader().getFormInfo(namespace);
                formFragment = getFormObject(submittedXml, info);
                s2sFormGenerator = s2SFormGeneratorService.getS2SGenerator(info.getNameSpace());
            }
            catch (S2SGeneratorNotFoundException e) {
                // Form generator not available for the namespace
                continue;
            }

            XmlObject formObject = s2sFormGenerator.getFormObject(formFragment);
            if (s2SValidatorService.validate(formObject, errors)) {
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                formPdfBytes = generatePdf(info.getStyleSheet(), formObject);
                try {
                    stream.write(formPdfBytes);
                }
                catch (IOException e) {
                    LOG.error(e.getMessage(), e);
                    throw new S2SException(e);
                }

                pdfBaosList.add(stream);
                bookmarksList.add(info.getFormName());
                List<S2sAppAttachments> attachmentList = getLatestS2SAppSubmission(pdDoc).getS2sApplication().get(0)
                        .getS2sAppAttachmentList();
                if (attachmentList != null && !attachmentList.isEmpty()) {
                    for (S2sAppAttachments attAppAttachments : attachmentList) {
                        ByteArrayOutputStream attStream = new ByteArrayOutputStream();
                        try {
                            attStream.write(getAttContent(pdDoc, attAppAttachments.getContentId()));
                        }
                        catch (IOException e) {
                            LOG.error(e.getMessage(), e);
                            throw new S2SException(e);
                        }
                        pdfBaosList.add(attStream);
                        StringBuilder attachment = new StringBuilder();
                        attachment.append("   ATT : ");
                        attachment.append(attAppAttachments.getContentId());
                        bookmarksList.add(attachment.toString() );
                    }
                }
            }
        }
        pdfArray = new ByteArrayOutputStream[pdfBaosList.size()];
        // KRAS2SServiceImpl.setValidationErrorMessage(errors);
        List<AuditError> auditErrors = new ArrayList<AuditError>();
        for (String error : errors) {
            auditErrors.add(new AuditError(Constants.NO_FIELD, Constants.GRANTS_GOV_GENERIC_ERROR_KEY, Constants.GRANTS_GOV_PAGE
                    + "." + Constants.GRANTS_GOV_PANEL_ANCHOR, new String[] { error }));
        }
        GlobalVariables.getAuditErrorMap().put("grantsGovAuditErrors",
                new AuditCluster(Constants.GRANTS_GOV_OPPORTUNITY_PANEL, auditErrors, Constants.GRANTSGOV_ERRORS));
        for (int frmIndex = 0; frmIndex < pdfArray.length; frmIndex++) {
            pdfArray[frmIndex] = (ByteArrayOutputStream) pdfBaosList.get(frmIndex);
        }
        return pdfArray;
    }

    /**
     * This method is used to generate byte stream of forms
     * 
     * @param pdDoc ProposalDevelopmentDocument
     * @return ByteArrayOutputStream[] PDF byte Array
     * @throws S2SException
     */
    private ByteArrayOutputStream[] getPDFStream(ProposalDevelopmentDocument pdDoc) throws S2SException {
        ArrayList<ByteArrayOutputStream> pdfBaosList = new ArrayList<ByteArrayOutputStream>();
        bookmarksList = new ArrayList<String>();
        byte[] formPdfBytes = null;
        FormMappingInfo info = null;
        S2SFormGenerator s2sFormGenerator = null;
        ByteArrayOutputStream[] pdfArray = null;
        List<String> errors = new ArrayList<String>();
        List<String> sortedNameSpaces = getSortedNameSpaces(pdDoc.getS2sOppForms());

        for (String namespace : sortedNameSpaces) {
            try {
                info = new FormMappingLoader().getFormInfo(namespace);
                s2sFormGenerator = s2SFormGeneratorService.getS2SGenerator(info.getNameSpace());
            }
            catch (S2SGeneratorNotFoundException e) {
                LOG.info("Form not found for namespace: " + namespace);
                continue;
            }
            XmlObject formObject = s2sFormGenerator.getFormObject(pdDoc);

            if (s2SValidatorService.validate(formObject, errors)) {
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                formPdfBytes = generatePdf(info.getStyleSheet(), formObject);
                try {
                    stream.write(formPdfBytes);
                }
                catch (IOException e) {
                    LOG.error(e.getMessage(), e);
                    throw new S2SException(e);
                }

                pdfBaosList.add(stream);
                bookmarksList.add(info.getFormName());
                List<AttachmentData> attachmentList = s2sFormGenerator.getAttachments();
                if (attachmentList != null && !attachmentList.isEmpty()) {
                    for (AttachmentData attachmentData : attachmentList) {
                        ByteArrayOutputStream attStream = new ByteArrayOutputStream();
                        try {
                            attStream.write(attachmentData.getContent());
                        }
                        catch (IOException e) {
                            LOG.error(e.getMessage(), e);
                            throw new S2SException(e);
                        }
                        pdfBaosList.add(attStream);
                        
                        StringBuilder attachment= new StringBuilder();
                        attachment.append("   ATT : ");
                        attachment.append(attachmentData.getContentId());
                        bookmarksList.add( attachment.toString());
                    }
                }
            }
        }
        pdfArray = new ByteArrayOutputStream[pdfBaosList.size()];
        // KRAS2SServiceImpl.setValidationErrorMessage(errors);
        List<AuditError> auditErrors = new ArrayList<AuditError>();
        for (String error : errors) {
            auditErrors.add(new AuditError(Constants.NO_FIELD, Constants.GRANTS_GOV_GENERIC_ERROR_KEY, Constants.GRANTS_GOV_PAGE
                    + "." + Constants.GRANTS_GOV_PANEL_ANCHOR, new String[] { error }));
        }
        if (!auditErrors.isEmpty()) {
            GlobalVariables.getAuditErrorMap().put("grantsGovAuditErrors",
                    new AuditCluster(Constants.GRANTS_GOV_OPPORTUNITY_PANEL, auditErrors, Constants.GRANTSGOV_ERRORS));
            return null;
        }
        for (int frmIndex = 0; frmIndex < pdfArray.length; frmIndex++) {
            pdfArray[frmIndex] = (ByteArrayOutputStream) pdfBaosList.get(frmIndex);
        }
        return pdfArray;
    }

    /**
     * 
     * This method gets formObject from submitted Application
     * 
     * @param submittedXml GrantApplicationDocument object of the submitted form.
     * @param info form mapping information of the form.
     * @return XmlObject form object corresponding to the GrantApplicationDocument and FormMappingInfo objects.
     * @throws S2SException
     */

    private XmlObject getFormObject(GrantApplicationDocument submittedXml, FormMappingInfo info) {
        XmlObject formObject = null;
        Forms forms = submittedXml.getGrantApplication().getForms();
        if (forms != null) {
            XmlCursor formCursor = forms.newCursor();
            formCursor.toNextToken();
            do {
                if (formCursor.getName().getNamespaceURI().equals(info.getNameSpace())) {
                    formObject = formCursor.getObject();
                    break;
                }
            } while (formCursor.toNextSibling());
        }
        return formObject;
    }

    /**
     * 
     * This method gets attachment contents from narrative based on content ID
     * 
     * @param pdDoc Proposal Development Document.
     * @param contentId for the particular attachment in the Narrative.
     * @return byte[] byte array of attachments based on the contentId object.
     */

    private byte[] getAttContent(ProposalDevelopmentDocument pdDoc, String contentId) {
        String[] contentIds = contentId.split("-");
        String[] contentDesc = contentIds[1].split("_");
        Narrative submittedNarrative = null;
        for (Narrative narrative : pdDoc.getNarratives()) {
            StringBuilder description = new StringBuilder();
            description.append(contentDesc[1]);
            description.append("_");
            description.append(contentDesc[2]);
            if (contentDesc[0].equals(String.valueOf(narrative.getModuleNumber()))
                    && description.toString().equals(narrative.getNarrativeType().getDescription())) {
                submittedNarrative = narrative;
                break;
            }
        }
        if (submittedNarrative != null) {
            submittedNarrative.refreshReferenceObject(NARRATIVE_ATTACHMENT_LIST);
            return submittedNarrative.getNarrativeAttachmentList().get(0).getContent();
        }
        return null;
    }

    /**
     * 
     * This method is used to merge different pages in to the generated pdf, and sets page number and font for the pdf.
     * 
     * @param byteArrayOutputStream stream of byte array.
     * @param bookmarks book marks in Pdf file.
     * @return byte[] byte array of pdf file.
     * @throws S2SException
     */
    private byte[] mergePdfBytes(ByteArrayOutputStream byteArrayOutputStream[], String bookmarks[]) throws S2SException {
        com.lowagie.text.Document document = null;
        PdfWriter writer = null;
        ByteArrayOutputStream mergedPdfReport = new ByteArrayOutputStream();
        byte fileBytes[];
        int totalNumOfPages = 0;
        PdfReader[] pdfReaderArr = new PdfReader[byteArrayOutputStream.length];
        for (int count = 0; count < byteArrayOutputStream.length; count++) {
            if (byteArrayOutputStream[count] == null) {
                continue;
            }
            fileBytes = byteArrayOutputStream[count].toByteArray();
            PdfReader reader;
            try {
                reader = new PdfReader(fileBytes);
            }
            catch (IOException e) {
                LOG.error(e.getMessage(), e);
                throw new S2SException(e);
            }
            pdfReaderArr[count] = reader;
            totalNumOfPages += reader.getNumberOfPages();
        }

        StringBuilder footerPhStr = new StringBuilder();
        footerPhStr.append(" of ");
        footerPhStr.append(totalNumOfPages);
        footerPhStr.append("                                                                            ");
        footerPhStr.append("                                                                            ");
        footerPhStr.append("                                                            ");
        Font font = FontFactory.getFont(FontFactory.TIMES, 8, Font.NORMAL, Color.BLACK);
        Phrase beforePhrase = new Phrase("Page ", font);
        Phrase afterPhrase = new Phrase(footerPhStr.toString(), font);
        HeaderFooter footer = new HeaderFooter(beforePhrase, afterPhrase);
        footer.setAlignment(Element.ALIGN_BASELINE);
        footer.setBorderWidth(0f);
        for (int count = 0; count < pdfReaderArr.length; count++) {
            if (byteArrayOutputStream[count] == null) {
                continue;
            }
            PdfReader reader = pdfReaderArr[count];
            int nop = reader.getNumberOfPages();

            if (count == 0) {
                document = nop > 0 ? new com.lowagie.text.Document(reader.getPageSizeWithRotation(1))
                        : new com.lowagie.text.Document();
                try {
                    writer = PdfWriter.getInstance(document, mergedPdfReport);
                }
                catch (DocumentException e) {
                    LOG.error(e.getMessage(), e);
                    throw new S2SException(e);
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
                PdfImportedPage page = writer.getImportedPage(reader, pageCount);

                cb.addTemplate(page, 1, 0, 0, 1, 0, 0);

                PdfOutline root = cb.getRootOutline();
                if (pageCount == 1) {
                    String pageName = bookmarks[count];
                    cb.addOutline(new PdfOutline(root, new PdfDestination(PdfDestination.FITH), pageName), pageName);
                }
            }
        }
        if (document != null) {
            try {
                document.close();
                return mergedPdfReport.toByteArray();
            }
            catch (Exception e) {
                // IO Exception occurs when PDF has no pages
                LOG.error("Exception occured because the generated PDF document has no pages", e);
            }
        }
        return null;
    }

    /**
     * 
     * This method gets the latest S2SAppSubmission record from the list of S2SAppSubmissions. It iterates through the list and
     * returns the record that has highest SubmissionNo
     * 
     * @param pdDoc {@link ProposalDevelopmentDocument}
     * @return {@link S2sAppSubmission}
     */
    private S2sAppSubmission getLatestS2SAppSubmission(ProposalDevelopmentDocument pdDoc) {
        S2sAppSubmission s2sSubmission = null;
        int submissionNo = 0;
        for (S2sAppSubmission s2sAppSubmission : pdDoc.getS2sAppSubmission()) {
            if (s2sAppSubmission.getSubmissionNumber() != null && s2sAppSubmission.getSubmissionNumber().intValue() > submissionNo) {
                s2sSubmission = s2sAppSubmission;
                submissionNo = s2sAppSubmission.getSubmissionNumber().intValue();
            }
        }
        return s2sSubmission;
    }

    /**
     * This method convert xmlObject to a PDF byte array
     * 
     * @param stylesheet {String} xsl style sheet corresponding to the form.
     * @param xmlObject {XmlObject} xml object of the form.
     * @return byte array containing PDF Document
     * @throws S2SException
     */
    private byte[] generatePdf(String stylesheet, XmlObject xmlObject) throws S2SException {
        try {
            BufferedInputStream stylesheetStream = new BufferedInputStream(getClass().getResourceAsStream("/" + stylesheet));
            byte[] stylesheetBytes = new byte[stylesheetStream.available()];
            stylesheetStream.read(stylesheetBytes);
            Document xmlDoc = (Document) xmlObject.getDomNode();
            return generatePdfBytes(xmlDoc, stylesheetBytes);
        }
        catch (IOException e) {
            LOG.error(e.getMessage(), e);
            throw new S2SException("Not able to read template ");
        }
    }

    /**
     * This method will be called by other programs when pdf needs to be generated for the given forms
     * 
     * @param xmldoc {Document}
     * @param byteIn {byte[]}
     * @return byte[] array containing PDF bytes
     * @throws S2SException
     */

    private byte[] generatePdfBytes(Document xmldoc, byte[] byteIn) throws IOException, S2SException {

        ByteArrayOutputStream out = null;
        try {
            org.apache.fop.apps.Driver driverFop = new org.apache.fop.apps.Driver();
            driverFop.setRenderer(Driver.RENDER_PDF);

            out = new ByteArrayOutputStream();
            driverFop.setOutputStream(out);

            TransformerFactory factory = TransformerFactory.newInstance();
            ByteArrayInputStream byteInStream = new ByteArrayInputStream(byteIn);
            byteInStream.reset();
            Transformer transformer = factory.newTransformer(new StreamSource(byteInStream));
            DOMSource source = new DOMSource(xmldoc);
            Result res = new SAXResult(driverFop.getContentHandler());
            transformer.transform(source, res);
        }
        catch (TransformerConfigurationException e) {
            LOG.error(e.getMessage(), e);
            throw new S2SException(e);
        }
        catch (TransformerException e) {
            LOG.error(e.getMessage(), e);
            throw new S2SException(e);
        }
        finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }
        return out.toByteArray();
    }

    /**
     * 
     * This method sorts all the forms in order as specified in S2sFormBinding.xml and returns the list of namespaces in sorted
     * order.
     * 
     * @param s2sOppForms list of S2sOppForms.
     * @return List<String> list of sorted name spaces.
     */
    private List<String> getSortedNameSpaces(List<S2sOppForms> s2sOppForms) {
        List<String> orderedNamespaces = new ArrayList<String>();
        List<String> namespaces;
        FormMappingLoader formMappingLoader = new FormMappingLoader();
        formMappingLoader.getBindings();
        Map<Integer, List<String>> sortedNamespaces = formMappingLoader.getSortedNameSpaces();
        List<Integer> sortedIndices = new ArrayList<Integer>(sortedNamespaces.keySet());
        int index = 0;
        for (Integer sortedIndex : sortedIndices) {
            namespaces = sortedNamespaces.get(sortedIndex);
            for (String namespace : namespaces) {
                for (S2sOppForms oppForm : s2sOppForms) {
                    if (namespace.equals(oppForm.getOppNameSpace())) {
                        if (Boolean.TRUE.equals(oppForm.getSelectToPrint())) {
                            orderedNamespaces.add(index++, namespace);
                        }
                    }
                }
            }
        }
        return orderedNamespaces;
    }

    /**
     * 
     * Setter for {@link S2SFormGeneratorService}
     * 
     * @param s2SFormGeneratorService
     */
    public void setS2SFormGeneratorService(S2SFormGeneratorService s2SFormGeneratorService) {
        this.s2SFormGeneratorService = s2SFormGeneratorService;
    }

    /**
     * 
     * Setter for {@link S2SFormGeneratorService}
     * 
     * @param s2SValidatorService
     */
    public void setS2SValidatorService(S2SValidatorService s2SValidatorService) {
        this.s2SValidatorService = s2SValidatorService;
    }

    /**
     * 
     * This class populates the bytes of PDF document to be generated
     */
    private class GrantsGovAttachmentData extends AttachmentDataSource {
        private byte[] streamData;

        public byte[] getContent() {
            return streamData;
        }

        public void setContent(byte[] streamData) {
            this.streamData = streamData;
        }
    }
}
