import React from 'react/addons';
import ReactRouter from 'react-router';
import RateActions from '../../stores/RateActions';

export class OnOffCampusToggles extends React.Component {
	constructor(props) {
		super(props);
	}
	render() {
		let styles = {
			label : {
				display: 'block',
				margin : '10px 0 10px 0',
			}
		}
		return (
			<div style={this.props.style}>
				<label style={styles.label}><input type="checkbox" id="onCampus" name="onCampus" checked={this.props.showOnCampus} onChange={this.toggleOnCampus}/>On Campus</label>
				<label style={styles.label}><input type="checkbox" id="offCampus" name="offCampus" checked={this.props.showOffCampus} onChange={this.toggleOffCampus}/>Off Campus</label>
			</div>
		)
	}
	toggleOnCampus(event) {
		RateActions.toggleOnCampusFlag();
	}
	toggleOffCampus(event) {
		RateActions.toggleOffCampusFlag();
	}
};	