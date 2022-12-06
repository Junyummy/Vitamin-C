import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Nemonemo extends AbNemo
	{
		OtherFrame other;
		JPanel nemo;
		OtherFrame.JPanelTest win;
		//메뉴
		
		DogBoard board;
		Column col;
		Row row;
		//부착(add)할 클래스의 선언
		
		String data = "0001000000011100001101010000101111111110111111111000011111100001111100000100010000010001000011001100"; //문제의 정답(초기답:강아지)

		public static boolean endFlag = false; //퍼즐이 풀렸는지 여부
		
		public Nemonemo(OtherFrame.JPanelTest win)
		{
			setLayout(null);
			this.win = win;
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
			board = new DogBoard(this);
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
				Nemonemo.endFlag = endFlag;
				System.out.println(Nemonemo.endFlag);
				board.repaint(); //퍼즐이 다 풀렸으면 보드의 칸을 채움
			}
		}
		
		public boolean getendFlag() {
			return Nemonemo.endFlag;
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
				Nemonemo.endFlag = true;
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