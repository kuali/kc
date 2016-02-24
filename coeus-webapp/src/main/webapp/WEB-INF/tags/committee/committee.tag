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


<%@ attribute name="cmtAttributes" required="false" type="java.util.Map"%>
<c:choose>
	<c:when test="${cmtAttributes == null}">
		<c:set var="committeeAttributes" value="${DataDictionary.Committee.attributes}" />
	</c:when>
	<c:otherwise>
		<c:set var="committeeAttributes" value="${cmtAttributes}" />
	</c:otherwise>
</c:choose>



<c:set var="action" value="committeeCommittee" />
<c:set var="className" value="org.kuali.kra.committee.document.CommitteeDocument" />
<c:set var="readOnly" value="${!KualiForm.committeeHelper.modifyCommittee}" scope="request" />

<kul:tab tabTitle="Committee" defaultOpen="true" tabErrorKey="document.committeeList[0].advancedSubmissionDaysRequired,document.committeeList[0].committeeId,document.committeeList[0].committeeName,document.committeeList[0].committeeTypeCode,document.committeeList[0].homeUnitNumber,document.committeeList[0].maxProtocols,document.committeeList[0].minimumMembersRequired,document.committeeList[0].reviewTypeCode" 
					auditCluster="requiredFieldsAuditErrors" tabAuditKey="document.title" useRiceAuditMode="true">
					
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">Committee</span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.committee.bo.Committee" altText="help"/></span>
        </h3>
		
		<table cellpadding="4" cellspacing="0" summary="">
            <tr>
		        <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${committeeAttributes.committeeId}" /></div></th>
                <td>
                    <c:choose>
	                    <c:when test="${empty KualiForm.document.committeeList[0].id}">
	                    	<kul:htmlControlAttribute property="document.committeeList[0].committeeId" readOnly="${readOnly or KualiForm.document.committeeList[0].sequenceNumber > 1}" attributeEntry="${committeeAttributes.committeeId}" />
	                    </c:when>
	                    <c:otherwise>
	                      <span id="committeeId">
	                          ${KualiForm.document.committeeList[0].committeeId}
	                      </span>
	                    </c:otherwise>
                   </c:choose>
                </td>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${committeeAttributes.committeeName}" /></div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.committeeList[0].committeeName" attributeEntry="${committeeAttributes.committeeName}" readOnly="${readOnly}"/>
                </td>
            </tr>
            
            <tr>
		        <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${committeeAttributes.homeUnitNumber}" /></div></th>
                <td>
                    <kul:htmlControlAttribute 
                    	property="document.committeeList[0].homeUnitNumber" 
                    	attributeEntry="${committeeAttributes.homeUnitNumber}"
                    	onblur="ajaxLoad('getUnitName', 'document.committeeList[0].homeUnitNumber', 'document.committee.homeUnitName');"/>
                    <c:choose>
                    <c:when test="${!readOnly}">
                        <kul:lookup boClassName="org.kuali.coeus.common.framework.unit.Unit" fieldConversions="unitNumber:document.committeeList[0].homeUnitNumber" />
                    </c:when>
                    <c:otherwise>
                        <html:hidden property="document.committeeList[0].homeUnitNumber"/>
                    </c:otherwise>
                    </c:choose>
                    <div id="document.committee.homeUnitName.div" align="left" />
                </td>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${committeeAttributes.committeeTypeCode}" /></div></th>
                <td align="left" valign="middle">
                	<c:out value="${KualiForm.document.committeeList[0].committeeType.description}" />
                </td>
            </tr>
            
            <tr>
		        <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${committeeAttributes.committeeDescription}" /></div></th>
                <td>
                    <kul:htmlControlAttribute property="document.committeeList[0].committeeDescription" attributeEntry="${committeeAttributes.committeeDescription}" />
                </td>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${committeeAttributes.scheduleDescription}" /></div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.committeeList[0].scheduleDescription" attributeEntry="${committeeAttributes.scheduleDescription}" />
                </td>
            </tr>
            
            <tr>
		        <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${committeeAttributes.minimumMembersRequired}" /></div></th>
                <td>
                    <kul:htmlControlAttribute property="document.committeeList[0].minimumMembersRequired" attributeEntry="${committeeAttributes.minimumMembersRequired}" />
                </td>
                
                <th><div align="right">*<kul:htmlAttributeLabel attributeEntry="${committeeAttributes.reviewTypeCode}" /></div></th>
                <td align="left" valign="middle">
                  <!-- review type  -->
                    <c:if test="${readOnly}">
                	    ${KualiForm.document.committeeList[0].reviewType.description}
                    </c:if>
                    <c:if test="${!readOnly}">
                	    <kul:htmlControlAttribute property="document.committeeList[0].reviewTypeCode" attributeEntry="${committeeAttributes.reviewTypeCode}" />
                    </c:if>
                    <kul:checkErrors keyMatch="document.committeeList[0].reviewTypeCode" />
                  
                </td>
            </tr>
            
            <tr>
		        <th><div align="right">
		        	<div id="maxProtocolsLabel">
		        		<c:if test="${!readOnly}">
		        			<kul:htmlAttributeLabel attributeEntry="${committeeAttributes.maxProtocols}" />
		        		</c:if>
		        	</div>
		        	</div>
		        </th>
                <td>
                    <c:if test="${!readOnly}">
                    	<kul:htmlControlAttribute property="document.committeeList[0].maxProtocols" attributeEntry="${committeeAttributes.maxProtocols}" />
                    </c:if>
                </td>
                <th><div align="right"><nobr><kul:htmlAttributeLabel attributeEntry="${committeeAttributes.updateTimestamp}" /></nobr></div></th>
                <td align="left" valign="middle">
                    <kul:htmlControlAttribute property="document.committeeList[0].updateTimestamp" readOnly="true" attributeEntry="${committeeAttributes.updateTimestamp}" />
                </td>
            </tr>
            
            <tr>
		        <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${committeeAttributes.advancedSubmissionDaysRequired}" /></div></th>
                <td>
                    <kul:htmlControlAttribute property="document.committeeList[0].advancedSubmissionDaysRequired" attributeEntry="${committeeAttributes.advancedSubmissionDaysRequired}" />
                </td>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${committeeAttributes.updateUser}" /></div></th>
                <td align="left" valign="middle">
                    <kul:htmlControlAttribute property="document.committeeList[0].updateUser" readOnly="true" attributeEntry="${committeeAttributes.updateUser}" />
                </td>
            </tr>
		</table>
	</div>	
</kul:tab>
