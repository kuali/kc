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
package org.kuali.kra.award.bo;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * This is an Annotation to represent any award syncable list properties
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface AwardSyncableList {
    public static final String DEFAULT_METHOD = "defaultMethod";
    /**
     * 
     * Set this property to the Class within a list
     * For eg: for List<AwardComment> awardComments, it should be set to AwardComment.class
     *
     */
    Class    syncClass();
    /**
     * Set this property to the variable name representing its parent.
     * 
     */
    String   parentPropertyName() default "award";
    /**
     *If the sync functionality to be overwritten in the service, set the name of the method here and implement the same in service. 
     */
    String   syncMethodName() default DEFAULT_METHOD;
}
