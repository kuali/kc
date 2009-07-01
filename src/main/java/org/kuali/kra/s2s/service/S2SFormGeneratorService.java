/*
 * Copyright 2008 The Kuali Foundation.
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
package org.kuali.kra.s2s.service;

import org.kuali.kra.s2s.S2SException;
import org.kuali.kra.s2s.generator.S2SFormGenerator;
import org.kuali.kra.s2s.generator.S2SGeneratorNotFoundException;

/**
 * 
 * This interface defines the service that is used to create instances of opportunity form generator classes.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public interface S2SFormGeneratorService {

    /**
     * 
     * This method is used to create and return a form generator instance. Based on the namespace provided as parameter, it
     * instantiates the respective generator class and returns it.
     * 
     * @param nameSpace {@link String}
     * @return S2SFormGenerator form generator instances corresponding to the name space.
     * @throws S2SException
     * @throws S2SGeneratorNotFoundException 
     * 
     */
    public abstract S2SFormGenerator getS2SGenerator(String nameSpace) throws S2SException, S2SGeneratorNotFoundException;

}
