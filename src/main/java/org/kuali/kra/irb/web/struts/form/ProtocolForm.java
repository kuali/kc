/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.irb.web.struts.form;

import static org.kuali.kra.infrastructure.KraServiceLocator.getService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.kuali.core.datadictionary.DocumentEntry;
import org.kuali.core.datadictionary.HeaderNavigation;
import org.kuali.core.document.authorization.DocumentActionFlags;
import org.kuali.core.service.DataDictionaryService;
import org.kuali.core.service.KualiConfigurationService;
import org.kuali.core.util.ActionFormUtilMap;
import org.kuali.core.workflow.service.KualiWorkflowDocument;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.bo.ProtocolInvestigator;
import org.kuali.kra.irb.bo.ProtocolLocation;
import org.kuali.kra.irb.bo.ProtocolParticipant;
import org.kuali.kra.irb.bo.ProtocolPerson;
import org.kuali.kra.irb.bo.ProtocolReference;
import org.kuali.kra.irb.bo.ProtocolUnit;
import org.kuali.kra.irb.document.ProtocolDocument;
import org.kuali.kra.web.struts.form.KraTransactionalDocumentFormBase;

/**
 * This class...
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */
public class ProtocolForm extends KraTransactionalDocumentFormBase {
    
    private static final long serialVersionUID = -7633960906991275328L;
    
    private ProtocolHelper protocolHelper;
    private PersonnelHelper personnelHelper;
    
    private ProtocolParticipant newProtocolParticipant;
    private ProtocolReference newProtocolReference;
    
    ProtocolPerson newProtocolPerson;
    private List<ProtocolUnit> newProtocolPersonUnits;

    //KNS Lookup hooks
    private String lookupResultsSequenceNumber;
    private String lookupResultsBOClassName;
    
    private String organizationName;
    
    public ProtocolForm() {
        super();
        this.setDocument(new ProtocolDocument());
        initialize();
    }

    /**
     *
     * This method initialize all form variables
     */
    public void initialize() {
        initializeHeaderNavigationTabs(); 
        setNewProtocolParticipant(new ProtocolParticipant());
        setProtocolHelper(new ProtocolHelper(this));
        setPersonnelHelper(new PersonnelHelper(this));
        setNewProtocolReference(new ProtocolReference());
        setNewProtocolPerson(new ProtocolPerson());
        setNewProtocolPersonUnits(new ArrayList<ProtocolUnit>());
    }

    

    public ProtocolDocument getProtocolDocument() {
        return (ProtocolDocument) this.getDocument();
    }

    public ProtocolParticipant getNewProtocolParticipant() {
        return newProtocolParticipant;
    }

    public void setNewProtocolParticipant(ProtocolParticipant newProtocolParticipant) {
        this.newProtocolParticipant = newProtocolParticipant;
    }

    /**
     * 
     * This method initializes the loads the header navigation tabs.
     */
    protected void initializeHeaderNavigationTabs(){
        DataDictionaryService dataDictionaryService = getDataDictionaryService();
        DocumentEntry docEntry = dataDictionaryService.getDataDictionary()
                                    .getDocumentEntry(org.kuali.kra.irb.document.ProtocolDocument.class.getName());
        List<HeaderNavigation> navList = docEntry.getHeaderNavigationList();
        HeaderNavigation[] list = new HeaderNavigation[navList.size()];
        navList.toArray(list);
        super.setHeaderNavigationTabs(list); 
    }
    
    /**
     * 
     * This method is a wrapper method for getting DataDictionary Service using the Service Locator.
     * @return
     */
    protected DataDictionaryService getDataDictionaryService(){
        return (DataDictionaryService) KraServiceLocator.getService(Constants.DATA_DICTIONARY_SERVICE_NAME);
    }

    @Override
    public void populate(HttpServletRequest request) {
        super.populate(request);
        
        // Temporary hack for KRACOEUS-489
        if (getActionFormUtilMap() instanceof ActionFormUtilMap) {
            ((ActionFormUtilMap) getActionFormUtilMap()).clear();
        }       
    }
    
    protected void populateHeaderFields(KualiWorkflowDocument workflowDocument) {
        super.populateHeaderFields(workflowDocument);
    }

    /* Reset method
     * @param mapping
     * @param request
     */
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        super.reset(mapping, request);
        this.setLookupResultsSequenceNumber(null);
        this.setLookupResultsBOClassName(null);
    }


    /**
     * Get the Header Dispatch.  This determines the action that will occur
     * when the user switches tabs for a protocol.  If the user can modify
     * the protocol, the protocol is automatically saved.  If not (view-only),
     * then a reload will be executed instead.
     * @return the Header Dispatch action
     */
    public String getHeaderDispatch() {
        return this.getDocumentActionFlags().getCanSave() ? "save" : "reload";
    }

    public KualiConfigurationService getConfigurationService() {
        return getService(KualiConfigurationService.class);
    }
    
    public String getLookupResultsSequenceNumber() {
        return lookupResultsSequenceNumber;
    }

    public void setLookupResultsSequenceNumber(String lookupResultsSequenceNumber) {
        this.lookupResultsSequenceNumber = lookupResultsSequenceNumber;
    }
    
    public String getLookupResultsBOClassName() {
        return lookupResultsBOClassName;
    }

    public void setLookupResultsBOClassName(String lookupResultsBOClassName) {
        this.lookupResultsBOClassName = lookupResultsBOClassName;
    }

    public void setProtocolHelper(ProtocolHelper protocolHelper) {
        this.protocolHelper = protocolHelper;
    }

    public ProtocolHelper getProtocolHelper() {
        return protocolHelper;
    }
    
    private void setPersonnelHelper(PersonnelHelper personnelHelper) {
        this.personnelHelper = personnelHelper;
    }
    
    public PersonnelHelper getPersonnelHelper() {
        return personnelHelper;
    }
    
    public void setNewProtocolReference(ProtocolReference newProtocolReference) {
        this.newProtocolReference = newProtocolReference;
    }

    public ProtocolReference getNewProtocolReference() {
        return newProtocolReference;
    }
    
    protected void setSaveDocumentControl(DocumentActionFlags tempDocumentActionFlags, Map editMode) {
      
    }
    
    protected String getLockRegion() {
        return "";
    }

    public void setNewProtocolPerson(ProtocolPerson newProtocolPerson) {
        this.newProtocolPerson = newProtocolPerson;
    }

    public ProtocolPerson getNewProtocolPerson() {
        return newProtocolPerson;
    }

    public List<ProtocolUnit> getNewProtocolPersonUnits() {
        if (this.getProtocolDocument().getProtocol().getProtocolPersons().size() > this.newProtocolPersonUnits.size()) {
            this.newProtocolPersonUnits.add(this.newProtocolPersonUnits.size(), new ProtocolUnit());
        }
        return newProtocolPersonUnits;
    }

    public void setNewProtocolPersonUnits(List<ProtocolUnit> newProtocolPersonUnits) {
        this.newProtocolPersonUnits = newProtocolPersonUnits;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

}
