<%--
 Copyright 2008-2009 The Kuali Foundation
 
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

<c:set var="docAffiliationAttributes" value="${DataDictionary.PersonDocumentAffiliation.attributes}" />

<kul:subtab lookedUpCollectionName="affiliations" width="${tableWidth}" subTabTitle="Affiliations" noShowHideButton="true" >      
        <table cellpadding="0" cellspacing="0" summary="">
          	<tr>
          		<th><div align="left">&nbsp;</div></th> 
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${docAffiliationAttributes.affiliationTypeCode}" noColon="true" /></div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${docAffiliationAttributes.campusCode}" noColon="true" /></div></th>
          		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${docAffiliationAttributes.dflt}" noColon="true" /></div></th>
           <c:if test="${not inquiry}">	
              	<kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col"/>
          </c:if>	
          	</tr>     
          <c:if test="${not inquiry and not readOnlyEntity}">	
             <tr>
				<th class="infoline">
					<c:out value="Add:" />
				</th>

                <td align="left" valign="middle" class="infoline">
                	<div align="center"><kul:htmlControlAttribute property="newAffln.affiliationTypeCode" attributeEntry="${docAffiliationAttributes.affiliationTypeCode}" disabled="${readOnlyEntity}"/>
                </div>
                </td>
                <td class="infoline">   
                <div align="center">             	
                  <kul:htmlControlAttribute property="newAffln.campusCode" attributeEntry="${docAffiliationAttributes.campusCode}" readOnly="${readOnlyEntity}" />
				</div>
				</td>
                <td class="infoline">
                <div align="center">
                	<kul:htmlControlAttribute property="newAffln.dflt" attributeEntry="${docAffiliationAttributes.dflt}" readOnly="${readOnlyEntity}" /> 
				</div>
                </td>
                <td class="infoline">
					<div align=center>
						<html:image property="methodToCall.addAffln.anchor${tabKey}"
						src='${ConfigProperties.kr.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton"/>
					</div>
                </td>
       </tr>         
       </c:if>     
        	<c:forEach var="affln" items="${KualiForm.document.affiliations}" varStatus="status">
        	    <c:set var="rowSpan" value="1"/>
        		<c:if test="${affln.employmentAffiliationType or fn:length(affln.empInfos) > 0}" >
        	    	<c:set var="rowSpan" value="2"/>
        		</c:if>
        	
	             <tr>
					<th rowspan="${rowSpan}" class="infoline">
						<c:out value="${status.index+1}" />
					</th>
	                <td align="left" valign="middle">
	                <div align="center"><kul:htmlControlAttribute property="document.affiliations[${status.index}].affiliationTypeCode" attributeEntry="${docAffiliationAttributes.affiliationTypeCode}"  readOnlyAlternateDisplay="${fn:escapeXml(affln.affiliationType.affiliationTypeName)}" disabled="true" readOnly="false" /></div>
	                </td>
	                <td>     
	                <div align="center">           	
	                  <kul:htmlControlAttribute property="document.affiliations[${status.index}].campusCode" attributeEntry="${docAffiliationAttributes.campusCode}" readOnly="${readOnlyEntity}" />
					</div>
					</td>
	                <td align="left" valign="middle">
		                <div align="center">
		                	<kul:htmlControlAttribute property="document.affiliations[${status.index}].dflt" attributeEntry="${docAffiliationAttributes.dflt}" readOnly="${readOnlyEntity}" /> 
						</div>
                    </td>
           <c:if test="${not inquiry}">	

					<td>
					<div align=center>&nbsp;
	        	     <c:choose>
	        	       <c:when test="${affln.edit  or readOnlyEntity}">
	        	          <img class='nobord' src='${ConfigProperties.kr.externalizable.images.url}tinybutton-delete2.gif' styleClass='tinybutton'/>
	        	       </c:when>
	        	       <c:otherwise>
	        	          <html:image property="methodToCall.deleteAffln.line${status.index}.anchor${currentTabIndex}"
								src='${ConfigProperties.kr.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
	        	       </c:otherwise>
	        	     </c:choose>  
					</div>
	                </td>
	            </c:if>    
	            </tr>
	            <c:if test="${affln.employmentAffiliationType  or fn:length(affln.empInfos) > 0}" >
	            <tr>
	              <td colspan=5 style="padding:0px;">
		        	<kim:personEmpInfo afflnIdx="${status.index}" />
		          </td>
		        </tr>
		        </c:if>
        	</c:forEach>        

            
        </table>
</kul:subtab>
		
