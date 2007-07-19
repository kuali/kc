<%--
 Copyright 2007 The Kuali Foundation.
 
 Licensed under the Educational Community License, Version 1.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 
 http://www.opensource.org/licenses/ecl1.php
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>

<%@ attribute name="rows" required="true" type="java.util.List"
              description="The rows of fields that we'll iterate to display." %>
<%@ attribute name="numberOfColumns" required="false" 
              description="The # of fields in this row." %>
<%@ attribute name="skipTheOldNewBar" required="false" 
              description="boolean that indicates whether the old and new bar should be skipped" %>
<%@ attribute name="depth" required="false" 
              description="the recursion depth number" %>
<%@ attribute name="rowsHidden" required="false"
              description="boolean that indicates whether the rows should be hidden or all fields are hidden" %>

<c:if test="${empty depth}">
	<c:set var="depth" value="0" />
</c:if>

<c:set var="maintenanceViewMode" value="${requestScope[Constants.PARAM_MAINTENANCE_VIEW_MODE]}" />
<!-- maintenanceViewMode = ${maintenanceViewMode}, KualiForm.methodToCall = ${KualiForm.methodToCall} -->

<%-- Is the screen an inquiry? --%>
<c:set var="isInquiry" value="${maintenanceViewMode eq Constants.PARAM_MAINTENANCE_VIEW_MODE_INQUIRY}" />

<%-- Is the screen a view of a mantenance document? --%>
<c:set var="isMaintenance" value="${KualiForm.class.name eq 'org.kuali.core.web.struts.form.KualiMaintenanceForm' || maintenanceViewMode eq Constants.PARAM_MAINTENANCE_VIEW_MODE_MAINTENANCE}" />

<%-- Is the screen a lookup? --%>
<c:set var="isLookup" value="${maintenanceViewMode eq Constants.PARAM_MAINTENANCE_VIEW_MODE_LOOKUP}" />

<%-- Is the form read-only? --%>
<c:set var="isFormReadOnly" value="${isInquiry || (isMaintenance && KualiForm.readOnly)}" />

<%-- What's the user trying to do? --%>
<c:set var="requestedAction" value="${isMaintenance ? KualiForm.maintenanceAction : KualiForm.methodToCall}" />

<c:set var="isActionEdit" value="${Constants.MAINTENANCE_EDIT_ACTION eq requestedAction}" />
<c:set var="isActionCopy" value="${Constants.MAINTENANCE_COPY_ACTION eq requestedAction}" />
<c:set var="isActionNew" value="${Constants.MAINTENANCE_NEW_ACTION eq requestedAction || Constants.MAINTENANCE_NEWWITHEXISTING_ACTION eq requestedAction}" />

<%-- Indiates whether hightlight should occur --%>
<c:set var="addHighlighting" value="${isMaintenance && isActionEdit}" />

<c:if test="${isMaintenance}">
	<c:set var="numberOfColumns" value="1" />
</c:if>

<c:set var="isHeaderDisplayed" value="false" />

