var Kc = Kc || {};
Kc.Global = Kc.Global || {};
(function (namespace, $) {
    // set all modals to static behavior (clicking out does not close)
    $.fn.modal.Constructor.DEFAULTS.backdrop = "static";
})(Kc.Global, jQuery);