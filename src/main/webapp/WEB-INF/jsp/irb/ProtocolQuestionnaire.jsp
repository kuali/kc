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

<kul:documentPage
	showDocumentInfo="true"
	htmlFormAction="questionnaire"
	documentTypeName="${KualiForm.docTypeName}"
	renderMultipart="false"
	showTabButtons="true"
	auditCount="0"
  	headerDispatch="${KualiForm.headerDispatch}"
  	headerTabActive="questionnaire">
  	<script src="scripts/jquery/jquery.js"></script>
<link rel="stylesheet" href="css/jquery/questionnaire.css" type="text/css" />
<link rel="stylesheet" href="css/jquery/screen.css" type="text/css" />
<link rel="stylesheet" href="css/jquery/new_kuali.css" type="text/css" />
<link rel="stylesheet" href="css/jquery/kuali-stylesheet.css" type="text/css" />
<link rel="stylesheet" href="css/jquery/jquery.treeview.css" type="text/css" />
<script type="text/javascript" src="scripts/jquery/jquery.treeview.js"></script>
  	
	<kra-irb:protocolQuestionnaireAnswers/>
	<kul:documentControls transactionalDocument="true" suppressRoutingControls="true" />
  	
  	
  		<script type="text/javascript">
		$(document).ready(function(){
			
			// Opened Panel
				$(".openedpanelcontrol").toggle(
					function()
					{
						$(this).parent().parent().parent().parent().next().slideUp(400);
						$(this).attr("src","../images/tinybutton-show.gif");
					},function(){
						$(this).parent().parent().parent().parent().next().slideDown(400);
						$(this).attr("src","../images/tinybutton-hide.gif");
					}
				);
			
			// Closed Panel
				$(".closedpaneldiv").attr("style","display:none;");
				$(".closedpanelcontrol").toggle(
					function()
					{
						$(this).parent().parent().parent().parent().next().slideDown(400);
						$(this).attr("src","../images/tinybutton-hide.gif");
					},function(){
						$(this).parent().parent().parent().parent().next().slideUp(400);
						$(this).attr("src","../images/tinybutton-show.gif");
					}
				);
				
			// Opened Section
				$(".openedsectioncontrol").toggle(
					function()
					{
						$(this).parent().parent().parent().parent().next().slideUp(400);
						$(this).attr("src","../images/tinybutton-show.gif");
					},function(){
						$(this).parent().parent().parent().parent().next().slideDown(400);
						$(this).attr("src","../images/tinybutton-hide.gif");
					}
				);
			
			// Closed Section
				$(".closedsectiondiv").attr("style","display:none;");
				$(".closedsectioncontrol").toggle(
					function()
					{
						$(this).parent().parent().parent().parent().next().slideDown(400);
						$(this).attr("src","../images/tinybutton-hide.gif");
					},function(){
						$(this).parent().parent().parent().parent().next().slideUp(400);
						$(this).attr("src","../images/tinybutton-show.gif");
					}
				);
			
			// More/Less Information...
				$(".Qmoreinfocontrol").parent().next().hide(0);
				$(".Qmoreinfocontrol").toggle(
					function()
					{
						$(this).parent().next().slideDown(400);
						$(this).html("Less Information...");
					},function(){
						$(this).parent().next().slideUp(400);
						$(this).html("More Information...");
					}
				);
				


			// set up Questions show/hide	
                    var ansHeaderSize = $("#numberOfQuestionaires").attr("value");
                    //alert("size "+ansHeaderSize);
               for ( var i = 0; i < ansHeaderSize; i++) {
					var panelcontentid = "questionpanelcontent"+i;
					var panelcontrolid = "questionpanelcontrol"+i;
	                $("#"+panelcontentid).hide();
	                $("#"+panelcontrolid).toggle(
	                    function()
	                    {
	                        $("#"+panelcontentid).slideDown(500);
	                        $("#"+panelcontrolid).html("<img src='kr/images/tinybutton-hide.gif' alt='show/hide panel' width='45' height='15' border='0' align='absmiddle'>");
	                    },function(){
	                        $("#"+panelcontentid).slideUp(500);
	                        $("#"+panelcontrolid).html("<img src='kr/images/tinybutton-show.gif' alt='show/hide panel' width='45' height='15' border='0' align='absmiddle'>");
	                    }
	                );
                }


					
		});
	</script>
  	
  	
</kul:documentPage>