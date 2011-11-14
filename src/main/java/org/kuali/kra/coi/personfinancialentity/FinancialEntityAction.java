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
package org.kuali.kra.coi.personfinancialentity;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.bo.Sponsor;
import org.kuali.kra.coi.disclosure.CoiDisclosureService;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.rule.event.KraDocumentEventBaseExtension;
import org.kuali.kra.service.ResearchDocumentService;
import org.kuali.kra.service.SponsorService;
import org.kuali.rice.ken.util.NotificationConstants;
import org.kuali.rice.kew.util.KEWConstants;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DictionaryValidationService;
import org.kuali.rice.kns.service.SequenceAccessorService;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.KNSConstants;
import org.kuali.rice.kns.web.struts.action.KualiAction;

/**
 * 
 * This class is the struts action for financial entity maintenance
 */
public class FinancialEntityAction extends KualiAction {

    protected static final String INACTIVATE_ENTITY = "inactive";
    protected static final String ACTIVATE_ENTITY = "active";

    /**
     * 
     * This method is called when user open the financial entity maintenance page
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward management(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ((FinancialEntityForm) form).getFinancialEntityHelper().initiate();
        // if this starts from FE link.  clean any coiDocId resicure
        ((FinancialEntityForm) form).setCoiDocId(null);
        return mapping.findForward("management");
    }

    /**
     * 
     * This method is to forward to 'New Financial Entity' page
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward editNew(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ((FinancialEntityForm) form).getFinancialEntityHelper().initiate();
        return mapping.findForward("editNew");
    }

    /**
     * 
     * This method is to forward to 'My Financial Entities' page.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward editList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ((FinancialEntityForm) form).getFinancialEntityHelper().initiate();
        return mapping.findForward("editList");
    }

    /*
     * Utility method to save financial entity when 'submit' is clicked.  also, retrieve the new list of active/inactive financial entities
     */
    protected void saveFinancialEntity(ActionForm form, PersonFinIntDisclosure personFinIntDisclosure) {
        getBusinessObjectService().save(personFinIntDisclosure);
        ((FinancialEntityForm) form).getFinancialEntityHelper().setActiveFinancialEntities(getFinancialEntities(true));
        ((FinancialEntityForm) form).getFinancialEntityHelper().setInactiveFinancialEntities(getFinancialEntities(false));
        recordSubmitActionSuccess("Financial Entity save ");
        
    }
    
    /**
     * 
     * This method for 'close' button action.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward close(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward(KNSConstants.MAPPING_PORTAL);
    }

    
    /*
     * check if financial is valid for save
     */
    protected boolean isValidToSave(PersonFinIntDisclosure personFinIntDisclosure, String errorPath) {

        // TODO : may need to add save event rule
        GlobalVariables.getMessageMap().addToErrorPath(errorPath);
        //getDictionaryValidationService().validateBusinessObjectsRecursively(personFinIntDisclosure, 2);
        getDictionaryValidationService().validateBusinessObject(personFinIntDisclosure);
        GlobalVariables.getMessageMap().removeFromErrorPath(errorPath);
        GlobalVariables.getMessageMap().addToErrorPath(errorPath + ".finEntityContactInfos[0]");
        //getDictionaryValidationService().validateBusinessObjectsRecursively(personFinIntDisclosure, 2);
        getDictionaryValidationService().validateBusinessObject(personFinIntDisclosure.getFinEntityContactInfos().get(0));
        GlobalVariables.getMessageMap().removeFromErrorPath(errorPath + ".finEntityContactInfos[0]");

        boolean isValid = GlobalVariables.getMessageMap().hasNoErrors();
        isValid &= checkRule(new SaveFinancialEntityEvent(errorPath,personFinIntDisclosure));
        return isValid;

    }

    /**
     * 
     * This method is to process rule check.
     * @param event
     * @return
     */
    protected boolean checkRule(KraDocumentEventBaseExtension event) {
        return event.getRule().processRules(event);
    }

    private DictionaryValidationService getDictionaryValidationService() {
        return KraServiceLocator.getService(DictionaryValidationService.class);
    }

    protected BusinessObjectService getBusinessObjectService() {
        return KraServiceLocator.getService(BusinessObjectService.class);
    }

    protected FinancialEntityService getFinancialEntityService() {
        return KraServiceLocator.getService(FinancialEntityService.class);
    }
    protected CoiDisclosureService getCoiDisclosureService() {
        return KraServiceLocator.getService(CoiDisclosureService.class);
    }

    protected SequenceAccessorService getSequenceAccessorService() {
        return KraServiceLocator.getService(SequenceAccessorService.class);
    }

    /*
     * utility method to get active/inactive financial entities.
     */
    protected List<PersonFinIntDisclosure> getFinancialEntities(boolean active) {
        return getFinancialEntities(GlobalVariables.getUserSession().getPrincipalId(), active);
    }
    
    protected List<PersonFinIntDisclosure> getFinancialEntities(String personId, boolean active) {
        return getFinancialEntityService().getFinancialEntities(personId, active);
    }
    
