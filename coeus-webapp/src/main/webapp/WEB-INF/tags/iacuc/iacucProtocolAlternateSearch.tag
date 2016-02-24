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

<%@ attribute name="protocolAlternateSearch" required="true" type="java.util.Map" %>
<%@ attribute name="modifyPermissions" required="true" %>

<c:set var="protocol" value="${KualiForm.document.protocolList[0]}" />
<c:set var="principlesAttributes" value="${DataDictionary.IacucPrinciples.attributes}" />
<c:set var="readOnly" value="${!modifyPermissions}" />

<!-- needed to attach to a styleClass attribute for a specific select box UI element -->
<style type="text/css">
    <!--
    .iacuc-alt-search-multi-select {
        width: 175px;
        height: 75px;
    }
    -->
</style>

<kul:tab tabTitle="Alternate Search" defaultOpen="true" useRiceAuditMode="true"
			tabErrorKey="iacucAlternateSearchHelper*,document.protocolList*" 
			auditCluster="alternateSearchAuditErrors"
			tabAuditKey="iacucAlternateSearchHelper*,document.protocolList*" >
    <div class="tab-container" align="center">
        <h3>
            <span class="subhead-left">Alternate Search Required?</span>
            <span class="subhead-right">
                <kul:help parameterNamespace="KC-IACUC" parameterDetailType="Document" parameterName="iacucAlternateSearchRequiredHelp" altText="help"/>                
            </span>
        </h3>
        <table cellpadding="4" cellspacing="0" summary="">
            <tr>
                <th width="50%">
                	<div align="right">
                		<kul:htmlAttributeLabel attributeEntry="${principlesAttributes.searchRequired}" forceRequired="true" />
                	</div>
                </th>
               	<c:set var="searchRequired" value="No" />
			    <c:if test="${'Y' eq KualiForm.document.protocolList[0].iacucPrinciples[0].searchRequired}">
	               	<c:set var="searchRequired" value="Yes" />
			    </c:if>
                <td align="left" valign="center">
					<c:choose>
						<c:when test="${readOnly}" >
					   		<c:out value="${searchRequired}" />
						</c:when>
						<c:otherwise>
					    	<html:select property="document.protocolList[0].iacucPrinciples[0].searchRequired"
		                                 onchange="alternateSearchRequired(this);">
					        	<html:option value="">Select</html:option>
					        	<html:option value="Y">Yes</html:option>
					        	<html:option value="N">No</html:option>
					        </html:select>
					    </c:otherwise>
					 </c:choose>       
                </td>        
            </tr>        
        </table>
    </div> 
    <div class="tab-container" align="center" id="alternate-search-div">
        <h3>
            <span class="subhead-left">Alternate Searches</span>
            <span class="subhead-right">
                <kul:help parameterNamespace="KC-IACUC" parameterDetailType="Document" parameterName="iacucProtocolAlternateSearchHelp" altText="help"/>                
            </span>
        </h3>
        <table cellpadding="4" cellspacing="0" summary="">
            <tr>
	            <th>&nbsp;</th>
	            <th><kul:htmlAttributeLabel attributeEntry="${protocolAlternateSearch.searchDate}" forceRequired="true"/></th>
	            <th><kul:htmlAttributeLabel attributeEntry="${protocolAlternateSearch.databases}" forceRequired="true"/></th>
	            <th><kul:htmlAttributeLabel attributeEntry="${protocolAlternateSearch.yearsSearched}" forceRequired="true"/></th>
	            <th><kul:htmlAttributeLabel attributeEntry="${protocolAlternateSearch.keywords}" forceRequired="true"/></th>
	            <th><kul:htmlAttributeLabel attributeEntry="${protocolAlternateSearch.comments}" /></th>
	            <th>Action</th>
            </tr>
            <!-- Add Alternate Search form -->
            
        	<kra:permission value="${modifyPermissions}">  
        	<tbody class="addline"> 
            <tr>
                <th><div align="right">Add:</div></th>
                <th>
                    <div>
                        <kul:htmlControlAttribute property="iacucAlternateSearchHelper.newAlternateSearch.searchDate" 
                            readOnly="${readOnly}" attributeEntry="${protocolAlternateSearch.searchDate}"  />           
                    </div>
                </th>
                <th>
                    <table cellspacing="0" cellpadding="0" border="0" style="border: none; background-image: inherit;">
                        <tbody style="background-image:inherit;">
                        <tr style="background-image:inherit; background-position: center top;">
                            <td style="background-image:inherit; background-position: center top; border: none; text-align: center;"><span style="text-align: center; color: #7E7E7E;">Available</span></td>
                            <td style="background-image:inherit; background-position: center top; border: none;">&nbsp;</td>
                            <td style="background-image:inherit; background-position: center top; border: none; text-align: center;"><span style="text-align: center; color: #7E7E7E;">Selected</span></td>
                        </tr>
                        <tr style="background-image:inherit; background-position: center middle;">
                            <td style="background-image:inherit; background-position: center middle; border: none;">
			                    <div>   
			                        <kul:htmlControlAttribute property="iacucAlternateSearchHelper.newAlternateSearch.databases" styleClass="iacuc-alt-search-multi-select"
			                            readOnly="${readOnly}" attributeEntry="${protocolAlternateSearch.databases}"  />            
			                    </div>
		                    </td>
		                    <td style="background-image:inherit; background-position: center middle; border: none;">
		                        <div align="center" class="ignoreMeFromWarningOnAddRow">
		                          <html:button property="move_right" styleId="move_right" value="&gt;"/><br/><html:button property="move_left" styleId="move_left" value="&lt;"/>
		                        </div>
		                    </td>
		                    <td style="background-image:inherit; background-position: center middle; border: none;">
		                        ${kfunc:registerEditableProperty(KualiForm, "iacucAlternateSearchHelper.newDatabases")}
                                <html:select property="iacucAlternateSearchHelper.newDatabases" multiple="multiple" size="5" styleId="new-databases-select" styleClass="iacuc-alt-search-multi-select" style="font-family: Verdana, Arial, Helvetica, sans-serif; font-size:11;">
                                </html:select>
		                    </td>
                        </tr>
                        <tr style="background-image: inherit; background-position: center bottom;">
                            <td style="background-image: inherit; background-position: center bottom; border: none;">&nbsp;</td>
                            <td style="background-image: inherit; background-position: center bottom; border: none; text-align: center;"><span style="text-align: center; color: #7E7E7E;">Other:</span></td>
                            <td style="background-image: inherit; background-position: center bottom; border: none;">
                            	<div class="ignoreMeFromWarningOnAddRow">
	                            	<input type="text" name="otherAltSearchDatabase" id="otherAltSearchDatabase" size="20"/><html:button property="add_other_db" styleId="add_other_db" value="add" onclick="addOtherDatabase(); return false;"/>
	                        	</div>    
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </th>
                <th>
                    <div>
                        <kul:htmlControlAttribute property="iacucAlternateSearchHelper.newAlternateSearch.yearsSearched" 
                            readOnly="${readOnly}" attributeEntry="${protocolAlternateSearch.yearsSearched}"  />            
                    </div>
                </th>
                <th>
                    <div>
                        <kul:htmlControlAttribute property="iacucAlternateSearchHelper.newAlternateSearch.keywords" 
                            readOnly="${readOnly}" attributeEntry="${protocolAlternateSearch.keywords}"  />
                    </div>
                </th>
                <th>
                    <div>
                        <kul:htmlControlAttribute property="iacucAlternateSearchHelper.newAlternateSearch.comments" 
                            readOnly="${readOnly}" attributeEntry="${protocolAlternateSearch.comments}"  />     
                    </div>
                </th>
                <th>
	                <div align="center">
	                    <html:image property="methodToCall.addAlternateSearchDatabase.anchor${tabKey}"
	                                src='${ConfigProperties.kr.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton addButton"/>
	                </div>
                </th>            
            </tr>   
			</tbody>

        	</kra:permission>
            
            
            
            <!--  existing alternate search database -->
            <c:forEach var="altSearches" items="${protocol.iacucAlternateSearches}" varStatus="status">
                <tr>
                    <th>${status.index + 1}</th>
                    <td align="left" valign="middle">${altSearches.searchDate}</td>
                    <td align="center" valign="middle">
                        <c:forEach var="altSearchDbs" items="${altSearches.databases}" varStatus="count">
                            <c:if test="${count.index != 0}">
                                <c:out value=", "/>
                            </c:if>
                            ${altSearchDbs.alternateSearchDatabaseName}    
                        </c:forEach>
                    </td>
                    <td align="center" valign="middle">${altSearches.yearsSearched}</td>
                    <td align="center" valign="middle">${altSearches.keywords}</td>
                    <td align="center" valign="middle">
                        <kra:truncateComment textAreaFieldName="protocol.iacucAlternateSearches[${status.index}].comments" action="iacucProtocolThreeRs" textAreaLabel="${protocolAlternateSearch.comments.label}" textValue="${KualiForm.document.protocolList[0].iacucAlternateSearches[status.index].comments}" displaySize="60"/>
                    </td>
                    
                    <c:choose>
                        <c:when test="${modifyPermissions}">
                        <td align="center" valign="middle">
                            <div align="center">
                            <nobr>
                                <html:image property="methodToCall.deleteAlternateSearch.line.${status.index}.anchor${tabKey}"
                                            src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
                            </nobr>
                            </div>
                        </td>
                        </c:when>
                        <c:otherwise>
                            <td>&nbsp;</td>
                        </c:otherwise>
                    </c:choose>
                </tr>         
            </c:forEach>         
        </table>
    </div>    
</kul:tab>         
