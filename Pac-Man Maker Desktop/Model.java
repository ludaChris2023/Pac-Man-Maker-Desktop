/* 
 Name: Christopher Heffernan
 Date: 3/27/24
 Assignment Description: Creating a Pacman Game utilizing Java Graphics
 */
import java.util.ArrayList;
import java.util.Iterator;

import java.util.Random;
public class Model 
{

   /* make marshal and unmarshal functions for JSON */
	private ArrayList<Sprite> sprites;
	private ArrayList<Sprite> toAdd;
	int previousX;
	int previousY;
	int cameraX;
	int cameraY;


	
	public static final Pacman pacman = new Pacman();

	public Model()
	{
		
		sprites = new ArrayList<Sprite>();
		toAdd = new ArrayList<Sprite>();
		toAdd.add(pacman);
		unmarshal();
		
		
	}

	public void update()
	{
	
		sprites.addAll(toAdd);
		toAdd.clear();
		Iterator<Sprite> iter1 = sprites.iterator();
		while(iter1.hasNext()){
			Sprite sprite1 = iter1.next();
			if(!sprite1.update()){
				iter1.remove();
				continue;
			}
			
			Iterator<Sprite> iter2 = sprites.iterator();
			while(iter2.hasNext()){
				Sprite sprite2 = iter2.next();
				if(sprite1.getClass() !=sprite2.getClass() && sprite1.checkCollision(sprite2)){
						
						sprite1.resolveCollision(sprite2);
				}
			}
			
		}
		
			
		}
		
		
		


	void setPreviousCoordinates()
	{
		pacman.savePrevious();
	}
	public void drawSprite(int xClick, int yClick, int w, int h){
		Sprite draw;
		int xCord, yCord;
		int cameraX = View.getCameraX();
		int cameraY = View.getCameraY();
		if(w > 0){

			xCord = xClick+cameraX;
		}else{
			xCord = xClick+w+cameraX;
			w = -w;
		}
		if(h >0){
			
			yCord = yClick+cameraY; 
		}else{
			yCord = yClick+h+cameraY;
			h = -h;
		}
		
		draw  = new Wall(xCord, yCord, w, h);
		toAdd.add(draw);
		
		
	}

	public int getSpriteSize(){
		return sprites.size();
	}

	public void removeSprite(int x, int y) {
        Iterator<Sprite> spriteIterator = sprites.iterator();
		int xClick = x+View.getCameraX();
		int yClick = y+View.getCameraY();
		while(spriteIterator.hasNext()){
			Sprite sprite = spriteIterator.next();
	
			if(xClick >= sprite.getLeft() && xClick <= sprite.getRight() && yClick >= sprite.getUp() && yClick <= sprite.getDown()){
				spriteIterator.remove();
			}
		}
        
    } 

	
	public void Clear(){
		this.sprites = new ArrayList<Sprite>();
	}
	public Json marshal(){

		Json tmpList = Json.newList();
		for(Sprite sprite: sprites){
			tmpList.add(sprite.marshal());
		}
		return tmpList;
	}
	public void unmarshal(){
	
		Json json = Json.load("map.json");
		Sprite sprite;
		String spriteName;

		for(int i = 0; i < json.size(); i++){
			spriteName =  json.get(i).getString("sprite");
			
			if(spriteName.equals("wall")){
				sprite = new Wall(json.get(i));
				toAdd.add(sprite);
			}
			else if(spriteName.equals("fruit")){
				sprite = new Fruit(json.get(i));
				toAdd.add(sprite);
			}
			else if(spriteName.equals("ghost")){
				sprite = new Ghost(json.get(i));
				toAdd.add(sprite);
			}
			else if(spriteName.equals("pellets")){
				sprite = new Pellet(json.get(i));
				toAdd.add(sprite);
			}
		}
		
		
		
	}
	public void addSprite(String type, int x, int y){

		Sprite sprite = null;
		Random rand = new Random();
		int fruitVariety = rand.nextInt(7)+1;
		int ghostVariety = rand.nextInt(4);
		
		
		
		if(type.equals("fruit")){
			fruitVariety = rand.nextInt(7)+1;
			System.out.println("Fruit");
			sprite = new Fruit(x, y, fruitVariety);
			this.toAdd.add(sprite);
		}
	
		if(type.equals("ghost")){
			sprite = new Ghost(x, y, ghostVariety);
		
			this.toAdd.add(sprite);

		}
		if(type.equals("pellet")){
			sprite = new Pellet(x,y);
			this.toAdd.add(sprite);
		}

		
		
		
		
	}

	
	
	public ArrayList<Sprite> getSpriteList(){
		ArrayList<Sprite> copyOfSprites = new ArrayList<>(sprites);
    
		return copyOfSprites;
	}

}
	
	
	

