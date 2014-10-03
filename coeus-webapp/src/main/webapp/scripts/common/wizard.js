var Kc = Kc || {};
Kc.Wizard = Kc.Wizard || {};
(function(namespace, $) {

    namespace.returnToFirstResultsPage = function() {
        $('.modal-body').find('a.first').click();
    };
})(Kc.Wizard, jQuery);
