Vaadin TestBench with JBehave
=============================

This sample demonstrates how to use Vaadin TestBench together with JBehave BDD framework to
write and run textual stories.
 * The simple "Vaadin Addressbook" example application is used as the tested application.
 * The tests use [TestBench](https://vaadin.com/add-ons/testbench) and the 
   [Page objects pattern](https://code.google.com/p/selenium/wiki/PageObjects). 
 * The BDD tests use [JBehave](http://jbehave.org/).

Running the example
-------------------
Make sure you have installed [Maven](http://maven.apache.org/) and 
[Git](http://git-scm.com/). You also need to have the 
[Firefox browser](https://www.mozilla.org/en-US/firefox/new/) installed 
in your machine where you run the tests. 

    git clone https://github.com/ripla/addressbook-jbehave
    cd testbench-jbehave
    mvn verify

License for TestBench 4
-----------------------
Vaadin TestBench 4 needs a valid license key installed in your development machine. If you don't have one you can always grap a trial key from [https://vaadin.com/addon/vaadin-testbench](https://vaadin.com/addon/vaadin-testbench)

Importing in Eclipse
--------------------
Make sure you have "Eclipse IDE for Java EE Developers" and Maven integration 
"m2e-wtp" installed. You will get Eclipse from http://eclipse.org/downloads/ and 
plugins through Help -> Eclipse Marketplace... menu

To checkout and run the project from Eclipse, do:
- File -> Import...
- Check out Maven Projects from CMS
- Choose Git from SCM menu and set URL to git://github.com/jojule/SimpleAddressbook.git
- Now you should have checked out the project
- To run it, choose Run As -> Run on Server
- Start experimenting

Note that if you are missing EGit plugin, "Maven SCM Handler for EGit" or a 
local server to run the address book on, you will be asked to install these 
while doing the above.

