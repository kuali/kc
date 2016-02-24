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
// This function is called when a traveler is picked from the look-up, but then
// the user makes a traveler selection from the known travelers drop-down list.
// This will help reduce confusion from the look-up traveler name being shown at
// the same time a different name is shown selected from the list  

function clearApprovedForeignTravelerTravelerName() {
    document.getElementById('ApprovedForeignTravel_TravelerName').innerHTML='';
}
