import java.awt.*; //Color 상수 등을 위한 awt 패키지 선언
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Dog3BoardrrrrrrrrrrrrrLPPAN extends Canvas //Canvas 클래스를 상속
	implements MouseListener, MouseMotionListener
{	
	Nemonemo3 parent; //Nemonemo 클래스의 객체를 저장
	boolean drag = false; //마우스 드래그 상태인지 여부
	int startX, startY; //마우스 드래그를 시작한 좌표
	int endX, endY; //마우스 드래그를 끝마친 좌표
	int stopX, stopY;
	
	Image Life;
	Image Boom;
	
	int itemtool = 1;
	int Selrect = 1;
	int Bommtool = 2;
	int Bnum = 1;
	
	private Dimension dim;
	Image offScr; //더블버퍼링을 위한 가상 화면
	Graphics offG;
	
	public Dog3BoardrrrrrrrrrrrrrLPPAN(Nemonemo3 nemo) //Nemonemo 클래스의 객체를 보관하고 리스너를 선언
	{
		this.parent = nemo; //Nemonemo 클래스의 객체를 보관
		this.addMouseListener(this); //마우스 사용을 위한 리스너 선언
		this.addMouseMotionListener(this);
		try {
			Life = ImageIO.read(new File("resources/Life.png"));
			Boom = ImageIO.read(new File("resources/boom.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void initBufferd() {
		dim = getSize();
		setBackground(Color.white);
		offScr = createImage(dim.width,dim.height);
		offG = offScr.getGraphics();
	}
	
	public void paint (Graphics g) //화면에 보드의 상태를 출력
	{
		initBufferd();
		for(int j=0; j<10; j++)
			for(int i=0; i<10; i++)
			{
				if(parent.endFlag) //게임이 끝난 경우
				{
					if(parent.data.charAt(j*10+i)=='1')
					{
					offG.fillRect(i*40, j*40, 40, 40); 
					//칸을 채워서 문제가 풀렸음을 표시
					}
				}
				else
				{
					if(parent.temp[j*10+i]==1)
					{
						if(parent.data.charAt(j*10+i)!='1') {
							offG.setColor(Color.red); 
							offG.drawLine(i*40, j*40, i*40+40, j*40+40);
							offG.drawLine(i*40, j*40+40, i*40+40, j*40);
							parent.temp[j*10+i] = 2;
							
						}
						else {
							offG.setColor(Color.blue); 
							offG.fillRect(i*40, j*40, 40, 40);
						}
	//게임 진행중일 때는 네모표시
						
					}
					else if(parent.temp[j*10+i]==2)
					{
						offG.setColor(Color.red); 
	//게임 진행중일 때는 X표시
						offG.drawLine(i*40, j*40, i*40+40, j*40+40);
						offG.drawLine(i*40, j*40+40, i*40+40, j*40);
					}
				}
				
			}
			
		if(drag) //마우스를 드래그한 경우
		{
			offG.setColor(Color.yellow);
			if(startX==endX)
			{
				if(startY<endY)
				{
					offG.fillRect(40*startX, 40*startY, 40, 40*(endY-startY+1));
					offG.setColor(Color.red);
					offG.drawString(String.valueOf(endY-startY+1), endX*40+4, (endY+1)*40-4);
				}
				else
				{
					offG.fillRect(40*endX, 40*endY, 40, 40*(startY-endY+1));
					offG.setColor(Color.red);
					offG.drawString(String.valueOf(startY-endY+1), endX*40+4, (endY+1)*40-4);
				}
			}
			else if(startY==endY)
			{
				if(startX<endX)
				{
					offG.fillRect(40*startX, 40*startY, 40*(endX-startX+1), 40);
					offG.setColor(Color.red);
					offG.drawString(String.valueOf(endX-startX+1), endX*40+4, (endY+1)*40-4);
				}
				else
				{
					offG.fillRect(40*endX, 40*endY, 40*(startX-endX+1), 40);
					offG.setColor(Color.red);
					offG.drawString(String.valueOf(startX-endX+1), endX*40+4, (endY+1)*40-4);	
				}
			}
		}
		for(int j=0; j<10; j++) //격자 출력
			for(int i=0; i<10; i++)
			{
				offG.setColor(Color.black);
				offG.drawRect(i*40, j*40, 40, 40);
			}
		offG.setColor(Color.black);
		
		for(int i=0; i<=400; i+=40*5)
		{
			offG.drawLine(i-1, 0, i-1, 400);
			offG.drawLine(i+1, 0, i+1, 400);
		}
		
		for(int i=0; i<=400; i+=40*5)
		{
			offG.drawLine(0, i-1, 400, i-1);
			offG.drawLine(0, i+1, 400, i+1);
		}
		for(int i=0;i<parent.heart;i++) {
			offG.drawImage(Life, i*60 + 30, 530, i*60 + 90, 590, 0, 0, 1200, 1200, this);
		}
		offG.drawImage(Boom, 30, 430, 110, 510, 0, 0, 3000, 3000, this);
		offG.drawRect(29, 430, 81, 81);
		offG.drawRect(28, 429, 83, 83);
		offG.drawImage(Boom, 120, 430, 200, 510, 0, 0, 3000, 3000, this);
		offG.drawRect(119, 430, 81, 81);
		offG.drawRect(118, 429, 83, 83);
		g.drawImage(offScr, 0, 0, null); //가상 화면을 실제 화면으로 복사
	}

	
	public void update(Graphics g)
	{
		paint(g);
	}
	@Override
	public void mousePressed(MouseEvent e) //플레이어가 마우스 버튼을 누른 경우
	{
		int x = e.getX();
		int y = e.getY();
		
		if((x/40)>=10) return;
		if((y/40)>=10) return;
		if(parent.endFlag)return;
		
		startX = x/40;
		startY = y/40;
		endX = x/40;
		endY = y/40;
	}
	public void mouseReleased(MouseEvent e) 
//플레이어가 마우스 버튼을 놓은 경우
	{
		int x = e.getX();
		int y = e.getY();
		if((x > 29 && x < 111)&&(y > 429 && y < 511)&&itemtool==1){
			this.itemtool = 2;
			System.out.println(itemtool);
		}
		else if((x > 120 && x<201) && (y>429 && y<511)&&itemtool == 2) {
			itemtool = 1;
			System.out.println(itemtool);
		}
		if((x/40)>=10) return;
		if((y/40)>=10) return;
		if(parent.endFlag)return;
		if(itemtool == 1) {
			if(drag) {
				if(startX == endX) {
					if(startY < endY) {
						for(int i=startY; i<endY+1; i++) {
							if('0' == parent.data.charAt(10*i+startX)) {
								stopX = startX;
								stopY = i;
								break;
							}
							else {
								stopX = startX;
								stopY = i;
							}
						}
					}
					else {
						for(int i=endY; i<startY+1; i++) {
							if('0' == parent.data.charAt(10*i+startX)) {
								stopX = startX;
								stopY = i;
								break;
							}
							else {
								stopX = startX;
								stopY = i;
							}
						}
					}
					
					}
				else if(startY == endY) {
					if(startX < endX) {
						for(int i=startX; i<endX+1; i++) {
							if('0' == parent.data.charAt(10*startY+i)) {
								stopX = i;
								stopY = startY;
								break;
							}
							else {
								stopX = i;
								stopY = startY;
							}
						}					
					}
					else {
						for(int i=endX; i<startX+1; i++) {
							if('0' == parent.data.charAt(10*startY+i)) {
								stopX = i;
								stopY = startY;
								break;
							}
							else {
								stopX = i;
								stopY = startY;
							}
						}	
					}
				}
			}
			else {
				stopX = startX;
				stopY = startY;
			}
			if('0' == parent.data.charAt(10*(startY)+startX)) {
				parent.temp[startY*10+startX] = 2;
				parent.heart--;
				if(parent.heart == 0) {
					parent.win.change("mainp");
				}
			}
			else if('0' == parent.data.charAt(10*(stopY)+stopX)) {
				if(startX == endX) {
					for(int j=startX; j<stopX+1; j++) {
						for(int i=startY; i<stopY; i++) {
								parent.temp[i*10+j] = 1;
						}
					}
				}
				else if(startY == endY) {
					for(int j=startY; j<stopY+1; j++) {
						for(int i=startX; i<stopX; i++) {
							parent.temp[j*10+i] = 1;
						}
					}
				}
				parent.temp[stopY*10+stopX] = 2;
				parent.heart--;
				if(parent.heart == 0) {
					parent.win.change("mainp");
				}
				
			}
			else {
				if((e.getModifiers() & InputEvent.BUTTON3_MASK)!=0) 
					//마우스 오른쪽 버튼
					{
						setTemp(x, y, 2);
					}
				else //마우스 왼쪽 버튼 
					{
						setTemp(x, y, 1);
					}
			}
		}
		else if(this.itemtool == 2) {
			if(startX-1<=0) {
				for(int j = 0; j<startX+2;j++) {
					for(int i = startY-1; i<startY+2;i++) {
						if(parent.data.charAt(i*10+j) == '1') {
							parent.temp[i*10+j] = 1;
						}
						else {
							parent.temp[i*10+j] = 2;
						}
					}
				}
			}
			else if(startX+1>=10) {
				for(int j = startX-1; j<10;j++) {
					for(int i = startY-1; i<startY+2;i++) {
						if(parent.data.charAt(i*10+j) == '1') {
							parent.temp[i*10+j] = 1;
						}
						else {
							parent.temp[i*10+j] = 2;
						}
					}
				}
			}
			else if(startY-1<=0) {
				for(int j = startX-1; j<startX+2;j++) {
					for(int i = 0; i<startY+2;i++) {
						if(parent.data.charAt(i*10+j) == '1') {
							parent.temp[i*10+j] = 1;
						}
						else {
							parent.temp[i*10+j] = 2;
						}
					}
				}
			}
			else if(startY+1>=10) {
				for(int j = startX-1; j<startX+2;j++) {
					for(int i = startY-1; i<10;i++) {
						if(parent.data.charAt(i*10+j) == '1') {
							parent.temp[i*10+j] = 1;
						}
						else {
							parent.temp[i*10+j] = 2;
						}
					}
				}
			}
			else {
				for(int j = startX-1; j<startX+2;j++) {
					for(int i = startY-1; i<startY+2;i++) {
						if(parent.data.charAt(i*10+j) == '1') {
							parent.temp[i*10+j] = 1;
						}
						else {
							parent.temp[i*10+j] = 2;
						}
					}
				}
			}
			
		}	
		parent.display(); //퍼즐이 풀렸는지 검사
		stopX = 0; 
		stopY = 0;
		this.drag = false;
		repaint();
	}
	public void mouseMoved(MouseEvent e) //마우스가 움직인 경우
	{
		int x = e.getX();
		int y = e.getY();
		
		if((x/40)>=10) return;
		if((y/40)>=10) return;
		parent.showLocation(x/40, y/40); //column과 row에 마우스 커서의위치를 표시
		repaint();		
	}
	public void mouseExited(MouseEvent e) //마우스가 보드를 벗어난 경우
	{
		parent.showLocation(-1, -1); //column과 row에 마우스 커서의 위치를 표시하지 않음
		this.drag = false;
		repaint();	
	}
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseDragged(MouseEvent e) //마우스를 드래그한 경우
	{
		int x = e.getX();
		int y = e.getY();
		
		if((x/40)>=10) return;
		if((y/40)>=10) return;
		
		parent.showLocation(x/40, y/40); //column과 row에 마우스 커서의 위치를 표시		
		this.drag = true;
		endX = x/40;
		endY = y/40;
		repaint();	
	}
	private void setTemp(int x, int y, int value)  
        //플레이어의 입력을 temp 배열에 저장
	{
		int i;
		
		if(drag)
		{
			if(startX==endX)
			{
				if(startY<endY)
				{
					for(i=startY; i<=endY; i++)
					{
						parent.temp[startX+i*10] = value;
					}
				}
				else if(startY>endY)
				{
					for(i=endY; i<=startY; i++)
					{
						parent.temp[startX+i*10] = value;
					}
				}
				else
				{
					if(parent.temp[startX+startY*10]!=0)
						parent.temp[startX+startY*10] = 0;
					else
						parent.temp[startX+startY*10] = value;
				}
			}
			else if(startY==endY)
			{
				if(startX<endX)
				{
					for(i=startX; i<=endX; i++)
					{
						parent.temp[i+startY*10] = value;
					}
				}
				else if(startX>endX)
				{
					for(i=endX; i<=startX; i++)
					{
						parent.temp[i+startY*10] = value;
					}
				}
				else
				{
					if(parent.temp[startX+startY*10]!=0)
						parent.temp[startX+startY*10] = 0;
					else
						parent.temp[startX+startY*10] = value;
				}
			}
		}
		else
		{
			if(parent.temp[x/40+y/40*10]!=0)
				parent.temp[x/40+y/40*10] = 0;
			else
				parent.temp[x/40+y/40*10] = value;
		}
	}

}
