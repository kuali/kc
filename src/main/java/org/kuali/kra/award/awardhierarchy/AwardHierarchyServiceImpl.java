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
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.rice.kns.service.BusinessObjectService;

public class AwardHierarchyServiceImpl implements AwardHierarchyService {

    private static final String DEFAULT_PARENT_AWARD_NUMBER = "000000-00000";
    BusinessObjectService businessObjectService;
    
    public void persistAwardHierarchy(AwardHierarchy awardHierarchy){
        businessObjectService.save(awardHierarchy);
    }
    
    public void createBasicHierarchy(String awardNumber){
        AwardHierarchy awardHierarchy = new AwardHierarchy();
        awardHierarchy.setRootAwardNumber(awardNumber);
        awardHierarchy.setAwardNumber(awardNumber);
        awardHierarchy.setParentAwardNumber(DEFAULT_PARENT_AWARD_NUMBER);
    }
    
    public Map<String, AwardHierarchy> getAwardHierarchy(String awardNumber, List<String> order){
        Map<String, AwardHierarchy> awardHierarchies = new HashMap<String, AwardHierarchy>();
        
        Map<String, String> primaryKeys = new HashMap<String, String>();
        Map<String, String> fieldValues = new HashMap<String, String>();        
        primaryKeys.put("awardNumber", awardNumber);
        
        Collection<AwardHierarchy> awardHierarchyChildren;
        AwardHierarchy awardHierarchy = (AwardHierarchy)businessObjectService.findByPrimaryKey(AwardHierarchy.class, primaryKeys);
        
        AwardHierarchy awardHierarchyRootNode = getRootNode(awardHierarchy.getRootAwardNumber());
        awardHierarchies.put(awardHierarchyRootNode.getAwardNumber(), awardHierarchyRootNode);
        fieldValues.put("parentAwardNumber", awardHierarchyRootNode.getAwardNumber());
        awardHierarchyChildren = businessObjectService.findMatchingOrderBy(AwardHierarchy.class, fieldValues, "parentAwardNumber", true);
        
        Map<String, Collection<AwardHierarchy>> mapOfChildren = new HashMap<String, Collection<AwardHierarchy>>();
        mapOfChildren.put(awardHierarchyRootNode.getAwardNumber(), awardHierarchyChildren);
        while(awardHierarchyChildren.size()!=0){
            Collection<AwardHierarchy> awardHierarchyChildren2 = null;
            for(AwardHierarchy awardHierarchyItem : awardHierarchyChildren){
                awardHierarchies.put(awardHierarchyItem.getAwardNumber(), awardHierarchyItem);
                fieldValues = new HashMap<String, String>();
                fieldValues.put("parentAwardNumber", awardHierarchyItem.getAwardNumber());
                Collection<AwardHierarchy> listOfChildren = businessObjectService.findMatchingOrderBy(AwardHierarchy.class, fieldValues, "parentAwardNumber", true);
                
                if(awardHierarchyChildren2!=null){
                    awardHierarchyChildren2.addAll(listOfChildren);    
                }else{
                    awardHierarchyChildren2 = listOfChildren;
                }
                
                mapOfChildren.put(awardHierarchyItem.getAwardNumber(), businessObjectService.findMatchingOrderBy(AwardHierarchy.class, fieldValues, "parentAwardNumber", true));
            }
            awardHierarchyChildren = awardHierarchyChildren2; 
        }

        String parentAwardNumber = awardHierarchyRootNode.getAwardNumber();
        
        order.add(parentAwardNumber);
        int l = 0;
        Collection<AwardHierarchy> ahCollection = null;
        AwardHierarchy ah1 = null;
        if(awardHierarchies.size()>1){
            while(!StringUtils.equalsIgnoreCase(parentAwardNumber,DEFAULT_PARENT_AWARD_NUMBER)){
                if(mapOfChildren.get(parentAwardNumber).size()!=0){
                    ahCollection = mapOfChildren.get(parentAwardNumber);
                    ah1 = ahCollection.iterator().next();
                    parentAwardNumber = ah1.getAwardNumber();        
                    order.add(parentAwardNumber);
                    l++;               
                }else if(ahCollection!=null && ahCollection.size() ==0){
                    ah1 = awardHierarchies.get(awardHierarchies.get(parentAwardNumber).getParentAwardNumber());
                    if(ah1!=null){
                        parentAwardNumber = ah1.getParentAwardNumber();
                        if(!StringUtils.equalsIgnoreCase(parentAwardNumber,DEFAULT_PARENT_AWARD_NUMBER)){                    
                            mapOfChildren.get(parentAwardNumber).remove(ah1);    
                        }   
                    }else{
                        parentAwardNumber = DEFAULT_PARENT_AWARD_NUMBER;
                    }
                }
                else if(ah1!=null){                
                    parentAwardNumber = ah1.getParentAwardNumber();
                    ahCollection.remove(ah1);                
                }
            }
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
