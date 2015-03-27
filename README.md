Chegg Downloader:
=================

Have a Chegg account and you want to download the solutions of a book locally?

Fill in the required url details in the `SiteDetails.properties` file and run the `CheggDownloadRunner.java`

How to Run the program for your Book
====================================
To make it run for your book, you need to update the necessary details in the `SiteDetails.properties`. Modify the following:

- bookName
- username
- password
- downloadUrl

Problems/Modifications that might occur:
========================================
1.Chegg might modify the xpath values in their site. In this case you need to update the xpaths in `SiteDetails.properties`

2.You might have new version of Firefox and Webdriver version might be out of date. In that case please update `selenium-java` version in `pom.xml`

3.Also if you run this program for a while, Chegg might block u as part of spam detection and might think your account is Compromised.
In this case you need to reset your username and password and run the program again.

Note:
It might not work if Chegg updates their html. If they update, you need to update the xpaths of the different elements in `SiteDetails.properties`
Also the error conditions haven't been handled thoroughly. This program however will work as of Mar 27 2015.
