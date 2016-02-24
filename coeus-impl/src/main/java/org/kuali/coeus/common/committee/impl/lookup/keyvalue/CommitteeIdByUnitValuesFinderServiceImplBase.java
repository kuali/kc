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
package org.kuali.coeus.common.committee.impl.lookup.keyvalue;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.committee.impl.bo.CommitteeBase;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.common.framework.unit.UnitService;
import org.kuali.coeus.common.framework.auth.UnitAuthorizationService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.*;

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
 * The rules here are slightly different for selecting/changing 
 * a committee assignment and is based on the Role Qualifier on the curent user's 
 * membership in some role that grants the permission to assign committees. If that role is a unit hierarchy role, 
 * and if the Descends flag is checked (which in the real 
 * world I think it always should be) then the current user should be able to change a committee 
 * assignment for any protocol where the Lead Unit is at or below their qualified 
 * node in the Org tree. They can select a new committee to any committee whose home 
 * unit is at or below their qualified node in the Org tree.
 * 
 */
public abstract class CommitteeIdByUnitValuesFinderServiceImplBase<CMT extends CommitteeBase<CMT, ?, ?>> implements CommitteeIdByUnitValuesFinderService<CMT> {


    private static final long serialVersionUID = -3005003472800028011L;

    public static final String FINAL_STATUS_CD = "F";
    private static final String COMMITTEE_TYPE_CODE = "committeeTypeCode";
    
    private UnitAuthorizationService unitAuthorizationService;
    private UnitService unitService;
    private BusinessObjectService businessObjectService;
     
    
    
    // this method should be invoked from within the transaction wrapper that covers the request processing so 
    // that the db-calls in this method do not each generate new transactions. If executed from within the context
    // of display rendering, this logic will be quite expensive due to the number of new transactions needed. 
    @Override
    public Collection<CMT> getAssignmentCommittees(String protocolLeadUnit, String docRouteStatus, String currentCommitteeId) {
        Collection<CMT> assignmentCommittees = new ArrayList<CMT>();
        // get the initial list of all valid committees; some of them will be filtered out by the logic below 
        Collection<CMT> candidateCommittees = getCandidateCommittees();        
        if (CollectionUtils.isNotEmpty(candidateCommittees)) {    
            if (isSaved(docRouteStatus)) {
                //Use the lead unit of the protocol to determine committees
                Set<String> unitIds = getProtocolUnitIds(protocolLeadUnit);
                for (CMT committee : candidateCommittees) {
                    if (StringUtils.equalsIgnoreCase(committee.getCommitteeDocument().getDocStatusCode(), "F") 
                            && unitIds.contains(committee.getHomeUnit().getUnitNumber())) {
                        assignmentCommittees.add(committee);
                    }
                }
            } else {
                // we check the current user's authorization to assign committees 
                String principalId = GlobalVariables.getUserSession().getPerson().getPrincipalId();
                for (CMT committee : candidateCommittees) {
                    if ( isCurrentUserAuthorizedToAssignThisCommittee(principalId, committee) || 
                         committee.getCommitteeId().equals(currentCommitteeId)) {
                        assignmentCommittees.add(committee);
                    }
                }                
            }
        }
        return assignmentCommittees;
    }  
    
    
    
    /**
     * This method returns the set of unique committees, by filtering
     * out the committees with the same committee id.  It takes the
     * committee id with the highest sequence number
     * @return a collection of unique committees based on committee id and sequence number.
     */
    private Collection<CMT> getCandidateCommittees() {
        Map<String, String> criteria = new HashMap<String, String>();
        criteria.put(COMMITTEE_TYPE_CODE, getCommitteeTypeCodeHook());
      
        Collection<CMT> allCommittees = getBusinessObjectService().findMatching(getCommitteeBOClassHook(), criteria);
        HashMap<String, CMT> committeeMap = new HashMap<String, CMT>();
        
        CMT tmpComm = null;
        for (CMT comm : allCommittees) {
            if (FINAL_STATUS_CD.equalsIgnoreCase(comm.getCommitteeDocument().getDocStatusCode())) {
                if (committeeMap.containsKey(comm.getCommitteeId())) {
                    tmpComm = committeeMap.get(comm.getCommitteeId());
                    if (comm.getSequenceNumber().intValue() > tmpComm.getSequenceNumber().intValue()) {
                        committeeMap.put(comm.getCommitteeId(), comm);
                    }
                } else {
                    committeeMap.put(comm.getCommitteeId(), comm);
                }
            }
        }
        
        return committeeMap.values();
    }
    
    
    
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    
    private BusinessObjectService getBusinessObjectService() {
        if(this.businessObjectService == null) {
            this.businessObjectService = KcServiceLocator.getService(BusinessObjectService.class);
        }
        return this.businessObjectService;
    }




