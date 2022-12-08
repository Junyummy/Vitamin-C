import java.awt.*; //Color 상수 등을 위한 awt 패키지 선언
import java.awt.event.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import javazoom.jl.player.Player;


public class MainBoard extends Canvas //Canvas 클래스를 상속
	implements MouseListener, MouseMotionListener
{	
	MainPanel parent; //Nemonemo 클래스의 객체를 저장
	OtherFrame.JPanelTest win;
	Nemonemo nemo;
	Nemonemo1 nemo1;
	boolean drag = false; //마우스 드래그 상태인지 여부
	int startX, startY; //마우스 드래그를 시작한 좌표
	int endX, endY; //마우스 드래그를 끝마친 좌표
	private Dimension dim;
	Image offScr; //더블버퍼링을 위한 가상 화면
	Graphics offG;
	
	public MainBoard(MainPanel mainp) //Nemonemo 클래스의 객체를 보관하고 리스너를 선언
	{
		this.parent = mainp; //Nemonemo 클래스의 객체를 보관
		nemo = new Nemonemo(win);
		nemo1 = new Nemonemo1(win);
		this.addMouseListener(this); //마우스 사용을 위한 리스너 선언
		this.addMouseMotionListener(this);
		
		bgplay();
	}
	
	private void bgplay() {
		Player jlPlayer = null;
        try {
            FileInputStream fileInputStream = new FileInputStream("resources/Empty.mp3");
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            jlPlayer = new Player(bufferedInputStream);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        final Player player = jlPlayer;
        new Thread() {
            public void run() {
                try {
                	player.play();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }.start();
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
			offG.fillRect(30, 30, 60, 60);
		}
		else {
			offG.setColor(Color.blue);
			offG.fillRect(30,30,60,60);
		}
		if(nemo1.getendFlag()) {
			offG.setColor(Color.green);
			offG.fillRect(90, 30, 60, 60);
		}
		else {
			offG.setColor(Color.red);
			offG.fillRect(90,30,60,60);
		}
		
		g.drawImage(offScr, 0, 0, null); //가상 화면을 실제 화면으로 복사
	}
	
	public MainBoard(ActionListener actionListener) {
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
