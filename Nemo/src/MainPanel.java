import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

import javax.swing.JButton;
import javax.swing.JPanel;

import javazoom.jl.player.Player;


public class MainPanel extends JPanel
	{	
		OtherFrame.JPanelTest win;
		JPanel Mainp;
		
		MainBoard board1;
		
		public MainPanel(OtherFrame.JPanelTest win){
			setLayout(null);
			this.win = win;

			Mainp = new JPanel();
			Mainp.setLayout(null); //null 레이아웃으로 설정			
			
			//board 생성
			board1 = new MainBoard(this);
			this.add(board1);
			board1.setFont(new Font("SansSerif", Font.BOLD, 14));
			board1.setBounds(120, 120, 201, 201);
			bgplay();
		}
		
		private void bgplay() {
			Player jlPlayer = null;
	        try {
	            FileInputStream fileInputStream = new FileInputStream("resources/Empty.mp3");
	            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
	            jlPlayer = new Player(bufferedInputStream);
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        }
	        
	        final Player player = jlPlayer;
	        new Thread() {
	            public void run() {
	                try {
	                	player.play();
	                } catch (Exception e) {
	                    System.out.println(e.getMessage());
	                }
	            }
	        }.start();
	    }
		
}