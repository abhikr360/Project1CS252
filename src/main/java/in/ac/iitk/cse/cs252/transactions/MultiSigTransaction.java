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
        ScriptBuilder builder = new ScriptBuilder();
        builder.op(OP_1);
        builder.op(OP_SWAP);
        builder.op(OP_1);
        builder.data((customerKeys.get(0)).getPubKey());
        builder.data((customerKeys.get(1)).getPubKey());
        builder.data((customerKeys.get(2)).getPubKey());
        builder.op(OP_3);
        builder.op(OP_CHECKMULTISIGVERIFY);
        builder.data(bankKey.getPubKey());
        builder.op(OP_CHECKSIG);
        return builder.build();

        //return null;
    }

    @Override
    public Script createRedemptionScript(Transaction unsignedTransaction) {
        // Please be aware of the CHECK_MULTISIG bug!
        // TODO: Create a spending script
        TransactionSignature txSigBank = sign(unsignedTransaction, bankKey);
        randomGenerator = new Random();//To pick a random Customer
        int idx = randomGenerator.nextInt(customerKeys.size());
        DeterministicKey randCust = customerKeys.get(idx);
        TransactionSignature txSigCustomer = sign(unsignedTransaction, randCust);
        TransactionSignature txSigAdversary = sign(unsignedTransaction, adversaryKey );
        ScriptBuilder builder = new ScriptBuilder();
        builder.data(txSigBank.encodeToBitcoin());
        builder.data(txSigCustomer.encodeToBitcoin());
        //UNCOMMENT THIS TO FAIL TRANSACTION
        /*
        builder.data(txSigAdversary.encodeToBitcoin());
         */
        return builder.build();
        //return null;
    }
}
