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
<kra:uncollapsable tabTitle="Select View:" tabErrorKey="personnelBudget*">
       <div align="center">
           <table cellpadding="0" cellspacing="0" class="grid" summary="">
             <tr>
               <th class="grid"><div align="right">View:</div></th>
               <td class="grid" >
					<html:select property="personnelBudgetViewMode">
	                    	<html:option value="0">Full Detail</html:option>  		                    	
	                    	<html:option value="1">Simple Detail</html:option>
  			       	</html:select>
               </td>
             </tr>
           </table>
           <br>
           <html:image property="methodToCall.updatePersonnelBudgetView" src="${ConfigProperties.kra.externalizable.images.url}tinybutton-updateview.gif" title="Update View" alt="Update View" styleClass="tinybutton"/>
	</div>
</kra:uncollapsable>
