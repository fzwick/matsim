/* *********************************************************************** *
 * project: org.matsim.*
 * DgCrossing
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 * copyright       : (C) 2010 by the members listed in the COPYING,        *
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
package playground.dgrether.koehlerstrehlersignal.data;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.matsim.api.core.v01.Id;


/**
 * @author dgrether
 *
 */
public class DgCrossing {

	private static final Logger log = Logger.getLogger(DgCrossing.class);
	
	private Id id;
	private boolean signalized;
	private Map<Id, DgCrossingNode> nodes = new HashMap<Id, DgCrossingNode>();
	private Map<Id, DgStreet> lights = new HashMap<Id, DgStreet>();
	private Map<Id, DgProgram> programs = new HashMap<Id, DgProgram>();

	public DgCrossing(Id id) {
		this.id = id;
	}

	public Id getId() {
		return this.id;
	}

	public void addNode(DgCrossingNode crossingNode) {
		if (this.nodes.containsKey(crossingNode.getId())){
			log.warn("CrossingNode " + crossingNode.getId() +" already exists.");
		}
		this.nodes.put(crossingNode.getId(), crossingNode);
	}
	
	public Map<Id, DgCrossingNode> getNodes(){
		return this.nodes;
	}
	
	public Map<Id, DgStreet> getLights(){
		return this.lights;
	}

	public void addLight(DgStreet light) {
		this.lights.put(light.getId(), light);
	}

	public void setSignalized(boolean signalized) {
		this.signalized = signalized;
	}
	
	public void addProgram(DgProgram p){
		if (this.programs.containsKey(p.getId())){
			log.warn("Program " + p.getId() + " already exists!");
		}
		this.programs.put(p.getId(), p);
	}
	
	public Map<Id, DgProgram> getPrograms(){
		return this.programs;
	}
}
