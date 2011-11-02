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
package org.kuali.kra.coi.print;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Source;

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

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -6448549655237673021L;
    
    private String committeeId; 

    abstract public String getProtoCorrespTypeCode();
    /**
     * This method fetches the XSL style-sheets required for transforming the
     * generated XML into PDF.
     * 
     * @return {@link ArrayList}} of {@link Source} XSLs
     */
    public List<Source> getXSLTemplates() {
        ArrayList<Source> sourceList = new ArrayList<Source>();
        //TODO
       return sourceList;
    }

    public String getCommitteeId() {
        return committeeId;
    }

    public void setCommitteeId(String committeeId) {
        this.committeeId = committeeId;
    }

}
