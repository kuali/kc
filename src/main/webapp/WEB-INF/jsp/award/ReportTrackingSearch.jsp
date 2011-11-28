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
<c:set var="reportTrackingAttributes" value="${DataDictionary.ReportTracking.attributes}" />
<jsp:useBean id="paramMap1" class="java.util.HashMap"/>

<%--NOTE: DO NOT FORMAT THIS FILE, DISPLAY:COLUMN WILL NOT WORK CORRECTLY IF IT CONTAINS LINE BREAKS --%>
<c:set var="headerMenu" value="" />
<c:if test="${KualiForm.suppressActions!=true and KualiForm.supplementalActionsEnabled!=true}">
    <c:set var="headerMenu" value="${KualiForm.lookupable.createNewUrl}   ${KualiForm.lookupable.htmlMenuBar}" />
</c:if>

<c:set var="numberOfColumns" value="${KualiForm.numColumns}" />
<c:set var="headerColspan" value="${numberOfColumns * 2}" />


<kul:page lookup="true" showDocumentInfo="false"
	headerMenuBar="${headerMenu}"
	headerTitle="Lookup" docTitle="" transactionalDocument="false"
	htmlFormAction="reportTrackingLookup" >

	<SCRIPT type="text/javascript">
      var kualiForm = document.forms['KualiForm'];
      var kualiElements = kualiForm.elements;
    </SCRIPT>
    <script type="text/javascript" src="${pageContext.request.contextPath}/dwr/interface/DocumentTypeService.js"></script>
    <script src="scripts/jquery/jquery.js"></script>
    <script type="text/javascript" src="scripts/jquery/jquery.fancybox-1.3.4jh.js"></script>
    <link rel="stylesheet" type="text/css" href="scripts/jquery/fancybox/jquery.fancybox-1.3.4.css"" media="screen"/>    
    
	<script type="text/javascript"> 
   		var $jq = jQuery.noConflict();
	</script>
    
	
	<c:if test="${KualiForm.headerBarEnabled}">
	<div class="headerarea-small" id="headerarea-small">
		<h1><c:out value="${KualiForm.lookupable.title}" /> <c:choose>
			<c:when test="${KualiForm.fields.docTypeFullName != null}">
				<%-- this is a custom doc search --%>
				<kul:help searchDocumentTypeName="${KualiForm.fields.docTypeFullName}" altText="lookup help" />
			</c:when>
			<c:otherwise>
				<%-- KNS looup --%>
				<kul:help lookupBusinessObjectClassName="${KualiForm.lookupable.businessObjectClass.name}" altText="lookup help" />
			</c:otherwise>
		</c:choose></h1>
	</div>
	</c:if>
	
	<c:if test="${KualiForm.renderSearchButtons}">
	  <kul:enterKey methodToCall="search" />
	</c:if>  

	<html-el:hidden name="KualiForm" property="backLocation" />
	<html-el:hidden name="KualiForm" property="formKey" />
	<html-el:hidden name="KualiForm" property="lookupableImplServiceName" />
	<html-el:hidden name="KualiForm" property="businessObjectClassName" />
	<html-el:hidden name="KualiForm" property="conversionFields" />
	<html-el:hidden name="KualiForm" property="hideReturnLink" />
	<html-el:hidden name="KualiForm" property="suppressActions" />
	<html-el:hidden name="KualiForm" property="multipleValues" />
	<html-el:hidden name="KualiForm" property="lookupAnchor" />
	<html-el:hidden name="KualiForm" property="readOnlyFields" />
	<html-el:hidden name="KualiForm" property="referencesToRefresh" />
	<html-el:hidden name="KualiForm" property="hasReturnableRow" />
	<html-el:hidden name="KualiForm" property="docNum" />
	<html-el:hidden name="KualiForm" property="showMaintenanceLinks" />
	<html-el:hidden name="KualiForm" property="headerBarEnabled" />
    <html-el:hidden name="KualiForm" property="fieldNameToFocusOnAfterSubmit"/>


	<c:if test="${KualiForm.headerBarEnabled}">
	<c:forEach items="${KualiForm.extraButtons}" varStatus="status">
		<html-el:hidden name="KualiForm" property="extraButtons[${status.index}].extraButtonSource" />
		<html-el:hidden name="KualiForm" property="extraButtons[${status.index}].extraButtonParams" />
	</c:forEach>
		<c:if test="${KualiForm.supplementalActionsEnabled==true}" >
		<div class="lookupcreatenew" title="Supplemental Search Actions" style="padding: 3px 30px 3px 300px;">
			${KualiForm.lookupable.supplementalMenuBar} &nbsp;
			<c:set var="extraField" value="${KualiForm.lookupable.extraField}"/>
			<c:if test="${not empty extraField}">
				<%--has to be a dropdown script for now--%>
				<c:if test="${extraField.fieldType eq extraField.DROPDOWN_SCRIPT}">

                            	${kfunc:registerEditableProperty(KualiForm, extraField.propertyName)}
                                <select id='${extraField.propertyName}' name='${extraField.propertyName}'
                                        onchange="${extraField.script}" style="">
                                    <kul:fieldSelectValues field="${extraField}"/>
                                </select>

						&nbsp;

							<kul:fieldShowIcons isReadOnly="${true}" field="${extraField}" addHighlighting="${true}" />

				</c:if>
			</c:if>
		</div>
	</c:if>
	
	<div class="right">
		<div class="excol">
		* required field
		</div>
	</div>
    <div class="msg-excol">
      <div class="left-errmsg">
        <kul:errors errorTitle="Errors found in Search Criteria:" />
        <kul:messages/>
	  </div>
    </div>
    <br/>
    </c:if>

	<table width="100%">
	  <c:if test="${KualiForm.lookupCriteriaEnabled}">
		<tr>
			<td width="1%"><img src="${ConfigProperties.kr.externalizable.images.url}pixel_clear.gif" alt="" width="20"
				height="20"></td>
			<td>

			<div id="lookup" align="center"><br />
			<br />
			<table align="center" cellpadding="0" cellspacing="0" class="datatable-100 searchTable">
				<c:set var="FormName" value="KualiForm" scope="request" />
				<c:set var="FieldRows" value="${KualiForm.lookupable.rows}" scope="request" />
				<c:set var="ActionName" value="Lookup.do" scope="request" />
				<c:set var="IsLookupDisplay" value="true" scope="request" />
				<c:set var="cellWidth" value="50%" scope="request" />

                <kul:rowDisplay rows="${FieldRows}" skipTheOldNewBar="true" numberOfColumns="${numberOfColumns}" />

				<tr align="center">
					<td height="30" colspan="${headerColspan}"  class="infoline">
					
					<c:if test="${KualiForm.renderSearchButtons}">
					  <html:image
						property="methodToCall.search" value="search"
						src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_search.gif" styleClass="tinybutton"
						alt="search" title="search" border="0" /> 
					  <html:image
						property="methodToCall.clearValues" value="clearValues"
						src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_clear.gif" styleClass="tinybutton"
						alt="clear" title="clear" border="0" /> 
					</c:if>	
					
					<c:if test="${KualiForm.formKey!=''}">
						<c:if test="${!empty KualiForm.backLocation}"><a
							href='<c:out value="${KualiForm.backLocation}?methodToCall=refresh&docFormKey=${KualiForm.formKey}&anchor=${KualiForm.lookupAnchor}&docNum=${KualiForm.docNum}" />'  title="cancel"><img
							src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_cancel.gif" class="tinybutton" alt="cancel" title="cancel"
							border="0" /></a></c:if>
					</c:if>
					
					
					<!-- Optional extra buttons -->
					<c:forEach items="${KualiForm.extraButtons}" var="extraButton" varStatus="status">
						<c:if test="${!empty extraButton.extraButtonSource && !empty extraButton.extraButtonParams}">
							<c:if test="${not KualiForm.ddExtraButton && !empty KualiForm.backLocation}">
								<a href='<c:out value="${KualiForm.backLocation}?methodToCall=refresh&refreshCaller=kualiLookupable&docFormKey=${KualiForm.formKey}&anchor=${KualiForm.lookupAnchor}&docNum=${KualiForm.docNum}" /><c:out value="${extraButton.extraButtonParams}" />'><img
							    	src='<c:out value="${extraButton.extraButtonSource}" />'
									class="tinybutton" border="0" /></a>
							</c:if>
							<c:if test="${KualiForm.ddExtraButton}">
								<html:image src="${extraButton.extraButtonSource}" styleClass="tinybutton" property="methodToCall.customLookupableMethodCall" alt="${extraButton.extraButtonAltText}" onclick="${extraButton.extraButtonOnclick}"/> &nbsp;&nbsp;
							</c:if>
						</c:if>

					</c:forEach>
					<c:if test="${KualiForm.multipleValues && !empty KualiForm.backLocation}">
						<a
							href='<c:out value="${KualiForm.backLocation}?methodToCall=refresh&docFormKey=${KualiForm.formKey}&anchor=${KualiForm.lookupAnchor}&docNum=${KualiForm.docNum}" />'>
						<img src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_retnovalue.gif" class="tinybutton"
							border="0" /></a>
						<a
							href='<c:out value="${KualiForm.backLocation}?methodToCall=refresh&docFormKey=${KualiForm.formKey}&refreshCaller=multipleValues&searchResultKey=${searchResultKey}&searchResultDataKey=${searchResultDataKey}&anchor=${KualiForm.lookupAnchor}&docNum=${KualiForm.docNum}"/>'>
						<img src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_returnthese.gif" class="tinybutton"
							border="0" /></a>
					</c:if>
					</td>
				</tr>
			  </c:if>
			</table>
			<table cellpadding="0" cellspacing="0" class="datatable-100" style="width: 100%">
				<tr>
					<td height="30" colspan="${headerColspan}" class="infoline" style="text-align: center;">
					  <div class="customSearchButtons">
				        <a class="showHideSearch hideSearch" onclick="toggleSearchTable(this);">show or hide search details</a>
						<a href="#customSelection" id="customSelLink"><img src="${ConfigProperties.kra.externalizable.images.url}buttonsmall-changeview.gif" alt="Change view"/></a>
						<!--  hidden image used by fancybox.close to call updateView on close -->
						${kfunc:registerEditableProperty(KualiForm, "methodToCall.resetCustomView")}			  
				  		<html:image styleId="onChangeViewClose" property="methodToCall.updateView" style="display:none;"
				     		src="${ConfigProperties.kra.externalizable.images.url}tinybutton-updateview.gif" styleClass="tinybutton"/>
				      </div>						
					</td>				
				</tr>			
			</table>
			</div>
			
			
			<style>
			  .hideTableCurveLeft {
			    background: white;
			    float: left;
			    width: 25px;
			    height: 65px;
				-moz-border-radius-topright: 25px;
				border-top-right-radius: 25px;							    
			  }
			  .hideTableCurveRight {
			    background: white;
			    float: right;
			    width: 25px;
			    height: 65px;
				-moz-border-radius-topleft: 25px;
				border-top-left-radius: 25px;
			  }
			  #hideTable {
			    background: yellow;
			    width: 225px;
			  }
			  .hideTableInner {
			  	text-align: center;
			  	height: 65px;
				width:175px;
				-moz-border-radius-bottomright: 25px;
				border-bottom-right-radius: 25px;
				-moz-border-radius-bottomleft: 25px;
				border-bottom-left-radius: 25px;				
			  }			
			  .reportTrackingResults {
			    margin-top: 2em;
			  }
			  
			  .aggregateTable {
			    width: 100%;
			  }
			  
			  .detailTable {
			    width: 100%;
			  }
			  .showHideLink {
			  	display: block;
			    width: 45px;
			    height: 15px;
			    border: 0px;
			    text-indent: -1000px;
			    font-size: 0px;
			    line-height: 0px;
			    overflow: hidden;
			    cursor: pointer;
			  }
			  .showHideSearch {
			  	display: block;
			  	clear: none;
			    width: 105px;
			    height: 18px;
			    border: 0px;
			    text-indent: -1000px;
			    font-size: 0px;
			    line-height: 0px;
			    overflow: hidden;
			    cursor: pointer;
			    float: left;
			  }		
			  .customSearchButtons {
			    margin-left: auto;
			    margin-right: auto;
			    width: 211px;
			  }
			  .showLink {
			    background-image: url("${ConfigProperties.kr.externalizable.images.url}tinybutton-show.gif");
			  }
			  .hideLink {
			    background-image: url("${ConfigProperties.kr.externalizable.images.url}tinybutton-hide.gif");
			  }
			  .showSearch {
			    background-image: url("${ConfigProperties.kra.externalizable.images.url}buttonsmall-showsearch.gif");
			  }
			  .hideSearch {
			    background-image: url("${ConfigProperties.kra.externalizable.images.url}buttonsmall-hidesearch.gif");
			  }
			  #customSelection {
			    text-align: center;
			  }
			  #customSelection table {
			    width: 100%;
			  }
			  div#fancyboxwrap{
			  	top:120px
			  }

			</style>
			<script>
				var showHideSearchClass = ".showHideSearch";
				var showSearchClass = "showSearch";
				var hideSearchClass = "hideSearch";
				function toggleSearchTable() {
					if ($jq('table.searchTable').is(':visible')) {
						$jq('table.searchTable').hide();
						$jq(showHideSearchClass).removeClass(hideSearchClass);
						$jq(showHideSearchClass).addClass(showSearchClass);
					} else {
						$jq('table.searchTable').show();
						$jq(showHideSearchClass).addClass(hideSearchClass);
						$jq(showHideSearchClass).removeClass(showSearchClass);
					}
				}
				function showSearchTable() {
					if (!$jq('table.searchTable').is(':visible')) {
						$jq('table.searchTable').show();
						$jq(showHideSearchClass).addClass(hideSearchClass);
						$jq(showHideSearchClass).removeClass(showSearchClass);
					}
				}				
				var showClass = "showLink";
				var hideClass = "hideLink";									
				function toggleDetails(link) {
					var detailRow = $jq(link).parents('.aggregateResult').next('.detailRow');
					if ($jq(detailRow).is(':visible')) {
						$jq(detailRow).children().children().slideUp(100, function() {
							$jq(detailRow).hide();});
						$jq(link).removeClass(hideClass);
						$jq(link).addClass(showClass);
					} else {
						getDetails(link);
					}					
				} 
				function buildAggregateQueryString(jsonStr) {
					var result = '';
					result += "groupByResultIndex=" + jsonStr;
					return result;
				}
				function getDetails(link) {
					var data = $jq('form').serialize() + "&" + buildAggregateQueryString($jq(link).next('div').html()) 
						+ "&methodToCall=getDetails";
					
		          $jq.ajax({
		              type: 'GET',
		              dataType: 'html',
		              data: data,
		              cache: false,
		              async: true,
		              timeout: 30000,
		              error: function(){
		                 alert('Error retrieving report tracking records');
		              },
		              success: function(xml){
		            	  try {
		            		  var detailRow = $jq(link).parents('.aggregateResult').next('.detailRow');
		            		  $(detailRow).find('div').html(xml);
							  $jq(detailRow).show();
							  $jq(detailRow).children().children().slideDown(100);
							  $jq(link).addClass(hideClass);
							  $jq(link).removeClass(showClass);			            		  
		            	  } catch(e) {
			            	  alert(e);
		            	  }
		              }
		          });
				}
				function toggleCustomView(radioBtn) {
					if ($jq(radioBtn).val() != ${KualiForm.reportTrackingViews.customViewIndex}) {
						$jq("input[name='customGroupByFields']").attr('disabled', true);
						$jq("input[name='customDetailFields']").attr('disabled', true);
					} else {
						$jq("input[name='customGroupByFields']").removeAttr('disabled');
						$jq("input[name='customDetailFields']").removeAttr('disabled');
					}
				} 
				$jq(document).ready(function() {
					$jq('a.showHideLink').each(function() { $jq(this).click(function() { toggleDetails(this); })});
					toggleCustomView($jq("input[name=currentViewIndex]:checked"));
					$jq.fancybox.setup({ dropshadow : false, overlayShow : false });
					$jq("#customSelLink").fancybox({
						'hideOnContentClick' : false,
						'width:' : 400,
						'onClosed' : function() { $jq('#onChangeViewClose').click(); },
						'onStart' : function() { showSearchTable(); }
						 });
				});
			</script>
			<div id="workarea" class="tab-container reportTrackingResults">			  
			  ${kfunc:registerEditableProperty(KualiForm, "groupByResultIndex")}
				<c:if test="${not empty KualiForm.groupedByResults}">
				    <script>$jq(document).ready(function() { toggleSearchTable($jq('.showHideSearch')); });</script>
					<table cellpadding="0" cellspacing="0" class="aggregateTable">
						<tr>
							<th>&nbsp;</th>
							<c:forEach items="${KualiForm.groupedByDisplayFields}" var="col">
								<th><kul:htmlAttributeLabel attributeEntry="${reportTrackingAttributes[col]}" noColon="true" readOnly="true"/></th>
							</c:forEach>
							<th>Count</th>
						</tr>
						<c:forEach items="${KualiForm.groupedByResults}" var="resultLine" varStatus="ctr">
							<tr class="aggregateResult">
							    <td><a class="showHideLink showLink">show and hide details</a><div style="display:none;" title="none"><c:out value="${ctr.index}"/></div></td>
								<c:forEach items="${KualiForm.groupedByDisplayFields}" var="col">
									<td>
									  <bean:write name="KualiForm" property="groupedByResults[${ctr.index}].${col}"/>
									 </td>
								</c:forEach>
								<td><c:out value="${resultLine['itemCount']}"/></td>
							</tr>
							<tr class="detailRow" style="display: none;">
							  <td colspan="${fn:length(KualiForm.groupedByDisplayFields)+2}">
							    <div></div>
							  </td>
							</tr>
						</c:forEach>
				</c:if>
			</div>
			
		</td>
	  </tr>
  </table>
  <div style="display:none;"><div id="customSelection">
           <html:image property="methodToCall.updateView"
     src="${ConfigProperties.kra.externalizable.images.url}tinybutton-updateview.gif" styleClass="tinybutton"
     onclick="$jq.fancybox.close(); return false;"/><br/>			  
           <c:forEach items="${krafn:getOptionList('org.kuali.kra.award.paymentreports.ReportTrackingViewValuesFinder', paramMap)}" var="option">
             <html:radio property="currentViewIndex" value="${option.key}" onchange="toggleCustomView(this);">${option.label}</html:radio>
           </c:forEach>
           <table id="customViewColumnSelection">
             <tr>
               <th>Columns</th>
               <th>Group</th>
               <th>Detail</th>
             </tr>
           	<c:forEach items="${KualiForm.reportTrackingViews.allFields}" var="col" varStatus="ctr">
           	  <tr>
           	    <th><kul:htmlAttributeLabel attributeEntry="${reportTrackingAttributes[col]}" noColon="true" readOnly="true"/></th>
           	    <td><c:set var="propertyName" value="customGroupByFields"/><html:multibox property="${propertyName}"><c:out value="${col}"/></html:multibox>${kfunc:registerEditableProperty(KualiForm, propertyName)}</td>
           	    <td><c:set var="propertyName" value="customDetailFields"/><html:multibox property="${propertyName}" value="${col}"/>${kfunc:registerEditableProperty(KualiForm, propertyName)}</td>
           	  </tr>
           	</c:forEach>
           	<tr>
           	  <td colspan="3">
	           <html:image property="methodToCall.resetCustomView"
			     src="${ConfigProperties.kra.externalizable.images.url}tinybutton-resetcustomview.gif" styleClass="tinybutton"
			     onclick="$jq('#onChangeViewClose').attr('name', 'methodToCall.resetCustomView'); $jq.fancybox.close(); return false;"/>
           	  </td>
           	</tr>
           </table>
           <html:image property="methodToCall.updateView"
     src="${ConfigProperties.kra.externalizable.images.url}tinybutton-updateview.gif" styleClass="tinybutton"
     onclick="$jq.fancybox.close(); return false;"/> 
           
  </div></div>  
</kul:page>
			