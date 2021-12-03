# randomthings -> recursivedictionary
This is a technical test from a hiring process.
It was complex because the algorithmics



## Instructions
The owner of a construction company has a surplus of rods of arbitrary lengths. A local contractor offers to
buy any of the surplus, as long as all the rods have the same exact integer length, referred to as
saleLength. The number of sellable rods can be increased by cutting each rod zero or more times, but
each cut has a cost denoted by costPerCut. After all cuts have been made, any leftover rods having a
length other than saleLength must be discarded for no profit. The owner's total profit for the sale is
calculated as:

_totalProfit = totalUniformRods x saleLength x salePrice - totalCuts x costPerCut_

where totalUniformRods is the number of sellable rods, salePrice is the per unit length price that the
contractor agrees to pay, and totalCuts is the total number of times the rods needed to be cut.

#### **Example**
* lengths = [30, 59, 110]
* costPerCut = 1
* salePrice = 10 per unit length

The following are tests based on lengths that are factors of 30, the length of the shortest bar. Factors of
other lengths might also be tested, but this demonstrates the methodology.

Working through the first stanza, length = 30, it is the same length as the first rod, so no cuts are required
and there is 1 piece. For the second rod, cut and discard the excess 29 unit rod. No more cuts are
necessary and another 1 piece is left to sell. Cut 20 units off the 110 unit rod to discard leaving 90 units,
then make two more cuts to have 3 more pieces to sell. Finally sell 5 totalUniformRods , saleLength = 30
at salePrice = 10 per unit length for 1500. The cost to produce was totalCuts = 4 times costPerCut = 1 per
cut, or 4. Total revenue 1500 4 1496. The maximum revenue among these tests is obtained at length 5
for 1913.

**Function Description**
Complete the function maxProfit in the editor below.

maxProfit has the following parameter(s):
* costPerCut: cost to make a cut
* salePrice: per unit length sales price
* lengths[n]: integer rod lengths


**Returns**
* int: maximum possible profit

**Constraints**
* 1 <= n <= 50
* 1 <= lengths[i] <= 10
* 1 <= salePrice, costPerCut <= 1000

#### **Sample case 1 **
**Sample Input**
STDIN Function
* 1 -> costPerCut = 1
* 10 -> salePrice = 10
* 3 -> lengths[] size n = 3
* 26 -> lengths = [26, 103, 59]
* 103
* 59

**Sample Output**
* 1770

**Explanation**
Since costPerCut = 1 is very inexpensive, a large number of cuts can be made to reduce the number of
wasted pieces. The optimal rod length for maximizing profit is 6, and the rods are cut as shown:

* lengths[0] = 26 : Cut off a piece of length 2 and discard it, resulting in a rod of length 24.
Then, cut this rod into 4 pieces of length 6.

* lengths[1] = 103 : Cut off a piece of length 1 and discard it, resulting in a rod of length 102.
Then, cut this rod into 17 pieces of length 6.

* lengths[2] = 59 : Cut off a piece of length 5 and discard it, resulting in a rod of length 54.
Then, cut this rod into 9 pieces of length 6.

After performing totalCuts = (1 + 3) + (1 + 16) + (1 + 8) = 30 cuts, there are totalUniformRods = 4 + 17 +
9 = 30 pieces of length saleLength = 6 that can be sold at salePrice = 10. This yields a total profit of
salePrice x totalUniformRods x saleLength - totalCuts x costPerCut = 10 x 30 x 6 - 30 x 1 = 1770

#### **Sample case 2 **
**Sample Input**
STDIN Function
* 100 -> costPerCut = 100
* 10 -> salePrice = 10
* 3 -> lengths[] size n = 3
* 26 -> lengths = [26, 103, 59]
* 103
* 59

**Sample Output**
* 1230

**Explanation**
Since costPerCut = 100, cuts are expensive and must be minimal. The optimal rod length for
maximizing profit is 51, and the rods are cut as shown:

* lengths[0] = 26 : Discard this rod entirely.

* lengths[1] = 103 : Cut off a piece of length 1 and discard it, resulting in a rod of length 102.
Then, cut this rod into 2 pieces of length 51.

* lengths[2] = 59 : Cut off a piece of length 8 and discard it, resulting in a rod of length 51.

After performing totalCuts = (0) + (1 + 1) + (1) = 3 cuts, there are totalUniformRods = 0 + 2 + 1 = 3
pieces of length saleLength = 51 that can be sold at salePrice = 10 each. This yields a total profit of
salePrice x totalUniformRods x saleLength - totalCuts x costPerCut = 10 x 3 x 51 - 3 x 100 = 1230.

## Author
* **Alvaro Longueira** - [alvarolongueira](https://github.com/alvarolongueira)


