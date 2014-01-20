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
