/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

if (!window.location.origin) {
  window.location.origin = window.location.protocol + "//" + window.location.hostname + (window.location.port ? ':' + window.location.port: '');
}

var Kc = Kc || {};
Kc.Global = Kc.Global || {};
(function (namespace, $) {
	namespace.ensureOneHeaderAction = 'Kc.Global.EnsureOneHeaderMessageAction';
	namespace.receiveHeaderMessage = function(event) {
		var origin = event.origin || event.originalEvent.origin;
		if (origin === window.location.origin && event.data.action === namespace.ensureOneHeaderAction) {
			window.location = event.data.location;
		}
	};
	namespace.onHeaderLoad = function() {
		window.addEventListener("message", Kc.Global.receiveHeaderMessage, false);
		if (window.parent !== window) {
			window.parent.postMessage({action : Kc.Global.ensureOneHeaderAction, location : window.location.href}, '*');
			//posting to the parent's parent due to the extra XDM iframe in the KNS portal
			if (window.parent.parent && window.parent.parent !== window) {
				window.parent.parent.postMessage({action : Kc.Global.ensureOneHeaderAction, location : window.location.href}, '*');
			}
		}
	};
	namespace.onKnsHeaderLoad = function() {
		window.addEventListener("message", Kc.Global.receiveHeaderMessage, false);
	};
})(Kc.Global, jQuery);
