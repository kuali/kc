package org.kuali.kra.dao;

import org.kuali.kra.common.printing.CurrentReportBean;
import org.kuali.rice.kew.exception.WorkflowException;

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