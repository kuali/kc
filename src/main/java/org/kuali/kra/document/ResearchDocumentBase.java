/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.document;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.bo.CustomAttributeDocument;
import org.kuali.kra.bo.DocumentNextvalue;
import org.kuali.kra.bo.RolePersons;
import org.kuali.kra.budget.core.BudgetService;
import org.kuali.kra.budget.versions.BudgetDocumentVersion;
import org.kuali.kra.budget.versions.BudgetVersionOverview;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.CustomAttributeService;
import org.kuali.kra.workflow.KraDocumentXMLMaterializer;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kim.api.identity.PersonService;
import org.kuali.rice.krad.bo.DocumentHeader;
import org.kuali.rice.krad.bo.Note;
import org.kuali.rice.krad.document.TransactionalDocumentBase;
import org.kuali.rice.krad.exception.ValidationException;
import org.kuali.rice.krad.rules.rule.event.KualiDocumentEvent;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.workflow.DocumentInitiator;
import org.kuali.rice.krad.workflow.KualiDocumentXmlMaterializer;
import org.kuali.rice.krad.workflow.KualiTransactionalDocumentInformation;

public abstract class ResearchDocumentBase extends TransactionalDocumentBase {
    private static final long serialVersionUID = -1879382692835231633L;
    private static final Log LOG = LogFactory.getLog(ResearchDocumentBase.class);

    private String updateUser;
    private Timestamp updateTimestamp;
    private List<DocumentNextvalue> documentNextvalues;
    private Map<String, CustomAttributeDocument> customAttributeDocuments;
    private boolean viewOnly = false;
    private transient PersonService personService;

    public ResearchDocumentBase() {
        super();
        documentNextvalues = new ArrayList<DocumentNextvalue>();
        customAttributeDocuments = new HashMap<String, CustomAttributeDocument>();
    }

    public void initialize() {
        populateCustomAttributes();
    }

    @Override
    public void prepareForSave() {
        super.prepareForSave();
        String updateUser = GlobalVariables.getUserSession().getPrincipalName();

        // Since the UPDATE_USER column is only VACHAR(60), we need to truncate this string if it's longer than 60 characters
        if (updateUser.length() > 60) {
            updateUser = updateUser.substring(0, 60);
        }

        setUpdateTimestamp((this.getService(DateTimeService.class)).getCurrentTimestamp());
        setUpdateUser(updateUser);

        CustomAttributeService customAttributeService = this.getService(CustomAttributeService.class);
        customAttributeService.saveCustomAttributeValues(this);
        if (this.getVersionNumber() == null) this.setVersionNumber(new Long(0));
        
        // Since we aren't doing optimistic locking, might need to update doc header's version number
        DocumentHeader dbDocHeader = (DocumentHeader) getService(BusinessObjectService.class).retrieve(this.getDocumentHeader());
        if (dbDocHeader != null) {
            this.getDocumentHeader().setVersionNumber(dbDocHeader.getVersionNumber());
        }
    }

    @Override
    public void processAfterRetrieve() {
        super.processAfterRetrieve();
        populateCustomAttributes();
    }

    /**
     * Override to provide more meaningful error message
     * @param event
     */
    @Override
    public void validateBusinessRules(KualiDocumentEvent event) {
        try {
            super.validateBusinessRules(event);
        } catch(ValidationException e) {
            LOG.error(String.format("ValidationException when validating event: %s. Check log entries preceding this error for details.", event.getName()));
            throw e;
        }
    }

    public void updateDocumentDescriptions(List<BudgetDocumentVersion> budgetVersionOverviews) {
        BudgetService budgetService = this.getService(BudgetService.class);
        for (BudgetDocumentVersion budgetDocumentVersion: budgetVersionOverviews) {
            BudgetVersionOverview budgetVersion = budgetDocumentVersion.getBudgetVersionOverview();
            if (budgetVersion.isDescriptionUpdatable() && !StringUtils.isBlank(budgetVersion.getDocumentDescription())) {
                budgetService.updateDocumentDescription(budgetVersion);
                budgetVersion.setDescriptionUpdatable(false); // Only get one chance to set this
            }
        }
    }

