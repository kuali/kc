/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.protocol.personnel;

import java.util.List;

public interface ProtocolPersonnelService {

    /**
     * This method is to get principal investigator person
     * This method also helps to check whether at least one investigator exists in person list
     * Return first found investigator so that we can check for duplicate if any
     * @param protocolPersons
     * @return null if no investigator else ProtocolPerson as investigator
     */
    public ProtocolPerson getPrincipalInvestigator(List<ProtocolPerson> protocolPersons);

}
