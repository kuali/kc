/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.propdev.impl.budget;

import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

import java.io.Serializable;

public class CostShareInfoDO implements Serializable {

    private String costShareUnit;
    private ScaleTwoDecimal costShareAmount;
    
    public String getCostShareUnit() {
        return costShareUnit;
    }
    public void setCostShareUnit(String costShareUnit) {
        this.costShareUnit = costShareUnit;
    }
    public ScaleTwoDecimal getCostShareAmount() {
        return costShareAmount;
    }
    public void setCostShareAmount(ScaleTwoDecimal costShareAmount) {
        this.costShareAmount = costShareAmount;
    }
    
    
}
