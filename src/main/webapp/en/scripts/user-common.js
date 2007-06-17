// WorkflowUserEntry.jsp
function workflowHelpPop(helpKey){
    window.open("../Help.do?methodToCall=getHelpEntry&helpKey=" + helpKey, "_blank", "width=640, height=365");
}