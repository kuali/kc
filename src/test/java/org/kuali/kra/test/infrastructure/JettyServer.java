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
package org.kuali.kra.test.infrastructure;

import java.io.File;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;
import org.kuali.rice.core.api.lifecycle.Lifecycle;

public class JettyServer implements Lifecycle {
	
	private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory
			.getLog(JettyServer.class);
	
    /**
     * The name of an attribute we set in the ServletContext to indicate to the webapp
     * that it is running within unit tests, in case it needs to alter its configuration
     * or behavior.
     */
    public static final String JETTYSERVER_TESTMODE_ATTRIB = "JETTYSERVER_TESTMODE";

	private int port;
	private String contextName;	
	private String relativeWebappRoot;
	private Class servletClass;
	private Server server;
	private ServletContextHandler context;
	private boolean failOnContextFailure;

	/**
	 * Whether we are in test mode
	 */
	private boolean testMode = false;

	public JettyServer() {
		this(8080);
	}

	public JettyServer(int port) {
		this(port, null, null, null);
	}

	public JettyServer(int port, String contextName) {
		this(port, contextName, null, null);
	}
	
	public JettyServer(int port, String contextName, String relativeWebappRoot) {
        this(port, contextName, relativeWebappRoot, null);
	}	

    public JettyServer(int port, String contextName, Class servletClass) {
        this(port, contextName, null, servletClass);
    }   

    public JettyServer(int port, String contextName, String relativeWebappRoot, Class servletClass) {
        this.port = port;
        this.contextName = contextName;
        this.relativeWebappRoot = relativeWebappRoot;
        this.servletClass = servletClass;
    }   

    public void setTestMode(boolean t) {
	    this.testMode = t;
	}

	public boolean isTestMode() {
	    return testMode;
	}

	public Server getServer() {
		return server;
	}

	public ServletContextHandler getContext() {
	    return context;
	}

	public void start() throws Exception {
		server = createServer();
		server.start();
		if (isFailOnContextFailure() && contextStartupFailed()) {
			try {
				server.stop();
			} catch (Exception e) {
				LOG.warn("Failed to stop server after web application startup failure.");
			}
			throw new Exception("Failed to startup web application context!  Check logs for specific error.");
		}
	}

	public void stop() throws Exception {
		server.stop(); 
	}

	public boolean isStarted() {
		return server.isStarted();
	}

	protected Server createServer() {
		Server server = new Server(getPort());
		setBaseDirSystemProperty();
		if (useWebAppContext()) {
			File tmpDir = new File(System.getProperty("basedir") + "/target/jetty-tmp");
			tmpDir.mkdirs();
			WebAppContext webAppContext = new WebAppContext(System.getProperty("basedir") + getRelativeWebappRoot(), getContextName());
			webAppContext.setTempDirectory(tmpDir);
			webAppContext.setAttribute(JETTYSERVER_TESTMODE_ATTRIB, String.valueOf(isTestMode()));
			context = webAppContext;
			server.setHandler(context);
		} else {
			ServletContextHandler root = new ServletContextHandler(server,"/",ServletContextHandler.SESSIONS);
			root.addServlet(new ServletHolder(servletClass), getContextName());
			root.setAttribute(JETTYSERVER_TESTMODE_ATTRIB, String.valueOf(isTestMode()));
			context = root;
		}
		return server;
	}

	protected void setBaseDirSystemProperty() {
		if (System.getProperty("basedir") == null) {
			System.setProperty("basedir", System.getProperty("user.dir"));
		}
	}
	
	private boolean useWebAppContext() {
		return StringUtils.isNotBlank(this.relativeWebappRoot);
	}

	protected boolean contextStartupFailed() throws Exception {
        return !context.isAvailable();
	}
	
	public String getRelativeWebappRoot() {
		if (relativeWebappRoot == null) {
			return "/sampleapp/web-root";
		}
		return relativeWebappRoot;
	}

	public void setRelativeWebappRoot(String relativeWebappRoot) {
		this.relativeWebappRoot = relativeWebappRoot;
	}

	public String getContextName() {
		if (contextName == null) {
			return "/SampleRiceClient";
		}
		return contextName;
	}

	public void setContextName(String contextName) {
		this.contextName = contextName;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
	
	
	public boolean isFailOnContextFailure() {
		return this.failOnContextFailure;
	}

	public void setFailOnContextFailure(boolean failOnContextFailure) {
		this.failOnContextFailure = failOnContextFailure;
	}

	public String toString() {
	    return new ToStringBuilder(this).append("port", port)
	                                    .append("contextName", contextName)
	                                    .append("relativeWebappRoot", relativeWebappRoot)
                                        .append("servletClass", servletClass)
	                                    .toString();
	}

    public static void main(String[] args) {
        int port = args.length > 0 ? Integer.parseInt(args[0]) : 8080;
        String contextName = args.length > 1 ? args[1] : null;
        String relativeWebappRoot = args.length > 2 ? args[2] : null;
        try {
            new JettyServer(port, contextName, relativeWebappRoot).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
