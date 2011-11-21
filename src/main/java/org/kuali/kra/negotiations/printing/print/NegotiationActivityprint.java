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
package org.kuali.kra.negotiations.printing.print;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Source;

import org.kuali.kra.negotiations.printing.NegotiationActivityPrintType;
import org.kuali.kra.printing.print.AbstractPrint;
import org.kuali.kra.printing.util.PrintingUtils;

public class NegotiationActivityprint extends AbstractPrint {

    public List<Source> getXSLTemplates() {
        ArrayList<Source> sourceList = PrintingUtils
                .getXSLTforReport(NegotiationActivityPrintType.NEGOTIATION_ACTIVITY_REPORT
                        .getNegotiationActivityPrintType());
        return sourceList;
    }
}
