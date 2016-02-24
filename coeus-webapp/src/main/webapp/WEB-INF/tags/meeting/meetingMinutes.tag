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

<%-- <c:set var="readOnly" value="${KualiForm.readOnly}"  scope="request"/> --%>

<%-- optional attributes with default initializers --%>
<%@ attribute name="committeeScheduleMinuteBOClassName" required="false" %>
<c:if test="${committeeScheduleMinuteBOClassName == null}">
	<c:set var="committeeScheduleMinuteBOClassName" value="org.kuali.kra.meeting.CommitteeScheduleMinute" />
</c:if>

<%@ attribute name="protocolValuesFinderClassName" required="false" %>
<c:if test="${protocolValuesFinderClassName == null}">
	<c:set var="protocolValuesFinderClassName" value="org.kuali.kra.meeting.ProtocolValuesFinder" />
</c:if>

<%@ attribute name="committeeScheduleMinuteAttributes" required="false" type="java.util.Map" %>
<c:if test="${committeeScheduleMinuteAttributes == null}">
	<c:set var="committeeScheduleMinuteAttributes" value="${DataDictionary.CommitteeScheduleMinute.attributes}" />
</c:if>

<%@ attribute name="protocolContingencyBOClassName" required="false" %>
<c:if test="${protocolContingencyBOClassName == null}">
	<c:set var="protocolContingencyBOClassName" value="org.kuali.kra.meeting.ProtocolContingency" />
</c:if>

<c:set var="attributeReferenceDummyAttributes" value="${DataDictionary.AttributeReference.attributes}" />
<jsp:useBean id="paramMap" class="java.util.HashMap"/>
<c:set target="${paramMap}" property="scheduleId" value="${KualiForm.meetingHelper.committeeSchedule.id}" />
<c:set var="showdiv" value="display :block"/>
<c:set var="hidediv" value="display :none"/>

<c:choose>
    <c:when test="${not empty KualiForm.meetingHelper.newCommitteeScheduleMinute.minuteEntryTypeCode and KualiForm.meetingHelper.newCommitteeScheduleMinute.minuteEntryTypeCode == '2'}">
        <c:set var="defaultHeaderDivStyle" value="display :none"/>
        <c:set var="genAttHeaderDivStyle" value="display :block"/>
        <c:set var="genAttDivStyle" value="display :block"/>
        <c:set var="pcHeaderDivStyle" value="display :none"/>
        <c:set var="pcDivStyle" value="display :none"/>
        <c:set var="actionItemHeaderDivStyle" value="display :none"/>
        <c:set var="actionItemDivStyle" value="display :none"/>
    </c:when>
    <c:when test="${not empty KualiForm.meetingHelper.newCommitteeScheduleMinute.minuteEntryTypeCode and KualiForm.meetingHelper.newCommitteeScheduleMinute.minuteEntryTypeCode == '3'}">
        <c:set var="defaultHeaderDivStyle" value="display :none"/>
        <c:set var="genAttHeaderDivStyle" value="display :none"/>
        <c:set var="genAttDivStyle" value="display :none"/>
        <c:set var="pcHeaderDivStyle" value="display :block"/>
        <c:set var="pcDivStyle" value="display :block"/>
        <c:set var="actionItemHeaderDivStyle" value="display :none"/>
        <c:set var="actionItemDivStyle" value="display :none"/>
    </c:when>
    <c:when test="${not empty KualiForm.meetingHelper.newCommitteeScheduleMinute.minuteEntryTypeCode and KualiForm.meetingHelper.newCommitteeScheduleMinute.minuteEntryTypeCode == '4'}">
        <c:set var="defaultHeaderDivStyle" value="display :none"/>
        <c:set var="genAttHeaderDivStyle" value="display :none"/>
        <c:set var="genAttDivStyle" value="display :none"/>
        <c:set var="pcHeaderDivStyle" value="display :none"/>
        <c:set var="pcDivStyle" value="display :none"/>
        <c:set var="actionItemHeaderDivStyle" value="display :block"/>
        <c:set var="actionItemDivStyle" value="display :block"/>
    </c:when>
    <c:otherwise>
        <c:set var="defaultHeaderDivStyle" value="display :block"/>
        <c:set var="genAttHeaderDivStyle" value="display :none"/>
        <c:set var="genAttDivStyle" value="display :none"/>
        <c:set var="pcHeaderDivStyle" value="display :none"/>
        <c:set var="actionItemHeaderDivStyle" value="display :none"/>
        <c:set var="actionItemDivStyle" value="display :none"/>
        <c:set var="pcDivStyle" value="display :none"/>
    </c:otherwise>
