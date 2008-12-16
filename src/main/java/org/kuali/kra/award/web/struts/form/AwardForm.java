/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.award.web.struts.form;

import java.util.List;
import java.util.Map;

import org.kuali.core.datadictionary.DocumentEntry;
import org.kuali.core.datadictionary.HeaderNavigation;
import org.kuali.core.document.authorization.DocumentActionFlags;
import org.kuali.core.service.DataDictionaryService;
import org.kuali.kra.authorization.KraAuthorizationConstants;
import org.kuali.kra.award.bo.AwardFandaRate;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.web.struts.form.KraTransactionalDocumentFormBase;
import org.kuali.kra.award.bo.*;

import edu.iu.uis.eden.clientapp.IDocHandler;

/**
 * 
 * This class represents the Award Form Struts class.
 */
public class AwardForm extends KraTransactionalDocumentFormBase {
    
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -7633960906991275328L;
    public static final String SAVE = "save";
    public static final String RELOAD = "reload";
    private AwardCostShare newAwardCostShare;
    private AwardComment newAwardCostShareComment;
    
    private AwardFandaRate newAwardFandaRate;
    
    
    public AwardCostShare getNewAwardCostShare() {
        return newAwardCostShare;
    }

    public void setNewAwardCostShare(AwardCostShare newAwardCostShare) {
        this.newAwardCostShare = newAwardCostShare;
    }

    /**
     * 
     * Constructs a AwardForm.java.
     */
    public AwardForm() {
        super();        
        this.setDocument(new AwardDocument());
        initialize();        
    }
    
    /**
     * 
     * This method initialize all form variables
     */
    public void initialize() {
        initializeHeaderNavigationTabs();
        newAwardCostShare = new AwardCostShare();
        newAwardFandaRate = new AwardFandaRate();
    }    
    
    /**
     * Get the Header Dispatch.  This determines the action that will occur
     * when the user switches tabs for a budget.  If the user can modify
     * the budget, the budget is automatically saved.  If not (view-only),
     * then a reload will be executed instead.
     * @return the Header Dispatch action
     */
    public String getHeaderDispatch() {
        return this.getDocumentActionFlags().getCanSave() ? SAVE : RELOAD;        
    }
    
    /**
     * 
     * This method returns the AwardDocument object.
     * @return
     */
    public AwardDocument getAwardDocument() {
        return (AwardDocument) super.getDocument();
    }
    
    /**
     * 
     * This method initializes the loads the header navigation tabs.
     */
    protected void initializeHeaderNavigationTabs(){
        DataDictionaryService dataDictionaryService = getDataDictionaryService();
        DocumentEntry docEntry = dataDictionaryService.getDataDictionary().getDocumentEntry(
                org.kuali.kra.award.document.AwardDocument.class.getName());
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
    
    /**
     * 
     * This method initializes either the document or the form based on the command value.
     */
    public void initializeFormOrDocumentBasedOnCommand(){
        if (IDocHandler.INITIATE_COMMAND.equals(getCommand())) {
            getAwardDocument().initialize();
        }else{
            initialize();
        }
    }

    public AwardComment getNewAwardCostShareComment() {
        return newAwardCostShareComment;
    }

    public void setNewAwardCostShareComment(AwardComment newAwardCostShareComment) {
        this.newAwardCostShareComment = newAwardCostShareComment;
    }

    /**
     *
     * @return
     */
    public AwardFandaRate getNewAwardFandaRate() {
        return newAwardFandaRate;
    }

    /**
     *
     * @param newAwardFandaRate
     */
    public void setNewAwardFandaRate(AwardFandaRate newAwardFandaRate) {
        this.newAwardFandaRate = newAwardFandaRate;
    }
    
    protected void setSaveDocumentControl(DocumentActionFlags tempDocumentActionFlags, Map editMode) {
        tempDocumentActionFlags.setCanSave(true);
    }
    
    protected String getLockRegion() {
        return KraAuthorizationConstants.LOCK_DESCRIPTOR_AWARD;
    }

}
