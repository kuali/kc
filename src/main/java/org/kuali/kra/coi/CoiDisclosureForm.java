/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.coi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;



import org.apache.commons.lang.StringUtils;
import org.kuali.kra.authorization.KraAuthorizationConstants;
import org.kuali.kra.coi.actions.DisclosureActionHelper;
import org.kuali.kra.coi.disclosure.DisclosureHelper;
import org.kuali.kra.coi.notesandattachments.CoiNotesAndAttachmentsHelper;
import org.kuali.kra.coi.notification.CoiNotificationContext;
import org.kuali.kra.common.notification.web.struts.form.NotificationHelper;
import org.kuali.kra.irb.notification.IRBNotificationContext;
import org.kuali.kra.web.struts.form.Auditable;
import org.kuali.kra.web.struts.form.KraTransactionalDocumentFormBase;
import org.kuali.rice.kns.datadictionary.HeaderNavigation;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.KNSServiceLocator;

public class CoiDisclosureForm extends KraTransactionalDocumentFormBase implements Auditable  {
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -5620344612882618024L;
    private transient DisclosureHelper disclosureHelper;
    private transient DisclosureActionHelper disclosureActionHelper;
    private boolean auditActivated;
    private transient CoiNotesAndAttachmentsHelper coiNotesAndAttachmentsHelper;
    private transient NotificationHelper<CoiNotificationContext> notificationHelper;
    
    //TODO : coiDisclosureStatusCode : this is just a quick set up here for 'approve' action to test 'master disclosure'
    // this should be moved to disclosureactionhelper when 'action' is really implemented
    private String coiDisclosureStatusCode; 

    
    public CoiDisclosureForm() {
        super();
        initialize();
    }

    /**
     * This method initialize all form variables
     */
    public void initialize() {
       setDisclosureHelper(new DisclosureHelper(this));
       setCoiNotesAndAttachmentsHelper(new CoiNotesAndAttachmentsHelper(this));
       coiNotesAndAttachmentsHelper.prepareView();
       setDisclosureActionHelper(new DisclosureActionHelper(this));
       disclosureActionHelper.prepareView();
       setNotificationHelper(new NotificationHelper<CoiNotificationContext>());
    }
    
   public void setCoiNotesAndAttachmentsHelper(CoiNotesAndAttachmentsHelper coiNotesAndAttachmentsHelper) {
        this.coiNotesAndAttachmentsHelper = coiNotesAndAttachmentsHelper;
        
    }
   
   public CoiNotesAndAttachmentsHelper getCoiNotesAndAttachmentsHelper() {
       return coiNotesAndAttachmentsHelper;
    }
    
   @Override
    protected String getDefaultDocumentTypeName() {
        // TODO Auto-generated method stub
        return "CoiDisclosureDocument";
    }

    @Override
    protected String getLockRegion() {
        return KraAuthorizationConstants.LOCK_DESCRIPTOR_COIDISCLOSURE;
    }

    @Override
    protected void setSaveDocumentControl(Map editMode) {
        // TODO Auto-generated method stub
        
    }

    public boolean isAuditActivated() {
        return this.auditActivated;
    }

    public void setAuditActivated(boolean auditActivated) {
        this.auditActivated = auditActivated;
        
    }

    public DisclosureHelper getDisclosureHelper() {
        return disclosureHelper;
    }

    public void setDisclosureHelper(DisclosureHelper disclosureHelper) {
        this.disclosureHelper = disclosureHelper;
    }

    public CoiDisclosureDocument getCoiDisclosureDocument() {
        return (CoiDisclosureDocument)this.getDocument();
    }

//    @Override
//    public ActionErrors validate(ActionMapping mapping, ServletRequest request) {
//        // TODO Auto-generated method stub
//        return super.validate(mapping, request);
//    }

    /**
     * for approved disclosure, only display "Disclosure" page tab
     * @see org.kuali.rice.kns.web.struts.form.KualiForm#getHeaderNavigationTabs()
     */
    @Override
    public HeaderNavigation[] getHeaderNavigationTabs() {

        HeaderNavigation[] navigation = super.getHeaderNavigationTabs();

        List<HeaderNavigation> resultList = new ArrayList<HeaderNavigation>();
//        boolean isApproved = false;
//        if (this.getCoiDisclosureDocument().getCoiDisclosure().getCoiDisclosureId() != null) {
//            isApproved = isApprovedDisclosure(this.getCoiDisclosureDocument().getCoiDisclosure());
//        }
        for (HeaderNavigation nav : navigation) {
            if ((!this.getCoiDisclosureDocument().getCoiDisclosure().isApprovedDisclosure() && !StringUtils.equals("viewMasterDisclosure", this.getMethodToCall())) || StringUtils.equals(nav.getHeaderTabNavigateTo(), "disclosure")) {
                resultList.add(nav);
            }
        }

        HeaderNavigation[] result = new HeaderNavigation[resultList.size()];
        resultList.toArray(result);
        return result;
    }

//    private boolean isApprovedDisclosure(CoiDisclosure coiDisclosure) {
//
//        Map fieldValues = new HashMap();
//        fieldValues.put("coiDisclosureId", coiDisclosure.getCoiDisclosureId());
//        fieldValues.put("disclosureStatus", CoiDisclosureStatus.APPROVE_DISCLOSURE_CODES);
//        return getBusinessObjectService().countMatching(CoiDisclosureHistory.class, fieldValues) > 0;
//    }

    private BusinessObjectService getBusinessObjectService() {
        return KNSServiceLocator.getBusinessObjectService();
    }

    public String getCoiDisclosureStatusCode() {
        return coiDisclosureStatusCode;
    }

    public void setCoiDisclosureStatusCode(String coiDisclosureStatusCode) {
        this.coiDisclosureStatusCode = coiDisclosureStatusCode;
    }

    public DisclosureActionHelper getDisclosureActionHelper() {
        return disclosureActionHelper;
    }

    public void setDisclosureActionHelper(DisclosureActionHelper disclosureActionHelper) {
        this.disclosureActionHelper = disclosureActionHelper;
    }

    public NotificationHelper<CoiNotificationContext> getNotificationHelper() {
        return notificationHelper;
    }

    public void setNotificationHelper(NotificationHelper<CoiNotificationContext> notificationHelper) {
        this.notificationHelper = notificationHelper;
    }
    
}
