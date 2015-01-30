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
package org.kuali.kra.subaward.document;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.kuali.coeus.common.framework.custom.DocumentCustomData;
import org.kuali.coeus.common.framework.version.VersionStatus;
import org.kuali.coeus.common.framework.version.history.VersionHistoryService;
import org.kuali.coeus.sys.framework.model.KcTransactionalDocumentBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.framework.workflow.KcWorkflowService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.krms.KcKrmsConstants;
import org.kuali.coeus.common.framework.krms.KrmsRulesContext;
import org.kuali.coeus.common.impl.krms.KcKrmsFactBuilderServiceHelper;
import org.kuali.kra.subaward.bo.SubAward;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kew.framework.postprocessor.DocumentRouteStatusChange;
import org.kuali.rice.krad.document.Copyable;
import org.kuali.rice.krad.document.SessionDocument;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krms.api.engine.Facts.Builder;


public class SubAwardDocument extends KcTransactionalDocumentBase
	implements  Copyable, SessionDocument, KrmsRulesContext {


    private static final long serialVersionUID = 5454534590787613256L;
    private transient boolean documentSaveAfterVersioning;
    private List<SubAward> subAwardList;
    public static final String DOCUMENT_TYPE_CODE = "SAWD";
    @Override
    public String getDocumentTypeCode() {
        return DOCUMENT_TYPE_CODE;
    }
    /**.
     * Constructs a subAwardDocument object
     */
    public SubAwardDocument() {
        super();
        init();
    }
    public SubAward getSubAward() {
        return getSubAwardList().size() > 0 ? getSubAwardList().get(0) : new SubAward();
    }
    public void setSubAward(SubAward subAward){
        subAwardList.set(0, subAward);
    }
    public void setSubAwardList(List<SubAward> subAwardList) {
        this.subAwardList = subAwardList;
    }
    public List<SubAward> getSubAwardList() {
        return subAwardList;
    }

    /**.
     * The method is for doRouteStatusChange
     */
    @Override
    public void doRouteStatusChange(
    DocumentRouteStatusChange statusChangeEvent) {
        super.doRouteStatusChange(statusChangeEvent);

        String newStatus = statusChangeEvent.getNewRouteStatus();

        if (KewApiConstants.ROUTE_HEADER_FINAL_CD.equalsIgnoreCase(newStatus)) {
            getVersionHistoryService().updateVersionHistory(getSubAward(), VersionStatus.ACTIVE, GlobalVariables.
                    getUserSession().getPrincipalName());
        }
        if (newStatus.equalsIgnoreCase(KewApiConstants.ROUTE_HEADER_CANCEL_CD)
        || newStatus.equalsIgnoreCase(
        KewApiConstants.ROUTE_HEADER_DISAPPROVED_CD)) {
            getVersionHistoryService().updateVersionHistory(getSubAward(), VersionStatus.CANCELED, GlobalVariables. 
                    getUserSession().getPrincipalName());
        }

        for (SubAward subAward : subAwardList) {
            subAward.setSubAwardDocument(this);
        }
    }
    /**
     * This method specifies if this document may be
     * edited; i.e. it's only initiated or saved
     * @return boolean
     */
    public boolean isEditable() {
        WorkflowDocument workflowDoc =
        getDocumentHeader().getWorkflowDocument();
        return workflowDoc.isInitiated() || workflowDoc.isSaved();
    }
    
    protected void init() {
        subAwardList = new ArrayList<SubAward>();
        subAwardList.add(new SubAward());
    }

    public boolean isDocumentSaveAfterVersioning() {
        return documentSaveAfterVersioning;
    }
    
    /**
     * @param documentSaveAfterVersioning
     */
    public void setDocumentSaveAfterSubAwardLookupEditOrVersion(boolean documentSaveAfterVersioning) {
        this.documentSaveAfterVersioning = documentSaveAfterVersioning;
    }

    protected VersionHistoryService getVersionHistoryService() {
        return KcServiceLocator.getService(VersionHistoryService.class);
    }
    /**
     * This method is to check whether rice
     * async routing is ok now.
     * Close to hack.  called by holdingpageaction
     * Different document type may have different
     * routing set up, so each document type
     * can implement its own
     *  isProcessComplete
     * @return
     */
    public boolean isProcessComplete() {

        boolean isComplete = false;

        if (getDocumentHeader().hasWorkflowDocument()) {
            /**
             * per KRACOEUS-5394 changing from getDocumentHeader().getWorkflowDocument().isFinal().  This way
             * we route back to the award document more appropriately from holding page.
             */
            if (getDocumentHeader().getWorkflowDocument().isFinal() 
                    || getDocumentHeader().getWorkflowDocument().isProcessed()
                    || KcServiceLocator.getService(KcWorkflowService.class).hasPendingApprovalRequests(getDocumentHeader().getWorkflowDocument())) {
                isComplete = true;
            }
        }
           
        return isComplete;
    }
    @Override
    public List<? extends DocumentCustomData> getDocumentCustomData() {
        return getSubAward().getSubAwardCustomDataList();
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List buildListOfDeletionAwareLists() {
        List managedLists = super.buildListOfDeletionAwareLists();
        SubAward subAward = getSubAward();
        managedLists.add(subAward.getSubAwardFundingSourceList());
        managedLists.add(subAward.getSubAwardContactsList());
        managedLists.add(subAward.getSubAwardCloseoutList());
        managedLists.add(subAward.getSubAwardAttachments());
        managedLists.add(subAward.getSubAwardReportList());
        return managedLists;
    }
    
    public String getDocumentBoNumber() {
        return getSubAward().getSubAwardCode();
    }
    @Override
    public void populateContextQualifiers(Map<String, String> qualifiers) {
        qualifiers.put("namespaceCode", Constants.MODULE_NAMESPACE_SUBAWARD);
        qualifiers.put("name", KcKrmsConstants.SubAward.SUBAWARD_CONTEXT);
    }
    
    @Override
    public void addFacts(Builder factsBuilder) {
        KcKrmsFactBuilderServiceHelper fbService = KcServiceLocator.getService("subAwardFactBuilderService");
        fbService.addFacts(factsBuilder, this);
    }
    @Override
    public void populateAgendaQualifiers(Map<String, String> qualifiers) {
        qualifiers.put(KcKrmsConstants.UNIT_NUMBER, getLeadUnitNumber());
    }

    public String getLeadUnitNumber() {
        return getSubAward().getLeadUnitNumber();
    }
}
