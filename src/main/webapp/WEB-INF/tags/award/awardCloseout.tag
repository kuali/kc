<%--
 Copyright 2006-2009 The Kuali Foundation
 
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

<c:set var="awardCloseoutAttributes" value="${DataDictionary.AwardCloseout.attributes}" />
<c:set var="awardAttributes" value="${DataDictionary.Award.attributes}" />
<c:set var="action" value="awardCloseout" />

<kul:tab tabTitle="AwardCloseout" defaultOpen="true" tabErrorKey="awardCloseoutBean.newAwardCloseout*,document.awardList[0].archiveLocation*,document.awardList[0].closeoutDate*" auditCluster="requiredFieldsAuditErrors" tabAuditKey="" useRiceAuditMode="true">
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left"> Award Closeout</span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.award.bo.AwardCloseout" altText="help"/></span>
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
                		<kul:htmlControlAttribute property="document.awardList[0].closeoutDate" attributeEntry="${awardAttributes.closeoutDate}" datePicker="true"  />
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
                	<kul:htmlControlAttribute property="awardCloseoutBean.newAwardCloseout.dueDate" attributeEntry="${awardCloseoutAttributes.dueDate}" datePicker="true" />
                	</div>
				</td>
                <td align="left" valign="middle" class="infoline">
                	<div align="center">
                	<kul:htmlControlAttribute property="awardCloseoutBean.newAwardCloseout.finalSubmissionDate" attributeEntry="${awardCloseoutAttributes.finalSubmissionDate}" datePicker="true" />
                	</div>
				</td>                
				<td class="infoline">
					<div align="center">
						<html:image property="methodToCall.addAwardCloseout.anchor${tabKey}"
						src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton"/>
					</div>
                </td>
            </tr>
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
							<c:when test="${KualiForm.awardCloseoutBean.closeoutReportTypeUserDefined == KualiForm.document.awardList[0].awardCloseoutItems[status.index].closeoutReportCode }" >
								<kul:htmlControlAttribute property="document.awardList[0].awardCloseoutItems[${status.index}].dueDate" attributeEntry="${awardCloseoutAttributes.dueDate}"  datePicker="true" />
							</c:when>
							<c:otherwise>
								<c:choose>
	                				<c:when test="${KualiForm.document.awardList[0].awardCloseoutItems[status.index].multiple}" >
	                					<c:out value="MULTIPLE" />
	                				</c:when>
	                				<c:otherwise>
	                					<kul:htmlControlAttribute property="document.awardList[0].awardCloseoutItems[${status.index}].dueDate" attributeEntry="${awardCloseoutAttributes.dueDate}"  datePicker="true" readOnly="true" />
	                				</c:otherwise>
                				</c:choose>
							</c:otherwise>
						</c:choose>	
					</div>
				  </td>
                  <td align="left" valign="middle">
					<div align="center">
                		<kul:htmlControlAttribute property="document.awardList[0].awardCloseoutItems[${status.index}].finalSubmissionDate" attributeEntry="${awardCloseoutAttributes.finalSubmissionDate}" datePicker="true" />
					</div>
				  </td>                  
				  <td class="infoline">
					<div align="center">
						<c:if test="${KualiForm.awardCloseoutBean.closeoutReportTypeUserDefined == KualiForm.document.awardList[0].awardCloseoutItems[status.index].closeoutReportCode }" >
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
