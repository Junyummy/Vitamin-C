import java.awt.event.ActionEvent;

import javax.swing.JPanel;

public abstract class AbNemo extends JPanel{
	
	OtherFrame.JPanelTest win;
	//마우스 커서의 좌표
	int mouseX = -1;
	int mouseY = -1;
	
	String data = "";
	int[] temp; //플레이어가 입력한 답
	
	int columnNums[][]; //해당 열에 연속한 '1'의 개수를 표시
	int numOfColumn[]; //'0'으로 끊어진 연속한 1의 개수가 몇 개인가를 표시
	int rowNums[][]; //해당 행에 연속한 '1'의 개수를 표시
	int numOfRow[]; //'0'으로 끊어진 연속한 1의 개수가 몇 개인가를 표시
	
	
	
	public abstract void showLocation(int mouseX, int mouseY);//마우스 커서의위치를 표시
	public abstract void display();
}
