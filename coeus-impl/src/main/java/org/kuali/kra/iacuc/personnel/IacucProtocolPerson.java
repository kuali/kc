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
package org.kuali.kra.iacuc.personnel;

import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.iacuc.IacucPersonTraining;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.procedures.IacucProcedurePersonResponsible;
import org.kuali.kra.iacuc.procedures.IacucProtocolProcedureService;
import org.kuali.kra.iacuc.procedures.IacucProtocolSpeciesStudyGroup;
import org.kuali.kra.protocol.personnel.ProtocolPersonBase;

import java.util.ArrayList;
import java.util.List;


public class IacucProtocolPerson extends ProtocolPersonBase {

    private static final long serialVersionUID = 6676849646094141708L;
    private String procedureQualificationDescription;
    private List<IacucPersonTraining> iacucPersonTrainings;
    
    private static final String PERSON_TRAINED_TRUE = "Yes";
    private static final String PERSON_TRAINED_FALSE = "No";
    
    private List<IacucProcedurePersonResponsible> iacucProcedurePersonResponsibleList;
    
    /* 
     * List of protocol studies and related procedures grouped by species
     * This collection is populated during protocol procedure actions
     */
    private List<IacucProtocolSpeciesStudyGroup> procedureDetails;
    
    private boolean allProceduresSelected;
    
    public IacucProtocolPerson() {
        super();
        setIacucPersonTrainings(new ArrayList<IacucPersonTraining>());
    }
    
    public IacucProtocol getIacucProtocol() {
        return (IacucProtocol) getProtocol();
    }

    public String getProcedureQualificationDescription() {
        return procedureQualificationDescription;
    }

    public void setProcedureQualificationDescription(String procedureQualificationDescription) {
        this.procedureQualificationDescription = procedureQualificationDescription;
    }

    public List<IacucPersonTraining> getIacucPersonTrainings() {
        return iacucPersonTrainings;
    }

    public void setIacucPersonTrainings(List<IacucPersonTraining> iacucPersonTrainings) {
        this.iacucPersonTrainings = iacucPersonTrainings;
    }
    
    public String getPersonTrainedStatus() {
        return getIacucPersonTrainings().size() > 0 ? PERSON_TRAINED_TRUE : PERSON_TRAINED_FALSE;
    }

    @Override
    protected void postLoad() {
        super.postLoad();
        setIacucPersonTrainings(getIacucProtocolProcedureService().getIacucPersonTrainingDetails(getPersonId()));
    }

    @Override
    protected void postPersist() {
        super.postPersist();
        setIacucPersonTrainings(getIacucProtocolProcedureService().getIacucPersonTrainingDetails(getPersonId()));
    }
    
    protected IacucProtocolProcedureService getIacucProtocolProcedureService() {
        return (IacucProtocolProcedureService) KcServiceLocator.getService("iacucProtocolProcedureService");
    }

    public List<IacucProtocolSpeciesStudyGroup> getProcedureDetails() {
        return procedureDetails;
    }

    public void setProcedureDetails(List<IacucProtocolSpeciesStudyGroup> procedureDetails) {
        this.procedureDetails = procedureDetails;
    }

    public boolean isAllProceduresSelected() {
        return allProceduresSelected;
    }

    public void setAllProceduresSelected(boolean allProceduresSelected) {
        this.allProceduresSelected = allProceduresSelected;
    }

    public List<IacucProcedurePersonResponsible> getIacucProcedurePersonResponsibleList() {
        return iacucProcedurePersonResponsibleList;
    }

    public void setIacucProcedurePersonResponsibleList(List<IacucProcedurePersonResponsible> iacucProcedurePersonResponsibleList) {
        this.iacucProcedurePersonResponsibleList = iacucProcedurePersonResponsibleList;
    }

}
