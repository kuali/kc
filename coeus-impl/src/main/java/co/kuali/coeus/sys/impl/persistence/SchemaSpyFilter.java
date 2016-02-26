/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 *
 * Copyright 2005-2016 Kuali, Inc.
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
package co.kuali.coeus.sys.impl.persistence;

import net.sourceforge.schemaspy.Config;
import net.sourceforge.schemaspy.SchemaAnalyzer;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.coeus.sys.framework.util.HttpUtils;
import org.kuali.rice.kim.api.KimConstants;
import org.kuali.rice.kim.api.permission.PermissionService;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.util.KRADConstants;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This filter generates html pages using schemaspy.  It only supports mysql and oracle.
 */
public class SchemaSpyFilter implements Filter {

    private static final Log LOG = LogFactory.getLog(SchemaSpyFilter.class);

    private static final Pattern MYSQL_DB_URL_PATTERN = Pattern.compile("(jdbc:mysql://)(.*)(:)(\\d*)(/)(.*)(\\?.*)");
    private static final Pattern ORACLE_DB_URL_PATTERN = Pattern.compile("(jdbc:oracle.thin:@)(.*)(:)(\\d*)(:)(.*)");
    private static final String DB_TYPE_FLAG = "-t";
    private static final String MYSQL_DB_TYPE = "mysql";
    private static final String ORACLE_DB_TYPE = "ora";
    private static final String ORACLE_THIN_DB_TYPE = "orathin";
    private static final String DB_HOST_FLAG = "-host";
    private static final String DB_PORT_FLAG = "-port";
    private static final String DP_DRIVER_LOCATION_FLAG = "-dp";
    private static final String DB_NAME_FLAG = "-db";
    private static final String DB_USER_FLAG = "-u";
    private static final String DB_PASSWORD_FLAG = "-p";
    private static final String OUTPUT_DIR_FLAG = "-o";
    private static final String KIM_SCHEMA_SPY_VIEW_ID = "schemaspy";
    private static final String LOGLEVEL_FLAG = "-loglevel";
    private static final String FINEST_LEVEL = "finest";
    private static final String CONFIG_LEVEL = "config";
    private static final String INFO_LEVEL = "info";
    private static final String WARNING_LEVEL = "warning";
    private static final String SEVERE_LEVEL = "severe";
    private static final String REFRESH_PARAM = "refresh";
    private static final String REFRESH_TRUE = "true";
    private static final String LOW_QUALITY_FLAG = "-lq";
    private static final String FORMAT_FLAG = "-format";
    private static final String SVG_FORMAT = "svg";
    private static final String RENDERER_FLAG = "-renderer";
    private static final String NO_RENDERER = "";
    private static final String NO_LOGO = "-nologo";
    private static final String MYSQL_PLATFORM_NAME = "MySQL";
    private static final String ORACLE_PLATFORM_NAME = "Oracle";
    private static final String ORACLE_9I_PLATFORM_NAME = "Oracle9i";
    private static final String ORACLE_THIN_CON_STR_FRAGMENT = "oracle:thin";
    private static final String SCHEMA_XML = "_schema.xml";

    private FilterConfig filterConfig;
    private PermissionService permissionService;
    private GlobalVariableService globalVariableService;
    private SchemaAnalyzer schemaAnalyzer;
    private boolean schemaSpyEnabled;
    private String datasourceUrl;
    private String dataSourceUsername;
    private String dataSourcePassword;
    private String dataSourcePlatform;
    private String datasourceDriverName;
    private String directoryName;

