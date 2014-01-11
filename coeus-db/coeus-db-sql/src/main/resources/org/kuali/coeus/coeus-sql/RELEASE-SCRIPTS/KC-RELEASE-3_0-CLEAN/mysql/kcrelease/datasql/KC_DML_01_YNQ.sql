delimiter /
TRUNCATE TABLE YNQ
/
INSERT INTO YNQ (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,GROUP_NAME,STATUS,EFFECTIVE_DATE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('11','Is there space change on this proposal','P','Proposal Questions','A',STR_TO_DATE('20000101','%Y%m%d'),2,'Y',null,'admin',NOW(),UUID(),1)
/
INSERT INTO YNQ (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,GROUP_NAME,STATUS,EFFECTIVE_DATE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('12','NSF Beginning Investigator ','P','Proposal Questions','A',STR_TO_DATE('20000101','%Y%m%d'),2,null,null,'admin',NOW(),UUID(),1)
/
INSERT INTO YNQ (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,GROUP_NAME,STATUS,EFFECTIVE_DATE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('13','NIH New Investigator','P','Proposal Questions','A',STR_TO_DATE('20000101','%Y%m%d'),2,null,null,'admin',NOW(),UUID(),1)
/
INSERT INTO YNQ (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,GROUP_NAME,STATUS,EFFECTIVE_DATE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('14','This is an NSF Small Grant for Exploratory Research','P','Proposal Questions','A',STR_TO_DATE('20000101','%Y%m%d'),2,null,null,'admin',NOW(),UUID(),1)
/
INSERT INTO YNQ (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,GROUP_NAME,STATUS,EFFECTIVE_DATE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('15','Is this proposal being submitted to other agencies?  If so, please list agency acronyms in explanation separated by commas.','P','Proposal Questions','A',STR_TO_DATE('20000101','%Y%m%d'),2,'Y',null,'admin',NOW(),UUID(),1)
/
INSERT INTO YNQ (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,GROUP_NAME,STATUS,EFFECTIVE_DATE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('16','Patents:  Answer "N/A" if there are no patents.  Answer "Yes" if all patents have been reported.  Answer "No" if there are patents that have not been reported.','P','Proposal Questions','A',STR_TO_DATE('20000101','%Y%m%d'),3,null,null,'admin',NOW(),UUID(),1)
/
INSERT INTO YNQ (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,GROUP_NAME,STATUS,EFFECTIVE_DATE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('17','Is this a Phase III Clinical Trial as defined by NIH?','P','Proposal Questions','A',STR_TO_DATE('20050519','%Y%m%d'),2,null,null,'admin',NOW(),UUID(),1)
/
INSERT INTO YNQ (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,GROUP_NAME,STATUS,EFFECTIVE_DATE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('18','Are human embryonic stem cells used?  If so, please click on ''More'' for instructions.','P','Proposal Questions','A',STR_TO_DATE('20000101','%Y%m%d'),2,'Y',null,'admin',NOW(),UUID(),1)
/
INSERT INTO YNQ (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,GROUP_NAME,STATUS,EFFECTIVE_DATE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('19','Are you currently serving, or have you previously served, as a PI, co-PI or Program Director on any Federally funded project?','P','Proposal Questions','A',STR_TO_DATE('20060216','%Y%m%d'),2,null,null,'admin',NOW(),UUID(),1)
/
INSERT INTO YNQ (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,GROUP_NAME,STATUS,EFFECTIVE_DATE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('20','Does the proposal contain high resolution graphics or other graphics where exact color representation is required for proper interpretation (GPG, Chapter I.G.1)?','P','Proposal Questions','A',STR_TO_DATE('20060216','%Y%m%d'),2,null,null,'admin',NOW(),UUID(),1)
/
INSERT INTO YNQ (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,GROUP_NAME,STATUS,EFFECTIVE_DATE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('21','Is the proposal a full application related to submission of a preliminary application?','P','Proposal Questions','A',STR_TO_DATE('20060216','%Y%m%d'),2,null,null,'admin',NOW(),UUID(),1)
/
INSERT INTO YNQ (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,GROUP_NAME,STATUS,EFFECTIVE_DATE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('22','Is the principal investigator changing with this application?  If so, please enter last and first names of previous investigator in explanation. Please enter LAST NAME, FIRST NAME.','P','Proposal Questions','A',STR_TO_DATE('20060216','%Y%m%d'),2,'Y',null,'admin',NOW(),UUID(),1)
/
INSERT INTO YNQ (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,GROUP_NAME,STATUS,EFFECTIVE_DATE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('23','Has the grantee institution changed with this application? If so, enter name of previous institution in explanation field.','P','Proposal Questions','A',STR_TO_DATE('20060216','%Y%m%d'),2,'Y',null,'admin',NOW(),UUID(),1)
/
INSERT INTO YNQ (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,GROUP_NAME,STATUS,EFFECTIVE_DATE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('24','Is the principal investigator participating in this project as an employee of the U.S. Government? If so,  please click on ''More'' for instructions.','P','Proposal Questions','A',STR_TO_DATE('20061006','%Y%m%d'),2,'Y',null,'admin',NOW(),UUID(),1)
/
INSERT INTO YNQ (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,GROUP_NAME,STATUS,EFFECTIVE_DATE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('25','Is the principal investigator participating in this project as an employee of a foreign organization?','P','Proposal Questions','A',STR_TO_DATE('20061006','%Y%m%d'),2,null,null,'admin',NOW(),UUID(),1)
/
INSERT INTO YNQ (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,GROUP_NAME,STATUS,EFFECTIVE_DATE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('26','For Dept. of Education, Check "Yes" or "No" only if assistance is being requested under a program that gives special consideration to novice applicants. Otherwise, check "Not Applicable".','P','Proposal Questions','A',STR_TO_DATE('20070102','%Y%m%d'),3,'Y',null,'admin',NOW(),UUID(),1)
/
INSERT INTO YNQ (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,GROUP_NAME,STATUS,EFFECTIVE_DATE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('27','If this project has an actual or potential impact on the environment, has an exemption been authorized or an environmental assessment (EA) or environmental impact statement (EIS) been performed?  If yes, please explain in comments.','P','Proposal Questions','A',STR_TO_DATE('20070102','%Y%m%d'),3,'YN',null,'admin',NOW(),UUID(),1)
/
INSERT INTO YNQ (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,GROUP_NAME,STATUS,EFFECTIVE_DATE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('28','Is this a clinical trial?','P','Proposal Questions','A',STR_TO_DATE('20090220','%Y%m%d'),2,null,null,'admin',NOW(),UUID(),1)
/
INSERT INTO YNQ (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,GROUP_NAME,STATUS,EFFECTIVE_DATE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('29','Is the Government permitted to disclose the project title, and the contact information of the signing official, to organizations that may be interested in contacting you for further information ?','P','Proposal Questions','A',STR_TO_DATE('20090220','%Y%m%d'),2,null,null,'admin',NOW(),UUID(),1)
/
INSERT INTO YNQ (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,GROUP_NAME,STATUS,EFFECTIVE_DATE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('30','Is this organization being created in order to submit applications as an individual rather than as a company, state, local or tribal government, academia, or other type of organization?','O',null,'A',STR_TO_DATE('20090220','%Y%m%d'),2,null,null,'admin',NOW(),UUID(),1)
/
INSERT INTO YNQ (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,GROUP_NAME,STATUS,EFFECTIVE_DATE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('31','Is this proposal an NSF Accomplishment Based renewal?','P','Proposal Questions','A',STR_TO_DATE('20090220','%Y%m%d'),2,null,null,'admin',NOW(),UUID(),1)
/
INSERT INTO YNQ (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,GROUP_NAME,STATUS,EFFECTIVE_DATE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('32','Is the Government permitted to disclose the project title, and the contact information of the signing official, to organizations that may be interested in contacting you for further information?','P','Proposal Questions','I',STR_TO_DATE('20090311','%Y%m%d'),2,null,null,'admin',NOW(),UUID(),1)
/
INSERT INTO YNQ (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,GROUP_NAME,STATUS,EFFECTIVE_DATE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('999','test question','P','Grants.gov/Agency Specific Questions','A',STR_TO_DATE('20090527','%Y%m%d'),2,'N','N','admin',NOW(),UUID(),1)
/
INSERT INTO YNQ (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,GROUP_NAME,STATUS,EFFECTIVE_DATE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('C1','From this entity did you receive in the last 12 months, or do you expect to receive in the next 12 months, salary, director''s fee, consulting payments, honoraria, royalties or other payments for patents, copyrights or other intellectual property, or any other payments that will exceed $10,000 (aggregate for yourself, your spouse, and your dependent children)?','F',null,'A',STR_TO_DATE('20000101','%Y%m%d'),2,'Y',null,'admin',NOW(),UUID(),1)
/
INSERT INTO YNQ (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,GROUP_NAME,STATUS,EFFECTIVE_DATE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('C2','For this entity, do you have stock, stock options, or other interests of significant monetary value either in excess of $10,000 in fair market value or in excess of 5 percent of the ownership interest in a company? (In either case, aggregate for yourself, your spouse and your dependant children; in the case of stock in companies that are not publicly traded, use the most recent sales price)','F',null,'I',STR_TO_DATE('20000101','%Y%m%d'),2,'Y',null,'admin',NOW(),UUID(),1)
/
INSERT INTO YNQ (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,GROUP_NAME,STATUS,EFFECTIVE_DATE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('C3','For any entity (company or public or non-proft organization) did you receive in last 12 months, or do you expect to receive in the next 12 months, salary director''s fees, consulting payments, honoraria, royalties or other payments for patents, copyrights or other intellectual property, or any other payments exceeding $10,000 (aggregate for yourself, your spouse, and your dependent children)?','C',null,'A',STR_TO_DATE('20000101','%Y%m%d'),2,'Y',null,'admin',NOW(),UUID(),1)
/
INSERT INTO YNQ (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,GROUP_NAME,STATUS,EFFECTIVE_DATE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('C4','Do you have stock, stock options, or other interests of significant monetary value either in excess of $10,000 in fair market value or in excess of 5 percent of the ownership interest in any company? (In either case, aggregate for yourself, your spouse and your dependent children; in the case of stock in companies that are not publicly traded, use most recent sales price recognized by the company)','C',null,'I',STR_TO_DATE('20000101','%Y%m%d'),2,'Y',null,'admin',NOW(),UUID(),1)
/
INSERT INTO YNQ (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,GROUP_NAME,STATUS,EFFECTIVE_DATE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('C5','For this entity, do you have stock, stock options, or other interests of significant monetary value in excess of $10,000 in fair market value (aggregate for yourself, your spouse and your dependent children)?','F',null,'A',STR_TO_DATE('20080708','%Y%m%d'),2,null,null,'admin',NOW(),UUID(),1)
/
INSERT INTO YNQ (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,GROUP_NAME,STATUS,EFFECTIVE_DATE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('C6','For this entity, do you have stock, stock options, equity or other interests in excess of 5 percent of the ownership interest (aggregate for yourself, your spouse and your dependent children)?','F',null,'A',STR_TO_DATE('20080708','%Y%m%d'),2,null,null,'admin',NOW(),UUID(),1)
/
INSERT INTO YNQ (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,GROUP_NAME,STATUS,EFFECTIVE_DATE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('C7','Do you have stock, stock options, or other interests of significant monetary value in excess of $10,000 in fair market value (aggregate for yourself, your spouse, and your dependent children)?','C',null,'A',STR_TO_DATE('20080708','%Y%m%d'),2,null,null,'admin',NOW(),UUID(),1)
/
INSERT INTO YNQ (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,GROUP_NAME,STATUS,EFFECTIVE_DATE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('C8','Do you have stock, stock options, equity or other interests in excess of 5 percent of the ownership interest in any company (aggregate for yourself, your spouse, and your dependent children)?','C',null,'A',STR_TO_DATE('20080708','%Y%m%d'),2,null,null,'admin',NOW(),UUID(),1)
/
INSERT INTO YNQ (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,GROUP_NAME,STATUS,EFFECTIVE_DATE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('EC1','Will any equipment be exported by MIT in the course of this project?','P','Proposal Questions','A',STR_TO_DATE('20070328','%Y%m%d'),2,null,null,'admin',NOW(),UUID(),1)
/
INSERT INTO YNQ (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,GROUP_NAME,STATUS,EFFECTIVE_DATE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('EC2','Will this project require any export controlled information to be received on campus? Contact Jennifer Ponting 3-2822 if you have questions.','P','Proposal Questions','A',STR_TO_DATE('20070328','%Y%m%d'),2,null,null,'admin',NOW(),UUID(),1)
/
INSERT INTO YNQ (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,GROUP_NAME,STATUS,EFFECTIVE_DATE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('EO','Is the proposal subject to review by state executive order 12372 process?','P','Proposal Questions','I',STR_TO_DATE('20051006','%Y%m%d'),3,null,'Y','admin',NOW(),UUID(),1)
/
INSERT INTO YNQ (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,GROUP_NAME,STATUS,EFFECTIVE_DATE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('FG','Application is Certified  ','P','Proposal Questions','A',STR_TO_DATE('20000101','%Y%m%d'),2,'N','N','admin',NOW(),UUID(),1)
/
INSERT INTO YNQ (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,GROUP_NAME,STATUS,EFFECTIVE_DATE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('G3','Hazardous Materials are Used or Produced','P','Proposal Questions','A',STR_TO_DATE('20000101','%Y%m%d'),2,'Y','Y','admin',NOW(),UUID(),1)
/
INSERT INTO YNQ (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,GROUP_NAME,STATUS,EFFECTIVE_DATE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('G4','Genetically Engineered organisms are Used or Produced','P','Proposal Questions','A',STR_TO_DATE('20000101','%Y%m%d'),2,'Y','Y','admin',NOW(),UUID(),1)
/
INSERT INTO YNQ (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,GROUP_NAME,STATUS,EFFECTIVE_DATE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('G6','Historical Sites Are Affected','P','Proposal Questions','A',STR_TO_DATE('20000101','%Y%m%d'),2,'Y','Y','admin',NOW(),UUID(),1)
/
INSERT INTO YNQ (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,GROUP_NAME,STATUS,EFFECTIVE_DATE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('G7','Facilities are Properly Accredited or Authorized','O',null,'A',STR_TO_DATE('20000101','%Y%m%d'),2,'N','N','admin',NOW(),UUID(),1)
/
INSERT INTO YNQ (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,GROUP_NAME,STATUS,EFFECTIVE_DATE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('G8','Proprietary or Privileged Information will be contained in the Application','P','Proposal Questions','A',STR_TO_DATE('20000101','%Y%m%d'),2,'Y','Y','admin',NOW(),UUID(),2)
/
INSERT INTO YNQ (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,GROUP_NAME,STATUS,EFFECTIVE_DATE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('G9','This Project is in Violation of an Environmental Compliance Regulation','P','Proposal Questions','A',STR_TO_DATE('20000101','%Y%m%d'),2,'Y','Y','admin',NOW(),UUID(),1)
/
INSERT INTO YNQ (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,GROUP_NAME,STATUS,EFFECTIVE_DATE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('H0','Organization Certifies Compliance with Federal Lobbing Regulation','O',null,'A',STR_TO_DATE('20000101','%Y%m%d'),2,'N','N','admin',NOW(),UUID(),1)
/
INSERT INTO YNQ (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,GROUP_NAME,STATUS,EFFECTIVE_DATE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('H1','Project involves the International Cooperative activities?','P','Proposal Questions','A',STR_TO_DATE('20000101','%Y%m%d'),2,'Y',null,'admin',NOW(),UUID(),1)
/
INSERT INTO YNQ (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,GROUP_NAME,STATUS,EFFECTIVE_DATE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('H2','Human Anatomical Substances Are Used   ','P','Proposal Questions','A',STR_TO_DATE('20000101','%Y%m%d'),2,'Y',null,'admin',NOW(),UUID(),1)
/
INSERT INTO YNQ (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,GROUP_NAME,STATUS,EFFECTIVE_DATE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('H5','Organization Certifies Compliance With the Drug-Free Workplace Act','O',null,'A',STR_TO_DATE('20000101','%Y%m%d'),2,'N','N','admin',NOW(),UUID(),1)
/
INSERT INTO YNQ (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,GROUP_NAME,STATUS,EFFECTIVE_DATE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('H6','Organization Certifies Compliance with the Code of Federal Regulations Regarding Research Misconduct','O',null,'A',STR_TO_DATE('20000101','%Y%m%d'),2,'N','N','admin',NOW(),UUID(),1)
/
INSERT INTO YNQ (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,GROUP_NAME,STATUS,EFFECTIVE_DATE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('H7','Organization Provides a Smoke Free Workplace','O',null,'A',STR_TO_DATE('20000101','%Y%m%d'),2,'N','N','admin',NOW(),UUID(),1)
/
INSERT INTO YNQ (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,GROUP_NAME,STATUS,EFFECTIVE_DATE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('H8','Organization Certifies Compliance with Federal Discrimination Regulations ','O',null,'A',STR_TO_DATE('20000101','%Y%m%d'),2,'N','N','admin',NOW(),UUID(),1)
/
INSERT INTO YNQ (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,GROUP_NAME,STATUS,EFFECTIVE_DATE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('H9','Organization Certifies Compliance with the Code of Federal Regulations Regarding Responsibility of Applicants for Promoting Objectivity in Research for which Public Health Service (PHS) Funding is Sought','O',null,'A',STR_TO_DATE('20000101','%Y%m%d'),2,'N','N','admin',NOW(),UUID(),1)
/
INSERT INTO YNQ (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,GROUP_NAME,STATUS,EFFECTIVE_DATE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('I7','Organization has Delinquent Federal Debts','O',null,'A',STR_TO_DATE('20000101','%Y%m%d'),2,'Y','Y','admin',NOW(),UUID(),1)
/
INSERT INTO YNQ (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,GROUP_NAME,STATUS,EFFECTIVE_DATE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('I8','Organization has been Placed on the Federal Debarment and Suspension List','O',null,'A',STR_TO_DATE('20000101','%Y%m%d'),2,'Y','Y','admin',NOW(),UUID(),1)
/
INSERT INTO YNQ (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,GROUP_NAME,STATUS,EFFECTIVE_DATE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('J1','Military Services Barred from Recruitment Activities at the Proposing Organization''s Site(s)','O',null,'A',STR_TO_DATE('20000101','%Y%m%d'),2,'Y','Y','admin',NOW(),UUID(),1)
/
INSERT INTO YNQ (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,GROUP_NAME,STATUS,EFFECTIVE_DATE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('J2','Rate Negotiated ','O',null,'A',STR_TO_DATE('20000101','%Y%m%d'),2,'N','N','admin',NOW(),UUID(),1)
/
INSERT INTO YNQ (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,GROUP_NAME,STATUS,EFFECTIVE_DATE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('J4','Project Requires Inter-Government Review for Activities that affect State or Local Government or Possible National Security Implications','P','Proposal Questions','A',STR_TO_DATE('20000101','%Y%m%d'),2,null,null,'admin',NOW(),UUID(),1)
/
INSERT INTO YNQ (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,GROUP_NAME,STATUS,EFFECTIVE_DATE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('NR','No Restrictions','O',null,'I',STR_TO_DATE('20000101','%Y%m%d'),2,null,null,'admin',NOW(),UUID(),1)
/
INSERT INTO YNQ (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,GROUP_NAME,STATUS,EFFECTIVE_DATE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('P002','Is there a space change on this proposal?','P','General Y/N Questions','I',STR_TO_DATE('20071101','%Y%m%d'),3,'Y','Y','admin',NOW(),UUID(),1)
/
INSERT INTO YNQ (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,GROUP_NAME,STATUS,EFFECTIVE_DATE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('P003','Military Services Barred from Recruitment Activities at the Proposing Organization''s Site(s)','P','Organization','I',STR_TO_DATE('20071101','%Y%m%d'),3,'N','N','admin',NOW(),UUID(),1)
/
INSERT INTO YNQ (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,GROUP_NAME,STATUS,EFFECTIVE_DATE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('P007','NSF Beginning Investigator?','P','General Y/N Questions','I',STR_TO_DATE('20071106','%Y%m%d'),3,null,null,'admin',NOW(),UUID(),1)
/
INSERT INTO YNQ (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,GROUP_NAME,STATUS,EFFECTIVE_DATE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('P008','NIH Beginning Investigator?','P','General Y/N Questions','I',STR_TO_DATE('20071106','%Y%m%d'),2,null,null,'admin',NOW(),UUID(),1)
/
INSERT INTO YNQ (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,GROUP_NAME,STATUS,EFFECTIVE_DATE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('P1','Can you certify that the information submitted within this application is true, complete and accurate to the best of your knowledge? That any false, fictitious, or fraudulent statements or claims may subject you, as the PI/Co-PI/Co-I to criminal, civil or administrative penalties? That you agree to accept responsibility for the scientific conduct of the project and to provide the required progress reports if an award is made as a result of this application.','I',null,'A',STR_TO_DATE('20060628','%Y%m%d'),2,null,null,'admin',NOW(),UUID(),1)
/
INSERT INTO YNQ (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,GROUP_NAME,STATUS,EFFECTIVE_DATE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('P2','Is there any potential for a perceived or real conflict of interest as defined in MIT''s Policies and Procedures with regard to this proposal?','I',null,'A',STR_TO_DATE('20060628','%Y%m%d'),2,null,null,'admin',NOW(),UUID(),1)
/
INSERT INTO YNQ (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,GROUP_NAME,STATUS,EFFECTIVE_DATE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('P3','If this is a NIH/NSF proposal have you submitted the required financial disclosures in the web based Coeus Conflict of Interest module?','I',null,'A',STR_TO_DATE('20060628','%Y%m%d'),2,null,null,'admin',NOW(),UUID(),1)
/
INSERT INTO YNQ (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,GROUP_NAME,STATUS,EFFECTIVE_DATE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('P4','Have lobbying activities been conducted on behalf of this proposal?','I',null,'A',STR_TO_DATE('20060628','%Y%m%d'),2,null,null,'admin',NOW(),UUID(),1)
/
INSERT INTO YNQ (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,GROUP_NAME,STATUS,EFFECTIVE_DATE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('P5','Are you currently debarred, suspended, proposed for debarment, declared ineligible or voluntarily excluded from current transactions by a federal department or agency?','I',null,'A',STR_TO_DATE('20060628','%Y%m%d'),2,null,null,'admin',NOW(),UUID(),1)
/
delimiter ;
