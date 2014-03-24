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
package org.kuali.kra.common.printing;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.kuali.coeus.common.framework.print.print.AbstractPrint;
import org.kuali.coeus.common.framework.print.util.PrintingUtils;

public class CorrespondencePrint extends AbstractPrint {
    private static final long serialVersionUID = -5905174316529503137L;
    private static final String XSL_CONTEXT_DIR = "/org/kuali/kra/printing/stylesheet/";

    /**
     * This method fetches the XSL style-sheets required for transforming the
     * generated XML into PDF.
     * 
     * @return {@link ArrayList}} of {@link Source} XSLs
     */
    public List<Source> getXSLTemplates() {
        List<Source> sourceList = new ArrayList<Source>();
        Object template = getReportParameters().get("template");
        if (template != null && ((byte[]) template).length > 0) {
            sourceList.add(new StreamSource(new ByteArrayInputStream((byte[]) template)));
        } else {
            Source src = new StreamSource(new PrintingUtils().getClass().getResourceAsStream(
                    XSL_CONTEXT_DIR + "/QuestionnaireReport.xsl"));
            sourceList.add(src);
        }
        return sourceList;
    }
}
