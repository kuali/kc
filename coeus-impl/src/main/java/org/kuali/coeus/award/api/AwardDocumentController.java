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

import org.kuali.coeus.award.dto.*;
import org.kuali.coeus.common.api.document.service.CommonApiService;
import org.kuali.coeus.common.framework.custom.attr.CustomAttributeDocument;
import org.kuali.coeus.common.framework.sponsor.term.SponsorTerm;
import org.kuali.coeus.common.framework.version.VersionStatus;
import org.kuali.coeus.sys.framework.controller.rest.RestController;
import org.kuali.coeus.sys.framework.controller.rest.audit.RestAuditLogger;
import org.kuali.coeus.sys.framework.controller.rest.audit.RestAuditLoggerFactory;
import org.kuali.coeus.sys.framework.rest.ResourceNotFoundException;
import org.kuali.coeus.sys.framework.rest.UnprocessableEntityException;
import org.kuali.kra.award.contacts.AwardPerson;
import org.kuali.kra.award.contacts.AwardProjectPersonnelBean;
import org.kuali.kra.award.customdata.AwardCustomData;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardComment;
import org.kuali.kra.award.home.AwardSponsorTerm;
import org.kuali.kra.award.paymentreports.awardreports.AwardReportTerm;
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
import org.springframework.util.AutoPopulatingList;
import org.springframework.web.bind.annotation.*;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

@RequestMapping(value="/api")
@Controller("awardDocumentController")
public class AwardDocumentController<T> extends RestController implements InitializingBean {

    public static final String SPONSOR_TERM_ID = "sponsorTermId";
    public static final int AWARD_TYPE_GRANT = 1;
    @Autowired
    @Qualifier("documentService")
    private DocumentService documentService;

    @Autowired
    @Qualifier("routeHeaderService")
    private RouteHeaderService routeHeaderService;

    @Autowired
    @Qualifier("restAuditLoggerFactory")
    private RestAuditLoggerFactory restAuditLoggerFactory;

    @Autowired
    @Qualifier("commonApiService")
    private CommonApiService commonApiService;

    @Autowired
    @Qualifier("businessObjectService")
    private BusinessObjectService businessObjectService;
    private List<String> awardDtoPersonProperties;
    private List<String> awardDtoProperties;

    @RequestMapping(method= RequestMethod.POST, value="/v1/award-documents/",
            consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.CREATED)
    @ResponseBody
    AwardDto createAward(@RequestBody AwardDto awardDto) throws WorkflowException, InvocationTargetException, IllegalAccessException {
        commonApiService.clearErrors();
        Award award = commonApiService.convertObject(awardDto, Award.class);
        defaultValues(award, awardDto);
        AwardDocument awardDocument = (AwardDocument) documentService.getNewDocument(AwardDocument.class);
        awardDocument.setAward(award);
        final List<AwardPersonDto> projectPersons = awardDto.getProjectPersons();
        awardDocument.getAward().setProjectPersons(new ArrayList<>());
        addPersons(projectPersons, awardDocument);
        addSponsorTerms(award, awardDto);
        addReportTerms(award, awardDto);
        addCustomData(awardDocument, awardDto);
        AwardDocument newDocument = (AwardDocument) commonApiService.saveDocument(awardDocument);
        AwardDto newAwardDto = commonApiService.convertObject(newDocument.getAward(), AwardDto.class);
        newAwardDto.setDocNbr(newDocument.getDocumentNumber());
        newAwardDto.setDocStatus(newDocument.getDocumentHeader().getWorkflowDocument().getStatus().getLabel());
        RestAuditLogger auditLogger = restAuditLoggerFactory.getNewAuditLogger(AwardDto.class, awardDtoProperties);
        auditLogger.addNewItem(awardDto);
        auditLogger.saveAuditLog();
        return newAwardDto;
    }

