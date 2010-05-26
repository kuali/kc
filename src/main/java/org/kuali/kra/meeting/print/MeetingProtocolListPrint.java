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
package org.kuali.kra.meeting.print;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.printing.print.AbstractPrint;

public class MeetingProtocolListPrint extends AbstractPrint {


    // TODO : These classes all very similar, can we create a generic class
    // with template type property.  Then template type can be used to retrieve xsl file ?? 

    private static final long serialVersionUID = 7716321242999095190L;

    @Override
    public ResearchDocumentBase getDocument() {
        return document;
    }

    /**
     * This method fetches the XSL style-sheets required for transforming the
     * generated XML into PDF.
     * 
     * @return {@link ArrayList}} of {@link Source} XSLs
     */
    public List<Source> getXSLT() {
        // TODO : should we override Print's 'getXSLTemplates' method, instead of implementing this method.
        Source src = new StreamSource();
        ArrayList<Source> sourceList = new ArrayList<Source>();
        // TODO: cniesen - get template (create a service to get the template)
        sourceList.add(src);
        return sourceList;
    }



}
