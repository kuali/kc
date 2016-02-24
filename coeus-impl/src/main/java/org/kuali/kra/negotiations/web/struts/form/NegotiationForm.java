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
package org.kuali.kra.negotiations.web.struts.form;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.notification.impl.NotificationHelper;
import org.kuali.coeus.sys.framework.model.KcTransactionalDocumentFormBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.authorization.KraAuthorizationConstants;
import org.kuali.coeus.common.framework.medusa.MedusaBean;
import org.kuali.kra.negotiations.bo.*;
import org.kuali.kra.negotiations.customdata.CustomDataHelper;
import org.kuali.kra.negotiations.document.NegotiationDocument;
import org.kuali.kra.negotiations.notifications.NegotiationCloseNotificationContext;
import org.kuali.kra.negotiations.notifications.NegotiationNotification;
import org.kuali.kra.negotiations.service.NegotiationService;
import org.kuali.coeus.common.framework.custom.CustomDataDocumentForm;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kns.web.ui.HeaderField;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.KRADConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.kuali.rice.krad.util.KRADConstants.EMPTY_STRING;

/**
 * 
 * This class holds all the objects required for a negotiation web object.
 */
public class NegotiationForm extends KcTransactionalDocumentFormBase implements CustomDataDocumentForm {
    
    private static final long serialVersionUID = 6245888664423593163L;


    private final String filterAllActivities = "All";
    private final String filterPendingActivities = "Pending";

    
    private List<NegotiationUnassociatedDetail> negotiationUnassociatedDetailsToDelete;
    private NegotiationActivityHelper negotiationActivityHelper;
    private NegotiationAssociatedDetailBean negotiationAssociatedDetailBean;
    private CustomDataHelper customDataHelper = new CustomDataHelper(this);
    private NotificationHelper<NegotiationCloseNotificationContext> notificationHelper;
    private String filterActivities;
    
    private MedusaBean medusaBean;
    

    public NegotiationForm() {
        super();
        negotiationUnassociatedDetailsToDelete = new ArrayList<NegotiationUnassociatedDetail>();
        negotiationActivityHelper = new NegotiationActivityHelper(this);
        medusaBean = new MedusaBean();
        notificationHelper = new NotificationHelper<NegotiationCloseNotificationContext>();
        filterActivities = "All";
        init();
    }
    
    private void init()
    {
        getCustomDataHelper().prepareCustomData();
    }

    public CustomDataHelper getCustomDataHelper() {
        return customDataHelper;
    }

    /**
     * This method sets the custom data helper
     * 
     * @param customDataHelper
     */
    public void setCustomDataHelper(CustomDataHelper customDataHelper) {
        this.customDataHelper = customDataHelper;
    }

    
    /**
     * This method returns a string representation of the document type
     * 
     * @return
     */
    public String getDocumentTypeName() {
        return "NegotiationDocument";
    }

    
    public NegotiationDocument getNegotiationDocument() {
        return (NegotiationDocument) this.getDocument();
    }
    
    public List<NegotiationUnassociatedDetail> getNegotiationUnassociatedDetailsToDelete() {
        return negotiationUnassociatedDetailsToDelete;
    }

    @Override
    protected String getDefaultDocumentTypeName() {
        return "NegotiationDocument";
    }

    @Override
    protected String getLockRegion() {
        return KraAuthorizationConstants.LOCK_NEGOTIATION;
    }

    @Override
    protected void setSaveDocumentControl(Map editMode) {
        getDocumentActions().put(KRADConstants.KUALI_ACTION_CAN_SAVE, KRADConstants.KUALI_DEFAULT_TRUE_VALUE);

    }
    
    public BusinessObjectService getBusinessObjectService() {
        return KcServiceLocator.getService(BusinessObjectService.class);
    }
    
    public NegotiationService getNegotiationService() {
        return KcServiceLocator.getService(NegotiationService.class);
    }
    
    private boolean isAssocitationType(String typeCode) {
        if (this.getNegotiationDocument().getNegotiation().getNegotiationAssociationType() != null) {
            return StringUtils.equalsIgnoreCase(typeCode, this.getNegotiationDocument().getNegotiation().getNegotiationAssociationType().getCode());
        }
        return false;
    }
    
    public boolean getDisplayUnAssociatedDetail() {
        return isAssocitationType(NegotiationAssociationType.NONE_ASSOCIATION);
    }
    
