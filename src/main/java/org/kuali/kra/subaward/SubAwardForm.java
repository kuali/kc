/*.
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.kra.subaward;

import java.util.List;
import java.util.Map;

import org.apache.struts.upload.FormFile;
import org.kuali.kra.web.struts.form.Auditable;
import org.kuali.kra.web.struts.form.CustomDataDocumentForm;
import org.kuali.kra.web.struts.form.KraTransactionalDocumentFormBase;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.bo.versioning.VersionHistory;
import org.kuali.kra.common.notification.web.struts.form.NotificationHelper;
import org.kuali.kra.common.permissions.web.struts.form.PermissionsForm;
import org.kuali.kra.common.permissions.web.struts.form.PermissionsHelperBase;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.medusa.MedusaBean;
import org.kuali.kra.service.TaskAuthorizationService;
import org.kuali.kra.service.VersionHistoryService;
import org.kuali.kra.subaward.bo.SubAward;
import org.kuali.kra.subaward.bo.SubAwardAmountInfo;
import org.kuali.kra.subaward.bo.SubAwardAmountReleased;
import org.kuali.kra.subaward.bo.SubAwardCloseout;
import org.kuali.kra.subaward.bo.SubAwardContact;
import org.kuali.kra.subaward.bo.SubAwardFundingSource;
import org.kuali.kra.subaward.customdata.CustomDataHelper;
import org.kuali.kra.subaward.document.SubAwardDocument;
import org.kuali.kra.subaward.document.authorization.SubAwardTask;
import org.kuali.kra.subaward.notification.SubAwardNotificationContext;
import org.kuali.kra.subaward.service.SubAwardService;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kns.web.ui.ExtraButton;
import org.kuali.rice.kns.web.ui.HeaderField;
import org.kuali.rice.krad.service.KRADServiceLocator;
import org.kuali.rice.krad.util.GlobalVariables;
/**
 * This class represents the SubAward Form Struts class....
 */
