import javax.swing.*; //스윙 패키지 선언
import java.awt.*; //Font 상수 등을 위한 awt 패키지 선언
import java.awt.event.*;
import java.io.*;

//JFrame으로부터 상속받은 CloseableFrame 상속
public class OtherFrame extends JFrame 
	implements ActionListener
{
	JPanel contentPane;
	Nemonemo  nemo;
	Nemonemo1  nemo1;
	Nemonemo2  nemo2;
	Nemonemo3  nemo3;
	Nemonemo4  nemo4;
	Nemonemo5  nemo5;
	Nemonemo6  nemo6;
	Nemonemo7  nemo7;
	Nemonemo8  nemo8;

	//메뉴
	JMenuBar menuBar = new JMenuBar();
	JMenu gameMenu = new JMenu("Game");
	JMenu helpMenu = new JMenu("Help");
	
	//부착(add)할 클래스의 선언
	Nemo2Dog board;
	MainBoard10X10 board1;
	MainBoard15X15 board2;
	Column col;
	Row row;
	
	public OtherFrame()
	{	
		 JPanelTest win = new JPanelTest();
		 win.setJMenuBar(createMenuBar());
	     win.setTitle("frame test");
	     win.mainp = new MainPanel10X10(win);
	     win.mainp2 = new MainPanel15X15(win);
	     win.nemo = new Nemonemo(win);
	     win.nemo1 = new Nemonemo1(win);
	     win.nemo2 = new Nemonemo2(win);
	     win.nemo3 = new Nemonemo3(win);
	     win.nemo4 = new Nemonemo4(win);
	     win.nemo5 = new Nemonemo5(win);
	     win.nemo6 = new Nemonemo6(win);
	     win.nemo7 = new Nemonemo7(win);
	     win.nemo8 = new Nemonemo8(win);
	     win.nemo15 = new Nemonemo15X1(win);
	     win.setBackground(Color.white);
	     win.add(win.mainp);
	     win.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	     win.setSize(900, 900);
	     win.setVisible(true);
	     win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     

	
	}
		
	private JMenuBar createMenuBar()
	{
		JMenuBar menuBar = new JMenuBar();
			
		JMenu gameMenu = new JMenu("게임(g)");
		gameMenu.setMnemonic(KeyEvent.VK_G);
		//Game 메뉴의 서부메뉴 생성
		JMenuItem newGame = new JMenuItem("New Game ...");
		newGame.addActionListener(this);
		newGame.setActionCommand("newGame");
		gameMenu.add(newGame);
			
		JMenuItem answerGame = new JMenuItem("Answer");
		answerGame.addActionListener(this);
		answerGame.setActionCommand("answerGame");
		gameMenu.add(answerGame);
		  
		JMenuItem exitGame = new JMenuItem("Exit");
		exitGame.addActionListener(this);
		exitGame.setActionCommand("exitGame");
		gameMenu.add(exitGame);
		
		JMenu helpMenu = new JMenu("도움(h)");
		gameMenu.setMnemonic(KeyEvent.VK_H);
		//Help 메뉴의 서부메뉴 생성
		JMenuItem aboutGame = new JMenuItem("About Game...");
		aboutGame.addActionListener(this);
		aboutGame.setActionCommand("aboutGame");
		helpMenu.add(aboutGame);
		
		menuBar.add(gameMenu);
		menuBar.add(helpMenu);
		return menuBar;
	}
		
	public void actionPerformed(ActionEvent e) //선택한 메뉴에 따라실행할 루틴을 호출
	{
		String cmd = e.getActionCommand();
		
		if(cmd.equals("newGame")) //네모네모로직 데이터를 불러와 새 게임을 시작
		{
			
		}
		else if(cmd.equals("exitGame")) //게임 종료
			this.dispose();
	}
	

	public class JPanelTest extends JFrame {
		 
	    public MainPanel15X15 mainp2;
		public MainPanel10X10 mainp = null;
	    public Nemonemo nemo = null;
	    public Nemonemo1 nemo1 = null;
	    public Nemonemo2 nemo2 = null;
	    public Nemonemo3 nemo3 = null;
	    public Nemonemo4 nemo4 = null;
	    public Nemonemo5 nemo5 = null;
	    public Nemonemo6 nemo6 = null;
	    public Nemonemo7 nemo7 = null;
	    public Nemonemo8 nemo8 = null;
	    public Nemonemo15X1 nemo15 = null;
	 
	    public void change(String panelName) { // 패널 1번과 2번 변경 후 재설정
	 
	        if (panelName.equals("mainp")) {
	            getContentPane().removeAll();
	            getContentPane().add(mainp);
	            revalidate();
	            repaint();
	        }
	        else if(panelName.equals("nemo")) {
	        	getContentPane().removeAll();
	            getContentPane().add(nemo);
	            revalidate();
	            repaint();
	        }
	        else if(panelName.equals("nemo1")) {
	        	getContentPane().removeAll();
	            getContentPane().add(nemo1);
	            revalidate();
	            repaint();
	        }
	        else if(panelName.equals("nemo2")) {
	        	getContentPane().removeAll();
	            getContentPane().add(nemo2);
	            revalidate();
	            repaint();
	        }
	        else if(panelName.equals("nemo3")) {
	        	getContentPane().removeAll();
	            getContentPane().add(nemo3);
	            revalidate();
	            repaint();
	        }
	        else if(panelName.equals("nemo4")) {
	        	getContentPane().removeAll();
	            getContentPane().add(nemo4);
	            revalidate();
	            repaint();
	        }
	        else if(panelName.equals("nemo5")) {
	        	getContentPane().removeAll();
	            getContentPane().add(nemo5);
	            revalidate();
	            repaint();
	        }
	        else if(panelName.equals("nemo6")) {
	        	getContentPane().removeAll();
	            getContentPane().add(nemo6);
	            revalidate();
	            repaint();
	        }
	        else if(panelName.equals("nemo7")) {
	        	getContentPane().removeAll();
	            getContentPane().add(nemo7);
	            revalidate();
	            repaint();
	        }
	        else if(panelName.equals("nemo8")) {
	        	getContentPane().removeAll();
	            getContentPane().add(nemo8);
	            revalidate();
	            repaint();
	        }
	        else if(panelName.equals("mainp2")) {
	        	getContentPane().removeAll();
	            getContentPane().add(mainp2);
	            revalidate();
	            repaint();
	        }
	        else {
	        	getContentPane().removeAll();
	            getContentPane().add(nemo15);
	            revalidate();
	            repaint();
	        }

	    }
	    
	 
	}
}