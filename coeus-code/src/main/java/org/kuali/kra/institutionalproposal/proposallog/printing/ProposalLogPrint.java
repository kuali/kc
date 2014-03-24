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
package org.kuali.kra.institutionalproposal.proposallog.printing;

import org.kuali.coeus.common.framework.print.print.AbstractPrint;
import org.kuali.coeus.common.framework.print.util.PrintingUtils;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.kra.institutionalproposal.proposallog.service.ProposalLogPrintingService;

import javax.xml.transform.Source;
import java.util.ArrayList;
import java.util.List;

/**
 * This class provides the implementation for printing Print Certification
 * Report. It generates XML that conforms with Certification Report XSD, fetches
 * XSL style-sheets applicable to this XML, returns XML and XSL for any consumer
 * that would use this XML and XSls for any purpose like report generation, PDF
 * streaming etc.
 * 
 */
public class ProposalLogPrint extends AbstractPrint {

    @Override
	public List<Source> getXSLTemplates() {
		ArrayList<Source> sourceList = PrintingUtils
				.getXSLTforReport(ProposalLogPrintingService.PROPOSAL_LOG_REPORT_TYPE);
		return sourceList;
	}

    @Override
    public KcPersistableBusinessObjectBase getPrintableBusinessObject() {
        return null;
    }

}
