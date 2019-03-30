# AppsUsageMonitor Library   [![Tweet](https://img.shields.io/twitter/url/http/shields.io.svg?style=social)](https://twitter.com/intent/tweet?text=&via=the_botbox&hashtags=API,UsageStatsManager,android)
Java library to detech time spend on applications in an android device. 

![-feature-graphic](https://user-images.githubusercontent.com/41512314/55276380-27e33400-5319-11e9-9282-100fce32653a.jpg)

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
<ul>
<li>Returns the list of all the application used in the device.</li>
<li>Returns the total duration for which the applications are being used.</li>
<li>Returns the total number of times the application is launched.</li>  
<li>Returns the amount of mobile data consumed by the application.</li>   
<li>Returns the timestamp on which the application was last launched.</li>      
<li>Returns the total time spend on device [calculates only the time spend on application & not idle screen time]</li>      
<li>Returns all the above mentioned usage data that can be filtered on the basis of Duration i.e. for TODAY, YESTERDAY, WEEK, MONTH </li>
<li>Code Commenting</li>          
</ul>


# Implementation     
Library is available on JCenter, simply add the following line in your app `build.gradle` 
```
implementation compile 'the.bot.box:monitor:{latest-version}'
```  
where `{latest-version}` corresponds to latest published version [ ![Download](https://api.bintray.com/packages/boxbotbarry/maven/appusagemonitor/images/download.svg) ](https://bintray.com/boxbotbarry/maven/appusagemonitor/_latestVersion)  



# API Usage   
First and foremost,in order to fetch list of used applications, we need to secure USAGE ACCESS permission from the user.
For that, declare below line in the application's ```Manifest.xml``` 
```   
       <uses-permission
        android:name="android.permission.PACKAGE_USAGE_STATS"
        tools:ignore="ProtectedPermissions" />

```

& to check, if the permission is granted or not,call 

```  
          if (Monitor.hasUsagePermission())
        else
            Monitor.requestUsagePermission();
```

Once, permission has been granted, one can easily fetch the list of application used by calling,
```
            Monitor.scan().getAppLists(this).fetchFor(Duration.TODAY);
```
where ```.getAppList(UsageContracts.View mView)``` returns a callback in your view once the list has been fetched.
   
further ```.fetchFor()``` requires the duration for which you want to query for usage details.   
As of now, one can chose from 
```Duration.TODAY```, ```Duration.YESTERDAY```, ```Duration.WEEK```, ```Duration.MONTH```    

Final Implementation of the UsageLibrary may look like below:   
```
    public class MainActivity extends AppCompatActivity implements UsageContracts.View{
    
     @Override
    protected void onResume() {
        super.onResume();
        if (Monitor.hasUsagePermission()) {
            Monitor.scan().getAppLists(this).fetchFor(Duration.TODAY);
            init();
        } else {
            Monitor.requestUsagePermission();
        }
    }
    
    @Override
    public void getUsageData(List<AppData> usageData, long mTotalUsage, int duration) {
      /**
       * 
       * @param usageData list of application that has been within the duration for which query has been made.
       * @param mTotalUsage a sum total(in millis) of the usage by each and every app with in the request duration. 
       * @param the same duration for which query has been made i.e.fetchFor(Duration...)
       */
    }    
    
    @Override
    public void showProgress() {}

    @Override
    public void hideProgress() {}
}
```   
```mTotalUsage``` returns total usage time in milliSeconds. To convert into string format, use library's in-built function i.e. ```UsageUtils.humanReadableMillis(mTotalUsage)```  

```List<AppData> usageData``` list of used apps with usage stats. For futher details on ```AppData``` model, please dive into 
[AppAdapter.java](https://github.com/TheBotBox/AppsUsageMonitorAPI/blob/master/app/src/main/java/com/example/appusage/AppAdapter.java)   

<u>Have a look on final output:</u>    
<img src="https://github.com/TheBotBox/AppsUsageMonitorAPI/blob/master/snapshots/shot_1.png" width="200">
<img src="https://github.com/TheBotBox/AppsUsageMonitorAPI/blob/master/snapshots/shot_2.png" width="200">
<img src="https://github.com/TheBotBox/AppsUsageMonitorAPI/blob/master/snapshots/shot_3.png" width="200">


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
<li>To Query for both system and installed applications or either one of them </li>
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

[ ![](https://img.shields.io/badge/Say%20Thanks-!-1EAEDB.svg) ](https://saythanks.io/to/TheBotBox)