<c:forEach items="${rows}" var="row" varStatus="rowVarStatus">

    <c:set var="rowHidden" value="${rowsHidden || row.hidden}" />
	
	<tr>

		<c:forEach items="${row.fields}" var="field" varStatus="fieldVarStatus">
			<c:set var="isFieldAContainer" value="${field.fieldType eq field.CONTAINER}" />
			<c:set var="isFieldAddingToACollection" value="${fn:contains(field.propertyName, 'add.')}" />

			<c:set var="headerColspan" value="${numberOfColumns * 2}" />
			<c:set var="dataCellWidth" value="${100 / (numberOfColumns * ((isMaintenance || requestedAction eq 'addLine') ? 4 : 2))}" />

			<%--
				######################
				# SHOW THE OLD/NEW BAR
				###################### --%>
			<c:if test="${isMaintenance && not skipTheOldNewBar && not rowHidden &&  rowVarStatus.count eq 1 && not isHeaderDisplayed && not isFieldAContainer && not isFieldAddingToACollection && field.fieldType ne field.IMAGE_SUBMIT}">
				<%-- For Copy and Edit the Old and New views are shown. 
				     For all other actions a New bar (row) is shown at the top level only (not in containers). --%>
				<kul:sectionOldNewBar action="${requestedAction}" colspan="${headerColspan}"  depth="${depth}"/>
				<c:set var="isHeaderDisplayed" value="true" />
			</c:if>

			<%--
				###################################################################
				# GATHER SOME INFORMATION ABOUT THE FIELD AND STORE IT IN VARIABLES
				################################################################### --%>
			<%-- isFieldReadOnly determines whether or not a field is readOnly --%>
			<%-- NOTE: The part about "fieldVarStatus.count mod 2" will work for any even number 
			of columns assuming that all columns alternate between read-only and not-read-only. --%>
			<c:set var="isFieldReadOnly" value="${field.readOnly || isFormReadOnly || (isMaintenance && not isActionNew && fieldVarStatus.count le numberOfColumns)}" />
                
			<%-- isFieldSecure determines whether or not the encrypted value should be shown for 
			non-collections and a similar function for collections --%>
			<c:set var="isFieldSecure" value="${field.secure && empty KualiForm.editingMode[field.displayEditMode]}" />
				
			<%-- textStyle is used to store the style of the field value. i.e. whether or not it 
			should display as red text. --%>
			<c:set var="textStyle" value="" />

			<%-- fieldValue should be used to store the appropriate value for a field, i.e. handle 
			showing the encrypted value if a field is secure, etc. --%>
			<c:set var="fieldValue" value="${field.propertyValue}" />
                
			<%--
				####################################################### 
				# PojoForm saves request input that the Formatter framework
				# could not convert to the type of a given field,
				# so that it can be redisplayed here for correction.
				#
				# (jdb) KualiRequestProcessor.processPopulate() puts this
				# in the request too (UnconvertedHash and UnconvertedValues),
				# but is that necessary?  Also, the non-maintenance
				# docs get this automatically by using the Struts tags
				# (e.g., html:text), because Struts is using PojoPropertyUtilsBean.
				# Should the maintenance framework use the Struts html tags too?
				####################################################### --%>
			<c:set var="unconvertedValue" value="${KualiForm.unconvertedValues[field.propertyName]}"/>
			<c:if test="${not empty unconvertedValue}">
				<c:set var="textStyle" value="border-color: red" />
				<c:set var="fieldValue" value="${unconvertedValue}" />
			</c:if>

			<%--
				####################################################### 
				# If the field has errors, highlight its display in red. 
				####################################################### --%>
			<kul:checkErrors keyMatch="${field.propertyName}" />
                
			<c:if test="${hasErrors eq true}">
				<c:set var="textStyle" value="border-color: red" />
				<c:if test="${field.fieldType eq field.DROPDOWN || field.fieldType eq field.DROPDOWN_APC || field.fieldType eq field.DROPDOWN_REFRESH || field.fieldType eq field.DROPDOWN_SCRIPT}">
					<c:set var="textStyle" value="background-color: red"/>
				</c:if>
			</c:if>
				
			<%--
				####################################################### 
				# Set the onBlur handlers for the field.
				####################################################### --%>
			<c:set var="onblur" value="" />
			<c:set var="onblurcall" value="" />
                
			<c:if test="${!(empty field.webOnBlurHandler)}">
	
				<c:choose>
	    
					<c:when test="${!(empty field.webOnBlurHandlerCallback)}">
						<c:set var="onblur" value="${field.webOnBlurHandler}( this, ${field.webOnBlurHandlerCallback} );" />									
					</c:when>
		
					<c:otherwise>
						<c:set var="onblur" value="${field.webOnBlurHandler}( this );" />									
					</c:otherwise>
		
				</c:choose>
	    
				<c:set var="onblurcall" value="onblur='${onblur}'" />
								
			</c:if>						

			<c:choose>

				<c:when test="${isFieldSecure}">

					<input type="hidden" name="${field.propertyName}" 
						value='<c:out value="${field.encryptedValue}"/>' />

				</c:when>

				<c:when test="${isFieldReadOnly && not isFieldAContainer}">

					<input type="hidden" name="${field.propertyName}" 
						value='<c:out value="${fieldValue}"/>' />

				</c:when>

			</c:choose>

			<%--
				########################################################### 
				# SHOW FIELD
				########################################################### --%>
			<c:choose>
	            
				<c:when test="${not isActionNew && fieldVarStatus.count eq 1 && isFieldAddingToACollection && not isFieldAContainer}">
					<%-- Don't show anything --%>
				</c:when>

				<c:when test="${not isActionNew && not (requestedAction eq Constants.SAVE_METHOD and isFieldAddingToACollection) && (not isActionNew && requestedAction ne Constants.ADD_LINE_METHOD && fieldVarStatus.count eq 1 && isFieldAddingToACollection && not isFieldAContainer)}">
					<%-- Don't show anything --%>
				</c:when>

				<c:when test="${empty field.fieldType}">
					<%-- Don't show anything --%>
				</c:when>
					
				<c:when test="${isFieldSecure}">
	    
					<kul:fieldDefaultLabel isLookup="${isLookup}" isRequired="${field.fieldRequired}" 
						isReadOnly="${isFieldReadOnly}" cellWidth="${dataCellWidth}%" fieldType="${field.fieldType}" 
						fieldLabel="${field.fieldLabel}" />
						
					<td class="grid" width="${dataCellWidth}%">
						
						<c:out value="${field.displayMaskValue}"/>
			
						<kul:fieldShowIcons isReadOnly="${isFieldReadOnly}" field="${field}" addHighlighting="${addHighlighting}" />
			
					</td>
		
				</c:when>
				
				<c:when test="${isFormReadOnly && isFieldAddingToACollection}">
					<%-- Don't show anything. --%>
				</c:when>
				
				<c:when test="${isFieldAContainer}">
				  <c:if test="${rowHidden}">
					<kul:containerRowDisplay rows="${field.containerRows}" numberOfColumns="${isMaintenance ? numberOfColumns : field.numberOfColumnsForCollection}" depth="${depth + 1}" rowsHidden="true"/>
				  </c:if>
				  
				  <c:if test="${!rowHidden}">
					<td colspan="${headerColspan * 2}" class="tab-subhead" style="${depth eq 0 ? '' : 'padding-top: 20px; padding-bottom: 20px;'} background-color: #E6E6E6;">
						<%-- Set the width for the collection container. --%>
						<c:set var="width" value="${depth eq 0 ? '100%' : '85%'}" />
						<c:set var="subTabTitle">
							<kul:containerElementSubTabTitle containerField="${field}" isFieldAddingToACollection="${isFieldAddingToACollection}"/>
						</c:set>
						<c:set var="subTabButtonAlt">
							<kul:containerElementSubTabTitle containerField="${field}" isFieldAddingToACollection="${isFieldAddingToACollection}"/>
						</c:set>
                                             
                        <%-- determine whether there are highlighted fields in the container. If so highlight subtab --%>
                        <kul:checkTabHighlight rows="${field.containerRows}" addHighlighting="${addHighlighting}" />

						<%-- Only show the show/hide button on collection entries that
						contain data (i.e. those that aren't adding --%>
						<kul:subtab noShowHideButton="${isFieldAddingToACollection or empty field.containerRows}" subTabTitle="${kfunc:scrubWhitespace(subTabTitle)}" buttonAlt="${kfunc:scrubWhitespace(subTabButtonAlt)}" width="${width}" highlightTab="${tabHighlight}"
								boClassName="${field.multipleValueLookupClassName}" lookedUpBODisplayName="${field.multipleValueLookupClassLabel}" lookedUpCollectionName="${field.multipleValueLookedUpCollectionName}" >
							<table style="width: ${width}; text-align: left; margin-left: auto; margin-right: auto;" class="datatable" cellpadding="0" cellspacing="0" align="center">
								<%--<c:out value="numberOfColumns is ${numberOfColumns}, field.numberOfColumnsForCollection is ${field.numberOfColumnsForCollection}<br/>" escapeXml="false" />--%>
								<kul:containerRowDisplay rows="${field.containerRows}" numberOfColumns="${isMaintenance ? numberOfColumns : field.numberOfColumnsForCollection}" depth="${depth + 1}" />
							</table>
						</kul:subtab>
					</td>
				  </c:if>
				</c:when>
				
				<c:when test="${(field.fieldType eq field.HIDDEN) || rowHidden}">
					<c:if test="${isInquiry}">
						<kul:fieldDefaultLabel isLookup="${isLookup}" isRequired="${field.fieldRequired}" 
							isReadOnly="${isFieldReadOnly}" cellWidth="${cellWidth}" fieldType="${field.fieldType}" 
							fieldLabel="${field.fieldLabel}" />
									
						<td class="grid" width="${cellWidth}">
							<kul:fieldShowReadOnly field="${field}" addHighlighting="${addHighlighting}" />
						</td>
					</c:if>
			    	<c:if test="${!isFieldReadOnly}"><%-- prevent the field from being written a 2nd time --%>
						<input type="hidden" name='${field.propertyName}'
							value='<c:out value="${isFieldSecure ? field.encryptedValue : fieldValue}"/>' />
					</c:if>
				</c:when>
					
				<c:when test="${field.fieldType eq field.SUB_SECTION_SEPARATOR}">
					
					<td colspan="${headerColspan}" class="tab-subhead">
				
						<c:out value="${field.fieldLabel}" />&nbsp;
				    
					</td>
				
				</c:when>
					
				<c:when test="${(field.fieldType eq field.BLANK_SPACE)}">
					
					<c:if test="${isInquiry}">
				
						<th class="grid">&nbsp;</th>
						<td class="grid">
						
							<c:out value="${field.fieldLabel}" />&nbsp;
					
						</td>
					
					</c:if>
					
				</c:when>
				
				<c:when test="${field.fieldType eq field.CURRENCY}">
						
					<kul:fieldDefaultLabel isLookup="${isLookup}" isRequired="${field.fieldRequired}" 
						isReadOnly="${isFieldReadOnly}" cellWidth="${dataCellWidth}%" fieldType="${field.fieldType}" 
						fieldLabel="${field.fieldLabel}" />
								
					<td class="grid" width="${dataCellWidth}%">
							
						<c:choose>

							<c:when test="${isFieldReadOnly}">
								<kul:fieldShowReadOnly field="${field}" addHighlighting="${addHighlighting}" />
							</c:when>

							<c:otherwise>

								<input type="text" name='${field.propertyName}'
									value='<c:out value="${fieldValue}"/>'
									size='${field.size}'
									maxlength='${field.formattedMaxLength}'
									style="${textStyle}" ${onblurcall} 
									class="${field.styleClass }"/>
						    
								<kul:fieldShowIcons isReadOnly="${isFieldReadOnly}" field="${field}" addHighlighting="${addHighlighting}" />

							</c:otherwise>

						</c:choose>
					
					</td>
							
				</c:when>

				<c:when test="${field.fieldType eq field.TEXT}">
						
					<kul:fieldDefaultLabel isLookup="${isLookup}" isRequired="${field.fieldRequired}" 
						isReadOnly="${isFieldReadOnly}" cellWidth="${dataCellWidth}%" fieldType="${field.fieldType}" 
						fieldLabel="${field.fieldLabel}" />
								
					<td class="grid" width="${dataCellWidth}%">
							
						<c:choose>
				
							<c:when test="${isFieldReadOnly}">
								<kul:fieldShowReadOnly field="${field}" addHighlighting="${addHighlighting}" />
							</c:when>
									
							<c:otherwise>
									
								<input type="text" name='${field.propertyName}'
									id='${field.propertyName}'
									value='<c:out value="${fieldValue}"/>'
									size='${field.size}'
									maxlength='${field.maxLength}'
									style="${textStyle}" ${onblurcall} 
									class="${field.styleClass}"/>
						    
								<c:if test="${field.datePicker eq true}">
											
									<img src="${ConfigProperties.kr.externalizable.images.url}cal.gif"
										id="${field.propertyName}_datepicker"
										style="cursor: pointer;" 
										title="Date selector"
										alt="Date selector" 
										onmouseover="this.style.backgroundColor='red';"
										onmouseout="this.style.backgroundColor='transparent';" />
											
									<script type="text/javascript">
							    
										Calendar.setup( 
											{ 
												inputField : "${field.propertyName}", // ID of the input field
												ifFormat : "%m/%d/%Y", // the date format 
												button : "${field.propertyName}_datepicker" // ID of the button 
											} 
										);

									</script>

								</c:if>
						    
								<kul:fieldShowIcons isReadOnly="${isFieldReadOnly}" field="${field}" addHighlighting="${addHighlighting}" />
							
							</c:otherwise>
					
						</c:choose>
					
					</td>
				    
				</c:when>

				<c:when test="${field.fieldType eq field.TEXT_AREA}">

					<kul:fieldDefaultLabel isLookup="${isLookup}" isRequired="${field.fieldRequired}" 
						isReadOnly="${isFieldReadOnly}" cellWidth="${dataCellWidth}%" fieldType="${field.fieldType}" 
						fieldLabel="${field.fieldLabel}" />
								
					<td class="grid" width="${dataCellWidth}%">
			    
						<c:choose>

							<c:when test="${isFieldReadOnly}">
								<kul:fieldShowReadOnly field="${field}" addHighlighting="${addHighlighting}" />
							</c:when>

							<c:otherwise>

								<textarea name='${field.propertyName}'
									rows='${field.rows}'
									cols='${field.cols}'
									style="${textStyle}" ${onblurcall}
									maxlength='${field.maxLength}' ><c:out
									value="${fieldValue}"/></textarea>
								
								<kul:fieldShowIcons isReadOnly="${isFieldReadOnly}" field="${field}" addHighlighting="${addHighlighting}" />

							</c:otherwise>

						</c:choose>
					
					</td>
							
				</c:when>

				<c:when test="${field.fieldType eq field.CHECKBOX}">
					
					<kul:fieldDefaultLabel isLookup="${isLookup}" isRequired="${field.fieldRequired}" 
						isReadOnly="${isFieldReadOnly}" cellWidth="${dataCellWidth}%" 
						fieldType="${field.fieldType}" fieldLabel="${field.fieldLabel}" />
						
					<td class="grid" width="${dataCellWidth}%">
							
						<c:choose>
							
							<c:when test="${isFieldReadOnly}">
								
								<kul:fieldShowReadOnly field="${field}" addHighlighting="${addHighlighting}" />
							
							</c:when>
									
							<c:otherwise>
									
								<input type="checkbox" name="${field.propertyName}" 
									${field.propertyValue eq 'Yes' || field.propertyValue eq 'YES' ? 'checked="checked"' : ''}
									${onblurcall} />
								<input type="hidden" name="${field.propertyName}${Constants.CHECKBOX_PRESENT_ON_FORM_ANNOTATION}" value="present"/>
									
							</c:otherwise>
					
						</c:choose>
					
						<kul:fieldShowIcons isReadOnly="${isFieldReadOnly}" field="${field}" addHighlighting="${addHighlighting}" />
	                        
					</td>
                        
				</c:when>

				<c:when test="${field.fieldType eq field.DROPDOWN || field.fieldType eq field.DROPDOWN_APC}">

					<kul:fieldDefaultLabel isLookup="${isLookup}" isRequired="${field.fieldRequired}" 
						isReadOnly="${isFieldReadOnly}" cellWidth="${dataCellWidth}%" 
						fieldType="${field.fieldType}" fieldLabel="${field.fieldLabel}" />
						
					<td class="grid" width="${dataCellWidth}%">
						
						<c:choose>
							
							<c:when test="${isFieldReadOnly}">
                                                          <kul:fieldShowReadOnly field="${field}" addHighlighting="${addHighlighting}" />
							</c:when>
										
							<c:otherwise>
								<select name='${field.propertyName}' style="${textStyle}" ${onblurcall}>
									<c:if test="${ActionName != null && ActionName == 'Lookup.do' && !empty field.fieldValidValues[0].key}">
										<option value=""></option>
									</c:if>
									<kul:fieldSelectValues field="${field}"/>
								</select>
							</c:otherwise>

						</c:choose>							
							
						<kul:fieldShowIcons isReadOnly="${isFieldReadOnly}" field="${field}" addHighlighting="${addHighlighting}" />
	                        
					</td>
						 
				</c:when>

				<c:when test="${field.fieldType eq field.DROPDOWN_REFRESH}">

					<kul:fieldDefaultLabel isLookup="${isLookup}" isRequired="${field.fieldRequired}" 
						isReadOnly="${isFieldReadOnly}" cellWidth="${dataCellWidth}%" fieldType="${field.fieldType}" 
						fieldLabel="${field.fieldLabel}" />
				
					<td class="grid" width="${dataCellWidth}%">

						<select name='${field.propertyName}'
							onchange="document.forms[0].submit();" style="${textStyle}">

                            <kul:fieldSelectValues field="${field}"/>

						</select>
						&nbsp;
					
						<kul:fieldShowIcons isReadOnly="${isFieldReadOnly}" field="${field}" addHighlighting="${addHighlighting}" />
	                        
					</td>
						
				</c:when>

				<c:when test="${field.fieldType eq field.DROPDOWN_SCRIPT}">
                    
					<kul:fieldDefaultLabel isLookup="${isLookup}" isRequired="${field.fieldRequired}" 
						isReadOnly="${isFieldReadOnly}" cellWidth="${dataCellWidth}%" fieldType="${field.fieldType}" 
						fieldLabel="${field.fieldLabel}" />
						
					<td class="grid" width="${dataCellWidth}%">
                        <c:choose>
                            <c:when test="${isFieldReadOnly}">
                                <kul:fieldShowReadOnly field="${field}" addHighlighting="${addHighlighting}" />
                            </c:when>
                            <c:otherwise>
                                <select name='${field.propertyName}'
                                        onchange="${field.script}" style="${textStyle}">
                                    <kul:fieldSelectValues field="${field}"/>
                                </select>
                            </c:otherwise>
                        </c:choose>
						&nbsp;
						<kul:fieldShowIcons isReadOnly="${isFieldReadOnly}" field="${field}" addHighlighting="${addHighlighting}" />
	                        
					</td>
						
				</c:when>

				<c:when test="${field.fieldType eq field.RADIO}">
                    
					<kul:fieldDefaultLabel isLookup="${isLookup}" isRequired="${field.fieldRequired}" 
						isReadOnly="${isFieldReadOnly}" cellWidth="${dataCellWidth}%" fieldType="${field.fieldType}" 
						fieldLabel="${field.fieldLabel}" />
							
					<td class="grid" width="${dataCellWidth}%">
						
						<c:choose>

							<c:when test="${isFieldReadOnly}">

								<kul:fieldShowReadOnly field="${field}" addHighlighting="${addHighlighting}" />

							</c:when>

							<c:otherwise>

								<kul:fieldRadioValues field="${field}"/>

								<kul:fieldShowIcons isReadOnly="${isFieldReadOnly}" field="${field}" addHighlighting="${addHighlighting}" />

							</c:otherwise>

						</c:choose>
	                        
					</td>
						
				</c:when>

				<c:when test="${field.fieldType eq field.KUALIUSER}">
                    
					<kul:fieldDefaultLabel isLookup="${isLookup}" isRequired="${field.fieldRequired}" 
						isReadOnly="${isFieldReadOnly}" cellWidth="${dataCellWidth}%" fieldType="${field.fieldType}" 
						fieldLabel="${field.fieldLabel}" />
							
					<td class="grid" width="${dataCellWidth}%">
						<%-- gets the actual field names based on the simple field names stored in the Field object
						     this compensates for prefixes added by the document (like document.newMaintainableObject)
						  --%>
						<c:forEach items="${rows}" var="row2">
							<c:forEach items="${row2.fields}" var="field2">
								<c:if test="${fn:endsWith(field2.propertyName, field.universalIdAttributeName)}">
									<c:set var="universalIdField" value="${field2}" />
								</c:if>
						
								<c:if test="${fn:endsWith(field2.propertyName, field.personNameAttributeName)}">
									<c:set var="userNameField" value="${field2}" />
								</c:if>								
							</c:forEach>							    
						</c:forEach>
		
						<c:if test="${!hasErrors}">
							<kul:checkErrors keyMatch="${universalIdField.propertyName}" />
						</c:if>
						<kul:user userIdFieldName="${field.propertyName}" 
								  universalIdFieldName="${universalIdField.propertyName}" 
								  userNameFieldName="${userNameField.propertyName}" 
								  userId="${field.propertyValue}" 
								  universalId="${universalIdField.propertyValue}" 
								  userName="${userNameField.propertyValue}" 
								  label="${field.fieldLabel}"
								  referencesToRefresh="${field.referencesToRefresh}"
								  fieldConversions="${field.fieldConversions}"
								  lookupParameters="${field.lookupParameters}"
								  hasErrors="${hasErrors}"
								  readOnly="${universalIdField.keyField || isFieldReadOnly}"
								  onblur="${onblur}"
								  highlight="${addHighlighting && field.highlightField}">
							<jsp:attribute name="helpLink" trim="true">
								<kul:help
									businessObjectClassName="${field.businessObjectClassName}"
									attributeName="${field.fieldHelpName}"
									altText="${field.fieldHelpSummary}" />	    
							</jsp:attribute>
						</kul:user>
					</td>

				</c:when>
				
				<c:when test="${field.fieldType eq field.WORKFLOW_WORKGROUP}">
				
				  <kul:fieldDefaultLabel isLookup="${isLookup}" isRequired="${field.fieldRequired}" 
						isReadOnly="${isFieldReadOnly}" cellWidth="${dataCellWidth}%" fieldType="${field.fieldType}" 
						fieldLabel="${field.fieldLabel}" />	
				
									
					<td class="grid" width="${dataCellWidth}%">
						<c:choose>
							<c:when test="${isFieldReadOnly}">
								<kul:fieldShowReadOnly field="${field}" addHighlighting="${addHighlighting}" />
							</c:when>
									
							<c:otherwise>
								<input type="text" name='${field.propertyName}'
									id='${field.propertyName}'
									value='<c:out value="${fieldValue}"/>'
									size='${field.size}'
									maxlength='${field.maxLength}'
									style="${textStyle}" ${onblurcall} 
									class="${field.styleClass}"/>
								
								<!--  adding a lookup here because it goes to workflow as opposed to Kuali -->
								<kul:workflowWorkgroupLookup fieldConversions="workgroupName:${field.propertyName}" />	
									
								<kul:fieldShowIcons isReadOnly="${isFieldReadOnly}" field="${field}" addHighlighting="${addHighlighting}" />
							</c:otherwise>
						</c:choose>
					</td>
				
				</c:when>
					
				<c:when test="${field.fieldType eq field.LOOKUP_HIDDEN || field.fieldType eq field.LOOKUP_READONLY}">
			    
					<kul:fieldDefaultLabel isLookup="${isLookup}" isRequired="${field.fieldRequired}" 
						isReadOnly="${isFieldReadOnly}" cellWidth="${dataCellWidth}%" fieldType="${field.fieldType}" 
						fieldLabel="${field.fieldLabel}" />
								
					<td class="grid" width="${dataCellWidth}%">
								
						<input type="hidden" name='${field.propertyName}' value='<c:out value="${fieldValue}"/>' />
					
						<c:if test="${field.fieldType eq field.LOOKUP_READONLY}">
					
							<c:out value="${fieldValue}"/>&nbsp;
							
						</c:if>

						&nbsp;

						<kul:fieldShowIcons isReadOnly="${isFieldReadOnly}" field="${field}" addHighlighting="${addHighlighting}" />
								
					</td>
							
				</c:when>

				<c:when test="${field.fieldType eq field.IMAGE_SUBMIT && not isFormReadOnly}">
					<%-- Might need something in here to show conditionally based upon isFieldReadOnly. --%>
                    <c:set var="imageCellSpan" value="2" />
                    <c:if test="${!isFieldAddingToACollection && isActionEdit}" >
                        <c:set var="imageCellSpan" value="4" />
                    </c:if>
                    
                    <c:set var="cellAlign" value="center" />
                    <c:if test="${!empty field.cellAlign}" >
                      <c:set var="cellAlign" value="${field.cellAlign}" />
                    </c:if>  
                    
                    <c:set var="anchorTabIndex" value="${currentTabIndex}"/>
                    <c:if test="${fn:contains(field.propertyName, Constants.DELETE_LINE_METHOD)}">
                    	<%-- when deleting, we have to anchor back to the top level tab, rather than the sub tab that we were viewing --%>
                    	<c:set var="anchorTabIndex" value="${topLevelTabIndex}"/>
                    </c:if>
					<th class="grid" colspan="4" align="${cellAlign}" >
						<input type="image" 
							name='${field.propertyName}.${Constants.METHOD_TO_CALL_PARM13_LEFT_DEL}${currentTabIndex}${Constants.METHOD_TO_CALL_PARM13_RIGHT_DEL}.anchor${anchorTabIndex}'
							src='<c:out value="${fieldValue}"/>'/>
						<kul:fieldShowIcons isReadOnly="${isFieldReadOnly}" field="${field}" addHighlighting="false"/>
					</th>
				</c:when>
	                
			</c:choose>
				
		</c:forEach>
		
	</tr>
	
</c:forEach>


