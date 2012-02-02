package zz.android.puzzle;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {
	public final static String tag = "MySurfaceView";
	public Bitmap p = BitmapFactory.decodeResource(getResources(), R.drawable.p1);

//	private void drawBorder(Canvas c, int x, int y, int w, int h) {
		
//	}

	public void redraw(Game game) {
		ArrayList<PieceVO> l = game.getPieces();
		PieceVO vo = null;
		Canvas c = null;
		try {
			c = this.getHolder().lockCanvas();
			for (int i = 0; i < game.total; i++) {
				vo = l.get(i);
				if (vo.isSelected) {
					c.drawBitmap(vo.getBmp(), vo.getPx(), vo.getPy(), new Paint());
					//TODO:selected
				} else {
					c.drawBitmap(vo.getBmp(), vo.getPx(), vo.getPy(), new Paint());
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			this.getHolder().unlockCanvasAndPost(c);
		}		
	}

	public void surfaceCreated(SurfaceHolder holder) {
		Canvas c = null;
		try {
			c = this.getHolder().lockCanvas();
			c.drawBitmap(p, 0, 0, new Paint());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			this.getHolder().unlockCanvasAndPost(c);
		}		
		
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}

	public MySurfaceView(Context context, AttributeSet attrs) {
		super(context, attrs);
		Log.e(tag, "MySurfaceView");
		SurfaceHolder holder = this.getHolder();
		holder.addCallback(this);
	}

}
