import java.awt.*; //Color 상수 등을 위한 awt 패키지 선언
import java.awt.event.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import javazoom.jl.player.Player;


public class MainBoard10X10 extends Canvas //Canvas 클래스를 상속
	implements MouseListener, MouseMotionListener
{	
	MainPanel10X10 parent; //Nemonemo 클래스의 객체를 저장
	MainFrame.JPanelTest win;
	Nemonemo nemo;
	Nemonemo1 nemo1;
	Nemonemo2 nemo2;
	Nemonemo3 nemo3;
	Nemonemo4 nemo4;
	Nemonemo5 nemo5;
	Nemonemo6 nemo6;
	Nemonemo7 nemo7;
	Nemonemo8 nemo8;
	MainPanel15X15 mainp2;
	
	Image mark;
	Image candle;
	Image coffee;
	Image lp판;
	Image deer;
	Image dog;
	Image kettle;
	Image kid;
	Image cissors;
	Image snale;
	
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
		mainp2 = new MainPanel15X15(win);
		
		this.addMouseListener(this); //마우스 사용을 위한 리스너 선언
		this.addMouseMotionListener(this);
		
		try {
			mark = ImageIO.read(new File("resources/image/mark.png"));
			dog = ImageIO.read(new File("resources/image/dog.png"));
			snale = ImageIO.read(new File("resources/image/snale.png"));
			deer = ImageIO.read(new File("resources/image/deer.png"));
			lp판 = ImageIO.read(new File("resources/image/lp판.png"));
			coffee = ImageIO.read(new File("resources/image/coffee.png"));
			kid = ImageIO.read(new File("resources/image/kid.png"));
			cissors = ImageIO.read(new File("resources/image/가위.png"));
			kettle = ImageIO.read(new File("resources/image/주전자.png"));
			candle = ImageIO.read(new File("resources/image/촛불.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void initBufferd() {
		dim = getSize();
		offScr = createImage(dim.width,dim.height);
		offG = offScr.getGraphics();
	}
	
	public void paint(Graphics g) {
		initBufferd();
		offG.clearRect(0, 0, dim.width, dim.height);
		
		offG.setColor(Color.black);
		offG.setFont(new Font("SansSerif", Font.BOLD, 20));
		offG.drawString("10X10", 275,20);
		if(nemo.getendFlag()) {
			offG.drawImage(dog, 55, 30, 185, 205, 0, 0, 251, 251, nemo);
		}
		else {
			offG.drawImage(mark, 55, 30, 185, 205, 0, 0, 348, 348, nemo);
		}
		
		if(nemo1.getendFlag()) {
			offG.drawImage(snale, 235, 30, 365, 205, 0, 0, 251, 249, nemo1);
		}
		else {
			offG.drawImage(mark, 235, 30, 365, 205, 0, 0, 348, 348, nemo1);
		}
		
		if(nemo2.getendFlag()) {
			offG.drawImage(deer, 415, 30, 545, 205, 0, 0, 253, 251, nemo2);
		}
		else {
			offG.drawImage(mark, 415, 30, 545, 205, 0, 0, 348, 348, nemo2);
		}
		
		if(nemo3.getendFlag()) {
			offG.drawImage(lp판, 55, 255, 185, 425, 0, 0, 253, 251, nemo3);
		}
		else {
			offG.drawImage(mark, 55, 255, 185, 425, 0, 0, 348, 348, nemo3);
		}
		
		if(nemo4.getendFlag()) {
			offG.drawImage(coffee, 235, 255, 365, 425, 0, 0, 253, 251, nemo4);
		}
		else {
			offG.drawImage(mark, 235, 255, 365, 425, 0, 0, 348, 348, nemo4);
		}
		
		if(nemo5.getendFlag()) {
			offG.drawImage(kid, 415, 255, 545, 425, 0, 0, 253, 251, nemo5);
		}
		else {
			offG.drawImage(mark, 415, 255, 545, 425, 0, 0, 348, 348, nemo5);
		}
		
		if(nemo6.getendFlag()) {
			offG.drawImage(cissors, 55, 475, 185, 650, 0, 0, 499, 505, nemo6);
		}
		else {
			offG.drawImage(mark, 55, 475, 185, 650, 0, 0, 348, 348, nemo6);
		}
		
		if(nemo7.getendFlag()) {
			offG.drawImage(kettle, 235, 475, 365, 650, 0, 0, 409, 501, nemo7);
		}
		else {
			offG.drawImage(mark, 235, 475, 365, 650, 0, 0, 348, 348, nemo7);
		}
		
		if(nemo8.getendFlag()) {
			offG.drawImage(candle, 415, 475, 545, 650, 0, 0, 503, 503, nemo8);
		}
		else {
			offG.drawImage(mark, 415, 475, 545, 650, 0, 0, 348, 348, nemo8);
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
			if((x>54 && x<186) && (y>29 && y<206)) {
				parent.win.change("nemo");
			}
			if((x>234 && x<366) && (y>29 && y<206)) {
				parent.win.change("nemo1");
			}
			if((x>414 && x<546) && (y>29 && y<206)) {
				parent.win.change("nemo2");
			}
			if((x>54 && x<186) && (y>254 && y<426)) {
				parent.win.change("nemo3");
			}
			if((x>234 && x<366) && (y>254 && y<426)) {
				parent.win.change("nemo4");
			}
			if((x>414 && x<546) && (y>254 && y<426)) {
				parent.win.change("nemo5");
			}
			if((x>29 && x<186) && (y>474 && y<651)) {
				parent.win.change("nemo6");
			}
			if((x>234 && x<366) && (y>474 && y<651)) {
				parent.win.change("nemo7");
			}
			if((x>414 && x<546) && (y>474 && y<651)) {
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
