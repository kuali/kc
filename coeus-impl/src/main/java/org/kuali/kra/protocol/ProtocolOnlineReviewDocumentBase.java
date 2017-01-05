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

package org.kuali.kra.protocol;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.controller.KcHoldingPageConstants;
import org.kuali.coeus.sys.framework.model.KcTransactionalDocumentBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.bo.RolePersons;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.protocol.onlinereview.ProtocolOnlineReviewBase;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants.COMPONENT;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants.NAMESPACE;
import org.kuali.rice.kew.framework.postprocessor.ActionTakenEvent;
import org.kuali.rice.kew.framework.postprocessor.DocumentRouteStatusChange;
import org.kuali.rice.krad.document.Copyable;
import org.kuali.rice.krad.document.SessionDocument;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.ObjectUtils;
import org.kuali.rice.krad.workflow.service.WorkflowDocumentService;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * This class represents the ProtocolBase Review Document Object.
 * ProtocolReviewDocument has a 1:1 relationship with ProtocolReview Business Object.
 * We have declared a list of ProtocolBase BOs in the ProtocolDocumentBase at the same time to
 * get around the OJB anonymous keys issue of primary keys of different data types.
 * Also we have provided convenient getter and setter methods so that to the outside world;
 * ProtocolBase and ProtocolDocumentBase can have a 1:1 relationship.
 */
@NAMESPACE(namespace=Constants.MODULE_NAMESPACE_IRB)
@COMPONENT(component=ParameterConstants.DOCUMENT_COMPONENT)
public abstract class ProtocolOnlineReviewDocumentBase extends KcTransactionalDocumentBase implements Copyable, SessionDocument {
	
    private static final String DOCUMENT_TYPE_CODE = "PTRV";
    private static final String OLR_DOC_ID_PARAM = "olrDocId";
    private static final String OLR_EVENT_PARAM = "olrEvent";


    private static final long serialVersionUID = 803158468103165087L;


	public ProtocolOnlineReviewDocumentBase() { 
        super();
	} 
	
    public void initialize() {
        super.initialize();
    }

    
    /**
     * 
     * This method is a convenience method for facilitating a 1:1 relationship between ProtocolDocumentBase 
     * and ProtocolBase to the outside world - aka a single ProtocolBase field associated with ProtocolDocumentBase
     * @return
     */
    public abstract ProtocolOnlineReviewBase getProtocolOnlineReview();
 
    /**
     * 
     * This method is a convenience method for facilitating a 1:1 relationship between ProtocolDocumentBase 
     * and ProtocolBase to the outside world - aka a single ProtocolBase field associated with ProtocolDocumentBase
     * @param protocol
     */
     public abstract void setProtocolOnlineReview(ProtocolOnlineReviewBase protocolOnlineReview);

     @Override
    protected List<RolePersons> getAllRolePersons() {
        return new ArrayList<RolePersons>();
    }
    
    public String getDocumentTypeCode() {
        return DOCUMENT_TYPE_CODE;
    }
    
    
    @Override
    public void doRouteStatusChange(DocumentRouteStatusChange statusChangeEvent) {
        executeAsLastActionUser(() -> {
            super.doRouteStatusChange(statusChangeEvent);
            return null;
        });
    }
  
    @Override
    public void doActionTaken( ActionTakenEvent event ) {
        executeAsLastActionUser( () -> {
            super.doActionTaken(event);
            return null;
        });
    }

    @Override
    public void prepareForSave() {
        super.prepareForSave();
        if (ObjectUtils.isNull(this.getVersionNumber())) {
            this.setVersionNumber(new Long(0));
        }
    }
    
    public boolean isProcessComplete() {
        boolean isComplete = true;
        
        String backLocation = (String) GlobalVariables.getUserSession().retrieveObject(KcHoldingPageConstants.HOLDING_PAGE_RETURN_LOCATION);
        String olrDocId = getURLParamValue(backLocation, OLR_DOC_ID_PARAM);
        if (olrDocId != null) {
            String olrEvent = getURLParamValue(backLocation, OLR_EVENT_PARAM);
            if (StringUtils.equalsIgnoreCase(olrEvent, "Approve")) {
                isComplete = isOnlineReviewApproveComplete(olrDocId);
            } else if (StringUtils.equalsIgnoreCase(olrEvent, "Return")) {
                isComplete = isOnlineReviewRejectComplete(olrDocId);         
            }
        }
            
        return isComplete;
    }
  
    private WorkflowDocumentService getWorkflowDocumentService() {
        return KRADServiceLocatorWeb.getWorkflowDocumentService();
    }    

    private boolean isOnlineReviewApproveComplete(String olrDocId) {
        boolean isComplete = true;
        try {
            ProtocolOnlineReviewDocumentBase onlineReviewDoc = (ProtocolOnlineReviewDocumentBase)getDocumentService().getByDocumentHeaderId(olrDocId);
            if (getWorkflowDocumentService().getCurrentRouteNodeNames(onlineReviewDoc.getDocumentHeader().getWorkflowDocument()).equalsIgnoreCase(Constants.ONLINE_REVIEW_ROUTE_NODE_ONLINE_REVIEWER)) {
                isComplete = false;
            }
        } catch (Exception e) {
            isComplete = true;
        }
        
        return isComplete;
    }

    private boolean isOnlineReviewRejectComplete(String olrDocId) {
        boolean isComplete = true;
        try {
            ProtocolOnlineReviewDocumentBase onlineReviewDoc = (ProtocolOnlineReviewDocumentBase)getDocumentService().getByDocumentHeaderId(olrDocId);
            if (!getWorkflowDocumentService().getCurrentRouteNodeNames(onlineReviewDoc.getDocumentHeader().getWorkflowDocument()).equalsIgnoreCase(Constants.ONLINE_REVIEW_ROUTE_NODE_ONLINE_REVIEWER)) {
                isComplete = false;
            }
        } catch (Exception e) {
            isComplete = true;
        }
        
        return isComplete;
    }
    
    private DocumentService getDocumentService() {
        return KcServiceLocator.getService(DocumentService.class);
    }
    
    private String getURLParamValue(String url, String paramName) {
        String pValue = null;
        
        if (StringUtils.isNotBlank(url) && url.indexOf("?") > -1) {
            String paramString = url.substring(url.indexOf("?") + 1);

            if (StringUtils.isNotBlank(paramString)) {
                String params[] = paramString.split("&");
                for (String param : params) {
                    String temp[] = param.split("=");

                    if (StringUtils.equals(temp[0], paramName)) {
                        pValue = temp[1];
                    }
                }
            }
        }
        
        return pValue;
    }

}
