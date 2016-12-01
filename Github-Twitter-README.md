## GRID Reactive Code Test

This library will Search for "Reactive" projects on GitHub, then for each project searches for tweets that mention it.
This library outputs a summary of each project with a short list of recent tweets, in JSON format.

### Zip File
The GithubTweets.zip is a standard scala sbt project. You would need to unzip this first, then goto the directory 'GithubTweets'

### Prerequisite
One prerequisite for running this project is that sbt should be setup correctly and available on your PATH.

###Resolving Dependencies and Compiling
Please run the following command which will download the required dependencies for this project:
    
    sbt clean update compile

### Running the project
This project can be run in two modes, first with default text 'reactive' and secondly the user can supply the text themselves.
Once you are inside the directory 'GithubTweets', then you can run the following command for the default mode:
        
        sbt run

if you want to supply your own text to search for the github project , then you can run the following command:

        sbt "run-main com.workday.twitter.GithubTweet Apache Spark"

In the above command "Apache Spark" is the keyword that user provided.
Also, Please note the double quotes("") in the command which are required and you can supply any word that yould want like 
"MongoDB" or "Reactive Mongo" or "Apache Spark Scala" etc 

Thank you!
