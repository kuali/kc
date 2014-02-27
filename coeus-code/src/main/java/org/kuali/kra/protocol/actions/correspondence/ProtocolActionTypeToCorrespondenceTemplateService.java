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
package org.kuali.kra.protocol.actions.correspondence;

import org.kuali.kra.protocol.correspondence.ProtocolCorrespondenceTemplateBase;

import java.util.List;

public interface ProtocolActionTypeToCorrespondenceTemplateService {
    /**
     * 
     * This method maps a protocol action type to a protocol correspondence template, and returns a list of ProtocolCorrespondenceTemplateBase objects.
     * @param protocolActionType a ProtocolActionType String
     * @param committeeId a Committee id
     * @return a list of ProtocolCorrespondenceTemplateBase objects tied to a committee.
     */
    List<ProtocolCorrespondenceTemplateBase> getTemplatesByProtocolAction(String protocolActionType, String committeeId); 

    /**
     * This method maps a protocol action type to a protocol correspondence template, and returns a list of ProtocolCorrespondenceTemplateBase objects.
     * @param protocolActionType a ProtocolActionType String
     * @return a list of ProtocolCorrespondenceTemplateBase DEFAULT objects.
     */
    List<ProtocolCorrespondenceTemplateBase> getTemplatesByProtocolAction(String protocolActionType); 

}
