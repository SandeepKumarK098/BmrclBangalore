// 999 lines of code to clone the Transactions of BMRCL, Bengaluru
//adminIdentity : "admin"
//adminPassword : "admin"

package bmrclBengaluru;

import java.util.*;
import java.lang.Math;

//Station Class holds the Individual Stations of BMRCL
class Station {
	
	public String name;
	public int ID, sn;
	public int np;
	Station left = null;
	Station right = null;
	
	Scanner scan = new Scanner(System.in);
	public static final Random rand = new Random();
	
	public Station(Station head, String name) {
		this.name = name;
		this.ID = genID(head);
		this.sn = genSN(head);
		this.np = 0;
		this.left = null;
		this.right = null;
	}
	
	public Station(Station head) {
		System.out.print("Enter Name : ");
		this.name = scan.nextLine().trim();
		this.ID = genID(head);
		this.sn = genSN(head);
		this.np = 0;
		this.left = null;
		this.right = null;
	}
	
	public static int genID(Station head) {							//Generate ID using Random
		int id = 0;
		
		for (int i = 0;i < 4;i++) {
			id = id * 10 + rand.nextInt(1, 9);
		}
		
		Station temp = head;										//Check whether ID is already existing since randomly generated
		while (temp != null) {
			if (temp.ID == id) {
				return genID(head);
			}
			
			temp = temp.right;
		}
		
		return id;
	}
	
	public static int genSN(Station head) {							//Allocate Station Number
		int sn = 0;
		Station temp = head;
		
		while (temp != null) {
			sn++;
			temp = temp.right;
		}
		
		return ++sn;
	}
	
	public void displayStation() {									//Display Station Format
		System.out.println("StNo : " + (this.sn < 10 ? ("0" + this.sn) : this.sn) + "\tName : " + this.name.substring(0, this.name.length() >= 8 ? 8 : this.name.length()).toUpperCase() + "\t\tID : " + this.ID);
	}
	
	public int getID() {											//Get ID of Station
		return this.ID;
	}
	
	public int getSN() {											//Get Station Number
		return this.sn;
	}
	
	public String getName() {										//Get Name
		return this.name;
	}
}


//Ticket Class holds Individual Tickets
class Ticket {
	
	public int ID, tn;
	public int np;
	public String from, to;
	public double price;
	Ticket next = null;
	
	public Ticket(Station shead, Ticket thead) {
		this.ID = genID(thead);
		this.tn = genTN(thead);
		this.np = 0;
		this.from = "";
		this.to = "";
		this.price = 0.0;
		this.next = null;
	}
	
	public Ticket(Ticket thead) {
		this.ID = genID(thead);
		this.tn = genTN(thead);
		this.np = 0;
		this.from = "";
		this.to = "";
		this.price = 0.0;
		this.next = null;
	}
	
	public static int genID(Ticket thead) {								//Generate ID using Random
		Random rand = new Random();
		int id = 0;
		
		for (int i = 0;i < 6;i++) {
			id = id * 10 + rand.nextInt(1, 9);
		}
		
		Ticket temp = thead;
		while (temp != null) {
			if (temp.ID == id) {
				return genID(thead);
			}
			
			temp = temp.next;
		}
		
		return id;
	}
	
	public static int genTN(Ticket head) {								//Allocate Ticket Number
		int res = 0;
		Ticket temp = head;
		
		while (temp != null) {
			res++;
			temp = temp.next;
		}
		
		return ++res;
	}
	
	public int getID() {												//Get ID of Ticket
		return this.ID;
	}
	
	public int getTN() {												//Get Ticket Number
		return this.tn;
	}
	
	public String getFromStation() {									//Get From Station of Journey
		return this.from;
	}
	
	public String getToStation() {										//Get To Station of Journey
		return this.to;
	}
	
	public double getPrice() {											//Get Price of Journey
		return this.price;
	}
	
	public void displayTicket() {										//Displaying Ticket Format
		System.out.println("\nTktNo : " + this.tn + "\tID : " + this.ID + "\tNoOfPass : " + this.np + "\tFrom : " + this.from.toUpperCase() + "\t\tTo : " + this.to.toUpperCase() + "\tPrice : " + this.price);
	}
}


