<%--
 Copyright 2005-2010 The Kuali Foundation

 Licensed under the Educational Community License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.osedu.org/licenses/ECL-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>

<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>

    <head>
        <script>var jsContextPath = "${pageContext.request.contextPath}";</script>
        <title>Kuali :: Financial Entities</title>
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
        <c:forEach items="${fn:split(ConfigProperties.css.files, ',')}" var="cssFile">
            <c:if test="${fn:length(fn:trim(cssFile)) > 0}">
                 <link href="${pageContext.request.contextPath}/${cssFile}"
                    rel="stylesheet" type="text/css" />
            </c:if>
        </c:forEach>
        <c:forEach items="${fn:split(ConfigProperties.javascript.files, ',')}" var="javascriptFile">
            <c:if test="${fn:length(fn:trim(javascriptFile)) > 0}">
                <script language="JavaScript" type="text/javascript"
                    src="${pageContext.request.contextPath}/${javascriptFile}"></script>
            </c:if>
        </c:forEach>
        <script type="text/javascript" src="scripts/jquery/jquery.js"></script> 
        <script type="text/javascript" src="scripts/jquery/jquery.tablesorter.js"></script> 
        <script type="text/javascript" src="scripts/jquery/jquery.fancybox-1.3.4.pack.js"></script>
        <link rel="stylesheet" type="text/css" href="scripts/jquery/fancybox/jquery.fancybox-1.3.4.css"" media="screen"/>
        
        <script type="text/javascript">
            var $j = jQuery.noConflict();
        	$j(document).ready(function() {
        		$j("a#history").fancybox({ 
        			'width':400,
        			'height':200,
        			'type':'iframe',
        			'autoScale':'false'
        			            		
        		});
        	})
        </script>
    </head>
    <body onload="if ( !restoreScrollPosition() ) {  }"
            onKeyPress="return isReturnKeyAllowed('methodToCall.' , event);">
            
        <html:form styleId="kualiForm" action="/financialEntityEditList.do"
            method="post" onsubmit="return hasFormAlreadyBeenSubmitted();">
            <c:set var="KualiForm" value="${KualiForm}" /> 
            <jsp:useBean id="KualiForm" type="org.kuali.rice.kns.web.struts.form.KualiForm" /> 
            <a name="topOfForm"></a>
            <div class="headerarea" id="headerarea">
                <h1>Financial Entities 
                    <a href="${pageContext.request.contextPath}/kr/help.do?methodToCall=getDocumentHelpText&amp;documentTypeName=CoiDisclosureDocument" tabindex="1000000" target="helpWindow"  title="[Help]document help"><img src="kr/static/images/my_cp_inf.gif" alt="[Help]document help" hspace=5 border=0  align="middle">
                    </a>
                </h1>
            </div>

        <!--TABBED TOP NAVIGATION-->
            <c:set var="readOnly" value="${KualiForm.readOnly}"  scope="request"/>
            <div class="horz-links-bkgrnd" id="horz-links">
                <div id="tabs">
                    <dl class="tabul">
                        <dt>
                            <span class="tabright">
                                <input type="submit" name="methodToCall.management" value="Reporter" alt="Reporter">
                            </span>
                        </dt> 
                        <dt>
                            <span class="tabright">
                                <input type="submit" name="methodToCall.editNew" value="New Financial Entity" alt="New financial Entity">
                            </span>
                        </dt> 
                        <dt class="licurrent" >
                            <span class="tabright tabcurrent">
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
                                <kra-coi:financialEntities />
                        
                  
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
                          <!--
                                <input type="image" name="methodToCall.save" src="kr/static/images/buttonsmall_save.gif"  class="globalbuttons" title="save" alt="save">
                          -->
                                <c:if test="${KualiForm.financialEntityHelper.editEntityIndex != -1}">
                                    <input type="image" name="methodToCall.save.line${KualiForm.financialEntityHelper.editEntityIndex}.anchor" src="kr/static/images/buttonsmall_save.gif" class="globalbuttons" title="Save Edit" alt="Save Edit">
                                    <input type="image" name="methodToCall.submit.line${KualiForm.financialEntityHelper.editEntityIndex}.anchor" src="kr/static/images/buttonsmall_submit.gif" class="globalbuttons" title="Submit Edit" alt="Submit Edit">
                                </c:if>
                                    <input type="image" name="methodToCall.editList" src="kr/static/images/buttonsmall_refresh.gif" class="globalbuttons" title="Refresh" alt="Refresh">
                                    <input type="image" name="methodToCall.close" src="kr/static/images/buttonsmall_close.gif" class="globalbuttons" title="close" alt="close">
                            <!--    
                                <input type="image" name="methodToCall.cancel" src="kr/static/images/buttonsmall_cancel.gif" class="globalbuttons" title="cancel" alt="cancel">
                                -->
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
<script language="javascript" src="dwr/interface/StateService.js"></script>

</html:html>
