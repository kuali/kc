/*
 * Copyright 2008 The Kuali Foundation.
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
package org.kuali.kra.s2s.generator.bo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.kuali.kra.budget.BudgetDecimal;

public class EquipmentInfo {

    private List<CostInfo> cvEquipmentList;
    private BudgetDecimal totalFund;
    private List<CostInfo> cvExtraEquipmentList;
    private BudgetDecimal totalExtraFund;
    private BudgetDecimal totalNonFund;
    private BudgetDecimal totalExtraNonFund;


    public EquipmentInfo() {
        cvEquipmentList = new ArrayList<CostInfo>();
        cvExtraEquipmentList = new ArrayList<CostInfo>();

    }


    /**
     * Getter for property cvEquipmentList.
     * 
     * @return Value of property cvEquipmentList.
     */
    public List<CostInfo> getEquipmentList() {
        return cvEquipmentList;
    }

    /**
     * Setter for property cvEquipmentList.
     * 
     * @param versionNumber New value of property versionNumber.
     */
    public void setEquipmentList(List<CostInfo> cvEquipmentList) {
        this.cvEquipmentList = cvEquipmentList;
    }


    /**
     * Getter for property totalFund.
     * 
     * @return Value of property totalFund.
     */
    public BudgetDecimal getTotalFund() {
        return totalFund;
    }

    /**
     * Setter for property totalFund.
     * 
     * @param totalFund New value of property totalFund.
     */
    public void setTotalFund(BudgetDecimal totalFund) {
        this.totalFund = totalFund;
    }


    public List<CostInfo> getExtraEquipmentList() {
        return cvExtraEquipmentList;
    }

    /**
     * Setter for property cvExtraEquipmentList.
     * 
     * @param cvExtraEquipmentList New value of property cvExtraEquipmentList.
     */
    public void setExtraEquipmentList(List<CostInfo> cvExtraEquipmentList) {
        this.cvExtraEquipmentList = cvExtraEquipmentList;
    }

    /**
     * Getter for property totalExtraFund.
     * 
     * @return Value of property totalExtraFund.
     */
    public BudgetDecimal getTotalExtraFund() {
        return totalExtraFund;
    }

    /**
     * Setter for property totalExtraFund.
     * 
     * @param totalExtraFund New value of property totalExtraFund.
     */
    public void setTotalExtraFund(BudgetDecimal totalExtraFund) {
        this.totalExtraFund = totalExtraFund;
    }

    // start add costSaring for fedNonFedBudget repport
    /**
     * Getter for property totalNonFund.
     * 
     * @return Value of property totalNonFund.
     */
    public BudgetDecimal getTotalNonFund() {
        return totalNonFund;
    }

    /**
     * Setter for property totalNonFund.
     * 
     * @param totalNonFund New value of property totalNonFund.
     */
    public void setTotalNonFund(BudgetDecimal totalNonFund) {
        this.totalNonFund = totalNonFund;
    }

    /**
     * Getter for property totalExtraNonFund.
     * 
     * @return Value of property totalExtraNonFund.
     */
    public BudgetDecimal getTotalExtraNonFund() {
        return totalExtraNonFund;
    }

    /**
     * Setter for property totalExtraNonFund.
     * 
     * @param totalExtraNonFund New value of property totalExtraNonFund.
     */
    public void setTotalExtraNonFund(BudgetDecimal totalExtraNonFund) {
        this.totalExtraNonFund = totalExtraNonFund;
    }


    protected LinkedHashMap toStringMapper() {
        LinkedHashMap hashMap = new LinkedHashMap();
        hashMap.put("totalFund", getTotalFund());
        hashMap.put("totalExtraFund", getTotalExtraFund());
        hashMap.put("totalNonFund", getTotalNonFund());
        hashMap.put("totalExtraNonFund", getTotalExtraNonFund());
        return hashMap;
    }
}
