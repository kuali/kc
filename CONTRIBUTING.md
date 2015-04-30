##Contributing to Kuali Coeus
Kuali Coeus is the public, open source release of KualiCo Grants and Research Management or GRM. We are early on in the transition from a fully open source project to a commercially released version. We are still early in trying to define the process and expectations of contributions so this process is likely to change.

##Our Development Process
Our team of developers all work directly with an internal KualiCo repository. This repository is continuously deployed and tested at KualiCo and pushed to our customers. Approximately once a month we will be releasing this code to this public repository. Due to this, you can always expect master to be the most recently available public code. We will also be tagging each push with the current release number.

##Release Numbers and Tagging
At KualiCo we are focusing on moving our development processes to continuous delivery and as such the old semantic versioning no longer makes sense. All future releases will have a version following the format YYMM.buildNumber. Our most recent release, the first to follow this standard, was 1504.3. Which was the 3rd build in April 2015. The monthly releases will also be tagged using that version number. For example the most above release was tagged as coeus-1504.3.

##Contrib Branch
After each release a new contrib branch will be created from the newly updated master. This is the branch where we would like pull requests created against and where accepted PRs will be merged into. This contrib branch should not be considered the basis of local deployments or development as it will never be updated by future releases. It is only a staging area where changes can viewed by other developers. 

##Pull Requests
The KualiCo GRM developers will be monitoring for pull requests submitted against the kuali/kc and associated repositories. Each PR will be reviewed to ensure the changes are functionally acceptable and code is at least minimally acceptable. At this point the PR will be merged into the above mentioned contrib branch. The KualiCo devs will also pull the change in internally, modifying for formatting and cleaning up as necessary based on internal processes and standards and finally committing to our development pipeline ensuring all unit, integration and acceptance tests pass.

Once a pull request has been accepted we will ensure, to the best of our ability, that the functionality in the PR is included in our next monthly release, but we make no guarantees that the commits will be the same or even similar, as we need to be able to fix up the code according to our standards and match our testing methodologies, etc.

Before submitting a pull request, please make sure you have done the following
* Fork the kuali/kc repo or update your fork and then create a feature branch from master.
* If you add code that should be tested, we would highly appreciate tests.
* If your changes include functionality that might require documentation please add or link to it so it can included in the product documentation.
* Ensure the existing tests pass. Minimally all unit tests MUST pass. Optimally you have also run the full suite of coeus-it integration tests.
* Finally if you haven’t already done so, sign and submit a CLA with KualiCo.

##Contributor License Agreement ("CLA")
In order to accept your pull request, we need you to submit a CLA. You only need to do this once, but we cannot accept any part of a PR until we have the CLA on file.
