import java.util.*;

import java.io.*;

public class demo {
	public void print(ArrayList<Partition> partitions) {
		for (int i = 0; i < partitions.size(); i++) {
			if (partitions.get(i).isFree())
				System.out.println(partitions.get(i).getPartitionName() + " (" + partitions.get(i).getSize()
						+ "KB) => External fragment");
			else
				System.out.println(partitions.get(i).getPartitionName() + " (" + partitions.get(i).getSize() + "KB) => "
						+ partitions.get(i).getProcess().getProcessName());
		}
	}

	public static void main(String[] args) {
		Scanner myObj1 = new Scanner(System.in);
		Scanner myObj2 = new Scanner(System.in);
		String PartitonName, processName;
		int No_Partitions, PartitionSize, No_processes, processSize;
		Policy policy;
		System.out.println("Enter nmuber of paritions ");
		No_Partitions = myObj1.nextInt();
		ArrayList<Partition> partitions = new ArrayList<>();
		ArrayList<Process> processes = new ArrayList<>();

		for (int i = 0; i < No_Partitions; i++) {
			System.out.println("Enter name of partition " + (i + 1) + " ");
			PartitonName = myObj2.nextLine();
			System.out.println("Enter size of partition ");
			PartitionSize = myObj1.nextInt();
			partitions.add(new Partition(PartitonName, PartitionSize, true));
		}

		System.out.println("Enter number of processes ");
		No_processes = myObj1.nextInt();
		for (int i = 0; i < No_processes; i++) {
			System.out.println("Enter name of process " + (i + 1) + " ");
			processName = myObj2.nextLine();
			System.out.println("Enter size of process ");
			processSize = myObj1.nextInt();
			processes.add(new Process(processName, processSize));
		}
		int select;
		demo d = new demo();
		while (true) {
			ArrayList<Partition> temp = new ArrayList<>();
			for(int i = 0;i<partitions.size();i++){
				temp.add(new Partition(partitions.get(i).getPartitionName(),partitions.get(i).getSize(),true));
			}
			Partition.ID = partitions.size();
			ArrayList<Process> notAllocated = new ArrayList<>();
			System.out.println("Select the policy you want to apply:\n"
					+ "1. First fit\n"
					+ "2. Worst fit\n"
					+ "3. Best fit \n"
					+ "4. exit \n");
			System.out.println("Select policy:");
			select = myObj1.nextInt();
			if (select == 1) {
				policy = new FirstFit();
				for (int i = 0; i < processes.size(); i++)
					if (!policy.allocateMemory(processes.get(i), temp))
						notAllocated.add(processes.get(i));
				d.print(temp);
				for (int i = 0; i < notAllocated.size(); i++)
					System.out.println(notAllocated.get(i).getProcessName() + " can not be allocated");
				int choice;
				System.out.println("Do you want to compact? 1.yes 2.no");
				choice = myObj1.nextInt();
				if (choice == 1) {
					policy.compaction(temp); ;
					for (int i = 0; i < notAllocated.size(); i++)
						if(policy.allocateMemory(notAllocated.get(i), temp))
							notAllocated.remove(notAllocated.get(i));
					d.print(temp);
					for (int i = 0; i < notAllocated.size(); i++)
						System.out.println(notAllocated.get(i).getProcessName() + " can not be allocated");
				}
			} else if (select == 2) {
				policy = new WorstFit();
				for (int i = 0; i < processes.size(); i++)
					if (!policy.allocateMemory(processes.get(i), temp))
						notAllocated.add(processes.get(i));
				d.print(temp);
				for (int i = 0; i < notAllocated.size(); i++)
					System.out.println(notAllocated.get(i).getProcessName() + " can not be allocated");
				int choice;
				System.out.println("Do you want to compact? 1.yes 2.no");
				choice = myObj1.nextInt();
				if (choice == 1) {
					policy.compaction(temp);
					for (int i = 0; i < notAllocated.size(); i++)
						if(policy.allocateMemory(notAllocated.get(i), temp))
							notAllocated.remove(notAllocated.get(i));
					d.print(temp);
					for (int i = 0; i < notAllocated.size(); i++)
						System.out.println(notAllocated.get(i).getProcessName() + " can not be allocated");
				}
			} else if (select == 3) {
				policy = new BestFit();
				for (int i = 0; i < processes.size(); i++)
					if (!policy.allocateMemory(processes.get(i), temp))
						notAllocated.add(processes.get(i));
				d.print(temp);
				for (int i = 0; i < notAllocated.size(); i++)
					System.out.println(notAllocated.get(i).getProcessName() + " can not be allocated");
				int choice;
				System.out.println("Do you want to compact? 1.yes 2.no");
				choice = myObj1.nextInt();
				if (choice == 1) {
					policy.compaction(temp);
					for (int i = 0; i < notAllocated.size(); i++)
						if(policy.allocateMemory(notAllocated.get(i), temp))
							notAllocated.remove(notAllocated.get(i));
					d.print(temp);
					for (int i = 0; i < notAllocated.size(); i++)
						System.out.println(notAllocated.get(i).getProcessName() + " can not be allocated");
				}
			} else if (select == 4)
				break;
			temp.clear();
		}
	}
}