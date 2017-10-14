package in.ac.iitk.cse.cs252.transactions;

import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.Transaction;
import org.bitcoinj.core.Utils;
import org.bitcoinj.script.Script;
import org.bitcoinj.script.ScriptBuilder;

import java.io.File;
import java.math.BigInteger;
import java.net.UnknownHostException;

import static org.bitcoinj.script.ScriptOpCodes.*;

/**
 * Created by bbuenz on 24.09.15.
 */
public class LinearEquationTransaction extends ScriptTransaction {
    // TODO: Problem 2
    public LinearEquationTransaction(NetworkParameters parameters, File file, String password) {
        super(parameters, file, password);
    }

    @Override
    public Script createInputScript() {
        // TODO: Create a script that can be spend by two numbers x and y such that x+y=first 4 digits of your iitk roll and |x-y|=last 4 digits of your suid (perhaps +1)
        ScriptBuilder builder = new ScriptBuilder();
        String firstHalf = "150", secondHalf="036";
        builder.op(OP_2DUP);
        builder.op(OP_ADD);
        builder.data(encode(new BigInteger(firstHalf)));
        builder.op(OP_EQUALVERIFY);
        builder.op(OP_SUB);
        builder.op(OP_ABS);
        builder.data(encode(new BigInteger(secondHalf)));
        builder.op(OP_EQUAL);
        return builder.build();

        // null;
    }

    @Override
    public Script createRedemptionScript(Transaction unsignedScript) {
        // TODO: Create a spending script

        ScriptBuilder builder = new ScriptBuilder();
        String x= "93", y = "57";

        builder.data(encode(new BigInteger(y)));
        builder.data(encode(new BigInteger(x)));
        return builder.build();
        //return null;
    }

    private byte[] encode(BigInteger bigInteger) {
        return Utils.reverseBytes(Utils.encodeMPI(bigInteger, false));
    }
}
