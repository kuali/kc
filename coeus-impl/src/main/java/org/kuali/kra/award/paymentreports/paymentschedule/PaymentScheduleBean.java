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
package org.kuali.kra.award.paymentreports.paymentschedule;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.AwardForm;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.paymentreports.awardreports.AwardReportTerm;
import org.kuali.kra.award.service.AwardScheduleGenerationService;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.service.KualiRuleService;
import org.kuali.rice.krad.util.ObjectUtils;

/**
 * This class supports the AwardForm class
 */
public class PaymentScheduleBean implements Serializable {    
    

    private static final long serialVersionUID = -5513993757805685581L;
    private AwardPaymentSchedule newAwardPaymentSchedule;
    private transient KualiRuleService ruleService;
    private AwardForm form;
    
    /**
     * Constructs a PaymentScheduleBean
     * @param parent
     */
    public PaymentScheduleBean(AwardForm form) {
        this.form = form;
        init();
    }
    
    /**
     * This method is called when adding a new payment schedule item
     * @param formHelper
     * @return
     */
    public boolean addPaymentScheduleItem() {
        AddAwardPaymentScheduleRuleEvent event = generateAddEvent();
        boolean success = getRuleService().applyRules(event);
        if(success){
            getAward().add(getNewAwardPaymentSchedule());
            init();
        }
        return success;
    }

    /**
     * This method delets a selected payment schedule item
     * @param formHelper
     * @param deletedItemIndex
     */
    public void deletePaymentScheduleItem(int deletedItemIndex) {
        List<AwardPaymentSchedule> items = getAward().getPaymentScheduleItems();
        if(deletedItemIndex >= 0 && deletedItemIndex < items.size()) {
            items.remove(deletedItemIndex);
        }        
    }
    
    /**
     * 
     * This method generates the payment schedules by calling the <code>AwardScheduleGenerationService</code>
     * @throws ParseException
     * @throws WorkflowException 
     */
    public void generatePaymentSchedules() throws ParseException{
        Map<AwardReportTerm, List<Date>> dateMap = new HashMap<AwardReportTerm, List<Date>>();
        List<AwardReportTerm> awardReportTermItems = getAward().getAwardReportTermItems();
        // save these here

        for (AwardReportTerm art : awardReportTermItems) {
            /*
             * Need to create this secondary list here since the awardScheduleGenerationService takes a list of
             * report term items and we only want the dates for the one item in the loop.
             */
            List<AwardReportTerm> awardReportTerms = new ArrayList<AwardReportTerm>();
            awardReportTerms.add(art);
        List<Date> dates = new ArrayList<Date>();
            dates = getAwardScheduleGenerationService().generateSchedules(getAward(), awardReportTerms, false);
            if (dateMap.containsKey(art)) {
                List<Date> currentDates = dateMap.get(art);
                currentDates.addAll(dates);
                dateMap.put(art, currentDates);
            } else {
                dateMap.put(art, dates);
            }
        }
        
        for (AwardReportTerm art : dateMap.keySet()) {
                List<Date> dates = dateMap.get(art);
        
        for(Date date: dates){
            newAwardPaymentSchedule = new AwardPaymentSchedule();
            java.sql.Date sqldate = new java.sql.Date(date.getTime());
            newAwardPaymentSchedule.setDueDate(sqldate);    
            newAwardPaymentSchedule.setAmount(ScaleTwoDecimal.ZERO);
                    // need to set this or 
                    newAwardPaymentSchedule.setAwardReportTermDescription(getSummary(art));
                    //award -> awardPaymentTerm -> AwardPaymentSchedule(awardPaymentTermId)
                    //award -> awardPaymentSchedule
                    // if not there award_report_term_id is null. 
                    //If I add it, award_id in payment schedule becomes null
                    //art.add(newAwardPaymentSchedule);
                    // this adds the payment schedule to award
            getAward().add(newAwardPaymentSchedule);
        }
        }
        init();
     
    }

    public String getSummary(AwardReportTerm art) {
        art.refreshReferenceObject("frequency");
        art.refreshReferenceObject("report");
        art.refreshReferenceObject("distribution");
        art.refreshReferenceObject("frequencyBase");
        
        String description = "";
        description += StringUtils.isNotEmpty(art.getReport().getDescription()) ?  art.getReport().getDescription() : "";
        description += StringUtils.isNotEmpty(art.getFrequency().getDescription()) ?  "-" + art.getFrequency().getDescription() : "";
        description += StringUtils.isNotEmpty(art.getFrequencyBase().getDescription()) ?  "-" + art.getFrequencyBase().getDescription() : "";
        description += StringUtils.isNotEmpty(art.getDistribution().getDescription()) ?  "-" + art.getDistribution().getDescription() : "";
        description += ObjectUtils.isNotNull(art.getDueDate()) ?  "-" + art.getDueDate() : "";

        return description;
    }
    

    public Award getAward() {
        return form.getAwardDocument().getAward();
    }


    public AwardDocument getAwardDocument() {
        return form.getAwardDocument();
    }
    

    public Object getData() {
        return getNewAwardPaymentSchedule();
    }
    
    /**
     * Initialize subform
     */
    public void init() {
        newAwardPaymentSchedule = new AwardPaymentSchedule(); 
    }
    
    /**
     * 
     * This is a helper method for the retrieval of KualiRuleService
     * @return
     */
    protected KualiRuleService getRuleService() {
        if(ruleService == null) {
            ruleService = (KualiRuleService) KcServiceLocator.getService(KualiRuleService.class);
        }
        return ruleService;
    }
    
    /**
     * 
     * @param ruleService
     */
    protected void setRuleService(KualiRuleService ruleService) {
        this.ruleService = ruleService;
    }
    

    AddAwardPaymentScheduleRuleEvent generateAddEvent() {        
        AddAwardPaymentScheduleRuleEvent event = new AddAwardPaymentScheduleRuleEvent(
                                                            "paymentScheduleBean.newAwardPaymentSchedule",
                                                            getAwardDocument(),
                                                            getAward(),
                                                            getNewAwardPaymentSchedule());
        return event;
    }

    /**
     * Gets the newAwardPaymentSchedule attribute. 
     * @return Returns the newAwardPaymentSchedule.
     */
    public AwardPaymentSchedule getNewAwardPaymentSchedule() {
        return newAwardPaymentSchedule;
    }

    /**
     * Sets the newAwardPaymentSchedule attribute value.
     * @param newAwardPaymentSchedule The newAwardPaymentSchedule to set.
     */
    public void setNewAwardPaymentSchedule(AwardPaymentSchedule newAwardPaymentSchedule) {
        this.newAwardPaymentSchedule = newAwardPaymentSchedule;
    }
    
    /**
     * 
     * This is a helper method to retrieve the AwardScheduleGenerationService.
     * @return
     */
    protected AwardScheduleGenerationService getAwardScheduleGenerationService(){
        return KcServiceLocator.getService(AwardScheduleGenerationService.class);
    }

}
