/* 
 Name: Christopher Heffernan
 Date: 3/27/24
 Assignment Description: Creating a Pacman Game utilizing Java Graphics
 */
import java.awt.image.BufferedImage;
import java.awt.Graphics;

class Wall extends Sprite{
    public static BufferedImage wallImage;
   
    Wall(){
        
        this(0,0,0,0);
        

    }
    Wall(int x, int y, int w, int h){
        super(x,y,w,h,0, false, true);
        
       
    }
    Wall(Json object){
        this.x = (int) object.getLong("x");
        this.y = (int) object.getLong("y");
        this.w = (int) object.getLong("w");
        this.h = (int) object.getLong("h");
     }
   public int getX(){
        return this.x;
    }
   public int getY(){
        return this.y;
    }
    public int getW(){
        return this.w;
    }
    public int getH(){
        return this.h;
    }
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
   public  void setW(int w){
        this.w = w;
    }
    public void setH(int h){
        this.h = h;
    }
   public Json marshal(){
        Json object = Json.newObject();
        object.add("sprite", "wall");

        object.add("x", x);
        object.add("y", y);
        object.add("w", w);
        object.add("h", h);
        return object;
    
    }
    @Override 
    public String toString()
    {
	    return "Wall (x,y) = (" + x + ", " + y + "), w = " + w + ", h = " + h;
    }
  
    public void draw(Graphics g, int cameraX, int cameraY){
        
        if (wallImage == null) {
            wallImage = View.loadImage("wall.png"); // Lazy loading
            
        }
        g.drawImage(wallImage, getX()-cameraX, getY()-cameraY, getW(), getH(), null);
    }

    public int getLeftSide(){
        return this.x;
    }
    public int getRightSide(){
        return this.x +this.w;
    }
    public int getUpSide(){
        return this.y; 
    }
    public int getDownSide(){
        return this.y + this.h;
    }
    public boolean update(){
        return true;
    }
    @Override
    public boolean isWall(){
        return true;
    }
    public void resolveCollision(Sprite sprite){

    }

    
}