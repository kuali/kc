<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<%-- <c:set var="readOnly" value="${KualiForm.readOnly}"  scope="request"/> --%>
<c:set var="committeeScheduleMinuteAttributes" value="${DataDictionary.CommitteeScheduleMinute.attributes}" />
<c:set var="attributeReferenceDummyAttributes" value="${DataDictionary.AttributeReferenceDummy.attributes}" />
<jsp:useBean id="paramMap" class="java.util.HashMap"/>
<c:set target="${paramMap}" property="scheduleId" value="${KualiForm.meetingHelper.committeeSchedule.id}" />

<kul:tab defaultOpen="false" tabTitle="Minutes"
    tabErrorKey="meetingHelper.newCommitteeScheduleMinute.*">

<div class="tab-container" align="center">
    <h3>
        <span class="subhead-left"> Minutes </span>
        <span class="subhead-right"> <kul:help businessObjectClassName="org.kuali.kra.meeting.CommitteeScheduleMinute" altText="help"/> </span>
    </h3>
        <table id="minutes-table" cellpadding=0 cellspacing=0 class="datatable" summary="view /edit Meeting Minutes">
        
        	<%-- Header --%>
        	<tr>
        		<kul:htmlAttributeHeaderCell literalLabel="&nbsp;" scope="col" />
        		<kul:htmlAttributeHeaderCell attributeEntry="${committeeScheduleMinuteAttributes.minuteEntryTypeCode}" scope="col" />
        		<kul:htmlAttributeHeaderCell literalLabel="Protocol" scope="col" />
				<kul:htmlAttributeHeaderCell attributeEntry="${committeeScheduleMinuteAttributes.minuteEntry}" scope="col" />
                 <th>
                    <div align="center" id ="meetingHelper.newCommitteeScheduleMinute.pcHeaderDiv">Standard Review Comment</div>
                    <div align="center" id ="meetingHelper.newCommitteeScheduleMinute.genAttHeaderDiv">Generate Attendance</div>
                 </th> 
				<%--<kul:htmlAttributeHeaderCell attributeEntry="${committeeScheduleMinuteAttributes.protocolContingencyCode}" scope="col" /> --%>
				<kul:htmlAttributeHeaderCell attributeEntry="${committeeScheduleMinuteAttributes.privateCommentFlag}" scope="col" />
				<c:if test="${!readOnly}">
					<kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col" />
				</c:if>
			</tr>
			<%-- Header --%>

	
            <%-- New data --%>
        	<%-- <kra:permission value="${KualiForm.meetingHelper.modifySubjects}">--%>
	            <tr>
				<th class="infoline" width="5%">
					<c:out value="Add:" />
				</th>
	
	            <td align="left" valign="middle" class="infoline" width="20%">
	               	<div align="center">
	               		<kul:htmlControlAttribute property="meetingHelper.newCommitteeScheduleMinute.minuteEntryTypeCode" onchange="showHideDiv(this);"  attributeEntry="${committeeScheduleMinuteAttributes.minuteEntryTypeCode}" readOnly="false" />
	            	</div>
				</td>
	            <td align="left" valign="middle" class="infoline" width="20%">
	               	<div align="center">
	            <c:set var="hasProtocolError" value="false"/>
          	    <c:set var="fieldName" value="meetingHelper.newCommitteeScheduleMinute.protocolIdFk" />
			     <c:forEach items="${ErrorPropertyList}" var="key">
				    <c:if test="${key eq fieldName }">
	                    <c:set var="hasProtocolError" value="true"/>
				    </c:if>
			     </c:forEach>
	               		 <html:select property="meetingHelper.newCommitteeScheduleMinute.protocolIdFk" tabindex="0"  >
 		                        
		                    <c:forEach items="${krafn:getOptionList('org.kuali.kra.meeting.ProtocolValuesFinder', paramMap)}" var="option">
		                    <c:choose>                    	
		                    	<c:when test="${KualiForm.meetingHelper.newCommitteeScheduleMinute.protocolIdFk == option.key}">
		                        <option value="${option.key}" selected>${option.label}</option>
		                        </c:when>
		                        <c:otherwise>
		                        
		                        <c:out value="${option.label}"/>
		                        <option value="${option.key}">${option.label}</option>
		                        </c:otherwise>
		                    </c:choose> 
		                                       
		                    </c:forEach>
		                    </html:select>
                     <c:if test="${hasProtocolError}">
	 		                <kul:fieldShowErrorIcon />
                      </c:if>
	               	
