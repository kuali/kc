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
package org.kuali.kra.award.rule.event;

import java.util.List;

import org.apache.log4j.Logger;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardApprovedSubaward;
import org.kuali.kra.award.rule.AwardTemplateSyncRule;
import org.kuali.kra.rule.event.KraDocumentEventBase;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.rule.BusinessRule;

/**
 * This class is to handle Award template sync
 */
public class AwardTemplateSyncEvent  extends KraDocumentEventBase{

    private AwardDocument awardDocument;
    private Award award;
    private String listPropertyName;
    private static final Logger LOG = Logger.getLogger(AwardTemplateSyncEvent.class);
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

    /**
     * Gets the listPropertyName attribute. 
     * @return Returns the listPropertyName.
     */
    public String getListPropertyName() {
        return listPropertyName;
    }

    /**
     * Sets the listPropertyName attribute value.
     * @param listPropertyName The listPropertyName to set.
     */
    public void setListPropertyName(String listPropertyName) {
        this.listPropertyName = listPropertyName;
    }

}
