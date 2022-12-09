import java.awt.*; //Color 상수 등을 위한 awt 패키지 선언
import java.awt.event.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import javazoom.jl.player.Player;


public class MainBoard10X10 extends Canvas //Canvas 클래스를 상속
	implements MouseListener, MouseMotionListener
{	
	MainPanel10X10 parent; //Nemonemo 클래스의 객체를 저장
	OtherFrame.JPanelTest win;
	Nemonemo nemo;
	Nemonemo1 nemo1;
	Nemonemo2 nemo2;
	Nemonemo3 nemo3;
	Nemonemo4 nemo4;
	Nemonemo5 nemo5;
	Nemonemo6 nemo6;
	Nemonemo7 nemo7;
	Nemonemo8 nemo8;
	
	boolean drag = false; //마우스 드래그 상태인지 여부
	int startX, startY; //마우스 드래그를 시작한 좌표
	int endX, endY; //마우스 드래그를 끝마친 좌표
	private Dimension dim;
	Image offScr; //더블버퍼링을 위한 가상 화면
	Graphics offG;
	
	public MainBoard10X10(MainPanel10X10 mainp) //Nemonemo 클래스의 객체를 보관하고 리스너를 선언
	{
		this.parent = mainp; //Nemonemo 클래스의 객체를 보관
		nemo = new Nemonemo(win);
		nemo1 = new Nemonemo1(win);
		nemo2 = new Nemonemo2(win);
		nemo3 = new Nemonemo3(win);
		nemo4 = new Nemonemo4(win);
		nemo5 = new Nemonemo5(win);
		nemo6 = new Nemonemo6(win);
		nemo7 = new Nemonemo7(win);
		nemo8 = new Nemonemo8(win);
		
		this.addMouseListener(this); //마우스 사용을 위한 리스너 선언
		this.addMouseMotionListener(this);

	}

	public void initBufferd() {
		dim = getSize();
		setBackground(Color.white);
		offScr = createImage(dim.width,dim.height);
		offG = offScr.getGraphics();
	}
	
	public void paint(Graphics g) {
		initBufferd();
		offG.clearRect(0, 0, dim.width, dim.height);
		offG.setColor(Color.black);
		offG.setFont(new Font("SansSerif", Font.BOLD, 14));
		offG.drawString("10X10", 85,20);
		if(nemo.getendFlag()) {
			offG.setColor(Color.black);
			offG.fillRect(10, 30, 60, 60);
		}
		else {
			offG.setColor(Color.blue);
			offG.fillRect(10,30,60,60);
		}
		
		if(nemo1.getendFlag()) {
			offG.setColor(Color.green);
			offG.fillRect(70, 30, 60, 60);
		}
		else {
			offG.setColor(Color.red);
			offG.fillRect(70,30,60,60);
		}
		
		if(nemo2.getendFlag()) {
			offG.setColor(Color.yellow);
			offG.fillRect(130, 30, 60, 60);
		}
		else {
			offG.setColor(Color.orange);
			offG.fillRect(130,30,60,60);
		}
		
		if(nemo3.getendFlag()) {
			offG.setColor(Color.pink);
			offG.fillRect(10, 90, 60, 60);
		}
		else {
			offG.setColor(Color.gray);
			offG.fillRect(10,90,60,60);
		}
		
		if(nemo4.getendFlag()) {
			offG.setColor(Color.cyan);
			offG.fillRect(70, 30, 60, 60);
		}
		else {
			offG.setColor(Color.black);
			offG.fillRect(70,90,60,60);
		}
		
		if(nemo5.getendFlag()) {
			offG.setColor(Color.red);
			offG.fillRect(130, 30, 60, 60);
		}
		else {
			offG.setColor(Color.cyan);
			offG.fillRect(130,90,60,60);
		}
		
		if(nemo6.getendFlag()) {
			offG.setColor(Color.blue);
			offG.fillRect(10, 150, 60, 60);
		}
		else {
			offG.setColor(Color.yellow);
			offG.fillRect(10,150,60,60);
		}
		
		if(nemo6.getendFlag()) {
			offG.setColor(Color.gray);
			offG.fillRect(70, 150, 60, 60);
		}
		else {
			offG.setColor(Color.green);
			offG.fillRect(70, 150, 60, 60);
		}
		
		if(nemo6.getendFlag()) {
			offG.setColor(Color.orange);
			offG.fillRect(130, 150, 60, 60);
		}
		else {
			offG.setColor(Color.pink);
			offG.fillRect(130, 150, 60, 60);
		}
		
		g.drawImage(offScr, 0, 0, null); //가상 화면을 실제 화면으로 복사
	}
	
	public MainBoard10X10(ActionListener actionListener) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		int x = e.getX();
		int y = e.getY();
		System.out.println(x + " "+ y);
		if((e.getModifiers() & InputEvent.BUTTON3_MASK)!=0) 
			//마우스 오른쪽 버튼
		{
			
		}
		else //마우스 왼쪽 버튼 
		{
			if((x>29 && x<91) && (y>29 && y<91)) {
				parent.win.change("nemo");
			}
			if((x>89 && x<151) && (y>29 && y<91)) {
				parent.win.change("nemo1");
			}
			if((x>149 && x<211) && (y>29 && y<91)) {
				parent.win.change("nemo2");
			}
			if((x>29 && x<91) && (y>89 && y<151)) {
				parent.win.change("nemo3");
			}
			if((x>89 && x<151) && (y>89 && y<151)) {
				parent.win.change("nemo4");
			}
			if((x>149 && x<211) && (y>89 && y<151)) {
				parent.win.change("nemo5");
			}
			if((x>29 && x<91) && (y>159 && y<211)) {
				parent.win.change("nemo6");
			}
			if((x>89 && x<151) && (y>159 && y<211)) {
				parent.win.change("nemo7");
			}
			if((x>149 && x<211) && (y>159 && y<211)) {
				parent.win.change("nemo8");
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
