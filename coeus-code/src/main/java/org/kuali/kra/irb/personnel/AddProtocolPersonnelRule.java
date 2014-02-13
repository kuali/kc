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
package org.kuali.kra.irb.personnel;

import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.protocol.personnel.ProtocolPersonnelRuleBase;

/**
 * Runs the rule processing for adding a <code>ProtocolPerson</code>.
 */
public class AddProtocolPersonnelRule extends ProtocolPersonnelRuleBase implements KcBusinessRule<AddProtocolPersonnelEvent> {

    /**
     * {@inheritDoc}
     * @see org.kuali.coeus.sys.framework.rule.KcBusinessRule#processRules(org.kuali.coeus.sys.framework.rule.KcDocumentEventBaseExtension)
     */
    public boolean processRules(AddProtocolPersonnelEvent event) {
        return processAddProtocolPersonnelEvent(event);
    }

    @Override
    public org.kuali.kra.protocol.personnel.ProtocolPersonnelService getProtocolPersonnelServiceHook() {
        return (ProtocolPersonnelService) KcServiceLocator.getService(ProtocolPersonnelService.class);
    }

}