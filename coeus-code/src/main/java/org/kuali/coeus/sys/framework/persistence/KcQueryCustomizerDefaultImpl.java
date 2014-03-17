/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.coeus.sys.framework.persistence;

import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.metadata.CollectionDescriptor;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.Query;
import org.apache.ojb.broker.query.QueryByCriteria;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
/**
 * 
 * This class used for supplying customized query to OJB repository <code>collection-descriptor</code> tag.
 * Eg: 
 *      <code>
 *          <query-customizer
 *               class="org.kuali.kra.proposaldevelopment.dao.NarrativeQueryCustomizer">
 *             <attribute
 *                 attribute-name="narrativeType.narrativeTypeGroup"
 *                 attribute-value="P"
 *             />
 *           </query-customizer>
 *       </code>
 * @author KRADEV team
 * @version 1.0
 */
public class KcQueryCustomizerDefaultImpl extends org.apache.ojb.broker.accesslayer.QueryCustomizerDefaultImpl {
    private final Map<String,String> queryMap = new HashMap<String,String>();
    private final Map<String,String> proposalSystemQueryMap = new HashMap<String,String>();
    private ParameterService parameterService;
    
    /**
     * Looks up and returns the ParameterService.
     * @return the parameter service. 
     */
    protected ParameterService getParameterService() {
        if (this.parameterService == null) {
            this.parameterService = KcServiceLocator.getService(ParameterService.class);
        }
        return this.parameterService;
    }
    @Override
    public Query customizeQuery(Object anObject,
            PersistenceBroker aBroker,
            CollectionDescriptor aCod, QueryByCriteria aQuery){
        Iterator<String> keys = queryMap.keySet().iterator();
        Criteria crit = aQuery.getCriteria();
        while(keys.hasNext()){
            String key = keys.next();
            crit.addEqualTo(key, queryMap.get(key));
        }
        Iterator<String> systemKeys = proposalSystemQueryMap.keySet().iterator();
        while(systemKeys.hasNext()){
            String key = systemKeys.next();
            String value = proposalSystemQueryMap.get(key);
            String sysParamVal = this.getParameterService().getParameterValueAsString(ProposalDevelopmentDocument.class, value);
            crit.addEqualTo(key, sysParamVal==null?value:sysParamVal);
        }
        return aQuery;
    }

    /**
     * Override this method, if developer needs more than just supplying values to the 'where' clause
     * @see org.apache.ojb.broker.accesslayer.QueryCustomizerDefaultImpl#addAttribute(java.lang.String, java.lang.String)
     */
    public void addAttribute(String name, String value) {
        queryMap.put(name, value);
    }
    /**
     * Helper method to add system parameter values to customized query criteria
     * If customizer class needs to add proposal system parameter, override <code>addAttribute</code> method 
     * and call <code>addProposalSystemAttribute</code> method.
     * @param name
     * @param value
     */
    protected void addProposalSystemAttribute(String name, String value) {
        proposalSystemQueryMap.put(name, value);
    }
}
