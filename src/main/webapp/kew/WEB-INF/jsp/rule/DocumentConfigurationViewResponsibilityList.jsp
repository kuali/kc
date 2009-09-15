<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>

        	          <table class="datatable" cellspacing="0" cellpadding="0" align="center" style="text-align: left; margin-left: auto; margin-right: auto; padding-left: 5em;">
        	            <tbody>
        	              <tr>
        	                <th style="width: 8em;">
        	                	<kul:htmlAttributeLabel attributeEntry="${kimAttributes.required}" noColon="true" />
       	                	</th>
        	                <th style="width: 11em;">
        	                	<kul:htmlAttributeLabel attributeEntry="${kimAttributes.actionDetailsAtRoleMemberLevel}" noColon="true" />
       	                	</th>
        	                <th>
                               <kul:htmlAttributeLabel attributeEntry="${responsibilityAttributes.assignedToRolesToDisplay}" noColon="true" />
                            </th>
        	                <th style="width: 20em;">Inherited</th>
       	                	<c:if test="${KualiForm.canInitiateResponsibilityDocument}">
	        	                <th style="width: 12em;">
	                                <a href="<c:url value="${ConfigProperties.kr.url}/${Constants.MAINTENANCE_ACTION}">
		                                <c:param name="methodToCall" value="Constants.MAINTENANCE_NEWWITHEXISTING_ACTION" />
		                                <%-- TODO: replace this class name with the interface or maintenance class and let module service handle --%>
		                                <c:param name="businessObjectClassName" value="org.kuali.rice.kim.bo.role.impl.KimResponsibilityImpl"/>
		                                <%-- TODO: replace hard-coding of attribute IDs with lookup stored on form --%>
		                                <c:param name="detailObjects[0].kimAttributeId" value="13"/>
		                                <c:param name="detailObjects[0].attributeValue" value="${documentType.name}"/>
		                                <c:param name="detailObjects[1].kimAttributeId" value="16"/>
		                                <c:param name="detailObjects[1].attributeValue" value="${node.routeNodeName}"/>
	                                </c:url>" target="_blank">Add Responsibility</a>
	        	                </th>
                            </c:if>	                
        	              </tr>
        	              <c:forEach var="resp" items="${responsibilities}">
        	                 <tr <c:if test="${resp.overridden}">class="overridden"</c:if>>
        	                    <td>                                	
                                	<c:choose>
                                	   <c:when test="${resp.details['required']}">Yes</c:when>
                                	   <c:otherwise>No</c:otherwise>
                                	</c:choose>
                                	<c:if test="${resp.overridden}"></del></c:if>
                                </td>
                                <td>
                                	<c:choose>
                                	   <c:when test="${resp.details['actionDetailsAtRoleMemberLevel']}">Yes</c:when>
                                	   <c:otherwise>No</c:otherwise>
                                	</c:choose>
                                </td>
                                <td>
                                	<c:forEach var="role" items="${KualiForm.responsibilityRoles[resp.responsibilityId]}">
                                		<kul:inquiry boClassName="org.kuali.rice.kim.bo.impl.RoleImpl" keyValues="roleId=${role.roleId}" render="true">
                                    		<c:out value="${role.namespaceCode} ${role.roleName}" />
                                		</kul:inquiry>
                                		<br />
                                	</c:forEach>
                                	<c:if test="${empty KualiForm.responsibilityRoles[resp.responsibilityId]}">
				                		&nbsp;
				                	</c:if>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${resp.details.documentTypeName == documentType.name}">
                                            No
                                        </c:when>
                                        <c:otherwise>
                                        	<a href="?documentTypeName=${resp.details.documentTypeName}"><c:out value="${resp.details.documentTypeName}" /></a>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                               	<c:if test="${KualiForm.canInitiateResponsibilityDocument}">
	                                <td>
	                                    <a href="<c:url value="${ConfigProperties.kr.url}/${Constants.MAINTENANCE_ACTION}">
		                                    <c:param name="methodToCall" value="edit" />
		                                    <c:param name="businessObjectClassName" value="org.kuali.rice.kim.bo.role.impl.KimResponsibilityImpl"/>
		                                    <c:param name="responsibilityId" value="${resp.responsibilityId}"/>
	                                    </c:url>" target="_blank">Edit Responsibility</a>
	                                </td>
                                </c:if>
        	                 </tr>
        	              </c:forEach>
        	            </tbody>
        	          </table>
