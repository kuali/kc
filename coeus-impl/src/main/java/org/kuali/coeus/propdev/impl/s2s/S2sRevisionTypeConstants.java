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
package org.kuali.coeus.propdev.impl.s2s;

public final class S2sRevisionTypeConstants {
    public static final String INCREASE_AWARD = "A";
    public static final String INCREASE_AWARD_INCREASE_DURATION = "AC";
    public static final String INCREASE_AWARD_DECREASE_DURATION = "AD";
    public static final String DECREASE_AWARD = "B";
    public static final String DECREASE_AWARD_INCREASE_DURATION = "BC";
    public static final String DECREASE_AWARD_DECREASE_DURATION = "BD";
    public static final String INCREASE_DURATION = "C";
    public static final String DECREASE_DURATION = "D";
    public static final String OTHER = "E";

    private S2sRevisionTypeConstants() {
        throw new UnsupportedOperationException("do not call");
    }
}
