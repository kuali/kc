/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.award.awardhierarchy;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;
import org.kuali.rice.kns.service.BusinessObjectService;

public class AwardHierarchyServiceImpl implements AwardHierarchyService {

    BusinessObjectService businessObjectService;
    
    public void persistAwardHierarchy(AwardHierarchy awardHierarchy){
        businessObjectService.save(awardHierarchy);
    }
    
    public void createBasicHierarchy(String awardNumber){
        AwardHierarchy awardHierarchy = new AwardHierarchy();
        awardHierarchy.setRootAwardNumber(awardNumber);
        awardHierarchy.setAwardNumber(awardNumber);
        awardHierarchy.setParentAwardNumber("0000000");
        
    }
    
    public Map<String, AwardHierarchy> getAwardHierarchy(String awardNumber){
        Map<String, AwardHierarchy> awardHierarchies = new TreeMap<String, AwardHierarchy>();
        
        Map<String, String> primaryKeys = new HashMap<String, String>();
        Map<String, String> fieldValues = new HashMap<String, String>();        
        primaryKeys.put("awardNumber", awardNumber);
        
        Collection<AwardHierarchy> awardHierarchyChildren;
        AwardHierarchy awardHierarchy = (AwardHierarchy)businessObjectService.findByPrimaryKey(AwardHierarchy.class, primaryKeys);
        
        AwardHierarchy awardHierarchyRootNode = getRootNode(awardHierarchy.getRootAwardNumber());
        awardHierarchies.put(awardHierarchyRootNode.getAwardNumber(), awardHierarchyRootNode);
        fieldValues.put("parentAwardNumber", awardHierarchyRootNode.getAwardNumber());
        awardHierarchyChildren = businessObjectService.findMatchingOrderBy(AwardHierarchy.class, fieldValues, "parentAwardNumber", true);
        
        while(awardHierarchyChildren.size()!=0){
            Collection<AwardHierarchy> awardHierarchyChildren2 = null;
            for(AwardHierarchy awardHierarchyItem : awardHierarchyChildren){
                awardHierarchies.put(awardHierarchyItem.getAwardNumber(), awardHierarchyItem);
                fieldValues = new HashMap<String, String>();
                fieldValues.put("parentAwardNumber", awardHierarchyItem.getAwardNumber());
                if(awardHierarchyChildren2!=null){
                    awardHierarchyChildren2.addAll(businessObjectService.findMatchingOrderBy(AwardHierarchy.class, fieldValues, "parentAwardNumber", true));    
                }else{
                    awardHierarchyChildren2 = businessObjectService.findMatchingOrderBy(AwardHierarchy.class, fieldValues, "parentAwardNumber", true);
                }                
            }
            awardHierarchyChildren = awardHierarchyChildren2; 
        }
        
        return awardHierarchies;
    }
    
    protected AwardHierarchy getRootNode(String rootAwardNumber){
        Map<String, String> primaryKey = new HashMap<String, String>();
        
        primaryKey.put("awardNumber", rootAwardNumber);
        return (AwardHierarchy) businessObjectService.findByPrimaryKey(AwardHierarchy.class, primaryKey);
    }
    
    public void createNewChildAward(String newAwardNumber, String parentAwardNumber, String rootAwardNumber){
        AwardHierarchy awardHierarchy = new AwardHierarchy();
        awardHierarchy.setRootAwardNumber(rootAwardNumber);
        awardHierarchy.setAwardNumber(newAwardNumber);
        awardHierarchy.setParentAwardNumber(parentAwardNumber);
        
    }
    
    public void createNewAwardBasedOnParent(String awardNumber){
        
    }
    
    public void createNewAwardBasedOnAnotherAwardInHierarchy(String awardNumber){
        
    }
    
    public void copyAwardAsNewHierarchy(){
        
    }
    
    public void copyAwardAndAllDescendantsAsNewHierarchy(){
        
    }
    
    public void copyAwardAsChildOfAnAwardInCurrentHierarchy(){
        
    }
    
    public void copyAwardAndDescendantsAsChildOfAnAwardInCurrentHierarchy(){
        
    }
    
    public void copyAwardAsChildOfAnAwardInAnotherHierarchy(){
        
    }
    
    public void copyAwardAndDescendantsAsChildOfAnAwardInAnotherHierarchy(){
        
    }
    
    /**
     * Gets the businessObjectService attribute. 
     * @return Returns the businessObjectService.
     */
    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    /**
     * Sets the businessObjectService attribute value.
     * @param businessObjectService The businessObjectService to set.
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

}
