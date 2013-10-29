/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.kra.iacuc.procedures;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.IacucProtocolAction;
import org.kuali.kra.iacuc.IacucProtocolDocument;
import org.kuali.kra.iacuc.IacucProtocolDocumentRule;
import org.kuali.kra.iacuc.IacucProtocolForm;
import org.kuali.kra.iacuc.infrastructure.IacucConstants;
import org.kuali.kra.iacuc.personnel.IacucProtocolPerson;
import org.kuali.kra.iacuc.procedures.rule.AddProcedureLocationEvent;
import org.kuali.kra.iacuc.procedures.rule.AddProtocolStudyGroupEvent;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.protocol.personnel.ProtocolPersonBase;
import org.kuali.rice.krad.util.KRADConstants;

public class IacucProtocolProceduresAction extends IacucProtocolAction {

    private static final String BEAN_FIND_PARAM_START = "iacucProtocolStudyGroupBeans[";
    private static final String BEAN_STUDY_GROUP_FIND_PARAM_START = "iacucProtocolStudyGroups[";
    private static final String FIND_PARAM_END = "].";
    private static final String CONFIRM_DELETE_PROCEDURE_LOCATION_KEY = "confirmDeleteProcedureLocation";
    private static final String CONFIRM_DELETE_PROCEDURE_STUDY_GROUP_KEY = "confirmDeleteProcedureStudyGroup";

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = super.execute(mapping, form, request, response);
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        protocolForm.getIacucProtocolProceduresHelper().prepareView();
        return forward;
    }
    
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
        String procedureCategory = selectedIacucProtocolStudyGroupBean.getIacucProcedureCategory().getProcedureCategory();
        String procedure = selectedIacucProtocolStudyGroupBean.getIacucProcedure().getProcedureDescription();
        String speciesAndGroups = selectedStudyGroup.getIacucProtocolSpecies().getGroupAndSpecies();
        return confirm(buildParameterizedConfirmationQuestion(mapping, form, request, response, CONFIRM_DELETE_PROCEDURE_STUDY_GROUP_KEY,
                KeyConstants.QUESTION_PROCEDURE_STUDY_GROUP_DELETE_CONFIRMATION, new String[]{procedureCategory,procedure,speciesAndGroups}), CONFIRM_DELETE_PROCEDURE_STUDY_GROUP_KEY, "");
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
    
    public ActionForward addProcedureLocation(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        IacucProtocolStudyGroupLocation newIacucProtocolStudyGroupLocation = protocolForm.getIacucProtocolProceduresHelper().getNewIacucProtocolStudyGroupLocation();
        if (applyRules(new AddProcedureLocationEvent(protocolForm.getIacucProtocolDocument(), newIacucProtocolStudyGroupLocation))) {
            getIacucProtocolProcedureService().addProcedureLocation(newIacucProtocolStudyGroupLocation, getIacucProtocol(form));
            protocolForm.getIacucProtocolProceduresHelper().setNewIacucProtocolStudyGroupLocation(new IacucProtocolStudyGroupLocation());
        }
        return mapping.findForward(IacucConstants.PROCEDURE_LOCATION);
    }
    
    
    public ActionForward updateIacucProtocolStudyGroupCategory(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    @Override
    public void preSave(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        super.preSave(mapping, form, request, response);
    }
    
    public ActionForward deleteProcedureLocation(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        IacucProtocolStudyGroupLocation procedureLocation = getSelectedProcedureLocation(protocolForm, request);
        String locationName = procedureLocation.getIacucLocationName().getLocationName();
        return confirm(buildParameterizedConfirmationQuestion(mapping, form, request, response, CONFIRM_DELETE_PROCEDURE_LOCATION_KEY,
                KeyConstants.QUESTION_PROCEDURE_LOCATION_DELETE_CONFIRMATION, new String[]{locationName}), CONFIRM_DELETE_PROCEDURE_LOCATION_KEY, "");
    }

    public ActionForward confirmDeleteProcedureLocation(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
    throws Exception {
        Object question = request.getParameter(KRADConstants.QUESTION_INST_ATTRIBUTE_NAME);
        if (CONFIRM_DELETE_PROCEDURE_LOCATION_KEY.equals(question)) {
            IacucProtocolForm protocolForm = (IacucProtocolForm) form;
            IacucProtocolDocument document = protocolForm.getIacucProtocolDocument();
            IacucProtocolStudyGroupLocation procedureLocation = getSelectedProcedureLocation(protocolForm, request);
            document.getIacucProtocol().getIacucProtocolStudyGroupLocations().remove(procedureLocation);
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
        return (IacucProtocolProcedureService)KraServiceLocator.getService("iacucProtocolProcedureService");
    }

    protected IacucProtocolStudyGroup getSelectedStudyGroup(HttpServletRequest request, IacucProtocolStudyGroupBean selectedProtocolStudyGroupBean) {
        int selectedStudyGroupIndex = getSelectedBeanIndex(request, BEAN_STUDY_GROUP_FIND_PARAM_START, FIND_PARAM_END);
        return selectedProtocolStudyGroupBean.getIacucProtocolStudyGroups().get(selectedStudyGroupIndex);
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
        saveProtocolProcedures(getIacucProtocol(form));
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        protocolForm.getIacucProtocolProceduresHelper().setCurrentProcedureDetailTab(IacucProcedureNavigation.PROCEDURES);
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    public ActionForward updatePersonnel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        if(isSaveProcedureValid(form)) {
            IacucProtocol iacucProtocol = getIacucProtocol(form);
            saveProtocolProcedures(iacucProtocol);
            getIacucProtocolProcedureService().setTrainingDetails(iacucProtocol);
            IacucProtocolForm protocolForm = (IacucProtocolForm) form;
            protocolForm.getIacucProtocolProceduresHelper().setCurrentProcedureDetailTab(IacucProcedureNavigation.PERSONNEL);
            forward = mapping.findForward(IacucConstants.PROCEDURE_PERSONNEL);
        }
        return forward;
    }

    public ActionForward updateLocation(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        if(isSaveProcedureValid(form)) {
            saveProtocolProcedures(getIacucProtocol(form));
            IacucProtocolForm protocolForm = (IacucProtocolForm) form;
            protocolForm.getIacucProtocolProceduresHelper().setCurrentProcedureDetailTab(IacucProcedureNavigation.LOCATION);
            forward = mapping.findForward(IacucConstants.PROCEDURE_LOCATION);
        }
        return forward;
    }

    public ActionForward updateSummary(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        if(isSaveProcedureValid(form)) {
            saveProtocolProcedures(getIacucProtocol(form));
            IacucProtocolForm protocolForm = (IacucProtocolForm) form;
            getIacucProtocolProcedureService().setProcedureSummaryBySpeciesGroup(getIacucProtocol(form));
            protocolForm.getIacucProtocolProceduresHelper().setCurrentProcedureDetailTab(IacucProcedureNavigation.SUMMARY);
            protocolForm.getIacucProtocolProceduresHelper().setSummaryGroupedBySpecies(false);
            forward = mapping.findForward(IacucConstants.PROCEDURE_SUMMARY);
        }
        return forward;
    }

    @SuppressWarnings("deprecation")
    public ActionForward setEditLocationProcedures(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        protocolForm.getIacucProtocolProceduresHelper().setCurrentProcedureDetailTab(IacucProcedureNavigation.LOCATION);
        getBusinessObjectService().save(getIacucProtocol(form).getIacucProtocolStudyGroupLocations());
        return mapping.findForward(IacucConstants.PROCEDURE_LOCATION);
    }
    
    @SuppressWarnings("deprecation")
    public ActionForward setEditPersonProcedures(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        protocolForm.getIacucProtocolProceduresHelper().setCurrentProcedureDetailTab(IacucProcedureNavigation.PERSONNEL);
        getBusinessObjectService().save(getIacucProtocol(form).getProtocolPersons());
        return mapping.findForward(IacucConstants.PROCEDURE_PERSONNEL);
    }
    
    private boolean isSaveProcedureValid(ActionForm form) {
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        protocolForm.getIacucProtocolProceduresHelper().setCurrentProcedureDetailTab(IacucProcedureNavigation.PROCEDURES);
        IacucProtocolDocument protocolDocument = protocolForm.getIacucProtocolDocument();
        IacucProtocolDocumentRule protocolDocumentRule = new IacucProtocolDocumentRule();
        return protocolDocumentRule.isProtocolStudyGroupValid(protocolDocument);
    }
    @SuppressWarnings("deprecation")
    private void saveProtocolProcedures(IacucProtocol protocol) {
        getBusinessObjectService().save(protocol.getIacucProtocolStudyGroupSpeciesList());
        getBusinessObjectService().save(protocol.getIacucProtocolStudyGroups());
        getBusinessObjectService().save(protocol.getProtocolPersons());
        getBusinessObjectService().save(protocol.getIacucProtocolStudyGroupLocations());
        
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

}
