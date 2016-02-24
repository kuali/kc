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
package org.kuali.kra.award.cgb;

public class AwardCgbConstants {

	public static class InvoicingOptions {
        public enum Types {
            AWARD("1", "Invoice by Award Hierarchy"), ACCOUNT("2", "Invoice by Account"), CONTRACT_CONTROL("3", "Invoice by Contract Control Account");
            private String code;
            private String name;
            Types(String code, String name) {
                this.code = code;
                this.name = name;
            }
            public String getCode() {
                return code;
            }
            public String getName() {
                return name;
            }
            public static String get(String code) {
                for(Types type : Types.values()) {
                    if(type.getCode().equals(code)){
                        return type.getName();
                    }
                }
                return null;
            }
        }
	}
}
