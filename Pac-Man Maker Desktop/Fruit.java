/* 
 Name: Christopher Heffernan
 Date: 3/27/24
 Assignment Description: Creating a Pacman Game utilizing Java Graphics
 */
import java.awt.Image;
import java.awt.Graphics;
class Fruit extends Sprite{
    Image fruitImage;
    int fruit;
    int ydir;
    int loop;
    int prevCameraY;
    boolean isScrolling;
    Fruit(){
        super(0,0,50,50,5, true, true);
        this.fruit = 0;
        this.ydir = 1;
        this.loop = 0;
    }
    Fruit(int x, int y, int fruit){
        super(x,y, 20, 20, 5, true, true);
        this.fruit = fruit;
        this.ydir = 1;
        this.loop =0;
    }
    Fruit(Json object){
        super((int) object.getLong("x"),(int) object.getLong("y"),20,20,5, true, true);
        this.fruit = (int) object.getLong("type");
        this.ydir = 1;
        this.loop = 0;
       
       
     }

    public boolean update(){
        savePrevious();
        if(!isScrolling){
            this.speed = 4;
        }else{
            this.speed = 3;
        }
        this.y = this.y+this.speed*ydir;

        loop +=1;
        
        
        if(this.y >= Game.height){
            this.y = 1;
            
        }else if(this.y <= 0){
            this.y = Game.height-1;
            
        }
        
        
    
        
        return isValid;
       
    }
 
   
    public void draw(Graphics g, int cameraX, int cameraY){
    
        if (fruitImage == null) {
            fruitImage = View.loadImage("fruit"+fruit+".png"); // Lazy loading
            
        }
      
        int offsetY = cameraY - prevCameraY;
        if(offsetY != 0){
            isScrolling = true;
        }else{
            isScrolling = false;
        }

        g.drawImage(fruitImage, getLeft() - cameraX, getUp()-cameraY, getW(), getH(), null);

        
        this.prevCameraY = cameraY;
    
       
       
    }
    
    public Json marshal(){
        Json object = Json.newObject();
        object.add("sprite", "fruit");
        object.add("type", this.fruit);
        object.add("x", x);
        object.add("y", y);
        object.add("w", w);
        object.add("h", h);
        return object;
    }
    @Override
    public boolean isFruit(){
        return true;
    }
    public void resolveCollision(Sprite sprite){
        
       if(sprite.isPacman()){
            isValid = false;
          
       }
       else if(sprite.isWall()){
    
        int spriteUp = sprite.getUp();
        int spriteDown = sprite.getDown();
       
         if(this.pDown <= spriteUp){
           this.y = spriteUp-speed-h;
          }
          else{
           this.y = spriteDown+speed;
          }
          ydir*=-1;
       

        
    }
   
    }
}