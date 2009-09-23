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

<c:set var="responsibilityAttributes" value="${DataDictionary.ResponsibilityImpl.attributes}" />

<kul:tab tabTitle="Responsibilities" defaultOpen="true" tabErrorKey="document.resp*">
	<div class="tab-container" align="center">
    
    <table cellpadding=0 cellspacing=0 summary="">
          <c:if test="${!readOnly}">	
          	
             <tr>
				<td align="center">
	                <div align="center">
	                	<br/>
						<b>Add Responsibility ID:</b>
						<kul:htmlControlAttribute property="responsibility.responsibilityId" attributeEntry="${responsibilityAttributes.responsibilityId}"/>
	                	<kul:lookup boClassName="org.kuali.rice.kim.bo.impl.ResponsibilityImpl" fieldConversions=
	                	"template.name:responsibility.kimResponsibility.template.name,responsibilityId:responsibility.responsibilityId,name:responsibility.kimResponsibility.name,namespaceCode:responsibility.kimResponsibility.namespaceCode" anchor="${tabKey}" />
						<html:hidden property="responsibility.kimResponsibility.name" />
						<html:hidden property="responsibility.kimResponsibility.namespaceCode" />
						${KualiForm.responsibility.kimResponsibility.namespaceCode}&nbsp;&nbsp;${KualiForm.responsibility.kimResponsibility.name}&nbsp;
	                	<br/>
	                	<br/>
		            </div>
				</td>
			</tr>
			<tr>                                
                <td class="infoline">
					<div align="center">
						<html:image property="methodToCall.addResponsibility.anchor${tabKey}"
							src="${ConfigProperties.kr.externalizable.images.url}tinybutton-add1.gif" styleClass="tinybutton"/>
					</div>
                </td>
	       </tr>         
     </c:if>       
	</table>
	<table>
        	<tr>
        		<th>&nbsp;</th> 
        		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${responsibilityAttributes.namespaceCode}" noColon="true" /></div></th>
        		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${responsibilityAttributes.responsibilityId}" noColon="true" /></div></th>
        		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${responsibilityAttributes.name}" noColon="true" /></div></th>
        		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${responsibilityAttributes.detailObjectsValues}" noColon="true" /></div></th>
        		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${responsibilityAttributes.active}" noColon="true" /></div></th>
				<c:if test="${!readOnly}">	
            		<kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col"/>
				</c:if>	
        	</tr>     
      	<c:forEach var="responsibility" items="${KualiForm.document.responsibilities}" varStatus="status">
       	    <c:set var="rows" value="1"/>
       		<c:if test="${responsibility.roleRspAction.roleResponsibilityId!=null}">	
        	       <c:set var="rows" value="2"/>
       		</c:if>        	
            <tr>
				<th rowspan="${rows}" class="infoline" valign="top">
					<c:out value="${status.index+1}" />
				</th>
	            <td align="left" valign="middle">
	               	<div align="left"> <kul:htmlControlAttribute property="document.responsibilities[${status.index}].kimResponsibility.namespaceCode"  attributeEntry="${responsibilityAttributes.namespaceCode}" readOnly="true"  />
					</div>
				</td>
	            <td align="left" valign="middle">
	               	<div align="left"> <kul:htmlControlAttribute property="document.responsibilities[${status.index}].responsibilityId"  attributeEntry="${responsibilityAttributes.responsibilityId}" readOnly="true"  />
					</div>
				</td>
	            <td align="left" valign="middle">
	               	<div align="left"> <kul:htmlControlAttribute property="document.responsibilities[${status.index}].kimResponsibility.name"  attributeEntry="${responsibilityAttributes.name}" readOnly="true"  />
					</div>
				</td>
	            <td align="left" valign="middle">
	               	<div align="left"> <kul:htmlControlAttribute property="document.responsibilities[${status.index}].kimResponsibility.detailObjectsValues"  attributeEntry="${responsibilityAttributes.detailObjectsToDisplay}" readOnly="true"  />
					</div>
				</td>
				<c:choose>
					<c:when test="${responsibility.edit && !readOnly}">
			            <td align="center" valign="middle">
			               	<div align="center"> <kul:htmlControlAttribute property="document.responsibilities[${status.index}].active"  attributeEntry="${responsibilityAttributes.active}" />
							</div>
						</td>
					</c:when>
       	       		<c:otherwise>
			            <td align="center" valign="middle">
			               	<div align="center"> <kul:htmlControlAttribute property="document.responsibilities[${status.index}].active"  attributeEntry="${responsibilityAttributes.active}" readOnly="true"  />
							</div>
						</td>
        	       	</c:otherwise>
       	     	</c:choose>  
			<c:if test="${!readOnly}">	
				<td>
					<div align=center>&nbsp;
						<c:choose>
							<c:when test="${responsibility.edit}">
	        	          		<img class='nobord' src='${ConfigProperties.kr.externalizable.images.url}tinybutton-delete2.gif' styleClass='tinybutton'/>
							</c:when>
	        	       		<c:otherwise>
	        	        		<html:image property='methodToCall.deleteResponsibility.line${status.index}.anchor${currentTabIndex}'
								src='${ConfigProperties.kr.externalizable.images.url}tinybutton-delete1.gif' styleClass='tinybutton'/>
		        	       	</c:otherwise>
	        	     	</c:choose>  
					</div>
				</td>
			</c:if>    
			</tr>
	        <c:if test="${responsibility.roleRspAction != null}">	
    			<tr>
	              <td colspan="7" style="padding:0px;">
	              	<kim:responsibilityAction responsibilityIdx="${status.index}" />
		          </td>
		        </tr>
			</c:if>	 
		</c:forEach>        
	</table>
	</div>
</kul:tab>