    private final AtomicBoolean initialized = new AtomicBoolean(false);
    private final Runnable refreshSchemaSpy = () -> {
        if (isSchemaSpyEnabled()) {
            LOG.info("Refresh SchemaSpy Started");

            initialized.set(false);

            deleteSchemaSpyContent();
            final List<String> argsList = createArgs();
            final String[] args = argsList.toArray(new String[argsList.size()]);
            final Config config = new Config(args);
            try {
                getSchemaAnalyzer().analyze(config);
            } catch (SQLException|IOException e) {
                throw new RuntimeException(e);
            }
            initialized.set(true);

            LOG.info("Refresh SchemaSpy Completed");
        }
    };


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        Executors.newSingleThreadExecutor().execute(refreshSchemaSpy);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        final UserSession session = getGlobalVariableService().getUserSession() != null ?
                getGlobalVariableService().getUserSession() : (UserSession) ((HttpServletRequest) request).getSession().getAttribute(KRADConstants.USER_SESSION_KEY);
        if (session == null || !getPermissionService().isAuthorizedByTemplate(session.getPrincipalId(),
                KRADConstants.KRAD_NAMESPACE,
                KimConstants.PermissionTemplateNames.OPEN_VIEW,
                Collections.singletonMap(KimConstants.AttributeConstants.VIEW_ID, KIM_SCHEMA_SPY_VIEW_ID),
                Collections.<String, String>emptyMap())) {
            HttpUtils.disableCache((HttpServletResponse) response);
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        if (!isSchemaSpyEnabled()) {
            HttpUtils.disableCache((HttpServletResponse) response);
            response.getWriter().write("SchemaSpy has been disabled.");
            return;
        }

        synchronized (initialized) {
            if (REFRESH_TRUE.equals(request.getParameter(REFRESH_PARAM)) && initialized.get()) {
                Executors.newSingleThreadExecutor().execute(refreshSchemaSpy);
            }

            if (!initialized.get()) {
                HttpUtils.disableCache((HttpServletResponse) response);
                response.getWriter().write("Please wait. SchemaSpy is still processing.");
                return;
            }
        }

        if (requestingSchemaXml(((HttpServletRequest) request).getRequestURL())) {
            ((HttpServletResponse) response).sendRedirect(getSchemaXmlLocation(((HttpServletRequest) request).getRequestURL()));
            return;
        }

        chain.doFilter(request, response);
    }

    private boolean requestingSchemaXml(StringBuffer url) {
        return url.indexOf(SCHEMA_XML) != -1;
    }

    private String getSchemaXmlLocation(StringBuffer url) {
        int index = url.indexOf(SCHEMA_XML);
        return url.replace(index, index + SCHEMA_XML.length(), parseDatabase(getDatasourceUrl()) + (isOracle(getDatasourceUrl()) ? "." + getDataSourceUsername() : "") + ".xml").toString();
    }

    private List<String> createArgs() {
        final List<String> args = new ArrayList<>();


        args.add(DB_TYPE_FLAG);
        args.add(getDbType(getDatasourceUrl()));
        args.add(DB_HOST_FLAG);
        args.add(parseHost(getDatasourceUrl()));
        args.add(DB_PORT_FLAG);
        args.add(parsePort(getDatasourceUrl()));
        args.add(DP_DRIVER_LOCATION_FLAG);
        args.add(getDriverLocation());
        args.add(DB_NAME_FLAG);
        args.add(parseDatabase(getDatasourceUrl()));
        args.add(DB_USER_FLAG);
        args.add(getDataSourceUsername());
        args.add(DB_PASSWORD_FLAG);
        args.add(getDataSourcePassword());
        args.add(OUTPUT_DIR_FLAG);
        args.add(getSchemaSpyPath().toString());
        args.add(LOGLEVEL_FLAG);
        if (LOG.isTraceEnabled()) {
            args.add(FINEST_LEVEL);
        } else if (LOG.isDebugEnabled()) {
            args.add(CONFIG_LEVEL);
        } else if (LOG.isInfoEnabled()) {
            args.add(INFO_LEVEL);
        } else if (LOG.isWarnEnabled()) {
            args.add(WARNING_LEVEL);
        } else {
            args.add(SEVERE_LEVEL);
        }
        //high quality images take a long time to generate
        args.add(LOW_QUALITY_FLAG);

        //due to our large schema, dot never completes using png format
        //http://sourceforge.net/p/schemaspy/bugs/174/
        args.add(FORMAT_FLAG);
        args.add(SVG_FORMAT);
        args.add(RENDERER_FLAG);
        args.add(NO_RENDERER);
        args.add(NO_LOGO);
        return args;
    }

    private boolean isMySql() {
        return MYSQL_PLATFORM_NAME.equals(getDataSourcePlatform());
    }

