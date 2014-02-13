/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.kra.budget.calculator;

import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.rice.core.api.datetime.DateTimeService;

import java.util.Calendar;
import java.util.Date;

/**
 * This class holds the date boundaries ie, Start Date & End Date. Also
 * have methods which to get the date related values like the no of days in
 * StartDate, month of StartDate, etc.
 *
 */
public class Boundary {
    private Date startDate; 
    private Date endDate; 
    private Calendar calendar = Calendar.getInstance();
    private DateTimeService dateTimeService;
    private BudgetDecimal applicableCost;
    private BudgetDecimal applicableCostSharing;
    
/**
 * Default Constructor...
 */
    public  Boundary() {
        init();
    }
    
    protected void init() {
        dateTimeService = KcServiceLocator.getService(DateTimeService.class);
    }

/**
 * Constructor...
 * Accepts 2 parameters, viz startDate & endDate and sets the values to the 
 * class variables
 * 
 * @param startDate 
 * @param endDate 
 */
    public  Boundary(Date startDate, Date endDate) {   
        this();
        this.startDate = startDate;
        this.endDate = endDate;
    } 

    public int getNumberOfDays(){
        return dateTimeService.dateDiff(getStartDate(), getEndDate(), true);
    }
    
/**
 * Returns the startDate Month
 * @return int
 */
    public int getStartDateMonth() {        
        calendar.setTime(startDate);
        return calendar.get(Calendar.MONTH)+1;
    }        
/**
 * Returns the total no. of days in startDate
 * 
 * @return int
 */
    public int getStartDateTotalDays() {        
        calendar.setTime(startDate);
        return calendar.getActualMaximum(Calendar.DATE);
    } // end getStartDateNoOfDays        

/**
 * Returns the remaining no. of days in startDate
 * 
 * @return int
 */
    public int getStartDateRemainingDays() {        
        calendar.setTime(startDate);
        return calendar.getActualMaximum(Calendar.DATE) - calendar.get(Calendar.DATE) + 1;
    } // end getStartDateNoOfDays       

/**
 * Returns the endDate Month * 
 * 
 * @return int
 */
    public int getEndDateMonth() {        
        calendar.setTime(endDate);
        return calendar.get(Calendar.MONTH)+1;
    }

/**
 * Returns the total no. of days in endDate
 * 
 * @return int
 */
    public int getEndDateTotalDays() {        
        calendar.setTime(endDate);
        return calendar.getActualMaximum(Calendar.DATE);
    }        

/**
 * Returns the no. of days in endDate
 * 
 * @return int
 */
    public int getEndDateNoOfDays() {        
        calendar.setTime(endDate);
        return calendar.get(Calendar.DATE);
    }   

/**
 * Returns the no. of days between the startDate & endDate
 * 
 * @return long
 */
    public long getDateDifference() {
        return Math.round((endDate.getTime() - startDate.getTime())/86400000.0d + 1);
    }
    
    /** Getter for property startDate.
     * @return Value of property startDate.
     */
    public java.util.Date getStartDate() {
        return startDate;
    }
    
    /** Setter for property startDate.
     * @param startDate New value of property startDate.
     */
    public void setStartDate(java.util.Date startDate) {
        this.startDate = startDate;
    }
    
    /** Getter for property endDate.
     * @return Value of property endDate.
     */
    public java.util.Date getEndDate() {
        return endDate;
    }
    
    /** Setter for property endDate.
     * @param endDate New value of property endDate.
     */
    public void setEndDate(java.util.Date endDate) {
        this.endDate = endDate;
    }
    /**
     * Overridden method of toString. It will form a string representation of
     * each element associated with this class.
     * @return Concatinated string representation of each element 
     */
    public String toString(){
        StringBuffer strBffr = new StringBuffer("");
        strBffr.append("Start date=>"+startDate);
        strBffr.append(";");
        strBffr.append("End date=>"+endDate);  
        strBffr.append("\n");
        return strBffr.toString();
    }

    /**
     * Gets the applicableCost attribute. 
     * @return Returns the applicableCost.
     */
    public BudgetDecimal getApplicableCost() {
        return applicableCost==null?BudgetDecimal.ZERO:applicableCost;
    }

    /**
     * Sets the applicableCost attribute value.
     * @param applicableCost The applicableCost to set.
     */
    public void setApplicableCost(BudgetDecimal applicableCost) {
        this.applicableCost = applicableCost;
    }

    /**
     * Gets the applicableCostSharing attribute. 
     * @return Returns the applicableCostSharing.
     */
    public BudgetDecimal getApplicableCostSharing() {
        return applicableCostSharing;
    }

    /**
     * Sets the applicableCostSharing attribute value.
     * @param applicableCostSharing The applicableCostSharing to set.
     */
    public void setApplicableCostSharing(BudgetDecimal applicableCostSharing) {
        this.applicableCostSharing = applicableCostSharing;
    }
    
 } // end Boundary



