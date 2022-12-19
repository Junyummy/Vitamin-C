import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Nemonemo15X9 extends AbNemo
{
	MainFrame other;
	JPanel nemo;
	MainFrame.JPanelTest win;
	//메뉴
	
	int heart = 4;
	Nemo15Arrow board;
	Column15X9 col;
	Row15X9 row;
	Backbutton2 backbt;
	//부착(add)할 클래스의 선언
	
	String data = "000011111100000000110010100000000100111100000000101101000000000101001000000000101101000000011100101001001111110111111110011100101001001000101101000000000101001000000000101101000000000100111100000000110010100000000011111100000"; //문제의 정답(화살)
	public static boolean endFlag = false; //퍼즐이 풀렸는지 여부
	
	public Nemonemo15X9(MainFrame.JPanelTest win)
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
		col = new Column15X9(this);
		this.add(col);
		col.setFont(new Font("SansSerif", Font.BOLD, 14));
		col.setBounds(390, 120, 480, 120);
		col.repaint();
			
		//row 생성
		row = new Row15X9(this);
		this.add(row);
		row.setFont(new Font("SansSerif", Font.BOLD, 14));
		row.setBounds(270, 240, 120, 700);
			
		//board 생성
		board = new Nemo15Arrow(this);
		this.add(board);
		board.setFont(new Font("SansSerif", Font.BOLD, 14));
		board.setBounds(390, 240, 480, 700);
		
		backbt = new Backbutton2(win);
		this.add(backbt);
		backbt.setFont(new Font("SansSerif", Font.BOLD, 14));
		backbt.setBounds(270, 120, 390, 240);
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
			Nemonemo15X9.endFlag = endFlag;
			board.repaint(); //퍼즐이 다 풀렸으면 보드의 칸을 채움
		}
	}
	
	public boolean getendFlag() {
		return Nemonemo15X9.endFlag;
	}
}
