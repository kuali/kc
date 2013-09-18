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

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public enum AwardTemplateSyncScope {
    
    //match any scope
    ANY (null),
    //sync everything.
    FULL (null),
    //The Award Page sync
    AWARD_PAGE ( "document.question.syncPanel.panelName.AWARD_PAGE" ),
    //for sync of sponsor contacts
    SPONSOR_CONTACTS_TAB ( "document.question.syncPanel.panelName.SPONSOR_CONTACTS_TAB" ),
    //for sync or Payments and Invoices section. 
    PAYMENTS_AND_INVOICES_TAB ( "document.question.syncPanel.panelName.PAYMENTS_AND_INVOICES_TAB" ),
    //sync the reports
    REPORTS_TAB ( "document.question.syncPanel.panelName.REPORTS_TAB" ),
    //sync the sponsor terms
    TERMS_TAB ( "document.question.syncPanel.panelName.TERMS_TAB" ),
    //sync the notes and attachements
    COMMENTS_TAB ( "document.question.syncPanel.panelName.COMMENTS_TAB" ),
    //sync the cost share
    COST_SHARE ( "document.question.syncPanel.panelName.COST_SHARE" ),
    //sync the pre award authorizations
    PREAWARD_AUTHORIZATIONS_TAB ( "document.question.syncPanel.panelName.PREAWARD_AUTHORIZATIONS_TAB" ),
    //sync the rates
    RATES_TAB ( "document.question.syncPanel.panelName.RATES_TAB" ),
    //inherit the containing classes scope - this means that the field will sync when the class containing it syncs.
    CONTAINING_CLASS_INHERIT (null) ;

    private String displayPropertyName;
    
    
    AwardTemplateSyncScope(  String displayPropertyName ) {
        this.displayPropertyName = displayPropertyName;
    }
    
    public String getDisplayPropertyName() {
        return this.displayPropertyName;
    }
    
    public static boolean isInScope( AwardTemplateSyncScope[] propertyScopes,  AwardTemplateSyncScope[] appliedScopes ) {
        boolean result = ArrayUtils.contains(propertyScopes, AwardTemplateSyncScope.ANY );
        if(result) return result;
        for( AwardTemplateSyncScope scope : appliedScopes ) {
            if( ArrayUtils.contains(propertyScopes, scope) ) return true;
        }
        return result;
    }
}
