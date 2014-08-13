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
