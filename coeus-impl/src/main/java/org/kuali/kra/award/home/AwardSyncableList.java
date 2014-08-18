/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.award.home;

import org.kuali.kra.award.AwardTemplateSyncScope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * This is an Annotation to represent any award syncable list properties
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface AwardSyncableList  {
    public static final String DEFAULT_METHOD = "defaultMethod";
    public static final String DEFAULT_PARENT_PROPERTY = "award";
    /**
     * 
     * Set this property to the Class within a list.  This should be set to the destination class.
     * For eg: for List<AwardComment> awardComments (in award ), it should be set to AwardComment.class
     *
     */
    Class    syncClass();
    
    /**
     * 
     * Set this property to the Class within the source list.
     * For eg: for List<AwardTemplateComment> in AwardTemplate, it should be set to AwardTemplateComment.class
     * It will default to Object.class.
     * 
     * This is used by the list sync methods to determine which isInScope method to call when syncing list objects.
     */
    Class    syncSourceClass() default Object.class;
    
    /**
     * Set this property to the variable name representing its parent.
     * 
     */
    String   parentPropertyName() default DEFAULT_PARENT_PROPERTY;
    
    /**
     *If the sync functionality to be overwritten in the service, set the name of the method here and implement the same in service. 
     */
    String   syncMethodName() default DEFAULT_METHOD;
    
    
    /**
     * The scope(s) where the sync should take place.
     * 
     */
    
    AwardTemplateSyncScope[] scopes() default {};
    
    /*
     * 
     */
    
    boolean removeMissingListElementsFromTarget() default true;
    
    /*
     * Do members of the list impact an empty within scope calculation.
     * If this is set to true, then this list has an impact on the source
     * is empty for the scopes.
     */
    boolean impactSourceScopeEmpty() default true;

}
