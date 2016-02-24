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
package org.kuali.coeus.common.budget.impl.calculator;

import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.rice.core.api.datetime.DateTimeService;

import java.util.Calendar;
import java.util.Date;

/**
 * This class holds the date boundaries ie, Start Date &amp; End Date. Also
 * have methods which to get the date related values like the no of days in
 * StartDate, month of StartDate, etc.
 *
 */
public class Boundary {
    private Date startDate; 
    private Date endDate; 
    private Calendar calendar = Calendar.getInstance();
    private DateTimeService dateTimeService;
    private ScaleTwoDecimal applicableCost;
    private ScaleTwoDecimal applicableCostSharing;
    

    public  Boundary() {
        init();
    }
    
    protected void init() {
        dateTimeService = KcServiceLocator.getService(DateTimeService.class);
    }

/**
 * Constructor...
 * Accepts 2 parameters, viz startDate &amp; endDate and sets the values to the
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
    }

/**
 * Returns the remaining no. of days in startDate
 * 
 * @return int
 */
    public int getStartDateRemainingDays() {        
        calendar.setTime(startDate);
        return calendar.getActualMaximum(Calendar.DATE) - calendar.get(Calendar.DATE) + 1;
    }

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
 * Returns the no. of days between the startDate &amp; endDate
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
    public ScaleTwoDecimal getApplicableCost() {
        return applicableCost==null? ScaleTwoDecimal.ZERO:applicableCost;
    }

    /**
     * Sets the applicableCost attribute value.
     * @param applicableCost The applicableCost to set.
     */
    public void setApplicableCost(ScaleTwoDecimal applicableCost) {
        this.applicableCost = applicableCost;
    }

    /**
     * Gets the applicableCostSharing attribute. 
     * @return Returns the applicableCostSharing.
     */
    public ScaleTwoDecimal getApplicableCostSharing() {
        return applicableCostSharing;
    }

    /**
     * Sets the applicableCostSharing attribute value.
     * @param applicableCostSharing The applicableCostSharing to set.
     */
    public void setApplicableCostSharing(ScaleTwoDecimal applicableCostSharing) {
        this.applicableCostSharing = applicableCostSharing;
    }
    
 }



