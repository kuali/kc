update YNQ set (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,STATUS,EFFECTIVE_DATE,GROUP_NAME) = 
		(select '11','Is there space change on this proposal','P',2,'Y',null,'A',to_date('01-JAN-00','DD-MON-RR'),'General Y/N Questions' from dual) 
		where question_id='11';
update YNQ set (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,STATUS,EFFECTIVE_DATE,GROUP_NAME) = 
		(select '12','NSF Beginning Investigator ','P',2,null,null,'A',to_date('01-JAN-00','DD-MON-RR'),'General Y/N Questions' from dual) 
		where question_id='12';
update YNQ set (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,STATUS,EFFECTIVE_DATE,GROUP_NAME) = 
		(select '13','NIH Beginning Investigator','P',2,null,null,'A',to_date('01-JAN-00','DD-MON-RR'),'General Y/N Questions' from dual) 
		where question_id='13';
update YNQ set (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,STATUS,EFFECTIVE_DATE,GROUP_NAME) = 
		(select '14','This is an NSF Small Grant for Exploratory Research','P',2,null,null,'A',to_date('01-JAN-00','DD-MON-RR'),'General Y/N Questions' from dual) 
		where question_id='14';
update YNQ set (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,STATUS,EFFECTIVE_DATE,GROUP_NAME) = 
		(select '15','Is this proposal being submitted to other agencies?  If so, please list agency acronyms in explanation separated by commas.','P',2,'Y',null,'A',to_date('01-JAN-00','DD-MON-RR'),'General Y/N Questions' from dual) 
		where question_id='15';
update YNQ set (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,STATUS,EFFECTIVE_DATE,GROUP_NAME) = 
		(select '16','Have all inventions or patents been previously reported?  Answer NA if there are no inventions or patents.','P',3,null,null,'A',to_date('01-JAN-00','DD-MON-RR'),'General Y/N Questions' from dual) 
		where question_id='16';
update YNQ set (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,STATUS,EFFECTIVE_DATE,GROUP_NAME) = 
		(select '17','Is this a Phase III Clinical Trial as defined by NIH?','P',2,null,null,'A',to_date('19-MAY-05','DD-MON-RR'),'General Y/N Questions' from dual) 
		where question_id='17';
update YNQ set (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,STATUS,EFFECTIVE_DATE,GROUP_NAME) = 
		(select '18','Are human embryonic stem cells used?  If so, list the registration numbers of the cell lines in the comment.','P',2,'Y',null,'A',to_date('01-JAN-00','DD-MON-RR'),'General Y/N Questions' from dual) 
		where question_id='18';
update YNQ set (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,STATUS,EFFECTIVE_DATE,GROUP_NAME) = 
		(select '19','Are you currently serving, or have you previously served, as a PI, co-PI or Program Director on any Federally funded project?','P',2,null,null,'A',to_date('01-JUL-05','DD-MON-RR'),'General Y/N Questions' from dual) 
		where question_id='19';
update YNQ set (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,STATUS,EFFECTIVE_DATE,GROUP_NAME) = 
		(select '20','Does the proposal contain high resolution graphics or other graphics where exact color representation is required for proper interpretation (GPG, Chapter I.G.1)?','P',2,null,null,'A',to_date('01-JUL-05','DD-MON-RR'),'General Y/N Questions' from dual) 
		where question_id='20';
update YNQ set (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,STATUS,EFFECTIVE_DATE,GROUP_NAME) = 
		(select '21','Is the proposal a full application related to submission of a preliminary application?','P',2,null,null,'A',to_date('01-JUL-05','DD-MON-RR'),'General Y/N Questions' from dual) 
		where question_id='21';
update YNQ set (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,STATUS,EFFECTIVE_DATE,GROUP_NAME) = 
		(select '22','Is the principal investigator changing with this application?  If so, please enter last and first names of previous investigator in explanation. Please enter LAST NAME, FIRST NAME.','P',2,'Y',null,'A',to_date('17-OCT-06','DD-MON-RR'),'General Y/N Questions' from dual) 
		where question_id='22';
update YNQ set (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,STATUS,EFFECTIVE_DATE,GROUP_NAME) = 
		(select '23','Has the grantee institution changed with this application? If so, enter name of previous institution in explanation field.','P',2,'Y',null,'A',to_date('17-OCT-06','DD-MON-RR'),'General Y/N Questions' from dual) 
		where question_id='23';
