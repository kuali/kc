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
package org.kuali.coeus.common.impl.rpt;

import org.eclipse.birt.report.engine.api.*;
import org.eclipse.birt.report.model.api.ElementFactory;
import org.eclipse.birt.report.model.api.OdaDataSourceHandle;
import org.eclipse.birt.report.model.api.activity.SemanticException;
import org.eclipse.birt.report.model.elements.ReportDesign;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.core.framework.persistence.jdbc.datasource.XAPoolDataSource;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;


public class BirtHelper {
    
    private static IReportEngine engine;
    private static XAPoolDataSource xAPoolDataSource;
    private static OdaDataSourceHandle dataSourceHandle;
    private static final String DATA_SOURCE = "org.eclipse.birt.report.data.oda.jdbc";
    
    public BirtHelper() throws Exception {
        BirtInstance birtInstance = BirtInstance.getInstance();
        engine = birtInstance.getIReportEngine();
        xAPoolDataSource = getXAPoolDataSource();
    }
    
    /**
     * Fetch input parameters from  template
     * @param reportStream
     * @return List of BirtParameterBean instances
     * @throws Exception
     */ 
    public ArrayList<BirtParameterBean> getParameters(InputStream reportStream) throws Exception {
        IReportRunnable design = null;
        ArrayList<BirtParameterBean> listParameters = new ArrayList<BirtParameterBean>();        
        design = engine.openReportDesign(reportStream);        
        IGetParameterDefinitionTask task = engine.createGetParameterDefinitionTask(design);
        Collection params = task.getParameterDefns(true);        
        Iterator parameterIterator = params.iterator();
        while (parameterIterator.hasNext()) {
            IParameterDefnBase param = (IParameterDefnBase) parameterIterator.next();           
            IScalarParameterDefn scalar = (IScalarParameterDefn) param;
            listParameters.add(loadParameterDetails(task, scalar, design));            
        }
        return listParameters;
    }
    
    /**
     * set properties of parameters
     * @param iGetParameterDefinitionTask
     * @param iScalarParameterDefn
     * @param iReportRunnable
     * @return birtParameterBean
     */ 
    private BirtParameterBean loadParameterDetails(IGetParameterDefinitionTask task, IScalarParameterDefn scalar, IReportRunnable report) {
        
        BirtParameterBean birtParameterBean = new BirtParameterBean();
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
    
    public static IReportEngine getEngine() {
        return engine;
    }

    public static void setEngine(IReportEngine engine) {
        BirtHelper.engine = engine;
    }
    
    public static XAPoolDataSource getXAPoolDataSource() {
        if (xAPoolDataSource == null) {
            xAPoolDataSource =  KcServiceLocator.getService("dataSourceXAPool");
        }
        return xAPoolDataSource;
    }

    public static OdaDataSourceHandle getDataSourceHandle() throws SemanticException, SQLException {
        if (dataSourceHandle == null) {
           return getNewDataSourceHandle();
            
        }
        return dataSourceHandle;
    }
    
    /**
     * sets the data source properties
     * @return OdaDataSourceHandle instance
     */
    private static OdaDataSourceHandle getNewDataSourceHandle() throws SemanticException, SQLException {
        
        ElementFactory designFactory = new ElementFactory(new ReportDesign());
        dataSourceHandle  = designFactory.newOdaDataSource(Constants.BIRT_DATA_SOURCE,DATA_SOURCE);

        String odaDriverClassName = xAPoolDataSource.getDriverClassName();
        String odaURL = xAPoolDataSource.getUrl();
        String odaUser = xAPoolDataSource.getUsername();
        String odaPassword =  xAPoolDataSource.getPassword();
        
        dataSourceHandle.setProperty("odaDriverClass", odaDriverClassName);
        dataSourceHandle.setProperty("odaURL", odaURL);
        dataSourceHandle.setProperty("odaUser", odaUser);
        dataSourceHandle.setProperty("odaPassword", odaPassword);
        return dataSourceHandle;
    }

    public static void setDataSourceHandle(OdaDataSourceHandle dataSourceHandle) {
        BirtHelper.dataSourceHandle = dataSourceHandle;
    }
}