/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.printing.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.printing.Printable;
import org.kuali.rice.kns.bo.PersistableBusinessObjectBase;

public class KCTestPrintable extends PersistableBusinessObjectBase implements Printable {

    @Override
    protected LinkedHashMap toStringMapper() {
        // TODO Auto-generated method stub
        return null;
    }

    public ArrayList<Source> getXSLT() {
        ArrayList<Source> ret = new ArrayList<Source>();        
        File xsltfile = new File( "src/main/webapp/static/printing/schemas/KCTestPrintable.xsl");
        ret.add(new StreamSource(xsltfile));
        return ret;
        
    }

    public InputStream renderXML() {
        FileInputStream ret = null;
        File xmlfile = new File( "src/main/webapp/static/printing/data/KCTestPrintableTestData.xml");
        System.out.println("xmlfile="+xmlfile.getAbsolutePath());
        try {
            ret  = new FileInputStream(xmlfile);
        }
        catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return ret;
        
    }

    public ResearchDocumentBase getDocument() {
        // TODO Auto-generated method stub
        return null;
    }



}