    /**
     * 
     * This method is for header message displaying
     * @param submitAction
     */
    protected void recordSubmitActionSuccess(String submitAction) {
        GlobalVariables.getMessageList().add(KeyConstants.MESSAGE_FINANCIAL_ENTITY_ACTION_COMPLETE, submitAction);
    }

    /**
     * This is specifically for 'sponsor' lookup'  when return a value, the addresses fields will be overriden.
     * @see org.kuali.rice.kns.web.struts.action.KualiAction#refresh(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward refresh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // TODO following is to handle populate entity address info after sponsor code change
        // need further refactoring
        ActionForward forward = super.refresh(mapping, form, request, response);
        FinancialEntityHelper financialEntityHelper = ((FinancialEntityForm) form).getFinancialEntityHelper();
        String refreshCaller = request.getParameter("refreshCaller"); // sponsorLookupable
        String sponsorCode = request.getParameter("financialEntityHelper.newPersonFinancialEntity.sponsorCode"); // sponsorLookupable
        boolean isEdit = false;
        if (StringUtils.isNotBlank(refreshCaller) && StringUtils.isBlank(sponsorCode) && financialEntityHelper.getEditEntityIndex() >= 0) {
            if (StringUtils.equals(ACTIVATE_ENTITY, financialEntityHelper.getEditType())) {
                sponsorCode = request.getParameter("financialEntityHelper.activeFinancialEntities["+financialEntityHelper.getEditEntityIndex()+"].sponsorCode");
            } else {
                sponsorCode = request.getParameter("financialEntityHelper.inactiveFinancialEntities["+financialEntityHelper.getEditEntityIndex()+"].sponsorCode");
            }
            isEdit = true;
        }
        if (StringUtils.isNotBlank(refreshCaller) && StringUtils.isNotBlank(sponsorCode)) {
            Sponsor sponsor = KraServiceLocator.getService(SponsorService.class).getSponsor(sponsorCode);
            if (sponsor != null) {
                if (sponsor.getRolodex() == null) {
                    sponsor.refreshReferenceObject("rolodex");
                }
                
                FinancialEntityContactInfo contactInfo = financialEntityHelper.getNewPersonFinancialEntity().getFinEntityContactInfos().get(0);
                if (isEdit) {
                    if (StringUtils.equals(ACTIVATE_ENTITY, financialEntityHelper.getEditType())) {
                        financialEntityHelper.getActiveFinancialEntities().get(financialEntityHelper.getEditEntityIndex()).setEntityName(sponsor.getSponsorName());
                        contactInfo = financialEntityHelper.getActiveFinancialEntities().get(financialEntityHelper.getEditEntityIndex()).getFinEntityContactInfos().get(0);
                    } else {
                        financialEntityHelper.getInactiveFinancialEntities().get(financialEntityHelper.getEditEntityIndex()).setEntityName(sponsor.getSponsorName());
                        contactInfo = financialEntityHelper.getInactiveFinancialEntities().get(financialEntityHelper.getEditEntityIndex()).getFinEntityContactInfos().get(0);
                    }
                } else {
                    financialEntityHelper.getNewPersonFinancialEntity().setEntityName(sponsor.getSponsorName());
                }
                contactInfo.setAddressLine1(sponsor.getRolodex().getAddressLine1());
                contactInfo.setAddressLine2(sponsor.getRolodex().getAddressLine2());
                contactInfo.setAddressLine3(sponsor.getRolodex().getAddressLine3());
                contactInfo.setCity(sponsor.getRolodex().getCity());
                contactInfo.setState(sponsor.getRolodex().getState());
                contactInfo.setCountryCode(sponsor.getRolodex().getCountryCode());
                contactInfo.setPostalCode(sponsor.getRolodex().getPostalCode());
            }
        }
        return forward;
    }

    protected String buildForwardUrl(Long routeHeaderId) {
        // TODO : this is a copy from KraTransactionalDocumentActionBase.
        // investigate if it can be shared
        ResearchDocumentService researchDocumentService = KraServiceLocator.getService(ResearchDocumentService.class);
        String forward = researchDocumentService.getDocHandlerUrl(routeHeaderId);
 //       forward = forward.replaceFirst(DEFAULT_TAB, ALTERNATE_OPEN_TAB);
        if (forward.indexOf("?") == -1) {
            forward += "?";
        }
        else {
            forward += "&";
        }
        forward += KEWConstants.ROUTEHEADER_ID_PARAMETER + "=" + routeHeaderId;
        forward += "&" + KEWConstants.COMMAND_PARAMETER + "=" + NotificationConstants.NOTIFICATION_DETAIL_VIEWS.DOC_SEARCH_VIEW;
        if (GlobalVariables.getUserSession().isBackdoorInUse()) {
            forward += "&" + KEWConstants.BACKDOOR_ID_PARAMETER + "=" + GlobalVariables.getUserSession().getPrincipalName();
        }
        return forward;
    }

 
}
