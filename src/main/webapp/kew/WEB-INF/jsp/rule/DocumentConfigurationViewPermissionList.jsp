<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>
	                <table class="datatable" cellspacing="0" cellpadding="0" align="center" style="text-align: left; margin-left: auto; margin-right: auto; padding-left: 5em;">
        	            <tbody>
        	              <tr>
        	                <kul:htmlAttributeHeaderCell scope="col" align="left" 
        	                    attributeEntry="${permissionAttributes['template.name']}" />
                            <kul:htmlAttributeHeaderCell scope="col" align="left" 
                                attributeEntry="${permissionAttributes.name}" />
                            <kul:htmlAttributeHeaderCell scope="col" align="left" 
                                attributeEntry="${permissionAttributes.detailObjectsValues}" />
                            <kul:htmlAttributeHeaderCell scope="col" align="left" 
                                attributeEntry="${permissionAttributes.assignedToRolesToDisplay}" />
                           	<c:if test="${KualiForm.canInitiatePermissionDocument}">
	        	                <th>
	                                <a href="<c:url value="${ConfigProperties.kr.url}/${Constants.MAINTENANCE_ACTION}">
		                                <c:param name="methodToCall" value="Constants.MAINTENANCE_NEWWITHEXISTING_ACTION" />
		                                <%-- TODO: replace this class name with the interface or maintenance class and let module service handle --%>
		                                <c:param name="businessObjectClassName" value="org.kuali.rice.kim.bo.role.impl.KimPermissionImpl"/>
		                                <%-- TODO: replace hard-coding of attribute ID with lookup stored on form --%>
		                                <c:param name="detailObjects[0].kimAttributeId" value="13"/>
		                                <c:param name="detailObjects[0].attributeValue" value="${permDocTypeName}"/>
	                                </c:url>" target="_blank">Add Permission</a>
	        	                </th>
                            </c:if>
        	              </tr>
        	            
        				<c:forEach var="perm" items="${permissions}">
                          <tr <c:if test="${perm.overridden}">class="overridden"</c:if>>
                            <td>
                            	<%-- TODO: update this to use the proper url for an inquiry and not use the impl class --%>
                                <kul:inquiry boClassName="org.kuali.rice.kim.bo.role.impl.KimPermissionTemplateImpl" 
                                			 keyValues="permissionTemplateId=${perm.template.permissionTemplateId}" render="true">
                                <c:out value="${perm.template.name}" />
                                (<c:out value="${perm.template.namespaceCode}" />)
                                </kul:inquiry>
                            </td>
                            <td>
                            	<%-- TODO: update this to use the proper url for a detailed inquiry and not use the impl class --%>
                                <kul:inquiry boClassName="org.kuali.rice.kim.bo.role.impl.KimPermissionImpl" 
                                			 keyValues="permissionId=${perm.permissionId}" render="true">
	                                <c:if test="${empty perm.name}">
	                                    <c:out value="${perm.template.name}" />
	                                </c:if>
	                                <c:out value="${perm.name}" />
	                                (<c:out value="${perm.namespaceCode}" />)
                                </kul:inquiry>
                            </td>
                            <td>
                            	<%-- skip output of the documentTypeName since in subhead --%>
                            	<c:set var="permDisplayed" value="false" />
                                <c:forEach var="dtl" items="${perm.details}" varStatus="status">
                                	<c:if test="${dtl.key != 'documentTypeName'}">
                                    	<c:if test="${permDisplayed}"><br /></c:if>
                                    	<c:if test="${!permDisplayed}"><c:set var="permDisplayed" value="true" /></c:if>
                                    	<c:out value="${attributeLabels[dtl.key]} = ${dtl.value}" />
                                    </c:if>
                                </c:forEach>
                               	<c:if test="${!permDisplayed}">
			                		&nbsp;
			                	</c:if>
                            </td>
                            <td>
                            	<c:forEach var="role" items="${KualiForm.permissionRoles[perm.permissionId]}">
                            		<kul:inquiry boClassName="org.kuali.rice.kim.bo.impl.RoleImpl" 
                            					 keyValues="roleId=${role.roleId}" render="true">
                            			<c:out value="${role.namespaceCode} ${role.roleName}" />
                            		</kul:inquiry>
                            		<br />
                            	</c:forEach>
                               	<c:if test="${empty KualiForm.permissionRoles[perm.permissionId]}">
			                		&nbsp;
			                	</c:if>
                            </td>
                        	<c:if test="${KualiForm.canInitiatePermissionDocument}">
	                            <td>
                                    <a href="<c:url value="${ConfigProperties.kr.url}/${Constants.MAINTENANCE_ACTION}">
		                                <c:param name="methodToCall" value="edit" />
		                                <c:param name="businessObjectClassName" value="org.kuali.rice.kim.bo.role.impl.KimPermissionImpl"/>
		                                <c:param name="permissionId" value="${perm.permissionId}"/>
	                                </c:url>" target="_blank">Edit Permission</a>
		                        </td>
                            </c:if>
                          </tr>
        				</c:forEach>
        	            </tbody>
        	          </table>
