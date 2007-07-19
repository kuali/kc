/*
 * Copyright 2005-2006 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
CREATE INDEX FS_UNIVERSAL_USR_TI1 ON FS_UNIVERSAL_USR_T(
        PRSN_LST_NM, PRSN_1ST_NM )
TABLESPACE KUL_IDX01
/
CREATE INDEX FS_UNIVERSAL_USR_TI2 ON FS_UNIVERSAL_USR_T(
        PERSON_USER_ID )
TABLESPACE KUL_IDX01
/
