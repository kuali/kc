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
