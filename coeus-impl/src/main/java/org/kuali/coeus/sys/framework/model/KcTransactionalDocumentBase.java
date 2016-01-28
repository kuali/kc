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
package org.kuali.coeus.sys.framework.model;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.PersistenceBrokerAware;
import org.apache.ojb.broker.PersistenceBrokerException;
import org.kuali.coeus.common.framework.custom.DocumentCustomData;
import org.kuali.coeus.common.framework.custom.attr.CustomAttributeDocument;
import org.kuali.coeus.common.framework.custom.attr.CustomAttributeService;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.framework.workflow.LastActionService;
import org.kuali.coeus.sys.framework.workflow.SimpleBooleanSplitNodeAware;
import org.kuali.kra.authorization.KraAuthorizationConstants;
import org.kuali.kra.bo.DocumentNextvalue;
import org.kuali.kra.bo.RolePersons;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kim.api.identity.IdentityService;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kim.api.identity.principal.Principal;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.bo.Note;
import org.kuali.rice.krad.bo.PersistableBusinessObject;
import org.kuali.rice.krad.bo.PersistableBusinessObjectExtension;
import org.kuali.rice.krad.data.jpa.DisableVersioning;
import org.kuali.rice.krad.document.TransactionalDocumentBase;
import org.kuali.rice.krad.exception.ValidationException;
import org.kuali.rice.krad.rules.rule.event.DocumentEvent;
import org.kuali.rice.krad.util.ErrorMessage;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.LegacyDataFramework;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.Callable;

@DisableVersioning
@MappedSuperclass
@AttributeOverride(name="documentNumber", column = @Column(name = "DOCUMENT_NUMBER",length=14) )
public abstract class KcTransactionalDocumentBase extends TransactionalDocumentBase implements SimpleBooleanSplitNodeAware, KcDataObject, PersistableBusinessObject, PersistenceBrokerAware {

    private static final long serialVersionUID = -1879382692835231633L;

    private static final Log LOG = LogFactory.getLog(KcTransactionalDocumentBase.class);
    
    @Column(name = "UPDATE_USER")
    private String updateUser;

    @Column(name = "UPDATE_TIMESTAMP")
    private Timestamp updateTimestamp;

    @OneToMany(orphanRemoval = true, cascade = { CascadeType.ALL })
    @JoinColumn(name = "DOCUMENT_NUMBER", referencedColumnName = "DOCUMENT_NUMBER")
    private List<DocumentNextvalue> documentNextvalues;

    @Transient
    private Map<String, CustomAttributeDocument> customAttributeDocuments;

    @Transient
    private boolean viewOnly = false;

    @Transient
    private boolean updateUserSet;

    @Transient
    private transient KcDataObjectService kcDataObjectService;

    @Transient
    private transient CustomAttributeService customAttributeService;

    @Transient
    private transient GlobalVariableService globalVariableService;

    @Transient
    private transient LastActionService lastActionService;

    @Transient
    private transient IdentityService identityService;

    public KcTransactionalDocumentBase() {
        super();
        documentNextvalues = new ArrayList<>();
    }

    public void initialize() {
    }

    @LegacyDataFramework
    public final void beforeInsert(PersistenceBroker persistenceBroker) throws PersistenceBrokerException {
        setObjectId(null);
        prePersist();
    }
    
    @Override
    @PrePersist
    protected void prePersist() {
        //do not call super to remove the call to  DocumentHeaderService.saveDocumentHeader(documentHeader)
        getKcDataObjectService().initVersionNumberForPersist(this);
        getKcDataObjectService().initUpdateFieldsForPersist(this);
        getKcDataObjectService().initObjectIdForPersist(this);
    }

    @LegacyDataFramework
    public final void afterInsert(PersistenceBroker persistenceBroker) throws PersistenceBrokerException {
    	postPersist();
    }

    @PostPersist
    protected void postPersist() {}

    @Override
    public void postProcessSave(DocumentEvent event) {
        super.postProcessSave(event);
    }
    
    @LegacyDataFramework
    public final void beforeUpdate(PersistenceBroker persistenceBroker) throws PersistenceBrokerException {
    	preUpdate();
    }

    @Override
    @PreUpdate
    protected void preUpdate() {
        getKcDataObjectService().initObjectIdForUpdate(this);
        getKcDataObjectService().initVersionNumberForUpdate(this);
        getKcDataObjectService().initUpdateFieldsForUpdate(this);

        super.preUpdate();
    }

