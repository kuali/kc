ALTER TABLE YNQ MODIFY (DESCRIPTION VARCHAR2(500));
UPDATE YNQ SET DESCRIPTION='Can you certify that the information submitted within this application is true, complete and accurate to the best of your knowledge? That any false, fictitious, or fraudulent statements or claims may subject you, as the PI/Co-PI/Co-I to criminal, civil or administrative penalties? That you agree to accept responsibility for the scientific conduct of the project and to provide the required progress reports if an award is made as a result of this application.' 
WHERE QUESTION_ID='P1';
COMMIT;