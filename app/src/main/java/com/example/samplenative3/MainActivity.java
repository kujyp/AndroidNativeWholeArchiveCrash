package com.example.samplenative3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static boolean sNativeLibraryLoaded = false;

    // Used to load the 'native-lib' library on application startup.
    static {
        try {
            System.loadLibrary("native-lib");
            sNativeLibraryLoaded = true;
        } catch (UnsatisfiedLinkError e) {
            sNativeLibraryLoaded = false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        TextView tv = findViewById(R.id.sample_text);
        tv.setText(stringFromJNI());
    }

    private String stringFromJNI() {
        if (!sNativeLibraryLoaded) {
            return "error";
        }
        return internalStringFromJNI();
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String internalStringFromJNI();
}