update YNQ set (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,STATUS,EFFECTIVE_DATE,GROUP_NAME) = 
		(select '24','Is the principal investigator participating in this project as an employee of the U.S. Government? If so,  please click on ''More'' for instructions.','P',2,'Y',null,'A',to_date('31-OCT-06','DD-MON-RR'),'General Y/N Questions' from dual) 
		where question_id='24';
update YNQ set (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,STATUS,EFFECTIVE_DATE,GROUP_NAME) = 
		(select '25','Is the principal investigator participating in this project as an employee of a foreign organization?','P',2,null,null,'A',to_date('31-OCT-06','DD-MON-RR'),'General Y/N Questions' from dual) 
		where question_id='25';
update YNQ set (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,STATUS,EFFECTIVE_DATE,GROUP_NAME) = 
		(select '26','For Dept. of Education, Check "Yes" or "No" only if assistance is being requested under a program that gives special consideration to novice applicants. Otherwise, check "Not Applicable".','P',3,'Y',null,'A',to_date('28-NOV-06','DD-MON-RR'),'General Y/N Questions' from dual) 
		where question_id='26';
update YNQ set (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,STATUS,EFFECTIVE_DATE,GROUP_NAME) = 
		(select '27','If this project has an actual or potential impact on the environment, has an exemption been authorized or an environmental assessment (EA) or environmental impact statement (EIS) been performed?  If yes, please explain in comments.','P',3,'YN',null,'A',to_date('01-JUN-08','DD-MON-RR'),'General Y/N Questions' from dual) 
		where question_id='27';
update YNQ set (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,STATUS,EFFECTIVE_DATE,GROUP_NAME) = 
		(select '28','Is this a clinical trial?','P',2,null,null,'A',to_date('20-FEB-09','DD-MON-RR'),'General Y/N Questions' from dual) 
		where question_id='28';
update YNQ set (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,STATUS,EFFECTIVE_DATE,GROUP_NAME) = 
		(select '29','Is the Government permitted to disclose the project title, and the contact information of the signing official, to organizations that may be interested in contacting you for further information ?','P',2,null,null,'A',to_date('20-FEB-09','DD-MON-RR'),'General Y/N Questions' from dual) 
		where question_id='29';
update YNQ set (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,STATUS,EFFECTIVE_DATE,GROUP_NAME) = 
		(select '30','Is this organization being created in order to submit applications as an individual rather than as a company, state, local or tribal government, academia, or other type of organization?','O',2,null,null,'A',to_date('20-FEB-09','DD-MON-RR'),null from dual) 
		where question_id='30';
update YNQ set (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,STATUS,EFFECTIVE_DATE,GROUP_NAME) = 
		(select '31','Is this proposal an NSF Accomplishment Based renewal?','P',2,null,null,'A',to_date('20-FEB-09','DD-MON-RR'),'General Y/N Questions' from dual) 
		where question_id='31';
update YNQ set (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,STATUS,EFFECTIVE_DATE,GROUP_NAME) = 
		(select '32','Is the Government permitted to disclose the project title, and the contact information of the signing official, to organizations that may be interested in contacting you for further information?','P',2,null,null,'I',to_date('11-MAR-09','DD-MON-RR'),'General Y/N Questions' from dual) 
		where question_id='32';
update YNQ set (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,STATUS,EFFECTIVE_DATE,GROUP_NAME) = 
		(select '999','test question','P',2,'N','N','A',to_date('27-MAY-09','DD-MON-RR'),'General Y/N Questions' from dual) 
		where question_id='999';
update YNQ set (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,STATUS,EFFECTIVE_DATE,GROUP_NAME) = 
		(select 'C1','From this entity did you receive in the last 12 months, or do you expect to receive in the next 12 months, salary, director''s fee, consulting payments, honoraria, royalties or other payments for patents, copyrights or other intellectual property, or any other payments that will exceed $10,000 (aggregate for yourself, your spouse, and your dependant children)?','F',2,'Y',null,'A',to_date('01-JAN-00','DD-MON-RR'),'General Y/N Questions' from dual) 
		where question_id='C1';
