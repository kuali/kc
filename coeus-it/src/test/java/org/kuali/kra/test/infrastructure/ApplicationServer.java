/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.kra.test.infrastructure;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.util.resource.ResourceCollection;
import org.eclipse.jetty.webapp.WebAppClassLoader;
import org.eclipse.jetty.webapp.WebAppContext;
import org.kuali.rice.core.api.lifecycle.Lifecycle;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.jar.Manifest;

public class ApplicationServer implements Lifecycle {

	private static final Log LOG = LogFactory
			.getLog(ApplicationServer.class);

	private final int port;
	private final String contextName;
	private final Collection<String> relativeWebappRoots;
    private Server server;
    private WebAppContext context;

    public ApplicationServer(int port, String contextName, Collection<String> relativeWebappRoots) {
        this.port = port;
        this.contextName = contextName;
        this.relativeWebappRoots = new ArrayList<>(relativeWebappRoots);
    }

    //need to expose the webapp's classloader
    //so rice resource loaders can be configured load
    //classes from the running webapp
	public ClassLoader getContextClassLoader() {
	    return context.getClassLoader();
	}

    @Override
	public void start() throws Exception {
		server = createServer();
		server.start();
	}

    @Override
	public void stop() throws Exception {
        server.stop();
    }

    @Override
	public boolean isStarted() {
		return server.isStarted();
	}

	private Server createServer() throws Exception {

        setBaseDirSystemProperty();

        final String baseDir = System.getProperty("basedir");
        final String tempDir = baseDir + "/target/jetty-tmp";

        LOG.info("baseDir: " + baseDir);
        LOG.info("tempDir: " + tempDir);
        LOG.info("relativeWebappRoot: " + relativeWebappRoots);
        LOG.info("contextName: " + contextName);
        LOG.info("port: " + port);
        LOG.info("Application Server: " + "Jetty " + Server.getVersion());

        final Collection<Resource> dirs = new ArrayList<Resource>() {{
            for (String root : relativeWebappRoots) {
                add(Resource.newResource(baseDir + root));
            }
        }};
        final ResourceCollection resources = new ResourceCollection();
        resources.setResources(dirs.toArray(new Resource[] {}));

        context = new WebAppContext();
        final Server server = new Server(port);
        server.setHandler(context);
        context.setServer(server);
        final File tmp = new File(tempDir);
        tmp.mkdirs();
        context.setTempDirectory(tmp);
        context.setBaseResource(resources);
        context.setContextPath(contextName);
        context.preConfigure();

        context.setClassLoader(createClassLoaderForJasper(context.getClassLoader()));
        return server;
	}

    /**
     * The jetty server's jsp compiler does not have access to the classpath artifacts to compile the jsps.
     * This method takes the current webapp classloader and creates one containing all of the
     * classpath artifacts on the test's classpath.
     *
     * See http://stackoverflow.com/questions/17685330/how-do-you-get-embedded-jetty-9-to-successfully-resolve-the-jstl-uri
     *
     * @param current the current webapp classpath
     * @return a classloader to replace it with
     * @throws IOException if an error occurs creating the classloader
     */
    private static ClassLoader createClassLoaderForJasper(ClassLoader current) throws IOException {
        // Replace classloader with a new classloader with all URLs in manifests
        // from the parent loader bubbled up so Jasper looks at them.
        final ClassLoader parentLoader = current.getParent();
        if (current instanceof WebAppClassLoader &&
                parentLoader instanceof URLClassLoader) {
            final LinkedList<URL> allURLs = new LinkedList<URL>(Arrays.asList(((URLClassLoader) parentLoader).getURLs()));

            for (URL url : ((LinkedList<URL>) allURLs.clone())) {
                try {
                    final URLConnection conn = new URL("jar:" + url.toString() + "!/").openConnection();
                    if (conn instanceof JarURLConnection) {
                        final JarURLConnection jconn = (JarURLConnection)conn;
                        final Manifest jarManifest = jconn.getManifest();
                        final String[] classPath = ((String)jarManifest.getMainAttributes().getValue("Class-Path")).split(" ");

                        for (String cpurl : classPath) {
                            allURLs.add(new URL(url, cpurl));
                        }
                    }
                } catch (IOException|NullPointerException e) {
                    //do nothing
                }
            }
            LOG.info("Creating new classloader for Application Server");
            return new WebAppClassLoader(new URLClassLoader(allURLs.toArray(new URL[]{}), parentLoader),
                            ((WebAppClassLoader)current).getContext());
        }
        LOG.warn("Cannot create new classloader for app server " + current);
        return current;
    }

	private void setBaseDirSystemProperty() {
		if (System.getProperty("basedir") == null) {
			System.setProperty("basedir", System.getProperty("user.dir"));
		}

        //hack for multi-module structure
        String baseDir = System.getProperty("basedir");
        if (baseDir != null && baseDir.contains("coeus-it")) {
            System.setProperty("basedir", baseDir.replace("coeus-it", ""));
        }
	}

    public int getPort() {
        return port;
    }

    public String getContextName() {
        return contextName;
    }

    public Collection<String> getRelativeWebappRoots() {
        return new ArrayList<>(relativeWebappRoots);
    }

    public String toString() {
	    return new ToStringBuilder(this).append("port", port)
	                                    .append("contextName", contextName)
	                                    .append("relativeWebappRoot", relativeWebappRoots)
	                                    .toString();
	}
}
