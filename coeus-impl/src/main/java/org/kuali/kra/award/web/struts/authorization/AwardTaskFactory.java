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
package org.kuali.kra.award.web.struts.authorization;

import org.apache.struts.action.ActionForm;
import org.kuali.coeus.common.framework.auth.task.Task;
import org.kuali.coeus.common.framework.auth.task.WebTaskFactoryBase;
import org.kuali.kra.award.AwardForm;
import org.kuali.kra.award.document.authorization.AwardTask;
import org.kuali.kra.infrastructure.TaskGroupName;

import javax.servlet.http.HttpServletRequest;

/**
 * The Proposal Task Factory will create a Proposal Task with its
 * task name and the proposal development document contained within
 * the form.
 */
public class AwardTaskFactory extends WebTaskFactoryBase {

    @Override
    public Task createTask(ActionForm form, HttpServletRequest request) {
        AwardForm awardForm = (AwardForm) form;
        return new AwardTask(getTaskName(), awardForm.getAwardDocument().getAward());
    }


    @Override
    public String getTaskGroupName() {
        return TaskGroupName.AWARD;
    }
}
