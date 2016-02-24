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
package org.kuali.kra.award.contacts;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBase;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.BusinessRule;

import java.util.List;

/**
 * This class is the event passed when a new projectPerson is being added
 */
public class SaveAwardProjectPersonsRuleEvent extends KcDocumentEventBase {
    private static final Log LOG = LogFactory.getLog(SaveAwardProjectPersonsRuleEvent.class);
    
    private List<AwardPerson> projectPersons;
    
    public SaveAwardProjectPersonsRuleEvent(String description, String errorPathPrefix, Document document) {
        super(description, errorPathPrefix, document);
        this.projectPersons = ((AwardDocument) document).getAward().getProjectPersons();
    }

    /**
     * Gets the Award project persons. 
     * @return Returns the Award project persons
     */
    public List<AwardPerson> getProjectPersons() {
        return projectPersons;
    }

    public Class<AwardProjectPersonAddRule> getRuleInterfaceClass() {
        return AwardProjectPersonAddRule.class;
    }

    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((AwardProjectPersonsSaveRule) rule).processSaveAwardProjectPersonsBusinessRules(this);
    }

    @Override
    protected void logEvent() {
        LOG.info("Logging SaveAwardProjectPersonsRuleEvent");
    }

}
