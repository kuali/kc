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


IRB (irb)

For future use

IACUC (iacuc)

For future use


