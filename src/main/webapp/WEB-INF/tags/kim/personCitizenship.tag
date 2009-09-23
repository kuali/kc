<%--
 Copyright 2009 The Kuali Foundation
 
 Licensed under the Educational Community License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 
 http://www.opensource.org/licenses/ecl2.php
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>

<c:set var="docCitizenshipAttributes" value="${DataDictionary.PersonDocumentCitizenship.attributes}" />

<kul:subtab lookedUpCollectionName="citizenship" width="${tableWidth}" subTabTitle="Citizenships">      
        <table cellpadding="0" cellspacing="0" summary="">
          	<tr>
          		<th><div align="left">&nbsp;</div></th> 
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${docCitizenshipAttributes.countryCode}" noColon="true" /></div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${docCitizenshipAttributes.startDate}" noColon="true" /></div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${docCitizenshipAttributes.endDate}" noColon="true" /></div></th>
              	<kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col"/>
          	
          	</tr>     
          	
             <tr>
				<th class="infoline">Add:</th>

                <td class="infoline">   
                <div align="center">             	
                  <kul:htmlControlAttribute property="newCitizenship.countryCode" attributeEntry="${docCitizenshipAttributes.countryCode}" readOnly="${readOnly}" />
				</div>
				</td>
                <td align="left" valign="middle" class="infoline">
                	<div align="center"><kul:htmlControlAttribute property="newCitizenship.startDate" attributeEntry="${docCitizenshipAttributes.startDate}" datePicker="true" readOnly="${readOnly}" />
                </div>
                </td>
                <td align="left" valign="middle" class="infoline">
                <div align="center">
                	<kul:htmlControlAttribute property="newCitizenship.endDate" attributeEntry="${docCitizenshipAttributes.endDate}" datePicker="true" readOnly="${readOnly}" />
	            </div>
				</td>
                <td class="infoline">
					<div align=center>
						<html:image property="methodToCall.addCitizenship.anchor${tabKey}"
						src='${ConfigProperties.kr.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton"/>
					</div>
                </td>
       </tr>         
            
        	<c:forEach var="citizenship" items="${KualiForm.document.citizenships}" varStatus="status">
	             <tr>
					<th class="infoline">
						<c:out value="${status.index+1}" />
					</th>
	                <td>     
	                <div align="center">           	
	                  <kul:htmlControlAttribute property="document.citizenships[${status.index}].countryCode" attributeEntry="${docCitizenshipAttributes.countryCode}" readOnly="${readOnly}" />
					</div>
					</td>
	                <td align="left" valign="middle">
	                <div align="center"><kul:htmlControlAttribute property="document.citizenships[${status.index}].startDate" attributeEntry="${docCitizenshipAttributes.startDate}" datePicker="true" readOnly="${readOnly}" /></div>
	                </td>
	                <td align="left" valign="middle">
	                	<div align="center"> <kul:htmlControlAttribute property="document.citizenships[${status.index}].endDate"  attributeEntry="${docCitizenshipAttributes.endDate}" datePicker="true" readOnly="${readOnly}" />
					</div>
					</td>

					<td>
					<div align=center>&nbsp;
						<html:image property="methodToCall.deleteCitizenship.line${status.index}.anchor${currentTabIndex}"
							src='${ConfigProperties.kr.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
					</div>
	                </td>
	            </tr>
        	</c:forEach>        

            
        </table>
</kul:subtab>
