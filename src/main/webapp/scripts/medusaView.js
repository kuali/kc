    var node;
    var i = 1;
    var j = 1;
    var removedNode=null;
    var cutNode;
    var sqlScripts = "";
    var ulTagId;
    var sqls = [];
    var sqlidx = 0;
    var deletedNodes="";
    var newNodes=";";
    var loadedidx=0;
      
    $(document).ready(function(){
      $.ajaxSettings.cache = false; 
      $("#medusaview").treeview({
                animated: "fast",
                collapsed: true,
                control: "#treecontrol"
              });

      $(document).ajaxStart(function(){
         $("#loading").show();
       });

      $(document).ajaxComplete(function(){
         $("#loading").hide();
       });
    });
         
    function processError(htmlObject, errorMessage, exception){
        alert('Error loading document. Please try again.');
     }
  
  function hasFormAlreadyBeenSubmitted() {
      // return false;
  }

  $('#medusaview .medusaNode a').click(function() {
	  var myDetails = getDetailsFromLink(this);
	  if (myDetails.is(':visible')) {
		  myDetails.slideUp(300);
	  } else {
		  //hide all other details
		  $('#medusaview .medusaDetails').slideUp(300);
		  //check to see if data has been loaded already
		  if (!myDetails.is('.medusaDetailsLoaded')) {
			  var moduleName = $(this).attr('name').split('-')[0];
			  var moduleId = $(this).attr('name').split('-')[1];
			  loadNodeFromModule(moduleName, moduleId, this);
		  } else {
			  myDetails.slideDown(300);
		  }
	  }
  });      
  
  function addDetails(context, data, textStatus, httpRequest) {
	  var myDetails = getDetailsFromLink(context);
	  myDetails.html(data).slideDown(300);
	  myDetails.addClass("medusaDetailsLoaded");
	  var showOpen =$(context).attr('name').split('-')[2];
	  if (showOpen == 0) {
		 myDetails.find('.medusaOpenLink').hide();  
	  }
  }
  
  function getDetailsFromLink(link) {
	  return $(link).parent().parent().find("> .medusaDetails");
  }
  
  function loadNodeFromModule(moduleName, moduleId, myContext){ 
      
      $.ajax({
        url: 'medusaAjax.do',
        type: 'GET',
        dataType: 'html',
        cache: false,
        data:'medusaBean.moduleName=' + moduleName + '&medusaBean.moduleIdentifier=' + moduleId,
        async:false,
        timeout: 15000,
        context: myContext,
        error:processError,
        success:function(data, textStatus) {
      	  return addDetails(myContext, data, textStatus);
        }
       });  
    }   
