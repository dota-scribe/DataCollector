# DataCollector

# Extracting Premium games from datdota

* Create the Dota Scribe DB by running the setup script at `src/sql/Create.sql`
* Install the Java Runtime Environment from [Java for Windows](https://www.java.com/en/download/win10.jsp)
* Make sure that the java bin folder is added to your path
* Open `cmd` navigate to the Data Collector folder and run `java -jar DataCollector.jar`
* At the prompt enter `6` to Collect Premium Matches (DatDota) prompt will only return in the event of an error. Once this has run 5000 matches should be loaded into the `PremiumMatch` table in SQL
* run `java -jar DataCollector.jar` again and select `7` to Sync Premium Match Data. This will start to load the full match data for the premium matches one by one. 

Let me know if you have any issues! 

