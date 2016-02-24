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
package org.kuali.kra.irb.actions.submit;


public interface ProtocolActionAjaxService extends org.kuali.kra.protocol.actions.ProtocolActionAjaxService {
    

    /**
     * Get the valid upcoming committee dates for scheduling a protocol.
     * This method is used exclusively by DWR for obtaining a list to show
     * to the end user in a drop-down menu.  To handle the conversion from
     * Java to JavaScript, the list is returned as a comma separated string.
     * The format is: <key1>;<description1>;<key2>;<description2>;...
     * @param committeeId the committee's unique id
     * @return the string representation of schedule dates
     */
    public String getValidCommitteeDates(String committeeId, String docFormKey);
}
