# property-valuations-nyc

## Introduction

This github repository consists of the code used to clean and profile data of a property valuations data set from [NYC OpenData](https://data.cityofnewyork.us/City-Government/Property-Valuation-and-Assessment-Data/yjxr-fw8i) jointly with [Alice Wang's code](https://github.com/alicewang65/film-permits-nyc)
to analyze the relationship between the locations of film permits and the average property valuations in NYC from the year 2018. This code uses MapReduce to average property valuations by zip code, and Alice's code is used to then joined the data set together with the film permits by zip code data set. 
Together, we found a somewhat weak positive correlation between the number of film permits and average property valuation in Manhattan, concluding that neighborhoods with more expensive properties 
might attract more film permits than neighborhoods with less expensive properties, but not by a drastic amount.

## Code Structure and Descriptions

* [avgValByZip](./avgValByZip) - the files (java, class and jar) in this folder are for averaging the property valuations by zip code
    * input: cleaned data set => [cleanedData](./clean/cleanedData)
    * outputs: average valuation of the buildings in each zip code of the cleaned dataset
    * result: [avgVals](./avgValByZip/avgVals)
* [clean](./clean) - the files (java, class and jar) in this folder are for cleaning the data set to have relevant data
    * input: original data set => [Property_Valuation_and_Assessment_Data.csv](https://data.cityofnewyork.us/City-Government/Property-Valuation-and-Assessment-Data/yjxr-fw8i)
    * outputs: a new dataset containing the data from 2017/2018 and 2018/2019, and only containing the data of properties located in Manhattan
    * note: the schema of the cleaned dataset only contains [owner][building class][valuation][postcode][year][borough]
    * result: [cleanedData](./clean/cleanedData)
* [countRecs](./countRecs) - the files (java, class and jar) in this folder are for counting the number of records in the data set
    * inputs: original data set => [Property_Valuation_and_Assessment_Data.csv](https://data.cityofnewyork.us/City-Government/Property-Valuation-and-Assessment-Data/yjxr-fw8i), cleaned data set => [cleanedData](./clean/cleanedData)
    * outputs: number of records in a given input file
    * result (original data set): [recordsBeforeCleaned](./countRecs/recordsBeforeCleaned) => 9845858
    * result (cleaned data set): [recordsAfterCleaned](./countRecs/recordsAfterCleaned) => 154463

## Instructions

Note: This code requires knowledge and access to Hadoop MapReduce Jobs.

1. Starting with the original Property Valuations data set ([Property_Valuation_and_Assessment_Data.csv](https://data.cityofnewyork.us/City-Government/Property-Valuation-and-Assessment-Data/yjxr-fw8i)), run [Clean.jar](./clean/Clean.jar) in order to output the cleaned data set.
    1. It should match the results in [cleanedData](./clean/cleanedData)
2. Using the cleaned data set [cleanedData](./clean/cleanedData) you can run [avgByValZip.jar](./avgValByZip/avgByValZip.jar) to generate the average value of properties for each Manhattan zip code
    1. It should match the results in [avgVals](./avgValByZip/avgVals)
3. If you would like to reproduce the results of counting the number of records in the original and cleaned data sets, follow the steps below:
    1. Starting with the original data set([Property_Valuation_and_Assessment_Data.csv](https://data.cityofnewyork.us/City-Government/Property-Valuation-and-Assessment-Data/yjxr-fw8i)), run [CountRecs.jar](./countRecs/CountRecs.jar) to generate the count of records in the data set.
        1. It should match the results/count in [countRecsBeforeCleaned](./countRecs/recordsBeforeCleaned)
    2. Next, clean the original data set by running [Clean.jar](./clean/Clean.jar).
        1. Your output should match the results in [cleanedData](./clean/cleanedData)
    3. Finally, count the number of records in the cleaned data set by again running [CountRecs.jar](.countRecs/CountRecs.jar).
        1. Your output should match the results in [countRecsAfterCleaned](./countRecs/recordsAfterCleaned)
