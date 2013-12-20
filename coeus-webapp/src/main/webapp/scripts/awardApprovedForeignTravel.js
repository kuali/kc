// This function is called when a traveler is picked from the look-up, but then
// the user makes a traveler selection from the known travelers drop-down list.
// This will help reduce confusion from the look-up traveler name being shown at
// the same time a different name is shown selected from the list  

function clearApprovedForeignTravelerTravelerName() {
    document.getElementById('ApprovedForeignTravel_TravelerName').innerHTML='';
}