    /**
     * Override to provide more meaningful error message.
     */
    @Override
    public void validateBusinessRules(DocumentEvent event) {
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

    /**
     * This method populates the customAttributes for this document.
     */
    public Map<String, CustomAttributeDocument> getCustomAttributeDocuments() {
        if (customAttributeDocuments == null) {
            customAttributeDocuments = getCustomAttributeService().getDefaultCustomAttributeDocuments(getDocumentTypeCode(), getDocumentCustomData());
        }
        return customAttributeDocuments;
    }

    public abstract List<? extends DocumentCustomData> getDocumentCustomData();

    @Override
    public Timestamp getUpdateTimestamp() {
        return updateTimestamp;
    }

    @Override
    public void setUpdateTimestamp(Timestamp updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
    }

    @Override
    public String getUpdateUser() {
        return updateUser;
    }

    @Override
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    @Override
    public boolean isUpdateUserSet() {
        return updateUserSet;
    }

    @Override
    public void setUpdateUserSet(boolean updateUserSet) {
        this.updateUserSet = updateUserSet;
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
            return this.getDocumentBoNumber() + "-" + activeLockRegion;
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
        return new ArrayList<>();
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

    KcDataObjectService getKcDataObjectService() {
        if (this.kcDataObjectService == null) {
            this.kcDataObjectService = KcServiceLocator.getService(KcDataObjectService.class);
        }
        return this.kcDataObjectService;
    }

    void setKcDataObjectService(KcDataObjectService kcDataObjectService) {
        this.kcDataObjectService = kcDataObjectService;
    }

    CustomAttributeService getCustomAttributeService() {
        if (this.customAttributeService == null) {
            this.customAttributeService = KcServiceLocator.getService(CustomAttributeService.class);
        }
        return this.customAttributeService;
    }

    void setCustomAttributeService(CustomAttributeService customAttributeService) {
        this.customAttributeService = customAttributeService;
    }

    @Override
    public PersistableBusinessObjectExtension getExtension() {
    	return (PersistableBusinessObjectExtension) super.getExtension();
    }

    @Override
    public void setExtension(PersistableBusinessObjectExtension extension) {
    	super.setExtension(extension);
    }

	@Override
	public void afterDelete(PersistenceBroker arg0) throws PersistenceBrokerException {}

	@Override
	public void afterLookup(PersistenceBroker arg0)
			throws PersistenceBrokerException {
		postLoad();
	}
	
	@PostUpdate
	public void postUpdate() { }

	@Override
	public void afterUpdate(PersistenceBroker arg0)
			throws PersistenceBrokerException {
		postUpdate();
	}
	
	@PreRemove
	public void preRemove() { }

	@Override
	public void beforeDelete(PersistenceBroker arg0)
			throws PersistenceBrokerException {
		preRemove();
	}

    protected <T> T executeAsLastActionUser(Callable<T> callable) {
        try {
            final String lastPrincipalId = getLastActionService().findLastUserActionTakenPrincipalId(getDocumentNumber());
            if (StringUtils.isNotBlank(lastPrincipalId) && !lastPrincipalId.equals(getGlobalVariableService().getUserSession().getPrincipalId())) {
                final Principal principal = getIdentityService().getPrincipal(lastPrincipalId);
                if (principal != null && StringUtils.isNotBlank(principal.getPrincipalName())) {
                    return GlobalVariables.doInNewGlobalVariables(new UserSession(principal.getPrincipalName()), callable);
                }
            }
            return callable.call();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected GlobalVariableService getGlobalVariableService() {
        if (this.globalVariableService == null) {
            this.globalVariableService = KcServiceLocator.getService(GlobalVariableService.class);
        }
        return this.globalVariableService;
    }

    protected void setGlobalVariableService(GlobalVariableService globalVariableService) {
        this.globalVariableService = globalVariableService;
    }

    LastActionService getLastActionService() {
        if (this.lastActionService == null) {
            this.lastActionService = KcServiceLocator.getService(LastActionService.class);
        }
        return this.lastActionService;
    }

    void setLastActionService(LastActionService lastActionService) {
        this.lastActionService = lastActionService;
    }

    IdentityService getIdentityService() {
        if (this.identityService == null) {
            this.identityService = KcServiceLocator.getService(IdentityService.class);
        }
        return this.identityService;
    }

    void setIdentityService(IdentityService identityService) {
        this.identityService = identityService;
    }
}
