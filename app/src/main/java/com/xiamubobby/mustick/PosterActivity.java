package com.xiamubobby.mustick;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Vibrator;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2014/11/24.
 */
public class PosterActivity extends Activity {
    private ViewPager pager;
    private MyPagerAdapter adapter;

    private Poster poster;

    private View posterConfigLayout;
    private View posterPreviewLayout;

    final int REQUEST_CODE = 1;

    Button imageSelectButton;
    EditText editFirst, editSecond;

    PosterPreview preview;
    Button exportButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pager_main);

        poster = Poster.getInstance();

        pager = (ViewPager) findViewById(R.id.pager);
        adapter = new MyPagerAdapter();
        pager.setAdapter(adapter);

        LayoutInflater inflater = getLayoutInflater().from(this);
        posterConfigLayout = inflater.inflate(R.layout.poster_config, null);
        posterPreviewLayout = inflater.inflate(R.layout.poster_preview, null);
    }

    public class MyPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return (view == object);
        }

        @Override
        public float getPageWidth(int i) {
            return 0.8f;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            if (position == 0) {
               container.addView(posterConfigLayout);

               imageSelectButton = (Button) findViewById(R.id.poster_select_image);
               imageSelectButton.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       Intent bgImgIntent = new Intent(Intent.ACTION_GET_CONTENT);
                       bgImgIntent.setType("image/*");
                       startActivityForResult(bgImgIntent, REQUEST_CODE);
                   }
               });

                editFirst = (EditText) findViewById(R.id.poster_text_first);
                editFirst.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {}
                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {}
                    @Override
                    public void afterTextChanged(Editable editable) {
                        poster.textFirst = editable.toString();
                        updatePreview();
                    }
                });

               return posterConfigLayout;
            }
            else if (position == 1) {
                container.addView(posterPreviewLayout);

                preview = (PosterPreview) findViewById(R.id.preview);
                preview.attachPoster(poster);

                exportButton = (Button) findViewById(R.id.export_button);
                exportButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(PosterActivity.this);
                        builder.setTitle("ahaha");
                        View dialoagView = getLayoutInflater().inflate(R.layout.poster_preview_dialog, null);
                        ImageView result = (ImageView) dialoagView.findViewById(R.id.result);
                        builder.setView(dialoagView);
                        preview.buildDrawingCache();
                        result.setImageBitmap(preview.getDrawingCache());
                        builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                            @Override
                            public void onDismiss(DialogInterface dialogInterface) {
                                preview.destroyDrawingCache();
                            }
                        });
                        final Bitmap resultBitmap = Bitmap.createBitmap(preview.getDrawingCache());
                        builder.setPositiveButton(R.string.result_export,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        FileOutputStream fos = null;
                                        try {
                                            fos = new FileOutputStream(Environment.getExternalStorageDirectory().getAbsolutePath()+"/someGay.jpg");
                                        } catch (FileNotFoundException e) {
                                            e.printStackTrace();
                                        }
                                        resultBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                                        File resultFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/someGay.jpg");
                                        Uri resultUri = Uri.fromFile(resultFile);
                                        Intent intent = new Intent();
                                        intent.setAction(Intent.ACTION_SEND);
                                        intent.putExtra(Intent.EXTRA_STREAM, resultUri);
                                        intent.setType("image/jpeg");
                                        startActivity(intent);
                                    }
                                });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                });
                return posterPreviewLayout;
            }
            else {
                return null;
            }
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            if (position == 0) {
                container.removeViewAt(position);
            }
            else if (position == 1) {

            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK)  {
            if (poster.image!=null) {
                poster.image.recycle();
            }
            InputStream stream = null;
            try {
                stream = getContentResolver().openInputStream(data.getData());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            poster.image = BitmapFactory.decodeStream(stream);
            updatePreview();
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void updatePreview() {
        preview.invalidate();
    }
}
