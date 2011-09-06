<%--
 Copyright 2005-2010 The Kuali Foundation

 Licensed under the Educational Community License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.osedu.org/licenses/ECL-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="financialEntityUnitsAttributes" value="${DataDictionary.FinancialEntityUnit.attributes}" />
<c:set var="financialEntityReporter" value="financialEntityHelper.financialEntityReporter" />
<c:set var="unitAttributes" value="${DataDictionary.Unit.attributes}" />
<bean:define id="financialEntityUnits" name="KualiForm" property="financialEntityHelper.financialEntityReporter.financialEntityUnits" />
<%-- c:set var="readOnly" value="${!KualiForm.personnelHelper.modifyPersonnel}" / --%>
<table cellpadding=0 cellspacing=0 summary="">
    <tr>
        <td>
            <kul:innerTab tabTitle="Unit Details" parentTab="Reporter" defaultOpen="false" tabErrorKey="financialEntityHelper.newFinancialEntityUnit.*,financialEntityHelper.financialEntityReporter*" useCurrentTabIndexAsKey="false">
                <div class="innerTab-container" align="left">
                    <table class=tab cellpadding="0" cellspacing="0" summary="">
                        <tbody id="G3">
                        <%-- Header --%>
                        <tr>
                            <kul:htmlAttributeHeaderCell literalLabel="&nbsp;" scope="col" /> 
                            <kul:htmlAttributeHeaderCell attributeEntry="${unitAttributes.unitName}" scope="col" align="center"/>
                            <kul:htmlAttributeHeaderCell attributeEntry="${financialEntityUnitsAttributes.unitNumber}" scope="col" align="center"/>
                            <kul:htmlAttributeHeaderCell attributeEntry="${financialEntityUnitsAttributes.leadUnitFlag}" scope="col" align="center"/>
                            <c:if test="${!readOnly}">
                                <kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col" align="center"/>
                            </c:if>
                        </tr> 
                        <%-- Header --%>
                        
                         <%-- New data --%>
                        <%-- kra:permission value="${KualiForm.financialEntityHelper.modifyPersonnel}" --%>
                             <tr>
                                <th class="infoline">
                                    <c:out value="Add:" />
                                </th>
                                <td align="left" valign="middle" class="infoline">
                                    <div id="financialEntityHelper.newFinancialEntityUnit.unitName.div" class="same-line">
                                        <kul:htmlControlAttribute property="financialEntityHelper.newFinancialEntityUnit.unitName"
                                                                  attributeEntry="${unitAttributes.unitName}" readOnly="true"
                                                                  readOnlyBody="${empty KualiForm.financialEntityHelper.newFinancialEntityUnit.unitName}">
                                            (select)
                                        </kul:htmlControlAttribute>
                                    </div>
                                    &nbsp;
                                    <kul:lookup boClassName="org.kuali.kra.bo.Unit" fieldConversions="unitNumber:financialEntityHelper.newFinancialEntityUnit.unitNumber,unitName:financialEntityHelper.newFinancialEntityUnit.unitName" />
                                    <span class="fineprint"></span> 
                                </td>
                                <td align="left" valign="middle" class="infoline">
                                    <div align=left>
                                        <kul:htmlControlAttribute property="financialEntityHelper.newFinancialEntityUnit.unitNumber"
                                            attributeEntry="${unitAttributes.unitNumber}" 
                                            onblur="ajaxLoad('getUnitName','financialEntityHelper.newFinancialEntityUnit.unitNumber', 'financialEntityHelper.newFinancialEntityUnit.unitName');" 
                                        />
                                        <%--   onblur="loadUnitNameTo('financialEntityHelper.newFinancialEntityUnit.unitNumber','financialEntityHelper.newFinancialEntityUnit.unitName');" />  --%>
                                    </div>
                                    <span class="fineprint"></span> 
                                </td>
                                    <td align="left" valign="middle" class="infoline">
                                        <div align=center>
                                            <bean:define id="leadFlag" name="KualiForm" property="financialEntityHelper.newFinancialEntityUnit.leadUnitFlag" />
                                            <html:radio property="financialEntityHelper.newFinancialEntityUnit.leadUnitFlag" value="true"/>
                                        </div>
                                    </td>
                                <td class="infoline">
                                    <div align=center>
                                        <html:image property="methodToCall.addFinancialEntityUnit.line${status.index}" 
                                        src="${ConfigProperties.kr.externalizable.images.url}tinybutton-add1.gif" title="Add Unit" alt="Add Unit" styleClass="tinybutton"/></div>
                                </td>
                            </tr>
                        <%-- /kra:permission --%>
                        <%-- New data --%>
                        
                        <%-- Existing data --%>
                        <c:forEach var="financialEntityUnit" items="${financialEntityUnits}" varStatus="status">
                             <tr>
                                <th class="infoline">
                                    <c:out value="${status.index+1}" />
                                </th>
                              <td align="left" valign="middle">
                                <div align="left">
                                    <kul:htmlControlAttribute property="${financialEntityReporter}.financialEntityUnits[${status.index}].unit.unitName" attributeEntry="${unitAttributes.unitName}"  readOnly="true" />
                                </div>
                              </td>
                              <td align="left" valign="middle">
                                <div align="left">
                                    <kul:htmlControlAttribute property="${financialEntityReporter}.financialEntityUnits[${status.index}].unitNumber" attributeEntry="${unitAttributes.unitNumber}"  readOnly="true" />
                                </div>
                              </td>
                                  <td align="left" valign="middle">
                                    <div align="center">
                                        <html:radio property="${financialEntityReporter}.selectedUnit" value="${status.index}" disabled="${readOnly}"/>
                                    </div>
                                  </td>
                              <c:if test="${!readOnly}">
                                  <td class="infoline">
                                    <div align="center">
                                        <html:image property="methodToCall.deleteFinancialEntityUnit.${financialEntityReporter}.line${status.index}"
                                        src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
                                    </div>
                                  </td>
                            </c:if>
                            </tr>
                        </c:forEach>
                        <%-- Existing data --%>
            </tr>            
                <tr>
                    <td align="center" colspan="5">
                        <div align="center">
                            <html:image property="methodToCall.saveFinancialEntityUnits.anchor${tabKey}"
                                        src='${ConfigProperties.kra.externalizable.images.url}tinybutton-submit.gif' styleClass="tinybutton"/>
                        </div>
                    </td>
                </tr>
                        </tbody>
                    </table>
                </div>
            </kul:innerTab>
        </td>
    </tr>
</table>