//BMRCL Class for all Operations and Activities + Transactions
public class BmrclBengaluru {
	
	public static final Random rand = new Random();
	public static final Scanner scan = new Scanner(System.in);
	
	public static final String adminID = "admin";						//Admin ID for Authentication
	public static final String adminPW = "admin";						//Admin Password for Authentication
	
	public static boolean isLogged = false;								//To Check whether Admin is Logged
	
	public static int NoOfTickets = 0;									//For Transactions Storage
	public static double totalRevenue = 0.0;
	
	public static void main(String[] args) {
		
		Station shead = new Station(null, "Majestic");
		Ticket thead = new Ticket(shead, null);
		
		shead = insertRear(shead, "Chickpet");
		shead = insertRear(shead, "KRMarket");
		shead = insertRear(shead, "NatCollege");
		shead = insertRear(shead, "LalBagh");
		shead = insertRear(shead, "SECircle");
		shead = insertRear(shead, "Jayanagar");
		shead = insertRear(shead, "RVRoad");
		shead = insertRear(shead, "Banashankari");
		shead = insertRear(shead, "JPNagar");
		shead = insertRear(shead, "Vajarahalli");
		shead = insertRear(shead, "SilkInst");
		
		int op = 0;
		
		do {
			
			System.out.print("\n\t\t-------------------MENU---------------------MENU--------------------MENU-----------------MENU----------------------\n");
			System.out.print("1. Display Stations\t2. Add Station\t\t3. DeleteStation\t4. ModifyStation\t5. Ticket Generation\n");
			System.out.print("6. Display Tickets\t7. Display Revenue\t8. Customer Queries\t9. Driver Queries\t10. EXIT\n\nEnter Option : ");
			
			op = scan.nextInt();
			
			switch(op) {
			
				case 1:
					displayAllStations(shead);
					
					break;
					
				case 2:
					int iop = 0;
					
					do {
						
						System.out.print("\n1. InsertFront\t2. InsertRear\t3. InsertPos\t4. EXIT\n\nEnter Option : ");
						iop = scan.nextInt();
						
						switch(iop) {
						
							case 1:
								shead = insertFront(shead);
								break;
								
							case 2:
								shead = insertRear(shead);
								break;
								
							case 3:
								System.out.print("Enter Position : ");
								int pos = scan.nextInt();
								
								shead = insertPos(shead, pos);
								break;
								
							default:
								System.out.println();
								break;
						}
						
					} while (iop < 4);
					
					break;
				
				case 3:
					int dop = 0;
					
					do {
						
						System.out.print("\n1. DeleteFront\t2. DeleteRear\t3. DeletePos\t4.EXIT\n\nEnter Option : ");
						dop = scan.nextInt();
						
						switch(dop) {
						
							case 1:
								shead = deleteFront(shead);
								break;
							
							case 2:
								shead = deleteRear(shead);
								break;
								
							case 3:
								System.out.print("Enter Position : ");
								int pos = scan.nextInt();
								shead = deletePos(shead, pos);
								break;
								
							default:
								break;
						}
						
					} while (dop < 4);
					
					break;
				
				case 4:
					displayStationWithID(shead);
					
					System.out.print("Enter ID : ");
					int id = scan.nextInt();
					
					String newName = "";
					while (newName.equalsIgnoreCase("")) {
						System.out.print("Enter new Name : ");
						newName = scan.nextLine();
					}
					
					shead = modifyStation(shead, id, newName);
					
					break;
					
				case 5:
					generateTicket(shead, thead);
					
					break;
					
				case 6:
					displayAllTickets(thead);
					
					break;
					
				case 7:
					displayRevenue(shead, thead);
					
					break;
					
				case 8:
					customerQueries(shead);
					
					break;
					
				case 9:
					driverQueries(shead);
					
					break;
					
				default:
					isLogged = false;
					
					break;
			}
			
		} while (op < 10);
		
		scan.close();
	}
	
