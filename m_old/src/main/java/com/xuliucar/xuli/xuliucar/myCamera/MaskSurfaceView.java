package com.xuliucar.xuli.xuliucar.myCamera;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;

import android.util.AttributeSet;
import android.util.DisplayMetrics;

import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;


public class MaskSurfaceView extends FrameLayout {

	private final MSurfaceView surfaceView;
	private final MaskView imageView;
	private int width;
	private int height;
	private int maskWidth;
	private int maskHeight;
	private final int screenWidth;
	private final int screenHeight;

	public MaskSurfaceView(Context context, AttributeSet attrs) {
		super(context, attrs);

		surfaceView = new MSurfaceView(context);
		imageView = new MaskView(context);
		this.addView(surfaceView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		this.addView(imageView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		

		WindowManager WM = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		WM.getDefaultDisplay().getMetrics(outMetrics);
		screenWidth = outMetrics.widthPixels;
		screenHeight = outMetrics.heightPixels;
		CameraHelper.getInstance().setMaskSurfaceView(this);
	}

	public void setMaskSize(Integer width, Integer height){
		maskHeight = height;
		maskWidth = width;
	}
	
	public int[] getMaskSize(){
		return new MaskSize().size;
	}

	private class MSurfaceView extends SurfaceView implements SurfaceHolder.Callback{
		private final SurfaceHolder holder;
		public MSurfaceView(Context context) {
			super(context);
			this.holder = this.getHolder();
			this.holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
			this.holder.addCallback(this);
		}

		@Override
		public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
			width = w;
			height = h;
			CameraHelper.getInstance().openCamera(holder, format, width, height, screenWidth, screenHeight);
		}

		@Override
		public void surfaceCreated(SurfaceHolder holder) {
			
		}

		@Override
		public void surfaceDestroyed(SurfaceHolder holder) {
			CameraHelper.getInstance().releaseCamera();
		}
	}

	private class MaskSize{
		private final int[] size;
		private MaskSize(){
			this.size = new int[]{maskWidth, maskHeight, width, height};
		}
	}
	
	private class MaskView extends View {
		private final Paint linePaint;
		private final Paint rectPaint;
		public MaskView(Context context) {
			super(context);
			

			//绘制中间透明区域矩形边界的Paint
			linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
			linePaint.setAntiAlias(true);// 抗锯齿
			linePaint.setDither(true);// 防抖动
			linePaint.setColor(Color.parseColor("#6F9CFB"));
			linePaint.setStyle(Style.STROKE);
			linePaint.setStrokeWidth(5f);

			//绘制四周阴影区域
			rectPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
			rectPaint.setAntiAlias(true);
			rectPaint.setDither(true);
			rectPaint.setColor(Color.GRAY);
			rectPaint.setStyle(Style.FILL);
			rectPaint.setAlpha(50);
		}
		@Override
		protected void onDraw(Canvas canvas) {
			if(maskHeight==0 && maskWidth==0){
				return;
			}
			if(maskHeight==height || maskWidth==width){
				return;
			}
			
			if((height>width&&maskHeight<maskWidth) || (height<width&&maskHeight>maskWidth)){
				int temp = maskHeight;
				maskHeight = maskWidth;
				maskWidth = temp;
			}
			
			int h = Math.abs((height-maskHeight)/2);
			int w = Math.abs((width-maskWidth)/2);
			
//			上
			canvas.drawRect(0, 0, width, h, this.rectPaint);
//			右
			canvas.drawRect(width-w, h, width, height-h, this.rectPaint);
//			下
			canvas.drawRect(0, height-h, width, height, this.rectPaint);
//			左
			canvas.drawRect(0, h, w, h+maskHeight, this.rectPaint);

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
			super.onDraw(canvas);
		}
	}

	
}
