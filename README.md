# BCNF_Decomposition
A java program that decomposes any relation with any set of given functional dependencies, and provides the final set of decompositions.

I have also created a jar that you can run from the command line (In windows, I can run it using 'java -jar BCNFDecomposition.jar'). It asks for user input for relations and existing functional dependencies and displays the decomposed output.

I tested it with the example you gave us : R(A,B,C,D,E),    F :{A->BC,C->D}                [Answer: (ABC)(AE)(CD)]

and another example I found here :             R = ABCDE,     F = {A -> B, C -> DE}         [Answer: (AB)(AC)(CDE)]

The output looks like this:

![alt text](https://static.wixstatic.com/media/69ceb2_dd21372a756d44a59b35df244e3a8293~mv2.png/v1/fill/w_1157,h_379,al_c,lg_1,q_90/unnamed.webp)
