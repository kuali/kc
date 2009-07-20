/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra.test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.junit.internal.runners.InitializationError;
import org.kuali.rice.test.runners.RiceUnitTestClassRunner;

public class KraUnitTestClassRunner extends RiceUnitTestClassRunner {

    public KraUnitTestClassRunner(final Class<?> testClass) throws InitializationError {
        super(testClass);
    }
    
    @Override
    protected List<Method> getTestMethods() {
        List<Method> runMethods = new ArrayList<Method>();  
        List<Method> allMethods = super.getTestMethods();
        
        for (Method eachMethod : allMethods) {
            if(eachMethod.getDeclaringClass().equals(getTestClass().getJavaClass())) {
                runMethods.add(eachMethod);
            }
        }
        return runMethods;
    }
}
