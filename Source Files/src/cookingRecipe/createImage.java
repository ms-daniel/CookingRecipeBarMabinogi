package cookingRecipe;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

//por hora essa classe  faz apenas imagens com resolução 1920x1080, ainda verei a questão de 1280x720
public class createImage {
	private int  width = 300, heigh = 27;
	private BufferedImage buffer;
	private Graphics g;
	
	//save a imagem com o nome passdo como parametro
	public void saveImage(String name, String resolution){
		try {
			ImageIO.write(buffer, "png", new File("resources\\" + resolution + "\\" + name + ".png") );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//pega o buffer da imagem pra poder mostrar (sem necessidade atualmente)
	public ImageIcon getImage() {
		return new ImageIcon(buffer);
	}
	//metodo que cria as fontes e desenha na iamgem
		public void doFont(String ig1,String ig2,String ig3) {
			ig1 = ig1 + "  | ";
			ig2 = ig2 + "  | ";
			
			int w2 = g.getFontMetrics().stringWidth(ig2),
				w1 = g.getFontMetrics().stringWidth(ig1);
			
			Font font = new Font("Times New Roman", Font.BOLD, 12);
			
			g.setColor( Color.BLUE );
			g.setFont( font );
			
			g.drawString(ig1, 0, 25); 
			
			if(!ig2.equals("0  | "))
				g.drawString(ig2, w1+8, 25); 
			
			if(!ig3.equals("0"))
				g.drawString(ig3, w1+w2+16, 25); 
		}
	
	//o metodo que cria as linhas
	public void doLines(int p1, int p2, int p3) {
		buffer = new BufferedImage( width, heigh, BufferedImage.TYPE_INT_ARGB );
		g = buffer.createGraphics();
		
        g.setColor( Color.BLUE );
        
        // linha inicial e final
        g.drawLine(0, 0, 0, 12);
        g.drawLine(244, 0, 244, 12);
        //
        
        g.setColor( Color.red );
        
        //
        if(p2 != 0) 
        	g.drawLine(p1, 0, p1, 12);

        //caso haja 3 ingredientes
        if(p3 != 0)
        	g.drawLine(p2, 0, p2, 12);
    }
}
