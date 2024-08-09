import java.util.*;

public class Client {
    private static Random rand = new Random();

    public static void main(String[] args) throws Exception {
        // List<Location> scenario = createRandomScenario(10, 10, 100, 1000, 100000);
        List<Location> scenario = createSimpleScenario();
        System.out.println(scenario);
        
        double budget = 2000;
        Set<Allocation> allocations = generateOptions(budget, scenario);
        printResult(allocations, budget);
        // generateOptions(budget, scenario);
    }

    public static Set<Allocation> generateOptions(double budget, List<Location> sites) {
        Set<Allocation> allocations = new HashSet<Allocation>();
        return generateOptions(budget, sites, allocations);
    }

    private static Set<Allocation> generateOptions(double budget, List<Location> sites, Set<Allocation> allocations) {
        /*if (budget >= 0 && locations.size() != 0) {
            Allocation allocation = new Allocation();
            for (Location loc : locations) {
                allocation = allocation.withLoc(loc);
            } 
            int currentsize = allocations.size();
            allocations.add(allocation);
            int finalsize = allocations.size();
            if (currentsize != finalsize) {
                System.out.println(allocation.toString());
            }
            for (int i = 0; i < sites.size(); i ++) {
                // choose
                Location loc = sites.get(i);
                sites.remove(i);
                locations.add(loc);
                // explore
                generateOptions(budget - loc.getCost(), sites, allocations, locations);
                // unchoose
                locations.remove(loc);
                sites.add(i, loc);
            
        }
        return allocations;
            */
        }

/*
    public static void generateOptions(double budget, List<Location> sites) {
        Set<Allocation> allocations = new HashSet<Allocation>();
        generateOptions(budget, sites, allocations, new HashSet<Location>());
    }

    private static void generateOptions(double budget, List<Location> sites, Set<Allocation> allocations, Set<Location> locations) {
        if (budget >= 0 && locations.size() != 0) {
            Allocation allocation = new Allocation();
            for (Location loc : locations) {
                allocation = allocation.withLoc(loc);
            } 
            int currentsize = allocations.size();
            allocations.add(allocation);
            int finalsize = allocations.size();
            if (currentsize != finalsize) {
                System.out.println(allocation.toString());
            }
            
        }
        /* if (locations.size() != 0) {
            Allocation allocation = new Allocation();
            for (Location loc : locations) {
                allocation = allocation.withLoc(loc);
            } 
            System.out.println(allocation.toString());
        } 
        
        
            for (int i = 0; i < sites.size(); i ++) {
                // choose
                Location loc = sites.get(i);
                sites.remove(i);
                locations.add(loc);
                // explore
                generateOptions(budget - loc.getCost(), sites, allocations, locations);
                // unchoose
                locations.remove(loc);
                sites.add(i, loc);
            
        }
    }
    */
    /*
    public static Set<Allocation> generateOptions(double budget, List<Location> sites) {
        Set<Allocation> allocations = new HashSet<Allocation>();
        return generateOptions(budget, sites, allocations, new HashSet<Location>());
    }

    private static Set<Allocation> generateOptions(double budget, List<Location> sites, Set<Allocation> allocations, Set<Location> locations) {
        if (budget < 0) {
            return allocations;
        }
        if (locations.size() != 0) {
            Allocation allocation = new Allocation();
            for (Location loc : locations) {
                allocation = allocation.withLoc(loc);
            } 
            allocations.add(allocation);
            return allocations;
        } 
        
        for (int i = 0; i < sites.size(); i ++) {
            // choose
            Location loc = sites.get(i);
            sites.remove(i);
            locations.add(loc);
            // explore
            allocations = generateOptions(budget - loc.getCost(), sites, allocations, locations);
            // unchoose
            locations.remove(loc);
            sites.add(i, loc);
            }
        return allocations;
        
    }
    */

    // TODO: add any of your own helper methods here

    // PROVIDED HELPER METHODS - **DO NOT MODIFY ANYTHING BELOW THIS LINE!**

    public static void printResult(Set<Allocation> allocs, double budget) {
        for (Allocation alloc : allocs) {
            System.out.println("Result: ");
            System.out.println("  " + alloc);
            System.out.println("  People helped: " + alloc.totalPeople());
            System.out.printf("  Cost: $%.2f\n", alloc.totalCost());
            System.out.printf("  Unused budget: $%.2f\n", (budget - alloc.totalCost()));        
        }
    }

    public static List<Location> createRandomScenario(int numLocs, int minPop, int maxPop, double minCostPer, double maxCostPer) {
        List<Location> result = new ArrayList<>();

        for (int i = 0; i < numLocs; i++) {
            int pop = rand.nextInt(minPop, maxPop + 1);
            double cost = rand.nextDouble(minCostPer, maxCostPer) * pop;
            result.add(new Location("Location #" + i, pop, round2(cost)));
        }

        return result;
    }

    public static List<Location> createSimpleScenario() {
        List<Location> result = new ArrayList<>();

        result.add(new Location("Location #1", 50, 500));
        result.add(new Location("Location #2", 100, 700));
        result.add(new Location("Location #3", 60, 1000));
        result.add(new Location("Location #4", 20, 1000));
        result.add(new Location("Location #5", 200, 900));

        return result;
    }    

    private static double round2(double num) {
        return Math.round(num * 100) / 100.0;
    }
}