    /**
     * This method populates the customAttributes for this document.
     */
    protected void populateCustomAttributes() {
        CustomAttributeService customAttributeService = KraServiceLocator.getService(CustomAttributeService.class);
        Map<String, CustomAttributeDocument> customAttributeDocuments = customAttributeService.getDefaultCustomAttributesForDocumentType(getDocumentTypeCode(), documentNumber);
        setCustomAttributeDocuments(customAttributeDocuments);
    }

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
            if(documentNextvalue.getPropertyName().equalsIgnoreCase(propertyName)) {
                propNextValue = documentNextvalue.getNextValue();
                documentNextvalue.setNextValue(propNextValue + 1);
            }
        }
        // property does not exist - set initial value and increment for next call
        if(propNextValue == 1) {
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
            if(documentNextvalue.getPropertyName().equalsIgnoreCase(propertyName)) {
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
    public Map<String, CustomAttributeDocument> getCustomAttributeDocuments() {
        return customAttributeDocuments;
    }

    /**
     * Gets the customAttributeDocuments attribute.
     * @return Returns the customAttributeDocuments.
     */
    public CustomAttributeDocument getCustomAttributeDocuments(String key) {
        return customAttributeDocuments.get(key);
    }

    /**
     * Gets the customAttributeDocuments attribute.
     * @return Returns the customAttributeDocuments.
     */
    public CustomAttributeDocument getCustomAttributeDocument(String key) {
        return customAttributeDocuments.get(key);
    }
    
    /**
     * Wraps a document in an instance of KualiDocumentXmlMaterializer, that provides additional metadata for serialization
     * 
     * @see org.kuali.rice.krad.document.Document#wrapDocumentWithMetadataForXmlSerialization()
     */
    @Override
    public KualiDocumentXmlMaterializer wrapDocumentWithMetadataForXmlSerialization() {
        KualiTransactionalDocumentInformation transInfo = new KualiTransactionalDocumentInformation();
        DocumentInitiator initiatior = new DocumentInitiator();
        String initiatorNetworkId = getDocumentHeader().getWorkflowDocument().getInitiatorPrincipalId();
        final Person initiatorUser = this.getPersonService().getPersonByPrincipalName(initiatorNetworkId);
        initiatior.setPerson(initiatorUser);
 
        transInfo.setDocumentInitiator(initiatior);
        
        KraDocumentXMLMaterializer xmlWrapper = new KraDocumentXMLMaterializer(); 
        xmlWrapper.setDocument(this); 
        xmlWrapper.setKualiTransactionalDocumentInformation(transInfo); 
        xmlWrapper.setRolepersons(getAllRolePersons()); 
        return xmlWrapper; 
    }

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
    
    /**
     * Lookups and returns a service class.  This method can be overriden for easier unit testing.
     * 
     * @param <T> the type of service.
     * @param serviceClass the service class.
     * @return the service.
     */
    protected <T> T getService(Class<T> serviceClass) {
        return KraServiceLocator.getService(serviceClass);
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
    public boolean answerSplitNodeQuestion(String routeNodeName) throws Exception {
       throw new UnsupportedOperationException( "Document does not support answerSplitNodeQuestion for routeNodeName:"+routeNodeName );   
    }
    
    /**
     * Looks up and returns the PersonService.
     * @return the person service. 
     */
    private PersonService getPersonService() {
        if (this.personService == null) {
            this.personService = KraServiceLocator.getService(PersonService.class);        
        }
        return this.personService;
    }
    
    //default implementation for the permissionable interface.
    public void populateAdditionalQualifiedRoleAttributes(Map<String, String> qualifiedRoleAttributes) {
    }

    @Override
    public void toCopy() throws WorkflowException, IllegalStateException {
        super.toCopy();
        //Temporary workaround for fixing budget notes OptimisticLockException due to auto-added copy notes
        for(Note note : this.getNotes()) {
            note.setNoteIdentifier(null);
            note.setObjectId(null);
        }
        //Temporary workaround ends here
    }
}
