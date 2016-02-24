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
package org.kuali.kra.iacuc.procedures;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.iacuc.*;
import org.kuali.kra.iacuc.infrastructure.IacucConstants;
import org.kuali.kra.iacuc.procedures.rule.AddProcedureLocationEvent;
import org.kuali.kra.iacuc.procedures.rule.AddProtocolStudyGroupEvent;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.krad.util.KRADConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IacucProtocolProceduresAction extends IacucProtocolAction {

    private static final String BEAN_FIND_PARAM_START = "iacucProtocolStudyGroupBeans[";
    private static final String BEAN_STUDY_GROUP_FIND_PARAM_START = "iacucProtocolStudyGroups[";
    private static final String BEAN_STUDY_GROUP_SPECIES_FIND_PARAM_START = "iacucProtocolSpeciesStudyGroups[";
    private static final String BEAN_PERSON_FIND_PARAM_START = "iacucProcedurePersonResponsibleList[";
    private static final String BEAN_LOCATION_FIND_PARAM_START = "iacucProcedureLocationResponsibleList[";
    private static final String FIND_PARAM_END = "].";
    private static final String CONFIRM_DELETE_PROCEDURE_LOCATION_KEY = "confirmDeleteProcedureLocation";
    private static final String CONFIRM_DELETE_PROCEDURE_GROUP_LOCATION_KEY = "confirmDeleteProcedureGroupLocation";
    private static final String CONFIRM_DELETE_PROCEDURE_STUDY_GROUP_KEY = "confirmDeleteProcedureStudyGroup";
    private static final String CONFIRM_DELETE_PROCEDURE_SPECIES_STUDY_GROUP_KEY = "confirmDeleteProcedureSpeciesStudyGroup";

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = super.execute(mapping, form, request, response);
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        protocolForm.getIacucProtocolProceduresHelper().prepareView();
        return forward;
    }
    
    @SuppressWarnings("deprecation")
    public ActionForward addProtocolStudyGroup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        int groupBeanIndex = getSelectedLine(request);
        IacucProtocolStudyGroupBean selectedIacucProtocolStudyGroupBean = getIacucProtocol(form).getIacucProtocolStudyGroupBeans().get(groupBeanIndex);
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        if (applyRules(new AddProtocolStudyGroupEvent(protocolForm.getIacucProtocolDocument(), selectedIacucProtocolStudyGroupBean, groupBeanIndex))) {
            getIacucProtocolProcedureService().addProtocolStudyGroup(selectedIacucProtocolStudyGroupBean, getIacucProtocol(form));
            selectedIacucProtocolStudyGroupBean.initializeStudyGroupItems() ;
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    public ActionForward deleteProtocolStudyGroup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        IacucProtocolStudyGroupBean selectedIacucProtocolStudyGroupBean = getSelectedProcedureBean(request, protocolForm.getIacucProtocolDocument());
        IacucProtocolStudyGroup selectedStudyGroup = getSelectedStudyGroup(request, selectedIacucProtocolStudyGroupBean);
        String speciesAndGroups = selectedStudyGroup.getIacucProtocolSpecies().getGroupAndSpecies();
        return deleteProcedureStudyGroup(mapping, protocolForm, request, response, speciesAndGroups, false);
    }

    public ActionForward deleteProtocolSpeciesStudyGroup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        IacucProtocolStudyGroupBean selectedIacucProtocolStudyGroupBean = getSelectedProcedureBean(request, protocolForm.getIacucProtocolDocument());
        IacucProtocolSpeciesStudyGroup selectedStudyGroup = getSelectedSpeciesStudyGroup(request, selectedIacucProtocolStudyGroupBean);
        String speciesAndGroups = selectedStudyGroup.getGroupAndSpecies();
        return deleteProcedureStudyGroup(mapping, protocolForm, request, response, speciesAndGroups, true);
    }

    private ActionForward deleteProcedureStudyGroup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, 
            String speciesAndGroups, boolean procedureGroupedBySpecies) throws Exception {
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        IacucProtocolStudyGroupBean selectedIacucProtocolStudyGroupBean = getSelectedProcedureBean(request, protocolForm.getIacucProtocolDocument());
        String procedureCategory = selectedIacucProtocolStudyGroupBean.getIacucProcedureCategory().getProcedureCategory();
        String procedure = selectedIacucProtocolStudyGroupBean.getIacucProcedure().getProcedureDescription();
        String confirmDeleteKey = procedureGroupedBySpecies ? CONFIRM_DELETE_PROCEDURE_SPECIES_STUDY_GROUP_KEY : CONFIRM_DELETE_PROCEDURE_STUDY_GROUP_KEY;
        return confirm(buildParameterizedConfirmationQuestion(mapping, form, request, response, confirmDeleteKey,
                KeyConstants.QUESTION_PROCEDURE_STUDY_GROUP_DELETE_CONFIRMATION, new String[]{procedureCategory,procedure,speciesAndGroups}), confirmDeleteKey, "");
    }
    
    public ActionForward confirmDeleteProcedureStudyGroup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
    throws Exception {
        Object question = request.getParameter(KRADConstants.QUESTION_INST_ATTRIBUTE_NAME);
        if (CONFIRM_DELETE_PROCEDURE_STUDY_GROUP_KEY.equals(question)) {
            IacucProtocolForm protocolForm = (IacucProtocolForm) form;
            IacucProtocolStudyGroupBean selectedIacucProtocolStudyGroupBean = getSelectedProcedureBean(request, protocolForm.getIacucProtocolDocument());
            IacucProtocolStudyGroup selectedStudyGroup = getSelectedStudyGroup(request, selectedIacucProtocolStudyGroupBean);
            getIacucProtocolProcedureService().deleteProtocolStudyGroup(selectedIacucProtocolStudyGroupBean, selectedStudyGroup, getIacucProtocol(form));
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public ActionForward confirmDeleteProcedureSpeciesStudyGroup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
    throws Exception {
        Object question = request.getParameter(KRADConstants.QUESTION_INST_ATTRIBUTE_NAME);
        if (CONFIRM_DELETE_PROCEDURE_SPECIES_STUDY_GROUP_KEY.equals(question)) {
            IacucProtocolForm protocolForm = (IacucProtocolForm) form;
            IacucProtocolStudyGroupBean selectedIacucProtocolStudyGroupBean = getSelectedProcedureBean(request, protocolForm.getIacucProtocolDocument());
            IacucProtocolSpeciesStudyGroup selectedStudyGroup = getSelectedSpeciesStudyGroup(request, selectedIacucProtocolStudyGroupBean);
            getIacucProtocolProcedureService().deleteProtocolStudyGroup(selectedIacucProtocolStudyGroupBean, selectedStudyGroup);
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    public ActionForward addProcedureLocation(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        IacucProtocolStudyGroupLocation newIacucProtocolStudyGroupLocation = protocolForm.getIacucProtocolProceduresHelper().getNewIacucProtocolStudyGroupLocation();
        if (applyRules(new AddProcedureLocationEvent(protocolForm.getIacucProtocolDocument(), newIacucProtocolStudyGroupLocation))) {
            getIacucProtocolProcedureService().addProcedureLocation(newIacucProtocolStudyGroupLocation, getIacucProtocol(form));
            protocolForm.getIacucProtocolProceduresHelper().setNewIacucProtocolStudyGroupLocation(new IacucProtocolStudyGroupLocation());
        }
        return mapping.findForward(IacucConstants.PROCEDURE_LOCATION);
    }
    
    public ActionForward addProcedureGroupLocation(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        IacucProtocolStudyGroupBean selectedIacucProtocolStudyGroupBean = getSelectedProcedureBean(request, protocolForm.getIacucProtocolDocument());
        IacucProtocolStudyGroup selectedStudyGroup = getSelectedStudyGroup(request, selectedIacucProtocolStudyGroupBean);
        IacucProtocolStudyGroupLocation newStudyGroupLocation =  selectedStudyGroup.getNewIacucProtocolStudyGroupLocation();
        if (applyRules(new AddProcedureLocationEvent(protocolForm.getIacucProtocolDocument(), newStudyGroupLocation))) {
            getIacucProtocolProcedureService().addProcedureGroupLocation(newStudyGroupLocation, selectedStudyGroup, getIacucProtocol(protocolForm));
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    
    public ActionForward updateIacucProtocolStudyGroupCategory(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    @Override
    public void preSave(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        super.preSave(mapping, form, request, response);
        getIacucProtocolProcedureService().synchronizeProtocolStudyGroups(getIacucProtocol(form));
    }
    
    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception {
        super.save(mapping, form, request, response);
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        return mapping.findForward(getProcedureActionForward(protocolForm));
    }
    
    public ActionForward deleteProcedureLocation(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        IacucProtocolStudyGroupLocation procedureLocation = getSelectedProcedureLocation(protocolForm, request);
        String locationName = procedureLocation.getIacucLocationName().getLocationName();
        return confirm(buildParameterizedConfirmationQuestion(mapping, form, request, response, CONFIRM_DELETE_PROCEDURE_LOCATION_KEY,
                KeyConstants.QUESTION_PROCEDURE_LOCATION_DELETE_CONFIRMATION, new String[]{locationName}), CONFIRM_DELETE_PROCEDURE_LOCATION_KEY, "");
    }

    public ActionForward deleteProcedureGroupPersonResponsible(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        IacucProtocolStudyGroupBean selectedIacucProtocolStudyGroupBean = getSelectedProcedureBean(request, protocolForm.getIacucProtocolDocument());
        IacucProtocolStudyGroup selectedStudyGroup = getSelectedStudyGroup(request, selectedIacucProtocolStudyGroupBean);
        int selectedPersonIndex = getSelectedBeanIndex(request, BEAN_PERSON_FIND_PARAM_START, FIND_PARAM_END);
        IacucProcedurePersonResponsible selectedPersonResponsible =  selectedStudyGroup.getIacucProcedurePersonResponsibleList().get(selectedPersonIndex);
        getIacucProtocolProcedureService().deleteProcedureGroupPersonResponsible(selectedStudyGroup, selectedPersonResponsible, getIacucProtocol(form));
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public ActionForward deleteProcedureGroupLocation(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        IacucProtocolStudyGroupBean selectedIacucProtocolStudyGroupBean = getSelectedProcedureBean(request, protocolForm.getIacucProtocolDocument());
        IacucProtocolStudyGroup selectedStudyGroup = getSelectedStudyGroup(request, selectedIacucProtocolStudyGroupBean);
        int selectedLocationIndex = getSelectedBeanIndex(request, BEAN_LOCATION_FIND_PARAM_START, FIND_PARAM_END);
        IacucProtocolStudyGroupLocation selectedStudyGroupLocation =  selectedStudyGroup.getIacucProcedureLocationResponsibleList().get(selectedLocationIndex);
        String groupName = selectedStudyGroup.getIacucProtocolSpecies().getGroupAndSpecies();
        String locationName = selectedStudyGroupLocation.getIacucLocationName().getLocationName();
        return confirm(buildParameterizedConfirmationQuestion(mapping, form, request, response, CONFIRM_DELETE_PROCEDURE_GROUP_LOCATION_KEY,
                KeyConstants.QUESTION_PROCEDURE_GROUP_LOCATION_DELETE_CONFIRMATION, new String[]{groupName, locationName}), CONFIRM_DELETE_PROCEDURE_GROUP_LOCATION_KEY, "");
    }

    public ActionForward confirmDeleteProcedureGroupLocation(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
    throws Exception {
        Object question = request.getParameter(KRADConstants.QUESTION_INST_ATTRIBUTE_NAME);
        if (CONFIRM_DELETE_PROCEDURE_GROUP_LOCATION_KEY.equals(question)) {
            IacucProtocolForm protocolForm = (IacucProtocolForm) form;
            IacucProtocolStudyGroupBean selectedIacucProtocolStudyGroupBean = getSelectedProcedureBean(request, protocolForm.getIacucProtocolDocument());
            IacucProtocolStudyGroup selectedStudyGroup = getSelectedStudyGroup(request, selectedIacucProtocolStudyGroupBean);
            int selectedLocationIndex = getSelectedBeanIndex(request, BEAN_LOCATION_FIND_PARAM_START, FIND_PARAM_END);
            IacucProtocolStudyGroupLocation selectedStudyGroupLocation =  selectedStudyGroup.getIacucProcedureLocationResponsibleList().get(selectedLocationIndex);
            getIacucProtocolProcedureService().deleteProcedureGroupLocation(selectedStudyGroup, selectedStudyGroupLocation, getIacucProtocol(form));
        }
        return mapping.findForward(IacucConstants.PROCEDURE_LOCATION);
    }
    
    public ActionForward confirmDeleteProcedureLocation(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
    throws Exception {
        Object question = request.getParameter(KRADConstants.QUESTION_INST_ATTRIBUTE_NAME);
        if (CONFIRM_DELETE_PROCEDURE_LOCATION_KEY.equals(question)) {
            IacucProtocolForm protocolForm = (IacucProtocolForm) form;
            IacucProtocolStudyGroupLocation procedureLocation = getSelectedProcedureLocation(protocolForm, request);
            getIacucProtocolProcedureService().deleteProcedureLocation(procedureLocation, getIacucProtocol(protocolForm));
        }
        return mapping.findForward(IacucConstants.PROCEDURE_LOCATION);
    }
    
    /**
     * This method is to get the selected procedure location.
     * @param protocolForm
     * @param request
     * @return
     */
    @SuppressWarnings("deprecation")
    private IacucProtocolStudyGroupLocation getSelectedProcedureLocation(IacucProtocolForm protocolForm, HttpServletRequest request) {
        IacucProtocolDocument document = protocolForm.getIacucProtocolDocument();
        IacucProtocolStudyGroupLocation procedureLocation = document.getIacucProtocol().getIacucProtocolStudyGroupLocations().get(getLineToDelete(request));
        return procedureLocation;
    }
    
    protected IacucProtocolProcedureService getIacucProtocolProcedureService() {
        return (IacucProtocolProcedureService) KcServiceLocator.getService("iacucProtocolProcedureService");
    }

    protected IacucProtocolStudyGroup getSelectedStudyGroup(HttpServletRequest request, IacucProtocolStudyGroupBean selectedProtocolStudyGroupBean) {
        int selectedStudyGroupIndex = getSelectedBeanIndex(request, BEAN_STUDY_GROUP_FIND_PARAM_START, FIND_PARAM_END);
        return selectedProtocolStudyGroupBean.getIacucProtocolStudyGroups().get(selectedStudyGroupIndex);
    }

    protected IacucProtocolSpeciesStudyGroup getSelectedSpeciesStudyGroup(HttpServletRequest request, IacucProtocolStudyGroupBean selectedProtocolStudyGroupBean) {
        int selectedStudyGroupIndex = getSelectedBeanIndex(request, BEAN_STUDY_GROUP_SPECIES_FIND_PARAM_START, FIND_PARAM_END);
        return selectedProtocolStudyGroupBean.getIacucProtocolSpeciesStudyGroups().get(selectedStudyGroupIndex);
    }
    
    /**
     * This method is to get the selected procedure bean.
     * @param request
     * @param document
     * @return
     */
    protected IacucProtocolStudyGroupBean getSelectedProcedureBean(HttpServletRequest request, IacucProtocolDocument document) {
        int selectedBeanIndex = getSelectedBeanIndex(request, BEAN_FIND_PARAM_START, FIND_PARAM_END);
        return document.getIacucProtocol().getIacucProtocolStudyGroupBeans().get(selectedBeanIndex);
    }

    protected int getSelectedBeanIndex(HttpServletRequest request, String beanNameOpen, String beanNameClose) {
        int selectedBeanIndex = -1;
        String parameterName = (String) request.getAttribute(KRADConstants.METHOD_TO_CALL_ATTRIBUTE);
        if (StringUtils.isNotBlank(parameterName)) {
            selectedBeanIndex = Integer.parseInt(StringUtils.substringBetween(parameterName, beanNameOpen, beanNameClose));
        }
        return selectedBeanIndex;
    }
    
    protected IacucProtocol getIacucProtocol(ActionForm form) {
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        return protocolForm.getIacucProtocolDocument().getIacucProtocol();
    }

    public ActionForward updateProcedures(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        if(protocolForm.getIacucProtocolProceduresHelper().isModifyProtocolProcedures()) {
            super.save(mapping, form, request, response);
        }
        protocolForm.getIacucProtocolProceduresHelper().setCurrentProcedureDetailTab(IacucProcedureNavigation.PROCEDURES);
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    public ActionForward updatePersonnel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        ActionForward forward = mapping.findForward(IacucConstants.PROCEDURE_PERSONNEL);
        if(protocolForm.getIacucProtocolProceduresHelper().isModifyProtocolProcedures()) {
            if(isSaveProcedureValid(form)) {
                super.save(mapping, form, request, response);
            }else {
                return mapping.findForward(Constants.MAPPING_BASIC);
            }
        }
        getIacucProtocolProcedureService().populateIacucSpeciesPersonProcedures(getIacucProtocol(protocolForm));
        protocolForm.getIacucProtocolProceduresHelper().setCurrentProcedureDetailTab(IacucProcedureNavigation.PERSONNEL);
        return forward;
    }

    public ActionForward updateLocation(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        ActionForward forward = mapping.findForward(IacucConstants.PROCEDURE_LOCATION);
        if(protocolForm.getIacucProtocolProceduresHelper().isModifyProtocolProcedures()) {
            if(isSaveProcedureValid(form)) {
                super.save(mapping, form, request, response);
            }else {
                return mapping.findForward(Constants.MAPPING_BASIC);
            }
        }
        getIacucProtocolProcedureService().populateIacucSpeciesLocationProcedures(getIacucProtocol(protocolForm));
        protocolForm.getIacucProtocolProceduresHelper().setCurrentProcedureDetailTab(IacucProcedureNavigation.LOCATION);
        return forward;
    }

    public ActionForward updateSummary(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        ActionForward forward = mapping.findForward(IacucConstants.PROCEDURE_SUMMARY);
        if(protocolForm.getIacucProtocolProceduresHelper().isModifyProtocolProcedures()) {
            if(isSaveProcedureValid(form)) {
                super.save(mapping, form, request, response);
            }else {
                return mapping.findForward(Constants.MAPPING_BASIC);
            }
        }
        getIacucProtocolProcedureService().setProcedureSummaryBySpeciesGroup(getIacucProtocol(form));
        protocolForm.getIacucProtocolProceduresHelper().setCurrentProcedureDetailTab(IacucProcedureNavigation.SUMMARY);
        protocolForm.getIacucProtocolProceduresHelper().setSummaryGroupedBySpecies(false);
        return forward;
    }

    public ActionForward setEditLocationProcedures(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        IacucProtocol iacucProtocol = getIacucProtocol(protocolForm);
        protocolForm.getIacucProtocolProceduresHelper().setCurrentProcedureDetailTab(IacucProcedureNavigation.LOCATION);
        getIacucProtocolProcedureService().addLocationResponsibleProcedures(iacucProtocol);
        super.save(mapping, form, request, response);
        return mapping.findForward(IacucConstants.PROCEDURE_LOCATION);
    }
    
    public ActionForward setEditPersonProcedures(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        IacucProtocol iacucProtocol = getIacucProtocol(protocolForm);
        protocolForm.getIacucProtocolProceduresHelper().setCurrentProcedureDetailTab(IacucProcedureNavigation.PERSONNEL);
        getIacucProtocolProcedureService().addPersonResponsibleProcedures(iacucProtocol);
        super.save(mapping, form, request, response);
        return mapping.findForward(IacucConstants.PROCEDURE_PERSONNEL);
    }
    
    private boolean isSaveProcedureValid(ActionForm form) {
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        protocolForm.getIacucProtocolProceduresHelper().setCurrentProcedureDetailTab(IacucProcedureNavigation.PROCEDURES);
        IacucProtocolDocument protocolDocument = protocolForm.getIacucProtocolDocument();
        IacucProtocolDocumentRule protocolDocumentRule = new IacucProtocolDocumentRule();
        return protocolDocumentRule.isProtocolStudyGroupValid(protocolDocument);
    }
    
    public ActionForward summaryBySpecies(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        getIacucProtocolProcedureService().setProcedureSummaryGroupedBySpecies(getIacucProtocol(form));
        protocolForm.getIacucProtocolProceduresHelper().setCurrentProcedureDetailTab(IacucProcedureNavigation.SUMMARY);
        protocolForm.getIacucProtocolProceduresHelper().setSummaryGroupedBySpecies(true);
        return mapping.findForward(IacucConstants.PROCEDURE_SUMMARY);
    }
    
    public ActionForward summaryByGroup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        getIacucProtocolProcedureService().setProcedureSummaryBySpeciesGroup(getIacucProtocol(form));
        protocolForm.getIacucProtocolProceduresHelper().setCurrentProcedureDetailTab(IacucProcedureNavigation.SUMMARY);
        protocolForm.getIacucProtocolProceduresHelper().setSummaryGroupedBySpecies(false);
        return mapping.findForward(IacucConstants.PROCEDURE_SUMMARY);
    }

    @Override
    public ActionForward reload(ActionMapping mapping, ActionForm form, 
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        ActionForward actionForward = super.reload(mapping, form, request, response);
        protocolForm.getIacucProtocolProceduresHelper().setCurrentProcedureDetailTab(IacucProcedureNavigation.PROCEDURES);
        return actionForward;        
    }
    
    private String getProcedureActionForward(IacucProtocolForm protocolForm) {
        IacucProcedureNavigation currentProcedureTab = protocolForm.getIacucProtocolProceduresHelper().getCurrentProcedureDetailTab();
        String procedureActionForwardName = Constants.MAPPING_BASIC;
        switch(currentProcedureTab) {
            case PERSONNEL :
                procedureActionForwardName = IacucConstants.PROCEDURE_PERSONNEL;
                break;
            case LOCATION :
                procedureActionForwardName = IacucConstants.PROCEDURE_LOCATION;
                break;
            case SUMMARY :
                procedureActionForwardName = IacucConstants.PROCEDURE_SUMMARY;
                break;
        }
        return procedureActionForwardName;
    }

}
