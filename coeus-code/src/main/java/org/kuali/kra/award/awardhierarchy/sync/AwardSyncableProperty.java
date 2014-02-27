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
package org.kuali.kra.award.awardhierarchy.sync;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation used to define the export of items for award sync.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface AwardSyncableProperty {
    /**
     * Is this property used as a key for this BO
     * @return
     */
    boolean key() default false;
    
    /**
     * Does this property identify a parent relationship that should be followed
     * as part of the sync export.
     * @return
     */
    boolean parent() default false;
    
    /**
     * Required if on a parent property {@link #parent}. Identifies the name
     * of the property the parent has to relate to this child element.
     * @return
     */
    String parentProperty() default "";
}
