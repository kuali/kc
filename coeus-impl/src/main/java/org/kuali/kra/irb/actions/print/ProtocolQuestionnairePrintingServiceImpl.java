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
