import java.util.Scanner;

public class BankersAlgo{
    public static void main(String args[]){
        int n,m,available[],max[][],allocation[][],need[][],work[];
        boolean finish[];
        Scanner reader = new Scanner(System.in);
        
        System.out.print("Enter number of processes: ");
        n = reader.nextInt();
        System.out.print("Enter number of resources: ");
        m = reader.nextInt();
        
        available = new int[m];
        max = new int[n][m];
        allocation = new int[n][m];
        need = new int[n][m];
        finish = new boolean[n];
        work = new int[m];
        
        System.out.println("Enter the available resources: ");
        for(int i=0;i<m;i++){
            available[i] = reader.nextInt();
            work[i] = available[i];
        }
        
        System.out.println("Enter the Max matrix: ");
        acceptInput(max,n,m);
        
        System.out.println("Enter the allocation matrix: ");
        acceptInput(allocation,n,m);
        
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                need[i][j] = max[i][j] - allocation[i][j];
            }
        }
        
        for(int i=0;i<n;i++){
            finish[i] = false;
        }
        
        int safeseq[] = new int[n];
        int count = 0;
        
        while(count < n){
            boolean flag = false;
            
            for(int i=0;i<n;i++){
    
                int j;
                if(finish[i] == false){
                    for(j=0;j<m;j++){
                        if(need[i][j]>work[j]){
                            break;
                        }
                    }
                    if(j == m){
                        safeseq[count++] = i;
                        finish[i] = true;
                        flag = true;
                        
                        for(j=0;j<m;j++){
                            work[j] = work[j] + allocation[i][j];
                        }
                    }       
                }            
            }
            if(flag == false){
                break;
            }
        
        }
        if(count < n){
            System.out.println("System is unsafe");
        }
        
        else{
            System.out.println("Safe sequence is: ");
            for(int i=0;i<n;i++){
                System.out.print("P"+safeseq[i]+"\t");
            }
        }
    }
    
    public static void acceptInput(int matrix[][], int rows, int cols){
        Scanner reader = new Scanner(System.in);
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                int a = reader.nextInt();
                matrix[i][j] = a;
            }
        
        }
    
    }
}
