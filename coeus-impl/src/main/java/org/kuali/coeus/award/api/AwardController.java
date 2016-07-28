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
package org.kuali.coeus.award.api;

import com.codiform.moo.Moo;
import com.codiform.moo.configuration.Configuration;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.award.dto.*;
import org.kuali.coeus.common.api.document.service.CommonApiService;
import org.kuali.coeus.common.framework.custom.attr.CustomAttributeDocument;
import org.kuali.coeus.common.framework.sponsor.term.SponsorTerm;
import org.kuali.coeus.common.framework.version.VersionStatus;
import org.kuali.coeus.common.framework.version.VersioningService;
import org.kuali.coeus.common.framework.version.history.VersionHistory;
import org.kuali.coeus.common.framework.version.history.VersionHistoryService;
import org.kuali.coeus.sys.framework.controller.rest.RestController;
import org.kuali.coeus.sys.framework.controller.rest.audit.RestAuditLogger;
import org.kuali.coeus.sys.framework.controller.rest.audit.RestAuditLoggerFactory;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.coeus.sys.framework.rest.NotImplementedException;
import org.kuali.coeus.sys.framework.rest.ResourceNotFoundException;
import org.kuali.coeus.sys.framework.rest.UnprocessableEntityException;
import org.kuali.kra.award.budget.AwardBudgetExt;
import org.kuali.kra.award.contacts.AwardPerson;
import org.kuali.kra.award.contacts.AwardProjectPersonnelBean;
import org.kuali.kra.award.contacts.AwardSponsorContact;
import org.kuali.kra.award.customdata.AwardCustomData;
import org.kuali.kra.award.dao.AwardDao;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardService;
import org.kuali.kra.award.home.AwardSponsorTerm;
import org.kuali.kra.award.home.fundingproposal.AwardFundingProposalBean;
import org.kuali.kra.award.paymentreports.awardreports.AwardReportTerm;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.service.InstitutionalProposalService;
import org.kuali.kra.timeandmoney.service.TimeAndMoneyExistenceService;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kew.api.exception.InvalidActionTakenException;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kew.routeheader.DocumentRouteHeaderValue;
import org.kuali.rice.kew.routeheader.service.RouteHeaderService;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

@RequestMapping(value="/api/v2")
@Controller("awardController")
public class AwardController extends RestController implements InitializingBean {

    public static final String PENDING_VERSION_ERROR = "There exists a pending version of this award. It cannot be versioned.";
    @Autowired
    @Qualifier("awardDao")
    private AwardDao awardDao;

    @Autowired
    @Qualifier("commonApiService")
    private CommonApiService commonApiService;

    @Autowired
    @Qualifier("businessObjectService")
    private BusinessObjectService businessObjectService;

    @Autowired
    @Qualifier("documentService")
    private DocumentService documentService;

    @Autowired
    @Qualifier("globalVariableService")
    private GlobalVariableService globalVariableService;

    @Autowired
    @Qualifier("versionHistoryService")
    private VersionHistoryService versionHistoryService;

    @Autowired
    @Qualifier("versioningService")
    private VersioningService versioningService;

    @Autowired
    @Qualifier("awardService")
    private AwardService awardService;

    @Autowired
    @Qualifier("routeHeaderService")
    private RouteHeaderService routeHeaderService;

    @Autowired
    @Qualifier("institutionalProposalService")
    private InstitutionalProposalService institutionalProposalService;

    @Autowired
    @Qualifier("timeAndMoneyExistenceService")
    private TimeAndMoneyExistenceService timeAndMoneyExistenceService;

    @Autowired
    @Qualifier("restAuditLoggerFactory")
    private RestAuditLoggerFactory restAuditLoggerFactory;
    private List<String> awardDtoPersonProperties;
    private List<String> awardDtoProperties;

