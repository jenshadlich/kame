kame crawler
============

Next TODOs:
* [ ] create crawler pom to manage all dependencies
* [x] setup crawler service with dropwizard
* [ ] implement correct handling of robots.txt (in progress)
* [ ] create scheduler (service + ui) (in progress)
* [x] split crawler-core, e.g. http client stuff or constants to a common module

Main components:
* domain name translation + cache
* robots exclusion + cache
* downloader
* scheduler + quota
* (priority) queue

