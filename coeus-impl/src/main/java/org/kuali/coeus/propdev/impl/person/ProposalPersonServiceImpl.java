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
package org.kuali.coeus.propdev.impl.person;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.common.framework.unit.UnitService;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.rice.core.api.criteria.PredicateFactory;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.krad.data.DataObjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.*;

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

    /**
     * This method is to get list of ProposalPersons by matching partial name.
     * Wildcards work as well.
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<ProposalPerson> getProposalPersonsByPartialName(String partialName) {
        List<ProposalPerson> results = getDataObjectService().findMatching(ProposalPerson.class, QueryByCriteria.Builder.fromPredicates(PredicateFactory.likeIgnoreCase("fullName", partialName))).getResults();
        return  results;
    }
}
