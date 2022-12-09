import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Dog3BoardrrrrrrrrrrrrrLPPAN extends Canvas implements MouseListener, MouseMotionListener
{	
	Nemonemo3 parent; //Nemonemo 클래스의 객체를 저장
	boolean drag = false; //마우스 드래그 상태인지 여부
	int startX, startY; //마우스 드래그를 시작한 좌표
	int endX, endY; //마우스 드래그를 끝마친 좌표
		
	private Dimension dim;
	Image offScr; //더블버퍼링을 위한 가상 화면
	Graphics offG;
	
	public Dog3BoardrrrrrrrrrrrrrLPPAN(Nemonemo3 nemo) //Nemonemo 클래스의 객체를 보관하고 리스너를 선언
	{
		this.parent = nemo; //Nemonemo 클래스의 객체를 보관
		this.addMouseListener(this); //마우스 사용을 위한 리스너 선언
		this.addMouseMotionListener(this);
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
					offG.fillRect(i*20, j*20, 20, 20); 
	//칸을 채워서 문제가 풀렸음을 표시
					}
				}
				else
				{
					if(parent.temp[j*10+i]==1)
					{
						if(parent.data.charAt(j*10+i)!='1') {
							offG.setColor(Color.red); 
							offG.drawLine(i*20, j*20, i*20+20, j*20+20);
							offG.drawLine(i*20, j*20+20, i*20+20, j*20);
							parent.temp[j*10+i] = 2;
							
						}
						else {
							offG.setColor(Color.blue); 
							offG.fillRect(i*20, j*20, 20, 20);
						}
	//게임 진행중일 때는 네모표시
						
					}
					else if(parent.temp[j*10+i]==2)
					{
						offG.setColor(Color.red); 
	//게임 진행중일 때는 X표시
						offG.drawLine(i*20, j*20, i*20+20, j*20+20);
						offG.drawLine(i*20, j*20+20, i*20+20, j*20);
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
					offG.fillRect(20*startX, 20*startY, 20, 20*(endY-startY+1));
					offG.setColor(Color.red);
					offG.drawString(String.valueOf(endY-startY+1), endX*20+2, (endY+1)*20-2);
				}
				else
				{
					offG.fillRect(20*endX, 20*endY, 20, 20*(startY-endY+1));
					offG.setColor(Color.red);
					offG.drawString(String.valueOf(startY-endY+1), endX*20+2, (endY+1)*20-2);
				}
			}
			else if(startY==endY)
			{
				if(startX<endX)
				{
					offG.fillRect(20*startX, 20*startY, 20*(endX-startX+1), 20);
					offG.setColor(Color.red);
					offG.drawString(String.valueOf(endX-startX+1), endX*20+2, (endY+1)*20-2);
				}
				else
				{
					offG.fillRect(20*endX, 20*endY, 20*(startX-endX+1), 20);
					offG.setColor(Color.red);
					offG.drawString(String.valueOf(startX-endX+1), endX*20+2, (endY+1)*20-2);	
				}
			}
		}
		for(int j=0; j<10; j++) //격자 출력
			for(int i=0; i<10; i++)
			{
				offG.setColor(Color.black);
				offG.drawRect(i*20, j*20, 20, 20);
			}
		offG.setColor(Color.black);
		
		for(int i=0; i<=200; i+=20*5)
		{
			offG.drawLine(i-1, 0, i-1, 200);
			offG.drawLine(i+1, 0, i+1, 200);
		}
		
		for(int i=0; i<=200; i+=20*5)
		{
			offG.drawLine(0, i-1, 200, i-1);
			offG.drawLine(0, i+1, 200, i+1);
		}
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
		
		if((x/20)>=10) return;
		if((y/20)>=10) return;
		if(parent.endFlag)return;
		
		startX = x/20;
		startY = y/20;
	}
	public void mouseReleased(MouseEvent e) 
//플레이어가 마우스 버튼을 놓은 경우
	{
		int x = e.getX();
		int y = e.getY();
		
		if((x/20)>=10) return;
		if((y/20)>=10) return;
		if(parent.endFlag)return;
		if('0' == parent.data.charAt(10*(startY)+startX)) {
			parent.temp[startY*10+startX] = 2;
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
		
		
		
		parent.display(); //퍼즐이 풀렸는지 검사
		this.drag = false;
		repaint();
	}
	public void mouseMoved(MouseEvent e) //마우스가 움직인 경우
	{
		int x = e.getX();
		int y = e.getY();
		
		if((x/20)>=10) return;
		if((y/20)>=10) return;
		parent.showLocation(x/20, y/20); //column과 row에 마우스 커서의위치를 표시
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
		
		if((x/20)>=10) return;
		if((y/20)>=10) return;
		
		parent.showLocation(x/20, y/20); //column과 row에 마우스 커서의 위치를 표시		
		this.drag = true;
		endX = x/20;
		endY = y/20;
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
			if(parent.temp[x/20+y/20*10]!=0)
				parent.temp[x/20+y/20*10] = 0;
			else
				parent.temp[x/20+y/20*10] = value;
		}
	}

}
