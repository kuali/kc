<%--
   - Kuali Coeus, a comprehensive research administration system for higher education.
   - 
   - Copyright 2005-2015 Kuali, Inc.
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

<%-- <c:set var="readOnly" value="${KualiForm.readOnly}"  scope="request"/> --%>


<%-- optional attributes with default initializers --%>
<%@ attribute name="protocolSubmissionBOClassName" required="false" %>
<c:if test="${protocolSubmissionBOClassName == null}">
	<c:set var="protocolSubmissionBOClassName" value="org.kuali.kra.irb.actions.submit.ProtocolSubmission" />
</c:if>

<%@ attribute name="protocolSubmissionAttributes" required="false" %>
<c:if test="${protocolSubmissionAttributes == null}">
	<c:set var="protocolSubmissionAttributes" value="${DataDictionary.ProtocolSubmission.attributes}" />
</c:if>

<%@ attribute name="protocolAttributes" required="false" %>
<c:if test="${protocolAttributes == null}">
	<c:set var="protocolAttributes" value="${DataDictionary.Protocol.attributes}" />
</c:if>

<%@ attribute name="meetingManagementActionName" required="false" %>
<c:if test="${meetingManagementActionName == null}">
	<c:set var="meetingManagementActionName" value="meetingManagement" />
</c:if>




<kul:tab defaultOpen="false" tabTitle="Protocol Submitted "
    tabErrorKey="document.committee*">

