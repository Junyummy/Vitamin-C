import java.awt.*; //Color 상수 등을 위한 awt 패키지 선언
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Backbutton2 extends Canvas //Canvas 클래스를 상속
	implements MouseListener, MouseMotionListener
{	
	MainFrame.JPanelTest win;
	Image Back;
	
	private Dimension dim;
	Image offScr; //더블버퍼링을 위한 가상 화면
	Graphics offG;
	
	public Backbutton2(MainFrame.JPanelTest win) //Nemonemo 클래스의 객체를 보관하고 리스너를 선언
	{
		this.win = win;
		this.addMouseListener(this); //마우스 사용을 위한 리스너 선언
		this.addMouseMotionListener(this);
		try {
			Back = ImageIO.read(new File("resources/back.png"));
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
		offG.drawImage(Back, 15, 40, 55, 75, 0, 0, 500, 511, null);
		offG.setFont(new Font("SansSerif", Font.BOLD, 18));
		offG.drawString("Back", 60, 64);
		offG.drawRect(15,35, 90, 45);
		g.drawImage(offScr, 0, 0, null); //가상 화면을 실제 화면으로 복사
	}

	
	public void update(Graphics g)
	{

	}
	@Override
	public void mousePressed(MouseEvent e) //플레이어가 마우스 버튼을 누른 경우
	{

	}
	public void mouseReleased(MouseEvent e) 
//플레이어가 마우스 버튼을 놓은 경우
	{
		int x = e.getX();
		int y = e.getY();
		if((x>14&&x<106) &&(y>34 && y<81)) {
			win.change("mainp2");
		}
	}
	public void mouseMoved(MouseEvent e) //마우스가 움직인 경우
	{
	}
	public void mouseExited(MouseEvent e) //마우스가 보드를 벗어난 경우
	{
	}
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseDragged(MouseEvent e) //마우스를 드래그한 경우
	{	}
}
