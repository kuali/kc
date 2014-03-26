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
package org.kuali.kra.irb.actions.print;

import org.kuali.coeus.common.framework.print.Printable;
import org.kuali.kra.protocol.actions.print.ProtocolPrintType;


/**
 * 
 * This class is for Protocol print in protocol actions page
 */
public interface ProtocolPrintingService extends org.kuali.kra.protocol.actions.print.ProtocolPrintingService {


    /**
     * 
     * This method is to get the printable for the selected report.
     * @param reportType
     * @return
     */
    Printable getProtocolPrintable(ProtocolPrintType reportType);

}
