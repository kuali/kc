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
package org.kuali.coeus.propdev.impl.budget.editable;

import org.kuali.coeus.propdev.api.budget.editable.BudgetColumnsToAlterContract;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.rice.krad.data.jpa.converters.BooleanYNConverter;

import javax.persistence.*;

@Entity
@Table(name = "BUDGET_COLUMNS_TO_ALTER")
public class BudgetColumnsToAlter extends KcPersistableBusinessObjectBase implements BudgetColumnsToAlterContract {

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

    @Override
    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    @Override
    public String getColumnLabel() {
        return columnLabel;
    }

    public void setColumnLabel(String columnLabel) {
        this.columnLabel = columnLabel;
    }

    @Override
    public Integer getDataLength() {
        return dataLength;
    }

    public void setDataLength(Integer dataLength) {
        this.dataLength = dataLength;
    }

    @Override
    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    @Override
    public boolean getHasLookup() {
        return hasLookup;
    }

    public void setHasLookup(boolean hasLookup) {
        this.hasLookup = hasLookup;
    }

    @Override
    public String getLookupClass() {
        return lookupClass;
    }

    public void setLookupClass(String lookupClass) {
        this.lookupClass = lookupClass;
    }

    @Override
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
