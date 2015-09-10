package com.lzy.mouse.view;





import com.lzy.loungercontrol.start.R;
import com.lzy.loungercontrol.until.RemoteOperateImpl;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class PuerMouseView extends SurfaceView implements SurfaceHolder.Callback {

	private String tag = this.getClass().getSimpleName();
	private boolean canDraw = false;
	
	private SurfaceHolder holder;
	private Paint mPaint;
	private Bitmap bitmapArrow;
	private Context mContext;
	private RemoteOperateImpl remoteOperateImpl = null;
	float lastX = 0;
	float lastY = 0;
	
	public PuerMouseView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		holder = getHolder();
		holder.addCallback(this);
		mPaint = new Paint();
		mContext = context;
		
		/**
		 * 鼠标形状
		 * */
		bitmapArrow = BitmapFactory.decodeResource(context.getResources()
				, R.drawable.arrow);
		
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int action = event.getAction();

		float x = event.getX();
		float y = event.getY();

		switch (action) {
		case MotionEvent.ACTION_DOWN: {
			lastX = x;
			lastY = y;
//			return dispatchTouchEvent(event);
			return true;
		}
		case MotionEvent.ACTION_UP:
			break;
		case MotionEvent.ACTION_MOVE: {
			drawCanvas(x, y);
			if (null != remoteOperateImpl) {
				float deltaX = x - lastX;
				float deltaY = y - lastY;
				if (deltaX != 0 && deltaY != 0) {
					Log.i(tag, "delta:"+deltaX+"|"+deltaY);
					remoteOperateImpl.mouseAdd(deltaX, deltaY);
				}
			}
			lastX = x;
			lastY = y;
		}
			break;
		default:
			break;
		}

		return true;
	}

	/**
	 * 绘制画布
	 * 
	 * */
	public void drawCanvas(float x, float y) {
		if (canDraw) {
			Canvas canvas = holder.lockCanvas();
			canvas.drawColor(Color.WHITE);
			canvas.drawBitmap(bitmapArrow, x, y, mPaint);
			Log.i(tag, "draw:(" + x + "," + y + ")");
			holder.unlockCanvasAndPost(canvas);
		}
		
	}
	
	@Override
	protected void finalize() throws Throwable {
		if (null!=bitmapArrow && !bitmapArrow.isRecycled()) {
			bitmapArrow.recycle();
			canDraw = false;
		}
		super.finalize();
	}
	
	
	public RemoteOperateImpl getRemoteOperateImpl() {
		return remoteOperateImpl;
	}

	public void setRemoteOperateImpl(RemoteOperateImpl remoteOperateImpl) {
		this.remoteOperateImpl = remoteOperateImpl;
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		canDraw = true;
		drawCanvas(0, 0);
		
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}

}
