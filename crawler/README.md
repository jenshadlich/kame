kame crawler
============

Next TODOs:
* [ ] create crawler pom
* [x] setup crawler service with dropwizard
* [ ] implement correct handling of robots.txt (in progress)
* [ ] create scheduler (service + ui)

Main components:
* domain name translation + cache
* robots exclusion + cache
* downloader
* scheduler + quota
* (priority) queue