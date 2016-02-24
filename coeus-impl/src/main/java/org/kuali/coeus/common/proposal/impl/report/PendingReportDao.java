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
package org.kuali.coeus.common.proposal.impl.report;

import org.kuali.coeus.common.framework.print.PendingReportBean;
import org.kuali.rice.kew.api.exception.WorkflowException;

import java.util.List;

/**
 * Contract for pending support DAO
 */
public interface PendingReportDao {
    /**
     * Loads the Pending Report data into a list of PendingReportBeans
     * @param personId - The person for whom pending support obligations are being queried
     * @return
     */
    List<PendingReportBean> queryForPendingSupport(String personId) throws WorkflowException;
}
