<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<c:set var="protocolPersonAttributes" value="${DataDictionary.ProtocolPerson.attributes}" />

	<kra:section permission="modifyProtocol">
    	<kul:uncollapsable tabTitle="Add Personnel" tabErrorKey="newProtocolPerson.*" auditCluster="PersonnelAuditErrors" tabAuditKey="newProtocolPerson*">
          <div align="center">
            <table  cellpadding="0" cellspacing="0" class="grid" summary="">
              <tr>
                <th class="grid"><div align="right">*Person:</div></th>
                <td nowrap class="grid">
					<c:choose>                  
  						<c:when test="${empty KualiForm.newProtocolPerson.personId && empty KualiForm.newProtocolPerson.rolodexId}">
                			<label>Employee Search</label>
                  			<label>
                  				<kul:lookup boClassName="org.kuali.kra.bo.Person" fieldConversions="personId:newProtocolPerson.personId,fullName:newProtocolPerson.personName" />
							</label>
							<br>
                  			<label>Non-employee Search</label> 
                  			<label>
                  				<kul:lookup boClassName="org.kuali.kra.bo.NonOrganizationalRolodex" fieldConversions="rolodexId:newProtocolPerson.rolodexId,fullName:newProtocolPerson.personName" />
							</label>
  						</c:when>
  						<c:otherwise>
                  			<label>
                  				<kul:htmlControlAttribute property="newProtocolPerson.personName" attributeEntry="${protocolPersonAttributes.personName}" readOnly="true"/>
							</label>
							<br/>
  						</c:otherwise>
					</c:choose>
                </td>
                <th class="grid"><div align="right">*Protocol Role:</div></th>
                <td class="grid" >
				<c:set var="roleIdAttribute" value="${protocolPersonAttributes.protocolPersonRoleId}" />
                <kul:htmlControlAttribute property="newProtocolPerson.protocolPersonRoleId" attributeEntry="${protocolPersonAttributes.protocolPersonRoleId}" />
                </td>
              </tr>
            </table>
            <br>
            <html:image property="methodToCall.clearProtocolPerson" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-clear1.gif" title="Clear Fields" alt="Clear Fields" styleClass="tinybutton"/>
            <html:image property="methodToCall.addProtocolPerson" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-addpers.gif" title="Add Protocol Person" alt="Add Protocol Person" styleClass="tinybutton"/>
          </div>
    	</kul:uncollapsable>
	</kra:section>
    <br/>
