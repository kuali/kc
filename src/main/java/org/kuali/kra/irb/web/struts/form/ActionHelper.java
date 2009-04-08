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
package org.kuali.kra.irb.web.struts.form;

import org.kuali.kra.irb.bo.Protocol;
import org.kuali.kra.irb.bo.ProtocolSubmitAction;
import org.kuali.kra.irb.document.ProtocolDocument;

public class ActionHelper {

    /**
     * Each Helper must contain a reference to its document form
     * so that it can access the document.
     */
    private ProtocolForm form;
    
    private ProtocolSubmitAction protocolSubmitAction;
    
    /**
     * Constructs an ActionHelper.
     * @param form the form
     */
    public ActionHelper(ProtocolForm form) {
        this.form = form;
        protocolSubmitAction = new ProtocolSubmitAction();
    }
    
    public void prepareView() {
        
    }
    
    public ProtocolSubmitAction getProtocolSubmitAction() {
        return protocolSubmitAction;
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
    
}
