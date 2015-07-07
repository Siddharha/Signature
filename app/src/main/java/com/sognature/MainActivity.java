package com.sognature;

import android.gesture.GestureOverlayView;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {


    GestureOverlayView gestureView;
    Integer GColor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();

    }


public void clk(View view)
{
    String Name = (view).getTag().toString();
    Log.e("name --> ",Name);
    if(Name.equals("1"))
    {
        GColor = Color.BLACK;
    }
    else if(Name.equals("2"))
    {
        GColor = Color.BLUE;
    }
    else if(Name.equals("3"))
    {
        GColor = Color.RED;
    }

    Toast.makeText(getBaseContext(),"color clicked",Toast.LENGTH_SHORT).show();
    gestureView.setGestureColor(GColor);
}
    private void initialize() {
        gestureView = (GestureOverlayView) findViewById(R.id.signaturePad);
    }

    public void saveSig(View view)
    {
        try {

            gestureView.setDrawingCacheEnabled(true);
            Bitmap bm = Bitmap.createBitmap(gestureView.getDrawingCache());
            File f = new File(Environment.getExternalStorageDirectory()
                    + File.separator + "signature.png");
            f.createNewFile();
            FileOutputStream os = null;
            os = new FileOutputStream(f);
            os = new FileOutputStream(f);
            bm.compress(Bitmap.CompressFormat.PNG, 100, os);
            os.close();
            Toast.makeText(getBaseContext(),"File Saved",Toast.LENGTH_SHORT).show();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }




    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
