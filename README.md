# Gamezop - How to Implement Chrome Custom Tabs (CCT)

Chrome Custom Tabs is a way to incorporate web content within your Android app. It allows you, as an app developer, to leverage the powerful rendering of Google Chrome for the web content running within your app.

You can get to know more about Chrome Custom Tabs, and detailed implementation notes, in their [official documentation here.](https://developer.chrome.com/docs/android/custom-tabs/overview/)

<br><br>

## 1. Why use CCT over WebView?
<br>

There are a few reasons we strongly recommend using CCT over the Android WebView:

- **Better gaming experience:** HTML5 games require heavy-duty graphics rendering. CCT directly uses Chrome to run web content - allowing it to leverage the latest updates from Chrome all the time. In comparison, Android WebView (especially in older Android versions) has different release cycles and may often be running older versions of the Javascript rendering engine. This can cause the games running in Android WebView to lag - whereas the gaming experience is very smooth in a CCT implementation.

- **Better advertising revenues:** Gamezop uses a mix of ad networks to show ads when users play games. This is the advertising revenue shared with you. Ad networks use cookies / browsing history other signals from the browser to show more relevant ads to users. Since CCT uses Chrome under the hood, when you open Gamezop in a CCT, it allows the ad networks we work with to access a much richer set of signals which can be used to show more relevant ads to users. This leads to better advertising revenue, meaning a higher share of revenue for you.

There are a whole host of other reasons to use CCT over WebView which you can read [here.](https://developer.chrome.com/docs/android/custom-tabs/overview/#when-should-i-use-custom-tabs-vs-webview)

<br><br>

## 2. How to implement CCT in Android
<br>
Follow the steps given below closely to get a perfectly working CCT implementation in your app:
<br><br>

**STEP 1 - Update the `build.gradle` file:** There are 2 `build.gradle` files in your app project. One in the root directory and the second `build.gradle` file is in the `android/app/` folder of your project. You have to make an update in the `android/app/build.gradle` file. Within that file, within the `dependencies` block, include the following line:

```
implementation 'androidx.browser:browser:1.3.0'
implementation 'com.google.guava:listenablefuture:9999.0-empty-to-avoid-conflict-with-guava'
```
1.3.0 is the latest CCT stable release at the time of writing. When you implement this, be sure to check for the latest stable release version [here.](https://developer.android.com/jetpack/androidx/releases/browser)
<br><br><br>
**STEP 2 - Initialise CCT in your `onCreate` method:** You may have a particular Android Activity where you place the Gamezop icon / banner. When a user taps on that icon / banner, you want to open your Gamezop URL within a CCT. Within the `onCreate` method of this Activity, you will have to define a click listener for that particular Gamezop icon / banner, which will trigger the CCT. 

<details>
<summary>Here's a sample code block for this in Kotlin:</summary>

```
override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cctButton = findViewById<Button>(R.id.openGamezopButton)
        cctButton.setOnClickListener {
            val url = "https://www.gamezop.com/?id=xyz"; //BE SURE TO INSERT YOUR GAMEZOP PUB ID INSTEAD OF xyz HERE
            val customTabsIntent: CustomTabsIntent  = CustomTabsIntent.Builder().build();
            customTabsIntent.launchUrl(this, Uri.parse(url));
        }
    }
```

</details>
<br>
<details>
<summary>Here's the same sample code block in Java:</summary>

```
override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        final cctButton = findViewById(R.id.openGamezopButton)
        cctButton.setOnClickListener {
            final url = "https://www.gamezop.com/?id=xyz"; //BE SURE TO INSERT YOUR GAMEZOP PUB ID INSTEAD OF xyz HERE
            final CustomTabsIntent customTabsIntent  = CustomTabsIntent.Builder().build();
            customTabsIntent.launchUrl(this, Uri.parse(url));
        }
    }
```
</details>
<br><br>

**STEP 3 - Make the relevant `xml` changes:** Within the `res/layout/` folder, find the `.xml` file corresponding to the Activity within which you want to place the Gamezop icon / banner. You will have to write the XML for the Button / Image widget depending on how you want to place the Gamezop icon / banner within your UI. Just ensure that the `android:id` attribute of that widget is set as `android:id="@+id/openGamezopButton"`

That's all you need to do to be able to open Gamezop within your Android app in a CCT. **Once again, please ensure that you use your Gamezop Pub ID instead of the sample in the code above.** [Here's](https://www.github.com/gamezop/cct-setup) a sample codebase with a working implementation that you can use as a reference.

<br><br>

## 3. FAQs around using CCT

<details>
    <summary>1. What if the user does not have Chrome installed?</summary>
<br>
In this case, the URL that is supposed to open in the CCT will open in the user's device default browser.
</details>
<br>
<details>
    <summary>2. Can the UI of the CCT be customised?</summary>
<br>
Yes, to a certain degree. You can read more about it [here.](https://developer.chrome.com/docs/android/custom-tabs/integration-guide/#configure-the-color-of-the-address-bar)

</details>
