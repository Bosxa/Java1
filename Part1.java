import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class Part1 {


    // Total of 7 arrays have been used to make this code functional.
    private static String[] queueOne = new String[]{"O","O"};
    private static String[] queueTwo = new String[]{"O","O","O"};
    private static String[] queueThree = new String[]{"O","O","O","O","O"};
    public static ArrayList<FoodQueue> queueInformation = new ArrayList<>();
    public static ArrayList<FoodQueue> waitingList = new ArrayList<>();

    private static Scanner input = new Scanner(System.in);

    // Three global variables used in the code.
    private static int burgerStock =50;
    private static int customerNumb =1;
    public static void main(String[] args) {
        System.out.println("\n             ----------- Foodies Fave Queue Management System -----------");
        mainMenu();


    }


    /* The method mainMenu is the method which handles, all the functions. This Function calls the other functions according to the
     * options user selects. Nested If conditions are used */
    private static void mainMenu(){

        System.out.println();
        while (true) {
            try {
                System.out.println("\n            ------Option Menu------            ");
                System.out.println("\n 100 or VFQ: View all Queues");
                System.out.println(" 101 or VEQ: View all Empty Queues.");
                System.out.println(" 102 or ACQ: Add customer to a Queue.");
                System.out.println(" 103 or RCQ: Remove a customer from a Queue.");
                System.out.println(" 104 or PCQ: Remove a served customer");
                System.out.println(" 105 or VCS: View Customers Sorted in alphabetical order");
                System.out.println(" 106 or SPD: Store Program Data into file.");
                System.out.println(" 107 or LPD: Load Program Data from file.");
                System.out.println(" 108 or STK: View Remaining burgers Stock.");
                System.out.println(" 109 or AFS: Add burgers to Stock.");
                System.out.println(" 999 or EXT: Exit the Program.");

                System.out.print("\nEnter Option Number : ");
                String OptionNo = input.nextLine();

                if (Objects.equals(OptionNo, "100") ||Objects.equals(OptionNo, "VFQ")||Objects.equals(OptionNo, "vfq") ){
                    System.out.println("\nOption Number 100 Selected  : View all Queues \n");
                    viewAllQue();

                } else if (Objects.equals(OptionNo, "101") ||Objects.equals(OptionNo, "VEQ")||Objects.equals(OptionNo, "veq")) {
                    System.out.println("\nOption Number 101 Selected  : View All Empty Queues \n");
                    viewAllEmpty();

                }else if (Objects.equals(OptionNo, "102") ||Objects.equals(OptionNo, "ACQ")||Objects.equals(OptionNo, "acq")) {
                    addCustomer(false);


                }else if (Objects.equals(OptionNo, "103") ||Objects.equals(OptionNo, "RCQ")||Objects.equals(OptionNo, "rcq")) {
                    removeCustomer();

                }else if (Objects.equals(OptionNo, "104") ||Objects.equals(OptionNo, "PCQ")||Objects.equals(OptionNo, "pcq")) {
                    removeServed();

                }else if (Objects.equals(OptionNo, "105") ||Objects.equals(OptionNo, "VCS")||Objects.equals(OptionNo, "vcs")) {
                    sortCustomers();

                }else if (Objects.equals(OptionNo, "106") ||Objects.equals(OptionNo, "SPD")||Objects.equals(OptionNo, "spd")) {
                    savaToFile();

                }else if (Objects.equals(OptionNo, "107") ||Objects.equals(OptionNo, "LPD")||Objects.equals(OptionNo, "lpd")) {
                    loadFromFile();

                }else if (Objects.equals(OptionNo, "108") ||Objects.equals(OptionNo, "STK")||Objects.equals(OptionNo, "stk")) {
                    viewBurgerStock();

                }else if (Objects.equals(OptionNo, "109") ||Objects.equals(OptionNo, "AFS")||Objects.equals(OptionNo, "afs")) {
                    addBurgers();

                }else if (Objects.equals(OptionNo, "999") ||Objects.equals(OptionNo, "EXT")||Objects.equals(OptionNo, "ext")) {
                    System.out.println("\nThank You For Using Our Program!");
                    System.out.println("Have a Great Day!");
                    break;

                }else {
                    System.out.println("Invalid Option Selected!");
                }


            } catch (InputMismatchException e) {
                input.nextLine();
                System.out.println("Invalid Option Number, Please Try Again!");
            }
        }


    }



    /* The Method viewAllQue prints all the ques to the console.*/
    private static void viewAllQue(){

        System.out.println("\n\n                         **********************************");
        System.out.println("                         *             Cashiers           *");
        System.out.println("                         **********************************");
        System.out.println();

        for(int i=0; i<5;i++){
            if(i<2){
                System.out.print("                              " + queueOne[i]);
                System.out.print("           " + queueTwo[i]);
                System.out.print("           " + queueThree[i]);
                System.out.println("\n");
            } else if (i>1 && i<3) {
                System.out.print("                                ");
                System.out.print("          " + queueTwo[i]);
                System.out.print("           " + queueThree[i]);
                System.out.println("\n");

            }else {
                System.out.print("                                ");
                System.out.print("           ");
                System.out.print("           " + queueThree[i]);
                System.out.println("\n");
            }
        }

        System.out.println("                         X – Occupied   O – Not Occupied");



    }




    /* The Method viewAllEmpty shows all the empty queues.*/
    private static void viewAllEmpty(){
        boolean q1Empty = true;
        boolean q2Empty = true;
        boolean q3Empty = true;
        for (String i : queueOne){
            if (i=="X"){
                q1Empty= false;
                break;
            }
        }
        for (String i : queueTwo){
            if (i=="X"){
                q2Empty= false;
                break;
            }
        }
        for (String i : queueThree){
            if (i=="X"){
                q3Empty= false;
                break;
            }
        }

        System.out.println("\n-----All Empty Queues-----\n");
        if (q1Empty){
            System.out.println("->Queue Number 1 is Empty");
        }
        if (q2Empty){
            System.out.println("->Queue Number 2 is Empty");
        }
        if (q3Empty){
            System.out.println("->Queue Number 3 is Empty");
        }else {
            System.out.println("->No Empty Queues");
        }


    }



    /* The Method addCustomer is a special method. This method add users to the queues. The Speciality
     * in this method is that, there is an added extra functionality. You can specify a queue number and add the user there. But Additionally
     * There is an option to let the program decide the best queue and the position foe the customer. The Program always make sure that, the user
     * get added to the fastest queue position available at that specific moment. This Functionality is the main speciality in this code.
     * And when removing a customer, this will update the burger stock as well.*/
    private static void addCustomer(boolean waitingListTrue){

        String customerFirstName ="";
        String customerLastName ="";
        int burgerNumber=0;
        int customerAddedIndex=-1;


        int q1Index=-1 ;
        int q2Index =-1;
        int q3Index =-1 ;
        int highestIndex = -1;
        int queueNum;
        boolean q1True = false;
        boolean q2True = false;
        boolean q3True = false;

        for (int i =0; i<2; i++){
            if (Objects.equals(queueOne[i], "O")){
                q1Index=i;
                q1True=true;
                break;
            }
        }
        for (int i =0; i<3; i++){
            if (Objects.equals(queueTwo[i], "O")){
                q2Index=i;
                q2True=true;
                break;
            }
        }
        for (int i =0; i<5; i++){
            if (Objects.equals(queueThree[i], "O")){
                q3Index=i;
                q3True=true;
                break;
            }
        }

        if (!waitingListTrue){
            if (burgerStock<=10){
                System.out.println("The Remaining Burger Stock Is : " + burgerStock);
            }
        }



        if (waitingListTrue){
            if (waitingList.isEmpty()){
                return;
            }else {
                customerFirstName= waitingList.get(0).getCustomer().getFirstName();
                customerLastName = waitingList.get(0).getCustomer().getLastName();
                burgerNumber = waitingList.get(0).getCustomer().getBurgerNumber();
                waitingList.remove(0);
            }


        }else {
            System.out.println("\nCustomer Number : " + customerNumb);
            System.out.print("\nPlease enter customer first name : ");
            customerFirstName = input.nextLine();
            System.out.print("\nPlease enter customer last name : ");
            customerLastName = input.nextLine();
            while (true){
                try {
                    System.out.print("\nPlease enter how many burgers you want : ");
                    burgerNumber = input.nextInt();
                    if (burgerNumber<= burgerStock){
                        break;
                    }else {
                        System.out.println("Only "+ burgerStock + " burgers remaining in stock!");
                        System.out.println("Please add more burgers to stock and come back!");
                        break;
                    }


                }catch (InputMismatchException e){
                    System.out.println("Please enter a valid integer only!");
                    input.nextLine();
                }

            }

        }
        if (q1True && q1Index<=q2Index){
            if (q1True && q1Index<q3Index){
                highestIndex=q1Index;
                queueNum = 1;
            } else if (q1Index==q3Index) {
                highestIndex=q1Index;
                queueNum = 1;
            } else {
                highestIndex=q3Index;
                queueNum = 3;
            }
        }else {
            if (q2True && q2Index<q3Index){
                highestIndex=q2Index;
                queueNum=2;
            } else if (q2Index==q3Index) {
                highestIndex=q2Index;
                queueNum=2;
            } else {
                highestIndex=q3Index;
                queueNum=3;
            }
        }
        if (highestIndex==-1){
            System.out.println("\nAll the queues are Full!, the customer is added to the waiting list!\n");
            Customer waitingListDetails = new Customer(customerFirstName, customerLastName, burgerNumber);
            FoodQueue customerObjectsWaiting =new FoodQueue(queueNum,customerAddedIndex,waitingListDetails);
            waitingList.add(customerObjectsWaiting);
            customerNumb++;
        }else {
            if (waitingListTrue){
                System.out.println("\nAdding a customer from the waiting list :\n");
                if (burgerStock<=10){
                    System.out.println("The Remaining Burger Stock Is : " + burgerStock);
                    System.out.println();
                }
            }
            if (queueNum==1){
                queueOne[highestIndex]="X";
                customerAddedIndex=highestIndex+1;
                System.out.println("\nCustomer " + customerFirstName + " " + customerLastName + " has been added to queue 1 slot no : " + customerAddedIndex + " successfully!\n");
                burgerStock=burgerStock-burgerNumber;
            } else if (queueNum==2) {
                queueTwo[highestIndex] ="X";
                customerAddedIndex=highestIndex+1;
                System.out.println("\nCustomer " + customerFirstName + " " + customerLastName + " has been added to queue 2 slot no : " + customerAddedIndex + " successfully!\n");
                burgerStock=burgerStock-burgerNumber;

            }else if (queueNum==3) {
                queueThree[highestIndex] ="X";
                customerAddedIndex=highestIndex+1;
                System.out.println("\nCustomer "+ customerFirstName + " " + customerLastName + " has been added to queue 3 slot no : " + customerAddedIndex + " successfully!\n");
                burgerStock=burgerStock-burgerNumber;
            }
            Customer customerDetailsV = new Customer(customerFirstName, customerLastName, burgerNumber);
            FoodQueue customerObjects =new FoodQueue(queueNum,customerAddedIndex,customerDetailsV);
            queueInformation.add(customerObjects);
            customerNumb++;
        }


        input.nextLine();

    }

    private static void circulateSlotNumber(int queueNumber, int slotNumber){

        for (FoodQueue i : queueInformation){
            if (i.getQueueNumber()==queueNumber){
                if (i.getSlotNumber()>slotNumber){
                    i.setSlotNumber(i.getSlotNumber()-1);
                }
            }

        }
    }
    private static void removeServedArrayList(int queueNumber, int slotNumber){
        int indexToRemove=-1;
        for (int y=0;y<queueInformation.size();y++){
            if (queueInformation.get(y).getQueueNumber()==queueNumber && queueInformation.get(y).getSlotNumber()==slotNumber){
                indexToRemove=y;
            }
        }
        queueInformation.remove(indexToRemove);

    }


    /* The Method removeCustomer is also a special function in this code. This function lets the user remove customers from
     * any specific location. The speciality in this function is, there is an added functionality. To make it more realistic like the real world,
     * When you remove a customer, the queues will get automatically updated, so that when the user removes a customer, The customers behind will come,
     * 1 position to the front. For an example, if queue 3 is full, and if u remove the 2nd person in the queue, all the other behind him will come one position
     * forward, and make the last position non-occupied. And when removing a customer, this will update the burger stock as well.*/
    private static void removeCustomer(){

        int qNumber = -1;
        int sNumber =-1;
        int LastXIndex=-1;
        while (true){
            try {
                System.out.println("\nPlease Enter Queue Number : ");
                qNumber = input.nextInt();
                if (qNumber==1){
                    while (true){
                        try{
                            System.out.println("\nPlease Enter Queue 1 Slot Number : ");
                            sNumber = input.nextInt();
                            if (sNumber>0 && sNumber<3){
                                if (Objects.equals(queueOne[sNumber-1], "X")){
                                    for (int i=0; i<2; i++){
                                        if (Objects.equals(queueOne[i], "O")){
                                            LastXIndex = i-1;
                                            queueOne[LastXIndex] = "O";

                                            int indexToRemove=-1;
                                            for (int y=0;y<queueInformation.size();y++){
                                                if (queueInformation.get(y).getQueueNumber()==qNumber && queueInformation.get(y).getSlotNumber()==sNumber){
                                                    indexToRemove=y;
                                                }
                                            }
                                            int burgerToRemove =0;
                                            burgerToRemove = queueInformation.get(indexToRemove).getCustomer().getBurgerNumber();
                                            queueInformation.remove(indexToRemove);

                                            System.out.println("\nCustomer Removed Successfully!\n");
                                            circulateSlotNumber(qNumber, sNumber);
                                            burgerStock = burgerStock + burgerToRemove;
                                            addCustomer(true);
                                            customerNumb--;
                                            break;
                                        }
                                    }
                                    if (LastXIndex==-1){
                                        queueOne[1] = "O";

                                        int indexToRemove=-1;
                                        for (int y=0;y<queueInformation.size();y++){
                                            if (queueInformation.get(y).getQueueNumber()==qNumber && queueInformation.get(y).getSlotNumber()==sNumber){
                                                indexToRemove=y;
                                            }
                                        }
                                        int burgerToRemove =0;
                                        burgerToRemove = queueInformation.get(indexToRemove).getCustomer().getBurgerNumber();
                                        queueInformation.remove(indexToRemove);


                                        System.out.println("\nCustomer Removed Successfully!\n");
                                        circulateSlotNumber(qNumber, sNumber);
                                        burgerStock = burgerStock + burgerToRemove;
                                        addCustomer(true);
                                        customerNumb--;
                                    }
                                    break;
                                }else {
                                    System.out.println("The Slot is Already Empty!\n");
                                    break;
                                }

                            }else {
                                System.out.println("Queue 1 Has Only 2 Slots!\n");
                            }

                        }catch (InputMismatchException e){
                            System.out.println("Please Enter a Valid Slot Number!\n");
                        }
                    }

                } else if (qNumber==2) {
                    while (true){
                        try{
                            System.out.println("\nPlease Enter Specific Place : ");
                            sNumber = input.nextInt();
                            if (sNumber>0 && sNumber<4){
                                if (Objects.equals(queueTwo[sNumber-1], "X")){
                                    for (int i=0; i<3; i++){
                                        if (Objects.equals(queueTwo[i], "O")){
                                            LastXIndex = i-1;
                                            queueTwo[LastXIndex] = "O";

                                            int indexToRemove=-1;
                                            for (int y=0;y<queueInformation.size();y++){
                                                if (queueInformation.get(y).getQueueNumber()==qNumber && queueInformation.get(y).getSlotNumber()==sNumber){
                                                    indexToRemove=y;
                                                }
                                            }
                                            queueInformation.remove(indexToRemove);
                                            int burgerToRemove =0;
                                            burgerToRemove = queueInformation.get(indexToRemove).getCustomer().getBurgerNumber();

                                            System.out.println("\nCustomer Removed Successfully!\n");
                                            circulateSlotNumber(qNumber, sNumber);
                                            burgerStock = burgerStock + burgerToRemove;
                                            addCustomer(true);
                                            customerNumb--;
                                            break;
                                        }
                                    }
                                    if (LastXIndex==-1){
                                        queueTwo[2] = "O";

                                        int indexToRemove=-1;
                                        for (int y=0;y<queueInformation.size();y++){
                                            if (queueInformation.get(y).getQueueNumber()==qNumber && queueInformation.get(y).getSlotNumber()==sNumber){
                                                indexToRemove=y;
                                            }
                                        }
                                        queueInformation.remove(indexToRemove);
                                        int burgerToRemove =0;
                                        burgerToRemove = queueInformation.get(indexToRemove).getCustomer().getBurgerNumber();

                                        System.out.println("\nCustomer Removed Successfully!\n");
                                        circulateSlotNumber(qNumber, sNumber);
                                        burgerStock = burgerStock + burgerToRemove;
                                        addCustomer(true);
                                        customerNumb--;
                                    }
                                    break;
                                }else {
                                    System.out.println("The Slot is Already Empty!\n");
                                    break;
                                }

                            }else {
                                System.out.println("Queue 2 Has Only 3 Slots!\n");
                            }

                        }catch (InputMismatchException e){
                            System.out.println("Please Enter a Valid Slot Number!\n");
                        }
                    }


                } else if (qNumber==3) {
                    while (true){
                        try{
                            System.out.println("\nPlease Enter Specific Place : ");
                            sNumber = input.nextInt();
                            if (sNumber>0 && sNumber<6){
                                if (Objects.equals(queueThree[sNumber-1], "X")){
                                    for (int i=0; i<5; i++){
                                        if (Objects.equals(queueThree[i], "O")){
                                            LastXIndex = i-1;
                                            queueThree[LastXIndex] = "O";

                                            int indexToRemove=-1;
                                            for (int y=0;y<queueInformation.size();y++){
                                                if (queueInformation.get(y).getQueueNumber()==qNumber && queueInformation.get(y).getSlotNumber()==sNumber){
                                                    indexToRemove=y;
                                                }
                                            }
                                            queueInformation.remove(indexToRemove);
                                            int burgerToRemove =0;
                                            burgerToRemove = queueInformation.get(indexToRemove).getCustomer().getBurgerNumber();

                                            System.out.println("\nCustomer Removed Successfully!\n");
                                            circulateSlotNumber(qNumber, sNumber);
                                            burgerStock = burgerStock + burgerToRemove;
                                            addCustomer(true);
                                            customerNumb--;
                                            break;
                                        }
                                    }
                                    if (LastXIndex==-1){
                                        queueThree[4] = "O";

                                        int indexToRemove=-1;
                                        for (int y=0;y<queueInformation.size();y++){
                                            if (queueInformation.get(y).getQueueNumber()==qNumber && queueInformation.get(y).getSlotNumber()==sNumber){
                                                indexToRemove=y;
                                            }
                                        }
                                        queueInformation.remove(indexToRemove);
                                        int burgerToRemove =0;
                                        burgerToRemove = queueInformation.get(indexToRemove).getCustomer().getBurgerNumber();

                                        System.out.println("\nCustomer Removed Successfully!\n");
                                        circulateSlotNumber(qNumber, sNumber);
                                        burgerStock = burgerStock + burgerToRemove;
                                        addCustomer(true);
                                        customerNumb--;
                                    }
                                    break;
                                }else {
                                    System.out.println("The Slot is Already Empty!\n");
                                    break;
                                }

                            }else {
                                System.out.println("Queue 3 Has Only 5 Slots!\n");
                            }

                        }catch (InputMismatchException e){
                            System.out.println("Please Enter a Valid Slot Number!\n");
                        }
                    }

                }else {
                    System.out.println("Invalid Queue Number!\n");
                }
                break;


            }catch (InputMismatchException e){
                System.out.println("Invalid Queue Number!\n");
            }
        }

    }


    /* removeServed method is close to the functionality of , removeCustomer method, but this method only removes served customers. That means
    the customers who are in the very front of the queues. And this does not affect the burger stock since, served customers have already bought the burgers.  */
    private static void removeServed(){
        int qNumberServed =-1;
        int LastXIndex=-1;

        while (true){
            try {
                System.out.print("\nPlease Enter Queue Number To Remove a Served Customer : ");
                qNumberServed = input.nextInt();
                if (qNumberServed>0 && qNumberServed<4){
                    break;
                }else {
                    System.out.println("Invalid Queue Number!\n");
                }

            }catch (InputMismatchException e){
                System.out.println("Invalid Queue Number!\n");
            }
        }
        if (qNumberServed==1){
            if (Objects.equals(queueOne[0], "X")){
                for (int i=0; i<2; i++){
                    if (Objects.equals(queueOne[i], "O")){
                        LastXIndex = i-1;
                        queueOne[LastXIndex] = "O";
                        break;
                    }
                }
                if (LastXIndex==-1){
                    queueOne[1] = "O";
                }
                removeServedArrayList(1,1);
                System.out.println("\nServed Customer removed Successfully From Queue 1!\n");
                circulateSlotNumber(1,1);
                addCustomer(true);

            }else {
                System.out.println("No Served Customer In Queue 1.\n");

            }

        } else if (qNumberServed==2) {
            if (Objects.equals(queueTwo[0], "X")){
                for (int i=0; i<3; i++){
                    if (Objects.equals(queueTwo[i], "O")){
                        LastXIndex = i-1;
                        queueTwo[LastXIndex] = "O";
                        break;
                    }
                }
                if (LastXIndex==-1){
                    queueTwo[2] = "O";
                }
                removeServedArrayList(2,1);
                System.out.println("\nServed Customer removed Successfully From Queue 2!\n");
                circulateSlotNumber(2,1);
                addCustomer(true);

            }else {
                System.out.println("No Served Customer In Queue 2.\n");

            }

        } else if (qNumberServed==3) {
            if (Objects.equals(queueThree[0], "X")){
                for (int i=0; i<5; i++){
                    if (Objects.equals(queueThree[i], "O")){
                        LastXIndex = i-1;
                        queueThree[LastXIndex] = "O";
                        break;
                    }
                }
                if (LastXIndex==-1){
                    queueThree[4] = "O";
                }
                removeServedArrayList(3,1);
                System.out.println("\nServed Customer removed Successfully From Queue 3!\n");
                circulateSlotNumber(3,1);
                addCustomer(true);

            }else {
                System.out.println("No Served Customer In Queue 3.\n");

            }

        }
        input.nextLine();

    }


    /* sortCustomers method, makes a duplicate array, and sorts the names in that array using SELECTION SORT method.
     * Then the function prints all the sorted customer names in the console.*/
    private static void sortCustomers(){
        ArrayList<FoodQueue> sortedCustomerDetails = new ArrayList<>(queueInformation);
        ArrayList<FoodQueue> sortedWaitingList = new ArrayList<>(waitingList);

        for (int i=0;i<sortedCustomerDetails.size() -1;i++){
            int minValue=i;
            for (int j=i+1;j<sortedCustomerDetails.size();j++){
                if (sortedCustomerDetails.get(minValue).getCustomer().getFirstName().compareTo(sortedCustomerDetails.get(j).getCustomer().getFirstName())>0){
                    minValue=j;
                }
            }
            FoodQueue temp= sortedCustomerDetails.get(i);
            sortedCustomerDetails.set(i,sortedCustomerDetails.get(minValue));
            sortedCustomerDetails.set(minValue,temp);
        }

        System.out.println("\nCustomers Sorted According To Alphabetical Order : \n");

        for (FoodQueue printSorted : sortedCustomerDetails){
            printSorted.print();
            System.out.println();
        }

        if (sortedWaitingList.isEmpty()){
            return;
        }else {
            for (int i=0;i<sortedWaitingList.size() -1;i++){
                int minValue=i;
                for (int j=i+1;j<sortedWaitingList.size();j++){
                    if (sortedWaitingList.get(minValue).getCustomer().getFirstName().compareTo(sortedWaitingList.get(j).getCustomer().getFirstName())>0){
                        minValue=j;
                    }
                }
                FoodQueue temp= sortedWaitingList.get(i);
                sortedWaitingList.set(i,sortedWaitingList.get(minValue));
                sortedWaitingList.set(minValue,temp);
            }

            System.out.println("\n Waiting List Customers Sorted According To Alphabetical Order : \n");

            for (FoodQueue printSorted : sortedWaitingList){
                System.out.println("Customer First Name : " + printSorted.getCustomer().getFirstName());
                System.out.println("Customer Last Name  : " + printSorted.getCustomer().getLastName());
                System.out.println();
            }
        }


    }


    /* The Method savaToFile saves the customer details to a text file. The customer name, Customer number and the queue number
     *  the customer was added, is being written to the text file. As an added functionality, this method allows the user to clear the customer details from the arrays
     * in case the user wants to add more than 10 customers. This is only available after saving to the file. So any customers details wouldn't be lost.*/
    private static void savaToFile(){
        char clearArrays;
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("CustomerDetails.txt"));

            int y =1;
            if (queueInformation.isEmpty()){
                System.out.println("No Customer Details Available To Save!");

            }else {
                for (FoodQueue i : queueInformation){
                    writer.write("Customer Number : " + (y)+"\n");
                    writer.write("Customer First Name : " + i.getCustomer().getFirstName()+"\n");
                    writer.write("Customer Last Name  : " + i.getCustomer().getLastName()+"\n");
                    writer.write("Burgers Bought      : " + i.getCustomer().getBurgerNumber()+"\n");
                    writer.write("Queue Number        : " + i.getQueueNumber()+"\n");
                    writer.write("Slot Number         : " + i.getSlotNumber()+"\n");
                    writer.newLine();
                    writer.newLine();
                    y++;

                }
            }

            if (waitingList.isEmpty()){
                System.out.println();

            }else {
                for (FoodQueue i : waitingList){
                    writer.write("Customer Number : " + (y)+"\n");
                    writer.write("Customer First Name   : " + i.getCustomer().getFirstName()+"\n");
                    writer.write("Customer Last Name   : " + i.getCustomer().getLastName()+"\n");
                    writer.newLine();
                    writer.newLine();
                    y++;

                }
            }


            writer.close();
        } catch (Exception e) {
            System.out.println("Unable To Write To The File!\n");
        }
        System.out.println("\nCustomer Details Saved Successfully!\n");
        System.out.println();

    }


    /* The Method loadFromFile loads the saved data and prints it in the console.*/
    private static void loadFromFile(){
        try {
            File file = new File("CustomerDetails.txt");
            Scanner fileScanner = new Scanner(file);
            System.out.println();

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                System.out.println(line);
            }
            fileScanner.close();
            System.out.println("\nLoaded From File Successfully! \n");


        } catch (Exception e) {
            System.out.println("Error While Loading From File!\n");
        }

    }


    /* The Method viewBurgerStock shows the remaining burger stock to the user.*/
    private static void viewBurgerStock(){
        System.out.println("\nThe Remaining Burger Stock Is : " + burgerStock);
        System.out.println();

    }


    /* The Method addBurgers lets the user add more burgers to the stock. But it will Only allow the stock to reach of 50 burgers,
     * since, the stock capacity is mentioned as 50 in the coursework. */
    private static void addBurgers(){
        int addBurger;
        int maxBurger = 50 - burgerStock;
        System.out.println("\nMaximum Number of Burgers That Can Be Added At The Moment To Refill The Stock : " + maxBurger);
        while (true){
            try{
                System.out.print("\nHow Many Burgers Would You Like To Add To Stock : ");
                addBurger = input.nextInt();
                if (addBurger>0 && addBurger<=maxBurger){
                    burgerStock = burgerStock + addBurger;
                    System.out.println("\nBurgers Have Been Successfully Added!");
                    System.out.println("The New Burger Stock is : " + burgerStock);
                    System.out.println();
                    break;
                }else {
                    System.out.println("\nPlease Enter a Number Less Than Or Equal To "+maxBurger);
                }

            }catch (InputMismatchException e){
                System.out.println("Invalid Burger Stock Number Entered\n");
            }
        }
        input.nextLine();

    }

}

class FoodQueue {
    private int queueNumber;
    private int slotNumber;
    private Customer customer;

    public FoodQueue(int queueNumber, int slotNumber, Customer customer) {
        this.queueNumber = queueNumber;
        this.slotNumber = slotNumber;
        this.customer = customer;
    }

    public int getQueueNumber() {
        return queueNumber;
    }

    public void setQueueNumber(int queueNumber) {
        this.queueNumber = queueNumber;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(int slotNumber) {
        this.slotNumber = slotNumber;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void print(){
        System.out.println("First Name      : "+ getCustomer().getFirstName());
        System.out.println("Last Name       : "+ getCustomer().getLastName());
        System.out.println("Burgers Ordered : "+ getCustomer().getBurgerNumber());
        System.out.println("Queue Number    : "+ getQueueNumber());
        System.out.println("Slot Number     : "+ getSlotNumber());
        System.out.println();
    }

}

class Customer {
    private String firstName;
    private String lastName;
    private int burgerNumber;

    public Customer(String firstName, String lastName, int burgerNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.burgerNumber = burgerNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getBurgerNumber() {
        return burgerNumber;
    }

    public void setBurgerNumber(int burgerNumber) {
        this.burgerNumber = burgerNumber;
    }
}
