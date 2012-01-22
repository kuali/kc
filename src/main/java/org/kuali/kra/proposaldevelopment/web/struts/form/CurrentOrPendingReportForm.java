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
package org.kuali.kra.proposaldevelopment.web.struts.form;

import org.kuali.kra.common.web.struts.form.ReportHelperBean;
import org.kuali.kra.common.web.struts.form.ReportHelperBeanContainer;
import org.kuali.rice.kns.web.struts.form.KualiForm;

public class CurrentOrPendingReportForm extends KualiForm implements ReportHelperBeanContainer{
    
    private ReportHelperBean reportHelperBean;
    
    public CurrentOrPendingReportForm(){
        super();
        reportHelperBean = new ReportHelperBean();
    }
    
    public void setReportHelperBean(ReportHelperBean reportHelperBean) {
        this.reportHelperBean = reportHelperBean;
    }

    public ReportHelperBean getReportHelperBean() {
         return reportHelperBean;
    }

}
