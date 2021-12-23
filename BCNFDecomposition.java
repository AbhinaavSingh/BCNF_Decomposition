import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class BCNFDecomposition {

    public static Relation relation;
    public static Set<FD> fdList;
    public static Set<Relation> finalRelations;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        /**
         * If you want to take example values instead of user input values, call the
         * method setUpExample() and it will make the code run for example values.
         * If you call setUpExample(), don't forget to comment out the
         * call for fetchRelationAndFDs().
          */

        fetchRelationAndFDs();

        getBCNFRelations(relation);

        displayBCNFRelations();

    }

    private static void fetchRelationAndFDs() {
        String relationString;
        int fdNum;
        finalRelations = new HashSet<>();
        fdList= new HashSet<>();
        System.out.print("Enter the initial relation without commas. Eg. R(A,B,C) as ABC :");
        relationString = scanner.nextLine();
        relation = Relation.create(relationString);

        System.out.print("How many Functional Dependencies do you want to enter (Enter integer): ");
        fdNum = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < fdNum; i++) {
            System.out.print("Enter FD"+(i+1)+"\'s LHS without commas. Eg. ABC : ");
            String lhsString = scanner.nextLine();
            System.out.print("Enter FD"+(i+1)+"\'s RHS without commas. Eg. ABC : ");
            String rhsString = scanner.nextLine();
            fdList.add(FD.create(lhsString, rhsString));
        }
    }

    private static void displayBCNFRelations() {

        System.out.println("********************************************************");


        System.out.println("Given relation :" + relation.toString());
        System.out.println("Given Functional Dependencies :");

        for (FD fd:fdList) {
            System.out.println(fd.toString());
        }
        System.out.println("********************************************************");

        System.out.println("BCNF Relations after decomposition: ");

        for (Relation rel:finalRelations) {
            System.out.println(rel.toString());
        }
        System.out.println("********************************************************");

    }

    private static void setUpExample() {
        relation = Relation.create("ABCDE");
        finalRelations = new HashSet<>();
        fdList= new HashSet<>();
        FD fd1 = FD.create("A", "BC");
        FD fd2 = FD.create("C", "D");
        fdList.add(fd1);
        fdList.add(fd2);
    }


    public static Set<FD> returnBadFDs(Relation relation, Set<FD> fds) {
        Set<FD> badFDs = new HashSet<>();
        Set<FD> allFDs = new HashSet<>();
        allFDs.addAll(fds);

        for (FD subFD:fds) {
            Set<String> fdAttributes = new HashSet<>();
            fdAttributes.addAll(subFD.getLhs());
            fdAttributes.addAll(subFD.getRhs());
            if (!(relation.getCompleteRelation().containsAll(fdAttributes))) {
                allFDs.remove(subFD);
            }
        }

        for (FD subFD:allFDs) {
            if(!(returnClosure(relation, allFDs, subFD).equals(relation.getCompleteRelation()))) {
                badFDs.add(subFD);
            }
        }
        return badFDs;
    }

    public static Set<String> returnClosure(Relation relation, Set<FD> fdList, FD fd) {
        Set<String> closure = new HashSet<>();
        closure.addAll(fd.getLhs());
        closure.addAll(fd.getRhs());
        Set<String> initialClosure = new HashSet<>();

        do{
            initialClosure.addAll(closure);
            for (FD subFd:fdList) {
                if(closure.containsAll(subFd.getLhs())){
                    closure.addAll(subFd.getRhs());
                }
            }
        }while(!closure.equals(initialClosure));

        return closure;
    }
    

    public static FD getFirstBadFD(Set<FD> fdList) {
        for(FD aSiteId: fdList) {
           return aSiteId;
        }
        return null;
    }


    public static void getBCNFRelations(Relation relation) {
        Set<Relation> bcnfRelations = new HashSet<>();

            if(!(returnBadFDs(relation, fdList).isEmpty())) {
                FD badFD = new FD(getFirstBadFD(returnBadFDs(relation, fdList)).getLhs(),
                        getFirstBadFD(returnBadFDs(relation, fdList)).getRhs());

                Set<String> attributesForDR1 = new HashSet<>();
                attributesForDR1.addAll(returnClosure(relation, returnBadFDs(relation, fdList), badFD));

                Set<String> attributesForDR2 = new HashSet<>();
                attributesForDR2.addAll(relation.getCompleteRelation());
                attributesForDR2.removeAll(returnClosure(relation, returnBadFDs(relation, fdList), badFD));
                attributesForDR2.addAll(badFD.getLhs());

                Relation decomposedRelation1 = new Relation(attributesForDR1);
                getBCNFRelations(decomposedRelation1);
                Relation decomposedRelation2 = new Relation(attributesForDR2);
                getBCNFRelations(decomposedRelation2);
            } else {finalRelations.add(relation); }
    }
    
}
