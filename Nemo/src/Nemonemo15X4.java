import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Nemonemo15X4 extends AbNemo
{
	OtherFrame other;
	JPanel nemo;
	OtherFrame.JPanelTest win;
	//메뉴
	
	int heart = 4;
	Nemo15Penguin board;
	Column15X4 col;
	Row15X4 row;
	//부착(add)할 클래스의 선언
	
	String data = "000000011111100000000111111110000000100100110000000110110111000011110110111011100001111111110000000111111011110000111111000001111111111000000111111111000000111001111000000110000111000001110000111000001100000011000001100000011"; //문제의 정답(펭귄)

	public static boolean endFlag = false; //퍼즐이 풀렸는지 여부
	
	public Nemonemo15X4(OtherFrame.JPanelTest win)
	{
		setLayout(null);
		this.win = win;
		//변수 초기화
		temp = new int[225]; //가로 10칸, 세로 10칸으로 총 100칸 선언
		for(int i=0; i<225; i++) //플레이어가 입력하기 전에 0으로 모두 초기화
		{
			temp[i] = 0;
		}
		columnNums = new int[15][15];
		numOfColumn = new int[15];
		rowNums = new int[15][15];
		numOfRow = new int[15];
		nemo = new JPanel();
		nemo.setBackground(Color.white);
		nemo.setLayout(null); //null 레이아웃으로 설정
		
		//column 생성
		col = new Column15X4(this);
		this.add(col);
		col.setFont(new Font("SansSerif", Font.BOLD, 14));
		col.setBounds(120, 0, 900, 120);
		col.repaint();
			
		//row 생성
		row = new Row15X4(this);
		this.add(row);
		row.setFont(new Font("SansSerif", Font.BOLD, 14));
		row.setBounds(0, 120, 120, 900);
			
		//board 생성
		board = new Nemo15Penguin(this);
		this.add(board);
		board.setFont(new Font("SansSerif", Font.BOLD, 14));
		board.setBounds(120, 120, 900, 900);
		
		JButton btn = new JButton("돌아가기");
        btn.setSize(110, 20);
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
		for(int j=0; (j<15)&&endFlag; j++)
			for(int i=0; (i<15)&&endFlag; i++)
			{
				if((data.charAt(j*15+i)=='1')&&(temp[j*15+i]!=1))
					endFlag=false; //채워야 할 칸을 모두채웠는지 검사
				else if((data.charAt(j*15+i)!='1')&&(temp[j*15+i]==1))
					endFlag=false; //채우지 않아야 할 칸을채웠는지 검사
			}
		if(endFlag)
		{
			Nemonemo15X4.endFlag = endFlag;
			board.repaint(); //퍼즐이 다 풀렸으면 보드의 칸을 채움
		}
	}
	
	public boolean getendFlag() {
		return Nemonemo15X4.endFlag;
	}
}
