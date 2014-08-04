/*
 * Copyright 2005-2014 The Kuali Foundation.
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
package org.kuali.coeus.s2sgen.impl.budget;

import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

import java.util.ArrayList;
import java.util.List;

public class EquipmentDto {

    private List<CostDto> cvEquipmentList;
    private ScaleTwoDecimal totalFund;
    private List<CostDto> cvExtraEquipmentList;
    private ScaleTwoDecimal totalExtraFund = ScaleTwoDecimal.ZERO;
    private ScaleTwoDecimal totalNonFund = ScaleTwoDecimal.ZERO;
    private ScaleTwoDecimal totalExtraNonFund = ScaleTwoDecimal.ZERO;


    public EquipmentDto() {
        cvEquipmentList = new ArrayList<CostDto>();
        cvExtraEquipmentList = new ArrayList<CostDto>();

    }


    /**
     * Getter for property cvEquipmentList.
     * 
     * @return Value of property cvEquipmentList.
     */
    public List<CostDto> getEquipmentList() {
        return cvEquipmentList;
    }

    /**
     * Setter for property cvEquipmentList.
     * 
     * @param cvEquipmentList New value of property cvEquipmentList.
     */
    public void setEquipmentList(List<CostDto> cvEquipmentList) {
        this.cvEquipmentList = cvEquipmentList;
    }


    /**
     * Getter for property totalFund.
     * 
     * @return Value of property totalFund.
     */
    public ScaleTwoDecimal getTotalFund() {
        return totalFund;
    }

    /**
     * Setter for property totalFund.
     * 
     * @param totalFund New value of property totalFund.
     */
    public void setTotalFund(ScaleTwoDecimal totalFund) {
        this.totalFund = totalFund;
    }


    public List<CostDto> getExtraEquipmentList() {
        return cvExtraEquipmentList;
    }

    /**
     * Setter for property cvExtraEquipmentList.
     * 
     * @param cvExtraEquipmentList New value of property cvExtraEquipmentList.
     */
    public void setExtraEquipmentList(List<CostDto> cvExtraEquipmentList) {
        this.cvExtraEquipmentList = cvExtraEquipmentList;
    }

    /**
     * Getter for property totalExtraFund.
     * 
     * @return Value of property totalExtraFund.
     */
    public ScaleTwoDecimal getTotalExtraFund() {
        return totalExtraFund;
    }

    /**
     * Setter for property totalExtraFund.
     * 
     * @param totalExtraFund New value of property totalExtraFund.
     */
    public void setTotalExtraFund(ScaleTwoDecimal totalExtraFund) {
        this.totalExtraFund = totalExtraFund;
    }

    // start add costSaring for fedNonFedBudget repport
    /**
     * Getter for property totalNonFund.
     * 
     * @return Value of property totalNonFund.
     */
    public ScaleTwoDecimal getTotalNonFund() {
        return totalNonFund;
    }

    /**
     * Setter for property totalNonFund.
     * 
     * @param totalNonFund New value of property totalNonFund.
     */
    public void setTotalNonFund(ScaleTwoDecimal totalNonFund) {
        this.totalNonFund = totalNonFund;
    }

    /**
     * Getter for property totalExtraNonFund.
     * 
     * @return Value of property totalExtraNonFund.
     */
    public ScaleTwoDecimal getTotalExtraNonFund() {
        return totalExtraNonFund;
    }

    /**
     * Setter for property totalExtraNonFund.
     * 
     * @param totalExtraNonFund New value of property totalExtraNonFund.
     */
    public void setTotalExtraNonFund(ScaleTwoDecimal totalExtraNonFund) {
        this.totalExtraNonFund = totalExtraNonFund;
    }
}
