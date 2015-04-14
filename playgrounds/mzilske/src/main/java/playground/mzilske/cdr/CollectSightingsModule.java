/*
 *  *********************************************************************** *
 *  * project: org.matsim.*
 *  * CDRModules.java
 *  *                                                                         *
 *  * *********************************************************************** *
 *  *                                                                         *
 *  * copyright       : (C) 2014 by the members listed in the COPYING, *
 *  *                   LICENSE and WARRANTY file.                            *
 *  * email           : info at matsim dot org                                *
 *  *                                                                         *
 *  * *********************************************************************** *
 *  *                                                                         *
 *  *   This program is free software; you can redistribute it and/or modify  *
 *  *   it under the terms of the GNU General Public License as published by  *
 *  *   the Free Software Foundation; either version 2 of the License, or     *
 *  *   (at your option) any later version.                                   *
 *  *   See also COPYING, LICENSE and WARRANTY file                           *
 *  *                                                                         *
 *  * ***********************************************************************
 */

package playground.mzilske.cdr;

import com.google.inject.Inject;
import org.matsim.api.core.v01.Scenario;
import org.matsim.core.controler.AbstractModule;

import javax.inject.Provider;

public class CollectSightingsModule extends AbstractModule {
    @Override
    public void install() {
        bindTo(Sightings.class, SightingsImpl.class);
        bindAsSingleton(CallProcess.class);
        bindAsSingleton(ZoneTracker.class);
        bindToProviderAsSingleton(CallProcessTicker.class, CallProcessTickerProvider.class);
        addEventHandler(CallProcessTicker.class);
        addControlerListenerBinding().to(CallControlerListener.class);
    }

    private static class CallProcessTickerProvider implements Provider<CallProcessTicker> {

        @Inject
        Scenario scenario;

        @Inject
        Sightings sightings;

        @Inject
        CallProcess callProcess;

        @Inject
        ZoneTracker zoneTracker;

        @Override
        public CallProcessTicker get() {
            CallProcessTicker ticker = new CallProcessTicker();
            ticker.addHandler(zoneTracker);
            ticker.addHandler(callProcess);
            ticker.addSteppable(callProcess);
            return ticker;
        }
    }
}
