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

package org.kuali.kra.subaward;

import org.apache.struts.upload.FormFile;
import org.kuali.coeus.common.framework.rolodex.Rolodex;
import org.kuali.coeus.common.framework.version.VersionStatus;
import org.kuali.coeus.common.framework.version.history.VersionHistoryService;
import org.kuali.coeus.common.notification.impl.NotificationHelper;
import org.kuali.coeus.common.permissions.impl.web.struts.form.PermissionsForm;
import org.kuali.coeus.common.permissions.impl.web.struts.form.PermissionsHelperBase;
import org.kuali.coeus.common.framework.auth.task.TaskAuthorizationService;
import org.kuali.coeus.sys.framework.validation.Auditable;
import org.kuali.coeus.sys.framework.model.KcTransactionalDocumentFormBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.coeus.common.framework.medusa.MedusaBean;
import org.kuali.kra.subaward.bo.*;
import org.kuali.kra.subaward.customdata.CustomDataHelper;
import org.kuali.kra.subaward.document.SubAwardDocument;
import org.kuali.kra.subaward.document.authorization.SubAwardTask;
import org.kuali.kra.subaward.notification.SubAwardNotificationContext;
import org.kuali.kra.subaward.service.SubAwardService;
import org.kuali.kra.subaward.templateAttachments.SubAwardAttachmentFormBean;
import org.kuali.coeus.common.framework.custom.CustomDataDocumentForm;
import org.kuali.rice.core.api.CoreApiServiceLocator;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kns.util.ActionFormUtilMap;
import org.kuali.rice.kns.web.ui.ExtraButton;
import org.kuali.rice.kns.web.ui.HeaderField;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class SubAwardForm extends KcTransactionalDocumentFormBase
implements PermissionsForm, Auditable, CustomDataDocumentForm {

    private static final long serialVersionUID = -1452575757578523254L;

    private static final String COLUMN = ":";
    private static final String USE_SUBAWARD_INVOICE_INQUIRY = "USE_SUBAWARD_INVOICE_INQUIRY";

    private String lookupResultsSequenceNumber;
    private String lookupResultsBOClassName;
    private SubAwardCloseout newSubAwardCloseout;
    private SubAwardFundingSource newSubAwardFundingSource;
    private SubAwardAmountInfo newSubAwardAmountInfo;
    private SubAwardContact newSubAwardContact;
    private SubAwardAmountReleased newSubAwardAmountReleased;
    private CustomDataHelper customDataHelper = new CustomDataHelper(this);
    private NotificationHelper<SubAwardNotificationContext> notificationHelper;
    private boolean auditActivated;
    private MedusaBean medusaBean;
    private FormFile newFile;
    private int defaultFollowUpDayDifference = 0;
    private SubAwardAttachmentFormBean subAwardAttachmentFormBean;
    private SubAwardReports newSubAwardReport;
    private SubAwardPrintAgreement subAwardPrintAgreement;
    private SubAwardForms subAwardForms;
    private SubAward subAward;
    private transient ParameterService parameterService;
   

    public SubAwardForms getSubAwardForms() {
        return subAwardForms;
    }

    public void setSubAwardForms(SubAwardForms subAwardForms) {
        this.subAwardForms = subAwardForms;
    }

    public SubAwardPrintAgreement getSubAwardPrintAgreement() {
        return subAwardPrintAgreement;
    }

    public void setSubAwardPrintAgreement(SubAwardPrintAgreement subAwardPrintAgreement) {
        this.subAwardPrintAgreement = subAwardPrintAgreement;
    }

    public SubAwardReports getNewSubAwardReport() {
        return newSubAwardReport;
    }

    public void setNewSubAwardReport(SubAwardReports newSubAwardReport) {
        this.newSubAwardReport = newSubAwardReport;
    }

    public SubAwardAttachmentFormBean getSubAwardAttachmentFormBean() {
        return subAwardAttachmentFormBean;
    }

    public void setSubAwardAttachmentFormBean(SubAwardAttachmentFormBean subAwardAttachmentFormBean) {
        this.subAwardAttachmentFormBean = subAwardAttachmentFormBean;
    }

	public FormFile getNewFile() {
		return newFile;
	}

	public void setNewFile(FormFile newFile) {
		this.newFile = newFile;
	}

	public boolean isAuditActivated() {
		return auditActivated;
	}

	public void setAuditActivated(boolean auditActivated) {
		this.auditActivated = auditActivated;
	}

	public SubAwardFundingSource getNewSubAwardFundingSource() {
		return newSubAwardFundingSource;
	}

	public void setNewSubAwardFundingSource(
			SubAwardFundingSource newSubAwardFundingSource) {
		this.newSubAwardFundingSource = newSubAwardFundingSource;
	}

	public SubAwardAmountInfo getNewSubAwardAmountInfo() {
		return newSubAwardAmountInfo;
	}

	public void setNewSubAwardAmountInfo(SubAwardAmountInfo newSubAwardAmountInfo) {
		this.newSubAwardAmountInfo = newSubAwardAmountInfo;
	}

	public SubAwardAmountReleased getNewSubAwardAmountReleased() {
		return newSubAwardAmountReleased;
	}

	public void setNewSubAwardAmountReleased(
			SubAwardAmountReleased newSubAwardAmountReleased) {
		this.newSubAwardAmountReleased = newSubAwardAmountReleased;
	}

	public CustomDataHelper getCustomDataHelper() {
		return customDataHelper;
	}

	public void setCustomDataHelper(CustomDataHelper customDataHelper) {
		this.customDataHelper = customDataHelper;
	}

	
	public NotificationHelper<SubAwardNotificationContext> getNotificationHelper() {
        return notificationHelper;
    }

    public void setNotificationHelper(NotificationHelper<SubAwardNotificationContext> notificationHelper) {
        this.notificationHelper = notificationHelper;
    }

    public void setSubAward(SubAward subAward) {
        this.subAward = subAward;
    }

	public SubAwardForm() {
        super();
        initialize();
    }

    public void initialize() {
        medusaBean = new MedusaBean();
        newSubAwardFundingSource = new SubAwardFundingSource(new Award());
        newSubAwardContact = new SubAwardContact(new Rolodex());
        newSubAwardCloseout = new SubAwardCloseout();
        newSubAwardAmountReleased = new SubAwardAmountReleased();
        newSubAwardAmountInfo = new SubAwardAmountInfo();
        notificationHelper = new NotificationHelper<>();
        subAwardAttachmentFormBean = new SubAwardAttachmentFormBean(this);
        subAwardPrintAgreement = new SubAwardPrintAgreement();
    }

    public SubAwardDocument getSubAwardDocument() {
        return (SubAwardDocument) super.getDocument();
    }

    public String getDocumentTypeName() {
        return "SubAwardDocument";
    }

    @Override
    public void populateHeaderFields(WorkflowDocument workflowDocument) {
         super.populateHeaderFields(workflowDocument);

        SubAwardDocument subAwardDocument = getSubAwardDocument();
        getDocInfo().clear();
        String docIdAndStatus = COLUMN;
        if (workflowDocument != null) {
            docIdAndStatus = getDocument().getDocumentNumber()
            + COLUMN + workflowDocument.getStatus().getLabel();
        }
        String lastUpdated ="";
        if (subAwardDocument.getSubAward().getUpdateTimestamp() != null
        && subAwardDocument.getSubAward().getUpdateUser() != null) {

            lastUpdated = subAwardDocument.getSubAward().getUpdateTimestamp().
            toString() + " By " +  subAwardDocument.getSubAward().
            getUpdateUser();
        }

        getDocInfo().add(new HeaderField(
        "DataDictionary.SubAward.attributes.requisitionerId",
        subAwardDocument.getSubAward().getRequisitionerUserName()));
        getDocInfo().add(new HeaderField(
        "DataDictionary.SubAward.attributes.docIdStatus", docIdAndStatus));
        if (subAwardDocument.getSubAward().getUnit() != null) {
            getDocInfo().add(new HeaderField(""
          +  "DataDictionary.SubAward.attributes.requisitionerUnit",
          subAwardDocument.getSubAward().getUnit().getUnitName()));
        } else {
            getDocInfo().add(new HeaderField(
            "DataDictionary.SubAward.attributes.requisitionerUnit", ""));

        }
        getDocInfo().add(new HeaderField(""
        + "DataDictionary.SubAward.attributes.subAwardId",
        subAwardDocument.getSubAward().getSubAwardCode()));
        getDocInfo().add(new HeaderField(""
      + "DataDictionary.SubAward.attributes.organizationId",
       subAwardDocument.getSubAward().getOrganizationName()));
        getDocInfo().add(new HeaderField(""
       + "DataDictionary.SubAward.attributes.lastUpdate", lastUpdated));


    }

    public void initializeFormOrDocumentBasedOnCommand(){
        if (KewApiConstants.INITIATE_COMMAND.equals(getCommand())) {
            getSubAwardDocument().initialize();
        } else {
            initialize();
        }
    }


    @Override
    protected String getLockRegion() {
        return null;
    }

    @Override
    protected void setSaveDocumentControl(Map editMode) {

    }

    @Override
    protected String getDefaultDocumentTypeName() {
        return "SubAwardDocument";
    }

    public SubAward getSubAward() {
        return getSubAwardDocument().getSubAward();
    }

    public void setLookupResultsSequenceNumber(String lookupResultsSequenceNumber) {
        this.lookupResultsSequenceNumber = lookupResultsSequenceNumber;
    }

    public String getLookupResultsSequenceNumber() {
        return lookupResultsSequenceNumber;
    }

    public void setLookupResultsBOClassName(String lookupResultsBOClassName) {
        this.lookupResultsBOClassName = lookupResultsBOClassName;
    }

    public String getLookupResultsBOClassName() {
        return lookupResultsBOClassName;
    }

    @Override
    public PermissionsHelperBase getPermissionsHelper() {
        return null;
    }

    public void setNewSubAwardCloseout(SubAwardCloseout newSubAwardCloseout) {
        this.newSubAwardCloseout = newSubAwardCloseout;
    }

    public SubAwardCloseout getNewSubAwardCloseout() {
        return newSubAwardCloseout;
    }

   

    public void setNewSubAwardContact(SubAwardContact newSubAwardContact) {
        this.newSubAwardContact = newSubAwardContact;
    }

    public SubAwardContact getNewSubAwardContact() {
        return newSubAwardContact;
    }

    public MedusaBean getMedusaBean() {
        return medusaBean;
    }

    public void setMedusaBean(MedusaBean medusaBean) {
        this.medusaBean = medusaBean;
    }

    public int getDefaultFollowUpDayDifference() {
        if (defaultFollowUpDayDifference == 0) {
            defaultFollowUpDayDifference = KcServiceLocator.
            getService(SubAwardService.class).getFollowupDateDefaultLengthInDays();
        }
        return defaultFollowUpDayDifference;
    }
        
    public List<ExtraButton> getExtraFinancialButtons() {
        // clear out the extra buttons array
        extraButtons.clear();
        extraButtons = super.getExtraButtons();
        SubAwardDocument doc = this.getSubAwardDocument();
        String externalImageURL = Constants.KRA_EXTERNALIZABLE_IMAGES_URI_KEY;

        TaskAuthorizationService tas = KcServiceLocator.getService(TaskAuthorizationService.class);
        ConfigurationService configurationService = CoreApiServiceLocator.getKualiConfigurationService();
        SubAwardTask task = new SubAwardTask(TaskName.ADD_INVOICE_SUBAWARD, doc);
        if(tas.isAuthorized(GlobalVariables.getUserSession().getPrincipalId(), task)) {       
            String submitToGrantsGovImage = configurationService.getPropertyValueAsString(externalImageURL) + "buttonsmall_addinvoice.gif";
            addExtraButton("methodToCall.addAmountReleased", submitToGrantsGovImage, "Add Invoice");
        }
        return extraButtons;
    }
    
    /*
     * returns flag indicating if edit button should be displayed at bottom of form 
     * 
     */
    public boolean getDisplayEditButton() {
        return !getSubAwardDocument().getDocumentHeader().getWorkflowDocument().isCanceled() && VersionStatus.ACTIVE.toString().equals(getSubAwardDocument().getSubAward().getSubAwardSequenceStatus());
    }

    public String getShortUrl() {
        return getBaseShortUrl() + "/kc-common/subawards/" + getSubAward().getSubAwardCode();
    }

    /**
     * This method disables the caching of drop down lists.  
     * Subaward Print has a drop down list whose value depends on another drop down list.  With caching enabled the
     * drop down list will always be empty.  Disabling caching will reload the drop down list whenever the page is posted.
     */
    @Override
    public void populate(HttpServletRequest request) {
        super.populate(request);
        if (getActionFormUtilMap() != null && getActionFormUtilMap() instanceof ActionFormUtilMap) {
            ((ActionFormUtilMap) getActionFormUtilMap()).setCacheValueFinderResults(false);
        } 
    }
    
    protected VersionHistoryService getVersionHistoryService() {
        return KcServiceLocator.getService(VersionHistoryService.class);
    }

    public boolean isUseInvoiceInquiry() {
        return getParameterService().getParameterValueAsBoolean(Constants.MODULE_NAMESPACE_SUBAWARD, Constants.PARAMETER_COMPONENT_DOCUMENT, USE_SUBAWARD_INVOICE_INQUIRY);
    }

    public ParameterService getParameterService() {
        if (parameterService == null) {
            parameterService = KcServiceLocator.getService(ParameterService.class);
        }

        return parameterService;
    }

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }
}
