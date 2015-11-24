# Kuali Coeus Data Conversion

This module handles complex data migrations.  These migrations may span multiple schemas or contain complex business logic.
The migrations represent a modification to KC data that is required at a certain point in time in the KC project.  Just
like normal SQL migration scripts, these migration programs only need to be executed once and must be execute at the
correct time.  These migrations can be run in dry run mode and when dry run is turned off will never commit when
encountering an error.

This program is designed to run as a standalone java application or to be integrated into a data loading process.  The
easiest way to figure how to execute this program is to run it without any arguments.  In this case, it will print out
the various options and targets.  The general idea behind this program is to do the following:

1. Place the database driver on the classpath.
2. Setup the commandline arguments to point to the kc and rice schemas.  In bundled mode this will be the same.
3. Pick any options desired such as logging level or dry run
4. Pick one of more targets
5. Execute

Note that all of the steps other than the first step happen at once on the command line.

## Supported Targets

Proposal (proposal)

This migration reads KIM information related to proposal role membership at the document-level.  These document-level
memberships are managed from the document access panel through the user interface.  The document-level membership
is removed from our existing unit based roles and moved to newly created document-level derived roles.  The membership
data will eventually be placed into the document_access table.

To look at the placement of this migration within a migration sequence see V600_085__ProposalRoleConversion in the
coeus-db-sql module.


Proposal Person Role (pprole)

This migration reads rice database parameter information related to the configuration of Proposal Person role
data.  This parameter information is eventually moved to a proposal person role table.  The parameters are then deleted
from the system as they are no longer used.  The target table for this migration is eps_prop_person_role.

To look at the placement of this migration within a migration sequence see V600_084__PropAwardPersonRoleConversion in the
coeus-db-sql module.

KRMS Question & Questionnaire Sequence (questseq)

This migration does the following:

switches the name in the krms_term_rslvr_parm_spec_t to be Question Seq ID when the name is Question ID or Question Ref ID
switches the name in the krms_term_rslvr_parm_spec_t to be Questionnaire Seq ID when the name is Questionnaire ID or Questionnaire Ref ID
switches the name in the krms_term_parm_t to be Question Seq ID when the name is Question ID or Question Ref ID and populates the val to be the proper value from the question table.
switches the name in the krms_term_parm_t to be Questionnaire Seq ID when the name is Questionnaire ID or Questionnaire Ref ID and populates the val to be the proper value from the questionnaire table.

Time & Money Document Status (tmdocstatus)

This migration supports adding a new column to the TIME_AND_MONEY_DOCUMENT table. The column should already be added via sql script, but this conversion updates that column based on the Rice KEW document header status. This ensures that the new document status column correctly reflects the status of the document for efficient queries.

Subaward Amount Info De-Duplication (subaward-amountinfo)

This migration uses some complex logic to remove duplications from the subaward amount info table. This conversion was complex enough that
it was done using Java and JDBC based conversion to avoid needing to write and test complex procedures and pl/sql. Previous to this change 
subaward copied amount infos forward to each successive subaward. This removes those items that were copied forward, leaving only the original in place. The application now reads all previous subaward amount infos for viewing and disables editing of amount infos already routed through the subaward.

This conversion, while doing the best it can, isn't 100% due to the fact that amount infos could be edited after having been previously routed. If changes have occurred to amount infos and the conversion is therefore unable to remove an expected duplicate, log messages will be printed similar to the following

```
Nov 23, 2015 1:18:03 PM org.kuali.coeus.dc.subaward.SubAwardAmountInfoDaoImpl fixSubAwardAmountInfoHistory
SEVERE: SUBAWARD-AMOUNTINFO:Cannot determine matching subaward amount info to delete. Found 0 potential matches in subaward_code(3370) subaward_id(30519) for previous subaward_amount_info_id(47970)
Nov 23, 2015 1:18:13 PM org.kuali.coeus.dc.subaward.SubAwardAmountInfoDaoImpl fixSubAwardAmountInfoHistory
SEVERE: SUBAWARD-AMOUNTINFO:Cannot determine matching subaward amount info to delete. Found 0 potential matches in subaward_code(3437) subaward_id(29785) for previous subaward_amount_info_id(49023)
Nov 23, 2015 1:18:30 PM org.kuali.coeus.dc.subaward.SubAwardAmountInfoDaoImpl fixSubAwardAmountInfoHistory
SEVERE: SUBAWARD-AMOUNTINFO:Cannot determine matching subaward amount info to delete. Found 0 potential matches in subaward_code(3617) subaward_id(29604) for previous subaward_amount_info_id(51997)
Nov 23, 2015 1:18:30 PM org.kuali.coeus.dc.subaward.SubAwardAmountInfoDaoImpl fixSubAwardAmountInfoHistory
SEVERE: SUBAWARD-AMOUNTINFO:Number of amount infos not deleted due to differences = 19
Nov 23, 2015 1:18:30 PM org.kuali.coeus.dc.subaward.SubAwardAmountInfoDaoImpl fixSubAwardAmountInfoHistory
SEVERE: SUBAWARD-AMOUNTINFO:Number of subawards with errors = 11
Nov 23, 2015 1:18:30 PM org.kuali.coeus.dc.subaward.SubAwardAmountInfoDaoImpl fixSubAwardAmountInfoHistory
SEVERE: SUBAWARD-AMOUNTINFO:Subawards in error = 3689, 3702, 3765, 3092, 3985, 2739, 3250, 3286, 3370, 3437, 3617
```

You should review these messages and cleanup duplicates within the subawards mentioned as having errors. You can also review these subawards within the app to see the potentially incorrect amounts. All records removed from the subaward_amount_info table are inserted into a subaward_amount_info_dups table. If a problem with this conversion does occur and the information needs to be restored this can be simply by executing

```
insert into subaward_amount_info select * from subaward_amount_info_dups;
```

IRB (irb)

For future use

IACUC (iacuc)

For future use


