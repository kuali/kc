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
package org.kuali.kra.award;

import org.kuali.coeus.sys.framework.rule.KcDocumentEventBase;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.BusinessRule;

/**
 * This class is to handle Award template sync
 */
public class AwardTemplateSyncEvent  extends KcDocumentEventBase {

    private AwardDocument awardDocument;
    private Award award;


    public AwardTemplateSyncEvent(String description, String errorPathPrefix, Document document) {
        super(description, errorPathPrefix, document);
        this.awardDocument = (AwardDocument)document;
        this.award = awardDocument.getAward();
    }

    @Override
    protected void logEvent() {
        
    }

    public Class getRuleInterfaceClass() {
        return AwardTemplateSyncRule.class;
    }

    public boolean invokeRuleMethod(BusinessRule rule) {
        
        return ((AwardTemplateSyncRule)rule).processAwardTemplateSyncRules(this);
    }

    /**
     * Gets the award attribute. 
     * @return Returns the award.
     */
    public Award getAward() {
        return award;
    }

    /**
     * Sets the award attribute value.
     * @param award The award to set.
     */
    public void setAward(Award award) {
        this.award = award;
    }

}
