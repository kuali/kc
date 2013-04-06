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
package org.kuali.kra.committee.web.struts.action;

import java.util.List;

import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.rule.event.DeleteCommitteeScheduleEvent;
import org.kuali.kra.committee.service.CommitteeScheduleService;
import org.kuali.kra.common.committee.bo.CommitteeBase;
import org.kuali.kra.common.committee.bo.CommitteeScheduleBase;
import org.kuali.kra.common.committee.document.authorization.CommitteeTaskBase;
import org.kuali.kra.common.committee.rule.event.DeleteCommitteeScheduleEventBase;
import org.kuali.kra.common.committee.service.CommitteeScheduleServiceBase;
import org.kuali.kra.common.committee.web.struts.action.CommitteeScheduleActionBase;
import org.kuali.kra.common.committee.web.struts.form.schedule.ScheduleData;
import org.kuali.kra.infrastructure.TaskGroupName;
import org.kuali.rice.krad.document.Document;

public class CommitteeScheduleAction extends CommitteeScheduleActionBase {
    
    @Override
    protected CommitteeTaskBase getNewCommitteeTaskInstanceHook(String taskName, CommitteeBase committee) {
        // creating an anonymous class to avoid task hierarchy issues
        return new CommitteeTaskBase<Committee>(TaskGroupName.COMMITTEE, taskName, (Committee) committee) {};
    }

    @Override
    protected String getCommitteeDocumentTypeSimpleNameHook() {
        return "CommitteeDocument";
    }

    @Override
    protected DeleteCommitteeScheduleEventBase getNewDeleteCommitteeScheduleEventInstanceHook(String errorPathPrefix,
            Document document, ScheduleData scheduleData, List<CommitteeScheduleBase> committeeSchedules, org.kuali.kra.common.committee.rule.event.CommitteeScheduleEventBase.ErrorType type) {
        
        return new DeleteCommitteeScheduleEvent(errorPathPrefix, document, scheduleData, committeeSchedules, type);
    }

    @Override
    protected Class<? extends CommitteeScheduleServiceBase> getCommitteeScheduleServiceClassHook() {
        return CommitteeScheduleService.class;
    }

