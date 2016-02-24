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
import ReactRouter from 'react-router';
import {assign} from 'lodash';

export class StyledSelect extends React.Component {
	constructor(props) {
		super(props);
	}
	render() {
		let styles = {
			select : {
  				WebkitAppearance: 'button',
				WebkitBorderRadius: 2,
				WebkitBoxShadow: '0px 1px 3px rgba(0, 0, 0, 0.1)',
				WebkitPaddingEnd: '20px',
				WebkitPaddingStart: '2px',
				WebkitUserSelect: 'none',
				backgroundImage: 'url(http://i62.tinypic.com/15xvbd5.png), -webkit-linear-gradient(#FAFAFA, #F4F4F4 40%, #E5E5E5)',
				backgroundPosition: '97% center',
				backgroundRepeat: 'no-repeat',
				border: '1px solid #AAA',
				color: 555,
				fontSize: 'inherit',
				margin: '0px 0 20px 0',
				overflow: 'hidden',
				padding: '5px 10px',
				textOverflow: 'ellipsis',
				whiteSpace: 'nowrap',
				width: 100,
			}
		};

		return (
			<select style={assign(styles.select, this.props.style)} id={this.props.id} name={this.props.name} value={this.props.value} onChange={this.props.onChange}>{this.props.children}</select>
		);
	}
}