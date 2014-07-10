/*
 * Copyright (c) 2013 GraphAware
 *
 * This file is part of GraphAware.
 *
 * GraphAware is free software: you can redistribute it and/or modify it under the terms of
 * the GNU General Public License as published by the Free Software Foundation, either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 *  without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details. You should have received a copy of
 * the GNU General Public License along with this program.  If not, see
 * <http://www.gnu.org/licenses/>.
 */

package com.graphaware.server;

import com.graphaware.database.GraphAwareEmbeddedGraphDatabase;
import com.graphaware.server.web.GraphAwareJetty9WebServer;
import org.neo4j.kernel.EmbeddedGraphDatabase;
import org.neo4j.kernel.GraphDatabaseAPI;
import org.neo4j.kernel.InternalAbstractGraphDatabase;
import org.neo4j.kernel.logging.Logging;
import org.neo4j.server.CommunityNeoServer;
import org.neo4j.server.configuration.Configurator;
import org.neo4j.server.database.Database;
import org.neo4j.server.database.LifecycleManagingDatabase;
import org.neo4j.server.web.WebServer;

import java.util.Map;

import static org.neo4j.server.database.LifecycleManagingDatabase.*;

/**
 *  {@link org.neo4j.server.CommunityNeoServer} that uses {@link com.graphaware.server.web.GraphAwareJetty9WebServer}.
 */
public class GraphAwareCommunityNeoServer extends CommunityNeoServer {

    public static final GraphFactory GA_EMBEDDED = new GraphFactory()
    {
        @Override
        public GraphDatabaseAPI newGraphDatabase( String storeDir, Map<String,String> params, InternalAbstractGraphDatabase.Dependencies dependencies )
        {
            return new GraphAwareEmbeddedGraphDatabase( storeDir, params, dependencies );
        }
    };

    public GraphAwareCommunityNeoServer(Configurator configurator, Logging logging) {
        super(configurator, lifecycleManagingDatabase(GA_EMBEDDED) ,logging);
    }

    public GraphAwareCommunityNeoServer(Configurator configurator, Database.Factory dbFactory, Logging logging) {
        super(configurator, dbFactory, logging);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected WebServer createWebServer() {
        return new GraphAwareJetty9WebServer(logging);
    }
}
