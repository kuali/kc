/*
 * Copyright 2005-2014 The Kuali Foundation
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

import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolForm;
import org.kuali.kra.irb.auth.ProtocolTask;
import org.kuali.kra.protocol.auth.ProtocolTaskBase;
import org.kuali.kra.protocol.specialreview.ProtocolSpecialReviewBase;
import org.kuali.kra.protocol.specialreview.ProtocolSpecialReviewHelperBase;

import java.util.ArrayList;
import java.util.List;

/**
 * Defines the Special Review Helper for Protocol.
 */
public class SpecialReviewHelper extends ProtocolSpecialReviewHelperBase {

    private static final long serialVersionUID = -6004130465079070854L;
    private ProtocolForm form;

    public SpecialReviewHelper(ProtocolForm form) {
        this.form = form;
        setNewSpecialReview(new ProtocolSpecialReview());
        setLinkedProtocolNumbers(new ArrayList<String>());
    }

    @Override
    protected List<ProtocolSpecialReviewBase> getSpecialReviews() {
        return form.getProtocolDocument().getProtocol().getSpecialReviews();
    }

    @Override
    protected boolean hasModifySpecialReviewPermission(String principalId) {
        ProtocolTaskBase task = new ProtocolTask(TaskName.MODIFY_PROTOCOL_SPECIAL_REVIEW, (Protocol)form.getProtocolDocument().getProtocol());
        return getTaskAuthorizationService().isAuthorized(principalId, task);
    }

    @Override
    public boolean isIrbProtocolLinkingEnabledForModule() {
        return false;
    }

    @Override
    public boolean isIacucProtocolLinkingEnabledForModule() {
        return false;
    }

    @Override
    public boolean isCanCreateIrbProtocol() {
        return true;
    }

    @Override
    public boolean isCanCreateIacucProtocol() {
        return false;
    }    
}