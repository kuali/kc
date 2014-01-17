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
package org.kuali.kra.printing.print;

import org.kuali.kra.proposaldevelopment.bo.AttachmentDataSource;

/**
 * 
 * This class populates the bytes of PDF document to be generated
 */
public class PrintableAttachment extends AttachmentDataSource {
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1860670624193916246L;
    private byte[] streamData;

    public byte[] getContent() {
        return streamData;
    }

    public void setContent(byte[] streamData) {
        this.streamData = streamData;
    }
}
