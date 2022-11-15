import javax.swing.JFrame;

public class MainFrame extends JFrame{

	public MainFrame() {
		setTitle("네모네모로직");
		setSize(800, 600);
		add(new Screen());
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
	}
