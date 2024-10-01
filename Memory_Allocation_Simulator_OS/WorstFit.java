import java.util.ArrayList;

public class WorstFit extends Policy{
    @Override
    
       public boolean allocateMemory(Process process, ArrayList<Partition> partitions) {
        int idx = -1;
        int maximumFragmentation = Integer.MIN_VALUE;
        for(int i = 0;i<partitions.size();i++){
            int fragmentation = partitions.get(i).getSize() - process.getSize();
            if(partitions.get(i).getSize()>=process.getSize()&&fragmentation>maximumFragmentation&&partitions.get(i).isFree()){
                maximumFragmentation = fragmentation;
                idx = i;
            }
        }
        if(idx != -1){
            Partition p = new Partition("Partition" + Partition.ID,partitions.get(idx).getSize()-process.getSize(),true);
            if(p.getSize()>0)
                partitions.add(idx+1, p);
            partitions.get(idx).setFree(false);
            partitions.get(idx).setSize(process.getSize());
            partitions.get(idx).setProcess(process);
            return true;
        }
        return false;
    }
}
