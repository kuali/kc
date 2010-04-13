/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra.service.impl;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.Sponsor;
import org.kuali.kra.bo.SponsorHierarchy;
import org.kuali.kra.dao.SponsorHierarchyDao;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.service.SponsorService;
import org.kuali.kra.service.Sponsorable;
import org.kuali.kra.web.struts.form.SponsorHierarchyForm;
import org.kuali.rice.kns.UserSession;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.ParameterService;
import org.kuali.rice.kns.util.GlobalVariables;
import org.mortbay.log.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class SponsorServiceImpl implements SponsorService, Constants {

    private SponsorHierarchyDao sponsorHierarchyDao;
    private BusinessObjectService businessObjectService;
    private ParameterService parameterService;

    private static final String sessionKey = "org.kuali.kra.service.impl.SponsorServiceImpl.actionList";
    private static final Integer HIERARCHY_MAX_HEIGHT = 10;
    private enum SponsorActionType {
        INSERT, UPDATE_NAME, UPDATE_SORT, DELETE;
    }
    private class SponsorAction {
        public SponsorActionType actionType;
        public String hierarchyName;
        public String sponsorCode;
        public String[] levels = new String[HIERARCHY_MAX_HEIGHT];
        public Integer[] sortIds = new Integer[HIERARCHY_MAX_HEIGHT];
        public Integer levelToChange;
        public String oldGroupName;
        public String newGroupName;
        public Boolean moveDown;
        public void setLevels(String[] newLevels) {
            for (int i = 0; i < levels.length && i < newLevels.length; i++) {
                levels[i] = newLevels[i];
            }
        }
        public void setSortIds(Integer[] sortIds) {
            for (int i = 0; i < sortIds.length && i < sortIds.length; i++) {
                this.sortIds[i] = sortIds[i];
            }
        }
    }

    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory
            .getLog(SponsorServiceImpl.class);
    
    /**
     * @see org.kuali.kra.proposaldevelopment.service.SponsorService#getSponsorName(java.lang.String)
     */
    public String getSponsorName(String sponsorCode) {
        String sponsorName = null;

        Map<String, String> primaryKeys = new HashMap<String, String>();
        if (StringUtils.isNotEmpty(sponsorCode)) {
            primaryKeys.put(Constants.SPONSOR_CODE, sponsorCode);
            Sponsor sponsor = (Sponsor) businessObjectService.findByPrimaryKey(Sponsor.class, primaryKeys);
            if (sponsor != null) {
                sponsorName = sponsor.getSponsorName();
            }
        }

        return sponsorName;
    }

    /**
     * 
     * @see org.kuali.kra.service.SponsorService#getTopSponsorHierarchy()
     */
    public String getTopSponsorHierarchy() {

       // KraServiceLocator.getService(UserRoleService.class).getUserRoles("abc", "abc");
        String topSponsorHierarchy = Constants.EMPTY_STRING;
        
        List sponsorHierarchies = (List)getTopSponsorHierarchyList();
        Collections.sort(sponsorHierarchies);
        
        for(Object hierarchyName : sponsorHierarchies) {
            topSponsorHierarchy = topSponsorHierarchy + hierarchyName + Constants.SPONSOR_HIERARCHY_SEPARATOR_C1C;            
        }
        
        topSponsorHierarchy = topSponsorHierarchy.substring(0, topSponsorHierarchy.length() - 3);

        return topSponsorHierarchy;
    }

    public Collection getTopSponsorHierarchyList() {

         Iterator sponsorHierarchyList = sponsorHierarchyDao.getTopSponsorHierarchy();
         List sponsorHierarchies = new ArrayList();
         while (sponsorHierarchyList.hasNext()) {
             Object[] names = (Object[])sponsorHierarchyList.next();
             String name = (String)names[0];
             sponsorHierarchies.add(name);             
         }
          
         Collections.sort(sponsorHierarchies);
         
         return sponsorHierarchies;
     }
    
    public String getSubSponsorHierarchiesForTreeView(String hierarchyName, String depth, String groups) {

        String returnSponsorHierarchy = null;
        String[] ascendantList = groups.split(Constants.SPONSOR_HIERARCHY_SEPARATOR_C1C);
        if (Integer.parseInt(depth) < 10) {
            returnSponsorHierarchy = sponsorHierarchyDao.getsubGroups(hierarchyName,Integer.parseInt(depth)+1, ascendantList);
        }
 
        if (StringUtils.isBlank(returnSponsorHierarchy)) {
            returnSponsorHierarchy = sponsorHierarchyDao.getSponsorCodesForGroup(hierarchyName,Integer.parseInt(depth)+1, ascendantList);

            if (StringUtils.isNotBlank(returnSponsorHierarchy)) {
                returnSponsorHierarchy = "((leafNodes))"+Constants.SPONSOR_HIERARCHY_SEPARATOR_C1C+returnSponsorHierarchy;
            }
        }
       
        return returnSponsorHierarchy;
    }

    

    
    
    public void copySponsorHierarchy(SponsorHierarchyForm sponsorHierarchyForm) {
        Map fieldValues = new HashMap();
        fieldValues.put(Constants.HIERARCHY_NAME, sponsorHierarchyForm.getSelectedSponsorHierarchy());

        List sponsors = (List)businessObjectService.findMatching(SponsorHierarchy.class, fieldValues);
        List<SponsorHierarchy> newSponsors = new ArrayList<SponsorHierarchy>();
        for(Object sponsorHierarchyObj : sponsors) {
            SponsorHierarchy sponsorHierarchy = (SponsorHierarchy)sponsorHierarchyObj;
            sponsorHierarchy.setHierarchyName(sponsorHierarchyForm.getNewHierarchyName());
            newSponsors.add(sponsorHierarchy);
        }
        businessObjectService.save(newSponsors);
    }
    
    public void deleteSponsorHierarchy(SponsorHierarchyForm sponsorHierarchyForm) {
        Map fieldValues = new HashMap();
        fieldValues.put(Constants.HIERARCHY_NAME, sponsorHierarchyForm.getSelectedSponsorHierarchy());

        List sponsors = (List)businessObjectService.findMatching(SponsorHierarchy.class, fieldValues);
        businessObjectService.delete(sponsors);
    }

    /**
     * 
     * @param sponsorable
     * @return
     */
    public boolean isSponsorNih(Sponsorable sponsorable) {
        sponsorable.setNih(false);

        for (SponsorHierarchy sponsorHierarchy : loadSponsorHierarchies(sponsorable.getSponsorCode())) {
            sponsorable.setNih(evaluateWhetherSponsorHierarchyIncludesNih(sponsorHierarchy));
            if(sponsorable.isNih()) {
                break;
            }
        }
        return sponsorable.isNih();
    }

    /**
     * Gets the businessObjectService attribute.
     * 
     * @return Returns the businessObjectService.
     */
    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    /**
     * Sets the businessObjectService attribute value.
     * 
     * @param businessObjectService The businessObjectService to set.
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    /**
     * @param parameterService
     */
    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    public SponsorHierarchyDao getSponsorHierarchyDao() {
        return sponsorHierarchyDao;
    }

    public void setSponsorHierarchyDao(SponsorHierarchyDao sponsorHierarchyDao) {
        this.sponsorHierarchyDao = sponsorHierarchyDao;
    }

    public String loadToSponsorHierachyMt(String hierarchyName) {

        String sponsorCodes=Constants.EMPTY_STRING;
        
        Iterator sponsorHierarchyList = sponsorHierarchyDao.getAllSponsors(hierarchyName);
        List sponsorHierarchies = new ArrayList();
        while (sponsorHierarchyList.hasNext()) {
            sponsorCodes = sponsorCodes +((Object[])sponsorHierarchyList.next())[0]+";";
        }
        return sponsorCodes;
    }

    public String getSponsorCodes(String hierarchyName, String depth, String groups) {

        String sponsorCodes=Constants.EMPTY_STRING;
        String[] ascendantList = groups.split(Constants.SPONSOR_HIERARCHY_SEPARATOR_C1C);
        
        return sponsorHierarchyDao.getSponsorCodesForDeletedGroup(hierarchyName,Integer.parseInt(depth)+1, ascendantList);
    }
    
    public void updateSponsorCodes(String sponsorCodes) {

        if (GlobalVariables.getUserSession().retrieveObject("sponsorCodes") != null) {
            GlobalVariables.getUserSession().removeObject("sponsorCodes");
        }
        GlobalVariables.getUserSession().addObject("sponsorCodes", (Object)sponsorCodes);
    }
    
    public void insertSponsor(String hierarchyName, String[] sponsorCodes, String[] levels,
            Integer[] sortIds) {
        for (String sponsorCode : sponsorCodes) {
            SponsorAction newAction = new SponsorAction();
            newAction.actionType = SponsorActionType.INSERT;
            newAction.hierarchyName = hierarchyName;
            newAction.sponsorCode = sponsorCode;
            newAction.setLevels(levels);
            newAction.setSortIds(sortIds);
            addActionToBeSaved(newAction);
        }
    }
    
    public void deleteSponsor(String hierarchyName, String sponsorCode, String[] levels) {
        SponsorAction newAction = new SponsorAction();
        newAction.actionType = SponsorActionType.DELETE;
        newAction.hierarchyName = hierarchyName;
        newAction.sponsorCode = sponsorCode;
        newAction.setLevels(levels);
        addActionToBeSaved(newAction); 
    }
    
    public void updateGroupName(String hierarchyName, Integer levelToChange, String oldGroupName, String newGroupName, String[] levels) {
        SponsorAction newAction = new SponsorAction();
        newAction.actionType = SponsorActionType.UPDATE_NAME;
        newAction.hierarchyName = hierarchyName;
        newAction.levelToChange = levelToChange;
        newAction.oldGroupName = oldGroupName;
        newAction.newGroupName = newGroupName;
        newAction.setLevels(levels);
        addActionToBeSaved(newAction); 
    }
    
    public void changeSponsorSortOrder(String hierarchyName, Integer levelToChange, Boolean moveDown, String[] levels) {
        SponsorAction newAction = new SponsorAction();
        newAction.actionType = SponsorActionType.UPDATE_SORT;
        newAction.hierarchyName = hierarchyName;
        newAction.levelToChange = levelToChange;
        newAction.moveDown = moveDown;
        newAction.setLevels(levels);
        addActionToBeSaved(newAction); 
    }
    
    
    @SuppressWarnings("unchecked")
    private void addActionToBeSaved(SponsorAction action) {
        List<SponsorAction> actions = (List)GlobalVariables.getUserSession().retrieveObject(sessionKey);
        if (actions == null) {
            actions = new ArrayList<SponsorAction>();
            GlobalVariables.getUserSession().addObject(sessionKey, actions);
        }
        actions.add(action);
    }
    
    public void executeActions() {
        List<SponsorAction> actions = (List)GlobalVariables.getUserSession().retrieveObject(sessionKey);
        if (actions != null) {
            for (SponsorAction action : actions) {
                if (action.actionType == SponsorActionType.INSERT) {
                    SponsorHierarchy newSponsor = new SponsorHierarchy();
                    newSponsor.setHierarchyName(action.hierarchyName);
                    newSponsor.setSponsorCode(action.sponsorCode);
                    newSponsor.setLevel1(action.levels[0]);
                    newSponsor.setLevel2(action.levels[1]);
                    newSponsor.setLevel3(action.levels[2]);
                    newSponsor.setLevel4(action.levels[3]);
                    newSponsor.setLevel5(action.levels[4]);
                    newSponsor.setLevel6(action.levels[5]);
                    newSponsor.setLevel7(action.levels[6]);
                    newSponsor.setLevel8(action.levels[7]);
                    newSponsor.setLevel9(action.levels[8]);
                    newSponsor.setLevel10(action.levels[9]);
                    newSponsor.setLevel1Sortid(action.sortIds[0]);
                    newSponsor.setLevel2Sortid(action.sortIds[1]);
                    newSponsor.setLevel3Sortid(action.sortIds[2]);
                    newSponsor.setLevel4Sortid(action.sortIds[3]);
                    newSponsor.setLevel5Sortid(action.sortIds[4]);
                    newSponsor.setLevel6Sortid(action.sortIds[5]);
                    newSponsor.setLevel7Sortid(action.sortIds[6]);
                    newSponsor.setLevel8Sortid(action.sortIds[7]);
                    newSponsor.setLevel9Sortid(action.sortIds[8]);
                    newSponsor.setLevel10Sortid(action.sortIds[9]);
                    getBusinessObjectService().save(newSponsor);
                } else if (action.actionType == SponsorActionType.DELETE) {
                    getBusinessObjectService().deleteMatching(SponsorHierarchy.class, getFieldValues(action));
                } else if (action.actionType == SponsorActionType.UPDATE_NAME) {
                    Collection<SponsorHierarchy> sponsors = getBusinessObjectService().findMatching(SponsorHierarchy.class, getFieldValues(action));
                    for (SponsorHierarchy sponsor : sponsors) {
                        updateGroup(sponsor, action.levelToChange, action.newGroupName);
                        getBusinessObjectService().save(sponsor);
                    }
                } else if (action.actionType == SponsorActionType.UPDATE_SORT) {
                    Collection<SponsorHierarchy> sponsors = getBusinessObjectService().findMatching(SponsorHierarchy.class, getFieldValues(action));
                    for (SponsorHierarchy sponsor : sponsors) {
                        updateSortId(sponsor, action.levelToChange, action.moveDown);
                        getBusinessObjectService().save(sponsor);
                    }  
                }
            }
            actions.clear();
        }
    }
    
    private void updateGroup(SponsorHierarchy sponsor, Integer level, String newGroupName) {
        try {
            Method setLevelMethod = SponsorHierarchy.class.getMethod("setLevel" + level, new Class[]{String.class});
            setLevelMethod.invoke(sponsor, newGroupName);
        }
        catch (Exception e) {
            Log.debug("Error setting group name on sponsor", e);
        }
    }
    
    private void updateSortId(SponsorHierarchy sponsor, Integer level, Boolean moveDown) {
        int changeBy = 1;
        if (moveDown) {
            changeBy = -1;
        }
        try {
            Method setSortIdMethod = SponsorHierarchy.class.getMethod("setLevel" + level + "Sortid", new Class[]{Integer.class});
            Method getSortIdMethod = SponsorHierarchy.class.getMethod("getLevel" + level + "Sortid", (Class[])null);
            setSortIdMethod.invoke(sponsor, getNewSortId((Integer)getSortIdMethod.invoke(sponsor, (Object[])null), changeBy));
        }
        catch (Exception e) {
            Log.debug("Error setting new sortId on sponsor", e);
        }
    }    
    
    private Integer getNewSortId(Integer currentSortId, int changeBy) {
        if (currentSortId == null) {
            currentSortId = 0;
        }
        return currentSortId + changeBy;
        
    }
    
    private Map<String, String> getFieldValues(SponsorAction action) {
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put("hierarchyName", action.hierarchyName);
        if (StringUtils.isNotBlank(action.sponsorCode)) {
            fieldValues.put("sponsorCode", action.sponsorCode);
        }
        for (int i = 0; i < action.levels.length; i++) {
            if (StringUtils.isNotBlank(action.levels[i])) {
                fieldValues.put("level" + (i+1), action.levels[i]);
            }
        }
        if (StringUtils.isNotBlank(action.oldGroupName)) {
            fieldValues.put("level" + action.levelToChange, action.oldGroupName);
        }
        return fieldValues;
    }
    
    public void clearCurrentActions() {
        GlobalVariables.getUserSession().removeObject(sessionKey);
    }

    private boolean evaluateWhetherSponsorHierarchyIncludesNih(SponsorHierarchy sponsorHierarchy) {
        String nihIndicator = findNihIndicatorForSponsorHierarchyLevel();
        return sponsorHierarchy.isNihSponsorInAnylevel(nihIndicator);
    }

    private String findNihIndicatorForSponsorHierarchyLevel() {
        return parameterService.getParameterValue(KC_GENERIC_PARAMETER_NAMESPACE, KC_ALL_PARAMETER_DETAIL_TYPE_CODE, SPONSOR_LEVEL_HIERARCHY);
    }

    private String findSponsorHierarchyName() {
        return parameterService.getParameterValue(KC_GENERIC_PARAMETER_NAMESPACE, KC_ALL_PARAMETER_DETAIL_TYPE_CODE, SPONSOR_HIERARCHY_NAME );
    }

    private Collection<SponsorHierarchy> loadSponsorHierarchies(String sponsorCode) {
        Map<String, String> valueMap = new HashMap<String, String>();
        valueMap.put("sponsorCode", sponsorCode);
        valueMap.put("hierarchyName", findSponsorHierarchyName());
        Collection<SponsorHierarchy> sponsorHierarchies = businessObjectService.findMatching(SponsorHierarchy.class, valueMap);
        if(sponsorHierarchies == null) {
            sponsorHierarchies = new ArrayList<SponsorHierarchy>();
        }
        return sponsorHierarchies;
    }

    public boolean isSponsorNihMultiplePi(Sponsorable sponsorable) {
        Map<String, String> valueMap = new HashMap<String, String>();
        valueMap.put("sponsorCode", sponsorable.getSponsorCode());
        // hard-coding the name is ugly, but there seems to be no ID field for identifying a hierarchy type
        valueMap.put("hierarchyName", "NIH Multiple PI");
        int matchingHierarchies = businessObjectService.countMatching(SponsorHierarchy.class, valueMap);
        
        return matchingHierarchies > 0;
    }
}
