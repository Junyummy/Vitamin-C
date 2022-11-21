import java.awt.*;

public class Row extends Canvas{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Nemonemo parent;
	Image offScr;
	Graphics offG;
	
	public Row(Nemonemo parent) {
		this.parent = parent;
		getRow();
	}
	
	public void getRow() {
		for(int i=0; i<parent.NUM_ROW; i++) {
			parent.numOfRow[i] = getNumber(i);
		}
	}
	
	int getNumber(int start) {
		int count = 0;
		int pos = 0;
		
		for(int i=start; i<parent.NUM_COLUMN * parent.NUM_ROW; i+=parent.NUM_COLUMN) {
			if(parent.data.charAt(i)=='0' && count>0) {
				parent.rowNums[start][pos++] = count;
				count = 0;
			}
			else if(parent.data.charAt(i)=='1' && count>=0) {
				count++;
			}
		}
		
		if(count>0)
			parent.rowNums[start][pos++] = count;
		if(pos==0)
			parent.rowNums[start][pos++] = 0;
		return pos;
	}
	
	public void paint (Graphics g) {
		offScr = createImage(121, 20*parent.NUM_ROW+1);
		offG = offScr.getGraphics();
		if(parent.mouseY!=-1) {
			offG.setColor(Color.yellow);
			offG.fillRect(20*parent.mouseY, 0, 120, 19);
		}
		
		int CountofRow[] = new int[parent.NUM_ROW];
		int CountofTemp[] = new int[parent.NUM_ROW];
		int right[] = new int[parent.NUM_ROW];
		
		for(int i=0; i<parent.NUM_ROW; i++) {
			for(int j=0; j<parent.NUM_COLUMN; j++) {
				if(parent.data.charAt(j*parent.NUM_COLUMN+i)=='1')
					CountofRow[i] += 1;
				if(parent.temp[j*parent.NUM_COLUMN+i]==1)
					CountofTemp[i] += 1;		
			}
			
			if(CountofRow[i]==CountofTemp[i])
				right[i] = 1;
			else
				right[i] = 0;
		}
		
		for(int i=0; i<parent.NUM_ROW; i++) {
			offG.setColor(Color.black);
			offG.drawLine(0, i*20, 12, i*20);
			for(int j=0; j<parent.numOfRow[i]; j++) {
				if(right[i] == 1)
					offG.setColor(Color.blue);
				else
					offG.setColor(Color.black);
				
				if(String.valueOf(parent.rowNums[i][j]).length()<2) {
					offG.drawString(String.valueOf(parent.rowNums[i][j]), (100-parent.numOfRow[i]*20)+j*20+27, i*20+18);
					offG.setColor(Color.black);
				}
				else {
					offG.drawString(String.valueOf(parent.rowNums[i][j]), (100-parent.numOfRow[i]*20)+j*20+21, i*20+18);
					offG.setColor(Color.black);
				}
			}
		}
		offG.setColor(Color.black);
		
		for(int i=0; i<=20*parent.NUM_COLUMN; i+=20*5) {
			offG.drawLine(0, i-1, 120, i-1);
			offG.drawLine(0, i+1, 120, i+1);
		}
		
		offG.drawLine(0, 200, 120, 200);
		offG.drawLine(120, 0, 120, 200);
		
		g.drawImage(offScr, 0, 0, this);
	}
	
	public void update(Graphics g) {
		paint(g);
	}
}
