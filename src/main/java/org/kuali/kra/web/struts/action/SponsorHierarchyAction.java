/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.web.struts.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.bo.Sponsor;
import org.kuali.kra.bo.SponsorHierarchy;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.rules.SponsorHierarchyRule;
import org.kuali.kra.service.SponsorService;
import org.kuali.kra.web.struts.form.SponsorHierarchyForm;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.kns.lookup.LookupResultsService;
import org.kuali.rice.kns.web.struts.action.KualiAction;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.util.GlobalVariables;

public class SponsorHierarchyAction extends KualiAction {

    private static final String MAINT = "maint";
    private static final String NEW = "new";
    private static final String LOOKUP = "lookup";
    private static final String HIERARCHY_NAME = "hierarchyName";
    private static final String SELECTED_HIERARCHY_NAME = "selectedHierarchyName";
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        SponsorHierarchyForm sponsorHierarchyForm = (SponsorHierarchyForm)form;
        ActionForward forward = super.execute(mapping, form, request, response);
        if (StringUtils.isNotBlank(request.getParameter("mapKey"))) {
            sponsorHierarchyForm.getNewSponsors().get(0).clear();
        }
        //TODO: refactor this.  2 things very similar, should not call twice
        sponsorHierarchyForm.setTopSponsorHierarchies(KraServiceLocator.getService(SponsorService.class).getTopSponsorHierarchy());   
        sponsorHierarchyForm.setHierarchyNameList((KraServiceLocator.getService(SponsorService.class).getTopSponsorHierarchyList()));   
        return forward;

    }

    public ActionForward copy(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        SponsorHierarchyForm sponsorHierarchyForm = (SponsorHierarchyForm)form;
        sponsorHierarchyForm.setHierarchyName(sponsorHierarchyForm.getSelectedSponsorHierarchy());
        return mapping.findForward("copy");
    }
    
    public ActionForward deleteSponsorHierarchy(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        KraServiceLocator.getService(SponsorService.class).deleteSponsorHierarchy((SponsorHierarchyForm)form);
        //((SponsorHierarchyForm)form).setTopSponsorHierarchies(KraServiceLocator.getService(SponsorService.class).getTopSponsorHierarchy());   
        ((SponsorHierarchyForm)form).setHierarchyNameList((KraServiceLocator.getService(SponsorService.class).getTopSponsorHierarchyList()));   
        ((SponsorHierarchyForm)form).setMessage("Sponsor Hierarchy was deleted successfully");
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public ActionForward copySponsorHierarchy(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        boolean rulePassed = new SponsorHierarchyRule().newHierarchyNameRequired((SponsorHierarchyForm)form);
        if (rulePassed) {
            KraServiceLocator.getService(SponsorService.class).copySponsorHierarchy((SponsorHierarchyForm)form);
            ((SponsorHierarchyForm)form).setTopSponsorHierarchies(KraServiceLocator.getService(SponsorService.class).getTopSponsorHierarchy());   
            ((SponsorHierarchyForm)form).setMessage("Sponsor Hierarchy was copied successfully");
        }
        return mapping.findForward(Constants.MAPPING_BASIC);            
    }
    
    public ActionForward cancelSponsorHierarchy(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        ActionForward  forward = new ActionForward("/portal.do??methodToCall=refresh&selectedTab=portalMaintenanceBody");  
        KraServiceLocator.getService(SponsorService.class).clearCurrentActions();
        forward.setRedirect(true);
        return forward;            
    }
    
    public ActionForward cancelSponsorHierarchyMaint(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        SponsorHierarchyForm sponsorHierarchyForm = (SponsorHierarchyForm)form;
        sponsorHierarchyForm.setSponsorCodeList(Constants.EMPTY_STRING);
        sponsorHierarchyForm.setNewHierarchyName(Constants.EMPTY_STRING);
        sponsorHierarchyForm.setSelectedSponsorHierarchy(Constants.EMPTY_STRING);
        KraServiceLocator.getService(SponsorService.class).clearCurrentActions();
        GlobalVariables.getUserSession().removeObject(SELECTED_HIERARCHY_NAME);
        GlobalVariables.getUserSession().removeObject(HIERARCHY_NAME);
        GlobalVariables.getUserSession().removeObject(sponsorHierarchyForm.getTimestamp());
        return mapping.findForward(Constants.MAPPING_BASIC);            
    }

    public ActionForward saveSponsorHierarchy(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        //boolean rulePassed = new SponsorHierarchyRule().newHierarchyNameRequired((SponsorHierarchyForm)form);
        //if (rulePassed) {
        KraServiceLocator.getService(SponsorService.class).executeActions();
        SponsorHierarchyForm sponsorHierarchyForm = (SponsorHierarchyForm)form;
 
        sponsorHierarchyForm.setActionSelected(MAINT);
        sponsorHierarchyForm.setSponsorCodeList(KraServiceLocator.getService(SponsorService.class).loadToSponsorHierachyMt(sponsorHierarchyForm.getSelectedSponsorHierarchy()));
        GlobalVariables.getUserSession().removeObject(SELECTED_HIERARCHY_NAME);
        GlobalVariables.getUserSession().removeObject(sponsorHierarchyForm.getTimestamp());
        GlobalVariables.getUserSession().removeObject("sponsorCodes");
        sponsorHierarchyForm.setTimestamp(KraServiceLocator.getService(DateTimeService.class).getCurrentTimestamp().toString());
        return mapping.findForward(MAINT);            
    }

    public ActionForward newSponsorHierarchy(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        boolean rulePassed = new SponsorHierarchyRule().newHierarchyNameRequired((SponsorHierarchyForm)form);
        if (rulePassed) {
            ((SponsorHierarchyForm)form).setNewSponsorHierarchy(new SponsorHierarchy());
            SponsorHierarchyForm sponsorHierarchyForm = (SponsorHierarchyForm)form;
            sponsorHierarchyForm.setHierarchyName(sponsorHierarchyForm.getNewHierarchyName());
            sponsorHierarchyForm.setSponsorCodeList(Constants.EMPTY_STRING);
            sponsorHierarchyForm.setTimestamp(KraServiceLocator.getService(DateTimeService.class).getCurrentTimestamp().toString());
            ((SponsorHierarchyForm)form).setActionSelected(NEW);
            KraServiceLocator.getService(SponsorService.class).clearCurrentActions();
            GlobalVariables.getUserSession().addObject(SELECTED_HIERARCHY_NAME, (Object)sponsorHierarchyForm.getSelectedSponsorHierarchy());
            GlobalVariables.getUserSession().addObject(HIERARCHY_NAME, (Object)sponsorHierarchyForm.getHierarchyName());
            return mapping.findForward(MAINT);            
        } else {
            return mapping.findForward(Constants.MAPPING_BASIC);
        }
    }
    
    public ActionForward maintSponsorHierarchy(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        ((SponsorHierarchyForm)form).setActionSelected(MAINT);
        SponsorHierarchyForm sponsorHierarchyForm = (SponsorHierarchyForm)form;
        sponsorHierarchyForm.setHierarchyName(sponsorHierarchyForm.getSelectedSponsorHierarchy());
        sponsorHierarchyForm.setSponsorCodeList(KraServiceLocator.getService(SponsorService.class).loadToSponsorHierachyMt(sponsorHierarchyForm.getSelectedSponsorHierarchy()));
        GlobalVariables.getUserSession().removeObject(SELECTED_HIERARCHY_NAME);
        GlobalVariables.getUserSession().addObject(HIERARCHY_NAME, (Object)sponsorHierarchyForm.getHierarchyName());
        KraServiceLocator.getService(SponsorService.class).clearCurrentActions();
        sponsorHierarchyForm.setTimestamp(KraServiceLocator.getService(DateTimeService.class).getCurrentTimestamp().toString());
        return mapping.findForward(MAINT);
        
    }

    public ActionForward deleteSponsorHierarchyGroup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        SponsorHierarchyForm sponsorHierarchyForm = (SponsorHierarchyForm)form;
        sponsorHierarchyForm.getSponsorHierarchyList().remove(getLineToDelete(request));
        sponsorHierarchyForm.getNewSponsors().remove(getLineToDelete(request));
        return mapping.findForward(NEW);
       
    }
    
    @Override
    public ActionForward refresh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        super.refresh(mapping, form, request, response);
        SponsorHierarchyForm sponsorHierarchyForm = (SponsorHierarchyForm) form;
        String sponsors = Constants.EMPTY_STRING; // return to treeview - still a test
        if (sponsorHierarchyForm.getLookupResultsBOClassName() != null && sponsorHierarchyForm.getLookupResultsSequenceNumber() != null) {
            String lookupResultsSequenceNumber = sponsorHierarchyForm.getLookupResultsSequenceNumber();
            
            @SuppressWarnings("unchecked")
            Class<BusinessObject> lookupResultsBOClass = (Class<BusinessObject>) Class.forName(sponsorHierarchyForm.getLookupResultsBOClassName());
            
            LookupResultsService lookupService = KraServiceLocator.getService(LookupResultsService.class);
            String principalId = GlobalVariables.getUserSession().getPerson().getPrincipalId();   // LookupService.retrieveSelectedResultBOs checks that this is the user that persisted the BO
            Collection<BusinessObject> rawValues = lookupService.retrieveSelectedResultBOs(lookupResultsSequenceNumber, lookupResultsBOClass, principalId);
            int idx = 0;
            String idxString = StringUtils.substringBetween(sponsorHierarchyForm.getLookedUpCollectionName(),"[","]");
            if (StringUtils.isNotBlank(idxString)) {
                idx = Integer.parseInt(idxString);
            }
            List sponsorCodes = new ArrayList();
            sponsorHierarchyForm.getNewSponsors().set(0, new ArrayList());
            sponsorHierarchyForm.setSelectedSponsors(Constants.EMPTY_STRING);
            for (Iterator iter = rawValues.iterator(); iter.hasNext();) {
                    Sponsor sponsor = (Sponsor) iter.next();
                    if (!sponsorCodes.contains(sponsor.getSponsorCode())) {
                        sponsorHierarchyForm.getNewSponsors().get(idx).add(sponsor);
                        if (StringUtils.isBlank(sponsors)) {
                            sponsors = sponsor.getSponsorCode()+":"+sponsor.getSponsorName();
                        } else {
                            sponsors = sponsors + Constants.SPONSOR_HIERARCHY_SEPARATOR_C1C+sponsor.getSponsorCode()+":"+sponsor.getSponsorName();
                            
                        }
                    }
                }
            sponsorHierarchyForm.setLookupResultsSequenceNumber(null);
        }
        
        if ((StringUtils.isNotBlank(sponsorHierarchyForm.getActionSelected()) && (sponsorHierarchyForm.getActionSelected().equals("maint") || sponsorHierarchyForm.getActionSelected().equals("new"))) || StringUtils.isNotBlank(sponsors)) {
            sponsorHierarchyForm.setSelectedSponsors(sponsors);
            return mapping.findForward(LOOKUP);            
        } else {
            return mapping.findForward(NEW);
        }
    }

    
}