update YNQ set (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,STATUS,EFFECTIVE_DATE,GROUP_NAME) = 
		(select 'C2','For this entity, do you have stock, stock options, or other interests of significant monetary value either in excess of $10,000 in fair market value or in excess of 5 percent of the ownership interest in a company? (In either case, aggregate for yourself, your spouse and your dependant children; in the case of stock in companies that are not publicly traded, use the most recent sales price)','F',2,'Y',null,'A',to_date('01-JAN-00','DD-MON-RR'),'General Y/N Questions' from dual) 
		where question_id='C2';
update YNQ set (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,STATUS,EFFECTIVE_DATE,GROUP_NAME) = 
		(select 'C3','For any entity (company or public or non-proft organization) did you receive in last 12 months, or do you expect to receive in the next 12 months, salary director''s fees, consulting payments, honoraria, royalties or other payments for patents, copyrights or other intellectual property, or any other payments exceeding $10,000 (aggregate for yourself, your spouse, and your dependent children)','C',2,'Y',null,'A',to_date('01-JAN-00','DD-MON-RR'),'General Y/N Questions' from dual) 
		where question_id='C3';
update YNQ set (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,STATUS,EFFECTIVE_DATE,GROUP_NAME) = 
		(select 'C4','Do you have stock, stock options, or other interests of significant monetary value either in excess of $10,000 in fair market value or in excess of 5 percent of the ownership interest in any company? (In either case, aggregate for yourself, your spouse and your dependent children; in the case of stock in companies that are not publicly traded, use most recent sales price recognized by the company)','C',2,'Y',null,'A',to_date('01-JAN-00','DD-MON-RR'),'General Y/N Questions' from dual) 
		where question_id='C4';
update YNQ set (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,STATUS,EFFECTIVE_DATE,GROUP_NAME) = 
		(select 'C5','For this entity, do you have stock, stock options, or other interests of significant monetary value in excess of $10,000 in fair market value (aggregate for yourself, your spouse and your dependent children)?','F',2,null,null,'A',to_date('08-JUL-08','DD-MON-RR'),null from dual) 
		where question_id='C5';
update YNQ set (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,STATUS,EFFECTIVE_DATE,GROUP_NAME) = 
		(select 'C6','For this entity, do you have stock, stock options, equity or other interests in excess of 5 percent of the ownership interest (aggregate for yourself, your spouse and your dependent children)?','F',2,null,null,'A',to_date('08-JUL-08','DD-MON-RR'),null from dual) 
		where question_id='C6';
update YNQ set (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,STATUS,EFFECTIVE_DATE,GROUP_NAME) = 
		(select 'C7','Do you have stock, stock options, or other interests of significant monetary value in excess of $10,000 in fair market value (aggregate for yourself, your spouse, and your dependent children)?','C',2,null,null,'A',to_date('08-JUL-08','DD-MON-RR'),null from dual) 
		where question_id='C7';
update YNQ set (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,STATUS,EFFECTIVE_DATE,GROUP_NAME) = 
		(select 'C8','Do you have stock, stock options, equity or other interests in excess of 5 percent of the ownership interest in any company (aggregate for yourself, your spouse, and your dependent children)?','C',2,null,null,'A',to_date('08-JUL-08','DD-MON-RR'),null from dual) 
		where question_id='C8';
update YNQ set (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,STATUS,EFFECTIVE_DATE,GROUP_NAME) = 
		(select 'EC1','Will any equipment be exported by MIT in the course of this project?','P',2,null,null,'A',to_date('28-MAR-07','DD-MON-RR'),'General Y/N Questions' from dual) 
		where question_id='EC1';
update YNQ set (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,STATUS,EFFECTIVE_DATE,GROUP_NAME) = 
		(select 'EC2','Will this project require any export controlled information to be received on campus? Contact Jennifer Ponting 3-2822 if you have questions.','P',2,null,null,'A',to_date('28-MAR-07','DD-MON-RR'),'General Y/N Questions' from dual) 
		where question_id='EC2';
update YNQ set (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,STATUS,EFFECTIVE_DATE,GROUP_NAME) = 
		(select 'EO','Is the proposal subject to review by state executive order 12372 process?','P',3,null,'Y','I',to_date('06-OCT-05','DD-MON-RR'),'General Y/N Questions' from dual) 
		where question_id='EO';
