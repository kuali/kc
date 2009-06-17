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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.Sponsor;
import org.kuali.kra.bo.SponsorHierarchy;
import org.kuali.kra.dao.SponsorHierarchyDao;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.service.SponsorService;
import org.kuali.kra.web.struts.form.SponsorHierarchyForm;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.GlobalVariables;

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

    public String loadToSponsorHierachyMt(String hierarchyName) {

        String sponsorCodes=Constants.EMPTY_STRING;
        
        Iterator sponsorHierarchyList = sponsorHierarchyDao.getAllSponsors(hierarchyName);
        List sponsorHierarchies = new ArrayList();
        while (sponsorHierarchyList.hasNext()) {
            sponsorCodes = sponsorCodes +((Object[])sponsorHierarchyList.next())[0]+";";
        }
        return sponsorCodes;
    }
    
    public void saveSponsorHierachy(String hierarchyName, String sqlScripts) {

        sponsorHierarchyDao.runScripts(sqlScripts.split(Constants.SPONSOR_HIERARCHY_SEPARATOR_C1C));
        
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

    public void uploadScripts(String key, String scripts) {
        // may need to save to db instead save in session.  the scripts may be huge??

        String sciptsInSession = (String)GlobalVariables.getUserSession().retrieveObject(key);
        if (sciptsInSession != null) {
            sciptsInSession = sciptsInSession.concat("#1#"+scripts);
        } else {
            sciptsInSession = scripts;
        }
        
        GlobalVariables.getUserSession().addObject(key, (Object)sciptsInSession);
    }
   
}
