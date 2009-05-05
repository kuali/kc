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
package org.kuali.kra.irb.customdata;

import org.kuali.kra.common.customattributes.CustomDataHelperBase;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.irb.bo.Protocol;
import org.kuali.kra.irb.document.ProtocolDocument;
import org.kuali.kra.irb.document.authorization.ProtocolTask;
import org.kuali.kra.irb.web.struts.form.ProtocolForm;

/**
 * The CustomDataHelper is used to manage the Custom Data tab web page.
 * It contains the data, forms, and methods needed to render the page.
 */
public class CustomDataHelper extends CustomDataHelperBase {
    
    /**
     * Each Helper must contain a reference to its document form
     * so that it can access the document.
     */
    private ProtocolForm form;
    
    /**
     * Constructs a CustomDataHelper.
     * @param form the form
     */
    public CustomDataHelper(ProtocolForm form) {
        this.form = form;
    }
    
    /*
     * Get the Protocol.
     */
    private Protocol getProtocol() {
        ProtocolDocument document = form.getDocument();
        if (document == null || document.getProtocol() == null) {
            throw new IllegalArgumentException("invalid (null) ProtocolDocument in ProtocolForm");
        }
        return document.getProtocol();
    }
    
    /**
     * @see org.kuali.kra.common.customattributes.CustomDataHelperBase#canModifyCustomData()
     */
    @Override
    public boolean canModifyCustomData() {
        ProtocolTask task = new ProtocolTask(TaskName.MODIFY_PROTOCOL, getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserName(), task);
    }
}
