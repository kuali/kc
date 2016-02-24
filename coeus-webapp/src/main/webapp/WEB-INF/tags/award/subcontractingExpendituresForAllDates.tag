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

<div class="tab-container" align="center">
	         	<h3>
					<span class="subhead-left">
						All Dates
					</span> 
					<span class="subhead-right">
						<kul:help businessObjectClassName="org.kuali.kra.award.subcontracting.reporting.SubcontractingExpenditureCategoryAmounts" altText="help" />
					</span>
				</h3>
				<table cellpadding="0" cellspacing="0" border="0">
					<tr>
						<td class="infoline">
							<div align="left">
								From award expenditure details for all available dates:  
								<html:image property="methodToCall.regenerateExpenditureDataForAllDates" src='${ConfigProperties.kra.externalizable.images.url}tinybutton-regenerate.gif' styleClass="tinybutton" /> 
							</div>
						</td>
					</tr>
				</table>
</div>
			
