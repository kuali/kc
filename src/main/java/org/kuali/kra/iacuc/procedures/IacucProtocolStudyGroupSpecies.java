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

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.iacuc.IacucSpecies;
import org.kuali.kra.protocol.ProtocolAssociateBase;

public class IacucProtocolStudyGroupSpecies extends ProtocolAssociateBase {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 5919176798738325709L;

    private Integer iacucProtocolStudyGroupSpeciesId; 
    private Integer speciesCode;

    private IacucSpecies iacucSpecies;

    private List<IacucProcedurePersonResponsible> iacucPersonResponsibleProcedures;

    // Used to display summary
    private List<IacucProtocolStudyGroupBean> protocolStudyProcedures;
    
    public IacucProtocolStudyGroupSpecies() {
        setIacucPersonResponsibleProcedures(new ArrayList<IacucProcedurePersonResponsible>());
        setProtocolStudyProcedures(new ArrayList<IacucProtocolStudyGroupBean>());
    }


    public Integer getSpeciesCode() {
        return speciesCode;
    }

    public void setSpeciesCode(Integer speciesCode) {
        this.speciesCode = speciesCode;
    }

    public IacucSpecies getIacucSpecies() {
        return iacucSpecies;
    }

    public void setIacucSpecies(IacucSpecies iacucSpecies) {
        this.iacucSpecies = iacucSpecies;
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
        IacucProtocolStudyGroupSpecies other = (IacucProtocolStudyGroupSpecies) obj;
        if (this.speciesCode == null) {
            if (other.speciesCode != null) {
                return false;
            }
        } else if (!this.speciesCode.equals(other.speciesCode)) {
            return false;
        }
        return true;
    }

    public void resetPersistenceState() {
        setIacucProtocolStudyGroupSpeciesId(null);
    }

    public Integer getIacucProtocolStudyGroupSpeciesId() {
        return iacucProtocolStudyGroupSpeciesId;
    }

    public void setIacucProtocolStudyGroupSpeciesId(Integer iacucProtocolStudyGroupSpeciesId) {
        this.iacucProtocolStudyGroupSpeciesId = iacucProtocolStudyGroupSpeciesId;
    }


    public List<IacucProcedurePersonResponsible> getIacucPersonResponsibleProcedures() {
        return iacucPersonResponsibleProcedures;
    }


    public void setIacucPersonResponsibleProcedures(List<IacucProcedurePersonResponsible> iacucPersonResponsibleProcedures) {
        this.iacucPersonResponsibleProcedures = iacucPersonResponsibleProcedures;
    }


    public List<IacucProtocolStudyGroupBean> getProtocolStudyProcedures() {
        return protocolStudyProcedures;
    }


    public void setProtocolStudyProcedures(List<IacucProtocolStudyGroupBean> protocolStudyProcedures) {
        this.protocolStudyProcedures = protocolStudyProcedures;
    }



}
