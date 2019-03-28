# AppsUsageMonitor Library
Java library for android to fetch the history of usage time of application that are being used. 

![-feature-graphic](https://user-images.githubusercontent.com/41512314/55131769-f8c89900-5145-11e9-9017-b97805d8b026.png)

#  Contents 
**[Requirements](#requirements)**   
**[Features](#features)**  
**[Implementation](#implementation)**   
**[API Usage](#api-usage)**  
**[To-dos](#to-dos)**   
**[License](#license)** 

# Requirements    
1. Minimum SDK Version - API Level 21 
2. Usage Permissions - As this library mostly works on top of **UsageStatsManager**, most methods of which require the permission **android.permission.PACKAGE_USAGE_STATS**  


# Features    


# Implementation     


# API Usage   

# To-dos   
<ul>
<li>Query app usage for a specific package  </li>
<li>Return sorted list on the basis of Data Usage, App Usage Time,App Launch Counts.[currently sorted on the basis of app usage time]  </li>
<li>Calcuate Data usage of applications(Both Wifi & Mobile Data)[currently calculating only mobile data] </li>
<li>App Usage for any particular date  </li>
<li>Fetch [launch & exit] timeline of any particular app for duration of TODAY & YESTERDAY.    </li>
<li>Fetch [lauch & exit] timeline of applications in device for duration of TODAY & YESTERDAY.  </li>
<li>Implement device & particular application usage limit & notify when usage limit exceeds</li>
<li>Query for device lock-unlock count </li>

</ul>

# License   
![alt tag](https://img.shields.io/github/license/mashape/apistatus.svg)  
```
Copyright (c) 2019 TheBotBox

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
documentation files (the "Software"), to deal in the Software without restriction, including without
limitation the rights to use, copy, 
modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to 
whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions 
of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED 
TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL 
THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
CONTRACT, TORT OR OTHERWISE,ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS 
IN THE SOFTWARE.
```
