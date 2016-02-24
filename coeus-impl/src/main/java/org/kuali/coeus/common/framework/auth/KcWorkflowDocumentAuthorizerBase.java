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
package org.kuali.coeus.common.framework.auth;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.framework.auth.perm.Permissionable;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.kim.bo.KcKimAttributes;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kew.doctype.bo.DocumentType;
import org.kuali.rice.kew.doctype.service.impl.KimDocumentTypeAuthorizer;
import org.kuali.rice.kew.routeheader.DocumentRouteHeaderValue;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class KcWorkflowDocumentAuthorizerBase extends KimDocumentTypeAuthorizer {
    
    protected static final Log LOG = LogFactory.getLog(KcWorkflowDocumentAuthorizerBase.class);
    
    private transient DocumentService documentService;
    private transient BusinessObjectService businessObjectService;

    /**
     * Implements {@link org.kuali.rice.kew.doctype.service.DocumentTypePermissionService#canRecall(String, org.kuali.rice.kew.routeheader.DocumentRouteHeaderValue)}
     */
    @Override
    public boolean canRecall(String principalId, DocumentRouteHeaderValue document) {
        validatePrincipalId(principalId);
        validateDocument(document);
        String documentId = document.getDocumentId();
        DocumentType documentType = document.getDocumentType();
        String documentStatus = document.getDocRouteStatus();
        String appDocStatus = document.getAppDocStatus();
        List<String> routeNodeNames = document.getCurrentNodeNames();
        validateDocumentType(documentType);
        validateDocumentStatus(documentStatus);
        // no need to validate appdocstatus, this is a free-form application defined value

        // add appDocStatus to the details
        List<Map<String, String>> permissionDetailList = buildDocumentTypePermissionDetailsForNodes(documentType, routeNodeNames, documentStatus, null);
        if (!StringUtils.isBlank(appDocStatus)) {
            for (Map<String, String> details: permissionDetailList) {
                details.put(KewApiConstants.APP_DOC_STATUS_DETAIL, appDocStatus);
            }
        }
        
        // loop over permission details, only one of them needs to be authorized
        for (Map<String, String> permissionDetails : permissionDetailList) {
            if (useKimPermission(KewApiConstants.KEW_NAMESPACE, KewApiConstants.RECALL_PERMISSION, permissionDetails, false)) {
                if (getPermissionService().isPermissionDefinedByTemplate(KewApiConstants.KEW_NAMESPACE, KewApiConstants.RECALL_PERMISSION, permissionDetails)) {
                    List<Map<String, String>> qualifierList = getSetsOfRoleQualifiers(document, permissionDetails.get(KewApiConstants.ROUTE_NODE_NAME_DETAIL));
                    for (Map<String, String> roleQualifiers : qualifierList) {
                        if (getPermissionService().isAuthorizedByTemplate(principalId, KewApiConstants.KEW_NAMESPACE,
                                KewApiConstants.RECALL_PERMISSION, permissionDetails, roleQualifiers)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    
    protected List<Map<String, String>> getSetsOfRoleQualifiers(DocumentRouteHeaderValue document, String routeNodeName) {
        List<Map<String, String>> result = new ArrayList<Map<String, String>>();
        Map<String, String> defaultQualifications = buildDocumentRoleQualifiers(document, routeNodeName);
        result.add(defaultQualifications);
        Permissionable permissionable = getPermissionable(document.getDocumentId());
        if (permissionable != null) {
            Map<String, String> docNbrQualifiers = new HashMap<String, String>();
            docNbrQualifiers.put(permissionable.getDocumentKey(), permissionable.getDocumentNumberForPermission());
            docNbrQualifiers.putAll(defaultQualifications);
            result.add(docNbrQualifiers);
            
            Map<String, String> unitNumberQualifiers = new HashMap<String, String>();
            unitNumberQualifiers.put(KcKimAttributes.UNIT_NUMBER, permissionable.getLeadUnitNumber());
            unitNumberQualifiers.putAll(defaultQualifications);
            result.add(unitNumberQualifiers);
        }
        return result;
    }
    
    protected abstract Permissionable getPermissionable(String documentId);

    public DocumentService getDocumentService() {
        if (documentService == null) {
            documentService = KcServiceLocator.getService(DocumentService.class);
        }
        return documentService;
    }

    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }

    public BusinessObjectService getBusinessObjectService() {
        if (businessObjectService == null) {
            businessObjectService = KcServiceLocator.getService(BusinessObjectService.class);
        }
        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
}

