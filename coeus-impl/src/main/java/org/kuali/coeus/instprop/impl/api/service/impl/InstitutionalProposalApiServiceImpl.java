package org.kuali.coeus.instprop.impl.api.service.impl;

import com.codiform.moo.Moo;
import com.codiform.moo.configuration.Configuration;
import org.kuali.coeus.common.api.document.service.CommonApiService;
import org.kuali.coeus.common.api.rolodex.RolodexContract;
import org.kuali.coeus.common.api.rolodex.RolodexService;
import org.kuali.coeus.common.framework.custom.attr.CustomAttributeDocument;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.common.framework.version.VersionStatus;
import org.kuali.coeus.instprop.impl.api.InstitutionalProposalApiConstants;
import org.kuali.coeus.instprop.impl.api.dto.InstitutionalProposalDto;
import org.kuali.coeus.instprop.impl.api.dto.IpPersonDto;
import org.kuali.coeus.instprop.impl.api.dto.ProposalLogDto;
import org.kuali.coeus.instprop.impl.api.service.InstitutionalProposalApiService;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.coeus.sys.framework.rest.UnprocessableEntityException;
import org.kuali.kra.award.home.ContactRole;
import org.kuali.kra.institutionalproposal.contacts.*;
import org.kuali.kra.institutionalproposal.customdata.InstitutionalProposalCustomData;
import org.kuali.kra.institutionalproposal.document.InstitutionalProposalDocument;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.proposallog.ProposalLog;
import org.kuali.kra.institutionalproposal.proposallog.service.ProposalLogService;
import org.kuali.kra.institutionalproposal.service.InstitutionalProposalService;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kim.api.identity.IdentityService;
import org.kuali.rice.kim.api.identity.entity.Entity;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.*;

@Component("institutionalProposalApiService")
public class InstitutionalProposalApiServiceImpl implements InstitutionalProposalApiService {

    @Autowired
    @Qualifier("documentService")
    private DocumentService documentService;

    @Autowired
    @Qualifier("proposalLogService")
    private ProposalLogService proposalLogService;

    @Autowired
    @Qualifier("institutionalProposalService")
    private InstitutionalProposalService institutionalProposalService;

    @Autowired
    @Qualifier("identityService")
    private IdentityService identityService;

    @Autowired
    @Qualifier("rolodexService")
    private RolodexService rolodexService;

    @Autowired
    @Qualifier("businessObjectService")
    private BusinessObjectService businessObjectService;

    @Autowired
    @Qualifier("globalVariableService")
    private GlobalVariableService globalVariableService;

    @Autowired
    @Qualifier("commonApiService")
    private CommonApiService commonApiService;

    public void addCustomData(InstitutionalProposal institutionalProposal, InstitutionalProposalDto institutionalProposalDto) {
        Map<String, CustomAttributeDocument> customAttributeDocuments = institutionalProposal.getInstitutionalProposalDocument().getCustomAttributeDocuments();
        if (institutionalProposalDto.getInstitutionalProposalCustomDataList() != null) {
            institutionalProposalDto.getInstitutionalProposalCustomDataList().stream().forEach(customDataDto -> {
                String customAttributeId = customDataDto.getCustomAttributeId().toString();
                String customDataValue = customDataDto.getValue();
                InstitutionalProposalCustomData customData = new InstitutionalProposalCustomData();
                customAttributeDocuments.keySet().stream().forEach(id -> {
                    CustomAttributeDocument customAttributeDoc = customAttributeDocuments.get(id);
                    if (customAttributeId.equalsIgnoreCase(customAttributeDoc.getCustomAttribute().getId().toString())) {
                        customData.setCustomAttributeId(customAttributeDoc.getId());
                        customData.setCustomAttribute(customAttributeDoc.getCustomAttribute());
                        customData.setValue(customDataValue);
                        customData.setInstitutionalProposal(institutionalProposal);
                        institutionalProposal.getInstitutionalProposalCustomDataList().add(customData);
                    }
                });
            });
        }
    }

