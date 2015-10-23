package dp.java.leetcode;

/**
    Input: n integers (range 0...k) A1...An
    Goal: partition into subsets S1 & S2
          Minimize |Sum(S1) - Sum(S2)|

    P(i, j) = $$ 1 if some subset of {A1...Ai} has a sum of j
              $$ 0 otherwise
            where i in (1..n), j in (0..nk)
    Function:
    P(i, j) = $$ 1 if P(i-1, j) = 1 or P(i-1, j-Ai) = 1
    ==> General form P(i, j) = max{P(i-1, j), P(i-1, j-Ai)}


    Min { S-i : p(n, i)=1} for i <= S
 */
public class BalancedPartition {


}