<%--	               		<kul:htmlControlAttribute property="meetingHelper.newCommitteeScheduleMinute.protocolIdFk" attributeEntry="${committeeScheduleMinuteAttributes.protocolIdFk}" readOnly="false" />
 --%>		                        
	            	</div>
				</td>
			
	            <td align="left" valign="middle" class="infoline" width="65%">
	               <div id="meetingHelper.newCommitteeScheduleMinute.minuteEntry.div" align="left">
	               	<div align="left">
	               	      <kul:htmlControlAttribute property="meetingHelper.newCommitteeScheduleMinute.minuteEntry" attributeEntry="${committeeScheduleMinuteAttributes.minuteEntry}" readOnly="false" />
                          <kra:expandedTextArea textAreaFieldName="meetingHelper.newCommitteeScheduleMinute.minuteEntry" action="meetingManagement" textAreaLabel="${committeeScheduleMinuteAttributes.minuteEntry.label}" />
	               	</div>
	               </div>	
	            </td>
	            <td align="left" valign="middle" class="infoline" width="20%">
	               	<div align="center" id = "meetingHelper.newCommitteeScheduleMinute.pcDiv">
	               		<kul:htmlControlAttribute property="meetingHelper.newCommitteeScheduleMinute.protocolContingencyCode" attributeEntry="${committeeScheduleMinuteAttributes.protocolContingencyCode}" onblur="loadStandardReviewComment('meetingHelper.newCommitteeScheduleMinute.protocolContingencyCode', 'meetingHelper.newCommitteeScheduleMinute.minuteEntry');"  />
                        <kul:lookup boClassName="org.kuali.kra.meeting.ProtocolContingency" 
                                    fieldConversions="protocolContingencyCode:meetingHelper.newCommitteeScheduleMinute.protocolContingencyCode,description:meetingHelper.newCommitteeScheduleMinute.minuteEntry" />
                   	 </div>
	               	<div align="center" id = "meetingHelper.newCommitteeScheduleMinute.genAttDiv">
	               		<kul:htmlControlAttribute property="meetingHelper.newCommitteeScheduleMinute.generateAttendance" attributeEntry="${attributeReferenceDummyAttributes.genericBoolean}" readOnly="false" />
                   	 </div>
				</td>
	            <td align="left" valign="middle" class="infoline" width="20%">
	               	<div align="center">
	               		<kul:htmlControlAttribute property="meetingHelper.newCommitteeScheduleMinute.privateCommentFlag" attributeEntry="${committeeScheduleMinuteAttributes.privateCommentFlag}" readOnly="false" />
	            	</div>
				</td>
	
				<td align="left" valign="middle" class="infoline" width="10%">
					<div align=center>
					    <html:image property="methodToCall.addCommitteeScheduleMinute.anchor${tabKey}"
						src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton"/>
					</div>
	               </td>
	            </tr>
            <%--</kra:permission> --%>
			<%-- New data --%>
			
			<%-- Existing data --%>
        	<c:forEach var="committeeScheduleMinute" items="${KualiForm.meetingHelper.committeeSchedule.committeeScheduleMinutes}" varStatus="status">
	             <tr>
					<th class="infoline">
						<c:out value="${status.index+1}" />
					</th>
	                <td align="left" valign="middle">
	               		<kul:htmlControlAttribute property="meetingHelper.committeeSchedule.committeeScheduleMinutes[${status.index}].minuteEntryTypeCode" attributeEntry="${committeeScheduleMinuteAttributes.minuteEntryTypeCode}" readOnly="false" />
					</td>
	                <td align="left" valign="middle">
	               		<kul:htmlControlAttribute property="meetingHelper.committeeSchedule.committeeScheduleMinutes[${status.index}].protocolIdFk" attributeEntry="${committeeScheduleMinuteAttributes.protocolIdFk}" readOnly="false" />
					</td>
	                <td align="left" valign="middle" colspan="2">
	               		  <kul:htmlControlAttribute property="meetingHelper.committeeSchedule.committeeScheduleMinutes[${status.index}].minuteEntry" attributeEntry="${committeeScheduleMinuteAttributes.minuteEntry}" readOnly="false" />
		                  <kra:expandedTextArea textAreaFieldName="meetingHelper.committeeSchedule.committeeScheduleMinutes[${status.index}].minuteEntry" action="meetingManagement" textAreaLabel="${committeeScheduleMinuteAttributes.minuteEntry.label}"  />
					</td>
	            <td align="left" valign="middle" class="infoline" width="20%">
	               	<div align="center">
	               		<kul:htmlControlAttribute property="meetingHelper.committeeSchedule.committeeScheduleMinutes[${status.index}].privateCommentFlag" attributeEntry="${committeeScheduleMinuteAttributes.privateCommentFlag}" readOnly="false" />
	            	</div>
				</td>
                   <c:if test="${!readOnly}">
						<td>
							<div align=center>&nbsp;					
								<html:image property="methodToCall.deleteCommitteeScheduleMinute.line${status.index}.anchor${currentTabIndex}"
										src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
							</div>
		                </td>
		            </c:if>
	            </tr>
        	</c:forEach>
				<%-- Existing data --%>
			        				
        </table>

</div>

</kul:tab>