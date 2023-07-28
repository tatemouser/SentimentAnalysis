# SentimentAnalysis

## Table of Contents

- [Project Description](#project-description)
- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [License](#license)
- [Contact](#contact)

## Project Description
This program takes in a URL as an input and outputs a window showing the percentages of 10 emotions used throughout the text. The 3 major parts of the program are the initial web scraper, the trie node tree used for matching words with the data set, and a scoring system that is displayed when the text has been scanned. The data set is a CSV file already added in the program that has a 6,500 words with 65,000 data points to indicate the strength of each emotion for each word. 

## Features

- Trie Node Tree / Prefix Tree
- Webscraper
- Updating Percentage Display
- CSV File Reader (Data set for analysis)

## Installation
Requires build path of SWT and JSOUP library located within the libs folder for display of results.

## Usage
![images](https://github.com/tatemouser/SentimentAnalysis/assets/114375692/22bd8059-3dda-43f5-a137-f7843126a2d7)

The trieNodeTree package is called first by the to create a prefix tree that adds all the words and associated data points to a tree.
Next the WordSearch class cleans the URL input and calls the scraper package which uses JSOUP to locate and copy the main text of the article. 
The Scores class is then called to scan each word and check for a match within the tree. For each match a LinkedHashMap holding scores is updated to fairly represent the frequency of each emotion.
Once scanning and comparing concludes, the Visuals class takes in the LinkedHashMap values which sums the values and divides them to create a percetage of the values out of 100. This is then presented in the window.

Input

https://www.cbsnews.com/pictures/presidents-ranked-worst-best/

Output

![Results](https://github.com/tatemouser/SentimentAnalysis/assets/114375692/76837dca-fc50-4bee-b352-0cdcca7009d6)

## License
None

## Contact
tatesmouser@gmail.com
