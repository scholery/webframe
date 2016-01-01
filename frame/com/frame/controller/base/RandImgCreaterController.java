package com.frame.controller.base;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class RandImgCreaterController {

	private static final String CODE_LIST = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";

	private static final int HEIGHT = 30;

	private static final int FONT_NUM = 4;

	private int width = 13 * FONT_NUM + 12;

	private int iNum = FONT_NUM;

	private String codeList = CODE_LIST;

	@RequestMapping("/createRandImageService")
	public void createRandImage(HttpServletRequest request,HttpServletResponse response, Model model) throws Exception {
		BufferedImage image = new BufferedImage(width, HEIGHT,BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		Random random = new Random();
		g.setColor(getRandColor(200, 250));
		g.fillRect(0, 0, width, HEIGHT);
		for (int i = 0; i < 155; i++) {
			g.setColor(getRandColor(140, 200));
			int x = random.nextInt(width);
			int y = random.nextInt(HEIGHT);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}
		g.setFont(new Font("Times New Roman", Font.PLAIN, 23));
		String sRand = "";
		for (int i = 0; i < iNum; i++) {
			int rand = random.nextInt(codeList.length());
			String strRand = codeList.substring(rand, rand + 1);
			sRand += strRand;
			g.setColor(new Color(20 + random.nextInt(110), 20 + random
					.nextInt(110), 20 + random.nextInt(110)));
			g.drawString(strRand, 13 * i + 6, 22);
		}
		g.dispose();
		request.getSession().setAttribute("rand", sRand);
		try {
			ImageIO.write(image, "JPEG", response.getOutputStream());
		} catch (IOException e) {

		}
	}


	private Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;

		if (bc > 255)
			bc = 255;

		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

}