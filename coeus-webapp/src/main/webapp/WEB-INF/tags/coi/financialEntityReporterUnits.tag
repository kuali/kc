<%--
   - Kuali Coeus, a comprehensive research administration system for higher education.
   - 
   - Copyright 2005-2016 Kuali, Inc.
   - 
   - This program is free software: you can redistribute it and/or modify
   - it under the terms of the GNU Affero General Public License as
   - published by the Free Software Foundation, either version 3 of the
   - License, or (at your option) any later version.
   - 
   - This program is distributed in the hope that it will be useful,
   - but WITHOUT ANY WARRANTY; without even the implied warranty of
   - MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   - GNU Affero General Public License for more details.
   - 
   - You should have received a copy of the GNU Affero General Public License
   - along with this program.  If not, see <http://www.gnu.org/licenses/>.
--%>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="financialEntityReporterUnitsAttributes" value="${DataDictionary.FinancialEntityReporterUnit.attributes}" />
<c:set var="financialEntityReporter" value="financialEntityHelper.financialEntityReporter" />
<c:set var="unitAttributes" value="${DataDictionary.Unit.attributes}" />
<bean:define id="financialEntityReporterUnits" name="KualiForm" property="financialEntityHelper.financialEntityReporter.financialEntityReporterUnits" />
<%-- c:set var="readOnly" value="${!KualiForm.personnelHelper.modifyPersonnel}" / --%>
<div class="tab-container" align="center">
	<h3>
	    <span class="subhead-left"> 
	    	<a href="#" id ="financialEntityControl" class="financialEntitySubpanel"><img src='kr/images/tinybutton-hide.gif' alt='show/hide panel' width='45' height='15' border='0' align='absmiddle'></a> Unit Details 
	    </span>
		<span class="subhead-right"> 
			<kul:help parameterNamespace="KC-COIDISCLOSURE" parameterDetailType="Document" parameterName="financialEntityUnitHelp" altText="help"/>
	  	</span>
	</h3>
	
	<div id="financialEntityContent" class="financialEntitySubpanelContent">                    
    <table id="response-table" width="100%" cellpadding="0" cellspacing="0" class="datatable">
		<%-- Header --%>
    	<tr>
        	<kul:htmlAttributeHeaderCell literalLabel="&nbsp;" scope="col" /> 
            <kul:htmlAttributeHeaderCell attributeEntry="${unitAttributes.unitName}" scope="col" align="center"/>
            <kul:htmlAttributeHeaderCell attributeEntry="${financialEntityReporterUnitsAttributes.unitNumber}" scope="col" align="center"/>
            <kul:htmlAttributeHeaderCell attributeEntry="${financialEntityReporterUnitsAttributes.leadUnitFlag}" scope="col" align="center"/>
            <c:if test="${!readOnly}">
            	<kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col" align="center"/>
	        </c:if>
	    <tbody class="addline">    
    	</tr> 
        <%-- Header --%>
            	            
        <%-- New data --%>
        <%-- kra:permission value="${KualiForm.financialEntityHelper.modifyPersonnel}" --%>
        	<tr>
            	<th class="infoline">
                	<c:out value="Add:" />
                </th>
                <td align="left" valign="middle" class="infoline">
                	<div id="financialEntityHelper.newFinancialEntityReporterUnit.unitName.div" class="same-line">
                    	<kul:htmlControlAttribute property="financialEntityHelper.newFinancialEntityReporterUnit.unitName"
                                                  attributeEntry="${unitAttributes.unitName}" readOnly="true"
                                                  readOnlyBody="${empty KualiForm.financialEntityHelper.newFinancialEntityReporterUnit.unitName}">
                        	(select)
                        </kul:htmlControlAttribute>
                    </div>
                    &nbsp;
                    <kul:lookup boClassName="org.kuali.coeus.common.framework.unit.Unit" fieldConversions="unitNumber:financialEntityHelper.newFinancialEntityReporterUnit.unitNumber,unitName:financialEntityHelper.newFinancialEntityReporterUnit.unitName" />
                    <span class="fineprint"></span> 
                </td>
                <td align="left" valign="middle" class="infoline">
                	<div align=left>
                    	<kul:htmlControlAttribute property="financialEntityHelper.newFinancialEntityReporterUnit.unitNumber"
                                                  attributeEntry="${unitAttributes.unitNumber}" 
                                                  onblur="ajaxLoad('getUnitName','financialEntityHelper.newFinancialEntityReporterUnit.unitNumber', 'financialEntityHelper.newFinancialEntityReporterUnit.unitName');" />

                    </div>
                    <span class="fineprint"></span> 
                </td>
                <td align="left" valign="middle" class="infoline">
                	<div align=center>
                    	<bean:define id="leadFlag" name="KualiForm" property="financialEntityHelper.newFinancialEntityReporterUnit.leadUnitFlag" />
                        <html:checkbox property="financialEntityHelper.newFinancialEntityReporterUnit.leadUnitFlag" title="Add this unit as lead" />                                        
                    </div>
                </td>
                <td class="infoline">
                	<div align=center>
                    	<html:image property="methodToCall.addFinancialEntityReporterUnit.line${status.index}" 
                                    src="${ConfigProperties.kr.externalizable.images.url}tinybutton-add1.gif" title="Add Unit" alt="Add Unit" styleClass="tinybutton addButton"/>
                    </div>
                </td>
            </tr>
            </tbody>
        <%-- /kra:permission --%>
        <%-- New data --%>
                        
        <%-- Existing data --%>
        <c:forEach var="financialEntityReporterUnit" items="${financialEntityReporterUnits}" varStatus="status">
        	<tr>
            	<th class="infoline">
                	<c:out value="${status.index+1}" />
                </th>
            	<td align="left" valign="middle">
                	<div align="left">
                    	<kul:htmlControlAttribute property="${financialEntityReporter}.financialEntityReporterUnits[${status.index}].unit.unitName" attributeEntry="${unitAttributes.unitName}"  readOnly="true" />
                    </div>
                </td>
                <td align="left" valign="middle">
                	<div align="left">
                    	<kul:htmlControlAttribute property="${financialEntityReporter}.financialEntityReporterUnits[${status.index}].unitNumber" attributeEntry="${unitAttributes.unitNumber}"  readOnly="true" />
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
                        	<html:image property="methodToCall.deleteFinancialEntityReporterUnit.${financialEntityReporter}.line${status.index}"
                                        src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
                        </div>
                    </td>
                </c:if>
            </tr>
        </c:forEach>
        <%-- Existing data --%>
        	    <%--         
    	            <tr>
	                    <td align="center" colspan="5">
                        	<div align="center">
                    	        <html:image property="methodToCall.saveFinancialEntityReporterUnits.anchor${tabKey}"
                	                        src='${ConfigProperties.kra.externalizable.images.url}tinybutton-submit.gif' styleClass="tinybutton"/>
            	            </div>
        	            </td>
    	            </tr>
	               --%> 
	</table>
</div>
</div>


