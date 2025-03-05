/* 
 Name: Christopher Heffernan
 Date: 3/27/24
 Assignment Description: Creating a Pacman Game utilizing Java Graphics
 */
import java.awt.Image;
import java.awt.Graphics;

public class Pacman extends Sprite{
    public static Image pacmanImages [][] = null;
    private int mouthSequence = 0; 
    private int loop = 0;
    private int direction = 0;
   
    Pacman(){
        super(0,0, 50,50, 5, true, true);
        

        pacmanImages = new Image[4][3];
        String temp;
        int counter = 1;

        for(int i = 0; i < pacmanImages.length; i++){
          for(int j = 0; j < pacmanImages[i].length; j++){
                    if(pacmanImages[i][j] == null){
                        temp = "pacman"+(counter)+".png";
                        
                
                        pacmanImages[i][j] = View.loadImage(temp);
                      
                        
                    }
                    if(pacmanImages[0][0] == null){
                        System.out.println("NULLL");
                    }
            
            counter++;
          }
          
        }
    }
    public boolean update(){
      return true;
    }
    public void draw(Graphics g, int cameraX, int cameraY){
      Image image = pacmanImages[direction][mouthSequence];
    
      g.drawImage(image, this.x-cameraX, this.y-cameraY, null);
    }
    public void resolveCollision(Sprite sprite){
       
       int spriteLeft = sprite.getLeft();
       int spriteRight = sprite.getRight();
       int spriteUp = sprite.getUp();
       int spriteDown = sprite.getDown();
       if(sprite instanceof Wall){
        if(this.pRight<= spriteLeft){
          this.x = spriteLeft-speed-w;
         }
         else if(this.pLeft >= spriteRight){
          this.x = spriteRight+speed;
         }else if(this.pDown <= spriteUp){
          this.y = spriteUp-speed-h;
         }
         else{
          this.y = spriteDown+speed;
         }
       }
      
       
    }
    public Json marshal(){
       Json object = Json.newObject();
        object.add("sprite", "pacman");
        object.add("x", x);
        object.add("y", y);
        object.add("w", w);
        object.add("h", h);
        return object;
    }

    
    public int getX(){
        return this.x;
    }
   public int getY(){
        return this.y;
    }
    public void setX(int x){
        this.x = x;
        
    }
    public void setY(int y){
        this.y = y;
    }
  
    @Override
    public String toString(){
          return "Pacman x: " + x + ", y: " + y + ", speed: " + speed;
    }
    public void right(){
      
      if(this.x + (int) this.speed > Game.width){
      
        this.x = 0;
      }else{
       
        this.x = this.x + this.speed;
        
      }
      this.direction  = 2;
    
    
      if(loop%5 ==0){
        if(this.mouthSequence == 2){
          this.mouthSequence = 0;
        }else{
          this.mouthSequence = this.mouthSequence+1;
        }
      }
      

      loop++;
    }
    public void left(){
    
      if(this.x - (int) this.speed <0){
        this.x = Game.width;
      }else{
        this.x = this.x - (int) this.speed;
      }
      this.direction = 0;
      

      if(loop%5==0){
        if(this.mouthSequence == 2){
          this.mouthSequence = 0;
        }else{
          this.mouthSequence = this.mouthSequence+1;
        }
      }
      
      loop++;
    
    }
    public void up(){
    if(this.y - (int) this.speed < 0){
      this.y = Game.height;
    }else{
      this.y = this.y - (int) this.speed;
    }
    
    this.direction = 1;
  
    
    if(loop%5==0){
      if(this.mouthSequence == 2){
        this.mouthSequence = 0;
      }else{
        this.mouthSequence = this.mouthSequence+1;
      }
    }
    
    loop++;
  
  
    }
    public void down(){
    if(this.y + (int) this.speed > Game.height){
      this.y = 0;
    }else{
      this.y = this.y + (int) this.speed;
    }
    this.direction = 3;
    if(loop%5==0){
      if(this.mouthSequence == 2){
        this.mouthSequence = 0;
      }else{
        this.mouthSequence = this.mouthSequence+1;
      }
    }
    

    loop++;
    }
    @Override
    public boolean isPacman(){
      return true;
    }
    public int getLeft(){
      return this.x;
    }
    public int getUp(){
      return this.y;
    }
    public int getDown(){
      return this.y+h;
    }
    public int getRight(){
      return this.x+w;
    }
    
}
    
  

  
  
  