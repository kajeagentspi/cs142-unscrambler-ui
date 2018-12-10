#CMSC142 Unscrambler
___
### Ubuntu 16.x+
>Install Java. Enter the commands one by one to accept prompts.

     sudo add-apt-repository ppa:webupd8team/java
     sudo apt update
     sudo apt install oracle-java8-installer unzip wget -y

>Install gradle 

    wget https://services.gradle.org/distributions/gradle-4.10.2-bin.zip
    sudo mkdir /opt/gradle
    sudo unzip -d /opt/gradle gradle-4.10.2-bin.zip
    export PATH=$PATH:/opt/gradle/gradle-4.10.2/bin


### Windows

>Install Java 1.8

1. Download the installer jdk-8u191-windows-x64.exe [here](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html).
2. Set the JAVA\_HOME System Variable as C:\Program Files\Java\jdk1.8.0_171.

>Install Gradle

1. Download file https://services.gradle.org/distributions/gradle-4.10.2-bin.zip
2. Unzip to C:\\.
3. Rename folder gradle-4.10.2 to gradle.
4. Add C:\gradle\bin to Environmental Variables
 ---
 ### Programming Languages Used
 * C for the solver
 * Java for the graphical user interface
 
 ---
 ### Execution
 1. Run the command make
 
 OR
 
 1. Compile the solver
    >gcc -o core/assets/unscrambler.exe lib/unscrambler.c
 2. Run the GUI
     >gradle run
 
 ### Contributors
 * Prince Alfred Ganayo
 * Jake Marbert Tagnepis