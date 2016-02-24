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
package org.kuali.kra.timeandmoney.document;

import java.util.Comparator;
import org.kuali.kra.timeandmoney.transactions.PendingTransaction;

public class PendingTransactionComparator implements Comparator<PendingTransaction> {

    public int compare(PendingTransaction arg0, PendingTransaction arg1) {
        // transactions ids are not generated until a save happens so there are going
        // to be null values in them.
        if (arg0.getTransactionId() == null && arg1.getTransactionId() == null){
            return 0;
        } else if (arg0.getTransactionId() != null && arg1.getTransactionId() != null){
            return arg0.getTransactionId().compareTo(arg1.getTransactionId());
        } else if (arg0.getTransactionId() == null) {
            return 1;
        } else {
            return -1;
        }
    }
}
