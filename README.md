Code Demos
=================================================

These are the code demos prepared for CS 212 Software Development at the University of San Francisco. Please see the [course website](http://cs212.cs.usfca.edu) for links to lecture slides and videos.

## Eclipse Setup ##

You may checkout these examples as Eclipse projects, and receive automatic updates as the examples are modified. To do this, use the following steps:

1.  If you have not already, add the following repository URL to your "SVN Repository Exploring" perspective in Eclipse:

    > https://github.com/cs212/demos

2. In the "SVN Repository Exploring" perspective, open the `trunk` subdirectory within the `demos` repository, and find the topic subdirectory you wish to checkout. Right-click the subdirectory and select "Checkout..." from the menu.

3. Make sure "Check out as a new project configured using the New Project Wizard" option is selected, and then click the "Finish" button.

4. On the next dialog window, select "Java Project" and click the "Next" button.

5. Keep the code demos up-to-date by periodically performing a `svn update` command. You will find this option when you right-click your root project folder and select the "Team" submenu.

You may also need to add third-party libraries to your build path after this setup. See the [CS Tutoring Center Resources](http://tutoringcenter.cs.usfca.edu/resources/adding-user-libraries-in-eclipse.html) for specific steps.

:octocat: Of course, you may also use `git` to grab this entire repository! However, the Eclipse setup is up to you.
