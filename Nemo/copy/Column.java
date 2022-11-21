import java.awt.*;

public class Column extends Canvas{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Nemonemo parent;
	Image offScr;
	Graphics offG;
	
	public Column(Nemonemo parent) {
		this.parent = parent;
		getColumn();
	}
	
	public void getColumn() {
		for(int i=0; i<parent.NUM_COLUMN; i++) {
			parent.numOfColumn[i] = getNumber(i);
		}
	}
	
	int getNumber(int start) {
		int count = 0;
		int pos = 0;
		
		for(int i=start; i<parent.NUM_COLUMN * parent.NUM_ROW; i+=parent.NUM_COLUMN) {
			if(parent.data.charAt(i)=='0' && count>0) {
				parent.columnNums[start][pos++] = count;
				count = 0;
			}
			else if(parent.data.charAt(i)=='1' && count>=0) {
				count++;
			}
		}
		
		if(count>0)
			parent.columnNums[start][pos++] = count;
		if(pos==0)
			parent.columnNums[start][pos++] = 0;
		return pos;
	}
	
	public void paint (Graphics g) {
		offScr = createImage(20*parent.NUM_COLUMN+1, 121);
		offG = offScr.getGraphics();
		if(parent.mouseX!=-1) {
			offG.setColor(Color.yellow);
			offG.fillRect(20*parent.mouseX, 0, 19, 120);
		}
		
		int CountofColumn[] = new int[parent.NUM_COLUMN];
		int CountofTemp[] = new int[parent.NUM_COLUMN];
		int right[] = new int[parent.NUM_COLUMN];
		
		for(int i=0; i<parent.NUM_COLUMN; i++) {
			for(int j=0; j<parent.NUM_ROW; j++) {
				if(parent.data.charAt(j*parent.NUM_COLUMN+i)=='1')
					CountofColumn[i] += 1;
				if(parent.temp[j*parent.NUM_COLUMN+i]==1)
					CountofTemp[i] += 1;		
			}
			
			if(CountofColumn[i]==CountofTemp[i])
				right[i] = 1;
			else
				right[i] = 0;
		}
		
		for(int i=0; i<parent.NUM_COLUMN; i++) {
			offG.setColor(Color.black);
			offG.drawLine(i*20, 0, i*20, 220);
			for(int j=0; j<parent.numOfColumn[i]; j++) {
				if(right[i] == 1)
					offG.setColor(Color.blue);
				else
					offG.setColor(Color.black);
				
				if(String.valueOf(parent.columnNums[i][j]).length()<2)
					offG.drawString(String.valueOf(parent.columnNums[i][j]), i*20+9, (100-parent.numOfColumn[i]*20+j*20)+39);
				else
					offG.drawString(String.valueOf(parent.columnNums[i][j]), i*20+1, (100-parent.numOfColumn[i]*20+j*20)+39);
			}
		}
		offG.setColor(Color.black);
		
		for(int i=0; i<=20*parent.NUM_COLUMN; i+=20*5) {
			offG.drawLine(i-1, 0, i-1, 120);
			offG.drawLine(i+1, 0, i+1, 120);
		}
		
		offG.drawLine(200, 0, 200, 120);
		offG.drawLine(0, 120, 200, 120);
		
		g.drawImage(offScr, 0, 0, this);
	}
	
	public void update(Graphics g) {
		paint(g);
	}
}
