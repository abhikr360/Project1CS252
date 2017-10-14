package in.ac.iitk.cse.cs252.transactions;

import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.Transaction;
import org.bitcoinj.core.Utils;
import org.bitcoinj.crypto.DeterministicKey;
import org.bitcoinj.crypto.TransactionSignature;
import org.bitcoinj.script.Script;
import org.bitcoinj.script.ScriptBuilder;

import java.io.File;
import java.math.BigInteger;
import java.util.*;

import static org.bitcoinj.script.ScriptOpCodes.*;

/**
 * Created by bbuenz on 24.09.15.
 */
public class MultiSigTransaction extends ScriptTransaction {
    // TODO: Problem 3

    private DeterministicKey bankKey,adversaryKey;
    private List<DeterministicKey> customerKeys = new ArrayList<DeterministicKey>();
    private Random randomGenerator;
    public MultiSigTransaction(NetworkParameters parameters, File file, String password) {
        super(parameters, file, password);
        bankKey = getWallet().freshReceiveKey();
        customerKeys.add(getWallet().freshReceiveKey());
        customerKeys.add(getWallet().freshReceiveKey());
        customerKeys.add(getWallet().freshReceiveKey());
        adversaryKey = getWallet().freshReceiveKey();

    }

    @Override
    public Script createInputScript() {
        // TODO: Create a script that can be spend using signatures from the bank and one of the customers
//        ScriptBuilder builder = new ScriptBuilder();
//        builder.op(OP_1);
//        builder.op(OP_SWAP);
//        builder.op(OP_1);
//        builder.data((customerKeys.get(0)).getPubKey());
//        builder.data((customerKeys.get(1)).getPubKey());
//        builder.data((customerKeys.get(2)).getPubKey());
//        builder.op(OP_3);
//        builder.op(OP_CHECKMULTISIGVERIFY);
//        builder.data(bankKey.getPubKey());
//        builder.op(OP_CHECKSIG);
//        return builder.build();
        //--------------------------------------------------------------------------
        //This accepts all inputs where there is one sig of bank at top and one or more valid sig of customers
        //This will reject all inputs which have one or more invalid sig, or if bank sig is absent, or if only bank sig is present.
        ScriptBuilder builder = new ScriptBuilder();
        builder.data(bankKey.getPubKey());
        builder.op(OP_CHECKSIGVERIFY);
        builder.op(OP_DEPTH);
        builder.op(OP_1);
        builder.op(OP_GREATERTHAN);
        builder.op(OP_VERIFY);
        builder.op(OP_DEPTH);
        builder.op(OP_1);
        builder.op(OP_SUB);
        for (int i = 0; i < 3; i++) {
            builder.data((customerKeys.get(0)).getPubKey());
            builder.data((customerKeys.get(1)).getPubKey());
            builder.data((customerKeys.get(2)).getPubKey());
        }
        builder.op(OP_9);
        builder.op(OP_CHECKMULTISIG);
        return builder.build();

        //return null;
    }

    @Override
    public Script createRedemptionScript(Transaction unsignedTransaction) {
        // Please be aware of the CHECK_MULTISIG bug!
        // TODO: Create a spending script
//        TransactionSignature txSigBank = sign(unsignedTransaction, bankKey);
//        randomGenerator = new Random();//To pick a random Customer
//        int idx = randomGenerator.nextInt(customerKeys.size());
//        DeterministicKey randCust = customerKeys.get(idx);
//        TransactionSignature txSigCustomer = sign(unsignedTransaction, randCust);
//        TransactionSignature txSigAdversary = sign(unsignedTransaction, adversaryKey );
//        ScriptBuilder builder = new ScriptBuilder();
//        builder.data(txSigBank.encodeToBitcoin());
//        builder.data(txSigCustomer.encodeToBitcoin());
          //Use THIS TO FAIL TRANSACTION
        /*
        builder.data(txSigAdversary.encodeToBitcoin());
         */

        //-----------------------------
        TransactionSignature txSigCustomer = sign(unsignedTransaction, customerKeys.get(1));
        TransactionSignature txSigBank = sign(unsignedTransaction, bankKey);
        ScriptBuilder builder = new ScriptBuilder();
        builder.op(OP_1);
        builder.data(txSigCustomer.encodeToBitcoin());
        builder.data(txSigBank.encodeToBitcoin());
        return builder.build();
        //return null;
    }
}
