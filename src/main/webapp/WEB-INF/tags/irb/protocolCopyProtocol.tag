<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="openFlag" value="false" />
<c:forEach items="${param}" var="par">
    <c:if test="${fn:startsWith(par.key, 'command')==true and fn:startsWith(par.value, 'displayDocSearchView')==true}">
        <c:set var="openFlag" value="true" />
    </c:if>
</c:forEach>


<c:set var="protocolDocumentAttributes" value="${DataDictionary.ProtocolDocument.attributes}" />
<c:set var="protocolAttributes" value="${DataDictionary.Protocol.attributes}" />
<c:set var="action" value="protocolActions" />

<kul:tab tabTitle="Copy to New Document" defaultOpen="${openFlag}" tabErrorKey="">
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">Copy to New Document</span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.irb.protocol.ProtocolType" altText="help"/></span>
        </h3>
        
         <table cellpadding="0" cellspacing="0" summary="">
        
        	<tr>
        		<th align="right" valign="middle" width="35%">Protocol:</th>
        		<td align="left" valign="middle">yes</td>
        	</tr>
        
            <tr>
				<td align="center" colspan="2">
					<div align="center">
						<html:image property="methodToCall.copyProtocol.anchor${tabKey}"
						            src='${ConfigProperties.kra.externalizable.images.url}tinybutton-copyprotocol.gif' styleClass="tinybutton"/>
					</div>
                </td>
			</tr>
        	
        </table>
    </div>
</kul:tab>