update YNQ set (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,STATUS,EFFECTIVE_DATE,GROUP_NAME) = 
		(select 'FG','Application is Certified  ','P',2,'N','N','A',to_date('01-JAN-00','DD-MON-RR'),'General Y/N Questions' from dual) 
		where question_id='FG';
update YNQ set (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,STATUS,EFFECTIVE_DATE,GROUP_NAME) = 
		(select 'G3','Hazardous Materials are Used or Produced','P',2,'Y','Y','A',to_date('01-JAN-00','DD-MON-RR'),'General Y/N Questions' from dual) 
		where question_id='G3';
update YNQ set (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,STATUS,EFFECTIVE_DATE,GROUP_NAME) = 
		(select 'G4','Genetically Engineered organisms are Used or Produced','P',2,'Y','Y','A',to_date('01-JAN-00','DD-MON-RR'),'General Y/N Questions' from dual) 
		where question_id='G4';
update YNQ set (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,STATUS,EFFECTIVE_DATE,GROUP_NAME) = 
		(select 'G6','Historical Sites Are Affected','P',2,null,null,'A',to_date('01-JUN-08','DD-MON-RR'),'General Y/N Questions' from dual) 
		where question_id='G6';
update YNQ set (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,STATUS,EFFECTIVE_DATE,GROUP_NAME) = 
		(select 'G7','Facilities are Properly Accredited or Authorized','O',2,'N','N','A',to_date('01-JAN-00','DD-MON-RR'),null from dual) 
		where question_id='G7';
update YNQ set (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,STATUS,EFFECTIVE_DATE,GROUP_NAME) = 
		(select 'G8','Proprietary or Privileged Information will be contained in the Application','P',2,null,null,'A',to_date('01-JUN-08','DD-MON-RR'),'General Y/N Questions' from dual) 
		where question_id='G8';
update YNQ set (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,STATUS,EFFECTIVE_DATE,GROUP_NAME) = 
		(select 'G9','This Project is in Violation of an Environmental Compliance Regulation','P',2,'Y','Y','A',to_date('01-JAN-00','DD-MON-RR'),'General Y/N Questions' from dual) 
		where question_id='G9';
update YNQ set (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,STATUS,EFFECTIVE_DATE,GROUP_NAME) = 
		(select 'H0','Organization Certifies Compliance with Federal Lobbing Regulation','O',2,'N','N','A',to_date('01-JAN-00','DD-MON-RR'),null from dual) 
		where question_id='H0';
update YNQ set (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,STATUS,EFFECTIVE_DATE,GROUP_NAME) = 
		(select 'H1','Project involves the International Cooperative activities?','P',2,'Y','Y','A',to_date('01-JAN-00','DD-MON-RR'),'General Y/N Questions' from dual) 
		where question_id='H1';
update YNQ set (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,STATUS,EFFECTIVE_DATE,GROUP_NAME) = 
		(select 'H2','Human Anatomical Substances Are Used   ','P',2,'Y',null,'A',to_date('01-JAN-00','DD-MON-RR'),'General Y/N Questions' from dual) 
		where question_id='H2';
update YNQ set (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,STATUS,EFFECTIVE_DATE,GROUP_NAME) = 
		(select 'H4','Lobbying activities have been conducted regarding the proposal','I',2,'Y','Y','A',to_date('01-JAN-00','DD-MON-RR'),'General Y/N Questions' from dual) 
		where question_id='H4';
update YNQ set (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,STATUS,EFFECTIVE_DATE,GROUP_NAME) = 
		(select 'H5','Organization Certifies Compliance With the Drug-Free Workplace Act','O',2,'N','N','A',to_date('01-JAN-00','DD-MON-RR'),null from dual) 
		where question_id='H5';
update YNQ set (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,STATUS,EFFECTIVE_DATE,GROUP_NAME) = 
		(select 'H6','Organization Certifies Compliance with the Code of Federal Regulations Regarding Research Misconduct','O',2,'N','N','A',to_date('01-JAN-00','DD-MON-RR'),null from dual) 
		where question_id='H6';
