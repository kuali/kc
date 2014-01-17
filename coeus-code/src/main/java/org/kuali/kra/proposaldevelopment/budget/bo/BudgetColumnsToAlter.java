/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.kra.proposaldevelopment.budget.bo;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.rice.krad.data.jpa.converters.BooleanYNConverter;

@Entity
@Table(name = "BUDGET_COLUMNS_TO_ALTER")
public class BudgetColumnsToAlter extends KraPersistableBusinessObjectBase {

    @Id
    @Column(name = "COLUMN_NAME")
    private String columnName;

    @Column(name = "COLUMN_LABEL")
    private String columnLabel;

    @Column(name = "DATA_LENGTH")
    private Integer dataLength;

    @Column(name = "DATA_TYPE")
    private String dataType;

    @Column(name = "HAS_LOOKUP")
    @Convert(converter = BooleanYNConverter.class)
    private boolean hasLookup;

    @Column(name = "LOOKUP_ARGUMENT")
    private String lookupClass;

    @Column(name = "LOOKUP_RETURN")
    private String lookupReturn;

    @Transient
    private String lookupPkReturn;

    public BudgetColumnsToAlter() {
        super();
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnLabel() {
        return columnLabel;
    }

    public void setColumnLabel(String columnLabel) {
        this.columnLabel = columnLabel;
    }

    public Integer getDataLength() {
        return dataLength;
    }

    public void setDataLength(Integer dataLength) {
        this.dataLength = dataLength;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public boolean getHasLookup() {
        return hasLookup;
    }

    public void setHasLookup(boolean hasLookup) {
        this.hasLookup = hasLookup;
    }

    public String getLookupClass() {
        return lookupClass;
    }

    public void setLookupClass(String lookupClass) {
        this.lookupClass = lookupClass;
    }

    public String getLookupReturn() {
        return lookupReturn;
    }

    public void setLookupReturn(String lookupReturn) {
        this.lookupReturn = lookupReturn;
    }

    public String getLookupPkReturn() {
        return lookupPkReturn;
    }

    public void setLookupPkReturn(String lookupPkReturn) {
        this.lookupPkReturn = lookupPkReturn;
    }
}
