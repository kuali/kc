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
package org.kuali.kra.iacuc.personnel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.kuali.kra.iacuc.IacucPersonTraining;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.procedures.IacucProcedurePersonResponsible;
import org.kuali.kra.iacuc.procedures.IacucProtocolProcedureService;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.protocol.personnel.ProtocolPersonBase;
import org.kuali.rice.krad.bo.PersistableBusinessObject;


public class IacucProtocolPerson extends ProtocolPersonBase {

    private static final long serialVersionUID = 6676849646094141708L;
    private String procedureQualificationDescription;
    private List<IacucProcedurePersonResponsible> procedureDetails;
    private List<IacucPersonTraining> iacucPersonTrainings;
    
    private static final String PERSON_TRAINED_TRUE = "Yes";
    private static final String PERSON_TRAINED_FALSE = "No";
    
    public IacucProtocolPerson() {
        super();
        setProcedureDetails(new ArrayList<IacucProcedurePersonResponsible>());
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

    public List<IacucProcedurePersonResponsible> getProcedureDetails() {
        return procedureDetails;
    }

    public void setProcedureDetails(List<IacucProcedurePersonResponsible> procedureDetails) {
        this.procedureDetails = procedureDetails;
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

    @SuppressWarnings("unchecked")
    @Override
    public List<Collection<PersistableBusinessObject>> buildListOfDeletionAwareLists() {      
        List<Collection<PersistableBusinessObject>> deleteAwareList = super.buildListOfDeletionAwareLists();
        deleteAwareList.add((Collection) getProcedureDetails());
        return deleteAwareList;
    }

    @Override
    protected void postLoad() {
        super.postLoad();
        setIacucPersonTrainings(getIacucProtocolProcedureService().getIacucPersonTrainingDetails(getPersonId()));
    }

    protected IacucProtocolProcedureService getIacucProtocolProcedureService() {
        return (IacucProtocolProcedureService)KraServiceLocator.getService("iacucProtocolProcedureService");
    }
}
