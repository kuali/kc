<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

    <c:set var="committeeScheduleAttendanceAttributes" value="${DataDictionary.CommitteeScheduleAttendance.attributes}" />
    <table id="memberPresent-table" cellpadding=0 cellspacing=0 class="datatable" summary="View/edit member present">
        <tr>
            <td colspan="5" class="tab-subhead1">Voting Members Present: ${fn:length(KualiForm.meetingHelper.memberPresentBeans)}</td>
        </tr>
        <tr>
            <th style="width:50px;" class="infoline"> &nbsp; </th>
        	<th style="width:200px;" class="infoline">Person Name </th>
        	<th style="width:200px;" class="infoline">Alternate For</th>
        	<th style="width:400px;" class="infoline">Comments </th>
        	<th style="width:150px;" class="infoline">Actions </th>
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
				<th style="width:48px;" class="infoline" align="center">
					<c:out value="${status.index+1}" />
				</th>
	            <td align="left" valign="middle" style="width:200px;">
	                <div align="left"> ${memberPresentBean.attendance.personName} </div>
	                <input type="hidden" name="meetingHelper.memberPresentBeans[${status.index}].attendance.personName" id="meetingHelper.memberPresentBeans[${status.index}].attendance.personName"  value="${memberPresentBean.attendance.personName}"/>
				</td>
	            <td align="left" valign="middle" style="width:200px;">
	                <c:if test="${memberPresentBean.attendance.alternateFlag and !empty KualiForm.meetingHelper.memberAbsentBeans}">
                        <jsp:useBean id="paramMap" class="java.util.HashMap"/>
		                <c:set target="${paramMap}" property="absenteeList" value="${KualiForm.meetingHelper.absenteeList}" />
                        <c:set var="hasAlternateError" value="false"/>
               	        <c:set var="fieldName" value="meetingHelper.memberPresentBeans[${status.index}].attendance.alternateFor" />
			            <c:forEach items="${ErrorPropertyList}" var="key">
				            <c:if test="${key eq fieldName }">
                                <c:set var="hasAlternateError" value="true"/>
				            </c:if>
			            </c:forEach>
                        <c:if test="${!readOnly}">
	                        <html:select property="meetingHelper.memberPresentBeans[${status.index}].attendance.alternateFor" tabindex="0"  >
                                <c:set var="alternatePerson" value="" />
		                        <c:forEach items="${krafn:getOptionList('org.kuali.kra.meeting.AlternateForValuesFinder', paramMap)}" var="option">
		                            <c:choose>                    	
		                    	        <c:when test="${KualiForm.meetingHelper.memberPresentBeans[status.index].attendance.alternateFor == option.key}">
		                                    <option value="${option.key}" selected>${option.value}</option>
                                            <c:set var="alternatePerson" value="${option.value}" />
		                                </c:when>
		                                <c:otherwise>
		                                    <option value="${option.key}">${option.value}</option>
		                                </c:otherwise>
		                            </c:choose>                    
		                        </c:forEach>
		                        <input type="hidden" name="alternatePerson[${status.index}]" id="alternatePerson[${status.index}]" value="${alternatePerson}" />
		                    </html:select>
		                </c:if>
		                <c:if test="${readOnly and !empty KualiForm.meetingHelper.memberPresentBeans[status.index].attendance.alternateFor}">
		                    <c:forEach items="${krafn:getOptionList('org.kuali.kra.meeting.AlternateForValuesFinder', paramMap)}" var="option">
		                        <c:if test="${KualiForm.meetingHelper.memberPresentBeans[status.index].attendance.alternateFor == option.key}">
		                             ${option.value} 
		                        </c:if>
		                    </c:forEach>		               
		                </c:if>
		               
                        <c:if test="${hasAlternateError}">
	 		                <kul:fieldShowErrorIcon />
                        </c:if>
	 	            </c:if>	                    	                    
			    </td>
	            <td align="left" valign="middle" style="width:400px;">
	               	<div align="left">
	               	    <kul:htmlControlAttribute property="meetingHelper.memberPresentBeans[${status.index}].attendance.comments" attributeEntry="${committeeScheduleAttendanceAttributes.comments}"/>
	               	</div>
				</td>
                <c:if test="${!readOnly}">
					<td style="width:150px;">
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