<div class="tab-container" align="center">
    <h3>
        <span class="subhead-left"> Protocol Submitted </span>
        <span class="subhead-right"> <kul:help businessObjectClassName="${protocolSubmissionBOClassName}" altText="help"/> </span>
    </h3>
    
        <table id="protocolSubmitted-wrap-table" cellpadding=0 cellspacing=0 class="datatable" summary="Protocol Submitted">
        
        	<%-- Header --%>

        	<tr>
        		<th width="4%" />
                 <th width="8%"><div align="center">Protocol No</div></th> 
                 <th width="12%"><div align="center">PI</div></th> 
                 <th width="16%"><div align="center">Protocol Title</div></th> 
                 <th width="16%"><div align="center">Submission Type</div></th> 
                 <th width="12%"><div align="center">Sub. Type Qualifier </div></th> 
                 <th width="9%"><div align="center">Sub. Review Type </div></th> 
                 <th width="12%"><div align="center">Submission Status</div></th> 
                 <th width="6%"><div align="center">Submission Date</div></th> 
				<c:if test="${!readOnly}">
					<th class="{sorter: false}" width="5%">Actions </th>
				</c:if>
			</tr>
			<%-- Header --%>

             <tr>
                	<td colspan="10" style="padding:0px;">
                        
                        <div>
                            <table id="protocolSubmitted-table" cellpadding=0 cellspacing="0"  style="border-collapse:collapse;">
                                <thead>
		                            <tr>
        		                        <th width="4%" />
                                        <th width="8%"><div align="center"><img src="${ConfigProperties.kra.externalizable.images.url}sort-arrows.gif" width="15" height="16" alt="sort"></div></th> 
                                        <th width="12%"><div align="center"><img src="${ConfigProperties.kra.externalizable.images.url}sort-arrows.gif" width="15" height="16" alt="sort"></div></th> 
                                        <th width="16%"><div align="center"><img src="${ConfigProperties.kra.externalizable.images.url}sort-arrows.gif" width="15" height="16" alt="sort"></div></th> 
                                        <th width="16%"><div align="center"><img src="${ConfigProperties.kra.externalizable.images.url}sort-arrows.gif" width="15" height="16" alt="sort"></div></th> 
                                        <th width="12%"><div align="center"><img src="${ConfigProperties.kra.externalizable.images.url}sort-arrows.gif" width="15" height="16" alt="sort"></div></th> 
                                        <th width="9%"><div align="center"><img src="${ConfigProperties.kra.externalizable.images.url}sort-arrows.gif" width="15" height="16" alt="sort"></div></th> 
                                        <th width="12%"><div align="center"><img src="${ConfigProperties.kra.externalizable.images.url}sort-arrows.gif" width="15" height="16" alt="sort"></div></th> 
                                        <th width="6%"><div align="center"><img src="${ConfigProperties.kra.externalizable.images.url}sort-arrows.gif" width="15" height="16" alt="sort"></div></th> 
				                        <c:if test="${!readOnly}">
					                       <th class="{sorter: false}" width="5%"> </th>
				                        </c:if>
			                       </tr>
                                </thead>			
                                <tbody>			
			<%-- Existing data --%>
			                        <c:set var="idx" value="1"/>
        	                        <c:forEach var="protocolSubmission" items="${KualiForm.meetingHelper.committeeSchedule.latestProtocolSubmissions}" varStatus="status">
        	                          <c:if test = "${protocolSubmission.protocol.active || protocolSubmission.protocol.protocolStatus.description eq 'Disapproved'}" >
	                                    <tr>
					                       <th class="infoline" align="center">
						                       <c:out value="${idx}" />
			                                   <c:set var="idx" value="${idx+1}"/>
					                       </th>
	                                       <td align="left" valign="middle">
	                                           <div align="left"> ${protocolSubmission.protocolNumber} </div>
					                       </td>
	                                       <td align="left" valign="middle">
	                    <%-- TODO : getPrincipalInvestigator is calling service which should be avoided.  So, rework this --%>
	                	                       <div align="left"> ${KualiForm.meetingHelper.protocolSubmittedBeans[status.index].personName}</div>
                                               <c:choose>
	                                               <c:when test="${!empty KualiForm.meetingHelper.protocolSubmittedBeans[status.index].personId}">
						                               <input type="hidden" name="meetingHelper.protocolSubmittedBeans[${status.index}].personId" value="${KualiForm.meetingHelper.protocolSubmittedBeans[status.index].personId}"/>
                                                       <kul:directInquiry boClassName="org.kuali.coeus.common.framework.person.KcPerson" inquiryParameters="meetingHelper.protocolSubmittedBeans[${status.index}].personId:personId" anchor="${tabKey}" />
	                                               </c:when>
	                                               <c:otherwise>
						                               <input type="hidden" name="meetingHelper.protocolSubmittedBeans[${status.index}].rolodexId" value="${KualiForm.meetingHelper.protocolSubmittedBeans[status.index].rolodexId}"/>
                                                       <kul:directInquiry boClassName="org.kuali.coeus.common.framework.rolodex.Rolodex" inquiryParameters="meetingHelper.protocolSubmittedBeans[${status.index}].rolodexId:rolodexId" anchor="${tabKey}" />
	                                               </c:otherwise>
                                              </c:choose>
	                	<%-- TODO : need to take care of rolodex_id  --%>
	                                       </td>
	                                       <td align="left" valign="middle">
	                	                       <div align="left"> ${protocolSubmission.protocol.title}</div>
	                                       </td>
	                                       <td align="left" valign="middle">
	                                           <div align="left"> ${protocolSubmission.protocolSubmissionType.description} </div>
					                       </td>
	                                       <td align="left" valign="middle">
	                                           <div align="left"> ${protocolSubmission.protocolSubmissionQualifierType.description} </div>
					                       </td>
	                                       <td align="left" valign="middle">
	                                           <div align="left"> ${protocolSubmission.protocolReviewType.description} </div>
					                       </td>
	                                       <td align="left" valign="middle">
	                                           <div align="left"> ${protocolSubmission.submissionStatus.description} </div>
					                       </td>
	                                       <td align="left" valign="middle">
	                                                         				
	                                               <fmt:formatDate value="${protocolSubmission.submissionDate}" pattern="MM/dd/yyyy" />
	                                            
					                       </td>

                                          	<td> 
                                          		<%-- view protocol popup alternatives --%>
                                          		<a href="${pageContext.request.contextPath}/${meetingManagementActionName}.do?command=viewProtocolSubmission&line=${status.index}" target="_blank" >
                                          			<img alt="View Protocol" src="${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif" styleClass="tinybutton" />
                                          		</a>
		                                    </td>
	                                   </tr>
	                                 </c:if>
        	                       </c:forEach>
			<%-- Existing data --%>
                       </tbody>		
                    </table>
                  </div>
               </td>
            </tr>	        				
        </table>
    </div>

</kul:tab>
