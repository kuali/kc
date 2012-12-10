/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.irb;

import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.ProtocolFinderDaoOjbBase;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;

/**
 * The ProtocolFinderDao implementation for OJB.
 */
public class ProtocolFinderDaoOjb extends ProtocolFinderDaoOjbBase implements ProtocolFinderDao {

    @Override
    protected Class<? extends ProtocolBase> getProtocolBOClassHook() {
        return Protocol.class;
    }

    @Override
    protected Class<? extends ProtocolSubmissionBase> getProtocolSubmissionBOClassHook() {
        return ProtocolSubmission.class;
    }

  public Protocol findCurrentProtocolByNumber(String protocolNumber) {
      return (Protocol)super.findCurrentProtocolByNumber(protocolNumber);
  }
  
    
// TODO ********************** commented out during IRB backfit ************************    
//    private static final String PROTOCOL_NUMBER = "protocolNumber";
//    private static final String SEQUENCE_NUMBER = "sequenceNumber";
//    private static final String MAX_SEQUENCE_NUMBER = "max(" + SEQUENCE_NUMBER + ")";
//    private static final String SUBMISSION_NUMBER = "submissionNumber"; 
//
//    /**
//     * @see org.kuali.kra.irb.ProtocolFinderDao#findCurrentProtocolByNumber(java.lang.String)
//     */
//    public Protocol findCurrentProtocolByNumber(String protocolNumber) {
//        
//        Criteria subCrit = new Criteria();
//        subCrit.addEqualTo(PROTOCOL_NUMBER, protocolNumber);
//        ReportQueryByCriteria subQuery = QueryFactory.newReportQuery(Protocol.class, subCrit);
//        subQuery.setAttributes(new String[] { MAX_SEQUENCE_NUMBER });
//
//        Criteria crit = new Criteria();
//        crit.addEqualTo(PROTOCOL_NUMBER, protocolNumber);
//        crit.addEqualTo(SEQUENCE_NUMBER, subQuery);
//        Query q = QueryFactory.newQuery(Protocol.class, crit);
//
//        return (Protocol) getPersistenceBrokerTemplate().getObjectByQuery(q);
//    }
//    
//    
//    /**
//     * 
//     * @see org.kuali.kra.irb.ProtocolFinderDao#findProtocolSubmissions(java.lang.String, int)
//     */
//    @SuppressWarnings("unchecked")
//    public List<ProtocolSubmission> findProtocolSubmissions(String protocolNumber, int submissionNumber) {
//        
//        Criteria crit = new Criteria();
//        crit.addLike(PROTOCOL_NUMBER, protocolNumber + "%");
//        crit.addEqualTo(SUBMISSION_NUMBER, submissionNumber);
//        Query q = QueryFactory.newQuery(ProtocolSubmission.class, crit);
//
//        return (List<ProtocolSubmission>) getPersistenceBrokerTemplate().getCollectionByQuery(q);
//    }
//
//    /**
//     * 
//     * @see org.kuali.kra.irb.ProtocolFinderDao#findProtocols(java.lang.String)
//     */
//    @SuppressWarnings("unchecked")
//    public List<Protocol> findProtocols(String protocolNumber) {
//        
//        Criteria crit = new Criteria();
//        crit.addLike(PROTOCOL_NUMBER, protocolNumber + "%");
//        Query q = QueryFactory.newQuery(Protocol.class, crit);
//
//        return (List<Protocol>) getPersistenceBrokerTemplate().getCollectionByQuery(q);
//    }

}
