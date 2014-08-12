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
package org.kuali.kra.iacuc;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.custom.DocumentCustomData;
import org.kuali.coeus.common.framework.auth.perm.KcAuthorizationService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.bo.RolePersons;
import org.kuali.kra.iacuc.onlinereview.IacucProtocolOnlineReview;
import org.kuali.kra.iacuc.onlinereview.IacucProtocolOnlineReviewStatus;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.ProtocolOnlineReviewDocumentBase;
import org.kuali.kra.protocol.onlinereview.ProtocolOnlineReviewBase;
import org.kuali.kra.protocol.onlinereview.ProtocolReviewAttachmentBase;
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
        protocolOnlineReviewList = new ArrayList<IacucProtocolOnlineReview>();
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
        String xml = super.serializeDocumentToXml(); 
        return xml; 
    }

    public void initialize() {
        super.initialize();
    }

    
    /**
     * 
     * This method is a convenience method for facilitating a 1:1 relationship between ProtocolDocument 
     * and ProtocolBase to the outside world - aka a single ProtocolBase field associated with ProtocolDocument
     * @return
     */
    public ProtocolOnlineReviewBase getProtocolOnlineReview() {
        if (protocolOnlineReviewList.size() == 0) return null;
        return protocolOnlineReviewList.get(0);
    }

    /**
     * 
     * This method is a convenience method for facilitating a 1:1 relationship between ProtocolDocument 
     * and ProtocolBase to the outside world - aka a single ProtocolBase field associated with ProtocolDocument
     * @param protocol
     */
    public void setProtocolOnlineReview(ProtocolOnlineReviewBase protocolOnlineReview) {
        protocolOnlineReviewList.set(0, (IacucProtocolOnlineReview) protocolOnlineReview);
    }

    /**
     * 
     * This method is used by OJB to get around with anonymous keys issue.
     * Warning : Developers should never use this method.
     * @return List<ProtocolBase>
     */
    public List<IacucProtocolOnlineReview> getProtocolOnlineReviewList() {
        return protocolOnlineReviewList;
    }

    /**
     * 
     * This method is used by OJB to get around with anonymous keys issue.
     * Warning : Developers should never use this method
     * @param protocolList
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
        KcAuthorizationService kraAuthService =
               (KcAuthorizationService) KcServiceLocator.getService(KcAuthorizationService.class);
        return new ArrayList<RolePersons>();
    }
    
    public String getDocumentTypeCode() {
        return DOCUMENT_TYPE_CODE;
    }
    
    
    @Override
    public void doRouteStatusChange(DocumentRouteStatusChange statusChangeEvent) {
        super.doRouteStatusChange(statusChangeEvent);
        if (StringUtils.equals(statusChangeEvent.getNewRouteStatus(), KewApiConstants.ROUTE_HEADER_CANCEL_CD) 
                || StringUtils.equals(statusChangeEvent.getNewRouteStatus(), KewApiConstants.ROUTE_HEADER_DISAPPROVED_CD)) {
            if (LOG.isDebugEnabled()) {
                LOG.debug(String.format("ProtocolBase Online Review Document %s has been cancelled, deleting associated review comments.", getDocumentNumber()));
            }
            List<ProtocolReviewAttachmentBase> deletedReviewAttachments = new ArrayList<ProtocolReviewAttachmentBase>();

            getProtocolOnlineReview().setProtocolOnlineReviewStatusCode(IacucProtocolOnlineReviewStatus.REMOVED_CANCELLED_STATUS_CD);
            getBusinessObjectService().save(getProtocolOnlineReview());
        }
    }
  
    @Override
    public void doActionTaken( ActionTakenEvent event ) {
        super.doActionTaken(event);
    }
    
    private BusinessObjectService getBusinessObjectService() {
        return KcServiceLocator.getService(BusinessObjectService.class);
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
        
        String backLocation = (String) GlobalVariables.getUserSession().retrieveObject(Constants.HOLDING_PAGE_RETURN_LOCATION);
        String olrDocId = getURLParamValue(backLocation, OLR_DOC_ID_PARAM);
        if (olrDocId != null) {
            String olrEvent = getURLParamValue(backLocation, OLR_EVENT_PARAM);
            if (StringUtils.equalsIgnoreCase(olrEvent, "Approve")) {
                isComplete = isOnlineReviewApproveComplete(olrDocId);
            } else if (StringUtils.equalsIgnoreCase(olrEvent, "Reject")) {
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

    @Override
    public List<? extends DocumentCustomData> getDocumentCustomData() {
        return null;
    }
    
    public String getDocumentBoNumber() {
        return getProtocolOnlineReview().getProtocolId().toString();
    }
}
