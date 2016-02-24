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
package org.kuali.kra.external.budget.service;

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
