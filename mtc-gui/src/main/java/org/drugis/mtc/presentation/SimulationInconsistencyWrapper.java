/*
 * This file is part of ADDIS (Aggregate Data Drug Information System).
 * ADDIS is distributed from http://drugis.org/.
 * Copyright (C) 2009 Gert van Valkenhoef, Tommi Tervonen.
 * Copyright (C) 2010 Gert van Valkenhoef, Tommi Tervonen, 
 * Tijs Zwinkels, Maarten Jacobs, Hanno Koeslag, Florin Schimbinschi, 
 * Ahmad Kamal, Daniel Reid.
 * Copyright (C) 2011 Gert van Valkenhoef, Ahmad Kamal, 
 * Daniel Reid, Florin Schimbinschi.
 * Copyright (C) 2012 Gert van Valkenhoef, Daniel Reid, 
 * Joël Kuiper, Wouter Reckman.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.drugis.mtc.presentation;

import java.util.List;
import java.util.Map;

import org.drugis.mtc.InconsistencyModel;
import org.drugis.mtc.Parameter;
import org.drugis.mtc.model.Treatment;

public class SimulationInconsistencyWrapper<TreatmentType> extends AbstractMTCSimulationWrapper<TreatmentType, InconsistencyModel> implements InconsistencyWrapper<TreatmentType> {

	public SimulationInconsistencyWrapper(InconsistencyModel model, Map<TreatmentType, Treatment> treatmentMap) {
		super(model, "Inconsistency Model", treatmentMap);
	}

	@Override
	public List<Parameter> getInconsistencyFactors() {	
		return d_nested.getInconsistencyFactors();
	}

	@Override
	public Parameter getInconsistencyVariance() {
		return d_nested.getInconsistencyVariance();
	}
}