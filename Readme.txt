Introduction
==============================================

In this home-assignment we’ll create several transactions and post them to the Bitcoin blockchain. We will use bitcoinj	API    and    the    starter    code. 

MultiSig Transaction.
=========================
Unlocking script pushes OP_1, one or more customer sig (may be valid/Invalid), Bank Sig.
This accepts all inputs where there is one sig of bank at top and one or more valid sig of customers
This will reject all inputs which have one or more invalid sig, or if bank sig is absent, or if only bank sig is present.
     