    private boolean isOracle(String url) {
        return ORACLE_PLATFORM_NAME.equals(getDataSourcePlatform()) ||
                ORACLE_9I_PLATFORM_NAME.equals(getDataSourcePlatform()) && !url.contains(ORACLE_THIN_CON_STR_FRAGMENT);
    }

    private boolean isOracleThin(String url) {
        return ORACLE_PLATFORM_NAME.equals(getDataSourcePlatform()) ||
                ORACLE_9I_PLATFORM_NAME.equals(getDataSourcePlatform()) && url.contains(ORACLE_THIN_CON_STR_FRAGMENT);
    }

    private String getDbType(String url) {
        if (isMySql()) {
            return MYSQL_DB_TYPE;
        } else if (isOracleThin(url)) {
            return ORACLE_THIN_DB_TYPE;
        } else if (isOracle(url)) {
            return ORACLE_DB_TYPE;
        } else {
            throw new RuntimeException("unknown db type");
        }
    }

    private String getDriverLocation() {
        try {
            return Class.forName(getDatasourceDriverName()).getProtectionDomain().getCodeSource().getLocation().getPath();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private Pattern getUrlPattern() {
        return isMySql() ? MYSQL_DB_URL_PATTERN : ORACLE_DB_URL_PATTERN;
    }

    private String parseHost(String url) {
        Matcher m = getUrlPattern().matcher(url);
        if (m.matches()) {
            return m.group(2);
        }
        return null;
    }

    private String parsePort(String url) {
        Matcher m = getUrlPattern().matcher(url);
        if (m.matches()) {
            return m.group(4);
        }
        return null;
    }

    private String parseDatabase(String url) {
        Matcher m = getUrlPattern().matcher(url);
        if (m.matches()) {
            return m.group(6);
        }
        return null;
    }

    private Path getSchemaSpyPath() {
        return Paths.get(filterConfig.getServletContext().getRealPath(File.separator), getDirectoryName());
    }

    private void deleteSchemaSpyContent() {
        if (Files.exists(getSchemaSpyPath())) {
            try {
                FileUtils.forceDelete(getSchemaSpyPath().toFile());
            } catch (IOException e) {
                LOG.warn(e.getMessage(), e);
            }
        }
    }

    @Override
    public void destroy() {
        deleteSchemaSpyContent();
        filterConfig = null;
    }

    public SchemaAnalyzer getSchemaAnalyzer() {
        return schemaAnalyzer;
    }

    public PermissionService getPermissionService() {
        return permissionService;
    }

    public GlobalVariableService getGlobalVariableService() {
        return globalVariableService;
    }

    public void setPermissionService(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    public void setGlobalVariableService(GlobalVariableService globalVariableService) {
        this.globalVariableService = globalVariableService;
    }

    public void setSchemaAnalyzer(SchemaAnalyzer schemaAnalyzer) {
        this.schemaAnalyzer = schemaAnalyzer;
    }

    public boolean isSchemaSpyEnabled() {
        return schemaSpyEnabled;
    }

    public void setSchemaSpyEnabled(boolean schemaSpyEnabled) {
        this.schemaSpyEnabled = schemaSpyEnabled;
    }

    public String getDatasourceUrl() {
        return datasourceUrl;
    }

    public void setDatasourceUrl(String datasourceUrl) {
        this.datasourceUrl = datasourceUrl;
    }

    public String getDataSourceUsername() {
        return dataSourceUsername;
    }

    public void setDataSourceUsername(String dataSourceUsername) {
        this.dataSourceUsername = dataSourceUsername;
    }

    public String getDataSourcePassword() {
        return dataSourcePassword;
    }

    public void setDataSourcePassword(String dataSourcePassword) {
        this.dataSourcePassword = dataSourcePassword;
    }

    public String getDataSourcePlatform() {
        return dataSourcePlatform;
    }

    public void setDataSourcePlatform(String dataSourcePlatform) {
        this.dataSourcePlatform = dataSourcePlatform;
    }

    public String getDatasourceDriverName() {
        return datasourceDriverName;
    }

    public void setDatasourceDriverName(String datasourceDriverName) {
        this.datasourceDriverName = datasourceDriverName;
    }

    public String getDirectoryName() {
        return directoryName;
    }

    public void setDirectoryName(String directoryName) {
        this.directoryName = directoryName;
    }
}
