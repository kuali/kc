package org.kuali.kra.common.web.struts.form;

/**
 * Since multiple forms must provide ReportHelperBeans, this interface defines a standard contract for them and offers polymorphic access to ReportHelperBean 
 */
public interface ReportHelperBeanContainer {
    ReportHelperBean getReportHelperBean();
}
