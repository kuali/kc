delimiter /
TRUNCATE TABLE YNQ_EXPLANATION
/
INSERT INTO YNQ_EXPLANATION (QUESTION_ID,EXPLANATION_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ((SELECT QUESTION_ID FROM YNQ WHERE DESCRIPTION = 'NSF Beginning Investigator '),'E','admin',NOW(),UUID(),1)
/
INSERT INTO YNQ_EXPLANATION (QUESTION_ID,EXPLANATION_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ((SELECT QUESTION_ID FROM YNQ WHERE DESCRIPTION = 'NIH New Investigator'),'E','admin',NOW(),UUID(),1)
/
INSERT INTO YNQ_EXPLANATION (QUESTION_ID,EXPLANATION_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ((SELECT QUESTION_ID FROM YNQ WHERE DESCRIPTION = 'Is this proposal being submitted to other agencies?  If so, please list agency acronyms in explanation separated by commas.'),'E','admin',NOW(),UUID(),1)
/
INSERT INTO YNQ_EXPLANATION (QUESTION_ID,EXPLANATION_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ((SELECT QUESTION_ID FROM YNQ WHERE DESCRIPTION = 'Patents:  Answer "N/A" if there are no patents.  Answer "Yes" if all patents have been reported.  Answer "No" if there are patents that have not been reported.'),'E','admin',NOW(),UUID(),1)
/
INSERT INTO YNQ_EXPLANATION (QUESTION_ID,EXPLANATION_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ((SELECT QUESTION_ID FROM YNQ WHERE DESCRIPTION = 'Are human embryonic stem cells used?  If so, please click on ''More'' for instructions.'),'E','admin',NOW(),UUID(),1)
/
INSERT INTO YNQ_EXPLANATION (QUESTION_ID,EXPLANATION_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ((SELECT QUESTION_ID FROM YNQ WHERE DESCRIPTION = 'Is the principal investigator participating in this project as an employee of the U.S. Government? If so,  please click on ''More'' for instructions.'),'E','admin',NOW(),UUID(),1)
/
INSERT INTO YNQ_EXPLANATION (QUESTION_ID,EXPLANATION_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ((SELECT QUESTION_ID FROM YNQ WHERE DESCRIPTION = 'For Dept. of Education, Check "Yes" or "No" only if assistance is being requested under a program that gives special consideration to novice applicants. Otherwise, check "Not Applicable".'),'E','admin',NOW(),UUID(),1)
/
INSERT INTO YNQ_EXPLANATION (QUESTION_ID,EXPLANATION_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ((SELECT QUESTION_ID FROM YNQ WHERE DESCRIPTION = 'From this entity did you receive in the last 12 months, or do you expect to receive in the next 12 months, salary, director''s fee, consulting payments, honoraria, royalties or other payments for patents, copyrights or other intellectual property, or any other payments that will exceed $10,000 (aggregate for yourself, your spouse, and your dependent children)?'),'R','admin',NOW(),UUID(),1)
/
INSERT INTO YNQ_EXPLANATION (QUESTION_ID,EXPLANATION_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ((SELECT QUESTION_ID FROM YNQ WHERE DESCRIPTION = 'From this entity did you receive in the last 12 months, or do you expect to receive in the next 12 months, salary, director''s fee, consulting payments, honoraria, royalties or other payments for patents, copyrights or other intellectual property, or any other payments that will exceed $10,000 (aggregate for yourself, your spouse, and your dependent children)?'),'P','admin',NOW(),UUID(),1)
/
INSERT INTO YNQ_EXPLANATION (QUESTION_ID,EXPLANATION_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ((SELECT QUESTION_ID FROM YNQ WHERE DESCRIPTION = 'From this entity did you receive in the last 12 months, or do you expect to receive in the next 12 months, salary, director''s fee, consulting payments, honoraria, royalties or other payments for patents, copyrights or other intellectual property, or any other payments that will exceed $10,000 (aggregate for yourself, your spouse, and your dependent children)?'),'E','admin',NOW(),UUID(),1)
/
INSERT INTO YNQ_EXPLANATION (QUESTION_ID,EXPLANATION_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ((SELECT QUESTION_ID FROM YNQ WHERE DESCRIPTION = 'For this entity, do you have stock, stock options, or other interests of significant monetary value either in excess of $10,000 in fair market value or in excess of 5 percent of the ownership interest in a company? (In either case, aggregate for yourself, your spouse and your dependant children; in the case of stock in companies that are not publicly traded, use the most recent sales price)'),'P','admin',NOW(),UUID(),1)
/
INSERT INTO YNQ_EXPLANATION (QUESTION_ID,EXPLANATION_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ((SELECT QUESTION_ID FROM YNQ WHERE DESCRIPTION = 'For this entity, do you have stock, stock options, or other interests of significant monetary value either in excess of $10,000 in fair market value or in excess of 5 percent of the ownership interest in a company? (In either case, aggregate for yourself, your spouse and your dependant children; in the case of stock in companies that are not publicly traded, use the most recent sales price)'),'E','admin',NOW(),UUID(),1)
/
INSERT INTO YNQ_EXPLANATION (QUESTION_ID,EXPLANATION_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ((SELECT QUESTION_ID FROM YNQ WHERE DESCRIPTION = 'For any entity (company or public or non-proft organization) did you receive in last 12 months, or do you expect to receive in the next 12 months, salary director''s fees, consulting payments, honoraria, royalties or other payments for patents, copyrights or other intellectual property, or any other payments exceeding $10,000 (aggregate for yourself, your spouse, and your dependent children)?'),'R','admin',NOW(),UUID(),1)
/
INSERT INTO YNQ_EXPLANATION (QUESTION_ID,EXPLANATION_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ((SELECT QUESTION_ID FROM YNQ WHERE DESCRIPTION = 'For any entity (company or public or non-proft organization) did you receive in last 12 months, or do you expect to receive in the next 12 months, salary director''s fees, consulting payments, honoraria, royalties or other payments for patents, copyrights or other intellectual property, or any other payments exceeding $10,000 (aggregate for yourself, your spouse, and your dependent children)?'),'P','admin',NOW(),UUID(),1)
/
INSERT INTO YNQ_EXPLANATION (QUESTION_ID,EXPLANATION_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ((SELECT QUESTION_ID FROM YNQ WHERE DESCRIPTION = 'For any entity (company or public or non-proft organization) did you receive in last 12 months, or do you expect to receive in the next 12 months, salary director''s fees, consulting payments, honoraria, royalties or other payments for patents, copyrights or other intellectual property, or any other payments exceeding $10,000 (aggregate for yourself, your spouse, and your dependent children)?'),'E','admin',NOW(),UUID(),1)
/
INSERT INTO YNQ_EXPLANATION (QUESTION_ID,EXPLANATION_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ((SELECT QUESTION_ID FROM YNQ WHERE DESCRIPTION = 'Do you have stock, stock options, or other interests of significant monetary value either in excess of $10,000 in fair market value or in excess of 5 percent of the ownership interest in any company? (In either case, aggregate for yourself, your spouse and your dependent children; in the case of stock in companies that are not publicly traded, use most recent sales price recognized by the company)'),'P','admin',NOW(),UUID(),1)
/
INSERT INTO YNQ_EXPLANATION (QUESTION_ID,EXPLANATION_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ((SELECT QUESTION_ID FROM YNQ WHERE DESCRIPTION = 'Do you have stock, stock options, or other interests of significant monetary value either in excess of $10,000 in fair market value or in excess of 5 percent of the ownership interest in any company? (In either case, aggregate for yourself, your spouse and your dependent children; in the case of stock in companies that are not publicly traded, use most recent sales price recognized by the company)'),'E','admin',NOW(),UUID(),1)
/
INSERT INTO YNQ_EXPLANATION (QUESTION_ID,EXPLANATION_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ((SELECT QUESTION_ID FROM YNQ WHERE DESCRIPTION = 'For this entity, do you have stock, stock options, or other interests of significant monetary value in excess of $10,000 in fair market value (aggregate for yourself, your spouse and your dependent children)?'),'R','admin',NOW(),UUID(),1)
/
INSERT INTO YNQ_EXPLANATION (QUESTION_ID,EXPLANATION_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ((SELECT QUESTION_ID FROM YNQ WHERE DESCRIPTION = 'For this entity, do you have stock, stock options, or other interests of significant monetary value in excess of $10,000 in fair market value (aggregate for yourself, your spouse and your dependent children)?'),'P','admin',NOW(),UUID(),1)
/
INSERT INTO YNQ_EXPLANATION (QUESTION_ID,EXPLANATION_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ((SELECT QUESTION_ID FROM YNQ WHERE DESCRIPTION = 'For this entity, do you have stock, stock options, or other interests of significant monetary value in excess of $10,000 in fair market value (aggregate for yourself, your spouse and your dependent children)?'),'E','admin',NOW(),UUID(),1)
/
INSERT INTO YNQ_EXPLANATION (QUESTION_ID,EXPLANATION_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ((SELECT QUESTION_ID FROM YNQ WHERE DESCRIPTION = 'For this entity, do you have stock, stock options, equity or other interests in excess of 5 percent of the ownership interest (aggregate for yourself, your spouse and your dependent children)?'),'R','admin',NOW(),UUID(),1)
/
INSERT INTO YNQ_EXPLANATION (QUESTION_ID,EXPLANATION_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ((SELECT QUESTION_ID FROM YNQ WHERE DESCRIPTION = 'For this entity, do you have stock, stock options, equity or other interests in excess of 5 percent of the ownership interest (aggregate for yourself, your spouse and your dependent children)?'),'P','admin',NOW(),UUID(),1)
/
INSERT INTO YNQ_EXPLANATION (QUESTION_ID,EXPLANATION_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ((SELECT QUESTION_ID FROM YNQ WHERE DESCRIPTION = 'For this entity, do you have stock, stock options, equity or other interests in excess of 5 percent of the ownership interest (aggregate for yourself, your spouse and your dependent children)?'),'E','admin',NOW(),UUID(),1)
/
INSERT INTO YNQ_EXPLANATION (QUESTION_ID,EXPLANATION_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ((SELECT QUESTION_ID FROM YNQ WHERE DESCRIPTION = 'Do you have stock, stock options, or other interests of significant monetary value in excess of $10,000 in fair market value (aggregate for yourself, your spouse, and your dependent children)?'),'R','admin',NOW(),UUID(),1)
/
INSERT INTO YNQ_EXPLANATION (QUESTION_ID,EXPLANATION_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ((SELECT QUESTION_ID FROM YNQ WHERE DESCRIPTION = 'Do you have stock, stock options, or other interests of significant monetary value in excess of $10,000 in fair market value (aggregate for yourself, your spouse, and your dependent children)?'),'P','admin',NOW(),UUID(),1)
/
INSERT INTO YNQ_EXPLANATION (QUESTION_ID,EXPLANATION_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ((SELECT QUESTION_ID FROM YNQ WHERE DESCRIPTION = 'Do you have stock, stock options, or other interests of significant monetary value in excess of $10,000 in fair market value (aggregate for yourself, your spouse, and your dependent children)?'),'E','admin',NOW(),UUID(),1)
/
INSERT INTO YNQ_EXPLANATION (QUESTION_ID,EXPLANATION_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ((SELECT QUESTION_ID FROM YNQ WHERE DESCRIPTION = 'Do you have stock, stock options, equity or other interests in excess of 5 percent of the ownership interest in any company (aggregate for yourself, your spouse, and your dependent children)?'),'R','admin',NOW(),UUID(),1)
/
INSERT INTO YNQ_EXPLANATION (QUESTION_ID,EXPLANATION_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ((SELECT QUESTION_ID FROM YNQ WHERE DESCRIPTION = 'Do you have stock, stock options, equity or other interests in excess of 5 percent of the ownership interest in any company (aggregate for yourself, your spouse, and your dependent children)?'),'P','admin',NOW(),UUID(),1)
/
INSERT INTO YNQ_EXPLANATION (QUESTION_ID,EXPLANATION_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ((SELECT QUESTION_ID FROM YNQ WHERE DESCRIPTION = 'Do you have stock, stock options, equity or other interests in excess of 5 percent of the ownership interest in any company (aggregate for yourself, your spouse, and your dependent children)?'),'E','admin',NOW(),UUID(),1)
/
INSERT INTO YNQ_EXPLANATION (QUESTION_ID,EXPLANATION_TYPE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ((SELECT QUESTION_ID FROM YNQ WHERE DESCRIPTION = 'Can you certify that the information submitted within this application is true, complete and accurate to the best of your knowledge? That any false, fictitious, or fraudulent statements or claims may subject you, as the PI/Co-PI/Co-I to criminal, civil or administrative penalties? That you agree to accept responsibility for the scientific conduct of the project and to provide the required progress reports if an award is made as a result of this application.'),'E','admin',NOW(),UUID(),1)
/
delimiter ;
