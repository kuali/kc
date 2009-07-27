var i = 1;
var ucount = 1;
var removedNode;
var cutNode;
var copyNode;
var sqlScripts = "edit";
var maxCopyNodeIdx = 0;
var isCopy = 'false';
var jsContextPath = "${pageContext.request.contextPath}";
var sqls = [];
var sqlidx = 0;
var groupid = 0;
var curgroup = 0;
var loadcount = 0;
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

//$("#save").click(
//		function() {
//			alert("save" + sqlScripts);
//			var qname = $('#newQuestionnaire\\.name').attr("value");
//			var qdescription = $('#newQuestionnaire\\.description').attr(
//					"value");
//			var qisfinal = $('#newQuestionnaire\\.isFinal').attr("checked");
//			var questionnaireId = $('#newQuestionnaire\\.questionnaireId')
//					.attr("value");
//			// if (qisfinal == 'on') {
//			// qisfinal = true;
//			// } else {
//			// qisfinal = false;
//			// }
//			// alert ("save"+sqlScripts);
//
//			if (qname == '') {
//				alert("Questionnaire Name is required");
//			} else if (qdescription == '') {
//				alert("Questionnaire Description is required");
//			} else {
//				// TODO : FF seems to have trouble with "#;#"
//				// alert ("save "+sqlScripts+"-"+sqlScripts.indexOf("#;#"));
//				if (sqlScripts.indexOf("#;#") > 1) {
//					sqls[sqlidx++] = sqlScripts;
//				}
//
//				// TODO : problem with '&' in string will in name or description
//				qname = qname.replace(/&/g, ";amp");
//				qdescription = qdescription.replace(/&/g, ";amp");
//
//				var desc1 = "";
//				var saveok = 'true';
//				if (qdescription.length > 1800) {
//					// TODO : 1800 should be ok
//					desc1 = qdescription.substring(1800);
//					qdescription = qdescription.substring(0, 1800);
//				}
//
//				$.ajax( {
//					url : 'questionnaireAjax.do',
//					type : 'GET',
//					dataType : 'html',
//					cache : false,
//					data : 'action=savebo&newQuestionnaire.name=' + qname
//							+ '&newQuestionnaire.questionnaireId='
//							+ questionnaireId
//							+ '&newQuestionnaire.description=' + qdescription
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
//					saveok = 'false';
//				},
//				success : function(xml) {
//					// sqlScripts="createnew";
//					$(xml).find('h3').each(function() {
//						// var item_text = $(this).text();
//							$('#newQuestionnaire\\.questionnaireId').attr(
//									"value", $(this).text().substring(9));
//							$('<span id="msg"/>').css("color", "black").html(
//									"Questionnaire saved successfully")
//									.appendTo($("#headermsg"));
//							$('<br/>').appendTo($("#headermsg"));
//							jumpToAnchor('topOfForm');
//						});
//				}
//				});// .ajax
//
//				if (desc1 != '' && saveok == 'true') {
//					// if description is really long cause the query string more
//					// than 2000 characters
//					$.ajax( {
//						url : 'questionnaireAjax.do',
//						type : 'GET',
//						dataType : 'html',
//						cache : false,
//						data : 'action=savebo1&newQuestionnaire.name=' + qname
//								+ '&newQuestionnaire.questionnaireId='
//								+ questionnaireId
//								+ '&newQuestionnaire.description=' + desc1
//								+ '&newQuestionnaire.isFinal=' + qisfinal,
//						async : false,
//						timeout : 1000,
//						error : function() {
//							// alert('Error loading XML document');
//						jumpToAnchor('topOfForm');
//						$('<span id="msg"/>').css("color", "red").html(
//								"Error when save Questionnaire").appendTo(
//								$("#headermsg"))
//						$('<br/>').appendTo($("#headermsg"));
//					},
//					success : function(xml) {
//						// sqlScripts="createnew";
//						$(xml).find('h3').each(function() {
//							// var item_text = $(this).text();
//								$('#newQuestionnaire\\.questionnaireId').attr(
//										"value", $(this).text().substring(9));
//							});
//					}
//					});// .ajax
//
//				}
//
//				// alert("save "+sqls.length);
//				for ( var k = 0; k < sqls.length; k++) {
//					sqlScripts = sqls[k];
//					sqlScripts = sqlScripts.replace(/#;#/g, ";;;");
//					$.ajax( {
//						url : 'questionnaireAjax.do',
//						type : 'GET',
//						dataType : 'html',
//						cache : false,
//						data : 'action=edit&sqlScripts=' + sqlScripts
//								+ '&newQuestionnaire.questionnaireId='
//								+ questionnaireId,
//						async : false,
//						timeout : 1000,
//						error : function() {
//							alert('Error loading XML document');
//						},
//						success : function(xml) {
//							sqlScripts = "edit";
//							$(xml).find('h3').each(function() {
//								var item_text = $(this).text();
//							});
//						}
//					}); // end ajax
//				} // end for
//				sqlidx = 0;
//			} // eid if-then-else
//			return false;
//		});
// }