    protected abstract Class<CMT> getCommitteeBOClassHook();
    protected abstract String getCommitteeTypeCodeHook(); 
   
    
     
    // check if the current user has the "assign committee" permission granted via some role membership 
    // that is qualified with a unit number that encompasses the committee's home unit number   
    protected boolean isCurrentUserAuthorizedToAssignThisCommittee(String userId, CMT candidateCommittee) {
        boolean retVal;
        String permissionNamespace = this.getAssignCommitteePermissionNamespaceHook();
        String permissionName = this.getAssignCommitteePermissionNameHook();
        retVal = this.getUnitAuthorizationService().hasPermission(userId, candidateCommittee.getHomeUnitNumber(), permissionNamespace, permissionName);
        return retVal;
    }
 
       
    protected abstract String getAssignCommitteePermissionNamespaceHook();    
    protected abstract String getAssignCommitteePermissionNameHook();
    

    protected UnitAuthorizationService getUnitAuthorizationService() {
        if(this.unitAuthorizationService == null) {
            this.unitAuthorizationService = KcServiceLocator.getService(UnitAuthorizationService.class);
        }
        return this.unitAuthorizationService;
    }
    
    public void setUnitAuthorizationService(UnitAuthorizationService unitAuthorizationService) {
        this.unitAuthorizationService = unitAuthorizationService;
    }
    
    /**
     * 
     * This method determines whether the document has been submitted for review.  Currently
     * we check to see if the document status is "Saved", which indicates that it is pre-submittal.
     * @return false if the document has not been submitted, true otherwise.
     */
    private boolean isSaved(String docRouteStatus) {
        if (docRouteStatus.equals(KewApiConstants.ROUTE_HEADER_SAVED_CD)) {
            return true;
        } 
        else {
            return false;
        }
    }
    



    /**
     * 
     * This method returns a set of unit ids that match the lead unit
     * of the protocol, all of the sub-units of the protocol lead 
     * unit, as well as all units between the protocol lead unit
     * and the root.
     * @return a set of unit ids.
     */
    private Set<String> getProtocolUnitIds(String protocolLeadUnit) {
        Set<String> unitIds = new HashSet<String>();
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
            unitIds = getParentUnitIds(protocolLeadUnit, topUnitNumber, unitIds);
        }
        return unitIds;
    }
    
    /**
     * 
     * This method walks up the tree to the root unit, adding the unit ids to the
     * set along the way.
     * @param currentUnitNumber the current unit
     * @param topUnitNumber the root unit
     * @param unitIds the set of unit ids
     * @return 
     */
    private Set<String> getParentUnitIds(String currentUnitNumber, String topUnitNumber, Set<String> unitIds) {
        while (!(currentUnitNumber.equals(topUnitNumber))) {
           String parentUnitNumber = getUnitService().getUnit(currentUnitNumber).getParentUnitNumber();
           Unit parentUnit = getUnitService().getUnit(parentUnitNumber);
           unitIds.add(parentUnit.getUnitNumber());
           currentUnitNumber = parentUnit.getUnitNumber();
        }
        return unitIds;
    }
  
    protected UnitService getUnitService() { 
        if(this.unitService == null) {
            this.unitService = KcServiceLocator.getService(UnitService.class);
        }
        return this.unitService;
    }
    
    public void setUnitService(UnitService unitService) {
        this.unitService = unitService;
    }
    
}
