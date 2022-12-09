import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Nemonemo1 extends AbNemo{

	OtherFrame other;
	JPanel nemo1;
	
	int heart = 4;
	Nemo2Snail board;
	Column1 col;
	Row1 row;
	
	String data = "0000000000000000000010000011000100010010010011101001110001100111110010001111111100000000000000000000"; //달팽이
	
	public static boolean endFlag = false; //퍼즐이 풀렸는지 여부
	
	public Nemonemo1(OtherFrame.JPanelTest win)
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
		nemo1 = new JPanel();
		nemo1.setBackground(Color.white);
		nemo1.setLayout(null); //null 레이아웃으로 설정
		
		//column 생성
		col = new Column1(this);
		this.add(col);
		col.setFont(new Font("SansSerif", Font.BOLD, 14));
		col.setBounds(120, 0, 900, 120);
		col.repaint();
			
		//row 생성
		row = new Row1(this);
		this.add(row);
		row.setFont(new Font("SansSerif", Font.BOLD, 14));
		row.setBounds(0, 120, 120, 900);
			
		//board 생성
		board = new Nemo2Snail(this);
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
			Nemonemo1.endFlag = endFlag;
			board.repaint(); //퍼즐이 다 풀렸으면 보드의 칸을 채움
		}
	}
	
	public boolean getendFlag() {
		return Nemonemo1.endFlag;
	}

}