// load questions & usages
// TODO : if edit data has '"', then it getElementById will only reach the
// character before '"'
// Question 1067 description has '"', and this is also probably why
// it only saved up 1067. total selected is 54, but only saved to q 43.
// $('<span id="msg"/>').css("color","red").html("loading... please
// wait").appendTo($("#loading"));
// $("#loading").show(); // not showing up ?
// $("#loading").show(); // if there is an alert
jumpToAnchor('topOfForm');
var editdata = document.getElementById("editData").value;
// alert(editdata);
var dataarray = editdata.split("#;#");
var firstidx = -1;
var idxArray = new Array(dataarray.length);
// alert (retdata+"-"+dataarray[0]);
var questions = dataarray[0].split("#q#");
// qqid/qid/seq/desc/qtypeid/qnum/cond/value
// var parentnum = 0;
var parentidx = 0;

loadQuestion();
// if (questions.length > loadcount) {
// loadIntervalId = setInterval ( "loadQuestion()", 5000 );
// }

// TODO : only the first question is expanded
$("#listcontrol" + firstidx).click();
// $("#listcontrol"+firstidx).click();

// TODO : test grouping questions
// alert("groupidx = "+groupid+$(".group1:eq(0)").attr("id"));
$(".group1").hide();
$(".group2").hide();
jumpToAnchor('topOfForm');


// load usage
// quid/modulecode/label

if (dataarray.length > 1) {
	var usages = dataarray[1].split("#u#");
	for ( var k = 0; k < usages.length; k++) {
		field = usages[k].split("#f#");
		trtmp = $('<tr/>').attr("id", "usage" + ucount);
		thtmp = $('<th class="infoline"/>').html(ucount);
		thtmp.appendTo(trtmp);
		// tdtmp = $('<td align="left" valign="middle">').html(field[1]);
		var tdtmp = $('<td align="left" valign="middle">').html(
				moduleCodes[field[1]]);
		var modulecode = $('<input type="hidden"/>').attr("value", field[1]);
		modulecode.appendTo(tdtmp);
		tdtmp.appendTo(trtmp);
		tdtmp = $('<td align="left" valign="middle">').html(field[2]);
		tdtmp.appendTo(trtmp);
		tdtmp = $('<td align="left" valign="middle">').html("1.00");
		tdtmp.appendTo(trtmp);
		inputtmp = $(
				'<input type="image" id="deleteUsage" name="deleteUsage" src="static/images/tinybutton-delete1.gif" class="tinybutton">')
				.attr("id", "deleteUsage" + ucount).click(
						function() {
							addSqlScripts("delete U;"
									+ $(this).parents('tr:eq(0)').children(
											'td:eq(0)').children('input:eq(0)')
											.attr("value"));
							// alert(sqlScripts);
							curnode = $(this).parents('tr:eq(0)');
							// alert("size "+curnode.next().size());
							while (curnode.next().size() > 0) {
								curnode = curnode.next();
								// alert(Number(curnode.children('th:eq(0)').html())-1);
								curnode.children('th:eq(0)').html(
										Number(curnode.children('th:eq(0)')
												.html()) - 1)
								// curnode=curnode.next();
							}

							$(this).parents('tr:eq(0)').remove();
							return false;
						});
		tdtmp = $('<td align="left" valign="middle">');
		$('<div align="center">').html(inputtmp).appendTo(tdtmp);
		tdtmp.appendTo(trtmp);
		ucount++;
		trtmp.appendTo($("#usage-table"));

	}
	// end for load usages
} // check dataarray.length

