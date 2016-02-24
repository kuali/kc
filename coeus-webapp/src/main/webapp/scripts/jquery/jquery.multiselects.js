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
/**
* Multiple Selects - jQuery plugin for converting a multiple <select> into two, adding the ability to move items between the boxes.
* http://code.google.com/p/jqmultiselects/
*
* Copyright (c) 2007 Rob Desbois
*               2011 Yury Samsonov
* Dual licensed under the MIT and GPL licenses:
* http://www.opensource.org/licenses/mit-license.php
* http://www.gnu.org/licenses/gpl.html
*
* Version: 0.3
*
* CHANGELOG
*
* 0.3 (Yury Samsonov <yury.samsonov@gmail.com>)
*
* INCOMPATIBLE WITH PREVIOUS VERSION!!!
*
* [+] Sorting feature added.
* [+] "Move all" triggers available
* [+] One call for full element - both source and destination
* [-] Old options syntax unsupported.
* [ ] All tabs replaced with spaces.
* [ ] Unix-style linebreaks (\n)
*
*  There's three new options:
*  - autoSort: true|false - enable sorting feature, enabled by default
*  - sortType: "key"|"value"|function, default - "value" (visible text in select)
*  - sortDesc: true|false - sort in reverse order, disabled by default
*
* You can use your own sorting function, recieving 2 <option> as argument (not jQuery-wrapped).
* Example:
*     ...
*     sortType: function (a, b) { return a.value - b.value; },
*     ...
*
* 0.2
* <option> elements can be automatically selected upon parent form submission
* plugin options now passed as an array
* all element identifiers now taken as jQuery selectors instead
* added beforeMove and afterMove callback functions
*
* 0.1
* initial release
*/

(function( $ ){
    $.fn.multiSelect = function(dest, options) {
        options = $.extend({
            keepSelected: false,       // true => keep moved items selected
            autoSubmit: true,          // true => select all child <option>s on parent form submit (if any)
            // buttons
            button_select: null,       // selector of elements whose 'click' should move selected options
            button_select_all: null,   // selector of elements whose 'click' should remove selected options from dest
            button_deselect: null,     // selector of elements whose 'click' should move all options to dest
            button_deselect_all: null, // selector of elements whose 'click' should remove all options from dest
            // sorting options
            autoSort: true,            // true => sort child <option>s elements after move
            sortType: "value",         // "value" => sort by visible text,
                                       // "key" => by <option>s "value" attr
                                       // function(a,b) => your sorting function
            sortDesc: false,           // true => sort in reverse order
            // callbacks
            beforeMove: null,          // before move callback function, if returns false, no item will be moved
            afterMove: null            // after move callback
        }, options);

        // for closures
        var $source = this;
        var $dest = $(dest);

        // make form submission select child <option>s
        if (options.autoSubmit)
            this.parents("form").submit(function() { selectChildOptions($dest); });

        // sort options in both <selects>
        var sortOptions = function(elem, sortType, desc) {
            var order = desc ? -1 : 1;
            if (typeof sortType == "function") {
                var sort = sortType;
            } else {
                if (sortType == "key") {
                    var sort = function(a, b) { return order * ((b.value < a.value) - (a.value < b.value)); }
                } else {
                    var sort = function(a, b) { return order * (($(b).text() < $(a).text()) - ($(a).text() < $(b).text())); }
                }
            }

            var options = $.makeArray($('option', elem).detach()).sort(sort);
            $(elem).append(options);
        }

        // wrapper for sort function
        var sortFunction = function(element) {
            if (!options.autoSort) {
                return;
            }

            sortOptions(element, options.sortType, options.sortDesc);
        }

        // initial sort
        sortFunction($source);
        sortFunction($dest);

        // wrapper for move function
        var moveFunction = function(from, to, action) {
            moveOptions(from, to, action, options.beforeMove, options.afterMove, sortFunction);
        };
        var moveAll = function (from, to) {
            if (options.beforeMove && !options.beforeMove(from, dest, 'all'))
                return;

            $('option', from).attr('selected', 'selected');
            moveFunction(from, to);

            sortFunction(dest);
            options.afterMove && options.afterMove(from, dest, 'all');
        };

        // move elements from source to dest
        $source.dblclick(function() { moveFunction($source, $dest, 'select'); });
        if(options.button_select) $(options.button_select).click(function() { moveFunction($source, $dest, 'select'); });
        if(options.button_select_all) $(options.button_select_all).click(function() { moveAll($source, $dest); });

        // move elements from dest to source
        $dest.dblclick(function() { moveFunction($dest, $source, 'deselect'); });
        if(options.button_deselect) $(options.button_deselect).click(function() { moveFunction($dest, $source, 'deselect'); });
        if(options.button_deselect_all) $(options.button_deselect_all).click(function() { moveAll($dest, $source); });

        return this;

        // moves the options between <select>
        // 'action' param can be 'select', 'deselect' or 'all'
        function moveOptions(from, to, action, beforeMove, afterMove, sortFunction) {
            if (beforeMove && !beforeMove(from, to, action))
                return;

            $("option:selected", from).each(function() {
                $(this).appendTo(to);
            });

            sortFunction(to);
            afterMove && afterMove(from, to, action);
        }

        // selects all child options
        function selectChildOptions($dest) {
            $dest.children("option").each(function() {
                this.selected = true;
            });
        }
    };
})(jQuery);
