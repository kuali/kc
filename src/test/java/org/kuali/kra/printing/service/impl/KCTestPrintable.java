/*
 * Copyright 2005-2010 The Kuali Foundation
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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.printing.Printable;
import org.kuali.kra.util.watermark.Watermarkable;
import org.kuali.rice.krad.bo.PersistableBusinessObjectBase;

public class KCTestPrintable extends PersistableBusinessObjectBase implements Printable {

    public List<Source> getXSLTemplates() {
        ArrayList<Source> ret = new ArrayList<Source>();
        File xsltfile = new File("src/main/webapp/static/printing/schemas/KCTestPrintable.xsl");
        ret.add(new StreamSource(xsltfile));
        return ret;
    }

    public Map<String, byte[]> renderXML() {
        Map<String, byte[]> xmlStreamMap = new LinkedHashMap<String, byte[]>();
        FileInputStream ret = null;
        File xmlfile = new File("src/main/webapp/static/printing/data/KCTestPrintableTestData.xml");
        try {
            ret = new FileInputStream(xmlfile);
            byte[] bytes = new byte[ret.available()];
            ret.read(bytes);
            xmlStreamMap.put("Bookmark", bytes);
        } catch (Exception e) {
            // TODO Auto-generated catch block 
            e.printStackTrace();
        }
        return xmlStreamMap;
    }

    public KraPersistableBusinessObjectBase getPrintableBusinessObject() {
        // TODO Auto-generated method stub 
        return null;
    }

    public Map<String, byte[]> getAttachments() {
        // TODO Auto-generated method stub 
        return null;
    }

    public Map<String, Source> getXSLTemplateWithBookmarks() {
        // TODO Auto-generated method stub 
        return null;
    }

    public boolean isWatermarkEnabled() {
        // TODO Auto-generated method stub 
        return false;
    }

    public Watermarkable getWatermarkable() {
        // TODO Auto-generated method stub 
        return null;
    }
}
