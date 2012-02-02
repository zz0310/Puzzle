package zz.android.puzzle;

import android.graphics.Bitmap;
import android.util.Log;

public class PieceVO {
	public final static String tag = "PieceVO";
	public Bitmap bmp = null;
    public int id = 0;
    public int x = 0;
    public int y = 0;
    public int p = 0;
    public int px = 0;
    public int py = 0;
	public boolean isSelected = false;

	public boolean isSelected() {
		return isSelected;
	}
	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	public Bitmap getBmp() {
		return bmp;
	}
	public void setBmp(Bitmap bmp) {
		this.bmp = bmp;
	}

    public PieceVO(int id) {
            this.id = id;
    }
    public int getId() {
            return id;
    }
    public void setId(int id) {
            this.id = id;
    }
    public int getX() {
            return x;
    }
    public void setX(int x) {
            this.x = x;
    }
    public int getY() {
            return y;
    }
    public void setY(int y) {
            this.y = y;
    }
    public int getP() {
            return p;
    }
    public void setP(int p) {
            this.p = p;
    }
    public int getPx() {
            return px;
    }
    public void setPx(int px) {
            this.px = px;
    }
    public int getPy() {
            return py;
    }
    public void setPy(int py) {
            this.py = py;
    }

	public void showAll() {
		Log.e(tag, "\tid=" + id + "\tx=" + x + "\ty=" + y + "\tp=" + p + "\tpx=" + px + "\tpy=" + py);
	}

}