	public static int getNoOfStations(Station head) {					//Total Number of Stations
		int res = 0;
		Station temp = head;
		
		while (temp != null) {
			res++;
			temp = temp.right;
		}
		
		return res;
	}
	
	public static int getNoOfTickets(Ticket thead) {					//Total Number of Tickets
		int res = 0;
		Ticket temp = thead;
		
		while (temp != null) {
			res++;
			temp = temp.next;
		}
		
		return res;
	}
	
	public static boolean adminAuthenticate() {							//Admin Authentication for Transactions
		System.out.print("Enter Admin ID : ");
		String id = scan.nextLine();
		
		System.out.print("Enter Password : ");
		String pw = scan.nextLine();
		
		return id.equalsIgnoreCase(adminID) && pw.equalsIgnoreCase(adminPW) ? true : false;
	}
	
	public static void displayAllStations(Station head) {				//Display All Existing Stations
		Station temp = head;
		System.out.println("\nNo of Stations : " + getNoOfStations(head));
		
		while (temp != null) {
			temp.displayStation();
			temp = temp.right;
		}
		
		System.out.println("\n------------------------------------------------------");
		System.out.println("------------------------------------------------------");
	}
	
	public static void displayAllTickets(Ticket thead) {				//Display All Tickets
		while (!isLogged) {
			System.out.println("Admin Authentication Required");
			isLogged = adminAuthenticate();
		}
		
		Ticket temp = thead.next;
		while (temp != null) {
			temp.displayTicket();
			temp = temp.next;
		}
	}
	
	public static void displayRevenue(Station shead, Ticket thead) {	//Display Revenue of Day
		System.out.println("Number of Tickets : " + getNoOfTickets(thead) + "\nTotal Collection : " + totalRevenue);
	}
	
	public static void displatStationRevenue(Station shead) {			//To Display Revenue of each Station
		Station temp = shead;
		
		while (temp != null) {
			System.out.println(temp.name + " : " + temp.np * 9.0);
			temp = temp.right;
		}
	}
	
	public static void displayStationWithID(Station head) {				//Display Stations with ID's
		Station temp = head;
		System.out.println();
		
		while (temp != null) {
			System.out.println((temp.name.length() >= 8 ? temp.name.substring(0, 8).toUpperCase() : temp.name.toUpperCase()) + "\t\t" + temp.ID);
			temp = temp.right;
		}
		
		System.out.println();
	}
	
	public static Station getStationByID(Station head, int ID) {		//Get Station by ID
		if (head == null) {
			return head;
		}
		
		Station temp = head;
		while (temp != null) {
			if (temp.ID == ID) {
				return temp;
			}
			
			temp = temp.right;
		}
		
		return temp;
	}
	
	public static Station getStationBySN(Station head, int sn) {		//Get Station by Station Number
		if (head == null) {
			return head;
		}
		
		Station temp = head;
		while (temp != null) {
			if (temp.sn == sn) {
				return temp;
			}
			
			temp = temp.right;
		}
		
		return temp;
	}
	
	public static Station getStationByName(Station shead) {				//Get Station by Name
		String name;
		System.out.print("Enter Name : ");
		name = scan.nextLine().trim();
		
		Station temp = shead;
		while (temp != null) {
			if (temp.name.equalsIgnoreCase(name)) {
				return temp;
			}
			
			temp = temp.right;
		}
		
		return temp;
	}
	
	public static Station getStationByName(Station head, String name) {
		if (head == null) {
			return head;
		}
		
		Station temp = head;
		while (temp != null) {
			if (temp.name.equalsIgnoreCase(name)) {
				return temp;
			}
			
			temp = temp.right;
		}
		
		return temp;
	}
	
	public static int getPositionOfStation(Station shead, int id) {		//Get Index or Position of Station in List
		int pos = 0;
		Station temp = shead;
		
		while (temp != null) {
			pos++;
			if (temp.ID == id) {
				return pos;
			}
			
			temp = temp.right;
		}
		
		return pos;
	}
	
