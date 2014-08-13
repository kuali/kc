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
package org.kuali.coeus.propdev.impl.action;

import org.apache.struts.upload.FormFile;

import java.io.Serializable;

public class ProposalDevelopmentRejectionBean implements Serializable {


    private static final long serialVersionUID = 4081027512143550976L;
    
    private String rejectReason;
    transient private FormFile rejectFile;
    

    public ProposalDevelopmentRejectionBean() {
        
    }

    public FormFile getRejectFile() {
        return rejectFile;
    }

    public void setRejectFile(FormFile rejectFile) {
        this.rejectFile = (FormFile) rejectFile;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }
}
