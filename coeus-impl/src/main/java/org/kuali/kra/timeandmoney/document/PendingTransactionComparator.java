/*
 * Copyright 2005-2014 The Kuali Foundation
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