    public InstitutionalProposalDocument saveInitialProposal(InstitutionalProposal proposal, String description) throws WorkflowException {
        InstitutionalProposalDocument ipDocument = (InstitutionalProposalDocument) getDocumentService().getNewDocument(InstitutionalProposalDocument.class);
        final String proposalNumber = getInstitutionalProposalService().getNextInstitutionalProposalNumber();
        addRequiredFields(proposal, ipDocument, proposalNumber);
        initializeCollections(proposal);
        proposal.setProjectPersons(new ArrayList<>());
        proposal.setInstitutionalProposalCustomDataList(new ArrayList<>());
        ipDocument.getDocumentHeader().setDocumentDescription(description);
        ipDocument.setInstitutionalProposal(proposal);
        commonApiService.saveDocument(ipDocument);
        return ipDocument;
    }

    public void initializeCollections(InstitutionalProposal proposal) {
        if(proposal.getProjectPersons() == null) proposal.setProjectPersons(new ArrayList<>());
        if(proposal.getInstitutionalProposalCustomDataList() == null ) proposal.setInstitutionalProposalCustomDataList(new ArrayList<>());
        if(proposal.getSpecialReviews() == null ) proposal.setSpecialReviews(new ArrayList<>());
        if(proposal.getInstitutionalProposalScienceKeywords() == null) proposal.setInstitutionalProposalScienceKeywords(new ArrayList<>());
        if(proposal.getInstitutionalProposalCostShares() == null) proposal.setInstitutionalProposalCostShares(new ArrayList<>());
        if(proposal.getInstitutionalProposalUnrecoveredFandAs() == null) proposal.setInstitutionalProposalUnrecoveredFandAs(new ArrayList<>());
        if(proposal.getProposalIpReviewJoins() == null ) proposal.setProposalIpReviewJoins(new ArrayList<>());
        if(proposal.getAwardFundingProposals() == null) proposal.setAwardFundingProposals(new ArrayList<>());
        if(proposal.getAllFundingProposals() == null) proposal.setAllFundingProposals(new ArrayList<>());
        if(proposal.getInstitutionalProposalUnitContacts() == null) proposal.setInstitutionalProposalUnitContacts(new ArrayList<>());
        if(proposal.getProposalComments() == null) proposal.setProposalComments(new ArrayList<>());
    }

    public void updateProposalLog(String proposalLogNumber, InstitutionalProposalDocument ipDocument) {
        getProposalLogService().mergeProposalLog(proposalLogNumber);
        getProposalLogService().updateMergedInstProposal(ipDocument.getInstitutionalProposal().getProposalId(), proposalLogNumber);
    }

    public String createProposalLog(InstitutionalProposalDto ipDto, IpPersonDto ipId) {
        String proposalLogNumber = null;
        ProposalLog proposalLog = addProposalLog(ipDto, ipId);
        proposalLogNumber = proposalLog.getProposalNumber();
        getProposalLogService().promoteProposalLog(proposalLogNumber);
        return proposalLogNumber;
    }

    public void addPersons(InstitutionalProposalDocument proposalDocument, List<IpPersonDto> personDtos) {
        if (personDtos != null) {
            personDtos.stream().forEach(personDto -> {
                addPerson(proposalDocument, personDto);
            });
        }
    }

    public InstitutionalProposalPerson addPerson(InstitutionalProposalDocument proposalDocument, IpPersonDto personDto) {
        InstitutionalProposal proposal = proposalDocument.getInstitutionalProposal();
        final InstitutionalProposalPerson projectPerson = (InstitutionalProposalPerson) commonApiService.convertObject(personDto, InstitutionalProposalPerson.class);
        validatePersonAndRole(projectPerson);
        if(projectPerson.isPrincipalInvestigator()) {
            proposal.refreshReferenceObject("leadUnit");
            proposal.initializeDefaultPrincipalInvestigator(projectPerson);
            validateAndAddPerson(proposalDocument, projectPerson, proposal);
            projectPerson.setRoleCode(ContactRole.PI_CODE);
        } else {
            initializeProposalPerson(proposalDocument, projectPerson);
        }
        return projectPerson;
    }

