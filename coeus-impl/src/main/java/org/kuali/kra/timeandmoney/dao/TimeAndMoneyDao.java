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
package org.kuali.kra.timeandmoney.dao;

import org.kuali.kra.timeandmoney.document.TimeAndMoneyDocument;
import org.kuali.kra.timeandmoney.history.TimeAndMoneyActionSummary;
import org.kuali.kra.timeandmoney.history.TransactionDetail;

import java.util.List;

public interface TimeAndMoneyDao {
    
    List<TimeAndMoneyActionSummary> buildTimeAndMoneyActionSummaryForAward(String awardNumber);
    
    List<TimeAndMoneyDocument> getTimeAndMoneyDocumentForAwards(List<Long> awardIds);

    List<TransactionDetail> getTransactionDetailsForDocument(String documentNumber);

    TimeAndMoneyDocument getTimeAndMoneyDocument(String documentNumber);

}
