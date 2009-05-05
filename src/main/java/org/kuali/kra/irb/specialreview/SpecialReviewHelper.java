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
package org.kuali.kra.irb.specialreview;

import java.io.Serializable;
import java.util.List;

import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.irb.bo.Protocol;
import org.kuali.kra.irb.document.ProtocolDocument;
import org.kuali.kra.irb.document.authorization.ProtocolTask;
import org.kuali.kra.irb.web.struts.form.ProtocolForm;
import org.kuali.kra.rice.shim.UniversalUser;
import org.kuali.kra.service.TaskAuthorizationService;
import org.kuali.rice.kns.util.GlobalVariables;

public class SpecialReviewHelper implements Serializable {
    
    /**
     * Each Helper must contain a reference to its document form
     * so that it can access the actual document.
     */
    private ProtocolForm form;
    
    private boolean modifyProtocol = false;
    
    private ProtocolSpecialReview newSpecialReview;
    private List<ProtocolSpecialReviewExemption> newSpecialReviewExemptions;
    private String[] newExemptionTypeCodes;
    
    public SpecialReviewHelper(ProtocolForm form) {
        this.form = form;
        setNewSpecialReview(new ProtocolSpecialReview());
    }
    
    public void prepareView() {
        initializePermissions(getProtocol());
    }
    
    public void reset() {
        for (ProtocolSpecialReview specialReview : getProtocol().getSpecialReviews()) {
            specialReview.setNewExemptionTypeCodes(null);
        }
    }
    
    private void initializePermissions(Protocol protocol) {
        ProtocolTask task = new ProtocolTask(TaskName.MODIFY_PROTOCOL, protocol);
        modifyProtocol = getTaskAuthorizationService().isAuthorized(getUsername(), task);   
    }
    
    private Protocol getProtocol() {
        ProtocolDocument document = form.getDocument();
        if (document == null || document.getProtocol() == null) {
            throw new IllegalArgumentException("invalid (null) ProtocolDocument in ProtocolForm");
        }
        return document.getProtocol();
    }
    
    public boolean getModifyProtocol() {
        return modifyProtocol;
    }

    public ProtocolSpecialReview getNewSpecialReview() {
        return newSpecialReview;
    }

    public void setNewSpecialReview(ProtocolSpecialReview newSpecialReview) {
        this.newSpecialReview = newSpecialReview;
    }

    public List<ProtocolSpecialReviewExemption> getNewSpecialReviewExemptions() {
        return newSpecialReviewExemptions;
    }

    public void setNewSpecialReviewExemptions(List<ProtocolSpecialReviewExemption> newSpecialReviewExemptions) {
        this.newSpecialReviewExemptions = newSpecialReviewExemptions;
    }

    public String[] getNewExemptionTypeCodes() {
        return newExemptionTypeCodes;
    }

    public void setNewExemptionTypeCodes(String[] newExemptionTypeCodes) {
        this.newExemptionTypeCodes = newExemptionTypeCodes;
    }
    
    private TaskAuthorizationService getTaskAuthorizationService() {
        return KraServiceLocator.getService(TaskAuthorizationService.class);
    }
    
    private String getUsername() {
        UniversalUser user = new UniversalUser(GlobalVariables.getUserSession().getPerson());
        return user.getPersonUserIdentifier();
   }
}
