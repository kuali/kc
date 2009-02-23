<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="protocolDocumentAttributes" value="${DataDictionary.ProtocolDocument.attributes}" />
<c:set var="protocolAttributes" value="${DataDictionary.Protocol.attributes}" />
<c:set var="action" value="protocolActions" />

<kul:tabTop tabTitle="Copy to New Document" defaultOpen="false" tabErrorKey="">
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">Copy to New Document</span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.irb.bo.ProtocolType" altText="help"/></span>
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
</kul:tabTop>