    @RequestMapping(method= RequestMethod.DELETE, value="/v1/award-documents/{documentNumber}",
            consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    void deleteAward(@PathVariable Long documentNumber) throws WorkflowException {
        commonApiService.clearErrors();
        AwardDocument awardDocument = getAwardDocument(documentNumber);
        DocumentRouteHeaderValue routeHeader = routeHeaderService.getRouteHeader(awardDocument.getDocumentHeader().getWorkflowDocument().getDocumentId());
        if (!routeHeader.getDocRouteStatus().equalsIgnoreCase(KewApiConstants.ROUTE_HEADER_CANCEL_CD)) {
            try {
                documentService.cancelDocument(awardDocument, KewApiConstants.ROUTE_HEADER_CANCEL_CD);
            }
            catch (InvalidActionTakenException e) {
                throw new UnprocessableEntityException("Document " + documentNumber + " is not in a state to be cancelled.");
            }
        }
    }

    @RequestMapping(method= RequestMethod.GET, value="/v1/award-documents/{documentNumber}",
            consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    AwardDto getAward(@PathVariable Long documentNumber) {
        AwardDocument awardDocument = getAwardDocument(documentNumber);
        Award award = awardDocument.getAward();
        AwardDto awardDto = commonApiService.convertObject(award, AwardDto.class);
        awardDto.setDocNbr(awardDocument.getDocumentNumber());
        awardDto.setStatusCode(award.getStatusCode());
        awardDto.setDocStatus(awardDocument.getDocumentHeader().getWorkflowDocument().getStatus().getLabel());
        return awardDto;
    }

    @RequestMapping(method= RequestMethod.PUT, value="/v1/award-documents/{documentNumber}", params="submit",
            consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    void submitDocument(@PathVariable Long documentNumber) throws WorkflowException {
        commonApiService.clearErrors();
        AwardDocument awardDocument = getAwardDocument(documentNumber);
        commonApiService.routeDocument(awardDocument);
    }

    @RequestMapping(method= RequestMethod.POST, value="/v1/award-documents/{documentNumber}/award-persons/",
            consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.CREATED)
    @ResponseBody
    List<AwardPersonDto> addAwardPersons(@RequestBody List<AwardPersonDto> awardPersonsDto, @PathVariable Long documentNumber) throws WorkflowException {
        commonApiService.clearErrors();
        AwardDocument awardDocument = getAwardDocument(documentNumber);
        addPersons(awardPersonsDto, awardDocument);
        documentService.saveDocument(awardDocument);
        List<AwardPersonDto> awardPersonDtos = getAwardPersonDtos(awardDocument);
        return awardPersonDtos;
    }

    @RequestMapping(method= RequestMethod.GET, value="/v1/award-documents/{documentNumber}/award-persons/",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    List<AwardPersonDto> getAwardPersons(@PathVariable Long documentNumber) throws WorkflowException {
        commonApiService.clearErrors();
        AwardDocument awardDocument = getAwardDocument(documentNumber);
        return getAwardPersonDtos(awardDocument);
    }


    @RequestMapping(method= RequestMethod.DELETE, value="/v1/award-documents/{documentNumber}/award-persons/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    void deletePerson(@PathVariable Long documentNumber, @PathVariable Long id) throws WorkflowException {
        AwardDocument awardDocument = getAwardDocument(documentNumber);
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
        AwardPerson person =  awardPersons.stream().filter(
                awardPerson -> awardPerson.getAwardContactId().compareTo(awardContactId) == 0).findFirst().orElse(null);
        if (person == null) {
            throw new ResourceNotFoundException("Person with award contact id " + awardContactId + " not found.");
        }
        return person;
    }

    protected List<AwardPersonDto> getAwardPersonDtos(AwardDocument awardDocument) {
        List<AwardPersonDto> awardPersonDtos = new ArrayList<>();
        awardDocument.getAward().getProjectPersons().stream().forEach(awardPerson -> {
            AwardPersonDto awardPersonSavedDto = commonApiService.convertObject(awardPerson, AwardPersonDto.class);
            awardPersonDtos.add(awardPersonSavedDto);
        });
        return awardPersonDtos;
    }

    protected void addPersons(List<AwardPersonDto> awardPersonsDto, AwardDocument awardDocument) {
        if (awardPersonsDto != null) {
            awardPersonsDto.stream().forEach(awardPersonDto -> {
                AwardPerson awardPerson = commonApiService.convertObject(awardPersonDto, AwardPerson.class);
                AwardProjectPersonnelBean awardProjectPersonnelBean = new AwardProjectPersonnelBean(awardDocument);
                awardProjectPersonnelBean.addPersonUnits(awardPerson);
                awardDocument.getAward().add(awardPerson);
            });
        }
    }

    protected AwardDocument getAwardDocument(Long documentNumber) {
        Document document = getDocument(documentNumber);
        AwardDocument awardDocument;
        if (document instanceof AwardDocument) {
            awardDocument = (AwardDocument) document;
        } else {
            throw new ResourceNotFoundException("The docId used in the request was not of type AwardDocument.");
        }
        return awardDocument;
    }

    protected Document getDocument(Long documentNumber) {
        return commonApiService.getDocumentFromDocId(documentNumber);
    }

    public void addCustomData(AwardDocument awardDocument, AwardDto awardDto) {
        List<AwardCustomDataDto> awardCustomDataList = awardDto.getAwardCustomDataList();
        awardDocument.getAward().setAwardCustomDataList(new ArrayList<>());
        Map<String, CustomAttributeDocument> customAttributeDocuments = awardDocument.getCustomAttributeDocuments();
        if (awardDto.getAwardCustomDataList() != null) {
            awardCustomDataList.stream().forEach(customDataDto -> {
                String customAttributeId = customDataDto.getCustomAttributeId().toString();
                String customDataValue = customDataDto.getValue();
                AwardCustomData customData = new AwardCustomData();
                customAttributeDocuments.entrySet().stream().forEach(entry -> {
                    CustomAttributeDocument customAttributeDoc = entry.getValue();
                    if (customAttributeId.equalsIgnoreCase(customAttributeDoc.getCustomAttribute().getId().toString())) {
                        customData.setCustomAttributeId(customAttributeDoc.getId());
                        customData.setCustomAttribute(customAttributeDoc.getCustomAttribute());
                        customData.setValue(customDataValue);
                        customData.setAward(awardDocument.getAward());
                        awardDocument.getAward().getAwardCustomDataList().add(customData);
                    }
                });
            });
        }
    }

    protected void defaultValues(Award award, AwardDto awardDto) {
        award.setAwardTypeCode(AWARD_TYPE_GRANT);
        award.setProjectEndDate(awardDto.getProjectEndDate());
        award.setAwardNumber(Award.DEFAULT_AWARD_NUMBER);
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
        award.setAwardComments(new AutoPopulatingList<>(AwardComment.class));
        award.setCurrentActionComments("");
        award.setNewVersion(false);
        award.setAwardSequenceStatus(VersionStatus.PENDING.name());
    }

    protected void addSponsorTerms(Award award, AwardDto awardDto) {
        List<AwardSponsorTermDto> sponsorTermsDtos = awardDto.getAwardSponsorTerms();
        award.setAwardSponsorTerms(new ArrayList<>());
        if (sponsorTermsDtos != null) {
            sponsorTermsDtos.stream().forEach(awardSponsorTermDto -> {
                addAwardSponsorTerm(awardSponsorTermDto.getSponsorTermId(), award);
            });
        }
    }

    protected void addAwardSponsorTerm(Long sponsorTermId, Award award) {
        SponsorTerm sponsorTerm = getSponsorTerm(sponsorTermId);
        if (sponsorTerm == null) {
            throw new UnprocessableEntityException("Sponsor term " + sponsorTermId + " cannot be found.");
        }
        AwardSponsorTerm newAwardSponsorTerm = new AwardSponsorTerm(sponsorTermId, sponsorTerm);
        award.getAwardSponsorTerms().add(newAwardSponsorTerm);
    }

    protected void addReportTerms(Award award, AwardDto awardDto) {
        List<AwardReportTermDto> awardReportTerms = awardDto.getAwardReportTerms();
        award.setAwardReportTermItems(new ArrayList<>());
        if(awardReportTerms != null) {
            awardReportTerms.stream().forEach(awardReportTermDto -> {
                AwardReportTerm awardReportTerm = commonApiService.convertObject(awardReportTermDto, AwardReportTerm.class);
                award.add(awardReportTerm);
            });
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
}

