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
package org.kuali.kra.budget.external.budget.service;

import java.io.Serializable;

/*
<p>Java class for BudgetCategoryDTO complex type.
* 
* <p>The following schema fragment specifies the expected content contained within this class.
* 
* <pre>
* &lt;complexType name="budgetCategoryDTO">
*   &lt;complexContent>
*     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
*       &lt;sequence>
*         &lt;element name="budgetCategoryCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
*         &lt;element name="authorPersonName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
*         &lt;element name="budgetCategoryTypeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
*         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
*         &lt;element name="budgetCategoryTypeDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
*       &lt;/sequence>
*     &lt;/restriction>
*   &lt;/complexContent>
* &lt;/complexType>
* </pre>
* 
* 
*/
public class BudgetCategoryDTO implements Serializable {


    private static final long serialVersionUID = 1L;
    
    String budgetCategoryCode;
    String authorPersonName;
    String budgetCategoryTypeCode;
    String description;
    String budgetCategoryTypeDescription;
    
    public String getBudgetCategoryCode() {
        return budgetCategoryCode;
    }
    public void setBudgetCategoryCode(String budgetCategoryCode) {
        this.budgetCategoryCode = budgetCategoryCode;
    }
    public String getAuthorPersonName() {
        return authorPersonName;
    }
    public void setAuthorPersonName(String authorPersonName) {
        this.authorPersonName = authorPersonName;
    }
    public String getBudgetCategoryTypeCode() {
        return budgetCategoryTypeCode;
    }
    public void setBudgetCategoryTypeCode(String budgetCategoryTypeCode) {
        this.budgetCategoryTypeCode = budgetCategoryTypeCode;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getBudgetCategoryTypeDescription() {
        return budgetCategoryTypeDescription;
    }
    public void setBudgetCategoryTypeDescription(String budgetCategoryTypeDescription) {
        this.budgetCategoryTypeDescription = budgetCategoryTypeDescription;
    }

}