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
package org.kuali.kra.proposaldevelopment.service.impl;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.lookup.keyvalue.KCStateValuesFinder;
import org.kuali.kra.proposaldevelopment.service.ProposalPersonService;
import org.kuali.kra.service.KcPersonService;
import org.kuali.kra.service.UnitService;
import org.kuali.kra.util.ValuesFinderUtility;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProposalPersonServiceImpl implements ProposalPersonService {
    
    private static final Integer UNIT_HEIRARCHY_NODE = 3;
    
    private BusinessObjectService businessObjectService;
    private KcPersonService kcPersonService;
     
    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
    /**
     * Sets the KC Person Service.
     * @param kcPersonService the kc person service
     */
    public void setKcPersonService(KcPersonService kcPersonService) {
        this.kcPersonService = kcPersonService;
    }
    
    public String getPersonName(ProposalDevelopmentDocument doc, String userId) {
        String propPersonName = null;
        List<ProposalPerson> proposalPersons = doc.getDevelopmentProposal().getProposalPersons();
        if(proposalPersons.isEmpty()){
            Map<String,String> queryMap = new HashMap<String,String>();
            queryMap.put("proposalNumber", doc.getDevelopmentProposal().getProposalNumber());
            proposalPersons = (List)getBusinessObjectService().findMatching(ProposalPerson.class, queryMap);
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
        keys.put("proposalNumber", proposalNumber.toString());
          
        return (List<ProposalPerson>) getBusinessObjectService().findMatching(ProposalPerson.class, keys);
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
            UnitService unitService = KraServiceLocator.getService(UnitService.class);
            List<Unit> units = unitService.getUnitHierarchyForUnit(proposalPerson.getHomeUnit());
            if(units.size() > UNIT_HEIRARCHY_NODE){
                Unit unit=units.get(UNIT_HEIRARCHY_NODE);
                personDivisionName = unit.getUnitName();
            }
        }
        return personDivisionName;
    }

    @Override
    public String getNewStateList(String countryCode) {
        List<KeyValue> values = KCStateValuesFinder.getKeyValues(countryCode.trim());
        return ValuesFinderUtility.processKeyValueList(values);
    }

}
