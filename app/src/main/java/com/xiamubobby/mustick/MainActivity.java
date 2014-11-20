package com.xiamubobby.mustick;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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
    Button switchRowButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtx = (EditText) findViewById(R.id.title_inp);
        mstc = (MuStickPrev) findViewById(R.id.mup);
        edtx.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {}
            @Override
            public void afterTextChanged(Editable editable) {
                mstc.setText(edtx.getText().toString());
            }
        });
        switchRowButton = (Button) findViewById(R.id.row_switch);
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

    public void switchPrevRow(View v) {
        mstc.switchTextRow();
        switchRowButton.setText(mstc.getTextRow()+"");
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
}
