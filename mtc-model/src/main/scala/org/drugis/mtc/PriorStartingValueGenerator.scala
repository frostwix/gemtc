/*
 * This file is part of drugis.org MTC.
 * MTC is distributed from http://drugis.org/mtc.
 * Copyright (C) 2009-2011 Gert van Valkenhoef.
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

package org.drugis.mtc

class PriorStartingValueGenerator[M <: Measurement, P <: Parametrization[M]](
model: NetworkModel[M, P])
extends StartingValueGenerator[M] {
	override def getBaselineEffect(study: Study[M]) = 0.0
	override def getRandomEffect(study: Study[M], p: BasicParameter) = 0.0
	override def getRelativeEffect(p: BasicParameter) = 0.0
	override def getRandomEffectsVariance() = model.variancePrior / 2
}