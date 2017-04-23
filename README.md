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

2.You might have new version of Firefox and Webdriver downgrade to version 30 for full page screenshots

3.Also if you run this program for a while, Chegg might block u as part of spam detection and might think your account is Compromised.
In this case you need to reset your username and password and run the program again.

Note:
It might not work if Chegg updates their html. If they update, you need to update the xpaths of the different elements in `SiteDetails.properties`
Also the error conditions haven't been handled thoroughly. This program however will work as of Mar 27 2015.

Running in Intellij Idea
========================
- I have used intellij idea and tried it out.
- It needs a selenium webdriver dependency.You can check pom.xml for its dependency list.
- To download the dependencies, you need to use maven (you might need to install if u dont have maven already on your system)
- Then its as simple as going into the root folder and running "mvn clean compile".
- Then right click on  "CheggDownloadRunner.java" and run it
