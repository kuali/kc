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
package org.kuali.kra.irb.actions.acknowledgement;

import org.kuali.kra.irb.Protocol;

/**
 * 
 * This class is for IRB acknowledgement action.
 */
public interface IrbAcknowledgementService {

    /**
     * 
     * This method is called when submit for IRB acknowledgement.  It will update submission status and send out notification.
     * @param protocol
     * @param irbAcknowledgementBean
     * @throws Exception
     */
    void irbAcknowledgement(Protocol protocol, IrbAcknowledgementBean irbAcknowledgementBean) throws Exception;

}
