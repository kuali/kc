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
<style type="text/css">
.multi-col-list dl {
 	clear:both;
	margin: .5em;
	width: 100%;
	min-width:38em;
}
.multi-col-list dl dd {
	width: 32%;
	float: left;
	padding-left: 65px;
	padding-right: .2em;
}
.multi-col-list dl dd ul { 
 	list-style: none; 
 	padding: 0;
}
.multi-col-list dl dd ul li {
   list-style: none;
}
li.truncateAlign table {
    display: inline-block;
    width: auto !important;
}
#workarea th {
  background-repeat: repeat-x;
  background-color: #f5f5f5;
}
#workarea td.infoline {
   background-repeat: repeat-x;
}
</style>
<c:set var="subAwardPrintAgreementAttributes" value="${DataDictionary.SubAwardPrintAgreement.attributes}" />
<c:set var="subAwardForms" value="${DataDictionary.SubAwardForms.attributes}" />
<c:set var="subAwardAttachments" value="${DataDictionary.SubAwardAttachments.attributes}" />
<c:set var="action" value="SubAwardHomeAction" />
<c:set var="subAwardPrintAgreement" value="${KualiForm.subAwardPrintAgreement }"/>

<kul:tab tabTitle="Print" tabItemCount="" defaultOpen="false" tabErrorKey="" auditCluster="requiredFieldsAuditErrors" tabAuditKey="" useRiceAuditMode="true">
		<div class="tab-container" align="center">
		<h3>
			<span class="subhead-left">Print</span>
		</h3>
		<table id="print-table" cellpadding="0" cellspacing="0" summary="Print Options">
			<tr>
				<th rowspan="8" scope="row" style="width:150px; text-align:center;">Agreement/Modification</th>
				</tr>
				<tr>
				<td scope="row" style="width:160px; text-align:center;" nowrap="nowrap">
				<kul:htmlAttributeLabel attributeEntry="${subAwardPrintAgreementAttributes.fundingSource}" useShortLabel="false" noColon="true" /></td>
				<td>
				   <div class="multi-col-list">
						<dl><dd><ul>
						<li><kul:htmlControlAttribute property="subAwardPrintAgreement.fundingSource" 
		      							attributeEntry="${subAwardPrintAgreementAttributes.fundingSource}" readOnly="false"/>
		      				  
		      			</li>
						</ul></dd></dl>
					</div>
						</td>
						<td rowspan="5" class="infoline" style="text-align:center;width:74px;"><html:image property="methodToCall.printForms"
						src='${ConfigProperties.kra.externalizable.images.url}tinybutton-print.gif' 
						alt="Print Award Notice" styleClass="tinybutton" onclick="excludeSubmitRestriction=true"/></td>
				</tr>
				<tr>
				<td scope="row" style="width:160px; text-align:center;" nowrap="nowrap">FDP Template:</td>
				<td>
						<table id="print-table" cellpadding="0" cellspacing="0">
						<tr>

						<div class="multi-col-list" id="awardPrintNoticeItems">
						<dl><dd><ul>
						<li>
				         <html:radio property="subAwardPrintAgreement.fdpType" value="fdpAgreement" />
				         <c:out value="FDP Agreement"/>
		      			</li>
						</ul></dd>
						<dd><ul>
						<li><html:radio property="subAwardPrintAgreement.fdpType" value="fdpModification" />
				         <c:out value="FDP Modification"/>
		      			</li>
						</ul></dd></dl>
						</div>

						</tr>
					</table>
				</td>
				</tr>
				<tr>
					<td scope="row" style="width:160px; text-align:center;" nowrap="nowrap">FDP Template Attachments:</td>
					  <td>
						<div class="multi-col-list" id="awardPrintNoticeItems">
						<dl><dd><ul>
						<li><kul:htmlControlAttribute property="subAwardPrintAgreement.attachment3A" 
		      							attributeEntry="${subAwardPrintAgreementAttributes.attachment3A}" readOnly="false"/>
		      				  <kul:htmlAttributeLabel attributeEntry="${subAwardPrintAgreementAttributes.attachment3A}" useShortLabel="true" noColon="true" />
		      			</li>
		      			<li><kul:htmlControlAttribute property="subAwardPrintAgreement.attachment3B" 
		      							attributeEntry="${subAwardPrintAgreementAttributes.attachment3B}" readOnly="false"/>
		      				  <kul:htmlAttributeLabel attributeEntry="${subAwardPrintAgreementAttributes.attachment3B}" useShortLabel="true" noColon="true" />
						</li>
						</ul></dd>
						<dd><ul>
						<li><kul:htmlControlAttribute property="subAwardPrintAgreement.attachment3BPage2" 
		      							attributeEntry="${subAwardPrintAgreementAttributes.attachment3BPage2}" readOnly="false"/>
		      				  <kul:htmlAttributeLabel attributeEntry="${subAwardPrintAgreementAttributes.attachment3BPage2}" useShortLabel="true" noColon="true" />
		      				  </li><li>
		      		    <kul:htmlControlAttribute property="subAwardPrintAgreement.attachment4" 
		      							attributeEntry="${subAwardPrintAgreementAttributes.attachment4}" readOnly="false"/>
		      				  <kul:htmlAttributeLabel attributeEntry="${subAwardPrintAgreementAttributes.attachment4}" useShortLabel="true" noColon="true" />
						</li>
						</ul></dd></dl>
						</div>
				</td>
				</tr> 
				<tr>
				<td scope="row" style="width:160px; text-align:center;">Sponsor Attachments:</td>
				<td>
				<div class="multi-col-list" id="awardPrintNoticeItems">
				<dl>
				<c:forEach var="form" items="${KualiForm.document.subAwardList[0].subAwardForms}" varStatus="status">
				
				<c:choose>
				<c:when test="${status.index % 2 == 0}" >
				 <dd><ul>
				  <li>
				  <html:checkbox property="document.subAwardList[0].subAwardForms[${status.index}].selectToPrint"/>
				  <c:out value="${KualiForm.document.subAwardList[0].subAwardForms[status.index].description}"/>
				</li>
				</ul></dd>
      			   </c:when>
      			  <c:otherwise >
      			 <dd><ul>
      			  <li>
      			  <html:checkbox property="document.subAwardList[0].subAwardForms[${status.index}].selectToPrint"/>
      			  <c:out value="${KualiForm.document.subAwardList[0].subAwardForms[status.index].description}"/>
      			  </li>
      			  </ul></dd>
      			  </c:otherwise>
      			  </c:choose>
      			  </c:forEach>
      			  </dl>
				  </div>
				
				</td>
				
			   </tr>
			   <tr>
				<td scope="row" style="width:160px; text-align:center;">Subaward Attachments:</td>
				
				<td>
				
				
				<div class="multi-col-list" id="awardPrintNoticeItems">
				 <dl>
				<c:forEach var="attachment" items="${KualiForm.document.subAwardList[0].subAwardAttachments}" varStatus="status">
				<c:if test="${KualiForm.document.subAwardList[0].subAwardAttachments[status.index].fileNameSplit!=null}" >
				<c:choose>
				<c:when test="${status.index % 2 == 0}" >
				 <dd><ul>
				  <li class="truncateAlign">
				  <html:checkbox property="document.subAwardList[0].subAwardAttachments[${status.index}].selectToPrint"/>
				  <kra:truncateComment textAreaFieldName="document.subAwardList[0].subAwardAttachments[${status.index}].description" action="subAwardActions" textAreaLabel="Descripton" 
				  textValue="${KualiForm.document.subAwardList[0].subAwardAttachments[status.index].description}" displaySize="30"/>
				  </li>
				  </ul></dd>
      			  </c:when>
      			  <c:otherwise >
      			 <dd><ul>
      			  <li class="truncateAlign">
				  <html:checkbox property="document.subAwardList[0].subAwardAttachments[${status.index}].selectToPrint"/>
				  <kra:truncateComment textAreaFieldName="document.subAwardList[0].subAwardAttachments[${status.index}].description" action="subAwardActions" textAreaLabel="Descripton" 
				  textValue="${KualiForm.document.subAwardList[0].subAwardAttachments[status.index].description}" displaySize="30"/>
				  </li>
      			  </ul></dd>
      			  </c:otherwise>
      			  </c:choose>
      			  </c:if>
      			  </c:forEach>
      			  </dl>
      			 </div>
      			  </td>
      			  </tr>
			   
				<tr>
				  <td colspan="3" style="text-align: center;">
					  <html:image property="methodToCall.selectAllSubAwardPrintNoticeItems.anchor${tabKey}" src="${ConfigProperties.kra.externalizable.images.url}tinybutton-selectall.gif" title="Select All" alt="Select All" styleClass="tinybutton" onclick="setAllItemsIn('awardPrintNoticeItems', true);return false;" />
      			      <html:image property="methodToCall.deselectAllSubAwardPrintNoticeItems.anchor${tabKey}" src="${ConfigProperties.kra.externalizable.images.url}tinybutton-selectnone.gif" title="Select None" alt="Select None" styleClass="tinybutton" onclick="setAllItemsIn('awardPrintNoticeItems', false);return false;" />
				  </td>
				</tr>
				
				
    	</table>
		</div>
</kul:tab>
