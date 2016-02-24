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
package org.kuali.kra.irb.questionnaire;

import org.kuali.coeus.common.framework.module.CoeusModule;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.amendrenew.ProtocolModule;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.questionnaire.ProtocolModuleQuestionnaireBeanBase;
import org.kuali.kra.protocol.questionnaire.ProtocolQuestionnaireAuditRuleBase;

public class ProtocolQuestionnaireAuditRule extends ProtocolQuestionnaireAuditRuleBase {

    @Override
    protected String getModuleCodeHook() {
        return CoeusModule.IRB_MODULE_CODE;
    }

    @Override
    protected ProtocolModuleQuestionnaireBeanBase getProtocolModuleQuestionnaireBean(ProtocolBase protocol) {
        return new ProtocolModuleQuestionnaireBean((Protocol)protocol);
    }

    @Override
    protected ProtocolModuleQuestionnaireBeanBase getProtocolModuleQuestionnaireBean(String moduleItemCode, String moduleItemKey,
            String moduleSubItemCode, String moduleSubItemKey, boolean finalDoc) {
        return new ProtocolModuleQuestionnaireBean(moduleItemCode, moduleItemKey, moduleSubItemCode, moduleSubItemKey, finalDoc);
    }

    @Override
    protected String getQuestionnaireModuleCodeHook() {
        return ProtocolModule.QUESTIONNAIRE;
    }

}