	public static Station modifyStation(Station head, int ID) {			//Alter Details of Station
		Station temp = getStationByID(head, ID);
		
		if (temp != null) {
			System.out.print("Enter New Name : ");
			String name = scan.nextLine();
			
			return modifyStation(head, ID, name);
		}
		
		return head;
	}
	
	public static Station modifyStation(Station head, int ID, String newName) {
		getStationByID(head, ID).name = newName;
		return head;
	}
	
	public static Station insertFront(Station head) {					//Insert at Head
		while (!isLogged) {
			System.out.println("Admin Authentication needed");
			isLogged = adminAuthenticate();
		}
		
		Station st = new Station(head);
		st.sn = 1;
		
		if (head == null) {
			return st;
		}
		
		st.right = head;
		head.left = st;
		head = st;
		
		int x = 0;
		Station temp = head;
		
		while (temp != null) {
			temp.sn = ++x;
			temp = temp.right;
		}
		
		return head;
	}
	
	public static Station insertFront(Station head, String name) {
		while (!isLogged) {
			System.out.println("Admin Authentication needed");
			isLogged = adminAuthenticate();
		}
		
		Station st = new Station(head, name);
		if (head == null) {
			return st;
		}
		
		st.right = head;
		head.left = st;
		head = st;
		
		int x = 0;
		Station temp = head;
		
		while (temp != null) {
			temp.sn = ++x;
			temp = temp.right;
		}
		
		return head;
	}
	
	public static Station insertRear(Station head) {					//Insert at Tail
		while (!isLogged) {
			System.out.println("Admin Authentication needed");
			isLogged = adminAuthenticate();
		}
		
		Station st = new Station(head);
		if (head == null) {
			return st;
		}
		
		Station temp = head;
		while (temp.right != null) {
			temp = temp.right;
		}
		
		temp.right = st;
		st.left = temp;
		
		return head;
	}
	
	public static Station insertRear(Station head, String name) {
		while (!isLogged) {
			System.out.println("Admin Authentication needed");
			isLogged = adminAuthenticate();
		}
		
		Station st = new Station(head, name);
		if (head == null) {
			return st;
		}
		
		Station temp = head;
		while (temp.right != null) {
			temp = temp.right;
		}
		
		temp.right = st;
		st.left = temp;
		
		return head;
	}
	
	public static Station insertPos(Station head, int pos) {			//Insert at given Position or Index
		while (!isLogged) {
			System.out.println("Admin Authentication needed");
			isLogged = adminAuthenticate();
		}
		
		if (head == null || pos < 1) {
			return insertFront(head);
		}
		
		else if (pos > getNoOfStations(head)) {
			return insertRear(head);
		}
		
		int x = 1;
		Station prev = null, cur = head;
		
		while (x <= pos && cur != null) {
			prev = cur;
			cur = cur.right;
			x++;
		}
		
		Station st = new Station(head);
		
		prev.right = st;
		st.left = prev;
		st.right = cur;
		
		if (cur != null) {
			cur.left = st;
		}
		
		x = prev.getSN();
		Station temp = st;
		
		while (temp != null) {
			temp.sn = ++x;
			temp = temp.right;
		}
		
		return head;
	}
	
	public static Station insertPos(Station head, String name, int pos) {
		while (!isLogged) {
			System.out.println("Admin Authentication needed");
			isLogged = adminAuthenticate();
		}
		
		if (head == null || pos < 1) {
			return insertFront(head, name);
		}
		
		else if (pos > getNoOfStations(head)) {
			return insertRear(head, name);
		}
		
		int x = 1;
		Station prev = null, cur = head;
		
		while (x <= pos && cur != null) {
			prev = cur;
			cur = cur.right;
			x++;
		}
		
		Station st = new Station(head, name);
		prev.right = st;
		st.left = prev;
		st.right = cur;
		
		if (cur != null) {
			cur.left = st;
		}
		
		x = prev.getSN();
		Station temp = st;
		
		while (temp != null) {
			temp.sn = ++x;
			temp = temp.right;
		}
		
		return head;
	}
	
