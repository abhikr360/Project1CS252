Introduction

In this home-assignment we’ll create several transactions and post them to the Bitcoin blockchain. We will use bitcoinj	API    and    the    starter    code. You can obtain testnet coins for free from http://tpfaucet.appspot.com/.  It is courteous to send the testnet coins back to the faucet after you are done experimenting with them.
6.   You can use the transaction hashes to track your transactions on a block explorer tool such as https://test-insight.bitpay.com/  (testnet) or https://insight.bitpay.com/ (mainnet).



-----------------------------------------------------------------------------------------------------------------------------
Generate an address whose standard Base58Check representation starts with 1 and then at least the first four letters of your surname in lowercase. If your surname is shorter than four letters, please append as many ‘X’ characters as necessary. If it contains an ‘l’ please use ‘L’ instead as the ‘l’ is dropped in Base58Check to avoid confusion with ‘1’. You may generate this address either using bitcoinj or using an external generator. 

Q1 : Done using VanityGen
-----------------------------------------------------------------------------------------------------------------------------
Pattern: 1kuma                                                                 
Address: 1kumamW9to5NmVF9ZziHfdAt59g8HEfD6
Privkey: 5HyJtzdRHjgpGMpoRcgvECnPSwycr5XHdYAMzvzc8oL1MuHSYFY

-----------------------------------------------------------------------------------------------------------------------------
Initial Address : n3w8UdeZtNqBJxzSrkQtZunPzLHBiXCFnq
Transaction Id to receive money : 64beb4322bc00777f0f5e87acfe958bcd3c071b35d4c5eab4cbc9725f5cf083d
Final Address : n3cGgKedzBnWyn2qMks5S5E9o4uBiDUsif
Balance  : 130000000

-----------------------------------------------------------------------------------------------------------------------------
Sidebar : Pay to Pub Key
------------------------------------------------------------------------------------------------------------------------------

Transaction Id 1 for PayToPubKey : 320eeb77778a2e34f3712ba6106490e76d8eef0e19343bdea44c519be5c59985
Transaction Id 2 for PayToPubKey : 66e8ede531c0b31982174bbc3d3275b9b137baea9a39b2a492d767484940e3a2

-----------------------------------------------------------------------------------------------------------------------------
Generate a transaction that can be redeemed by the solution (x,y) to the following system of two linear equations:
x+y = (first half of your iitk roll) 	and 	x-y = (second half or your iitk roll)
[to ensure that an integer solution exists, please change the last digit of the two numbers on the right hand side so the numbers are both even or both odd].

The redemption script should be as small as possible. That is, a valid script sig should consist of simply pushing two integers x and y to the stack.  Make sure you use OP_ADD and OP_SUB in your script.

Q2 : Linear Transaction. Roll used = 150036
-----------------------------------------------------------------------------------------------------------------------------
Transaction Id 1 for TestLinearEquation : b79bbc2e589b1d129c3a6220a6828fee128ef2eaad536999eccd662b1a3371c0
Transaction Id 2 for TestLinearEquation : b148932584a852cecc8efad3d497087f515857310283741f65191231b9e82d65

-----------------------------------------------------------------------------------------------------------------------------

Generate  a  multi-sig  transaction  involving  four  parties  such  that  the  transaction  can be redeemed by the first party (bank) combined with any one of the 3 others (customers) but not by only the customers or only the bank. Create and redeem the transaction and make sure that the script  is as small  as possible.  You can use any legal combination of signatures to redeem the transaction but make sure that all combinations would have worked.


Q3 : MultiSig Transaction.
-----------------------------------------------------------------------------------------------------------------------------
Unlocking script pushes OP_1, one or more customer sig (may be valid/Invalid), Bank Sig.
This accepts all inputs where there is one sig of bank at top and one or more valid sig of customers
This will reject all inputs which have one or more invalid sig, or if bank sig is absent, or if only bank sig is present.
     
Transaction Id 1 for TestMultiSig : 14bb87b35da4276d617db0528449c82c28b51424faf536f826ea3f1f04ca612a
Transaction Id 2 for TestMultiSig : 80bb1d6e660dd59548246998800d1e25aa7d14a088d3677e99e71005810fb17a

----------------------------------------------------- THE END :) -------------------------------------------------------------
