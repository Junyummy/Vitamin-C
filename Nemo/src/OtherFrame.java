import javax.swing.*; //스윙 패키지 선언
import java.awt.*; //Font 상수 등을 위한 awt 패키지 선언
import java.awt.event.*;
import java.io.*;
import javax.swing.JMenu;

//JFrame으로부터 상속받은 CloseableFrame 상속
public class OtherFrame extends JFrame 
	implements ActionListener
{
	JPanel contentPane;
	Nemonemo  nemo;
	Nemonemo1  nemo1;
	//메뉴
	JMenuBar menuBar = new JMenuBar();
	JMenu gameMenu = new JMenu("Game");
	JMenu helpMenu = new JMenu("Help");
	
	//부착(add)할 클래스의 선언
	DogBoard board;
	MainBoard board1;
	Column col;
	Row row;
	
	//마우스 커서의 좌표
	int mouseX = -1;
	int mouseY = -1;
	
	String data = "0001000000011100001101010000101111111110111111111000011111100001111100000100010000010001000011001100"; //문제의 정답(초기답:강아지)
	int[] temp; //플레이어가 입력한 답
	
	int columnNums[][]; //해당 열에 연속한 '1'의 개수를 표시
	int numOfColumn[]; //'0'으로 끊어진 연속한 1의 개수가 몇 개인가를 표시
	int rowNums[][]; //해당 행에 연속한 '1'의 개수를 표시
	int numOfRow[]; //'0'으로 끊어진 연속한 1의 개수가 몇 개인가를 표시
	
	boolean endFlag = false; //퍼즐이 풀렸는지 여부
	
	public OtherFrame()
	{
		
		 JPanelTest win = new JPanelTest();
		 win.setJMenuBar(createMenuBar());
	     win.setTitle("frame test");
	     win.mainp = new MainPanel(win);
	     win.nemo = new Nemonemo(win);
	     win.nemo1 = new Nemonemo1(win);
	     win.nemo15 = new Nemonemo15(win);
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
			showOpenDialog();
		else if(cmd.equals("answerGame")) //Answer를 선택하면정답을 출력
		{
			this.endFlag = true;
			board.repaint();
		}
		else if(cmd.equals("exitGame")) //게임 종료
			this.dispose();
	}
	
	//메뉴에서 New Game 선택 시, 퍼즐 데이터를 불러오는 메소드
	public void showOpenDialog()
	{
		FileDialog fd = new FileDialog(this, "Open a File", FileDialog.LOAD);
		
		fd.setFile("*.nemo; *.NEMO"); //데이터 파일의 확장자는nemo 또는 NEMO
		fd.setVisible(true);
		
		if(fd.getFile()!=null)
		{
			String filename = fd.getFile();
			String logicDir = fd.getDirectory();
			if(filename.indexOf('.')!=-1)
			{
				filename = (filename.substring(0, filename.indexOf('.'))).toLowerCase();
			}
			else
			{
				filename = filename.toLowerCase();
			}
			String logicName = filename;
			
			File f;
			FileInputStream from = null;
			BufferedReader d = null;
			
			try
			{
				f = new File(logicDir + logicName + ".nemo");
				from = new FileInputStream(f);
				d = new BufferedReader(new InputStreamReader(from));
				
				data = d.readLine();
				data.trim();
				
				d.close();
			}
			catch(IOException e)
			{
				System.out.println("I/O ERROR: "+ e);
			}
			
			//변수 초기화
			for(int i=0; i<100; i++)
			{
				temp[i] = 0;
			}
			this.endFlag = false;
			
			//불러온 데이터에 맞춰 column, row의 숫자를 재생성하고 깨끗한 보드를 다시 출력
			col.getColumn();
			row.getRow();
			board.repaint();
		}
	}
	
	public class JPanelTest extends JFrame {
		 
	    public MainPanel mainp = null;
	    public Nemonemo nemo = null;
	    public Nemonemo1 nemo1 = null;
	    public Nemonemo15 nemo15 = null;
	 
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
	        else {
	        	getContentPane().removeAll();
	            getContentPane().add(nemo15);
	            revalidate();
	            repaint();
	        }
	    }
	 
	}
}