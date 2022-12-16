import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import javazoom.jl.player.Player;

public class MainPanel15X15 extends JPanel{	
	OtherFrame.JPanelTest win;
	JPanel Mainp2;
	
	MainBoard15X15 board2;
	
	public MainPanel15X15(OtherFrame.JPanelTest win){
		setLayout(null);
		this.win = win;

		Mainp2 = new JPanel();
		Mainp2.setLayout(null); //null 레이아웃으로 설정			
		
		//board 생성
		board2 = new MainBoard15X15(this);
		this.add(board2);
		board2.setFont(new Font("SansSerif", Font.BOLD, 14));
		board2.setBounds(270, 120, 600, 680);
		bgplay();
		JButton btn = new JButton("10X10");
	    btn.setSize(110, 20);
        btn.setLocation(120, 455);
        add(btn);
        btn.addActionListener(new MyActionListener());
        bgplay();
	}
	class MyActionListener implements ActionListener { // 버튼 키 눌리면 패널 1번 호출
        @Override
        public void actionPerformed(ActionEvent e) {
            win.change("mainp");
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