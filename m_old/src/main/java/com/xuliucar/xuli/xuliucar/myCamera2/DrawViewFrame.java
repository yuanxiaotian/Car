package com.xuliucar.xuli.xuliucar.myCamera2;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ImageView;

public class DrawViewFrame extends ImageView{
    private int width;
    private int height;
    private int maskWidth;
    private int maskHeight;
    public DrawViewFrame(Context context, AttributeSet attrs) {  
        super(context, attrs);  
        // TODO Auto-generated constructor stub  
    }

    private final Paint linePaint = new Paint();
    {
        linePaint.setAntiAlias(true);
        linePaint.setColor(Color.parseColor("#417af1"));
        linePaint.setStyle(Style.STROKE);
        linePaint.setStrokeWidth(4f);
        linePaint.setAlpha(100);
    }

    private final Paint rectPaint = new Paint();
    {
        rectPaint.setAntiAlias(true);
        rectPaint.setColor(Color.BLACK);
        rectPaint.setStyle(Style.FILL);
        rectPaint.setAlpha(150);
    }

    public void PixSize(Integer w, Integer h){
        width = w;
        height = h;
    }
    public void setMaskSize(Integer widths, Integer heights){
        maskWidth = widths;
        maskHeight = heights;
    }

    public int[] getMaskSize(){
        return new MaskSize().size;
    }

    private class MaskSize{
        private final int[] size;
        private MaskSize(){
            this.size = new int[]{maskWidth, maskHeight, width, height};
        }
    }


    protected void onDraw(Canvas canvas) {  
        // TODO Auto-generated method stub  
        super.onDraw(canvas);  
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(dm);



        int h = Math.abs((height-maskHeight)/2);
        int w = Math.abs((width-maskWidth)/2);
        Log.i("myLog","w "+ w+" h " +h);


        //上
//        canvas.drawRect(0, 0, width, h, this.rectPaint);
////			右
//        canvas.drawRect(width-w, h, width, height-h, this.rectPaint);
////			下
//        canvas.drawRect(0, height-h, width, height, this.rectPaint);
////			左
//        canvas.drawRect(0, h, w, h+maskHeight, this.rectPaint);

        //canvas.drawRect(w, h, w+maskWidth, h+maskHeight, this.linePaint);
        //左上角
        canvas.drawLine(w,h,w+30,h,this.linePaint);// _
        canvas.drawLine(w,h,w,h+30,this.linePaint);// |
        //左下角
        canvas.drawLine(w,h+maskHeight,w,h+maskHeight-30,this.linePaint);// |
        canvas.drawLine(w,h+maskHeight,w+30,h+maskHeight,this.linePaint);// _
        //右上角
        canvas.drawLine(w+maskWidth,h,w+maskWidth-30,h,this.linePaint);// _
        canvas.drawLine(w+maskWidth,h,w+maskWidth,h+30,this.linePaint);// |
        //右下角
        canvas.drawLine(w+maskWidth,h+maskHeight,w+maskWidth,h+maskHeight-30,this.linePaint);// |
        canvas.drawLine(w+maskWidth,h+maskHeight,w+maskWidth-30,h+maskHeight,this.linePaint);// _
        
    }  
  
}  