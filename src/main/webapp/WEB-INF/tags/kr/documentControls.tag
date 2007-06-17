<%--
 Copyright 2005-2007 The Kuali Foundation.
 
 Licensed under the Educational Community License, Version 1.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 
 http://www.opensource.org/licenses/ecl1.php
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>

<%@ attribute name="transactionalDocument" required="true" %>
<%@ attribute name="saveButtonOverride" required="false" %>
<%@ attribute name="suppressRoutingControls" required="false" %>
<%@ attribute name="extraButtonSource" required="false" %>
<%@ attribute name="extraButtonProperty" required="false" %>
<%@ attribute name="extraButtonAlt" required="false" %>
<%@ attribute name="extraButtons" required="false" type="java.util.List" %>
<%@ attribute name="viewOnly" required="false" %>

        <c:set var="saveButtonValue" value="save" />
        <c:if test="${not empty saveButtonOverride}"><c:set var="saveButtonValue" value="${saveButtonOverride}" /></c:if>

        <html:hidden property="documentActionFlags.canAnnotate" />
        <html:hidden property="documentActionFlags.canReload" />
        <html:hidden property="documentActionFlags.canSave" />
        <html:hidden property="documentActionFlags.canRoute" />
        <html:hidden property="documentActionFlags.canCancel" />
        <html:hidden property="documentActionFlags.canClose" />
        <html:hidden property="documentActionFlags.canBlanketApprove" />
        <html:hidden property="documentActionFlags.canApprove" />
        <html:hidden property="documentActionFlags.canDisapprove" />
        <html:hidden property="documentActionFlags.canFYI" />
        <html:hidden property="documentActionFlags.canAcknowledge" />
        <html:hidden property="documentActionFlags.canAdHocRoute" />
        <html:hidden property="documentActionFlags.canSupervise" />
        <html:hidden property="documentActionFlags.canCopy" />
        <html:hidden property="documentActionFlags.canPerformRouteReport" />

        <c:if test="${transactionalDocument}">
            <html:hidden property="documentActionFlags.canErrorCorrect" />
        </c:if>

        <%--c:if test="${KualiForm.documentActionFlags.canAnnotate and not suppressRoutingControls and not KualiForm.suppressAllButtons}">
            <div class="annotate">
              <table width="100%" cellpadding="0" cellspacing="0" class="annotate-top" summary="">
                <tr>
                  <td class="annotate-t"><img src="${ConfigProperties.kr.externalizable.images.url}annotate-tl1.gif" alt="" width="12" height="24" align="middle" class="annotate-t">
                    <label for="routing annotation">Routing Annotation:</label></td>
                  <td class="annotate-t"><div align="right"><img src="${ConfigProperties.kr.externalizable.images.url}annotate-tr1.gif" alt="" width="12" height="24" align="middle"></div></td>
                </tr>
              </table>
              
              <div class="annotate-container">
                <kul:displayIfErrors keyMatch="annotation">
                  <kul:errors keyMatch="annotation" />
                </kul:displayIfErrors>
                <br>
                <html:textarea property="annotation" cols="60" rows="3" />
              </div>
              
              <table width="100%" cellpadding="0" cellspacing="0" class="annotate-top" summary="">
                <tr>
                  <td class="annotate-b"><img src="${ConfigProperties.kr.externalizable.images.url}annotate-bl1.gif" alt="" width="12" height="24"></td>
                  <td class="annotate-b"><div align="right"><img src="${ConfigProperties.kr.externalizable.images.url}annotate-br1.gif" alt="" width="12" height="24"></div></td>
                </tr>
              </table>
            </div>
        </c:if--%>

		<c:if test="${not KualiForm.suppressAllButtons}">
	        <div id="globalbuttons" class="globalbuttons">
	        	<c:if test="${!empty extraButtonSource}">
	        		<html:image src="${extraButtonSource}" styleClass="globalbuttons" property="${extraButtonProperty}" alt="${extraButtonAlt}"/>
	        	</c:if>
	        	<c:if test="${!empty extraButtons}">
		        	<c:forEach items="${extraButtons}" var="extraButton">
		        		<html:image src="${extraButton.extraButtonSource}" styleClass="globalbuttons" property="${extraButton.extraButtonProperty}" alt="${extraButton.extraButtonAltText}"/>
		        	</c:forEach>
	        	</c:if>
	            <c:if test="${KualiForm.documentActionFlags.canPerformRouteReport and not suppressRoutingControls}">
				    <html:image src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_routereport.gif" styleClass="globalbuttons" property="methodToCall.performRouteReport" title="Perform Route Report" alt="Perform Route Report" />
	            </c:if>
	            <c:if test="${KualiForm.documentActionFlags.canSupervise and not suppressRoutingControls}">
				    <html:image src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_supervfunct.gif" styleClass="globalbuttons" property="methodToCall.supervisorFunctions" title="Supervisor Functions" alt="Supervisor Functions" />
	            </c:if>
	            <c:if test="${KualiForm.documentActionFlags.canRoute and not suppressRoutingControls}">
	                <html:image src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_submit.gif" styleClass="globalbuttons" property="methodToCall.route" title="submit" alt="submit"/>
	            </c:if>
	            <c:if test="${KualiForm.documentActionFlags.canSave and not viewOnly}">
	                <html:image src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_save.gif" styleClass="globalbuttons" property="methodToCall.${saveButtonValue}" title="save" alt="save"/>
	            </c:if>
	             <c:if test="${KualiForm.documentActionFlags.canReload}">
	                <html:image src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_reload.gif" styleClass="globalbuttons" property="methodToCall.reload" title="reload" alt="reload" onclick="excludeSubmitRestriction=true"/>
	            </c:if>
	            <c:if test="${KualiForm.documentActionFlags.canBlanketApprove and not suppressRoutingControls}">
	                <html:image src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_blanketapp.gif" styleClass="globalbuttons" property="methodToCall.blanketApprove" title="blanket approve" alt="blanket approve"/>
	            </c:if>
	            <c:if test="${KualiForm.documentActionFlags.canApprove and not suppressRoutingControls}">
	                <html:image src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_approve.gif" styleClass="globalbuttons" property="methodToCall.approve" title="approve" alt="approve"/>
	            </c:if>
	            <c:if test="${KualiForm.documentActionFlags.canDisapprove and not suppressRoutingControls}">
	                <html:image src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_disapprove.gif" styleClass="globalbuttons" property="methodToCall.disapprove" title="disapprove" alt="disapprove"/>
	            </c:if>
	            <c:if test="${KualiForm.documentActionFlags.canFYI and not suppressRoutingControls}">
	                <html:image src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_fyi.gif" styleClass="globalbuttons" property="methodToCall.fyi" title="fyi" alt="fyi"/>
	            </c:if>
	            <c:if test="${KualiForm.documentActionFlags.canAcknowledge and not suppressRoutingControls}">
	                <html:image src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_acknowledge.gif" styleClass="globalbuttons" property="methodToCall.acknowledge" title="acknowledge" alt="acknowledge"/>
	            </c:if>
	            <c:if test="${KualiForm.documentActionFlags.canClose}">
	                <html:image src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_close.gif" styleClass="globalbuttons" property="methodToCall.close" title="close" alt="close"/>
	            </c:if>            
	            <c:if test="${KualiForm.documentActionFlags.canCancel}">
	                <html:image src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_cancel.gif" styleClass="globalbuttons" property="methodToCall.cancel" title="cancel" alt="cancel"/>
	            </c:if>
	                <c:if test="${KualiForm.documentActionFlags.canCopy}">
                    <html:image src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_copy.gif" styleClass="globalbuttons" property="methodToCall.copy" title="Copy current document" alt="Copy current document"/>
	                </c:if>
	            <c:if test="${transactionalDocument}">
	                <c:if test="${KualiForm.documentActionFlags.canErrorCorrect}">
	                    <html:image src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_errcorr.gif" styleClass="globalbuttons" property="methodToCall.correct" title="Create error correction document from current document" alt="Create error correction document from current document"/>
	                </c:if>
	            </c:if>
	        </div>
        </c:if>
        