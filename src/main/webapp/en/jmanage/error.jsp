<%@ page import="org.jmanage.core.util.Loggers,
                 java.util.logging.Level"%>
 <%--
  Copyright 2004-2005 jManage.org

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
--%>
<%@ taglib uri="/WEB-INF/jmanage/tlds/jmanage/html.tld" prefix="jmhtml"%>
<%@ page isErrorPage="true" %>

<table cellspacing="0" cellpadding="5" width="600" class="table">
    <tr class="tableHeader">
        <td>Error</td>
    </tr>
    <tr>
        <td height="31">
            <jmhtml:errors />
            <%
                if(exception != null){
                    try {
                        Loggers.getLogger(Loggers.class).log(Level.SEVERE,
                                "Exception in JSP",
                                exception);
                    } catch (Exception e) {
                        e.printStackTrace();  //To change body of catch statement use Options | File Templates.
                    }
                    if(exception.getMessage() != null){
                        out.println(exception.getMessage());
                    }else{
                        out.println(exception.getClass().getName());
                    }
                    out.println("<br/>");
                    StackTraceElement[] stackTrace = exception.getStackTrace();
                    for(int i=0; i<stackTrace.length; i++){
                        out.println("&nbsp;&nbsp;&nbsp;&nbsp;at " +
                                stackTrace[i].toString());
                        out.println("<br/>");
                    }
                }
            %>
        </td>
    </tr>
</table>
