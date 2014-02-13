<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<%-- <c:set var="readOnly" value="${KualiForm.readOnly}"  scope="request"/> --%>
<c:set var="protocolCorrespondenceAttributes" value="${DataDictionary.IacucProtocolCorrespondence.attributes}" />

<kul:tabTop defaultOpen="false" tabTitle="Correspondence"
    tabErrorKey="document.committee*">

<div class="tab-container" align="center">
    <h3>
        <span class="subhead-left"> Correspondence </span>
        <span class="subhead-right"> <kul:help businessObjectClassName="org.kuali.kra.iacuc.correspondence.IacucProtocolCorrespondence" altText="help"/> </span>
    </h3>
         <table id="Correspondence-table" cellpadding=0 cellspacing=0 class="datatable" summary="Set correspondence">
        
            <tr>
                <kul:htmlAttributeHeaderCell literalLabel="Protocol Number" scope="col" />
                <kul:htmlAttributeHeaderCell literalLabel="Correspondence" scope="col" />
                <kul:htmlAttributeHeaderCell literalLabel="Final" scope="col" />
                <kul:htmlAttributeHeaderCell literalLabel="Action" scope="col" />
            </tr>
                 <tr>
                    <th class="infoline" align="right">
                        <c:out value="${KualiForm.actionHelper.protocolCorrespondence.protocolNumber}" />
                    </th>
                    <td align="left" valign="middle">
                        <div align="left"><c:out value="${KualiForm.actionHelper.protocolCorrespondence.protocolCorrespondenceType.description}" /></div>
                    </td>
                    <td align="left" valign="middle" class="infoline">
                        <div align="center">
                        <kul:htmlControlAttribute property="actionHelper.protocolCorrespondence.finalFlag" attributeEntry="${protocolCorrespondenceAttributes.finalFlag}" readOnly="false" />
                        </div>
                    </td>
                        <td>
                            <div align="center">&nbsp;                  
                                    <html:image property="methodToCall.viewCorrespondence.line${KualiForm.actionHelper.protocolCorrespondence.id}.anchor${currentTabIndex}"
                                        src='${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif' styleClass="tinybutton"
                                        alt="View Agenda" onclick="excludeSubmitRestriction = true;"/>
                            </div>
                        </td>
                </tr>
       </table>     
</div>

</kul:tabTop>