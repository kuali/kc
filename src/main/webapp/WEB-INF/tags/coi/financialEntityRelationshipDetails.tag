<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<%@ attribute name="prop" required="true" %>
<%@ attribute name="detailList" required="true" type="java.util.List" %>
<%@ attribute name="methodtocall" required="true" %>

<%-- <c:set var="readOnly" value="${KualiForm.readOnly}"  scope="request"/> --%>
<c:set var="FinIntRelTypesAttribute" value="${DataDictionary.FinIntEntityRelType.attributes}" />
<c:set var="FinEntitiesDataMatrixAttribute" value="${DataDictionary.FinEntitiesDataMatrix.attributes}" />
<c:set var="PersonFinIntDisclDetAttribute" value="${DataDictionary.PersonFinIntDisclDet.attributes}" />
<c:set var="kraAttributeReferenceDummyAttributes" value="${DataDictionary.KraAttributeReferenceDummy.attributes}" />
    <div class="tab-container" align="center">
        <h3>
            <span class="subhead-left"> Relationship Details </span>
            <span class="subhead-right"> <kul:help businessObjectClassName="org.kuali.kra.coi.personfinancialentity.FinIntEntityStatus" altText="help"/> </span>
        </h3>
                    
        <table id="response-table" width="100%" cellpadding="0" cellspacing="0" class="datatable">
            <tr>
                <th align="middle" valign="middle" >
                    &nbsp;
                </th>
            <c:forEach var="relationshipType" items="${KualiForm.financialEntityHelper.finEntityRelationshipTypes}" varStatus="status">
                <th align="middle" valign="middle">


                    <kul:htmlControlAttribute property="financialEntityHelper.finEntityRelationshipTypes[${status.index}].description" 
                                              attributeEntry="${FinIntRelTypesAttribute.description}" readOnly="true"/> 
                </th>
             </c:forEach> 
                <th align="middle" valign="middle" >
                    Comment
                </th>
            </tr>
            <c:set var="groupId" value=""/>
            <c:forEach var="dataRow" items="${detailList}" varStatus="groupstatus">
                 <c:if test="${dataRow.dataGroupId != groupId}">
                  <tr>
                   <th align="middle" valign="middle" >
                        <kul:htmlControlAttribute property="${prop}[${groupstatus.index}].dataGroupName" 
                                              attributeEntry="${FinEntitiesDataMatrixAttribute.columnLabel}" readOnly="true"/> 
                    </th>
                    <td colspan="${fn:length(KualiForm.financialEntityHelper.finEntityRelationshipTypes) + 1}"> 
                        &nbsp;
                    </td>
                    <c:set var="groupId" value="${dataRow.dataGroupId}"/>
                   <tr>
                 </c:if>
                 <tr>
                    <th align="middle" valign="middle">
                        <kul:htmlControlAttribute property="${prop}[${groupstatus.index}].columnLabel" 
                                              attributeEntry="${FinEntitiesDataMatrixAttribute.columnLabel}" readOnly="true"/> 
                    </th>
                <c:forEach var="relationship" items="${dataRow.relationshipTypeBeans}" varStatus="status">
                     <td align="middle" valign="middle">
        <c:choose>
            <%-- c:when test = "${dataRow.guiType == 'DROPDOWN' and not empty dataRow.lookupArgument}" --%>
            <c:when test = "${dataRow.argValueLookup}">
                <kra-coi:argValueLookupData  valueIndex="${status.index}" bean="${dataRow}" 
                    property="${prop}[${groupstatus.index}].relationshipTypeBeans[${status.index}].stringValue" />
            </c:when>
            <c:otherwise>
                                                 <kul:htmlControlAttribute property="${prop}[${groupstatus.index}].relationshipTypeBeans[${status.index}].booleanValue" 
                                                      attributeEntry="${kraAttributeReferenceDummyAttributes.checkBox}" />
                        <%--                              
                        <kul:htmlControlAttribute property="${prop}[${groupstatus.index}].relationshipTypeBeans[${status.index}].booleanValue" 
                                              attributeEntry="${PersonFinIntDisclDetAttribute.columnValue}"/>
                         --%>                      
            </c:otherwise>
        </c:choose>
                    </td>
                 </c:forEach> 
                      <td align="middle" valign="middle">
                        <kul:htmlControlAttribute property="${prop}[${groupstatus.index}].comments" 
                                              attributeEntry="${PersonFinIntDisclDetAttribute.comments}"/> 
                    </td>
                 </tr>
             </c:forEach> 
            </tr>            
                <tr>
                    <td align="center" colspan="${fn:length(KualiForm.financialEntityHelper.finEntityRelationshipTypes) + 2}">
                        <div align="center">
                            <html:image property="${methodtocall}.anchor${tabKey}"
                                        src='${ConfigProperties.kra.externalizable.images.url}tinybutton-submit.gif' styleClass="tinybutton"/>
                        </div>
                    </td>
                </tr>
            
        </table>
    </div>
