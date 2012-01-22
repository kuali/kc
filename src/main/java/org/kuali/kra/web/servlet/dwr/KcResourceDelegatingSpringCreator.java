
/*
 * Copyright 2007 The Kuali Foundation
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
package org.kuali.kra.web.servlet.dwr;

import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.kns.web.servlet.dwr.GlobalResourceDelegatingSpringCreator;

public class KcResourceDelegatingSpringCreator extends GlobalResourceDelegatingSpringCreator {
	
	@Override
	public Object getInstance() throws InstantiationException {
		Object bean = KraServiceLocator.getService(this.getBeanName());
		if (bean == null) {
			bean = super.getInstance();
			if (bean == null) {
				throw new InstantiationException("Unable to find bean " + this.getBeanName() + " in Global Resource Loader");
			}
		}
		return bean;
	}

}
