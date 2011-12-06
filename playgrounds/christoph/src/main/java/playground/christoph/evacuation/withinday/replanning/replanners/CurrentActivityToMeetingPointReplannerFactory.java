/* *********************************************************************** *
 * project: org.matsim.*
 * CurrentActivityToMeetingPointReplanner.java
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 * copyright       : (C) 2011 by the members listed in the COPYING,        *
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

package playground.christoph.evacuation.withinday.replanning.replanners;

import org.matsim.api.core.v01.Scenario;
import org.matsim.core.replanning.modules.AbstractMultithreadedModule;
import org.matsim.withinday.replanning.replanners.interfaces.WithinDayDuringActivityReplanner;
import org.matsim.withinday.replanning.replanners.interfaces.WithinDayDuringActivityReplannerFactory;

import playground.christoph.evacuation.withinday.replanning.utils.ModeAvailabilityChecker;
import playground.christoph.evacuation.withinday.replanning.utils.HouseholdsUtils;

public class CurrentActivityToMeetingPointReplannerFactory extends WithinDayDuringActivityReplannerFactory {

	private final Scenario scenario;
	private final HouseholdsUtils householdsUtils; 
	private final ModeAvailabilityChecker modeAvailabilityChecker;
	
	public CurrentActivityToMeetingPointReplannerFactory(Scenario scenario, 
			AbstractMultithreadedModule abstractMultithreadedModule, double replanningProbability, 
			HouseholdsUtils householdsUtils, ModeAvailabilityChecker modeAvailabilityChecker) {
		super(abstractMultithreadedModule, replanningProbability);
		this.scenario = scenario;
		this.householdsUtils = householdsUtils;
		this.modeAvailabilityChecker = modeAvailabilityChecker;
	}

	@Override
	public WithinDayDuringActivityReplanner createReplanner() {
		WithinDayDuringActivityReplanner replanner = new CurrentActivityToMeetingPointReplanner(super.getId(), scenario, 
				householdsUtils, modeAvailabilityChecker);
		super.initNewInstance(replanner);
		return replanner;
	}

}