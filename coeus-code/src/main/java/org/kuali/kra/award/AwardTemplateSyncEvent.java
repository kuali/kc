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
