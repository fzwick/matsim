/* *********************************************************************** *
 * project: org.matsim.*
 * LinkSensorManagerTest.java
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

package org.matsim.core.trafficmonitoring;

import org.matsim.core.basic.v01.IdImpl;
import org.matsim.core.config.Config;
import org.matsim.core.controler.Controler;
import org.matsim.core.controler.events.IterationEndsEvent;
import org.matsim.core.controler.events.IterationStartsEvent;
import org.matsim.core.controler.listener.IterationEndsListener;
import org.matsim.core.controler.listener.IterationStartsListener;
import org.matsim.testcases.MatsimTestCase;

/**
 * @author dgrether
 */
public class LinkSensorManagerTest extends MatsimTestCase implements IterationStartsListener, IterationEndsListener {

	private LinkSensorManager manager = null;
	private Config config = null;

	/**
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		this.config = loadConfig(getInputDirectory() + "config.xml");
	}

	@Override
	protected void tearDown() throws Exception {
		this.config = null;
		this.manager = null;
		super.tearDown();
	}

	public void testSensorManagement() {
		Controler controler = new Controler(this.config);
		controler.setCreateGraphs(false);
		controler.setWriteEventsInterval(0);
		controler.addControlerListener(this);
		this.manager = new LinkSensorManager();
		this.manager.addLinkSensor(new IdImpl("1"));
		controler.run();
	}

	public void notifyIterationEnds(final IterationEndsEvent event) {
		if (event.getIteration() > 0)  {
			assertEquals(100, this.manager.getLinkTraffic(new IdImpl("1")));
		}
	}

	public void notifyIterationStarts(final IterationStartsEvent event) {
		if (event.getIteration() == 1) {
			event.getControler().getEvents().addHandler(this.manager);
		}
	}

}
