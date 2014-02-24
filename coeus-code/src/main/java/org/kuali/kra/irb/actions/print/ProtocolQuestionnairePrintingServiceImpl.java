/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.kra.irb.actions.print;

import org.kuali.coeus.common.framework.module.CoeusModule;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.protocol.actions.print.ProtocolQuestionnairePrintingServiceImplBase;


public class ProtocolQuestionnairePrintingServiceImpl extends ProtocolQuestionnairePrintingServiceImplBase implements ProtocolQuestionnairePrintingService {

    @Override
    protected Class<ProtocolAction> getProtocolActionBOClassHook() {
        return ProtocolAction.class;
    }

    @Override
    protected Class<ProtocolSubmission> getProtocolSubmissionBOClassHook() {
        return ProtocolSubmission.class;
    }

    @Override
    protected Class<Protocol> getProtocolBOClassHook() {
        return Protocol.class;
    }

    @Override
    protected String getCoeusModuleCode() {
        return CoeusModule.IRB_MODULE_CODE;
    }

}
