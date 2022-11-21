import java.awt.*;
import java.awt.event.*;

public class Board extends Canvas implements MouseListener, MouseMotionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Nemonemo parent;
	boolean drag = false;
	int startX, startY;
	int endX, endY;
	
	Image offScr;
	Graphics offG;
	
	public Board(Nemonemo parent) {
		this.parent = parent;
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}
	
	public void paint(Graphics g) {
		offScr = createImage(20*parent.NUM_COLUMN+1, 20*parent.NUM_ROW+1);
		offG = offScr.getGraphics();
		
		if(parent.mouseX!=-1 && parent.mouseY!= -1 && parent.endFlag == false) {
			offG.setColor(Color.yellow);
			for(int i=0; i<parent.mouseX; i++)
				offG.fillRect(20*i, 20*parent.mouseY, 20, 20);
			for(int i=0; i<parent.mouseY; i++)
				offG.fillRect(20*parent.mouseX, 20*i, 20, 20);
			
			offG.setColor(Color.orange);
			offG.fillRect(20*parent.mouseX, 20*parent.mouseY, 20, 20);
			
		}
		
		for(int j=0; j<parent.NUM_ROW; j++)
			for(int i=0; i<parent.NUM_COLUMN; i++) {
				if(parent.endFlag) {
					if(parent.data.charAt(j*parent.NUM_COLUMN+i)=='1') {
						offG.fillRect(i*20, j*20, 20, 20);
					}
				}
				else {
					if(parent.temp[j*parent.NUM_COLUMN+i]==1) {
						offG.setColor(Color.blue);
						offG.fillRect(i*20, j*20, 20, 20);
					}
					else if(parent.temp[j*parent.NUM_COLUMN+i]==2) {
						offG.setColor(Color.red);
						offG.drawLine(i*20, j*20, i*20+20, j*20+20);
						offG.drawLine(i*20, j*20+20, i*20+20, j*20);
					}
				}
			}
		if(drag) {
			offG.setColor(Color.orange);
			
			if(startX==endX) {
				if(startY<endY) {
					offG.fillRect(20*startX,  20*startY, 20, 20*(endY-startY+1));
					offG.setColor(Color.red);
					offG.drawString(String.valueOf(endY-startY+1), endX*20+2, (endY+1)*20-2);
				}
				else {
					offG.fillRect(20*endX,  20*endY, 20, 20*(startY-endY+1));
					offG.setColor(Color.red);
					offG.drawString(String.valueOf(startY-endY+1), endX*20+2, (endY+1)*20-2);
				}
			}
			else if(startY==endY) {
				if(startX<endX) {
					offG.fillRect(20*startX,  20*startY, 20, 20*(endX-startX+1));
					offG.setColor(Color.red);
					offG.drawString(String.valueOf(endX-startX+1), endX*20+2, (endY+1)*20-2);
				}
				else {
					offG.fillRect(20*endX,  20*endY, 20, 20*(startX-endX+1));
					offG.setColor(Color.red);
					offG.drawString(String.valueOf(startX-endX+1), endX*20+2, (endY+1)*20-2);
				}
			}
		}
		
		for(int j=0; j<parent.NUM_ROW; j++)
			for(int i=0; i<parent.NUM_COLUMN; i++) {
				offG.setColor(Color.black);
				offG.drawRect(i*20,  j*20, 20, 20);
			}
		offG.setColor(Color.black);
		
		for(int i=0; i<=20*parent.NUM_COLUMN; i+=20*5) {
			offG.drawLine(i-1, 0, i-1, 20*parent.NUM_ROW);
			offG.drawLine(i-1, 0, i+1, 20*parent.NUM_ROW);
		}
		
		for(int i=0; i<=20*parent.NUM_ROW; i+=20*5) {
			offG.drawLine(0, i-1, 20*parent.NUM_COLUMN, i-1);
			offG.drawLine(0, i+1, 20*parent.NUM_COLUMN, i+1);
		}
		
		g.drawImage(offScr, 0, 0, this);
	}
	
	public void update(Graphics g) {
		paint(g);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		int x = e.getX();
		int y = e.getY();
		
		if((x/20)>=parent.NUM_COLUMN) return;
		if((y/20)>=parent.NUM_ROW) return;
		if(parent.endFlag) return;
		
		startX = x/20;
		startY = y/20;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		int x = e.getX();
		int y = e.getY();
		
		if((x/20)>=parent.NUM_COLUMN) return;
		if((y/20)>=parent.NUM_ROW) return;
		if(parent.endFlag) return;
		
		if((e.getModifiers() & InputEvent.BUTTON3_MASK)!=0) {
			setTemp(x,y,2);
		}
		else {
			setTemp(x,y,1);
		}
		
		parent.display();
		this.drag = false;
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		int x = e.getX();
		int y = e.getY();
		
		if((x/20)>=parent.NUM_COLUMN) return;
		if((y/20)>=parent.NUM_ROW) return;

		parent.showLocation(x/20, y/20);
		repaint();
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		int x = e.getX();
		int y = e.getY();
		
		if((x/20)>=parent.NUM_COLUMN) return;
		if((y/20)>=parent.NUM_ROW) return;

		parent.showLocation(x/20, y/20);
		
		this.drag = true;
		endX = x/20;
		endY = y/20;
		repaint();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		parent.showLocation(-1, -1);
		this.drag = false;
		repaint();
	}
	
	private void setTemp(int x, int y, int value) {
		// TODO Auto-generated method stub
		int i;
		
		if(drag) {
			if(startX==endX) {
				if(startY<endY) {
					if(parent.temp[startX+startY*parent.NUM_COLUMN] !=0) {
						for(i=startY; i<=endY; i++)
							parent.temp[startX+i*parent.NUM_COLUMN] = 0;
					}
					else {
						for(i=startY; i<=endY; i++)
							parent.temp[startX+i*parent.NUM_COLUMN] = value;
					}
				}
				else if(startY>endY) {
					if(parent.temp[startX+startY*parent.NUM_COLUMN] !=0) {
						for(i=endY; i<=startY; i++)
							parent.temp[startX+i*parent.NUM_COLUMN] = 0;
					}
					else {
						for(i=endY; i<=startY; i++)
							parent.temp[startX+i*parent.NUM_COLUMN] = value;
					}
				}
				else { //startY==endY
					if(parent.temp[startX+startY*parent.NUM_COLUMN] !=0)
						parent.temp[startX+startY*parent.NUM_COLUMN] = 0;
					else
						parent.temp[startX+startY*parent.NUM_COLUMN] = value;
				}
			}
			else if(startY==endY) {
				if(startX<endX) {
					if(parent.temp[startX+startY*parent.NUM_COLUMN] !=0) {
						for(i=startX; i<=endX; i++)
							parent.temp[i+startY*parent.NUM_COLUMN] = 0;
					}
					else {
						for(i=startX; i<=endX; i++)
							parent.temp[i+startY*parent.NUM_COLUMN] = value;
					}
				}
				else if(startX>endX) {
					if(parent.temp[startX+startY*parent.NUM_COLUMN] !=0) {
						for(i=endX; i<=startX; i++)
							parent.temp[i+startY*parent.NUM_COLUMN] = 0;
					}
					else {
						for(i=endX; i<=startX; i++)
							parent.temp[i+startY*parent.NUM_COLUMN] = value;
					}
				}
				else { //startX==endX
					if(parent.temp[startX+startY*parent.NUM_COLUMN] !=0)
						parent.temp[startX+startY*parent.NUM_COLUMN] = 0;
					else
						parent.temp[startX+startY*parent.NUM_COLUMN] = value;
				}
			}
		}
		else {
			if(parent.temp[x/20+y/20*parent.NUM_COLUMN] !=0)
				parent.temp[x/20+y/20*parent.NUM_COLUMN] = 0;
			else
				parent.temp[x/20+y/20*parent.NUM_COLUMN] = value;
		}
	}

}
