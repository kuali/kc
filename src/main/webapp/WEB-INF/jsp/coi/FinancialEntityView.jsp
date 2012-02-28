<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>

<head>
<link rel="stylesheet" href="css/jquery/new_kuali.css" type="text/css" />
<link rel="stylesheet" href="css/jquery/kuali-stylesheet.css"
	type="text/css" />
<script>var jsContextPath = "${pageContext.request.contextPath}";</script>
<title>Kuali :: Financial Entities</title>
<script type="text/javascript" src="scripts/jquery/jquery.js"></script>
<script type="text/javascript" src="scripts/jquery/jquery.tablesorter.js"></script>
<script type="text/javascript" src="scripts/jquery/jquery.fancybox-1.3.4jh.js"></script>
<link rel="stylesheet" type="text/css" href="scripts/jquery/fancybox/jquery.fancybox-1.3.4.css" media="screen" />

<script type="text/javascript">
            var $j = jQuery.noConflict();
        	$j.fancybox.setup({ dropshadow : false, overlayShow : false});  
        	$j(document).ready(function() {       		 
        		        $j('#previousEntry').hide();
       		       		$j(".change2").hide();
        		        $j('#previousEntry-showHide').click(function() {
            		        if ($j("#showHide").attr('src') == "${ConfigProperties.kra.externalizable.images.url}tinybutton-hide.gif") {
            		       		$j("#showHide").attr('src',"${ConfigProperties.kra.externalizable.images.url}tinybutton-show.gif"); 
            		       		$j(".changed").css({color: "#000000", 'font-weight': "normal"});
            		       		$j(".change1").css({color: "#000000", 'font-weight': "bold"});
            		       		$j(".change2").hide();
            		        	$j('#previousEntry').hide('fast');       
            		        } else {
            		        	$j("#showHide").attr('src',"${ConfigProperties.kra.externalizable.images.url}tinybutton-hide.gif"); 
            		        	$j('#previousEntry').show('fast');  
            		        	$j(".changed").css({color: "#FF0000", 'font-weight': "bold"});
            		        	$j(".change1").css({color: "#FF0000", 'font-weight': "bold"});
            		       		$j(".change2").show();
            		        	$j(".change2").css({color: "#FF0000", 'font-weight': "bold"});
            		        }
                		    return false;
        		        });      	
        	})
        </script>
</head>


<c:set var="numberOfVersions"
	value="${KualiForm.financialEntitySummaryHelper.numberOfVersions}" />
<c:set var="currentVersionNumber"
	value="${KualiForm.financialEntitySummaryHelper.currentVersionNumber}" />
<c:set var="attributes"
	value="${DataDictionary.PersonFinIntDisclosure.attributes}" />
<c:set var="currentFinancialEntity"
	value="${KualiForm.financialEntitySummaryHelper.currentFinancialEntity}" />
<c:set var="previousFinancialEntity"
	value="${KualiForm.financialEntitySummaryHelper.previousFinancialEntity}" />
<c:set var="entityNumber" value="${currentFinancialEntity.entityNumber}" />
<c:set var="entityStatus" value="${currentFinancialEntity.statusCode}" />
<table class="content_table">
	<tr>
		<th class="elementhead_left">${currentFinancialEntity.entityName}</th>
		<th class="elementhead_right" colspan="3">
		<spanclass="subhead-right"> &nbsp; </span>
		</th>
	</tr>
	<tr>
		<td class="content_gradient" colspan="4">
			<b><fmt:formatDate value="${currentFinancialEntity.updateTimestamp}" pattern="MM/dd/yyyy KK:mm a" /> &nbsp;
			${currentFinancialEntity.updateUser} 
			<span style="float: right;">
				Version ${currentVersionNumber} of ${numberOfVersions} 
		
				<c:choose>
					<c:when test="${currentVersionNumber != 1}">
						<c:set var="versionNumber" value="${currentVersionNumber - 1}" />
						<a href="${pageContext.request.contextPath}/financialEntityEditList.do?methodToCall=previousNextVersion&versionNumber=${versionNumber}&entityNumber=${entityNumber}&status=${entityStatus}">		
							<img id="prev" src='${ConfigProperties.kra.externalizable.images.url}tinybutton-prev.gif' styleClass="tinybutton" title="Show" />
						</a>
					</c:when>
					<c:otherwise>
						<img id="prev" src='${ConfigProperties.kra.externalizable.images.url}tinybutton-prev2.png' styleClass="tinybutton" title="Show" />
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${currentVersionNumber != numberOfVersions}" >
						<c:set var="versionNumber" value="${currentVersionNumber + 1}" />
						<a href="${pageContext.request.contextPath}/financialEntityEditList.do?methodToCall=previousNextVersion&versionNumber=${versionNumber}&entityNumber=${entityNumber}&status=${entityStatus}">		
							<img id="next" src='${ConfigProperties.kra.externalizable.images.url}tinybutton-next.gif' styleClass="tinybutton" title="Show" /> 
						</a>
					</c:when>
					<c:otherwise>
						<img id="next" src='${ConfigProperties.kra.externalizable.images.url}tinybutton-next2.png' styleClass="tinybutton" title="Show" /> 		
					</c:otherwise>
				</c:choose>
			</span>
			</b>
		</td>
	</tr>
	<tr>
		<td>
			<table style="width: 100%" class="content_table" border="1">
				<kra-coi:FinancialEntitySummary current="true" />
			</table>
		</td>
	</tr>
	<c:if test="${currentVersionNumber > 1}" >	
		<tr>
			<td colspan="4" style="background-color: rgb(195, 195, 195); padding : 5px">
			<b>
				<a id="previousEntry-showHide" title="" href="">
					<img id="showHide" src='${ConfigProperties.kra.externalizable.images.url}tinybutton-show.gif' styleClass="tinybutton" title="Show" /> 
				</a> Compare to Previous Version
				<fmt:formatDate value="${previousFinancialEntity.updateTimestamp}"
					pattern="MM/dd/yyyy KK:mm a" /> &nbsp;
				${previousFinancialEntity.updateUser}</b>
			</td>
		</tr>
		<tr>
			<td>
				<div id="previousEntry">
					<table style="width: 100%" class="content_table" border="1">
						<kra-coi:FinancialEntitySummary current="false" />
					</table>
				</div>
			</td>
		</tr>
	</c:if>
</table>
</html:html>