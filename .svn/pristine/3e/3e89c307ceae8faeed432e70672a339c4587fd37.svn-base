package com.hst.game;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import com.clover4u.scene.Scene;
import com.clover4u.utils.Load;
import com.clover4u.utils.Mtd;
import com.clover4u.utils.MyObject;
import com.clover4u.view.View;


public class TestScene extends Scene {
	public static final int ZFX = 0;
	public static final int SJX = 1;
	public static final int W = 640;
	public static final int H = 530;
	public int w = 1;
	public int h = 1;
	public int px = 0;
	public int py = 0;
	Image image = null;

	public static void main(String[] args) {

	}

	public static class MyBox extends MyObject {
		public int xy[][] = new int[4][2];// 两个三角形 拼成的四边形 第一个和第二个点为判定碰撞的一边
		public int dx = 1;

		public MyBox() {
			dx = 15;
			dy = 15;
		}

		public void paint(Graphics g) {
			// TODO Auto-generated method stub

		}

		public void update() {
			// TODO Auto-generated method stub

		}

	}

	public int[] getXYByIndex(int i, int j) {
		return new int[] { (j + 1) * w - w / 2, (i + 1) * h - h / 2 };
	}

	public static int[][] s = { 
		    { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 1, 0, 0 }, 
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 2 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 2, 0 }, 
			{ 0, 0, 0, 0, 0, 0, 0, 2, 1, 0 },
			{ 0, 0, 0, 0, 0, 0, 2, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 2, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 2, 0, 0, 0, 0, 0 }, 
			{ 0, 0, 0, 2, 0, 0, 0, 0, 0, 0 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }

	};

	public void init() {
		// TODO Auto-generated method stub
		w = W / s[0].length;
		h = H / s.length;
		px = w;
		py = h;
		loadRes();

	}

	public void draw(Graphics g) {
		// TODO Auto-generated method stub

		g.setColor(255, 255, 255);
		g.fillRect(0, 0, W, H);
		g.setColor(0, 0, 0);
		for (int i = 0; i < s.length; i++) {
			for (int j = 0; j < s[i].length; j++) {
				int[] xy = getXYByIndex(i, j);
				switch (s[i][j]) {
				case 1:
					g.fillRect(xy[0] - w / 2, xy[1] - h / 2, w, h);
					break;
				case 2:
					g.fillTriangle(xy[0] - w / 2, xy[1] + h / 2 + 2, xy[0] + w/ 2, xy[1] + h / 2 + 2, xy[0] + w / 2, xy[1] - h/ 2);
					break;
				}
			}
		}
		/*
		 * Graphics gg = image.getGraphics(); gg.drawImage(image, 0, 0,
		 * View.ANCHOR_LEFT_TOP);
		 */
		g.drawImage(image, 0, 0, View.ANCHOR_LEFT_TOP);
		g.drawRect(0, 0, image.getWidth(), image.getHeight());
	}

	public void update() {
		// TODO Auto-generated method stub

	}

	public void keyPressed(int keyCode) {
		// TODO Auto-generated method stub

	}

	public void keyReleased(int keyCode) {
		// TODO Auto-generated method stub

	}

	public void loadRes() {
		// TODO Auto-generated method stub
		image = Load.loadImage("/img/hero1.png", false);
		// image.getRGB(arg0, arg1, arg2, arg3, arg4, arg5, arg6)
		/*int[] s = new int[image.getWidth() * image.getHeight()];
		System.out.println("=============" + s.length);

		System.out.println("========结束=====" + s.length);
		image.getRGB(s, 0, image.getWidth(), 0, 0, image.getWidth(),
				image.getHeight());
	
		image = Image.createRGBImage(s, image.getWidth(), image.getHeight(),
				true);*/
		image = rotate(image, image.getWidth() / 2, image.getHeight() / 2, 124);
		setKeyLocked(false);
	}

	public void freeRes() {
		// TODO Auto-generated method stub

	}

	public static final Image zoomImage(Image imgSource, int newW, int newH) {
		int w1 = imgSource.getWidth(); // 原始图像的高度和宽度
		int h1 = imgSource.getHeight();
		int[] srcMap = new int[w1 * h1];
		imgSource.getRGB(srcMap, 0, w1, 0, 0, w1, h1); // 获取原始图像的像素信息
		
		if(newW <= 0 || newH <= 0 || newW == w1 && newH == h1) {
			return imgSource;
		}
		int[] newMap = new int[newW * newH];
        for(int i = 0; i < h1; i++) {
        	for(int j = 0; j < w1; j ++) {
        		int x = (int)(newW  * i * 1.0 / w1);
        		int y = (int)(newH * j * 1.0 / h1);
        		if(x >=0 && x < newW && y >=0 && y < newH) {
        			newMap[y * newW + x] = srcMap[i * w1 + j];
        		}
        	}
        }		
        srcMap = null;
       
        int[] wh = new int[2];
        int[] rgb = getCorrectArray(newMap, newW, newH, wh); 
        fixRGB(rgb, wh[0], wh[1]);
        Image image = Image.createRGBImage(rgb,wh[0], wh[1], true);
		return image;
	}
	// X2 = y1 * sin(t) + x1 * cos(t)
	// y2 = y1 * cos(t) - x1 * sin(t)
	/*
	 * @param imgSource 源图像
	 * 
	 * @param cx 旋转点相对于源图像坐上角横坐标
	 * 
	 * @param cy 旋转点相对于源图像坐上角纵坐标
	 * 
	 * @param theta 图像逆时针旋转的角度
	 
	 * @return 旋转后的图像
	 */
	public static final  Image rotate(Image imgSource, int cx, int cy, int theta) {
		
		if(theta > 360) {
			theta =theta % 360;
		}
		int w1 = imgSource.getWidth(); // 原始图像的高度和宽度
		int h1 = imgSource.getHeight();
		int[] srcMap = new int[w1 * h1];
		imgSource.getRGB(srcMap, 0, w1, 0, 0, w1, h1); // 获取原始图像的像素信息
		
		int dx = cx > w1 / 2 ? cx : w1 - cx; // 计算旋转半径
		int dy = cy > h1 / 2 ? cy : h1 - cy;
		double dr = Mtd.sqrt2(dx * dx + dy * dy);
		int wh2 = (int) (2 * dr); // 旋转后新图像为正方形，其边长+1是为了防止数组越界
		int[] destMap = new int[wh2 * wh2]; // 存放新图像象素的数组
		double destX, destY;
		
		//double radian = theta * Math.PI / 180; // 计算角度计算对应的弧度值
		/*for(int i = 0; i < wh2; i++) {
			for(int j = 0; j < wh2; j++) {
				destX = cx + (j - cx) * SinCos.sin[theta] + (i - cy)* SinCos.cos[theta];
				destY = cy - (j - cx) * SinCos.sin[theta] + (i - cy)* SinCos.cos[theta];
				//从源图像中往新图像中填充像素
				int index = (int)(destY) + w1 * (int)destX;
				int sign = 0;
				while(!((int)destY >= 0 && (int)destY < h1 && (int)destX  >= 0 && (int)(destX) < w1)) {
					destY += 0.01;
					destX += 0.01;
					sign = 1;
					if((int)destY >= h1 + 1 || (int)destX >= w1 + 1) {
						sign = -1;
						break;
					}
				}
				if(sign != -1) 
				  destMap[i * wh2 + j] = srcMap[(int)(destY) + w1 * (int)destX]; 

			}
		}*/
		
		 /*double cosT,sinT;
	        cosT = cos(t);
	        sinT = sin(t);
	        for (y = min_y; y <= max_y; y++)
	         { x' = (min_x-x1) * cosT + (y-y1) * sinT + x1';
	           y' = (y-y1) * cosT - (min_x-x1) * sinT + y1';
	           for (x = min_x; x <= max_x; x++)
	            { if (x', y') is in the bounds of the bitmap, 
	                 get pixel(x', y') and plot the pixel to 
	                 (x, y) on screen.
	              x' += cosT;
	              y' -= sinT;
	            }
	         }*/
		
		for (int i = 0; i < h1; i++) { //旋转正确当时会失真
			destX = dr + (0 - cx) * SinCos.cos[theta] + (i - cy)* SinCos.sin[theta];
			destY = dr + (i - cy) * SinCos.cos[theta] - (0 - cx)* SinCos.sin[theta];
			for (int j = 0; j < w1; j++) {
				if((srcMap[i * w1 + j] >> 24 != 0) && destX >= 0 && destX < wh2 && destY >= 0 && destY < wh2) {
					if(destMap[((int) destY) * wh2 + (int) destX] >> 24 == 0)
					   destMap[((int) destY) * wh2 + (int) destX] = srcMap[i * w1+ j];
				}
				destX += SinCos.cos[theta];
				destY -= SinCos.sin[theta];
				
		}
		}
		srcMap  = null;
		fixRGB(destMap,wh2,wh2);
		/*for (int i = 0; i < wh2; i++) { //坏点修补
			for (int j = 0; j < wh2; j++) {
				 if(j > 0 && j < wh2 - 1 && (destMap[i * wh2 + j] >>24 == 0) &&(destMap[i * wh2 + j - 1] >> 24 != 0)&& (destMap[i * wh2 + j + 1] >> 24 != 0)) {
					 destMap[i * wh2 + j] = destMap[i * wh2 + j - 1];
					 j ++;
					
				 }
			}
	    }*/
		
      
        int[] wh = new int[2];
        int[] rgb = getCorrectArray(destMap, wh2, wh2, wh); 
        fixRGB(rgb, wh[0], wh[1]);
        Image image = Image.createRGBImage(rgb,wh[0], wh[1], true);
        destMap = null;
		return image;// 返回旋转后的图像

	}
	
	
	public static void fixRGB(int[] destMap, int w, int h) {
		for (int i = 0; i < h; i++) { //坏点修补
			for (int j = 0; j < w; j++) {
				 //上下方向修复
				 if(i > 0 && i < h - 1 && (destMap[i * w + j] >>24 == 0) &&(destMap[(i + 1) * w + j] >> 24 != 0)&& (destMap[(i - 1) * w + j] >> 24 != 0)) {
					 destMap[i * w + j] = destMap[(i - 1) * w + j];
				 }
				 //左右方向修复
				 if(j > 0 && j < w - 1 && (destMap[i * w + j] >>24 == 0) &&(destMap[i * w + j - 1] >> 24 != 0)&& (destMap[i * w + j + 1] >> 24 != 0)) {
					 destMap[i * w + j] = destMap[i * w + j - 1];
					 j ++;
				 }
				
			}
	    }
	}
	public static int[] rotate3(int[] _pixels,int _width,int _height,int _angle)   
	{   
	    int i,j;   
	    double radius = Mtd.sqrt(_width*_width + _height*_height);   
	    int r = (int)radius;   
	    int[] newPixels = new int[r * r];   
	   
	    double x2,y2;   
	    int x3,y3;   
	    double cos = SinCos.cos[_angle];   
	    double sin = SinCos.sin[_angle];   
	    for(i = 0;i < r;i++)   
	    {   
	        x2 = (-r/2)*cos + (i - r/2)*sin;   
	        y2 = (r/2)*sin + (i - r/2)*cos;   
	        x3 = (int)x2;   
	        y3 = (int)y2;   
	        for(j = 0;j < r; j++)   
	        {   
	            if(x3 >= -_width/2&&x3< _width/2&&y3 >= -_height/2&&y3< _height/2)   
	            {   
	                newPixels[i*r +j] = _pixels[(y3 + _height/2)*_width+x3+_width/2];   
	            }   
	            x2 += cos;   
	            y2 -= sin;   
	            x3 = (int)x2;   
	            y3 = (int)y2;   
	        }   
	    }   
	    return newPixels;   
	}  
	
	public static int[] getCorrectArray(int[] destMap, int w, int h, int[] wh) {
		//图片大小优化去白边
		        System.out.println("destMap = " + destMap);
				int top=-1,left=-1,right=-1,bottom =-1;
				//从上往下搜索
				int len = w * h;
				for (int i = 0; i < len; i++) {
					if (destMap[i] >> 24 != 0 && i > w)  {
						top = i / w- 1 ;
						break;
					}
				}
				//从下往上      
			    for (int i = len - 1; i > 0; i--) {
						if (destMap[i] >> 24 != 0 && i > w)  {
							bottom = i / w- 1 ;
							break;
						}
				}
			    
				//从左往右
				outer1:for(int j = 0; j < w; j++)
				       for(int i = top; i <= bottom; i++) {
				    	   if (destMap[i * w + j] >> 24 != 0) {
				    		  if(j <= left || left == -1) {
				    			   left = j;
				    			   continue outer1;
				    		   }
				    	   } 
				       }
				
				//从右往左
				outer2:for(int j = w - 1; j >= left; j--)
				       for(int i = top; i <= bottom; i++) {
				    	   if (destMap[i * w + j] >> 24 != 0) {
				    		   if(j >= right || right == -1)  {
				    			   right = j;
				    			   continue outer2;
				    		   }
				    	   } 
				       }
			   
		        int[] finalArray = new int[(right - left + 1) * (bottom - top + 1)];
		        int index = -1;
		        for(int i = top; i <= bottom; i++) {
		        	for(int j = left; j <= right; j++) {
		        		index ++;
		        		finalArray[index] = destMap[i * w + j];
		        	}
		        }
		        wh[0] = right - left + 1;
		        wh[1] = bottom - top + 1;
		        return finalArray;
	}

}
