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
package org.kuali.coeus.instprop.impl.api;

import org.kuali.coeus.common.api.document.service.CommonApiService;
import org.kuali.coeus.instprop.impl.api.dto.InstitutionalProposalDto;
import org.kuali.coeus.instprop.impl.api.dto.IpPersonDto;
import org.kuali.coeus.instprop.impl.api.service.InstitutionalProposalApiService;
import org.kuali.coeus.sys.framework.controller.rest.RestController;
import org.kuali.coeus.sys.framework.controller.rest.audit.RestAuditLogger;
import org.kuali.coeus.sys.framework.controller.rest.audit.RestAuditLoggerFactory;
import org.kuali.coeus.sys.framework.rest.ResourceNotFoundException;
import org.kuali.coeus.sys.framework.rest.UnprocessableEntityException;
import org.kuali.kra.award.home.ContactRole;
import org.kuali.kra.institutionalproposal.contacts.InstitutionalProposalPerson;
import org.kuali.kra.institutionalproposal.document.InstitutionalProposalDocument;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kew.api.exception.InvalidActionTakenException;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kew.routeheader.DocumentRouteHeaderValue;
import org.kuali.rice.kew.routeheader.service.RouteHeaderService;
import org.kuali.rice.krad.service.DocumentService;
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

@RequestMapping(value="/api")
@Controller("institutionalProposalDocumentController")
public class InstitutionalProposalDocumentController extends RestController {

    @Autowired
    @Qualifier("institutionalProposalApiService")
    InstitutionalProposalApiService institutionalProposalApiService;

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

    private List<String> ipDtoProperties;
    private List<String> ipPersonDtoProperties;

    public InstitutionalProposalDocumentController() throws IntrospectionException {
        ipDtoProperties = getIpdtoProperties();
        ipPersonDtoProperties = getIpPersonDtoProperties();
    }

    protected List<String> getIpdtoProperties() throws IntrospectionException {
        return Arrays.asList(Introspector.getBeanInfo(InstitutionalProposalDto.class).getPropertyDescriptors()).stream()
                .map(PropertyDescriptor::getName)
                .filter(prop -> !"class".equals(prop))
                .collect(Collectors.toList());
    }

    protected List<String> getIpPersonDtoProperties() throws IntrospectionException {
        return Arrays.asList(Introspector.getBeanInfo(IpPersonDto.class).getPropertyDescriptors()).stream()
                .map(PropertyDescriptor::getName)
                .filter(prop -> !"class".equals(prop))
                .collect(Collectors.toList());
    }

    @RequestMapping(method= RequestMethod.POST, value="/v1/institutional-proposal-document",
            consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.CREATED)
    @ResponseBody
    public String createInstitutionalProposal(@RequestBody InstitutionalProposalDto ipDto, @RequestParam(value = "createProposalLog", required = false) boolean createProposalLog) throws WorkflowException, InvocationTargetException, IllegalAccessException {
        RestAuditLogger auditLogger = getRestAuditLoggerFactory().getNewAuditLogger(InstitutionalProposalDto.class, ipDtoProperties);
        getCommonApiService().clearErrors();
        InstitutionalProposal proposal = getCommonApiService().convertObject(ipDto, InstitutionalProposal.class);
        String proposalLogNumber = null;
        if (createProposalLog) {
            IpPersonDto ip = null;
            if (ipDto.getProjectPersons() != null) {
                ip = ipDto.getProjectPersons().stream().filter(ipPersonDto -> ipPersonDto.getRoleCode().equalsIgnoreCase(ContactRole.PI_CODE)).findFirst().orElse(null);
            }
            proposalLogNumber = getInstitutionalProposalApiService().createProposalLog(ipDto, ip);
        }
        proposal.setProjectPersons(new ArrayList<>());
        getInstitutionalProposalApiService().initializeData(proposal);
        InstitutionalProposalDocument ipDocument = getInstitutionalProposalApiService().saveInitialProposal(proposal, ipDto.getDocumentDescription());
        getInstitutionalProposalApiService().addPersons(ipDocument, ipDto.getProjectPersons());
        getInstitutionalProposalApiService().addCustomData(ipDocument.getInstitutionalProposal(), ipDto);
        if (createProposalLog) {
            ipDocument.getInstitutionalProposal().setProposalNumber(proposalLogNumber);
            getInstitutionalProposalApiService().updateProposalLog(proposalLogNumber, ipDocument);
        }
        saveDocument(ipDocument);
        auditLogger.addNewItem(ipDto);
        auditLogger.saveAuditLog();

        return ipDocument.getDocumentNumber();
    }

