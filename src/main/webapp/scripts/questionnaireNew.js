var node;
var i = 1;
var ucount = 1;
var removedNode;
var maxCopyNodeIdx = 0;
var cutNode;
var copyNode;
var sqlScripts = "createnew";
var jsContextPath = "${pageContext.request.contextPath}";
var sqls = [];
var sqlidx = 0;
var groupid = 0;
var curgroup = 0;
var cutNodeParentCode = 0;
$(document).ready(function() {
	$.ajaxSettings.cache = false;
	$("#example").treeview( {
		toggle : function() {
			// alert ("toggle "+$(this).attr("id"));
	},
	animated : "fast",
	collapsed : true,
	control : "#treecontrol"
	});

});

// need to modify this for questionnaire
// $(document).ready(function(){

//$("#save").click(function() {
//	var qname = $('#newQuestionnaire\\.name').attr("value");
//	var qnaireid = $('#newQuestionnaire\\.questionnaireId').attr("value");
//	var qdescription = $('#newQuestionnaire\\.description').attr("value");
//	var qisfinal = $('#newQuestionnaire\\.isFinal').attr("checked");
//	// alert
//		// ("save"+qname+qdescription+$('#newQuestionnaire\\.isFinal').attr("checked"));
//		if (qname == '') {
//			alert("Questionnaire Name is required");
//		} else if (qdescription == '') {
//			alert("Questionnaire description is required");
//		} else {
//			// TODO : FF seems to have trouble with "#;#"
//
//			if (sqlScripts.indexOf("#;#") > 1) {
//				// if current sqlScripts is not in array yet
//				// 10 should be fine to use as check
//				sqls[sqlidx++] = sqlScripts;
//			}
//			// TODO : problem with '&' in string will in name or description
//			qname = qname.replace(/&/g, ";amp");
//			qdescription = qdescription.replace(/&/g, ";amp");
//
//			var desc1 = "";
//			var saveok = 'true';
//			if (qdescription.length > 1800) {
//				// TODO : 1800 should be ok
//				desc1 = qdescription.substring(1800);
//				qdescription = qdescription.substring(0, 1800);
//			}
//
//			// Save new questionnaire bo
//			$.ajax( {
//				url : 'questionnaireAjax.do',
//				type : 'GET',
//				dataType : 'html',
//				cache : false,
//				data : 'action=savebo&newQuestionnaire.name=' + qname
//						+ '&newQuestionnaire.questionnaireId=' + qnaireid
//						+ '&newQuestionnaire.description=' + qdescription
//						+ '&newQuestionnaire.isFinal=' + qisfinal,
//				async : false,
//				timeout : 1000,
//				error : function() {
//					// alert('Error loading XML document');
//				jumpToAnchor('topOfForm');
//				$('<span id="msg"/>').css("color", "red").html(
//						"Error when save Questionnaire").appendTo(
//						$("#headermsg"))
//				$('<br/>').appendTo($("#headermsg"));
//				saveok = 'false';
//			},
//			success : function(xml) {
//				// sqlScripts="createnew";
//				$(xml).find('h3').each(function() {
//					// var item_text = $(this).text();
//						$('#newQuestionnaire\\.questionnaireId').attr("value",
//								$(this).text().substring(9));
//						$('<span id="msg"/>').css("color", "black").html(
//								"Questionnaire saved successfully").appendTo(
//								$("#headermsg"));
//						$('<br/>').appendTo($("#headermsg"));
//						jumpToAnchor('topOfForm');
//					});
//			}
//			});// .ajax
//
//			if (desc1 != '' && saveok == 'true') {
//				// if description is really long cause the query string more
//				// than 2000 characters
//				$.ajax( {
//					url : 'questionnaireAjax.do',
//					type : 'GET',
//					dataType : 'html',
//					cache : false,
//					data : 'action=savebo1&newQuestionnaire.name=' + qname
//							+ '&newQuestionnaire.questionnaireId='
//							+ questionnaireId
//							+ '&newQuestionnaire.description=' + desc1
//							+ '&newQuestionnaire.isFinal=' + qisfinal,
//					async : false,
//					timeout : 1000,
//					error : function() {
//						// alert('Error loading XML document');
//					jumpToAnchor('topOfForm');
//					$('<span id="msg"/>').css("color", "red").html(
//							"Error when save Questionnaire").appendTo(
//							$("#headermsg"))
//					$('<br/>').appendTo($("#headermsg"));
//				},
//				success : function(xml) {
//					// sqlScripts="createnew";
//					$(xml).find('h3').each(function() {
//						// var item_text = $(this).text();
//							$('#newQuestionnaire\\.questionnaireId').attr(
//									"value", $(this).text().substring(9));
//						});
//				}
//				});// .ajax
//
//			}
//
//			for ( var k = 0; k < sqls.length; k++) {
//				sqlScripts = sqls[k];
//				sqlScripts = sqlScripts.replace(/#;#/g, ";;;");
//				qnaireid = $('#newQuestionnaire\\.questionnaireId').attr(
//						"value");
//				// alert(sqls.length+"-"+k)
//				// TODO : should consider to send newquestionnaire itself to
//				// save.
//				// need to resend newquestionnaire because it might be changed.
//				// also need to deal with 'description' which is varchar2(2000)
//				// we can not assume that is is not over 2000, so need to think
//				// about it.
//				$.ajax( {
//					url : 'questionnaireAjax.do',
//					type : 'GET',
//					dataType : 'html',
//					cache : false,
//					data : 'action=new&sqlScripts=' + sqlScripts
//							+ '&newQuestionnaire.questionnaireId=' + qnaireid,
//					async : false,
//					timeout : 1000,
//					error : function() {
//						alert('error when saving');
//					},
//					success : function(xml) {
//						sqlScripts = "createnew";
//						$(xml).find('h3').each(function() {
//							// var item_text = $(this).text();
//								// $('#newQuestionnaire\\.questionnaireId').attr("value",$(this).text().substring(9));
//							});
//					}
//				});// .ajax
//			} // end for
//			sqlidx = 0;
//		} // if-then-else
//		return false;
//	});

// }
	$("#nextGroup").hide();
	$("#prevGroup").hide();

