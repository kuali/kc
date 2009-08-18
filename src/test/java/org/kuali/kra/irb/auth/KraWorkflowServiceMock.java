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
package org.kuali.kra.irb.auth;

import org.kuali.kra.service.KraWorkflowService;
import org.kuali.rice.kns.document.Document;

public class KraWorkflowServiceMock implements KraWorkflowService {

    private boolean inWorkflow;

    public KraWorkflowServiceMock() {
        inWorkflow = false;
    }
    
    public KraWorkflowServiceMock(boolean inWorkflow) {
        this.inWorkflow = inWorkflow;
    }
    
    public boolean hasWorkflowPermission(String username, Document doc) {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean isClosed(Document doc) {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean isEnRoute(Document doc) {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean isInWorkflow(Document doc) {
        return inWorkflow;
    }

}
