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
package org.kuali.kra.test.infrastructure;

import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;

public class KcSeleniumTestRunner extends KcUnitTestRunner {
    
    private static boolean listenerAdded = false;

    public KcSeleniumTestRunner(Class<?> klass) throws InitializationError {
        super(klass);
    }
    
    /**
     * Overridden to add listener to catch <code>testRunFinished</code> events. Not supposed to be here, but the "right way" to do
     * it is to add the listener to an instance of <code>JUnitCore</code> and then run all your tests using that. Doesn't work with
     * the multiple ways we run our tests including via Eclipse or within Hudson.
     * 
     * @see org.junit.runners.ParentRunner#run(org.junit.runner.notification.RunNotifier)
     */
    @Override
    public void run(RunNotifier notifier) {
        if (!listenerAdded) {
            notifier.addListener(KcSeleniumTestBase.getRunListener());
            listenerAdded = true;
        }
        super.run(notifier);
    }

}
