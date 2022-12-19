import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Nemonemo7 extends AbNemo{

	MainFrame other;
	JPanel nemo7;
	
	int heart = 4;
	Nemo2Kettle board;
	Column7 col;
	Row7 row;
	Backbutton1 backbt;
	
	String data = "0000100000000111000000110110001010001000101111100011100011111100010101010011010101000001010111111100"; //주전자
	
	public static boolean endFlag = false; //퍼즐이 풀렸는지 여부
	
	public Nemonemo7(MainFrame.JPanelTest win)
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
		nemo7 = new JPanel();
		nemo7.setBackground(Color.white);
		nemo7.setLayout(null); //null 레이아웃으로 설정
		
		//column 생성
		col = new Column7(this);
		this.add(col);
		col.setFont(new Font("SansSerif", Font.BOLD, 14));
		col.setBounds(390, 120, 480, 120);
		col.repaint();
			
		//row 생성
		row = new Row7(this);
		this.add(row);
		row.setFont(new Font("SansSerif", Font.BOLD, 14));
		row.setBounds(270, 240, 120, 600);
			
		//board 생성
		board = new Nemo2Kettle(this);
		this.add(board);
		board.setFont(new Font("SansSerif", Font.BOLD, 14));
		board.setBounds(390, 240, 480, 600);
		
		backbt = new Backbutton1(win);
		this.add(backbt);
		backbt.setFont(new Font("SansSerif", Font.BOLD, 14));
		backbt.setBounds(270, 120, 390, 240);
	}
	
	
	@Override
	public void showLocation(int mouseX, int mouseY) {
		// TODO Auto-generated method stub
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

	@Override
	public void display() {
		// TODO Auto-generated method stub
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
			Nemonemo7.endFlag = endFlag;
			board.repaint(); //퍼즐이 다 풀렸으면 보드의 칸을 채움
		}
	}
	
	public boolean getendFlag() {
		return Nemonemo7.endFlag;
	}
}
