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
        <title>Kuali :: Meeting</title>
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
        <script language="JavaScript" type="text/javascript"
				src="dwr/interface/MeetingService.js"></script>
    </head>
    <body onload="if ( !restoreScrollPosition() ) {  }"
			onKeyPress="return isReturnKeyAllowed('methodToCall.' , event);">
			
        <html:form styleId="kualiForm" action="/meetingManagement.do"
		    method="post" onsubmit="return hasFormAlreadyBeenSubmitted();">
            <c:set var="KualiForm" value="${KualiForm}" /> 
			<jsp:useBean id="KualiForm" type="org.kuali.rice.kns.web.struts.form.KualiForm" /> 
            <a name="topOfForm"></a>
            <div class="headerarea" id="headerarea">
                <h1>Meeting 
                    <a href="${pageContext.request.contextPath}/kr/help.do?methodToCall=getDocumentHelpText&amp;documentTypeName=CommitteeDocument" tabindex="1000000" target="helpWindow"  title="[Help]document help"><img src="kr/static/images/my_cp_inf.gif" alt="[Help]document help" hspace=5 border=0  align="middle">
                    </a>
                </h1>
            </div>

        <!--TABBED TOP NAVIGATION-->
            <c:set var="readOnly" value="${KualiForm.readOnly}"  scope="request"/>
            <div class="horz-links-bkgrnd" id="horz-links">
                <div id="tabs">
                    <dl class="tabul">
             	        <dt class="licurrent" >
             	            <span class="tabright tabcurrent">
                                <input type="submit" name="methodToCall.management" value="${KualiForm.meetingHelper.tabLabel}" alt="Meeting">
                            </span>
                        </dt> 
                        <dt>
                            <span class="tabright">
                                <c:choose>
                                    <c:when test="${!readOnly}">
                                        <input type="submit" name="methodToCall.headerTab.headerDispatch.save.navigateTo.actions" value="Meeting Actions" alt="Meeting Actions">
                                    </c:when>
                                    <c:otherwise>
                                        <input type="submit" name="methodToCall.headerTab.headerDispatch.reload.navigateTo.actions" value="Meeting Actions" alt="Meeting Actions">
                                    </c:otherwise>
                                </c:choose>
                            </span>
                        </dt>
                    </dl>
                </div>
            </div>
            
            <c:set var="errorKey" value="meetingHelper"/>
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
                   <!--  commenting out the return to committee link until resolution of the mismatch view button issue -->
		           <!--  input type="image" alt="return to committee" class="tinybutton" onclick="" src="${ConfigProperties.kra.externalizable.images.url}tinybutton-retcommittee.gif" name="methodToCall.returnToCommittee" --> &nbsp;&nbsp;
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
                
            <!-- Tabbed Panel Head: Meeting details -->            
                                <kra-meeting:meetingDetails />
            
            <!-- Tabbed Panel Head: Protocols Submitted -->
                                <kra-meeting:protocolSubmitted />
            
            <!-- Tabbed Panel Head: Other Actions -->
                                <kra-meeting:meetingOtherActions />
            
            <!-- Tabbed Panel Head: Attendance -->
                                <kra-meeting:meetingAttendance />
            
            <!-- Tabbed Panel Head: Minutes -->
                                <kra-meeting:meetingMinutes />
                  
                  
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
                                <c:if test="${!readOnly}">
    	                        <input type="image" name="methodToCall.save" src="kr/static/images/buttonsmall_save.gif"  class="globalbuttons" title="save" alt="save">
			                        <input type="image" name="methodToCall.close" src="kr/static/images/buttonsmall_close.gif" class="globalbuttons" title="close" alt="close">
                                </c:if>
			                    <input type="image" name="methodToCall.cancel" src="kr/static/images/buttonsmall_cancel.gif" class="globalbuttons" title="cancel" alt="cancel">
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

    $(document).ready(function()     {
       $("#protocolSubmitted-table").tablesorter({         
		// pass the headers argument and assing a object         
		   headers: {             // assign the first column (we start counting zero)             
			   0: {                 // disable it by setting the property sorter to false                 
			      sorter: false             },             
			// assign the 10th (Action) column (we start counting zero)             
			   9: {                 // disable it by setting the property sorter to false                 
				   sorter: false             }
			         }
	      
          }); 
    
    } ); 

    </SCRIPT> 

</html:html>
