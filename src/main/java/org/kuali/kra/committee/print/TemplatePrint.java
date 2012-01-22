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
package org.kuali.kra.committee.print;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.kuali.kra.irb.correspondence.ProtocolCorrespondenceTemplate;
import org.kuali.kra.irb.correspondence.ProtocolCorrespondenceTemplateService;
import org.kuali.kra.printing.print.AbstractPrint;

/**
 * This class provides the core for printing Committee reports from the appropriate template.
 * It generates XML that conforms with Certification Report XSD, fetches
 * XSL style-sheets applicable to this XML, returns XML and XSL for any consumer
 * that would use this XML and XSls for any purpose like report generation, PDF
 * streaming etc.
 * 
 */
public abstract class TemplatePrint extends AbstractPrint {

    private static final long serialVersionUID = -370310478073561152L;

    private String committeeId; 

    private ProtocolCorrespondenceTemplateService protocolCorrespondenceTemplateService;

    abstract public String getProtoCorrespTypeCode();
    /**
     * This method fetches the XSL style-sheets required for transforming the
     * generated XML into PDF.
     * 
     * @return {@link ArrayList}} of {@link Source} XSLs
     */
    public List<Source> getXSLTemplates() {
        Source src = new StreamSource();
        ArrayList<Source> sourceList = new ArrayList<Source>();
        ProtocolCorrespondenceTemplate template = protocolCorrespondenceTemplateService.getProtocolCorrespondenceTemplate(
                getCommitteeId() , getProtoCorrespTypeCode());
        if (template != null) {
            src = new StreamSource(new ByteArrayInputStream(template.getCorrespondenceTemplate()));
            sourceList.add(src);
        }
        return sourceList;
    }

    public String getCommitteeId() {
        return committeeId;
    }

    public void setCommitteeId(String committeeId) {
        this.committeeId = committeeId;
    }

    /**
     * Populated by Spring Beans.
     * @param protocolCorrespondenceTemplateService
     */
    public void setProtocolCorrespondenceTemplateService(ProtocolCorrespondenceTemplateService protocolCorrespondenceTemplateService) {
        this.protocolCorrespondenceTemplateService = protocolCorrespondenceTemplateService;
    }
}
