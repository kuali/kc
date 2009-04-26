/*
 * Copyright 2007 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS
 * IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 */
package org.kuali.kra.rice.shim;

import java.util.List;

import org.kuali.rice.core.lifecycle.Lifecycle;
import org.kuali.rice.test.RiceTestCase;
import org.kuali.rice.test.lifecycles.JettyServerLifecycle;
import org.kuali.rice.test.lifecycles.SQLDataLoaderLifecycle;
import org.kuali.rice.test.web.HtmlUnitUtil;


/**
 * Default test base for a full KNS enabled unit test.
 * 
 * @author Kuali Rice Team (kuali-rice@googlegroups.com)
 */
public abstract class KNSTestCase extends RiceTestCase {

	private String contextName = "/knstest";
	private String relativeWebappRoot = "/../kns/src/test/webapp";
    private String sqlFilename = "classpath:KNSDefaultTestData.sql";
    private String sqlDelimiter = ";";
	private String xmlFilename = "classpath:KNSDefaultTestData.xml";

	@Override
	protected List<Lifecycle> getSuiteLifecycles() {
		List<Lifecycle> lifecycles = super.getSuiteLifecycles();
		lifecycles.add(new Lifecycle() {
		    JettyServerLifecycle jettyLifecycle = null;
			boolean started = false;

			public boolean isStarted() {
				return this.started;
			}

			public void start() throws Exception {
				//ConfigFactoryBean.CONFIG_OVERRIDE_LOCATION = getTestConfigFilename();
                new SQLDataLoaderLifecycle(getSqlFilename(), getSqlDelimiter()).start();
                jettyLifecycle = new JettyServerLifecycle(getPort(), getContextName(), getRelativeWebappRoot());
                jettyLifecycle.start();
				//new KEWXmlDataLoaderLifecycle(getXmlFilename()).start();
				this.started = true;
			}

			public void stop() throws Exception {
			    if (jettyLifecycle != null && jettyLifecycle.isStarted()) {
			        jettyLifecycle.stop();
			    }
				this.started = false;
			}

		});
		return lifecycles;
	}

	@Override
	protected String getDerbySQLFileLocation() {
		return "classpath:db/derby/kns.sql";
	}

	@Override
	protected String getModuleName() {
		return "kns";
	}

	protected String getContextName() {
		return contextName;
	}

	protected void setContextName(String contextName) {
		this.contextName = contextName;
	}

	protected int getPort() {
		return HtmlUnitUtil.getPort();
	}

	protected String getRelativeWebappRoot() {
		return relativeWebappRoot;
	}

	protected void setRelativeWebappRoot(String relativeWebappRoot) {
		this.relativeWebappRoot = relativeWebappRoot;
	}

	protected String getXmlFilename() {
		return xmlFilename;
	}

	protected void setXmlFilename(String xmlFilename) {
		this.xmlFilename = xmlFilename;
	}

    protected String getSqlDelimiter() {
        return sqlDelimiter;
    }

    protected void setSqlDelimiter(String sqlDelimiter) {
        this.sqlDelimiter = sqlDelimiter;
    }

    protected String getSqlFilename() {
        return sqlFilename;
    }

    protected void setSqlFilename(String sqlFilename) {
        this.sqlFilename = sqlFilename;
    }
}