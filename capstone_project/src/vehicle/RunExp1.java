package vehicle;

public class RunExp1 implements Runnable  
{    
    public void run()  
    {    
        System.out.println("Thread is running...");    
    }    
    public static void main(String args[])  
    {    
        RunExp1 r1=new RunExp1();    
        Thread t1 =new Thread(r1);       
        RunExp1 r2=new RunExp1();    
        Thread t2 =new Thread(r2);  
        // this will call run() method   
        t1.start();    
        t2.start();
    }    
} 