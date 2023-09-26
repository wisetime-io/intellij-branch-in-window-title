# Branch in Window Title

Plugin to append current Git or Subversion branch, if available, into the window title of IntelliJ IDEA -based IDEs.

The plugin is available via the JetBrains plugin repository: [https://plugins.jetbrains.com/plugin/9675-branch-in-window-title](https://plugins.jetbrains.com/plugin/9675-branch-in-window-title).

![Git Branch](doc/resources/git-screenshot.png)

![Subversion Branch](doc/resources/svn-screenshot.png)

## Automated Timekeeping for Development Teams

Branch in Window Title can be used to automate time allocation to issue tracking systems via [WiseTime](https://wisetime.com). Our engineering team uses Jira, and this is how it fits into our development workflow at WiseTime.

### Keep Calm and Flow on

I've got my brew next to me, my headphones on. Let's get some work done. I check my Jira board and start on a new ticket. I read the issue description, looks easy enough for a change! First up I need to create a new branch. There's a Create Branch link right there in Jira. Let's click it.

![Jira Issue](doc/resources/jira-issue.png)

I select the relevant repository, and choose to branch from master.

![Create Git Branch from Jira](doc/resources/jira-create-branch.png)

By default, Bitbucket will add the Jira issue number to the branch name. If I can get this branch name into my window title, WiseTime will be able to automatically tag the time I spend in IDE with the right issue. This is exactly what the Branch in Window Title extension allows me to do.

I checkout the branch and open the project in IntelliJ IDEA. While I work in my IDE, my activity starts showing up in my [private](https://wisetime.com/privacy-by-design/) timeline in the WiseTime console, tagged with the Jira issue number.

![Time Automatically Tagged in WiseTime Console](doc/resources/wisetime-console.png)

This means that I can go about my day without ever worrying about time keeping. WiseTime keeps an accurate record of what I have been doing, even if I've been multitasking like crazy (I don't recommend). At the end of the day, or week (I don't recommend!), I can review my timeline and post time to the team.

Our [WiseTime Jira Connector](https://wisetime.com/jira/) ([also open source](https://github.com/wisetime-io/wisetime-jira-connector)!) then receives the posted time and creates a worklog entry against the Jira issue.

![Time Posted to Jira Worklog](doc/resources/jira-worklog.png)

The Jira Connector also watches Jira for new issues and automatically syncs them to WiseTime as tags for automatic activity tagging. The Jira Connector is a small application that integrates with both WiseTime and Jira APIs. If you are using Jira Cloud, we allow you to provision a Jira Cloud Connector through your WiseTime team settings page. If you are running your own Jira Server, you can pull our Jira Connector Docker image and connect it to your onprem instance.

### Many Connection Options

Not using Jira? We offer many other types of [Connectors](https://wisetime.com/connectors/), including [Zapier](https://wisetime.com/zapier/). We also provide the [WiseTime Connect API](https://wisetime.com/docs/connect/), as well as a [WiseTime Connector Java Library](https://github.com/wisetime-io/wisetime-connector-java) that wraps the API. With these, you can easily implement your own custom connector.

## Using Visual Studio Code?

We've also got you covered if you use Visual Studio Code. You can install the VS Code version of our extension from [the Visual Studio Code Marketplace](https://marketplace.visualstudio.com/items?itemName=wisetime.branch-in-window-title).

## Acknowledgements

With thanks to prior work from the `mavenversion-plugin`.