	public static Station deleteFront(Station head) {					//Delete Head Station
		while (!isLogged) {
			System.out.println("Admin Authentication needed");
			isLogged = adminAuthenticate();
		}
		
		if (head == null) {
			return head;
		}
		
		Station temp = head.right;
		head.left = null;
		head.right = null;
		temp.left = null;
		head = null;
		
		Station st = temp;
		int x = 0;
		
		while (st != null) {
			st.sn = ++x;
			st = st.right;
		}
		
		return temp;
	}
	
	public static Station deleteRear(Station head) {					//Delete at Tail
		while (!isLogged) {
			System.out.println("Admin Authentication needed");
			isLogged = adminAuthenticate();
		}
		
		if (head == null) {
			return head;
		}
		
		Station prev = null, temp = head;
		while (temp.right != null) {
			prev = temp;
			temp = temp.right;
		}
		
		if (prev == null) {
			return prev;
		}
		
		prev.right = null;
		temp.left = null;
		temp = null;
		
		return head;
	}
	
	public static Station deletePos(Station head, int pos) {		//Delete at Position or Index
		while (!isLogged) {
			System.out.println("Admin Authentication needed");
			isLogged = adminAuthenticate();
		}
		
		if (head == null || pos <= 0) {
			return head;
		}
		
		else if (pos > getNoOfStations(head)) {
			return head;
		}
		
		int x = 1;
		Station prev = null, cur = head;
		
		while (x <= pos && cur != null) {
			prev = cur;
			cur = cur.right;
			x++;
		}
		
		if (cur != null) {
			prev.right = cur.right;
			cur.left = cur.right = null;
			
			if (cur.right != null) {
				cur.right.left = prev;
			}
			
			cur = null;
			Station temp = prev;
			
			x = prev.getSN();
			
			while (temp != null) {
				temp.sn = x++;
				temp = temp.right;
			}
		}
		
		else {
			prev.right = null;
		}
		
		return head;
	}
	
	public static double calculatePrice(Station shead, int from, int to) {		//Price for Journey
		return ((double) Math.abs(getStationByID(shead, from).sn - getStationByID(shead, to).sn)) * 9.0;
	}
	
	public static void updateRevenue(Ticket tkt) {								//Update Transactions
		NoOfTickets += tkt.np;
		totalRevenue += tkt.price;
	}
	
	public static void generateTicket(Station shead, Ticket thead) {			//Initiate Ticket for Journey
		displayAllStations(shead);
		
		int fromID, toID;
		
		System.out.print("Enter from Station ID : ");
		fromID = scan.nextInt();
		System.out.print("Enter to Station ID : ");
		toID = scan.nextInt();
		
		Ticket tkt = new Ticket(shead, thead);
		Station fromStat = getStationByID(shead, fromID);
		Station toStat = getStationByID(shead, toID);
		
		tkt.from = fromStat.name;
		tkt.to = toStat.name;
		
		System.out.print("Enter No of Passengers : ");
		tkt.np = scan.nextInt();
		tkt.price = tkt.np * calculatePrice(shead, fromID, toID);
		
		if (tkt.price >= 27.0) {
			tkt.price -= tkt.price * 0.10;
		}
		
		else if (tkt.price >= 54.0) {
			tkt.price -= tkt.price * 0.20;
		}
		
		else {
			tkt.price -= tkt.price * 0.30;
		}
		
		fromStat.np += tkt.np;
		toStat.np += tkt.np;
		
		System.out.print("Mode of Payment\n1. CASH\t2. UPI\nEnter your Choice : ");
		int op = scan.nextInt();
		
		if (op == 1) {
			System.out.println("Paid through Cash");
		}
		else {
			System.out.println("Paid through UPI");
		}
		
		updateRevenue(tkt);
		
		thead = insertTicket(thead, tkt);
		System.out.println("Ticket from " + getStationByID(shead, fromID).name +" to " + getStationByID(shead, toID).name +" initiated with id " + tkt.ID +" and Price " + tkt.price);
	}
	
