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
package org.kuali.coeus.common.budget.impl.print;

import org.kuali.coeus.common.budget.framework.print.BudgetPrintType;
import org.kuali.coeus.common.framework.print.AbstractPrint;
import org.kuali.coeus.common.framework.print.stream.xml.XmlStream;
import org.kuali.coeus.common.framework.print.util.PrintingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.xml.transform.Source;
import java.util.ArrayList;
import java.util.List;

/**
 * This class provides the implementation for printing Budget Summary Report. It
 * generates XML that conforms with Summary Report XSD, fetches XSL style-sheets
 * applicable to this XML, returns XML and XSL for any consumer that would use
 * this XML and XSls for any purpose like report generation, PDF streaming etc.
 * 
 */
@Component("budgetSummaryPrint")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class BudgetSummaryPrint extends AbstractPrint {

    @Autowired
    @Qualifier("budgetSummaryXmlStream")
    @Override
    public void setXmlStream(XmlStream budgetSummaryXmlStream) {
        super.setXmlStream(budgetSummaryXmlStream);
    }

    /**
	 * This method fetches the XSL style-sheets required for transforming the
	 * generated XML into PDF.
	 * 
	 * @return {@link ArrayList}} of {@link Source} XSLs
	 */
	public List<Source> getXSLTemplates() {
		ArrayList<Source> sourceList = PrintingUtils
				.getXSLTforReport(BudgetPrintType.BUDGET_SUMMARY_REPORT
						.getBudgetPrintType());
		return sourceList;
	}


}
