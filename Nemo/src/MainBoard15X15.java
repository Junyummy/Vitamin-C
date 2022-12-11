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

	}

	private void addMouseMotionListener(MainBoard15X15 mainBoard15X15) {
	// TODO Auto-generated method stub
	
	}

	private void addMouseListener(MainBoard15X15 mainBoard15X15) {
	// TODO Auto-generated method stub
	
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
			offG.setColor(Color.black);
			offG.fillRect(10, 30, 60, 60);
		}
	
		else {
			offG.setColor(Color.blue);
			offG.fillRect(10,30,60,60);
		}
	
		if(nemo15X2.getendFlag()) {
			offG.setColor(Color.green);
			offG.fillRect(70, 30, 60, 60);
		}
		else {
			offG.setColor(Color.red);
			offG.fillRect(70,30,60,60);
		}
	
		if(nemo15X3.getendFlag()) {
			offG.setColor(Color.yellow);
			offG.fillRect(130, 30, 60, 60);
		}
		else {
			offG.setColor(Color.orange);
			offG.fillRect(130,30,60,60);
		}	
	
		if(nemo15X4.getendFlag()) {
		offG.setColor(Color.pink);
		offG.fillRect(10, 90, 60, 60);
	}
	else {
		offG.setColor(Color.gray);
		offG.fillRect(10,90,60,60);
	}
	
	if(nemo15X5.getendFlag()) {
		offG.setColor(Color.cyan);
		offG.fillRect(70, 30, 60, 60);
	}
	else {
		offG.setColor(Color.black);
		offG.fillRect(70,90,60,60);
	}
	
	if(nemo15X6.getendFlag()) {
		offG.setColor(Color.red);
		offG.fillRect(130, 30, 60, 60);
	}
	else {
		offG.setColor(Color.cyan);
		offG.fillRect(130,90,60,60);
	}
	
	if(nemo15X7.getendFlag()) {
		offG.setColor(Color.blue);
		offG.fillRect(10, 150, 60, 60);
	}
	else {
		offG.setColor(Color.yellow);
		offG.fillRect(10,150,60,60);
	}
	
	if(nemo15X8.getendFlag()) {
		offG.setColor(Color.gray);
		offG.fillRect(70, 150, 60, 60);
	}
	else {
		offG.setColor(Color.green);
		offG.fillRect(70, 150, 60, 60);
	}
	
	if(nemo15X9.getendFlag()) {
		offG.setColor(Color.orange);
		offG.fillRect(130, 150, 60, 60);
	}
	else {
		offG.setColor(Color.pink);
		offG.fillRect(130, 150, 60, 60);
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