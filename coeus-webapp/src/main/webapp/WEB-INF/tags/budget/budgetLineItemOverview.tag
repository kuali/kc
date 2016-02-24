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