    private InstitutionalProposalProjectPersonRuleAddEvent generateAddProjectPersonEvent(InstitutionalProposalPerson person, InstitutionalProposalDocument document) {
        return new InstitutionalProposalProjectPersonRuleAddEvent("AddInstitutionalProposalProjectPersonRuleEvent", "projectPersonnelBean.newInstitutionalProposalContact", document,
                person);
    }

    protected InstitutionalProposalPerson initializeProposalPerson(InstitutionalProposalDocument proposalDocument, InstitutionalProposalPerson person) {
        InstitutionalProposal proposal = proposalDocument.getInstitutionalProposal();
        person.setProposalNumber(proposal.getProposalNumber());
        person.setSequenceNumber(proposal.getSequenceNumber());
        person.initializeDefaultCreditSplits();
        person.setFaculty(getFacultyFlag(person));
        person.setInstitutionalProposal(proposal);
        person.setContactRoleCode(person.getRoleCode());
        person.refreshContactRole();
        if(!person.isKeyPerson()) {
            InstitutionalProposalPersonUnit ipPersonUnit = new InstitutionalProposalPersonUnit();
            ipPersonUnit.setUnitNumber(getPersonUnit(person).getUnitNumber());
            ipPersonUnit.initializeDefaultCreditSplits();
            person.add(ipPersonUnit);
        }
        validateAndAddPerson(proposalDocument, person, proposal);
        return person;
    }

    protected Unit getPersonUnit(InstitutionalProposalPerson person) {
        return person.getPerson() != null? person.getPerson().getUnit() : person.getRolodex().getUnit();
    }

    private Boolean getFacultyFlag(InstitutionalProposalPerson person) {
        if (person.getPerson() != null) {
            return person.getPerson().getFacultyFlag();
        }
        return Boolean.FALSE;
    }

    private void validateAndAddPerson(InstitutionalProposalDocument proposalDocument, InstitutionalProposalPerson person, InstitutionalProposal proposal) {
        InstitutionalProposalProjectPersonRuleAddEvent event = generateAddProjectPersonEvent(person, proposalDocument);
        boolean success = new InstitutionalProposalProjectPersonAddRuleImpl().processAddInstitutionalProposalProjectPersonBusinessRules(event);
        if (success) {
            proposal.add(person);
        } else {
            String errors = commonApiService.getValidationErrors();
            throw new RuntimeException(errors);
        }
    }

    public void validatePersonAndRole(InstitutionalProposalPerson person) {
        validatePerson(person);

        if (!person.isInvestigator() && !person.isKeyPerson()) {
            throw new UnprocessableEntityException("Invalid role " + person.getRoleCode() + " for person " + getId(person));
        }
    }

    public void validatePerson(InstitutionalProposalPerson person) {
        Entity personEntity = null;
        RolodexContract rolodex = null;
        if (person.getPersonId() != null) {
            personEntity = identityService.getEntityByPrincipalId(person.getPersonId());
        }
        else {
            rolodex = rolodexService.getRolodex(person.getRolodexId());
            if(rolodex != null) {
                person.setRolodexId(rolodex.getRolodexId());
                person.setPersonId(null);
            }
        }

        if (rolodex == null && personEntity == null) {
                throw new UnprocessableEntityException("Invalid person or rolodex for person " + getId(person));
        }
    }

    protected String getId(InstitutionalProposalPerson person) {
        return person.getPersonId() != null ? person.getPersonId() : person.getRolodexId().toString();
    }

