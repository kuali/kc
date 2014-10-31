var Kc = Kc || {};
Kc.PessimisticLock = Kc.PessimisticLock || {};
(function(namespace, $) {

    namespace.clearLock = function(id) {
        $.ajax('../kc-sys-krad/sys/pessimisticLock/'+ id, {type: 'DELETE'});
    };
})(Kc.PessimisticLock, jQuery);
