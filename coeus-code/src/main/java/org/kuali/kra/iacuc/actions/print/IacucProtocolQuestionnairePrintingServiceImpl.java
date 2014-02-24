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
package org.kuali.kra.iacuc.actions.print;

import org.kuali.coeus.common.framework.module.CoeusModule;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.actions.IacucProtocolAction;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmission;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.actions.ProtocolActionBase;
import org.kuali.kra.protocol.actions.print.ProtocolQuestionnairePrintingServiceImplBase;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;

public class IacucProtocolQuestionnairePrintingServiceImpl extends ProtocolQuestionnairePrintingServiceImplBase implements IacucProtocolQuestionnairePrintingService{

    @Override
    protected String getCoeusModuleCode() {
        return CoeusModule.IACUC_PROTOCOL_MODULE_CODE;
    }

    @Override
    protected Class<? extends ProtocolSubmissionBase> getProtocolSubmissionBOClassHook() {
        return IacucProtocolSubmission.class;
    }

    @Override
    protected Class<? extends ProtocolActionBase> getProtocolActionBOClassHook() {
        return IacucProtocolAction.class;
    }

    @Override
    protected Class<? extends ProtocolBase> getProtocolBOClassHook() {
        return IacucProtocol.class;
    }

}
