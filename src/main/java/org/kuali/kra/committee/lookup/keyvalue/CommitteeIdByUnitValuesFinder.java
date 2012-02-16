/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.committee.lookup.keyvalue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.kim.bo.KcKimAttributes;
import org.kuali.kra.lookup.keyvalue.KeyValueComparator;
import org.kuali.kra.lookup.keyvalue.PrefixValuesFinder;
import org.kuali.kra.service.UnitService;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kim.api.role.Role;
import org.kuali.rice.kim.api.role.RoleService;
import org.kuali.rice.krad.keyvalues.KeyValuesBase;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;

/**
 * This class returns a list of committees that is filtered by the current
 * user's home unit.  The available committees are based on some 
 * conditional logic as explained in kcirb-1314:
 * 
 * If not yet submitted:
 * For the Submitter/Aggregator they should be able to select any committee where 
 * the Lead Unit of that committee is above or below the Lead Unit on the protocol. 
 * However, they cannot select a committee from another branch of the org tree. 
 * So, using the org Hierarchy in Trunk as an example, if the Lead Unit on the 
 * protocol is BL-RUGS then the submitter should be able to pick any committee with 
 * a lead unit in the Bloomington campus branch of the tree 
 * (BL-IIDC, BL-RCEN, BL-RUGS, BL-BL) or at the university level (000001, or IU-UNIV), 
 * but they should not be able to pick a committee with a home unit is in the 
 * Indianapolis branch of the org tree (IN-IN, IN-MED, INMDEP, IN-CARD, IN-CARR, IN-PED or INC-PERS). 
 *
 * Post-submittal:
 * The rules for an IRB administrator are slightly different for selecting/changing 
 * a committee assignment and is based on the Role Qualifier on their 
 * IRB Administrator (1119) role. If the Descends flag is checked (which in the real 
 * world I think it always should be) then they should be able to change a committee 
 * assignment for any protocol where the Lead Unit is at or below their qualified 
 * node in the Org tree. They can select a new committee to any committee whose home 
 * unit is at or below their qualified node in the Org tree.
 * 
 */
public class CommitteeIdByUnitValuesFinder extends KeyValuesBase {

    private String protocolLeadUnit;
    private String docRouteStatus;
    private String currentCommitteeId;
    
    private Set<String> unitIds = new HashSet<String>(); 
    
    /**
     * Returns the committees that the user is eligible to choose from.
     * @see org.kuali.rice.krad.keyvalues.KeyValuesFinder#getKeyValues()
     */
    @SuppressWarnings("unchecked" )
    public List getKeyValues() {
        Collection<Committee> committees = getValidCommittees();
        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        
        if (CollectionUtils.isNotEmpty(committees)) {    
            if (isSaved()) {
                //Use the lead unit of the protocol to determine committees
                getProtocolUnitIds();
                for (Committee committee : committees) {
                    if (StringUtils.equalsIgnoreCase(committee.getCommitteeDocument().getDocStatusCode(), "F") 
                            && unitIds.contains(committee.getHomeUnit().getUnitNumber())) {
                        keyValues.add(new ConcreteKeyValue(committee.getCommitteeId(), committee.getCommitteeName()));
                    }
                }
            } else {
                //Use the lead unit of the irb admin
                getIRBAdminUnitIds();
                for (Committee committee : committees) {
                    if (unitIds.contains(committee.getHomeUnit().getUnitNumber()) ||
                            committee.getCommitteeId().equals(getCurrentCommitteeId())) {
                        keyValues.add(new ConcreteKeyValue(committee.getCommitteeId(), committee.getCommitteeName()));
                    }
                }                
            }

            Collections.sort(keyValues, new KeyValueComparator());            
        }
        keyValues.add(0, new ConcreteKeyValue(PrefixValuesFinder.getPrefixKey(), PrefixValuesFinder.getDefaultPrefixValue()));
                
        return keyValues;
    }
    