    public boolean getDisplayProposalLog() {
        return isAssocitationType(NegotiationAssociationType.PROPOSAL_LOG_ASSOCIATION);
    }
    
    public boolean getDisplayInstitutionalProposal() {
        return isAssocitationType(NegotiationAssociationType.INSTITUATIONAL_PROPOSAL_ASSOCIATION);
    }
    
    public boolean getDisplayAward() {
        return isAssocitationType(NegotiationAssociationType.AWARD_ASSOCIATION);
    }
    
    public boolean getDisplaySubAward() {
        return isAssocitationType(NegotiationAssociationType.SUB_AWARD_ASSOCIATION);
    }

    public NegotiationActivityHelper getNegotiationActivityHelper() {
        return negotiationActivityHelper;
    }

    public void setNegotiationActivityHelper(NegotiationActivityHelper negotiationActivityHelper) {
        this.negotiationActivityHelper = negotiationActivityHelper;
    }
    
    public boolean getDispayAssociatedDetailPanel() { 
        return !getDisplayUnAssociatedDetail() && StringUtils.isNotEmpty(this.getNegotiationDocument().getNegotiation().getAssociatedDocumentId());
    }
    
    
    @Override
    public void populateHeaderFields(WorkflowDocument workflowDocument) {
        super.populateHeaderFields(workflowDocument);
        NegotiationDocument nd = getNegotiationDocument();
        final String ATTRIB_NEG_ID = "DataDictionary.Negotiation.attributes.negotiationId";
        final String ATTRIB_NEG_USER_NAME = "DataDictionary.Negotiation.attributes.negotiatorUserName";
        
        if (nd == null || nd.getNegotiation() == null)
        {
            getDocInfo().add(2, new HeaderField(ATTRIB_NEG_ID, EMPTY_STRING));
            getDocInfo().add(2, new HeaderField(ATTRIB_NEG_USER_NAME, EMPTY_STRING));            
            return;
        }
        if (nd.getNegotiation().getNegotiationId() == null)
        {
             getDocInfo().add(2, new HeaderField(ATTRIB_NEG_ID, EMPTY_STRING));
        }
        else
        {             
            getDocInfo().add(2, new HeaderField(ATTRIB_NEG_ID, nd.getNegotiation().getNegotiationId().toString()));            
        }
        if (nd.getNegotiation().getNegotiatorUserName() == null)
        {
            getDocInfo().add(2, new HeaderField(ATTRIB_NEG_USER_NAME, EMPTY_STRING));
        }
        else
        {
            getDocInfo().add(2, new HeaderField(ATTRIB_NEG_USER_NAME, nd.getNegotiation().getNegotiatorUserName()));            
        }
        
    }

    
    /**
     * 
     * This method returns the NegotiationAssociatedDetailBean.  If it hasn't been set, it does so.
     * @return
     */
    public NegotiationAssociatedDetailBean getNegotiationAssociatedDetailBean() {
        Negotiation negotiation = getNegotiationDocument().getNegotiation();
        if (negotiationAssociatedDetailBean == null || !StringUtils.equals(negotiationAssociatedDetailBean.getAssociatedDocumentId(), negotiation.getAssociatedDocumentId())) {
            this.negotiationAssociatedDetailBean = getNegotiationService().buildNegotiationAssociatedDetailBean(negotiation);
        }
        return negotiationAssociatedDetailBean;
    }
    
