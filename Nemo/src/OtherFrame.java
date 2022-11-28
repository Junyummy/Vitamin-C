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
	
	
	//메뉴
	JMenuBar menuBar = new JMenuBar();
	JMenu gameMenu = new JMenu("Game");
	JMenu helpMenu = new JMenu("Help");
	
	//부착(add)할 클래스의 선언
	Board board;
	Board1 board1;
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
		 this.setJMenuBar(createMenuBar());
	     win.setTitle("frame test");
	     win.mainp = new MainPanel(win);
	     win.nemo = new Nemonemo(win);
	 
	     win.add(win.mainp);
	     win.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	     win.setSize(500, 500);
	     win.setVisible(true);
		
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

	public void showLocation(int mouseX, int mouseY) //마우스 커서의위치를 표시
	{
		if(mouseX!=this.mouseX) //마우스 커서가 위치한 열이 변한 경우
		{
			this.mouseX = mouseX;
			col.repaint();
		}
		if(mouseY!=this.mouseY) //마우스 커서가 위치한 행이 변한 경우
		{
			this.mouseY = mouseY;
			row.repaint();
		}
	}
	
	public void display() //퍼즐이 풀렸는지 여부를 검사
	{
		boolean endFlag = true;
		for(int j=0; (j<10)&&endFlag; j++)
			for(int i=0; (i<10)&&endFlag; i++)
			{
				if((data.charAt(j*10+i)=='1')&&(temp[j*10+i]!=1))
					endFlag=false; //채워야 할 칸을 모두채웠는지 검사
				else if((data.charAt(j*10+i)!='1')&&(temp[j*10+i]==1))
					endFlag=false; //채우지 않아야 할 칸을채웠는지 검사
			}
		if(endFlag)
		{
			this.endFlag = endFlag;
			board.repaint(); //퍼즐이 다 풀렸으면 보드의 칸을 채움
		}
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
	
	public class MainPanel extends JPanel
	{	
		OtherFrame parent2;
		JPanelTest win;
		JPanel Mainp;
		
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
		
		public MainPanel(JPanelTest win){
			setLayout(null);
			this.win = win;
			this.parent2 = parent2;
			temp = new int[100]; //가로 10칸, 세로 10칸으로 총 100칸 선언
			for(int i=0; i<100; i++) //플레이어가 입력하기 전에 0으로 모두 초기화
			{
				temp[i] = 0;
			}
			columnNums = new int[10][10];
			numOfColumn = new int[10];
			rowNums = new int[10][10];
			numOfRow = new int[10];
			
			Mainp = new JPanel();
			Mainp.setLayout(null); //null 레이아웃으로 설정			
			
			//board 생성
			board1 = new Board1(this);
			this.add(board1);
			board1.setFont(new Font("SansSerif", Font.BOLD, 14));
			board1.setBounds(120, 120, 201, 201);
			
			JButton btn = new JButton("버튼");
	        btn.setSize(70, 20);
	        btn.setLocation(0, 0);
	        add(btn);
	        btn.addActionListener(new MyActionListener());
		}
		class MyActionListener implements ActionListener { // 버튼 키 눌리면 패널 1번 호출
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            win.change("nemo");
	        }
	    }
		
	}
	public class Nemonemo extends JPanel
	{
		JPanel nemo;
		private JPanelTest win;
		OtherFrame parent2;
		//메뉴
		
		//부착(add)할 클래스의 선언
		
		
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
		
		
		public Nemonemo(JPanelTest win)
		{
			setLayout(null);
			this.win = win;
			this.parent2 = parent2;
			//변수 초기화
			temp = new int[100]; //가로 10칸, 세로 10칸으로 총 100칸 선언
			for(int i=0; i<100; i++) //플레이어가 입력하기 전에 0으로 모두 초기화
			{
				temp[i] = 0;
			}
			columnNums = new int[10][10];
			numOfColumn = new int[10];
			rowNums = new int[10][10];
			numOfRow = new int[10];
			nemo = new JPanel();
			nemo.setBackground(Color.white);
			nemo.setLayout(null); //null 레이아웃으로 설정
			
			//column 생성
			col = new Column(this);
			this.add(col);
			col.setFont(new Font("SansSerif", Font.BOLD, 14));
			col.setBounds(120, 0, 201, 120);
			col.repaint();
				
			//row 생성
			row = new Row(this);
			this.add(row);
			row.setFont(new Font("SansSerif", Font.BOLD, 14));
			row.setBounds(0, 120, 120, 201);
				
			//board 생성
			board = new Board(this);
			this.add(board);
			board.setFont(new Font("SansSerif", Font.BOLD, 14));
			board.setBounds(120, 120, 201, 201);
			
			JButton btn = new JButton("버튼");
	        btn.setSize(70, 20);
	        btn.setLocation(10, 10);
	        add(btn);
	        btn.addActionListener(new MyActionListener());
		}
		class MyActionListener implements ActionListener { // 버튼 키 눌리면 패널 1번 호출
            @Override
            public void actionPerformed(ActionEvent e) {
                win.change("mainp");
            }
        }
			
		
		public void showLocation(int mouseX, int mouseY) //마우스 커서의위치를 표시
		{
			if(mouseX!=this.mouseX) //마우스 커서가 위치한 열이 변한 경우
			{
				this.mouseX = mouseX;
				col.repaint();
			}
			if(mouseY!=this.mouseY) //마우스 커서가 위치한 행이 변한 경우
			{
				this.mouseY = mouseY;
				row.repaint();
			}
		}
			
		public void display() //퍼즐이 풀렸는지 여부를 검사
		{
			boolean endFlag = true;
			for(int j=0; (j<10)&&endFlag; j++)
				for(int i=0; (i<10)&&endFlag; i++)
				{
					if((data.charAt(j*10+i)=='1')&&(temp[j*10+i]!=1))
						endFlag=false; //채워야 할 칸을 모두채웠는지 검사
					else if((data.charAt(j*10+i)!='1')&&(temp[j*10+i]==1))
						endFlag=false; //채우지 않아야 할 칸을채웠는지 검사
				}
			if(endFlag)
			{
				this.endFlag = endFlag;
				board.repaint(); //퍼즐이 다 풀렸으면 보드의 칸을 채움
			}
		}
			
		public void actionPerformed(ActionEvent e) //선택한 메뉴에 따라실행할 루틴을 호출
		{
			String cmd = e.getActionCommand();
				
			if(cmd.equals("newGame")) {
				//네모네모로직 데이터를 불러와 새 게임을 시작
				//showOpenDialog();
			}
				
			else if(cmd.equals("answerGame")) //Answer를 선택하면정답을 출력
			{
				this.endFlag = true;
				board.repaint();
			}
			else if(cmd.equals("exitGame")) //게임 종료
			{
				System.exit(0);
			}
			else if(cmd.equals("aboutGame")) {
				//애플리케이션 정보를 출력
				//showAboutDialog();
			}
				
		}
			


		//메뉴에서 New Game 선택 시, 퍼즐 데이터를 불러오는 메소드
//		public void showOpenDialog()
//		{
//			FileDialog fd = new FileDialog(this, "Open a File", FileDialog.LOAD);
//				
//			fd.setFile("*.nemo; *.NEMO"); //데이터 파일의 확장자는nemo 또는 NEMO
//			fd.setVisible(true);
//				
//			if(fd.getFile()!=null)
//			{
//				String filename = fd.getFile();
//				String logicDir = fd.getDirectory();
//				if(filename.indexOf('.')!=-1)
//				{
//					filename = (filename.substring(0, filename.indexOf('.'))).toLowerCase();
//				}
//				else
//				{
//					filename = filename.toLowerCase();
//				}
//				String logicName = filename;
//					
//				File f;
//				FileInputStream from = null;
//				BufferedReader d = null;
//					
//				try
//				{
//					f = new File(logicDir + logicName + ".nemo");
//					from = new FileInputStream(f);
//					d = new BufferedReader(new InputStreamReader(from));
//						
//					data = d.readLine();
//					data.trim();
//						
//					d.close();
//				}
//				catch(IOException e)
//				{
//					System.out.println("I/O ERROR: "+ e);
//				}
//					
//				//변수 초기화
//				for(int i=0; i<100; i++)
//				{
//					temp[i] = 0;
//				}
//				this.endFlag = false;
//					
//				//불러온 데이터에 맞춰 column, row의 숫자를 재생성하고 깨끗한 보드를 다시 출력
//				col.getColumn();
//				row.getRow();
//				board.repaint();
//			}
//		}
			
			//메뉴에서 About Game 선택 시 출력하는 애플리케이션 정보
//		public void showAboutDialog() 
//		{
//			AboutDialog ad = new AboutDialog(this);
//			ad.setVisible(true);
//		}
	}
	public class JPanelTest extends JFrame {
		 
	    public MainPanel mainp = null;
	    public Nemonemo nemo = null;
	 
	    public void change(String panelName) { // 패널 1번과 2번 변경 후 재설정
	 
	        if (panelName.equals("mainp")) {
	            getContentPane().removeAll();
	            getContentPane().add(mainp);
	            revalidate();
	            repaint();
	        } else {
	            getContentPane().removeAll();
	            getContentPane().add(nemo);
	            revalidate();
	            repaint();
	        }
	    }
	 
	}
	//메뉴에서 About Game 선택 시 출력하는 애플리케이션 정보
}