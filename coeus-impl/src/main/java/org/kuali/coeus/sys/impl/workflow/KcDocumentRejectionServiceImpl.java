/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component("kcDocumentRejectionService")
public class KcDocumentRejectionServiceImpl implements KcDocumentRejectionService {

    private static final Log LOG = LogFactory.getLog(KcDocumentRejectionServiceImpl.class);

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

    protected String getWorkflowInitialNodeName(String docTypeName) {
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
