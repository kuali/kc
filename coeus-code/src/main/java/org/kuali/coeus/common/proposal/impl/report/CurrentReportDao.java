package org.kuali.coeus.common.proposal.impl.report;

import org.kuali.coeus.common.framework.print.CurrentReportBean;
import org.kuali.rice.kew.api.exception.WorkflowException;

import java.util.List;

/**
 * Contract for current support DAO
 */
public interface CurrentReportDao {
    /**
     * Loads the Current Report data into a list of CurrentReportBeans
     * @param personId - The person for whom current support obligations are being queried
     * @return
     */
    List<CurrentReportBean> queryForCurrentSupport(String personId) throws WorkflowException;
}