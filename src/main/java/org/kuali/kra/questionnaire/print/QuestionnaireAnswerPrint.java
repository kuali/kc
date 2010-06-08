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
package org.kuali.kra.questionnaire.print;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.printing.print.AbstractPrint;
import org.kuali.kra.printing.util.PrintingUtils;

/**
 * 
 * This class implements printable for questionnaire answer printing
 */
public class QuestionnaireAnswerPrint extends AbstractPrint {

    // TODO : can QuestionnaireAnswerPrint just extend or use QuestionnairePrint ??
    private static final long serialVersionUID = 3779580500239593471L;
    private static final String XSL_CONTEXT_DIR = "/org/kuali/kra/printing/stylesheet/";

    /**
     * Fetches the {@link ResearchDocumentBase}
     * 
     * @return {@link ResearchDocumentBase} document
     */
    public ResearchDocumentBase getDocument() {
        return document;
    }

    /**
     * This method fetches the XSL style-sheets required for transforming the
     * generated XML into PDF.
     * 
     * @return {@link ArrayList}} of {@link Source} XSLs
     */
    public List<Source> getXSLTemplates() {
        // TODO : more work to finish.  need feedback from offshore team
//        ArrayList<Source> sourceList = PrintingUtils
//        .getXSLTforReport(BudgetPrintType.BUDGET_TOTAL_REPORT
//                .getBudgetPrintType());
        Source src = new StreamSource(new PrintingUtils().getClass()
                .getResourceAsStream(XSL_CONTEXT_DIR + "/QuestionnaireReport.xsl"));
        List<Source> sourceList = new ArrayList<Source>();
        sourceList.add(src);
        return sourceList;
    }

}
