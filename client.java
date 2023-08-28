import javax.swing.*;
import java.net.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
public class client
{
public static void main(String args[])throws Exception
{
Socket soc;
BufferedImage img=null;
soc=new Socket("localhost",4000);
System.out.println("client is running");
try
{
System.out.println("Reading image from disdk");
img=ImageIO.read(new File("360_F_122729880_a4rHgPGiwVktwwsovKfL2iqrd2vM042R"));
ByteArrayOutputStream baos=new ByteArrayOutputStream();
ImageIO.write(img,"jpg",baos);
baos.flush();

byte[]bytes=baos.toByteArray();
baos.close();
System.out.println("Sending image to server");

OutputStream out=soc.getOutputStream();
DataOutputStream dos=new DataOutputStream(out);
dos.writeInt(bytes.length);
dos.write(bytes,0,bytes.length);
System.out.println("Image sent to server.");
dos.close();
out.close();
}
catch(Exception e)
{
System.out.println("exception: "+e.getMessage());
soc.close();
}
soc.close();
}
}
