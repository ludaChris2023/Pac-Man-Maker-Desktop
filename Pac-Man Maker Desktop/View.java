/* 
 Name: Christopher Heffernan
 Date: 3/27/24
 Assignment Description: Creating a Pacman Game utilizing Java Graphics
 */
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import java.awt.Color;


public class View extends JPanel
{
	private JButton b1;
	private Model model;

	/* */
    public static final int cameraWidth = 448;
	public static final int cameraHeight = 448;
	private static int cameraX, cameraY;
	public View(Controller c, Model model)
	{
		this.model = model;
		c.setView(this);
		cameraX = 0;
		cameraY = 0;
		
		
		
	}
		
		



		
	void removeButton()
	{
		this.remove(b1);
		this.repaint();
		
	}
	
	public static BufferedImage loadImage(String fileString){
		BufferedImage image = null;
		
		try{
			image = ImageIO.read(new File(fileString));
		}catch(Exception e){
			e.printStackTrace();
		}
    	
		System.out.println(fileString);
		
		
		return image;
	}
	
	public void paintComponent(Graphics g)
	{

		cameraX = Math.max(0, Math.min(Game.width - View.cameraWidth, Model.pacman.getX() - View.cameraWidth / 2));
		cameraY = Math.max(0, Math.min(Game.height - View.cameraHeight, Model.pacman.getY() - View.cameraHeight / 2));
		
		
		
		
		g.setColor(new Color(0,0,0));
		g.fillRect(0,0, this.getWidth(), this.getHeight());
		
		
		
		ArrayList<Sprite> sprites = model.getSpriteList();
		
		for(Sprite sprite: sprites){
			sprite.draw(g, cameraX, cameraY);
		}
		


		
	}
	public static int getCameraX(){
		return cameraX;
	}
	public static int getCameraY(){
		return cameraY;
	}
	
	
}