function loadQuestion() {
	var qlen = questions.length;
	var rootidx;
	var pnum0 = 0;
	var nodecount = 0;
	for ( var k = 0; k < qlen; k++) {
		var curq = questions[k];
		// alert("parent 0 "+k+"-"+curq+pnum0+"-"+nodecount+"-"+qlen);
		if (curq.indexOf("parent-") == 0) {
			// parentnum = curq.substring(7);
			// for (var l = 1 ; l <= k+1; l++) {
			// if ($("#qnum"+l).attr("value")) {
			// if ($("#qnum"+l).attr("value") == parentnum) {
			// parentidx = l;
			// }
			// }
			// }
		} else {

			var field = curq.split("#f#");
			var parentnum = field[8];
			i++;
			var parenturl;
			var ischild = 'false';
			if (parentnum == 0) {
				// if ((pnum0 == 20 && loadcount != 0) || (loadcount == 0 &&
				// pnum0 == 40)) {
				// //alert("break");
				// break;
				// }
				parenturl = $('#example');
				pnum0++;
				rootidx = i;
				// alert("parent 0 "+pnum0+"-"+nodecount);

			} else {
				if (field[2] == 1) { // the first children
					parentidx = i - 1;
				} else {
					for ( var l = rootidx; l <= i - 1; l++) {
						if ($("#qnum" + l).attr("value")) {
							if ($("#qnum" + l).attr("value") == parentnum) {
								parentidx = l;
							}
						}
					}
				}
				parenturl = $("#qnaireid" + parentidx).children('ul:eq(0)');
				ischild = 'true';
			}
			nodecount++;
			var listitem = getQuestionNew(field[3], field[4], "V1.01", ischild);
			var ultag = $('<ul></ul>');
			ultag.appendTo(listitem);
			var idx = listitem.attr("id").substring(8);
			// listitem.appendTo('ul#example');
			// last one no 'move dn'
			if (firstidx == -1) {
				firstidx = idx;
			}

			listitem.appendTo($(parenturl));
			// also need this to show 'folder' icon
			$('#example').treeview( {
				add : listitem
			});

			// TODO : test idea of display page by page with hide().
			if (parentnum == 0) {
				addToGroup(listitem);
				if (groupid > 0) {
					$(listitem).hide();
				}
			}

			if ($(listitem).parents('ul:eq(0)').children('li').size() == 1) {
				$("#moveup" + idx).hide();
				$("#movedn" + idx).hide();
			} else {
				$("#movedn" + idx).hide();
				if (listitem.prev().size() > 0) {
					$("#movedn" + listitem.prev().attr("id").substring(8))
							.show();
				}
			}

			if (parentnum > 0 && field[6] != 'null') {
				// alert ("add req for parent
				// "+$("#addrequirement"+i).parents('tr:eq(0)').size());
				var newResponse = getRequirementDeleteRow(
						responseArray[field[6]], field[7]);
				newResponse.appendTo($("#addrequirement" + i).parents(
						'div:eq(0)').children('table:eq(0)').children('tbody'));
				$("#cond" + i).attr("value", field[6]);
				$("#condvalue" + i).attr("value", field[7]);
				$("#addrequirement" + i).parents('tr:eq(0)').remove();
			}
			// TODO : set up for insert
			/*
			 * questionnairenumber from #questionnairenumber questionId from
			 * #qid sequenceNumber from
			 * $(this).parents('li:eq(0)').siblings().size() ?
			 */

			$("#qnum" + idx).attr("value", field[5]);
			$("#qid" + idx).attr("value", field[1]);
			$("#qseq" + idx).attr("value", field[2]);
			// set up qdesc & qtypeid
			$("#qdesc" + idx).attr("value", field[3]);
			$("#qtypeid" + idx).attr("value", field[4]);

		} // end if-then-else
	} // end for to set up questions

	if (groupid == 0) {
		$("#nextGroup").hide();
		$("#prevGroup").hide();
	} 	
	if (curgroup == 0) {
		$("#prevGroup").hide();
	} 	

} // loadquestion

