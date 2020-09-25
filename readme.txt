Should be able to run jar file using java -jar quickstart-1.0.0-Production-jar-with-dependencies.jar (located in target directory), else code should run after a maven build in an IDE.

User chooses what usernames are compared, and is given answers in the terminal.

src/main/resources/twitter4j.properties has been purged of my credentials, if application doesn't work add API credentials here.

Provides overall summary at the end of user score, didn't want to re-list previous answers since user can already scroll up to see this, so just gave final score after user quits.

Used ArrayList to store cleansed tweets, could have chosen array, but this makes it a little easier to incorporate changes to the max number of tweets we can access in the future.

Tested by outputting tweets to file, making sure up to 3200 were included, then outputted cleansed strings to ensure correct cleansing procedure, then tried game with Kanye West/Elon Musk and other public figures such as Bill Gates/Mark Zuckerberg