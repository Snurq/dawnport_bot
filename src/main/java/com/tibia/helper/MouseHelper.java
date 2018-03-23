package com.tibia.helper;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class MouseHelper {
    private Robot robot;
	private ConstantsHelper constantsHelper;
    
    public MouseHelper() throws AWTException {
    	robot = new Robot();
		constantsHelper = new ConstantsHelper();
    }
	
	public void getLoot() throws AWTException, InterruptedException {
    	robot.keyPress(KeyEvent.VK_SHIFT);
	    
		robot.mouseMove(constantsHelper.WEST_X, constantsHelper.WEST_Y);
	    robot.mousePress(InputEvent.BUTTON3_MASK);
	    robot.mouseRelease(InputEvent.BUTTON3_MASK);

	    Thread.sleep(50);
	    
		robot.mouseMove(constantsHelper.NORTH_WEST_X, constantsHelper.NORTH_WEST_Y);
	    robot.mousePress(InputEvent.BUTTON3_MASK);
	    robot.mouseRelease(InputEvent.BUTTON3_MASK);

	    Thread.sleep(50);
	    
		robot.mouseMove(constantsHelper.NORTH_X, constantsHelper.NORTH_Y);
	    robot.mousePress(InputEvent.BUTTON3_MASK);
	    robot.mouseRelease(InputEvent.BUTTON3_MASK);

	    Thread.sleep(50);
	    
		robot.mouseMove(constantsHelper.NORTH_EAST_X, constantsHelper.NORTH_EAST_Y);
	    robot.mousePress(InputEvent.BUTTON3_MASK);
	    robot.mouseRelease(InputEvent.BUTTON3_MASK);

	    Thread.sleep(50);
	    
		robot.mouseMove(constantsHelper.EAST_X, constantsHelper.EAST_Y);
	    robot.mousePress(InputEvent.BUTTON3_MASK);
	    robot.mouseRelease(InputEvent.BUTTON3_MASK);

	    Thread.sleep(50);
	    
		robot.mouseMove(constantsHelper.SOUTH_WEST_X, constantsHelper.SOUTH_WEST_Y);
	    robot.mousePress(InputEvent.BUTTON3_MASK);
	    robot.mouseRelease(InputEvent.BUTTON3_MASK);

	    Thread.sleep(50);
	    
		robot.mouseMove(constantsHelper.SOUTH_X, constantsHelper.SOUTH_Y);
	    robot.mousePress(InputEvent.BUTTON3_MASK);
	    robot.mouseRelease(InputEvent.BUTTON3_MASK);

	    Thread.sleep(50);
	    
		robot.mouseMove(constantsHelper.SOUTH_EAST_X, constantsHelper.SOUTH_EAST_Y);
	    robot.mousePress(InputEvent.BUTTON3_MASK);
	    robot.mouseRelease(InputEvent.BUTTON3_MASK);

	    Thread.sleep(50);

    	robot.keyRelease(KeyEvent.VK_SHIFT);
	}
    
	public void clickOn(int x, int y) throws AWTException, InterruptedException {
		robot.mouseMove(x, y);    
	    robot.mousePress(InputEvent.BUTTON1_MASK);
	    robot.mouseRelease(InputEvent.BUTTON1_MASK);

	    Thread.sleep(500);
	}
	
	public void clickOnCentreZoom() throws AWTException, InterruptedException {
		robot.mouseMove(constantsHelper.CENTRE_ZOOM_BUTTON_X, constantsHelper.CENTRE_ZOOM_BUTTON_Y);    
	    robot.mousePress(InputEvent.BUTTON1_MASK);
	    robot.mouseRelease(InputEvent.BUTTON1_MASK);

	    Thread.sleep(500);
	}
	
	public void clickOnPictureFirstMonster() throws AWTException, InterruptedException {
		robot.mouseMove(constantsHelper.PICTURE_FIRST_MONSTER_X, constantsHelper.PICTURE_FIRST_MONSTER_Y);    
	    robot.mousePress(InputEvent.BUTTON3_MASK);
	    robot.mouseRelease(InputEvent.BUTTON3_MASK);

	    Thread.sleep(100);
	}
	
	public void clickOnHealthPotion() throws AWTException, InterruptedException {
		robot.mouseMove(constantsHelper.HEALTH_POTION_X, constantsHelper.HEALTH_POTION_Y);    
	    robot.mousePress(InputEvent.BUTTON1_MASK);
	    robot.mouseRelease(InputEvent.BUTTON1_MASK);

	    Thread.sleep(100);
	}
	
	public void clickOnFood() throws AWTException, InterruptedException {
		robot.mouseMove(constantsHelper.FOOD_X, constantsHelper.FOOD_Y);    
	    robot.mousePress(InputEvent.BUTTON1_MASK);
	    robot.mouseRelease(InputEvent.BUTTON1_MASK);

	    Thread.sleep(100);
	}
	
	public void clickOnRope() throws AWTException, InterruptedException {
		robot.mouseMove(constantsHelper.ROPE_X, constantsHelper.ROPE_Y); 
	    Thread.sleep(100);   
	    robot.mousePress(InputEvent.BUTTON1_MASK);
	    robot.mouseRelease(InputEvent.BUTTON1_MASK);
	    Thread.sleep(100);   
		robot.mouseMove(constantsHelper.CENTRE_CHAR_X, constantsHelper.CENTRE_CHAR_Y); 
	    Thread.sleep(100);   
	    robot.mousePress(InputEvent.BUTTON1_MASK);
	    robot.mouseRelease(InputEvent.BUTTON1_MASK);

	    Thread.sleep(100);
	}
	
	public void clickOnCloseBattleMenu() throws AWTException, InterruptedException {
		robot.mouseMove(constantsHelper.CLOSE_BATTLE_MENU_X, constantsHelper.CLOSE_BATTLE_MENU_Y);    
	    robot.mousePress(InputEvent.BUTTON1_MASK);
	    robot.mouseRelease(InputEvent.BUTTON1_MASK);

	    Thread.sleep(250);
	}
	
	public void clickOnAttackMonster() throws AWTException, InterruptedException {
		robot.mouseMove(constantsHelper.ATTACK_OR_STOP_CREATURE_X, constantsHelper.ATTACK_OR_STOP_CREATURE_Y);    
	    robot.mousePress(InputEvent.BUTTON1_MASK);
	    robot.mouseRelease(InputEvent.BUTTON1_MASK);

	    Thread.sleep(250);
	}
	
	public void clickOnZoomIn() throws AWTException, InterruptedException {
		robot.mouseMove(constantsHelper.ZOOM_IN_BUTTON_X, constantsHelper.ZOOM_IN_BUTTON_Y);    
	    robot.mousePress(InputEvent.BUTTON1_MASK);
	    robot.mouseRelease(InputEvent.BUTTON1_MASK);

	    Thread.sleep(500);
	}
	
	public void clickOnZoomOut() throws AWTException, InterruptedException {
		robot.mouseMove(constantsHelper.ZOOM_OUT_BUTTON_X, constantsHelper.ZOOM_OUT_BUTTON_Y);    
	    robot.mousePress(InputEvent.BUTTON1_MASK);
	    robot.mouseRelease(InputEvent.BUTTON1_MASK);

	    Thread.sleep(500);
	}
	
	public void clickOnAttackMode() throws AWTException, InterruptedException {
		robot.mouseMove(constantsHelper.ATTACK_MODE_BUTTON_X, constantsHelper.ATTACK_MODE_BUTTON_Y);    
	    robot.mousePress(InputEvent.BUTTON1_MASK);
	    robot.mouseRelease(InputEvent.BUTTON1_MASK);

	    Thread.sleep(500);
	}
	
	public void clickOnDefenseMode() throws AWTException, InterruptedException {
		robot.mouseMove(constantsHelper.DEFENSE_MODE_BUTTON_X, constantsHelper.DEFENSE_MODE_BUTTON_Y);    
	    robot.mousePress(InputEvent.BUTTON1_MASK);
	    robot.mouseRelease(InputEvent.BUTTON1_MASK);

	    Thread.sleep(500);
	}
	
	public void clickOnPursuitTarget() throws AWTException, InterruptedException {
		robot.mouseMove(constantsHelper.PURSUIT_TARGET_BUTTON_X, constantsHelper.PURSUIT_TARGET_BUTTON_Y);    
	    robot.mousePress(InputEvent.BUTTON1_MASK);
	    robot.mouseRelease(InputEvent.BUTTON1_MASK);

	    Thread.sleep(500);
	}
}
