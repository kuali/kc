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
package org.kuali.kra.irb.actions.correspondence;

import org.kuali.kra.printing.Printable;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.correspondence.ProtocolCorrespondenceTemplate;

/**
 * 
 * This class defines the functions needed to create protocol print out.
 */
public interface ProtocolXMLStreamService {
    /**
     * 
     * This method applies the correspondence template to the Protocol and returns an org.kuali.kra.printing.Printable Object.
     * @param protocol a Protocol object
     * @param template a ProtocolCorrespondenceTemplate object
     * @return
     */
    Printable getPrintableXMLStream(Protocol protocol, ProtocolCorrespondenceTemplate template);

}
