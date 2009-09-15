<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>

<c:set var="docEmailAttributes" value="${DataDictionary.PersonDocumentEmail.attributes}" />

<kul:subtab lookedUpCollectionName="email" width="${tableWidth}" subTabTitle="Email Addresses" noShowHideButton="true">      
  	<table cellpadding="0" cellspacing="0" summary="">
     	<tr>
       		<th>&nbsp;</th> 
       		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${docEmailAttributes.emailAddress}" noColon="true" /></div></th>
       		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${docEmailAttributes.emailTypeCode}" noColon="true" /></div></th>
       		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${docEmailAttributes.dflt}" noColon="true" /></div></th>
       		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${docEmailAttributes.active}" noColon="true" /></div></th>
           	<c:if test="${not inquiry}">	
              	<kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col"/>
          	</c:if>
      	</tr>     
        <c:if test="${not inquiry and not readOnly}">	          	
           	<tr>
				<th class="infoline">Add:</th>
                <td class="infoline">   
                <div align="center">             	
                  <kul:htmlControlAttribute property="newEmail.emailAddress" attributeEntry="${docEmailAttributes.emailAddress}" readOnly="${readOnly}" />
				</div>
				</td>
                <td align="left" valign="middle" class="infoline">
                <div align="center">
                	<kul:htmlControlAttribute property="newEmail.emailTypeCode" attributeEntry="${docEmailAttributes.emailTypeCode}" readOnly="${readOnly}" />
	            </div>
				</td>
                <td class="infoline">   
                <div align="center">             	
                  <kul:htmlControlAttribute property="newEmail.dflt" attributeEntry="${docEmailAttributes.dflt}" readOnly="${readOnly}" />
				</div>
				</td>
                <td class="infoline">   
                <div align="center">             	
                  <kul:htmlControlAttribute property="newEmail.active" attributeEntry="${docEmailAttributes.active}" readOnly="${readOnly}" />
				</div>
				</td>                                
                <td class="infoline">
					<div align=center>
						<html:image property="methodToCall.addEmail.anchor${tabKey}"
						src='${ConfigProperties.kr.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton"/>
					</div>
                </td>
       		</tr>         
    	</c:if>        
        <c:forEach var="email" items="${KualiForm.document.emails}" varStatus="status">
	       	<tr>
				<th class="infoline">
					<c:out value="${status.index+1}" />
				</th>
                <td>     
	                <div align="center">           	
	                  <kul:htmlControlAttribute property="document.emails[${status.index}].emailAddress" attributeEntry="${docEmailAttributes.emailAddress}" readOnly="${readOnly}" />
					</div>
				</td>
                <td align="left" valign="middle">
                	<div align="center"> <kul:htmlControlAttribute property="document.emails[${status.index}].emailTypeCode"  attributeEntry="${docEmailAttributes.emailTypeCode}"  readOnlyAlternateDisplay="${email.emailType.name}" readOnly="${readOnly}" />
					</div>
				</td>
				<td>
	                <div align="center">           	
	                  <kul:htmlControlAttribute property="document.emails[${status.index}].dflt" attributeEntry="${docEmailAttributes.dflt}" readOnly="${readOnly}" />
					</div>
				</td>
				<td>
	                <div align="center">           	
	                  <kul:htmlControlAttribute property="document.emails[${status.index}].active" attributeEntry="${docEmailAttributes.active}" readOnly="${readOnly}" />
					</div>
				</td>
           		<c:if test="${not inquiry}">						
					<td>
						<div align=center>&nbsp;
			        	     <c:choose>
			        	       <c:when test="${email.edit  or readOnly}">
			        	          <img class='nobord' src='${ConfigProperties.kr.externalizable.images.url}tinybutton-delete2.gif' styleClass='tinybutton'/>
			        	       </c:when>
			        	       <c:otherwise>
			        	          <html:image property='methodToCall.deleteEmail.line${status.index}.anchor${currentTabIndex}'
										src='${ConfigProperties.kr.externalizable.images.url}tinybutton-delete1.gif' styleClass='tinybutton'/>
			        	       </c:otherwise>
			        	     </c:choose>  
						</div>
	                </td>
	            </c:if>    
	        </tr>
        </c:forEach>                    
   	</table>
</kul:subtab>
