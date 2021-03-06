package com.example.helloandroid;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView; // add import package press Ctrl-Shift-O ; then ...

public class HelloAndroid extends Activity { // ... Run > Run : Android Application
	// command-line tool - example of Creating the Project without Eclipse
	// http://developer.android.com/guide/developing/tools/index.html
	// The Android SDK includes tool "android" that can be used to create all source
	//  code and directory stubs for project, as well as an ant-compatible build.xml
	// android create project \
	//     --package com.example.helloandroid \
	//     --activity HelloAndroid \
	//     --target 2 \
	//     --path <path-to-your-project>/HelloAndroid
	// This creates the required folders and files for the project at the location
	//  defined by the path
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set a breakpoint in your source code on the line
        // double-click on the marker bar next to the source code line
        // Run > Debug History > Hello, Android
        //Object o = null;
        //o.toString();
        setContentView(R.layout.main); // res/layout/main.xml // XML layout files
        //TextView tv = new TextView(this); // "programmatic" UI layout
        //tv.setText("Hello, Android");
        //setContentView(tv);
        // http://developer.android.com/resources/tutorials/hello-world.html
        // http://developer.android.com/reference/android/app/Activity.html onCreate
        // http://developer.android.com/reference/android/view/View.html
        // = drawable object used as an element in your UI layout
        // http://developer.android.com/reference/android/widget/TextView.html
        // = subclass that handles text
        // http://developer.android.com/reference/android/content/Context.html
        // = system services: resolve resources, obtain access to db's,pref's, so on
        // = Activity class inherits from Context, you can pass this as Context
        // pass TextView to setContentView() display it as content for Activity UI.
        // If Activity doesn't call method, no UI is present ; system display blank
    	// Run > Run History > Android Activity
    } // "Hello, Android" in grey bar (or top bar) = automatically = app_name - ...
} // ... - res/values/strings.xml ; AndroidManifest.xml