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
package org.kuali.kra.common.committee.print;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.kuali.kra.iacuc.correspondence.IacucProtocolCorrespondenceTemplateService;
import org.kuali.kra.protocol.correspondence.ProtocolCorrespondenceTemplate;

/**
 * 
 * This class identifies the template print functionality for committee schedule reports.
 */
public class ScheduleTemplatePrint extends TemplatePrint {
    
    private IacucProtocolCorrespondenceTemplateService iacucProtocolCorrespondenceTemplateService;
    private static final long serialVersionUID = -1565960151556324475L;
    public static final String COMMON_SCHEDULE_TEMPLATE_PRINT_SPRING_NAME = "commonScheduleTemplatePrint";
    public static final String REPORT_PARAMETER_KEY = "protoCorrespTypeCode";

    @Override
    public String getProtoCorrespTypeCode() {
        return  (String) getReportParameters().get(REPORT_PARAMETER_KEY);
    }
    
    @Override
    public List<Source> getXSLTemplates() {
        Source src = new StreamSource();
        ArrayList<Source> sourceList = new ArrayList<Source>();
        ProtocolCorrespondenceTemplate template = getIacucProtocolCorrespondenceTemplateService().getProtocolCorrespondenceTemplate(getCommitteeId() , getProtoCorrespTypeCode());
        if (template != null) {
            src = new StreamSource(new ByteArrayInputStream(template.getCorrespondenceTemplate()));
            sourceList.add(src);
        }
        return sourceList;
    }
    
    public void setIacucProtocolCorrespondenceTemplateService(IacucProtocolCorrespondenceTemplateService iacucProtocolCorrespondenceTemplateService) {
        this.iacucProtocolCorrespondenceTemplateService = iacucProtocolCorrespondenceTemplateService;
    }
    
    public IacucProtocolCorrespondenceTemplateService getIacucProtocolCorrespondenceTemplateService() {
        return this.iacucProtocolCorrespondenceTemplateService;
    }

}
