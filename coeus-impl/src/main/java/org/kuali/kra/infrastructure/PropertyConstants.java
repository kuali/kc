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
package org.kuali.kra.infrastructure;


/**
 * A constants class containing constants relating to class properties.
 * 
 * @see org.kuali.kra.infrastructure.Constants
 * @see org.kuali.kra.infrastructure.KeyConstants
 */
public class PropertyConstants {

    public enum DOCUMENT {
        TYPE_NAME("documentTypeName");

        
        private String value;
        
        private DOCUMENT(String val) {
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
