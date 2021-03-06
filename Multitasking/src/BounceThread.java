import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BounceThread {
	public static void main(String[] args){
		EventQueue.invokeLater(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				JFrame frame = new BounceFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
			
		});
	}
}
class BallRunnable implements Runnable{

	private Ball ball;
	private Component component;
	public static final int STEPS = 1000;
	public static final int DELAY=5;
	public BallRunnable(Ball aBall, Component aComponent){
		ball = aBall;
		component = aComponent;
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			for(int i=1;i <= STEPS; i++){
				ball.move(component.getBounds());
				component.repaint();
				Thread.sleep(DELAY);
			}
		}
		catch(InterruptedException e){
			
		};
	}
	
}
class BounceFrame extends JFrame{
	
	private BallComponent comp;
	public static final int DEFAULT_WIDTH = 450;
	public static final int DEFAULT_HEIGHT = 350;
	public static final int STEPS = 1000;
	public static final int DELAY = 3;
	public BounceFrame(){
		setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
		setTitle("BounceThread");
		
		comp = new BallComponent();
		add(comp,BorderLayout.CENTER);
		JPanel buttonPanel = new JPanel();
		addButton(buttonPanel,"Start", new ActionListener()
				{
						@Override
						public void actionPerformed(ActionEvent arg0) {
							// TODO Auto-generated method stub
							addBall();
						}
				});
		
		addButton(buttonPanel,"Close", new ActionListener()
		{
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					System.exit(0);
				}
		});
		add(buttonPanel,BorderLayout.SOUTH);
	}
	public void addButton(Container c, String title,ActionListener listener){
		JButton button = new JButton(title);
		c.add(button);
		button.addActionListener(listener);
	}
	public void addBall(){
		Ball b = new Ball();
		comp.add(b);
		Runnable r = new BallRunnable(b,comp);
		Thread t = new Thread(r);
		t.start();
	}
}