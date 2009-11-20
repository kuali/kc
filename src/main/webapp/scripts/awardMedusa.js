
    /*
	 * Load first level area of research when page is initially loaded
	 */
    function loadFirstLevel(){ 
      
      $.ajax({
        url: 'awardMedusaViewAjax.do',
        type: 'GET',
        dataType: 'html',
        cache: false,
        data:'medusaBean.medusaViewRadio=' + $("#medusaBean\\.medusaViewRadio").attr("value") + '&medusaBean.moduleName=' + $("#medusaBean\\.moduleName").attr("value") + '&medusaBean.moduleIdentifier=' + $("#medusaBean\\.moduleIdentifier").attr("value"),
        async:false,
        timeout: 1000,
        error:processError,
        success:processData 
       });  
      // return false;
    }  // generate
    
    
    
