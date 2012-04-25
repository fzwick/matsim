/* *********************************************************************** *
 * project: org.matsim.*
 * Plansgenerator.java
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 * copyright       : (C) 2007 by the members listed in the COPYING,        *
 *                   LICENSE and WARRANTY file.                            *
 * email           : info at matsim dot org                                *
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 2 of the License, or     *
 *   (at your option) any later version.                                   *
 *   See also COPYING, LICENSE and WARRANTY file                           *
 *                                                                         *
 * *********************************************************************** */
package playground.andreas.P2.replanning.modules;


import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;
import org.matsim.core.gbl.MatsimRandom;
import org.matsim.testcases.MatsimTestUtils;

import playground.andreas.P2.PScenarioHelper;
import playground.andreas.P2.pbox.Cooperative;
import playground.andreas.P2.plan.PPlan;
import playground.andreas.P2.replanning.PPlanStrategy;

/**
 * @author droeder
 *
 */
public class ConvexHullRouteExtensionTest {

	@Test
    public final void testRun() {
		MatsimRandom.reset();
		Cooperative coop = PScenarioHelper.createCoop2111to1314to4443();
		PPlan oldPlan = coop.getBestPlan();
		
		ArrayList<String> parameters = new ArrayList<String>();
		PPlanStrategy strategy = new ConvexHullRouteExtension(parameters);
		coop.init(coop.getRouteProvider(), strategy, 1);
		PPlan newPlan = coop.getBestPlan();
		
		Assert.assertEquals(3, oldPlan.getStopsToBeServed().size(), MatsimTestUtils.EPSILON);
		Assert.assertEquals("p_2111", oldPlan.getStopsToBeServed().get(0).getId().toString());
		Assert.assertEquals("p_1314", oldPlan.getStopsToBeServed().get(1).getId().toString());
		Assert.assertEquals("p_4443", oldPlan.getStopsToBeServed().get(2).getId().toString());
		
		Assert.assertEquals(4, newPlan.getStopsToBeServed().size(), MatsimTestUtils.EPSILON);
		Assert.assertEquals("p_2111", newPlan.getStopsToBeServed().get(0).getId().toString());
		Assert.assertEquals("p_1314", newPlan.getStopsToBeServed().get(1).getId().toString());
		Assert.assertEquals("p_4443", newPlan.getStopsToBeServed().get(2).getId().toString());
		Assert.assertEquals("p_1222", newPlan.getStopsToBeServed().get(3).getId().toString());
	}

}
