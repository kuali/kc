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
package org.kuali.kra.iacuc.procedures;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

public class IacucProcedure extends KcPersistableBusinessObjectBase {
    
    private static final long serialVersionUID = 1L;

    private Integer procedureCode; 
    private Integer procedureCategoryCode; 
    private String procedureDescription; 
    
    private IacucProcedureCategory iacucProcedureCategory; 
    
    private boolean procedureSelected;
    
    public IacucProcedure() { 

    } 
    
    public Integer getProcedureCode() {
        return procedureCode;
    }

    public void setProcedureCode(Integer procedureCode) {
        this.procedureCode = procedureCode;
    }

    public Integer getProcedureCategoryCode() {
        return procedureCategoryCode;
    }

    public void setProcedureCategoryCode(Integer procedureCategoryCode) {
        this.procedureCategoryCode = procedureCategoryCode;
    }

    public String getProcedureDescription() {
        return procedureDescription;
    }

    public void setProcedureDescription(String procedureDescription) {
        this.procedureDescription = procedureDescription;
    }

    public IacucProcedureCategory getIacucProcedureCategory() {
        return iacucProcedureCategory;
    }

    public void setIacucProcedureCategory(IacucProcedureCategory iacucProcedureCategory) {
        this.iacucProcedureCategory = iacucProcedureCategory;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.procedureCategoryCode == null) ? 0 : this.procedureCategoryCode.hashCode());
        result = prime * result + ((this.procedureCode == null) ? 0 : this.procedureCode.hashCode());
        result = prime * result + ((this.procedureDescription == null) ? 0 : this.procedureDescription.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        IacucProcedure other = (IacucProcedure) obj;
        if (this.procedureCategoryCode == null) {
            if (other.procedureCategoryCode != null) {
                return false;
            }
        } else if (!this.procedureCategoryCode.equals(other.procedureCategoryCode)) {
            return false;
        }
        if (this.procedureCode == null) {
            if (other.procedureCode != null) {
                return false;
            }
        } else if (!this.procedureCode.equals(other.procedureCode)) {
            return false;
        }
        if (this.procedureDescription == null) {
            if (other.procedureDescription != null) {
                return false;
            }
        } else if (!this.procedureDescription.equals(other.procedureDescription)) {
            return false;
        }
        return true;
    }

    public boolean isProcedureSelected() {
        return procedureSelected;
    }

    public void setProcedureSelected(boolean procedureSelected) {
        this.procedureSelected = procedureSelected;
    }

    
}
