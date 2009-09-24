<%--
 Copyright 2006-2009 The Kuali Foundation
 
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

<c:set var="awardHierarchyAttributes" value="${DataDictionary.AwardHierarchy.attributes}" />
<c:set var="action" value="awardHierarchy" />

<kul:tab tabTitle="Hierarchy" defaultOpen="false" tabErrorKey="document.awardList[0].awardReportTermItems" auditCluster="reportsAuditErrors" tabAuditKey="document.reportTermsAuditRules*" useRiceAuditMode="true">
	<div class="tab-container" align="right">
    	<h3>
    		<span class="subhead-left">Hierarchy</span>
    		<span class="subhead-right">
    			<kul:help businessObjectClassName="org.kuali.kra.award.commitments.AwardFandaRate" altText="help"/>
			</span>
        </h3>        
    
	<table style="border: medium none ; width: 100%; border-collapse: collapse;">
	<tbody><tr>
		<td style="border: medium none ; border-collapse: collapse; background-color: rgb(234, 233, 234);">
		<span style="display: inline;" id="treecontrol">
		${fn:length(KualiForm.order)} Nodes: 
		<a href="#" title="Collapse the entire tree below"><img src="static/images/jquery/minus.gif" /> Collapse All</a>
		<a href="#" title="Expand the entire tree below"><img src="static/images/jquery/plus.gif" /> Expand All</a>
		</span>
		</td>
	</tr>
	</tbody></table>	     
     <div style = "background:#e4e4e4; margin: 10px 0pt 0pt; clear: left; height: 285px; overflow-y: scroll; overflow-x: hidden; position: relative;" >     
  <ul id="awardhierarchy" class="filetree stripeli treeview"  >
        <%-- <li><span class="folder">00000</span>
        </li> --%>
    </ul>
   </div>
       
	<table cellpadding="0" cellspacing="0" summary="">
    	<%-- Header --%>
    	<tr>
    	<th align="right" colspan="4" >Enter the Award Number That Should Be Used For Copy</th>
    	<td><input type="text" name="awardNumberInputTemp" value="${KualiForm.awardNumberInputTemp}"></td>
    	</tr>
    	</br>
    		<tr>
          		<kul:htmlAttributeHeaderCell literalLabel="&nbsp;" scope="col" /> 
          		<kul:htmlAttributeHeaderCell attributeEntry="${awardHierarchyAttributes.rootAwardNumber}" scope="col" /></div></th>
          		<kul:htmlAttributeHeaderCell attributeEntry="${awardHierarchyAttributes.awardNumber}" scope="col" /></div></th>
          		<kul:htmlAttributeHeaderCell attributeEntry="${awardHierarchyAttributes.parentAwardNumber}" scope="col" /></div></th>
          		<kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col" />
          	</tr>
        
		<c:forEach var="order" items="${KualiForm.order}" varStatus="status">
          <tr>
			<th class="infoline">
				<c:out value="${status.index+1}" />
			</th>
                <td align="left" valign="middle">
			<div align="center">
              		<c:out value ="${KualiForm.awardHierarchyNodes[order].rootAwardNumber}" />
			</div>
  			</td>
                <td align="left" valign="middle">
			<div align="center">
              		<c:out value ="${KualiForm.awardHierarchyNodes[order].awardNumber}" />
			</div>
		  </td>
          <td align="left" valign="middle">
			<div align="center">
              		<c:out value ="${KualiForm.awardHierarchyNodes[order].parentAwardNumber}" />
			</div>
		  </td>
		  <td class="infoline">
			<div align="center">
				<html:image property="methodToCall.createANewChildAward.line${status.index}.awardNumber${KualiForm.awardHierarchyNodes[order].awardNumber}.anchor${currentTabIndex}"
					src='${ConfigProperties.kra.externalizable.images.url}tinybutton-maintain1.gif' styleClass="tinybutton" alt="Create New Child Award" />
			</div>
			<div align="center">					
				<html:image property="methodToCall.createANewChildAwardBasedOnParent.line${status.index}.awardNumber${KualiForm.awardHierarchyNodes[order].awardNumber}.anchor${currentTabIndex}"
					src='${ConfigProperties.kra.externalizable.images.url}tinybutton-maintain1.gif' styleClass="tinybutton" alt="Create New Child Award Based on Parent Award" />					
			</div>
			<div align="center">
				<html:image property="methodToCall.createANewChildAwardBasedOnAnotherAwardInHierarchy.line${status.index}.awardNumber${KualiForm.awardHierarchyNodes[order].awardNumber}.anchor${currentTabIndex}"
					src='${ConfigProperties.kra.externalizable.images.url}tinybutton-maintain1.gif' styleClass="tinybutton" alt="Create New Child Award Based on Another Award in Hierarchy" />
			</div>
			<div align="center">	
				<html:image property="methodToCall.copyAwardAsANewHierarchy.line${status.index}.awardNumber${KualiForm.awardHierarchyNodes[order].awardNumber}.anchor${currentTabIndex}"
					src='${ConfigProperties.kra.externalizable.images.url}tinybutton-maintain1.gif' styleClass="tinybutton" alt="Copy an Award as a New Hierarchy" />
			</div>
			<div align="center">
				<html:image property="methodToCall.copyAwardAsANewHierarchyWithDescendants.line${status.index}.awardNumber${KualiForm.awardHierarchyNodes[order].awardNumber}.anchor${currentTabIndex}"
					src='${ConfigProperties.kra.externalizable.images.url}tinybutton-maintain1.gif' styleClass="tinybutton" alt="Copy an Award as a New Hierarchy with all its descendants" />
			</div>
			<div align="center">
				<html:image property="methodToCall.copyAwardAsAChildInCurrentHierarchy.line${status.index}.awardNumber${KualiForm.awardHierarchyNodes[order].awardNumber}.anchor${currentTabIndex}"
					src='${ConfigProperties.kra.externalizable.images.url}tinybutton-maintain1.gif' styleClass="tinybutton" alt="Copy an Award as a child in the current Hierarchy" />
			</div>
			<div align="center">
				<html:image property="methodToCall.copyAwardAsAChildInCurrentHierarchyWithDescendants.line${status.index}.awardNumber${KualiForm.awardHierarchyNodes[order].awardNumber}.anchor${currentTabIndex}"
					src='${ConfigProperties.kra.externalizable.images.url}tinybutton-copy1.gif' styleClass="tinybutton" alt="Copy an Award as a child in the current Hierarchy with all its descendants" />
			</div>
			<div align="center">
				<html:image property="methodToCall.copyAwardAsAChildOfAwardInAnotherHierarchy.line${status.index}.awardNumber${KualiForm.awardHierarchyNodes[order].awardNumber}.anchor${currentTabIndex}"
					src='${ConfigProperties.kra.externalizable.images.url}tinybutton-copy1.gif' styleClass="tinybutton" alt="Copy Award as Child of an Award in another Hierarchy" />
			</div>
			<div align="center">
				<html:image property="methodToCall.copyAwardAsAChildOfAwardInAnotherHierarchyWithDescendants.line${status.index}.awardNumber${KualiForm.awardHierarchyNodes[order].awardNumber}.anchor${currentTabIndex}"
					src='${ConfigProperties.kra.externalizable.images.url}tinybutton-copy1.gif' styleClass="tinybutton" alt="Copy Award as Child of an Award in another Hierarchy with all its descendants" />	
			</div>
          </td>
           </tr>
      	</c:forEach>    
      </table>	
    
    </div>
    
    <input type="hidden" id = "rootAwardNumber" name="rootAwardNumber" value="${KualiForm.rootAwardNumber}">
    
    <input type="hidden" id = "awardHierarchyTempOjbect[1].awardNumber1" name="awardHierarchyTempOjbect[1].awardNumber1" value="${KualiForm.awardHierarchyTempOjbect[1].awardNumber1}">
    <input type="hidden" id = "awardHierarchyTempOjbect[2].awardNumber1" name="awardHierarchyTempOjbect[2].awardNumber1" value="${KualiForm.awardHierarchyTempOjbect[2].awardNumber1}">
    <input type="hidden" id = "awardHierarchyTempOjbect[3].awardNumber1" name="awardHierarchyTempOjbect[3].awardNumber1" value="${KualiForm.awardHierarchyTempOjbect[3].awardNumber1}">
    <input type="hidden" id = "awardHierarchyTempOjbect[4].awardNumber1" name="awardHierarchyTempOjbect[4].awardNumber1" value="${KualiForm.awardHierarchyTempOjbect[4].awardNumber1}">
    <input type="hidden" id = "awardHierarchyTempOjbect[5].awardNumber1" name="awardHierarchyTempOjbect[5].awardNumber1" value="${KualiForm.awardHierarchyTempOjbect[5].awardNumber1}">
    <input type="hidden" id = "awardHierarchyTempOjbect[6].awardNumber1" name="awardHierarchyTempOjbect[6].awardNumber1" value="${KualiForm.awardHierarchyTempOjbect[6].awardNumber1}">
    <input type="hidden" id = "awardHierarchyTempOjbect[7].awardNumber1" name="awardHierarchyTempOjbect[7].awardNumber1" value="${KualiForm.awardHierarchyTempOjbect[7].awardNumber1}">
    <input type="hidden" id = "awardHierarchyTempOjbect[8].awardNumber1" name="awardHierarchyTempOjbect[8].awardNumber1" value="${KualiForm.awardHierarchyTempOjbect[8].awardNumber1}">
    <input type="hidden" id = "awardHierarchyTempOjbect[9].awardNumber1" name="awardHierarchyTempOjbect[9].awardNumber1" value="${KualiForm.awardHierarchyTempOjbect[9].awardNumber1}">
    <input type="hidden" id = "awardHierarchyTempOjbect[10].awardNumber1" name="awardHierarchyTempOjbect[10].awardNumber1" value="${KualiForm.awardHierarchyTempOjbect[10].awardNumber1}">
    <input type="hidden" id = "awardHierarchyTempOjbect[11].awardNumber1" name="awardHierarchyTempOjbect[11].awardNumber1" value="${KualiForm.awardHierarchyTempOjbect[11].awardNumber1}">
    <input type="hidden" id = "awardHierarchyTempOjbect[12].awardNumber1" name="awardHierarchyTempOjbect[12].awardNumber1" value="${KualiForm.awardHierarchyTempOjbect[12].awardNumber1}">
    <input type="hidden" id = "awardHierarchyTempOjbect[13].awardNumber1" name="awardHierarchyTempOjbect[13].awardNumber1" value="${KualiForm.awardHierarchyTempOjbect[13].awardNumber1}">
    <input type="hidden" id = "awardHierarchyTempOjbect[14].awardNumber1" name="awardHierarchyTempOjbect[14].awardNumber1" value="${KualiForm.awardHierarchyTempOjbect[14].awardNumber1}">
    <input type="hidden" id = "awardHierarchyTempOjbect[15].awardNumber1" name="awardHierarchyTempOjbect[15].awardNumber1" value="${KualiForm.awardHierarchyTempOjbect[15].awardNumber1}">
    
    <input type="hidden" id = "awardHierarchyTempOjbect[1].selectBox1" name="awardHierarchyTempOjbect[1].selectBox1" value="${KualiForm.awardHierarchyTempOjbect[1].selectBox1}">
    <input type="hidden" id = "awardHierarchyTempOjbect[2].selectBox1" name="awardHierarchyTempOjbect[2].selectBox1" value="${KualiForm.awardHierarchyTempOjbect[2].selectBox1}">
    <input type="hidden" id = "awardHierarchyTempOjbect[3].selectBox1" name="awardHierarchyTempOjbect[3].selectBox1" value="${KualiForm.awardHierarchyTempOjbect[3].selectBox1}">
    <input type="hidden" id = "awardHierarchyTempOjbect[4].selectBox1" name="awardHierarchyTempOjbect[4].selectBox1" value="${KualiForm.awardHierarchyTempOjbect[4].selectBox1}">
    <input type="hidden" id = "awardHierarchyTempOjbect[5].selectBox1" name="awardHierarchyTempOjbect[5].selectBox1" value="${KualiForm.awardHierarchyTempOjbect[5].selectBox1}">
    <input type="hidden" id = "awardHierarchyTempOjbect[6].selectBox1" name="awardHierarchyTempOjbect[6].selectBox1" value="${KualiForm.awardHierarchyTempOjbect[6].selectBox1}">
    <input type="hidden" id = "awardHierarchyTempOjbect[7].selectBox1" name="awardHierarchyTempOjbect[7].selectBox1" value="${KualiForm.awardHierarchyTempOjbect[7].selectBox1}">
    <input type="hidden" id = "awardHierarchyTempOjbect[8].selectBox1" name="awardHierarchyTempOjbect[8].selectBox1" value="${KualiForm.awardHierarchyTempOjbect[8].selectBox1}">
    <input type="hidden" id = "awardHierarchyTempOjbect[9].selectBox1" name="awardHierarchyTempOjbect[9].selectBox1" value="${KualiForm.awardHierarchyTempOjbect[9].selectBox1}">
    <input type="hidden" id = "awardHierarchyTempOjbect[10].selectBox1" name="awardHierarchyTempOjbect[10].selectBox1" value="${KualiForm.awardHierarchyTempOjbect[10].selectBox1}">
    <input type="hidden" id = "awardHierarchyTempOjbect[11].selectBox1" name="awardHierarchyTempOjbect[11].selectBox1" value="${KualiForm.awardHierarchyTempOjbect[11].selectBox1}">
    <input type="hidden" id = "awardHierarchyTempOjbect[12].selectBox1" name="awardHierarchyTempOjbect[12].selectBox1" value="${KualiForm.awardHierarchyTempOjbect[12].selectBox1}">
    <input type="hidden" id = "awardHierarchyTempOjbect[13].selectBox1" name="awardHierarchyTempOjbect[13].selectBox1" value="${KualiForm.awardHierarchyTempOjbect[13].selectBox1}">
    <input type="hidden" id = "awardHierarchyTempOjbect[14].selectBox1" name="awardHierarchyTempOjbect[14].selectBox1" value="${KualiForm.awardHierarchyTempOjbect[14].selectBox1}">
    <input type="hidden" id = "awardHierarchyTempOjbect[15].selectBox1" name="awardHierarchyTempOjbect[15].selectBox1" value="${KualiForm.awardHierarchyTempOjbect[15].selectBox1}">
    
    <input type="hidden" id = "awardHierarchyTempOjbect[1].awardNumber2" name="awardHierarchyTempOjbect[1].awardNumber2" value="${KualiForm.awardHierarchyTempOjbect[1].awardNumber2}">
    <input type="hidden" id = "awardHierarchyTempOjbect[2].awardNumber2" name="awardHierarchyTempOjbect[2].awardNumber2" value="${KualiForm.awardHierarchyTempOjbect[2].awardNumber2}">
    <input type="hidden" id = "awardHierarchyTempOjbect[3].awardNumber2" name="awardHierarchyTempOjbect[3].awardNumber2" value="${KualiForm.awardHierarchyTempOjbect[3].awardNumber2}">
    <input type="hidden" id = "awardHierarchyTempOjbect[4].awardNumber2" name="awardHierarchyTempOjbect[4].awardNumber2" value="${KualiForm.awardHierarchyTempOjbect[4].awardNumber2}">
    <input type="hidden" id = "awardHierarchyTempOjbect[5].awardNumber2" name="awardHierarchyTempOjbect[5].awardNumber2" value="${KualiForm.awardHierarchyTempOjbect[5].awardNumber2}">
    <input type="hidden" id = "awardHierarchyTempOjbect[6].awardNumber2" name="awardHierarchyTempOjbect[6].awardNumber2" value="${KualiForm.awardHierarchyTempOjbect[6].awardNumber2}">
    <input type="hidden" id = "awardHierarchyTempOjbect[7].awardNumber2" name="awardHierarchyTempOjbect[7].awardNumber2" value="${KualiForm.awardHierarchyTempOjbect[7].awardNumber2}">
    <input type="hidden" id = "awardHierarchyTempOjbect[8].awardNumber2" name="awardHierarchyTempOjbect[8].awardNumber2" value="${KualiForm.awardHierarchyTempOjbect[8].awardNumber2}">
    <input type="hidden" id = "awardHierarchyTempOjbect[9].awardNumber2" name="awardHierarchyTempOjbect[9].awardNumber2" value="${KualiForm.awardHierarchyTempOjbect[9].awardNumber2}">
    <input type="hidden" id = "awardHierarchyTempOjbect[10].awardNumber2" name="awardHierarchyTempOjbect[10].awardNumber2" value="${KualiForm.awardHierarchyTempOjbect[10].awardNumber2}">
    <input type="hidden" id = "awardHierarchyTempOjbect[11].awardNumber2" name="awardHierarchyTempOjbect[11].awardNumber2" value="${KualiForm.awardHierarchyTempOjbect[11].awardNumber2}">
    <input type="hidden" id = "awardHierarchyTempOjbect[12].awardNumber2" name="awardHierarchyTempOjbect[12].awardNumber2" value="${KualiForm.awardHierarchyTempOjbect[12].awardNumber2}">
    <input type="hidden" id = "awardHierarchyTempOjbect[13].awardNumber2" name="awardHierarchyTempOjbect[13].awardNumber2" value="${KualiForm.awardHierarchyTempOjbect[13].awardNumber2}">
    <input type="hidden" id = "awardHierarchyTempOjbect[14].awardNumber2" name="awardHierarchyTempOjbect[14].awardNumber2" value="${KualiForm.awardHierarchyTempOjbect[14].awardNumber2}">
    <input type="hidden" id = "awardHierarchyTempOjbect[15].awardNumber2" name="awardHierarchyTempOjbect[15].awardNumber2" value="${KualiForm.awardHierarchyTempOjbect[15].awardNumber2}">
    
    <input type="hidden" id = "awardHierarchyTempOjbect[1].selectBox2" name="awardHierarchyTempOjbect[1].selectBox2" value="${KualiForm.awardHierarchyTempOjbect[1].selectBox2}">
    <input type="hidden" id = "awardHierarchyTempOjbect[2].selectBox2" name="awardHierarchyTempOjbect[2].selectBox2" value="${KualiForm.awardHierarchyTempOjbect[2].selectBox2}">
    <input type="hidden" id = "awardHierarchyTempOjbect[3].selectBox2" name="awardHierarchyTempOjbect[3].selectBox2" value="${KualiForm.awardHierarchyTempOjbect[3].selectBox2}">
    <input type="hidden" id = "awardHierarchyTempOjbect[4].selectBox2" name="awardHierarchyTempOjbect[4].selectBox2" value="${KualiForm.awardHierarchyTempOjbect[4].selectBox2}">
    <input type="hidden" id = "awardHierarchyTempOjbect[5].selectBox2" name="awardHierarchyTempOjbect[5].selectBox2" value="${KualiForm.awardHierarchyTempOjbect[5].selectBox2}">
    <input type="hidden" id = "awardHierarchyTempOjbect[6].selectBox2" name="awardHierarchyTempOjbect[6].selectBox2" value="${KualiForm.awardHierarchyTempOjbect[6].selectBox2}">
    <input type="hidden" id = "awardHierarchyTempOjbect[7].selectBox2" name="awardHierarchyTempOjbect[7].selectBox2" value="${KualiForm.awardHierarchyTempOjbect[7].selectBox2}">
    <input type="hidden" id = "awardHierarchyTempOjbect[8].selectBox2" name="awardHierarchyTempOjbect[8].selectBox2" value="${KualiForm.awardHierarchyTempOjbect[8].selectBox2}">
    <input type="hidden" id = "awardHierarchyTempOjbect[9].selectBox2" name="awardHierarchyTempOjbect[9].selectBox2" value="${KualiForm.awardHierarchyTempOjbect[9].selectBox2}">
    <input type="hidden" id = "awardHierarchyTempOjbect[10].selectBox2" name="awardHierarchyTempOjbect[10].selectBox2" value="${KualiForm.awardHierarchyTempOjbect[10].selectBox2}">
    <input type="hidden" id = "awardHierarchyTempOjbect[11].selectBox2" name="awardHierarchyTempOjbect[11].selectBox2" value="${KualiForm.awardHierarchyTempOjbect[11].selectBox2}">
    <input type="hidden" id = "awardHierarchyTempOjbect[12].selectBox2" name="awardHierarchyTempOjbect[12].selectBox2" value="${KualiForm.awardHierarchyTempOjbect[12].selectBox2}">
    <input type="hidden" id = "awardHierarchyTempOjbect[13].selectBox2" name="awardHierarchyTempOjbect[13].selectBox2" value="${KualiForm.awardHierarchyTempOjbect[13].selectBox2}">
    <input type="hidden" id = "awardHierarchyTempOjbect[14].selectBox2" name="awardHierarchyTempOjbect[14].selectBox2" value="${KualiForm.awardHierarchyTempOjbect[14].selectBox2}">
    <input type="hidden" id = "awardHierarchyTempOjbect[15].selectBox2" name="awardHierarchyTempOjbect[15].selectBox2" value="${KualiForm.awardHierarchyTempOjbect[15].selectBox2}">
    
    <input type="hidden" id = "awardHierarchyTempOjbect[1].copyDescendants" name="awardHierarchyTempOjbect[1].copyDescendants" value="${KualiForm.awardHierarchyTempOjbect[1].copyDescendants}">
    <input type="hidden" id = "awardHierarchyTempOjbect[2].copyDescendants" name="awardHierarchyTempOjbect[2].copyDescendants" value="${KualiForm.awardHierarchyTempOjbect[2].copyDescendants}">
    <input type="hidden" id = "awardHierarchyTempOjbect[3].copyDescendants" name="awardHierarchyTempOjbect[3].copyDescendants" value="${KualiForm.awardHierarchyTempOjbect[3].copyDescendants}">
    <input type="hidden" id = "awardHierarchyTempOjbect[4].copyDescendants" name="awardHierarchyTempOjbect[4].copyDescendants" value="${KualiForm.awardHierarchyTempOjbect[4].copyDescendants}">
    <input type="hidden" id = "awardHierarchyTempOjbect[5].copyDescendants" name="awardHierarchyTempOjbect[5].copyDescendants" value="${KualiForm.awardHierarchyTempOjbect[5].copyDescendants}">
    <input type="hidden" id = "awardHierarchyTempOjbect[6].copyDescendants" name="awardHierarchyTempOjbect[6].copyDescendants" value="${KualiForm.awardHierarchyTempOjbect[6].copyDescendants}">
    <input type="hidden" id = "awardHierarchyTempOjbect[7].copyDescendants" name="awardHierarchyTempOjbect[7].copyDescendants" value="${KualiForm.awardHierarchyTempOjbect[7].copyDescendants}">
    <input type="hidden" id = "awardHierarchyTempOjbect[8].copyDescendants" name="awardHierarchyTempOjbect[8].copyDescendants" value="${KualiForm.awardHierarchyTempOjbect[8].copyDescendants}">
    <input type="hidden" id = "awardHierarchyTempOjbect[9].copyDescendants" name="awardHierarchyTempOjbect[9].copyDescendants" value="${KualiForm.awardHierarchyTempOjbect[9].copyDescendants}">
    <input type="hidden" id = "awardHierarchyTempOjbect[10].copyDescendants" name="awardHierarchyTempOjbect[10].copyDescendants" value="${KualiForm.awardHierarchyTempOjbect[10].copyDescendants}">
    <input type="hidden" id = "awardHierarchyTempOjbect[11].copyDescendants" name="awardHierarchyTempOjbect[11].copyDescendants" value="${KualiForm.awardHierarchyTempOjbect[11].copyDescendants}">
    <input type="hidden" id = "awardHierarchyTempOjbect[12].copyDescendants" name="awardHierarchyTempOjbect[12].copyDescendants" value="${KualiForm.awardHierarchyTempOjbect[12].copyDescendants}">
    <input type="hidden" id = "awardHierarchyTempOjbect[13].copyDescendants" name="awardHierarchyTempOjbect[13].copyDescendants" value="${KualiForm.awardHierarchyTempOjbect[13].copyDescendants}">
    <input type="hidden" id = "awardHierarchyTempOjbect[14].copyDescendants" name="awardHierarchyTempOjbect[14].copyDescendants" value="${KualiForm.awardHierarchyTempOjbect[14].copyDescendants}">
    <input type="hidden" id = "awardHierarchyTempOjbect[15].copyDescendants" name="awardHierarchyTempOjbect[15].copyDescendants" value="${KualiForm.awardHierarchyTempOjbect[15].copyDescendants}">
    
	<input type="hidden" id = "awardHierarchyTempOjbect[1].createNewChildRadio" name="awardHierarchyTempOjbect[1].createNewChildRadio" value="${KualiForm.awardHierarchyTempOjbect[1].createNewChildRadio}">
    <input type="hidden" id = "awardHierarchyTempOjbect[2].createNewChildRadio" name="awardHierarchyTempOjbect[2].createNewChildRadio" value="${KualiForm.awardHierarchyTempOjbect[2].createNewChildRadio}">
    <input type="hidden" id = "awardHierarchyTempOjbect[3].createNewChildRadio" name="awardHierarchyTempOjbect[3].createNewChildRadio" value="${KualiForm.awardHierarchyTempOjbect[3].createNewChildRadio}">
    <input type="hidden" id = "awardHierarchyTempOjbect[4].createNewChildRadio" name="awardHierarchyTempOjbect[4].createNewChildRadio" value="${KualiForm.awardHierarchyTempOjbect[4].createNewChildRadio}">
    <input type="hidden" id = "awardHierarchyTempOjbect[5].createNewChildRadio" name="awardHierarchyTempOjbect[5].createNewChildRadio" value="${KualiForm.awardHierarchyTempOjbect[5].createNewChildRadio}">
    <input type="hidden" id = "awardHierarchyTempOjbect[6].createNewChildRadio" name="awardHierarchyTempOjbect[6].createNewChildRadio" value="${KualiForm.awardHierarchyTempOjbect[6].createNewChildRadio}">
    <input type="hidden" id = "awardHierarchyTempOjbect[7].createNewChildRadio" name="awardHierarchyTempOjbect[7].createNewChildRadio" value="${KualiForm.awardHierarchyTempOjbect[7].createNewChildRadio}">
    <input type="hidden" id = "awardHierarchyTempOjbect[8].createNewChildRadio" name="awardHierarchyTempOjbect[8].createNewChildRadio" value="${KualiForm.awardHierarchyTempOjbect[8].createNewChildRadio}">
    <input type="hidden" id = "awardHierarchyTempOjbect[9].createNewChildRadio" name="awardHierarchyTempOjbect[9].createNewChildRadio" value="${KualiForm.awardHierarchyTempOjbect[9].createNewChildRadio}">
    <input type="hidden" id = "awardHierarchyTempOjbect[10].createNewChildRadio" name="awardHierarchyTempOjbect[10].createNewChildRadio" value="${KualiForm.awardHierarchyTempOjbect[10].createNewChildRadio}">
    <input type="hidden" id = "awardHierarchyTempOjbect[11].createNewChildRadio" name="awardHierarchyTempOjbect[11].createNewChildRadio" value="${KualiForm.awardHierarchyTempOjbect[11].createNewChildRadio}">
    <input type="hidden" id = "awardHierarchyTempOjbect[12].createNewChildRadio" name="awardHierarchyTempOjbect[12].createNewChildRadio" value="${KualiForm.awardHierarchyTempOjbect[12].createNewChildRadio}">
    <input type="hidden" id = "awardHierarchyTempOjbect[13].createNewChildRadio" name="awardHierarchyTempOjbect[13].createNewChildRadio" value="${KualiForm.awardHierarchyTempOjbect[13].createNewChildRadio}">
    <input type="hidden" id = "awardHierarchyTempOjbect[14].createNewChildRadio" name="awardHierarchyTempOjbect[14].createNewChildRadio" value="${KualiForm.awardHierarchyTempOjbect[14].createNewChildRadio}">
    <input type="hidden" id = "awardHierarchyTempOjbect[15].createNewChildRadio" name="awardHierarchyTempOjbect[15].createNewChildRadio" value="${KualiForm.awardHierarchyTempOjbect[15].createNewChildRadio}">
    
	<input type="hidden" id = "awardHierarchyTempOjbect[1].copyAwardRadio" name="awardHierarchyTempOjbect[1].copyAwardRadio" value="${KualiForm.awardHierarchyTempOjbect[1].copyAwardRadio}">
    <input type="hidden" id = "awardHierarchyTempOjbect[2].copyAwardRadio" name="awardHierarchyTempOjbect[2].copyAwardRadio" value="${KualiForm.awardHierarchyTempOjbect[2].copyAwardRadio}">
    <input type="hidden" id = "awardHierarchyTempOjbect[3].copyAwardRadio" name="awardHierarchyTempOjbect[3].copyAwardRadio" value="${KualiForm.awardHierarchyTempOjbect[3].copyAwardRadio}">
    <input type="hidden" id = "awardHierarchyTempOjbect[4].copyAwardRadio" name="awardHierarchyTempOjbect[4].copyAwardRadio" value="${KualiForm.awardHierarchyTempOjbect[4].copyAwardRadio}">
    <input type="hidden" id = "awardHierarchyTempOjbect[5].copyAwardRadio" name="awardHierarchyTempOjbect[5].copyAwardRadio" value="${KualiForm.awardHierarchyTempOjbect[5].copyAwardRadio}">
    <input type="hidden" id = "awardHierarchyTempOjbect[6].copyAwardRadio" name="awardHierarchyTempOjbect[6].copyAwardRadio" value="${KualiForm.awardHierarchyTempOjbect[6].copyAwardRadio}">
    <input type="hidden" id = "awardHierarchyTempOjbect[7].copyAwardRadio" name="awardHierarchyTempOjbect[7].copyAwardRadio" value="${KualiForm.awardHierarchyTempOjbect[7].copyAwardRadio}">
    <input type="hidden" id = "awardHierarchyTempOjbect[8].copyAwardRadio" name="awardHierarchyTempOjbect[8].copyAwardRadio" value="${KualiForm.awardHierarchyTempOjbect[8].copyAwardRadio}">
    <input type="hidden" id = "awardHierarchyTempOjbect[9].copyAwardRadio" name="awardHierarchyTempOjbect[9].copyAwardRadio" value="${KualiForm.awardHierarchyTempOjbect[9].copyAwardRadio}">
    <input type="hidden" id = "awardHierarchyTempOjbect[10].copyAwardRadio" name="awardHierarchyTempOjbect[10].copyAwardRadio" value="${KualiForm.awardHierarchyTempOjbect[10].copyAwardRadio}">
    <input type="hidden" id = "awardHierarchyTempOjbect[11].copyAwardRadio" name="awardHierarchyTempOjbect[11].copyAwardRadio" value="${KualiForm.awardHierarchyTempOjbect[11].copyAwardRadio}">
    <input type="hidden" id = "awardHierarchyTempOjbect[12].copyAwardRadio" name="awardHierarchyTempOjbect[12].copyAwardRadio" value="${KualiForm.awardHierarchyTempOjbect[12].copyAwardRadio}">
    <input type="hidden" id = "awardHierarchyTempOjbect[13].copyAwardRadio" name="awardHierarchyTempOjbect[13].copyAwardRadio" value="${KualiForm.awardHierarchyTempOjbect[13].copyAwardRadio}">
    <input type="hidden" id = "awardHierarchyTempOjbect[14].copyAwardRadio" name="awardHierarchyTempOjbect[14].copyAwardRadio" value="${KualiForm.awardHierarchyTempOjbect[14].copyAwardRadio}">
    <input type="hidden" id = "awardHierarchyTempOjbect[15].copyAwardRadio" name="awardHierarchyTempOjbect[15].copyAwardRadio" value="${KualiForm.awardHierarchyTempOjbect[15].copyAwardRadio}">
            
</kul:tab>
