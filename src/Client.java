import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Random;
public class Client {
    
    public static void main(String[] args) throws IOException {
        Socket sk = new Socket("localhost",6667);
        BufferedReader br = new BufferedReader(new InputStreamReader(sk.getInputStream()));
        PrintStream sout = new PrintStream(sk.getOutputStream());
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while(true)
        {
               Client cl = new Client();
               s = cl.getRandomText();
               System.out.println("Client : "+s);
               
               sout.println(s);
               if(s.equalsIgnoreCase("BYE"))
               {
                   System.err.println("Connection Closed by client");
                   break;
               }
               s = br.readLine();
               System.err.println("Server : "+s+"\n");
        }
        sk.close();
        br.close();
        sout.close();
        stdin.close();
        
    }
    
    public String getRandomText() throws FileNotFoundException, IOException
    {
        String str_Line="";
        BufferedReader breee = new BufferedReader(new FileReader("D://Inputfile.txt"));
        ArrayList file_data = new ArrayList();
        while(str_Line!=null)
        {
            if(str_Line==null)
            {
                break;
            }
            str_Line = breee.readLine();
            file_data.add(str_Line);
        }
        int arr_size = file_data.size();
        int randomNumber = this.generateRandom(0, arr_size-1);
        return (String) file_data.get(randomNumber);
    }
    public int generateRandom(int low, int high)
    {
    Random r = new Random();
    return(r.nextInt(high-low) + low);
    }
}
