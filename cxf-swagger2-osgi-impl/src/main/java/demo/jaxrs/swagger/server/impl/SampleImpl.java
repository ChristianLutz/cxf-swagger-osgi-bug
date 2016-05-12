/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package demo.jaxrs.swagger.server.impl;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import demo.jaxrs.swagger.server.Item;
import demo.jaxrs.swagger.server.Sample;



public class SampleImpl implements Sample {
    private Map<String, Item> items;

    public SampleImpl() {
        items = Collections.synchronizedMap(new TreeMap<String, Item>(String.CASE_INSENSITIVE_ORDER));
        items.put("Item 1", new Item("Item 1", "Value 1"));
        items.put("Item 2", new Item("Item 2", "Value 2"));
    }
    
    public Response getItems(int page) {
        return Response.ok(items.values()).build();
    }
    
    public Item getItem(final String language, String name) {
        return items.get(name);
    }
    
    public Response createItem(final UriInfo uriInfo, final Item item) {
        items.put(item.getName(), item);
        return Response
            .created(uriInfo.getBaseUriBuilder().path(item.getName()).build())
            .entity(item).build();
    }
    
    public Item updateItem(String name, String value) {
        Item item = new Item(name, value);
        items.put(name,  item);
        return item;
    }
    
    public Response delete(String name) {
        items.remove(name);
        return Response.ok().build();
    }
}
