import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MainBoard15X15 extends Canvas
	implements MouseListener, MouseMotionListener
{	
	MainPanel15X15 parent; //Nemonemo 클래스의 객체를 저장
	OtherFrame.JPanelTest win;
	Nemonemo15X1 nemo15X1;
	Nemonemo15X2 nemo15X2;
	Nemonemo15X3 nemo15X3;
	Nemonemo15X4 nemo15X4;
	Nemonemo15X5 nemo15X5;
	Nemonemo15X6 nemo15X6;
	Nemonemo15X7 nemo15X7;
	Nemonemo15X8 nemo15X8;
	Nemonemo15X9 nemo15X9;
	
	Image phone;
	Image cow;
	Image mikimouse;
	Image Penguin;
	Image rabbit;
	Image wing;
	Image boots;
	Image cross;
	Image arrow;
	Image mark;

	
	boolean drag = false; //마우스 드래그 상태인지 여부
	int startX, startY; //마우스 드래그를 시작한 좌표
	int endX, endY; //마우스 드래그를 끝마친 좌표
	private Dimension dim;
	Image offScr; //더블버퍼링을 위한 가상 화면
	Graphics offG;


	public MainBoard15X15(MainPanel15X15 mainp2) //Nemonemo 클래스의 객체를 보관하고 리스너를 선언
	{
		this.parent = mainp2; //Nemonemo 클래스의 객체를 보관
		nemo15X1 = new Nemonemo15X1(win);
		nemo15X2 = new Nemonemo15X2(win);
		nemo15X3 = new Nemonemo15X3(win);
		nemo15X4 = new Nemonemo15X4(win);
		nemo15X5 = new Nemonemo15X5(win);
		nemo15X6 = new Nemonemo15X6(win);
		nemo15X7 = new Nemonemo15X7(win);
		nemo15X8 = new Nemonemo15X8(win);
		nemo15X9 = new Nemonemo15X9(win);
		
	
		this.addMouseListener(this); //마우스 사용을 위한 리스너 선언
		this.addMouseMotionListener(this);
		
		try {
			phone = ImageIO.read(new File("resources/image/phone.png"));
			cow = ImageIO.read(new File("resources/image/cow.png"));
			mikimouse = ImageIO.read(new File("resources/image/mikimouse.png"));
			Penguin = ImageIO.read(new File("resources/image/펭귄.png"));
			rabbit = ImageIO.read(new File("resources/image/rabbit.png"));
			wing = ImageIO.read(new File("resources/image/wing.png"));
			boots = ImageIO.read(new File("resources/image/boots.png"));
			cross = ImageIO.read(new File("resources/image/cross.png"));
			arrow = ImageIO.read(new File("resources/image/arrow.png"));
			mark = ImageIO.read(new File("resources/image/mark.png")); 
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

	public void paint(Graphics g) {
		initBufferd();
		offG.clearRect(0, 0, dim.width, dim.height);
		offG.setColor(Color.black);
		offG.setFont(new Font("SansSerif", Font.BOLD, 14));
		offG.drawString("15X15", 85,20);
	
		if(nemo15X1.getendFlag()) {
			offG.drawImage(phone, 10, 30, 75, 85, 0, 0, 570, 570, nemo15X1);
		}
		else {
			offG.drawImage(mark, 10, 30, 75, 85, 0, 0, 348, 348, nemo15X1);
		}
		
		if(nemo15X2.getendFlag()) {
			offG.drawImage(cow, 70, 30, 135, 85, 0, 0, 570, 570, nemo15X2);
		}
		else {
			offG.drawImage(mark, 70, 30, 135, 85, 0, 0, 348, 348, nemo15X2);
		}
		
		if(nemo15X2.getendFlag()) {
			offG.drawImage(cow, 130, 30, 195, 85, 0, 0, 570, 570, nemo15X2);
		}
		else {
			offG.drawImage(mark, 130, 30, 195, 85, 0, 0, 348, 348, nemo15X2);
		}
		
		if(nemo15X3.getendFlag()) {
			offG.drawImage(mikimouse, 10, 90, 75, 145, 0, 0, 570, 570, nemo15X3);
		}
		else {
			offG.drawImage(mark, 10, 90, 75, 145, 0, 0, 348, 348, nemo15X3);
		}
		
		if(nemo15X4.getendFlag()) {
			offG.drawImage(Penguin, 70, 90, 135, 145, 0, 0, 570, 570, nemo15X4);
		}
		else {
			offG.drawImage(mark, 70, 90, 135, 145, 0, 0, 348, 348, nemo15X4);
		}
		
		if(nemo15X5.getendFlag()) {
			offG.drawImage(rabbit, 130, 90, 195, 145, 0, 0, 570, 570, nemo15X5);
		}
		else {
			offG.drawImage(mark, 130, 90, 195, 145, 0, 0, 570, 570, nemo15X5);
		}
		
		if(nemo15X6.getendFlag()) {
			offG.drawImage(wing, 10, 150, 75, 205, 0, 0, 570, 570, nemo15X6);
		}
		else {
			offG.drawImage(mark, 10, 150, 75, 205, 0, 0, 348, 348, nemo15X6);
		}
		
		if(nemo15X7.getendFlag()) {
			offG.drawImage(boots, 70, 150, 135, 205, 0, 0, 570, 570, nemo15X7);
		}
		else {
			offG.drawImage(mark, 70, 150, 135, 205, 0, 0, 348, 348, nemo15X7);
		}
		
		if(nemo15X8.getendFlag()) {
			offG.drawImage(cross, 130, 150, 195, 205, 0, 0, 570, 570, nemo15X8);
		}
		else {
			offG.drawImage(mark, 130, 150, 195, 205, 0, 0, 348, 348, nemo15X8);
		}
		if(nemo15X9.getendFlag()) {
			offG.drawImage(arrow, 130, 150, 195, 205, 0, 0, 570, 570, nemo15X9);
		}
		else {
			offG.drawImage(mark, 130, 150, 195, 205, 0, 0, 348, 348, nemo15X9);
		}
	offG.setColor(Color.black);
	offG.fillRect(400, 400, 40, 40);
	g.drawImage(offScr, 0, 0, null); //가상 화면을 실제 화면으로 복사
}

public MainBoard15X15(ActionListener actionListener) {
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
			parent.win.change("nemo15X1");
		}
		if((x>89 && x<151) && (y>29 && y<91)) {
			parent.win.change("nemo15X2");
		}
		if((x>149 && x<211) && (y>29 && y<91)) {
			parent.win.change("nemo15X3");
		}
		if((x>29 && x<91) && (y>89 && y<151)) {
			parent.win.change("nemo15X4");
		}
		if((x>89 && x<151) && (y>89 && y<151)) {
			parent.win.change("nemo15X5");
		}
		if((x>149 && x<211) && (y>89 && y<151)) {
			parent.win.change("nemo15X6");
		}
		if((x>29 && x<91) && (y>159 && y<211)) {
			parent.win.change("nemo15X7");
		}
		if((x>89 && x<151) && (y>159 && y<211)) {
			parent.win.change("nemo15X8");
		}
		if((x>149 && x<211) && (y>159 && y<211)) {
			parent.win.change("nemo15X9");
		}
		if((x>399 && x<441) && (y>399 && y<441)) {
			parent.win.change("mainp");
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