    @Override
    protected String getMeetingManagementActionIdHook() {
        return "meetingManagement";
    }
    
// TODO ********************** commented out during IRB backfit ************************    
//    @SuppressWarnings("unused")
//    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(CommitteeScheduleAction.class);
//    
//    private static final String DELETE_QUESTION = "Are you sure you want to delete?";
//    
//    private static final String DELETE_QUESTION_ID = "committeeSchedule.delete.question";
//        
//    public static final boolean FALSE = false;
//    
//    /**
//     * @see org.kuali.kra.committee.web.struts.action.CommitteeAction#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
//     */
//    @Override
//    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
//            throws Exception {
//
//        ActionForward actionForward = super.execute(mapping, form, request, response);
//        ((CommitteeForm)form).getCommitteeHelper().prepareView();
//        
//        return actionForward;
//    }    
//    
//    /**
//     * @see org.kuali.kra.committee.web.struts.action.CommitteeAction#save(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
//     */
//    @Override
//    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {    
//                
//        CommitteeForm committeeForm = (CommitteeForm) form;
//        ScheduleData scheduleData = committeeForm.getCommitteeHelper().getScheduleData();
//      
//        ActionForward actionForward = super.save(mapping, form, request, response);
//
//        scheduleData.populateStyleClass(); 
//        return actionForward;
//    }    
//    
//    /**
//     * This method us UI hook to add new schedules to list of schedules.
//     * @param mapping
//     * @param form
//     * @param request
//     * @param response
//     * @return
//     * @throws Exception
//     */
//    public ActionForward addEvent(ActionMapping mapping, ActionForm form,
//            HttpServletRequest request, HttpServletResponse response) throws Exception {
// 
//        CommitteeForm committeeForm = (CommitteeForm) form;
//        ScheduleData scheduleData = committeeForm.getCommitteeHelper().getScheduleData();
//        boolean flag = false;
//        
//        flag = new CommitteeScheduleDataDictionaryValidationRule().applyRules(scheduleData);
//        
//        flag &= applyRules(new CommitteeScheduleWeekDayEvent(Constants.EMPTY_STRING, committeeForm.getDocument(), scheduleData, null, ErrorType.HARDERROR));
//        
//        flag &= applyRules(new CommitteeScheduleDayEvent(Constants.EMPTY_STRING, committeeForm.getDocument(), scheduleData, null, ErrorType.HARDERROR));
//        
//        flag &= applyRules(new CommitteeScheduleTimeEvent(Constants.EMPTY_STRING, committeeForm.getDocument(), scheduleData, null, ErrorType.HARDERROR));
//        
//        flag &= applyRules(new CommitteeScheduleStartAndEndDateEvent(Constants.EMPTY_STRING, committeeForm.getDocument(), scheduleData, null, ErrorType.HARDERROR));
//        
//        if(flag) {
//            CommitteeScheduleService service  = getCommitteeScheduleService();
//            service.addSchedule(scheduleData, committeeForm.getCommitteeDocument().getCommittee());            
//            applyRules(new CommitteeScheduleDateConflictEvent(Constants.EMPTY_STRING, committeeForm.getDocument(), scheduleData, null, ErrorType.SOFTERROR));
//            committeeForm.getCommitteeHelper().prepareView();
//        }
//
//        scheduleData.populateStyleClass();        
//        return mapping.findForward(Constants.MAPPING_BASIC );
//    }
//
//    /**
//     * This method is UI hook to delete CommitteeSchedule from list.
//     * @param mapping
//     * @param form
//     * @param request
//     * @param response
//     * @return
//     * @throws Exception
//     */
//    public ActionForward deleteCommitteeSchedule(ActionMapping mapping, ActionForm form, HttpServletRequest request,
//            HttpServletResponse response) throws Exception {
//
//        CommitteeForm committeeForm = (CommitteeForm) form;
//        Object question = request.getParameter(KRADConstants.QUESTION_INST_ATTRIBUTE_NAME);
//        String methodToCall = committeeForm.getMethodToCall();
//        if (question == null) {
//            if (applyRules(new DeleteCommitteeScheduleEvent(Constants.EMPTY_STRING, committeeForm.getDocument(), null, committeeForm.getCommitteeDocument().getCommittee().getCommitteeSchedules(),
//                ErrorType.HARDERROR))) {
//                return performQuestionWithoutInput(mapping, form, request, response, DELETE_QUESTION_ID, DELETE_QUESTION,
//                        KRADConstants.CONFIRMATION_QUESTION, methodToCall, "");
//            }
//        } else {
//            Object buttonClicked = request.getParameter(KRADConstants.QUESTION_CLICKED_BUTTON);
//            if ((DELETE_QUESTION_ID.equals(question)) && ConfirmationQuestion.YES.equals(buttonClicked)) {
//
//                List<CommitteeSchedule> list = committeeForm.getCommitteeDocument().getCommittee().getCommitteeSchedules();
//                List<CommitteeSchedule> updatedlist = new ArrayList<CommitteeSchedule>(list);
//                Collections.copy(updatedlist, list);
//                for (CommitteeSchedule schedule : list) {
//                    if (schedule.isSelected())
//                        updatedlist.remove(schedule);
//                }
//                committeeForm.getCommitteeDocument().getCommittee().setCommitteeSchedules(updatedlist);
//            }
//        }
//        return mapping.findForward(Constants.MAPPING_BASIC);
//    }
//    
//    /**
//     * This method is UI hook to filter dates in between start and end date.
//     * @param mapping
//     * @param form
//     * @param request
//     * @param response
//     * @return
//     * @throws Exception
//     */
//    public ActionForward filterCommitteeScheduleDates(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
//        
//        CommitteeForm committeeForm = (CommitteeForm) form;  
//        
//        
//        ScheduleData scheduleData = committeeForm.getCommitteeHelper().getScheduleData();
//        if(applyRules(new CommitteeScheduleFilterEvent(Constants.EMPTY_STRING, committeeForm.getDocument(), scheduleData, null, ErrorType.HARDERROR))) {
//            Date startDate = scheduleData.getFilterStartDate();
//            Date endDate = scheduleData.getFilerEndDate();
//            committeeForm.getCommitteeHelper().prepareFilterDatesView(startDate, endDate);
//        }
//        return mapping.findForward(Constants.MAPPING_BASIC );
//    }
//
//    /**
//     * This method is UI hook to reset filtered dates.
//     * @param mapping
//     * @param form
//     * @param request
//     * @param response
//     * @return
//     * @throws Exception
//     */
//    public ActionForward resetCommitteeScheduleDates(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
//        
//        CommitteeForm committeeForm = (CommitteeForm) form;        
//        committeeForm.getCommitteeHelper().resetFilterDatesView();
//        return mapping.findForward(Constants.MAPPING_BASIC );
//    } 
//    
//    /**
//     * This method is UI hook to load recurrence, in case javascript is turned off on browser.
//     * @param mapping
//     * @param form
//     * @param request
//     * @param response
//     * @return
//     * @throws Exception
//     */
//    public ActionForward loadRecurrence(ActionMapping mapping, ActionForm form,
//            HttpServletRequest request, HttpServletResponse response) throws Exception {
//        CommitteeForm committeeForm = (CommitteeForm) form;
//        committeeForm.getCommitteeHelper().getScheduleData().populateStyleClass();
//        return mapping.findForward(Constants.MAPPING_BASIC );
//    }  
//    
//    /**
//     * This method retrieve CommitteeScheduleService.
//     * @return
//     */
//    private CommitteeScheduleService getCommitteeScheduleService(){
//        return KraServiceLocator.getService(CommitteeScheduleService.class);
//    }
//        
//    public ActionForward maintainSchedule(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
//        
//        CommitteeForm committeeForm = (CommitteeForm) form;     
//        CommitteeSchedule commSchedule = ((CommitteeDocument)committeeForm.getDocument()).getCommittee().getCommitteeSchedules().get(getLineToDelete(request));
//        response.sendRedirect("meetingManagement.do?methodToCall=start&scheduleId="+commSchedule.getId()
//                +"&lineNum="+(getLineToDelete(request)+1)+"&readOnly=" +(!committeeForm.getCommitteeHelper().canModifySchedule()));
//        return null;
//    }    

}
