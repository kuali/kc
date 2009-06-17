/*
 * Copyright 2006-2009 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.budget.web.struts.form;

import java.io.Serializable;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;

public class BudgetJustificationWrapper implements Serializable {
    private String justificationText;        
    private String lastUpdateTime;
    private String lastUpdateUser;        
    
    private static DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
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
        this.lastUpdateTime = formatter.format(lastUpdateTime);
    }

    public void setJustificationText(String justificationText) {
        this.justificationText = justificationText;
    }
    
    public String toString() {
        Document document = DocumentHelper.createDocument();
        document.addElement("budgetJustification")
                .addAttribute("lastUpdateBy", lastUpdateUser)
                .addAttribute("lastUpdateOn", lastUpdateTime)
                .addCDATA(justificationText);
        return document.asXML();
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
        
        try {
            Document document = DocumentHelper.parseText(budgetJustificationAsXML);
            Node node = document.selectSingleNode("//budgetJustification");
            lastUpdateUser = node.valueOf("@lastUpdateBy");
            lastUpdateTime = node.valueOf("@lastUpdateOn");
            justificationText = node.getText();
        } catch (DocumentException e) {
            LOG.warn(e.getMessage(), e);
        }            
    }
}
