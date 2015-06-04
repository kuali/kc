import React from 'react/addons';
import ReactRouter from 'react-router';
import {find} from 'lodash';
import RateActions from '../../stores/RateActions';
import { SelectedOption } from '../shared/SelectedOption';
import {StyledSelect} from '../shared/StyledSelect';

export class SelectedOptionsFilter extends React.Component {
	constructor(props) {
		super(props);
		this.onChange = this.onChange.bind(this);
		this.removeSelected = this.removeSelected.bind(this);
	}
	render() {
		let style = {
			select : {
				width: 300,
				display: 'block',
			}
		}
		let options = this.props.options.filter((option) => {
			return this.props.selectedOptionCodes.indexOf(option[this.props.optionCodeProp]) == -1;
		}).map((option) => {
			return (
				<option value={option[this.props.optionCodeProp]} key={option[this.props.optionCodeProp]}>{option[this.props.optionDescProp]}</option>
			);
		});
		options.splice(0, 0, (<option value='' key=''>select...</option>));
		let selectedOptions = this.props.selectedOptionCodes.map((optionId) => {
			let option = find(this.props.options, this.props.optionCodeProp, optionId);
			return (
				<SelectedOption id={option[this.props.optionCodeProp]} text={option[this.props.optionDescProp]} callback={this.removeSelected}/>
			);
		});

		return (
			<span style={this.props.style}>
				<label style={{display : 'block'}} htmlFor="rateClass">{this.props.label}</label>
				<StyledSelect style={style.select} id="rateClass" name="rateClass" onChange={this.onChange}>{options}</StyledSelect>
				{selectedOptions}
			</span>
		);
	}
	onChange(event) {
		this.props.onSelect(event.target.value);
	}
	removeSelected(id) {
		this.props.onRemove(id);
	}
};
SelectedOptionsFilter.defaultProps = { optionCodeProp : 'code', optionDescProp : 'description' };