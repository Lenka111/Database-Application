
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
// Programmer: Elena Voinu

public class DataApplication
{
    public static void main(String[] args) {
        try
        {
            File fin = new File("data.txt");
            Scanner scan = new Scanner(fin);
            ArrayList<String> theData = new ArrayList<String>();

            // read the column headings from the flat text file
            String line = scan.nextLine();

            while(scan.hasNextLine())
            {
                line = scan.nextLine();
                String[] list = line.split(",");
                int key = Integer.parseInt(list[0]);
                String name = list[1];
                int fee = Integer.parseInt(list[2]);
                String specialty = list[3];

                theData.add(String.valueOf(key));
                theData.add(name);
                theData.add(String.valueOf(fee));
                theData.add(specialty);
            }
            // added the header
            System.out.println("ID\t\t Name\t\tFee\t\t\tSpecialty");
            int count = 1;
            for (int i = 0; i < theData.size(); i++)
            {
                System.out.print(theData.get(i) + "\t\t");
                if(count % 4 == 0 )
                    System.out.println(" ");
                count++;
            }
            System.out.println("__________________________________________________________________");
            searchData(theData);
            System.out.println("__________________________________________________________________");
            searchSpecialty(theData);
            System.out.println("__________________________________________________________________");
            chargesFee(theData);

            scan.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }// end main

    public static void searchData(ArrayList<String> vals)
    {
        System.out.print("Enter a name: ");
        Scanner sc = new Scanner(System.in);
        String strName = sc.nextLine().trim();
        boolean found = false;

        for (int i = 0; i < vals.size(); i++)
        {
            //changed to ignore case
            if(vals.get(i).equalsIgnoreCase(strName.trim()))
            {
                found = true;
                break;
            }
        }
        if(found)
            System.out.println("Data found ");
        else
            System.out.println("Data not found ");

        sc.close();
    } //end searchData method



    public static void chargesFee(ArrayList<String> vals) {
        System.out.println();
        System.out.println("Checking if any consultants charge more than $2,000...");
        System.out.println("Consultants who charge fees over $2,000:");

        for (int i = 0; i < vals.size(); i++) {
            // if string matches numbers
            if (vals.get(i).matches("[0-9]+"))
            {
                // convert string to integer and check if it's greater than 2k
                if (Integer.parseInt(vals.get(i)) > 2000)
                {
                    //return the result
                    System.out.print(" >");
                    System.out.println(vals.get(i - 1));

                }
            }
        }


    }// end method

    public static void searchSpecialty(ArrayList<String> vals){
        System.out.println();
        System.out.println("Consultants that specialize in providing media services: ");
        int count = 0;

        for(int i = 0; i < vals.size(); i ++)
        {
           // looking for media specialty
            if (vals.get(i).matches("Media") )
            {
                System.out.print(" >");
                //print if found
                System.out.println(vals.get(i - 2));
                count++;
            }
        }
        System.out.println();
        System.out.println("Total consultants that specialize in providing media services: " + count);



    } //end searchSpecialty method

}// end class

