##Contributing to Kuali Research

##Our Development Process
Our team of developers all work directly with an internal private repository. This repository is continuously deployed and tested. Builds are frequently pushed to hosted customers. We release this code to this public repository monthly. You can always expect master to be the most recently available public code. We also tag each push with the current release number.

##Release Numbers and Tagging
Kuali practices agile developent and continuous delivery. Semantic versioning no longer makes sense. Releases have a version following the format YYMM.buildNumber. The monthly releases are tagged using that version number. The first of these monthly releases was tagged as coeus-1504.3.

##Contrib Branch
A long lived contrib branch is available. This is the branch that pull requests should be created against. This contrib branch should not be considered the basis of local deployments or development as it will never be updated by future releases. It is only a staging area where changes can viewed by other developers.

##Pull Requests
The Kuali Research developers monitor pull requests submitted against the kuali/kc and associated repositories. Each PR is reviewed to ensure the changes are functionally acceptable and code is at least minimally acceptable. At this point the PR will be merged into the above mentioned contrib branch. The Kuali devs will also pull the change in internally, modifying for formatting and cleaning up as necessary based on internal processes and standards and finally committing to our development pipeline ensuring all unit, integration and acceptance tests pass.

Once a pull request has been accepted we will ensure, to the best of our ability, that the functionality in the PR is included in our next monthly release, but we make no guarantees that the commits will be the same or even similar, as we need to be able to fix up the code according to our standards and match our testing methodologies, etc.

Before submitting a pull request, please make sure you have done the following
* Fork the kuali/kc repo or update your fork and then create a feature branch from master.
* If you add code that should be tested, we would highly appreciate tests.
* If your changes include functionality that might require documentation please add or link to it so it can included in the product documentation.
* Ensure the existing tests pass. Minimally all unit tests MUST pass. Optimally you have also run the full suite of coeus-it integration tests.
* Finally if you haven’t already done so, sign and submit a CLA with Kuali.

##Contributor License Agreement ("CLA")
In order to accept your pull request, we need you to submit a CLA. You only need to do this once, but we cannot accept any part of a PR until we have the CLA on file.

## Functional Standards
##Introduction
The following standards outline at a high level the expectations of Kuali and the community subcommittees. Contributions to the Kuali codebase should follow these standards in order to maintain the highest level of UI quality possible in the product.  These standards are meant to be broad principles that could apply to a wide variety of contributions without stymieing the process of bringing new features into the application.  Each standard in this document is based on experience with a given contribution.  Additional standards may be added as we gain more experience with the contribution process.  This should both guide schools who want to contribute code, Kuali when they are deciding how to integrate contributed features, and subcommittee co-chairs when reviewing contributions and requesting revisions to contributed functionality.

##Standard 1 - Naming and Terminology
Contributions will use existing terminology for elements which mirror functionality that exists elsewhere in the application.  Where possible they will remain consistent with terminology within the module the functionality is enhancing.  If there is no similar terminology within the applicable module then enhancements will be consistent with terminology from other KC modules.

##Standard 2 - User Interface/User Interaction
Contributions will be built to utilize common user interactions that exist in the system and have been set as standards for future functionality.  Until all the modules are re-written and have the same interaction model, enhancements will honor the existing user interactions for the module they are a part of.  If a contribution is specifically designed to improve on the existing interface model it will be built to avoid UI regressions where possible.

##Standard 3 - Existing Fields
Contributions will not repurpose existing fields in ways that are inconsistent with their current design.