    @RequestMapping(method= RequestMethod.DELETE, value="/v1/institutional-proposal-document/{documentNumber}",
            consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    void deleteInstitutionalProposal(@PathVariable Long documentNumber) throws WorkflowException {
        InstitutionalProposalDocument proposalDocument = (InstitutionalProposalDocument) getCommonApiService().getDocumentFromDocId(documentNumber);
        DocumentRouteHeaderValue routeHeader = getRouteHeaderService().getRouteHeader(proposalDocument.getDocumentHeader().getWorkflowDocument().getDocumentId());
        if (!routeHeader.getDocRouteStatus().equalsIgnoreCase(KewApiConstants.ROUTE_HEADER_CANCEL_CD)) {
            try {
                getDocumentService().cancelDocument(proposalDocument, "Cancelled");
            }
            catch (InvalidActionTakenException e) {
                throw new UnprocessableEntityException("Document " + documentNumber + " is not in a state to be cancelled.");
            }
        }
    }

    @RequestMapping(method= RequestMethod.GET, value="/v1/institutional-proposal-document/{documentNumber}",
            consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    InstitutionalProposalDto getInstitutionalProposal(@PathVariable Long documentNumber) {
        InstitutionalProposalDocument proposalDocument = (InstitutionalProposalDocument) getCommonApiService().getDocumentFromDocId(documentNumber);
        InstitutionalProposal proposal = proposalDocument.getInstitutionalProposal();
        InstitutionalProposalDto proposalDto = getCommonApiService().convertObject(proposal, InstitutionalProposalDto.class);
        proposalDto.setDocNbr(proposalDocument.getDocumentNumber());
        proposalDto.setDocStatus(proposalDocument.getDocumentHeader().getWorkflowDocument().getStatus().getLabel());
        return proposalDto;
    }

    @RequestMapping(method= RequestMethod.POST, value="/v1/institutional-proposal-document/{documentNumber}/proposal-persons",
            consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.CREATED)
    @ResponseBody
    public List<IpPersonDto> addProposalPersons(@RequestBody List<IpPersonDto> ipPersonDto, @PathVariable Long documentNumber) throws WorkflowException {
        getCommonApiService().clearErrors();
        InstitutionalProposalDocument proposalDocument = (InstitutionalProposalDocument) getCommonApiService().getDocumentFromDocId(documentNumber);
        RestAuditLogger auditLogger = getRestAuditLoggerFactory().getNewAuditLogger(IpPersonDto.class, ipPersonDtoProperties);
        getInstitutionalProposalApiService().addPersons(proposalDocument, ipPersonDto);
        ipPersonDto.stream().forEach( item ->
                auditLogger.addNewItem(item)
        );
        saveDocument(proposalDocument);
        auditLogger.saveAuditLog();
        return getAllProposalPersons(Long.parseLong(proposalDocument.getDocumentNumber()));
    }

    @RequestMapping(method= RequestMethod.GET, value="/v1/institutional-proposal-document/{documentNumber}/proposal-persons",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    List<IpPersonDto> getAllProposalPersons(@PathVariable Long documentNumber) throws WorkflowException {
        InstitutionalProposalDocument proposalDocument = (InstitutionalProposalDocument) getCommonApiService().getDocumentFromDocId(documentNumber);
        InstitutionalProposalDto proposalDto = getCommonApiService().
                convertObject(proposalDocument.getInstitutionalProposal(), InstitutionalProposalDto.class);
        List<IpPersonDto> persons = proposalDto.getProjectPersons();
        return persons;
    }

    private InstitutionalProposalPerson getInstitutionalProposalPerson(Long id, InstitutionalProposalDocument proposalDocument) {
        InstitutionalProposalPerson person = proposalDocument.getInstitutionalProposal().getProjectPersons().stream().filter(
                institutionalProposalPerson -> id.intValue() == institutionalProposalPerson.getInstitutionalProposalContactId().intValue()).findFirst().orElse(null);
        if (person == null) {
                throw new ResourceNotFoundException("Person with id " + id + " not found.");
        }
        return person;
    }

    @RequestMapping(method= RequestMethod.DELETE, value="/v1/institutional-proposal-document/{documentNumber}/proposal-persons/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    void deleteProposalPerson(@PathVariable Long documentNumber, @PathVariable Long id) throws WorkflowException {
        InstitutionalProposalDocument proposalDocument = (InstitutionalProposalDocument) getCommonApiService().getDocumentFromDocId(documentNumber);
        RestAuditLogger auditLogger = getRestAuditLoggerFactory().getNewAuditLogger(IpPersonDto.class, ipPersonDtoProperties);
        InstitutionalProposalPerson person = getInstitutionalProposalPerson(id, proposalDocument);
        IpPersonDto personDto = getCommonApiService().convertObject(person, IpPersonDto.class);
        proposalDocument.getInstitutionalProposal().getProjectPersons().remove(person);
        saveDocument(proposalDocument);
        auditLogger.addDeletedItem(personDto);
        auditLogger.saveAuditLog();
    }

    @RequestMapping(method= RequestMethod.GET, value="/v1/institutional-proposal-document/{documentNumber}/proposal-persons/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    IpPersonDto getProposalPerson(@PathVariable Long documentNumber, @PathVariable Long id) throws WorkflowException {
        InstitutionalProposalDocument proposalDocument = (InstitutionalProposalDocument) getCommonApiService().getDocumentFromDocId(documentNumber);
        InstitutionalProposalPerson person = getInstitutionalProposalPerson(id, proposalDocument);
        IpPersonDto personDto = getCommonApiService().convertObject(person, IpPersonDto.class);
        return personDto;
    }

    @RequestMapping(method= RequestMethod.PUT, value="/v1/institutional-proposal-document/{documentNumber}", params="route",
            consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    void routeDocument(@PathVariable Long documentNumber) throws WorkflowException {
        getCommonApiService().clearErrors();
        InstitutionalProposalDocument proposalDocument = (InstitutionalProposalDocument) getDocumentService().getByDocumentHeaderId(documentNumber.toString());
        getCommonApiService().routeDocument(proposalDocument);
    }

    protected void saveDocument(InstitutionalProposalDocument proposalDocument) throws WorkflowException {
        final WorkflowDocument workflowDocument = proposalDocument.getDocumentHeader().getWorkflowDocument();
        // Rice lets you save a cancelled doc, so check status before saving.
        if(getCommonApiService().isDocInModifiableState(workflowDocument)) {
            getInstitutionalProposalApiService().initializeCollections(proposalDocument.getInstitutionalProposal());
            getCommonApiService().saveDocument(proposalDocument);
        } else {
                throw new UnprocessableEntityException("Document " + proposalDocument.getDocumentNumber() + " with status " + workflowDocument.getStatus() +
                        " is not in a state to be saved.");
        }
    }

    public RouteHeaderService getRouteHeaderService() {
        return routeHeaderService;
    }

    public void setRouteHeaderService(RouteHeaderService routeHeaderService) {
        this.routeHeaderService = routeHeaderService;
    }

    public InstitutionalProposalApiService getInstitutionalProposalApiService() {
        return institutionalProposalApiService;
    }

    public void setInstitutionalProposalApiService(InstitutionalProposalApiService institutionalProposalApiService) {
        this.institutionalProposalApiService = institutionalProposalApiService;
    }

    public RestAuditLoggerFactory getRestAuditLoggerFactory() {
        return restAuditLoggerFactory;
    }

    public void setRestAuditLoggerFactory(RestAuditLoggerFactory restAuditLoggerFactory) {
        this.restAuditLoggerFactory = restAuditLoggerFactory;
    }

    public CommonApiService getCommonApiService() {
        return commonApiService;
    }

    public void setCommonApiService(CommonApiService commonApiService) {
        this.commonApiService = commonApiService;
    }

    public DocumentService getDocumentService() {
        return documentService;
    }

    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }
}
