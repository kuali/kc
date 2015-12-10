/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.coeus.sys.impl.workflow;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.sys.framework.workflow.KcDocumentRejectionService;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kew.api.doctype.DocumentTypeService;
import org.kuali.rice.kew.api.doctype.RoutePath;
import org.kuali.rice.kew.api.document.WorkflowDocumentService;
import org.kuali.rice.kew.api.exception.InvalidActionTakenException;
import org.kuali.rice.kew.routeheader.DocumentRouteHeaderValue;
import org.kuali.rice.kew.routeheader.service.RouteHeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component("kcDocumentRejectionService")
public class KcDocumentRejectionServiceImpl implements KcDocumentRejectionService {

    private static final Log LOG = LogFactory.getLog(KcDocumentRejectionServiceImpl.class);

    @Autowired
    @Qualifier("routeHeaderService")
    private RouteHeaderService routeHeaderService;


    @Autowired
    @Qualifier("documentTypeService")
    private DocumentTypeService documentTypeService;

    @Autowired
    @Qualifier("kewWorkflowDocumentService")
    private WorkflowDocumentService workflowDocumentService;

    @Override
    public void reject(WorkflowDocument document, String reason, String principalId, String appDocStatus) {
        reject(document, reason, principalId, appDocStatus, getWorkflowInitialNodeName(document.getDocumentTypeName()));
    }

    @Override
    public void reject(WorkflowDocument document, String reason, String principalId,  String appDocStatus, String nodeName) {
        if( LOG.isDebugEnabled() ) {
            LOG.debug( String.format( "Rejecting document %s to node %s as %s with reason '%s'", document, nodeName, principalId, reason ));
        }

        document.returnToPreviousNode(reason, nodeName);
        try {
            DocumentRouteHeaderValue routeHeader = routeHeaderService.getRouteHeader(document.getDocumentId());
            routeHeader.markDocumentSaved();
            routeHeaderService.saveRouteHeader(routeHeader);
        } catch (InvalidActionTakenException e) {
           throw new RuntimeException(e);
        }
        if (appDocStatus != null) {
            if( LOG.isDebugEnabled() ) {
                LOG.debug( String.format( "Setting application document status of document %s to %s", document.getDocumentId(), appDocStatus));
            }
            document.setApplicationDocumentStatus(appDocStatus);
        }
    }

    @Override
    public boolean isDocumentOnInitialNode(WorkflowDocument document)  {
        boolean ret = false;
        if (document != null) {
            ret = isDocumentOnNode(document.getDocumentId(), getWorkflowInitialNodeName(document.getDocumentTypeName()));
        }
        return ret;
    }

    protected boolean isDocumentOnNode(String documentNumber,String nodeName) {
        if (StringUtils.isNotBlank(documentNumber) && StringUtils.isNotBlank(nodeName)) {
            final Collection<String> currentRouteNodeNames = workflowDocumentService.getActiveRouteNodeNames(documentNumber);
            return currentRouteNodeNames.contains(nodeName);
        }

        return false;
    }

    public String getWorkflowInitialNodeName(String docTypeName) {
        final RoutePath path = documentTypeService.getRoutePathForDocumentTypeName(docTypeName);
        return path.getPrimaryProcess().getInitialRouteNode().getName();
    }

    public void setDocumentTypeService(DocumentTypeService documentTypeService) {
        this.documentTypeService = documentTypeService;
    }

    public DocumentTypeService getDocumentTypeService() {
        return documentTypeService;
    }

    public WorkflowDocumentService getWorkflowDocumentService() {
        return workflowDocumentService;
    }

    public void setWorkflowDocumentService(WorkflowDocumentService workflowDocumentService) {
        this.workflowDocumentService = workflowDocumentService;
    }
}
