const React = require('react/addons');
const {Rates} = require('./components/Rates');

class App extends React.Component {
	render() {
		let styles = {
			container : {
				height: '100%',
				fontSize: '1.2em',
			}
		};

		return (
			<div style={styles.container}>
				<Rates/>
			</div>
		);
	}
}

React.render(<App/>, document.body);
