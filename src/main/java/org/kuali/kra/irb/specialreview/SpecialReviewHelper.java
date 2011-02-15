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
package org.kuali.kra.irb.specialreview;

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.common.specialreview.web.struts.form.SpecialReviewHelperBase;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.irb.ProtocolForm;
import org.kuali.kra.irb.auth.ProtocolTask;
import org.kuali.kra.service.TaskAuthorizationService;

/**
 * Defines the Special Review Helper for Protocol.
 */
public class SpecialReviewHelper extends SpecialReviewHelperBase<ProtocolSpecialReview> {

    private static final long serialVersionUID = -6004130465079070854L;

    private ProtocolForm form;
    
    private transient TaskAuthorizationService taskAuthorizationService;
    
    /**
     * Constructs a SpecialReviewHelper.
     * @param form the container form
     */
    public SpecialReviewHelper(ProtocolForm form) {
        this.form = form;
        setNewSpecialReview(new ProtocolSpecialReview());
        setLinkedProtocolNumbers(new ArrayList<String>());    
    }

    @Override
    protected boolean hasModifySpecialReviewPermission(String principalId) {
        ProtocolTask task = new ProtocolTask(TaskName.MODIFY_PROTOCOL_SPECIAL_REVIEW, form.getDocument().getProtocol());
        return getTaskAuthorizationService().isAuthorized(principalId, task);
    }
    
    @Override
    protected boolean isProtocolLinkingEnabledForModule() {
        return false;
    }

    @Override
    protected List<ProtocolSpecialReview> getSpecialReviews() {
        return form.getDocument().getProtocol().getSpecialReviews();
    }
    
    public TaskAuthorizationService getTaskAuthorizationService() {
        if (taskAuthorizationService == null) {
            taskAuthorizationService = KraServiceLocator.getService(TaskAuthorizationService.class);
        }
        return taskAuthorizationService;
    }
    
    public void setTaskAuthorizationService(TaskAuthorizationService taskAuthorizationService) {
        this.taskAuthorizationService = taskAuthorizationService;
    }
    
}