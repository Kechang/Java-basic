
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Bounce {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				JFrame frame = new BounceFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}

}
class BounceFrame extends JFrame{
	private static final int DEFAULT_WIDTH = 450;
	private static final int DEFAULT_HEIGHT = 350;
	private static final int STEPS = 1000;
	private static final int DELAY = 3;
	private BallComponent comp;

	public BounceFrame(){
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setTitle("Bounce");
		
		comp = new BallComponent();
		add(comp, BorderLayout.CENTER);
		JPanel buttonPanel = new JPanel();
		addButton(buttonPanel,"Start", new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				addBall();
			}
			
		});
		
		addButton(buttonPanel,"Close",new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent event){
				System.exit(0);
			}
		});
		add(buttonPanel,BorderLayout.SOUTH);
		
	}

	public void addButton(JPanel c, String title, ActionListener actionListener) {
		// TODO Auto-generated method stub
		JButton button = new JButton(title);
		c.add(button);
		button.addActionListener(actionListener);
	}
	public void addBall(){
		try{
			Ball ball = new Ball();
			comp.add(ball);
			
			for(int i =1 ; i<= STEPS; i++){
				ball.move(comp.getBounds());
				comp.paint(comp.getGraphics());
				Thread.sleep(DELAY);
			}
		}catch(InterruptedException e){
			
		}
		
	}
}