public class SubAwardForm extends KraTransactionalDocumentFormBase
implements PermissionsForm, Auditable, CustomDataDocumentForm {

    private static final long serialVersionUID = -1452575757578523254L;


    public static final String COLUMN = ":";
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

    /**.
    * This is the Getter Method for newFile
    * @return Returns the newFile.
    */
	public FormFile getNewFile() {
		return newFile;
	}

	/**.
	 * This is the Setter Method for newFile
	 * @param newFile The newFile to set.
	 */
	public void setNewFile(FormFile newFile) {
		this.newFile = newFile;
	}

	/**.
	 * This is the Getter Method for auditActivated
	 * @return Returns the auditActivated.
	 */
	public boolean isAuditActivated() {
		return auditActivated;
	}

	/**.
	 * This is the Setter Method for auditActivated
	 * @param auditActivated The auditActivated to set.
	 */
	public void setAuditActivated(boolean auditActivated) {
		this.auditActivated = auditActivated;
	}

	/**.
	 * This is the Getter Method for newSubAwardFundingSource
	 * @return Returns the newSubAwardFundingSource.
	 */
	public SubAwardFundingSource getNewSubAwardFundingSource() {
		return newSubAwardFundingSource;
	}

	/**.
	 * This is the Setter Method for newSubAwardFundingSource
	 * @param newSubAwardFundingSource The newSubAwardFundingSource to set.
	 */
	public void setNewSubAwardFundingSource(
			SubAwardFundingSource newSubAwardFundingSource) {
		this.newSubAwardFundingSource = newSubAwardFundingSource;
	}

	 /**.
	 * This is the Getter Method for newSubAwardAmountInfo
	 * @return Returns the newSubAwardAmountInfo.
	 */
	public SubAwardAmountInfo getNewSubAwardAmountInfo() {
		return newSubAwardAmountInfo;
	}

	/**.
	 * This is the Setter Method for newSubAwardAmountInfo
	 * @param newSubAwardAmountInfo The newSubAwardAmountInfo to set.
	 */
	public void setNewSubAwardAmountInfo(SubAwardAmountInfo newSubAwardAmountInfo) {
		this.newSubAwardAmountInfo = newSubAwardAmountInfo;
	}

	/**.
	 * This is the Getter Method for newSubAwardAmountReleased
	 * @return Returns the newSubAwardAmountReleased.
	 */
	public SubAwardAmountReleased getNewSubAwardAmountReleased() {
		return newSubAwardAmountReleased;
	}

	/**.
	 * This is the Setter Method for newSubAwardAmountReleased
	 * @param newSubAwardAmountReleased
	 *  The newSubAwardAmountReleased to set.
	 */
	public void setNewSubAwardAmountReleased(
			SubAwardAmountReleased newSubAwardAmountReleased) {
		this.newSubAwardAmountReleased = newSubAwardAmountReleased;
	}

	/**.
	 * This is the Getter Method for customDataHelper
	 * @return Returns the customDataHelper.
	 */
	public CustomDataHelper getCustomDataHelper() {
		return customDataHelper;
	}

	/**.
	 * This is the Setter Method for customDataHelper
	 * @param customDataHelper The customDataHelper to set.
	 */
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

    /**
	 * Constructs a SubAwardForm.java.
	 */
	public SubAwardForm() {
        super();
        initialize();
    }

   /**.
   * this method for subAward
   */
   private SubAward subAward;


    /**.
     *
     * This method initialize all form variables
     */
    public void initialize() {
        medusaBean = new MedusaBean();
        newSubAwardFundingSource = new SubAwardFundingSource(new Award());
        newSubAwardContact = new SubAwardContact(new Rolodex());
        newSubAwardCloseout = new SubAwardCloseout();
        newSubAwardAmountReleased = new SubAwardAmountReleased();
        newSubAwardAmountInfo = new SubAwardAmountInfo();
        notificationHelper = new NotificationHelper<SubAwardNotificationContext>();
        
    }

    /**
     *
     * This method returns the SubAwardDocument object.
     * @return SubAwardDocument
     */
    public SubAwardDocument getSubAwardDocument() {
        return (SubAwardDocument) super.getDocument();
    }
    /**.
     * This method returns a string representation of the document type
     * @return SubAwardDocument
     */
    public String getDocumentTypeName() {
        return "SubAwardDocument";
    }
    /**
     *
     * @see org.kuali.rice.kns.web.struts.form.
     * KualiDocumentFormBase#populateHeaderFields(
     * org.kuali.rice.kew.api.WorkflowDocument)
     */
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

    /**
     * 
     * This method initializes either the document
     *  or the form based on the command value.
     */
    public void initializeFormOrDocumentBasedOnCommand(){
        if (KewApiConstants.INITIATE_COMMAND.equals(getCommand())) {
            getSubAwardDocument().initialize();
        } else {
            initialize();
        }
    }


    @Override
    protected String getLockRegion() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected void setSaveDocumentControl(Map editMode) {
        // TODO Auto-generated method stub

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

    public PermissionsHelperBase getPermissionsHelper() {
        // TODO Auto-generated method stub
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

    /**
     * Gets the medusaBean attribute. 
     * @return Returns the medusaBean.
     */
    public MedusaBean getMedusaBean() {
        return medusaBean;
    }

    /**
     * Sets the medusaBean attribute value.
     * @param medusaBean The medusaBean to set.
     */
    public void setMedusaBean(MedusaBean medusaBean) {
        this.medusaBean = medusaBean;
    }

    /**.
     * 
     * This returns the value of subawardservice.
     * getFollowupDateDefaultLengthInDays()
     * to be used on subAwardCloseout.tag
     * @return
     */
    public int getDefaultFollowUpDayDifference() {
        if (defaultFollowUpDayDifference == 0) {
            defaultFollowUpDayDifference = KraServiceLocator.
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

        TaskAuthorizationService tas = KraServiceLocator.getService(TaskAuthorizationService.class);
        ConfigurationService configurationService = KRADServiceLocator.getKualiConfigurationService();
        SubAwardTask task = new SubAwardTask(TaskName.ADD_INVOICE_SUBAWARD, doc);
        if(tas.isAuthorized(GlobalVariables.getUserSession().getPrincipalId(), task)) {       
            String submitToGrantsGovImage = KRADServiceLocator.getKualiConfigurationService().getPropertyValueAsString(externalImageURL) + "buttonsmall_addinvoice.gif";
            addExtraButton("methodToCall.addAmountReleased", submitToGrantsGovImage, "Add Invoice");
        }
        return extraButtons;
    }
    
    /*
     * returns flag indicating if edit button should be displayed at bottom of form 
     * 
     */
    public boolean getDisplayEditButton() {
        boolean displayEditButton = !isViewOnly();
        VersionHistory activeVersion = getVersionHistoryService().findActiveVersion(SubAward.class, getSubAwardDocument().getSubAward().getSubAwardCode());
        if (activeVersion != null) {
            displayEditButton &= activeVersion.getSequenceOwnerSequenceNumber().equals(getSubAwardDocument().getSubAward().getSequenceNumber());
        }
        
        return displayEditButton;
    }
    
    protected VersionHistoryService getVersionHistoryService() {
        return KraServiceLocator.getService(VersionHistoryService.class);
    }

}
