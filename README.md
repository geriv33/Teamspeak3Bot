![GitHub last commit](https://img.shields.io/github/last-commit/Backxtar/Teamspeak3Bot?color=%230091ff)
[![GitHub issues](https://img.shields.io/github/issues/Backxtar/Teamspeak3Bot?color=%23fc3003)](https://github.com/Backxtar/Teamspeak3Bot/issues)
![GitHub repo size](https://img.shields.io/github/repo-size/Backxtar/Teamspeak3Bot?color=%233aa63a)
[![GitHub all releases](https://img.shields.io/github/downloads/Backxtar/Teamspeak3Bot/total?color=%233aa63a&label=download)](https://github.com/Backxtar/Teamspeak3Bot/releases)

<img align="right" src="https://mlpfyvpfyq69.i.optimole.com/cZ9PPCo-D60oTdJD/w:auto/h:auto/q:auto/https://gameserververgleich.info/wp-content/uploads/TeamSpeak-Server-mieten.png" height="200" width="200">
# Teamspeak 3 Bot
Quick manual for setting up the TS3Bot on Linux servers. Make sure your server has enough RAM and a good connection.
## Summary
I do only support my own software! This software is free to use.

1. [Minimum System Requirements](#mininum-system-requirements)
2. [Install Java on Debian or Ubuntu Systems](#installing-java-on-debian-or-ubuntu-systems)
3. [Install Screen on Debian or Ubuntu Systems](#installing-screen-on-debian-or-ubuntu-systems)
4. [Edit config.cfg](#edit-configcfg)
5. [Run the bot](#run-the-bot)

## Mininum System Requirements
* **RAM:** 4Gb
* **CPU:** 1 Core
* **Internet:** 100mbit/s
* **Java 1.8.0_282**
* **Screen**

[Java 1.8.0_282](https://www.oracle.com/de/java/technologies/javase/javase-jdk8-downloads.html) or above is needed.
## Installing Java on Debian or Ubuntu Systems
**Step 1:** _Check which version of the JDK your system is using:_
```java
java -version
```
If the OpenJDK is used, the results should look like:
```java
openjdk version "1.8.0_292"
OpenJDK Runtime Environment (build 1.8.0_292-8u292-b10-0ubuntu1~18.04-b10)
OpenJDK 64-Bit Server VM (build 25.292-b10, mixed mode)
```
If Oracle Java is used, the results should look like:
```java
java version "1.8.0_241"
Java(TM) SE Runtime Environment (build 1.8.0_292-b07)
Java HotSpot(TM) 64-Bit Server VM (build 25.292-b07, mixed mode)
```
**Step 2:** _Update the repositories:_
```java
sudo apt-get update
```
**Step 3:** _Install OpenJDK_
```java
sudo apt-get install openjdk-8-jdk
```
The latest JDK is installed.

**Step 4:** _Verify the version of the JDK:_
```java
java -version
```
```java
openjdk version "1.8.0_292"
OpenJDK Runtime Environment (build 1.8.0_292-8u292-b10-0ubuntu1~18.04-b10)
OpenJDK 64-Bit Server VM (build 25.292-b10, mixed mode)
```
**Step 5:** _If the correct version of Java is not being used, use the alternatives command to switch it:_
```java
sudo update-alternatives --set java /usr/lib/jvm/jdk1.8.0_version/bin/java
```
**Step 6:** _Verify the version of the JDK:_
```java
java -version
```
```java
openjdk version "1.8.0_292"
OpenJDK Runtime Environment (build 1.8.0_292-8u292-b10-0ubuntu1~18.04-b10)
OpenJDK 64-Bit Server VM (build 25.292-b10, mixed mode)
```
## Installing Screen on Debian or Ubuntu Systems
**Step 1:** _Update the repositories:_
```java
sudo apt-get update
```
**Step 2:** _Install Screen_
```java
sudo apt-get install screen
```
## Edit config.cfg
Your config.cfg will look like this:
```java
# Bot configuration
# Comments will automatically be excluded
        
host=77.777.777.77
username=YourQueryUser
password=YourQueryPassword
nickname=TS3Bot
```
Change the values to your server settings!
## Run the bot
**Step 1:** _Navigate to your folder the .jar is in_
```java
cd folder/folder/...
```
**Step 2:** _Make a screen instance_
```java
screen -S "Screenname"
```
**Step 3:** _Execute the .jar_
```java
java -jar Teamspeak3Bot.jar
```