var Kc = Kc || {};
Kc.PropDev.DataOverride = Kc.PropDev.DataOverride || {};
(function(namespace, $) {
    namespace.init = function() {
        $('#PropDev-DataOverride-ColumnName').on('change', function(e) {ajaxSubmitForm('prepareDataOverride');})
        $('#PropDev-DataOverride-Dialog').on(kradVariables.EVENTS.UPDATE_CONTENT, function(e) {
            $('#PropDev-DataOverride-ColumnName').on('change', function(e) {ajaxSubmitForm('prepareDataOverride');});
        })
        };
})(Kc.PropDev.DataOverride, jQuery);
