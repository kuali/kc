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
package org.kuali.coeus.common.committee.impl.service;

import org.kuali.coeus.common.committee.impl.bo.CommitteeBatchCorrespondenceBase;

import java.sql.Date;

/**
 * 
 * This class generates the batch correspondence of committees.
 */
public interface CommitteeBatchCorrespondenceServiceBase {
    
    /**
     * 
     * This method generates the batch correspondence of a committee.
     * @param batchCorrespondenceTypeCode
     * @param startDate
     * @param endDate
     * @return CommitteeBatchCorrespondenceBase
     * @throws Exception 
     */
    CommitteeBatchCorrespondenceBase generateBatchCorrespondence(String batchCorrespondenceTypeCode, String committeeId, Date startDate, Date endDate) throws Exception;

}
