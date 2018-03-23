package com.tibia.facade;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import com.tibia.helper.ConstantsHelper;
import com.tibia.helper.ImageHelper;
import com.tibia.helper.MouseHelper;
import com.tibia.helper.UtilHelper;
import com.tibia.helper.XMLHelper;
import com.tibia.model.Waypoint;

import net.sourceforge.tess4j.TesseractException;

public class Facade {
	private List<Waypoint> waypoints;
	
	private XMLHelper xml;
	private UtilHelper util;
	private MouseHelper mouse;
	private ImageHelper image;
	private ConstantsHelper constants;

	public Facade() throws AWTException {
		new Robot();
		xml = new XMLHelper();
		util = new UtilHelper();
		mouse = new MouseHelper();
		image = new ImageHelper();
		constants = new ConstantsHelper();
	}

	public void run() throws InterruptedException, AWTException, IOException, TesseractException {
		showMessage("Bem-vindo \n\n 1) Abra a janela do Tibia. \n 2) Deixe o char na dungeon. \n 3) Aperte OK. \n\n");
		
		delay(1000);

		mouse.clickOnCentreZoom();
		
		mouse.clickOnZoomIn();
		mouse.clickOnZoomIn();
		mouse.clickOnZoomIn();

		mouse.clickOnZoomOut();
		
		mouse.clickOnAttackMode();
		mouse.clickOnPursuitTarget();
		
		waypoints = xml.getWaypointsList();
		
		int i = 0;
		while(true) {
			boolean hitCheckpoint = false;
			while(!hitCheckpoint) {
				
				boolean hasMonsterInBattle = true;
				while(hasMonsterInBattle) {
					String monsterName = util.normalizeText(image.getTextFromImage(
							constants.BATTLE_WINDOW_FIRST_MONSTER_X_TOP,
							constants.BATTLE_WINDOW_FIRST_MONSTER_Y_TOP,
							constants.BATTLE_WINDOW_FIRST_MONSTER_X_BOTTOM,
							constants.BATTLE_WINDOW_FIRST_MONSTER_Y_BOTTOM));
					
					if (monsterName.equals("")) {
						hasMonsterInBattle = false;
					} else {
						mouse.clickOnPictureFirstMonster();
						
						String attackFlag = util.normalizeText(image.getTextFromImage(
								constants.FIRST_MONSTER_MENU_X_TOP,
								constants.FIRST_MONSTER_MENU_Y_TOP,
								constants.FIRST_MONSTER_MENU_X_BOTTOM,
								constants.FIRST_MONSTER_MENU_Y_BOTTOM));
						
						if (attackFlag.equals("Attack")) {
							mouse.clickOnAttackMonster();
							delay(100);
						}
						
						if (attackFlag.equals("StopAttack")) {
							mouse.clickOnCloseBattleMenu();
							delay(150);
						}
						
						mouse.clickOnPursuitTarget();
						
//						delay(constants.DELAY_BEFORE_LOOTING);
//						
//						mouse.getLoot();
//						mouse.getLoot();
						
						int health = Integer.parseInt(util.normalizePrice(image.getTextFromImage(
								constants.HEALTH_X_TOP,
								constants.HEALTH_Y_TOP,
								constants.HEALTH_X_BOTTOM,
								constants.HEALTH_Y_BOTTOM)));
						
						if (health < constants.MIN_LIFE_TO_HEAL) {
							mouse.clickOnHealthPotion();
						}
						
						mouse.clickOnFood();
					}
				}
				
				int[] coordinates = findSubimage(image.getMinimap(), image.getCurrentMarker(waypoints.get(i).getMarker()));
				
			   	mouse.clickOn((coordinates[0] + constants.OFFSET_MINIMAP_X), (coordinates[1] + constants.OFFSET_MINIMAP_Y));
			   	
			   	if (waypoints.get(i).getDirection().equals("ROPE")) {
			   		mouse.clickOnRope();
					hitCheckpoint = true;
			   	} else if (waypoints.get(i).getDirection().equals("HOLE")) {
			   		delay(20000);
			   		hitCheckpoint = true;
			   	} else {
			   		double isInTheRightSpot = getDifferencePercent(image.getCurrentCheckpoint(waypoints.get(i).getDirection()), image.getCheckpoint(waypoints.get(i).getCheckpoint()));
				   	System.out.println(isInTheRightSpot);
				   	
				   	if (isInTheRightSpot == 0) {
						hitCheckpoint = true;
				   	}
			   	}
			}
			
			i++;
			
			if (i == waypoints.size()) {
				i = 0;
			}
		}
	}
	
