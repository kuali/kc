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

import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.notification.IRBNotificationRenderer;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.actions.print.ProtocolPrintWatermarkBase;
import org.kuali.kra.protocol.notification.ProtocolNotificationRendererBase;

/**
 * 
 * This class for setting watermark to the protocol related reports.
 */
public class ProtocolPrintWatermark extends ProtocolPrintWatermarkBase {

    @Override
    protected ProtocolNotificationRendererBase getNewProtocolNotificationRendererInstanceHook(ProtocolBase protocol) {
        return new IRBNotificationRenderer((Protocol) protocol);
    }
}
