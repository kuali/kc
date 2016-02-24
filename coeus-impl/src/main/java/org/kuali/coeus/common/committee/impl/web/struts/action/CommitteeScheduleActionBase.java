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
package org.kuali.coeus.common.committee.impl.web.struts.action;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;
import org.kuali.coeus.common.committee.impl.bo.CommitteeScheduleBase;
import org.kuali.coeus.common.committee.impl.document.CommitteeDocumentBase;
import org.kuali.coeus.common.committee.impl.rule.event.*;
import org.kuali.coeus.common.committee.impl.rule.event.CommitteeScheduleEventBase.ErrorType;
import org.kuali.coeus.common.committee.impl.rules.CommitteeScheduleDataDictionaryValidationRule;
import org.kuali.coeus.common.committee.impl.service.CommitteeScheduleServiceBase;
import org.kuali.coeus.common.committee.impl.web.struts.form.CommitteeFormBase;
import org.kuali.coeus.common.committee.impl.web.struts.form.schedule.ScheduleData;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.kns.question.ConfirmationQuestion;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.util.KRADConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public abstract class CommitteeScheduleActionBase extends CommitteeActionBase {
    
    @SuppressWarnings("unused")
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(CommitteeScheduleActionBase.class);
    
    private static final String DELETE_QUESTION = "Are you sure you want to delete?";
    
    private static final String DELETE_QUESTION_ID = "committeeSchedule.delete.question";
        
    public static final boolean FALSE = false;
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ActionForward actionForward = super.execute(mapping, form, request, response);
        ((CommitteeFormBase)form).getCommitteeHelper().prepareView();
        
        return actionForward;
    }    
    
    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {    
                
        CommitteeFormBase committeeForm = (CommitteeFormBase) form;
        ScheduleData scheduleData = committeeForm.getCommitteeHelper().getScheduleData();
      
        ActionForward actionForward = super.save(mapping, form, request, response);

        scheduleData.populateStyleClass(); 
        return actionForward;
    }    
    
    /**
     * This method us UI hook to add new schedules to list of schedules.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward addEvent(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
 
        CommitteeFormBase committeeForm = (CommitteeFormBase) form;
        ScheduleData scheduleData = committeeForm.getCommitteeHelper().getScheduleData();
        boolean flag = false;
        
        flag = new CommitteeScheduleDataDictionaryValidationRule().applyRules(scheduleData);
        
        flag &= applyRules(new CommitteeScheduleWeekDayEvent(Constants.EMPTY_STRING, committeeForm.getDocument(), scheduleData, null, ErrorType.HARDERROR));
        
        flag &= applyRules(new CommitteeScheduleDayEvent(Constants.EMPTY_STRING, committeeForm.getDocument(), scheduleData, null, ErrorType.HARDERROR));
        
        flag &= applyRules(new CommitteeScheduleTimeEvent(Constants.EMPTY_STRING, committeeForm.getDocument(), scheduleData, null, ErrorType.HARDERROR));
        
        flag &= applyRules(new CommitteeScheduleStartAndEndDateEvent(Constants.EMPTY_STRING, committeeForm.getDocument(), scheduleData, null, ErrorType.HARDERROR));
        
        if(flag) {
            CommitteeScheduleServiceBase service  = getCommitteeScheduleService();
            service.addSchedule(scheduleData, committeeForm.getCommitteeDocument().getCommittee());            
            applyRules(new CommitteeScheduleDateConflictEvent(Constants.EMPTY_STRING, committeeForm.getDocument(), scheduleData, null, ErrorType.SOFTERROR));
            committeeForm.getCommitteeHelper().prepareView();
        }

        scheduleData.populateStyleClass();        
        return mapping.findForward(Constants.MAPPING_BASIC );
    }

    /**
     * This method is UI hook to delete CommitteeScheduleBase from list.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteCommitteeSchedule(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        CommitteeFormBase committeeForm = (CommitteeFormBase) form;
        Object question = request.getParameter(KRADConstants.QUESTION_INST_ATTRIBUTE_NAME);
        String methodToCall = committeeForm.getMethodToCall();
        if (question == null) {
            if (applyRules(getNewDeleteCommitteeScheduleEventInstanceHook(Constants.EMPTY_STRING, committeeForm.getDocument(), null, committeeForm.getCommitteeDocument().getCommittee().getCommitteeSchedules(),
                ErrorType.HARDERROR))) {
                return performQuestionWithoutInput(mapping, form, request, response, DELETE_QUESTION_ID, DELETE_QUESTION,
                        KRADConstants.CONFIRMATION_QUESTION, methodToCall, "");
            }
        } else {
            Object buttonClicked = request.getParameter(KRADConstants.QUESTION_CLICKED_BUTTON);
            if ((DELETE_QUESTION_ID.equals(question)) && ConfirmationQuestion.YES.equals(buttonClicked)) {

                List<CommitteeScheduleBase> list = committeeForm.getCommitteeDocument().getCommittee().getCommitteeSchedules();
                List<CommitteeScheduleBase> updatedlist = new ArrayList<CommitteeScheduleBase>(list);
                Collections.copy(updatedlist, list);
                for (CommitteeScheduleBase schedule : list) {
                    if (schedule.isSelected())
                        updatedlist.remove(schedule);
                }
                committeeForm.getCommitteeDocument().getCommittee().setCommitteeSchedules(updatedlist);
            }
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    protected abstract DeleteCommitteeScheduleEventBase getNewDeleteCommitteeScheduleEventInstanceHook(String errorPathPrefix, Document document, ScheduleData scheduleData, List<CommitteeScheduleBase> committeeSchedules, ErrorType type);

    /**
     * This method is UI hook to filter dates in between start and end date.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward filterCommitteeScheduleDates(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        CommitteeFormBase committeeForm = (CommitteeFormBase) form;  
        
        
        ScheduleData scheduleData = committeeForm.getCommitteeHelper().getScheduleData();
        if(applyRules(new CommitteeScheduleFilterEvent(Constants.EMPTY_STRING, committeeForm.getDocument(), scheduleData, null, ErrorType.HARDERROR))) {
            Date startDate = scheduleData.getFilterStartDate();
            Date endDate = scheduleData.getFilerEndDate();
            committeeForm.getCommitteeHelper().prepareFilterDatesView(startDate, endDate);
        }
        return mapping.findForward(Constants.MAPPING_BASIC );
    }

    /**
     * This method is UI hook to reset filtered dates.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward resetCommitteeScheduleDates(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        CommitteeFormBase committeeForm = (CommitteeFormBase) form;        
        committeeForm.getCommitteeHelper().resetFilterDatesView();
        return mapping.findForward(Constants.MAPPING_BASIC );
    } 
    
    /**
     * This method is UI hook to load recurrence, in case javascript is turned off on browser.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward loadRecurrence(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        CommitteeFormBase committeeForm = (CommitteeFormBase) form;
        committeeForm.getCommitteeHelper().getScheduleData().populateStyleClass();
        return mapping.findForward(Constants.MAPPING_BASIC );
    }  
    
    /**
     * This method retrieve CommitteeScheduleService.
     * @return
     */
    private CommitteeScheduleServiceBase getCommitteeScheduleService(){
        return KcServiceLocator.getService(getCommitteeScheduleServiceClassHook());
    }
        
    protected abstract Class<? extends CommitteeScheduleServiceBase> getCommitteeScheduleServiceClassHook();
    

    
    public ActionForward maintainSchedule(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        CommitteeFormBase committeeForm = (CommitteeFormBase) form;     
        CommitteeScheduleBase commSchedule = ((CommitteeDocumentBase<?, ?, ?>)committeeForm.getDocument()).getCommittee().getCommitteeSchedules().get(getLineToDelete(request));
        
        return new ActionRedirect(getMeetingManagementActionIdHook() + ".do?methodToCall=start&scheduleId="+commSchedule.getId()+"&lineNum="+(getLineToDelete(request)+1)+"&readOnly=" +(!committeeForm.getCommitteeHelper().canModifySchedule()));
    }

    protected abstract String getMeetingManagementActionIdHook();

}
