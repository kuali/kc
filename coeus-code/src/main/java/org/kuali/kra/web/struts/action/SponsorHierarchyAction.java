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
package org.kuali.kra.web.struts.action;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.common.framework.sponsor.Sponsor;
import org.kuali.coeus.common.permissions.impl.PermissionableKeys;
import org.kuali.coeus.sys.framework.auth.UnitAuthorizationService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.authorization.KraAuthorizationConstants;
import org.kuali.kra.bo.SponsorHierarchy;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.rules.SponsorHierarchyRule;
import org.kuali.kra.service.SponsorService;
import org.kuali.kra.web.struts.form.SponsorHierarchyForm;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.kns.lookup.LookupResultsService;
import org.kuali.rice.kns.web.struts.action.KualiAction;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.exception.AuthorizationException;
import org.kuali.rice.krad.util.GlobalVariables;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * This class takes care of the actions needed to manage sponsor hierarchy.
 */
public class SponsorHierarchyAction extends KualiAction {

    private static final String MAINT = "maint";
    private static final String NEW = "new";
    private static final String LOOKUP = "lookup";
    private static final String HIERARCHY_NAME = "hierarchyName";
    private static final String SELECTED_HIERARCHY_NAME = "selectedHierarchyName";
    private UnitAuthorizationService unitAuthorizationService;
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        if (this.getUnitAuthorizationService().hasPermission(GlobalVariables.getUserSession().getPrincipalId(), 
                KraAuthorizationConstants.KC_SYSTEM_NAMESPACE_CODE, PermissionConstants.SPONSOR_HIERARCHY_MODIFY)) {
            SponsorHierarchyForm sponsorHierarchyForm = (SponsorHierarchyForm) form;
            ActionForward forward = super.execute(mapping, form, request, response);
            if (StringUtils.isNotBlank(request.getParameter("mapKey"))) {
                sponsorHierarchyForm.getNewSponsors().get(0).clear();
            }
            //TODO: refactor this.  2 things very similar, should not call twice
            sponsorHierarchyForm.setTopSponsorHierarchies(KcServiceLocator.getService(SponsorService.class).getTopSponsorHierarchy());
            sponsorHierarchyForm.setHierarchyNameList(KcServiceLocator.getService(SponsorService.class).getTopSponsorHierarchyList());
            return forward;
        } else {
            throw new AuthorizationException(GlobalVariables.getUserSession().getPrincipalId(), "Open Sponsor Hierarchy", PermissionableKeys.SPONSOR_HIREARCHY_KEY);
        }

    }

    public ActionForward copy(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        if (this.getUnitAuthorizationService().hasPermission(GlobalVariables.getUserSession().getPrincipalId(), 
                KraAuthorizationConstants.KC_SYSTEM_NAMESPACE_CODE, PermissionConstants.SPONSOR_HIERARCHY_ADD)) {
            SponsorHierarchyForm sponsorHierarchyForm = (SponsorHierarchyForm) form;
            sponsorHierarchyForm.setHierarchyName(sponsorHierarchyForm.getSelectedSponsorHierarchy());
            return mapping.findForward("copy");
        } else {
            throw new AuthorizationException(GlobalVariables.getUserSession().getPrincipalId(), "Copy Sponsor Hierarchy", PermissionableKeys.SPONSOR_HIREARCHY_KEY);
        }
    }
    
    public ActionForward deleteSponsorHierarchy(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        if (this.getUnitAuthorizationService().hasPermission(GlobalVariables.getUserSession().getPrincipalId(), 
                KraAuthorizationConstants.KC_SYSTEM_NAMESPACE_CODE, PermissionConstants.SPONSOR_HIERARCHY_DELETE)) {
            KcServiceLocator.getService(SponsorService.class).deleteSponsorHierarchy((SponsorHierarchyForm)form);
            //((SponsorHierarchyForm)form).setTopSponsorHierarchies(KraServiceLocator.getService(SponsorService.class).getTopSponsorHierarchy());   
            ((SponsorHierarchyForm)form).setHierarchyNameList((KcServiceLocator.getService(SponsorService.class).getTopSponsorHierarchyList()));
            ((SponsorHierarchyForm)form).setMessage("Sponsor Hierarchy was deleted successfully");
            return mapping.findForward(Constants.MAPPING_BASIC);
        } else {
            throw new AuthorizationException(GlobalVariables.getUserSession().getPrincipalId(), "Delete Sponsor Hierarchy", PermissionableKeys.SPONSOR_HIREARCHY_KEY);
        }
    }
    
    public ActionForward copySponsorHierarchy(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        if (this.getUnitAuthorizationService().hasPermission(GlobalVariables.getUserSession().getPrincipalId(), 
                KraAuthorizationConstants.KC_SYSTEM_NAMESPACE_CODE, PermissionConstants.SPONSOR_HIERARCHY_ADD)) {
            boolean rulePassed = new SponsorHierarchyRule().newHierarchyNameRequired((SponsorHierarchyForm) form);
            if (rulePassed) {
                KcServiceLocator.getService(SponsorService.class).copySponsorHierarchy((SponsorHierarchyForm) form);
                ((SponsorHierarchyForm) form).setTopSponsorHierarchies(KcServiceLocator.getService(SponsorService.class).getTopSponsorHierarchy());
                ((SponsorHierarchyForm) form).setMessage("Sponsor Hierarchy was copied successfully");
            }
            return mapping.findForward(Constants.MAPPING_BASIC); 
        } else {
            throw new AuthorizationException(GlobalVariables.getUserSession().getPrincipalId(), "Copy Sponsor Hierarchy", PermissionableKeys.SPONSOR_HIREARCHY_KEY);
        }
    }
    
    public ActionForward cancelSponsorHierarchy(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        ActionForward  forward = new ActionForward("/portal.do??methodToCall=refresh&selectedTab=portalMaintenanceBody");  
        KcServiceLocator.getService(SponsorService.class).clearCurrentActions();
        forward.setRedirect(true);
        return forward;            
    }
    
    public ActionForward cancelSponsorHierarchyMaint(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        SponsorHierarchyForm sponsorHierarchyForm = (SponsorHierarchyForm)form;
        sponsorHierarchyForm.setSponsorCodeList(Constants.EMPTY_STRING);
        sponsorHierarchyForm.setNewHierarchyName(Constants.EMPTY_STRING);
        sponsorHierarchyForm.setSelectedSponsorHierarchy(Constants.EMPTY_STRING);
        KcServiceLocator.getService(SponsorService.class).clearCurrentActions();
        GlobalVariables.getUserSession().removeObject(SELECTED_HIERARCHY_NAME);
        GlobalVariables.getUserSession().removeObject(HIERARCHY_NAME);
        GlobalVariables.getUserSession().removeObject(sponsorHierarchyForm.getTimestamp());
        return mapping.findForward(Constants.MAPPING_BASIC);            
    }

    public ActionForward saveSponsorHierarchy(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        if (this.getUnitAuthorizationService().hasPermission(GlobalVariables.getUserSession().getPrincipalId(), 
                KraAuthorizationConstants.KC_SYSTEM_NAMESPACE_CODE, PermissionConstants.SPONSOR_HIERARCHY_MODIFY)) {
            
            KcServiceLocator.getService(SponsorService.class).executeActions();
            SponsorHierarchyForm sponsorHierarchyForm = (SponsorHierarchyForm)form;
     
            sponsorHierarchyForm.setActionSelected(MAINT);
            sponsorHierarchyForm.setSponsorCodeList(KcServiceLocator.getService(SponsorService.class).loadToSponsorHierachyMt(sponsorHierarchyForm.getSelectedSponsorHierarchy()));
            GlobalVariables.getUserSession().removeObject(SELECTED_HIERARCHY_NAME);
            GlobalVariables.getUserSession().removeObject(sponsorHierarchyForm.getTimestamp());
            GlobalVariables.getUserSession().removeObject("sponsorCodes");
            sponsorHierarchyForm.setTimestamp(KcServiceLocator.getService(DateTimeService.class).getCurrentTimestamp().toString());
            return mapping.findForward(MAINT);  
        } else {
            throw new AuthorizationException(GlobalVariables.getUserSession().getPrincipalId(), "Save Sponsor Hierarchy", PermissionableKeys.SPONSOR_HIREARCHY_KEY);
        }
    }

    public ActionForward newSponsorHierarchy(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        if (this.getUnitAuthorizationService().hasPermission(GlobalVariables.getUserSession().getPrincipalId(), 
                KraAuthorizationConstants.KC_SYSTEM_NAMESPACE_CODE, PermissionConstants.SPONSOR_HIERARCHY_ADD)) {
            boolean rulePassed = new SponsorHierarchyRule().newHierarchyNameRequired((SponsorHierarchyForm) form);
            if (rulePassed) {
                ((SponsorHierarchyForm) form).setNewSponsorHierarchy(new SponsorHierarchy());
                SponsorHierarchyForm sponsorHierarchyForm = (SponsorHierarchyForm) form;
                sponsorHierarchyForm.setHierarchyName(sponsorHierarchyForm.getNewHierarchyName());
                sponsorHierarchyForm.setSponsorCodeList(Constants.EMPTY_STRING);
                sponsorHierarchyForm.setTimestamp(KcServiceLocator.getService(DateTimeService.class).getCurrentTimestamp().toString());
                ((SponsorHierarchyForm) form).setActionSelected(NEW);
                KcServiceLocator.getService(SponsorService.class).clearCurrentActions();
                GlobalVariables.getUserSession().addObject(SELECTED_HIERARCHY_NAME, (Object) sponsorHierarchyForm.getSelectedSponsorHierarchy());
                GlobalVariables.getUserSession().addObject(HIERARCHY_NAME, (Object) sponsorHierarchyForm.getHierarchyName());
                return mapping.findForward(MAINT);            
            } else {
                return mapping.findForward(Constants.MAPPING_BASIC);
            }
        } else {
            throw new AuthorizationException(GlobalVariables.getUserSession().getPrincipalId(), "Add Sponsor Hierarchy", PermissionableKeys.SPONSOR_HIREARCHY_KEY);
        }
    }
    
    public ActionForward maintSponsorHierarchy(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        if (this.getUnitAuthorizationService().hasPermission(GlobalVariables.getUserSession().getPrincipalId(), 
                KraAuthorizationConstants.KC_SYSTEM_NAMESPACE_CODE, PermissionConstants.SPONSOR_HIERARCHY_MODIFY)) {
            ((SponsorHierarchyForm) form).setActionSelected(MAINT);
            SponsorHierarchyForm sponsorHierarchyForm = (SponsorHierarchyForm) form;
            sponsorHierarchyForm.setHierarchyName(sponsorHierarchyForm.getSelectedSponsorHierarchy());
            sponsorHierarchyForm.setSponsorCodeList(KcServiceLocator.getService(SponsorService.class).loadToSponsorHierachyMt(
                    sponsorHierarchyForm.getSelectedSponsorHierarchy()));
            GlobalVariables.getUserSession().removeObject(SELECTED_HIERARCHY_NAME);
            GlobalVariables.getUserSession().addObject(HIERARCHY_NAME, (Object) sponsorHierarchyForm.getHierarchyName());
            KcServiceLocator.getService(SponsorService.class).clearCurrentActions();
            sponsorHierarchyForm.setTimestamp(KcServiceLocator.getService(DateTimeService.class).getCurrentTimestamp().toString());
            return mapping.findForward(MAINT);
        } else {
            throw new AuthorizationException(GlobalVariables.getUserSession().getPrincipalId(), "Modify Sponsor Hierarchy", PermissionableKeys.SPONSOR_HIREARCHY_KEY);
        }
    }

    public ActionForward deleteSponsorHierarchyGroup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        SponsorHierarchyForm sponsorHierarchyForm = (SponsorHierarchyForm) form;
        int selectedSponsorHierarchy = getLineToDelete(request);
        SponsorHierarchy sh = sponsorHierarchyForm.getSponsorHierarchyList().get(selectedSponsorHierarchy);
        if (this.getUnitAuthorizationService().hasPermission(GlobalVariables.getUserSession().getPrincipalId(), 
                KraAuthorizationConstants.KC_SYSTEM_NAMESPACE_CODE, PermissionConstants.SPONSOR_HIERARCHY_DELETE)) {
            sponsorHierarchyForm.getSponsorHierarchyList().remove(selectedSponsorHierarchy);
            sponsorHierarchyForm.getNewSponsors().remove(getLineToDelete(request));
            return mapping.findForward(NEW);
        } else {
            throw new AuthorizationException(GlobalVariables.getUserSession().getPrincipalId(), "Delete Sponsor Hierarchy",
                    PermissionableKeys.SPONSOR_HIREARCHY_KEY);
        }
       
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
            
            LookupResultsService lookupService = KcServiceLocator.getService(LookupResultsService.class);
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
    
    /**
     * This method returns an instance of UnitAuthorizationService from the service locator.
     */
    public UnitAuthorizationService getUnitAuthorizationService() {
        if (unitAuthorizationService == null) {
            unitAuthorizationService = KcServiceLocator.getService(UnitAuthorizationService.class);
        }
        return unitAuthorizationService;
    }
    
}
