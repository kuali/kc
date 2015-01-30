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
-- insert new parameter type for COI certifications
INSERT INTO KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD)
  VALUES ('KC-COIDISCLOSURE','Document','COI_CERTIFICATION_ACKNOWLEDGEMENT',UUID(),1,'CONFG','<p>I acknowledge that it is my responsibility to disclose any new SIGNIFICANT FINANCIAL INTERESTS obtained during the term of the above proposed project. I certify that this is a complete disclosure of all my financial interests related to the above proposed project.','Acknowledgement statement presented as part of the Disclosure document.','A','KC')
/
INSERT INTO KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD)
  VALUES ('KC-COIDISCLOSURE','Document','COI_CERTIFICATION_STATEMENT',UUID(),1,'CONFG','<p>In accordance with the University''s policy on Disclosure of Financial Interests and Management of Conflict of Interest Related to Sponsored Projects, the Principal Investigator and all other Investigators who share responsibility for the design, conduct, or reporting of sponsored projects must disclose their personal SIGNIFICANT FINANCIAL INTERESTS in any non-profit foundation or for-profit company that might benefit from the predictable results of those proposed projects.</p><p>In addition, when the work to be performed under the proposed research project and the results of the proposed research project would reasonably appear to affect the Investigator''s SIGNIFICANT FINANCIAL INTEREST, the interest is regarded as being related to the proposed research project and must be reported.</p><p>For the purposes of this disclosure, SIGNIFICANT FINANCIAL INTEREST is considered to include:</p><ol>  <li>Income (Includes salary, stock dividends and/or interest earned, consulting fees, royalty payments and honoraria from a single business entity exceeding $10,000).</li><li>Position with a single business entity (Includes director, employee, founder, manager, officer, partner, trustee, or advisory board member).</li>  <li>Investment Ownership or Controlling Interest of more than 5% of the voting stock in a single business entity.</li><li>Interest in Intellectual Property Rights belonging to a single business entity (Includes patents, copyrights or other license rights).</li></ul>','Certification instructions presented as part of the Disclosure document.','A','KC')
/
DELIMITER ;
