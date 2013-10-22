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


public class IacucProtocolLocationProcedure extends IacucProcedureDetailBase { 
    
    private static final long serialVersionUID = 1L;

    private Integer iacucProtocolLocationProcedureId; 
    private Integer iacucProcedureLocationDetailId; 
    
    private IacucProcedureLocationDetail iacucProcedureLocationDetail;
    
    public IacucProtocolLocationProcedure() { 
    } 
    
    public void resetPersistenceState() {
        this.setIacucProcedureLocationDetailId(null);        
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
        IacucProtocolLocationProcedure other = (IacucProtocolLocationProcedure) obj;
        if (this.iacucProcedureLocationDetailId == null) {
            if (other.iacucProcedureLocationDetailId != null) {
                return false;
            }
        } else if (!this.iacucProcedureLocationDetailId.equals(other.iacucProcedureLocationDetailId)) {
            return false;
        }

        return true;
    }

    public Integer getIacucProcedureLocationDetailId() {
        return iacucProcedureLocationDetailId;
    }


    public void setIacucProcedureLocationDetailId(Integer iacucProcedureLocationDetailId) {
        this.iacucProcedureLocationDetailId = iacucProcedureLocationDetailId;
    }


    public Integer getIacucProtocolLocationProcedureId() {
        return iacucProtocolLocationProcedureId;
    }


    public void setIacucProtocolLocationProcedureId(Integer iacucProtocolLocationProcedureId) {
        this.iacucProtocolLocationProcedureId = iacucProtocolLocationProcedureId;
    }


    public IacucProcedureLocationDetail getIacucProcedureLocationDetail() {
        return iacucProcedureLocationDetail;
    }


    public void setIacucProcedureLocationDetail(IacucProcedureLocationDetail iacucProcedureLocationDetail) {
        this.iacucProcedureLocationDetail = iacucProcedureLocationDetail;
    }


}