	public static Ticket insertTicket(Ticket thead, Ticket tkt) {				//Add Ticket to Record
		if (thead == null) {
			thead = tkt;
			return thead;
		}
		
		Ticket temp = thead;
		while (temp.next != null) {
			temp = temp.next;
		}
		
		temp.next = tkt;
		return thead;
	}
	
	public static void customerQueries(Station shead) {							//Customer Queries and Responses
		System.out.println("-----------------QUERIES-----------------\n");
		System.out.print("1. How many Stations are in BMRCL ?\n2. Know Fare Price ?\n3. Metro Feeder BUS Facilities ?\n4. Wrong Ticket Booked ?\n5. Your Belongings Missing ?\nEnter Query No : ");
		int op = scan.nextInt();
		
		switch(op) {
			case 1:
				System.out.println("WELL.!! There are " + getNoOfStations(shead) + " Stations in BMRCL.");
				System.out.println("Wish you a Happy and Safeful Journey");
				break;
				
			case 2:
				displayAllStations(shead);
				int from, to;
				
				System.out.print("Enter from ID : ");
				from = scan.nextInt();
				System.out.print("Enter to : ");
				to = scan.nextInt();
				
				System.out.println("Fare Price from " + getStationByID(shead, from).name + " to " + getStationByID(shead, to).name + " is : " + calculatePrice(shead, from, to));
				System.out.println("Wish you a Happy and Safeful Journey");
				break;
				
			case 3:
				displayAllStations(shead);
				
				System.out.print("Enter Station ID : ");
				int id = scan.nextInt();
				
				if (getStationByID(shead, id).sn % 2 == 0) {
					System.out.println("YES.!! Metro Feeder BUS Services are Available at " + getStationByID(shead, id).name);
					System.out.println("Wish you a Happy and Safeful Journey");
				}
				
				else {
					System.out.println("NO.!! We are Sorry as the Metro Feeder BUS Services are Available at " + getStationByID(shead, id).name);
					System.out.println("Wish you a Happy and Safeful Journey");
				}
				
				break;
				
			case 4:
				System.out.println("Don't Woory..!!");
				System.out.println("If your Ticket is booked for longer trip, than you can get down at your station");
				System.out.println("If your Ticket is booked for shorter trip than your station, than get down at that destination");
				System.out.println("Book new Ticket from there to your Destination");
				break;
				
			case 5:
				System.out.println("Don't Worry..!!");
				System.out.println("Approach to the Counter Staff and explain about the Issue to recover");
				System.out.println("Be careful about your Belongings from next time as we are not responsible");
				
				break;
				
			default:
				System.out.println("THANK YOU..👋👋!!We are here for your all time queries\n");
				System.out.println("Wish you a Happy and Safeful Journey");
				break;
		}
	}
	
	public static void driverQueries(Station shead) {								//Driver Queries and Responses
		System.out.println("\n-----------------QUERIES-----------------\n");
		System.out.print("1. Know Current Station Name ?\n2. Is the Track non-defective at next Station ?\n");
		System.out.print("3. Train Engine Defect ?\nEnter Query No : ");
		
		int op = scan.nextInt();
		int id;
		
		switch(op) {
		
			case 1:
				displayAllStations(shead);
				
				System.out.print("Enter ID : ");
				id = scan.nextInt();
				
				System.out.println("You are at " + getStationByID(shead, id).name + " Station.");
				System.out.println("Wish you a Happy and Safeful Journey");
				break;
				
			case 2:
				System.out.println("YES.!! GODDAMN Track is non-defective on your Way.");
				System.out.println("Wish you a Happy and Safeful Journey");
				break;
				
			case 3:
				displayAllStations(shead);
				
				System.out.print("Enter Stat ID of your current LOC : ");
				id = scan.nextInt();
				
				System.out.println("Don't PANIC.!! We are heading towards " + getStationByID(shead, id).name);
				
				break;
				
			default:
				System.out.println("THANK YOU..👋👋!! We are here for your all time queries\n");
				System.out.println("Wish you a Happy and Safeful Journey");
				
				break;
		}
	}
}