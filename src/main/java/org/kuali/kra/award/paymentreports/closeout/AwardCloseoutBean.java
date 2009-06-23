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
package org.kuali.kra.award.paymentreports.closeout;

import java.io.Serializable;
import java.util.List;

import org.kuali.kra.award.bo.Award;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.web.struts.form.AwardForm;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.kns.service.KualiConfigurationService;
import org.kuali.rice.kns.service.KualiRuleService;
import org.kuali.rice.kns.web.ui.KeyLabelPair;

/**
 * This class supports the AwardForm class.
 */
public class AwardCloseoutBean implements Serializable {    
    
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 7888151034323714279L;
    
    private AwardCloseout newAwardCloseout;
    private KualiRuleService ruleService;
    private AwardForm form;
    private String closeoutReportTypeUserDefined;
    private String closeoutReportTypeFinancialReport;
    private String closeoutReportTypePatent;
    private String closeoutReportTypeTechnical;
    private String closeoutReportTypeProperty;
   
    /**
     * Constructs an AwardCloseoutBean.
     * @param parent
     */
    public AwardCloseoutBean(AwardForm form) {
        this.form = form;
        initializeAwardCloseoutSystemParams();
    }
    
    /**
     * 
     * Constructs a AwardCloseoutBean.java.
     */
    public AwardCloseoutBean() {
        
    }
    
    private void initializeAwardCloseoutSystemParams(){
        setCloseoutReportTypeUserDefined(getKualiConfigurationService().getParameter(Constants.PARAMETER_MODULE_AWARD ,Constants.PARAMETER_COMPONENT_DOCUMENT
                    ,KeyConstants.CLOSE_OUT_REPORT_TYPE_USER_DEFINED).getParameterValue());
        setCloseoutReportTypeFinancialReport(getKualiConfigurationService().getParameter(Constants.PARAMETER_MODULE_AWARD 
                ,Constants.PARAMETER_COMPONENT_DOCUMENT,KeyConstants.CLOSE_OUT_REPORT_TYPE_FINANCIAL_REPORT).getParameterValue());
        setCloseoutReportTypeTechnical(getKualiConfigurationService().getParameter(Constants.PARAMETER_MODULE_AWARD
                ,Constants.PARAMETER_COMPONENT_DOCUMENT,KeyConstants.CLOSE_OUT_REPORT_TYPE_TECHNICAL).getParameterValue());
        setCloseoutReportTypePatent(getKualiConfigurationService().getParameter(Constants.PARAMETER_MODULE_AWARD,
                Constants.PARAMETER_COMPONENT_DOCUMENT,KeyConstants.CLOSE_OUT_REPORT_TYPE_PATENT).getParameterValue());
        setCloseoutReportTypeProperty(getKualiConfigurationService().getParameter(Constants.PARAMETER_MODULE_AWARD
                ,Constants.PARAMETER_COMPONENT_DOCUMENT,KeyConstants.CLOSE_OUT_REPORT_TYPE_PROPERTY).getParameterValue());
    }
    
    /**
     * This method is called when adding a new Award Closeout item.
     * @param formHelper
     * @return
     */
    public boolean addAwardCloseoutItem() {
        AddAwardCloseoutRuleEvent event = generateAddEvent();
        boolean success = getRuleService().applyRules(event);
        getNewAwardCloseout().setCloseoutReportCode(this.getCloseoutReportTypeUserDefined());
        if(success){
            getAward().add(getNewAwardCloseout());
            init();
        }
        return success;
    }

    /**
     * 
     * This method deletes an award closeout item.
     * @param deletedItemIndex
     */
    public void deleteAwardCloseoutItem(int deletedItemIndex) {
        List<AwardCloseout> items = getAward().getAwardCloseoutItems();
        if (deletedItemIndex >= 0 && deletedItemIndex < items.size()) {
            items.remove(deletedItemIndex);
        }        
    }
    
    /**
     * 
     * This method adds the Award Closeout static reports. This gets called at the time of creation of Award.
     *   
     * @param keyLabelPairs
     */
    public void addAwardCloseoutStaticItems(List<KeyLabelPair> keyLabelPairs) {
        AwardCloseout awardCloseout = new AwardCloseout();
        for (KeyLabelPair keyLabelPair : keyLabelPairs) {
            awardCloseout.setCloseoutReportCode(keyLabelPair.getKey().toString());
            awardCloseout.setCloseoutReportName(keyLabelPair.getLabel());
            getAward().addStaticCloseout(awardCloseout);
            awardCloseout = new AwardCloseout();
        }
    }
    
