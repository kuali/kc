/*
 * Copyright 2005-2010 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.s2s.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xmlbeans.XmlOptions;
import org.kuali.kra.s2s.S2SException;
import org.kuali.kra.s2s.formmapping.FormMappingInfo;
import org.kuali.kra.s2s.formmapping.FormMappingLoader;
import org.kuali.kra.s2s.generator.S2SFormGenerator;
import org.kuali.kra.s2s.generator.S2SGeneratorNotFoundException;
import org.kuali.kra.s2s.service.S2SFormGeneratorService;

/**
 * 
 * This class is used as a service implementation that is used to create instances of opportunity form generator classes. It
 * provides an abstraction level over the different generator class implementations.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class S2SFormGeneratorServiceImpl implements S2SFormGeneratorService {
    private static final Log LOG = LogFactory.getLog(S2SFormGeneratorServiceImpl.class);

    
    /**
     *
     * This method is used to create and return a form generator instance. Based on the namespace provided as parameter, it
     * instantiates the respective generator class and returns it.
     * 
     * @param nameSpace name space of the generator.
     * @return S2SFormGenerator form generator instances corresponding to the name space.
     * @throws S2SException if generator could not be loaded
     * @throws S2SGeneratorNotFoundException if form generator for given namespace is not available
     * @see org.kuali.kra.s2s.service.S2SFormGeneratorService#getS2SGenerator(java.lang.String)
     */
    public final S2SFormGenerator getS2SGenerator(String nameSpace) throws S2SException, S2SGeneratorNotFoundException {
        FormMappingInfo formInfo = new FormMappingLoader().getFormInfo(nameSpace);
        S2SFormGenerator formGenerator;
        try {
            formGenerator = (S2SFormGenerator) Class.forName(formInfo.getMainClass()).newInstance();
        }catch (InstantiationException e) {
            LOG.error("Cannot instantiate "+formInfo.getMainClass(), e);
            throw new S2SException(e);
        }catch (IllegalAccessException e) {
            LOG.error("Illegal access : "+formInfo.getMainClass(), e);
            throw new S2SException(e);
        }catch (ClassNotFoundException e) {
            LOG.error("No class found : "+formInfo.getMainClass(), e);
            throw new S2SException(e);
        }catch(Exception e){
            LOG.error("Unknown error from "+formInfo.getMainClass(), e);
            throw new S2SException("Could not generate form for "+formInfo.getMainClass(),e);
        }
        return formGenerator;
    }


    public XmlOptions getXmlOptionsPrefixes() {
        XmlOptions xmlOptions = new XmlOptions();
        Map<String, String> prefixMap = new HashMap<String, String>();
        prefixMap.put(
                "http://apply.grants.gov/system/MetaGrantApplication", "");
        prefixMap.put(
                "http://apply.grants.gov/forms/RR_SubawardBudget-V1.0",
                "RR_SubawardBudget");
        prefixMap.put(
                "http://apply.grants.gov/forms/RR_SubawardBudget-V1.1",
                "RR_SubawardBudget");
        prefixMap.put(
                "http://apply.grants.gov/forms/RR_SubawardBudget-V1.2",
                "RR_SubawardBudget");
        

        xmlOptions.setSaveSuggestedPrefixes(prefixMap);
        return xmlOptions;
    }
}
