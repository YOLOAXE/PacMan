import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Stream;

public class CarteCreator 
{
	public static void main(String[] args) 
	{
		int hauteur = 0;
		int largeur = 0;
		File file;
		InputStream inputStream = null;
		if(args.length == 0)
		{
			System.out.println("file arg");
			return;
		}
		try 
		{
			file = new File(classLoader.getResource(args[0]).getFile());
			inputStream = new FileInputStream(file);
		}     
		finally 
		{
			if (inputStream != null) 
			{
				try 
				{
					inputStream.close();
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
		}
	}
}