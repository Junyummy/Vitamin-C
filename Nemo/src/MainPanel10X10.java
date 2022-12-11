import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

import javax.swing.JButton;
import javax.swing.JPanel;

import javazoom.jl.player.Player;


public class MainPanel10X10 extends JPanel
	{	
		OtherFrame.JPanelTest win;
		JPanel Mainp;
		
		MainBoard10X10 board1;
		
		public MainPanel10X10(OtherFrame.JPanelTest win){
			setLayout(null);
			this.win = win;

			Mainp = new JPanel();
			Mainp.setLayout(null); //null 레이아웃으로 설정			
			
			//board 생성
			board1 = new MainBoard10X10(this);
			this.add(board1);
			board1.setFont(new Font("SansSerif", Font.BOLD, 14));
			board1.setBounds(120, 120, 600, 600);
			JButton btn = new JButton("15X15");
	        btn.setSize(110, 20);
	        btn.setLocation(751, 401);
	        add(btn);
	        btn.addActionListener(new MyActionListener());
			bgplay();
		}
		class MyActionListener implements ActionListener { // 버튼 키 눌리면 패널 1번 호출
            @Override
            public void actionPerformed(ActionEvent e) {
                win.change("mainp2");
            }
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