/* 
 Name: Christopher Heffernan
 Date: 3/27/24
 Assignment Description: Creating a Pacman Game utilizing Java Graphics
 */
import java.awt.Image; 
import java.awt.Graphics;
class Ghost extends Sprite{
    private Image [][] ghostImages = null;
    private String ghostName; 
    private final String [] ghostNames = {"inky", "blinky", "pinky", "sue"};
    private boolean deathSequence, isScrolling;
    private int ghostNumber, iteration, loop, xdir, previousCameraX;
   

    Ghost(int x, int y, int ghostNumber){
     
        super(x,y,30,30,5,true, true);
        this.ghostNumber = ghostNumber;
        this.deathSequence = false;
        this.iteration = 0;
        this.loop = 0;
        this.xdir = 1;
        this.previousCameraX = 0;
        this.isScrolling = false;
        this.ghostName = this.ghostNames[this.ghostNumber];
        ghostImages = new Image[2][8];
       
    }
    Ghost(Json object){
        super((int) object.getLong("x"),(int) object.getLong("y"),30,30,5, true, true);
        this.ghostName = object.getString("type");
        this.iteration = 0;
        this.loop = 0;
        this.xdir = 1;
        this.previousCameraX = 0;
        this.isScrolling = false;
        ghostImages = new Image[2][8];
    }
    public Json marshal(){
        Json object = Json.newObject();
         object.add("sprite", "ghost");
         object.add("type", ghostName);
         object.add("x", x);
         object.add("y", y);
         object.add("w", w);
         object.add("h", h);
         return object;
     }
     public boolean update(){
        savePrevious();
        if(!isScrolling){
            this.speed = 4;
        }else{
            this.speed = 2;
        }
        this.x = this.x+this.speed*xdir;
        if(this.x >= Game.width){
        this.x = 1;
        
        }else if(this.x <= 0){
            this.x = Game.height-1;
            
         }
            
    
      
    
        if(loop%10 == 0){
            if(iteration < 7){
                iteration +=1;
            }else{
                iteration =0; 
            }
           
        }
      
        loop+=1;
        if(deathSequence && iteration ==7){
            isValid = false;
        }
        return isValid;
     }
     public void resolveCollision(Sprite sprite){
        if(sprite.isPacman()){
            deathSequence = true;
        }
        else if(sprite.isWall()){
    
            int spriteLeft = sprite.getLeft();
            int spriteRight = sprite.getRight();
           
             if(this.pLeft <= sprite.getLeft()){
               this.x = spriteLeft-speed-w;
              }
              else{
               this.x = spriteRight+speed;
              }
              xdir*=-1;
           
    
            
        }
     }
     public void draw(Graphics g, int cameraX, int cameraY){
        int deathSequenceNumber = deathSequence ? 1: 0;
        Image image = ghostImages[deathSequenceNumber][iteration];
        String loadString;
        int offsetX = cameraX - previousCameraX;
        if(offsetX != 0){
            isScrolling = true;
        }else{
            isScrolling = false;
        }
        if(image == null){
            if(deathSequenceNumber == 1){
                
                loadString = "ghost"+(iteration+1)+".png";
                ghostImages[deathSequenceNumber][iteration] = View.loadImage(loadString);
            }else{
                loadString = ghostName+(iteration+1)+".png";
                ghostImages[deathSequenceNumber][iteration] = View.loadImage(loadString);
            }
        }
        g.drawImage(image, x - cameraX, y - cameraY, w, h, null);
        previousCameraX = cameraX;
    }
       
     
     @Override
     public String toString(){
           return "Ghost x: " + x + ", y: " + y + " w: "+ w+ " h: "+ h+  " speed: " + speed;
     }
     @Override
     public boolean isGhost(){
        return true;
     }
    
}