--
-- Kuali Coeus, a comprehensive research administration system for higher education.
-- 
-- Copyright 2005-2015 Kuali, Inc.
-- 
-- This program is free software: you can redistribute it and/or modify
-- it under the terms of the GNU Affero General Public License as
-- published by the Free Software Foundation, either version 3 of the
-- License, or (at your option) any later version.
-- 
-- This program is distributed in the hope that it will be useful,
-- but WITHOUT ANY WARRANTY; without even the implied warranty of
-- MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
-- GNU Affero General Public License for more details.
-- 
-- You should have received a copy of the GNU Affero General Public License
-- along with this program.  If not, see <http://www.gnu.org/licenses/>.
--

DELIMITER /
UPDATE YNQ
SET DESCRIPTION = 'Are human embryonic stem cells used? If so, please click on ''view'' button for instructions'
WHERE QUESTION_ID = '18'
/

UPDATE YNQ_EXPLANATION
SET EXPLANATION = 'List no more than 20 registration numbers of the cell lines in the explanation for the question and separate them by commas. The maximum allowed length of each registration number is 4. If a specific stem cell line cannot be referenced at this time, enter ''UNKNOWN'' as the explanation.'
WHERE QUESTION_ID = '18'
/

UPDATE YNQ
SET DESCRIPTION = 'Is the principal investigator participating in this project as an employee of the U.S. Government? If so, please click on ''view'' button for instructions.'
WHERE QUESTION_ID = '24'
/

UPDATE YNQ_EXPLANATION
SET EXPLANATION = 'Enter one of the following 3-digit agency codes in the explanation for the question.
101: Agency for International Development
102: Air Force Research Laboratory
103: Army Research Laboratory
104: Center for Disease Control and Prevention
105: Coast Guard
106: Customs Service
107: Defense Advanced Research Projects Agency
108: Department of Agriculture (USDA)
109: Department of Commerce (DOC)
110: Department of Defense (DOD)
111: Department of Education (ED)
112: Department of Energy (DOE)
113: Department of Health and Human Services (HHS)
114: Department of Homeland Security (DHS)
115: Department of Justice (DOJ)
116: Department of State (DOS)
117: Department of the Air Force
118: Department of the Army
119: Department of the Interior (DOI)
120: Department of the Navy
121: Department of Transportation (DOT)
122: Department of Veterans Affairs (VA)
123: Environmental Protection Agency (EPA)
124: Federal Aviation Administration (FAA)
125: Federal Emergency Management Agency (FEMA)
126: Federal Maritime Commission
127: Fish and Wildlife Service
128: Forest Service
129: NASA Ames Research Center
130: NASA Dryden Flight Research Center
131: NASA Glenn Research Center
132: NASA Goddard Space Flight Center
133: NASA Headquarters
134: NASA Johnson Space Center
135: NASA Kennedy Space Center
136: NASA Langley Research Center
137: NASA Marshall Space Flight Center
138: NASA Stennis Space Center
139: National Institute of Standards & Technology (NIST)
140: National Institutes of Health (NIH)
141: National Oceanic and Atmospheric Administration (NOAA)
142: National Park Service
143: National Science Foundation (NSF)
144: Naval Observatory
145: Naval Research Laboratory
146: Other
147: Smithsonian Institution
148: United States Geological Survey (USGS)
149: United States Marine Corps
150: Walter Reed Army Institute Research'
WHERE QUESTION_ID = '24'
/

DELIMITER ;
