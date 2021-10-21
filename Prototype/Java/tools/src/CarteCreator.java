import java.io.*;
import java.nio.file.FileStore;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.*;

public class CarteCreator {
	public static void main(String[] args) 
	{
		int hauteur = 0;
		int largeur = 0;
		int carteId [][] = new int[100][100];	
		File file;
		File outfile;
		OutputStream outputStream = null;
		InputStream inputStream = null;
		BufferedReader reader = null;

		if(args.length < 2)
		{
			System.out.println("Usage : java CarteCreator <in.txt> <out.carte>");
			return;
		}

		try 
		{
			file = new File(args[0]);
			inputStream = new FileInputStream(file);
			reader = new BufferedReader(new InputStreamReader(inputStream));
			try 
			{
				String line = reader.readLine();
				int count = 0;
				hauteur = Integer.parseInt(line);
				line = reader.readLine();
				largeur = Integer.parseInt(line);
				carteId = new int[hauteur][largeur];
            	while(line != null && count < hauteur)
				{
                	line = reader.readLine();
					String[] result = line.split(",",largeur);
					for(int i = 0; i < result.length;i++)
					{
						carteId[count][i] = Integer.parseInt(result[i]);
					}
					count++;
            	}
			}
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}     
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
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
		try 
		{
			outfile = new File(args[1]);
			outputStream = new FileOutputStream(outfile);
			outputStream.write(hauteur);
			outputStream.write(largeur);
			for(int i = 0; i < hauteur;i++)
			{
				for(int j = 0; j < largeur;j++)
				{
					outputStream.write(carteId[i][j]);
				}
			}
		} 
		catch (IOException ex) 
		{
            ex.printStackTrace();
        }
		if (outputStream != null) 
		{
			try 
			{
				outputStream.close();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		/*System.out.println("Re print from memory");
		for(int i = 0; i < hauteur;i++)
		{
			for(int j = 0; j < largeur;j++)
			{
				System.out.print(carteId[i][j]+",");
			}
			System.out.println(" ");
		}*/
	}
}