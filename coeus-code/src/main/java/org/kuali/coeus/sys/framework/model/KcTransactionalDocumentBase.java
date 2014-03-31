/*
 * Copyright 2005-2014 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.coeus.sys.framework.model;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.framework.custom.DocumentCustomData;
import org.kuali.coeus.common.framework.custom.attr.CustomAttributeDocument;
import org.kuali.coeus.common.framework.custom.attr.CustomAttributeService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.framework.workflow.SimpleBooleanSplitNodeAware;
import org.kuali.kra.authorization.KraAuthorizationConstants;
import org.kuali.kra.bo.DocumentNextvalue;
import org.kuali.kra.bo.RolePersons;
import org.kuali.kra.budget.core.BudgetService;
import org.kuali.kra.budget.versions.BudgetDocumentVersion;
import org.kuali.kra.budget.versions.BudgetVersionOverview;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.krad.bo.DocumentHeader;
import org.kuali.rice.krad.bo.Note;
import org.kuali.rice.krad.data.jpa.DisableVersioning;
import org.kuali.rice.krad.document.TransactionalDocumentBase;
import org.kuali.rice.krad.exception.ValidationException;
import org.kuali.rice.krad.rules.rule.event.KualiDocumentEvent;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;
import org.kuali.rice.krad.util.ErrorMessage;
import org.kuali.rice.krad.util.GlobalVariables;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.*;

@DisableVersioning
@MappedSuperclass
@AttributeOverride(name="documentNumber", column = @Column(name = "DOCUMENT_NUMBER",length=14) )
public abstract class KcTransactionalDocumentBase extends TransactionalDocumentBase implements SimpleBooleanSplitNodeAware {

    private static final long serialVersionUID = -1879382692835231633L;

    private static final Log LOG = LogFactory.getLog(KcTransactionalDocumentBase.class);

    @Column(name = "UPDATE_USER")
    private String updateUser;

    @Column(name = "UPDATE_TIMESTAMP")
    private Timestamp updateTimestamp;

    @OneToMany(targetEntity = DocumentNextvalue.class, orphanRemoval = true, cascade = { CascadeType.REFRESH, CascadeType.REMOVE, CascadeType.PERSIST })
    @JoinColumn(name = "DOCUMENT_NUMBER", referencedColumnName = "DOCUMENT_NUMBER", insertable = false, updatable = false)
    private List<DocumentNextvalue> documentNextvalues;

    @Transient
    private Map<String, CustomAttributeDocument> customAttributeDocuments;

    @Transient
    private boolean viewOnly = false;

    public KcTransactionalDocumentBase() {
        super();
        documentNextvalues = new ArrayList<DocumentNextvalue>();
    }

    public void initialize() {
    }

    @Override
    public void prepareForSave() {
        super.prepareForSave();
        String updateUser = GlobalVariables.getUserSession().getPrincipalName();
        // Since the UPDATE_USER column is only VACHAR(60), we need to truncate this string if it's longer than 60 characters
        if (updateUser.length() > 60) {
            updateUser = updateUser.substring(0, 60);
        }
        setUpdateTimestamp(new java.sql.Timestamp(new Date().getTime()));
        setUpdateUser(updateUser);
        if (this.getVersionNumber() == null) {
            this.setVersionNumber(new Long(0));
        }

        documentHeader = KRADServiceLocatorWeb.getDocumentHeaderService().saveDocumentHeader(documentHeader);
    }

    @Override
    protected void prePersist() {
        //do not call super to remove the call to  DocumentHeaderService.saveDocumentHeader(documentHeader)
        if (StringUtils.isEmpty(getObjectId())) {
            setObjectId(UUID.randomUUID().toString());
        }
    }

    @Override
    protected void postPersist() {
        super.postPersist();
        DocumentHeader temp = KRADServiceLocatorWeb.getDocumentHeaderService().getDocumentHeaderById(documentNumber);
        if (temp != null && temp.getWorkflowDocument() != null) {
            documentHeader = temp;
        }
    }

    @Override
    public void postProcessSave(KualiDocumentEvent event) {
        super.postProcessSave(event);
        DocumentHeader temp =  KRADServiceLocatorWeb.getDocumentHeaderService().getDocumentHeaderById(documentNumber);
        if (temp != null && temp.getWorkflowDocument() != null) {
            documentHeader = temp;
        }
    }

    /**
     * Override to provide more meaningful error message
     * @param event
     */
    @Override
    public void validateBusinessRules(KualiDocumentEvent event) {
        try {
            super.validateBusinessRules(event);
        } catch (ValidationException e) {
            String errors = "";
            for (Map.Entry<String, List<ErrorMessage>> entry : GlobalVariables.getMessageMap().getErrorMessages().entrySet()) {
                for (ErrorMessage msg : entry.getValue()) {
                    errors += entry.getKey() + "=" + msg.getErrorKey()  + (msg.getMessageParameters() != null ? Arrays.asList(msg.getMessageParameters()) : Collections.emptyList());
                }
            }

            LOG.error(String.format("ValidationException when validating event: %s. Check log entries preceding this error for details. Errors: %s", event.getName(), errors));
            throw e;
        }
    }

    public void updateDocumentDescriptions(List<BudgetDocumentVersion> budgetVersionOverviews) {
        BudgetService budgetService = KcServiceLocator.getService(BudgetService.class);
        for (BudgetDocumentVersion budgetDocumentVersion : budgetVersionOverviews) {
            BudgetVersionOverview budgetVersion = budgetDocumentVersion.getBudgetVersionOverview();
            if (budgetVersion.isDescriptionUpdatable() && !StringUtils.isBlank(budgetVersion.getDocumentDescription())) {
                budgetService.updateDocumentDescription(budgetVersion);
                budgetVersion.setDescriptionUpdatable(false);
            }
        }
    }

    /**
     * This method populates the customAttributes for this document.
     */
    public Map<String, CustomAttributeDocument> getCustomAttributeDocuments() {
        if (customAttributeDocuments == null) {
            CustomAttributeService customAttributeService = KcServiceLocator.getService(CustomAttributeService.class);
            customAttributeDocuments = customAttributeService.getDefaultCustomAttributeDocuments(getDocumentTypeCode(), getDocumentCustomData());
        }
        return customAttributeDocuments;
    }

    public abstract List<? extends DocumentCustomData> getDocumentCustomData();

    public Timestamp getUpdateTimestamp() {
        return updateTimestamp;
    }

    public void setUpdateTimestamp(Timestamp updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public void setDocumentNextvalues(List<DocumentNextvalue> documentNextvalues) {
        this.documentNextvalues = documentNextvalues;
    }

    public List<DocumentNextvalue> getDocumentNextvalues() {
        return documentNextvalues;
    }

    public Integer getDocumentNextValue(String propertyName) {
        Integer propNextValue = 1;
        // search for property and get the latest number - increment for next call 
        for (DocumentNextvalue documentNextvalue : documentNextvalues) {
            if (documentNextvalue.getPropertyName().equalsIgnoreCase(propertyName)) {
                propNextValue = documentNextvalue.getNextValue();
                documentNextvalue.setNextValue(propNextValue + 1);
            }
        }
        // property does not exist - set initial value and increment for next call 
        if (propNextValue == 1) {
            DocumentNextvalue documentNextvalue = new DocumentNextvalue();
            documentNextvalue.setNextValue(propNextValue + 1);
            documentNextvalue.setPropertyName(propertyName);
            documentNextvalue.setDocumentKey(documentNumber);
            documentNextvalues.add(documentNextvalue);
        }
        setDocumentNextvalues(documentNextvalues);
        return propNextValue;
    }

    // TODO : this is for the attachment that save attachment only when click 'add 
    public DocumentNextvalue getDocumentNextvalueBo(String propertyName) {
        for (DocumentNextvalue documentNextvalue : documentNextvalues) {
            if (documentNextvalue.getPropertyName().equalsIgnoreCase(propertyName)) {
                return documentNextvalue;
            }
        }
        // following should not happen because it already got the next value for this property before calling this for updating 
        DocumentNextvalue documentNextvalue = new DocumentNextvalue();
        documentNextvalue.setNextValue(1);
        documentNextvalue.setPropertyName(propertyName);
        return documentNextvalue;
    }

    /**
     * Sets the customAttributeDocuments attribute value.
     * @param customAttributeDocuments The customAttributeDocuments to set.
     */
    public void setCustomAttributeDocuments(Map<String, CustomAttributeDocument> customAttributeDocuments) {
        this.customAttributeDocuments = customAttributeDocuments;
    }

    /**
     * Gets the customAttributeDocuments attribute.
     * @return Returns the customAttributeDocuments.
     */
    public CustomAttributeDocument getCustomAttributeDocument(String key) {
        return customAttributeDocuments.get(key);
    }

    /*
     * Please keep in mind that the system checks for existing locks by matching the lock descriptor string  against 
     * all the descriptors for the given document while creating new locks. So elements that change over the document's
     * functional process, like updateTimeStamp should never be added to the lock descriptor since this will cause new locks to be created
     * upon every save.
     */
    @Override
    public String getCustomLockDescriptor(Person user) {
        String activeLockRegion = (String) GlobalVariables.getUserSession().retrieveObject(KraAuthorizationConstants.ACTIVE_LOCK_REGION);
        if (StringUtils.isNotEmpty(activeLockRegion)) {
            return this.getDocumentBoNumber() + "-" + activeLockRegion + "-" + GlobalVariables.getUserSession().getPrincipalName();
        }
        return null;
    }

    @Override
    public boolean useCustomLockDescriptors() {
        return true;
    }

    /*
     * Gets the unique identifier of the BO associated with a document.
     * Gets the proposalNumber, awardNumber, negotiationNumber etc.
     */
    public abstract String getDocumentBoNumber();

    /**
     * Get the list of roles for the document along with each of the individuals in those roles.
     * This information will be serialized into XML for workflow routing purposes.  It is
     * expected that this method will be overridden by derived classes.
     * 
     * @return the list of roles and the users in those roles for this document
     */
    protected List<RolePersons> getAllRolePersons() {
        return new ArrayList<RolePersons>();
    }

    public abstract String getDocumentTypeCode();

    public boolean isViewOnly() {
        return viewOnly;
    }

    public void setViewOnly(boolean viewOnly) {
        this.viewOnly = viewOnly;
    }

    /**
     * In documents that support it, this method should answer T/F for a SplitNode question
     * regarding routing.  The SimpleBooleanSplitNode will supply the route node name which implementations
     * should use to answer the question.
     * 
     * For example, isHierarchyChild would be answered by ProposalDevelopementDocument as T if the document
     * is a child within a proposal hierarchy and false otherwise.
     * 
     * If a document does not support answering the question, it should throw an UnsupportedOperationException.
     *  
     * The stub implementation throws an UnsupportedOperationException for any input.
     * 
     * @param routeNodeName The name of the routeNode as it appears in the document workflow definition.
     * @return boolean value representing the answer.
     * @throws UnsupportedOperationException if the document does not support answering a split node question, or
     * if it does not support answering the question for the supplied route name.
     * 
     */
    @Override
    public boolean answerSplitNodeQuestion(String routeNodeName) {
        throw new UnsupportedOperationException("Document does not support answerSplitNodeQuestion for routeNodeName:" + routeNodeName);
    }

    //default implementation for the permissionable interface. 
    public void populateAdditionalQualifiedRoleAttributes(Map<String, String> qualifiedRoleAttributes) {
    }

    @Override
    public void toCopy() throws WorkflowException, IllegalStateException {
        super.toCopy();
        //Temporary workaround for fixing budget notes OptimisticLockException due to auto-added copy notes 
        for (Note note : this.getNotes()) {
            note.setNoteIdentifier(null);
            note.setObjectId(null);
        }
    }

    /**
     * Returns whether the post-processing is considered complete for this document.
     * @return true if the post-processing is complete, false otherwise
     */
    // TODO : have NOT found a consistent indicator of whether a document route is processed or not, so this is a hack 
    public abstract boolean isProcessComplete();
}
