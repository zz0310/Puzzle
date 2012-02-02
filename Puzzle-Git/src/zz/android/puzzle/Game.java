package zz.android.puzzle;

import java.util.ArrayList;
import java.util.Random;

import android.graphics.Bitmap;
import android.util.Log;

public class Game {
	public final static String tag = "Game";
	public final static int GAME_LEVEL_EASY = 0;
	public final static int GAME_LEVEL_MIDDLE = 1;
	public final static int GAME_LEVEL_HARD = 2;
	public final static int PICTURE_SIZE_WIDTH = 450;
	public final static int PICTURE_SIZE_HEIGHT = 450;
	public Bitmap bmp = null;
	public int count = 0;
	public int total = 0;
	public int width = 0;
	public int height = 0;
	public ArrayList<PieceVO> pieces = new ArrayList<PieceVO>();

	public Game(Bitmap bmp, int c) {
		//Log.e(tag, "bmp size width=" + bmp.getWidth() + " height=" + bmp.getHeight());
		switch (c) {
		case GAME_LEVEL_EASY:
			count = 5;
			break;
		case GAME_LEVEL_MIDDLE:
			count = 6;
			break;
		case GAME_LEVEL_HARD:
			count = 9;
			break;
		default:
			count = 5;
			break;
		}
		total = count * count;
		width = PICTURE_SIZE_WIDTH / count;
		height = PICTURE_SIZE_HEIGHT / count;
		int x = 0;
		int y = 0;
		int id = 0;
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < count; j++) {
				PieceVO vo = new PieceVO(id);
				x = i * width;
				vo.setX(x);
				y = j * height;
				vo.setY(y);
				vo.setBmp(Bitmap.createBitmap(bmp, x, y, width, height));
				pieces.add(vo);
				id++;
			}
		}
	}

	public void initGame() {
		Random r = new Random(System.currentTimeMillis());
		int ri = r.nextInt(total);
		PieceVO vo = pieces.get(0);
		vo.setP(ri);
		vo.setPx(pieces.get(ri).x);
		vo.setPy(pieces.get(ri).y);
		boolean ok = false;
		for (int i = 1; i < total; i++) {
			vo = pieces.get(i);
			while (! ok) {
				ri = r.nextInt(total);
				ok = true;
				for (int j = 0; j < i; j++) {
					if (pieces.get(j).p == ri) {
						ok = false;
					}
				}
			}
			vo.p = ri;
			vo.setPx(pieces.get(ri).x);
			vo.setPy(pieces.get(ri).y);
			ok = false;
		}
	}

	public void setPiecePosById(int i, int x, int y) {
		PieceVO vo = pieces.get(i);
		vo.setPx(x);
		vo.setPy(y);
	}

	public PieceVO getPieceById(int id) {
		return pieces.get(id);
	}

	public PieceVO getPieceByPos(int x, int y) {
		PieceVO vo = null;
		for (int i = 0; i < total; i++) {
			//vo = pieces.get(i);
			if ((pieces.get(i).px == x) && (pieces.get(i).py == y)) {
				vo = pieces.get(i);
				break;
			}
		}
		return vo;
	}

	public boolean isFinished() {
		for (int i = 0; i < total; i++) {
			PieceVO vo = pieces.get(i);
			if ((vo.x != vo.px) || (vo.y != vo.py)) {
				return false;
			}
		}
		return true;
	}

	public void showAll() {
		Log.e(tag, "count=" + count + "\ttotal=" + total);
		for (int i = 0; i < total; i++) {
			PieceVO vo = pieces.get(i);
			Log.e(tag, "\tid=" + vo.id + "\tx=" + vo.x + "\ty=" + vo.y + "\tp=" + vo.p + "\tpx=" + vo.px + "\tpy=" + vo.py);
		}
	}

	public ArrayList<PieceVO> getPieces() {
		return pieces;
	}

}

