<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

 <c:set var="committeeScheduleAttendanceAttributes" value="${DataDictionary.CommitteeScheduleAttendance.attributes}" />
        <table id="memberPresent-table" cellpadding=0 cellspacing=0 class="datatable" summary="View/edit member present">
            <tr>
                 <td colspan="5" class="tab-subhead1">Voting Members Present: ${fn:length(KualiForm.meetingHelper.memberPresentBeans)}</td>
            </tr>
        
        	<%-- Header --%>
        <%--	<tr>
        		<kul:htmlAttributeHeaderCell literalLabel="&nbsp;" scope="col" />
        		<kul:htmlAttributeHeaderCell attributeEntry="${committeeScheduleAttendanceAttributes.personName}" scope="col" />
        		<kul:htmlAttributeHeaderCell attributeEntry="${committeeScheduleAttendanceAttributes.alternateFor}" scope="col" />
        		<kul:htmlAttributeHeaderCell attributeEntry="${committeeScheduleAttendanceAttributes.comments}" scope="col" />
				<c:if test="${!readOnly}">
					<kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col" />
				</c:if>
			</tr>--%>
        	<tr>
        		<th width="5%" class="infoline"> &nbsp; </th>
        		<th width="20%" class="infoline">Person Name </th>
        		<th width="20%" class="infoline">Alternate For</th>
        		<th width="40%" class="infoline">Comments </th>
        		<th width="15%" class="infoline">Actions </th>
			</tr>
			<%-- Header --%>
			
			<%-- Existing data --%>
			<c:if test="${fn:length(KualiForm.meetingHelper.memberPresentBeans) > 2}">
			
             <tr>
                	<td colspan="5" style="padding:0px;">
                        
                        <div style="clear:left; height:120px; overflow-y:scroll; overflow-x:hidden; position:relative;">
                            <table cellpadding=0 cellspacing="0"  style="border-collapse:collapse;">
             </c:if>                
        	<c:forEach var="memberPresentBean" items="${KualiForm.meetingHelper.memberPresentBeans}" varStatus="status">
	             <tr>
					<th width="5%" class="infoline" align="center">
						<c:out value="${status.index+1}" />
					</th>
	                <td align="left" valign="middle" width="20%">
	                    <div align="left"> ${memberPresentBean.attendance.personName} </div>
					</td>
	                <td align="left" valign="middle" width="20%">
	                    <%-- <div align="left"> ${memberPresentBean.alternateFor} </div> --%>
	                    
	                     <c:if test="${memberPresentBean.role.membershipRoleCode == '12' and !empty KualiForm.meetingHelper.memberAbsentBeans}">
                           <jsp:useBean id="paramMap" class="java.util.HashMap"/>
		                   <c:set target="${paramMap}" property="absenteeList" value="${KualiForm.meetingHelper.absenteeList}" />
                <c:set var="hasAlternateError" value="false"/>
               	    <c:set var="fieldName" value="meetingHelper.memberPresentBeans[${status.index}].attendance.alternateFor" />
			     <c:forEach items="${ErrorPropertyList}" var="key">
				    <c:if test="${key eq fieldName }">
                       <c:set var="hasAlternateError" value="true"/>
				    </c:if>
			     </c:forEach>
	                       <html:select property="meetingHelper.memberPresentBeans[${status.index}].attendance.alternateFor" tabindex="0"  >
		                    <c:forEach items="${krafn:getOptionList('org.kuali.kra.meeting.AlternateForValuesFinder', paramMap)}" var="option">
		                    <c:choose>                    	
		                    	<c:when test="${KualiForm.meetingHelper.memberPresentBeans[status.index].attendance.alternateFor == option.key}">
		                        <option value="${option.key}" selected>${option.label}</option>
		                        </c:when>
		                        <c:otherwise>
		                        <c:out value="${option.label}"/>
		                        <option value="${option.key}">${option.label}</option>
		                        </c:otherwise>
		                    </c:choose>                    
		                    </c:forEach>
		                    </html:select>
                <c:if test="${hasAlternateError}">
	 		                <kul:fieldShowErrorIcon />
                      </c:if>
	 	                    </c:if>
	                    
	                    
					</td>
	                <td align="left" valign="middle" width="40%">
	               	<div align="left">
	               	    <kul:htmlControlAttribute property="meetingHelper.memberPresentBeans[${status.index}].attendance.comments" attributeEntry="${committeeScheduleAttendanceAttributes.comments}"/>
                        <kra:expandedTextArea textAreaFieldName="meetingHelper.memberPresentBeans[${status.index}].attendance.comments" action="meetingManagement" textAreaLabel="${committeeScheduleAttendanceAttributes.comments.label}" />
	               	</div>
					</td>
                   <c:if test="${!readOnly}">
						<td width="15%">
							<div align=center>&nbsp;					
								<html:image property="methodToCall.markAbsent.line${status.index}.anchor${currentTabIndex}"
										src='${ConfigProperties.kra.externalizable.images.url}tinybutton-markabsent.gif' styleClass="tinybutton"/>
							</div>
		                </td>
		            </c:if>
	            </tr>
        	</c:forEach>    
        	
			<c:if test="${fn:length(KualiForm.meetingHelper.memberPresentBeans) > 2}">
        </table>
        </div>
        </td>	
        </tr>
            	</c:if>
  </table>