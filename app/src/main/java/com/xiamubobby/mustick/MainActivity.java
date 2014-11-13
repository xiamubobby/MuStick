package com.xiamubobby.mustick;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;


public class MainActivity extends Activity {

    Bitmap b;
    final int REQUEST_CODE = 1;
    ImageView imgv;
    TextView txtv;
    EditText edtx;
    MuStickPrev mstc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgv = (ImageView) findViewById(R.id.canvas);
        txtv = (TextView) findViewById(R.id.title);
        edtx = (EditText) findViewById(R.id.title_inp);
        mstc = (MuStickPrev) findViewById(R.id.mup);
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

    public void picSelButClicked(View v) {
        Intent bgImgIntent = new Intent(Intent.ACTION_GET_CONTENT);
        bgImgIntent.setType("image/*"); // intent type to filter application based on your requirement
        startActivityForResult(bgImgIntent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK)  {
            if (b!=null) {
                b.recycle();
            }
            InputStream stream = null;
            try {
                stream = getContentResolver().openInputStream(data.getData());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            b = BitmapFactory.decodeStream(stream);
            //imgv.setImageBitmap(b);
            mstc.setImg(b);
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void titleRef(View v) {
        Log.v("getT", edtx.getText().toString());
        mstc.setTitle(edtx.getText().toString());
    }
}
