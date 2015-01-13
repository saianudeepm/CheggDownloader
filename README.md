Chegg Downloader:
=================

Have a Chegg account and you want to download the solutions of a book locally?
fill in the required url details in the SiteDetails.properties file and run the CheggDownloadRunner.java

How to Run the program for your Book
====================================
To make it run for your book, you need to update the necessary details in the SiteDetails.properties.

Do not forget to modify the following:

bookName
username
password
downloadUrl

Note:
It might not work if Chegg updates their html. If they update, you need to update the xpaths of the different elements in SiteDetails.properties
Also the error conditions havent been handled thoroughly. This program however will work as of jan 13 2015.
