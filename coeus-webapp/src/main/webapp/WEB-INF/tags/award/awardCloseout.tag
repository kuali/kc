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
<%-- member of AwardPaymentReportsAndTerms.jsp --%>

<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="awardCloseoutAttributes" value="${DataDictionary.AwardCloseout.attributes}" />
<c:set var="awardAttributes" value="${DataDictionary.Award.attributes}" />
<c:set var="action" value="awardCloseout" />

<kul:tab tabTitle="Closeout" defaultOpen="false" tabErrorKey="awardCloseoutBean.newAwardCloseout*,document.awardList[0].archiveLocation*,document.awardList[0].closeoutDate*" auditCluster="requiredFieldsAuditErrors" tabAuditKey="" useRiceAuditMode="true">
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left"> Award Closeout</span>
     	    <span class="subhead-right"><kul:help parameterNamespace="KC-AWARD" parameterDetailType="Document" parameterName="awardCloseOutHelpUrl" altText="help"/></span>      
       </h3>
        <table cellpadding="0" cellspacing="0" summary="">
        	<tr>
        		<kul:htmlAttributeHeaderCell attributeEntry="${awardAttributes.archiveLocation}" scope="col" />
				<td align="left" valign="middle">
					<div align="center">
                		<kul:htmlControlAttribute property="document.awardList[0].archiveLocation" attributeEntry="${awardAttributes.archiveLocation}"   />
					</div>
				  </td>
				<kul:htmlAttributeHeaderCell attributeEntry="${awardAttributes.closeoutDate}" scope="col" />
				<td align="left" valign="middle">
					<div align="center">
                		<kul:htmlControlAttribute property="document.awardList[0].closeoutDate" attributeEntry="${awardAttributes.closeoutDate}" />
					</div>
				</td>
        	</tr>
        </table>
        <table cellpadding="0" cellspacing="0" summary="">
          	<%-- Header --%>
          	<tr>
          		<kul:htmlAttributeHeaderCell literalLabel="&nbsp;" scope="col" />
          		<kul:htmlAttributeHeaderCell attributeEntry="${awardCloseoutAttributes.closeoutReportName}" scope="col" />
          		<kul:htmlAttributeHeaderCell attributeEntry="${awardCloseoutAttributes.dueDate}" scope="col" />
          		<kul:htmlAttributeHeaderCell attributeEntry="${awardCloseoutAttributes.finalSubmissionDate}" scope="col" />          		
          		<kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col" />
          	</tr> 
          	<%-- Header --%>
          	
             <%-- New data --%>
             <c:if test="${!readOnly}">
             <tbody class="addline">
             <tr>
				<th class="infoline">
					<c:out value="Add:" />
				</th>                
                <td align="left" valign="middle" class="infoline">
                	<div align="center">
                	<kul:htmlControlAttribute property="awardCloseoutBean.newAwardCloseout.closeoutReportName" attributeEntry="${awardCloseoutAttributes.closeoutReportName}"  />
                	</div>
				</td>
                <td align="left" valign="middle" class="infoline">
                	<div align="center">
                	<kul:htmlControlAttribute property="awardCloseoutBean.newAwardCloseout.dueDate" attributeEntry="${awardCloseoutAttributes.dueDate}" />
                	</div>
				</td>
                <td align="left" valign="middle" class="infoline">
                	<div align="center">
                	<kul:htmlControlAttribute property="awardCloseoutBean.newAwardCloseout.finalSubmissionDate" attributeEntry="${awardCloseoutAttributes.finalSubmissionDate}" />
                	</div>
				</td>                
				<td class="infoline">
					<div align="center">
						<html:image property="methodToCall.addAwardCloseout.anchor${tabKey}"
						src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton addButton"/>
					</div>
                </td>
            </tr>
            </tbody>
            </c:if>
            <%-- New data --%>
            
            <%-- Existing data --%>
        	<c:forEach var="awardCloseout" items="${KualiForm.document.awardList[0].awardCloseoutItems}" varStatus="status">
	             <tr>
					<th class="infoline">
						<c:out value="${status.index+1}" />
					</th>
                  <td align="left" valign="middle">
					<div align="center">
                		<kul:htmlControlAttribute property="document.awardList[0].awardCloseoutItems[${status.index}].closeoutReportName" attributeEntry="${awardCloseoutAttributes.closeoutReportName}"  readOnly="true" />
					</div>
				  </td>
                  <td align="left" valign="middle">
					<div align="center">								
						<c:choose>
							<c:when test="${KualiForm.document.awardList[0].awardCloseoutItems[status.index].userDefinedReport }" >
								<kul:htmlControlAttribute property="document.awardList[0].awardCloseoutItems[${status.index}].dueDate" attributeEntry="${awardCloseoutAttributes.dueDate}" />
							</c:when>
							<c:otherwise>
								<c:choose>
	                				<c:when test="${KualiForm.document.awardList[0].awardCloseoutItems[status.index].multiple}" >
	                					<c:out value="MULTIPLE" />
	                				</c:when>
	                				<c:otherwise>
	                					<kul:htmlControlAttribute property="document.awardList[0].awardCloseoutItems[${status.index}].dueDate" attributeEntry="${awardCloseoutAttributes.dueDate}" readOnly="true" />
	                				</c:otherwise>
                				</c:choose>
							</c:otherwise>
						</c:choose>	
					</div>
				  </td>
                  <td align="left" valign="middle">
					<div align="center">
                		<kul:htmlControlAttribute property="document.awardList[0].awardCloseoutItems[${status.index}].finalSubmissionDate" attributeEntry="${awardCloseoutAttributes.finalSubmissionDate}" />
					</div>
				  </td>                  
				  <td class="infoline">
					<div align="center">
						<c:if test="${KualiForm.awardCloseoutBean.closeoutReportTypeUserDefined == KualiForm.document.awardList[0].awardCloseoutItems[status.index].closeoutReportCode && !readOnly}" >
							<html:image property="methodToCall.deleteAwardCloseout.line${status.index}.anchor${currentTabIndex}"
							src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
						</c:if>
					</div>
                  </td>

	            </tr>
        	</c:forEach> 
            <%-- Existing data --%>
        </table>

    </div>
</kul:tab>
