import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;


public class MainPanel extends JPanel
	{	
		OtherFrame parent2;
		OtherFrame.JPanelTest win;
		JPanel Mainp;
		
		Board1 board1;
		
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
		
		public MainPanel(OtherFrame.JPanelTest win){
			setLayout(null);
			this.win = win;
			temp = new int[100]; //가로 10칸, 세로 10칸으로 총 100칸 선언
			for(int i=0; i<100; i++) //플레이어가 입력하기 전에 0으로 모두 초기화
			{
				temp[i] = 0;
			}			
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