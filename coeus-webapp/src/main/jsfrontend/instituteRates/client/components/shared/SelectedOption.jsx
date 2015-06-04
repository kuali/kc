import React from 'react/addons';

export class SelectedOption extends React.Component {
	constructor(props) {
		super(props);
		this.onClick = this.onClick.bind(this);
	}
	render() {
		let style = {
			button : {
				background : 'rgb(66,117, 136)',
				borderRadius: 6,
				padding: '5px 30px 5px 15px',
				color: 'white',

			}
		}
		return (
			<button style={style.button} onClick={this.onClick}>{this.props.text}</button>
		);
	}
	onClick(event) {
		this.props.callback(this.props.id);
	}
};