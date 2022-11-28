import javax.swing.*; //스윙 패키지 선언
import java.awt.*;
import java.awt.event.*;

public class AboutDialog extends JDialog //스윙의 JDialog 상속
	implements ActionListener, WindowListener
{
	//스윙 컴포넌트 선언
	JPanel aboutPanel;
	JButton ok;
	JLabel titleLabel, nameLabel;
	
	public AboutDialog(Frame parent)
	{
		super(parent, "Nemonemo Logic", true); //다이얼로그의 타이틀 설정
		this.setSize(240, 190); //다이얼로그 크기 설정
		this.addWindowListener(this);
		this.setLayout(new BorderLayout(15, 15));
		this.setFont(new Font("SansSerif", Font.BOLD, 14));
		
		createAboutPanel();
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==ok)
		{
			this.dispose();
		}
	}
	
	public void createAboutPanel()
	{
		aboutPanel = new JPanel();
		aboutPanel.setLayout(null);
		
		//About Game 정보 출력
		titleLabel = new JLabel("Nemonemo Logic 2011/12");
		aboutPanel.add(titleLabel);
		titleLabel.setBounds(40, 30, 200, 25);
		
		nameLabel = new JLabel("by @SOSO_RARA");
		aboutPanel.add(nameLabel);
		nameLabel.setBounds(50, 60, 200, 25);
		
		//다이얼로그 종료 버튼
		ok = new JButton("Okay");
		ok.addActionListener(this);
		aboutPanel.add(ok);
		ok.setBounds(80, 110, 80, 25);
		
		this.add("Center", aboutPanel);
	}
	//asas
	@Override
	public void windowClosing(WindowEvent e) {this.dispose();}
	public void windowOpened(WindowEvent e) {}
	public void windowClosed(WindowEvent e) {}
	public void windowIconified(WindowEvent e) {}
	public void windowDeiconified(WindowEvent e) {}
	public void windowActivated(WindowEvent e) {}
	public void windowDeactivated(WindowEvent e) {}
}
