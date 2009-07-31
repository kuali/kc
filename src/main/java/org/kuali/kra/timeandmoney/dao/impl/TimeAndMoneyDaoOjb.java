/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.timeandmoney.dao.impl;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.accesslayer.LookupException;
import org.kuali.kra.timeandmoney.dao.TimeAndMoneyDao;
import org.kuali.kra.timeandmoney.history.TimeAndMoneyActionSummary;
import org.kuali.rice.kns.dao.impl.PlatformAwareDaoBaseOjb;
import org.kuali.rice.kns.util.KualiDecimal;

public class TimeAndMoneyDaoOjb extends PlatformAwareDaoBaseOjb implements TimeAndMoneyDao {

    public void runScripts(List<TimeAndMoneyActionSummary> timeAndMoneyActionSummaryItems, String awardNumber) throws LookupException, SQLException{
        Statement stmt = null;
        PersistenceBroker pbInstance = getPersistenceBroker(true);
        TimeAndMoneyActionSummary timeAndMoneyActionSummary;
        
        try {
            stmt = pbInstance.serviceConnectionManager().getConnection().createStatement();
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT A.NOTICE_DATE, C.DESCRIPTION, B.OBLIGATED_CHANGE, B.AMOUNT_OBLIGATED_TO_DATE, ");
            sb.append("B.OBLIGATION_EXPIRATION_DATE, B.CURRENT_FUND_EFFECTIVE_DATE FROM AWARD_AMOUNT_TRANSACTION A, AWARD_AMOUNT_INFO B, AWARD_TRANSACTION_TYPE C ");
            sb.append("WHERE A.AWARD_NUMBER = B.AWARD_NUMBER AND A.TRANSACTION_ID = B.TNM_DOCUMENT_NUMBER AND A.AWARD_NUMBER = '");
            sb.append(awardNumber);
            sb.append("' AND A.TRANSACTION_TYPE_CODE = C.AWARD_TRANSACTION_TYPE_CODE");
            
            ResultSet rs = stmt.executeQuery(sb.toString());
            while(rs.next()){
                timeAndMoneyActionSummary = new TimeAndMoneyActionSummary();
                timeAndMoneyActionSummary.setNoticeDate(rs.getDate(1));
                timeAndMoneyActionSummary.setTransactionType(rs.getString(2));
                timeAndMoneyActionSummary.setChangeAmount(new KualiDecimal((BigDecimal)rs.getObject(3)));
                timeAndMoneyActionSummary.setObligationCumulative(new KualiDecimal((BigDecimal)rs.getObject(4)));
                timeAndMoneyActionSummary.setObligationEndDate(rs.getDate(5));
                timeAndMoneyActionSummary.setObligationStartDate(rs.getDate(6));
                timeAndMoneyActionSummaryItems.add(timeAndMoneyActionSummary);
            }
            
        }
        catch (LookupException e) {
            throw e;
        }
        catch (SQLException e) {
            throw e;
        }
    }
}
