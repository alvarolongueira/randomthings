# randomthings -> recursivedictionary
This is a technical test from a hiring process.
It was complex because it needed to be fast to pass the tests.


Strong points: 
* Recursion
* Algorithmics
* Maps to split information and faster to find 


## Instructions
Given an array of words representing a dictionary, test each word to see if it can be made into another
word in the dictionary when characters are removed one at a time. Each word represents its own first
element of its string chain, so start with a string chain length of 1. Each time a character is removed,
increment the string chain by 1. In order to remove a character, the resulting word must be in the original
dictionary. Determine the longest string chain achievable for a given dictionary.

#### **Example**
n = 4

words = ['a', 'and', 'an', 'bear']

The word 'and' could be reduced to 'an' and then to 'a'. The single character a cannot be reduced any
further because there is not a null string in the dictionary. The word 'bear' cannot be reduced at all. The
longest string chain has a length of 3.


**Function Description**

Complete the function longestChain in the editor below.

* longestChain has the following parameter(s):
* int n: length of words
* str words[n]: dictionary of strings to test

**Returns**

* int: the length of the longest string chain

**Constraints**
* 1 <= n <= 50000
* 1 <= |words[i]| <= 60, where 0 <= i < n
* Each words[i] is composed of lowercase letters in the range ascii[a-z].

#### **Sample case**
**Sample Input**
* 6 -> words[] size n = 6
* a -> arr = ['a', 'b', 'ba', 'bca', 'bda', 'bdca']
* b
* ba
* bca
* bda
* bdca

**Sample Output**
* 4

**Explanation**
Sample Case 1: words = {"a", "b", "ba", "bca", "bda", "bdca"}

Because "a" and "b" are single-character words, they cannot be reduced. That would result in the empty
string, so the length for both of these string chains is 1.

The word "ba" can create two different string chains each with a length of 2: ("ba" -> "a" and "ba" ->
"b"). This means the current longest string chain is 2.

The word "bca" can create two different string chains of length 3: ("bca" -> "ba" -> "a" and "bca" -> "ba"
3/8
-> "b"). This means the current longest string chain is now 3.

The word "bda" can create two different string chains of length 3: ("bda" -> "ba" -> "a" and "bda" -> "ba"
-> "b"). At this point, the current longest string chain is still 3.

The word "bdca" can create four different string chains of length: 4 ("bdca" -> "bda" -> "ba" -> "a",
"bdca" -> "bda" -> "ba" -> "b", "bdca" -> "bca" -> "ba" -> "a", and "bdca" -> "bca" -> "ba" -> "b"). The
current longest string chain is now 4.

The longest string chain is 4.

## Author
* **Alvaro Longueira** - [alvarolongueira](https://github.com/alvarolongueira)


