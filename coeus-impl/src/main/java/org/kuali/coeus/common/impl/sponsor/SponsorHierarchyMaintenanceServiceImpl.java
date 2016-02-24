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
package org.kuali.coeus.common.impl.sponsor;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.framework.sponsor.hierarchy.SponsorHierarchy;
import org.kuali.coeus.common.impl.sponsor.hierarchy.SponsorHierarchyDao;
import org.kuali.coeus.common.impl.sponsor.hierarchy.SponsorHierarchyForm;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.*;

@Component("sponsorHierarchyMaintenanceService")
@SuppressWarnings({"deprecation","unchecked"})
public class SponsorHierarchyMaintenanceServiceImpl implements SponsorHierarchyMaintenanceService, Constants {
	
    private static final Log LOG = LogFactory.getLog(SponsorHierarchyMaintenanceServiceImpl.class);
    private static final String SESSION_KEY = SponsorHierarchyMaintenanceServiceImpl.class.getName() + ".actionList";
    private static final Integer HIERARCHY_MAX_HEIGHT = 10;
	
	@Autowired
	@Qualifier("sponsorHierarchyDao")
    private SponsorHierarchyDao sponsorHierarchyDao;
	
	@Autowired
	@Qualifier("businessObjectService")
    private BusinessObjectService businessObjectService;
	
	@Autowired
	@Qualifier("parameterService")
    private ParameterService parameterService;

    @Autowired
    @Qualifier("globalVariableService")
    private GlobalVariableService globalVariableService;

    protected enum SponsorActionType {
        INSERT, UPDATE_NAME, UPDATE_SORT, DELETE;
    }
    
    protected class SponsorAction {
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

    @Override
    public String getTopSponsorHierarchy() {

        String topSponsorHierarchy = Constants.EMPTY_STRING;
        
        List sponsorHierarchies = (List)getTopSponsorHierarchyList();
        Collections.sort(sponsorHierarchies);
        
        for(Object hierarchyName : sponsorHierarchies) {
            topSponsorHierarchy = topSponsorHierarchy + hierarchyName + Constants.SPONSOR_HIERARCHY_SEPARATOR_C1C;            
        }
        if(topSponsorHierarchy.length() >= 3) {
            topSponsorHierarchy = topSponsorHierarchy.substring(0, topSponsorHierarchy.length() - 3);
        }
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
            returnSponsorHierarchy = getSubSponsorHierarchy(hierarchyName,Integer.parseInt(depth)+1, ascendantList);
        }
 
        if (StringUtils.isBlank(returnSponsorHierarchy)) {
            returnSponsorHierarchy = getSponsorCodesForGroup(hierarchyName,Integer.parseInt(depth)+1, ascendantList);

            if (StringUtils.isNotBlank(returnSponsorHierarchy)) {
                returnSponsorHierarchy = "((leafNodes))"+Constants.SPONSOR_HIERARCHY_SEPARATOR_C1C+returnSponsorHierarchy;
            }
        }
       
        return returnSponsorHierarchy;
    }
    
    protected String getSubSponsorHierarchy(String hierarchyName, int level, String[] levelName) {
        Map<String, Object> values = new HashMap<String, Object>();
        values.put("hierarchyName", hierarchyName);
        for (int i = 1; i< level; i++) {
            values.put("level" + i, levelName[i-1]);
        }
        Collection<SponsorHierarchy> sponsors = getBusinessObjectService().findMatchingOrderBy(SponsorHierarchy.class, values, "level"+level+"Sortid", true);
        try {
            Method getLevel = SponsorHierarchy.class.getDeclaredMethod("getLevel" + level, null);
            Method getSortId = SponsorHierarchy.class.getDeclaredMethod("getLevel" + level + "Sortid", null);
            String retVal = null;
            List<String> uniqueLevel = new ArrayList<String>();
            for (SponsorHierarchy sponsor : sponsors) {
                String levelValue = (String) getLevel.invoke(sponsor, null);
                Integer sortValue = (Integer) getSortId.invoke(sponsor, null);
                if (levelValue != null && !uniqueLevel.contains(levelValue + ":1:" + sortValue)) {
                    uniqueLevel.add(levelValue + ":1:" + sortValue);
                }
            }
            for (String uniqueItem : uniqueLevel) {
                if (retVal == null) {
                    retVal = uniqueItem.split(":1:")[0];
                } else {
                    retVal += Constants.SPONSOR_HIERARCHY_SEPARATOR_C1C + uniqueItem.split(":1:")[0];
                }
            }
            return retVal;
        }
        catch (Exception e) {
            LOG.error("Error getting sponsor hierarchy information", e);
        }
        return null;
    }
    