update YNQ set (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,STATUS,EFFECTIVE_DATE,GROUP_NAME) = 
		(select 'H7','Organization Provides a Smoke Free Workplace','O',2,'N','N','A',to_date('01-JAN-00','DD-MON-RR'),null from dual) 
		where question_id='H7';
update YNQ set (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,STATUS,EFFECTIVE_DATE,GROUP_NAME) = 
		(select 'H8','Organization Certifies Compliance with Federal Discrimination Regulations ','O',2,'N','N','A',to_date('01-JAN-00','DD-MON-RR'),null from dual) 
		where question_id='H8';
update YNQ set (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,STATUS,EFFECTIVE_DATE,GROUP_NAME) = 
		(select 'H9','Organization Certifies Compliance with the Code of Federal Regulations Regarding Responsibility of Applicants for Promoting Objectivity in Research for which Public Health Service (PHS) Funding is Sought','O',2,'N','N','A',to_date('01-JAN-00','DD-MON-RR'),null from dual) 
		where question_id='H9';
update YNQ set (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,STATUS,EFFECTIVE_DATE,GROUP_NAME) = 
		(select 'I7','Organization has Delinquent Federal Debts','O',2,'Y','Y','A',to_date('01-JAN-00','DD-MON-RR'),null from dual) 
		where question_id='I7';
update YNQ set (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,STATUS,EFFECTIVE_DATE,GROUP_NAME) = 
		(select 'I8','Organization has been Placed on the Federal Debarment and Suspension List','O',2,'Y','Y','A',to_date('01-JAN-00','DD-MON-RR'),null from dual) 
		where question_id='I8';
update YNQ set (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,STATUS,EFFECTIVE_DATE,GROUP_NAME) = 
		(select 'J1','Military Services Barred from Recruitment Activities at the Proposing Organization''s Site(s)','O',2,'Y','Y','A',to_date('01-JAN-00','DD-MON-RR'),null from dual) 
		where question_id='J1';
update YNQ set (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,STATUS,EFFECTIVE_DATE,GROUP_NAME) = 	
		(select 'J2','Rate Negotiated ','O',2,'N','N','A',to_date('01-JAN-00','DD-MON-RR'),null from dual) 
		where question_id='J2';
update YNQ set (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,STATUS,EFFECTIVE_DATE,GROUP_NAME) = 
		(select 'J4','Project Requires Inter-Government Review for Activities that affect State or Local Government or Possible National Security Implications','P',2,null,null,'A',to_date('01-JAN-00','DD-MON-RR'),'General Y/N Questions' from dual) 
		where question_id='J4';
update YNQ set (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,STATUS,EFFECTIVE_DATE,GROUP_NAME) = 
		(select 'NR','No Restrictions','O',2,null,null,'I',to_date('01-JAN-00','DD-MON-RR'),null from dual) 
		where question_id='NR';
update YNQ set (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,STATUS,EFFECTIVE_DATE,GROUP_NAME) = 
		(select 'P002','Is there a space change on this proposal?','P',3,'Y','Y','I',to_date('01-NOV-07','DD-MON-RR'),'General Y/N Questions' from dual) 
		where question_id='P002';
update YNQ set (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,STATUS,EFFECTIVE_DATE,GROUP_NAME) = 
		(select 'P004','Lobbying activities have been conducted regarding the proposal ','I',2,null,null,'I',to_date('01-NOV-07','DD-MON-RR'),'Group Investigator' from dual) 
		where question_id='P004';
update YNQ set (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,STATUS,EFFECTIVE_DATE,GROUP_NAME) = 
		(select 'P005','Are you familiar with the requirements of the Procurement Integrity Act [(OFPP, Section 27 (1-3)] (http://web.mit.edu/osp/www/Procuint.htm) and will you report any violations to the Office of Sponsored Programs?','I',3,'N','N','A',to_date('06-NOV-07','DD-MON-RR'),'Group Investigator' from dual) 
		where question_id='P005';
update YNQ set (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,STATUS,EFFECTIVE_DATE,GROUP_NAME) = 
		(select 'P006','Can you certify that the information submitted within this application is true, complete and accurate to the best of your knowledge? That any false, fictitious, or fraudulent statements or claims may subject you, as the PI/Co-PI/Co-I to criminal, civil or administrative penalties? That you agree to accept responsibility for the scientific conduct of the project and to provide the required ','I',2,'N','N','A',to_date('06-NOV-07','DD-MON-RR'),'Group Investigator' from dual) 
		where question_id='P006';
