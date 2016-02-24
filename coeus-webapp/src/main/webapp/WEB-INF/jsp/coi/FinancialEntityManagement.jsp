<%--
   - Kuali Coeus, a comprehensive research administration system for higher education.
   - 
   - Copyright 2005-2016 Kuali, Inc.
   - 
   - This program is free software: you can redistribute it and/or modify
   - it under the terms of the GNU Affero General Public License as
   - published by the Free Software Foundation, either version 3 of the
   - License, or (at your option) any later version.
   - 
   - This program is distributed in the hope that it will be useful,
   - but WITHOUT ANY WARRANTY; without even the implied warranty of
   - MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   - GNU Affero General Public License for more details.
   - 
   - You should have received a copy of the GNU Affero General Public License
   - along with this program.  If not, see <http://www.gnu.org/licenses/>.
--%>

<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<!DOCTYPE html>
<html>

    <head>
        <script>var jsContextPath = "${pageContext.request.contextPath}";</script>
        <title><bean:message key="app.title" /> :: $(headerTitle})</title>
        <style type="text/css">
            #workarea td.tab-subhead1
            {
		        font-weight: bold;
		        background-color: #939393;
		        height: 18px;
		        text-align: left;
		        border-left: 1px solid #999999;
		        color: #FFFFFF;
		        padding: 2px 6px;
		        border-bottom-width: 1px;
		        border-bottom-style: solid;
		        border-bottom-color: #B2B2B2;
            }
        </style>
    <c:forEach items="${fn:split(ConfigProperties.kns.css.files, ',')}"
        var="cssFile">
<c:if test="${fn:length(fn:trim(cssFile)) > 0}">
            <link href="${pageContext.request.contextPath}/${cssFile}"
                rel="stylesheet" type="text/css" />
</c:if>
</c:forEach>
    <c:forEach items="${fn:split(ConfigProperties.kns.javascript.files, ',')}"
        var="javascriptFile">
<c:if test="${fn:length(fn:trim(javascriptFile)) > 0}">
            <script language="JavaScript" type="text/javascript"
                src="${pageContext.request.contextPath}/${javascriptFile}"></script>