    public ArrayList<LinkedHashMap> getProposalPersons(ArrayList<LinkedHashMap> persons, String roleCode) {
        ArrayList<LinkedHashMap> proposalPersons = new ArrayList<>();
        persons.stream().forEach(person -> {
            if (person.get(InstitutionalProposalApiConstants.ROLE_CODE).toString().equalsIgnoreCase(roleCode)) {
                proposalPersons.add(person);
            }
        });
        return proposalPersons;
    }

    public ProposalLog addProposalLog(InstitutionalProposalDto ipDto, IpPersonDto personDto) {
        ProposalLogDto proposalLogDto = (ProposalLogDto) commonApiService.convertObject(ipDto, ProposalLogDto.class);
        ProposalLog proposalLog = (ProposalLog) commonApiService.convertObject(proposalLogDto, ProposalLog.class);
        if (ipDto.getProposalTypeCode() == null) {
            throw new UnprocessableEntityException("Proposal type code cannot be null.");
        }
        proposalLog.setProposalTypeCode(ipDto.getProposalTypeCode().toString());
        if (ipDto.getCreateTimestamp() != null) {
            proposalLog.setCreateTimestamp(new java.sql.Timestamp(ipDto.getCreateTimestamp().getTime()));
        }
        proposalLog.setLogStatus(InstitutionalProposalApiConstants.LOG_STATUS_DEFAULT);
        proposalLog.setCreateUser("admin");

        if (personDto != null) {
            final InstitutionalProposalPerson projectPerson = (InstitutionalProposalPerson) commonApiService.convertObject(personDto, InstitutionalProposalPerson.class);
            validatePerson(projectPerson);
            if (projectPerson.isPrincipalInvestigator()) {
                proposalLog.setRolodexId(projectPerson.getRolodexId());
                proposalLog.setPiId(projectPerson.getPersonId());
            }
        }
        proposalLog.setProposalNumber(institutionalProposalService.getNextInstitutionalProposalNumber());
        businessObjectService.save(proposalLog);
        return proposalLog;
    }

    public void addRequiredFields(InstitutionalProposal proposal, InstitutionalProposalDocument ipDocument, String proposalNumber) {
        proposal.setProposalNumber(proposalNumber);
        proposal.setProposalSequenceStatus(VersionStatus.ACTIVE.toString());
        proposal.setSequenceNumber(1);
        proposal.setSubcontractFlag(false);
        proposal.setInstProposalNumber(proposalNumber);
        if (proposal.getScienceCodeIndicator() == null) proposal.setScienceCodeIndicator(InstitutionalProposalApiConstants.SCIENCE_CODE_DEFAULT_VALUE);
        if (proposal.getAwardType() == null) proposal.setAwardTypeCode(null);
        proposal.setInstitutionalProposalDocument(ipDocument);
    }

    public void initializeCostTotals(InstitutionalProposal proposal) {
        if(proposal.getTotalDirectCostTotal() == null) proposal.setTotalDirectCostTotal(ScaleTwoDecimal.ZERO);
        if(proposal.getTotalIndirectCostInitial() == null) proposal.setTotalIndirectCostInitial(ScaleTwoDecimal.ZERO);
        if(proposal.getTotalDirectCostInitial() == null) proposal.setTotalDirectCostInitial(ScaleTwoDecimal.ZERO);
        if(proposal.getTotalIndirectCostTotal() == null) proposal.setTotalIndirectCostTotal(ScaleTwoDecimal.ZERO);
    }

    public void updateDataObjectFromDto(Object existingDataObject, Object input) {
        Configuration mooConfig = new Configuration();
        mooConfig.setSourcePropertiesRequired(false);
        Moo moo = new Moo(mooConfig);
        moo.update(input, existingDataObject);
    }


    public void initializeData(InstitutionalProposal proposal) {
        initializeCollections(proposal);
        initializeCostTotals(proposal);
    }

    public InstitutionalProposalService getInstitutionalProposalService() {
        return institutionalProposalService;
    }

    public DocumentService getDocumentService() {
        return documentService;
    }

    public ProposalLogService getProposalLogService() {
        return proposalLogService;
    }
}
