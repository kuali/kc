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

import org.junit.Test;
import org.kuali.coeus.common.framework.print.Printable;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.printing.util.PrintingServiceTestBase;

import static org.junit.Assert.assertTrue;
public class ProtocolPrintingServiceTest extends PrintingServiceTestBase {
    private ProtocolPrintingService protocolPrintingService;

    @Test
    public void testGetProtocolPrintable() {

        Printable printable = getPrintingService().getProtocolPrintable(org.kuali.kra.protocol.actions.print.ProtocolPrintType.PROTOCOL_FULL_PROTOCOL_REPORT);
        assertTrue(printable instanceof ProtocolFullProtocolPrint);
        printable = getPrintingService().getProtocolPrintable(org.kuali.kra.protocol.actions.print.ProtocolPrintType.PROTOCOL_PROTOCOL_HISTORY_REPORT);
        assertTrue(printable instanceof ProtocolHistoryPrint);
        printable = getPrintingService().getProtocolPrintable(org.kuali.kra.protocol.actions.print.ProtocolPrintType.PROTOCOL_REVIEW_COMMENTS_REPORT);
        assertTrue(printable instanceof ProtocolReviewCommentsPrint);
        printable = getPrintingService().getProtocolPrintable(org.kuali.kra.protocol.actions.print.ProtocolPrintType.PROTOCOL_SUMMARY_VIEW_REPORT);
        assertTrue(printable instanceof ProtocolSummaryViewPrint);
    }

    private ProtocolPrintingService getPrintingService() {
        if (protocolPrintingService == null) {
            protocolPrintingService = KcServiceLocator.getService(ProtocolPrintingService.class);
        }
        return protocolPrintingService;
    }

}
