/* Import Professors vs. Students Library */
import pvs.University;
import pvs.objects.Timer;

/* Import Professors */
import pvs.essentials.*;

/* Import Java Native Classes */
import java.util.Scanner;

public class Demo {
	private static int menu() {
		Scanner sc = new Scanner(System.in);

		System.out.println("-----------------------------------------------------------");
		System.out.println("*                  Professors vs. Zombies                 *");
		System.out.println("-----------------------------------------------------------");
		System.out.println(" [1] Option 1");
		System.out.println(" [2] Option 2");
		System.out.println(" [3] Option 3");
		System.out.println("-----------------------------------------------------------");

		System.out.print(" Choice: ");
		return sc.nextInt();
	}

	public static void main(String[] args) {
		// University uplb = new University(1);

		// Professor a = new Professor(100, 10, 5, "Ralph", 500, 10);
		// Professor b = new Professor(100, 10, 5, "lawrence", 500, 10);
		// uplb.hireProfessor(0, 0, a);
		// uplb.hireProfessor(0, 1, b);
		// uplb.log();

		// Timer timer = new Timer(10);
		// Thread timerthread = new Thread(timer);
		// timerthread.start();
		University uplb = new University(1);
		Talker talker = new Talker(uplb);
		WaterThrower waterThrower = new WaterThrower(uplb);
		Graduating graduating = new Graduating(uplb);
		Freshie freshie = new Freshie(uplb);

		uplb.hireProfessor(0, 0, talker);
		uplb.hireProfessor(0, 1, waterThrower);
		uplb.positionStudent(1, 1, graduating);
		uplb.positionStudent(1, 0, freshie);

		uplb.log();
	}
}