    /*
     * 
     * This method generates the AddAwardCloseoutRuleEvent event. 
     * @return
     */
    private AddAwardCloseoutRuleEvent generateAddEvent(){
        AddAwardCloseoutRuleEvent event = new AddAwardCloseoutRuleEvent(
                "awardCloseoutBean.newAwardCloseout",
                getAwardDocument(),
                getAward(),
                getNewAwardCloseout());
        return event;
    }
    
    /**
     * @return
     */
    public Award getAward() {
        return form.getAwardDocument().getAward();
    }

    /**
     * @return
     */
    public AwardDocument getAwardDocument() {
        return form.getAwardDocument();
    }
    
    /**
     * @return
     */
    public Object getData() {
        return getNewAwardCloseout();
    }
    
    /**
     * Initialize subform
     */
    public void init() {
        newAwardCloseout = new AwardCloseout(); 
    }
    
    /**
     * 
     * This is a helper method for the retrieval of KualiRuleService
     * @return
     */
    public KualiRuleService getRuleService() {
        if(ruleService == null) {
            ruleService = (KualiRuleService) KraServiceLocator.getService(KualiRuleService.class); 
        }
        return ruleService;
    }
    
    /**
     * 
     * @param ruleService
     */
    public void setRuleService(KualiRuleService ruleService) {
        this.ruleService = ruleService;
    }

    /**
     * Gets the newAwardCloseout attribute. 
     * @return Returns the newAwardCloseout.
     */
    public AwardCloseout getNewAwardCloseout() {
        return newAwardCloseout;
    }

    /**
     * Sets the newAwardCloseout attribute value.
     * @param newAwardCloseout The newAwardCloseout to set.
     */
    public void setNewAwardCloseout(AwardCloseout newAwardCloseout) {
        this.newAwardCloseout = newAwardCloseout;
    }
    
    protected KualiConfigurationService getKualiConfigurationService(){
        return (KualiConfigurationService) KraServiceLocator.getService(KualiConfigurationService.class);
    }

    /**
     * Gets the closeoutReportTypeUserDefined attribute. 
     * @return Returns the closeoutReportTypeUserDefined.
     */
    public String getCloseoutReportTypeUserDefined() {
        return closeoutReportTypeUserDefined;
    }

    /**
     * Sets the closeoutReportTypeUserDefined attribute value.
     * @param closeoutReportTypeUserDefined The closeoutReportTypeUserDefined to set.
     */
    public void setCloseoutReportTypeUserDefined(String closeoutReportTypeUserDefined) {
        this.closeoutReportTypeUserDefined = closeoutReportTypeUserDefined;
    }

    /**
     * Gets the closeoutReportTypeFinancialReport attribute. 
     * @return Returns the closeoutReportTypeFinancialReport.
     */
    public String getCloseoutReportTypeFinancialReport() {
        return closeoutReportTypeFinancialReport;
    }

    /**
     * Sets the closeoutReportTypeFinancialReport attribute value.
     * @param closeoutReportTypeFinancialReport The closeoutReportTypeFinancialReport to set.
     */
    public void setCloseoutReportTypeFinancialReport(String closeoutReportTypeFinancialReport) {
        this.closeoutReportTypeFinancialReport = closeoutReportTypeFinancialReport;
    }

    /**
     * Gets the closeoutReportTypePatent attribute. 
     * @return Returns the closeoutReportTypePatent.
     */
    public String getCloseoutReportTypePatent() {
        return closeoutReportTypePatent;
    }

    /**
     * Sets the closeoutReportTypePatent attribute value.
     * @param closeoutReportTypePatent The closeoutReportTypePatent to set.
     */
    public void setCloseoutReportTypePatent(String closeoutReportTypePatent) {
        this.closeoutReportTypePatent = closeoutReportTypePatent;
    }

    /**
     * Gets the closeoutReportTypeTechnical attribute. 
     * @return Returns the closeoutReportTypeTechnical.
     */
    public String getCloseoutReportTypeTechnical() {
        return closeoutReportTypeTechnical;
    }

    /**
     * Sets the closeoutReportTypeTechnical attribute value.
     * @param closeoutReportTypeTechnical The closeoutReportTypeTechnical to set.
     */
    public void setCloseoutReportTypeTechnical(String closeoutReportTypeTechnical) {
        this.closeoutReportTypeTechnical = closeoutReportTypeTechnical;
    }

    /**
     * Gets the closeoutReportTypeProperty attribute. 
     * @return Returns the closeoutReportTypeProperty.
     */
    public String getCloseoutReportTypeProperty() {
        return closeoutReportTypeProperty;
    }

    /**
     * Sets the closeoutReportTypeProperty attribute value.
     * @param closeoutReportTypeProperty The closeoutReportTypeProperty to set.
     */
    public void setCloseoutReportTypeProperty(String closeoutReportTypeProperty) {
        this.closeoutReportTypeProperty = closeoutReportTypeProperty;
    }
}
