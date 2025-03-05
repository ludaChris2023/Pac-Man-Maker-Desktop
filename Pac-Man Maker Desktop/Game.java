/* 
 Name: Christopher Heffernan
 Date: 3/27/24
 Assignment Description: Creating a Pacman Game utilizing Java Graphics
 */
import javax.swing.JFrame;
import java.awt.Toolkit;

public class Game extends JFrame
{
	private Controller controller;
	private Model model;
	private View view;
	public static final int width = 1000;
	public static final int height = 1000; 
	public Game()
	{
		model = new Model();
		controller = new Controller(model);
		view = new View(controller, model);
		view.addMouseListener(controller);
		this.addKeyListener(controller);
		this.setTitle("A4 Pacman");
		this.setSize(500, 500);
		this.setFocusable(true);
		this.getContentPane().add(view);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	public void run()
{
	while(true)
	{
		controller.update();
		model.update();
		view.repaint(); // This will indirectly call View.paintComponent
		//model.unmarshal();
		Toolkit.getDefaultToolkit().sync(); // Updates screen

		// Go to sleep for 40 milliseconds
		try
		{
			Thread.sleep(15);
		} catch(Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		
	}
}
	public static void main(String[] args)
	{
		Game g = new Game();
		g.run();
		
	}
}