    @RequestMapping(method= RequestMethod.GET, value="/awards/{awardId}",
            consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    AwardDto getAward(@PathVariable Long awardId, @RequestParam(value = "includeBudgets", required = false) boolean includeBudgets) {
        Award award = getAwardDao().getAward(awardId);
        if(award == null) {
            throw new ResourceNotFoundException("Award with award id " + awardId + " not found.");
        }

        AwardDto awardDto = commonApiService.convertObject(award, AwardDto.class);
        if (!includeBudgets) {
            awardDto.setBudgets(new ArrayList<>());
        }
        return awardDto;
    }

    @RequestMapping(method= RequestMethod.GET, value="/awards", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    List<AwardDto> getAwardByCriteria(@RequestParam(value = "awardNumber", required = false) String awardNumber,
                                         @RequestParam(value = "awardHierarchy", required = false) String awardHierarchy,
                                         @RequestParam(value = "includeBudgets", required = false) boolean includeBudgets
                                         ) {
        List<AwardDto> awardDtos = new ArrayList<>();
        if(StringUtils.isBlank(awardNumber) && StringUtils.isBlank(awardHierarchy)) {
            throw new NotImplementedException("GET all awards not yet implemented in version 2.");
        }
        if(StringUtils.isNotBlank(awardNumber)) {
            List<Award> awards = getAwardDao().getAwardByAwardNumber(awardNumber);
            awardDtos = translateAwards(includeBudgets, awards);
        }
        else if(StringUtils.isNotBlank(awardHierarchy)) {
            List<Award> awards = getAwardDao().getAwardByAwardHierarchy(awardHierarchy);
            awardDtos = translateAwards(includeBudgets, awards);
        }
        return awardDtos;
    }

    @RequestMapping(method= RequestMethod.PUT, value="/awards/{awardId}", params="version",
            consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    AwardDto versionAward(@RequestBody AwardDto awardDto, @PathVariable Long awardId) throws Exception {
        Award award = getAwardDao().getAward(awardId);
        if(award == null) {
            throw new ResourceNotFoundException("Award with award id " + awardId + " not found.");
        }
        return versionAward(awardDto, award);
    }

    protected AwardDto versionAward(AwardDto awardDto, Award award) throws WorkflowException {
        AwardDocument oldAwardDocument = (AwardDocument) commonApiService.getDocumentFromDocId(Long.parseLong(award.getAwardDocument().getDocumentNumber()));
        String rootAwardNumber = awardService.getRootAwardNumber(award.getAwardNumber());
        if(timeAndMoneyExistenceService.validateTimeAndMoneyRule(award, rootAwardNumber)) {
            if (award.getAwardSequenceStatus().equalsIgnoreCase(Constants.PENDING)) {
                throw new UnprocessableEntityException(PENDING_VERSION_ERROR);
            }
            VersionHistory foundPending = versionHistoryService.findPendingVersion(award);
            if(foundPending != null) {
                throw new UnprocessableEntityException(PENDING_VERSION_ERROR);
            } else {
                award.setNewVersion(true);
                Award newAwardVersion = versioningService.createNewVersion(award);
                updateDataObjectFromDto(newAwardVersion, awardDto);
                defaultValues(newAwardVersion, awardDto);
                translateCollections(awardDto, newAwardVersion, newAwardVersion.getAwardDocument());
                AwardDocument newAwardDocument = awardService.generateAndPopulateAwardDocument(oldAwardDocument, newAwardVersion);
                newAwardDocument = (AwardDocument) documentService.saveDocument(newAwardDocument);
                awardService.updateAwardSequenceStatus(newAwardDocument.getAward(), VersionStatus.PENDING);
                versionHistoryService.updateVersionHistory(newAwardDocument.getAward(), VersionStatus.PENDING,
                        globalVariableService.getUserSession().getPrincipalId());
                return commonApiService.convertObject(newAwardDocument.getAward(), AwardDto.class);

           }
        } else {
            throw new UnprocessableEntityException("Award cannot be versioned. Pending T&M document exist for this award.");
        }
    }

    @RequestMapping(method= RequestMethod.GET, value="/awards/{awardId}/budgets/", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    List<AwardBudgetExtDto> getBudgets( @PathVariable Long awardId) {
        Award award = getAwardDao().getAward(awardId);
        if(award == null) {
            throw new ResourceNotFoundException("Award with award id " + awardId + " not found.");
        }
        return award.getBudgets().stream().map(budget ->
                        commonApiService.convertObject(budget, AwardBudgetExtDto.class)

        ).collect(Collectors.toList());
    }

    @RequestMapping(method= RequestMethod.GET, value="/award-budgets/{budgetId}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    AwardBudgetExtDto getAwardBudget(@PathVariable String budgetId) {
        AwardBudgetExt budget = getAwardDao().getAwardBudget(budgetId);
        if(budget == null) {
            throw new ResourceNotFoundException("Budget with budget id " + budgetId + " not found.");
        }
        return commonApiService.convertObject(budget, AwardBudgetExtDto.class);
    }

    @RequestMapping(method= RequestMethod.GET, value="/award-budgets", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    List<AwardBudgetExtDto> getAwardBudgetByStatus(@RequestParam(value = "budgetStatusCode", required = true) Integer budgetStatusCode) {
        List<AwardBudgetExt> budgets = getAwardDao().getAwardBudgetByStatusCode(budgetStatusCode);
        return budgets.stream().map(budget ->
                        commonApiService.convertObject(budget, AwardBudgetExtDto.class)
        ).collect(Collectors.toList());
    }

    @RequestMapping(method= RequestMethod.PUT, value="/award-budgets/{budgetId}/general-info/", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    void modifyAwardBudget(@RequestBody AwardBudgetGeneralInfoDto generalInfoDto, @PathVariable String budgetId) {
        AwardBudgetExt budget = getAwardDao().getAwardBudget(budgetId);
        if(budget == null) {
            throw new ResourceNotFoundException("Budget with budget id " + budgetId + " not found.");
        }
        updateDataObjectFromDto(budget, generalInfoDto);
        businessObjectService.save(budget);
    }

    @RequestMapping(method= RequestMethod.POST, value="/awards/",
            consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.CREATED)
    @ResponseBody
    AwardDto createAward(@RequestBody AwardDto awardDto) throws WorkflowException, InvocationTargetException, IllegalAccessException {
        commonApiService.clearErrors();
        Award award = commonApiService.convertObject(awardDto, Award.class);
        defaultValues(award, awardDto);
        AwardDocument awardDocument = (AwardDocument) documentService.getNewDocument(AwardDocument.class);
        awardDocument.setAward(award);
        translateCollections(awardDto, award, awardDocument);
        awardService.checkAwardNumber(award);
        AwardDocument newDocument = (AwardDocument) commonApiService.saveDocument(awardDocument);
        AwardDto newAwardDto = commonApiService.convertObject(newDocument.getAward(), AwardDto.class);
        newAwardDto.setDocNbr(newDocument.getDocumentNumber());
        newAwardDto.setDocStatus(newDocument.getDocumentHeader().getWorkflowDocument().getStatus().getLabel());
        versionHistoryService.updateVersionHistory(award, VersionStatus.PENDING, globalVariableService.getUserSession().getPrincipalName());
        RestAuditLogger auditLogger = restAuditLoggerFactory.getNewAuditLogger(AwardDto.class, awardDtoProperties);
        auditLogger.addNewItem(awardDto);
        auditLogger.saveAuditLog();
        return newAwardDto;
    }

    protected void translateCollections(AwardDto awardDto, Award award, AwardDocument awardDocument) {
        awardDocument.getAward().setProjectPersons(new ArrayList<>());
        final List<AwardPersonDto> projectPersons = awardDto.getProjectPersons();
        addPersons(projectPersons, awardDocument);
        addSponsorTerms(award, awardDto);
        addReportTerms(award, awardDto);
        addCustomData(awardDocument, award, awardDto);
        addFundingProposals(awardDto, award);
        translateSponsorContacts(awardDto, award);
        if(!globalVariableService.getMessageMap().getErrorMessages().isEmpty()) {
            String errors = commonApiService.getValidationErrors();
            throw new UnprocessableEntityException(errors);
        }
    }

    public void translateSponsorContacts(AwardDto awardDto, Award award) {
        if(CollectionUtils.isNotEmpty(awardDto.getSponsorContacts())) {
            award.setSponsorContacts(awardDto.getSponsorContacts().stream().map(awardSponsorContactDto -> {
                        AwardSponsorContact awardSponsorContact = commonApiService.convertObject(awardSponsorContactDto, AwardSponsorContact.class);
                        awardSponsorContact.setAwardNumber(award.getAwardNumber());
                        awardSponsorContact.setSequenceNumber(award.getSequenceNumber());
                        return awardSponsorContact;
                    }
            ).collect(Collectors.toList()));
        }
    }

    @RequestMapping(method= RequestMethod.DELETE, value="/awards/{awardId}",
            consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    void deleteAward(@PathVariable Long awardId) throws WorkflowException {
        commonApiService.clearErrors();
        AwardDocument awardDocument = getAwardDocumentById(awardId);
        DocumentRouteHeaderValue routeHeader = routeHeaderService.getRouteHeader(awardDocument.getDocumentHeader().getWorkflowDocument().getDocumentId());
        if (!routeHeader.getDocRouteStatus().equalsIgnoreCase(KewApiConstants.ROUTE_HEADER_CANCEL_CD)) {
            try {
                documentService.cancelDocument(awardDocument, KewApiConstants.ROUTE_HEADER_CANCEL_CD);
            }
            catch (InvalidActionTakenException e) {
                throw new UnprocessableEntityException("Award " + awardId + " is not in a state to be cancelled.");
            }
        }
    }

    @RequestMapping(method= RequestMethod.PUT, value="/awards/{awardId}", params="submit",
            consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    void submitDocument(@PathVariable Long awardId) throws WorkflowException {
        commonApiService.clearErrors();
        AwardDocument awardDocument = getAwardDocumentById(awardId);
        commonApiService.routeDocument(awardDocument);
    }


    @RequestMapping(method= RequestMethod.POST, value="/awards/{awardId}/award-persons/",
            consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.CREATED)
    @ResponseBody
    List<AwardPersonDto> addAwardPersons(@RequestBody List<AwardPersonDto> awardPersonsDto, @PathVariable Long awardId) throws WorkflowException {
        commonApiService.clearErrors();
        AwardDocument awardDocument = getAwardDocumentById(awardId);
        addPersons(awardPersonsDto, awardDocument);
        documentService.saveDocument(awardDocument);
        List<AwardPersonDto> awardPersonDtos = getAwardPersonDtos(awardDocument);
        return awardPersonDtos;
    }

    @RequestMapping(method= RequestMethod.GET, value="/v1/awards/{awardId}/award-persons/",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    List<AwardPersonDto> getAwardPersons(@PathVariable Long awardId) throws WorkflowException {
        commonApiService.clearErrors();
        AwardDocument awardDocument = getAwardDocumentById(awardId);
        return getAwardPersonDtos(awardDocument);
    }


    @RequestMapping(method= RequestMethod.DELETE, value="/v1/awards/{awardId}/award-persons/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    void deletePerson(@PathVariable Long awardId, @PathVariable Long id) throws WorkflowException {
        AwardDocument awardDocument = getAwardDocumentById(awardId);
        if(awardDocument.getAward().getProjectPersons() != null) {
            AwardPerson person = getAwardPerson(id, awardDocument.getAward().getProjectPersons());
            awardDocument.getAward().getProjectPersons().remove(person);
            commonApiService.saveDocument(awardDocument);
            RestAuditLogger auditLogger = restAuditLoggerFactory.getNewAuditLogger(AwardPersonDto.class, awardDtoPersonProperties);
            auditLogger.addDeletedItem(person);
            auditLogger.saveAuditLog();
        }
    }

    protected AwardPerson getAwardPerson(Long awardContactId, List<AwardPerson> awardPersons) {
        Optional<AwardPerson> person =  awardPersons.stream()
                .filter(awardPerson -> awardPerson.getAwardContactId().equals(awardContactId))
                .findFirst();
        if (!person.isPresent()) {
            throw new ResourceNotFoundException("Person with award contact id " + awardContactId + " not found.");
        }
        return person.get();
    }

    protected List<AwardPersonDto> getAwardPersonDtos(AwardDocument awardDocument) {
        List<AwardPersonDto> awardPersonDtos = awardDocument.getAward().getProjectPersons().stream().map(awardPerson ->
                        commonApiService.convertObject(awardPerson, AwardPersonDto.class)
        ).collect(Collectors.toList());
        return awardPersonDtos;
    }

    protected void addPersons(List<AwardPersonDto> awardPersonsDto, AwardDocument awardDocument) {
        if (awardPersonsDto != null) {
            List<AwardPerson> awardPersons = awardPersonsDto.stream().map(awardPersonDto -> {
                AwardPerson awardPerson = commonApiService.convertObject(awardPersonDto, AwardPerson.class);
                AwardProjectPersonnelBean awardProjectPersonnelBean = new AwardProjectPersonnelBean(awardDocument);
                awardProjectPersonnelBean.addPersonUnits(awardPerson);
                awardPerson.setAward(awardDocument.getAward());
                return awardPerson;
            }).collect(Collectors.toList());

            awardDocument.getAward().getProjectPersons().addAll(awardPersons);
        }

    }

    protected AwardDocument getAwardDocument(Long documentNumber) {
        Document document = getDocument(documentNumber);
        AwardDocument awardDocument;
        if (document instanceof AwardDocument) {
            awardDocument = (AwardDocument) document;
        } else {
            throw new ResourceNotFoundException("The docId used in the request was not of type " + document.getClass().getName());
        }
        return awardDocument;
    }

    protected AwardDocument getAwardDocumentById(Long awardId) {
        Award award = awardDao.getAward(awardId);
        if(award == null) {
            throw new ResourceNotFoundException("Award with award id " + awardId + " not found.");
        }
        return (AwardDocument) commonApiService.getDocumentFromDocId(Long.parseLong(award.getAwardDocument().getDocumentNumber()));
    }

    protected Document getDocument(Long documentNumber) {
        return commonApiService.getDocumentFromDocId(documentNumber);
    }

    public void addCustomData(AwardDocument awardDocument, Award award, AwardDto awardDto) {
        List<AwardCustomDataDto> awardCustomDataList = awardDto.getAwardCustomDataList();
        award.setAwardCustomDataList(new ArrayList<>());
        Map<String, CustomAttributeDocument> customAttributeDocuments = awardDocument.getCustomAttributeDocuments();
        if (awardDto.getAwardCustomDataList() != null) {
            awardCustomDataList.stream().forEach(customDataDto -> {
                String customAttributeId = customDataDto.getCustomAttributeId().toString();
                String customDataValue = customDataDto.getValue();
                List<AwardCustomData> customDataList = customAttributeDocuments.entrySet().stream().
                        filter(entry -> {
                                    CustomAttributeDocument customAttributeDoc = entry.getValue();
                                    return customAttributeId.equalsIgnoreCase(customAttributeDoc.getCustomAttribute().getId().toString());
                                }
                        ).map(entry -> {
                    CustomAttributeDocument customAttributeDoc = entry.getValue();
                    AwardCustomData customData = new AwardCustomData();
                    customData.setCustomAttributeId(customAttributeDoc.getId());
                    customData.setCustomAttribute(customAttributeDoc.getCustomAttribute());
                    customData.setValue(customDataValue);
                    customData.setAward(award);
                    return customData;
                }).collect(Collectors.toList());
                award.getAwardCustomDataList().addAll(customDataList);
            });
        }
    }

    public void addFundingProposals(AwardDto awardDto, Award award) {
        if(CollectionUtils.isNotEmpty(awardDto.getFundingProposals())) {
            awardDto.getFundingProposals().stream().forEach(awardFundingProposalDto -> {
                AwardFundingProposalBean fundingProposalBean = new AwardFundingProposalBean();
                fundingProposalBean.setMergeTypeCode(awardFundingProposalDto.getMergeTypeCode());
                InstitutionalProposal institutionalProposal = institutionalProposalService.getInstitutionalProposal(awardFundingProposalDto.getProposalId().toString());
                fundingProposalBean.setNewFundingProposal(institutionalProposal);
                fundingProposalBean.validateAndPerformFeed(new ArrayList<>(), award);
            });
        }
    }

    protected void addSponsorTerms(Award award, AwardDto awardDto) {
        List<AwardSponsorTermDto> sponsorTermsDtos = awardDto.getAwardSponsorTerms();
        award.setAwardSponsorTerms(new ArrayList<>());
        if (sponsorTermsDtos != null) {
            List<AwardSponsorTerm> sponsorTerms = sponsorTermsDtos.stream().map(awardSponsorTermDto ->
                getAwardSponsorTerm(awardSponsorTermDto.getSponsorTermId(), award)
            ).collect(Collectors.toList());
            award.getAwardSponsorTerms().addAll(sponsorTerms);
        }
    }

    protected AwardSponsorTerm getAwardSponsorTerm(Long sponsorTermId, Award award) {
        SponsorTerm sponsorTerm = getSponsorTerm(sponsorTermId);
        if (sponsorTerm == null) {
            throw new UnprocessableEntityException("Sponsor term " + sponsorTermId + " cannot be found.");
        }
        AwardSponsorTerm newAwardSponsorTerm = new AwardSponsorTerm(sponsorTermId, sponsorTerm);
        newAwardSponsorTerm.setAward(award);
        return newAwardSponsorTerm;
    }

    protected void addReportTerms(Award award, AwardDto awardDto) {
        List<AwardReportTermDto> awardReportTermDtos = awardDto.getAwardReportTerms();
        award.setAwardReportTermItems(new ArrayList<>());
        if(awardReportTermDtos != null) {
            List<AwardReportTerm> awardReportTerms = awardReportTermDtos.stream().map(awardReportTermDto -> {
                        AwardReportTerm awardReportTerm = commonApiService.convertObject(awardReportTermDto, AwardReportTerm.class);
                        awardReportTerm.setAward(award);
                        return awardReportTerm;
                    }
            ).collect(Collectors.toList());
            award.getAwardReportTermItems().addAll(awardReportTerms);
        }
    }

    protected SponsorTerm getSponsorTerm(Long sponsorTermId) {
        return businessObjectService.findBySinglePrimaryKey(SponsorTerm.class, sponsorTermId.toString());
    }

    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }

    public void setRouteHeaderService(RouteHeaderService routeHeaderService) {
        this.routeHeaderService = routeHeaderService;
    }

    public void setRestAuditLoggerFactory(RestAuditLoggerFactory restAuditLoggerFactory) {
        this.restAuditLoggerFactory = restAuditLoggerFactory;
    }

    public void setCommonApiService(CommonApiService commonApiService) {
        this.commonApiService = commonApiService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    protected List<String> getDtoProperties(Class dtoClass) throws IntrospectionException {
        return Arrays.asList(Introspector.getBeanInfo(dtoClass).getPropertyDescriptors()).stream()
                .map(PropertyDescriptor::getName)
                .filter(prop -> !"class".equals(prop))
                .collect(Collectors.toList());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        awardDtoProperties = getDtoProperties(AwardDto.class);
        awardDtoPersonProperties = getDtoProperties(AwardPersonDto.class);
    }

    protected void defaultValues(Award award, AwardDto awardDto) {
        if(award.getAwardAmountInfos() == null) award.initializeAwardAmountInfoObjects();
        if(award.getAwardCostShares() == null) award.setAwardCostShares(new ArrayList<>());
        if(award.getApprovedForeignTravelTrips() == null) award.setApprovedForeignTravelTrips(new ArrayList<>());
        if(award.getFundingProposals() == null) award.setFundingProposals(new ArrayList<>());
        if(award.getAllFundingProposals() == null) award.setAllFundingProposals(new ArrayList<>());
        if(award.getAwardComments() == null)  award.setAwardComments(new ArrayList<>());
        if(award.getAwardReportTermItems() == null) award.setAwardReportTermItems(new ArrayList<>());
        if(award.getAwardSponsorTerms() == null) award.setAwardSponsorTerms(new ArrayList<>());
        if(award.getSponsorContacts() == null) award.setSponsorContacts(new ArrayList<>());
        if(award.getAwardFandaRate() == null) award.setAwardFandaRate(new ArrayList<>());
        if(award.getAwardDirectFandADistributions() == null) award.setAwardDirectFandADistributions(new ArrayList<>());
        if(award.getAwardApprovedSubawards() == null) award.setAwardApprovedSubawards(new ArrayList<>());
        if(award.getKeywords() == null) award.setKeywords(new ArrayList<>());
        if(award.getProjectPersons() == null) award.setProjectPersons(new ArrayList<>());
        if(award.getAwardUnitContacts() == null) award.setAwardUnitContacts(new ArrayList<>());
        if(award.getSpecialReviews() == null) award.setSpecialReviews(new ArrayList<>());
        if(award.getApprovedEquipmentItems() == null) award.setApprovedEquipmentItems(new ArrayList<>());
        if(award.getPaymentScheduleItems() == null) award.setPaymentScheduleItems(new ArrayList<>());
        if(award.getAwardTransferringSponsors() == null) award.setAwardTransferringSponsors(new ArrayList<>());
        if(award.getAwardCloseoutItems() == null) award.setAwardCloseoutItems(new ArrayList<>());
        if(award.getAwardCloseoutNewItems() == null) award.setAwardCloseoutNewItems(new ArrayList<>());
        if(award.getAwardNotepads() == null) award.setAwardNotepads(new ArrayList<>());
        if(award.getAwardAttachments() == null) award.setAttachments(new ArrayList<>());
        if(award.getAwardBudgetLimits() == null) award.setAwardBudgetLimits(new ArrayList<>());
        if(award.getAwardCustomDataList() == null) award.setAwardCustomDataList(new ArrayList<>());

        award.setProjectEndDate(awardDto.getProjectEndDate());
        if(award.getAwardNumber() == null) award.setAwardNumber(Award.DEFAULT_AWARD_NUMBER);
        award.setSequenceNumber(1);
        award.setApprovedEquipmentIndicator(Award.NO_FLAG);
        award.setApprovedForeignTripIndicator(Award.NO_FLAG);
        award.setSubContractIndicator(Award.NO_FLAG);
        award.setCostSharingIndicator(Award.NO_FLAG);
        award.setIdcIndicator(Award.NO_FLAG);
        award.setPaymentScheduleIndicator(Award.NO_FLAG);
        award.setScienceCodeIndicator(Award.NO_FLAG);
        award.setSpecialReviewIndicator(Award.NO_FLAG);
        award.setTransferSponsorIndicator(Award.NO_FLAG);
        award.setCurrentActionComments("");
        award.setNewVersion(false);
        award.setAwardSequenceStatus(VersionStatus.PENDING.name());
    }

    protected List<AwardDto> translateAwards(boolean includeBudgets, List<Award> awards) {
        List<AwardDto> awardDtos = awards.stream().map(award -> {
                AwardDto awardDto = commonApiService.convertObject(award, AwardDto.class);
                if (!includeBudgets) {
                    awardDto.setBudgets(new ArrayList<>());
                }
                awardDto.setDocNbr(award.getAwardDocument().getDocumentNumber());
                awardDto.setStatusCode(award.getStatusCode());
                return awardDto;
            }).collect(Collectors.toList());
        return awardDtos;
    }

    protected void updateDataObjectFromDto(Object existingDataObject, Object input) {
        Configuration mooConfig = new Configuration();
        mooConfig.setSourcePropertiesRequired(false);
        Moo moo = new Moo(mooConfig);
        moo.update(input, existingDataObject);
    }

    public AwardDao getAwardDao() {
        return awardDao;
    }

}
