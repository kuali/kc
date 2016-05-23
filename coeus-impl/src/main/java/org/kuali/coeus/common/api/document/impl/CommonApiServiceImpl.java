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
package org.kuali.coeus.common.api.document.impl;

import com.codiform.moo.Moo;
import com.codiform.moo.configuration.Configuration;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.api.document.service.CommonApiService;
import org.kuali.coeus.common.api.rolodex.RolodexContract;
import org.kuali.coeus.common.api.rolodex.RolodexService;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.coeus.sys.framework.rest.ResourceNotFoundException;
import org.kuali.coeus.sys.framework.rest.UnprocessableEntityException;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.framework.validation.AuditHelper;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.institutionalproposal.document.InstitutionalProposalDocument;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kim.api.identity.IdentityService;
import org.kuali.rice.kim.api.identity.entity.Entity;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.exception.UnknownDocumentIdException;
import org.kuali.rice.krad.exception.ValidationException;
import org.kuali.rice.krad.rules.rule.event.SaveDocumentEvent;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.util.AuditCluster;
import org.kuali.rice.krad.util.AuditError;
import org.kuali.rice.krad.util.ErrorMessage;
import org.kuali.rice.krad.util.KRADUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Component("commonApiService")
public class CommonApiServiceImpl implements CommonApiService {

    @Autowired
    @Qualifier("identityService")
    private IdentityService identityService;

    @Autowired
    @Qualifier("rolodexService")
    private RolodexService rolodexService;

    @Autowired
    @Qualifier("documentService")
    private DocumentService documentService;

    @Autowired
    @Qualifier("globalVariableService")
    private GlobalVariableService globalVariableService;

    @Autowired
    @Qualifier("auditHelper")
    private AuditHelper auditHelper;

    public void validatePerson(String personId, Integer rolodexId) {
        Entity personEntity = null;
        RolodexContract rolodex = null;
        if (personId != null) {
            personEntity = getIdentityService().getEntityByPrincipalId(personId);
        }
        else {
            rolodex = getRolodexService().getRolodex(rolodexId);
        }

        if (rolodex == null && personEntity == null) {
            throw new UnprocessableEntityException("Invalid person or rolodex for person " + personId != null ? personId : rolodexId.toString() );
        }
    }

    public void clearErrors() {
        getGlobalVariableService().getMessageMap().clearErrorMessages();
    }

    public Object convertObject(Object input, Class clazz) {
        Configuration mooConfig = new Configuration();
        mooConfig.setSourcePropertiesRequired(false);
        Moo moo = new Moo(mooConfig);
        Object newDataObject = getNewDataObject(clazz);
        moo.update(input, newDataObject);
        return newDataObject;
    }


    public Object getNewDataObject(Class clazz) {
        try {
            return clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("cannot create new data object", e);
        }
    }

    public Document getDocumentFromDocId(Long documentNumber) {
        try {
            return getDocumentService().getByDocumentHeaderId(documentNumber.toString());
        } catch (UnknownDocumentIdException | WorkflowException exception) {
            throw new ResourceNotFoundException("Could not locate document with document number " + documentNumber);
        }
    }

    public void routeDocument(Document document) {
        List<ErrorMessage> auditErrors = getAuditErrors(document);
        String errorMessage = StringUtils.EMPTY;
        for (ErrorMessage error : auditErrors) {
            errorMessage = errorMessage + KRADUtils.getMessageText(error, false);
        }
        if (!errorMessage.equalsIgnoreCase(StringUtils.EMPTY)) {
            throw new UnprocessableEntityException(errorMessage);
        }
        try {
            getDocumentService().routeDocument(document, "", new ArrayList<>());
        } catch (ValidationException | WorkflowException e) {
            throw new UnprocessableEntityException(e.getMessage(), e);
        }
    }

    public List<ErrorMessage> getAuditErrors(Document document) {
        boolean auditPassed = getAuditHelper().auditUnconditionally(document);
        List<ErrorMessage> errors = new ArrayList<>();
        if (!auditPassed) {
            final Map<String, AuditCluster> auditErrorMap = getGlobalVariableService().getAuditErrorMap();
            for (String key: auditErrorMap.keySet()) {
                AuditCluster auditCluster = auditErrorMap.get(key);
                if (!StringUtils.equalsIgnoreCase(auditCluster.getCategory(), Constants.AUDIT_WARNINGS)) {
                    List<AuditError> auditErrors = auditCluster.getAuditErrorList();
                    for (AuditError auditError : auditErrors) {
                        ErrorMessage errorMessage = new ErrorMessage();
                        errorMessage.setErrorKey(auditError.getMessageKey());
                        errorMessage.setMessageParameters(auditError.getParams());
                        errors.add(errorMessage);
                    }
                }
            }
        }
        return errors;
    }

    public String getValidationErrors() {
        String errors = "";
        for (Map.Entry<String, List<ErrorMessage>> entry : getGlobalVariableService().getMessageMap().getErrorMessages().entrySet()) {
            for (ErrorMessage msg : entry.getValue()) {
                errors += KRADUtils.getMessageText(msg, false);
            }
        }
        return errors;
    }

    public Document saveDocument(Document document) throws WorkflowException {
            try {
                document.validateBusinessRules(new SaveDocumentEvent("", document));
                document = getDocumentService().saveDocument(document);
            } catch (ValidationException e) {
                String errors = getValidationErrors() + " " + e.getMessage();
                throw new UnprocessableEntityException(errors, e);
            }

        return document;
    }

    public boolean isDocInModifiableState(WorkflowDocument workflowDocument) {
        return !workflowDocument.isCanceled();
    }

    public DocumentService getDocumentService() {
        return documentService;
    }

    public GlobalVariableService getGlobalVariableService() {
        return globalVariableService;
    }

    public IdentityService getIdentityService() {
        return identityService;
    }

    public RolodexService getRolodexService() {
        return rolodexService;
    }

    public AuditHelper getAuditHelper() {
        return auditHelper;
    }
}
