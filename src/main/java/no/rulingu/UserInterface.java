package no.rulingu;

import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

public class UserInterface
{
    //public teamlist; //WIP
    private String[] tasks = {
            "1 - Task 1",
            "2 - Task 2",
            "3 - Task 3",
            "4 - Task 4",
            "5 - Authorize",
    };


    public UserInterface() {

    }

    public void bootMenu() {
        boolean quit = false;
        while(!quit){
            try{
                int menuSelection = this.showMenu();
                switch(menuSelection){
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        POST auth = new POST("datakomm.work", 80);
                        auth.sendauhorizationinformation();
                        break;
                    case 6:
                        System.out.println("YEET");
                        quit = true;
                    default:
                }
            }
            catch(InputMismatchException ime)
            {
                System.out.println("\n ERROR: number out of range! ");
            }
        }
    }

    public int showMenu()
    {
        int maxMenuItemNumber = tasks.length + 1;
        System.out.println("Select task (1 - " + maxMenuItemNumber + "): ");
        for (String tasks : tasks)
        {
            System.out.println(tasks);
        }

        System.out.println(maxMenuItemNumber + " - Exit\n");

        Scanner reader = new Scanner(System.in);
        int selection = reader.nextInt();
        if ((selection < 1) || (selection > maxMenuItemNumber)){
            throw new InputMismatchException();
        }
        return selection;
    }
}