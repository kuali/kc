/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.coeus.propdev.impl.person;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.common.framework.unit.UnitService;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.krad.data.DataObjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("proposalPersonService")
public class ProposalPersonServiceImpl implements ProposalPersonService {
    
    private static final Integer UNIT_HEIRARCHY_NODE = 3;
    
    @Autowired
    @Qualifier("dataObjectService")
    private DataObjectService dataObjectService;
    @Autowired
    @Qualifier("kcPersonService")
    private KcPersonService kcPersonService;
    @Autowired
    @Qualifier("unitService")
    private UnitService unitService;

    protected DataObjectService getDataObjectService() {
        return dataObjectService;
    }

    public void setDataObjectService(DataObjectService dataObjectService) {
        this.dataObjectService = dataObjectService;
    }
    
    /**
     * Sets the KC Person Service.
     * @param kcPersonService the kc person service
     */
    public void setKcPersonService(KcPersonService kcPersonService) {
        this.kcPersonService = kcPersonService;
    }
    protected KcPersonService getKcPersonService(){
        return kcPersonService;
    }

    public void setUnitService (UnitService unitService){
        this.unitService = unitService;
    }
    protected UnitService getUnitService (){
        return unitService;
    }
    public String getPersonName(ProposalDevelopmentDocument doc, String userId) {
        String propPersonName = null;
        List<ProposalPerson> proposalPersons = doc.getDevelopmentProposal().getProposalPersons();
        if(proposalPersons.isEmpty()) {
            proposalPersons = getProposalKeyPersonnel(doc.getDevelopmentProposal().getProposalNumber());
        }
        for (ProposalPerson proposalPerson : proposalPersons) {
            if(StringUtils.equals(proposalPerson.getPersonId(), userId)){
                propPersonName = proposalPerson.getFullName();
                break;
            }
        }
        if(StringUtils.isBlank(propPersonName)){
            KcPerson person = this.kcPersonService.getKcPersonByPersonId(userId);
            propPersonName = person.getFullName();
        }
        return propPersonName;
    }
    
    public List<ProposalPerson> getProposalKeyPersonnel(String proposalNumber) {
        Map<String, String> keys = new HashMap<String, String>();
        keys.put("developmentProposal.proposalNumber", proposalNumber);

        final List<ProposalPerson> persons = dataObjectService.findMatching(ProposalPerson.class,
                QueryByCriteria.Builder.andAttributes(keys).build()).getResults();
        return persons;
    }
    
    /**
     * This method is to get division name using the 4th level node on the Unit hierarchy
     * 
     * @param proposalPerson Proposal person.
     * @return divisionName based on the 4th level node on the Unit hierarchy.
     */
    public String getProposalPersonDivisionName(ProposalPerson proposalPerson){
        String personDivisionName = null;
        if(proposalPerson != null ) {
            List<Unit> units = getUnitService().getUnitHierarchyForUnit(proposalPerson.getHomeUnit());
            if(units.size() > UNIT_HEIRARCHY_NODE){
                Unit unit=units.get(UNIT_HEIRARCHY_NODE);
                personDivisionName = unit.getUnitName();
            }
        }
        return personDivisionName;
    }

}
