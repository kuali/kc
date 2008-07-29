/*
 * Copyright 2006-2008 The Kuali Foundation
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

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.util.ObjectUtils;
import org.kuali.kra.bo.Sponsor;
import org.kuali.kra.bo.SponsorHierarchy;
import org.kuali.kra.bo.SponsorHierarchyMt;
import org.kuali.kra.dao.SponsorHierarchyDao;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.SponsorService;
import org.kuali.kra.web.struts.form.SponsorHierarchyForm;

public class SponsorServiceImpl implements SponsorService {

    private SponsorHierarchyDao sponsorHierarchyDao;
    private BusinessObjectService businessObjectService;
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory
            .getLog(SponsorServiceImpl.class);

    /**
     * @see org.kuali.kra.proposaldevelopment.service.SponsorService#getSponsorName(java.lang.String)
     */
    public String getSponsorName(String sponsorCode) {
        String sponsorName = null;

        Map<String, String> primaryKeys = new HashMap<String, String>();
        if (StringUtils.isNotEmpty(sponsorCode)) {
            primaryKeys.put("sponsorCode", sponsorCode);
            Sponsor sponsor = (Sponsor) businessObjectService.findByPrimaryKey(Sponsor.class, primaryKeys);
            if (sponsor != null) {
                sponsorName = sponsor.getSponsorName();
            }
        }

        return sponsorName;
    }

    /**
     * TODO : the list is long, so use this levelso1sortid to limit it, not a pretty idea. Should we use jdbc ? or some other
     * options ?
     * 
     * @see org.kuali.kra.service.SponsorService#getTopSponsorHierarchy()
     */
    public String getTopSponsorHierarchy() {

       // KraServiceLocator.getService(UserRoleService.class).getUserRoles("abc", "abc");
        String topSponsorHierarchy = "";
//        Map<String, Integer> levelKey = new HashMap<String, Integer>();
//        levelKey.put("level1Sortid", new Integer(1));
//        List sponsorHierarchyList = (List) businessObjectService.findMatchingOrderBy(SponsorHierarchy.class, levelKey,
//                "hierarchyName", true);
//        String hierarchyName = "";
//        for (Object sponsorHierarchyObj : sponsorHierarchyList) {
//            SponsorHierarchy sponsorHierarchy = (SponsorHierarchy) sponsorHierarchyObj;
//            if (!sponsorHierarchy.getHierarchyName().equals(hierarchyName)) {
//                hierarchyName = sponsorHierarchy.getHierarchyName();
//                topSponsorHierarchy = topSponsorHierarchy + sponsorHierarchy.getHierarchyName() + ";1;";
//            }
//        }
        
        List sponsorHierarchies = (List)getTopSponsorHierarchyList();
        Collections.sort(sponsorHierarchies);
        
        for(Object hierarchyName : sponsorHierarchies) {
            topSponsorHierarchy = topSponsorHierarchy + hierarchyName + ";1;";            
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
            // topSponsorHierarchy = topSponsorHierarchy + name + ";1;";
             
         }
          
         Collections.sort(sponsorHierarchies);
         
         return sponsorHierarchies;
     }

    public Collection getOldSponsorHierarchyMt() {

        Iterator sponsorHierarchyList = sponsorHierarchyDao.getSponsorHierarchyMt();
        List sponsorHierarchies = new ArrayList();
        while (sponsorHierarchyList.hasNext()) {
            Object[] names = (Object[])sponsorHierarchyList.next();
            String name = (String)names[0];
            sponsorHierarchies.add(name);
           // topSponsorHierarchy = topSponsorHierarchy + name + ";1;";
            
        }
         
        Collections.sort(sponsorHierarchies);
        
        return sponsorHierarchies;
    }

    /**
     * 
     * @see org.kuali.kra.service.SponsorService#cleanSponsorHierarchyMt()
     */
    public void cleanSponsorHierarchyMt() {
    
        Map levelKey = new HashMap();
        List sponsorHierarchyList = new ArrayList();
        for (Object name : getOldSponsorHierarchyMt()) {
            levelKey.put("hierarchyName", (String)name);
            sponsorHierarchyList.addAll(businessObjectService.findMatching(SponsorHierarchyMt.class, levelKey));
        }
        businessObjectService.delete(sponsorHierarchyList);
    }
    
    
    public String getSubSponsorHierarchiesForTreeView(String hierarchyName, String depth, String groups) {

        // node is "depth-"+nodelhtml. it's a long list if button is set.
        String returnSponsorHierarchy = null;
        Map<String, String> levelKey = new HashMap<String, String>();
        String[] ascendantList = groups.split(";1;");
        String methodName = "getLevel" + new Integer(Integer.parseInt(depth)+1);
        String sortidMethodName = "";
        levelKey.put("hierarchyName", hierarchyName);

        int level = 1;
        try {
            Class cls = Class.forName("org.kuali.kra.bo.SponsorHierarchyMt");
            if (StringUtils.isNotBlank(groups)) {
                for (int i = 0; i < ascendantList.length ; i++) {
                    levelKey.put("level" + level, ascendantList[i]);
                    level++;
                }
            }

            String sortField ;
            
            if (Integer.parseInt(depth) < 10) {
                sortField="level" + new Integer(Integer.parseInt(depth) + 1) + "Sortid";
            } else {
                sortField = "sponsorCode";
                methodName = "getSponsorCode";
            }
            
            List sponsorHierarchyList = (List) businessObjectService.findMatchingOrderBy(SponsorHierarchyMt.class, levelKey, sortField, true);
            String workLevelName = "";
            returnSponsorHierarchy="";
                Method method = cls.getMethod(methodName, null);
                // method.invoke(cls.newInstance(), null);
                boolean leafNode = false;
                boolean nextLevelIsLeafNode = false;
                
                if (sponsorHierarchyList.size() > 0 && (Integer.parseInt(depth) ==10 || method.invoke(sponsorHierarchyList.get(0), null) == null)) {
                    // if it's a leaf node, then getsponsorcode
                    // should we reload the list by sort order by sponsorcode ?
                    // reset method again, in case depth < 10
                    method = cls.getMethod("getSponsorCode", null);
                    leafNode = true;
                }
                // not a good indicator because some nodes may have more levels than the other
//                if (sponsorHierarchyList.size() > 0 && method.invoke(sponsorHierarchyList.get(0), null) != null) {
//                    String nextLevelMethodName = "getLevel" + new Integer(Integer.parseInt(depth)+2);
//                    Method nextLevelMethod = cls.getMethod(nextLevelMethodName, null);
//                    if (nextLevelMethod.invoke(sponsorHierarchyList.get(0), null) == null) {
//                        nextLevelIsLeafNode = true;
//                    }
//                }
                
                if (CollectionUtils.isNotEmpty(sponsorHierarchyList)) {
                    for (Object sponsorHierarchyObj : sponsorHierarchyList) {
                        SponsorHierarchy sponsorHierarchy = (SponsorHierarchy) sponsorHierarchyObj;
                        String levelName = (String)method.invoke(sponsorHierarchy, null);
                        if (!levelName.equals(workLevelName)) {
                            workLevelName = levelName;
                            if (leafNode) {
                                levelName=levelName+":"+sponsorHierarchy.getSponsor().getSponsorName();
                            }
                            returnSponsorHierarchy = returnSponsorHierarchy + levelName + ";1;";
                        }
                    }
                    returnSponsorHierarchy = returnSponsorHierarchy.substring(0, returnSponsorHierarchy.length() - 3);
                    if (leafNode) {
                        returnSponsorHierarchy = "((leafNodes));1;"+returnSponsorHierarchy;
    //                } else if (nextLevelIsLeafNode) {
    //                    returnSponsorHierarchy = "((leafNodesParent));1;"+returnSponsorHierarchy;
                    }
                }
            }
            catch (Exception e) {
                LOG.info("reflection error " + e.getStackTrace());
            }
        
        return returnSponsorHierarchy;
    }

    
    
    public void changeGroupName(String hierarchyName, String depth, String oldGroupName, String groups) {

        // node is "depth-"+nodelhtml. it's a long list if button is set.
        // also need old label
        Map<String, String> levelKey = new HashMap<String, String>();

        String[] ascendantList = groups.split(";1;");
        String methodName = "setLevel" + depth;
        levelKey.put("hierarchyName", hierarchyName);
        levelKey.put("level" + depth, oldGroupName);

        int level = 1;
        try {
            Class cls = Class.forName("org.kuali.kra.bo.SponsorHierarchyMt");
            if (StringUtils.isNotBlank(groups)) {
                // last one is new group name
                for (int i = 0; i < ascendantList.length - 1 ; i++) {
                    levelKey.put("level" + level, ascendantList[i]);
                    level++;
                }
            } 
            LOG.info("chggroup levelKey="+levelKey+"depth = "+depth+"groups = "+groups+"oldlabel="+oldGroupName);

            List sponsorHierarchyList = (List) businessObjectService.findMatching(SponsorHierarchyMt.class, levelKey);
            
            Method method = cls.getMethod(methodName, String.class);
            // method.invoke(cls.newInstance(), null);
            String newGroupName = ascendantList[ascendantList.length - 1];
            for (Object sponsorHierarchyObj : sponsorHierarchyList) {
                SponsorHierarchyMt sponsorHierarchy = (SponsorHierarchyMt) sponsorHierarchyObj;
                method.invoke(sponsorHierarchy, newGroupName);
            }
            businessObjectService.save(sponsorHierarchyList);
            LOG.info("after save "+sponsorHierarchyList.size());
            
        } catch (Exception e) {
                LOG.info("reflection error " + e.getStackTrace());
        }
        
    }

    public String checkSubGroup(String hierarchyName, String depth, String groups) {

        // node is "depth-"+nodelhtml. it's a long list if button is set.
        // also need old label
        Map<String, String> levelKey = new HashMap<String, String>();

        String[] ascendantList = groups.split(";1;");
        String methodName = "getLevel" +new Integer(Integer.parseInt(depth)+1);
        levelKey.put("hierarchyName", hierarchyName);
        String retMsg = null;
        int level = 1;
        try {
            Class cls = Class.forName("org.kuali.kra.bo.SponsorHierarchyMt");
            if (StringUtils.isNotBlank(groups)) {
                // last one is new group name
                for (int i = 0; i < ascendantList.length ; i++) {
                    levelKey.put("level" + level, ascendantList[i]);
                    level++;
                }
            } 

            List sponsorHierarchyList = (List) businessObjectService.findMatching(SponsorHierarchyMt.class, levelKey);
            
            Method method = cls.getMethod(methodName, null);
            if (CollectionUtils.isNotEmpty(sponsorHierarchyList)) {
                retMsg = (String)method.invoke((SponsorHierarchyMt)sponsorHierarchyList.get(0), null);
            }
            //businessObjectService.save(sponsorHierarchyList);
            
        } catch (Exception e) {
                LOG.info("reflection error " + e.getStackTrace());
        }
        LOG.info("retMsg ="+retMsg);
        if (retMsg == null) {
            return "0";
        } else {
            return retMsg;
        }
        
    }

    public void changeSortId_bak(String hierarchyName, String depth, String groups, int sortid) {
        sponsorHierarchyDao.changeSortId(hierarchyName, depth, groups, sortid);

    }

    public void changeSortId(String hierarchyName, String depth, String groups, boolean moveUp) {

        // node is "depth-"+nodelhtml. it's a long list if button is set.
        // also need old label
        Map<String, String> levelKey = new HashMap<String, String>();
        
        
        String[] ascendantList = groups.split(";1;");
        String methodName = "";
        String getterMethodName = "";
        levelKey.put("hierarchyName", hierarchyName);
        List sponsorHierarchyList=null;
        int level = 1;
        int newSortid=0;
        try {
            Class cls = Class.forName("org.kuali.kra.bo.SponsorHierarchyMt");
            if (StringUtils.isNotBlank(groups)) {
                // last one is new group name
                for (int i = 0; i < ascendantList.length ; i++) {
                    levelKey.put("level" + level, ascendantList[i]);
                    level++;
                }
            } 
            methodName = "setLevel" + depth+ "Sortid";
            getterMethodName = "getLevel" + depth+ "Sortid";
            
            
            LOG.info("chgsortid levelKey="+levelKey+"depth = "+depth+"groups = "+groups+"moveUp="+moveUp);
            sponsorHierarchyList = (List) businessObjectService.findMatching(SponsorHierarchyMt.class, levelKey);
                Method method = cls.getMethod(methodName, Integer.class);
                Method methodGetSortid = cls.getMethod(getterMethodName, null);
                LOG.info("method "+method+"-"+methodGetSortid);
                if (CollectionUtils.isNotEmpty(sponsorHierarchyList)) {   
                    for (Object sponsorHierarchyObj : sponsorHierarchyList) {
                        SponsorHierarchyMt sponsorHierarchy = (SponsorHierarchyMt) sponsorHierarchyObj;
                        Integer sortid = (Integer)methodGetSortid.invoke(sponsorHierarchy, null);
                        if (sortid != null) {
                            if (moveUp) {
                                method.invoke(sponsorHierarchy, sortid -1 );
                                newSortid=sortid -1;
                            } else {
                                method.invoke(sponsorHierarchy, sortid +1 );  
                                newSortid=sortid +1;
                            }
                        }
                    }
                    LOG.info("before save "+sponsorHierarchyList.size());
                    //businessObjectService.save(sponsorHierarchyList);
                }
            } catch (Exception e) {
                LOG.info("reflection error " +e.getMessage()+ e.getStackTrace());
            }
            businessObjectService.save(sponsorHierarchyList);
            List sponsorHierarchyList1 = (List) businessObjectService.findMatching(SponsorHierarchyMt.class, levelKey);
            try {
                Class cls = Class.forName("org.kuali.kra.bo.SponsorHierarchyMt");
            if (CollectionUtils.isNotEmpty(sponsorHierarchyList1)) {
                Method methodGetSortid = cls.getMethod(getterMethodName, null);
                Integer sortid = (Integer)methodGetSortid.invoke((SponsorHierarchyMt)sponsorHierarchyList1.get(0), null);
                if (newSortid != sortid) {
                    LOG.info("Sortid not updated " +"chgsortid levelKey="+levelKey+"depth = "+depth+"groups = "+groups+"moveUp="+moveUp);
                }
            }
            } catch (Exception e) {
                LOG.info("reflection error " +e.getMessage()+ e.getStackTrace());
            }
            LOG.info("after save "+sponsorHierarchyList.size());

    }

    public void deleteSponsorHierarchyDwr(String hierarchyName, String depth, String nodeName, String groups, boolean isDeleteSponsor) {

        // node is "depth-"+nodelhtml. it's a long list if button is set.
        // also need old label
        Map<String, String> levelKey = new HashMap<String, String>();

        String[] ascendantList = groups.split(";1;");
        levelKey.put("hierarchyName", hierarchyName);
        
        List sponsorHierarchyList;
        
        if (isDeleteSponsor) {
            levelKey.put("hierarchyName", hierarchyName);
            levelKey.put("sponsorCode", nodeName.substring(0, nodeName.indexOf(":")));
            sponsorHierarchyList = (List) businessObjectService.findMatching(SponsorHierarchyMt.class, levelKey);

        } else {
            int level = 1;
            levelKey.put("level" + depth, nodeName);

            if (StringUtils.isNotBlank(groups)) {
                // last one is new group name
                for (int i = 0; i < ascendantList.length ; i++) {
                    levelKey.put("level" + level, ascendantList[i]);
                    level++;
                }
            } 

            sponsorHierarchyList = (List) businessObjectService.findMatching(SponsorHierarchyMt.class, levelKey);
        }
        businessObjectService.delete(sponsorHierarchyList);
        
    }
    
    public void addSponsorHierarchyDwr(String hierarchyName, String sponsors, String ascendants) {
        
        SponsorHierarchyMt sponsorHierarchy = new SponsorHierarchyMt();
        // stringtokenizer is problematic
//        StringTokenizer token = new StringTokenizer(ascendants.replaceAll(";1;", "###"), "###");
        String[] ascendantList = ascendants.split(";1;");
        String methodName = "";
        String sortidMethodName = "";
        int level = 1;
        try {
            Class cls = Class.forName("org.kuali.kra.bo.SponsorHierarchyMt");
            sponsorHierarchy.setHierarchyName(hierarchyName);
//            while (token.hasMoreTokens()) {
            for (int i = 0; i < ascendantList.length ; i++) {
                String node = ascendantList[i];
                methodName = "setLevel" + level;
                sortidMethodName = "setLevel" + level+"Sortid";
                Method method = cls.getMethod(methodName, String.class);;
                Method methodSetSortid = cls.getMethod(sortidMethodName, Integer.class);
                method.invoke(sponsorHierarchy, node.substring(0, node.indexOf("((#")));
                methodSetSortid.invoke(sponsorHierarchy, Integer.parseInt(StringUtils.substringsBetween(node, "((#", "#))")[0]));
                level++;
            }
        }
        catch (Exception e) {
            LOG.info("reflection error " + e.getStackTrace());
        }

        List sponsorHierarchyList = new ArrayList();
        
        //stringtokenizer has problem with 001044;1;002200.  will have token of '00','1044' and '002200' 
        //StringTokenizer sponsorList = new StringTokenizer(sponsors.replaceAll(";1;", "###"), "###");
        String[] sponsorList = sponsors.split(";1;");
        
        for (int i = 0; i < sponsorList.length; i++) {
            SponsorHierarchyMt newSponsorHierarchy = (SponsorHierarchyMt)ObjectUtils.deepCopy(sponsorHierarchy);
            String nextSponsor = sponsorList[i];
            newSponsorHierarchy.setSponsorCode(nextSponsor.substring(0, nextSponsor.indexOf(":")));
            sponsorHierarchyList.add(newSponsorHierarchy);
        }
        
        businessObjectService.save(sponsorHierarchyList);
        
    }

    
    public void copySponsorHierarchy(SponsorHierarchyForm sponsorHierarchyForm) {
        Map fieldValues = new HashMap();
        fieldValues.put("hierarchyName", sponsorHierarchyForm.getSelectedSponsorHierarchy());

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
        fieldValues.put("hierarchyName", sponsorHierarchyForm.getSelectedSponsorHierarchy());

        List sponsors = (List)businessObjectService.findMatching(SponsorHierarchy.class, fieldValues);
        businessObjectService.delete(sponsors);
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

    public SponsorHierarchyDao getSponsorHierarchyDao() {
        return sponsorHierarchyDao;
    }

    public void setSponsorHierarchyDao(SponsorHierarchyDao sponsorHierarchyDao) {
        this.sponsorHierarchyDao = sponsorHierarchyDao;
    }

    public String loadToSponsorHierachyMt(String hierarchyName, String timestampKey) {

        Map<String, String> levelKey = new HashMap<String, String>();
        levelKey.put("hierarchyName", hierarchyName);
        String sponsorCodes="";
        
        List deleteList = (List) businessObjectService.findMatching(SponsorHierarchyMt.class, levelKey);
        List sponsorHierarchyList = (List) businessObjectService.findMatching(SponsorHierarchy.class, levelKey);
            List <SponsorHierarchyMt> loadList = new ArrayList<SponsorHierarchyMt>();   
                for (Object sponsorHierarchyObj : sponsorHierarchyList) {
                    SponsorHierarchy sponsorHierarchy = (SponsorHierarchy) sponsorHierarchyObj;
                    SponsorHierarchyMt sponsorHierarchyMt = new SponsorHierarchyMt();
                    sponsorHierarchyMt.setHierarchyName(timestampKey+"#"+sponsorHierarchy.getHierarchyName());
                    sponsorHierarchyMt.setSponsorCode(sponsorHierarchy.getSponsorCode());
                    sponsorHierarchyMt.setLevel1(sponsorHierarchy.getLevel1());
                    sponsorHierarchyMt.setLevel2(sponsorHierarchy.getLevel2());
                    sponsorHierarchyMt.setLevel3(sponsorHierarchy.getLevel3());
                    sponsorHierarchyMt.setLevel4(sponsorHierarchy.getLevel4());
                    sponsorHierarchyMt.setLevel5(sponsorHierarchy.getLevel5());
                    sponsorHierarchyMt.setLevel6(sponsorHierarchy.getLevel6());
                    sponsorHierarchyMt.setLevel7(sponsorHierarchy.getLevel7());
                    sponsorHierarchyMt.setLevel8(sponsorHierarchy.getLevel8());
                    sponsorHierarchyMt.setLevel9(sponsorHierarchy.getLevel9());
                    sponsorHierarchyMt.setLevel10(sponsorHierarchy.getLevel10());
                    
                    sponsorHierarchyMt.setLevel1Sortid(sponsorHierarchy.getLevel1Sortid());
                    sponsorHierarchyMt.setLevel2Sortid(sponsorHierarchy.getLevel2Sortid());
                    sponsorHierarchyMt.setLevel3Sortid(sponsorHierarchy.getLevel3Sortid());
                    sponsorHierarchyMt.setLevel4Sortid(sponsorHierarchy.getLevel4Sortid());
                    sponsorHierarchyMt.setLevel5Sortid(sponsorHierarchy.getLevel5Sortid());
                    sponsorHierarchyMt.setLevel6Sortid(sponsorHierarchy.getLevel6Sortid());
                    sponsorHierarchyMt.setLevel7Sortid(sponsorHierarchy.getLevel7Sortid());
                    sponsorHierarchyMt.setLevel8Sortid(sponsorHierarchy.getLevel8Sortid());
                    sponsorHierarchyMt.setLevel9Sortid(sponsorHierarchy.getLevel9Sortid());
                    sponsorHierarchyMt.setLevel10Sortid(sponsorHierarchy.getLevel10Sortid());
                    sponsorCodes=sponsorCodes+sponsorHierarchyMt.getSponsorCode()+";";
                    loadList.add(sponsorHierarchyMt);
                }
            
                businessObjectService.delete(deleteList);
                businessObjectService.save(loadList);
                return sponsorCodes;
    }
    
    public void saveSponsorHierachy(String hierarchyName, String timestampKey) {

        Map<String, String> levelKey = new HashMap<String, String>();
        levelKey.put("hierarchyName", hierarchyName);
            
        List deleteList = (List) businessObjectService.findMatching(SponsorHierarchy.class, levelKey);
        levelKey.put("hierarchyName", timestampKey+"#"+hierarchyName);
        List sponsorHierarchyList = (List) businessObjectService.findMatching(SponsorHierarchyMt.class, levelKey);
        List <SponsorHierarchy> loadList = new ArrayList<SponsorHierarchy>();   
                for (Object sponsorHierarchyObj : sponsorHierarchyList) {
                        SponsorHierarchyMt sponsorHierarchyMt = (SponsorHierarchyMt) sponsorHierarchyObj;
                        SponsorHierarchy sponsorHierarchy = new SponsorHierarchy();
                        sponsorHierarchy.setHierarchyName(hierarchyName);
                        sponsorHierarchy.setSponsorCode(sponsorHierarchyMt.getSponsorCode());
                        sponsorHierarchy.setLevel1(sponsorHierarchyMt.getLevel1());
                        sponsorHierarchy.setLevel2(sponsorHierarchyMt.getLevel2());
                        sponsorHierarchy.setLevel3(sponsorHierarchyMt.getLevel3());
                        sponsorHierarchy.setLevel4(sponsorHierarchyMt.getLevel4());
                        sponsorHierarchy.setLevel5(sponsorHierarchyMt.getLevel5());
                        sponsorHierarchy.setLevel6(sponsorHierarchyMt.getLevel6());
                        sponsorHierarchy.setLevel7(sponsorHierarchyMt.getLevel7());
                        sponsorHierarchy.setLevel8(sponsorHierarchyMt.getLevel8());
                        sponsorHierarchy.setLevel9(sponsorHierarchyMt.getLevel9());
                        sponsorHierarchy.setLevel10(sponsorHierarchyMt.getLevel10());
                        
                        sponsorHierarchy.setLevel1Sortid(sponsorHierarchyMt.getLevel1Sortid());
                        sponsorHierarchy.setLevel2Sortid(sponsorHierarchyMt.getLevel2Sortid());
                        sponsorHierarchy.setLevel3Sortid(sponsorHierarchyMt.getLevel3Sortid());
                        sponsorHierarchy.setLevel4Sortid(sponsorHierarchyMt.getLevel4Sortid());
                        sponsorHierarchy.setLevel5Sortid(sponsorHierarchyMt.getLevel5Sortid());
                        sponsorHierarchy.setLevel6Sortid(sponsorHierarchyMt.getLevel6Sortid());
                        sponsorHierarchy.setLevel7Sortid(sponsorHierarchyMt.getLevel7Sortid());
                        sponsorHierarchy.setLevel8Sortid(sponsorHierarchyMt.getLevel8Sortid());
                        sponsorHierarchy.setLevel9Sortid(sponsorHierarchyMt.getLevel9Sortid());
                        sponsorHierarchy.setLevel10Sortid(sponsorHierarchyMt.getLevel10Sortid());
                        loadList.add(sponsorHierarchy);
                    }
            
                businessObjectService.delete(deleteList);
                businessObjectService.save(loadList);
            
    }

}
