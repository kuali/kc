package org.kuali.kra.dao;

import java.util.List;

import org.kuali.kra.common.printing.CurrentReportBean;
import org.kuali.rice.kew.api.exception.WorkflowException;

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