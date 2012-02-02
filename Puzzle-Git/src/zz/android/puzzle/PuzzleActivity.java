package zz.android.puzzle;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class PuzzleActivity extends Activity implements OnTouchListener, OnClickListener {
    public final static String tag = "JigsawActivity";
    private Button buttonStart = null;
    private Button buttonDebug = null;
    private ImageView viewShow = null;
    private ImageView viewTmp = null;
    private MySurfaceView viewWorking = null;
    //
    private Bitmap pic = null;
    private Game game = null;
    private boolean isInit = false;
    private boolean readyToSwitch = false;
    private PieceVO from = null;
    private PieceVO to = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    	pic = BitmapFactory.decodeResource(getResources(), R.drawable.p1);
        buttonStart = (Button)findViewById(R.id.buttonStart);
        buttonStart.setOnClickListener(this);
        buttonDebug = (Button)findViewById(R.id.buttonDebug);
        buttonDebug.setOnClickListener(this);
        viewShow = (ImageView)findViewById(R.id.viewShow);
        viewShow.setImageBitmap(pic);
        viewWorking = (MySurfaceView)findViewById(R.id.viewWorking);
        viewWorking.setOnTouchListener(this);
        viewTmp = (ImageView)findViewById(R.id.viewtmp);
    }

	private void initGame(int level) {
		Bitmap pp = BitmapFactory.decodeResource(getResources(), R.drawable.p1);
		game = new Game(pp, level);
		game.initGame();
		//game.showAll();
	}

	public boolean onTouch(View v, MotionEvent e) {
		if (! isInit) {
			return true;
		}
    	if (e.getAction() == MotionEvent.ACTION_DOWN) {
    		//Log.e(tag, "MotionEvent=ACTION_DOWN X=" + x + " Y=" + y);
    		if (readyToSwitch) {
        		int x = ((int)(Math.floor(e.getX()) / game.width)) * game.width;
        		int y = ((int)(Math.floor(e.getY()) / game.height)) * game.height;
        		to = game.getPieceByPos(x, y);
    			//switch
        		int tx = from.getPx();
        		int ty = from.getPy();
        		from.setPx(to.getPx());
        		from.setPy(to.getPy());
        		to.setPx(tx);
        		to.setPy(ty);
        		from.setSelected(false);
    			readyToSwitch = false;
    			viewWorking.redraw(game);
    			if (game.isFinished()) {
    				Log.e(tag, "Finished!");
    				Toast t = Toast.makeText(getApplicationContext(), "Congratulations!", Toast.LENGTH_SHORT);
    				t.setGravity(Gravity.CENTER, 0, 0);
    				t.show();
    				isInit = false;
    				game = null;
    			}
    		} else {
        		int x = ((int)(Math.floor(e.getX()) / game.width)) * game.width;
        		int y = ((int)(Math.floor(e.getY()) / game.height)) * game.height;
        		from = game.getPieceByPos(x, y);
        		from.setSelected(true);
        		viewTmp.setImageBitmap(from.getBmp());
    			readyToSwitch = true;
    			viewWorking.redraw(game);
    		}
    		//Bitmap p = Bitmap.createBitmap(pic, x, y, game.width, game.height);
    	}
    	return true;
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.buttonStart:
			new AlertDialog.Builder(this).setTitle("Please Select...").setSingleChoiceItems(
					new String[] {"Easy", "Middle", "Hard"}, 0,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							Log.e(tag, "which=" + which);
							dialog.dismiss();
							initGame(which);
							Log.e(tag, "GameInited!");
							isInit = true;
							viewWorking.redraw(game);
						}
					}).setPositiveButton("Cancel", null).show();
			break;
		case R.id.buttonDebug:
			if (isInit) {
				game.showAll();
			}
		case R.id.viewShow:
			break;
		default:
			break;
		}
	}
}