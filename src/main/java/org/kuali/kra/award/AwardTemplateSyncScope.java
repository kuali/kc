/*
 * Copyright 2005-2010 The Kuali Foundation
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

import java.lang.reflect.Field;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.award.contacts.AwardSponsorContact;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.AwardComment;
import org.kuali.kra.award.home.AwardSyncable;
import org.kuali.kra.award.home.AwardSyncableList;
import org.kuali.kra.award.home.AwardTemplateComment;
import org.kuali.kra.award.home.AwardTemplateContact;
import org.kuali.kra.award.home.AwardTemplateReportTerm;
import org.kuali.kra.award.paymentreports.awardreports.AwardReportTerm;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;

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
    
    
    
    private static final Log LOG = LogFactory.getLog(AwardTemplateSyncScope.class);
    private String displayPropertyName;
    
    
    AwardTemplateSyncScope(  String displayPropertyName ) {
        this.displayPropertyName = displayPropertyName;
    }
    
    public String getDisplayPropertyName() {
        return this.displayPropertyName;
    }
    
    public boolean isInScope( Field field ) {
        boolean result = false;
        AwardSyncable async = field.getAnnotation(AwardSyncable.class);
        if( async != null && (ArrayUtils.contains(async.scopes(), this)  || this == ANY)) return true; 
        AwardSyncableList asynclist = field.getAnnotation(AwardSyncableList.class);
        if( asynclist !=null && ( ArrayUtils.contains(asynclist.scopes(), this) || this == ANY )) return true; 
        return result;
    }
    
    
    public static boolean isInScope( AwardSyncable syncAnnotation, AwardTemplateSyncScope[] scopes ) {
        return isInScope( syncAnnotation.scopes(), scopes );
    }
    
    public static boolean isInScope( AwardSyncableList syncAnnotation, AwardTemplateSyncScope[] scopes ) {
        return isInScope( syncAnnotation.scopes(), scopes );
    }
    
    public static boolean isInScope( AwardTemplateSyncScope[] propertyScopes,  AwardTemplateSyncScope[] appliedScopes ) {
        boolean result = ArrayUtils.contains(propertyScopes, AwardTemplateSyncScope.ANY );
        if(result) return result;
        for( AwardTemplateSyncScope scope : appliedScopes ) {
            if( ArrayUtils.contains(propertyScopes, scope) ) return true;
        }
        return result;
    } 
    
    public boolean isInScope( AwardTemplateComment comment ) {
        String commentTypeCode = comment.getCommentTypeCode();
        boolean result =isAwardCommentInScope( commentTypeCode );
        if( LOG.isDebugEnabled() )
            LOG.debug(String.format( "%s.isInScope for AwardTemplateComment ( commentTypeCode = %s ) returns %s.",  this, comment.getCommentTypeCode(), result ));
        return result;
    }
    
    public static boolean isInScope( AwardTemplateComment comment, AwardTemplateSyncScope[] scopes ) {
        for( AwardTemplateSyncScope scope : scopes ) {
            
            if( scope.isInScope(comment) ) { 
                return true;
            }
        }
        if( LOG.isDebugEnabled() )
            LOG.debug(String.format( "isInScope called for AwardTemplateComment ( commentTypeCode = %s ) with scopes = %s returning false.", comment.getCommentTypeCode(), ArrayUtils.toString(scopes) ));
        return false;
    }
    
    public boolean isInScope( AwardComment comment ) {
        String commentTypeCode = comment.getCommentTypeCode();
        boolean result =isAwardCommentInScope( commentTypeCode );
        if( LOG.isDebugEnabled() )
            LOG.debug(String.format( "%s.isInScope for AwardComment ( commentTypeCode = %s ) returns %s.",  this, comment.getCommentTypeCode(), result ));
        return result;
    }
    
    public static boolean isInScope( AwardComment comment, AwardTemplateSyncScope[] scopes ) {
        for( AwardTemplateSyncScope scope : scopes ) {
            
            if( scope.isInScope(comment) ) { 
                return true;
            }
        }
        if( LOG.isDebugEnabled() )
            LOG.debug(String.format( "isInScope called for AwardComment ( commentTypeCode = %s ) with scopes = %s returning false.", comment.getCommentTypeCode(), ArrayUtils.toString(scopes) ));
        return false;
    }

    
    //default implementation of a list object it to return true.
    //if an object has a narrower version of isInScope then that method
    //will be used, but it is assumed that unless otherwise specified a object
    //shall be syncd if the containing collection is being syncd.
    
    public static boolean isInScope( Object awardObject, AwardTemplateSyncScope[] scopes ) {
        if( LOG.isDebugEnabled() )
            LOG.debug(String.format( "default static isInScope called with scopes = %s", ArrayUtils.toString(scopes) ));
        for( AwardTemplateSyncScope scope: scopes ) {
            if( scope.isInScope(awardObject)) return true;
        }
        return false;
    }
    
    public boolean isInScope( Object awardObject ) {
        LOG.debug(String.format( "default %s.isInScope called on %s.", this, awardObject.getClass() ));
        
        
        return true;
    }

    public boolean isInScope( AwardTemplateReportTerm report ) {
        String reportCode = report.getReportCode();
        String reportClassCode = report.getReportClassCode();
        LOG.debug(String.format( "%s.isInScope called for AwardTemplateReportTerm ( code = %s, class = %s ).", this , reportCode, reportClassCode ));
        boolean result = isReportTermInScope( reportCode, reportClassCode ); 
        return result;
    }
    
    public static boolean isInScope( AwardTemplateReportTerm report, AwardTemplateSyncScope[] scopes ) {
        String reportCode = report.getReportCode();
        String reportClassCode = report.getReportClassCode();
        LOG.debug(String.format( "isInScope called for AwardTemplateReportTerm ( code = %s, reportTermClass = %s ) with scopes = %s", reportCode, reportClassCode,ArrayUtils.toString(scopes) ));
        for( AwardTemplateSyncScope scope : scopes ) {
            if( scope.isInScope(report) ) { 
                return true;
            }
        }
        LOG.debug(String.format( "isInScope called for AwardTemplateReportTerm ( code = %s, class = %s ) with scopes = %s returning false.", reportCode, reportClassCode, ArrayUtils.toString(scopes) ));
        return false;
    }
        
    public boolean isInScope( AwardReportTerm report ) {
        String reportCode = report.getReportCode();
        String reportClassCode = report.getReportClassCode();
        boolean result = isReportTermInScope( reportCode, reportClassCode ); 
        return result;
    }
    
    public static boolean isInScope( AwardReportTerm report, AwardTemplateSyncScope[] scopes ) {
         for( AwardTemplateSyncScope scope : scopes ) {
            if( scope.isInScope(report) ) { 
                return true;
            }
        }
        return false;
    }


    
    public static boolean isInScope( AwardSponsorContact sponsorContact, AwardTemplateSyncScope[] scopes ) {
        for( AwardTemplateSyncScope scope : scopes ) {
            if( scope.isInScope(sponsorContact) ) { 
                return true;
            }
        }
        if( LOG.isDebugEnabled() )
            LOG.debug(String.format( "isInScope called for AwardSponsorContact (contactType=%s) with scopes = %s returning false.", sponsorContact.getContactType().getContactTypeCode(),ArrayUtils.toString(scopes) ));
        return false;
    }

   
    public boolean isInScope( AwardSponsorContact sponsorContact ) {
        boolean result = false;
        if( this.equals(SPONSOR_CONTACTS_TAB)) result = true;
        return result;
    }

    
    public static boolean isInScope( AwardTemplateContact templateContact, AwardTemplateSyncScope[] scopes ) {
        for( AwardTemplateSyncScope scope : scopes ) {
            if( scope.isInScope(templateContact) ) { 
                return true;
            }
        }
        if( LOG.isDebugEnabled() )
            LOG.debug(String.format( "isInScope called for AwardTemplateContact (contactType=%s) with scopes = %s returning false.", templateContact.getContactType().getContactTypeCode(),ArrayUtils.toString(scopes) ));
        return false;
    }

   
    public boolean isInScope( AwardTemplateContact templateContact ) {
        boolean result = false;
        if( this.equals(SPONSOR_CONTACTS_TAB)) result = true;
        return result;
    }
    
    
    private boolean isReportTermInScope( String reportCode, String reportClassCode ) {
        boolean result = false;
        result = ArrayUtils.contains(getSyncScopeParameters("AwardReportTerm","reportClassCode"), reportClassCode );
        return result;
    }
    

    private boolean isAwardCommentInScope( String commentTypeCode ) {
        boolean result = false;
        result = ArrayUtils.contains(getSyncScopeParameters("AwardComment","commentTypeCode"), commentTypeCode );
        return result;
    }
    
    
    public String[] getSyncScopeParameters( String syncClass, String syncField ) {
        String[] values = { };
        try {
                String settingValue = KraServiceLocator
                .getService(ParameterService.class)
                .getParameterValueAsString(AwardDocument.class,String.format( "scope.sync.%s.%s.%s", this.toString(),syncClass,syncField));
                values = settingValue == null?new String[] {}: StringUtils.split(settingValue,",");
        } catch (Error e ) {
            LOG.error( String.format( "Error returned from parameter lookup scope.sync.%s.%s.%s failed, defaulting to empty list.  Error: %s", this.toString(),syncClass,syncField, e.getMessage() ));
        }
        return values;
    }
           
        
    
    
}