	private int[] getSizeFromTwoPoints(int topX, int topY, int bottomX, int bottomY) {
		int[] size = new int[2];
		
		size[0] = (bottomX - topX);
		size[1] = (bottomY - topY);
				
		return size;
	}

	private void delay(int milliseconds) throws InterruptedException {
		Thread.sleep(milliseconds);
	}

	private void showMessage(String message) {
		JOptionPane jOptionPane = new JOptionPane();
		jOptionPane.setMessage(message);
		
		JDialog dialog = jOptionPane.createDialog(null);
		dialog.setTitle(constants.PROGRAM_TITLE);
		dialog.setAlwaysOnTop(true);  
		dialog.setVisible(true);
	}
    
    /**
     * Finds the a region in one image that best matches another, smaller, image.
     */
     public static int[] findSubimage(BufferedImage im1, BufferedImage im2){
       int w1 = im1.getWidth(); int h1 = im1.getHeight();
       int w2 = im2.getWidth(); int h2 = im2.getHeight();
       assert(w2 <= w1 && h2 <= h1);
       // will keep track of best position found
       int bestX = 0; int bestY = 0; double lowestDiff = Double.POSITIVE_INFINITY;
       // brute-force search through whole image (slow...)
       for(int x = 0;x < w1-w2;x++){
         for(int y = 0;y < h1-h2;y++){
           double comp = compareImages(im1.getSubimage(x,y,w2,h2),im2);
           if(comp < lowestDiff){
             bestX = x; bestY = y; lowestDiff = comp;
           }
         }
       }
       // return best location
       return new int[]{bestX,bestY};
     }

     /**
     * Determines how different two identically sized regions are.
     */
     public static double compareImages(BufferedImage im1, BufferedImage im2){
       assert(im1.getHeight() == im2.getHeight() && im1.getWidth() == im2.getWidth());
       double variation = 0.0;
       for(int x = 0;x < im1.getWidth();x++){
         for(int y = 0;y < im1.getHeight();y++){
            variation += compareARGB(im1.getRGB(x,y),im2.getRGB(x,y))/Math.sqrt(3);
         }
       }
       return variation/(im1.getWidth()*im1.getHeight());
     }

     /**
     * Calculates the difference between two ARGB colours (BufferedImage.TYPE_INT_ARGB).
     */
     public static double compareARGB(int rgb1, int rgb2){
       double r1 = ((rgb1 >> 16) & 0xFF)/255.0; double r2 = ((rgb2 >> 16) & 0xFF)/255.0;
       double g1 = ((rgb1 >> 8) & 0xFF)/255.0;  double g2 = ((rgb2 >> 8) & 0xFF)/255.0;
       double b1 = (rgb1 & 0xFF)/255.0;         double b2 = (rgb2 & 0xFF)/255.0;
       double a1 = ((rgb1 >> 24) & 0xFF)/255.0; double a2 = ((rgb2 >> 24) & 0xFF)/255.0;
       // if there is transparency, the alpha values will make difference smaller
       return a1*a2*Math.sqrt((r1-r2)*(r1-r2) + (g1-g2)*(g1-g2) + (b1-b2)*(b1-b2));
     }
     
     private static double getDifferencePercent(BufferedImage img1, BufferedImage img2) {
         int width = img1.getWidth();
         int height = img1.getHeight();
         int width2 = img2.getWidth();
         int height2 = img2.getHeight();
         if (width != width2 || height != height2) {
             throw new IllegalArgumentException(String.format("Images must have the same dimensions: (%d,%d) vs. (%d,%d)", width, height, width2, height2));
         }
  
         long diff = 0;
         for (int y = 0; y < height; y++) {
             for (int x = 0; x < width; x++) {
                 diff += pixelDiff(img1.getRGB(x, y), img2.getRGB(x, y));
             }
         }
         long maxDiff = 3L * 255 * width * height;
  
         return 100.0 * diff / maxDiff;
     }
  
     private static int pixelDiff(int rgb1, int rgb2) {
         int r1 = (rgb1 >> 16) & 0xff;
         int g1 = (rgb1 >>  8) & 0xff;
         int b1 =  rgb1        & 0xff;
         int r2 = (rgb2 >> 16) & 0xff;
         int g2 = (rgb2 >>  8) & 0xff;
         int b2 =  rgb2        & 0xff;
         return Math.abs(r1 - r2) + Math.abs(g1 - g2) + Math.abs(b1 - b2);
     }
}
