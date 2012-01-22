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
package org.kuali.kra.institutionalproposal.proposallog.printing;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Source;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.institutionalproposal.proposallog.service.ProposalLogPrintingService;
import org.kuali.kra.printing.print.AbstractPrint;
import org.kuali.kra.printing.util.PrintingUtils;

/**
 * This class provides the implementation for printing Print Certification
 * Report. It generates XML that conforms with Certification Report XSD, fetches
 * XSL style-sheets applicable to this XML, returns XML and XSL for any consumer
 * that would use this XML and XSls for any purpose like report generation, PDF
 * streaming etc.
 * 
 */
public class ProposalLogPrint extends AbstractPrint {

    /**
     * 
     * @see org.kuali.kra.printing.print.AbstractPrint#getXSLTemplates()
     */
    @Override
	public List<Source> getXSLTemplates() {
		ArrayList<Source> sourceList = PrintingUtils
				.getXSLTforReport(ProposalLogPrintingService.PROPOSAL_LOG_REPORT_TYPE);
		return sourceList;
	}

    @Override
    public KraPersistableBusinessObjectBase getPrintableBusinessObject() {
        return null;
    }

}
