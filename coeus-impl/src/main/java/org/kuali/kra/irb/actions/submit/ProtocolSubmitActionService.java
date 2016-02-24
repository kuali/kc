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
package org.kuali.kra.irb.actions.submit;

import org.kuali.kra.irb.Protocol;

import java.util.List;

/**
 * Handles the processing of submitting a protocol to the IRB office.
 */
public interface ProtocolSubmitActionService {
    
    /**
     * Finds all submissions for the given protocolNumber and calculates how many total submissions it has overall.
     * @param protocol protocol
     * @return the total number of submissions for the given protocolNumber
     */
    int getTotalSubmissions(Protocol protocol);

    /**
     * Submit a protocol to the IRB office for review.
     * @param protocol the protocol
     * @param submitAction the submission data
     * @throws Exception 
     */
    void submitToIrbForReview(Protocol protocol, ProtocolSubmitAction submitAction) throws Exception;
    
    /**
     * 
     * This method for getting ProtocolSubmissionsLookup from given protocolNumber...
     * @return
     * @throws Exception
     */
   
    public List<ProtocolSubmission> getProtocolSubmissionsLookupData(List<ProtocolSubmission> protocolSbmissionList) throws Exception;
}
