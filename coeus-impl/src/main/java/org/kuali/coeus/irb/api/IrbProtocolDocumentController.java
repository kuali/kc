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

package org.kuali.coeus.irb.api;

import org.kuali.coeus.common.api.document.service.CommonApiService;
import org.kuali.coeus.common.api.rolodex.RolodexContract;
import org.kuali.coeus.common.api.rolodex.RolodexService;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.irb.api.dto.IrbProtocolActionDto;
import org.kuali.coeus.irb.api.dto.IrbProtocolDto;
import org.kuali.coeus.irb.api.dto.IrbProtocolPersonDto;
import org.kuali.coeus.irb.api.dto.IrbProtocolSubmissionDto;
import org.kuali.coeus.sys.framework.controller.rest.RestController;
import org.kuali.coeus.sys.framework.controller.rest.audit.RestAuditLoggerFactory;
import org.kuali.coeus.sys.framework.rest.NotImplementedException;
import org.kuali.coeus.sys.framework.rest.ResourceNotFoundException;
import org.kuali.coeus.sys.framework.rest.UnprocessableEntityException;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.ProtocolStatus;
import org.kuali.kra.irb.actions.approve.ProtocolApproveService;
import org.kuali.kra.irb.actions.delete.ProtocolDeleteService;
import org.kuali.kra.irb.actions.expeditedapprove.ProtocolExpeditedApproveBean;
import org.kuali.kra.irb.actions.grantexemption.ProtocolGrantExemptionBean;
import org.kuali.kra.irb.actions.grantexemption.ProtocolGrantExemptionService;
import org.kuali.kra.irb.actions.noreview.ProtocolReviewNotRequiredBean;
import org.kuali.kra.irb.actions.noreview.ProtocolReviewNotRequiredService;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.actions.submit.ProtocolSubmitAction;
import org.kuali.kra.irb.actions.submit.ProtocolSubmitActionService;
import org.kuali.kra.irb.personnel.ProtocolPerson;
import org.kuali.kra.irb.protocol.ProtocolNumberService;
import org.kuali.kra.protocol.actions.ProtocolActionBase;
import org.kuali.kra.protocol.personnel.ProtocolPersonBase;
import org.kuali.kra.protocol.personnel.ProtocolPersonnelService;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kew.api.exception.InvalidActionTakenException;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kew.routeheader.DocumentRouteHeaderValue;
import org.kuali.rice.kew.routeheader.service.RouteHeaderService;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.util.ErrorMessage;
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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@RequestMapping(value="/api/v1/")
@Controller("irbProtocolDocumentController")
public class IrbProtocolDocumentController extends RestController implements  InitializingBean {
    public static final String PROTOCOL_PERSON_ROLE = "protocolPersonRole";
    public static final String ROLODEX = "rolodex";
    public static final String ACTION_ERROR_MESSAGE = "This action cannot be taken on a document that is not in progress.";

    @Autowired
    @Qualifier("restAuditLoggerFactory")
    private RestAuditLoggerFactory restAuditLoggerFactory;

    @Autowired
    @Qualifier("documentService")
    private DocumentService documentService;

    @Autowired
    @Qualifier("protocolNumberService")
    private ProtocolNumberService protocolNumberService;

    @Autowired
    @Qualifier("protocolPersonnelService")
    private ProtocolPersonnelService protocolPersonnelService;

    @Autowired
    @Qualifier("routeHeaderService")
    private RouteHeaderService routeHeaderService;

    @Autowired
    @Qualifier("commonApiService")
    private CommonApiService commonApiService;

    @Autowired
    @Qualifier("protocolSubmitActionService")
    private ProtocolSubmitActionService protocolSubmitActionService;

    @Autowired
    @Qualifier("protocolDeleteService")
    private ProtocolDeleteService protocolDeleteService;

    @Autowired
    @Qualifier("protocolApproveService")
    private ProtocolApproveService protocolApproveService;

    @Autowired
    @Qualifier("protocolGrantExemptionService")
    private ProtocolGrantExemptionService protocolGrantExemptionService;

    @Autowired
    @Qualifier("protocolReviewNotRequiredService")
    private ProtocolReviewNotRequiredService protocolReviewNotRequiredService;

    @Autowired
    @Qualifier("kcPersonService")
    private KcPersonService kcPersonService;

    @Autowired
    @Qualifier("rolodexService")
    private RolodexService rolodexService;