    /**
     * 
     * This method determines whether the document has been submitted for review.  Currently
     * we check to see if the document status is "Saved", which indicates that it is pre-submittal.
     * @return false if the document has not been submitted, true otherwise.
     */
    private boolean isSaved() {
        if (getDocRouteStatus().equals(KewApiConstants.ROUTE_HEADER_SAVED_CD)) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * This method returns a set of unit ids that represent all the units
     * at or below the irb admin's lead unit.  The lead unit is determined
     * by his role (irb admin) and the role qualifier associated with it.
     * @return
     */
    private void getIRBAdminUnitIds() {        
        String principalId = GlobalVariables.getUserSession().getPerson().getPrincipalId();
        Role roleInfo = getRoleService().getRoleByNamespaceCodeAndName(RoleConstants.DEPARTMENT_ROLE_TYPE, RoleConstants.IRB_ADMINISTRATOR);
        List<String> roleIds = new ArrayList<String>();
        roleIds.add(roleInfo.getId());
        Map<String, String> qualifiedRoleAttributes = new HashMap<String, String>();
        qualifiedRoleAttributes.put(KcKimAttributes.UNIT_NUMBER, "*");
        Map<String,String> qualifications =new HashMap<String,String>(qualifiedRoleAttributes);
        boolean valid = getRoleService().principalHasRole(principalId, roleIds, qualifications);
        
        //User has the irb admin role, now check to see if he has the necessary role qualifier.
        if (valid) {
            List<Map<String,String>> principalQualifications = getRoleService().getNestedRoleQualifiersForPrincipalByRoleIds(principalId, roleIds, qualifications);
            for (Map<String,String> attrSet : principalQualifications) {            
                Unit unit = getUnitService().getUnit(attrSet.get(KcKimAttributes.UNIT_NUMBER));
                if(unit != null) {
                    unitIds.add(unit.getUnitNumber());
                    //If descends heirarchy is yes, add all the sub units as well
                    if(attrSet.containsKey(KcKimAttributes.SUBUNITS) && 
                       StringUtils.equalsIgnoreCase("Y", attrSet.get(KcKimAttributes.SUBUNITS))) {
                       List<Unit> subUnits = getUnitService().getAllSubUnits(attrSet.get(KcKimAttributes.UNIT_NUMBER));
                       for (Unit u : subUnits) {
                           unitIds.add(u.getUnitNumber());
                       }
                    }
                }
            }
        }
    }
    
    /**
     * This method returns the set of unique committees, by filtering
     * out the committees with the same committee id.  It takes the
     * committee id with the highest sequence number
     * @return a collection of unique committees based on committee id and sequence number.
     */
    @SuppressWarnings("unchecked")
    private Collection<Committee> getValidCommittees() {
        Collection<Committee> allCommittees = KraServiceLocator.getService(BusinessObjectService.class).findAll(Committee.class);
        HashMap<String, Committee> committeeMap = new HashMap<String, Committee>();
        
        Committee tmpComm = null;
        for (Committee comm : allCommittees) {
            if (committeeMap.containsKey(comm.getCommitteeId())) {
                tmpComm = committeeMap.get(comm.getCommitteeId());
                if (comm.getSequenceNumber().intValue() > tmpComm.getSequenceNumber().intValue()) {
                    committeeMap.put(comm.getCommitteeId(), comm);
                }
            } else {
                committeeMap.put(comm.getCommitteeId(), comm);
            }
        }
        
        return committeeMap.values();
    }
    
    /**
     * 
     * This method returns a set of unit ids that match the lead unit
     * of the protocol, all of the sub-units of the protocol lead 
     * unit, as well as all units between the protocol lead unit
     * and the root.
     * @return a set of unit ids.
     */
    private void getProtocolUnitIds() {
        
        if (StringUtils.isNotBlank(protocolLeadUnit)) {
            //Add the protocol lead unit
            unitIds.add(protocolLeadUnit);
            
            //Add all sub units
            List<Unit> subUnits = getUnitService().getAllSubUnits(protocolLeadUnit);
            for (Unit unit : subUnits) {
                unitIds.add(unit.getUnitNumber());
            }
            
            //Add all units between the lead unit and the root unit
            String topUnitNumber = getUnitService().getTopUnit().getUnitNumber();
            getParentUnitIds(protocolLeadUnit, topUnitNumber);
        }
    }
    
    /**
     * 
     * This method uses recursion to walk up the tree to the root unit, adding the unit ids to the
     * set along the way.
     * @param currentUnitNumber the current unit
     * @param topUnitNumber the root unit
     * @param unitIds the set of unit ids
     */
    private void getParentUnitIds(String currentUnitNumber, String topUnitNumber) {
        if (currentUnitNumber.equals(topUnitNumber)) {
            return;
        } else {
            String parentUnitNumber = getUnitService().getUnit(currentUnitNumber).getParentUnitNumber();
            Unit parentUnit = getUnitService().getUnit(parentUnitNumber);
            unitIds.add(parentUnit.getUnitNumber());
            getParentUnitIds(parentUnit.getUnitNumber(), topUnitNumber);
        }
    }
    
    /**
     * 
     * Quick method to get the UnitService
     * @return UnitService reference
     */
    private UnitService getUnitService() {
        return KraServiceLocator.getService(UnitService.class);
    }
    
    /**
     * Quick method to get the RoleService
     * @return RoleService reference
     */
    private RoleService getRoleService() {
        return KraServiceLocator.getService(RoleService.class);
    }

    public String getProtocolLeadUnit() {
        return protocolLeadUnit;
    }


    public void setProtocolLeadUnit(String protocolLeadUnit) {
        this.protocolLeadUnit = protocolLeadUnit;
    }


    public String getDocRouteStatus() {
        return docRouteStatus;
    }


    public void setDocRouteStatus(String docRouteStatus) {
        this.docRouteStatus = docRouteStatus;
    }


    public String getCurrentCommitteeId() {
        return currentCommitteeId;
    }


    public void setCurrentCommitteeId(String currentCommitteeId) {
        this.currentCommitteeId = currentCommitteeId;
    }
    
    
    

}
