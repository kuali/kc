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
package org.kuali.kra.institutionalproposal.proposallog;

/**
 * This class is a set of common convenience methods used by Proposal Log related classes.
 */
public final class ProposalLogUtils {
    
    private ProposalLogUtils() {
        
    }
    
    public static String getProposalLogPendingStatusCode() {
        return "1";
    }
    
    public static String getProposalLogMergedStatusCode() {
        return "2";
    }
    
    public static String getProposalLogVoidStatusCode() {
        return "4";
    }
    
    public static String getProposalLogPermanentTypeCode() {
        return "1";
    }
    
    public static String getProposalLogTemporaryTypeCode() {
        return "2";
    }

}
