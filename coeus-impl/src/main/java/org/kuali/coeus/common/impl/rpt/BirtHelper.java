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
package org.kuali.coeus.common.impl.rpt;

import org.eclipse.birt.report.engine.api.*;
import org.eclipse.birt.report.model.api.ElementFactory;
import org.eclipse.birt.report.model.api.OdaDataSourceHandle;
import org.eclipse.birt.report.model.api.activity.SemanticException;
import org.eclipse.birt.report.model.elements.ReportDesign;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.core.api.config.property.Config;
import org.kuali.rice.core.api.config.property.ConfigurationService;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


public class BirtHelper {

    private static final String DATA_SOURCE = "org.eclipse.birt.report.data.oda.jdbc";

    private static IReportEngine engine;
    private static ConfigurationService configurationService;
    private static OdaDataSourceHandle dataSourceHandle;

    /**
     * Fetch input parameters from template.
     */ 
    public List<BirtParameterBean> getParameters(InputStream reportStream) throws Exception {

        final IReportRunnable design = getEngine().openReportDesign(reportStream);
        final IGetParameterDefinitionTask task = getEngine().createGetParameterDefinitionTask(design);
        final Collection<IScalarParameterDefn> defns = task.getParameterDefns(true);
        return defns.stream().map(scalar -> loadParameterDetails(task, scalar, design)).collect(Collectors.toList());
    }
    
    /**
     * set properties of parameters.
     */ 
    private BirtParameterBean loadParameterDetails(IGetParameterDefinitionTask task, IScalarParameterDefn scalar, IReportRunnable report) {
        
        final BirtParameterBean birtParameterBean = new BirtParameterBean();
        birtParameterBean.setName(scalar.getName());
        birtParameterBean.setHelp(scalar.getHelpText());
        birtParameterBean.setFormat(scalar.getDisplayFormat());
        birtParameterBean.setDefaultValue(scalar.getDefaultValue());
        birtParameterBean.setHidden(scalar.isHidden());
        birtParameterBean.setRequired(scalar.isRequired());
        birtParameterBean.setPromptText(scalar.getPromptText());

        switch (scalar.getControlType()) {
            case IScalarParameterDefn.TEXT_BOX:
                birtParameterBean.setControlType(Constants.TYPE_TEXT);
                break;
            default:
                birtParameterBean.setControlType(Constants.TYPE_TEXT);
                break;
        }

        switch (scalar.getDataType()) {
            case IScalarParameterDefn.TYPE_STRING:
                birtParameterBean.setDataType(Constants.STRING_TYPE);
                break;
            case IScalarParameterDefn.TYPE_DATE_TIME:
                birtParameterBean.setDataType(Constants.DATE_TIME_TYPE);
                break;
            default:
                birtParameterBean.setDataType(Constants.STRING_TYPE);
                break;
        }
        return birtParameterBean;
    }
    
    /**
     * sets the data source properties
     * @return OdaDataSourceHandle instance
     */
    private static OdaDataSourceHandle getNewDataSourceHandle() throws SemanticException, SQLException {
        
        final ElementFactory designFactory = new ElementFactory(new ReportDesign());
        final OdaDataSourceHandle newDataSourceHandle  = designFactory.newOdaDataSource(Constants.BIRT_DATA_SOURCE,DATA_SOURCE);

        final String odaDriverClassName = getConfigurationService().getPropertyValueAsString(Config.DATASOURCE_DRIVER_NAME);
        final String odaURL = getConfigurationService().getPropertyValueAsString(Config.DATASOURCE_URL);
        final String odaUser = getConfigurationService().getPropertyValueAsString(Config.DATASOURCE_USERNAME);
        final String odaPassword =  getConfigurationService().getPropertyValueAsString(Config.DATASOURCE_PASSWORD);

        newDataSourceHandle.setProperty("odaDriverClass", odaDriverClassName);
        newDataSourceHandle.setProperty("odaURL", odaURL);
        newDataSourceHandle.setProperty("odaUser", odaUser);
        newDataSourceHandle.setProperty("odaPassword", odaPassword);
        return newDataSourceHandle;
    }

    public static IReportEngine getEngine() {
        if (engine == null) {
            final BirtInstance birtInstance;
            try {
                birtInstance = BirtInstance.getInstance();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            engine = birtInstance.getIReportEngine();
        }

        return engine;
    }

    public static void setEngine(IReportEngine engine) {
        BirtHelper.engine = engine;
    }

    public static OdaDataSourceHandle getDataSourceHandle() throws SemanticException, SQLException {
        if (dataSourceHandle == null) {
            dataSourceHandle = getNewDataSourceHandle();

        }
        return dataSourceHandle;
    }

    public static void setDataSourceHandle(OdaDataSourceHandle dataSourceHandle) {
        BirtHelper.dataSourceHandle = dataSourceHandle;
    }

    public static ConfigurationService getConfigurationService() {
        if (configurationService == null) {
            configurationService =  KcServiceLocator.getService(ConfigurationService.class);
        }
        return configurationService;
    }

    public static void setConfigurationService(ConfigurationService configurationService) {
        BirtHelper.configurationService = configurationService;
    }
}
