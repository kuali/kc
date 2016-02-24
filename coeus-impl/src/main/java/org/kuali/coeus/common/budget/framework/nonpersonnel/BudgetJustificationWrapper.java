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
package org.kuali.coeus.common.budget.framework.nonpersonnel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class BudgetJustificationWrapper implements Serializable {
    private String justificationText;        
    private String lastUpdateTime;
    private String lastUpdateUser;        
    private static final Log LOG = LogFactory.getLog(BudgetJustificationWrapper.class);
    
    public BudgetJustificationWrapper(String budgetJustificationAsXML) {
        super();
        parse(budgetJustificationAsXML);
    }
    
    public BudgetJustificationWrapper(Date lastUpdateTime, String lastUpdateUser, String justificationText) {
        super();
        this.justificationText = justificationText;
        this.lastUpdateUser = lastUpdateUser;
        setLastUpdateTime(lastUpdateTime);
    }
    
    public String getLastUpdateUser() {
        return lastUpdateUser;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public String getJustificationText() {
        return justificationText;
    }

    public void setLastUpdateUser(String lastUpdateUser) {
        this.lastUpdateUser = lastUpdateUser;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
    
    public void setLastUpdateTime(Date lastUpdateTime) {
        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
        this.lastUpdateTime = formatter.format(lastUpdateTime);
    }

    public void setJustificationText(String justificationText) {
        this.justificationText = justificationText;
    }
    
    public String toString() {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();

        try (Writer out = new StringWriter()) {
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document document = docBuilder.newDocument();

            Element rootElement = document.createElement("budgetJustification");
            rootElement.setAttribute("lastUpdateBy", lastUpdateUser);
            rootElement.setAttribute("lastUpdateOn", lastUpdateTime);
            document.appendChild(rootElement);

            CDATASection cdata = document.createCDATASection(justificationText == null ? "" : justificationText);
            rootElement.appendChild(cdata);

            Transformer tf = TransformerFactory.newInstance().newTransformer();
            tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            tf.setOutputProperty(OutputKeys.INDENT, "yes");
            tf.transform(new DOMSource(document), new StreamResult(out));
            return out.toString();
        } catch (ParserConfigurationException|TransformerException|IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
    * This method parses the raw budgetJustification String into the fields needed for this class
    * Expected format:
    * &lt;budgetJustification lastUpdateBy="" lastUpdateOn=""&gt;
    *   &lt;![CDATA[
    *     Justification text
    *   ]]&gt;
    * &lt;/justification&gt;
    */
    private void parse(String budgetJustificationAsXML) {
        if (budgetJustificationAsXML == null || budgetJustificationAsXML.trim().length() == 0) {
            return;
        }
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try (InputStream is = new ByteArrayInputStream(budgetJustificationAsXML.getBytes("utf-8"))) {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(new InputSource(is));
            Element node = document.getDocumentElement();
            lastUpdateUser = node.getAttribute("lastUpdateBy");
            lastUpdateTime = node.getAttribute("lastUpdateOn");
            justificationText = node.getTextContent();
        } catch (ParserConfigurationException|SAXException|IOException e) {
            LOG.warn("Unable to parse budget justification XML.", e);
        }
    }
}
