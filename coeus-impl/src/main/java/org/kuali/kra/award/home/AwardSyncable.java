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
