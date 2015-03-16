Kuali Coeus
==

Kuali Coeus (KC) for Research Administration is a comprehensive system to manage the complexities of research administration needs from the faculty researcher through grants administration to federal funding agencies. KC is using MIT’s proven COEUS system as its baseline design, updating its technical architecture for vendor independence and integration with other administration systems.


Build instructions

The Kuali Coeus application uses maven as it's build tool and can be built with a simple mvn clean install.  Inside
of Kuali Coeus's maven configuration is a list of dependencies.  Some of these dependencies may not be available in
a publicly available maven repository.  At the very least you will need to install the following projects into your
maven repo.  These projects may have more than one submodule.

* Kuali Coeus API (https://github.com/kuali/kc-api)
* Kuali Coeus S2S Gen (https://github.com/kuali/kc-s2sgen)
* Kuali Rice (https://github.com/KualiCo/rice-kc)

Grm Profile

When building Kuali Coeus you should turn the grm maven profile off.  You can do this by commenting out the activation
section of the profile or sending the following system parameter grm.off on the command line.  This profile is internal
to KualiCo.

Populating a Database

All the data needed to run Kuali Coeus is located in the coeus-db module.  They are conveniently divided into jar modules
to support any automated sql processes you may have.  The coeus-db-sql project contains sequenced sql and java files
that can build a database from scratch.  See the README file for more information.  The coeus-db-xml contains all
Kuali Coeus and Rice KEW configuration files.  These contains all the KEW files for a complete KEW configuration.
