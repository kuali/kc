/*
 * Copyright 2007-2008 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.test;

import java.lang.reflect.Method;

/**
 * Interface to be implemented by test cases that want to be informed of the Method being
 * run.  
 * @author Kuali Rice Team (rice.collab@kuali.org)
 */
public interface MethodAware {
    public void setTestMethod(Method testMethod);
}
