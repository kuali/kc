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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.kuali.core.bo.Parameter;
import org.kuali.core.datadictionary.DocumentEntry;
import org.kuali.core.datadictionary.HeaderNavigation;
import org.kuali.core.service.DataDictionaryService;
import org.kuali.core.service.KualiConfigurationService;
import org.kuali.core.workflow.service.KualiWorkflowDocument;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.document.ProtocolDocument;
import org.kuali.kra.web.struts.form.KraTransactionalDocumentFormBase;

/**
 * This class...
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */
public class ProtocolForm extends KraTransactionalDocumentFormBase {
    
    private static final long serialVersionUID = -7633960906991275328L;
    private Map<String, Parameter> protocolParameters;
    
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
        protocolParameters = new HashMap<String, Parameter>();
    }


    public ProtocolDocument getProtocolDocument() {
        return (ProtocolDocument) this.getDocument();
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

    public void setProtocolParameters(Map<String, Parameter> protocolParameters) {
        this.protocolParameters = protocolParameters;
    }

    public Map<String, Parameter> getProtocolParameters() {
        return protocolParameters;
    }

}