</c:choose>
<kul:tab defaultOpen="false" tabTitle="Minutes"
    tabErrorKey="meetingHelper.newCommitteeScheduleMinute.*">

    <div class="tab-container" align="center">
        <h3>
            <span class="subhead-left"> Minutes </span>
            <span class="subhead-right"> <kul:help businessObjectClassName="${committeeScheduleMinuteBOClassName}" altText="help"/> </span>
        </h3>
        <table id="minutes-table" cellpadding=0 cellspacing=0 class="datatable" summary="view /edit Meeting Minutes">
        
        	<%-- Header --%>
        	<tr>
        		<kul:htmlAttributeHeaderCell literalLabel="&nbsp;" scope="col" />
        		<kul:htmlAttributeHeaderCell attributeEntry="${committeeScheduleMinuteAttributes.minuteEntryTypeCode}" scope="col" />
        		<th>
                    <div align="center" id ="meetingHelper.newCommitteeScheduleMinute.defaultHeaderDiv" style="${defaultHeaderDivStyle}">Entry Type Detail</div>
                    <div align="center" id ="meetingHelper.newCommitteeScheduleMinute.genAttHeaderDiv" style="${genAttHeaderDivStyle}">Generate Attendance</div>
                    <div align="center" id ="meetingHelper.newCommitteeScheduleMinute.pcHeaderDiv" style="${pcHeaderDivStyle}"><font color="">*&nbsp;</font>Protocol</div>
                    <div align="center" id ="meetingHelper.newCommitteeScheduleMinute.actionItemHeaderDiv" style="${actionItemHeaderDivStyle}"><font color="">*&nbsp;</font>Other Action</div>
                </th> 
				<kul:htmlAttributeHeaderCell attributeEntry="${committeeScheduleMinuteAttributes.minuteEntry}" scope="col" />
				<kul:htmlAttributeHeaderCell literalLabel="Standard Review Comment" scope="col" />
				<kul:htmlAttributeHeaderCell attributeEntry="${committeeScheduleMinuteAttributes.privateCommentFlag}" scope="col" />
				<kul:htmlAttributeHeaderCell attributeEntry="${committeeScheduleMinuteAttributes.finalFlag}" scope="col" />
                <c:if test="${not KualiForm.meetingHelper.hideReviewerName}">
                <kul:htmlAttributeHeaderCell literalLabel="Last Updated By" scope = "col"/>
                <kul:htmlAttributeHeaderCell literalLabel="Created By" scope = "col"/>
                </c:if>
				<c:if test="${!readOnly}">
					<kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col" />
				</c:if>
			</tr>
			<%-- Header --%>

	
            <%-- New data --%>
            <c:if test="${!readOnly}">
	            <tr>
			        <th class="infoline" width="5%">
					    <c:out value="Add:" />
				    </th>
	
	                <td align="left" valign="middle" class="infoline">
	               	    <div align="center">
	               		    <kul:htmlControlAttribute property="meetingHelper.newCommitteeScheduleMinute.minuteEntryTypeCode" onchange="showHideDiv(this);"  attributeEntry="${committeeScheduleMinuteAttributes.minuteEntryTypeCode}" readOnly="false" />
	            	    </div>
				    </td>
	                <td align="left" valign="middle" class="infoline">
	               	    <div align="center" id = "meetingHelper.newCommitteeScheduleMinute.genAttDiv" style="${genAttDivStyle}">
                            <kul:htmlControlAttribute property="meetingHelper.newCommitteeScheduleMinute.generateAttendance" attributeEntry="${attributeReferenceDummyAttributes.genericBoolean}" 
                               onclick="generateAttendance(this, ${fn:length(KualiForm.meetingHelper.memberPresentBeans)}, ${fn:length(KualiForm.meetingHelper.otherPresentBeans)});" />
                        </div>
	               	    <div align="center" id = "meetingHelper.newCommitteeScheduleMinute.pcSelectDiv" style="${pcDivStyle}">
	                        <c:set var="hasProtocolError" value="false"/>
          	                <c:set var="fieldName" value="meetingHelper.newCommitteeScheduleMinute.protocolIdFk" />
			                <c:forEach items="${ErrorPropertyList}" var="key">
				                <c:if test="${key eq fieldName }">
	                                <c:set var="hasProtocolError" value="true"/>
				                </c:if>
			                </c:forEach>
	               		    <html:select property="meetingHelper.newCommitteeScheduleMinute.protocolIdFk" tabindex="0"  > 		                        
		                        <c:forEach items="${krafn:getOptionList(protocolValuesFinderClassName, paramMap)}" var="option">
		                            <c:choose>                    	
		                    	        <c:when test="${KualiForm.meetingHelper.newCommitteeScheduleMinute.protocolIdFk == option.key}">
		                                    <option value="${option.key}" selected>${option.value}</option>
		                                </c:when>
		                                <c:otherwise>		                        
		                                    <c:out value="${option.value}"/>
		                                    <option value="${option.key}">${option.value}</option>
		                                </c:otherwise>
		                            </c:choose> 		                                       
		                        </c:forEach>
		                    </html:select>
                            <c:if test="${hasProtocolError}">
	 		                    <kul:fieldShowErrorIcon />
                            </c:if>
	               		</div>
                        <div align="center" id = "meetingHelper.newCommitteeScheduleMinute.actionItemDiv"  style="${actionItemDivStyle}">
                            <c:set var="hasActionItemError" value="false"/>
                            <c:set var="actionItemFieldName" value="meetingHelper.newCommitteeScheduleMinute.commScheduleActItemsIdFk" />
                            <c:forEach items="${ErrorPropertyList}" var="key">
                                <c:if test="${key eq actionItemFieldName }">
                                    <c:set var="hasActionItemError" value="true"/>
                                </c:if>
                            </c:forEach>
                            <html:select property="meetingHelper.newCommitteeScheduleMinute.commScheduleActItemsIdFk" tabindex="0"  >
                                <option value="">select</option>
                                <c:forEach items="${KualiForm.meetingHelper.committeeSchedule.commScheduleActItems}" var="option">
                                    <c:choose>
                                        <c:when test="${fn:length(option.itemDescription) > 30}">
                                            <c:set var="label" value="${fn:substring(option.itemDescription, 0, 30)}..."/>
                                        </c:when>
                                        <c:otherwise>
                                            <c:set var="label" value="${option.itemDescription}"/>
							            </c:otherwise>
							        </c:choose>
                                    <c:choose>
                                        <c:when test="${KualiForm.meetingHelper.newCommitteeScheduleMinute.commScheduleActItemsIdFk == option.commScheduleActItemsId}">
                                            <option value="${option.commScheduleActItemsId}" selected>${label}</option>
                                        </c:when>
	                                    <c:otherwise>                               
	                                        <c:out value="${label}"/>
	                                        <option value="${option.commScheduleActItemsId}">${label}</option>
	                                    </c:otherwise>
                                    </c:choose>                                                
                                </c:forEach>
                            </html:select>
                            <c:if test="${hasActionItemError}">
                                <kul:fieldShowErrorIcon />
                            </c:if>
                        </div>

                        <noscript>
                             <input type="hidden" name="meetingHelper.jsDisabled" value="true"/>
                        </noscript>
				    </td>
	                <td align="left" valign="middle" class="infoline" width="55%">
	                    <div id="meetingHelper.newCommitteeScheduleMinute.minuteEntry.div" align="left">
	               	        <div align="left">
	               	            <kul:htmlControlAttribute property="meetingHelper.newCommitteeScheduleMinute.minuteEntry" attributeEntry="${committeeScheduleMinuteAttributes.minuteEntry}" readOnly="false" />
	               	        </div>
	                    </div>	
	                </td>
	                <td align="left" valign="middle" class="infoline">
	               	    <div align="center" id = "meetingHelper.newCommitteeScheduleMinute.pcCommentDiv"  style="${pcDivStyle}">
	               		    <kul:htmlControlAttribute property="meetingHelper.newCommitteeScheduleMinute.protocolContingencyCode" attributeEntry="${committeeScheduleMinuteAttributes.protocolContingencyCode}" onblur="loadStandardReviewComment('meetingHelper.newCommitteeScheduleMinute.protocolContingencyCode', 'meetingHelper.newCommitteeScheduleMinute.minuteEntry');"  />
                            <kul:lookup boClassName="${protocolContingencyBOClassName}" 
                                    fieldConversions="protocolContingencyCode:meetingHelper.newCommitteeScheduleMinute.protocolContingencyCode,description:meetingHelper.newCommitteeScheduleMinute.minuteEntry" />
                   	    </div>
                   	    <noscript>
                             <input type="hidden" name="meetingHelper.jsDisabled" value="true"/>
                        </noscript>
				    </td>
	                <td align="left" valign="middle" class="infoline">
	               	    <div align="center">
	               		    <kul:htmlControlAttribute property="meetingHelper.newCommitteeScheduleMinute.privateCommentFlag" attributeEntry="${committeeScheduleMinuteAttributes.privateCommentFlag}" readOnly="false" />
	            	    </div>
				    </td>
	                <td align="left" valign="middle" class="infoline">
	               	    <div align="center">
	               		    <kul:htmlControlAttribute property="meetingHelper.newCommitteeScheduleMinute.finalFlag" attributeEntry="${committeeScheduleMinuteAttributes.finalFlag}" />
	            	    </div>
				    </td>
                <c:if test="${not KualiForm.meetingHelper.hideReviewerName}">
                    <td>&nbsp;</td>
					<td>&nbsp;</td>
	            </c:if>
				    <td align="left" valign="middle" class="infoline">
					    <div align=center>
					        <html:image property="methodToCall.addCommitteeScheduleMinute.anchor${tabKey}"
						    src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton"/>
					    </div>
	                </td>
	            </tr>
	        </c:if> 
			<%-- New data --%>
			
			<%-- Existing data --%>
            <c:set var="maxLen" value="${fn:length(KualiForm.meetingHelper.committeeSchedule.committeeScheduleMinutes) - 1}"/>
        	<c:forEach var="committeeScheduleMinute" items="${krafn:copy(KualiForm.meetingHelper.committeeSchedule.committeeScheduleMinutes)}" varStatus="status">
	            <tr>
					<th class="infoline">
						&nbsp;
					</th>
	                <td align="left" valign="middle">
	                    ${committeeScheduleMinute.minuteEntryType.description}
					</td>
	                <td align="left" valign="middle">
	                    <c:if test="${!empty committeeScheduleMinute.protocolIdFk}" >
	                        ${committeeScheduleMinute.protocol.protocolNumber}
	                    </c:if>
	                    <c:if test="${!empty committeeScheduleMinute.commScheduleActItemsIdFk}" >
	                       ${committeeScheduleMinute.commScheduleActItem.scheduleActItemType.description}:
	                       ${committeeScheduleMinute.commScheduleActItem.itemDescription}
	                    </c:if>
					</td>
	                <td align="left" valign="middle" colspan="2">
	               		  <kul:htmlControlAttribute property="meetingHelper.committeeSchedule.committeeScheduleMinutes[${status.index}].minuteEntry" attributeEntry="${committeeScheduleMinuteAttributes.minuteEntry}" />
					</td>
	                <td align="left" valign="middle" class="infoline">
	               	    <div align="center">
	               		    <kul:htmlControlAttribute property="meetingHelper.committeeSchedule.committeeScheduleMinutes[${status.index}].privateCommentFlag" attributeEntry="${committeeScheduleMinuteAttributes.privateCommentFlag}" />
	            	    </div>
				    </td>
	                <td align="left" valign="middle" class="infoline">
	               	    <div align="center">
	               		    <kul:htmlControlAttribute property="meetingHelper.committeeSchedule.committeeScheduleMinutes[${status.index}].finalFlag" attributeEntry="${committeeScheduleMinuteAttributes.finalFlag}" />
	            	    </div>
				    </td>
                <c:if test="${not KualiForm.meetingHelper.hideReviewerName}">
				    
                   <c:choose>
                       <c:when test="${KualiForm.meetingHelper.committeeSchedule.committeeScheduleMinutes[status.index].displayReviewerName}">
	                        <td style="text-align:center; vertical-align:middle">
	                        	<kul:htmlControlAttribute property="meetingHelper.committeeSchedule.committeeScheduleMinutes[${status.index}].updateUserFullName" 
	                                                      attributeEntry="${committeeScheduleMinuteAttributes.updateUser}"
	                                                      readOnly="true" />  <kul:htmlControlAttribute property="meetingHelper.committeeSchedule.committeeScheduleMinutes[${status.index}].updateTimestamp" 
	                                                      attributeEntry="${committeeScheduleMinuteAttributes.updateTimestamp}"
	                                                      readOnly="true" />
	                        </td>
	                        
	                        <td style="text-align:center; vertical-align:middle">
	                        	<kul:htmlControlAttribute property="meetingHelper.committeeSchedule.committeeScheduleMinutes[${status.index}].createUserFullName" 
	                                                      attributeEntry="${committeeScheduleMinuteAttributes.createUser}"
	                                                      readOnly="true" /> <kul:htmlControlAttribute property="meetingHelper.committeeSchedule.committeeScheduleMinutes[${status.index}].createTimestamp" 
	                                                      attributeEntry="${committeeScheduleMinuteAttributes.createTimestamp}"
	                                                      readOnly="true" />
	                        </td>
                       </c:when>
                       <c:otherwise>
                           <td>&nbsp;</td>
					       <td>&nbsp;</td>
                       </c:otherwise>
                   </c:choose>
				   </c:if>
                   <c:if test="${!readOnly}">
						<td nowrap>
                            <div>
                                <c:if test="${status.index > 0}">
                                    <html:image property="methodToCall.moveupCommitteeScheduleMinute.line${status.index}.anchor${currentTabIndex}"
                                                src='${ConfigProperties.kra.externalizable.images.url}tinybutton-moveup.gif' styleClass="tinybutton" title="Move Up"/>
                                </c:if>
                                <c:if test="${status.index < maxLen}">
                                    <html:image property="methodToCall.movedownCommitteeScheduleMinute.line${status.index}.anchor${currentTabIndex}"
                                                src='${ConfigProperties.kra.externalizable.images.url}tinybutton-movedown.gif' styleClass="tinybutton" title="Move Down"/>
                                </c:if>
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
