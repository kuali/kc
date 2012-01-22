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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.kuali.kra.award.awardhierarchy.AwardHierarchy;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.service.impl.adapters.BusinessObjectServiceAdapter;
import org.kuali.kra.service.impl.adapters.DocumentServiceAdapter;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.bo.DocumentHeader;
import org.kuali.rice.krad.bo.PersistableBusinessObject;
import org.kuali.rice.krad.document.Document;

public class IrbMockServicesHelper {

    public static class MockBusinessObjectService extends BusinessObjectServiceAdapter {
        Map<String, AwardHierarchy> awardHierarchyMap = new TreeMap<String, AwardHierarchy>();
        Map<String, Award> awardMap = new TreeMap<String, Award>();
        Map<Long, InstitutionalProposal> institutionalProposalMap = new TreeMap<Long, InstitutionalProposal>();

        @SuppressWarnings("unchecked")
        @Override
        public PersistableBusinessObject findByPrimaryKey(Class klass, Map identifiers) {
            if(klass.equals(Award.class)) {
                String awardNumber = (String) identifiers.get("awardNumber");
                return awardNumber != null ? awardMap.get(awardNumber) : null;
            }  else if(klass.equals(AwardHierarchy.class)) {
                String awardNumber = (String) identifiers.get("awardNumber");
                return awardNumber != null ? awardHierarchyMap.get(awardNumber) : null;
            } else if(klass.equals(InstitutionalProposal.class)) {
                String proposalId = (String) identifiers.get("proposalId");                
                return proposalId != null ? institutionalProposalMap.get(Long.valueOf(proposalId)) : null;
            }
            return null;
        }

        @Override
        public PersistableBusinessObject save(PersistableBusinessObject bo) {
            if(bo instanceof AwardHierarchy) {
                AwardHierarchy awardHierarchy = (AwardHierarchy) bo;
                awardHierarchyMap.put(awardHierarchy.getAwardNumber(), awardHierarchy);
            }
            if(bo instanceof Award) {
                Award award = (Award) bo;
                award.setAwardId(award.getAwardId() == null ? 1L : award.getAwardId() + 1);
                awardMap.put(award.getAwardNumber(), award);
            } else if (bo instanceof InstitutionalProposal) {
                InstitutionalProposal instProp = (InstitutionalProposal) bo;
                instProp.setProposalId(instProp.getProposalId() == null ? 1L : instProp.getProposalId());
                institutionalProposalMap.put(instProp.getProposalId(), instProp);
            }
            
            return bo;
        }

        @Override
        public List<? extends PersistableBusinessObject> save(List<? extends PersistableBusinessObject> bizObjects) {
            for(Object bo: bizObjects) {
                save((PersistableBusinessObject) bo);
            }
            
            return bizObjects;
        }

        @SuppressWarnings("unchecked")
        @Override
        public Collection findMatching(Class klass, Map fieldValues) {
            Collection c;
            if(klass.equals(AwardHierarchy.class)) {
                List<AwardHierarchy> awardHierarchyNodes = new ArrayList<AwardHierarchy>();
                awardHierarchyNodes.add(awardHierarchyMap.get(fieldValues.get("awardNumber")));
                c = awardHierarchyNodes;
            } else  if(klass.equals(Award.class)) {
                List<Award> awards = new ArrayList<Award>();
                awards.add(awardMap.get(fieldValues.get("awardNumber")));
                c = awards;
            }  else  if(klass.equals(InstitutionalProposal.class)) {
                List<InstitutionalProposal> proposals = new ArrayList<InstitutionalProposal>();
                proposals.add(institutionalProposalMap.get(fieldValues.get("proposalId")));
                c = proposals;
            } else if(klass.equals(DocumentHeader.class)) {
                c = new ArrayList<DocumentHeader>();
            } else {
                c = null;
            }
            return c;
        }

        /**
         * @see org.kuali.kra.service.impl.adapters.BusinessObjectServiceAdapter#findMatchingOrderBy(java.lang.Class, java.util.Map, java.lang.String, boolean)
         */
        @SuppressWarnings("unchecked")
        @Override
        public Collection findMatchingOrderBy(Class klass, Map fieldValues, String sortField, boolean sortAscending) {
            if(klass.equals(AwardHierarchy.class)) {
                String parentAwardNumber = (String) fieldValues.get("parentAwardNumber");
                Map<String, AwardHierarchy> matching = new TreeMap<String, AwardHierarchy>();
                for(AwardHierarchy node: awardHierarchyMap.values()) {
                    if(node.getParentAwardNumber().equals(parentAwardNumber)) {
                        matching.put(node.getAwardNumber(), node);
                    }
                }
                List<AwardHierarchy> list = new ArrayList<AwardHierarchy>();
                for(String awardNumber: matching.keySet()) {
                    list.add(matching.get(awardNumber));
                }
                return list;
            } else  if(klass.equals(Award.class)) {
                List<Award> list = new ArrayList<Award>();
                list.add(awardMap.get(fieldValues.get("awardNumber")));
                return list;
            } else  if(klass.equals(InstitutionalProposal.class)) {
                List<InstitutionalProposal> list = new ArrayList<InstitutionalProposal>();
                list.add(institutionalProposalMap.get(fieldValues.get("awardNumber")));
                return list;
            }
            return null;
        }
    }

    static class MockDocumentService extends DocumentServiceAdapter {
        @Override
        public Document getByDocumentHeaderId(String documentHeaderId) throws WorkflowException {
            return null;
        }

        @SuppressWarnings("unchecked")
        @Override
        public Document getNewDocument(Class documentClass) throws WorkflowException {
            return null;
        }

    }
}
