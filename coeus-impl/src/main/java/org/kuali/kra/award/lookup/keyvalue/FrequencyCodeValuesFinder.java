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
package org.kuali.kra.award.lookup.keyvalue;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.paymentreports.ValidClassReportFrequency;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.service.KeyValuesService;
import org.kuali.rice.krad.uif.control.UifKeyValuesFinderBase;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.*;

/**
 * 
 * This class is a values finder for <code>Frequency</code> business object.
 */
public class FrequencyCodeValuesFinder extends UifKeyValuesFinderBase {
    
    private String reportClassCode;
    private String reportCode;
    private KeyValuesService keyValuesService;
    

    public FrequencyCodeValuesFinder() {
        super();
    }
    
    /**
     * 
     * Constructs a FrequencyCodeValuesFinder.java.
     * @param reportClassCode
     * @param reportCode
     */
    public FrequencyCodeValuesFinder(String reportClassCode, String reportCode) {
        super();
        this.reportClassCode = reportClassCode;
        this.reportCode = reportCode;
    }
    
    /**
     * Constructs the list of Reports.  Each entry
     * in the list is a &lt;key, value&gt; pair, where the "key" is the unique
     * frequency code and the "value" is the textual description that is viewed
     * by a user. 
     * 
     * @return the list of &lt;key, value&gt; pairs of abstract types.  The first entry
     * is always &lt;"", "select"&gt;.
     * @see org.kuali.core.lookup.keyvalues.KeyValuesFinder#getKeyValues()
     */
    @Override
    public List<KeyValue> getKeyValues() {
        
        
        if (GlobalVariables.getUserSession().retrieveObject("awfreqr"+getReportClassCode()+"c"+getReportCode()) != null) {
            return (List<KeyValue>)GlobalVariables.getUserSession().retrieveObject("awfreqr"+getReportClassCode()+"c"+getReportCode());
        } else {

            Collection<ValidClassReportFrequency> validClassReportFrequencies 
            = (Collection<ValidClassReportFrequency>) getKeyValuesService().findAll(
                    ValidClassReportFrequency.class);
            return getKeyValues((Set<String>) 
                    getUniqueRelevantFrequencyCodes(validClassReportFrequencies));
        }
    }

    public String getReportClassCode() {
        return reportClassCode;
    }

    public void setReportClassCode(String reportClassCode) {
        this.reportClassCode = reportClassCode;
    }

    public String getReportCode() {
        return reportCode;
    }

    public void setReportCode(String reportCode) {
        this.reportCode = reportCode;
    }    
    
    /**
     * 
     * Wrapper method for retrieval of KeyValuesService
     * @return
     */
    protected KeyValuesService getKeyValuesService(){
        if(keyValuesService == null){
            keyValuesService = 
                (KeyValuesService) KcServiceLocator.getService("keyValuesService");
        }
        return keyValuesService;
    }
    
    /**
     * 
     * This method iterates through the validClassReportFrequency and puts the valid ones
     * in a set for another method to process. 
     * 
     * @param validClassReportFrequencies
     * @return
     */
    protected Set<String> getUniqueRelevantFrequencyCodes(
            Collection<ValidClassReportFrequency> validClassReportFrequencies){
        
        Set<String> uniqueRelevantFrequencyCodes
            = new HashSet<String>();
        
        for(ValidClassReportFrequency validClassReportFrequency: validClassReportFrequencies){
            if(StringUtils.equalsIgnoreCase(validClassReportFrequency.getReportClassCode()
                    ,getReportClassCode())
                  && StringUtils.equalsIgnoreCase(validClassReportFrequency.getReportCode(),getReportCode())){
                
                uniqueRelevantFrequencyCodes.add(validClassReportFrequency.getFrequencyCode());    
            }
        }
        
        return uniqueRelevantFrequencyCodes;
    }
    
    class FrequenceComparator implements Comparator
    {    
        public int compare(Object kv1, Object kv2 )
        {    
            try
            {
                String desc1 = ((KeyValue)kv1).getValue();
                String desc2 = ((KeyValue)kv2).getValue();
                if (desc1 == null)
                {
                    desc1 = "";
                }
                if (desc2 == null)
                {
                    desc2 = "";
                }
                return desc1.compareTo(desc2);  
            }
            catch (Exception e)
            {
                return 0;
            }
        }
        
    }
    
    /**
     * 
     * This method browses through set and creates the KeyValue list from it.
     * 
     * @param uniqueValidClassReportFrequencies
     * @return
     */
    protected List<KeyValue> getKeyValues(
            Set<String> uniqueValidClassReportFrequencies){
        
        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        ValidClassReportFrequency validClassReportFrequency = new ValidClassReportFrequency();
        for(String frequencyCode: uniqueValidClassReportFrequencies){
            if(frequencyCode!=null){
                validClassReportFrequency.setFrequencyCode(frequencyCode);
                validClassReportFrequency.refreshReferenceObject("frequency");
                keyValues.add(new ConcreteKeyValue(validClassReportFrequency.getFrequencyCode()
                        , validClassReportFrequency.getFrequency().getDescription()));    
            }
        }
        Collections.sort(keyValues, new FrequenceComparator());
        keyValues.add(0, new ConcreteKeyValue("","select"));
        GlobalVariables.getUserSession().addObject("awfreqr"+getReportClassCode()+"c"+getReportCode(), keyValues);
        return keyValues;
    }
}
