import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

public class Column15X6 extends Canvas //Canvas 클래스를 상속
{

	Nemonemo15X6 parent; //Nemonemo 클래스의 객체를 저장
	
	Image offScr; //더블버퍼링을 위한 가상 화면
	Graphics offG;
	
	public Column15X6(Nemonemo15X6 nemo) //Nemonemo 클래스의 객체를 보관하고 모든열의 연속한 '1'의 개수를 계산
	{
		this.parent = nemo;
		getColumn();
	}
	
	public void getColumn() //데이터에 맞춰 column의 숫자를 생성
	{
		for(int i=0; i<15; i++) //모든 열에 연속한 '1'의 개수를 계산
		{
			parent.numOfColumn[i] = getNumber(i);
		}
	}
		
	int getNumber(int start) //해당하는 열의 연속한 '1'의 개수를 계산
	{
		int count = 0; //연속된 '1'의 개수
		int pos = 0; //몇 번째 연속된 '1'의 개수를 나타내는 수인지를 표시
			
		for(int i = start; i<225; i+=15) //같은 열에 속한 data의 값을 비교
		{
			if(parent.data.charAt(i)=='0' && count>0) 
//연속하지 않은 경우('0'인 경우)
			{
				parent.columnNums[start][pos++] = count;
				count = 0;
			}
			else if(parent.data.charAt(i)=='1' && count>=0) 
//연속한 경우('1'인 경우)
			{
				count++;
			}
		}
			
		if(count>0)
			parent.columnNums[start][pos++] = count;
		if(pos==0)
			parent.columnNums[start][pos++] = 0;
			
		return pos;
	}
		
	public void paint (Graphics g) //화면에 column을 출력
	{
		offScr = createImage(900, 121); //가상 화면 생성
		setBackground(Color.white);
		offG = offScr.getGraphics();
		if(parent.mouseX!=-1)
		{
			offG.setColor(Color.yellow);
			offG.fillRect(30*parent.mouseX, 0, 29, 120); 
//마우스 커서가 있는 열의 경우
		}
		
		offG.setColor(Color.black);
			
		for(int i=0; i<15; i++)
		{
			offG.drawLine(i*30, 0, i*30, 220);
			offG.setFont(new Font("함초롬돋움",Font.BOLD, 20));
			for(int j=0; j<parent.numOfColumn[i]; j++) //숫자 출력
			{
				if(String.valueOf(parent.columnNums[i][j]).length()<2)
					offG.drawString(String.valueOf(parent.columnNums[i][j]), i*30+9, ((100-parent.numOfColumn[i]*30+j*30)+30)/3*2+50);
				else
					offG.drawString(String.valueOf(parent.columnNums[i][j]), i*30+4, ((100-parent.numOfColumn[i]*30+j*30)+30)/3*2+50);			}
		}
				
		for(int i=0; i<=30*15; i+=30*5)
		{
			offG.drawLine(i-1, 0, i-1, 120);
			offG.drawLine(i+1, 0, i+1, 120);
		}
				
		offG.drawLine(450, 0, 450, 120);
		offG.drawLine(0, 120, 450, 120);
				
		g.drawImage(offScr, 0, 0, this);
	}
		
			
	public void update(Graphics g)
	{
		paint(g);
	}
}

