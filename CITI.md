*CITI Integration*

Kuali Research integrates with CITI (www.citiprogram.org) in order to populate person training.  This integration works by
downloading CITI training data into a staging table.  The staging table is processed and validated.  Valid records are
converted (using configured mapping information) to person training records.  Error messages are persisted for invalid
records.  These error messages can be queried through standard maintenance screen from within the System Admin section
of the application.
 
In order to use CITI integration a few requirements must be met:

The *citi.endpoints* xml parameter must be configured to point to one or more CITI url.  If using more than one CITI url,
each url should be comma separated.

The *citi.delimiter* xml parameter must be configured to the correct delimiter used in the CITI dataset.  By default, this
parameter is configured to a tab character for tab delimited files.  A comma is also common as a delimiter for CITI data.

The *citi.header.label.xxxx* xml parameters must be configured if any CITI data headers are customized in the CITI dataset.
These parameters are configured to the standard default values used by CITI.

*Required CITI Data*

All CITI datasets must be configured with the following fields for integration to work without error:

Curriculum Number (CR Number)
Curriculum
Group Id
Group
Passing Score
Stage Number
Stage
Institutional Username

To map CITI training data to Person Training records, mapping entries must be created.  These records map the combination
of group, curriculum, and stage to training code.  The maintenance table for this is located within the System Admin
section of the Kuali Research portal. If mapping data is not present and CITI integration is turned on mapping records
will automatically be created without training codes selected.


To turn on the CITI batch job which starts the integration process, configure the following database parameters:

citi.job.cronExpression
citi.job.enabled

By default, CITI integration is turned off.  Note that currently citi.job.enabled also controls whether the CITI
maintenance links are visible. 
