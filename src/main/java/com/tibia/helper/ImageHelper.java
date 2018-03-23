package com.tibia.helper;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import net.coobird.thumbnailator.Thumbnails;
import net.sourceforge.tess4j.TesseractException;

public class ImageHelper {
	
	private Robot robot;
	private OCRHelper ocrHelper;
	private ConstantsHelper constants;
	
	public ImageHelper() throws AWTException {
		robot = new Robot();
		ocrHelper = new OCRHelper();
		constants = new ConstantsHelper();
	}
	
	public BufferedImage getMinimap() throws IOException {
		int[] rectangleSize = getSizeFromTwoPoints(constants.MINIMAP_X_TOP, constants.MINIMAP_Y_TOP, constants.MINIMAP_X_BOTTOM, constants.MINIMAP_Y_BOTTOM);
		
    	Rectangle rectArea = new Rectangle(constants.MINIMAP_X_TOP, constants.MINIMAP_Y_TOP, rectangleSize[0], rectangleSize[1]);
        return robot.createScreenCapture(rectArea);
	}
	
	public BufferedImage getCurrentMarker(String markerPath) throws IOException {
		return ImageIO.read(new File(markerPath));
	}
	
	public BufferedImage getCheckpoint(String checkpoint) throws IOException {
		return ImageIO.read(new File(checkpoint));
	}
	
	public BufferedImage getCurrentCheckpoint(String direction) throws IOException {
		int[] rectangleSize = getSizeFromTwoPoints(constants.SOUTH_CHECKPOINT_X_TOP, constants.SOUTH_CHECKPOINT_Y_TOP, constants.SOUTH_CHECKPOINT_X_BOTTOM, constants.SOUTH_CHECKPOINT_Y_BOTTOM);
		Rectangle rectArea = new Rectangle(constants.SOUTH_CHECKPOINT_X_TOP, constants.SOUTH_CHECKPOINT_Y_TOP, rectangleSize[0], rectangleSize[1]);
		
//		BufferedImage bufferedImage = robot.createScreenCapture(rectArea);
//      ImageIO.write(bufferedImage, "png", new File("images/sample.png"));
		
        return robot.createScreenCapture(rectArea);
	}
	
	public BufferedImage resize(BufferedImage image, int newWidth, int newHeight) throws IOException {
		return Thumbnails.of(image).forceSize(newWidth, newHeight).asBufferedImage();
	}
	
	public String getTextFromImage(int topX, int topY, int bottomX, int bottomY) throws IOException, TesseractException {
		int[] rectangleSize = getSizeFromTwoPoints(topX, topY, bottomX, bottomY);
		
    	Rectangle rectArea = new Rectangle(topX, topY, rectangleSize[0], rectangleSize[1]);
    	
    	return ocrHelper.getTextFromImage(resize(robot.createScreenCapture(rectArea), rectangleSize[0] * constants.TIMES_TO_RESIZE_SAMPLE, rectangleSize[1] * constants.TIMES_TO_RESIZE_SAMPLE));
	}
	
	private int[] getSizeFromTwoPoints(int topX, int topY, int bottomX, int bottomY) {
		int[] size = new int[2];
		
		size[0] = (bottomX - topX);
		size[1] = (bottomY - topY);
				
		return size;
	}
}
