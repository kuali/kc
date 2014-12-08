var Kc = Kc || {};
Kc.Global = Kc.Global || {};
(function (namespace, $) {
    // set all modals to static behavior (clicking out does not close)
    $.fn.modal.Constructor.DEFAULTS.backdrop = "static";

    $(document).on("ready", function(){
        // date conversion for date fields to full leading 0 - for days and months and to full year dates
        $(document).on("blur", ".uif-dateControl", function(){
            var dateFormat = $.datepicker._defaults.dateFormat;
            var date = $(this).val();
            if (!date) {
                return;
            }

            date = date.replace(/-/g, "/");

            if (date && (date.match(/\//g) || []).length === 2) {

                // find the expected position and value of year in the string based on date format
                var year;
                if (dateFormat.indexOf("y") === 0) {
                    year = date.substr(0, date.indexOf("/"));
                }
                else {
                    year = date.substr(date.lastIndexOf("/") + 1, date.length - 1);
                }

                // when year is length of 2 append the first 2 numbers of the current full year (ie 14 -> 2014)
                if (year.length === 2) {
                    var currentDate = new Date();
                    year = currentDate.getFullYear().toString().substr(0,2) + year;
                }

                var dateObj = new Date(date);
                if (isNaN(dateObj.valueOf())) {
                    // not valid abandon conversion
                    return;
                }

                dateObj.setFullYear(year);

                var formattedDate = $.datepicker.formatDate(dateFormat, dateObj);
                $(this).val(formattedDate);
            }
        });
    });
})(Kc.Global, jQuery);