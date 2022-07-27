import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Insets;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Operations {
	private String recipe;
	private float size;
	
	private Point point = new Point();
	
	private boolean mouseEstado = false;
	
	private JButton close;
	
	private int n1, n2, n3;
	private int qt;
	
	private ImageIcon img;
	
	private JLabel l1, l2, l3, bar_img;
	
	private Insets margem = new Insets(0,0,0,0);
	
	public Operations(String recipe, float size) {
		this.recipe = recipe;
		this.size = size;
		
		CountQt();
		Bar();
		
	}
	
	private void Bar() {
		JFrame jan = new JFrame();
		jan.setUndecorated(true);
		jan.setBounds(351, 690, 360, 27);
		jan.setBackground(new Color(1.0f,1.0f,1.0f,0.1f));
		jan.setVisible(true);
		jan.setLayout(null);
		jan.setAlwaysOnTop(true);
		
		try {
			img = new ImageIcon(getClass().getClassLoader().getResource(recipe + " " + size +".png"));
		}catch(NullPointerException e) {
			JOptionPane.showMessageDialog(jan, "This recipe doesn't exist yet.");
			jan.setVisible(false);
		}
		
		close = new JButton("X");
		close.setBounds(300, 1, 15, 15);
		close.setMargin(margem);
		close.setCursor(new Cursor(Cursor.HAND_CURSOR));
		close.setFont(new Font("Arial", Font.BOLD, 13));
		close.setBackground(Color.red);
		close.setForeground(Color.white);
		close.setFocusable(false);
		close.setEnabled(true);
		
		bar_img = new JLabel();
		bar_img.setIcon(img);
		bar_img.setBounds(0, -9, 300, 40);
		
		jan.getContentPane().add(bar_img);
		jan.getContentPane().add(close);
		
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jan.setVisible(false);
			}
		});
		
		jan.addMouseListener(new MouseAdapter() {
		      public void mousePressed(MouseEvent e) {
		        point.x = e.getX();
		        point.y = e.getY();
		      }
		    });
		 jan.addMouseMotionListener(new MouseMotionAdapter() {
		      public void mouseDragged(MouseEvent e) {
		        Point p = jan.getLocation();
		        jan.setLocation(p.x + e.getX() - point.x, p.y + e.getY() - point.y);
		      }
		    });
		
	}
	
	private void CountQt() {
		if(recipe.equals("Steamed Potato")) {
			qt = 3;
		}
		else
			qt = 3;
	}
	
	private void Change() {
		JFrame jan = new JFrame();
		if(!mouseEstado)
			jan.setUndecorated(false);
		else
			jan.setUndecorated(true);
		
		jan.setBounds(50, 900, 340, 50);
		//jan.setBackground(new Color(1.0f,1.0f,1.0f,0.1f));
		jan.setVisible(true);
		jan.setLayout(null);
	}
	
}
