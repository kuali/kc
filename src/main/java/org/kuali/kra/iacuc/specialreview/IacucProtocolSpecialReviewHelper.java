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
package org.kuali.kra.iacuc.specialreview;

import java.util.ArrayList;

import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.IacucProtocolForm;
import org.kuali.kra.iacuc.auth.IacucProtocolTask;
import org.kuali.kra.protocol.auth.ProtocolTask;
import org.kuali.kra.protocol.specialreview.ProtocolSpecialReviewHelper;

/**
 * Defines the Special Review Helper for Protocol.
 */
public class IacucProtocolSpecialReviewHelper extends ProtocolSpecialReviewHelper {

    private static final long serialVersionUID = 1485258866764215682L;

    /**
     * Constructs a SpecialRevidocument = ewHelper.
     * @param form the container form
     */
    public IacucProtocolSpecialReviewHelper(IacucProtocolForm form) {
        super(form);
        setLinkedProtocolNumbers(new ArrayList<String>());    
        setNewSpecialReview();
    }

    @Override
    protected boolean hasModifySpecialReviewPermission(String principalId) {
        ProtocolTask task = new IacucProtocolTask(TaskName.MODIFY_IACUC_PROTOCOL_SPECIAL_REVIEW, (IacucProtocol)form.getProtocolDocument().getProtocol());
        return getTaskAuthorizationService().isAuthorized(principalId, task);
    }
    
    protected void setNewSpecialReview() {
        setNewSpecialReview(new IacucProtocolSpecialReview());
    }

    @Override
    public boolean isCanCreateIacucProtocol() {
        return false;
    }
    @Override
    protected boolean isIacucProtocolLinkingEnabledForModule() {
        return true;
    }

}