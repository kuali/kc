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
package org.kuali.kra.irb.dao.ojb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.Query;
import org.apache.ojb.broker.query.QueryFactory;
import org.apache.ojb.broker.query.ReportQueryByCriteria;
import org.kuali.core.dao.ojb.PlatformAwareDaoBaseOjb;
import org.kuali.core.util.OjbCollectionAware;
import org.kuali.kra.irb.bo.Protocol;
import org.kuali.kra.irb.bo.ProtocolFundingSource;
import org.kuali.kra.irb.bo.ProtocolLocation;
import org.kuali.kra.irb.bo.ProtocolPerson;
import org.kuali.kra.irb.bo.ProtocolResearchArea;
import org.kuali.kra.irb.dao.ProtocolDao;

public class ProtocolDaoOjb extends PlatformAwareDaoBaseOjb implements OjbCollectionAware, ProtocolDao {

    
    public List<Protocol> getProtocols(Map<String,String> fieldValues) {
        Criteria crit = new Criteria();
        Map<String,Map<String,String>> critMap = setupCritMaps(fieldValues);
        String fundingSourceTypeCode = "1";
        Map<String,String> lookupNames = critMap.get("lookupNames");
        for (Entry<String, String> entry : lookupNames.entrySet()) {
            if (StringUtils.isNotBlank(entry.getValue())) {
                      crit.addLike(entry.getKey(), entry.getValue());
            }
        }
        if (!critMap.get("collectionNames").isEmpty()) {
            for (Entry<String, String> entry : critMap.get("collectionNames").entrySet()) {
                    crit.addExists(setupCollectionCriteria(entry));
            }
        }

        //Query q = QueryFactory.newQuery(ProtocolLookup.class, crit);
        Query q = QueryFactory.newQuery(Protocol.class, crit);
        
        return (List)getPersistenceBrokerTemplate().getCollectionByQuery(q);
    }

    private ReportQueryByCriteria setupCollectionCriteria(Entry<String, String> entry) {
        
        Criteria crit = new Criteria();
        crit.addEqualToField("protocolId", Criteria.PARENT_QUERY_PREFIX + "protocolId");
        Class clazz=null;
        if (entry.getKey().equals("fundingSource")) {
            clazz = ProtocolFundingSource.class;
            crit.addLike(entry.getKey(), entry.getValue().substring(1));
            crit.addEqualTo("fundingSourceTypeCode", entry.getValue().substring(0,1));
            
        } else if (entry.getKey().equals("personId") || entry.getKey().equals("principalInvestigatorId")) {
            // TODO : need to add criteria for "protocolPersonRoleId"  to distinguish between investigator & keyperson
            clazz = ProtocolPerson.class;
            setupProtocolPerson(entry, crit);
       } else if (entry.getKey().equals("performingOrganizationId")) {
            // protocollocation
            clazz = ProtocolLocation.class;
            crit.addLike("organizationId", entry.getValue());
            crit.addEqualTo("protocolOrganizationTypeCode", "1");
        } else {
            // researcharea
            clazz = ProtocolResearchArea.class;
            crit.addLike(entry.getKey(), entry.getValue());
        }
        return QueryFactory.newReportQuery(clazz, crit);

    }

    private void setupProtocolPerson(Entry<String, String> entry, Criteria crit) {
        List<String> investigatorRole = new ArrayList<String>();
        List<String> personRole = new ArrayList<String>();
        // TODO : only principal investigator
        investigatorRole.add("PI"); 
        //investigatorRole.add("COI");
        // TODO : what should be in personRole ?
        personRole.add("SP");
        personRole.add("CA");
        personRole.add("CRC");
        Class clazz = ProtocolPerson.class;
        if (entry.getValue().substring(0,1).equals("Y")) {
            crit.addLike("personId", entry.getValue().substring(1));
        } else {
            crit.addLike("rolodexId", entry.getValue().substring(1));
        }
        if (entry.getKey().equals("personId")) {
            crit.addIn("protocolPersonRoleId", personRole);
        } else {
            crit.addIn("protocolPersonRoleId", investigatorRole);
        }

    }
    
    private Map setupCritMaps(Map<String,String> fieldValues) {
        List<String> excludedFields = new ArrayList<String>();
        excludedFields.add("backLocation");
        excludedFields.add("docFormKey");
        excludedFields.add("personEmployeeIndicator");
        excludedFields.add("investigatorEmployeeIndicator");
        excludedFields.add("fundingSourceTypeCode");
        
        
        Map <String, Map> critMap = new HashMap();
        List <String> collectionFieldNames = new ArrayList();
        collectionFieldNames.add("personId");
        collectionFieldNames.add("principalInvestigatorId");
        collectionFieldNames.add("fundingSource");
        collectionFieldNames.add("researchAreaCode");
        collectionFieldNames.add("performingOrganizationId");
        //collectionFieldNames.add("fundingSourceTypeCode");
        Map<String,String> collectionFieldMap = new HashMap<String,String>();
        Map<String,String> lookupNamesMap = new HashMap<String,String>();
        for (Entry<String, String> entry : fieldValues.entrySet()) {
            if (!excludedFields.contains(entry.getKey())  && StringUtils.isNotBlank(entry.getValue())) { 
                String nameValue = entry.getValue().replace('*', '%');
                if (collectionFieldNames.contains(entry.getKey())) {
                    if (entry.getKey().equals("fundingSource")) {
                        // prefix value with fundingsourcetypecode. not good
                        collectionFieldMap.put(entry.getKey(), findFundingSourceTypeCode(fieldValues)+nameValue);
                    } else if (entry.getKey().equals("personId") || entry.getKey().equals("principalInvestigatorId")) {
                        // prefix value with fundingsourcetypecode. not good
                        collectionFieldMap.put(entry.getKey(), findEmployeeIndicator(entry.getKey(),fieldValues)+nameValue);
                    } else {
                        collectionFieldMap.put(entry.getKey(), nameValue);
                    }
                } else {
                    lookupNamesMap.put(entry.getKey(), nameValue);
                }
            }
        }

        critMap.put("collectionNames", collectionFieldMap);
        critMap.put("lookupNames", lookupNamesMap);
        return critMap;
    }
    
    private String findFundingSourceTypeCode(Map <String, String> fieldValues) {
        for (Entry<String, String> entry : fieldValues.entrySet()) {
             if (entry.getKey().equals("fundingSourceTypeCode")) {
                 return entry.getValue();
             }
        }    
        return null;
    }
    
    private String  findEmployeeIndicator(String key ,Map <String, String> fieldValues) {
        for (Entry<String, String> entry : fieldValues.entrySet()) {
            if ((key.equals("principalInvestigatorId") && entry.getKey().equals("investigatorEmployeeIndicator")) || (key.equals("personId") && entry.getKey().equals("personEmployeeIndicator"))) {
                return entry.getValue();
            }
       }    
       return null;

    }
}
