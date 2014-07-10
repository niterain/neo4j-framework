package com.graphaware.database;

import com.graphaware.tx.manager.TransactionManager;
import com.graphaware.tx.manager.TransactionManagerAwareDatabase;
import org.neo4j.graphdb.Transaction;
import org.neo4j.kernel.EmbeddedGraphDatabase;
import org.neo4j.kernel.TransactionBuilder;
import org.neo4j.kernel.impl.transaction.xaframework.ForceMode;

import java.util.Map;

/**
 *
 */
public class GraphAwareEmbeddedGraphDatabase extends EmbeddedGraphDatabase implements TransactionManagerAwareDatabase {

    private final GraphAwareTransactionBuilder transactionBuilder = new GraphAwareTransactionBuilder(this, ForceMode.forced);

    public GraphAwareEmbeddedGraphDatabase(String storeDir, Map<String, String> params, Dependencies dependencies) {
        super(storeDir, params, dependencies);
    }

    @Override
    public TransactionBuilder tx() {
        return transactionBuilder;
    }

    @Override
    protected Transaction beginTx(ForceMode forceMode) {
        return super.beginTx(forceMode);
    }

    public TransactionManager getTransactionManager() {
        return transactionBuilder.getTransactionManager();
    }
}
