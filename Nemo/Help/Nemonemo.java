import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.time.Duration;

public class Nemonemo extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel contentPane;
	JPanel stopwatch;
	
	//메뉴
	JMenuBar menuBar = new JMenuBar();
	JMenu gameMenu = new JMenu("Game");
	
	Board board;
	Column col;
	Row row;
	
	int NUM_COLUMN = 20;
	int NUM_ROW = 20;
	
	int mouseX = -1;
	int mouseT = -1;
	
	String data = "0001000000011100001101010000101111111110111111111000011111100001111100000100010000010001000011001100";
	int[] temp;
	
	int[][] columnNums;
	int[] numOfColumn;
	int[][] rowNums;
	int[] numOfRow;
	
	boolean endFlag = false;
	public int mouseY;
	
	public static void main(String args[]) {
		Nemonemo nemo = new Nemonemo();
		nemo.setVisible(true);
		nemo.toFront();
	}
	
	public Nemonemo() {
		this.setTitle("LOGIC");
		this.setSize(121+20*(NUM_COLUMN+1), 170+20*(NUM_ROW+1));
		
		temp = new int[NUM_COLUMN * NUM_ROW];
		
		for(int i=0; i<(NUM_COLUMN * NUM_ROW); i++) {
			temp[i] = 0;
		}
		
		columnNums = new int[NUM_COLUMN][NUM_ROW];
		numOfColumn = new int[NUM_COLUMN];
		rowNums = new int[NUM_ROW][NUM_ROW];
		numOfRow = new int[NUM_ROW];
		
		contentPane = (JPanel) getContentPane();
		contentPane.setBackground(Color.white);
		contentPane.setLayout(null);
		
		createMenus();
		
		col = new Column(this);
		contentPane.add(col);
		col.setFont(new Font("SansSerif", Font.BOLD, 14));
		col.setBounds(120, 0, 20*NUM_COLUMN+1, 120);
		col.repaint();
		
		row = new Row(this);
		contentPane.add(row);
		row.setFont(new Font("SansSerif", Font.BOLD, 14));
		row.setBounds(0, 120, 120, 20*NUM_ROW+1);
		
		board = new Board(this);
		contentPane.add(board);
		board.setFont(new Font("SansSerif", Font.BOLD, 14));
		board.setBounds(120, 120, 20*NUM_COLUMN+1, 20*NUM_ROW+1);
		
		stopwatch = new StopWatchPane();
		stopwatch.setBackground(Color.white);
		contentPane.add(stopwatch);
		stopwatch.setBounds(0, 0, 119, 119);
	}
	
	public void createMenus() {
		this.setJMenuBar(menuBar);
		menuBar.add(gameMenu);
		
		JMenuItem newGame = new JMenuItem("New Game!");
		newGame.addActionListener(this);
		newGame.setActionCommand("newGame");
		gameMenu.add(newGame);
		
		JMenuItem answerGame = new JMenuItem("Answer");
		answerGame.addActionListener(this);
		answerGame.setActionCommand("answerGame");
		gameMenu.add(answerGame);

		JMenuItem exitGame = new JMenuItem("Exit");
		exitGame.addActionListener(this);
		exitGame.setActionCommand("exitGame");
		gameMenu.add(exitGame);
	}
	
	public void showLocation(int mouseX, int mouseY) {
		if(mouseX!=this.mouseX) {
			this.mouseX = mouseX;
			col.repaint();
		}
		if(mouseY!=this.mouseY) {
			this.mouseY = mouseY;
			row.repaint();
		}
	}
	
	public void display() {
		boolean endFlag = true;
		for(int j=0; (j<NUM_ROW)&&endFlag; j++)
			for(int i=0; (i<NUM_COLUMN)&&endFlag; i++)
			{
				if((data.charAt(j*NUM_COLUMN+i)=='1')&&(temp[j*NUM_COLUMN+i]!=1)) {
					endFlag=false;
				}
				else if((data.charAt(j*NUM_COLUMN+i)!='1')&&(temp[j*NUM_COLUMN+i]==1)) {
					endFlag=false;
				}
					
			}
		if(endFlag) {
			this.endFlag = endFlag;
			board.repaint();
			
			StopWatchPane.timer.stop();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		if(cmd.equals("newGame")) {
			showOpenDialog();
		}
		
		else if(cmd.equals("answerGame")) {
			this.endFlag = true;
			board.repaint();
			StopWatchPane.timer.stop();
		}
		
		else if(cmd.equals("exitGame")) {
			this.dispose();
		}
	}
	
	public void showOpenDialog() {
		FileDialog fd = new FileDialog(this, "Open a File");
		
		fd.setFile("*.txt");
		fd.setVisible(true);
		
		if(fd.getFile()!=null) {
			String filename = fd.getFile();
			String logicDir = fd.getDirectory();
			if(filename.indexOf('.')!=-1) {
				filename = (filename.substring(0, filename.indexOf('.'))).toLowerCase();
			}
			else {
				filename = filename.toLowerCase();
			}
			String logicName = filename;
			
			File f;
			FileInputStream from = null;
			BufferedReader d = null;
			
			try {
				f = new File(logicDir + logicName + ".txt");
				from = new FileInputStream(f);
				d = new BufferedReader(new InputStreamReader(from));
				
				int count_col = 0;
				int count_row = 0;
				
				data = d.readLine();
				for(int i=0; i<data.length(); i++) {
					char tmpchar = data.charAt(i);
					if(count_col ==0) 
						count_row ++;
					if(tmpchar == ' ')
						count_col ++;
					
				}
				NUM_COLUMN = count_col + 1;
				NUM_ROW = count_row - 1;
				
				data = data.replace(" ", "");
				Column col;
				Row row;
				
				this.setTitle("LOGIC");
				
				this.setSize(121+20*(NUM_COLUMN+1), 170+20*(NUM_ROW+1));
				
				temp = new int[NUM_COLUMN * NUM_ROW];
				
				for(int i=0; i<(NUM_COLUMN * NUM_ROW); i++) {
					temp[i] = 0;
				}
				columnNums = new int[NUM_COLUMN][NUM_ROW];
				numOfColumn = new int[NUM_COLUMN];
				rowNums = new int[NUM_ROW][NUM_ROW];
				numOfRow = new int[NUM_ROW];
				
				contentPane = (JPanel) getContentPane();
				contentPane.setBackground(Color.white);
				contentPane.setLayout(null);
				
				col = new Column(this);
				contentPane.add(col);
				col.setFont(new Font("SansSerif", Font.BOLD, 14));
				col.setBounds(120, 0, 20*NUM_COLUMN+1, 120);
				col.repaint();
				
				row = new Row(this);
				contentPane.add(row);
				row.setFont(new Font("SansSerif", Font.BOLD, 14));
				row.setBounds(0, 120, 120, 20*NUM_ROW+1);
				
				board = new Board(this);
				contentPane.add(board);
				board.setFont(new Font("SansSerif", Font.BOLD, 14));
				board.setBounds(120, 120, 20*NUM_COLUMN+1, 20*NUM_ROW+1);
				
				StopWatchPane.timer.stop();
				stopwatch = new StopWatchPane();
				stopwatch.setBackground(Color.white);
				contentPane.add(stopwatch);
				stopwatch.setBounds(0, 0, 119, 119);
				
				d.close();
			}
			catch(IOException e) {
				System.out.print("I/O ERROR: "+e);
			}
			
			for(int i=0; i<(NUM_COLUMN * NUM_ROW); i++) {
				temp[i] = 0;
			}
			this.endFlag = false;
			
			col.getColumn();
			row.getRow();
			board.repaint();
		}
	}
	
	
	public static class StopWatchPane extends JPanel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private JLabel label;
		private long lastTickTime;
		private static Timer timer;
		
		public StopWatchPane() {
			setLayout(new GridBagLayout());
			label = new JLabel(String.format("%04d:%02d:%02d.%03d", 0, 0, 0, 0));
			
			timer = new Timer(100, new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					long runningTime = System.currentTimeMillis() - lastTickTime;
					Duration duration = Duration.ofMillis(runningTime);
					long hours = duration.toHours();
					duration = duration.minusHours(hours);
					long minutes = duration.toMinutes();
					duration = duration.minusMinutes(minutes);
					long millis = duration.toMillis();
					long seconds = millis / 1000;
					millis -= (seconds * 1000);
					// TODO Auto-generated method stub
					label.setText(String.format("%04d:%02d:%02d.%03d", hours, minutes, seconds, millis));
				}
			});
			
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.weightx = 1;
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			gbc.insets = new Insets(4, 4, 4, 4);
			add(label, gbc);
			
			lastTickTime = System.currentTimeMillis();
			timer.start();
		}

	}

}
