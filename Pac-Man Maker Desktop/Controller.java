/* 
 Name: Christopher Heffernan
 Date: 3/27/24
 Assignment Description: Creating a Pacman Game utilizing Java Graphics
 */
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;




public class Controller implements ActionListener, MouseListener, KeyListener

{
	private View view;
	private Model model;
	private boolean edit = false;
	private boolean add = false;
	private boolean left = false;
	private boolean right = false; 
	private boolean up  = false;
	private boolean down = false;
	private boolean fruit = false;
	private boolean remove = false;
	private boolean ghost = false;
	private boolean pellet = false;
	private int start_point [];
	private int end_point [];
	private int click_point [];
	public static final int scrollAmount = 10;


	
	

	public Controller(Model m)
	{
		model = m;
		this.start_point = new int[2];
		this.end_point = new int[2];
		this.click_point = new int[] {Integer.MAX_VALUE, Integer.MAX_VALUE};

	}

	public void actionPerformed(ActionEvent e)
	{
		view.removeButton();
	}
	void setView(View v)
	{
    	view = v;
	}
	public void mousePressed(MouseEvent e)
	{
		if(add && edit){
			this.start_point[0] = e.getX(); 
			this.start_point[1] = e.getY();
		}
	}
	
	public void mouseReleased(MouseEvent e) { 
		
		if(add && edit){
			this.end_point[0] = e.getX();
			this.end_point[1] = e.getY();
			if(this.end_point[0] < this.start_point[0]){
				model.drawSprite(end_point[0], end_point[1], start_point[0]-end_point[0], start_point[1]-end_point[1]);
			}else{
				model.drawSprite(start_point[0], start_point[1], end_point[0]-start_point[0], end_point[1]-start_point[1]);
			}
			
			
			
		}
		
		
	}
	public void mouseEntered(MouseEvent e) {    }
	public void mouseExited(MouseEvent e) {    }
	public void mouseClicked(MouseEvent e) { 
		System.out.println("Mouse Clicked");  
		if(edit){
			if(remove){
				this.click_point[0]= e.getX();
				this.click_point[1] = e.getY();
		
				update();
			
			}else if(fruit || ghost ||pellet){
				this.click_point[0]= e.getX()+View.getCameraX();
				this.click_point[1] = e.getY()+View.getCameraY();
				update();
			}
			
		}
			
	
		this.click_point[0] = Integer.MAX_VALUE;
		this.click_point[1] = Integer.MAX_VALUE;
		
	}	
	
	public void keyPressed(KeyEvent e)
	{

		
		switch(e.getKeyCode()){
			
			case KeyEvent.VK_UP: this.up = true; update(); break;
			case KeyEvent.VK_DOWN: this.down = true; update(); break;
			case KeyEvent.VK_RIGHT: this.right = true; update();break;
			case KeyEvent.VK_LEFT:  this.left = true; update(); break;
			default: break;
		}
		
		
	}
		
	

	public void keyReleased(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			/*Remeber your break statements after switch cases */
			case KeyEvent.VK_ESCAPE: System.exit(0); break;
			case KeyEvent.VK_Q: System.exit(0); break;
			case KeyEvent.VK_UP: this.up = false; update(); break;
			case KeyEvent.VK_DOWN: this.down = false; update(); break;
			case KeyEvent.VK_RIGHT: this.right = false; update(); break;
			case KeyEvent.VK_LEFT: this.left = false; update(); break;
			default: break;
	
			
			
		}
	
		
		char key  = Character.toLowerCase(e.getKeyChar());

		if(!add && key == 'c' && edit){
			model.Clear();
		}
		
		if(key == 's'){
			Json mapsave = model.marshal();
			mapsave.save("map.json");

		}
		if(key == 'l'){
			model.unmarshal();
		}
		if(key == 'e'){
			this.edit = !this.edit; 
			if(!edit){ 
				setAlltoFalse();
			}else{ 
				setOtherstoFalse("add");
			}
		}
		if(edit){

		
			if(key == 'a'){
					add = !add;
					setOtherstoFalse("add");

			}
			if(key == 'f'){
				fruit = !fruit; 
				setOtherstoFalse("fruit");
			}
			if(key == 'r'){
				remove = !remove;
				setOtherstoFalse("remove");
			}
			if(key == 'g'){
				ghost = !ghost;
				setOtherstoFalse("ghost");
			}
			if(key == 'p'){
				pellet = !pellet; 
				setOtherstoFalse("pellet");
			}
		}
			
		
		
		
		System.out.println("Edit Mode: " + edit + "\n" + "Walls: " +add +"\n"+"Fruit: " + fruit + "\n" + "Ghost: "+ ghost +"\n"+ "Remove Walls: " + remove + "\n"+"Pellet: " +pellet+ "\n");
	}

	public void keyTyped(KeyEvent e)
	{
	}
	public void setOtherstoFalse(String t){
		if(t.equals("add")){
			fruit = false;
			remove = false;
			ghost = false;
			pellet = false;
		}else if(t.equals("fruit")){
			add = false;
			remove = false;
			ghost = false;
			pellet = false;
		}else if(t.equals("ghost")){
			add = false;
			remove = false; 
			fruit = false;
			pellet = false;
		}else if(t.equals("pellet")){
			add = false;
			remove = false;
			fruit = false;
			ghost = false;
		}
		else{
			add = false; 
			fruit = false;
			ghost = false;
			pellet = false;
		}
	}
	public void setAlltoFalse(){
		fruit = false;
		add = false;
		ghost = false;
		pellet = false;
		remove = false;
	}
	public void update()
	{
		
		Model.pacman.savePrevious();
		
		if(this.left){
			
			Model.pacman.left();
			
			
			
		}
		else if (this.up){
			Model.pacman.up();
			
			
			
		}
		else if(this.right){
			
			Model.pacman.right();
			
			
		}
		else if(this.down){
			Model.pacman.down();
		}
		else if(this.click_point[0] != Integer.MAX_VALUE){

			if(remove == true){
				model.removeSprite(this.click_point[0], this.click_point[1]);
			}else if(fruit == true){
				model.addSprite("fruit", this.click_point[0], this.click_point[1]);
			}
			else if(ghost == true){
				model.addSprite("ghost", this.click_point[0], this.click_point[1]);
			}
			else if(pellet == true){
				model.addSprite("pellet", this.click_point[0], this.click_point[1]);
			}
		}
		
	
	}
	
}
