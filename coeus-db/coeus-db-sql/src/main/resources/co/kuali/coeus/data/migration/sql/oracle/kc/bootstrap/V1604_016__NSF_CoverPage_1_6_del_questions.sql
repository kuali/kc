--
-- Kuali Coeus, a comprehensive research administration system for higher education.
--
-- Copyright 2005-2016 Kuali, Inc.
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


delete from questionnaire_answer where QUESTIONNAIRE_QUESTIONS_ID_FK in (select QUESTIONNAIRE_QUESTIONS_ID from questionnaire_questions where
QUESTIONNAIRE_REF_ID_FK in (SELECT QUESTIONNAIRE_REF_ID FROM QUESTIONNAIRE WHERE NAME = 'NSF cover page 1-6 supporting questions') AND
QUESTION_REF_ID_FK in (SELECT QUESTION_REF_ID FROM QUESTION WHERE QUESTION_ID = '54'));

delete from questionnaire_questions where
  QUESTIONNAIRE_REF_ID_FK in (SELECT QUESTIONNAIRE_REF_ID FROM QUESTIONNAIRE WHERE NAME = 'NSF cover page 1-6 supporting questions') AND
  QUESTION_REF_ID_FK in (SELECT QUESTION_REF_ID FROM QUESTION WHERE QUESTION_ID = '54');

delete from questionnaire_answer where QUESTIONNAIRE_QUESTIONS_ID_FK in (select QUESTIONNAIRE_QUESTIONS_ID from questionnaire_questions where
  QUESTIONNAIRE_REF_ID_FK in (SELECT QUESTIONNAIRE_REF_ID FROM QUESTIONNAIRE WHERE NAME = 'NSF cover page 1-6 supporting questions') AND
  QUESTION_REF_ID_FK in (SELECT QUESTION_REF_ID FROM QUESTION WHERE QUESTION_ID = '55'));

delete from questionnaire_questions where
  QUESTIONNAIRE_REF_ID_FK in (SELECT QUESTIONNAIRE_REF_ID FROM QUESTIONNAIRE WHERE NAME = 'NSF cover page 1-6 supporting questions') AND
  QUESTION_REF_ID_FK in (SELECT QUESTION_REF_ID FROM QUESTION WHERE QUESTION_ID = '55');

delete from questionnaire_answer where QUESTIONNAIRE_QUESTIONS_ID_FK in (select QUESTIONNAIRE_QUESTIONS_ID from questionnaire_questions where
  QUESTIONNAIRE_REF_ID_FK in (SELECT QUESTIONNAIRE_REF_ID FROM QUESTIONNAIRE WHERE NAME = 'NSF cover page 1-6 supporting questions') AND
  QUESTION_REF_ID_FK in (SELECT QUESTION_REF_ID FROM QUESTION WHERE QUESTION_ID = '58'));

delete from questionnaire_questions where
  QUESTIONNAIRE_REF_ID_FK in (SELECT QUESTIONNAIRE_REF_ID FROM QUESTIONNAIRE WHERE NAME = 'NSF cover page 1-6 supporting questions') AND
  QUESTION_REF_ID_FK in (SELECT QUESTION_REF_ID FROM QUESTION WHERE QUESTION_ID = '58');

delete from questionnaire_answer where QUESTIONNAIRE_QUESTIONS_ID_FK in (select QUESTIONNAIRE_QUESTIONS_ID from questionnaire_questions where
  QUESTIONNAIRE_REF_ID_FK in (SELECT QUESTIONNAIRE_REF_ID FROM QUESTIONNAIRE WHERE NAME = 'NSF cover page 1-6 supporting questions') AND
  QUESTION_REF_ID_FK in (SELECT QUESTION_REF_ID FROM QUESTION WHERE QUESTION_ID = '59'));

delete from questionnaire_questions where
  QUESTIONNAIRE_REF_ID_FK in (SELECT QUESTIONNAIRE_REF_ID FROM QUESTIONNAIRE WHERE NAME = 'NSF cover page 1-6 supporting questions') AND
  QUESTION_REF_ID_FK in (SELECT QUESTION_REF_ID FROM QUESTION WHERE QUESTION_ID = '59');

delete from questionnaire_answer where QUESTIONNAIRE_QUESTIONS_ID_FK in (select QUESTIONNAIRE_QUESTIONS_ID from questionnaire_questions where
  QUESTIONNAIRE_REF_ID_FK in (SELECT QUESTIONNAIRE_REF_ID FROM QUESTIONNAIRE WHERE NAME = 'NSF cover page 1-6 supporting questions') AND
  QUESTION_REF_ID_FK in (SELECT QUESTION_REF_ID FROM QUESTION WHERE QUESTION_ID = '60'));

delete from questionnaire_questions where
  QUESTIONNAIRE_REF_ID_FK in (SELECT QUESTIONNAIRE_REF_ID FROM QUESTIONNAIRE WHERE NAME = 'NSF cover page 1-6 supporting questions') AND
  QUESTION_REF_ID_FK in (SELECT QUESTION_REF_ID FROM QUESTION WHERE QUESTION_ID = '60');

delete from questionnaire_answer where QUESTIONNAIRE_QUESTIONS_ID_FK in (select QUESTIONNAIRE_QUESTIONS_ID from questionnaire_questions where
  QUESTIONNAIRE_REF_ID_FK in (SELECT QUESTIONNAIRE_REF_ID FROM QUESTIONNAIRE WHERE NAME = 'NSF cover page 1-6 supporting questions') AND
  QUESTION_REF_ID_FK in (SELECT QUESTION_REF_ID FROM QUESTION WHERE QUESTION_ID = '62'));

delete from questionnaire_questions where
  QUESTIONNAIRE_REF_ID_FK in (SELECT QUESTIONNAIRE_REF_ID FROM QUESTIONNAIRE WHERE NAME = 'NSF cover page 1-6 supporting questions') AND
  QUESTION_REF_ID_FK in (SELECT QUESTION_REF_ID FROM QUESTION WHERE QUESTION_ID = '62');