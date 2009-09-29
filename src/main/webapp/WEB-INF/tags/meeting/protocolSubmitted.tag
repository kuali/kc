<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<%-- <c:set var="readOnly" value="${KualiForm.readOnly}"  scope="request"/> --%>
<c:set var="protocolSubmissionAttributes" value="${DataDictionary.ProtocolSubmission.attributes}" />
<c:set var="protocolAttributes" value="${DataDictionary.Protocol.attributes}" />

<kul:tab defaultOpen="false" tabTitle="Protocol Submitted "
    tabErrorKey="document.committee*">

<div class="tab-container" align="center">
    <h3>
        <span class="subhead-left"> Protocol Submitted </span>
        <span class="subhead-right"> <kul:help businessObjectClassName="org.kuali.kra.irb.actions.submit.ProtocolSubmission" altText="help"/> </span>
    </h3>
    
        <table id="protocolSubmitted-table" cellpadding=0 cellspacing=0 class="datatable" summary="Protocol Submitted">
        
        	<%-- Header --%>
        	<tr>
        		<kul:htmlAttributeHeaderCell literalLabel="&nbsp;" scope="col" />
        		<kul:htmlAttributeHeaderCell attributeEntry="${protocolSubmissionAttributes.protocolNumber}" scope="col" />
        		<kul:htmlAttributeHeaderCell literalLabel="PI" scope="col" />
        		<kul:htmlAttributeHeaderCell literalLabel="Protocol Title" scope="col" />
        		<kul:htmlAttributeHeaderCell attributeEntry="${protocolSubmissionAttributes.submissionTypeCode}" scope="col" />
        		<kul:htmlAttributeHeaderCell attributeEntry="${protocolSubmissionAttributes.submissionTypeQualifierCode}" scope="col" />
        		<kul:htmlAttributeHeaderCell attributeEntry="${protocolSubmissionAttributes.protocolReviewTypeCode}" scope="col" />
        		<kul:htmlAttributeHeaderCell attributeEntry="${protocolSubmissionAttributes.submissionStatusCode}" scope="col" />
        		<kul:htmlAttributeHeaderCell attributeEntry="${protocolSubmissionAttributes.submissionDate}" scope="col" />
				<c:if test="${!readOnly}">
					<kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col" />
				</c:if>
			</tr>
			<%-- Header --%>
			
			
			<%-- Existing data --%>
        	<c:forEach var="protocolSubmission" items="${KualiForm.meetingHelper.committeeSchedule.protocolSubmissions}" varStatus="status">
	             <tr>
					<th class="infoline">
						<c:out value="${status.index+1}" />
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
                                <kul:directInquiry boClassName="org.kuali.kra.bo.Person" inquiryParameters="meetingHelper.protocolSubmittedBeans[${status.index}].personId:personId" anchor="${tabKey}" />
	                        </c:when>
	                        <c:otherwise>
						        <input type="hidden" name="meetingHelper.protocolSubmittedBeans[${status.index}].rolodexId" value="${KualiForm.meetingHelper.protocolSubmittedBeans[status.index].rolodexId}"/>
                                <kul:directInquiry boClassName="org.kuali.kra.bo.Rolodex" inquiryParameters="meetingHelper.protocolSubmittedBeans[${status.index}].rolodexId:rolodexId" anchor="${tabKey}" />
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
	                    <div align="left">               				
	                        <fmt:formatDate value="${protocolSubmission.submissionDate}" pattern="MM/dd/yyyy" />
	                     </div>
					</td>

                   <c:if test="${!readOnly}">
						<td>
							<div align=center>&nbsp;					
								<html:image property="methodToCall.viewProtocolSubmission.line${status.index}.anchor${currentTabIndex}"
										src='${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif' styleClass="tinybutton"/>
							</div>
		                </td>
		            </c:if>
	            </tr>
        	</c:forEach>
			<%-- Existing data --%>
			        				
        </table>
</div>

</kul:tab>