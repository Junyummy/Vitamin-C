import java.awt.*; //Color 상수 등을 위한 awt 패키지 선언

public class Row2 extends Canvas //Canvas 클래스를 상속
{
	Nemonemo2 parent; //Nemonemo 클래스의 객체를 저장
	Image offScr; //더블버퍼링을 위한 가상 화면
	Graphics offG;
	
	public Row2(Nemonemo2 nemo) //Nemonemo 클래스의 객체를 보관하고 모든 행의 연속한 '1'의 개수를 계산
	{ 
		this.parent = nemo;
		getRow(); 
	}
	
	public void getRow() //데이터에 맞춰 row의 숫자를 생성
	{
		for(int i=0; i<10; i++) //모든 행에 연속한 '1'의 개수를 계산
		{
			parent.numOfRow[i] = getNumber(i);
		}
	}
		
	int getNumber(int start) //해당하는 행의 연속한 '1'의 개수를 계산
	{
		int count = 0; //연속된 '1'의 개수
		int pos = 0; //몇 번째 연속된 '1'의 개수를 나타내는 수인지를 표시
			
		for(int i=start*10; i<(start+1)*10; i++) 
//같은 행에 속한 data의 값을 비교
		{
			if(parent.data.charAt(i)=='0' && count>0) 
//연속하지 않은 경우('0'인 경우)
			{
				parent.rowNums[start][pos++] = count;
				count = 0;
			}
			else if(parent.data.charAt(i)=='1' && count>=0) 
//연속한 경우('1'인 경우)
			{
				count++;
			}
		}
			
		if(count>0)
			parent.rowNums[start][pos++] = count;
		if(pos==0)
			parent.rowNums[start][pos++] = 0;
			
		return pos;
	}
		
	public void paint (Graphics g) //화면에 row를 출력
	{
		offScr = createImage(121, 900); //가상 화면 생성
		setBackground(Color.white);
		offG = offScr.getGraphics();
		if(parent.mouseY!=-1)
		{
			offG.setColor(Color.yellow);
			offG.fillRect(0, 40*parent.mouseY, 120, 39); 
//마우스 커서가 있는 행의 경우
		}
		
		offG.setColor(Color.black);
			
		for(int i=0; i<10; i++)
		{
			offG.drawLine(0, i*40, 120, i*40);
			offG.setFont(new Font("함초롬돋움",Font.BOLD, 20));
			for(int j=0; j<parent.numOfRow[i]; j++) //숫자 출력
			{			if(String.valueOf(parent.rowNums[i][j]).length()<2)
					
					offG.drawString(String.valueOf(parent.rowNums[i][j]), (120-parent.numOfRow[i]*40)+j*40+27, i*40+27);
				else
					offG.drawString(String.valueOf(parent.rowNums[i][j]), (120-parent.numOfRow[i]*40)+j*40+21, i*40+27);
			}
		}
				
		for(int i=0; i<=40*10; i+=40*5)
		{
			offG.drawLine(0, i-1, 120, i-1);
			offG.drawLine(0, i+1, 120, i+1);
		}
				
		offG.drawLine(0, 400, 120, 400);
		offG.drawLine(120, 0, 120, 400);
				
		g.drawImage(offScr, 0, 0, this);
	}
		
			
	public void update(Graphics g)
	{
		paint(g);
	}
}
