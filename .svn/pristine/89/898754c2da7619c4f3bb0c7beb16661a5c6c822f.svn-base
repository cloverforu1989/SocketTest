package com.hst.game;

import javax.microedition.lcdui.Graphics;

import com.clover4u.utils.Mtd;
import com.clover4u.view.View;

public class Bullet {
	public int x;
	public int y;
	public int dx;
	public int dy = -20;
	public int step;
	public int state = 1;
	public int imgIndex = -1;
    public Bullet(int x, int y) {
    	this.x = x;
    	this.y = y;
    	this.imgIndex = 0;
    }
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		if(state == 1)
		Mtd.drawImage(GameScene.img[0], x, y, View.ANCHOR_HCENTER_VCENTER, g);
	}

	public void nextStep(int _dx2, int _dy2) {
		// TODO Auto-generated method stub
		if(state == 1) {
			step ++;
			x += dx;
			y += dy;
			if(x < 0) {
				x = 0;
				state = 0;
			}else if(x > 640) {
				x = 640;
				state = 0;
			}
			
			if(y < 0) {
				y = 0;
				state = 0;
			}else if(y > 530) {
				y = 530;
				state = 0;
			}
		}
		
	}
}