</c:if>
</c:forEach>
        <script type="text/javascript" src="scripts/jquery/jquery.tablesorter.js"></script> 
        <script type="text/javascript">
            var $j = jQuery.noConflict();
        </script>
    </head>
    <body onload="if ( !restoreScrollPosition() ) {  }"
			onKeyPress="return isReturnKeyAllowed('methodToCall.' , event);">
			
        <html:form styleId="kualiForm" action="/financialEntityManagement.do"
		    method="post" onsubmit="return hasFormAlreadyBeenSubmitted();">
            <c:set var="KualiForm" value="${KualiForm}" /> 
			<jsp:useBean id="KualiForm" type="org.kuali.rice.kns.web.struts.form.KualiForm" /> 
            <a name="topOfForm"></a>
            <div class="headerarea" id="headerarea">
                <h1>Financial Entities 
                    <kul:help parameterNamespace="KC-COIDISCLOSURE" parameterDetailType="Document" parameterName="financialEntityHelp" altText="help"/>
                </h1>
            </div>

        <!--TABBED TOP NAVIGATION-->
            <c:set var="readOnly" value="${KualiForm.readOnly}"  scope="request"/>
            <div class="horz-links-bkgrnd" id="horz-links">
                <div id="tabs">
                    <dl class="tabul">
                        <dt class="licurrent" >
                            <span class="tabright tabcurrent">
                                <input type="submit" name="methodToCall.management" value="Reporter" alt="Reporter">
                            </span>
                        </dt> 
                        <dt>
                            <span class="tabright">
                                <input type="submit" name="methodToCall.editNew" value="New Financial Entity" alt="New financial Entity">
                            </span>
                        </dt> 
                        <dt>
                            <span class="tabright">
                                <input type="submit" name="methodToCall.editList" value="My Financial Entities" alt="My Financial Entities">
                            </span>
                        </dt> 
                    </dl>
                </div>
            </div>
            
            <c:set var="errorKey" value="financialEntityHelper"/>
            <div class="msg-excol">
				<div class="left-errmsg">
					<kul:errorCount auditCount="${auditCount}"/>
					<c:if test="${!empty errorKey}">
						<kul:errors keyMatch="${errorKey}" errorTitle=" "/>
					</c:if>
					<c:if test="${empty errorKey}">
					    <kul:errors keyMatch="${Constants.GLOBAL_ERRORS}"
															errorTitle=" " />
					</c:if>
					<kul:messages/>
					<kul:lockMessages/>
				</div>
            </div>
            
            <div class="right">
                <div class="excol">
                   <html:image property="methodToCall.showAllTabs" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-expandall.gif" title="show all panel content" alt="show all panel content" styleClass="tinybutton" onclick="return expandAllTab();" />
                    <html:image property="methodToCall.hideAllTabs" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-collapseall.gif" title="hide all panel content" alt="hide all panel content" styleClass="tinybutton" onclick="return collapseAllTab();" />
                </div>
            </div>

            <table width="100%" cellpadding="0" cellspacing="0">
                <tr>
                    <td class="column-left"><img src="static/images/pixel_clear.gif" alt="" width="20" height="20"></td>
                    <td>
                        <div  align="center">
                            <div id="workarea">
                
            <!-- Tabbed Panel Head: reporter -->            
                                <kra-coi:financialEntityReporter />
            <!-- Tabbed Panel Head: new financial entity -->            
                                <%-- kra-coi:newFinancialEntity / --%>
           <!-- Tabbed Panel Head: financial entities  -->           
                                <%-- kra-coi:financialEntities / --%>
                        
                  
            <!-- Tabbed Panel Footer -->    
                                <div class="tab-container" align="center" id="G125" style="display: none;"></div>
                                <table width="100%" border="0" cellpadding="0" cellspacing="0" class="b3" summary="">
                                    <tr>
                                        <td align="left" class="footer"><img src="static/images/pixel_clear.gif" alt="" width="12" height="14" class="bl3"></td>
                                        <td align="right" class="footer-right"><img src="static/images/pixel_clear.gif" alt="" width="12" height="14" class="br3"></td>
                                    </tr>
                                </table>
                            </div> <%-- end workarea --%>
                
                
                            <div align="right"><br>
                              * required </div>
                  
                  
                  
                            <div id="globalbuttons" class="globalbuttons"> 

                                    <input type="image" name="methodToCall.saveFinancialEntityReporterUnits.anchor" src="kr/static/images/buttonsmall_submit.gif" class="globalbuttons" title="Submit" alt="Submit">
                                    <input type="image" name="methodToCall.management" src="kr/static/images/buttonsmall_refresh.gif" class="globalbuttons" title="Refresh" alt="Refresh">
			                        <input type="image" name="methodToCall.close" src="kr/static/images/buttonsmall_close.gif" class="globalbuttons" title="close" alt="close">

                            </div>
                        </div><%-- end aligncenter --%>
                    </td>
                    <td class="column-right"><img src="static/images/pixel_clear.gif" alt="" width="20" height="20"></td>
                </tr>
            </table>
            <div class="left-errmsg">
				<kul:errors displayRemaining="true"
						errorTitle="Other errors:"
						warningTitle="Other warnings:"
						infoTitle="Other informational messages:"/>
            </div>          
		
        </html:form>
        <div id="formComplete"></div> 
    </body>

    <SCRIPT type="text/javascript">
    var kualiForm = document.forms['KualiForm'];
    var kualiElements = kualiForm.elements;
    </SCRIPT> 
        <script type="text/javascript" src="scripts/financialEntity.js"></script> 
<script language="javascript" src="dwr/interface/RolodexService.js"></script>
<script language="javascript" src="dwr/interface/SponsorService.js"></script>

</html>
