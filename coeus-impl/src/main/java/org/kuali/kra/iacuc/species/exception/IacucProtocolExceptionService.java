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
package org.kuali.kra.iacuc.species.exception;

import org.kuali.kra.iacuc.IacucProtocol;

public interface IacucProtocolExceptionService {

    /**
     * This method adds Protocol Exception to the List of Protocol Exception.
     * @param protocol which contains list of ProtocolException.
     * @param ProtocolException object is added to ProtocolException list.
     */
    public abstract void addProtocolException(IacucProtocol protocol, IacucProtocolException protocolException);
    
    /**
     * This method is to get a new formatted protocol exception
     * @param protocol
     * @param protocolException
     * @return
     */
    public IacucProtocolException getNewProtocolException(IacucProtocol protocol, IacucProtocolException protocolException);

}
