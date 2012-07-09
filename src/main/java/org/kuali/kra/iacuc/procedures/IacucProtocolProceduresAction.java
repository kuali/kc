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
package org.kuali.kra.iacuc.procedures;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.IacucProtocolAction;
import org.kuali.kra.iacuc.IacucProtocolDocument;
import org.kuali.kra.iacuc.IacucProtocolForm;
import org.kuali.kra.iacuc.procedures.rule.AddProcedureLocationEvent;
import org.kuali.kra.iacuc.procedures.rule.AddProcedurePersonResponsibleEvent;
import org.kuali.kra.iacuc.procedures.rule.AddProtocolStudyGroupEvent;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.krad.util.KRADConstants;

public class IacucProtocolProceduresAction extends IacucProtocolAction {

    private static final String BEAN_FIND_PARAM_START = "iacucProtocolStudyGroupBeans[";
    private static final String BEAN_DETAIL_FIND_PARAM_START = "iacucProtocolStudyGroupDetailBeans[";
    private static final String BEAN_PERSON_FIND_PARAM_START = "iacucProcedurePersonsResponsible[";
    private static final String BEAN_LOCATION_FIND_PARAM_START = "iacucProtocolStudyGroupLocations[";
    private static final String FIND_PARAM_END = "].";

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = super.execute(mapping, form, request, response);
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        protocolForm.getIacucProtocolProceduresHelper().prepareView();
        return forward;
    }
    
    public ActionForward addProtocolStudyGroup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        int groupBeanIndex = getSelectedLine(request);
        printStudyGroups(getIacucProtocol(form).getIacucProtocolStudyGroupBeans(), "---------- printing before add-------------");        
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
        IacucProtocolStudyGroupDetailBean selectedProcedureDetailBean = getSelectedProcedureDetailBean(request, protocolForm.getIacucProtocolDocument());
        getIacucProtocolProcedureService().deleteProtocolStudyGroup(selectedIacucProtocolStudyGroupBean, selectedProcedureDetailBean, getIacucProtocol(form));
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public ActionForward reload(ActionMapping mapping, ActionForm form, 
            HttpServletRequest request, HttpServletResponse response) throws Exception { 
        super.reload(mapping, form, request, response);
        IacucProtocol iacucProtocol = getIacucProtocol(form);
        //iacucProtocol.setIacucProtocolStudyGroupBeans(getIacucProtocolProcedureService().getRevisedStudyGroupBeans(iacucProtocol));
        iacucProtocol.setIacucProtocolStudyGroupBeans(getIacucProtocolProcedureService().getRevisedStudyGroupBeans(iacucProtocol));
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public ActionForward addProcedurePersonResponsible(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        IacucProtocolStudyGroupDetailBean procedureDetailBean = getSelectedProcedureDetailBean(request, protocolForm.getIacucProtocolDocument());
        IacucProtocolStudyGroupBean procedureBean = getSelectedProcedureBean(request, protocolForm.getIacucProtocolDocument());
        int selectedBeanIndex = getSelectedBeanIndex(request, BEAN_FIND_PARAM_START, FIND_PARAM_END);
        int selectedBeanDetailIndex = getSelectedBeanIndex(request,BEAN_DETAIL_FIND_PARAM_START, FIND_PARAM_END);
        if (applyRules(new AddProcedurePersonResponsibleEvent(protocolForm.getIacucProtocolDocument(), procedureDetailBean, selectedBeanIndex, selectedBeanDetailIndex))) {
            IacucProcedurePersonResponsible newIacucProcedurePersonResponsible = procedureDetailBean.getNewIacucProcedurePersonResponsible();
            getIacucProtocolProcedureService().addProcedurePersonResponsible(newIacucProcedurePersonResponsible, procedureDetailBean, procedureBean);
            procedureDetailBean.setNewIacucProcedurePersonResponsible(new IacucProcedurePersonResponsible());
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public ActionForward addProcedureLocation(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        IacucProtocolStudyGroupDetailBean procedureDetailBean = getSelectedProcedureDetailBean(request, protocolForm.getIacucProtocolDocument());
        int selectedBeanIndex = getSelectedBeanIndex(request, BEAN_FIND_PARAM_START, FIND_PARAM_END);
        int selectedBeanDetailIndex = getSelectedBeanIndex(request,BEAN_DETAIL_FIND_PARAM_START, FIND_PARAM_END);
        if (applyRules(new AddProcedureLocationEvent(protocolForm.getIacucProtocolDocument(), procedureDetailBean, selectedBeanIndex, selectedBeanDetailIndex))) {
            IacucProtocolStudyGroupLocation newIacucProtocolStudyGroupLocation = procedureDetailBean.getNewIacucProtocolStudyGroupLocation();
            getIacucProtocolProcedureService().addProcedureLocation(newIacucProtocolStudyGroupLocation, procedureDetailBean, getIacucProtocol(form));
            procedureDetailBean.setNewIacucProtocolStudyGroupLocation(new IacucProtocolStudyGroupLocation());
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    
    public ActionForward updateIacucProtocolStudyGroupCategory(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    @Override
    public void preSave(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        super.preSave(mapping, form, request, response);
        getIacucProtocolProcedureService().updateIacucProtocolStudyGroup(getIacucProtocol(form));
    }
    
    public ActionForward deleteProcedurePersonResponsible(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        IacucProtocolStudyGroupDetailBean selectedProcedureDetailBean = getSelectedProcedureDetailBean(request, protocolForm.getIacucProtocolDocument());
        IacucProcedurePersonResponsible selectedPersonResponsible = getSelectedProcedurePerson(request, protocolForm.getIacucProtocolDocument());
        getIacucProtocolProcedureService().deleteProcedurePersonResponsible(selectedProcedureDetailBean, selectedPersonResponsible, getIacucProtocol(form));
        //selectedProcedureDetailBean.getIacucProcedurePersonsResponsible().remove(getSelectedProcedurePersonIndex(request));
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    public ActionForward deleteProcedureLocation(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        IacucProtocolStudyGroupDetailBean selectedProcedureDetailBean = getSelectedProcedureDetailBean(request, protocolForm.getIacucProtocolDocument());
        IacucProtocolStudyGroupLocation selectedStudyGroupLocation = getSelectedProcedureLocation(request, protocolForm.getIacucProtocolDocument());
        getIacucProtocolProcedureService().deleteStudyGroupLocation(selectedProcedureDetailBean, selectedStudyGroupLocation, getIacucProtocol(form));
        //selectedProcedureDetailBean.getIacucProcedurePersonsResponsible().remove(getSelectedProcedurePersonIndex(request));
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    
    protected IacucProtocolProcedureService getIacucProtocolProcedureService() {
        return (IacucProtocolProcedureService)KraServiceLocator.getService("iacucProtocolProcedureService");
    }

    /**
     * This method is to get the selected procedure detail bean.
     * We have all collection and grouping information in the detail bean.
     * @param request
     * @param document
     * @return
     */
    protected IacucProtocolStudyGroupDetailBean getSelectedProcedureDetailBean(HttpServletRequest request, IacucProtocolDocument document) {
        int selectedBeanIndex = getSelectedBeanIndex(request, BEAN_FIND_PARAM_START, FIND_PARAM_END);
        int selectedBeanDetailIndex = getSelectedBeanIndex(request,BEAN_DETAIL_FIND_PARAM_START, FIND_PARAM_END);
        return document.getIacucProtocol().getIacucProtocolStudyGroupBeans().get(selectedBeanIndex).getIacucProtocolStudyGroupDetailBeans().get(selectedBeanDetailIndex);
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
    
    protected int getSelectedProcedurePersonIndex(HttpServletRequest request) {
        return getSelectedBeanIndex(request, BEAN_PERSON_FIND_PARAM_START, FIND_PARAM_END);
    }

    /**
     * This method is to get the selected person in each section.
     * Based on the index set in the tag, we get appropriate person selected by user.
     * say for example this will help us to remove the person information from the collection.
     * @param request
     * @param document
     * @return
     */
    protected IacucProcedurePersonResponsible getSelectedProcedurePerson(HttpServletRequest request, IacucProtocolDocument document) {
        IacucProtocolStudyGroupDetailBean procedureDetailBean = getSelectedProcedureDetailBean(request, document);
        int selectedPersonIndex = getSelectedBeanIndex(request, BEAN_PERSON_FIND_PARAM_START, FIND_PARAM_END);
        return procedureDetailBean.getIacucProcedurePersonsResponsible().get(selectedPersonIndex);
    }
    
    /**
     * This method is to get the selected location in each section.
     * Based on the index set in the tag, we get appropriate study group location selected by user.
     * say for example this will help us to remove the location information from the collection.
     * @param request
     * @param document
     * @return
     */
    protected IacucProtocolStudyGroupLocation getSelectedProcedureLocation(HttpServletRequest request, IacucProtocolDocument document) {
        IacucProtocolStudyGroupDetailBean procedureDetailBean = getSelectedProcedureDetailBean(request, document);
        int selectedLocationIndex = getSelectedBeanIndex(request, BEAN_LOCATION_FIND_PARAM_START, FIND_PARAM_END);
        return procedureDetailBean.getIacucProtocolStudyGroupLocations().get(selectedLocationIndex);
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


    private void printStudyGroups(List<IacucProtocolStudyGroupBean> iacucProtocolStudyGroupBeans, String message) {
        for(IacucProtocolStudyGroupBean studyGroupBean : iacucProtocolStudyGroupBeans) {
            printStudyGroup(studyGroupBean, message);
        }
    }
    
    private void printStudyGroup(IacucProtocolStudyGroupBean iacucProtocolStudyGroupBean, String message) {
        System.out.println("printing group bean ==========> " + message);
        printForStudyGroupBean(iacucProtocolStudyGroupBean);
        for(IacucProtocolStudyGroupDetailBean studyGroupDetailBean : iacucProtocolStudyGroupBean.getIacucProtocolStudyGroupDetailBeans()) {
            printForStudyGroupDetailBean(studyGroupDetailBean);
            for(IacucProtocolStudyGroup studyGroup : studyGroupDetailBean.getIacucProtocolStudyGroups()) {
                printForStudyGroup(studyGroup);
            }
        }
        System.out.println("-------------------------------PRINT Complete-------------------------------------------\r\n");
    }
    
    private void printForStudyGroupBean(IacucProtocolStudyGroupBean studyGroupBean) {
        System.out.println("study grp hdr id ==========> " + studyGroupBean.getIacucProtocolStudyGroupHeaderId());
        System.out.println("protocol id ==========> " + studyGroupBean.getProtocolId());
        System.out.println("protocol num ==========> " + studyGroupBean.getProtocolNumber());
        System.out.println("seq num ==========> " + studyGroupBean.getSequenceNumber());
        System.out.println("proc cat code ==========> " + studyGroupBean.getProcedureCategoryCode());
        System.out.println("proc code ==========> " + studyGroupBean.getProcedureCode());
        System.out.println("species and grps ==========> " + studyGroupBean.getProtocolSpeciesAndGroups());
        System.out.println("persons responsible ==========> " + studyGroupBean.getProtocolPersonsResponsible());
    }

    private void printForStudyGroupDetailBean(IacucProtocolStudyGroupDetailBean studyGroupDetailBean) {
        System.out.println("study grp dtl id ==========> " + studyGroupDetailBean.getIacucProtocolStudyGroupDetailId());
        System.out.println("study grp hdr id ==========> " + studyGroupDetailBean.getIacucProtocolStudyGroupHeaderId());
        System.out.println("max pain category ==========> " + studyGroupDetailBean.getMaxPainCategory());
        System.out.println("max pain category code ==========> " + studyGroupDetailBean.getMaxPainCategoryCode());
        System.out.println("species code ==========> " + studyGroupDetailBean.getSpeciesCode());
        System.out.println("total species ==========> " + studyGroupDetailBean.getTotalSpeciesCount());
    }
    
    private void printForStudyGroup(IacucProtocolStudyGroup studyGroup) {
        System.out.println("pk study grp id ==========> " + studyGroup.getIacucProtocolStudyGroupId());
        System.out.println("study grp id ==========> " + studyGroup.getStudyGroupId());
        System.out.println("prot species id ==========> " + studyGroup.getIacucProtocolSpeciesId());
    }

}
