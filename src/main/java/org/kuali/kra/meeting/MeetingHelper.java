/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.meeting;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;

public class MeetingHelper implements Serializable {

    private static final long serialVersionUID = 2363534404324211441L;
    private MeetingForm form;
    private Date agendaGenerationDate;
    private CommitteeSchedule committeeSchedule;
    private List<ProtocolSubmittedBean> protocolSubmittedBeans;        
    private CommScheduleActItem newOtherAction;        
  private List<CommScheduleActItem> deletedOtherActions;   
    private String tabLabel;

    public MeetingHelper(MeetingForm form) {
        this.form = form;
        committeeSchedule = new CommitteeSchedule();
        protocolSubmittedBeans = new ArrayList<ProtocolSubmittedBean>();
        deletedOtherActions = new ArrayList<CommScheduleActItem>();
        newOtherAction = new CommScheduleActItem();
    }

    public MeetingForm getForm() {
        return form;
    }

    public void setForm(MeetingForm form) {
        this.form = form;
    }


    public CommScheduleActItem getNewOtherAction() {
        return newOtherAction;
    }

    public void setNewOtherAction(CommScheduleActItem newOtherAction) {
        this.newOtherAction = newOtherAction;
    }

    public String getTabLabel() {
        return tabLabel;
    }

    public void setTabLabel(String tabLabel) {
        this.tabLabel = tabLabel;
    }

    public CommitteeSchedule getCommitteeSchedule() {
        return committeeSchedule;
    }

    public void setCommitteeSchedule(CommitteeSchedule committeeSchedule) {
        this.committeeSchedule = committeeSchedule;
    }

    public Date getAgendaGenerationDate() {
        return agendaGenerationDate;
    }

    public void setAgendaGenerationDate(Date agendaGenerationDate) {
        this.agendaGenerationDate = agendaGenerationDate;
    }

    public List<CommScheduleActItem> getDeletedOtherActions() {
        return deletedOtherActions;
    }

    public void setDeletedOtherActions(List<CommScheduleActItem> deletedOtherActions) {
        this.deletedOtherActions = deletedOtherActions;
    }

    public List<ProtocolSubmittedBean> getProtocolSubmittedBeans() {
        return protocolSubmittedBeans;
    }

    public void setProtocolSubmittedBeans(List<ProtocolSubmittedBean> protocolSubmittedBeans) {
        this.protocolSubmittedBeans = protocolSubmittedBeans;
    }

}
