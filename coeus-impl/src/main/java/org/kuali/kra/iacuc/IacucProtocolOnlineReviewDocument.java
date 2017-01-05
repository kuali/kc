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
package org.kuali.kra.iacuc;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.custom.DocumentCustomData;
import org.kuali.coeus.sys.framework.controller.KcHoldingPageConstants;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.bo.RolePersons;
import org.kuali.kra.iacuc.onlinereview.IacucProtocolOnlineReview;
import org.kuali.kra.iacuc.onlinereview.IacucProtocolOnlineReviewStatus;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.ProtocolOnlineReviewDocumentBase;
import org.kuali.kra.protocol.onlinereview.ProtocolOnlineReviewBase;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants.COMPONENT;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants.NAMESPACE;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kew.framework.postprocessor.ActionTakenEvent;
import org.kuali.rice.kew.framework.postprocessor.DocumentRouteStatusChange;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.ObjectUtils;
import org.kuali.rice.krad.workflow.service.WorkflowDocumentService;

import java.util.ArrayList;
import java.util.List;

@NAMESPACE(namespace=Constants.MODULE_NAMESPACE_IACUC)
@COMPONENT(component=ParameterConstants.DOCUMENT_COMPONENT)
public class IacucProtocolOnlineReviewDocument  extends ProtocolOnlineReviewDocumentBase { 
    

    private static final long serialVersionUID = 43793212884887769L;
    private static final String DOCUMENT_TYPE_CODE = "PTRV";
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(IacucProtocolOnlineReviewDocument.class);
    private static final String OLR_DOC_ID_PARAM = "olrDocId";
    private static final String OLR_EVENT_PARAM = "olrEvent";

    private List<IacucProtocolOnlineReview> protocolOnlineReviewList;


    public IacucProtocolOnlineReviewDocument() { 
        super();
        protocolOnlineReviewList = new ArrayList<>();
        IacucProtocolOnlineReview newProtocolReview = new IacucProtocolOnlineReview();
        newProtocolReview.setProtocolOnlineReviewDocument(this);
        protocolOnlineReviewList.add(newProtocolReview);
    } 
    
    @Override
    public String serializeDocumentToXml() {
        for(ProtocolOnlineReviewBase protocolOnlineReview: this.getProtocolOnlineReviewList()) {
            ProtocolBase protocol = protocolOnlineReview.getProtocol();
            protocol.getLeadUnitNumber();
        }
        return super.serializeDocumentToXml();
    }

    @Override
    public void initialize() {
        super.initialize();
    }

    
    /**
     * 
     * This method is a convenience method for facilitating a 1:1 relationship between ProtocolDocument 
     * and ProtocolBase to the outside world - aka a single ProtocolBase field associated with ProtocolDocument
     */
    @Override
    public ProtocolOnlineReviewBase getProtocolOnlineReview() {
        if (protocolOnlineReviewList.size() == 0) return null;
        return protocolOnlineReviewList.get(0);
    }

    /**
     * 
     * This method is a convenience method for facilitating a 1:1 relationship between ProtocolDocument 
     * and ProtocolBase to the outside world - aka a single ProtocolBase field associated with ProtocolDocument
     */
    @Override
    public void setProtocolOnlineReview(ProtocolOnlineReviewBase protocolOnlineReview) {
        protocolOnlineReviewList.set(0, (IacucProtocolOnlineReview) protocolOnlineReview);
    }

    /**
     * 
     * This method is used by OJB to get around with anonymous keys issue.
     * Warning : Developers should never use this method.
     * @return List&lt;ProtocolBase&gt;
     */
    public List<IacucProtocolOnlineReview> getProtocolOnlineReviewList() {
        return protocolOnlineReviewList;
    }

    /**
     * 
     * This method is used by OJB to get around with anonymous keys issue.
     * Warning : Developers should never use this method
     */
    public void setProtocolOnlineReviewList(List<IacucProtocolOnlineReview> protocolOnlineReviewList) {
        this.protocolOnlineReviewList = protocolOnlineReviewList;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public List buildListOfDeletionAwareLists() {
        List managedLists = super.buildListOfDeletionAwareLists();
        if (getProtocolOnlineReview() != null) {
            managedLists.addAll(getProtocolOnlineReview().buildListOfDeletionAwareLists());
        }
        managedLists.add(protocolOnlineReviewList);
        return managedLists;
    }
    
    @Override
    protected List<RolePersons> getAllRolePersons() {
        return new ArrayList<>();
    }

    @Override
    public String getDocumentTypeCode() {
        return DOCUMENT_TYPE_CODE;
    }
    
    
    @Override
    public void doRouteStatusChange(DocumentRouteStatusChange statusChangeEvent) {
        executeAsLastActionUser(() -> {
            super.doRouteStatusChange(statusChangeEvent);
            if (StringUtils.equals(statusChangeEvent.getNewRouteStatus(), KewApiConstants.ROUTE_HEADER_CANCEL_CD)
                    || StringUtils.equals(statusChangeEvent.getNewRouteStatus(), KewApiConstants.ROUTE_HEADER_DISAPPROVED_CD)) {
                if (LOG.isDebugEnabled()) {
                    LOG.debug(String.format("ProtocolBase Online Review Document %s has been cancelled, deleting associated review comments.", getDocumentNumber()));
                }
                getProtocolOnlineReview().setProtocolOnlineReviewStatusCode(IacucProtocolOnlineReviewStatus.REMOVED_CANCELLED_STATUS_CD);
                getBusinessObjectService().save(getProtocolOnlineReview());
            }
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
    
    private BusinessObjectService getBusinessObjectService() {
        return KcServiceLocator.getService(BusinessObjectService.class);
    }

    @Override
    public void prepareForSave() {
        super.prepareForSave();
        if (ObjectUtils.isNull(this.getVersionNumber())) {
            this.setVersionNumber(0L);
        }
    }

    @Override
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
            if (getWorkflowDocumentService().getCurrentRouteNodeNames(onlineReviewDoc.getDocumentHeader().getWorkflowDocument()).equalsIgnoreCase(Constants.IACUC_ONLINE_REVIEW_ROUTE_NODE_ONLINE_REVIEWER)) {
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
            if (!getWorkflowDocumentService().getCurrentRouteNodeNames(onlineReviewDoc.getDocumentHeader().getWorkflowDocument()).equalsIgnoreCase(Constants.IACUC_ONLINE_REVIEW_ROUTE_NODE_ONLINE_REVIEWER)) {
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
        
        if (StringUtils.isNotBlank(url) && url.contains("?")) {
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

    @Override
    public List<? extends DocumentCustomData> getDocumentCustomData() {
        return null;
    }

    @Override
    public String getDocumentBoNumber() {
        return getProtocolOnlineReview().getProtocolId().toString();
    }
}
