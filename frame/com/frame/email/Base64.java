package com.frame.email;

/**
 * BASE64编码格式转换，可以进行转换和反转
 * @version
 */
public class Base64 {
	/** */
	/** 一个8位串的最大长度 */
	static private final int BASELENGTH = 255;

	/** */
	/** BASE64编码长度 */
	static private final int LOOKUPLENGTH = 64;

	/** */
	/** BASE64数组 */
	public static final byte[] base64_alphabet = new byte[LOOKUPLENGTH];

	/** */
	/** BASE64反转数组 */
	public static final byte[] base64_inv = new byte[BASELENGTH];

	static {
		for (int i = 0; i < BASELENGTH; i++) {
			base64_inv[i] = -1;
		}
		for (int i = 'Z'; i >= 'A'; i--)// A-Z从0-25
		{
			base64_inv[i] = (byte) (i - 'A');
		}
		for (int i = 'z'; i >= 'a'; i--) {
			base64_inv[i] = (byte) (i - 'a' + 26);// a-z是从26-51
		}
		for (int i = '9'; i >= '0'; i--) {
			base64_inv[i] = (byte) (i - '0' + 52);// 0-9是从52-61
		}

		base64_inv['+'] = 62;

		base64_inv['/'] = 63;

		for (int i = 0; i <= 25; i++) {
			base64_alphabet[i] = (byte) ('A' + i);
		}
		for (int i = 26, j = 0; i <= 51; i++, j++) {
			base64_alphabet[i] = (byte) ('a' + j);
		}
		for (int i = 52, j = 0; i <= 61; i++, j++) {
			base64_alphabet[i] = (byte) ('0' + j);
		}

		base64_alphabet[62] = '+';

		base64_alphabet[63] = '/';
	}

	/**
	 * 转换BYTE数组为BASE形式
	 * 
	 * @param bt
	 * @return
	 * @see:
	 */
	public static byte[] encodeBase64(byte[] bt) {
		byte[] btcl = new byte[bt.length
				+ (bt.length % 3 == 0 ? 0 : (bt.length % 3 == 1 ? 2 : 1))];// 补齐为3的倍数的数据源集合
		byte[] r = new byte[btcl.length * 8 / 6];// 结果集的长度，数据源集合按照6BIT一组拆分后的大小,原来是8BIT一组
		int j = 0;
		System.arraycopy(bt, 0, btcl, 0, bt.length);
		for (int c = 0; c < bt.length; c += 3)// 每次取3个字节进行操作
		{
			// 不做折行处理
			int n = ((btcl[c] << 16) & 0xff0000)
					| ((btcl[c + 1] << 8) & 0xff00) | (btcl[c + 2] & 0xff);
			r[j] = base64_alphabet[n >>> 18 & 63];
			r[j + 1] = base64_alphabet[n >>> 12 & 63];
			r[j + 2] = base64_alphabet[n >>> 6 & 63];
			r[j + 3] = base64_alphabet[n & 63];
			j += 4;
		}
		for (int i = bt.length, k = 0; i < btcl.length; i++, k++) {
			r[r.length - 1 - k] = (byte) '=';
		}
		return r;
	}

	/**
	 * 进行BASE64解码 bt的长度满足下面公式 bt.length = 3n*8/6 所以bt的长度必定为4的倍数，必须能被4整除
	 * 
	 * @param bt
	 * @return
	 * @see:
	 */
	public static byte[] decodeBase64(byte[] bt) {
		if (bt.length % 4 != 0)// 做个基本的验证
		{
			throw new NumberFormatException("输入的BASE64编码不合法");
		}
		byte[] r = new byte[bt.length * 6 / 8];// 存放解码结果,大小为BASE64编码的BYTE数组的6倍除以8
		int i, k = 0;
		for (i = 0; i < 2; i++)// 如果最后2位是'=',转化为'A'
		{
			if (bt[bt.length - 1 - i] == (byte) '=') {
				bt[bt.length - 1 - i] = (byte) 'A';
				k++;
			}
		}
		int j = 0;
		for (int c = 0; c < bt.length; c += 4) {
			int n = (base64_inv[bt[c]] << 18) | (base64_inv[bt[c + 1]] << 12)
					| (base64_inv[bt[c + 2]] << 6) | base64_inv[bt[c + 3]];
			r[j] = (byte) (n >>> 16 & 0xff);
			r[j + 1] = (byte) (n >>> 8 & 0xff);
			r[j + 2] = (byte) (n & 0xff);
			j += 3;
		}
		byte[] result = new byte[r.length - k];
		System.arraycopy(r, 0, result, 0, result.length);
		return result;
	}

}
