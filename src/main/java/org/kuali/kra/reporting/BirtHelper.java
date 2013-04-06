/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.kra.reporting;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.birt.core.exception.BirtException;
import org.eclipse.birt.report.engine.api.IGetParameterDefinitionTask;
import org.eclipse.birt.report.engine.api.IParameterDefnBase;
import org.eclipse.birt.report.engine.api.IParameterGroupDefn;
import org.eclipse.birt.report.engine.api.IReportEngine;
import org.eclipse.birt.report.engine.api.IReportRunnable;
import org.eclipse.birt.report.engine.api.IScalarParameterDefn;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.reporting.bo.BirtParameterBean;


public class BirtHelper {
    
    private static IReportEngine engine;
    
    public BirtHelper() throws Exception {
        BirtInstance birtInstance = BirtInstance.getInstance();
        engine = birtInstance.getIReportEngine();
    }
    
    public ArrayList getParameters(InputStream reportStream) throws Exception {
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


}