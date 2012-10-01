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
package org.kuali.kra.protocol.actions.noreview;

import java.io.Serializable;
import java.sql.Date;

import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.actions.ActionHelperBase;
import org.kuali.kra.protocol.actions.ProtocolActionBean;

/**
 * This class manages the HTML Elements needed for the review not required panel.
 */
public class ProtocolReviewNotRequiredBean implements ProtocolActionBean, Serializable {

    private static final long serialVersionUID = -5865035287789746024L;

    private String comments = "";
    private Date actionDate = new Date(System.currentTimeMillis());
    private Date decisionDate = new Date(System.currentTimeMillis());

    private ProtocolBase protocol;
    private ActionHelperBase actionHelper;
    
    /**
     * Constructs a ProtocolReviewNotRequiredBean.
     * @param actionHelper Reference back to the action helper for this bean
     */
    public ProtocolReviewNotRequiredBean(ActionHelperBase actionHelper) {
        this.actionHelper = actionHelper;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Date getActionDate() {
        return actionDate;
    }

    public void setActionDate(Date actionDate) {
        this.actionDate = actionDate;
    }

    public Date getDecisionDate() {
        return decisionDate;
    }

    public void setDecisionDate(Date decisionDate) {
        this.decisionDate = decisionDate;
    }

    public ProtocolBase getProtocol() {
        return protocol;
    }
    
    public void setProtocol(ProtocolBase protocol) {
        this.protocol = protocol;
    }
    
    public ActionHelperBase getActionHelper() {
        return actionHelper;
    }

    @Override
    public void setActionHelper(ActionHelperBase actionHelper) {
        this.actionHelper = actionHelper;
        this.protocol = actionHelper.getProtocol();
    }

}