    /**
     * 
     * This method builds the javascript the disables and enables the ending date field based on the status field.
     * @return
     */
    public String getStatusRelatedJavascript() {
        StringBuffer sb = new StringBuffer(100);
        String newLine = "\n ";
        sb.append("function manageStatusEndDate(doUpdateDate){").append(newLine);
        sb.append("var statusField = document.getElementById('document.negotiationList[0].negotiationStatusId');").append(newLine);
        sb.append("var dateField = document.getElementById('document.negotiationList[0].negotiationEndDate');").append(newLine);
        sb.append("var statusFieldSelectedVal = statusField.options[statusField.selectedIndex].value;").append(newLine);
        sb.append("var dateFieldPicker = document.getElementById('document.negotiationList[0].negotiationEndDate_datepicker');").append(newLine);
        
        sb.append("if (");
        int currentIndex = 0;
        List<String> completedCodes = this.getNegotiationService().getCompletedStatusCodes();
        for (String currentCode : completedCodes) {
            NegotiationStatus currentStatus = getNegotiationStatus(currentCode);
            sb.append("statusFieldSelectedVal == '").append(currentStatus.getId().toString()).append("'");
            if (currentIndex + 1 < completedCodes.size()) {
                sb.append(" || ");
            }
            currentIndex++;
        }
        sb.append(") {").append(newLine);
        
        sb.append("  dateField.disabled = false;").append(newLine);
        sb.append("  if (dateField.value == '' && doUpdateDate) {").append(newLine);
        sb.append("    var currentTime = new Date();").append(newLine);
        sb.append("    dateField.value = currentTime.getMonth() + 1 + \"/\" +  currentTime.getDate() + \"/\" + currentTime.getFullYear();").append(newLine);
        sb.append("  dateFieldPicker.style.display='inline';").append(newLine);
        sb.append("  }").append(newLine).append("} else {").append(newLine);
        sb.append("  dateField.disabled = true;").append(newLine).append("  dateField.value = '';").append(newLine);
        sb.append("  dateFieldPicker.style.display='none';").append(newLine);
        sb.append("}").append(newLine).append("}").append(newLine);
        sb.append("manageStatusEndDate(false);");

        return sb.toString();
    }
    
    private NegotiationStatus getNegotiationStatus(String code) {
        return getNegotiationService().getNegotiationStatus(code);
    }

    public MedusaBean getMedusaBean() {
        return medusaBean;
    }

    public void setMedusaBean(MedusaBean medusaBean) {
        this.medusaBean = medusaBean;
    }

    public String getFilterActivities() {
        return filterActivities;
    }

    public void setFilterActivities(String filterActivities) {
        this.filterActivities = filterActivities;
    }
    
    /**
     * 
     * This method calls the negotiation service and return the results of the getNegotiationActivityHistoryLineBeans function.
     * @return
     */
    public List<NegotiationActivityHistoryLineBean> getNegotiationActivityHistoryLineBeans() {
        return this.getNegotiationService().getNegotiationActivityHistoryLineBeans(this.getNegotiationDocument().getNegotiation().getActivities());
    }

    /**
     * 
     * This method calls the negotiation service and return the results of the getNegotiationNotifications function.
     * @return
     */
    public List<NegotiationNotification> getNegotiationNotifications() {
        return this.getNegotiationService().getNegotiationNotifications(this.getNegotiationDocument().getNegotiation());
    }

    public NotificationHelper<NegotiationCloseNotificationContext> getNotificationHelper() {
        return notificationHelper;
    }

    public void setNotificationHelper(NotificationHelper<NegotiationCloseNotificationContext> notificationHelper) {
        this.notificationHelper = notificationHelper;
    }

    public String getFilterAllActivities() {
        return filterAllActivities;
    }

    public String getFilterPendingActivities() {
        return filterPendingActivities;
    }
    
    /**
     * 
     * This method returns true if the negotiable select icon's need a div tag surrounding it,to generate a javascript warning.
     * @return
     */
    public boolean getDispayChangeAssociatedDocumentWarning() {
        boolean retVal = !StringUtils.isEmpty(this.getNegotiationDocument().getNegotiation().getAssociatedDocumentId());
        return retVal;
    }
    
    /**
     * 
     * This method creates creates the opening div tag for the warning javascript message.
     * @return
     */
    public String getDispayChangeAssociatedDocumentWarningMessage() {
        if (getDispayChangeAssociatedDocumentWarning() && this.getNegotiationDocument().getNegotiation().getNegotiationAssociationType() != null) {
            StringBuffer sb = new StringBuffer("<div id=\"searchIconDiv\" style=\"display: inline;\" onclick=\"return confirm('");
            String associatedType = this.getNegotiationDocument().getNegotiation().getNegotiationAssociationType().getDescription();
            String docNumber = this.getNegotiationDocument().getNegotiation().getAssociatedNegotiable().getAssociatedDocumentId();
            sb.append("This Negotiation is already associated with ").append(associatedType).append(" number ").append(docNumber);
            sb.append(".  Selecting a different ").append(associatedType).append(" document will disassociate this Negotiation with "); 
            sb.append(docNumber).append(".  Are you sure?").append("')\">");
            return sb.toString();
            
        } else {
            return "";
        }
    }

    public String getShortUrl() {
        return getBaseShortUrl() + "/kc-common/negotiations/" + getNegotiationDocument().getNegotiation().getNegotiationId();
    }

}
