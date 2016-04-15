package org.kuali.kra.s2s.backport;


import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.*;
import gov.grants.apply.coeus.personProfile.PersonProfileListDocument;
import gov.grants.apply.forms.nsfKeyPersonExpandedV10.NSFKeyPersonExpandedDocument;
import gov.grants.apply.forms.nsfKeyPersonExpandedV10.PersonProfileDataType;
import gov.grants.apply.forms.nsfKeyPersonExpandedV10.ProjectRoleDataType;
import gov.grants.apply.system.attachmentsV10.AttachedFileDataType;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.xmlbeans.XmlObject;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.infrastructure.Constants;

import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.printing.PrintingException;
import org.kuali.kra.printing.print.GenericPrintable;
import org.kuali.kra.printing.service.PrintingService;
import org.kuali.kra.proposaldevelopment.bo.*;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.s2s.generator.S2SBaseFormGenerator;
import org.kuali.kra.s2s.service.S2SUtilService;
import org.kuali.kra.s2s.util.S2SConstants;
import org.kuali.kra.service.KcPersonService;
import org.kuali.kra.service.SponsorService;
import org.kuali.kra.service.UnitService;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kns.util.AuditError;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.springframework.util.CollectionUtils;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class NSFKeyPersonExpandedV1_0Generator extends S2SBaseFormGenerator {

    private static final Logger LOG = Logger.getLogger(NSFKeyPersonExpandedV1_0Generator.class);

    private static final int TITLE_MAX_LENGTH = 45;
    private static final int ROLE_DESCRIPTION_MAX_LENGTH = 40;
    private static final int MAX_KEY_PERSON_COUNT = 100;
    private static final int BIOSKETCH_DOC_TYPE = 16;
    private static final int CURRENTPENDING_DOC_TYPE = 17;
    private static final int COLLABORATOR_DOC_TYPE = 148;
    private static final String BIOSKETCH_TYPE = "1";
    private static final String COLLABORATOR_TYPE = "6";
    private static final String CURRENT_PENDING_TYPE = "2";
    private static final String COMMENT = "Auto generated document for ";
    private static final String BIOSKETCH_COMMENT = "BIOSKETCH";
    private static final String CURRENT_PENDING_COMMENT = "CURRENTPENDING";
    private static final String COLLABORATOR_COMMENT = "COLLABORATOR";
    private static final String PROFILE_COMMENT = "PROFILE";
    private static final int PROFILE_TYPE = 18;
    private static final int DIVISION_NAME_MAX_LENGTH = 30;
    private static final String NIH_CO_INVESTIGATOR = "Co-Investigator";
    private static final String ERROR_ERA_COMMON_USER_NAME = "eRA Commons User Name is missing for ";
    private static final int DEPARTMENT_NAME_MAX_LENGTH = 30;
    private static final int ADDRESS_LINE_MAX_LENGTH = 55;
    private static final int WHITESPACE_LENGTH_76 = 76;
    private static final int WHITESPACE_LENGTH_60 = 60;
    private static final String ADDITIONALKEYPERSONPROFILES_XSL = "/org/kuali/kra/s2s/stylesheet/additionalkeypersonprofiles.xsl";

    private Rolodex rolodex;
    private List<ProposalPerson> extraPersons = null;
    private String pIPersonOrRolodexId = null;
    protected S2SUtilService s2sUtilService;
    private DateTimeService dateTimeService;
    private PrintingService printingService;
    private KcPersonService kcPersonService;
    private SponsorService sponsorService;
    private BusinessObjectService businessObjectService;
    private UnitService unitService;
    private ParameterService parameterService;

    public NSFKeyPersonExpandedV1_0Generator() {
        s2sUtilService = KraServiceLocator.getService(S2SUtilService.class);
        dateTimeService = KraServiceLocator.getService(DateTimeService.class);
        printingService = KraServiceLocator.getService(PrintingService.class);
        kcPersonService = KraServiceLocator.getService(KcPersonService.class);
        sponsorService =  KraServiceLocator.getService(SponsorService.class);
        businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
        unitService = KraServiceLocator.getService(UnitService.class);
        parameterService = KraServiceLocator.getService(ParameterService.class);
    }

    protected void saveKeyPersonAttachmentsToProposal() {
        if (!CollectionUtils.isEmpty(extraPersons)) {
            saveKeyPersonAttachments();
            saveKeypersonProfileObject();
        }
    }


    private byte[] mergePdfBytes(List<byte[]> pdfBytesList,
                                 List<String> bookmarksList) throws PrintingException {
        Document document = null;
        PdfWriter writer = null;
        ByteArrayOutputStream mergedPdfReport = new ByteArrayOutputStream();
        int totalNumOfPages = 0;
        PdfReader[] pdfReaderArr = new PdfReader[pdfBytesList.size()];
        int pdfReaderCount = 0;
        for (byte[] fileBytes : pdfBytesList) {
            LOG.debug("File Size " + fileBytes.length + " For "
                    + bookmarksList.get(pdfReaderCount));
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
                LOG.debug("Empty PDF byetes found for "
                        + bookmarksList.get(count));
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

    private Narrative[] saveKeyPersonAttachments() {
        List<String> bioSketchBookMarks = new ArrayList<String>();
        List<String> curPendBookMarks = new ArrayList<String>();
        List<String> collaboratorBookMarks = new ArrayList<String>();
        List<byte[]> bioSketchDataList = new ArrayList<byte[]>();
        List<byte[]> curPendDataList = new ArrayList<byte[]>();
        List<byte[]> collaboratorDataList = new ArrayList<byte[]>();

        Narrative[] extraKeyPersonAttachments = new Narrative[2];
        for (ProposalPerson proposalPerson : extraPersons) {
            setBookMarkAndData(bioSketchBookMarks, bioSketchDataList, proposalPerson, BIOSKETCH_TYPE);
            setBookMarkAndData(curPendBookMarks, curPendDataList, proposalPerson, CURRENT_PENDING_TYPE);
            setBookMarkAndData(collaboratorBookMarks, collaboratorDataList, proposalPerson, COLLABORATOR_TYPE);
        }

        try {
            byte[] bioSketchData = mergePdfBytes(bioSketchDataList, bioSketchBookMarks);
            byte[] curPendData = mergePdfBytes(curPendDataList, curPendBookMarks);
            byte[] collaboratorData = mergePdfBytes(collaboratorDataList, collaboratorBookMarks);
            String fileName;
            if (bioSketchData != null && bioSketchData.length > 0) {
                fileName = pdDoc.getDevelopmentProposal().getProposalNumber() + "_" + BIOSKETCH_COMMENT + ".pdf";
                extraKeyPersonAttachments[0] = saveNarrative(bioSketchData, "" + BIOSKETCH_DOC_TYPE, fileName, COMMENT + BIOSKETCH_COMMENT);
            }
            if (curPendData != null && curPendData.length > 0) {
                fileName = pdDoc.getDevelopmentProposal().getProposalNumber() + "_" + CURRENT_PENDING_COMMENT + ".pdf";
                extraKeyPersonAttachments[1] = saveNarrative(curPendData, "" + CURRENTPENDING_DOC_TYPE, fileName, COMMENT + CURRENT_PENDING_COMMENT);
            }
            if (collaboratorData != null && collaboratorData.length > 0) {
                fileName = pdDoc.getDevelopmentProposal().getProposalNumber() + "_" + COLLABORATOR_COMMENT + ".pdf";
                extraKeyPersonAttachments[1] = saveNarrative(collaboratorData, "" + COLLABORATOR_DOC_TYPE, fileName, COMMENT + COLLABORATOR_COMMENT);
            }
        } catch (PrintingException e) {
            LOG.error("Auto generation of Biosketch/Currend Pending report for extra Keypersons is failed", e);
        }
        return extraKeyPersonAttachments;
    }

    private void setBookMarkAndData(List<String> bookMarksList,
                                    List<byte[]> dataList, ProposalPerson proposalPerson, String docType) {
        final String personId;
        if (proposalPerson.getPersonId() != null && proposalPerson.getPersonId().length() > 0) {
            personId = proposalPerson.getPersonId();
        } else {
            personId = "" + proposalPerson.getRolodexId();
        }

        for (ProposalPersonBiography personBiography : getPernonnelAttachments(pdDoc, proposalPerson, docType)) {
            byte[] content = personBiography.getData();
            if (content != null && content.length > 0) {
                dataList.add(content);
                bookMarksList.add(personId);
            }
        }
    }

    private List<ProposalPersonBiography> getPernonnelAttachments(ProposalDevelopmentDocument pdDoc, ProposalPerson proposalPerson, String documentType) {
        List<ProposalPersonBiography> result = new ArrayList<ProposalPersonBiography>();
        for (ProposalPersonBiography proposalPersonBiography : pdDoc.getDevelopmentProposal().getPropPersonBios()) {
            String personId = proposalPerson.getPersonId();
            Integer rolodexId = proposalPerson.getRolodexId();
            if (personId != null && proposalPersonBiography.getPersonId() != null && proposalPersonBiography.getPersonId().equals(personId) && documentType.equals(proposalPersonBiography.getPropPerDocType().getDocumentTypeCode())) {
                result.add(proposalPersonBiography);
            } else if (rolodexId != null && proposalPersonBiography.getRolodexId() != null
                    && proposalPersonBiography.getRolodexId().toString().equals(rolodexId.toString())
                    && documentType.equals(proposalPersonBiography.getPropPerDocType().getDocumentTypeCode())) {
                result.add(proposalPersonBiography);
            }
        }
        return result;
    }

    protected PersonProfileListDocument.PersonProfileList.ExtraKeyPerson[] getExtraKeyPersons() {
        List<PersonProfileListDocument.PersonProfileList.ExtraKeyPerson> extraPersonList = new ArrayList<PersonProfileListDocument.PersonProfileList.ExtraKeyPerson>();

        for (ProposalPerson proposalPerson : extraPersons) {

            PersonProfileListDocument.PersonProfileList.ExtraKeyPerson extraPerson = PersonProfileListDocument.PersonProfileList.ExtraKeyPerson.Factory.newInstance();

            extraPerson.setName(getExtraPersonName(proposalPerson));
            extraPerson.setAddress(getExtraPersonAddress(proposalPerson));
            if (proposalPerson.getPrimaryTitle() != null && proposalPerson.getPrimaryTitle().length() > TITLE_MAX_LENGTH)
                extraPerson.setTitle(proposalPerson.getPrimaryTitle().substring(0, TITLE_MAX_LENGTH));
            else {
                extraPerson.setTitle(proposalPerson.getPrimaryTitle());
            }

            if (proposalPerson.getProposalPersonRoleId() != null) {
                if (proposalPerson.getProposalPersonRoleId().equals(Constants.PRINCIPAL_INVESTIGATOR_ROLE)) {
                    extraPerson.setProjectRole(PersonProfileListDocument.PersonProfileList.ExtraKeyPerson.ProjectRole.PD_PI);
                } else if (proposalPerson.getProposalPersonRoleId().equals(Constants.CO_INVESTIGATOR_ROLE)) {
                    if (isSponsorNIH(pdDoc)) {
                        extraPerson.setProjectRole(PersonProfileListDocument.PersonProfileList.ExtraKeyPerson.ProjectRole.OTHER_SPECIFY);
                        extraPerson.setOtherProjectRoleCategory(NIH_CO_INVESTIGATOR);
                    } else {
                        extraPerson.setProjectRole(PersonProfileListDocument.PersonProfileList.ExtraKeyPerson.ProjectRole.CO_PD_PI);
                    }
                } else {
                    final String otherRole;
                    if (proposalPerson.getProjectRole() != null && proposalPerson.getProjectRole().length() > ROLE_DESCRIPTION_MAX_LENGTH) {
                        otherRole = proposalPerson.getProjectRole().substring(0, ROLE_DESCRIPTION_MAX_LENGTH);
                    } else {
                        otherRole = proposalPerson.getProjectRole();
                    }
                    extraPerson.setOtherProjectRoleCategory(otherRole);
                    extraPerson.setProjectRole(PersonProfileListDocument.PersonProfileList.ExtraKeyPerson.ProjectRole.OTHER_SPECIFY);
                }
            }

            if (proposalPerson.getEraCommonsUserName() != null) {
                extraPerson.setCredential(proposalPerson.getEraCommonsUserName());
            }
            setDepartmentName(extraPerson);
            setDivisionName(extraPerson);
            if (proposalPerson.getEmailAddress() != null) {
                extraPerson.setEmail(proposalPerson.getEmailAddress());
            }
            if (StringUtils.isNotEmpty(proposalPerson.getFaxNumber())) {
                extraPerson.setFax(proposalPerson.getFaxNumber());
            }
            Unit unit = proposalPerson.getHomeUnitRef();

            if (unit != null && unit.getUnitName() != null) {
                extraPerson.setOrganizationName(unit.getUnitName());
            }
            if (proposalPerson.getOfficePhone() != null) {
                extraPerson.setPhone(proposalPerson.getOfficePhone());
            }
            // degree type and year added for version 1.2 - case 4337
            if (proposalPerson.getDegree() != null) {
                extraPerson.setDegreeType(proposalPerson.getDegree());
            }
            if (proposalPerson.getYearGraduated() != null) {
                extraPerson.setDegreeYear(proposalPerson.getYearGraduated());
            }
            AttachedFileDataType bioSketchAttachment = getPernonnelAttachments(pdDoc, proposalPerson.getPersonId(), proposalPerson.getRolodexId(), BIOSKETCH_TYPE);
            if (bioSketchAttachment != null) {
                extraPerson.setBioSketchAttached(gov.grants.apply.coeus.personProfile.PersonProfileListDocument.PersonProfileList.ExtraKeyPerson.BioSketchAttached.YES);
            }

            AttachedFileDataType curPendingAttachment = getPernonnelAttachments(pdDoc, proposalPerson.getPersonId(), proposalPerson.getRolodexId(), CURRENT_PENDING_TYPE);
            if (curPendingAttachment != null) {
                extraPerson.setSupportsAttached(gov.grants.apply.coeus.personProfile.PersonProfileListDocument.PersonProfileList.ExtraKeyPerson.SupportsAttached.YES);
            }

            AttachedFileDataType collaboratorAttachment = getPernonnelAttachments(pdDoc, proposalPerson.getPersonId(), proposalPerson.getRolodexId(), COLLABORATOR_TYPE);
            if (collaboratorAttachment != null) {
                extraPerson.setCollaboratorAttached(gov.grants.apply.coeus.personProfile.PersonProfileListDocument.PersonProfileList.ExtraKeyPerson.CollaboratorAttached.YES);
            }

            extraPersonList.add(extraPerson);
        }
        return extraPersonList.toArray(new PersonProfileListDocument.PersonProfileList.ExtraKeyPerson[0]);
    }

    private PersonProfileListDocument.PersonProfileList.ExtraKeyPerson.Address getExtraPersonAddress(
            ProposalPerson proposalPerson) {
        PersonProfileListDocument.PersonProfileList.ExtraKeyPerson.Address address = PersonProfileListDocument.PersonProfileList.ExtraKeyPerson.Address.Factory.newInstance();
        if (proposalPerson.getAddressLine1() != null) {
            if (proposalPerson.getAddressLine1().length() > ADDRESS_LINE_MAX_LENGTH) {
                address.setStreet1(proposalPerson.getAddressLine1().substring(0, ADDRESS_LINE_MAX_LENGTH));
            } else {
                address.setStreet1(proposalPerson.getAddressLine1());
            }
        }
        if (proposalPerson.getAddressLine2() != null) {
            if (proposalPerson.getAddressLine2().length() > ADDRESS_LINE_MAX_LENGTH) {
                address.setStreet2(proposalPerson.getAddressLine2().substring(0, ADDRESS_LINE_MAX_LENGTH));
            } else {
                address.setStreet2(proposalPerson.getAddressLine2());
            }
        }
        if (proposalPerson.getCity() != null) {
            address.setCity(proposalPerson.getCity());
        }
        if (proposalPerson.getCounty() != null) {
            address.setCounty(proposalPerson.getCounty());
        }

        if (proposalPerson.getPostalCode() != null) {
            address.setZipCode(proposalPerson.getPostalCode());
        }

        if (proposalPerson.getCountryCode() != null) {
            PersonProfileListDocument.PersonProfileList.ExtraKeyPerson.Address.Country.Enum county = PersonProfileListDocument.PersonProfileList.ExtraKeyPerson.Address.Country.Enum.forString(proposalPerson.getCountryCode());
            address.setCountry(county);
        }
        if (proposalPerson.getState() != null) {
            address.setState(proposalPerson.getState());
        }
        return address;
    }

    private PersonProfileListDocument.PersonProfileList.ExtraKeyPerson.Name getExtraPersonName(ProposalPerson proposalPerson) {
        PersonProfileListDocument.PersonProfileList.ExtraKeyPerson.Name name = PersonProfileListDocument.PersonProfileList.ExtraKeyPerson.Name.Factory.newInstance();
        if (proposalPerson.getFirstName() != null) {
            name.setFirstName(proposalPerson.getFirstName());
        }
        if (proposalPerson.getMiddleName() != null) {
            name.setMiddleName(proposalPerson.getMiddleName());
        }
        if (proposalPerson.getLastName() != null) {
            name.setLastName(proposalPerson.getLastName());
        }
        return name;
    }

    private void setDivisionName(PersonProfileListDocument.PersonProfileList.ExtraKeyPerson extraPerson) {
        extraPerson.setDivisionName("");
    }

    private void setDepartmentName(PersonProfileListDocument.PersonProfileList.ExtraKeyPerson extraPerson) {
        extraPerson.setDepartmentName("");
    }

    private Narrative saveKeypersonProfileObject() {
        Narrative narrative = null;
        if (!CollectionUtils.isEmpty(extraPersons)) {
            PersonProfileListDocument.PersonProfileList extraPersonProfileList = PersonProfileListDocument.PersonProfileList.Factory.newInstance();

            extraPersonProfileList.setProposalNumber(pdDoc.getDevelopmentProposal().getProposalNumber());
            extraPersonProfileList.setExtraKeyPersonArray(getExtraKeyPersons());

            PersonProfileListDocument extraPersonDoc = PersonProfileListDocument.Factory.newInstance();
            extraPersonDoc.setPersonProfileList(extraPersonProfileList);
            String xmlData = extraPersonDoc.xmlText();
            Map<String, byte[]> streamMap = new HashMap<String, byte[]>();
            streamMap.put("", xmlData.getBytes());

            Source xsltSource = new StreamSource(getClass()
                    .getResourceAsStream(ADDITIONALKEYPERSONPROFILES_XSL));

            Map<String, Source> xSLTemplateWithBookmarks = new HashMap<String, Source>();
            xSLTemplateWithBookmarks.put("", xsltSource);


            GenericPrintable printable = new GenericPrintable();
            printable.setXSLTemplateWithBookmarks(xSLTemplateWithBookmarks);
            printable.setStreamMap(streamMap);

            try {
                AttachmentDataSource printData = printingService.print(printable);
                String fileName = pdDoc.getDevelopmentProposal().getProposalNumber() + "_" + PROFILE_COMMENT + ".pdf";
                narrative = saveNarrative(printData.getContent(), "" + PROFILE_TYPE, fileName, COMMENT + PROFILE_COMMENT);
            } catch (PrintingException e) {
                LOG.error("Auto generation of Profile attachment for extra Keypersons failed", e);
            }
        }
        return narrative;
    }


    private NSFKeyPersonExpandedDocument getNSFKeyPersonExpanded() {
        NSFKeyPersonExpandedDocument nsfKeyPersonExpandedDocument = NSFKeyPersonExpandedDocument.Factory.newInstance();
        NSFKeyPersonExpandedDocument.NSFKeyPersonExpanded nsfKeyPersonExpanded = NSFKeyPersonExpandedDocument.NSFKeyPersonExpanded.Factory.newInstance();
        setNSFKeyPersonExpandedAttributes(nsfKeyPersonExpanded);
        nsfKeyPersonExpandedDocument.setNSFKeyPersonExpanded(nsfKeyPersonExpanded);

        return nsfKeyPersonExpandedDocument;
    }

    private void setNSFKeyPersonExpandedAttributes(NSFKeyPersonExpandedDocument.NSFKeyPersonExpanded nsfKeyPersonExpanded) {
        nsfKeyPersonExpanded.setFormVersion(FormVersion.v1_0.getVersion());
        nsfKeyPersonExpanded.setPDPI(getPersonProfilePI());
        PersonProfileDataType[] keyPersonArray = getpersonProfileKeyPerson();
        if (keyPersonArray.length > 0) {
            nsfKeyPersonExpanded.setKeyPersonArray(keyPersonArray);
        }
        saveKeyPersonAttachmentsToProposal();
        if (extraPersons.size() > 0) {
            for (ProposalPerson extraPerson : extraPersons) {
                setBioSketchAttchment(nsfKeyPersonExpanded, extraPerson);
                setCurrentPendingTypeAttachment(nsfKeyPersonExpanded, extraPerson);
            }
            for (Narrative narrative : pdDoc.getDevelopmentProposal().getNarratives()) {
                if (narrative.getNarrativeType().getNarrativeTypeCode() != null) {
                    if (Integer.parseInt(narrative.getNarrativeType().getNarrativeTypeCode()) == PROFILE_TYPE) {
                        setProfileTypeAttachment(nsfKeyPersonExpanded, narrative);
                    }
                }
            }
        }
    }


    private void setProfileTypeAttachment(NSFKeyPersonExpandedDocument.NSFKeyPersonExpanded nsfKeyPersonExpanded, Narrative narrative) {
        AttachedFileDataType attachedFileDataType = getAttachedFileType(narrative);
        if (attachedFileDataType != null) {
            NSFKeyPersonExpandedDocument.NSFKeyPersonExpanded.AdditionalProfilesAttached additionalProfilesAttached = NSFKeyPersonExpandedDocument.NSFKeyPersonExpanded.AdditionalProfilesAttached.Factory.newInstance();
            additionalProfilesAttached.setAdditionalProfileAttached(attachedFileDataType);
            nsfKeyPersonExpanded.setAdditionalProfilesAttached(additionalProfilesAttached);
        }
    }

    private void setCurrentPendingTypeAttachment(NSFKeyPersonExpandedDocument.NSFKeyPersonExpanded nsfKeyPersonExpanded, ProposalPerson extraPerson) {
        AttachedFileDataType supportAttachment = getPernonnelAttachments(pdDoc, extraPerson.getPersonId(), extraPerson.getRolodexId(), CURRENT_PENDING_TYPE);
        if (supportAttachment != null) {
            NSFKeyPersonExpandedDocument.NSFKeyPersonExpanded.SupportsAttached supportsAttached = NSFKeyPersonExpandedDocument.NSFKeyPersonExpanded.SupportsAttached.Factory.newInstance();
            supportsAttached.setSupportAttached(supportAttachment);
            nsfKeyPersonExpanded.setSupportsAttached(supportsAttached);
        }
    }

    private void setBioSketchAttchment(NSFKeyPersonExpandedDocument.NSFKeyPersonExpanded nsfKeyPersonExpanded, ProposalPerson extraPerson) {
        NSFKeyPersonExpandedDocument.NSFKeyPersonExpanded.BioSketchsAttached personBioSketch = NSFKeyPersonExpandedDocument.NSFKeyPersonExpanded.BioSketchsAttached.Factory.newInstance();
        AttachedFileDataType bioSketchAttachment = getPernonnelAttachments(pdDoc, extraPerson.getPersonId(), extraPerson.getRolodexId(), BIOSKETCH_TYPE);
        personBioSketch.setBioSketchAttached(bioSketchAttachment);
        nsfKeyPersonExpanded.setBioSketchsAttached(personBioSketch);
    }


    private PersonProfileDataType getPersonProfilePI() {
        PersonProfileDataType profileDataType = PersonProfileDataType.Factory.newInstance();
        PersonProfileDataType.Profile profile = PersonProfileDataType.Profile.Factory.newInstance();
        ProposalPerson PI = s2sUtilService.getPrincipalInvestigator(pdDoc);
        if (PI != null) {
            setPersonalProfileDetailsToProfile(profileDataType, profile, PI);
        }
        return profileDataType;
    }

    private void setPersonalProfileDetailsToProfile(PersonProfileDataType profileDataType, PersonProfileDataType.Profile profile, ProposalPerson PI) {
        assignRolodexId(PI);
        profile.setName(globLibV20Generator.getHumanNameDataType(PI));
        setDirectoryTitleToProfile(profile, PI);
        profile.setAddress(globLibV20Generator.getAddressDataType(PI));
        profile.setPhone(PI.getOfficePhone());
        if (StringUtils.isNotEmpty(PI.getFaxNumber())) {
            profile.setFax(PI.getFaxNumber());
        }
        setDegreeInfo(PI, profile);
        profile.setEmail(PI.getEmailAddress());
        DevelopmentProposal developmentProposal = pdDoc.getDevelopmentProposal();
        setOrganizationName(profile, developmentProposal);
        setDepartmentNameToProfile(profile, PI);
        String divisionName = PI.getDivision();
        if (divisionName != null) {
            profile.setDivisionName(StringUtils.substring(divisionName, 0, DIVISION_NAME_MAX_LENGTH));
        } else {
            String personId = PI.getPersonId();

            KcPerson kcPersonContact = kcPersonService.getKcPersonByPersonId(personId);
            if (!kcPersonContact.getOrganizationIdentifier().isEmpty()) {
                divisionName = getPIDivision(kcPersonContact.getOrganizationIdentifier());
            }
            if (divisionName != null) {
                profile.setDivisionName(StringUtils.substring(divisionName, 0, DIVISION_NAME_MAX_LENGTH));
            }
        }
        if (PI.getEraCommonsUserName() != null) {
            profile.setCredential(PI.getEraCommonsUserName());
        } else {
            if (sponsorService.isSponsorNihMultiplePi(pdDoc.getDevelopmentProposal())) {
                getAuditErrors().add(new AuditError(Constants.NO_FIELD, S2SConstants.ERROR_ERA_COMMON_USER_NAME + PI.getFullName(),
                        Constants.GRANTS_GOV_PAGE + "." + Constants.GRANTS_GOV_PANEL_ANCHOR));
            }
        }
        profile.setProjectRole(ProjectRoleDataType.PD_PI);
        setAttachments(profile, PI);
        profileDataType.setProfile(profile);
    }

    private String getPIDivision(String departmentId) {
        String divisionName = null;
        String unitName = getUnitName(departmentId);
        String heirarchyLevelDivisionName = null;

        int hierarchyLevel = Integer.parseInt(parameterService.getParameterValueAsString(Constants.KC_S2S_PARAMETER_NAMESPACE,
                ParameterConstants.ALL_COMPONENT, "HIERARCHY_LEVEL"));
        int levelCount = 1;
        List<Unit> heirarchyUnits = unitService.getUnitHierarchyForUnit(departmentId);
        for (Unit heirarchyUnit : heirarchyUnits) {
            if (levelCount < hierarchyLevel && heirarchyUnit.getUnitName().equalsIgnoreCase(unitName)) {
                divisionName = heirarchyUnit.getUnitName();
            } else if (levelCount == hierarchyLevel) {
                heirarchyLevelDivisionName = heirarchyUnit.getUnitName();
                if (heirarchyUnit.getUnitName().equalsIgnoreCase(unitName)) {
                    divisionName = heirarchyLevelDivisionName;
                }
            } else if (levelCount > hierarchyLevel && heirarchyUnit.getUnitName().equalsIgnoreCase(unitName)) {
                divisionName = heirarchyLevelDivisionName;
            }
            levelCount++;
        }
        return divisionName;
    }

    private String getUnitName(String departmentCode) {

        Unit unit = unitService.getUnit(departmentCode);
        return unit == null ? null : unit.getUnitName();
    }

    private void setDepartmentNameToProfile(PersonProfileDataType.Profile profile, ProposalPerson PI) {
        if (PI.getHomeUnit() != null && PI.getPerson() != null && PI.getPerson().getUnit() != null) {
            final String departmentName = PI.getPerson().getUnit().getUnitName();
            profile.setDepartmentName(StringUtils.substring(departmentName, 0, DEPARTMENT_NAME_MAX_LENGTH));
        } else {
            DevelopmentProposal developmentProposal = pdDoc.getDevelopmentProposal();
            profile.setDepartmentName(StringUtils.substring(developmentProposal.getOwnedByUnit().getUnitName(), 0, DEPARTMENT_NAME_MAX_LENGTH));
        }
    }

    private void setDirectoryTitleToProfile(PersonProfileDataType.Profile profile, ProposalPerson PI) {
        if (PI.getDirectoryTitle() != null) {
            if (PI.getDirectoryTitle().length() > TITLE_MAX_LENGTH) {
                profile.setTitle(PI.getDirectoryTitle().substring(0, TITLE_MAX_LENGTH));
            } else {
                profile.setTitle(PI.getDirectoryTitle());
            }
        }
    }

    private void assignRolodexId(ProposalPerson PI) {
        if (PI.getPersonId() != null) {
            pIPersonOrRolodexId = PI.getPersonId();
            rolodex = null;
        } else if (PI.getRolodexId() != null) {
            pIPersonOrRolodexId = PI.getRolodexId().toString();
            rolodex = businessObjectService.findBySinglePrimaryKey(Rolodex.class,pIPersonOrRolodexId);
        }
    }

    private void setAttachments(PersonProfileDataType.Profile profile, ProposalPerson PI) {
        setBioSketchAttachment(profile, PI);
        setCurrentPendingAttachment(profile, PI);
        setCollaboratorsAttachment(profile, PI);
    }

    private void setCurrentPendingAttachment(PersonProfileDataType.Profile profile, ProposalPerson PI) {
        AttachedFileDataType supportAttachment = getPernonnelAttachments(pdDoc, PI.getPersonId(), PI.getRolodexId(), CURRENT_PENDING_TYPE);
        if (supportAttachment != null) {
            PersonProfileDataType.Profile.SupportsAttached supportsAttached = PersonProfileDataType.Profile.SupportsAttached.Factory.newInstance();
            supportsAttached.setSupportAttached(supportAttachment);
            profile.setSupportsAttached(supportsAttached);
        }
    }

    private void setBioSketchAttachment(PersonProfileDataType.Profile profile, ProposalPerson PI) {
        PersonProfileDataType.Profile.BioSketchsAttached personBioSketch = PersonProfileDataType.Profile.BioSketchsAttached.Factory.newInstance();
        AttachedFileDataType bioSketchAttachment = getPernonnelAttachments(pdDoc, PI.getPersonId(), PI.getRolodexId(), BIOSKETCH_TYPE);
        if (bioSketchAttachment != null) {
            personBioSketch.setBioSketchAttached(bioSketchAttachment);
            profile.setBioSketchsAttached(personBioSketch);
        }
    }

    private void setCollaboratorsAttachment(PersonProfileDataType.Profile profile, ProposalPerson PI) {
        PersonProfileDataType.Profile.CollaboratorsAttached personCollaborator = PersonProfileDataType.Profile.CollaboratorsAttached.Factory.newInstance();
        AttachedFileDataType collaboratorAttachment = getPernonnelAttachments(pdDoc, PI.getPersonId(), PI.getRolodexId(), COLLABORATOR_TYPE);
        if (collaboratorAttachment != null) {
            personCollaborator.setCollaboratorAttached(collaboratorAttachment);
            profile.setCollaboratorsAttached(personCollaborator);
        }
    }

    private PersonProfileDataType[] getpersonProfileKeyPerson() {
        List<PersonProfileDataType> personProfileDataTypeList = new ArrayList<PersonProfileDataType>();
        DevelopmentProposal developmentProposal = pdDoc.getDevelopmentProposal();
        List<ProposalPerson> keyPersons = developmentProposal.getProposalPersons();
        if (keyPersons != null) {
            Collections.sort(keyPersons, new ProposalPersonComparator());
        }
        List<ProposalPerson> nKeyPersons = s2sUtilService.getNKeyPersons(keyPersons, true, MAX_KEY_PERSON_COUNT);
        extraPersons = s2sUtilService.getNKeyPersons(keyPersons, false, MAX_KEY_PERSON_COUNT);
        if (nKeyPersons.size() > 0) {
            setKeyPersonToPersonProfileDataType(personProfileDataTypeList, nKeyPersons);
        }
        PersonProfileDataType[] personProfileDataArray = new PersonProfileDataType[0];
        personProfileDataArray = personProfileDataTypeList.toArray(personProfileDataArray);
        return personProfileDataArray;
    }

    private void setKeyPersonToPersonProfileDataType(
            List<PersonProfileDataType> personProfileDataTypeList,
            List<ProposalPerson> nKeyPersons) {
        for (ProposalPerson keyPerson : nKeyPersons) {
            if (pIPersonOrRolodexId != null) {
                // Don't add PI to keyperson list
                if (keyPerson.getPersonId() != null && keyPerson.getPersonId().equals(pIPersonOrRolodexId)) {
                    continue;
                } else if ((keyPerson.getRolodexId() != null) && pIPersonOrRolodexId.equals(keyPerson.getRolodexId().toString())) {
                    continue;
                }
            }
            PersonProfileDataType.Profile profileKeyPerson = PersonProfileDataType.Profile.Factory.newInstance();
            setAllkeyPersonDetailsToKeyPerson(keyPerson, profileKeyPerson);
            setAttachments(profileKeyPerson, keyPerson);
            PersonProfileDataType personProfileDataTypeKeyPerson = PersonProfileDataType.Factory.newInstance();
            personProfileDataTypeKeyPerson.setProfile(profileKeyPerson);
            personProfileDataTypeList.add(personProfileDataTypeKeyPerson);
        }
    }

    private void setAllkeyPersonDetailsToKeyPerson(ProposalPerson keyPerson,
                                                   PersonProfileDataType.Profile profileKeyPerson) {
        assignRolodexId(keyPerson);
        profileKeyPerson.setName(globLibV20Generator.getHumanNameDataType(keyPerson));
        setDirectoryTitleToProfile(profileKeyPerson, keyPerson);
        profileKeyPerson.setAddress(globLibV20Generator.getAddressDataType(keyPerson));
        profileKeyPerson.setPhone(keyPerson.getOfficePhone());
        if (StringUtils.isNotEmpty(keyPerson.getFaxNumber())) {
            profileKeyPerson.setFax(keyPerson.getFaxNumber());
        }
        setDegreeInfo(keyPerson, profileKeyPerson);
        profileKeyPerson.setEmail(keyPerson.getEmailAddress());
        DevelopmentProposal developmentProposal = pdDoc.getDevelopmentProposal();
        setOrganizationName(profileKeyPerson, developmentProposal);
        setDepartmentNameToProfile(profileKeyPerson, keyPerson);
        String divisionName = keyPerson.getDivision();
        if (divisionName != null) {
            profileKeyPerson.setDivisionName(StringUtils.substring(divisionName, 0, DIVISION_NAME_MAX_LENGTH));
        }
        if (keyPerson.getEraCommonsUserName() != null) {
            profileKeyPerson.setCredential(keyPerson.getEraCommonsUserName());
        } else {
            if (sponsorService.isSponsorNihMultiplePi(pdDoc.getDevelopmentProposal())) {
                if (keyPerson.isMultiplePi()) {
                    getAuditErrors().add(new AuditError(Constants.NO_FIELD, ERROR_ERA_COMMON_USER_NAME + keyPerson.getFullName(), Constants.GRANTS_GOV_PAGE + "." + Constants.GRANTS_GOV_PANEL_ANCHOR));
                }
            }
        }
        setProjectRole(keyPerson, profileKeyPerson);
    }

    private void setDegreeInfo(ProposalPerson keyPerson, PersonProfileDataType.Profile profileKeyPerson) {
        if (keyPerson.getDegree() != null) {
            profileKeyPerson.setDegreeType(keyPerson.getDegree());
        }
        if (keyPerson.getYearGraduated() != null) {
            profileKeyPerson.setDegreeYear(keyPerson.getYearGraduated());
        }
        if (keyPerson.getDegree() == null && keyPerson.getYearGraduated() == null) {
            if (keyPerson.getProposalPersonDegrees() != null && keyPerson.getProposalPersonDegrees().size() > 0) {
                ProposalPersonDegree proposalPersonDegree = keyPerson.getProposalPersonDegrees().get(0);
                if (proposalPersonDegree != null) {
                    if (proposalPersonDegree.getDegreeType() != null && proposalPersonDegree.getDegreeType().getDescription() != null)
                        profileKeyPerson.setDegreeType(proposalPersonDegree.getDegreeType().getDescription());
                    if (proposalPersonDegree.getGraduationYear() != null)
                        profileKeyPerson.setDegreeYear(proposalPersonDegree.getGraduationYear());
                }
            }
        }
    }

    private void setProjectRole(ProposalPerson keyPerson, PersonProfileDataType.Profile profileKeyPerson) {
        if (keyPerson.isMultiplePi() || Constants.CO_INVESTIGATOR_ROLE.equals(keyPerson.getProposalPersonRoleId())) {
            if (sponsorService.isSponsorNihMultiplePi(pdDoc.getDevelopmentProposal())) {
                if (keyPerson.isMultiplePi()) {
                    profileKeyPerson.setProjectRole(ProjectRoleDataType.PD_PI);
                } else {
                    profileKeyPerson.setProjectRole(ProjectRoleDataType.CO_INVESTIGATOR);
                }
            } else {
                profileKeyPerson.setProjectRole(ProjectRoleDataType.CO_PD_PI);
            }
        } else {
            setProjectRoleCategoryToProfile(keyPerson, profileKeyPerson);
        }
    }

    private void setOrganizationName(PersonProfileDataType.Profile profileKeyPerson,
                                     DevelopmentProposal developmentProposal) {
        if (developmentProposal.getApplicantOrganization() != null && developmentProposal.getApplicantOrganization().getOrganization() != null) {
            if (rolodex != null) {
                profileKeyPerson.setOrganizationName(rolodex.getOrganization());
            } else
                profileKeyPerson.setOrganizationName(developmentProposal.getApplicantOrganization().getOrganization().getOrganizationName());
        }
    }

    private void setProjectRoleCategoryToProfile(ProposalPerson keyPerson,
                                                 PersonProfileDataType.Profile profileKeyPerson) {
        if (keyPerson.getRolodexId() != null && keyPerson.getProjectRole().equals(ProjectRoleDataType.PD_PI.toString())) {
            profileKeyPerson.setProjectRole(ProjectRoleDataType.PD_PI);
        } else {
            profileKeyPerson.setProjectRole(ProjectRoleDataType.OTHER_SPECIFY);
            PersonProfileDataType.Profile.OtherProjectRoleCategory otherProjectRole = PersonProfileDataType.Profile.OtherProjectRoleCategory.Factory.newInstance();
            String otherRole;
            if (keyPerson.getProjectRole() != null) {
                if (keyPerson.getProjectRole().length() > ROLE_DESCRIPTION_MAX_LENGTH) {
                    otherRole = keyPerson.getProjectRole().substring(0, ROLE_DESCRIPTION_MAX_LENGTH);
                } else {
                    otherRole = keyPerson.getProjectRole();
                }
            } else {
                otherRole = FieldValueConstants.VALUE_UNKNOWN;
            }
            otherProjectRole.setStringValue(otherRole);
            profileKeyPerson.setOtherProjectRoleCategory(otherProjectRole);
        }
    }

    public XmlObject getFormObject(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        this.pdDoc = proposalDevelopmentDocument;
        return getNSFKeyPersonExpanded();
    }
}
