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
package org.kuali.coeus.common.questionnaire.framework.print;

import org.kuali.coeus.common.framework.print.AbstractPrint;
import org.kuali.coeus.common.framework.print.stream.xml.XmlStream;
import org.kuali.coeus.common.framework.print.util.PrintingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * This class is the printable of Questionnaire
 */
@Component("questionnairePrint")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class QuestionnairePrint extends AbstractPrint {

    private static final long serialVersionUID = -5905174316529503137L;
    private static final String XSL_CONTEXT_DIR = "/org/kuali/kra/printing/stylesheet";

    @Autowired
    @Qualifier("questionnaireXmlStream")
    @Override
    public void setXmlStream(XmlStream xmlStream) {
        super.setXmlStream(xmlStream);
    }

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
