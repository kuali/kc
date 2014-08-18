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
 * This Annotation represents award syncable property
 * The scopes attribute determines what scope the sync will happen if
 * a scope or scopes are specified by the call to sync the award.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface AwardSyncable {

    public static final String DEFAULT_PARENT_PROPERTY = "award";
    
    /**
     * The scope(s) where the sync should take place.    
     * 
     */
    AwardTemplateSyncScope[] scopes() default {};
    
    String   parentPropertyName() default DEFAULT_PARENT_PROPERTY;
    
    boolean impactSourceScopeEmpty() default true;
    
    
}
