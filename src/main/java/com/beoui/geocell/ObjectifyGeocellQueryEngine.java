package com.beoui.geocell;

import java.util.List;
import java.util.StringTokenizer;

import com.beoui.geocell.model.GeocellQuery;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.Query;

/**
 * Objectify implementation for GeocellQueryEngine.
 * <p>
 * Example:
 * <pre>
 * // paramList contains values for each filter() condition
 * List&lt;Object&gt; paramList = new ArrayList&lt;Object&gt;();
 * paramList.add(q);
 * paramList.add(q + "\uFFFD");
 *
 * // comma separated filter() conditions
 * GeocellQuery baseQuery = new GeocellQuery("title &gt;=, title
&lt;", paramList);
 * GeocellQueryEngine queryEngine = new
ObjectifyGeocellQueryEngine(ofy());
 *
 * // perform search
 * GeocellManager.proximitySearch(
 *      new Point(lat, lng), maxResults, maxDistance,
 *      MyEntity.class,
 *      baseQuery, queryEngine,
GeocellManager.MAX_GEOCELL_RESOLUTION);
 * </pre>
 */
public class ObjectifyGeocellQueryEngine implements GeocellQueryEngine
{
    private String geocellsProperty;
    private Objectify ofy;

    public static final String DEFAULT_GEOCELLS_PROPERTY = "cells";

    public ObjectifyGeocellQueryEngine(Objectify ofy) {
        this(ofy, DEFAULT_GEOCELLS_PROPERTY);
    }

    public ObjectifyGeocellQueryEngine(Objectify ofy, String
geocellsProperty) {
        this.ofy = ofy;
        this.geocellsProperty = geocellsProperty;
    }

    @Override
    public <T> List<T> query(GeocellQuery baseQuery, List<String>
geocells, Class<T> entityClass) {
        StringTokenizer st;
        int tokenNo = 0;
        System.out.println(geocells);
        Query<T> query = ofy.query(entityClass);

        if (baseQuery != null) {
            st = new StringTokenizer(baseQuery.getBaseQuery(), ",");
            while (st.hasMoreTokens()) {
                query.filter(st.nextToken(),
baseQuery.getParameters().get(tokenNo++));
            }
        }

        return query.filter(geocellsProperty + " IN",
geocells).list();
    }
}