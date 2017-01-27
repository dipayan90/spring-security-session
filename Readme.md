Spring Security Session Demo
=============

Description
------------

A simple spring security and session application that uses mongo as the authorization store.  

It supports the following :

 - An endpoint /sessionId which generates a session ID ( A random UUID ). If your session is established, you keep getting the 
   same sessionId. Session times out after 1 min of inactivity. 
 - First create a user hitting the /register endpoint Example: http://localhost:8080/register?username=dipayan&pwd=dipayan
 - Going to / will take you to a welcome screen from where you could use spring security to login
   and access a resource.
 - An option to logout once you are done. 
 - By default runs on port 8080, and has all spring boot goodness like health endpoints, metrics endpoints and other stuff.
 
Requirements
------------

1. Java 8+
2. Local mongo instance running, configurable by changing the application properties file. 

Build Image
-------------

docker build -t sessions-webapp .

Run Image
------------

docker run -it -p 8080:8080 sessions-webapp
