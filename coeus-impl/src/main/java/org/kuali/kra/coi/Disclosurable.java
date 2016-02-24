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
package org.kuali.kra.coi;

/**
 * 
 * This class are the common API for all projects that need coi disclosure
 */
public interface Disclosurable {
// TODO : this should keep expanding when we moving forward for coi implementation
    /**
     * 
     * This method is mainly to get the title
     * @return
     */
    String getProjectName();
    
    /**
     * 
     * This method to get project number
     * @return
     */
    String getProjectId();
}
