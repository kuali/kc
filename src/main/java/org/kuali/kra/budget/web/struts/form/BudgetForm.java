/*
 * Copyright 2007 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.budget.web.struts.form;

import java.util.List;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.kuali.core.service.DataDictionaryService;
import org.kuali.core.service.KualiConfigurationService;
import org.kuali.core.util.ActionFormUtilMap;
import org.kuali.core.web.ui.ExtraButton;
import org.kuali.kra.budget.bo.BudgetPeriod;
import org.kuali.core.service.KualiConfigurationService;
import org.kuali.core.web.ui.ExtraButton;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.web.struts.form.KraTransactionalDocumentFormBase;

public class BudgetForm extends KraTransactionalDocumentFormBase {
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(BudgetForm.class);
    private BudgetPeriod newBudgetPeriod;

    private List<ExtraButton> extraTopButtons;
    
    public BudgetForm() {
        super();
        this.setDocument(new BudgetDocument());
        initialize();
    }

    /**
     * 
     * This method initialize all form variables
     */
    public void initialize() {
        DataDictionaryService dataDictionaryService = (DataDictionaryService) KraServiceLocator.getService(Constants.DATA_DICTIONARY_SERVICE_NAME);
        this.setHeaderNavigationTabs((dataDictionaryService.getDataDictionary().getDocumentEntry(org.kuali.kra.budget.document.BudgetDocument.class.getName())).getHeaderTabNavigation());
        setNewBudgetPeriod(new BudgetPeriod());
        setExtraTopButtons(new ArrayList<ExtraButton>());
        ExtraButton returnToProposal = new ExtraButton();
        returnToProposal.setExtraButtonProperty("methodToCall.returnToProposal");
        KualiConfigurationService configService = KraServiceLocator.getService(KualiConfigurationService.class);
        String imagesUrl = configService.getPropertyString("kra.externalizable.images.url");
        returnToProposal.setExtraButtonSource(imagesUrl + "tinybutton-retprop.gif");
        returnToProposal.setExtraButtonAltText("return to proposal");
        extraTopButtons.add(returnToProposal);
    }

    public BudgetDocument getBudgetDocument() {
        return (BudgetDocument) this.getDocument();
    }
    
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        super.reset(mapping, request);
        // if there are more ...
        for(Object displayedErrorsKey:getDisplayedErrors().keySet()) {
            getDisplayedErrors().put(displayedErrorsKey, false);
        }
    }

    public BudgetPeriod getNewBudgetPeriod() {
        return newBudgetPeriod;
    }

    public void setNewBudgetPeriod(BudgetPeriod newBudgetPeriod) {
        Integer budgetPeriod = 1;
        if(getBudgetDocument().getBudgetPeriods() != null) {
            budgetPeriod = getBudgetDocument().getBudgetPeriods().size() + 1;
        }
        newBudgetPeriod.setBudgetPeriod(budgetPeriod);
        this.newBudgetPeriod = newBudgetPeriod;
    }

    @Override
    public List<ExtraButton> getExtraButtons() {
        // clear out the extra buttons array
        extraButtons.clear();
        String externalImageURL = "kra.externalizable.images.url";
        String generatePeriodImage = KraServiceLocator.getService(KualiConfigurationService.class).getPropertyString(externalImageURL) + "buttonsmall_generatePeriods.gif"; 
        String calculatePeriodImage = KraServiceLocator.getService(KualiConfigurationService.class).getPropertyString(externalImageURL) + "buttonsmall_calculatePeriods.gif"; 
        String appExternalImageURL = "ConfigProperties.kra.externalizable.images.url"; 
        addExtraButton("methodToCall.generateAllPeriods", generatePeriodImage, "Generate All Periods");
        addExtraButton("methodToCall.calculateAllPeriods",calculatePeriodImage, "Calculate All Periods");
        
        return extraButtons;
    }

    /**
     * This is a utility method to add a new button to the extra buttons
     * collection.
     *   
     * @param property
     * @param source
     * @param altText
     */ 
    protected void addExtraButton(String property, String source, String altText){
        
        ExtraButton newButton = new ExtraButton();
        
        newButton.setExtraButtonProperty(property);
        newButton.setExtraButtonSource(source);
        newButton.setExtraButtonAltText(altText);
        
        extraButtons.add(newButton);
    }

    public List<ExtraButton> getExtraTopButtons() {
        return extraTopButtons;
    }

    public void setExtraTopButtons(List<ExtraButton> extraTopButtons) {
        this.extraTopButtons = extraTopButtons;
    }
    
}
