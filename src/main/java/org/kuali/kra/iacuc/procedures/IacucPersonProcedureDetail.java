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
package org.kuali.kra.iacuc.procedures;


public class IacucPersonProcedureDetail extends IacucProcedureDetailBase { 
    
    private static final long serialVersionUID = 1L;

    private Integer iacucPersonProcedureDetailId; 
    private Integer iacucProcedurePersonResponsibleId; 
    
    private IacucProcedurePersonResponsible iacucProcedurePersonResponsible;
    
    public IacucPersonProcedureDetail() { 
    } 
    
    public Integer getIacucPersonProcedureDetailId() {
        return iacucPersonProcedureDetailId;
    }


    public void setIacucPersonProcedureDetailId(Integer iacucPersonProcedureDetailId) {
        this.iacucPersonProcedureDetailId = iacucPersonProcedureDetailId;
    }

    public Integer getIacucProcedurePersonResponsibleId() {
        return iacucProcedurePersonResponsibleId;
    }

    public void setIacucProcedurePersonResponsibleId(Integer iacucProcedurePersonResponsibleId) {
        this.iacucProcedurePersonResponsibleId = iacucProcedurePersonResponsibleId;
    }


    public void resetPersistenceState() {
        this.setIacucPersonProcedureDetailId(null);        
    }

    /**  {@inheritDoc} */
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
        IacucPersonProcedureDetail other = (IacucPersonProcedureDetail) obj;
        if (this.getIacucProtocolStudyGroupHeaderId() == null) {
            if (other.getIacucProtocolStudyGroupHeaderId() != null) {
                return false;
            }
        } else if (!this.getIacucProtocolStudyGroupHeaderId().equals(other.getIacucProtocolStudyGroupHeaderId())) {
            return false;
        }
        if (this.iacucPersonProcedureDetailId == null) {
            if (other.iacucPersonProcedureDetailId != null) {
                return false;
            }
        } else if (!this.iacucPersonProcedureDetailId.equals(other.iacucPersonProcedureDetailId)) {
            return false;
        }
        if (this.iacucProcedurePersonResponsibleId == null) {
            if (other.iacucProcedurePersonResponsibleId != null) {
                return false;
            }
        } else if (!this.iacucProcedurePersonResponsibleId.equals(other.iacucProcedurePersonResponsibleId)) {
            return false;
        }
        return true;
    }

    public IacucProcedurePersonResponsible getIacucProcedurePersonResponsible() {
        return iacucProcedurePersonResponsible;
    }

    public void setIacucProcedurePersonResponsible(IacucProcedurePersonResponsible iacucProcedurePersonResponsible) {
        this.iacucProcedurePersonResponsible = iacucProcedurePersonResponsible;
    }


}