    protected String getSponsorCodesForGroup(String hierarchyName, int level, String[] levelName) {
        Map<String, Object> values = new HashMap<String, Object>();
        values.put("hierarchyName", hierarchyName);
        for (int i = 1; i< level; i++) {
            values.put("level" + i, levelName[i-1]);
        }
        int groupingNumber = 300;
        try {
           String sysParam = parameterService.getParameterValueAsString(
                Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT, "A", Constants.NUMBER_PER_SPONSOR_HIERARCHY_GROUP);
           groupingNumber=Integer.parseInt(sysParam);
        } catch (Exception e) {
            LOG.debug("System param for numberPerSponsorHierarchyGroup is not defined");
        }        
        Collection<SponsorHierarchy> sponsors = getBusinessObjectService().findMatchingOrderBy(SponsorHierarchy.class, values, "sponsorCode", true);
        String retVal = null;
        int i = groupingNumber;
        for (SponsorHierarchy sponsor : sponsors) {
            if (StringUtils.isBlank(retVal)) {
                retVal = sponsor.getSponsorCode()+":"+sponsor.getSponsor().getSponsorName();
                i--;
            } else if (i-- > 0){
                retVal += Constants.SPONSOR_HIERARCHY_SEPARATOR_C1C + sponsor.getSponsorCode() + ":" + sponsor.getSponsor().getSponsorName();
            } else {
                retVal += Constants.SPONSOR_HIERARCHY_SEPARATOR_P1P + sponsor.getSponsorCode() + ":" + sponsor.getSponsor().getSponsorName();
                i = groupingNumber;
            }
        }
        return retVal;
    }
    
    protected String getSponsorCodesForDeletedGroup(String hierarchyName, int level, String[] levelName) {
        Map<String, Object> values = new HashMap<String, Object>();
        values.put("hierarchyName", hierarchyName);
        for (int i = 1; i< level; i++) {
            values.put("level" + i, levelName[i-1]);
        }
        Collection<SponsorHierarchy> sponsors = getBusinessObjectService().findMatching(SponsorHierarchy.class, values);
        String retVal = null;
        for (SponsorHierarchy sponsor : sponsors) {
            if (StringUtils.isBlank(retVal)) { 
                retVal = sponsor.getSponsorCode();
            } else {
                retVal += ";" + sponsor.getSponsorCode();
            }
        }
        return retVal;
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
        
        return getSponsorCodesForDeletedGroup(hierarchyName,Integer.parseInt(depth)+1, ascendantList);
    }
    
    public void updateSponsorCodes(String sponsorCodes) {

        if (globalVariableService.getUserSession().retrieveObject("sponsorCodes") != null) {
            globalVariableService.getUserSession().removeObject("sponsorCodes");
        }
        globalVariableService.getUserSession().addObject("sponsorCodes", (Object)sponsorCodes);
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
    protected void addActionToBeSaved(SponsorAction action) {
        List<SponsorAction> actions = (List)globalVariableService.getUserSession().retrieveObject(SESSION_KEY);
        if (actions == null) {
            actions = new ArrayList<SponsorAction>();
            globalVariableService.getUserSession().addObject(SESSION_KEY, actions);
        }
        actions.add(action);
    }
    
    public void executeActions() {
        List<SponsorAction> actions = (List)globalVariableService.getUserSession().retrieveObject(SESSION_KEY);
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
    
    protected void updateGroup(SponsorHierarchy sponsor, Integer level, String newGroupName) {
        try {
            Method setLevelMethod = SponsorHierarchy.class.getMethod("setLevel" + level, new Class[]{String.class});
            setLevelMethod.invoke(sponsor, newGroupName);
        }
        catch (Exception e) {
            LOG.debug("Error setting group name on sponsor", e);
        }
    }
    
    protected void updateSortId(SponsorHierarchy sponsor, Integer level, Boolean moveDown) {
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
            LOG.debug("Error setting new sortId on sponsor", e);
        }
    }    
    
    protected Integer getNewSortId(Integer currentSortId, int changeBy) {
        if (currentSortId == null) {
            currentSortId = 0;
        }
        return currentSortId + changeBy;
        
    }
    
    protected Map<String, String> getFieldValues(SponsorAction action) {
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
        globalVariableService.getUserSession().removeObject(SESSION_KEY);
    }

    @Override
    public List<String> getUniqueGroupingNames(String hierarchyName, Integer level) {
       List<String> result = getSponsorHierarchyDao().getUniqueNamesAtLevel(hierarchyName, level);
       Collections.sort(result);
       return result;
    }

    /**
     * Gets the businessObjectService attribute.
     * 
     * @return Returns the businessObjectService.
     */
    protected BusinessObjectService getBusinessObjectService() {
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
    
    protected ParameterService getParameterService() {
    	return parameterService;
    }

    /**
     * @param parameterService
     */
    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    protected SponsorHierarchyDao getSponsorHierarchyDao() {
        return sponsorHierarchyDao;
    }

    protected void setSponsorHierarchyDao(SponsorHierarchyDao sponsorHierarchyDao) {
        this.sponsorHierarchyDao = sponsorHierarchyDao;
    }

    public GlobalVariableService getGlobalVariableService() {
        return globalVariableService;
    }

    public void setGlobalVariableService(GlobalVariableService globalVariableService) {
        this.globalVariableService = globalVariableService;
    }
}
