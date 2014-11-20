package com.xiamubobby.mustick;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2014/11/13.
 */
public class MuStickPrev extends View {

    Bitmap b;
    String text;
    int textRowCount = 1;
    int bgc;
    float imgTranslationX = 0;
    float imgTranslationY = 0;
    Timer timer;

    public MuStickPrev(Context context) {
        super(context);
        init();
    }

    public MuStickPrev(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MuStickPrev(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
    }

    public void setImg(Bitmap barg) {
        b = barg;
        imgTranslationX = 0;
        imgTranslationY = 0;
        invalidate();
    }
    public void setText(String targ) {
        text = targ;
        invalidate();
    }
    public void switchTextRow() {
        textRowCount = (textRowCount ==1)?2:1;
        invalidate();
    }
    public int getTextRow() {
        return textRowCount;
    }
    public void setBgColor(int carg) {
        bgc = carg;
        invalidate();
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();

        //drawBack~
        canvas.drawARGB(255, 0, 0, 0);

        //drawPic!
        if (b != null) {
            canvas.drawBitmap(b, null, new RectF(
                    ((float) (canvas.getWidth()*0.1 + imgTranslationX)),
                    ((float) (canvas.getHeight()*0.1 +  imgTranslationY)),
                    ((float) (canvas.getWidth()*0.9 + imgTranslationX)),
                    ((float) (b.getHeight() * (canvas.getWidth()*0.8) / b.getWidth() + canvas.getWidth()*0.1 + imgTranslationY))),
                    paint);
        }

        //drawFrames-_-
        paint.setARGB(255,0,0,0);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawRect(
                0,
                0,
                ((float) (canvas.getWidth()*0.1)),
                ((float) (canvas.getHeight())),
                paint);
        canvas.drawRect(
                ((float) (canvas.getWidth()*0.9)),
                0,
                ((float) (canvas.getWidth())),
                ((float) (canvas.getHeight())),
                paint);
        canvas.drawRect(
                0,
                0,
                ((float) (canvas.getWidth())),
                ((float) (canvas.getHeight()*0.1)),
                paint);
        canvas.drawRect(
                0,
                ((float) (canvas.getHeight()*0.7)),
                ((float) (canvas.getWidth())),
                ((float) (canvas.getHeight())),
                paint);

        //drawText :)
        paint.setARGB(255, 255, 255, 255);
        paint.setTextAlign(Paint.Align.CENTER);
        Paint.FontMetrics fm = paint.getFontMetrics();
        if (text != null && text != "") {
            if (textRowCount ==1) {
                float s = ((float) (canvas.getWidth() * 0.85 / text.length()));
                paint.setTextSize((s < 85) ? s : 85);
                Log.v("font", paint.getTextSize() + "");
                paint.getFontMetrics(fm);
                canvas.drawText(text,
                        canvas.getWidth() / 2,
                        ((float) (canvas.getHeight() * 0.85 - fm.descent + (fm.descent - fm.ascent) / 2)),
                        paint);
            }
            if (textRowCount ==2) {
                paint.setTextSize(50);
                paint.getFontMetrics(fm);
                canvas.drawText(text,
                        canvas.getWidth() / 2,
                        ((float) (canvas.getHeight() * 0.85 - 35 - fm.descent + (fm.descent - fm.ascent) / 2)),
                        paint);
                canvas.drawText(text,
                        canvas.getWidth() / 2,
                        ((float) (canvas.getHeight() * 0.85 + 35 - fm.descent + (fm.descent - fm.ascent) / 2)),
                        paint);

            }
        }
    }
    final Handler _handler = new Handler();
    Runnable _longPressed = new Runnable() {
        public void run() {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("ahaha");
            View dialoagView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.mustickprev_dialog, null);
            ImageView result = (ImageView) dialoagView.findViewById(R.id.result);
            builder.setView(dialoagView);
            buildDrawingCache();
            result.setImageBitmap(getDrawingCache());
            builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialogInterface) {
                    destroyDrawingCache();
                }
            });
            final Bitmap resultBitmap = Bitmap.createBitmap(getDrawingCache());
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
                            Intent itnt = new Intent();
                            itnt.setAction(Intent.ACTION_SEND);
                            itnt.putExtra(Intent.EXTRA_STREAM, resultUri);
                            itnt.setType("image/jpeg");
                            getContext().startActivity(itnt);
                        }
                    });
            AlertDialog dialog = builder.create();
            dialog.show();
            Vibrator vibrator = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(15L);
        }
    };
    float oldX, oldY, oldTrX, oldTrY;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                _handler.postDelayed(_longPressed, 500);

                oldX = event.getX();
                oldY = event.getY();
                oldTrX = imgTranslationX;
                oldTrY = imgTranslationY;

                return true;
            case MotionEvent.ACTION_MOVE:
                _handler.removeCallbacks(_longPressed);
                imgTranslationX = oldTrX + event.getX() - oldX;
                imgTranslationY = oldTrY + event.getY() - oldY;
                invalidate();
                return true;
            case MotionEvent.ACTION_UP:
                _handler.removeCallbacks(_longPressed);
                return true;
            default:
                return true;
        }
    }

}
