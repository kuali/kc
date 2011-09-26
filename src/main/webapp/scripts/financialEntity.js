
    $j(document).ready(function()     {
      var idx = $j("#editIndex").attr("value");
      if (idx != -1) {
       $j("#A"+idx).click(); 
      $j("#financialEntityHelper\\.activeFinancialEntities\\["+idx+"\\]\\.sponsorCode").focus(function() {
          displayAlertMessage('messageBox'+idx, 'The entity address fields will be overriden when a valid sponsor code is entered');
          //return false;
      });
      }
      
      
      $j("#financialEntityHelper\\.newPersonFinancialEntity\\.sponsorCode").focus(function() {
          displayAlertMessage('messageBox', 'The entity address fields will be overriden when a valid sponsor code is entered');
          //return false;
      });
      
      $j('input[name^="methodToCall.performLookup.(!!org.kuali.kra.bo.Sponsor!!)"]').click(function() {
        if(!confirm('The entity address fields will be overriden when a valid sponsor is selected.  Do you want to continue ?')) {
            return false;
        }
      });
      
      
      
      // there are 2 different rows, one is the entity name/sponsor .. row, and the other row is
      // this history header row of the entity listed above.  However, the sorter can't distinguish them.
      // this widget is to reloacate the 'history row' back immediately following its belonging entity.
$j.tablesorter.addWidget({ 
    // give the widget a id 
    id: "relocaterow", 
    // format is called when the on init and when a sorting has finished 
    format: function(table) { 
         
        // loop all tr elements and insert a copy of the "headers"     
        for(var i=0; i < table.tBodies[0].rows.length; i++) { 
            if (table.tBodies[0].rows[i].cells.length > 1) {
                var idx = $j(table.tBodies[0].rows[i]).attr("id").substring(6); // "hdrrowxx"
                var tableRow = $j("#G"+idx);
                $j(tableRow).insertAfter(table.tBodies[0].rows[i]);
             }
        } 
    } 
}); 
 
 
 // there is no proper parser for data/time field.  'shortDate is on for date'
 // add this parser for 'last update' column
 $j.tablesorter.addParser({
        id: "datetime",
        is: function(s) {
            return s.match(new RegExp(/^([0]\d|[1][0-2])\/([0-2]\d|[3][0-1])\/([2][01]|[1][6-9])\d{2}(\s([0]\d|[1][0-2])(\:[0-5]\d){1,2})*\s*([aApP][mM]{0,2})?$/));
        },
        format: function(s) {
            return $j.tablesorter.formatFloat(new Date(s).getTime());
        },
        type: "numeric"
    });


// call the tablesorter plugin and assign widgets with id "zebra" (Default widget in the core) and the newly created "repeatHeaders" 
      
      
      $j("#activeEntities-table").tablesorter(
       // weird ? "if widgets is listed after header, then tablesorter can't find the widgets
      
       {widgets: ['zebra','relocaterow']},
           {         
        // pass the headers argument and assing a object 
           headers: {             // assign the first column (we start counting zero)             
               0: {                 // disable it by setting the property sorter to false                 
                  sorter: false             },      
               3: {sorter:'datetime' },   
               4: {                 // disable it by setting the property sorter to false                 
                   sorter: false             }
                     },
                      
          
          }
          ); 
      
      $j("#inActiveEntities-table").tablesorter(
       // weird ? "if widgets is listed after header, then tablesorter can't find the widgets
      
       {widgets: ['zebra','relocaterow']},
           {         
        // pass the headers argument and assing a object 
           headers: {             // assign the first column (we start counting zero)             
               0: {                 // disable it by setting the property sorter to false                 
                  sorter: false             },      
               3: {sorter:'datetime' },   
               4: {                 // disable it by setting the property sorter to false                 
                   sorter: false             }
                     },
                      
          
          }
          ); 
      
      
    } );  // end document.ready

    function displayAlertMessage(messagedivid, message) {
        var timeOut = 5
        $j('#'+messagedivid).text(message).fadeIn().css({"display":"block","color":"red"});
        setTimeout(function() {
            $j('#'+messagedivid).fadeOut().css("display", "none");
         }, timeOut * 1000);
    }
