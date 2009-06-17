/*
 * Copyright 2006-2009 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

INSERT INTO RISK_LEVEL ( RISK_LEVEL_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 1, 'No greater than minimal risk.', sysdate, user ); 
INSERT INTO RISK_LEVEL ( RISK_LEVEL_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 2, 'Greater than minimal risk but potential for direct benefit for participant', sysdate, user ); 
INSERT INTO RISK_LEVEL ( RISK_LEVEL_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 3, 'Moderate Risk', sysdate, user ); 
INSERT INTO RISK_LEVEL ( RISK_LEVEL_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 4, 'Research involving greater than minimal risk, with no potential for benefit to participant, but likely to yield generalizable knowledge about the participant''s condition', sysdate, user ); 
INSERT INTO RISK_LEVEL ( RISK_LEVEL_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 5, 'High Risk', sysdate, user ); 
