/* 
 Name: Christopher Heffernan
 Date: 3/27/24
 Assignment Description: Creating a Pacman Game utilizing Java Graphics
 */
import java.awt.Image;
import java.awt.Graphics;
class Pellet extends Sprite{
    public static Image pelletImage = null;
   
    Pellet(int x, int y){
        super(x,y,5,5,0, false, true);
        
    }
    Pellet(Json object){
        super((int) object.getLong("x"),(int) object.getLong("y"),5,5,5, true, true);
    }
    public boolean update(){
        return isValid;
    }
    public void resolveCollision(Sprite sprite){
        if(sprite.isPacman()){
            isValid = false;
        }
    }
    public void draw(Graphics g, int cameraX, int cameraY){
        
        
        if(pelletImage == null){
            System.out.println("Drawing Pellets");
            pelletImage = View.loadImage("pellet.png");
        }
        g.drawImage(pelletImage, x - cameraX, y - cameraY, w, h, null);
    }
    public Json marshal(){
        Json object = Json.newObject();
        object.add("sprite", "pellets");
        object.add("x", x);
        object.add("y", y);
        object.add("w", w);
        object.add("h", h);
        return object;
    }
}