    private List<String> irbProtocolDtoProperties;
    private List<String> irbPersonDtoProperties;
    private List<String> irbProtocolActionDtoProperties;

    protected List<String> getDtoProperties(Class dtoClass) throws IntrospectionException {
        return Arrays.asList(Introspector.getBeanInfo(dtoClass).getPropertyDescriptors()).stream()
                .map(PropertyDescriptor::getName)
                .filter(prop -> !"class".equals(prop))
                .collect(Collectors.toList());
    }

    @RequestMapping(method= RequestMethod.POST, value="irb-protocol-documents/",
            consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.CREATED)
    @ResponseBody
    public IrbProtocolDto createProtocol(@RequestBody IrbProtocolDto protocolDto) throws WorkflowException, IllegalAccessException {
        commonApiService.clearErrors();
        ProtocolDocument protocolDocument = (ProtocolDocument) documentService.getNewDocument(ProtocolDocument.class);
        Protocol protocol = commonApiService.convertObject(protocolDto, Protocol.class);
        protocolDocument.setProtocol(protocol);
        protocol.setProtocolDocument(protocolDocument);
        protocolDocument.initialize();
        String protocolNumber = protocolNumberService.generateProtocolNumber();
        protocol.setProtocolNumber(protocolNumber);
        protocolDocument.initializeProtocolLocation();
        createInitialProtocolAction(protocol);
        addProtocolPersonnel(protocol, protocolDto.getProtocolPersons());
        saveDocument(protocolDocument);
        IrbProtocolDto irbProtocolDto = commonApiService.convertObject(protocolDocument.getProtocol(), IrbProtocolDto.class);
        irbProtocolDto.setDocNbr(protocolDocument.getDocumentNumber());
        irbProtocolDto.setStatus(protocolDocument.getProtocol().getProtocolStatusCode());
        return irbProtocolDto;
    }

    public void addProtocolPersonnel(Protocol protocol, List<IrbProtocolPersonDto> protocolDtos) {
        protocol.setProtocolPersons(new ArrayList<>());
        protocolDtos.stream().forEach(personDto -> {
            addPerson(protocol, personDto);
        });
    }

    public void addPerson(Protocol protocol, IrbProtocolPersonDto personDto) {
        final ProtocolPerson protocolPerson = commonApiService.convertObject(personDto, ProtocolPerson.class);
        final String personId = personDto.getPersonId();
        final Integer rolodexId = personDto.getRolodexId();
        commonApiService.validatePerson(personId, rolodexId);
        if(protocolPerson.isPrincipalInvestigator()) {
            final ProtocolPersonBase principalInvestigator = createPrincipalInvestigator(personId, rolodexId, protocol);
            principalInvestigator.setAffiliationTypeCode(protocolPerson.getAffiliationTypeCode());
            protocolPersonnelService.addProtocolPerson(protocol, principalInvestigator);
        } else {
            protocolPerson.setPersonName(getPersonName(personId, rolodexId));
            protocolPersonnelService.addProtocolPerson(protocol, protocolPerson);
        }
    }

    protected ProtocolPersonBase createPrincipalInvestigator(String personId, Integer rolodexId, Protocol protocol) {
        ProtocolPersonBase pi = new ProtocolPerson();
        pi.setProtocolPersonRoleId(Constants.PRINCIPAL_INVESTIGATOR_ROLE);
        pi.setProtocolNumber(protocol.getProtocolNumber());
        pi.setSequenceNumber(0);
        pi.refreshReferenceObject(PROTOCOL_PERSON_ROLE);
        if (personId == null) {
            pi.refreshReferenceObject(ROLODEX);
            pi.setRolodexId(Integer.valueOf(rolodexId));
        } else {
            pi.setPersonId(personId);
        }
        pi.setPersonName(getPersonName(personId, rolodexId));
        return pi;
    }

    protected String getPersonName(String personId, Integer rolodexId) {
        if (personId == null) {
            RolodexContract rolodex = rolodexService.getRolodex(rolodexId);
            return rolodex.getFullName();
        } else {
            KcPerson person = kcPersonService.getKcPersonByPersonId(personId);
            return person.getFullName();
        }
    }

    public void createInitialProtocolAction(Protocol protocol) {
        protocol.getProtocolActions().clear();
        ProtocolActionBase protocolAction = new ProtocolAction(protocol, null, ProtocolActionType.PROTOCOL_CREATED);
        protocolAction.setComments(ProtocolActionType.PROTOCOL_CREATED);
        protocol.getProtocolActions().add(protocolAction);
    }