update YNQ set (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,STATUS,EFFECTIVE_DATE,GROUP_NAME) = 
		(select 'P007','NSF Beginning Investigator?','P',3,null,null,'I',to_date('06-NOV-07','DD-MON-RR'),'General Y/N Questions' from dual) 
		where question_id='P007';
update YNQ set (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,STATUS,EFFECTIVE_DATE,GROUP_NAME) = 
		(select 'P008','NIH Beginning Investigator?','P',2,null,null,'I',to_date('06-NOV-07','DD-MON-RR'),'General Y/N Questions' from dual) 
		where question_id='P008';
update YNQ set (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,STATUS,EFFECTIVE_DATE,GROUP_NAME) = 
		(select 'P1','Can you certify that the information submitted within this application is true, complete and accurate to the best of your knowledge?   That any false, fictitious, or fraudulent statements or claims may subject you, as the PI/Co-PI/Co-I to criminal, civil or administrative penalties?  That you agree to accept responsibility for the scientific conduct of the project and to provide the required','I',2,null,null,'A',to_date('28-JUN-06','DD-MON-RR'),null from dual) 
		where question_id='P1';
update YNQ set (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,STATUS,EFFECTIVE_DATE,GROUP_NAME) = 
		(select 'P2','Is there any potential for a perceived or real conflict of interest as defined in MIT''s Policies and Procedures with regard to this proposal?','I',2,null,null,'A',to_date('28-JUN-06','DD-MON-RR'),null from dual) 
		where question_id='P2';
update YNQ set (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,STATUS,EFFECTIVE_DATE,GROUP_NAME) = 
		(select 'P3','If this is a NIH/NSF proposal have you submitted the required financial disclosures in the web based Coeus Conflict of Interest module?','I',2,null,null,'A',to_date('28-JUN-06','DD-MON-RR'),null from dual) 
		where question_id='P3';
update YNQ set (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,STATUS,EFFECTIVE_DATE,GROUP_NAME) = 
		(select 'P4','Have lobbying activities been conducted on behalf of this proposal?','I',2,null,null,'A',to_date('28-JUN-06','DD-MON-RR'),null from dual) 
		where question_id='P4';
update YNQ set (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,STATUS,EFFECTIVE_DATE,GROUP_NAME) = 
		(select 'P5','Are you currently debarred, suspended, proposed for debarment, declared ineligible or voluntarily excluded from current transactions by a federal department or agency?','I',2,null,null,'A',to_date('28-JUN-06','DD-MON-RR'),null from dual) 
		where question_id='P5';
update YNQ set (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,STATUS,EFFECTIVE_DATE,GROUP_NAME) = 
		(select 'P6','Are you familiar with the requirements of the Procurement Integrity Act [(OFPP, Section 27 (1-3)] (http://web.mit.edu/osp/www/Procuint.htm) and will you report any violations to the Office of Sponsored Programs?','I',2,null,null,'A',to_date('28-JUN-06','DD-MON-RR'),null from dual) 
		where question_id='P6';
update YNQ set (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,STATUS,EFFECTIVE_DATE,GROUP_NAME) = 
		(select 'Z1','Is there a Financial Conflict of Interest with regards to this proposal?','I',2,'N','N','A',to_date('01-JAN-00','DD-MON-RR'),'General Y/N Questions' from dual) 
		where question_id='Z1';
update YNQ set (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,STATUS,EFFECTIVE_DATE,GROUP_NAME) = 
		(select 'Z2','Are you currently Debarred, Suspended, or proposed for debarment or suspension?','I',2,'Y','Y','A',to_date('01-JAN-00','DD-MON-RR'),'General Y/N Questions' from dual) 
		where question_id='Z2';
update YNQ set (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,NO_OF_ANSWERS,EXPLANATION_REQUIRED_FOR,DATE_REQUIRED_FOR,STATUS,EFFECTIVE_DATE,GROUP_NAME) = 
		(select 'Z3','Are you delinquent on any federal debt?','I',2,'Y',null,'A',to_date('01-JAN-00','DD-MON-RR'),'General Y/N Questions' from dual) 
		where question_id='Z3';

commit;

