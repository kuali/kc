/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
import React from 'react/addons';
import RateStore from '../stores/RateStore';
import RateActions from '../stores/RateActions';
import {RateSelection} from './RateSelection';
import {RateListing} from './RateListing';

export class Rates extends React.Component {
	constructor(props) {
		super(props);
		this.rateStore = RateStore;
		this.state = this.rateStore.getState();
		this.onChange = this.onChange.bind(this);
	}

	componentDidMount() { 
		this.rateStore.listen(this.onChange); 
		RateActions.fetchSupportData();
	}
	componentWillUnmount() { this.rateStore.unlisten(this.onChange); }
	
	render() {
		return (
			<div>
				<h1 style={{padding:'0 30px 0 30px'}}>INSTITUTE RATES for {this.state.selectedUnit.unitName}</h1>
				<hr/>
				{!this.state.editMode && <RateSelection style={{padding:'0 30px 0 30px'}}/>}
				<RateListing style={{padding:'0 30px 0 30px'}}/>
			</div>
		);
	}
	onChange() {
		this.setState(RateStore.getState());
	}
	selectRateClassType(event) {
		RateActions.selectRateClassType(event.target.value);
	}
}