import {assign} from 'lodash';

const colors = {
	secondaryColor: 'rgb(66, 117, 136)',
};
const styles = {
	filterLabels : {
		display: 'block',
		color: colors.secondaryColor,
		textTransform: 'uppercase',
	}
};

export default assign({}, styles, colors);