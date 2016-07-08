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
package org.kuali.kra.s2s.backport;

public enum FormVersion {
    v1_0("1.0"),
    v1_1("1.1"),
    v1_2("1.2"),
    v1_3("1.3"),
    v1_4("1.4"),
    v1_6("1.6"),
    v2_0("2.0"),
    v2_1("2.1"),
    v3_0("3.0"),
    v3_1("3.1");

    private final String version;

    FormVersion(String version) {
        this.version = version;
    }

    public String getVersion() {
        return version;
    }
}
