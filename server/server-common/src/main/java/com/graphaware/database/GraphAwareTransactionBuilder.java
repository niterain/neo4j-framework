package com.graphaware.database;

import com.graphaware.tx.manager.TransactionManager;
import com.graphaware.tx.manager.TransactionManagerImpl;
import org.neo4j.graphdb.Transaction;
import org.neo4j.kernel.TransactionBuilder;
import org.neo4j.kernel.impl.transaction.xaframework.ForceMode;

/**
 *
 */
public class GraphAwareTransactionBuilder implements TransactionBuilder {

    private final GraphAwareEmbeddedGraphDatabase db;
    private final ForceMode forceMode;
    private final TransactionManager transactionManager;

    GraphAwareTransactionBuilder(GraphAwareEmbeddedGraphDatabase db, ForceMode forceMode) {
        this.db = db;
        this.forceMode = forceMode;
        this.transactionManager = new TransactionManagerImpl(db);
    }

    @Override
    public Transaction begin() {
        Transaction tx = this.db.beginTx(forceMode);
        return transactionManager.begunTx(tx);
    }

    @Override
    public TransactionBuilder unforced() {
        return new GraphAwareTransactionBuilder(db, ForceMode.unforced);
    }

    public TransactionManager getTransactionManager() {
        return transactionManager;
    }
}
