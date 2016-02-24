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
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
    <c:set var="committeeScheduleAttendanceAttributes" value="${DataDictionary.CommitteeScheduleAttendance.attributes}" />
    <table id="otherPresent-table" cellpadding=0 cellspacing=0 class="datatable" summary="View/edit other present">
        <tr>
            <td colspan="5" class="tab-subhead1">Others Present: ${fn:length(KualiForm.meetingHelper.otherPresentBeans)}</td>
        </tr>
    
        <c:if test="${!readOnly}">
            <tr> 
                <th>Add:</th>
                <td class="infoline" colspan="3">                    
                    <div style="text-align:center;">
                        <table style="border:none; width:100%; background:none;" cellpadding="0" cellspacing="0">
                            <tr>
                                <td style="border:none; background:none; text-align:center;">
                                    <label>Employee Search</label>
                                    <label>
                                        <kul:lookup boClassName="org.kuali.coeus.common.framework.person.KcPerson" 
                                            fieldConversions="personId:meetingHelper.newOtherPresentBean.attendance.personId,fullName:meetingHelper.newOtherPresentBean.attendance.personName" />
                                    </label>
                                    <label>Non-employee Search</label> 
                                    <label>
                                        <kul:lookup boClassName="org.kuali.coeus.common.framework.rolodex.NonOrganizationalRolodex"
                                            fieldConversions="rolodexId:meetingHelper.newOtherPresentBean.attendance.personId,fullName:meetingHelper.newOtherPresentBean.attendance.personName" />
                                    </label>
                                    <c:if test="${!empty KualiForm.meetingHelper.newOtherPresentBean }" >
						                <input type="hidden" name="meetingHelper.newOtherPresentBean.attendance.personId" value="${KualiForm.meetingHelper.newOtherPresentBean.attendance.personId}"/>
						       <%--  <input type="hidden" name="meetingHelper.newOtherPresentBean.rolodexId" value="${KualiForm.meetingHelper.newOtherPresentBean.rolodexId}"/> --%>
						                <input type="hidden" name="meetingHelper.newOtherPresentBean.attendance.personName" value="${KualiForm.meetingHelper.newOtherPresentBean.attendance.personName}"/>
						                <input type="hidden" name="meetingHelper.newOtherPresentBean.attendance.nonEmployeeFlag" value="${KualiForm.meetingHelper.newOtherPresentBean.attendance.nonEmployeeFlag}"/>
						                ${KualiForm.meetingHelper.newOtherPresentBean.attendance.personName}
						            </c:if> 
                                </td>
                            </tr>
                        </table> 
                    </div> 
                        
                </td>
                <td class="infoline" style="text-align:center; width:112px;">
				    <html:image property="methodToCall.addOtherPresent.anchor${currentTabIndex}"
										src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton"/>
                </td>
            </tr>
        </c:if>
        	<%-- Header --%>

        <tr>
        	<th width="5%" class="infoline"> &nbsp; </th>
        	<th width="20%" class="infoline">Person Name </th>
        	<th width="20%" class="infoline">Role</th>
        	<th width="40%" class="infoline">Comments </th>
        	<th width="15%" class="infoline">Actions </th>
		</tr>
			<%-- Header --%>
		<c:if test="${fn:length(KualiForm.meetingHelper.otherPresentBeans) > 1}">
            <tr>
                <td colspan="5" style="padding:0px;">
                    <div style="clear:left; height:80px; overflow-y:scroll; overflow-x:hidden; position:relative;">
                
                		<table cellpadding=0 cellspacing="0"  style="border-collapse:collapse;">
        </c:if>
        <c:forEach var="otherPresentBean" items="${KualiForm.meetingHelper.otherPresentBeans}" varStatus="status">
	        <tr>
				<th  width="5%" class="infoline" align="center">
					<c:out value="${status.index+1}" />
				</th>
	            <td align="left" valign="middle"  width="20%">
	                <div align="left"> ${otherPresentBean.attendance.personName} </div>
	                <input type="hidden" name="meetingHelper.otherPresentBeans[${status.index}].attendance.personName" id="meetingHelper.otherPresentBeans[${status.index}].attendance.personName"  value="${otherPresentBean.attendance.personName}"/>
				</td>
	            <td align="left" valign="middle" width="20%">
	                <div align="left"> ${otherPresentBean.attendance.roleName} </div>
				</td>
	            <td align="left" valign="middle" width="40%">
	               	<div align="left">
	               	    <kul:htmlControlAttribute property="meetingHelper.otherPresentBeans[${status.index}].attendance.comments" attributeEntry="${committeeScheduleAttendanceAttributes.comments}"/>
	               	</div>
				</td>
                <c:if test="${!readOnly}">
					<td  width="15%">
						<div align=center>&nbsp;					
							<html:image property="methodToCall.deleteOtherPresent.line${status.index}.anchor${currentTabIndex}"
										src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
						</div>
		            </td>
		        </c:if>
	        </tr>
        </c:forEach>
 		<c:if test="${fn:length(KualiForm.meetingHelper.otherPresentBeans) > 1}">      	
        	            </table>
			        </div>
		        </td>
			</tr>
        </c:if>        	
    </table>	
