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
var Kc = Kc || {};
Kc.Medusa = Kc.Medusa || {};
(function(namespace, $) {
	namespace.minimizeMedusaTrees = function() {
		$('section.medusa div.medusa-node').prev().off('click.medusa');
		$('section.medusa div.medusa-node').prev().on('click.medusa', function(){

            $(this).siblings('.medusa-node').toggle();

            //18px is the width of the jstree icon
            if (!$(this).siblings('.medusa-node').is(":hidden")) {
                $(this).siblings('.medusa-node').css('margin-left','18px');
            }
            return true;
        });
        $('section.medusa div.medusa-node').hide();
        $('section.medusa div.jstree').jstree('open_all');

	}

    namespace.externalMedusaLoader = function() {
        //minimizeMedusaTrees gets called before the jstree functionality.  set a timer to wait for it to be added.
        var timer = undefined;

        timer = setInterval(function() {
            if ($('section.medusa div.jstree')){
                clearInterval(timer);
                namespace.minimizeMedusaTrees();
            }
        },10);
    }
})(Kc.Medusa, jQuery);
