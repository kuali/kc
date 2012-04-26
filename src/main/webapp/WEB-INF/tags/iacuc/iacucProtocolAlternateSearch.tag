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

<%@ attribute name="protocolAlternateSearch" required="true" type="java.util.Map" %>

<kul:tab tabTitle="Alternate Search" defaultOpen="false" tabErrorKey="">
    <div class="tab-container" align="center">
        <h3>
            <span class="subhead-left">Alternate Search Required?</span>
            <span class="subhead-right">
                <kul:help parameterNamespace="KC-IACUC" parameterDetailType="Document" parameterName="iacucAlternateSearchRequiredHelp" altText="help"/>                
            </span>
        </h3>
        <table cellpadding="4" cellspacing="0" summary="">
            <tr>
                <th width="50%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${protocolAlternateSearch.searchRequired}" /></div></th>
                <td align="left" valign="center">
                    <kul:htmlControlAttribute property="iacucAlternateSearchHelper.newAlternateSearch.searchRequired" 
                                              readOnly="${readOnly}" 
                                              attributeEntry="${protocolAlternateSearch.searchRequired}" 
                                              onchange="alternateSearchRequired(this);"/>
                </td>        
            </tr>        
        </table>
    </div> 
    <div class="tab-container" align="center" id="alternate-search-div">
        <h3>
            <span class="subhead-left">Alternate Searches</span>
            <span class="subhead-right">
                <kul:help parameterNamespace="KC-IACUC" parameterDetailType="Document" parameterName="iacucAlternateSearchesHelp" altText="help"/>                
            </span>
        </h3>
        <table cellpadding="4" cellspacing="0" summary="">
            <tr>
	            <th>&nbsp;</th>
	            <th><kul:htmlAttributeLabel attributeEntry="${protocolAlternateSearch.searchDate}" /></th>
	            <th><kul:htmlAttributeLabel attributeEntry="${protocolAlternateSearch.databases}" /></th>
	            <th><kul:htmlAttributeLabel attributeEntry="${protocolAlternateSearch.yearsSearched}" /></th>
	            <th><kul:htmlAttributeLabel attributeEntry="${protocolAlternateSearch.keywords}" /></th>
	            <th><kul:htmlAttributeLabel attributeEntry="${protocolAlternateSearch.comments}" /></th>
	            <th>Action</th>
            </tr>
            <!-- Add Alternate Search form -->
            <tr>
                <th><div align="right">Add:</div></th>
                <th>
                    <div>
                        &nbsp;                 
                    </div>
                </th>
                <th>
                    <table cellspacing="0" cellpadding="0">
                        <tr>
                            <td><span style="text-align: center;">Available</span></td>
                            <td>&nbsp;</td>
                            <td><span style="text-align: center;">Selected</span></td>
                        </tr>
                        <tr>
                            <td>
			                    <div>   
			                        <kul:htmlControlAttribute property="iacucAlternateSearchHelper.newAlternateSearch.databases" 
			                            readOnly="${readOnly}" attributeEntry="${protocolAlternateSearch.databases}"  />            
			                    </div>
		                    </td>
		                    <td>
		                        <div align="center">
		                          <html:button property="move_right" styleId="move_right" value="&gt;"/><br/><html:button property="move_left" styleId="move_left" value="&lt;"/>
		                        </div>
		                    </td>
		                    <td>
                                <html:select property="iacucAlternateSearchHelper.newDatabases" multiple="multiple" size="5" styleId="new-databases-select" style="font-family: Verdana, Arial, Helvetica, sans-serif; font-size:11;">
                                    <!--   html:optionsCollection property="iacucAlternateSearchHelper.newAlternateSearch.databases" value="alternateSearchDatabaseName" label="alternateSearchDatabaseName" style="font-family: Verdana, Arial, Helvetica, sans-serif; font-size:11;"/  -->
                                </html:select>		                    
		                    </td>
                        </tr>
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
	                                src='${ConfigProperties.kr.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton"/>
	                </div>
                </th>            
            </tr>            
        </table>
    </div>    
</kul:tab>         