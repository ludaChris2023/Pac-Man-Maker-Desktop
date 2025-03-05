/* 
 Name: Christopher Heffernan
 Date: 3/27/24
 Assignment Description: Creating a Pacman Game utilizing Java Graphics
 */
import java.awt.Graphics;
abstract class Sprite{
    /*Class to setup up basic functions of characters or 'sprites' */
    protected int x,y,w,h, px, py, pLeft, pRight, pUp, pDown, speed;
    protected boolean moving, isValid;
    Sprite(){
        /*Default Constructor */
        this(0,0,0,0, 0, false, true);
    }
    Sprite(int x, int y, int w, int h, int speed, boolean moving, boolean isValid){
        /* Allows Subclass to Initialize Superclasses' Instance Variables*/
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.speed = speed;
        this.moving = moving;
        this.isValid = isValid;
    }
    Sprite(Json object){
        /*Instantiate Sprite Object via Json Unmarshaling */
        this.x = (int) object.getLong("x");
        this.y = (int) object.getLong("y");
        this.w = (int) object.getLong("w");
        this.h = (int) object.getLong("h");
     }
   
    abstract boolean update();
    abstract void draw(Graphics g, int cameraX, int cameraY);
    abstract Json marshal();
    abstract void resolveCollision(Sprite sprite);
   
    public boolean checkCollision(Sprite sprite){
        boolean colliding = true;
        /*Check Null Hypothesis */
        if(getRight() < sprite.getLeft()){
				
          colliding = false;
        
          
        }
      
        if(getLeft() > sprite.getRight()){
        
          colliding = false;
          
        }
        
        if(getUp() > sprite.getDown()){ // assumes bigger is downward
          
          colliding = false;
          
        }
      
        
        if(getDown() < sprite.getUp()){ // assumes bigger is downward
        
          colliding = false;
          
        }
     
        return colliding;
      }
      public void savePrevious()
      {
        px = x;
        py = y;
        pLeft = x;
        pUp = y;
        pRight = px+w;
        pDown = py+h;

      }
      
      
    public boolean isPacman(){
      return false;
    }
    public boolean isGhost(){
      return false;
    }
    public boolean isFruit(){
      return false;
    }
    public boolean isWall(){
      return false;
    }
    public boolean isMoving(){
      return this.moving;
    }
    public boolean isValid(){
      return isValid; 
    }
    public int getLeft(){
        return this.x;
    }
    public int getRight(){
        return this.x +this.w;
    }
    public int getUp(){
        return this.y;
    }
    public int getDown(){
        return this.y+this.h;
    }
    public int getW(){
      return this.w;
    }
    public int getH(){
      return this.h;
    }
    @Override 
    public String toString(){
      return "this.x";
    }
    


}