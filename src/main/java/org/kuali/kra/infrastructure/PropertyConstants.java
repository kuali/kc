/*
 * Copyright 2006-2009 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.infrastructure;


/**
 * A constants class containing constants relating to class properties.
 * 
 * @see org.kuali.kra.infrastructure.Constants
 * @see org.kuali.kra.infrastructure.KeyConstants
 */
public class PropertyConstants {

    /**
     * Property Constants relating to <code>{@link org.kuali.core.bo.Parameter}</code>
     * 
     * @see org.kuali.core.bo.Parameter
     */
    public enum PARAMETER {
        DETAIL_TYPE_CODE("parameterDetailTypeCode"),
        NAME("parameterName"),
        NAMESPACE_CODE("parameterNamespaceCode");

        
        private String value;
        
        private PARAMETER(String val) {
            value = val;
        }
        
        /**
         * 
         * @see java.lang.Enum#toString()
         */
        public String toString() {
            return value;
        }
    }
}
