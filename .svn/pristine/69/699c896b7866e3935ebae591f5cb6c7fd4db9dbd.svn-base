package com.itvgame.remotecontrol.main;

/**byteArray 和各基本数据类型之间的转换
 * @author Clover4Green
 *
 */
  public class DataTypeConversion {
    /*******************int byte
     * @return ************************************/
	
	public static final  int byte2Int(byte[] b, int index) {//高位到低位顺序
		int n = 24;
		int result = 0;
		if(b.length - index >= 4) {
			for(int i = 0; i < 4; i++) {
				result += ((b[i + index] & 0xff)  << n);
				n-=8;
			}
			return result;
		}else throw new IllegalArgumentException("b.length - index must be >= 4 ");
	}
	//重载
	public static final byte[] int2Byte(int n, byte[] b, int index) {//0000 0000 0000 0000 0000 0000 0000 0000
		
		for(int i = 3; i >= 0; i--) {
			b[i + index] = (byte)(n & 0xff); //获取低八位
			n = n >> 8;//消除低八位
		}
		return b;
	}
	
	public static final byte[] int2Byte(int n) {//0000 0000 0000 0000 0000 0000 0000 0000
		byte[] b = new byte[4];
		for(int i = b.length - 1; i >= 0; i--) {
			b[i] = (byte)(n & 0xff); //获取低八位
			n = n >> 8;//消除低八位
		}
		return b;
	}
	/**********************long byte***********************************/
	public static final byte[] long2Byte(long n, byte[] b, int index) {//返回顺序高位到低位
		for(int i = 7; i >= 0; i--) {
			b[i + index] = (byte)(n & 0xff); //获取低八位
			n = (n >> 8);//消除低八位
			
		}
		return b;
	}
	public static final byte[] long2Byte(long n) {//返回顺序高位到低位
		byte[] b = new byte[8];
		for(int i = b.length - 1; i >= 0; i--) {
			b[i] = (byte)(n & 0xff); //获取低八位
			n = (n >> 8);//消除低八位
			
		}
		
		return b;
	}
	//8 * 8 * 2 + 255 + 12
	public static final long byte2Long(byte[] b, int index) {//8 * 8
		int n = 56;
		long result = 0;
		if(b.length - index >= 8) {
			for(int i = 0; i < 8; i++) {
				result += (((long)b[i + index] & 0xff) << n);//必须强转成long才有64位 否则会当作int类型处理 只有32位
				n-=8;
			}
			return result;
		}else
			throw new IllegalArgumentException("b.length - index must >= 8 ");
	}
    	
	public static final  short byte2Short(byte[] b, int index) {//高位到低位顺序
		int n = 8;
		short result = 0;
		if(b.length - index >= 2) {
			for(int i = 0; i < 2; i++) {
				result += ((b[i + index] & 0xff)  << n);
				n-=8;
			}
			return result;
		}else throw new IllegalArgumentException("b.length - index must be >= 2 ");
	}
	
	public static final byte[] short2Byte(short n) {//0000 0000 0000 0000 0000 0000 0000 0000
		if(n > Short.MAX_VALUE || n < Short.MIN_VALUE) throw new IllegalArgumentException("n must bwteen "+Short.MIN_VALUE+" and "+Short.MAX_VALUE);
		byte[] b = new byte[2];
	    for(int i = b.length - 1; i >= 0; i--) {
			b[i] = (byte)(n & 0xff); //获取低八位
			n = (short) (n >> 8);//消除低八位
		}
		return b;
	}
	
	public static final byte[] short2Byte(short n, byte[] b, int index) {//0000 0000 0000 0000 0000 0000 0000 0000
		if(n > Short.MAX_VALUE || n < Short.MIN_VALUE) throw new IllegalArgumentException("n must bwteen "+Short.MIN_VALUE+" and "+Short.MAX_VALUE);
		for(int i = 1; i >= 0; i--) {
			b[i + index] = (byte)(n & 0xff); //获取低八位
			n = (short) (n >> 8);//消除低八位
		}
		return b;
	}
	public static final  char byte2Char(byte[] b, int index) {//高位到低位顺序
		return (char)byte2Short(b, index);
	}
	
	public static final byte[] char2Byte(char n, byte[] b, int index) {//0000 0000 0000 0000 0000 0000 0000 0000
		return short2Byte((short)n, b, index);
	}
	
	public static final byte[] char2Byte(char n) {//0000 0000 0000 0000 0000 0000 0000 0000
		return short2Byte((short)n);
	}
	
	public static long getX(int m, int n) {
		if(m == 0) return 0;
		long result = 1;
		while(n-- > 0) {
			result *= m;
		}
		return result;
	}

  
	
	public static void main(String[] args) {
		//System.out.println(byte2Int(int2Byte(560),0));
		//System.out.println(Long.MAX_VALUE);
		//System.out.println(byte2Long(long2Byte(Long.MAX_VALUE), 0));
		//System.out.println(getLong(longToByte(Long.MAX_VALUE)));
		//System.out.println(127 * getX(2,56));
		//System.out.println(Short.MAX_VALUE);
		//System.out.println((127 & 0xff) << 56);
		//System.out.println(((long)127 & 0xff) << 56);
//		double d = 1.001d;
//		long n = Double.doubleToLongBits(d);
//		System.out.println(n);
		//System.out.println(byte2Char(char2Byte('a'), 0));
		System.out.println(byte2Short(short2Byte((short)40), 0));
	}

 }
