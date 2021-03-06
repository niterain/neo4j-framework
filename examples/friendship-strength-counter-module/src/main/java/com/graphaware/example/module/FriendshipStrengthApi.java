/*
 * Copyright (c) 2015 GraphAware
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

package com.graphaware.example.module;

import org.neo4j.graphdb.GraphDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *  Simple REST API for finding the total friendship strength.
 */
@Controller
@RequestMapping("/friendship/strength")
public class FriendshipStrengthApi {

    private final FriendshipStrengthCounter counter;

    @Autowired
    public FriendshipStrengthApi(GraphDatabaseService database) {
        counter = new FriendshipStrengthCounter(database);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    @Transactional
    public long getTotalFriendshipStrength() {
        return counter.getTotalFriendshipStrength();
    }
}
