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
package org.kuali.coeus.common.questionnaire.impl;

import org.kuali.rice.krms.api.engine.TermResolver;
import org.kuali.rice.krms.api.repository.term.TermResolverDefinition;
import org.kuali.rice.krms.framework.type.TermResolverTypeService;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component("questionResolverTypeService")
public class QuestionResolverTypeServiceImpl implements TermResolverTypeService {
    
    public TermResolver<?> loadTermResolver(TermResolverDefinition termResolverDefinition) {
        Set<String> paramsSet = termResolverDefinition.getParameterNames();
        return new QuestionResolver(termResolverDefinition.getOutput().getName(), paramsSet);
    }

}
