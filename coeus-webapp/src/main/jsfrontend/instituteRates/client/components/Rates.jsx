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
				<div>INSTITUTE RATES for {this.state.selectedUnit.unitName}</div>
				<hr/>
				{!this.state.editMode && <RateSelection/>}
				<hr/>
				<RateListing/>
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