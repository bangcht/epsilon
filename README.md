# Epsilon Project

## Description
An application to help online learners find e-learning courses, based on state-of-the-art recommender algorithm.

## APIs Documentation: 
https://docs.google.com/spreadsheets/d/1MJc-czKGSrXcBhLaSk2gnUj2tQGQOtmqxQxsrAql8hA/edit#gid=0

## Server side:
Using NodeJS Express framework

## Client side:
Using a clean, maintainable MVP software architecture for Android application.

## Run project 
+ Server side :
  + Run mysql server and import data in epsilon_db.sql
  + Install npm and run command :
      - npm install;
      - npm start;

+ Client side :
  + Import project Epsilon in client folder as a Android Studio project
  + Change API_URL in file utils/Constants.java to address and port from server side;
  + Build and run

## Third party libraries used in project 
+ Server side :
  + Alike: A simple-but-useful kNN library for NodeJS, comparing JSON Objects using Euclidean distances https://github.com/axiomzen/Alike

+ Client side :
  + https://github.com/blipinsk/RecyclerViewHeader
  + https://github.com/qiujuer/Genius-Android
  + https://github.com/square/picasso
  + http://jakewharton.github.io/butterknife/
      
