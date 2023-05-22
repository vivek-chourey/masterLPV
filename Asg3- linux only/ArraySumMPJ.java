import mpi.MPI;
import mpi.MPIException;

public class ArraySumMPJ {
    public static void main(String[] args) throws MPIException {
        MPI.Init(args);

        int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10,11,12};
        int n = a.length;

        int rank = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();

        int elementsPerProcessor = n / size;
        int[] subArray = new int[elementsPerProcessor];

        // Scatter the array elements to different processors
        MPI.COMM_WORLD.Scatter(a, 0, elementsPerProcessor, MPI.INT, subArray, 0, elementsPerProcessor, MPI.INT, 0);

        int subSum = 0;
        for (int i = 0; i < elementsPerProcessor; i++) {
            subSum += subArray[i];
        }

        System.out.println("Intermediate sum calculated by processor " + rank + ": " + subSum);

        int[] globalSum = new int[1];

        // Reduce the sub-sums into a global sum
        MPI.COMM_WORLD.Reduce(new int[]{subSum}, 0, globalSum, 0, 1, MPI.INT, MPI.SUM, 0);

        if (rank == 0) {
            System.out.println("Sum of array: " + globalSum[0]);
        }

        MPI.Finalize();
    }
}