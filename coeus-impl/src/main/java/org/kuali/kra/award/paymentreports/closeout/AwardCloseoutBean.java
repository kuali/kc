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
package org.kuali.kra.award.paymentreports.closeout;

import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.AwardForm;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.KualiRuleService;

import java.io.Serializable;
import java.util.List;

/**
 * This class supports the AwardForm class.
 */
public class AwardCloseoutBean implements Serializable {    
    

    private static final long serialVersionUID = 7888151034323714279L;
    
    private AwardCloseout newAwardCloseout;
    private transient KualiRuleService ruleService;
    private AwardForm form;
    private String closeoutReportTypeUserDefined;
    private String closeoutReportTypeFinancialReport;
    private String closeoutReportTypePatent;
    private String closeoutReportTypeTechnical;
    private String closeoutReportTypeProperty;
    private String closeoutReportTypeInvoice;
    private transient ParameterService parameterService;
   
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
     
    public AwardCloseoutBean() {
        
    }*/
    
    private void initializeAwardCloseoutSystemParams(){
        setCloseoutReportTypeUserDefined(getParameterService().getParameterValueAsString(AwardDocument.class, KeyConstants.CLOSE_OUT_REPORT_TYPE_USER_DEFINED));
        setCloseoutReportTypeFinancialReport(getParameterService().getParameterValueAsString(AwardDocument.class, KeyConstants.CLOSE_OUT_REPORT_TYPE_FINANCIAL_REPORT));
        setCloseoutReportTypeTechnical(getParameterService().getParameterValueAsString(AwardDocument.class, KeyConstants.CLOSE_OUT_REPORT_TYPE_TECHNICAL));
        setCloseoutReportTypePatent(getParameterService().getParameterValueAsString(AwardDocument.class, KeyConstants.CLOSE_OUT_REPORT_TYPE_PATENT));
        setCloseoutReportTypeProperty(getParameterService().getParameterValueAsString(AwardDocument.class, KeyConstants.CLOSE_OUT_REPORT_TYPE_PROPERTY));
        setCloseoutReportTypeInvoice(getParameterService().getParameterValueAsString(AwardDocument.class, KeyConstants.CLOSE_OUT_REPORT_TYPE_INVOICE));
        getAward().setAwardCloseoutItems(getAward().getAwardCloseoutItems());
        setNewAwardCloseout(new AwardCloseout());
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
     * @param keyValues
     */
    public void addAwardCloseoutStaticItems(List<KeyValue> keyValues) {
        AwardCloseout awardCloseout = new AwardCloseout();
        for (KeyValue KeyValue : keyValues) {
            awardCloseout.setCloseoutReportCode(KeyValue.getKey().toString());
            awardCloseout.setCloseoutReportName(KeyValue.getValue());
            getAward().addStaticCloseout(awardCloseout);
            awardCloseout = new AwardCloseout();
        }
        getAward().orderStaticCloseOutReportItems(getAward().getAwardCloseoutItems());
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
    

    public Award getAward() {
        return form.getAwardDocument().getAward();
    }


    public AwardDocument getAwardDocument() {
        return form.getAwardDocument();
    }
    

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
            ruleService = (KualiRuleService) KcServiceLocator.getService(KualiRuleService.class);
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
    
    /**
     * Looks up and returns the ParameterService.
     * @return the parameter service. 
     */
    protected ParameterService getParameterService() {
        if (this.parameterService == null) {
            this.parameterService = KcServiceLocator.getService(ParameterService.class);
        }
        return this.parameterService;
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
    
    /**
     * Gets the closeoutReportTypeInvoice attribute. 
     * @return Returns the closeoutReportTypeInvoice.
     */
    public String getCloseoutReportTypeInvoice() {
        return closeoutReportTypeInvoice;
    }

    /**
     * Sets the closeoutReportTypeInvoice attribute value.
     * @param closeoutReportTypeInvoice The closeoutReportTypeInvoice to set.
     */
    public void setCloseoutReportTypeInvoice(String closeoutReportTypeInvoice) {
        this.closeoutReportTypeInvoice = closeoutReportTypeInvoice;
    }
}
