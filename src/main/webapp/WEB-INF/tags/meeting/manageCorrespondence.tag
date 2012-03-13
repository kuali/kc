<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<%-- <c:set var="readOnly" value="${KualiForm.readOnly}"  scope="request"/> --%>
<c:set var="protocolCorrespondenceAttributes" value="${DataDictionary.ProtocolCorrespondence.attributes}" />

<kul:tabTop defaultOpen="false" tabTitle="Correspondence"
    tabErrorKey="document.committee*">

<div class="tab-container" align="center">
    <h3>
        <span class="subhead-left"> Correspondence </span>
        <span class="subhead-right"> <kul:help businessObjectClassName="org.kuali.kra.irb.correspondence.ProtocolCorrespondence" altText="help"/> </span>
    </h3>
         <table id="Correspondence-table" cellpadding=0 cellspacing=0 class="datatable" summary="Set correspondence">
        
            <tr>
                <kul:htmlAttributeHeaderCell literalLabel="Protocol Number" scope="col" />
                <kul:htmlAttributeHeaderCell literalLabel="Correspondence" scope="col" />
                <kul:htmlAttributeHeaderCell literalLabel="Final" scope="col" />
                <kul:htmlAttributeHeaderCell literalLabel="Action" scope="col" />
            </tr>
            <c:forEach var="correspondence" items="${KualiForm.meetingHelper.regeneratedCorrespondences}" varStatus="status">
                 <tr>
                    <th class="infoline" align="right">
                        <c:out value="${correspondence.protocolNumber}" />
                    </th>
                    <td align="left" valign="middle">
                        <div align="left"><c:out value="${correspondence.protocolCorrespondenceType.description}" /></div>
                    </td>
                    <td align="left" valign="middle" class="infoline">
                        <div align="center">
                        <kul:htmlControlAttribute property="meetingHelper.regeneratedCorrespondences[${status.index}].finalFlag" attributeEntry="${protocolCorrespondenceAttributes.finalFlag}" styleClass="finalclass"readOnly="false" />
                        </div>
                    </td>
                        <td>
                            <div align="center">&nbsp;                  
                                    <html:image property="methodToCall.viewGeneratedCorrespondence.line${status.index}.anchor${currentTabIndex}"
                                        src='${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif' styleClass="tinybutton"
                                        alt="View Generated Correspondence" title="View Generated Correspondence" onclick="excludeSubmitRestriction = true;"/>
                            </div>
                        </td>
                </tr>
          </c:forEach>      
                <tr>
                    <td colspan="4">
                            <div align="center">&nbsp;   
                                  <html:image property="methodToCall.selectAllProtocolPrint.anchor${tabKey}" src="${ConfigProperties.kra.externalizable.images.url}tinybutton-selectall.gif" title="Select All" alt="Select All" styleClass="tinybutton" onclick="$('.finalclass').attr('checked', true);return false;" />
                                  <html:image property="methodToCall.deselectAllProtocolPrint.anchor${tabKey}" src="${ConfigProperties.kra.externalizable.images.url}tinybutton-selectnone.gif" title="Select None" alt="Select None" styleClass="tinybutton" onclick="$('.finalclass').attr('checked', false);return false;" />
                            </div>
                    </td>
                </tr>    
       </table>     
</div>

</kul:tabTop>