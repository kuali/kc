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