    @RequestMapping(method= RequestMethod.DELETE, value="irb-protocol-documents/{documentNumber}",
            consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @ResponseBody
    void deleteProtocol(@PathVariable Long documentNumber) throws WorkflowException {
        commonApiService.clearErrors();
        ProtocolDocument protocolDocument = getProtocolDocument(documentNumber);
        DocumentRouteHeaderValue routeHeader = routeHeaderService.getRouteHeader(protocolDocument.getDocumentHeader().getWorkflowDocument().getDocumentId());
        if (!routeHeader.getDocRouteStatus().equalsIgnoreCase(KewApiConstants.ROUTE_HEADER_CANCEL_CD)) {
            try {
                protocolDeleteService.delete(protocolDocument);
            }
            catch (InvalidActionTakenException e) {
                throw new UnprocessableEntityException("Document " + documentNumber + " is not in a state to be cancelled.", e);
            }
        }
    }

    @RequestMapping(method= RequestMethod.GET, value="irb-protocol-documents/{documentNumber}",
            consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    IrbProtocolDto getProtocol(@PathVariable Long documentNumber) {
        commonApiService.clearErrors();
        ProtocolDocument protocolDocument = getProtocolDocument(documentNumber);
        Protocol protocol = protocolDocument.getProtocol();
        IrbProtocolDto protocolDto = commonApiService.convertObject(protocol, IrbProtocolDto.class);
        protocolDto.setDocNbr(protocolDocument.getDocumentNumber());
        protocolDto.setStatus(protocolDocument.getProtocol().getProtocolStatusCode());
        return protocolDto;
    }

    @RequestMapping(method= RequestMethod.POST, value="irb-protocol-documents/{documentNumber}/submissions/",
            consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    IrbProtocolSubmissionDto takeAction(@RequestBody IrbProtocolActionDto protocolActionDto, @PathVariable Long documentNumber) throws Exception {
        commonApiService.clearErrors();
        ProtocolDocument protocolDocument = getProtocolDocument(documentNumber);
        List<ErrorMessage> errors = commonApiService.getAuditErrors(protocolDocument);
        if (errors.size() == 0) {
            String actionTypeCode = protocolActionDto.getActionCode();
            final Protocol protocol = protocolDocument.getProtocol();
            if (actionTypeCode.equalsIgnoreCase(ProtocolActionType.SUBMIT_TO_IRB)) {
                if (protocol.getProtocolStatus().getProtocolStatusCode().equalsIgnoreCase(ProtocolStatus.IN_PROGRESS)) {
                    ProtocolSubmitAction submitAction = commonApiService.convertObject(protocolActionDto, ProtocolSubmitAction.class);
                    submitAction.setNewCommitteeId(submitAction.getCommitteeId());
                    protocol.setInitialSubmissionDate(protocolActionDto.getInitialSubmissionDate());
                    Timestamp actionTimestamp = protocolActionDto.getActionDate()!= null? new Timestamp(protocolActionDto.getActionDate().getTime()) : null;
                    protocolSubmitActionService.submitToIrbForReview(protocolDocument, submitAction, actionTimestamp);
                    // Submission date is auto generated on ProtocolSubmission but is not if the value != null in Protocol.java
                    // so make them equal in this case.
                    protocol.getProtocolSubmission().setSubmissionDate(protocol.getInitialSubmissionDate());
                    commonApiService.routeDocument(protocolDocument);
                } else {
                    throw new UnprocessableEntityException(ACTION_ERROR_MESSAGE);
                }
            } else if (actionTypeCode.equalsIgnoreCase(ProtocolActionType.EXPEDITE_APPROVAL)) {
                if (protocol.getProtocolStatus().getProtocolStatusCode().equalsIgnoreCase(ProtocolStatus.SUBMITTED_TO_IRB)) {
                    ProtocolExpeditedApproveBean approveBean = commonApiService.convertObject(protocolActionDto, ProtocolExpeditedApproveBean.class);
                    approveBean.setAssignToAgenda(false);
                    approveBean.setExpirationDate(protocolApproveService.buildExpirationDate(protocol, approveBean.getApprovalDate()));
                    protocolApproveService.grantExpeditedApproval(protocolDocument, approveBean);
                } else {
                    throw new UnprocessableEntityException(ACTION_ERROR_MESSAGE);
                }
            } else if (actionTypeCode.equalsIgnoreCase(ProtocolActionType.GRANT_EXEMPTION)) {
                if (protocol.getProtocolStatus().getProtocolStatusCode().equalsIgnoreCase(ProtocolStatus.SUBMITTED_TO_IRB)) {
                    ProtocolGrantExemptionBean exemptionBean = commonApiService.convertObject(protocolActionDto, ProtocolGrantExemptionBean.class);
                    protocolGrantExemptionService.grantExemption(protocol, exemptionBean);
                } else {
                    throw new UnprocessableEntityException(ACTION_ERROR_MESSAGE);
                }
            }
            else if (actionTypeCode.equalsIgnoreCase(ProtocolActionType.IRB_REVIEW_NOT_REQUIRED)) {
                if (protocol.getProtocolStatus().getProtocolStatusCode().equalsIgnoreCase(ProtocolStatus.SUBMITTED_TO_IRB)) {
                    ProtocolReviewNotRequiredBean reviewNotRequiredBean = commonApiService.convertObject(protocolActionDto, ProtocolReviewNotRequiredBean.class);
                    protocolReviewNotRequiredService.reviewNotRequired(protocolDocument, reviewNotRequiredBean);
                } else {
                    throw new UnprocessableEntityException(ACTION_ERROR_MESSAGE);
                }
            }
            else {
                throw new NotImplementedException();
            }
        }
        ProtocolSubmission protocolSubmission = protocolDocument.getProtocol().getProtocolSubmission();
        IrbProtocolSubmissionDto submissionDto = commonApiService.convertObject(protocolSubmission, IrbProtocolSubmissionDto.class);
        submissionDto.setApprovalDate(protocolDocument.getProtocol().getApprovalDate());
        return submissionDto;
    }

    private ProtocolDocument getProtocolDocument(Long documentNumber) {
        Document document = commonApiService.getDocumentFromDocId(documentNumber);
        ProtocolDocument protocolDocument;
        if (ProtocolDocument.class.isAssignableFrom(document.getClass())) {
            protocolDocument = (ProtocolDocument) document;
        } else {
            throw new ResourceNotFoundException("The docId used in the request was not of type ProtocolDocument.");
        }
        return protocolDocument;
    }

    protected void saveDocument(ProtocolDocument protocolDocument) throws WorkflowException {
        final WorkflowDocument workflowDocument = protocolDocument.getDocumentHeader().getWorkflowDocument();
        // Rice lets you save a cancelled doc, so check status before saving.
        if(commonApiService.isDocInModifiableState(workflowDocument)) {
            commonApiService.saveDocument(protocolDocument);
        } else {
            throw new UnprocessableEntityException("Document " + protocolDocument.getDocumentNumber() + " with status " + workflowDocument.getStatus() +
                    " is not in a state to be saved.");
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        irbProtocolDtoProperties = getDtoProperties(IrbProtocolDto.class);
        irbPersonDtoProperties = getDtoProperties(IrbProtocolPersonDto.class);
        irbProtocolActionDtoProperties = getDtoProperties(IrbProtocolActionDto.class);
    }

    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }

    public void setProtocolNumberService(ProtocolNumberService protocolNumberService) {
        this.protocolNumberService = protocolNumberService;
    }

    public void setProtocolPersonnelService(ProtocolPersonnelService protocolPersonnelService) {
        this.protocolPersonnelService = protocolPersonnelService;
    }

    public void setRouteHeaderService(RouteHeaderService routeHeaderService) {
        this.routeHeaderService = routeHeaderService;
    }

    public void setCommonApiService(CommonApiService commonApiService) {
        this.commonApiService = commonApiService;
    }

    public void setProtocolSubmitActionService(ProtocolSubmitActionService protocolSubmitActionService) {
        this.protocolSubmitActionService = protocolSubmitActionService;
    }

    public void setProtocolApproveService(ProtocolApproveService protocolApproveService) {
        this.protocolApproveService = protocolApproveService;
    }

    public void setProtocolGrantExemptionService(ProtocolGrantExemptionService protocolGrantExemptionService) {
        this.protocolGrantExemptionService = protocolGrantExemptionService;
    }

    public void setKcPersonService(KcPersonService kcPersonService) {
        this.kcPersonService = kcPersonService;
    }

    public void setRolodexService(RolodexService rolodexService) {
        this.rolodexService = rolodexService;
    }


}
