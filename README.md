# BCNF_Decomposition
A java program that decomposes any relation with any set of given functional dependencies, and provides the final set of decompositions.

I have also created a jar that you can run from the command line (In windows, I can run it using 'java -jar BCNFDecomposition.jar'). It asks for user input for relations and existing functional dependencies and displays the decomposed output.

I tested it with the example you gave us : R(A,B,C,D,E),    F :{A->BC,C->D}                [Answer: (ABC)(AE)(CD)]

and another example I found here :             R = ABCDE,     F = {A -> B, C -> DE}         [Answer: (AB)(AC)(CDE)]

The output looks like this:

![alt text](http://abhinaav.com/bcnfdecomposition)
